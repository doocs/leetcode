## 最长回文子串
### 题目描述

给定一个字符串 **s**，找到 **s** 中最长的回文子串。你可以假设 **s** 的最大长度为1000。

**示例 1：**
```
输入: "babad"
输出: "bab"
注意: "aba"也是一个有效答案。
```

**示例 2：**
```
输入: "cbbd"
输出: "bb"
```

### 解法
- 解法1

利用动态规划，二维数组 `res` 存储 `[j, i]` 区间是否为回文串。动态规划递推式：

`res[j][i] = res[j + 1][i - 1] && chars[j] == chars[i]`

此方法时间和空间复杂度均为 `O(n²)`。

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String str = "";
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] res = new boolean[len][len];
        int start = 0;
        int max = 1;
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j <= i; ++j) {
                
                res[j][i] = i - j < 2
                    ? chars[j] == chars[i]
                    : res[j + 1][i - 1] && chars[j] == chars[i];
                
                if (res[j][i] && max < i - j + 1) {
                    max = i - j + 1;
                    start = j;
                }
                    
            }
        }
        
        return s.substring(start, start + max);
        
    }
}
```

