package top.mixedinfos.reports.utils;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class DataTransformUtils {

    private static BASE64Encoder encoder = new BASE64Encoder();
    private static BASE64Decoder decoder = new BASE64Decoder();


    /**
     * 将base64 转换为制定的文件
     * @param base64sString base64字符串
     * @param path 文件的保存路径
     * @param filename 文件的名称(包含后缀)
     */
    public static void base64StrToPDF(String base64sString,String path,String filename) {
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            // 将base64编码的字符串解码成字节数组
            byte[] bytes =  decoder.decodeBuffer(base64sString);
            // apache公司的API
            // byte[] bytes = Base64.decodeBase64(base64sString);
            // 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出的文件
            File file = new File(path + filename);
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);

            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将bytes 转换为base64的字符串
     * @param bytes
     * @return
     * @throws IOException
     */
    public static String byteToBase64(byte[]bytes) throws IOException {
        String base64=  Base64Utils.encodeToString(bytes);
        return base64;
    }


}
