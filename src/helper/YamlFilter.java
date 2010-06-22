package helper;
import java.io.File;
import java.io.FilenameFilter;

/**
 * @author tuland
 *
 */
public class YamlFilter implements FilenameFilter{
	
    /* (non-Javadoc)
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept(File dir, String name) {
        return (name.endsWith(".yml"));
    }


}
