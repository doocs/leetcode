## 有效的括号
### 题目描述

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
```
输入: "()"
输出: true
```

示例 2:
```
输入: "()[]{}"
输出: true
```

示例 3:
```
输入: "(]"
输出: false
```

示例 4:
```
输入: "([)]"
输出: false
```

示例 5:
```
输入: "{[]}"
输出: true
```

### 解法
遍历 string，遇到左括号，压入栈中；遇到右括号，从栈中弹出元素，元素不存在或者元素与该右括号不匹配，返回 false。遍历结束，栈为空则返回 true，否则返回 false。

```java
class Solution {
    public boolean isValid(String s) {
        if (s == null || s == "") {
            return true;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < n; ++i) {
            char a = chars[i];
            if (isLeft(a)) {
                stack.push(a);
            } else {
                if (stack.isEmpty() || !isMatch(stack.pop(), a)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
                
    }
    
    private boolean isMatch(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
    
    private boolean isLeft(char a) {
        return a == '(' || a == '[' || a == '{';
    }
    
    private boolean isRight(char a) {
        return a == ')' || a == ']' || a == '}';
    }
}
```