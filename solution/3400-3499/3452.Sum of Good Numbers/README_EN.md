---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3452.Sum%20of%20Good%20Numbers/README_EN.md
rating: 1199
source: Biweekly Contest 150 Q1
tags:
    - Array
---

<!-- problem:start -->

# [3452. Sum of Good Numbers](https://leetcode.com/problems/sum-of-good-numbers)

[中文文档](/solution/3400-3499/3452.Sum%20of%20Good%20Numbers/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, an element <code>nums[i]</code> is considered <strong>good</strong> if it is <strong>strictly</strong> greater than the elements at indices <code>i - k</code> and <code>i + k</code> (if those indices exist). If neither of these indices <em>exists</em>, <code>nums[i]</code> is still considered <strong>good</strong>.</p>

<p>Return the <strong>sum</strong> of all the <strong>good</strong> elements in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,1,5,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The good numbers are <code>nums[1] = 3</code>, <code>nums[4] = 5</code>, and <code>nums[5] = 4</code> because they are strictly greater than the numbers at indices <code>i - k</code> and <code>i + k</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The only good number is <code>nums[0] = 2</code> because it is strictly greater than <code>nums[1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= floor(nums.length / 2)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We can traverse the array $\textit{nums}$ and check each element $\textit{nums}[i]$ to see if it meets the conditions:

- If $i \ge k$ and $\textit{nums}[i] \le \textit{nums}[i - k]$, then $\textit{nums}[i]$ is not a good number.
- If $i + k < \textit{len}(\textit{nums})$ and $\textit{nums}[i] \le \textit{nums}[i + k]$, then $\textit{nums}[i]$ is not a good number.
- Otherwise, $\textit{nums}[i]$ is a good number, and we add it to the answer.

After traversing, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodNumbers(self, nums: List[int], k: int) -> int:
        ans = 0
        for i, x in enumerate(nums):
            if i >= k and x <= nums[i - k]:
                continue
            if i + k < len(nums) and x <= nums[i + k]:
                continue
            ans += x
        return ans
```

#### Java

```java
class Solution {
    public int sumOfGoodNumbers(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i] <= nums[i - k]) {
                continue;
            }
            if (i + k < n && nums[i] <= nums[i + k]) {
                continue;
            }
            ans += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfGoodNumbers(vector<int>& nums, int k) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i] <= nums[i - k]) {
                continue;
            }
            if (i + k < n && nums[i] <= nums[i + k]) {
                continue;
            }
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfGoodNumbers(nums []int, k int) (ans int) {
	for i, x := range nums {
		if i >= k && x <= nums[i-k] {
			continue
		}
		if i+k < len(nums) && x <= nums[i+k] {
			continue
		}
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function sumOfGoodNumbers(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i >= k && nums[i] <= nums[i - k]) {
            continue;
        }
        if (i + k < n && nums[i] <= nums[i + k]) {
            continue;
        }
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
