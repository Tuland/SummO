package helper;
import java.io.File;
import java.io.FilenameFilter;

public class YamlFilter implements FilenameFilter{
	
    public boolean accept(File dir, String name) {
        return (name.endsWith(".yml"));
    }


}
