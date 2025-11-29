import java.util.*;

public class KenoLogic {

    public static Set<Integer> generateQuickPick(int spots) {
        Random random = new Random();
        Set<Integer> picks = new HashSet<>();
        while (picks.size() < spots) {
            picks.add(random.nextInt(80) + 1);
        }
        return picks;
    }

    public static List<Integer> generateDraw(int numbersToDraw) {
        Random random = new Random();
        List<Integer> draw = new ArrayList<>();
        while (draw.size() < numbersToDraw) {
            int num = random.nextInt(80) + 1;
            if (!draw.contains(num)) draw.add(num);
        }
        return draw;
    }
}
