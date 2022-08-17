import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        GameProgress save1 = new GameProgress(100, 48, 20, 546.43);
        GameProgress save2 = new GameProgress(10, 90, 95, 66.52);
        GameProgress save3 = new GameProgress(65, 8, 40, 189.39);

        saveGame(save1, "D://Games/savegames/save1.dat");
        saveGame(save2, "D://Games/savegames/save2.dat");
        saveGame(save3, "D://Games/savegames/save3.dat");

        zipFiles("D://Games/savegames/zip.zip",
                "D://Games/savegames/save1.dat",
                "D://Games/savegames/save2.dat",
                "D://Games/savegames/save3.dat");

    }

    public static void saveGame(GameProgress save, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream cos = new ObjectOutputStream(fos))
        {
            cos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipFile, String...list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile, true))) {

            for (String filesx : list) {
                File fileToZip = new File(filesx);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry entry = new ZipEntry(fileToZip.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
                fileToZip.delete();
            }
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
