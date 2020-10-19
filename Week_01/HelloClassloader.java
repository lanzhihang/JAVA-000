package Week01;

import java.io.*;
import java.lang.reflect.Method;

public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Class<?> aClass=  new HelloClassloader().findClass("D:\\Hello.xlass");
        Method hello=aClass.getDeclaredMethod("hello");
        hello.invoke(aClass.newInstance());
    }

    @Override
    protected Class<?> findClass(String name)  {
        byte[] bytes = new byte[0];
        try {
            bytes = fileToBytes(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(null,bytes,0,bytes.length);
    }

    private byte[] fileToBytes(String name) throws IOException {
        FileInputStream fis = new FileInputStream(new File(name));
        byte[] b = new byte[1];
        byte[] b1 = new byte[1];
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        int n;
        while ((n = fis.read(b)) != -1) {
            b1[0] = (byte) (255 - b[0]);
            bos.write(b1, 0, n);
        }
        return bos.toByteArray();
    }
}
