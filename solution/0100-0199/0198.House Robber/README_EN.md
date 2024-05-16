---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0198.House%20Robber/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [198. House Robber](https://leetcode.com/problems/house-robber)

[中文文档](/solution/0100-0199/0198.House%20Robber/README.md)

## Description

<!-- description:start -->

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <b>without alerting the police</b></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9,3,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * (n + 1)
        f[1] = nums[0]
        for i in range(2, n + 1):
            f[i] = max(f[i - 1], f[i - 2] + nums[i - 1])
        return f[n]
```

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        f[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            f[i] = max(f[i - 1], f[i - 2] + nums[i - 1]);
        }
        return f[n];
    }
};
```

```go
func rob(nums []int) int {
	n := len(nums)
	f := make([]int, n+1)
	f[1] = nums[0]
	for i := 2; i <= n; i++ {
		f[i] = max(f[i-1], f[i-2]+nums[i-1])
	}
	return f[n]
}
```

```ts
function rob(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    f[1] = nums[0];
    for (let i = 2; i <= n; ++i) {
        f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
    }
    return f[n];
}
```

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut f = [0, 0];
        for x in nums {
            f = [f[0].max(f[1]), f[0] + x];
        }
        f[0].max(f[1])
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = max(f, g), f + x
        return max(f, g)
```

```java
class Solution {
    public int rob(int[] nums) {
        int f = 0, g = 0;
        for (int x : nums) {
            int ff = Math.max(f, g);
            g = f + x;
            f = ff;
        }
        return Math.max(f, g);
    }
}
```

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = max(f, g);
            g = f + x;
            f = ff;
        }
        return max(f, g);
    }
};
```

```go
func rob(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = max(f, g), f+x
	}
	return max(f, g)
}
```

```ts
function rob(nums: number[]): number {
    let [f, g] = [0, 0];
    for (const x of nums) {
        [f, g] = [Math.max(f, g), f + x];
    }
    return Math.max(f, g);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
