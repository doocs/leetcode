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

# [3038. Maximum Number of Operations With the Same Score I](https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i)

[中文文档](/solution/3000-3099/3038.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20I/README.md)

## Description

<p>Given an array of integers called <code>nums</code>, you can perform the following operation while <code>nums</code> contains <strong>at least</strong> <code>2</code> elements:</p>

<ul>
	<li>Choose the first two elements of <code>nums</code> and delete them.</li>
</ul>

<p>The<strong> score</strong> of the operation is the sum of the deleted elements.</p>

<p>Your task is to find the <strong>maximum</strong> number of operations that can be performed, such that <strong>all operations have the same score</strong>.</p>

<p>Return <em>the <strong>maximum</strong> number of operations possible that satisfy the condition mentioned above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,4,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [1,4,5].
- Delete the first two elements, with score 1 + 4 = 5, nums = [5].
We are unable to perform any more operations as nums contain only 1 element.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,6,1,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
We are unable to perform any more operations as the score of the next operation isn&#39;t the same as the previous one.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Traversal

First, we calculate the sum of the first two elements, denoted as $s$. Then we traverse the array, taking two elements at a time. If their sum is not equal to $s$, we stop the traversal. Finally, we return the number of operations performed.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

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

```go
func maxOperations(nums []int) (ans int) {
	s, n := nums[0]+nums[1], len(nums)
	for i := 0; i+1 < n && nums[i]+nums[i+1] == s; i += 2 {
		ans++
	}
	return
}
```

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

<!-- end -->
