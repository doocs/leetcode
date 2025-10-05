---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3701.Compute%20Alternating%20Sum/README_EN.md
---

<!-- problem:start -->

# [3701. Compute Alternating Sum](https://leetcode.com/problems/compute-alternating-sum)

[中文文档](/solution/3700-3799/3701.Compute%20Alternating%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>The <strong>alternating sum</strong> of <code>nums</code> is the value obtained by <strong>adding</strong> elements at even indices and <strong>subtracting</strong> elements at odd indices. That is, <code>nums[0] - nums[1] + nums[2] - nums[3]...</code></p>

<p>Return an integer denoting the alternating sum of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">-4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Elements at even indices are <code>nums[0] = 1</code> and <code>nums[2] = 5</code> because 0 and 2 are even numbers.</li>
	<li>Elements at odd indices are <code>nums[1] = 3</code> and <code>nums[3] = 7</code> because 1 and 3 are odd numbers.</li>
	<li>The alternating sum is <code>nums[0] - nums[1] + nums[2] - nums[3] = 1 - 3 + 5 - 7 = -4</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [100]</span></p>

<p><strong>Output:</strong> <span class="example-io">100</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The only element at even indices is <code>nums[0] = 100</code> because 0 is an even number.</li>
	<li>There are no elements on odd indices.</li>
	<li>The alternating sum is <code>nums[0] = 100</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly traverse the array $\textit{nums}$. For each index $i$, if $i$ is even, we add $\textit{nums}[i]$ to the answer; otherwise, we subtract $\textit{nums}[i]$ from the answer.

Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def alternatingSum(self, nums: List[int]) -> int:
        return sum(nums[0::2]) - sum(nums[1::2])
```

#### Java

```java
class Solution {
    public int alternatingSum(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans += (i % 2 == 0 ? nums[i] : -nums[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int alternatingSum(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += (i % 2 == 0 ? nums[i] : -nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func alternatingSum(nums []int) (ans int) {
	for i, x := range nums {
		if i%2 == 0 {
			ans += x
		} else {
			ans -= x
		}
	}
	return
}
```

#### TypeScript

```ts
function alternatingSum(nums: number[]): number {
    let ans: number = 0;
    for (let i = 0; i < nums.length; ++i) {
        ans += i % 2 === 0 ? nums[i] : -nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
