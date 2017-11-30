import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubsetSumTest {

    public static void main(String[] args) {

        List<Integer> elements = new LinkedList<>();
        elements.add(3415);
        elements.add(771356);
        elements.add(147607);
        elements.add(988658);
        elements.add(444525);
        elements.add(680330);
        elements.add(435035);
        elements.add(486015);
        elements.add(583100);
        elements.add(807532);
        elements.add(110073);
        elements.add(828303);
        elements.add(846241);
        elements.add(443489);
        elements.add(884076);
        elements.add(502113);
        elements.add(416851);
        elements.add(739659);
        elements.add(188814);
        elements.add(373902);
        int sum = 5698840;

        SubsetSum subsetSum = new SubsetSum(elements,sum);

        List<Integer> bestSolution = new ArrayList<>(elements.size());
        List<Integer> solution;
        int delta = Integer.MAX_VALUE;

        //Existing approach
        long initTimeExisting = System.currentTimeMillis();
        for(int trial = 1; trial <= 50; trial++) {
            if(delta == 0) {
                System.out.println("Trials :" + trial);
                break;
            }
            solution = subsetSum.getExistingSolution();
            int newDelta = subsetSum.findDelta(solution);
            if(newDelta < delta) {
                delta = newDelta;
                bestSolution = solution;
            }
        }
        long finalTimeExisting = System.currentTimeMillis();

        for(int index = 0; index < bestSolution.size(); index++) {
            System.out.print(bestSolution.get(index) + ",");
        }
        System.out.println();
        long totalTime = finalTimeExisting - initTimeExisting;
        System.out.println("Existing Approach - Time : " + totalTime);
        System.out.println("Delta : " + delta);

        //Improved Approach
        bestSolution = new ArrayList<>(elements.size());
        delta = Integer.MAX_VALUE;
        long initTimeImproved = System.currentTimeMillis();
        for(int trial = 1; trial <= 50; trial++) {
            if(delta == 0) {
                System.out.println("Trials :" + trial);
                break;
            }
            solution = subsetSum.getImprovedSolution();
            int newDelta = subsetSum.findDelta(solution);
            if(newDelta < delta) {
                delta = newDelta;
                bestSolution = solution;
            }
        }
        long finalTimeImproved = System.currentTimeMillis();

        for(int index = 0; index < bestSolution.size(); index++) {
            System.out.print(bestSolution.get(index) + ",");
        }
        System.out.println();
        totalTime = finalTimeImproved - initTimeImproved;
        System.out.println("Improved Approach - Time : " + totalTime);
        System.out.println("Delta : " + delta);
    }
}
