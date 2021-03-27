# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Description

<p>Given a string, find the length of the <b>longest substring</b> without repeating characters.</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;abcabcbb&quot;</span>

<strong>Output: </strong><span id="example-output-1">3 

<strong>Explanation:</strong></span> The answer is <code>&quot;abc&quot;</code>, with the length of 3. 

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;bbbbb&quot;</span>

<strong>Output: </strong><span id="example-output-2">1

</span><span id="example-output-1"><strong>Explanation: </strong>T</span>he answer is <code>&quot;b&quot;</code>, with the length of 1.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;pwwkew&quot;</span>

<strong>Output: </strong><span id="example-output-3">3

</span><span id="example-output-1"><strong>Explanation: </strong></span>The answer is <code>&quot;wke&quot;</code>, with the length of 3. 

             Note that the answer must be a <b>substring</b>, <code>&quot;pwke&quot;</code> is a <i>subsequence</i> and not a substring.

</pre>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res, chars = 0, dict()
        i = j = 0
        while j < len(s):
            if s[j] in chars:
                i = max(i, chars[s[j]] + 1)
            res = max(res, j - i + 1)
            chars[s[j]] = j
            j += 1
        return res
```

### **Java**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> chars = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            if (chars.containsKey(c)) {
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
