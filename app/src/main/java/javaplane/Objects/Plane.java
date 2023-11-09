package javaplane.Objects;
/*
Якщо різниця кількості палива в баках досягла величини 200 кгс, і
спалахнуло табло "ДИСБАЛАНС ПАЛИВА",
усуньте дисбаланс палива з точністю до ±50 кгс таким чином:
- відкрийте кран кільцювання;
- відключіть насоси бака з меншою кількістю палива та витрачайте на обидва двигуни різницю палива з бака з більшою кількістю палива;
- після вирівнювання кількості палива в баках увімкніть відключені насоси;
- закрийте кран кільцювання.
 */
//object from VIPER architecture
public class Plane {
    //баки
    public FuelTank leftTank = new FuelTank("Лівий", 100, 100);
    public FuelTank rightTank = new FuelTank("Правий", 100, 100);
    public double fuelConsumptiondx = 1.0;
    //насоси
    public Pump leftPump = new Pump("Лівий", leftTank, fuelConsumptiondx);
    public Pump leftPump2 = new Pump("Лівий2", leftTank, fuelConsumptiondx);
    public Pump rightPump = new Pump("Правий", rightTank, fuelConsumptiondx);
    public Pump rightPump2 = new Pump("Правий2", rightTank, fuelConsumptiondx);
    //кран кільцювання
    public Boolean isRingOn = false;
    //пожежні крани, їх роботу не моделюємо
    public Boolean leftEngineValve = true;
    public Boolean rightEngineValve = true;

    public Boolean getFuelDisbalance(){
        return Math.abs(leftTank.fuel - rightTank.fuel) > 200;
    }
    public Boolean getFuelBalanceGood(){
        return Math.abs(leftTank.fuel - rightTank.fuel) < 50;
    }
    public void tick(double dt){
        //висмоктуємо паливо з баків насосами
        try {
            double leftEngineFlow = leftPump.consume(dt) + leftPump2.consume(dt);
            double rightEngineFlow = rightPump.consume(dt) + rightPump2.consume(dt);
            //кільцювання рівномірно розподіляє паливо між двигунами
            if(isRingOn){
                double ringFlow = (leftEngineFlow + rightEngineFlow)/2;
                //не реалізовано даним варіантом
            }   
            if(!leftEngineValve && !rightEngineValve){
                throw new RuntimeException("Обидва двигуни вимкнені!");
            }
            
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}