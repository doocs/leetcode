---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2730.Find%20the%20Longest%20Semi-Repetitive%20Substring/README_EN.md
rating: 1501
tags:
    - String
    - Sliding Window
---

# [2730. Find the Longest Semi-Repetitive Substring](https://leetcode.com/problems/find-the-longest-semi-repetitive-substring)

[中文文档](/solution/2700-2799/2730.Find%20the%20Longest%20Semi-Repetitive%20Substring/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> that consists of digits from <code>0</code> to <code>9</code>.</p>

<p>A string <code>t</code> is called a <strong>semi-repetitive</strong> if there is at most one consecutive pair of the same digits inside <code>t</code>. For example, <code>0010</code>, <code>002020</code>, <code>0123</code>, <code>2002</code>, and <code>54944</code> are semi-repetitive while&nbsp;<code>00101022</code>, and <code>1101234883</code> are not.</p>

<p>Return <em>the length of the longest semi-repetitive substring inside</em> <code>s</code>.</p>

<p>A <b>substring</b> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;52233&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest semi-repetitive substring is &quot;5223&quot;, which starts at i = 0 and ends at j = 3. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;5494&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> s is a semi-reptitive string, so the answer is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1111111&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest semi-repetitive substring is &quot;11&quot;, which starts at i = 0 and ends at j = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>&#39;0&#39; &lt;= s[i] &lt;= &#39;9&#39;</code></li>
</ul>

## Solutions

### Solution 1: Two Pointers

We use two pointers to maintain a range $s[j..i]$ such that there is at most one pair of adjacent characters that are equal, initially $j = 0$, $i = 1$. Initialize the answer $ans = 1$.

We use $cnt$ to record the number of pairs of adjacent characters that are equal in the range. If $cnt > 1$, then we need to move the left pointer $j$ until $cnt \le 1$. Each time, we update the answer as $ans = \max(ans, i - j + 1)$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def longestSemiRepetitiveSubstring(self, s: str) -> int:
        ans, n = 1, len(s)
        cnt = j = 0
        for i in range(1, n):
            cnt += s[i] == s[i - 1]
            while cnt > 1:
                cnt -= s[j] == s[j + 1]
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int ans = 1, n = s.length();
        for (int i = 1, j = 0, cnt = 0; i < n; ++i) {
            cnt += s.charAt(i) == s.charAt(i - 1) ? 1 : 0;
            for (; cnt > 1; ++j) {
                cnt -= s.charAt(j) == s.charAt(j + 1) ? 1 : 0;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestSemiRepetitiveSubstring(string s) {
        int ans = 1, n = s.size();
        for (int i = 1, j = 0, cnt = 0; i < n; ++i) {
            cnt += s[i] == s[i - 1] ? 1 : 0;
            for (; cnt > 1; ++j) {
                cnt -= s[j] == s[j + 1] ? 1 : 0;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func longestSemiRepetitiveSubstring(s string) (ans int) {
	ans = 1
	for i, j, cnt := 1, 0, 0; i < len(s); i++ {
		if s[i] == s[i-1] {
			cnt++
		}
		for ; cnt > 1; j++ {
			if s[j] == s[j+1] {
				cnt--
			}
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

```ts
function longestSemiRepetitiveSubstring(s: string): number {
    const n = s.length;
    let ans = 1;
    for (let i = 1, j = 0, cnt = 0; i < n; ++i) {
        cnt += s[i] === s[i - 1] ? 1 : 0;
        for (; cnt > 1; ++j) {
            cnt -= s[j] === s[j + 1] ? 1 : 0;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
