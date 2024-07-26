---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2730.Find%20the%20Longest%20Semi-Repetitive%20Substring/README_EN.md
rating: 1501
source: Biweekly Contest 106 Q2
tags:
    - String
    - Sliding Window
---

<!-- problem:start -->

# [2730. Find the Longest Semi-Repetitive Substring](https://leetcode.com/problems/find-the-longest-semi-repetitive-substring)

[中文文档](/solution/2700-2799/2730.Find%20the%20Longest%20Semi-Repetitive%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a digit string <code>s</code> that consists of digits from 0 to 9.</p>

<p>A string is called <strong>semi-repetitive</strong> if there is <strong>at most</strong> one adjacent pair of the same digit. For example, <code>&quot;0010&quot;</code>, <code>&quot;002020&quot;</code>, <code>&quot;0123&quot;</code>, <code>&quot;2002&quot;</code>, and <code>&quot;54944&quot;</code> are semi-repetitive while the following are not: <code>&quot;00101022&quot;</code> (adjacent same digit pairs are 00 and 22), and <code>&quot;1101234883&quot;</code> (adjacent same digit pairs are 11 and 88).</p>

<p>Return the length of the <strong>longest semi-repetitive <span data-keyword="substring-nonempty">substring</span></strong> of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;52233&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest semi-repetitive substring is &quot;5223&quot;. Picking the whole string &quot;52233&quot; has two adjacent same digit pairs 22 and 33, but at most one is allowed.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;5494&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><code>s</code> is a semi-repetitive string.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1111111&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest semi-repetitive substring is &quot;11&quot;. Picking the substring &quot;111&quot; has two adjacent same digit pairs, but at most one is allowed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>&#39;0&#39; &lt;= s[i] &lt;= &#39;9&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers to maintain a range $s[j..i]$ such that there is at most one pair of adjacent characters that are equal, initially $j = 0$, $i = 1$. Initialize the answer $ans = 1$.

We use $cnt$ to record the number of pairs of adjacent characters that are equal in the range. If $cnt > 1$, then we need to move the left pointer $j$ until $cnt \le 1$. Each time, we update the answer as $ans = \max(ans, i - j + 1)$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers (Optimization)

Since the problem only requires us to find the length of the longest semi-repetitive substring, each time the number of adjacent identical characters in the interval exceeds $1$, we can move the left pointer $l$ once, while the right pointer $r$ continues to move to the right. This ensures that the length of the substring does not decrease.

Finally, the answer is $n - l$, where $n$ is the length of the string.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSemiRepetitiveSubstring(self, s: str) -> int:
        n = len(s)
        cnt = l = 0
        for i in range(1, n):
            cnt += s[i] == s[i - 1]
            if cnt > 1:
                cnt -= s[l] == s[l + 1]
                l += 1
        return n - l
```

#### Java

```java
class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int cnt = 0, l = 0;
        for (int i = 1; i < n; ++i) {
            cnt += s.charAt(i) == s.charAt(i - 1) ? 1 : 0;
            if (cnt > 1) {
                cnt -= s.charAt(l) == s.charAt(++l) ? 1 : 0;
            }
        }
        return n - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSemiRepetitiveSubstring(string s) {
        int n = s.length();
        int cnt = 0, l = 0;
        for (int i = 1; i < n; ++i) {
            cnt += s[i] == s[i - 1] ? 1 : 0;
            if (cnt > 1) {
                cnt -= s[l] == s[++l] ? 1 : 0;
            }
        }
        return n - l;
    }
};
```

#### Go

```go
func longestSemiRepetitiveSubstring(s string) (ans int) {
	cnt, l := 0, 0
	for i, c := range s[1:] {
		if byte(c) == s[i] {
			cnt++
		}
		if cnt > 1 {
			if s[l] == s[l+1] {
				cnt--
			}
			l++
		}
	}
	return len(s) - l
}
```

#### TypeScript

```ts
function longestSemiRepetitiveSubstring(s: string): number {
    const n = s.length;
    let [cnt, l] = [0, 0];
    for (let i = 1; i < n; ++i) {
        cnt += s[i] === s[i - 1] ? 1 : 0;
        if (cnt > 1) {
            cnt -= s[l] === s[l + 1] ? 1 : 0;
            ++l;
        }
    }
    return n - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
