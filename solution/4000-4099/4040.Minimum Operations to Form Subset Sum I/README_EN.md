---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4040.Minimum%20Operations%20to%20Form%20Subset%20Sum%20I/README_EN.md
rating: 1869
source: Weekly Contest 517 Q3
---

<!-- problem:start -->

# [4040. Minimum Operations to Form Subset Sum I](https://leetcode.com/problems/minimum-operations-to-form-subset-sum-i)

[中文文档](/solution/4000-4099/4040.Minimum%20Operations%20to%20Form%20Subset%20Sum%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>sum</code>.</p>

<p>In one <strong>operation</strong>, choose an element with current value <code>x</code> and replace it with either <code>2 * x</code> or <code>floor(x / 2)</code>.</p>

<p>For each element, all <strong>multiplication</strong> operations performed on it must occur <strong>before</strong> any <strong>division</strong> operations performed on it.</p>

<p>Return the <strong>minimum</strong> number of operations needed so that some <span data-keyword="subset">subset</span> of the resulting array has a sum <strong>exactly</strong> equal to <code>sum</code>. If it is impossible, return -1.</p>

<p>The <code>floor()</code> function returns the integer part of the division.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,6,10], sum = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Divide <code>nums[0] = 5</code> twice: <code>5 &rarr; 2 &rarr; 1</code>, costing 2 operations.</li>
	<li>Divide <code>nums[1] = 6</code> once: <code>6 &rarr; 3</code>, costing 1 operation.</li>
	<li>After these operations, <code>nums = [1, 3, 10]</code>. The subset <code>{1, 3}</code> sums to 4 using 3 operations in total.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,2], sum = 13</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Divide <code>nums[0] = 10</code> once: <code>10 &rarr; 5</code>, costing 1 operation.</li>
	<li>Multiply <code>nums[1] = 2</code> twice: <code>2 &rarr; 4 &rarr; 8</code>, costing 2 operations.</li>
	<li>After these operations, <code>nums = [5, 8]</code>. The subset <code>{5, 8}</code> sums to 13 using 3 operations in total.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,3], sum = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>No sequence of operations lets a subset of <code>nums</code> sum to 8, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 500</code></li>
	<li><code>1 &lt;= sum &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 0-1 Knapsack

Applying $a$ multiplications followed by $b$ divisions to an element gives $\lfloor x \times 2^a / 2^b \rfloor$, which is exactly $x \times 2^{a-b}$ or $\lfloor x / 2^{b-a} \rfloor$. The same value is reachable with only $|a - b|$ operations instead of $a + b$, so mixing the two directions is never worthwhile. Therefore each element has only two families of reachable values: $x \times 2^i$ or $\lfloor x / 2^i \rfloor$, each costing $i$ operations, while an element left out of the subset costs nothing.

This turns the problem into a 0-1 knapsack: every element contributes at most one (value, cost) pair, and we want the minimum cost to fill a capacity of exactly $\textit{sum}$.

We define $f[w]$ as the minimum number of operations needed for a subset to sum to exactly $w$, with $f[0] = 0$ and all other entries set to $+\infty$. For each element $x$, we iterate the capacity $w$ from large to small, enumerate every value $y$ that $x$ can become together with its cost $i$, and update $f[w]$ with $f[w - y] + i$ whenever $y \leq w$. If $f[\textit{sum}]$ is still $+\infty$ at the end, no valid sequence of operations exists and we return $-1$; otherwise we return $f[\textit{sum}]$.

The time complexity is $O(n \times S \times \log S)$, and the space complexity is $O(S)$. Here, $n$ is the length of the array $\textit{nums}$, and $S$ is the given $\textit{sum}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], sum: int) -> int:
        f = [0] + [inf] * sum
        for x in nums:
            for w in range(sum, -1, -1):
                i, y = 0, x
                while y <= w:
                    f[w] = min(f[w], f[w - y] + i)
                    i += 1
                    y <<= 1
                i, y = 1, x >> 1
                while y > 0:
                    if y <= w:
                        f[w] = min(f[w], f[w - y] + i)
                    i += 1
                    y >>= 1
        return -1 if f[sum] == inf else f[sum]
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int sum) {
        int inf = Integer.MAX_VALUE / 2;
        int[] f = new int[sum + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                int i = 0, y = x;
                while (y <= w) {
                    f[w] = Math.min(f[w], f[w - y] + i);
                    ++i;
                    y <<= 1;
                }

                i = 1;
                y = x >> 1;
                while (y > 0) {
                    if (y <= w) {
                        f[w] = Math.min(f[w], f[w - y] + i);
                    }
                    ++i;
                    y >>= 1;
                }
            }
        }

        return f[sum] == inf ? -1 : f[sum];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int sum) {
        const int inf = 1e9;
        vector<int> f(sum + 1, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                int i = 0, y = x;
                while (y <= w) {
                    f[w] = min(f[w], f[w - y] + i);
                    ++i;
                    y <<= 1;
                }

                i = 1;
                y = x >> 1;
                while (y > 0) {
                    if (y <= w) {
                        f[w] = min(f[w], f[w - y] + i);
                    }
                    ++i;
                    y >>= 1;
                }
            }
        }

        return f[sum] == inf ? -1 : f[sum];
    }
};
```

#### Go

```go
func minOperations(nums []int, sum int) int {
	const inf = int(1e9)

	f := make([]int, sum+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0

	for _, x := range nums {
		for w := sum; w >= 0; w-- {
			i, y := 0, x
			for y <= w {
				f[w] = min(f[w], f[w-y]+i)
				i++
				y <<= 1
			}

			i, y = 1, x>>1
			for y > 0 {
				if y <= w {
					f[w] = min(f[w], f[w-y]+i)
				}
				i++
				y >>= 1
			}
		}
	}

	if f[sum] == inf {
		return -1
	}
	return f[sum]
}
```

#### TypeScript

```ts
function minOperations(nums: number[], sum: number): number {
    const inf = 1e9;
    const f = Array(sum + 1).fill(inf);
    f[0] = 0;

    for (const x of nums) {
        for (let w = sum; w >= 0; --w) {
            let i = 0;
            let y = x;

            while (y <= w) {
                f[w] = Math.min(f[w], f[w - y] + i);
                ++i;
                y *= 2;
            }

            i = 1;
            y = Math.floor(x / 2);

            while (y > 0) {
                if (y <= w) {
                    f[w] = Math.min(f[w], f[w - y] + i);
                }
                ++i;
                y = Math.floor(y / 2);
            }
        }
    }

    return f[sum] === inf ? -1 : f[sum];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
