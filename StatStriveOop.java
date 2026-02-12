import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

// 1. The Player Class: Encapsulates stats and logic related to the character
class Player {
    private int finance;
    private int inspiration;
    private int stress;
    private int progress;

    public Player(int finance, int inspiration, int stress){
        this.finance = finance;
        this.inspiration = inspiration;
        this.stress = stress;
        this.progress = 0;
    }

    public void updateStats(int f, int i, int s, int p){
        this.finance += f;
        this.inspiration = Math.max(0, this.inspiration + i);
        this.stress += s;
        this.progress = Math.min(200, this.progress + p);
    }

    public int getFinance(){ return finance; }
    public int getInspiration() { return inspiration; }
    public int getStress() { return stress; }
    public int getProgress() { return progress; }

    public boolean isBurnedOut(){
        return stress >= 100;
    }
    public boolean isBroke(){
        return finance < 0;
    }
    public boolean isUninspired(){
        return inspiration <= 0;
    }
    public boolean isProjectFinished(){
        return progress >= 200;
    }
}

// 2. The GameEngine: Controls the flow and logic
class GameEngine{
    private Player player;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private int currentMonth = 1;

    public void start() throws InterruptedException{
        UI.printTitle();
        setupDifficulty();

        while (currentMonth <= 12){
            UI.showStats(player);
            UI.printMonthHeader(currentMonth);

            processMonth();

            if (checkGameOver()) return;
            currentMonth++;
        }

        UI.printSummary(player);
    }

    private void setupDifficulty(){
        UI.printDifficultyMenu();
        int choice = UI.getValidInt(scanner, 1, 3);
        switch (choice) {
            case 1 -> player = new Player(80, 70, 10);
            case 2 -> player = new Player(60, 50, 30);
            case 3 -> player = new Player(40, 40, 50);
            default -> player = new Player(60, 50, 20);
        }
    }

    private void processMonth() throws InterruptedException{
        UI.printMonthStory(currentMonth);
        UI.printChoices(currentMonth);
        int choice = UI.getValidInt(scanner, 1, 5);

        applyEffects(choice);

        if (currentMonth == 9) {
            handleRandomEvent();
        }
    }

    private void applyEffects(int choice) {
        // We define the changes for this specific turn
        int f = 0, i = 0, s = 0, p = 0;

        switch (currentMonth){
            case 1 ->{
                if (choice == 1){p = 15; s = 5; f = -5;}
                else if (choice == 2){i = 8; p = 10;}
                else if (choice == 3){f = -10; i = 12; p = 8;}
                else if (choice == 4){f = -15; p = 20;}
                else{s = -5; i = 5;}
            }
            case 2 ->{
                if (choice == 1){p = 18; s = 8; f = -5;}
                else if (choice == 2){i = 10; p = 10;}
                else if (choice == 3){i = 7; p = 8;}
                else if (choice == 4){s = -8; i = 3;}
                else{ f = 10; p = 5; }
            }
            case 3 ->{
                if (choice == 1){p = 20; s = 7;}
                else if (choice == 2){f = -10; p = 12;}
                else if (choice == 3){i = 12; p = 10;}
                else if (choice == 4){s = -7; i = 5;}
                else{i = 4; p = 5;}
            }
            case 4 ->{
                if (choice == 1){p = 15; s = 6;}
                else if (choice == 2){s = -10; i = 5;}
                else if (choice == 3){f = -20; p = 18;}
                else if (choice == 4){p = 25; s = 15;}
                else{i = 6; p = 7;}
            }
            case 5 ->{
                if (choice == 1){i = 5; p = 10;}
                else if (choice == 2){f = 20; s = 5;}
                else if (choice == 3){i = 10; f = -10;}
                else if (choice == 4){s = -10; i = 5;}
                else{p = 12; s = 3;}
            }
            case 6 ->{
                if (choice == 1){p = 25; s = 10;}
                else if (choice == 2){s = -10; i = 5;}
                else if (choice == 3){f = -10; p = 15;}
                else if (choice == 4){f = 20; p = 10; s = 5;}
                else{i = 10; p = 8; }
            }
            case 7 ->{
                if (choice == 1){ p = 18; s = 10; }
                else if (choice == 3){s = -8; i = 5;}
                else if (choice == 4){i = 12; p = 10; f = -5;}
                else if (choice == 5){f = 15; p = 5;}
                else{i = 8; p = 9;}
            }
            case 8 ->{
                if (choice == 1){f = -15; i = 10; p = 8;}
                else if (choice == 2){p = 15; s = 5;}
                else if (choice == 4){f = -20; i = 15;}
                else if (choice == 5){p = 20; s = 8;}
                else{s = -10; i = 8;}
            }
            case 9 -> {
                if (choice == 1){p = 25; s = 12;}
                else if (choice == 2){p = 10; s = -5;}
                else if (choice == 3) { i = 8; p = 10;}
                else if (choice == 4){f = 20; p = 5;}
                else{s = -10; i = 5;}
            }
            case 10 ->{
                if (choice == 1){p = 30; s = 15;}
                else if (choice == 2){p = 15; i = 5;}
                else if (choice == 3){p = 10; s = -5;}
                else if (choice == 4){f = 15; p = 5;}
                else{s = -10; i = 5;}
            }
            case 11 ->{
                if (choice == 1){p = 20; s = 10; }
                else if (choice == 2){p = 18; s = 5;}
                else if (choice == 3){p = 15; i = 5;}
                else if (choice == 5){s = -8; i = 3;}
                else {p = 10; s = 2;}
            }
            case 12 ->{
                if (choice == 1){p = 40; s = 20;}
                else if (choice == 2){p = 25; s = 10;}
                else if (choice == 3){p = 20; i = 5;}
                else if (choice == 5){s = -10; p = 10;}
                else{p = -20; i = 5;}
            }
        }

        // After calculating the changes, apply them to the player object
        player.updateStats(f, i, s, p);
    }

