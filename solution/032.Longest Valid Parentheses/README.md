## 最长有效括号
### 题目描述

给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:
```
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
```

示例 2:
```
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```

### 解法

此题采用动态规划方法。开辟一个数组空间 res，res[i] 表示必须以 s[i] 结尾的字符串的最长有效括号长度。

- 若 s[i] == '('，res[i] = 0；
- 若 s[i] == ')' && s[i - 1] == '('，res[i] = res[i - 2] + 2；
- 若 s[i] == ')' && s[i - 1] == ')'，判断 s[i - 1 - res[i - 1]] 的符号，若为 '('，则 res[i] = res[i - 1] + 2 + res[i - res[i - 1] - 2]。

注意数组下标越界检查。

```java
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] res = new int[n];
        res[0] = 0;
        res[1] = chars[1] == ')' && chars[0] == '(' ? 2 : 0;
        
        int max = res[1];
        
        for (int i = 2; i < n; ++i) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    res[i] = res[i - 2] + 2;
                } else {
                    int index = i - res[i - 1] - 1;
                    if (index >= 0 && chars[index] == '(') {
                        // ()(())
                        res[i] = res[i - 1] + 2 + (index - 1 >= 0 ? res[index - 1] : 0);
                    }
                }
            }
            max = Math.max(max, res[i]);
        }
        
        return max;
        
    }
}
```