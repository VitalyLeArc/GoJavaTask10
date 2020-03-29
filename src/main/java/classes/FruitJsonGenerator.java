package classes;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
// чисто вспомогательный класс чтобы создать базовые файлы для поставки
public class FruitJsonGenerator {
    //функция принимает набор параметров чтобы можно было создать фрукт 1 и 2 по конструктору
    //можно
    public static void newFruitJson(String fileName, Fruit.fruitsType type, int shelfLife, int cost
    , Fruit.fruitsType type2, int shelfLife2, int cost2, String initialDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  //формат принимаемой даты в стринге
        try {
            LocalDate ld ;
            ld = LocalDate.parse(initialDateString, formatter);   //подготовка даты в формате даты из стринга
            ArrayList<Fruit> fruits = new ArrayList<>();
            fruits.add(new Fruit(type,shelfLife,ld,cost));
            fruits.add(new Fruit(type2,shelfLife2,ld,cost2));
            FileWriter fw = new FileWriter("src/main/db/"+fileName+".txt");
            fw.write(JSON.toJSONString(fruits));                    //запись в файл того что вышло
            fw.close();
            System.out.println("success");
        }
        catch (Exception e){
            System.out.println("неверный ввод");
        }

    }
}


/*Шпаргалка
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateInString = "07/06/2013";
            try {
                LocalDate ld = LocalDate.parse(dateInString, formatter);
                System.out.println(ld.format(formatter));
            }
            catch (Exception e){
                System.out.println("Вронг");
            }
* */
