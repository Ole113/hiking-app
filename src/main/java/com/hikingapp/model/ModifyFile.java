package com.hikingapp.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Modifies a specified file by either writing, reading, or clearing the file's contents.
 */
public class ModifyFile {
    
    /**
     * Writes the data to the specified file.
     * @param chosenFile The file to write to.
     * @param data The data to write.
     * @param pos The position to start at in the file. The start of the file is position 0.
     * @throws IOException Catches any IO exceptions that are thrown.
     */
    public void writeToFile(File chosenFile, String data, int pos) throws IOException {
        RandomAccessFile file = new RandomAccessFile(chosenFile, "rw");
        file.seek(pos);
        file.write(data.getBytes());
        file.close();
    }
    
    /**
     * Reads from a specified file.
     * @param chosenFile The file to read from.
     * @param pos The position to start reading from.
     * @param size The amount of bytes to read.
     * @return The array of bytes that is the size of the parameter size.
     * @throws IOException The exception that can be thrown by RandomAccessFile.
     */
    public byte[] readFromFile(File chosenFile, int pos, int size) throws IOException {
        RandomAccessFile file = new RandomAccessFile(chosenFile, "r");  
        file.seek(pos);
        byte[] bytes = new byte[size];
        file.read(bytes);
        file.close();
        return bytes;
    }
    
    /**
     * Deletes all of the contents in the specified file.
     * @param chosenFile The file to clear the content.
     * @throws IOException The exception that can possibly be thrown.
     */
    public void clearContents(File chosenFile) throws IOException {
        RandomAccessFile file = new RandomAccessFile(chosenFile, "rw");
        file.setLength(0);
    }
}
