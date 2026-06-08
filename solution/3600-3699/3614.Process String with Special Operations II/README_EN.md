---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3614.Process%20String%20with%20Special%20Operations%20II/README_EN.md
rating: 2010
source: Weekly Contest 458 Q3
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [3614. Process String with Special Operations II](https://leetcode.com/problems/process-string-with-special-operations-ii)

[中文文档](/solution/3600-3699/3614.Process%20String%20with%20Special%20Operations%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters and the special characters: <code>&#39;*&#39;</code>, <code>&#39;#&#39;</code>, and <code>&#39;%&#39;</code>.</p>

<p>You are also given an integer <code>k</code>.</p>

<p>Build a new string <code>result</code> by processing <code>s</code> according to the following rules from left to right:</p>

<ul>
	<li>If the letter is a <strong>lowercase</strong> English letter append it to <code>result</code>.</li>
	<li>A <code>&#39;*&#39;</code> <strong>removes</strong> the last character from <code>result</code>, if it exists.</li>
	<li>A <code>&#39;#&#39;</code> <strong>duplicates</strong> the current <code>result</code> and <strong>appends</strong> it to itself.</li>
	<li>A <code>&#39;%&#39;</code> <strong>reverses</strong> the current <code>result</code>.</li>
</ul>

<p>Return the <code>k<sup>th</sup></code> character of the final string <code>result</code>. If <code>k</code> is out of the bounds of <code>result</code>, return <code>&#39;.&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a#b%*&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;a&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">Operation</th>
			<th style="border: 1px solid black;">Current <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&#39;a&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;a&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;a&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;aa&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&#39;b&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;b&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;aab&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>&#39;%&#39;</code></td>
			<td style="border: 1px solid black;">Reverse <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;baa&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>&#39;*&#39;</code></td>
			<td style="border: 1px solid black;">Remove the last character</td>
			<td style="border: 1px solid black;"><code>&quot;ba&quot;</code></td>
		</tr>
	</tbody>
</table>

<p>The final <code>result</code> is <code>&quot;ba&quot;</code>. The character at index <code>k = 1</code> is <code>&#39;a&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cd%#*#&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;d&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">Operation</th>
			<th style="border: 1px solid black;">Current <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&#39;c&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;c&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;c&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&#39;d&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;d&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;cd&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&#39;%&#39;</code></td>
			<td style="border: 1px solid black;">Reverse <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;dc&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;dcdc&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>&#39;*&#39;</code></td>
			<td style="border: 1px solid black;">Remove the last character</td>
			<td style="border: 1px solid black;"><code>&quot;dcd&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;dcddcd&quot;</code></td>
		</tr>
	</tbody>
</table>

<p>The final <code>result</code> is <code>&quot;dcddcd&quot;</code>. The character at index <code>k = 3</code> is <code>&#39;d&#39;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;z*#&quot;, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;.&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">Operation</th>
			<th style="border: 1px solid black;">Current <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&#39;z&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;z&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;z&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&#39;*&#39;</code></td>
			<td style="border: 1px solid black;">Remove the last character</td>
			<td style="border: 1px solid black;"><code>&quot;&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate the string</td>
			<td style="border: 1px solid black;"><code>&quot;&quot;</code></td>
		</tr>
	</tbody>
</table>

<p>The final <code>result</code> is <code>&quot;&quot;</code>. Since index <code>k = 0</code> is out of bounds, the output is <code>&#39;.&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters and special characters <code>&#39;*&#39;</code>, <code>&#39;#&#39;</code>, and <code>&#39;%&#39;</code>.</li>
	<li><code>0 &lt;= k &lt;= 10<sup>15</sup></code></li>
	<li>The length of <code>result</code> after processing <code>s</code> will not exceed <code>10<sup>15</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Tracking

We first calculate the length $m$ of the processed result string $\textit{result}$. If $k \geq m$, it indicates that $k$ exceeds the valid indices of the result string, so we return '.'.

Otherwise, we traverse the string $s$ in reverse order and handle each character based on the following cases:

1. If $s[i]$ is '\*', we increase $m$ by $1$.
2. If $s[i]$ is '#', we divide $m$ by $2$. At this point, if $k \geq m$, we subtract $m$ from $k$.
3. If $s[i]$ is '%', we update $k$ to $m - 1 - k$.
4. Otherwise, $s[i]$ is a letter. We decrease $m$ by $1$. If $k = m$, it means we have found the $k$-th character, so we return $s[i]$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def processStr(self, s: str, k: int) -> str:
        m = 0
        for c in s:
            if c == "*":
                m = max(0, m - 1)
            elif c == "#":
                m <<= 1
            elif c != "%":
                m += 1
        if k >= m:
            return "."
        for c in reversed(s):
            if c == "*":
                m += 1
            elif c == "#":
                m //= 2
                if k >= m:
                    k -= m
            elif c == "%":
                k = m - 1 - k
            else:
                m -= 1
                if k == m:
                    return c
```

#### Java

```java
class Solution {
    public char processStr(String s, long k) {
        long m = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                m = Math.max(0, m - 1);
            } else if (c == '#') {
                m <<= 1;
            } else if (c != '%') {
                m += 1;
            }
        }
        if (k >= m) {
            return '.';
        }
        for (int i = s.length() - 1;; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                m += 1;
            } else if (c == '#') {
                m /= 2;
                if (k >= m) {
                    k -= m;
                }
            } else if (c == '%') {
                k = m - 1 - k;
            } else {
                m -= 1;
                if (k == m) {
                    return c;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    char processStr(string s, long long k) {
        long long m = 0;
        for (char c : s) {
            if (c == '*') {
                m = max(0LL, m - 1);
            } else if (c == '#') {
                m <<= 1;
            } else if (c != '%') {
                m += 1;
            }
        }
        if (k >= m) {
            return '.';
        }
        for (int i = s.length() - 1;; i--) {
            char c = s[i];
            if (c == '*') {
                m += 1;
            } else if (c == '#') {
                m /= 2;
                if (k >= m) {
                    k -= m;
                }
            } else if (c == '%') {
                k = m - 1 - k;
            } else {
                m -= 1;
                if (k == m) {
                    return c;
                }
            }
        }
    }
};
```

#### Go

```go
func processStr(s string, k int64) byte {
	var m int64 = 0
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c == '*' {
			if m-1 > 0 {
				m = m - 1
			} else {
				m = 0
			}
		} else if c == '#' {
			m <<= 1
		} else if c != '%' {
			m += 1
		}
	}
	if k >= m {
		return '.'
	}
	for i := len(s) - 1; ; i-- {
		c := s[i]
		if c == '*' {
			m += 1
		} else if c == '#' {
			m /= 2
			if k >= m {
				k -= m
			}
		} else if c == '%' {
			k = m - 1 - k
		} else {
			m -= 1
			if k == m {
				return c
			}
		}
	}
}
```

#### TypeScript

```ts
function processStr(s: string, k: number): string {
    let m = 0n;
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        if (c === '*') {
            const sub = m - 1n;
            m = sub > 0n ? sub : 0n;
        } else if (c === '#') {
            m <<= 1n;
        } else if (c !== '%') {
            m += 1n;
        }
    }
    if (BigInt(k) >= m) {
        return '.';
    }
    let bigK = BigInt(k);
    for (let i = s.length - 1; ; i--) {
        const c = s[i];
        if (c === '*') {
            m += 1n;
        } else if (c === '#') {
            m /= 2n;
            if (bigK >= m) {
                bigK -= m;
            }
        } else if (c === '%') {
            bigK = m - 1n - bigK;
        } else {
            m -= 1n;
            if (bigK === m) {
                return c;
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
