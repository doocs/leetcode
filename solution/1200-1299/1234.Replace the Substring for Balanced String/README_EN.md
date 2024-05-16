---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1234.Replace%20the%20Substring%20for%20Balanced%20String/README_EN.md
rating: 1877
source: Weekly Contest 159 Q3
tags:
    - String
    - Sliding Window
---

# [1234. Replace the Substring for Balanced String](https://leetcode.com/problems/replace-the-substring-for-balanced-string)

[中文文档](/solution/1200-1299/1234.Replace%20the%20Substring%20for%20Balanced%20String/README.md)

## Description

<p>You are given a string s of length <code>n</code> containing only four kinds of characters: <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</p>

<p>A string is said to be <strong>balanced</strong><em> </em>if each of its characters appears <code>n / 4</code> times where <code>n</code> is the length of the string.</p>

<p>Return <em>the minimum length of the substring that can be replaced with <strong>any</strong> other string of the same length to make </em><code>s</code><em> <strong>balanced</strong></em>. If s is already <strong>balanced</strong>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QWER&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> s is already balanced.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQWE&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We need to replace a &#39;Q&#39; to &#39;R&#39;, so that &quot;RQWE&quot; (or &quot;QRWE&quot;) is balanced.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQQW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can replace the first &quot;QQ&quot; to &quot;ER&quot;. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is a multiple of <code>4</code>.</li>
	<li><code>s</code> contains only <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Counting + Two Pointers

First, we use a hash table or array `cnt` to count the number of each character in string $s$. If the count of all characters does not exceed $n/4$, then the string $s$ is balanced, and we directly return $0$.

Otherwise, we use two pointers $j$ and $i$ to maintain the left and right boundaries of the window, initially $j = 0$.

Next, we traverse the string $s$ from left to right. Each time we encounter a character, we decrease its count by $1$, then we check whether the current window meets the condition, that is, the count of characters outside the window does not exceed $n/4$. If the condition is met, we update the answer, then move the left boundary of the window to the right until the condition is not met.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of the string $s$; and $C$ is the size of the character set, in this problem $C = 4$.

<!-- tabs:start -->

```python
class Solution:
    def balancedString(self, s: str) -> int:
        cnt = Counter(s)
        n = len(s)
        if all(v <= n // 4 for v in cnt.values()):
            return 0
        ans, j = n, 0
        for i, c in enumerate(s):
            cnt[c] -= 1
            while j <= i and all(v <= n // 4 for v in cnt.values()):
                ans = min(ans, i - j + 1)
                cnt[s[j]] += 1
                j += 1
        return ans
```

```java
class Solution {
    public int balancedString(String s) {
        int[] cnt = new int[4];
        String t = "QWER";
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]++;
        }
        int m = n / 4;
        if (cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m) {
            return 0;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]--;
            while (j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m) {
                ans = Math.min(ans, i - j + 1);
                cnt[t.indexOf(s.charAt(j++))]++;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int balancedString(string s) {
        int cnt[4]{};
        string t = "QWER";
        int n = s.size();
        for (char& c : s) {
            cnt[t.find(c)]++;
        }
        int m = n / 4;
        if (cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m) {
            return 0;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[t.find(s[i])]--;
            while (j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m) {
                ans = min(ans, i - j + 1);
                cnt[t.find(s[j++])]++;
            }
        }
        return ans;
    }
};
```

```go
func balancedString(s string) int {
	cnt := [4]int{}
	t := "QWER"
	n := len(s)
	for i := range s {
		cnt[strings.IndexByte(t, s[i])]++
	}
	m := n / 4
	if cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m {
		return 0
	}
	ans := n
	for i, j := 0, 0; i < n; i++ {
		cnt[strings.IndexByte(t, s[i])]--
		for j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m {
			ans = min(ans, i-j+1)
			cnt[strings.IndexByte(t, s[j])]++
			j++
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
