---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2270.Number%20of%20Ways%20to%20Split%20Array/README_EN.md
rating: 1334
source: Biweekly Contest 78 Q2
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [2270. Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array)

[中文文档](/solution/2200-2299/2270.Number%20of%20Ways%20to%20Split%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p><code>nums</code> contains a <strong>valid split</strong> at index <code>i</code> if the following are true:</p>

<ul>
	<li>The sum of the first <code>i + 1</code> elements is <strong>greater than or equal to</strong> the sum of the last <code>n - i - 1</code> elements.</li>
	<li>There is <strong>at least one</strong> element to the right of <code>i</code>. That is, <code>0 &lt;= i &lt; n - 1</code>.</li>
</ul>

<p>Return <em>the number of <strong>valid splits</strong> in</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,4,-8,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
There are three ways of splitting nums into two non-empty parts:
- Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 &gt;= 3, i = 0 is a valid split.
- Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 &gt;= -1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 &lt; 7, i = 2 is not a valid split.
Thus, the number of valid splits in nums is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
There are two valid splits in nums:
- Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and its sum is 1. Since 5 &gt;= 1, i = 1 is a valid split. 
- Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and its sum is 0. Since 6 &gt;= 0, i = 2 is a valid split.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

First, we calculate the total sum $s$ of the array $\textit{nums}$. Then, we traverse the first $n-1$ elements of the array $\textit{nums}$, using the variable $t$ to record the prefix sum. If $t \geq s - t$, we increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        s = sum(nums)
        ans = t = 0
        for x in nums[:-1]:
            t += x
            ans += t >= s - t
        return ans
```

#### Java

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        long s = 0;
        for (int x : nums) {
            s += x;
        }
        long t = 0;
        int ans = 0;
        for (int i = 0; i + 1 < nums.length; ++i) {
            t += nums[i];
            ans += t >= s - t ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToSplitArray(vector<int>& nums) {
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        long long t = 0;
        int ans = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            t += nums[i];
            ans += t >= s - t;
        }
        return ans;
    }
};
```

#### Go

```go
func waysToSplitArray(nums []int) (ans int) {
	var s, t int
	for _, x := range nums {
		s += x
	}
	for _, x := range nums[:len(nums)-1] {
		t += x
		if t >= s-t {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function waysToSplitArray(nums: number[]): number {
    const s = nums.reduce((acc, cur) => acc + cur, 0);
    let [ans, t] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        t += x;
        if (t >= s - t) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
