# [395. Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters)

[中文文档](/solution/0300-0399/0395.Longest%20Substring%20with%20At%20Least%20K%20Repeating%20Characters/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, return <em>the length of the longest substring of</em> <code>s</code> <em>such that the frequency of each character in this substring is greater than or equal to</em> <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabb&quot;, k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest substring is &quot;aaa&quot;, as &#39;a&#39; is repeated 3 times.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababbc&quot;, k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The longest substring is &quot;ababb&quot;, as &#39;a&#39; is repeated 2 times and &#39;b&#39; is repeated 3 times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
