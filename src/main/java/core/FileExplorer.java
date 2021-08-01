package core;

import model.File;
import model.SampleFile;
import shared.FileManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FileExplorer implements FileManager {
    private File root;
private Deque<File> exam;

    public FileExplorer() {
        this.root = new SampleFile(1, "Root");
        this.exam = new ArrayDeque<>();
    }


    @Override
    public void addInDirectory(int directorNumber, File file) {
        File test = getFileByKey(directorNumber);
        test.getChildren().add(file);
    }

    private File getParentByKey(int key){
        return getFile(true, key);
    }
    private File getFileByKey(int key){
        return getFile(false, key);
    }

    private File getFile(boolean returnParent, int key){
        ArrayDeque<File> deque = new ArrayDeque<>();
        if(this.root.getNumber()==key){
            return this.root;
        }
        deque.offer(this.root);
        while(!deque.isEmpty()){
            File file = deque.poll();
            for(File f: file.getChildren()){
                if(f.getNumber()==key){
                    return returnParent?file:f;
                }
                deque.offer(f);
            }
        }
        throw new IllegalStateException("Node not found");
    }

    @Override
    public File getRoot() {
        return this.root;
    }

    @Override
    public File get(int number) {
        return getFileByKey(number);
    }

    @Override
    public Boolean deleteFile(File file) {
        try {
            if (file.getNumber() == root.getNumber()) {
                this.root = null;
                return true;
            }
            File f = getFile(true, file.getNumber());
            f.getChildren().removeIf(e -> e.getNumber() == file.getNumber());
            return true;
        } catch (IllegalStateException ignored) {
            return false;
        }
    }

    @Override
    public List<File> getFilesInPath(File path) { return getFileByKey(path.getNumber()).getChildren();
    }

    @Override
    public void move(File file, File destination) {
        if (file.getNumber() == root.getNumber()) {
            throw new IllegalStateException();
        }
        this.deleteFile(file);
        getFileByKey(destination.getNumber()).getChildren().add(file);
    }

    @Override
    public Boolean contains(File file) {

        try {
            getFileByKey(file.getNumber());
            return true;
        } catch (IllegalStateException ignored) {
            return false;
        }

    }

    @Override
    public List<File> getInDepth() {
        List<File> files = new ArrayList<>();
        dfs(this.root, files);
        return files;
    }

    private void dfs(File file, List<File> files) {
        if (file == null) {
            return;
        }

        files.add(file);

        for (File child : file.getChildren()) {
            dfs(child, files);
        }
    }

    @Override
    public List<File> getInLevel() {
        List<File> test = new ArrayList<>();
        ArrayDeque<File> deque = new ArrayDeque<>();
        deque.offer(this.root);
        while (!deque.isEmpty()) {
            File file = deque.poll();
            test.add(file);
            for (File f : file.getChildren()) {
                deque.offer(f);
            }
        }
        return test;
    }

    @Override
    public void cut(int number) {
        File file = get(number);
        deleteFile(file);
        this.exam.push(file);
    }

    @Override
    public void paste(File destination) {
        File file = getFileByKey(destination.getNumber());
        File latestStored = this.exam.pop();
        file.getChildren().add(latestStored);
    }

    @Override
    public Boolean isEmpty() {
        return this.root.getChildren().isEmpty();
    }

    @Override
    public String getAsString() {
        if (this.root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        print(this.root, builder, "", "");
        return builder.toString().trim();
    }

    private void print(File file, StringBuilder builder, String prefix, String childrenPrefix) {
        builder.append(prefix);
        builder.append(file.getNumber());
        builder.append(System.lineSeparator());
        List<File> children = file.getChildren();
        for (int i = 0; i < children.size(); i++) {
            File next = children.get(i);
            if (i < children.size() - 1) {
                print(next, builder, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                print(next, builder, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
