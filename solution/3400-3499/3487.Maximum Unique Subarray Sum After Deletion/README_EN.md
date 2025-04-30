---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3487.Maximum%20Unique%20Subarray%20Sum%20After%20Deletion/README_EN.md
rating: 1399
source: Weekly Contest 441 Q1
tags:
    - Greedy
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3487. Maximum Unique Subarray Sum After Deletion](https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion)

[中文文档](/solution/3400-3499/3487.Maximum%20Unique%20Subarray%20Sum%20After%20Deletion/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You are allowed to delete any number of elements from <code>nums</code> without making it <strong>empty</strong>. After performing the deletions, select a <span data-keyword="subarray-nonempty">subarray</span> of <code>nums</code> such that:</p>

<ol>
	<li>All elements in the subarray are <strong>unique</strong>.</li>
	<li>The sum of the elements in the subarray is <strong>maximized</strong>.</li>
</ol>

<p>Return the <strong>maximum sum</strong> of such a subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>Select the entire array without deleting any element to obtain the maximum sum.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,0,1,1]</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>Delete the element <code>nums[0] == 1</code>, <code>nums[1] == 1</code>, <code>nums[2] == 0</code>, and <code>nums[3] == 1</code>. Select the entire array <code>[1]</code> to obtain the maximum sum.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,-1,-2,1,0,-1]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>Delete the elements <code>nums[2] == -1</code> and <code>nums[3] == -2</code>, and select the subarray <code>[2, 1]</code> from <code>[1, 2, 1, 0, -1]</code> to obtain the maximum sum.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Hash Table

We first find the maximum value $\textit{mx}$ in the array. If $\textit{mx} \leq 0$, then all elements in the array are less than or equal to 0. Since we need to select a non-empty subarray with the maximum element sum, the maximum element sum would be $\textit{mx}$.

If $\textit{mx} > 0$, then we need to find all distinct positive integers in the array such that their sum is maximized. We can use a hash table $\textit{s}$ to record all distinct positive integers, and then iterate through the array, adding up all distinct positive integers.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: List[int]) -> int:
        mx = max(nums)
        if mx <= 0:
            return mx
        ans = 0
        s = set()
        for x in nums:
            if x < 0 or x in s:
                continue
            ans += x
            s.add(x)
        return ans
```

#### Java

```java
class Solution {
    public int maxSum(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        if (mx <= 0) {
            return mx;
        }
        boolean[] s = new boolean[201];
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s[x]) {
                continue;
            }
            ans += x;
            s[x] = true;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums) {
        int mx = ranges::max(nums);
        if (mx <= 0) {
            return mx;
        }
        unordered_set<int> s;
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s.contains(x)) {
                continue;
            }
            ans += x;
            s.insert(x);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int) (ans int) {
	mx := slices.Max(nums)
	if mx <= 0 {
		return mx
	}
	s := make(map[int]bool)
	for _, x := range nums {
		if x < 0 || s[x] {
			continue
		}
		ans += x
		s[x] = true
	}
	return
}
```

#### TypeScript

```ts
function maxSum(nums: number[]): number {
    const mx = Math.max(...nums);
    if (mx <= 0) {
        return mx;
    }
    const s = new Set<number>();
    let ans: number = 0;
    for (const x of nums) {
        if (x < 0 || s.has(x)) {
            continue;
        }
        ans += x;
        s.add(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
