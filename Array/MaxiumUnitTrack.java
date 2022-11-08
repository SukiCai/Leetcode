/**
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.


Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.

Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91



Greedy algrithom:
A greedy algorithm is an algorithmic strategy that makes the best optimal choice at each small stage with the goal of this eventually leading to a globally optimum solution.


Algrithom: Greedy Algrithom
1. Sort the current array according to the second argument in the current array
2. For loop to travese the whole array and for each subarray, if the current number of boxes (box[0]) is smaller than the remaining trucksize, then the result + the number of box * box size
3. If the current number of boxese larger then the remainning trucksize, meaning that we can only fit it the remaining number of boxes which is the trucksize, and return the current total sizes + trucSize * currrent box size


Complexity:
- Time: O(nlog(n)), Java soring array running time (MergeSort)
- Space: O(1), no extra data structure used
*/


class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> -Integer.compare(a[1], b[1]));
        int result = 0;
        for (int[] box: boxTypes) {
            if (box[0] > truckSize) {
                return result + truckSize * box[1];
            }
            truckSize -= box[0];
            result += box[1] * box[0];
        }
        return result;
    }
}