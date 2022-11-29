/**
20. Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Algorithom:
1. 用stack，如果是左括号就push进它的右括号
2. 如果不是左括号就pop最后进去的那个出来，如果不一样就return false
3. 如果一开始上来就是右括号，pop一个空的stack时return false
4. 当所有的括号都走完了stack不为空时 return false

"([{}})" -> return true
Dry working:
index i = 0:  )
index i = 1: ])
index i = 2: }])
index i = 3: 因为是右括号，所以pop stack里最外边的那个 -> "}", 发现此时 pop出来的 等于 index上的 "}" -> continue   此时stack: ])
index i = 4: 因为是右括号，所以pop stack里最外边的那个 -> "]", 发现此时 pop出来的 不等于 index上的 "}" -> return false
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character c: s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.pop() != c) {
                        return false;
                    }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;

    }
}