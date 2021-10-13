class Solution {
    public boolean isValid(String s) {
        if (s.length()%2 != 0) {
            return false;
        }
        
        Stack<Character> bracketStack = new Stack();
        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                bracketStack.push(c);
            } else if (c == ')' && !bracketStack.isEmpty() && bracketStack.peek() =='(') {
                bracketStack.pop();
            } else if (c == '}' && !bracketStack.isEmpty() && bracketStack.peek() == '{') {
                bracketStack.pop();
            } else if (c == ']' && !bracketStack.isEmpty() && bracketStack.peek() == '[') {
                bracketStack.pop();
            } else {
                return false;
            }
        }
        return bracketStack.isEmpty();
    }
        
//         int count = 0;
//         HashMap<String, Integer> bracketMap = new HashMap<String, Integer>();
//         bracketMap.put("(", 1);
//         bracketMap.put(")", 3);
//         bracketMap.put("[", 7);
//         bracketMap.put("]", 9);
//         bracketMap.put("{", 13);
//         bracketMap.put("}", 15);
        
        
//         if (s.equals("(")|s.equals(")")|s.equals("[")
//             |s.equals("]")|s.equals("{")| s.equals("}") | (s.length()%2) != 0) {
//             return false;
//         }
        
//         for (int i = 0; i < s.length() - 1; i++) {
//             String curr = Character.toString(s.charAt(i));
//             String next = Character.toString(s.charAt(i+1));
//             int temp = bracketMap.get(curr) + 2;
            
//             if (temp == bracketMap.get(next)) {
//                 count++;
//             }
//         }
//         if (count == 0) {
//             return false;
//         }
        
//         if (count >= (s.length()/2) | count%2 != 0) {
//             return true;
//         }
//         return false;
//     }
    
    
}