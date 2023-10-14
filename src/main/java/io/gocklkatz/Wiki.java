package io.gocklkatz;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Properties;

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

    public static void filesBlock() {
        // ---  ---  ---  ---  ---
        // Files.java
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
