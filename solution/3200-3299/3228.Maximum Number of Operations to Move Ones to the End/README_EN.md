---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README_EN.md
rating: 1593
source: Weekly Contest 407 Q3
tags:
    - Greedy
    - String
    - Counting
---

<!-- problem:start -->

# [3228. Maximum Number of Operations to Move Ones to the End](https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end)

[中文文档](/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="binary-string">binary string</span> <code>s</code>.</p>

<p>You can perform the following operation on the string <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>any</strong> index <code>i</code> from the string where <code>i + 1 &lt; s.length</code> such that <code>s[i] == &#39;1&#39;</code> and <code>s[i + 1] == &#39;0&#39;</code>.</li>
	<li>Move the character <code>s[i]</code> to the <strong>right</strong> until it reaches the end of the string or another <code>&#39;1&#39;</code>. For example, for <code>s = &quot;010010&quot;</code>, if we choose <code>i = 1</code>, the resulting string will be <code>s = &quot;0<strong><u>001</u></strong>10&quot;</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> number of operations that you can perform.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1001101&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>We can perform the following operations:</p>

<ul>
	<li>Choose index <code>i = 0</code>. The resulting string is <code>s = &quot;<u><strong>001</strong></u>1101&quot;</code>.</li>
	<li>Choose index <code>i = 4</code>. The resulting string is <code>s = &quot;0011<u><strong>01</strong></u>1&quot;</code>.</li>
	<li>Choose index <code>i = 3</code>. The resulting string is <code>s = &quot;001<strong><u>01</u></strong>11&quot;</code>.</li>
	<li>Choose index <code>i = 2</code>. The resulting string is <code>s = &quot;00<strong><u>01</u></strong>111&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;00111&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We use a variable $\textit{ans}$ to record the answer and another variable $\textit{cnt}$ to count the current number of $1$s.

Then, we iterate through the string $s$. If the current character is $1$, then we increment $\textit{cnt}$. Otherwise, if there is a previous character and the previous character is $1$, then the previous $\textit{cnt}$ number of $1$s can be moved backward, and we add $\textit{cnt}$ to the answer.

Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOperations(self, s: str) -> int:
        ans = cnt = 0
        for i, c in enumerate(s):
            if c == "1":
                cnt += 1
            elif i and s[i - 1] == "1":
                ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int maxOperations(String s) {
        int ans = 0, cnt = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else if (i > 0 && s.charAt(i - 1) == '1') {
                ans += cnt;
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
    int maxOperations(string s) {
        int ans = 0, cnt = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '1') {
                ++cnt;
            } else if (i && s[i - 1] == '1') {
                ans += cnt;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxOperations(s string) (ans int) {
	cnt := 0
	for i, c := range s {
		if c == '1' {
			cnt++
		} else if i > 0 && s[i-1] == '1' {
			ans += cnt
		}
	}
	return
}
```

#### TypeScript

```ts
function maxOperations(s: string): number {
    let [ans, cnt] = [0, 0];
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === '1') {
            ++cnt;
        } else if (i && s[i - 1] === '1') {
            ans += cnt;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_operations(s: String) -> i32 {
        let mut ans = 0;
        let mut cnt = 0;
        let n = s.len();
        let bytes = s.as_bytes();
        for i in 0..n {
            if bytes[i] == b'1' {
                cnt += 1;
            } else if i > 0 && bytes[i - 1] == b'1' {
                ans += cnt;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
