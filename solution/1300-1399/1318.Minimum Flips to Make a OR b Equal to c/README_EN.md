---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1318.Minimum%20Flips%20to%20Make%20a%20OR%20b%20Equal%20to%20c/README_EN.md
rating: 1382
source: Weekly Contest 171 Q2
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c)

[中文文档](/solution/1300-1399/1318.Minimum%20Flips%20to%20Make%20a%20OR%20b%20Equal%20to%20c/README.md)

## Description

<p>Given 3 positives numbers <code>a</code>, <code>b</code> and <code>c</code>. Return the minimum flips required in some bits of <code>a</code> and <code>b</code> to make (&nbsp;<code>a</code> OR <code>b</code> == <code>c</code>&nbsp;). (bitwise OR operation).<br />

Flip operation&nbsp;consists of change&nbsp;<strong>any</strong>&nbsp;single bit 1 to 0 or change the bit 0 to 1&nbsp;in their binary representation.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1318.Minimum%20Flips%20to%20Make%20a%20OR%20b%20Equal%20to%20c/images/sample_3_1676.png" style="width: 260px; height: 87px;" /></p>

<pre>

<strong>Input:</strong> a = 2, b = 6, c = 5

<strong>Output:</strong> 3

<strong>Explanation: </strong>After flips a = 1 , b = 4 , c = 5 such that (<code>a</code> OR <code>b</code> == <code>c</code>)</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> a = 4, b = 2, c = 7

<strong>Output:</strong> 1

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> a = 1, b = 2, c = 3

<strong>Output:</strong> 0

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= a &lt;= 10^9</code></li>

    <li><code>1 &lt;= b&nbsp;&lt;= 10^9</code></li>

    <li><code>1 &lt;= c&nbsp;&lt;= 10^9</code></li>

</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

We can enumerate each bit of the binary representation of $a$, $b$, and $c$, denoted as $x$, $y$, and $z$ respectively. If the bitwise OR operation result of $x$ and $y$ is different from $z$, we then check if both $x$ and $y$ are $1$. If so, we need to flip twice, otherwise, we only need to flip once. We accumulate all the required flip times.

The time complexity is $O(\log M)$, where $M$ is the maximum value of the numbers in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        ans = 0
        for i in range(32):
            x, y, z = a >> i & 1, b >> i & 1, c >> i & 1
            ans += x + y if z == 0 else int(x == 0 and y == 0)
        return ans
```

```java
class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            ans += z == 0 ? x + y : (x == 0 && y == 0 ? 1 : 0);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            ans += z == 0 ? x + y : (x == 0 && y == 0 ? 1 : 0);
        }
        return ans;
    }
};
```

```go
func minFlips(a int, b int, c int) (ans int) {
	for i := 0; i < 32; i++ {
		x, y, z := a>>i&1, b>>i&1, c>>i&1
		if z == 0 {
			ans += x + y
		} else if x == 0 && y == 0 {
			ans++
		}
	}
	return
}
```

```ts
function minFlips(a: number, b: number, c: number): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        const [x, y, z] = [(a >> i) & 1, (b >> i) & 1, (c >> i) & 1];
        ans += z === 0 ? x + y : x + y === 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
