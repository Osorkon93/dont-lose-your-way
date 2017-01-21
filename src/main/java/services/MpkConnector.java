package services;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;


public class MpkConnector {

    public String getDatabaseUrl() throws IOException {
        String url = "http://m.rozklady.mpk.krakow.pl/Services/data.asmx/GetDatabase";
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
        String query = String.format("{}");

        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

        try (OutputStream output = connection.getOutputStream()) {
            System.out.println(query);
            output.write(query.getBytes(charset));
        }
        InputStream response = connection.getInputStream();

        JSONObject jsonObject = new JSONObject(IOUtils.toString(response, charset));
        return (String)jsonObject.get("d");
    }

    public boolean downloadDatabase(String url, String destinationPath) {
        try {
            if(!Paths.get(destinationPath).toFile().exists())
                if (!Paths.get(destinationPath).toFile().getParentFile().mkdirs()) System.out.println("Can't create directory #MpkConnetor#downloadDatabase(): " + destinationPath);
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destinationPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
