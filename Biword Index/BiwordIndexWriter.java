import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BiwordIndexWriter {
    public static void writeBiwordIndex(BiwordIndex biwordIndex, String newFileName) {

        Set<String> treeSet = new TreeSet<>(biwordIndex.getBiwordIndex().keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < biwordIndex.getFiles().size(); i++) {
                writer.write(i + " = " + new File(biwordIndex.getFiles().get(i)).getName() + System.lineSeparator());
            }
            for (String fileName : treeSet) {
                writer.write(System.lineSeparator());

                List<String> values = biwordIndex.getBiwordIndex().get(fileName);
                writer.write(biwordIndex.getFiles().indexOf(fileName) + ": " + System.lineSeparator());
                for (String value : values) {
                    writer.write(value + System.lineSeparator());
                }
                writer.write(System.lineSeparator());

                System.out.println(fileName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}