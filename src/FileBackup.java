import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
public class FileBackup {

    public static  void main(String[] args){
        String sourceDir = "/path/to/source/directory";
        String backupDir = "/path/to/back/directory";

        try{
            createBackup(sourceDir, backupDir);
            System.out.println("Backup created successfully");
        } catch (IOException e){
            System.out.println("Error creating backup " + e.getMessage());
        }
    }

    public  static void  createBackup(String sourceDir, String backupDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File(backupDir);

        if(!sourceDirectory.isDirectory()){
            throw  new IllegalArgumentException("Source path is not a directory.");
        }

        if(!backupDirectory.exists()){
            backupDirectory.mkdirs();
        }
        File[] files = sourceDirectory.listFiles();
        if (files != null){
            for(File file : files){
                if(file.isFile()){
                    File backupFile = new File(backupDir + "/" + file.getName());
                    copyFile(file, backupFile);
                }
            }
        }
    }
    public static void copyFile(File sourceFile, File distinationFile) throws  IOException{
        try(FileInputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream outputStream = new FileOutputStream(distinationFile)){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
