import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class StatStrive {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    // Player stats
    static int finance;
    static int inspiration;
    static int time = 0;
    static int stress;
    static int progress = 0;
    static boolean gameOver = false;

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("\n\n _______ _________ _______ _________   _______ _________ _______ _________          _______ \n" +
                "(  ____ \\\\__   __/(  ___  )\\__   __/  (  ____ \\\\__   __/(  ____ )\\__   __/|\\     /|(  ____ \\\n" +
                "| (    \\/   ) (   | (   ) |   ) (     | (    \\/   ) (   | (    )|   ) (   | )   ( || (    \\/\n" +
                "| (_____    | |   | (___) |   | |     | (_____    | |   | (____)|   | |   | |   | || (__    \n" +
                "(_____  )   | |   |  ___  |   | |     (_____  )   | |   |     __)   | |   ( (   ) )|  __)   \n" +
                "      ) |   | |   | (   ) |   | |           ) |   | |   | (\\ (      | |    \\ \\_/ / | (      \n" +
                "/\\____) |   | |   | )   ( |   | |     /\\____) |   | |   | ) \\ \\_____) (___  \\   /  | (____/\\\n" +
                "\\_______)   )_(   |/     \\|   )_(     \\_______)   )_(   |/   \\__/\\_______/   \\_/   (_______/\n" +
                "                                                                                            ");
        System.out.println("\nYou play as a character who have been assigned a project");
        System.out.println("You need to balance your work along with your health, stress, and creativity.");
        System.out.println("Your goal: Complete your project (Progress = 200%) before the year ends!");
        System.out.println("Following are conditions to achieve!");
        System.out.println("Progress = 200, Stress < 100, Inspiration > 0 and finance > 0");
        System.out.println("Be careful about your stats\n");
        System.out.println("===============================================================\n");

        selectDifficulty();
        showStats();

        // 12 months decision statements
        for (int monthNum = 1; monthNum <= 12 && !gameOver; monthNum++) {

            System.out.println("\n\n=================== MONTH " + monthNum + " ===================\n");

            //noinspection BusyWait
            Thread.sleep(300);

            performance(monthNum);
            choicesForMonth(monthNum);

            int choice = getChoice(1, 5);
            System.out.println("\nHint: " + hintsForChoices(monthNum, choice));

            applyChoiceEffects(monthNum, choice);
            time++;

            showStats();
            checkGameState(monthNum);
        }

        endSummary();
    }

    // ==================Select Difficulty===================

    static void selectDifficulty()
    {
        System.out.println("Choose Difficulty Level:");
        System.out.println("1. Easy (High finance, low stress)");
        System.out.println("2. Normal (Balanced start)");
        System.out.println("3. Hard (Low finance, high stress)");
        System.out.print("Enter choice (1-3): ");
        int choice;
        while(true){
            try {
                choice = input.nextInt();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Invalid Input! " + ex.getMessage());
                input.next();
            }
        }

        switch (choice)
        {
            case 1:{
                finance = 80;
                inspiration = 70;
                stress = 10;
                break;
            }
            case 2:{
                finance = 60;
                inspiration = 50;
                stress = 30;
                break;
            }
            case 3:{
                finance = 40;
                inspiration = 40;
                stress = 50;
                break;
            }
            default:{
                finance = 60;
                inspiration = 50;
                stress = 20;
                break;
            }
        }
    }

    // ===================== Show current stats =========================

    static void showStats()
    {
        System.out.println("\n-----------------------------------");
        System.out.println(" Finance: " + finance);
        System.out.println(" Inspiration: " + inspiration);
        System.out.println(" Time (Months): " + time);
        System.out.println(" Stress: " + stress);
        System.out.println(" Progress: " + progress + "%");
        System.out.println("-----------------------------------");
    }

    // ================== Monthly story text ===========================

    static void performance(int month)
    {
        switch (month) {
            case 1 -> System.out.println("Month 1: A fresh start!!! You begin your new journey!");
            case 2 -> System.out.println("Month 2: Work piles up now, balancing is getting harder.");
            case 3 -> System.out.println("Month 3: Your success gives you hope!");
            case 4 -> System.out.println("Month 4: Energy is dropping; motivation fading.");
            case 5 -> System.out.println("Month 5: Mid-spring time to think about long-term goals.");
            case 6 -> System.out.println("Month 6: Halfway point! Can you push through burnout?");
            case 7 -> System.out.println("Month 7: Summer brings distractions, but also opportunities.");
            case 8 -> System.out.println("Month 8: You get invited to a big event will it help or distract?");
            case 9 -> System.out.println("Month 9: Tired but determined, the finish line is closer.");
            case 10 -> System.out.println("Month 10: Deadlines are coming near this month decides your fate.");
            case 11 -> System.out.println("Month 11: Your health and money are tight every decision matters.");
            case 12 -> System.out.println("Month 12: The final push! Either complete it or lose it all.");
            default -> System.out.println("A month passes quietly...");
        }
    }

    // ===================== Choices for every month ======================

    static void choicesForMonth(int month) {
        switch (month) {
            case 1: {
                System.out.println("1. Start working on the project immediately.");
                System.out.println("2. Plan carefully before starting.");
                System.out.println("3. Take a small course to improve your skills.");
                System.out.println("4. Spend money to upgrade your tools.");
                System.out.println("5. Relax this month and gather energy.");
                break;
            }
            case 2: {
                System.out.println("1. Work overtime to meet early goals.");
                System.out.println("2. Join a small online community for support.");
                System.out.println("3. Spend time learning something new.");
                System.out.println("4. Take a short weekend break to reduce stress.");
                System.out.println("5. Focus on your main job to earn extra cash.");
                break;
            }
            case 3: {
                System.out.println("1. Push hard to hit your first milestone.");
                System.out.println("2. Buy new software to speed up your work.");
                System.out.println("3. Meet other creators for inspiration.");
                System.out.println("4. Spend time with family to relax.");
                System.out.println("5. Delay work and think about new ideas.");
                break;
            }
            case 4: {
                System.out.println("1. Try to fix bugs or flaws in your project.");
                System.out.println("2. Take a short break for mental health.");
                System.out.println("3. Hire a freelancer for help (costs money).");
                System.out.println("4. Ignore rest and work day and night.");
                System.out.println("5. Spend evenings journaling ideas.");
                break;
            }
            case 5: {
                System.out.println("1. Reorganize your work schedule.");
                System.out.println("2. Accept a new side job for income.");
                System.out.println("3. Join a seminar to learn new tricks.");
                System.out.println("4. Take a small vacation.");
                System.out.println("5. Continue slow steady progress.");
                break;
            }
            case 6: {
                System.out.println("1. Double your efforts to reach halfway.");
                System.out.println("2. Rest for mental stability.");
                System.out.println("3. Invest in marketing or publicity.");
                System.out.println("4. Borrow money to fund bigger progress.");
                System.out.println("5. Focus on personal growth this month.");
                break;
            }
            case 7: {
                System.out.println("1. Work through the summer heat non-stop.");
                System.out.println("2. Go out and socialize to refresh your mind.");
                System.out.println("3. Attend a motivational workshop.");
                System.out.println("4. Take freelance work for quick income.");
                System.out.println("5. Spend quiet nights reflecting and coding.");
                break;
            }
            case 8: {
                System.out.println("1. Attend the big event to network.");
                System.out.println("2. Skip the event and stay focused.");
                System.out.println("3. Travel for new ideas (expensive).");
                System.out.println("4. Launch a beta version of your project!");
                System.out.println("5. Spend time meditating to clear your mind.");
                break;
            }
            case 9: {
                System.out.println("1. Push extra hard toward finishing line.");
                System.out.println("2. Take light work to avoid burnout.");
                System.out.println("3. Ask a friend to collaborate.");
                System.out.println("4. Seek investors or sponsors.");
                System.out.println("5. Take a full week of rest.");
                break;
            }
            case 10: {
                System.out.println("1. All-out work mode (maximum effort).");
                System.out.println("2. Review and polish your existing work.");
                System.out.println("3. Cut unnecessary features to save time.");
                System.out.println("4. Take on client work for side money.");
                System.out.println("5. Spend time exercising for health.");
                break;
            }
            case 11: {
                System.out.println("1. Finalize the project plan.");
                System.out.println("2. Fix errors and bugs.");
                System.out.println("3. Present to early testers.");
                System.out.println("4. Sleep well to maintain energy.");
                System.out.println("5. Continue steady daily progress.");
                break;
            }
            case 12: {
                System.out.println("1. Push through sleepless nights to finish!");
                System.out.println("2. Focus on quality rather than speed.");
                System.out.println("3. Ask for last-minute feedback.");
                System.out.println("4. Take a final short rest before the deadline.");
                System.out.println("5. Give up and reflect.");
                break;
            }
        }
    }


    // ===================Choice input method (fix)======================

    @SuppressWarnings("SameParameterValue")
    static int getChoice(int low, int high)
    {
        System.out.print("\nEnter your choice (" + low + "-" + high + "): ");
        int choice;
        while(true) {
            try {
                choice = input.nextInt();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Invalid Input!");
                input.nextLine();
            }
        }
        while (choice < low || choice > high)
        {
            System.out.print("Invalid! Enter between " + low + " and " + high + ": ");
            while(true){
                try{
                    choice =  input.nextInt();
                    break;
                }
                catch(InputMismatchException ex){
                    System.out.println("Invalid Input! " + ex.getMessage());
                    input.next();
                }
            }
        }
        return choice;
    }


    // ====================== Apply choice effects on stats =======================

    static void applyChoiceEffects(int month, int choice) throws InterruptedException
    {
        switch (month) {
            case 1:
            {
                if (choice == 1){
                    progress += 15;
                    stress += 5;
                    finance -= 5;
                }
                else if (choice == 2){
                    inspiration += 8;
                    progress += 10;
                }
                else if (choice == 3){
                    finance -= 10;
                    inspiration += 12;
                    progress += 8;
                }
                else if (choice == 4){
                    finance -= 15;
                    progress += 20;
                }
                else{
                    stress -= 5;
                    inspiration += 5;
                }
                break;
            }
            case 2:
            {
                if (choice == 1){
                    progress += 18;
                    stress += 8;
                    finance -= 5;
                }
                else if (choice == 2){
                    inspiration += 10;
                    progress += 10;
                }
                else if (choice == 3){
                    inspiration += 7;
                    progress += 8;
                }
                else if (choice == 4){
                    stress -= 8;
                    inspiration += 3;
                }
                else{
                    finance += 10;
                    progress += 5;
                }
                break;
            }
            case 3:
            {
                if (choice == 1)
                {
                    progress += 20;
                    stress += 7;
                }
                else if (choice == 2)
                {
                    finance -= 10;
                    progress += 12;
                }
                else if (choice == 3)
                {
                    inspiration += 12;
                    progress += 10;
                }
                else if (choice == 4){
                    stress -= 7;
                    inspiration += 5;
                }
                else{
                    inspiration += 4;
                    progress += 5;
                }
                break;
            }
            case 4:{
                if (choice == 1){
                    progress += 15;
                    stress += 6;
                }
                else if (choice == 2){
                    stress -= 10;
                    inspiration += 5;
                }
                else if (choice == 3){
                    finance -= 20;
                    progress += 18;
                }
                else if (choice == 4)
                {
                    progress += 25;
                    stress += 15;
                }
                else{
                    inspiration += 6;
                    progress += 7;
                }
                break;
            }
            case 5:{
                if (choice == 1)
                {
                    inspiration += 5;
                    progress += 10;
                }
                else if(choice == 2)
                {
                    finance += 20;
                    stress += 5;
                }
                else if (choice == 3)
                {
                    inspiration += 10;
                    finance -= 10;
                }
                else if (choice == 4)
                {
                    stress -= 10;
                    inspiration += 5;
                }
                else {
                    progress += 12;
                    stress += 3;

                }
                break;
            }
            case 6:{
                if (choice == 1){
                    progress += 25;
                    stress += 10;
                }
                else if (choice == 2){
                    stress -= 10;
                    inspiration += 5;
                }
                else if (choice == 3){
                    finance -= 10;
                    progress += 15;
                }
                else if (choice == 4)
                {
                    finance += 20;
                    progress += 10;
                    stress += 5;
                }
                else {
                    inspiration += 10;
                    progress += 8;
                }
                break;
            }
            case 7:{
                if (choice == 1)
                {
                    progress += 18;
                    stress += 10;
                }
                else if (choice == 3){
                    stress -= 8;
                    inspiration += 5;
                }
                else if (choice == 4){
                    inspiration += 12;
                    progress += 10;
                    finance -= 5;
                }
                else if (choice == 5){
                    finance += 15;
                    progress += 5;
                }
                else {
                    inspiration += 8;
                    progress += 9;
                }
                break;
            }
            case 8:{
                if (choice == 1){
                    finance -= 15;
                    inspiration += 10;
                    progress += 8;
                }
                else if (choice == 2){
                    progress += 15;
                    stress += 5;
                }
                else if (choice == 4){
                    finance -= 20;
                    inspiration += 15;
                }
                else if (choice == 5){
                    progress += 20;
                    stress += 8;
                }
                else{
                    stress -= 10;
                    inspiration += 8;
                }
                break;
            }
            case 9:{
                if (choice == 1){
                    progress += 25;
                    stress += 12;
                }
                else if (choice == 2){
                    progress += 10;
                    stress -= 5;
                }
                else if (choice == 3){
                    inspiration += 8;
                    progress += 10;
                }
                else if (choice == 4){
                    finance += 20;
                    progress += 5;
                }
                else {
                    stress -= 10;
                    inspiration += 5;
                }

                // ===================== Calling the random event=====================
                Thread.sleep(1000);
                randomEvent();
                Thread.sleep(1000);
                break;
            }
            case 10:{
                if (choice == 1){
                    progress += 30;
                    stress += 15;
                }
                else if (choice == 2){
                    progress += 15;
                    inspiration += 5;
                }
                else if (choice == 3){
                    progress += 10;
                    stress -= 5;
                }
                else if (choice == 4){
                    finance += 15;
                    progress += 5;
                }
                else {
                    stress -= 10;
                    inspiration += 5;
                }
                break;
            }
            case 11:{
                if (choice == 1){
                    progress += 20;
                    stress += 10;
                }
                else if (choice == 2){
                    progress += 18;
                    stress += 5;
                }
                else if (choice == 3){
                    progress += 15;
                    inspiration += 5;
                }
                else if (choice == 5){
                    stress -= 8;
                    inspiration += 3;
                }
                else{
                    progress += 10;
                    stress += 2;
                }
                break;
            }
            case 12:{
                if (choice == 1){
                    progress += 40;
                    stress += 20;
                }
                else if (choice == 2){
                    progress += 25;
                    stress += 10;
                }
                else if (choice == 3){
                    progress += 20;
                    inspiration += 5;
                }
                else if (choice == 5){
                    stress -= 10;
                    progress += 10;
                }
                else {
                    progress -= 20;
                    inspiration += 5;
                }
                break;
            }
        }

        if (progress > 200) progress = 200;
        if (inspiration < 0) inspiration = 0;
    }

    // =====================Random event =========================
    static void randomEvent()
    {
        int roption;
        System.out.print("\n\n\tA random event occurs out of no-where!");
        System.out.print("\n\tPress '0' to Try your luck!!! Otherwise skip event");
        while(true) {
            try {
                roption = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid Input! " + e.getMessage());
                input.next();
            }
        }

        if (roption == 0)
        {

            int eventChance = random.nextInt(100);
            if (eventChance < 80)
            {
                int type = random.nextInt(1, 6);

                switch (type) {
                    case 1:{
                        System.out.println("\n\n\tBONUS EVENT: You got a freelance job! +15 finance");
                        finance += 15;
                        break;
                    }
                    case 2: {
                        System.out.println("\n\n\tRANDOM EVENT: You fell sick! -10 progress, +5 stress");
                        progress -= 10;
                        stress += 5;
                        break;
                    }
                    case 3: {
                        System.out.println("\n\n\tINSPIRATION EVENT: Great idea hit you! +15 inspiration");
                        inspiration += 15;
                        break;
                    }
                    case 4: {
                        System.out.println("\n\n\tBAD LUCK: Equipment broke! -10 finance");
                        finance -= 10;
                        break;
                    }
                    case 5: {
                        System.out.println("\n\n\tSUPPORT: A friend encouraged you! -5 stress, +5 inspiration");
                        stress -= 5;
                        inspiration += 5;
                        break;
                    }
                }
            }
            else{
                System.out.println("\nNo major life events this month.");
            }
        }
    }

    // =================== Hints for choices made ======================

    static String hintsForChoices(int month, int pick)
    {
        //noinspection EnhancedSwitchMigration
        switch (pick)
        {
            case 1:
                return "Focus on progress, but increases stress.";
            case 2:
                return "Rest or smart work helps balance.";
            case 3:
                return "Good for long-term success.";
            case 4:
                return "Involves money or stress but can help growth.";
            case 5:
                return "Safe option with small rewards.";
            default:
                return "Neutral outcome.";
        }
    }

    // =========================Check game-ending conditions==========================

    static void checkGameState(int month) throws InterruptedException
    {
        Thread.sleep(800);
        if (finance < 0) {
            System.out.println("\n\tYou ran out of money! Game Over!");
            gameOver = true;
        }
        else if (stress >= 100) {
            System.out.println("\n\tYou collapsed from burnout! Game Over!");
            gameOver = true;
        }
        else if (month == 12 && progress < 200) {
            System.out.println("\n\tYou can not complete you project! Game Over!");
            gameOver = true;
        }
        else if (time > 12) {
            System.out.println("\n\tYou ran out of time! Game Over!");
            gameOver = true;
        }
        else if (inspiration < 0) {
            System.out.println("\n\tYou lost inspiration to complete project. Game Over!");
            gameOver = true;
        }
    }

    // ======================Ending summary==========================

    static void endSummary() throws InterruptedException
    {
        Thread.sleep (1000);
        System.out.print("\n\n============== YEAR SUMMARY ==============\n\n");
        System.out.println("Finance: " + finance);
        System.out.println("Inspiration: " + inspiration);
        System.out.println("Stress: " + stress);
        System.out.println("Progress: " + progress + "%");
        System.out.print("\n\n===========================================\n");

        if ((progress >= 200) && (stress < 100) && (finance > 0) && (inspiration > 0)){
            System.out.print("\n\t\tCONGRATULATIONS!!!");
            System.out.println("\nYou completed your dream project successfully!");
        }
        else{
            System.out.print("\n\t\tYou have lost the Game!!!");
            System.out.println("\n\tYou could not complete your project this year.");
        }

        Thread.sleep(1200);

        System.out.print("\n\n================THE END====================\n\n");
    }
}
//============================THE END=========================