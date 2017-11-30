import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SubsetSum {

    private List<Integer> elements;
    private int sum;

    public SubsetSum(List<Integer> elements, int sum) {
        this.elements = elements;
        this.sum = sum;
    }

    public List<Integer> getExistingSolution() {
        //first phase - random selection of elements as solution
        List<Integer> solutionSet = randomSelection();

        int nonSolutionSetSize = elements.size() - solutionSet.size();
        List<Integer> nonSolutionSet = new ArrayList<>(nonSolutionSetSize);
        for(int index = 0; index < elements.size(); index++) {
            if(!solutionSet.contains(elements.get(index))) {
                nonSolutionSet.add(elements.get(index));
            }
        }

        //sort the values not in current solution
        nonSolutionSet.sort(Collections.reverseOrder());
        replaceWithPotentialElements(solutionSet, nonSolutionSet);

        return solutionSet;
    }

    public List<Integer> getImprovedSolution() {

        //first phase - random selection of elements as solution
        List<Integer> solutionSet = randomSelection();

        int solutionMinValue = Integer.MAX_VALUE;
        int solutionMaxValue = -1;

        //finding the min and max value for the randomized solution
        for(int index = 0; index < solutionSet.size(); index++) {
            int value = solutionSet.get(index);
            if(value > solutionMaxValue) {
                solutionMaxValue = solutionSet.get(index);
            }
            if(value < solutionMinValue) {
                solutionMinValue = solutionSet.get(index);
            }
        }

        int delta = findDelta(solutionSet);

        List<Integer> potentialValues = new ArrayList<>();
        for(int index = 0; index < solutionSet.size(); index++) {
            int value = solutionSet.get(index);
            if((value >= solutionMinValue) && (value <= solutionMaxValue + delta)) {
                potentialValues.add(value);
            }
        }
        if(potentialValues.size() <= 4*Math.pow(elements.size(), 3/4)) {
            potentialValues.sort(Collections.reverseOrder());
            replaceWithPotentialElements(solutionSet, potentialValues);
        } else {
            Random randomGenerator = new Random();
            for(int index = 0; index < solutionSet.size(); index++) {
                int potentialValueIndex = randomGenerator.nextInt(potentialValues.size());
                int difference = potentialValues.get(potentialValueIndex) - solutionSet.get(index);
                if((difference > 0) && (difference <= delta)) {
                    if(!solutionSet.contains(potentialValues.get(potentialValueIndex))) {
                        solutionSet.remove(index);
                        solutionSet.add(index, potentialValues.get(potentialValueIndex));
                        delta = delta - difference;
                    }
                }
            }
        }

        return solutionSet;
    }

    public int findDelta(List<Integer> solutionSet) {
        int solutionSum = 0;
        for (int i = 0; i < solutionSet.size(); i++) {
            solutionSum += solutionSet.get(i);
        }
        return sum - solutionSum;
    }

    private List<Integer> randomSelection() {
        List<Integer> solutionSet = new ArrayList<>(elements.size());
        Random randomGenerator = new Random();
        int delta = findDelta(solutionSet);
        for (int count = 0; count < elements.size(); count++) {
            int index = randomGenerator.nextInt(elements.size());
            if (elements.get(index) <= delta) {
                if(!solutionSet.contains(elements.get(index))) {
                    solutionSet.add(elements.get(index));
                    delta = delta - elements.get(index);
                }
            }
        }
        return solutionSet;
    }

    private void replaceWithPotentialElements(List<Integer> solutionSet, List<Integer> nonSolutionSet) {
        int delta = findDelta(solutionSet);
        for (int index = 0; index < solutionSet.size(); index++) {
            if (delta == 0)
                break;
            int rangeMin = solutionSet.get(index);
            int rangeMax = solutionSet.get(index) + delta;

            //search for largest replaceable value
            int minValueIndex = -1;
            int maxValueIndex = -1;
            for (int value = rangeMax; value > rangeMin; value--) {
                int indexValue = Collections.binarySearch(nonSolutionSet, value);
                if (indexValue > 0) {
                    if (maxValueIndex < 0) {
                        maxValueIndex = indexValue;
                    } else {
                        minValueIndex = indexValue;
                    }
                }
            }

            //found a potential replaceable element
            if (maxValueIndex > 0) {
                int newValue = nonSolutionSet.get(maxValueIndex);
                int oldValue = solutionSet.get(index);
                solutionSet.remove(index);
                solutionSet.add(newValue);
                nonSolutionSet.remove(maxValueIndex);
                nonSolutionSet.add(minValueIndex + 1, oldValue);

                delta = delta - (newValue - oldValue);
            }
        }
    }
}