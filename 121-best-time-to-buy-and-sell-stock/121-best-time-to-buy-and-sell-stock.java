class Solution {
    // public int maxProfit(int[] prices) {
    //     int[] oldArray = prices;
    //     Arrays.sort(prices);
    //     int max = 0;
    //     for (int i = 0; i < prices.length - 1; i++) {
    //          int benifit = prices[prices.length - 1- i] - prices[i];
    //          int indexI = Arrays.asList(oldArray).indexOf(prices[i]);
    //         System.out.println("I = "+ indexI);
    //          int indexJ = Arrays.asList(oldArray).indexOf(prices[prices.length - 1 - i]);
    //         System.out.println("J = " + indexJ);
    //          if (benifit > 0 && (indexI < indexJ))  {
    //             return benifit;
    //         } else { 
    //              i++;
    //         }
    //     }
    //     return max;
    // }
    // Time Limit Exceeded
    // public int maxProfit(int[] prices) {
    //     int max = 0;
    //     for (int i = 0; i < prices.length - 1; i++) {
    //         for (int j = i+1; j < prices.length; j++) {
    //             int benefit = prices[j] - prices[i];
    //             if(benefit > 0) {
    //                 max = Math.max(max, benefit);
    //             }
    //         }
    //     }
    //     return max;
    // }
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
    
}