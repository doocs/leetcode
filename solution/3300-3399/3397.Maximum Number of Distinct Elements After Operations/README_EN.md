---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3397.Maximum%20Number%20of%20Distinct%20Elements%20After%20Operations/README_EN.md
rating: 1687
source: Weekly Contest 429 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3397. Maximum Number of Distinct Elements After Operations](https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations)

[中文文档](/solution/3300-3399/3397.Maximum%20Number%20of%20Distinct%20Elements%20After%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You are allowed to perform the following <strong>operation</strong> on each element of the array <strong>at most</strong> <em>once</em>:</p>

<ul>
	<li>Add an integer in the range <code>[-k, k]</code> to the element.</li>
</ul>

<p>Return the <strong>maximum</strong> possible number of <strong>distinct</strong> elements in <code>nums</code> after performing the <strong>operations</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,3,3,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums</code> changes to <code>[-1, 0, 1, 2, 3, 4]</code> after performing operations on the first four elements.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4,4,4], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>By adding -1 to <code>nums[0]</code> and 1 to <code>nums[1]</code>, <code>nums</code> changes to <code>[3, 5, 4, 4]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

We can sort the array $\textit{nums}$ and then consider each element $x$ from left to right.

For the first element, we can greedily change it to $x - k$, making $x$ as small as possible to leave more space for subsequent elements. We use the variable $\textit{pre}$ to track the maximum value of the elements used so far, initialized to negative infinity.

For subsequent elements $x$, we can greedily change it to $\min(x + k, \max(x - k, \textit{pre} + 1))$. Here, $\max(x - k, \textit{pre} + 1)$ means we try to make $x$ as small as possible but not smaller than $\textit{pre} + 1$. If this value exists and is less than $x + k$, we can change $x$ to this value, increment the count of distinct elements, and update $\textit{pre}$ to this value.

After traversing the array, we obtain the maximum number of distinct elements.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistinctElements(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 0
        pre = -inf
        for x in nums:
            cur = min(x + k, max(x - k, pre + 1))
            if cur > pre:
                ans += 1
                pre = cur
        return ans
```

#### Java

```java
class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0, pre = Integer.MIN_VALUE;
        for (int x : nums) {
            int cur = Math.min(x + k, Math.max(x - k, pre + 1));
            if (cur > pre) {
                ++ans;
                pre = cur;
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
    int maxDistinctElements(vector<int>& nums, int k) {
        ranges::sort(nums);
        int ans = 0, pre = INT_MIN;
        for (int x : nums) {
            int cur = min(x + k, max(x - k, pre + 1));
            if (cur > pre) {
                ++ans;
                pre = cur;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDistinctElements(nums []int, k int) (ans int) {
	sort.Ints(nums)
	pre := math.MinInt32
	for _, x := range nums {
		cur := min(x+k, max(x-k, pre+1))
		if cur > pre {
			ans++
			pre = cur
		}
	}
	return
}
```

#### TypeScript

```ts
function maxDistinctElements(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let [ans, pre] = [0, -Infinity];
    for (const x of nums) {
        const cur = Math.min(x + k, Math.max(x - k, pre + 1));
        if (cur > pre) {
            ++ans;
            pre = cur;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
