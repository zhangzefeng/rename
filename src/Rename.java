import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rename {

    public static void main(String[] args) {
        for (File f : new File(".").listFiles()) {
            if (f.isFile()) {
                String n = f.getName();
                int p = n.lastIndexOf('.');
                if (p >= 0) {
                    String prefix = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(f.lastModified()));
                    String suffix = n.substring(p);
                    for (int i = 0; ; i++) {
                        String nn = prefix + (i > 0 ? "-" + i : "") + suffix;
                        if (nn.equals(n)) {
                            System.out.println("skip " + n);
                            break;
                        }
                        File nf = new File(nn);
                        if (!nf.exists()) {
                            System.out.println(n + " => " + nn);
                            f.renameTo(nf);
                            break;
                        }
                    }
                }
            }
        }
    }
}
