---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0868.Binary%20Gap/README_EN.md
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [868. Binary Gap](https://leetcode.com/problems/binary-gap)

[中文文档](/solution/0800-0899/0868.Binary%20Gap/README.md)

## Description

<!-- description:start -->

<p>Given a positive integer <code>n</code>, find and return <em>the <strong>longest distance</strong> between any two <strong>adjacent</strong> </em><code>1</code><em>&#39;s in the binary representation of </em><code>n</code><em>. If there are no two adjacent </em><code>1</code><em>&#39;s, return </em><code>0</code><em>.</em></p>

<p>Two <code>1</code>&#39;s are <strong>adjacent</strong> if there are only <code>0</code>&#39;s separating them (possibly no <code>0</code>&#39;s). The <b>distance</b> between two <code>1</code>&#39;s is the absolute difference between their bit positions. For example, the two <code>1</code>&#39;s in <code>&quot;1001&quot;</code> have a distance of 3.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 22
<strong>Output:</strong> 2
<strong>Explanation:</strong> 22 in binary is &quot;10110&quot;.
The first adjacent pair of 1&#39;s is &quot;<u>1</u>0<u>1</u>10&quot; with a distance of 2.
The second adjacent pair of 1&#39;s is &quot;10<u>11</u>0&quot; with a distance of 1.
The answer is the largest of these two distances, which is 2.
Note that &quot;<u>1</u>01<u>1</u>0&quot; is not a valid pair since there is a 1 separating the two 1&#39;s underlined.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> 0
<strong>Explanation:</strong> 8 in binary is &quot;1000&quot;.
There are not any adjacent pairs of 1&#39;s in the binary representation of 8, so we return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 in binary is &quot;101&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

We use two pointers $\textit{pre}$ and $\textit{cur}$ to represent the positions of the previous and current $1$ bits, respectively. Initially, $\textit{pre} = 100$ and $\textit{cur} = 0$. Then, we traverse the binary representation of $n$. When we encounter a $1$, we calculate the distance between the current position and the previous $1$ position and update the answer.

The time complexity is $O(\log n)$, where $n$ is the given integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def binaryGap(self, n: int) -> int:
        ans = 0
        pre, cur = inf, 0
        while n:
            if n & 1:
                ans = max(ans, cur - pre)
                pre = cur
            cur += 1
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int binaryGap(int n) {
        int ans = 0;
        for (int pre = 100, cur = 0; n != 0; n >>= 1) {
            if (n % 2 == 1) {
                ans = Math.max(ans, cur - pre);
                pre = cur;
            }
            ++cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int binaryGap(int n) {
        int ans = 0;
        for (int pre = 100, cur = 0; n != 0; n >>= 1) {
            if (n & 1) {
                ans = max(ans, cur - pre);
                pre = cur;
            }
            ++cur;
        }
        return ans;
    }
};
```

#### Go

```go
func binaryGap(n int) (ans int) {
	for pre, cur := 100, 0; n != 0; n >>= 1 {
		if n&1 == 1 {
			ans = max(ans, cur-pre)
			pre = cur
		}
		cur++
	}
	return
}
```

#### TypeScript

```ts
function binaryGap(n: number): number {
    let ans = 0;
    for (let pre = 100, cur = 0; n; n >>= 1) {
        if (n & 1) {
            ans = Math.max(ans, cur - pre);
            pre = cur;
        }
        ++cur;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn binary_gap(mut n: i32) -> i32 {
        let mut ans = 0;
        let mut pre = 100;
        let mut cur = 0;
        while n != 0 {
            if n % 2 == 1 {
                ans = ans.max(cur - pre);
                pre = cur;
            }
            cur += 1;
            n >>= 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
