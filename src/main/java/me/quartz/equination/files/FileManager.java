package me.quartz.equination.files;

public class FileManager {

    private final CustomFile horsesFile;

    public FileManager() {
        this.horsesFile = new CustomFile("horses");
    }

    public CustomFile getHorsesFile() {
        return horsesFile;
    }
}
