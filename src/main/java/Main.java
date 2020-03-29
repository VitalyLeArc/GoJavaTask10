import classes.Fruit;
import classes.Shop;
import classes.StallSaveLoad;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

       Shop StallOfFriend ;
        StallOfFriend = StallSaveLoad.load("src/main/db/Shop1.txt");                      //загрузка магаза

        StallOfFriend.load();
        StallOfFriend.addFruits("Aiva+Pear",true);                               //скармливаем файлы для добавления инфы в склад
        StallOfFriend.save();
        StallOfFriend.addFruits("Apple+Banan",true);
        StallOfFriend.addFruits("Cherry+Coconut",true);
        StallOfFriend.addFruits("Mango+Papaya",true);
        StallOfFriend.addFruits("Plum+Orange",true);
        StallOfFriend.save();
        System.out.println("Осталось "+StallOfFriend.getShopMoney()+" грн");
        StallOfFriend.printSpoiledFruits("20/05/2020");
        StallOfFriend.printAvaliableFruits("26/02/2020");
        ArrayList<Fruit> spoiledListByOverload = StallOfFriend.getSpoiledFruits(Fruit.fruitsType.Apple,"15/01/2020");                 //получили список испорченого
        ArrayList<Fruit> avaliableListByOverload = StallOfFriend.getAvaliableFruits(Fruit.fruitsType.Quince,"10/03/2020");            //получили список готового
        ArrayList<Fruit> addedList = StallOfFriend.getAddedFruits("28/02/2020");                                                      //получили список добавленного на дату
        ArrayList<Fruit> addedListByOverload = StallOfFriend.getAddedFruits(Fruit.fruitsType.Quince,"28/02/2020");                    //получили список добавленной айвы на дату





        StallSaveLoad.save(StallOfFriend);                                                                  //сохранение магаза
    }

}


