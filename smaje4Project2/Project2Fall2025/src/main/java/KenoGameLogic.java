
import java.util.*;

public class KenoGameLogic {
    
    /**
     * Calculate winnings based on NC Lottery Keno payout table
     * @param spots Number of spots played (1, 4, 8, or 10)
     * @param matches Number of matches
     * @return Winnings amount
     */
    public int calculateWinnings(int spots, int matches) {
        // Validate input
        if (matches < 0 || matches > spots) {
            return 0;
        }
        
        // Based on NC Lottery Keno payouts
        switch (spots) {
            case 1:
                return (matches == 1) ? 2 : 0;
                
            case 4:
                if (matches == 4) return 75;
                if (matches == 3) return 5;
                if (matches == 2) return 1;
                return 0;
                
            case 8:
                if (matches == 8) return 10000;
                if (matches == 7) return 750;
                if (matches == 6) return 50;
                if (matches == 5) return 12;
                if (matches == 4) return 2;
                return 0;
                
            case 10:
                if (matches == 10) return 100000;
                if (matches == 9) return 4500;
                if (matches == 8) return 300;
                if (matches == 7) return 40;
                if (matches == 6) return 15;
                if (matches == 5) return 2;
                if (matches == 0) return 5;
                return 0;
                
            default:
                return 0;
        }
    }
    
    /**
     * Draw 20 random numbers between 1 and 80 with no duplicates
     * @return List of 20 drawn numbers
     */
    public List<Integer> drawNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            allNumbers.add(i);
        }
        Collections.shuffle(allNumbers);
        return allNumbers.subList(0, 20);
    }
    
    /**
     * Calculate how many numbers match between player selection and drawn numbers
     * @param playerNumbers Player's selected numbers
     * @param drawnNumbers Numbers drawn in the game
     * @return Number of matches
     */
    public int calculateMatches(Set<Integer> playerNumbers, List<Integer> drawnNumbers) {
        Set<Integer> matches = new HashSet<>(playerNumbers);
        matches.retainAll(drawnNumbers);
        return matches.size();
    }
}