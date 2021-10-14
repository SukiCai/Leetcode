class Solution {
    public int reverse(int x) {
        int result;
        StringBuilder sb = new StringBuilder();
        int newX = Math.abs(x);
        if ((x < Integer.MIN_VALUE) || (x > Integer.MAX_VALUE)){
            return result = 0;
        } else {
             if (x<0) {
                sb.append("-");
            }
            String temp = Integer.toString(newX);
            int[] intArray = new int[temp.length()];
            for (int i = temp.length() - 1; i >= 0; i--) {
                sb.append(temp.charAt(i)); 
            }
           try {
              Integer.valueOf(sb.toString());
              return result = Integer.valueOf(sb.toString());
           } catch (Exception e) {
              return 0;
           }
        }
    }
}