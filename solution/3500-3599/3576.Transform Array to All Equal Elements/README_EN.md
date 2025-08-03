---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README_EN.md
rating: 1489
source: Weekly Contest 453 Q1
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [3576. Transform Array to All Equal Elements](https://leetcode.com/problems/transform-array-to-all-equal-elements)

[中文文档](/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of size <code>n</code> containing only <code>1</code> and <code>-1</code>, and an integer <code>k</code>.</p>

<p>You can perform the following operation at most <code>k</code> times:</p>

<ul>
	<li>
	<p>Choose an index <code>i</code> (<code>0 &lt;= i &lt; n - 1</code>), and <strong>multiply</strong> both <code>nums[i]</code> and <code>nums[i + 1]</code> by <code>-1</code>.</p>
	</li>
</ul>

<p><strong>Note</strong> that you can choose the same index <code data-end="459" data-start="456">i</code> more than once in <strong>different</strong> operations.</p>

<p>Return <code>true</code> if it is possible to make all elements of the array <strong>equal</strong> after at most <code>k</code> operations, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,1,-1,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>We can make all elements in the array equal in 2 operations as follows:</p>

<ul>
	<li>Choose index <code>i = 1</code>, and multiply both <code>nums[1]</code> and <code>nums[2]</code> by -1. Now <code>nums = [1,1,-1,-1,1]</code>.</li>
	<li>Choose index <code>i = 2</code>, and multiply both <code>nums[2]</code> and <code>nums[3]</code> by -1. Now <code>nums = [1,1,1,1,1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-1,-1,1,1,1], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>It is not possible to make all array elements equal in at most 5 operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either -1 or 1.</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal and Counting

According to the problem description, to make all elements in the array equal, all elements must be either $\textit{nums}[0]$ or $-\textit{nums}[0]$. Therefore, we design a function $\textit{check}$ to determine whether the array can be transformed into all elements equal to $\textit{target}$ with at most $k$ operations.

The idea of this function is to traverse the array and count the number of operations needed. Each element is either modified once or not at all. If the current element is equal to the target value, no modification is needed and we continue to the next element. If the current element is not equal to the target value, an operation is needed, increment the counter, and flip the sign, indicating that subsequent elements need the opposite operation.

After the traversal, if the counter is less than or equal to $k$ and the sign of the last element matches the target value, return $\textit{true}$; otherwise, return $\textit{false}$.

The final answer is the result of $\textit{check}(\textit{nums}[0], k)$ or $\textit{check}(-\textit{nums}[0], k)$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMakeEqual(self, nums: List[int], k: int) -> bool:
        def check(target: int, k: int) -> bool:
            cnt, sign = 0, 1
            for i in range(len(nums) - 1):
                x = nums[i] * sign
                if x == target:
                    sign = 1
                else:
                    sign = -1
                    cnt += 1
            return cnt <= k and nums[-1] * sign == target

        return check(nums[0], k) or check(-nums[0], k)
```

#### Java

```java
class Solution {
    public boolean canMakeEqual(int[] nums, int k) {
        return check(nums, nums[0], k) || check(nums, -nums[0], k);
    }

    private boolean check(int[] nums, int target, int k) {
        int cnt = 0, sign = 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            int x = nums[i] * sign;
            if (x == target) {
                sign = 1;
            } else {
                sign = -1;
                ++cnt;
            }
        }
        return cnt <= k && nums[nums.length - 1] * sign == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canMakeEqual(vector<int>& nums, int k) {
        auto check = [&](int target, int k) -> bool {
            int n = nums.size();
            int cnt = 0, sign = 1;
            for (int i = 0; i < n - 1; ++i) {
                int x = nums[i] * sign;
                if (x == target) {
                    sign = 1;
                } else {
                    sign = -1;
                    ++cnt;
                }
            }
            return cnt <= k && nums[n - 1] * sign == target;
        };
        return check(nums[0], k) || check(-nums[0], k);
    }
};
```

#### Go

```go
func canMakeEqual(nums []int, k int) bool {
	check := func(target, k int) bool {
		cnt, sign := 0, 1
		for i := 0; i < len(nums)-1; i++ {
			x := nums[i] * sign
			if x == target {
				sign = 1
			} else {
				sign = -1
				cnt++
			}
		}
		return cnt <= k && nums[len(nums)-1]*sign == target
	}
	return check(nums[0], k) || check(-nums[0], k)
}
```

#### TypeScript

```ts
function canMakeEqual(nums: number[], k: number): boolean {
    function check(target: number, k: number): boolean {
        let [cnt, sign] = [0, 1];
        for (let i = 0; i < nums.length - 1; i++) {
            const x = nums[i] * sign;
            if (x === target) {
                sign = 1;
            } else {
                sign = -1;
                cnt++;
            }
        }
        return cnt <= k && nums[nums.length - 1] * sign === target;
    }

    return check(nums[0], k) || check(-nums[0], k);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_make_equal(nums: Vec<i32>, k: i32) -> bool {
        fn check(target: i32, k: i32, nums: &Vec<i32>) -> bool {
            let mut cnt = 0;
            let mut sign = 1;
            for i in 0..nums.len() - 1 {
                let x = nums[i] * sign;
                if x == target {
                    sign = 1;
                } else {
                    sign = -1;
                    cnt += 1;
                }
            }
            cnt <= k && nums[nums.len() - 1] * sign == target
        }

        check(nums[0], k, &nums) || check(-nums[0], k, &nums)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
