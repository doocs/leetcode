## 最长公共前缀
### 题目描述

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:
```
输入: ["flower","flow","flight"]
输出: "fl"
```

示例 2:
```
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

说明:

所有输入只包含小写字母 a-z 。

### 解法
取字符串数组第一个元素，遍历每一个字符，与其他每个字符串的对应位置字符做比较，如果不相等，退出循环，返回当前子串。

注意：其他字符串的长度可能小于第一个字符串长度，所以要注意数组越界异常。

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        
        char[] chars = strs[0].toCharArray();
        int i = 0;
        boolean flag = true;
        for (; i < chars.length; ++i) {
            char ch = chars[i];
            
            for (int j = 1; j < strs.length; ++j) {
                if (strs[j].length() <= i) {
                    flag = false;
                    break;
                }
                if (strs[j].charAt(i) != ch) {
                    flag = false;
                    break;
                }
                
            }
            if (!flag) {
                break;
            }
        }
        return strs[0].substring(0, i);
        
        
    }
}
```