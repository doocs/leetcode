---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2460.Apply%20Operations%20to%20an%20Array/README_EN.md
rating: 1223
source: Weekly Contest 318 Q1
tags:
    - Array
    - Two Pointers
    - Simulation
---

<!-- problem:start -->

# [2460. Apply Operations to an Array](https://leetcode.com/problems/apply-operations-to-an-array)

[中文文档](/solution/2400-2499/2460.Apply%20Operations%20to%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of size <code>n</code> consisting of <strong>non-negative</strong> integers.</p>

<p>You need to apply <code>n - 1</code> operations to this array where, in the <code>i<sup>th</sup></code> operation (<strong>0-indexed</strong>), you will apply the following on the <code>i<sup>th</sup></code> element of <code>nums</code>:</p>

<ul>
	<li>If <code>nums[i] == nums[i + 1]</code>, then multiply <code>nums[i]</code> by <code>2</code> and set <code>nums[i + 1]</code> to <code>0</code>. Otherwise, you skip this operation.</li>
</ul>

<p>After performing <strong>all</strong> the operations, <strong>shift</strong> all the <code>0</code>&#39;s to the <strong>end</strong> of the array.</p>

<ul>
	<li>For example, the array <code>[1,0,2,0,0,1]</code> after shifting all its <code>0</code>&#39;s to the end, is <code>[1,2,1,0,0,0]</code>.</li>
</ul>

<p>Return <em>the resulting array</em>.</p>

<p><strong>Note</strong> that the operations are applied <strong>sequentially</strong>, not all at once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,1,1,0]
<strong>Output:</strong> [1,4,2,0,0,0]
<strong>Explanation:</strong> We do the following operations:
- i = 0: nums[0] and nums[1] are not equal, so we skip this operation.
- i = 1: nums[1] and nums[2] are equal, we multiply nums[1] by 2 and change nums[2] to 0. The array becomes [1,<strong><u>4</u></strong>,<strong><u>0</u></strong>,1,1,0].
- i = 2: nums[2] and nums[3] are not equal, so we skip this operation.
- i = 3: nums[3] and nums[4] are equal, we multiply nums[3] by 2 and change nums[4] to 0. The array becomes [1,4,0,<strong><u>2</u></strong>,<strong><u>0</u></strong>,0].
- i = 4: nums[4] and nums[5] are equal, we multiply nums[4] by 2 and change nums[5] to 0. The array becomes [1,4,0,2,<strong><u>0</u></strong>,<strong><u>0</u></strong>].
After that, we shift the 0&#39;s to the end, which gives the array [1,4,2,0,0,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> No operation can be applied, we just shift the 0 to the end.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate according to the problem description.

First, we traverse the array $nums$. For any two adjacent elements $nums[i]$ and $nums[i+1]$, if $nums[i] = nums[i+1]$, then we double the value of $nums[i]$ and change the value of $nums[i+1]$ to $0$.

Then, we create an answer array $ans$ of length $n$, and put all non-zero elements of $nums$ into $ans$ in order.

Finally, we return the answer array $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def applyOperations(self, nums: List[int]) -> List[int]:
        n = len(nums)
        for i in range(n - 1):
            if nums[i] == nums[i + 1]:
                nums[i] <<= 1
                nums[i + 1] = 0
        ans = [0] * n
        i = 0
        for x in nums:
            if x:
                ans[i] = x
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        int[] ans = new int[n];
        int i = 0;
        for (int x : nums) {
            if (x > 0) {
                ans[i++] = x;
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
    vector<int> applyOperations(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        vector<int> ans(n);
        int i = 0;
        for (int& x : nums) {
            if (x) {
                ans[i++] = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func applyOperations(nums []int) []int {
	n := len(nums)
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			nums[i] <<= 1
			nums[i+1] = 0
		}
	}
	ans := make([]int, n)
	i := 0
	for _, x := range nums {
		if x > 0 {
			ans[i] = x
			i++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function applyOperations(nums: number[]): number[] {
    const n = nums.length;
    for (let i = 0; i < n - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            nums[i] <<= 1;
            nums[i + 1] = 0;
        }
    }
    const ans: number[] = Array(n).fill(0);
    let i = 0;
    for (const x of nums) {
        if (x !== 0) {
            ans[i++] = x;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn apply_operations(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;

        for i in 0..nums.len() - 1 {
            if nums[i] == nums[i + 1] {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }

        let mut cur = 0;
        for i in 0..nums.len() {
            if nums[i] != 0 {
                nums.swap(i, cur);
                cur += 1;
            }
        }

        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
