---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0848.Shifting%20Letters/README_EN.md
tags:
    - Array
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [848. Shifting Letters](https://leetcode.com/problems/shifting-letters)

[中文文档](/solution/0800-0899/0848.Shifting%20Letters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of lowercase English letters and an integer array <code>shifts</code> of the same length.</p>

<p>Call the <code>shift()</code> of a letter, the next letter in the alphabet, (wrapping around so that <code>&#39;z&#39;</code> becomes <code>&#39;a&#39;</code>).</p>

<ul>
	<li>For example, <code>shift(&#39;a&#39;) = &#39;b&#39;</code>, <code>shift(&#39;t&#39;) = &#39;u&#39;</code>, and <code>shift(&#39;z&#39;) = &#39;a&#39;</code>.</li>
</ul>

<p>Now for each <code>shifts[i] = x</code>, we want to shift the first <code>i + 1</code> letters of <code>s</code>, <code>x</code> times.</p>

<p>Return <em>the final string after all such shifts to s are applied</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, shifts = [3,5,9]
<strong>Output:</strong> &quot;rpl&quot;
<strong>Explanation:</strong> We start with &quot;abc&quot;.
After shifting the first 1 letters of s by 3, we have &quot;dbc&quot;.
After shifting the first 2 letters of s by 5, we have &quot;igc&quot;.
After shifting the first 3 letters of s by 9, we have &quot;rpl&quot;, the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;, shifts = [1,2,3]
<strong>Output:</strong> &quot;gfd&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>shifts.length == s.length</code></li>
	<li><code>0 &lt;= shifts[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Suffix Sum

For each character in the string $s$, we need to calculate its final shift amount, which is the sum of $\textit{shifts}[i]$, $\textit{shifts}[i + 1]$, $\textit{shifts}[i + 2]$, and so on. We can use the concept of suffix sum, traversing $\textit{shifts}$ from back to front, calculating the final shift amount for each character, and then taking modulo $26$ to get the final character.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shiftingLetters(self, s: str, shifts: List[int]) -> str:
        n, t = len(s), 0
        s = list(s)
        for i in range(n - 1, -1, -1):
            t += shifts[i]
            j = (ord(s[i]) - ord('a') + t) % 26
            s[i] = ascii_lowercase[j]
        return ''.join(s)
```

#### Java

```java
class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long t = 0;
        for (int i = n - 1; i >= 0; --i) {
            t += shifts[i];
            int j = (int) ((cs[i] - 'a' + t) % 26);
            cs[i] = (char) ('a' + j);
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string shiftingLetters(string s, vector<int>& shifts) {
        long long t = 0;
        int n = s.size();
        for (int i = n - 1; ~i; --i) {
            t += shifts[i];
            int j = (s[i] - 'a' + t) % 26;
            s[i] = 'a' + j;
        }
        return s;
    }
};
```

#### Go

```go
func shiftingLetters(s string, shifts []int) string {
	t := 0
	n := len(s)
	cs := []byte(s)
	for i := n - 1; i >= 0; i-- {
		t += shifts[i]
		j := (int(cs[i]-'a') + t) % 26
		cs[i] = byte('a' + j)
	}
	return string(cs)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
