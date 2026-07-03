---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3950.Exactly%20One%20Consecutive%20Set%20Bits%20Pair/README_EN.md
rating: 1270
source: Biweekly Contest 184 Q1
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [3950. Exactly One Consecutive Set Bits Pair](https://leetcode.com/problems/exactly-one-consecutive-set-bits-pair)

[中文文档](/solution/3900-3999/3950.Exactly%20One%20Consecutive%20Set%20Bits%20Pair/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Return <code>true</code> if its binary representation contains <strong>exactly one adjacent&nbsp;pair</strong> of&nbsp;<span data-keyword="set-bit">set bits</span>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Binary representation of 6 is <code>110</code>.</li>
	<li>There is exactly&nbsp;one adjacent pair of set bits (<code>&quot;11&quot;</code>). Thus, the answer is <code>true</code>​​​​​​​.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Binary representation of 5 is <code>101</code>.</li>
	<li>There is no&nbsp;adjacent pair of set bits. Thus, the answer is <code>false</code>​​​​​​​.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable $\textit{pre}$ to record the digit of the previous bit, initialized to $\textit{pre} = 0$, and another variable $\textit{vis}$ to record whether a pair of consecutive set bits has already been found, initialized to $\textit{vis} = \text{false}$.

Iterate through each binary bit of $n$, and denote the current binary bit as $\textit{cur}$. If $\textit{pre} = \textit{cur} = 1$, and if $\textit{vis} = \text{true}$ at this moment, it indicates that there are multiple pairs of consecutive set bits, so we directly return $\text{false}$. Otherwise, we set $\textit{vis}$ to $\text{true}$. Then, we update $\textit{pre} = \textit{cur}$ and continue to iterate through the next binary bit.

After the iteration ends, if $\textit{vis} = \text{true}$, return $\text{true}$; otherwise, return $\text{false}$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def consecutiveSetBits(self, n: int) -> bool:
        pre = 0
        vis = False
        while n:
            cur = n & 1
            if pre == cur == 1:
                if vis:
                    return False
                vis = True
            pre = cur
            n = n >> 1
        return vis
```

#### Java

```java
class Solution {
    public boolean consecutiveSetBits(int n) {
        boolean vis = false;
        for (int pre = 0; n > 0; n >>= 1) {
            int cur = n & 1;
            if (pre == cur && cur == 1) {
                if (vis) {
                    return false;
                }
                vis = true;
            }
            pre = cur;
        }
        return vis;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool consecutiveSetBits(int n) {
        bool vis = false;
        for (int pre = 0; n > 0; n >>= 1) {
            int cur = n & 1;
            if (pre == cur && cur == 1) {
                if (vis) {
                    return false;
                }
                vis = true;
            }
            pre = cur;
        }
        return vis;
    }
};
```

#### Go

```go
func consecutiveSetBits(n int) bool {
	vis := false
	for pre := 0; n > 0; n >>= 1 {
		cur := n & 1
		if pre == cur && cur == 1 {
			if vis {
				return false
			}
			vis = true
		}
		pre = cur
	}
	return vis
}
```

#### TypeScript

```ts
function consecutiveSetBits(n: number): boolean {
    let vis = false;
    for (let pre = 0; n > 0; n >>= 1) {
        const cur = n & 1;
        if (pre === cur && cur === 1) {
            if (vis) {
                return false;
            }
            vis = true;
        }
        pre = cur;
    }
    return vis;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
