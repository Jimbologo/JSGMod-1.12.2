package tauri.dev.jsg.gui.mainmenu;

import tauri.dev.jsg.JSG;
import tauri.dev.jsg.config.JSGConfig;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class GetUpdate {

    public static final String ERROR_STRING = "Error was occurred while updating JSG!";

    public static final String URL_BASE = "https://api.justsgmod.eu/?api=curseforge&version=" + JSG.MC_VERSION;

    public static final String GET_NAME_URL = URL_BASE + "&t=name";
    public static final String GET_DOWNLOAD_URL = URL_BASE + "?t=url";
    public static final String GET_SIZE_URL = URL_BASE + "?t=size";

    public static double percentOfFileDownloaded = 0;

    public static String checkForUpdate(String currentVersion){
        if(!JSGConfig.enableAutoUpdater) return "false";
        String gotVersion = getSiteContent(GET_NAME_URL).split("-")[2];
        if(gotVersion.equals(ERROR_STRING)) return "false";

        String[] currentVersionSplit = currentVersion.split("\\.");
        String[] gotVersionSplit = gotVersion.split("\\.");
        try {
            for (int i = 0; i < 4; i++) {
                if (gotVersionSplit.length < i + 1 || currentVersionSplit.length < i + 1)
                    continue;

                if (parseInt(currentVersionSplit[i]) < parseInt(gotVersionSplit[i])){
                    return gotVersion;
                }

                if (parseInt(currentVersionSplit[i]) > parseInt(gotVersionSplit[i])){
                    return "false";
                }
            }
        }
        catch(Exception ignored){}
        return "false";
    }

    public static boolean updateMod(String currentVersion, String alphaTag){
        return false;
        //if(checkForUpdate(currentVersion).equals("false") || checkForUpdate(currentVersion).equals(ERROR_STRING)) return false;
        // TODO(Mine): Do file download
        /*
        try {
            String[] url = getSiteContent(GET_NAME_URL).split("/");
            StringBuilder url_final = new StringBuilder();
            for(int i = 0; i < url.length; i++) {
                if (url[i].equals("files"))
                    url[i] = "download";
                url_final.append(url[i]);
            }

            try (InputStream in = URI.create(String.valueOf(url_final)).toURL().openStream()) {
                Files.copy(in, Paths.get(MINECRAFT_ROOT + "jsg-1.12.2-" + currentVersion + alphaTag + ".jar"));
                deleteQuietly(new File(MINECRAFT_ROOT + "jsg-1.12.2-" + currentVersion + alphaTag + ".jar"));
            }

            //copyURLToFile(new URL(GET_DOWNLOAD_URL), new File(MINECRAFT_ROOT + url_final), 1000000000, 1000000000);
            return true;
        }
        catch(Exception ignored){}
        return false;*/
    }

    public static void updatePercents() {
        try {
            long downloadedFileSize = Files.size(Paths.get(JSG.clientModPath));
            final long targetFileSize = Long.parseLong(getSiteContent(GET_SIZE_URL));
            percentOfFileDownloaded = (double) (downloadedFileSize/targetFileSize)*100;
        }
        catch(Exception ignored){
            JSG.error("Error while getting update percents!");
            JSG.error("GetUpdate.java::78-88::updatePercents");
        }
    }

    public static double getPercents(){
        updatePercents();
        return percentOfFileDownloaded;
    }


    public static String getSiteContent(String link) {
        URL Url;
        try {
            Url = new URL(link);
        } catch (MalformedURLException e1) {
            return ERROR_STRING;
        }
        HttpURLConnection Http;
        try {
            Http = (HttpURLConnection) Url.openConnection();
        } catch (IOException e1) {
            return ERROR_STRING;
        }
        if(Http == null) return ERROR_STRING;
        Map<String, List<String>> Header = Http.getHeaderFields();

        try {
            for (String header : Header.get(null)) {
                if (header.contains(" 302 ") || header.contains(" 301 ")) {
                    link = Header.get("Location").get(0);
                    try {
                        Url = new URL(link);
                    } catch (MalformedURLException e) {
                        return ERROR_STRING;
                    }
                    try {
                        Http = (HttpURLConnection) Url.openConnection();
                    } catch (IOException e) {
                        return ERROR_STRING;
                    }
                    Header = Http.getHeaderFields();
                }
            }
        }
        catch(Exception ignored){
            return ERROR_STRING;
        }

        InputStream Stream;
        try {
            Stream = Http.getInputStream();
        } catch (IOException e) {
            return ERROR_STRING;
        }
        String Response;
        try {
            Response = GetStringFromStream(Stream);
        } catch (IOException e) {
            return ERROR_STRING;
        }
        return Response;
    }

    private static String GetStringFromStream(InputStream Stream) throws IOException {
        if (Stream != null) {
            Writer Writer = new StringWriter();

            char[] Buffer = new char[2048];
            try {
                Reader Reader = new BufferedReader(new InputStreamReader(Stream, "UTF-8"));
                int counter;
                while ((counter = Reader.read(Buffer)) != -1) {
                    Writer.write(Buffer, 0, counter);
                }
            } finally {
                Stream.close();
            }
            return Writer.toString();
        } else {
            return ERROR_STRING;
        }
    }
}
