---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0696.Count%20Binary%20Substrings/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [696. Count Binary Substrings](https://leetcode.com/problems/count-binary-substrings)

[中文文档](/solution/0600-0699/0696.Count%20Binary%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Given a binary string <code>s</code>, return the number of non-empty substrings that have the same number of <code>0</code>&#39;s and <code>1</code>&#39;s, and all the <code>0</code>&#39;s and all the <code>1</code>&#39;s in these substrings are grouped consecutively.</p>

<p>Substrings that occur multiple times are counted the number of times they occur.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110011&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 substrings that have equal number of consecutive 1&#39;s and 0&#39;s: &quot;0011&quot;, &quot;01&quot;, &quot;1100&quot;, &quot;10&quot;, &quot;0011&quot;, and &quot;01&quot;.
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, &quot;00110011&quot; is not a valid substring because all the 0&#39;s (and 1&#39;s) are not grouped together.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10101&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 substrings: &quot;10&quot;, &quot;01&quot;, &quot;10&quot;, &quot;01&quot; that have equal number of consecutive 1&#39;s and 0&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Iteration and Counting

We can iterate through the string $s$, using a variable $\textit{pre}$ to record the count of the previous consecutive characters, and another variable $\textit{cur}$ to record the count of the current consecutive characters. The number of valid substrings ending with the current character is $\min(\textit{pre}, \textit{cur})$. We accumulate $\min(\textit{pre}, \textit{cur})$ to the answer, assign the value of $\textit{cur}$ to $\textit{pre}$, and continue iterating through string $s$ until the end.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        n = len(s)
        ans = i = 0
        pre = 0
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            cur = j - i
            ans += min(pre, cur)
            pre = cur
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int cur = j - i;
            ans += Math.min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countBinarySubstrings(string s) {
        int n = s.size();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int cur = j - i;
            ans += min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func countBinarySubstrings(s string) (ans int) {
	n := len(s)
	i := 0
	pre := 0
	for i < n {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		cur := j - i
		ans += min(pre, cur)
		pre = cur
		i = j
	}
	return
}
```

#### TypeScript

```ts
function countBinarySubstrings(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    let pre = 0;

    while (i < n) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            j++;
        }
        const cur = j - i;
        ans += Math.min(pre, cur);
        pre = cur;
        i = j;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_binary_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let n: usize = bytes.len();

        let mut ans: i32 = 0;
        let mut i: usize = 0;
        let mut pre: i32 = 0;

        while i < n {
            let mut j: usize = i + 1;
            while j < n && bytes[j] == bytes[i] {
                j += 1;
            }
            let cur: i32 = (j - i) as i32;
            ans += pre.min(cur);
            pre = cur;
            i = j;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
