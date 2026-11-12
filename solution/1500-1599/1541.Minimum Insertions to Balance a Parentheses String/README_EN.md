---
comments: true
difficulty: Medium
rating: 1759
source: Biweekly Contest 32 Q3
tags:
    - Stack
    - Greedy
    - String
    - Parentheses
---

<!-- problem:start -->

# [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string)

[中文文档](/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a parentheses string <code>s</code> containing only the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. A parentheses string is <strong>balanced</strong> if it can be formed using the following rules:</p>

<ul>
	<li>The empty string is balanced.</li>
	<li>If <code>A</code> is balanced, then <code>&#39;(&#39;</code> + <code>A</code> + <code>&#39;))&#39;</code> is balanced.</li>
	<li>If <code>A</code> and <code>B</code> are balanced, then their concatenation is balanced.</li>
</ul>

<p>In other words, every left parenthesis <code>&#39;(&#39;</code> must have a corresponding pair of two consecutive right parentheses <code>&#39;))&#39;</code> that comes after it, and every right parenthesis must belong to exactly one such matching pair.</p>

<p>For example, <code>&quot;())&quot;</code>, <code>&quot;())(())))&quot;</code> and <code>&quot;(())())))&quot;</code> are balanced, while <code>&quot;)()&quot;</code>, <code>&quot;()))&quot;</code> and <code>&quot;(()))&quot;</code> are not balanced.</p>

<p>You can insert the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> at any position of the string to balance it if needed.</p>

<p>Return the <strong>minimum</strong> number of insertions needed to make <code>s</code> balanced.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;(()))&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The second <code>&#39;(&#39;</code> is matched with two consecutive <code>&#39;)&#39;</code>, but the first <code>&#39;(&#39;</code> has only one matching <code>&#39;)&#39;</code>. We need to insert one more <code>&#39;)&#39;</code> at the end to make the string <code>&quot;(())))&quot;</code>, which is balanced.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;())&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The string is already balanced because its <code>&#39;(&#39;</code> is followed by two consecutive <code>&#39;)&#39;</code>, and both right parentheses belong to that matching pair.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;))())(&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Insert <code>&#39;(&#39;</code> at the beginning to match the first <code>&#39;))&#39;</code>, and insert <code>&#39;))&#39;</code> at the end to match the last <code>&#39;(&#39;</code>. Three insertions are needed in total.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A valid string pairs each '(' with two consecutive ')' . $n\le 10^5$, so repeatedly rescanning insertion sites is the wrong tool; one greedy pass is enough.
>
> Keep $x$, the number of unmatched left parentheses. A '(' increments $x$. On a ')', insert a mate if the next character is not also ')' . Then either insert a '(' when $x=0$, or consume one pending left. After the scan, each leftover left needs two right parentheses.

<!-- thinking:end -->

The greedy strategy is the same as above; the Rust version only changes the scanning mechanics: a `Peekable` iterator consumes the matching second `')'` with `next_if_eq`, so no explicit index bounds check is needed.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        ans = x = 0
        i, n = 0, len(s)
        while i < n:
            if s[i] == '(':
                x += 1
            else:
                if i < n - 1 and s[i + 1] == ')':
                    i += 1
                else:
                    ans += 1
                if x == 0:
                    ans += 1
                else:
                    x -= 1
            i += 1
        ans += x << 1
        return ans
```

#### Java

```java
class Solution {
    public int minInsertions(String s) {
        int ans = 0, x = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                ++x;
            } else {
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int ans = 0, x = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++x;
            } else {
                if (i < n - 1 && s[i + 1] == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
};
```

#### Go

```go
func minInsertions(s string) int {
	ans, x, n := 0, 0, len(s)
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			x++
		} else {
			if i < n-1 && s[i+1] == ')' {
				i++
			} else {
				ans++
			}
			if x == 0 {
				ans++
			} else {
				x--
			}
		}
	}
	ans += x << 1
	return ans
}
```

#### TypeScript

```ts
function minInsertions(s: string): number {
    let ans = 0;
    let x = 0;
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (s[i] === '(') {
            x++;
        } else {
            if (i < n - 1 && s[i + 1] === ')') {
                i++;
            } else {
                ans++;
            }
            if (x === 0) {
                ans++;
            } else {
                x--;
            }
        }
    }
    ans += x << 1;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_insertions(s: String) -> i32 {
        let mut ans = 0;
        let mut x = 0;
        let mut chars = s.bytes().peekable();
        while let Some(c) = chars.next() {
            if c == b'(' {
                x += 1;
            } else {
                if chars.next_if_eq(&b')').is_none() {
                    ans += 1;
                }
                if x == 0 {
                    ans += 1;
                } else {
                    x -= 1;
                }
            }
        }
        ans + x * 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
