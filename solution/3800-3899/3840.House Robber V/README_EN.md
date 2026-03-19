---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3840.House%20Robber%20V/README_EN.md
rating: 1618
source: Biweekly Contest 176 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3840. House Robber V](https://leetcode.com/problems/house-robber-v)

[中文文档](/solution/3800-3899/3840.House%20Robber%20V/README.md)

## Description

<!-- description:start -->

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed and is protected by a security system with a color code.</p>

<p>You are given two integer arrays <code>nums</code> and <code>colors</code>, both of length <code>n</code>, where <code>nums[i]</code> is the amount of money in the <code>i<sup>th</sup></code> house and <code>colors[i]</code> is the color code of that house.</p>

<p>You <strong>cannot rob two adjacent</strong> houses if they share the <strong>same color</strong> code.</p>

<p>Return the <strong>maximum</strong> amount of money you can rob.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3,5], colors = [1,1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose houses <code>i = 1</code> with <code>nums[1] = 4</code> and <code>i = 3</code> with <code>nums[3] = 5</code> because they are non-adjacent.</li>
	<li>Thus, the total amount robbed is <code>4 + 5 = 9</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2,4], colors = [2,3,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose houses <code>i = 0</code> with <code>nums[0] = 3</code>, <code>i = 1</code> with <code>nums[1] = 1</code>, and <code>i = 3</code> with <code>nums[3] = 4</code>.</li>
	<li>This selection is valid because houses <code>i = 0</code> and <code>i = 1</code> have different colors, and house <code>i = 3</code> is non-adjacent to <code>i = 1</code>.</li>
	<li>Thus, the total amount robbed is <code>3 + 1 + 4 = 8</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,1,3,9], colors = [1,1,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose houses <code>i = 0</code> with <code>nums[0] = 10</code>, <code>i = 2</code> with <code>nums[2] = 3</code>, and <code>i = 3</code> with <code>nums[3] = 9</code>.</li>
	<li>This selection is valid because houses <code>i = 0</code> and <code>i = 2</code> are non-adjacent, and houses <code>i = 2</code> and <code>i = 3</code> have different colors.</li>
	<li>Thus, the total amount robbed is <code>10 + 3 + 9 = 22</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == colors.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], colors[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define two variables $f$ and $g$, where $f$ represents the maximum amount when the current house is not robbed, and $g$ represents the maximum amount when the current house is robbed. Initially, $f = 0$ and $g = nums[0]$. The answer is $\max(f, g)$.

Next, we traverse starting from the second house:

- If the current house has the same color as the previous house, then $f$ is updated to $\max(f, g)$, and $g$ is updated to $f + nums[i]$.
- If the current house has a different color from the previous house, then $f$ is updated to $\max(f, g)$, and $g$ is updated to $\max(f, g) + nums[i]$.

Finally, return $\max(f, g)$.

The time complexity is $O(n)$, where $n$ is the number of houses. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rob(self, nums: List[int], colors: List[int]) -> int:
        n = len(nums)
        f, g = 0, nums[0]
        for i in range(1, n):
            if colors[i - 1] == colors[i]:
                f, g = max(f, g), f + nums[i]
            else:
                f, g = max(f, g), max(f, g) + nums[i]
        return max(f, g)
```

#### Java

```java
class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long f = 0, g = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i - 1] == colors[i]) {
                long gg = f + nums[i];
                f = Math.max(f, g);
                g = gg;
            } else {
                long gg = Math.max(f, g) + nums[i];
                f = Math.max(f, g);
                g = gg;
            }
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long rob(vector<int>& nums, vector<int>& colors) {
        int n = nums.size();
        long long f = 0, g = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i - 1] == colors[i]) {
                long long gg = f + nums[i];
                f = max(f, g);
                g = gg;
            } else {
                long long gg = max(f, g) + nums[i];
                f = max(f, g);
                g = gg;
            }
        }
        return max(f, g);
    }
};
```

#### Go

```go
func rob(nums []int, colors []int) int64 {
	n := len(nums)
	var f int64 = 0
	var g int64 = int64(nums[0])

	for i := 1; i < n; i++ {
		if colors[i-1] == colors[i] {
			f, g = max(f, g), f+int64(nums[i])
		} else {
			f, g = max(f, g), max(f, g)+int64(nums[i])
		}
	}

	return max(f, g)
}
```

#### TypeScript

```ts
function rob(nums: number[], colors: number[]): number {
    const n = nums.length;
    let f = 0;
    let g = nums[0];

    for (let i = 1; i < n; i++) {
        if (colors[i - 1] === colors[i]) {
            [f, g] = [Math.max(f, g), f + nums[i]];
        } else {
            [f, g] = [Math.max(f, g), Math.max(f, g) + nums[i]];
        }
    }

    return Math.max(f, g);
}
```

#### Rust

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>, colors: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut f: i64 = 0;
        let mut g: i64 = nums[0] as i64;

        for i in 1..n {
            if colors[i - 1] == colors[i] {
                let gg = f + nums[i] as i64;
                f = f.max(g);
                g = gg;
            } else {
                let gg = f.max(g) + nums[i] as i64;
                f = f.max(g);
                g = gg;
            }
        }

        f.max(g)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
