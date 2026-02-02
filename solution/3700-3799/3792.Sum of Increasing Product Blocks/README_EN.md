---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3792.Sum%20of%20Increasing%20Product%20Blocks/README_EN.md
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [3792. Sum of Increasing Product Blocks 🔒](https://leetcode.com/problems/sum-of-increasing-product-blocks)

[中文文档](/solution/3700-3799/3792.Sum%20of%20Increasing%20Product%20Blocks/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>A sequence is formed as follows:</p>

<ul>
	<li>The <code>1<sup>st</sup></code> block contains <code>1</code>.</li>
	<li>The <code>2<sup>nd</sup></code> block contains <code>2 * 3</code>.</li>
	<li>The <code>i<sup>th</sup></code> block is the product of the next <code>i</code> consecutive integers.</li>
</ul>

<p>Let <code>F(n)</code> be the sum of the first <code>n</code> blocks.</p>

<p>Return an integer denoting <code>F(n)</code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">127</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Block 1: <code>1</code></li>
	<li>Block 2: <code>2 * 3 = 6</code></li>
	<li>Block 3: <code>4 * 5 * 6 = 120</code></li>
</ul>

<p><code>F(3) = 1 + 6 + 120 = 127</code></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">6997165</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Block 1: <code>1</code></li>
	<li>Block 2: <code>2 * 3 = 6</code></li>
	<li>Block 3: <code>4 * 5 * 6 = 120</code></li>
	<li>Block 4: <code>7 * 8 * 9 * 10 = 5040</code></li>
	<li>Block 5: <code>11 * 12 * 13 * 14 * 15 = 360360</code></li>
	<li>Block 6: <code>16 * 17 * 18 * 19 * 20 * 21 = 39070080</code></li>
	<li>Block 7: <code>22 * 23 * 24 * 25 * 26 * 27 * 28 = 5967561600</code></li>
</ul>

<p><code>F(7) = 6006997207 % (10<sup>9</sup> + 7) = 6997165</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the product of each block and accumulate it to the answer. Note that since the product can be very large, we need to take the modulo at each step of the calculation.

The time complexity is $O(n^2)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfBlocks(self, n: int) -> int:
        ans = 0
        mod = 10**9 + 7
        k = 1
        for i in range(1, n + 1):
            x = 1
            for j in range(k, k + i):
                x = (x * j) % mod
            ans = (ans + x) % mod
            k += i
        return ans
```

#### Java

```java
class Solution {
    public int sumOfBlocks(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfBlocks(int n) {
        const int mod = 1e9 + 7;
        long long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfBlocks(n int) (ans int) {
	const mod int = 1e9 + 7
	k := 1
	for i := 1; i <= n; i++ {
		x := 1
		for j := k; j < k+i; j++ {
			x = x * j % mod
		}
		ans = (ans + x) % mod
		k += i
	}
	return
}
```

#### TypeScript

```ts
function sumOfBlocks(n: number): number {
    const mod = 1000000007;
    let k = 1;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        let x = 1;
        for (let j = k; j < k + i; j++) {
            x = (x * j) % mod;
        }
        ans = (ans + x) % mod;
        k += i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
