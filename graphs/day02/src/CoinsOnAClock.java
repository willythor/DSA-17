import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinsOnAClock {

    // T(n) = 3^n
    // T(n-1) = 3T(n-2)
    // T(n-2) = 3T(n-3)

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        char[] clock = new char[hoursInDay];
        char[] coins = new char[] {'p','n','d'};
        char[] tempClock;
        List<char[]> res = new ArrayList<>();
        List<char[]> tempRes;
        int hour=0;
        int tempPennies;
        int tempNickels;
        int tempDimes;
        
        int i = 0;
            tempClock = Arrays.copyOf(clock,clock.length);
            for (char c: coins){
                tempDimes = 0;
                tempNickels = 0;
                tempPennies = 0;

                if (c == 'p') {
                    hour = 1;
                    tempPennies=1;
                }
                else if (c == 'n'){
                    hour = 5;
                    tempNickels=1;
                }
                else if (c == 'd'){
                    hour = 10;
                    tempDimes=1;
                }
                hour = (hour+i)%hoursInDay;
                tempClock[i] = c;
                tempRes = coinsOnAClock(tempClock,hour,pennies-tempPennies,nickels-tempNickels,dimes-tempDimes,hoursInDay);
                if (tempRes!=null){
                    res.addAll(tempRes);
                }
            }

        return res;

    }

    public static List<char[]> coinsOnAClock(char[] clock, int hour, int pennies, int nickels, int dimes, int hoursInDay){
        List<char[]> res = new ArrayList<>();
        List<char[]> tempRes = new ArrayList<>();
        char[] tempClock;
        int tempHour;

        if (pennies == 0 && nickels == 0 && dimes == 0){
            res.add(clock);
            return res;
        }

        if (clock[hour]!=0){
            return null;
        }

        char[] coins = new char[] {'p','n','d'};
        for (char c: coins){
            tempClock = Arrays.copyOf(clock,clock.length);
            if (c == 'p'){
                if (pennies == 0) {
                    continue;
                }
                tempHour = 1;
                tempHour = (hour+tempHour)%hoursInDay;
                tempClock[hour] = 'p';
                tempRes = coinsOnAClock(tempClock,tempHour,pennies-1,nickels,dimes,hoursInDay);
                if (tempRes!=null){
                    res.addAll(tempRes);
                }
            }

            if (c == 'n'){
                if (nickels == 0) {
                    continue;
                }
                tempHour = 5;
                tempHour = (hour+tempHour)%hoursInDay;
                tempClock[hour] = 'n';
                tempRes = coinsOnAClock(tempClock,tempHour,pennies,nickels-1,dimes,hoursInDay);
                if (tempRes!=null){
                    res.addAll(tempRes);
                }
            }

            if (c == 'd'){
                if (dimes == 0) {
                    continue;
                }
                tempHour = 10;
                tempHour = (hour+tempHour)%hoursInDay;
                tempClock[hour] = 'd';
                tempRes = coinsOnAClock(tempClock,tempHour,pennies,nickels,dimes-1,hoursInDay);
                if (tempRes!=null){
                    res.addAll(tempRes);
                }
            }

        }
        if (res.size()==0){
            return null;
        }
        return res;

    }

    public static void main (String[] args){
        List<char[]> test = coinsOnAClock(2, 2, 2, 6);
        System.out.println(test.size());
        for (char[] m: test){
            System.out.println(Arrays.toString(m));
        }
    }
}

