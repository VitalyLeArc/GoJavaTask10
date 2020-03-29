package classes;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class StallSaveLoad {
    public static Shop newStall(int shopIndex,int shopMoney,String dataBasePath){
        Shop shop;
        shop = new Shop(shopIndex,shopMoney,dataBasePath);
        save(shop);
        return shop;
    }
    public static void save(Shop shop){
        try {
            File file = new File(shop.getDataBasePath()+"Shop"+shop.getShopIndex()+".txt");
            FileWriter fw = new FileWriter(file);
            fw.write(JSON.toJSONString(shop));
            fw.close();
            System.out.println("Сохранён ларёк");
        }catch (Exception e){
            System.out.println("Не удалось");
        }
    }
    public static Shop load(String pathToShopSaveFile){
        String json="";
        try {
            File file = new File(pathToShopSaveFile);
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                json += scan.nextLine();
            }

        }catch(Exception e){
            System.out.println("Не удалось");
        }
        Shop shop = JSON.parseObject(json,Shop.class);
        System.out.println("Загружено сохранение ларька");
        return shop;
    }
}
