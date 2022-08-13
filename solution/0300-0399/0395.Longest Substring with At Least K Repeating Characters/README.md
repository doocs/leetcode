# [395. 至少有 K 个重复字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters)

[English Version](/solution/0300-0399/0395.Longest%20Substring%20with%20At%20Least%20K%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出 <code>s</code> 中的最长子串， 要求该子串中的每一字符出现次数都不少于 <code>k</code> 。返回这一子串的长度。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabb", k = 3
<strong>输出：</strong>3
<strong>解释：</strong>最长子串为 "aaa" ，其中 'a' 重复了 3 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ababbc", k = 2
<strong>输出：</strong>5
<strong>解释：</strong>最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubstring(self, s: str, k: int) -> int:
        n = len(s)
        maxLength = 0

        for i in range(1, 27):
            counter = dict()
            left = 0
            right = 0
            unique = 0
            kCount = 0

            while right < n:
                if unique <= i:
                    r = s[right]
                    counter[r] = counter.get(r, 0) + 1

                    if counter[r] == 1:
                        unique += 1
                    if counter[r] == k:
                        kCount += 1

                    right += 1

                else:
                    l = s[left]
                    counter[l] = counter.get(l, 0) - 1

                    if counter[l] == 0:
                        unique -= 1
                    if counter[l] == k - 1:
                        kCount -= 1

                    left += 1

                if unique == i and kCount == i:
                    maxLength = max(maxLength, right - left)

        return maxLength
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSubstring(String s, int k) {
        int maxLength = 0;
        int n = s.length();

        for (int i = 1; i < 27; i++) {
            Map<Character, Integer> counter = new HashMap<>();
            int left = 0;
            int right = 0;
            int unique = 0;
            int kCount = 0;

            while (right < n) {
                if (unique <= i) {
                    char r = s.charAt(right);
                    counter.put(r, counter.getOrDefault(r, 0) + 1);
                    if (counter.get(r) == 1) {
                        unique += 1;
                    }
                    if (counter.get(r) == k) {
                        kCount += 1;
                    }
                    right += 1;
                } else {
                    char l = s.charAt(left);
                    counter.put(l, counter.getOrDefault(l, 2) - 1);
                    if (counter.get(l) == 0) {
                        unique -= 1;
                    }
                    if (counter.get(l) == k - 1) {
                        kCount -= 1;
                    }
                    left += 1;
                }
                if (unique == i && kCount == i) {
                    maxLength = Math.max(maxLength, right - left);
                }
            }
        }
        return maxLength;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
