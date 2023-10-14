package io.gocklkatz;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class Wiki {

    public static void main(String[] args) throws IOException {

        /*  *** *** ***  *** *** ***  *** *** ***
         *
         *  interface java.nio.file.Path !!!
         *  class java.nio.file.Files !!!
         *
         * *** *** ***  *** *** ***  *** *** ***/

        //pathBlock();
        filesBlock();

    }

    public static void filesBlock() throws IOException {
        // ---  ---  ---  ---  ---
        // Files.java

        Path path = Path.of("test.txt");
        System.out.println(Files.size(path));
        for(String line : Files.readAllLines(path)) {
            System.out.println(line);
        }

        /*
        try (InputStream is = new FileInputStream(path.toFile())) {
            int i = 0;
            while ((i = is.read()) != -1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        try (InputStream is = new FileInputStream(path.toFile())) {
            int i = 0;
            while ((i = is.read()) != -1) {
                if (i != 10) {
                    char c = (char) i;
                    System.out.print(c + " ");
                } else {
                    System.out.print("\\n ");
                }
            }
        }
         */

        // readAllBytes()
        byte[] readAllBytes = Files.readAllBytes(path);
        System.out.println(Arrays.toString(readAllBytes));

        // readAllLines()
        List<String> readAllLines1 = Files.readAllLines(path);
        readAllLines1.forEach(System.out::println);
        List<String> readAllLines2 = Files.readAllLines(path, StandardCharsets.UTF_8);
        readAllLines2.forEach(System.out::println);
        List<String> readAllLines3 = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
        readAllLines3.forEach(System.out::println);

        // readString()
        String readString1 = Files.readString(path);
        System.out.println("Readstring1: " + readString1);
        String readString2 = Files.readString(path, StandardCharsets.UTF_8);
        // Alternative vor Java 22
        String readString3 = new String( Files.readAllBytes(path), StandardCharsets.UTF_8);

        Path pathWrite = Path.of("test2.txt");
        // write() bytes
        byte[] bytesWrite = new byte[] {109, 110, 111};
        Files.write(pathWrite, bytesWrite);
        // write() Iterable<? extends CharSequence>
        List<String> stringList = new ArrayList<>();
        stringList.add("pqr");
        stringList.add("stu");
        Files.write(pathWrite, stringList, StandardCharsets.UTF_8);
        // writeString()
        Files.writeString(pathWrite, "some String", StandardCharsets.UTF_8);

        // lines(), you need to CLOSE the stream. I dont care if you cross it, you need to close it!
        try (Stream<String> stringStream = Files.lines(path, StandardCharsets.UTF_8)) {
            System.out.println("Lines as Stream");
            stringStream.forEach(System.out::println);
        }

        // copy
        Path test3Path = Path.of("test3.txt");
        Files.deleteIfExists(test3Path);
        try (InputStream is = new FileInputStream("test.txt")) {
            Files.copy(is, test3Path);
        }

        try (OutputStream os = new FileOutputStream("test4.txt")) {
            Files.copy(test3Path, os);
        }

    }

    public static void pathBlock() throws IOException {
        String fileName = "test.txt";

        //java.io
        File file;

        //java.nio.file
        Path path;
        Paths paths;
        Files files;
        FileSystem fileSystem;

        //Get a file system
        FileSystem fs = FileSystems.getDefault();
        //Get a path
        Path p1 = Path.of(fileName);

        // ---  ---  ---  ---  ---
        // Path.java

        //Through FileSystem.getPath(), the long way, many are through FileSystem ...
        FileSystem myFileSystem = FileSystems.getDefault();
        Path myPath = myFileSystem.getPath(fileName);
        //Through Paths.get()
        Path myOtherPath = Paths.get(fileName);
        //Through Path.of()
        Path myYetAnotherPath = Path.of(fileName);
        //Through file.toPath()
        Path myYetYetAnotherPath = new File(fileName).toPath();

        //Paths.get() with String
        Path pathFromString = Paths.get("test.txt");
        //Paths.get with URI
        URI uri = URI.create("file:///Users/katzi/Documents/Projects/asamander/test.txt");
        Path pathFromUri = Paths.get(uri);
        //Path.of() with String
        Path pathFromString2 = Path.of("test.txt");
        //Path.of() with URI
        Path pathFromUri2 = Path.of(uri);
        //Get FileSystem from Path
        FileSystem gotAfileSystem = pathFromUri2.getFileSystem();

        System.out.println("*** *** ***");

        System.out.println();
        Path p = Path.of("/Users/katzi/Documents/Projects/asamander/test.txt");
        System.out.println(p.toString());
        System.out.println(p.isAbsolute());
        System.out.println(p.getRoot());
        System.out.println(p.getParent());
        System.out.println(p.getNameCount());
        System.out.println(p.getName(p.getNameCount()-2));

        System.out.println();
        System.out.println("*** *** ***");
        System.out.println();

        //toString(), toUri(), toFile()

        // Path.resolve()
        Path pp = Path.of(System.getProperty("user.dir")).resolve("test.txt");
        System.out.println(pp);
        System.out.println("test.txt exists: " + pp.toFile().exists());

        //System.out.println(System.getProperty("user.home"));
        //Properties properties = System.getProperties();
        //for (Object o : properties.keySet()) {
        //    System.out.println(o + ": " + properties.get(o));
        //}

        // Path.resolveSibling()
        Path test2 = pp.resolveSibling("test2.txt");
        System.out.println("test2.txt exists: " + Files.exists(test2));

        // Path.relativize()
        System.out.println("Relativize: " + pp.relativize(test2));

        // Path.normalize()
        Path relative = Path.of("../..");
        Path combine = Path.of(System.getProperty("user.dir")).resolve(relative);
        System.out.println(combine);
        System.out.println(combine.normalize());

        System.out.println(relative.toAbsolutePath());
        System.out.println(relative.toRealPath(LinkOption.NOFOLLOW_LINKS));

        System.out.println();
        System.out.println("*** *** ***");
        System.out.println();
    }
}
