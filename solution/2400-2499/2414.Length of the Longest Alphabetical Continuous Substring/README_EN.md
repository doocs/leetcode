---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README_EN.md
rating: 1221
source: Weekly Contest 311 Q2
tags:
    - String
---

<!-- problem:start -->

# [2414. Length of the Longest Alphabetical Continuous Substring](https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring)

[中文文档](/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README.md)

## Description

<!-- description:start -->

<p>An <strong>alphabetical continuous string</strong> is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string <code>&quot;abcdefghijklmnopqrstuvwxyz&quot;</code>.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> is an alphabetical continuous string, while <code>&quot;acb&quot;</code> and <code>&quot;za&quot;</code> are not.</li>
</ul>

<p>Given a string <code>s</code> consisting of lowercase letters only, return the <em>length of the <strong>longest</strong> alphabetical continuous substring.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacaba&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 4 distinct continuous substrings: &quot;a&quot;, &quot;b&quot;, &quot;c&quot; and &quot;ab&quot;.
&quot;ab&quot; is the longest continuous substring.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> &quot;abcde&quot; is the longest continuous substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We can traverse the string $s$ and use a variable $\textit{ans}$ to record the length of the longest lexicographically consecutive substring, and another variable $\textit{cnt}$ to record the length of the current consecutive substring. Initially, $\textit{ans} = \textit{cnt} = 1$.

Next, we start traversing the string $s$ from the character at index $1$. For each character $s[i]$, if $s[i] - s[i - 1] = 1$, it means the current character and the previous character are consecutive. In this case, $\textit{cnt} = \textit{cnt} + 1$, and we update $\textit{ans} = \max(\textit{ans}, \textit{cnt})$. Otherwise, it means the current character and the previous character are not consecutive, so $\textit{cnt} = 1$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = cnt = 1
        for x, y in pairwise(map(ord, s)):
            if y - x == 1:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 1
        return ans
```

#### Java

```java
class Solution {
    public int longestContinuousSubstring(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) - s.charAt(i - 1) == 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestContinuousSubstring(string s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.size(); ++i) {
            if (s[i] - s[i - 1] == 1) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestContinuousSubstring(s string) int {
	ans, cnt := 1, 1
	for i := range s[1:] {
		if s[i+1]-s[i] == 1 {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestContinuousSubstring(s: string): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < s.length; ++i) {
        if (s.charCodeAt(i) - s.charCodeAt(i - 1) === 1) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_continuous_substring(s: String) -> i32 {
        let mut ans = 1;
        let mut cnt = 1;
        let s = s.as_bytes();
        for i in 1..s.len() {
            if s[i] - s[i - 1] == 1 {
                cnt += 1;
                ans = ans.max(cnt);
            } else {
                cnt = 1;
            }
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char* s) {
    int n = strlen(s);
    int ans = 1, cnt = 1;
    for (int i = 1; i < n; ++i) {
        if (s[i] - s[i - 1] == 1) {
            ++cnt;
            ans = max(ans, cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
