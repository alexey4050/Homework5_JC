import java.io.*;
import java.nio.ByteBuffer;

/**Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
        и представляют собой, например, состояния ячеек поля для игры в крестики-нолики,
        где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение.
        Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
        Записать в файл 9 значений так, чтобы они заняли три байта. */
public class TicTacToe {
    public static void main(String[] args) {
        int[] field = {1, 0, 2, 3, 2, 0, 1, 3, 1};
        String filename = "field.bin";

        //записываем 9 значений в файл
        try (OutputStream outputStream = new FileOutputStream(filename)) {
            ByteBuffer buffer = ByteBuffer.allocate(3);
            for(int i = 0; i < field.length; i += 3){
                buffer.clear();
                buffer.put((byte) (field[i] & 0xFF));
                buffer.put((byte) (field[i + 1] & 0xFF));
                buffer.put((byte) (field[i + 2] & 0xFF));
                outputStream.write(buffer.array());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //распаковка файла и отсортировка поля
        try (InputStream inputStream = new FileInputStream(filename)){
            byte[] bytes = new byte[3];
            int bytesRead;
            while ((bytesRead = inputStream.read(bytes)) != -1){
                for (int i = 0; i < bytesRead; i++) {
                    int value = bytes[i] & 0xFF;
                    char symbol;
                    switch (value){
                        case 0:
                            symbol = '.';
                            break;
                        case 1:
                            symbol = 'x';
                            break;
                        case 2:
                            symbol = 'o';
                            break;
                        default:
                            symbol = ' ';
                            break;
                    }
                    System.out.println(symbol);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