    private void handleRandomEvent() {
        if (UI.askForRandomEvent(scanner)) {
            int event = random.nextInt(1, 6);
            switch (event) {
                case 1:{
                    System.out.println("\n\n\tBONUS EVENT: You got a freelance job! +15 finance");
                    player.updateStats(15,0,0,0);
                    break;
                }
                case 2:{
                    System.out.println("\n\n\tRANDOM EVENT: You fell sick! -10 progress, +5 stress");
                    player.updateStats(0,0,5,-10);
                    break;
                }
                case 3:{
                    System.out.println("\n\n\tINSPIRATION EVENT: Great idea hit you! +15 inspiration");
                    player.updateStats(0,15,0,0);
                    break;
                }
                case 4:{
                    System.out.println("\n\n\tBAD LUCK: Equipment broke! -10 finance");
                    player.updateStats(-10,0,0,0);
                    break;
                }
                case 5:{
                    System.out.println("\n\n\tSUPPORT: A friend encouraged you! -5 stress, +5 inspiration");
                    player.updateStats(0,5,-5,0);
                    break;
                }
            }
        }
        else{
            System.out.println("\nNo major life events this month.");
        }
    }

    private boolean checkGameOver(){
        if (player.isBroke()){
            UI.printLoseMsg("You ran out of money!");
            return true;
        }
        if (player.isBurnedOut()){
            UI.printLoseMsg("Burnout collapse!");
            return true;
        }
        if (player.isUninspired()){
            UI.printLoseMsg("Lost all inspiration!");
            return true;
        }
        if (currentMonth == 12 && !player.isProjectFinished()){
            UI.printLoseMsg("Deadline missed!");
            return true;
        }
        return false;
    }
}

// 3. UI Class: Purely for visual representation and user input
class UI{
    public static void printTitle() {
        System.out.println("\n\t--- STAT STRIVE: THE 200% MILESTONE ---");
    }

    public static void showStats(Player p){
        System.out.println("\n-----------------------------------");
        System.out.println(" Finance: " + p.getFinance());
        System.out.println(" Inspiration: " + p.getInspiration());
        System.out.println(" Stress: " + p.getStress());
        System.out.println(" Progress: " + p.getProgress() + "%");
        System.out.println("-----------------------------------");
    }

    public static void printDifficultyMenu() {
        System.out.println("\nChoose Difficulty Level:");
        System.out.println("1. Easy (High finance, low stress)");
        System.out.println("2. Normal (Balanced start)");
        System.out.println("3. Hard (Low finance, high stress)");
    }

    public static int getValidInt(Scanner sc, int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("Enter choice (" + min + "-" + max + "): ");
                choice = sc.nextInt();
                if (choice >= min && choice <= max) return choice;
                System.out.println("Out of range!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.next();
            }
        }
    }

    public static void printMonthHeader(int m) {
        System.out.println("\n=================== MONTH " + m + " ===================");
    }
    static void printMonthStory(int month)
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

    public static void printLoseMsg(String reason) {
        System.out.println("\nGAME OVER: " + reason);
    }

    // Move your existing print switch-cases here (choicesForMonth, performance, etc.)
    public static void printChoices(int month) {
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
    public static void printSummary(Player p) {
        System.out.print("\n\n============== YEAR SUMMARY ==============\n\n");
        System.out.println("Finance: " + p.getFinance());
        System.out.println("Inspiration: " + p.getInspiration());
        System.out.println("Stress: " + p.getStress());
        System.out.println("Progress: " + p.getProgress() + "%");
        System.out.print("\n\n===========================================\n");

        if ((p.getProgress() >= 200) && (p.getStress() < 100) && (p.getFinance() > 0) && (p.getInspiration() > 0)){
            System.out.print("\n\t\tCONGRATULATIONS!!!");
            System.out.println("\nYou completed your dream project successfully!");
        }
        else{
            System.out.print("\n\t\tYou have lost the Game!!!");
            System.out.println("\n\tYou could not complete your project this year.");
        }

        System.out.print("\n\n================THE END====================\n\n");
    }

    public static boolean askForRandomEvent(Scanner sc) {
        System.out.print("\nA random event occurs! Press 0 to try luck, or 1 to skip: ");
        return sc.nextInt() == 0;
    }
}

// 4. Main Class: Entry point
public class StatStriveOop {
    public static void main(String[] args) throws InterruptedException {
        GameEngine game = new GameEngine();
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
        game.start();
    }
}
