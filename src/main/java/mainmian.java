import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class mainmian {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\IdeaProjects\\mianshi\\src\\main\\resources\\jpeg\\基础");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
            file1.renameTo(new File(file1.getName()+".jpeg"));
        }
    }
}



