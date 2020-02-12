import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JUnitHTMLReporter  {
    static File junitReport;
    static BufferedWriter junitWriter;

    @BeforeClass
    public static void setUp() throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        junitReport = new File("logs/report.html");
        junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
        junitWriter.write("<html><body><h2>Test Execution Summary - " + dateFormat.format(date)
                + "</h2>");

    }

    @AfterClass
    public static void tearDown() throws IOException {

        junitWriter.write("</body></html>");
        junitWriter.close();

    }

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                junitWriter.write(description.getDisplayName() + " Passed<br/>");
            } catch (Exception e) {}
        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                junitWriter.write(description.getDisplayName() + " "
                        + e.getClass().getSimpleName() + " " + e.getMessage() + "<br/>");
            } catch (Exception ex) {}
        }
    };
}
