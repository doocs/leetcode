# [266. 回文排列](https://leetcode-cn.com/problems/palindrome-permutation)

[English Version](/solution/0200-0299/0266.Palindrome%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> <code>&quot;code&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> <code>&quot;aab&quot;</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> <code>&quot;carerac&quot;</code>
<strong>输出:</strong> true</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

利用 HashMap（字典表）统计每个字符出现的频率，至多有一个字符出现奇数次数即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        mapper = {}
        for ch in s:
            mapper[ch] = mapper.get(ch, 0) + 1
        cnt = 0
        for _, v in mapper.items():
            if v % 2 != 0:
                cnt += 1
        return cnt <= 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int cnt = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                ++cnt;
            }
        }
        return cnt <= 1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
