# [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)

[English Version](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个字符串，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入: </strong>&quot;abcabcbb&quot;
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>&quot;abc&quot;，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>&quot;bbbbb&quot;
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>&quot;b&quot;</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入: </strong>&quot;pwwkew&quot;
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>&quot;wke&quot;</code>，所以其长度为 3。
&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>&quot;pwke&quot;</code>&nbsp;是一个<em>子序列，</em>不是子串。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 定义一个哈希表存放字符及其出现的位置；
- 定义 i, j 分别表示不重复子串的开始位置和结束位置；
- j 向后遍历，若遇到与 `[i, j]` 区间内字符相同的元素，更新 i 的值，此时 `[i, j]` 区间内不存在重复字符，计算 res 的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res, chars = 0, dict()
        i = j = 0
        while j < len(s):
            if s[j] in chars:
                # chars[s[j]]+1 可能比 i 还小，通过 max 函数来锁住左边界
                # e.g. 在"tmmzuxt"这个字符串中，遍历到最后一步时，最后一个字符't'和第一个字符't'是相等的。如果没有 max 函数，i 就会回到第一个't'的索引0处的下一个位置
                i = max(i, chars[s[j]] + 1)
            res = max(res, j - i + 1)
            chars[s[j]] = j
            j += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> chars = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            if (chars.containsKey(c)) {
                // chars.get(c)+1 可能比 i 还小，通过 max 函数来锁住左边界
                // e.g. 在"tmmzuxt"这个字符串中，遍历到最后一步时，最后一个字符't'和第一个字符't'是相等的。如果没有 max 函数，i 就会回到第一个't'的索引0处的下一个位置
                i = Math.max(i, chars.get(c) + 1);
            }
            chars.put(c, j);
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
