---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3954.Sum%20of%20Compatible%20Numbers%20in%20Range%20I/README_EN.md
---

<!-- problem:start -->

# [3954. Sum of Compatible Numbers in Range I](https://leetcode.com/problems/sum-of-compatible-numbers-in-range-i)

[中文文档](/solution/3900-3999/3954.Sum%20of%20Compatible%20Numbers%20in%20Range%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>k</code>.</p>

<p>A <strong>positive</strong> integer <code>x</code> is called <strong>compatible</strong> if it satisfies both of the following conditions:</p>

<ul>
    <li><code>abs(n - x) &lt;= k</code></li>
    <li><code>(n &amp; x) == 0</code></li>
</ul>

<p>Return the sum of all <strong>compatible</strong> integers <code>x</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
    <li>Here, <code>&amp;</code> denotes the <strong>bitwise AND</strong> operator.</li>
    <li>The <strong>absolute</strong> difference between integers <code>i</code> and <code>j</code> is defined as <code>abs(i - j)</code>.</li>
</ul>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The compatible integers are:</p>

<ul>
	<li><code>x = 1</code>, since <code>abs(2 - 1) = 1</code> and <code>2 &amp; 1 = 0</code>.</li>
	<li><code>x = 4</code>, since <code>abs(2 - 4) = 2</code> and <code>2 &amp; 4 = 0</code>.</li>
	<li><code>x = 5</code>, since <code>abs(2 - 5) = 3</code> and <code>2 &amp; 5 = 0</code>.</li>
</ul>

<p>Thus, the answer is <code>1 + 4 + 5 = 10</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no compatible integers in the range <code>[4, 6]</code>. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We iterate through $x$ within the range $[\max(1, n - k), n + k]$. If the bitwise AND result of $n$ and $x$ is $0$, we accumulate $x$ into the answer.

After the iteration ends, simply return the answer.

The time complexity is $O(k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodIntegers(self, n: int, k: int) -> int:
        ans = 0
        for x in range(max(1, n - k), n + k + 1):
            if (n & x) == 0:
                ans += x
        return ans
```

#### Java

```java
class Solution {
    public int sumOfGoodIntegers(int n, int k) {
        int ans = 0;
        int start = Math.max(1, n - k);
        int end = n + k;
        for (int x = start; x <= end; x++) {
            if ((n & x) == 0) {
                ans += x;
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
    int sumOfGoodIntegers(int n, int k) {
        int ans = 0;
        int start = max(1, n - k);
        int end = n + k;
        for (int x = start; x <= end; ++x) {
            if ((n & x) == 0) {
                ans += x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfGoodIntegers(n int, k int) (ans int) {
	start := max(1, n-k)
	end := n + k
	for x := start; x <= end; x++ {
		if (n & x) == 0 {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function sumOfGoodIntegers(n: number, k: number): number {
    let ans = 0;
    const start = Math.max(1, n - k);
    const end = n + k;
    for (let x = start; x <= end; x++) {
        if ((n & x) === 0) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
