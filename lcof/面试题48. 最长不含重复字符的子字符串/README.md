# [面试题48. 最长不含重复字符的子字符串](https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)

## 题目描述
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

**示例 1:**

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

**提示：**

- `s.length <= 40000`

## 解法
<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        cache = {}
        cache[s[0]] = 0
        dp = [0 for _ in s]
        dp[0] = res = 1
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                dp[i] = 1
            else:
                if cache.get(s[i]) is None:
                    dp[i] = dp[i - 1] + 1
                else:
                    dp[i] = min(dp[i - 1] + 1, i - cache[s[i]])
            cache[s[i]] = i
            res = max(res, dp[i])
        return res
```

### **Java**
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[n];
        int res = 1;
        Map<Character, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(chars[0], 0);
        for (int i = 1; i < n; ++i) {
            if (chars[i] == chars[i - 1]) {
                dp[i] = 1;
            } else {
                if (map.get(chars[i]) == null) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = Math.min(dp[i - 1] + 1, i - map.get(chars[i]));
                }
            }
            map.put(chars[i], i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

### **JavaScript**
```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function(s) {
    let left = 0
    let right = 0
    let res = 0
    let len = s.length
    let rec = {}
    while(right < len) {
        let tmp = '*'
        while(right < len) {
            tmp = s[right]
            if(!rec[tmp]) rec[tmp] = 0
            rec[tmp]++
            if(rec[tmp] > 1) break
            right++
        }
        res = Math.max(res, right - left)
        while(rec[tmp] > 1) rec[s[left++]]--
        right++
    }
    return res
};
```

### **...**
```

```

<!-- tabs:end -->