---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3978.Unique%20Middle%20Element/README_EN.md
rating: 1180
source: Biweekly Contest 186 Q1
---

<!-- problem:start -->

# [3978. Unique Middle Element](https://leetcode.com/problems/unique-middle-element)

[中文文档](/solution/3900-3999/3978.Unique%20Middle%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of odd length <code>n</code>.</p>

<p>Return <code>true</code> if the middle element of <code>nums</code> appears <strong>exactly</strong> once in the array. Otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The middle element of <code>nums</code> is 2, which appears exactly once.</p>

<p>Thus, the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The middle element of <code>nums</code> is 2, which appears twice.</p>

<p>Thus, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>n</code> is odd.</li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We take the element at the middle index of the array and count how many times it appears. If the count is $1$, return $\textit{true}$; otherwise return $\textit{false}$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMiddleElementUnique(self, nums: list[int]) -> bool:
        return nums.count(nums[len(nums) // 2]) == 1
```

#### Java

```java
class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[nums.length / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isMiddleElementUnique(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[n / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
};
```

#### Go

```go
func isMiddleElementUnique(nums []int) bool {
	cnt := 0
	for _, x := range nums {
		if x == nums[len(nums)/2] {
			cnt++
		}
	}
	return cnt == 1
}
```

#### TypeScript

```ts
function isMiddleElementUnique(nums: number[]): boolean {
    let cnt: number = 0;
    for (const x of nums) {
        if (x === nums[nums.length >> 1]) {
            ++cnt;
        }
    }
    return cnt === 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
