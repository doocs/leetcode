---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3038.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20I/README_EN.md
rating: 1201
source: Biweekly Contest 124 Q1
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [3038. Maximum Number of Operations With the Same Score I](https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i)

[中文文档](/solution/3000-3099/3038.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code>. Consider the following operation:</p>

<ul>
	<li>Delete the first two elements <code>nums</code> and define the <em>score</em> of the operation as the sum of these two elements.</li>
</ul>

<p>You can perform this operation until <code>nums</code> contains fewer than two elements. Additionally, the <strong>same</strong> <em>score</em> must be achieved in <strong>all</strong> operations.</p>

<p>Return the <strong>maximum</strong> number of operations you can perform.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,1,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We can perform the first operation with the score <code>3 + 2 = 5</code>. After this operation, <code>nums = [1,4,5]</code>.</li>
	<li>We can perform the second operation as its score is <code>4 + 1 = 5</code>, the same as the previous operation. After this operation, <code>nums = [5]</code>.</li>
	<li>As there are fewer than two elements, we can&#39;t perform more operations.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,3,3,4,1,3,2,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We can perform the first operation with the score <code>1 + 5 = 6</code>. After this operation, <code>nums = [3,3,4,1,3,2,2,3]</code>.</li>
	<li>We can perform the second operation as its score is <code>3 + 3 = 6</code>, the same as the previous operation. After this operation, <code>nums = [4,1,3,2,2,3]</code>.</li>
	<li>We cannot perform the next operation as its score is <code>4 + 1 = 5</code>, which is different from the previous scores.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

First, we calculate the sum of the first two elements, denoted as $s$. Then we traverse the array, taking two elements at a time. If their sum is not equal to $s$, we stop the traversal. Finally, we return the number of operations performed.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        s = nums[0] + nums[1]
        ans, n = 0, len(nums)
        for i in range(0, n, 2):
            if i + 1 == n or nums[i] + nums[i + 1] != s:
                break
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxOperations(int[] nums) {
        int s = nums[0] + nums[1];
        int ans = 0, n = nums.length;
        for (int i = 0; i + 1 < n && nums[i] + nums[i + 1] == s; i += 2) {
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxOperations(vector<int>& nums) {
        int s = nums[0] + nums[1];
        int ans = 0, n = nums.size();
        for (int i = 0; i + 1 < n && nums[i] + nums[i + 1] == s; i += 2) {
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func maxOperations(nums []int) (ans int) {
	s, n := nums[0]+nums[1], len(nums)
	for i := 0; i+1 < n && nums[i]+nums[i+1] == s; i += 2 {
		ans++
	}
	return
}
```

#### TypeScript

```ts
function maxOperations(nums: number[]): number {
    const s = nums[0] + nums[1];
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i + 1 < n && nums[i] + nums[i + 1] === s; i += 2) {
        ++ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
