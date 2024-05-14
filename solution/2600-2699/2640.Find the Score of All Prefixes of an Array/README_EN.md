---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2640.Find%20the%20Score%20of%20All%20Prefixes%20of%20an%20Array/README_EN.md
rating: 1314
tags:
    - Array
    - Prefix Sum
---

# [2640. Find the Score of All Prefixes of an Array](https://leetcode.com/problems/find-the-score-of-all-prefixes-of-an-array)

[中文文档](/solution/2600-2699/2640.Find%20the%20Score%20of%20All%20Prefixes%20of%20an%20Array/README.md)

## Description

<p>We define the <strong>conversion array</strong> <code>conver</code> of an array <code>arr</code> as follows:</p>

<ul>
	<li><code>conver[i] = arr[i] + max(arr[0..i])</code> where <code>max(arr[0..i])</code> is the maximum value of <code>arr[j]</code> over <code>0 &lt;= j &lt;= i</code>.</li>
</ul>

<p>We also define the <strong>score</strong> of an array <code>arr</code> as the sum of the values of the conversion array of <code>arr</code>.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>, return <em>an array </em><code>ans</code><em> of length </em><code>n</code><em> where </em><code>ans[i]</code><em> is the score of the prefix</em> <code>nums[0..i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,7,5,10]
<strong>Output:</strong> [4,10,24,36,56]
<strong>Explanation:</strong> 
For the prefix [2], the conversion array is [4] hence the score is 4
For the prefix [2, 3], the conversion array is [4, 6] hence the score is 10
For the prefix [2, 3, 7], the conversion array is [4, 6, 14] hence the score is 24
For the prefix [2, 3, 7, 5], the conversion array is [4, 6, 14, 12] hence the score is 36
For the prefix [2, 3, 7, 5, 10], the conversion array is [4, 6, 14, 12, 20] hence the score is 56
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,4,8,16]
<strong>Output:</strong> [2,4,8,16,32,64]
<strong>Explanation:</strong> 
For the prefix [1], the conversion array is [2] hence the score is 2
For the prefix [1, 1], the conversion array is [2, 2] hence the score is 4
For the prefix [1, 1, 2], the conversion array is [2, 2, 4] hence the score is 8
For the prefix [1, 1, 2, 4], the conversion array is [2, 2, 4, 8] hence the score is 16
For the prefix [1, 1, 2, 4, 8], the conversion array is [2, 2, 4, 8, 16] hence the score is 32
For the prefix [1, 1, 2, 4, 8, 16], the conversion array is [2, 2, 4, 8, 16, 32] hence the score is 64
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum

We use a variable $mx$ to record the maximum value of the first $i$ elements in the array $nums$, and use an array $ans[i]$ to record the score of the first $i$ elements in the array $nums$.

Next, we traverse the array $nums$. For each element $nums[i]$, we update $mx$, i.e., $mx = \max(mx, nums[i])$, and then update $ans[i]$. If $i = 0$, then $ans[i] = nums[i] + mx$, otherwise $ans[i] = nums[i] + mx + ans[i - 1]$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findPrefixScore(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        mx = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            ans[i] = x + mx + (0 if i == 0 else ans[i - 1])
        return ans
```

```java
class Solution {
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, nums[i]);
            ans[i] = nums[i] + mx + (i == 0 ? 0 : ans[i - 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> findPrefixScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, nums[i]);
            ans[i] = nums[i] + mx + (i == 0 ? 0 : ans[i - 1]);
        }
        return ans;
    }
};
```

```go
func findPrefixScore(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	mx := 0
	for i, x := range nums {
		mx = max(mx, x)
		ans[i] = int64(x + mx)
		if i > 0 {
			ans[i] += ans[i-1]
		}
	}
	return ans
}
```

```ts
function findPrefixScore(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(n);
    let mx: number = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, nums[i]);
        ans[i] = nums[i] + mx + (i === 0 ? 0 : ans[i - 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
