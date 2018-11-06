## 逆波兰表达式求值
### 题目描述

根据逆波兰表示法，求表达式的值。

有效的运算符包括 `+`, `-`, `*`, `/` 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：
```
输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
```

示例 2：
```
输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
```

示例 3：
```
输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

### 解法
遍历数组，若遇到操作数，将其压入栈中；若遇到操作符，从栈中弹出右操作数和左操作数，将运算结果压入栈中。最后栈中唯一的元素就是结果。


```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String e : tokens) {
            if (isNum(e)) {
                stack.push(Integer.parseInt(e));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                int z = 0;
                switch (e) {
                    case "+": z = x + y; break;
                    case "-": z = x - y; break;
                    case "*": z = x * y; break;
                    case "/": z = x / y; break;    
                }
                stack.push(z);
            }
        }
        return stack.peek();
    }
    
    private boolean isNum(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```