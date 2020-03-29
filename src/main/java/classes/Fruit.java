package classes;

import java.time.LocalDate;
public class Fruit{

    public enum fruitsType {Apple,Banana,Orange,Papaya,Pear,Plum,Quince,Cherry,Mango,Coconut}
    private fruitsType type;
    private int shelfLife;
    private LocalDate initialDate;
    private int cost;

    public Fruit(fruitsType type, int shelfLife, LocalDate initialDate, int cost){
        this.type = type;
        this.shelfLife=shelfLife;
        this.initialDate=initialDate;
        this.cost = cost;
    }

    //Геттеры, нужно для жсона
    public fruitsType getType(){
        return type;
    }
    public int getShelfLife(){
        return shelfLife;
    }
    public LocalDate getInitialDate(){
        return initialDate;
    }
    public int getCost() {
        return cost;
    }


}

/* Типы енума
*
* Apple - яблоко
* Banana - банан
* Orange - апельсин
* Papaya - папайя
* Pear - груша
* Plum - слива
* Quince - айва
* Coconut - кокос
* Cherry - вишня
* Mango - манго
* */
