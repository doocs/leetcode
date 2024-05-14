---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2656.Maximum%20Sum%20With%20Exactly%20K%20Elements/README_EN.md
rating: 1213
tags:
    - Greedy
    - Array
---

# [2656. Maximum Sum With Exactly K Elements](https://leetcode.com/problems/maximum-sum-with-exactly-k-elements)

[中文文档](/solution/2600-2699/2656.Maximum%20Sum%20With%20Exactly%20K%20Elements/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>. Your task is to perform the following operation <strong>exactly</strong> <code>k</code> times in order to maximize your score:</p>

<ol>
	<li>Select an element <code>m</code> from <code>nums</code>.</li>
	<li>Remove the selected element <code>m</code> from the array.</li>
	<li>Add a new element with a value of <code>m + 1</code> to the array.</li>
	<li>Increase your score by <code>m</code>.</li>
</ol>

<p>Return <em>the maximum score you can achieve after performing the operation exactly</em> <code>k</code> <em>times.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 3
<strong>Output:</strong> 18
<strong>Explanation:</strong> We need to choose exactly 3 elements from nums to maximize the sum.
For the first iteration, we choose 5. Then sum is 5 and nums = [1,2,3,4,6]
For the second iteration, we choose 6. Then sum is 5 + 6 and nums = [1,2,3,4,7]
For the third iteration, we choose 7. Then sum is 5 + 6 + 7 = 18 and nums = [1,2,3,4,8]
So, we will return 18.
It can be proven, that 18 is the maximum answer that we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5], k = 2
<strong>Output:</strong> 11
<strong>Explanation:</strong> We need to choose exactly 2 elements from nums to maximize the sum.
For the first iteration, we choose 5. Then sum is 5 and nums = [5,5,6]
For the second iteration, we choose 6. Then sum is 5 + 6 = 11 and nums = [5,5,7]
So, we will return 11.
It can be proven, that 11 is the maximum answer that we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

### Solution 1: Greedy + Mathematics

We notice that to make the final score maximum, we should make each choice as large as possible. Therefore, we select the largest element $x$ in the array for the first time, $x+1$ for the second time, $x+2$ for the third time, and so on, until the $k$th time we select $x+k-1$. This way of selection ensures that the element selected each time is the largest in the current array, so the final score is also the largest. The answer is $k$ $x$ sum plus $0+1+2+\cdots+(k-1)$, that is, $k \times x + (k - 1) \times k / 2$.

Time complexity is $O(n)$, where $n$ is the length of the array. Space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximizeSum(self, nums: List[int], k: int) -> int:
        x = max(nums)
        return k * x + k * (k - 1) // 2
```

```java
class Solution {
    public int maximizeSum(int[] nums, int k) {
        int x = 0;
        for (int v : nums) {
            x = Math.max(x, v);
        }
        return k * x + k * (k - 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int maximizeSum(vector<int>& nums, int k) {
        int x = *max_element(nums.begin(), nums.end());
        return k * x + k * (k - 1) / 2;
    }
};
```

```go
func maximizeSum(nums []int, k int) int {
	x := slices.Max(nums)
	return k*x + k*(k-1)/2
}
```

```ts
function maximizeSum(nums: number[], k: number): number {
    const x = Math.max(...nums);
    return k * x + (k * (k - 1)) / 2;
}
```

```rust
impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut mx = 0;

        for &n in &nums {
            if n > mx {
                mx = n;
            }
        }

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mx = *nums.iter().max().unwrap_or(&0);

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
```

<!-- tabs:end -->

<!-- end -->
