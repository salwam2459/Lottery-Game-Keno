import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MyTest {
    
    private KenoGameLogic game;
    
    @BeforeEach
    public void setUp() {
        game = new KenoGameLogic();
    }
    
    // Test winnings calculation for 1 spot
    @Test
    public void testCalculateWinnings1SpotMatch() {
        assertEquals(2, game.calculateWinnings(1, 1));
    }
    
    @Test
    public void testCalculateWinnings1SpotNoMatch() {
        assertEquals(0, game.calculateWinnings(1, 0));
    }
    
    // Test winnings calculation for 4 spots
    @Test
    public void testCalculateWinnings4Spots4Matches() {
        assertEquals(75, game.calculateWinnings(4, 4));
    }
    
    @Test
    public void testCalculateWinnings4Spots3Matches() {
        assertEquals(5, game.calculateWinnings(4, 3));
    }
    
    @Test
    public void testCalculateWinnings4Spots2Matches() {
        assertEquals(1, game.calculateWinnings(4, 2));
    }
    
    @Test
    public void testCalculateWinnings4Spots1Match() {
        assertEquals(0, game.calculateWinnings(4, 1));
    }
    
    @Test
    public void testCalculateWinnings4SpotsNoMatch() {
        assertEquals(0, game.calculateWinnings(4, 0));
    }
    
    // Test winnings calculation for 8 spots
    @Test
    public void testCalculateWinnings8Spots8Matches() {
        assertEquals(10000, game.calculateWinnings(8, 8));
    }
    
    @Test
    public void testCalculateWinnings8Spots7Matches() {
        assertEquals(750, game.calculateWinnings(8, 7));
    }
    
    @Test
    public void testCalculateWinnings8Spots6Matches() {
        assertEquals(50, game.calculateWinnings(8, 6));
    }
    
    @Test
    public void testCalculateWinnings8Spots5Matches() {
        assertEquals(12, game.calculateWinnings(8, 5));
    }
    
    @Test
    public void testCalculateWinnings8Spots4Matches() {
        assertEquals(2, game.calculateWinnings(8, 4));
    }
    
    @Test
    public void testCalculateWinnings8Spots3Matches() {
        assertEquals(0, game.calculateWinnings(8, 3));
    }
    
    // Test winnings calculation for 10 spots
    @Test
    public void testCalculateWinnings10Spots10Matches() {
        assertEquals(100000, game.calculateWinnings(10, 10));
    }
    
    @Test
    public void testCalculateWinnings10Spots9Matches() {
        assertEquals(4500, game.calculateWinnings(10, 9));
    }
    
    @Test
    public void testCalculateWinnings10Spots8Matches() {
        assertEquals(300, game.calculateWinnings(10, 8));
    }
    
    @Test
    public void testCalculateWinnings10Spots7Matches() {
        assertEquals(40, game.calculateWinnings(10, 7));
    }
    
    @Test
    public void testCalculateWinnings10Spots6Matches() {
        assertEquals(15, game.calculateWinnings(10, 6));
    }
    
    @Test
    public void testCalculateWinnings10Spots5Matches() {
        assertEquals(2, game.calculateWinnings(10, 5));
    }
    
    @Test
    public void testCalculateWinnings10Spots0Matches() {
        assertEquals(5, game.calculateWinnings(10, 0));
    }
    
    @Test
    public void testCalculateWinnings10Spots4Matches() {
        assertEquals(0, game.calculateWinnings(10, 4));
    }
    
    // Test invalid inputs
    @Test
    public void testCalculateWinningsInvalidSpots() {
        assertEquals(0, game.calculateWinnings(5, 3));
    }
    
    @Test
    public void testCalculateWinningsNegativeMatches() {
        assertEquals(0, game.calculateWinnings(4, -1));
    }
    
    @Test
    public void testCalculateWinningsMatchesExceedSpots() {
        assertEquals(0, game.calculateWinnings(4, 5));
    }
    
    // Test random number generation
    @Test
    public void testDrawNumbersReturns20Numbers() {
        List<Integer> drawn = game.drawNumbers();
        assertEquals(20, drawn.size());
    }
    
    @Test
    public void testDrawNumbersNoDuplicates() {
        List<Integer> drawn = game.drawNumbers();
        Set<Integer> uniqueNumbers = new HashSet<>(drawn);
        assertEquals(20, uniqueNumbers.size());
    }
}