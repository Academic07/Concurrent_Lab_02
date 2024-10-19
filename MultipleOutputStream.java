import java.io.IOException;
import java.io.OutputStream;

public class MultipleOutputStream extends OutputStream {
    private OutputStream[] outputs;

    public MultipleOutputStream(OutputStream... outputs) {
        this.outputs = outputs;
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream out : outputs) {
            out.write(b);
        }
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream out : outputs) {
            out.flush();
        }
    }

    @Override
    public void close() throws IOException {
        for (OutputStream out : outputs) {
            out.close();
        }
    }
}
