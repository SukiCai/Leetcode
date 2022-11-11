/**
253. Meeting Rooms II

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1

Algrithom: 
1. Using PriorityQueue to get the earliest ending time for the current occupaied room! (所有加到priorityqueue里的数，在peek永远是最小的)
2. 如果新的interval的start time比目前peek上的大，则可以用peek上的房间，该房间的ending time需要update成新的interval的ending time
3. 如果比peek小，说明需要一个新的房间，这个新的房间的ending time就是目前interval的ending time
4. Queue的大小就是一共需要的room的个数

Complexity:
- Time: O(Nlog(N))
- Space: O(1)

*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
          if (intervals[i][0] >= minHeap.peek()) {
            minHeap.poll();
          } 
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
}