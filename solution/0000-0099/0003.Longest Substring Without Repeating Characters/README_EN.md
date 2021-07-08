# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Description

<p>Given a string <code>s</code>, find the length of the <b>longest substring</b> without repeating characters.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;abc&quot;, with the length of 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbbb&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The answer is &quot;b&quot;, with the length of 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pwwkew&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;wke&quot;, with the length of 3.
Notice that the answer must be a substring, &quot;pwke&quot; is a subsequence and not a substring.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;&quot;
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
</ul>


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

### **TypeScript**

```ts
function lengthOfLongestSubstring(s: string): number {
    // 滑动窗口+哈希表
    let left = -1;
    let maxLen = 0;
    let hashTable = new Map();
    for (let right = 0; right < s.length; right++) {
        let cur = s.charAt(right);
        if (hashTable.has(cur)) {
          left = Math.max(left, hashTable.get(cur));
        }
        hashTable.set(cur, right);
        maxLen = Math.max(maxLen, right - left);
    }
      return maxLen;
  };
```

### **Swift**

```swift
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        var map = [Character: Int]()
        var currentStartingIndex = 0
        var i = 0
        var maxLength = 0
        for char in s {
            if map[char] != nil {
                if map[char]! >= currentStartingIndex {
                    maxLength = max(maxLength, i - currentStartingIndex)
                    currentStartingIndex = map[char]! + 1
                }
            }
            map[char] = i
            i += 1
        }
        return max(maxLength, i - currentStartingIndex)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
