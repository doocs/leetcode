---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3744.Find%20Kth%20Character%20in%20Expanded%20String/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [3744. Find Kth Character in Expanded String 🔒](https://leetcode.com/problems/find-kth-character-in-expanded-string)

[中文文档](/solution/3700-3799/3744.Find%20Kth%20Character%20in%20Expanded%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of one or more words separated by single spaces. Each word in <code>s</code> consists of lowercase English letters.</p>

<p>We obtain the <strong>expanded</strong> string <code>t</code> from <code>s</code> as follows:</p>

<ul>
	<li>For each <strong>word</strong> in <code>s</code>, repeat its first character once, then its second character twice, and so on.</li>
</ul>

<p>For example, if <code>s = &quot;hello world&quot;</code>, then <code>t = &quot;heelllllllooooo woorrrllllddddd&quot;</code>.</p>

<p>You are also given an integer <code>k</code>, representing a <strong>valid</strong> index of the string <code>t</code>.</p>

<p>Return the <code>k<sup>th</sup></code> character of the string <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hello world&quot;, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;h&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>t = &quot;heelllllllooooo woorrrllllddddd&quot;</code>. Therefore, the answer is <code>t[0] = &quot;h&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hello world&quot;, k = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot; &quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>t = &quot;heelllllllooooo woorrrllllddddd&quot;</code>. Therefore, the answer is <code>t[15] = &quot; &quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters and spaces <code>&#39; &#39;</code>.</li>
	<li><code>s</code> <strong>does not contain</strong> any leading or trailing spaces.</li>
	<li>All the words in <code>s</code> are separated by a <strong>single space</strong>.</li>
	<li><code>0 &lt;= k &lt; t.length</code>. That is, <code>k</code> is a <strong>valid</strong> index of <code>t</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Math + Simulation

We first split the string $\textit{s}$ into multiple words by spaces. For each word $\textit{w}$, we can calculate the length it occupies in the expanded string $\textit{t}$ as $m=\frac{(1+|\textit{w}|)\cdot |\textit{w}|}{2}$.

If $k = m$, it means the $k$-th character is a space, and we can directly return a space.

If $k > m$, it means the $k$-th character is not in the expanded part of the current word. We subtract the expanded length $m$ of the current word and the space length $1$ from $k$, and continue processing the next word.

Otherwise, the $k$-th character is in the expanded part of the current word. We can find the $k$-th character by simulating the expansion process:

-   Initialize a variable $\textit{cur} = 0$ to represent the number of characters that have been expanded so far.
-   Iterate through each character $\textit{w}[i]$ of the word $\textit{w}$:
    -   Increase $\textit{cur}$ by $i + 1$.
    -   If $k < \textit{cur}$, it means the $k$-th character is $\textit{w}[i]$, and we return this character.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthCharacter(self, s: str, k: int) -> str:
        for w in s.split():
            m = (1 + len(w)) * len(w) // 2
            if k == m:
                return " "
            if k > m:
                k -= m + 1
            else:
                cur = 0
                for i in range(len(w)):
                    cur += i + 1
                    if k < cur:
                        return w[i]
```

#### Java

```java
class Solution {
    public char kthCharacter(String s, long k) {
        for (String w : s.split(" ")) {
            long m = (1L + w.length()) * w.length() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w.charAt(i);
                    }
                }
            }
        }
        return ' ';
    }
}
```

#### C++

```cpp
class Solution {
public:
    char kthCharacter(string s, long long k) {
        stringstream ss(s);
        string w;
        while (ss >> w) {
            long long m = (1 + (long long) w.size()) * (long long) w.size() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w[i];
                    }
                }
            }
        }
        return ' ';
    }
};
```

#### Go

```go
func kthCharacter(s string, k int64) byte {
	for _, w := range strings.Split(s, " ") {
		m := (1 + int64(len(w))) * int64(len(w)) / 2
		if k == m {
			return ' '
		}
		if k > m {
			k -= m + 1
		} else {
			var cur int64
			for i := 0; ; i++ {
				cur += int64(i + 1)
				if k < cur {
					return w[i]
				}
			}
		}
	}
	return ' '
}
```

#### TypeScript

```ts
function kthCharacter(s: string, k: number): string {
    for (const w of s.split(' ')) {
        const m = ((1 + w.length) * w.length) / 2;
        if (k === m) {
            return ' ';
        }
        if (k > m) {
            k -= m + 1;
        } else {
            let cur = 0;
            for (let i = 0; ; ++i) {
                cur += i + 1;
                if (k < cur) {
                    return w[i];
                }
            }
        }
    }
    return ' ';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
