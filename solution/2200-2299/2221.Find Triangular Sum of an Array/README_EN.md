---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/README_EN.md
rating: 1317
source: Biweekly Contest 75 Q2
tags:
    - Array
    - Math
    - Combinatorics
    - Simulation
---

<!-- problem:start -->

# [2221. Find Triangular Sum of an Array](https://leetcode.com/problems/find-triangular-sum-of-an-array)

[中文文档](/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, where <code>nums[i]</code> is a digit between <code>0</code> and <code>9</code> (<strong>inclusive</strong>).</p>

<p>The <strong>triangular sum</strong> of <code>nums</code> is the value of the only element present in <code>nums</code> after the following process terminates:</p>

<ol>
	<li>Let <code>nums</code> comprise of <code>n</code> elements. If <code>n == 1</code>, <strong>end</strong> the process. Otherwise, <strong>create</strong> a new <strong>0-indexed</strong> integer array <code>newNums</code> of length <code>n - 1</code>.</li>
	<li>For each index <code>i</code>, where <code>0 &lt;= i &lt;&nbsp;n - 1</code>, <strong>assign</strong> the value of <code>newNums[i]</code> as <code>(nums[i] + nums[i+1]) % 10</code>, where <code>%</code> denotes modulo operator.</li>
	<li><strong>Replace</strong> the array <code>nums</code> with <code>newNums</code>.</li>
	<li><strong>Repeat</strong> the entire process starting from step 1.</li>
</ol>

<p>Return <em>the triangular sum of</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/images/ex1drawio.png" style="width: 250px; height: 250px;" />
<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 8
<strong>Explanation:</strong>
The above diagram depicts the process from which we obtain the triangular sum of the array.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
Since there is only one element in nums, the triangular sum is the value of that element itself.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the operations described in the problem. Perform $n - 1$ rounds of operations on the array $\textit{nums}$, updating the array $\textit{nums}$ according to the rules described in the problem for each round. Finally, return the only remaining element in the array $\textit{nums}$.

The time complexity is $O(n^2)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def triangularSum(self, nums: List[int]) -> int:
        for k in range(len(nums) - 1, 0, -1):
            for i in range(k):
                nums[i] = (nums[i] + nums[i + 1]) % 10
        return nums[0]
```

#### Java

```java
class Solution {
    public int triangularSum(int[] nums) {
        for (int k = nums.length - 1; k > 0; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int triangularSum(vector<int>& nums) {
        for (int k = nums.size() - 1; k; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
};
```

#### Go

```go
func triangularSum(nums []int) int {
	for k := len(nums) - 1; k > 0; k-- {
		for i := 0; i < k; i++ {
			nums[i] = (nums[i] + nums[i+1]) % 10
		}
	}
	return nums[0]
}
```

#### TypeScript

```ts
function triangularSum(nums: number[]): number {
    for (let k = nums.length - 1; k; --k) {
        for (let i = 0; i < k; ++i) {
            nums[i] = (nums[i] + nums[i + 1]) % 10;
        }
    }
    return nums[0];
}
```

#### Rust

```rust
impl Solution {
    pub fn triangular_sum(mut nums: Vec<i32>) -> i32 {
        let mut k = nums.len() as i32 - 1;
        while k > 0 {
            for i in 0..k as usize {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            k -= 1;
        }
        nums[0]
    }
}
```

#### C#

```cs
public class Solution {
    public int TriangularSum(int[] nums) {
        for (int k = nums.Length - 1; k > 0; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
