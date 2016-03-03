import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Kings on 2016/3/4.
 */
public class TestCase {

    @Test
    public void calc() {
        final double finit = 4812.25 + 23676.87 + 5987.1 + 2985.98 - 30000;
        final double fupdate = 1916.66 + 524.4 + 3333.33 + 528 + 2500;
        final double fdayrate = 0.0005;
        final double frepay = 8100.00 - 1300.00 - 200.00 - 300.00 - 1000.00 - 150.00;
        final int fistartm = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int fistarty = Calendar.getInstance().get(Calendar.YEAR);

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, 7);
        final int fgfcalc = cal.get(Calendar.MONTH) - fistartm + 12 * (cal.get(Calendar.YEAR) - fistarty);//16;
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, 4);
        final int fzxcalc = cal.get(Calendar.MONTH) - fistartm + 12 * (cal.get(Calendar.YEAR) - fistarty);//25;
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, 10);
        final int fjscalc = cal.get(Calendar.MONTH) - fistartm + 12 * (cal.get(Calendar.YEAR) - fistarty);//19;

        double overpay = 0;

        while (overpay < 1001) {
            String startmd = "N/A";
            System.out.println("");
            System.out.println("");
            System.out.println(String.format("=========================额外多还款%s的情形========================", overpay));

            int vgfcalc = fgfcalc;
            int vzxcalc = fzxcalc;
            int vjscalc = fjscalc;

            double vinit = finit;
            System.out.printf("初始欠费 " + vinit);
            int vistartm = fistartm;
            int vistarty = fistarty;
            double vupdate = fupdate;
            double blt = 0;
            boolean setstartmd = false;
            double allpay = 0;

            while (vinit > 0 || vgfcalc > 0 || vzxcalc > 0 || vjscalc > 0) {
                vgfcalc--;
                vzxcalc--;
                vjscalc--;
                int mtd = 30;
                switch (vistartm) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        mtd = 31;
                        break;
                    case 2:
                        mtd = 28;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        mtd = 30;
                }
                double monthrate = fdayrate * mtd;

                vinit += vupdate;
                double pt = vinit;
                double pec = vinit * monthrate;
                vinit += pec;
                double payover = frepay;
                double left = vinit * 0.1 - payover;
                if (left > 0) {
                    payover = vinit * 0.1;
                    blt += left;
                    if (!setstartmd) {
                        startmd = vistarty + "-" + vistartm;
                        setstartmd = true;
                    }
                }
                vinit -= payover;
                if (left < 0)
                    vinit -= overpay;
                allpay += payover;
                System.out.println(String.format("%s-%s 基数:%s,增加:%s,利息%s,最低还款%s,本月还款%s,还完,额外多还%s,需要额外借钱%s,总借款%s,从%s开始,还了总共%s", vistarty, vistartm, pt, vupdate, pec, vinit * 0.1, payover, overpay, left > 0 ? left : 0, blt, startmd, allpay));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                vistartm++;
                if (vistartm > 12) {
                    vistartm = 1;
                    vistarty += 1;
                    vinit += 5000;
                }
                if (vgfcalc == 0)
                    vupdate -= 3333.33 + 528;
                if (vzxcalc == 0)
                    vupdate -= 1916.66 + 524.4;
                if (vjscalc == 0)
                    vupdate -= 2500;
                if (vupdate < 1)
                    vupdate = 0.0;
            }
            System.out.println(String.format("=========================额外多还款%s的情形========================", overpay));
            overpay += 100;
        }

    }
}
