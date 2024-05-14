# [2765. Longest Alternating Subarray](https://leetcode.com/problems/longest-alternating-subarray)

[中文文档](/solution/2700-2799/2765.Longest%20Alternating%20Subarray/README.md)

<!-- tags:Array,Enumeration -->

<!-- difficulty:Easy -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A subarray <code>s</code> of length <code>m</code> is called <strong>alternating</strong> if:</p>

<ul>
	<li><code>m</code> is greater than <code>1</code>.</li>
	<li><code>s<sub>1</sub> = s<sub>0</sub> + 1</code>.</li>
	<li>The <strong>0-indexed</strong> subarray <code>s</code> looks like <code>[s<sub>0</sub>, s<sub>1</sub>, s<sub>0</sub>, s<sub>1</sub>,...,s<sub>(m-1) % 2</sub>]</code>. In other words, <code>s<sub>1</sub> - s<sub>0</sub> = 1</code>, <code>s<sub>2</sub> - s<sub>1</sub> = -1</code>, <code>s<sub>3</sub> - s<sub>2</sub> = 1</code>, <code>s<sub>4</sub> - s<sub>3</sub> = -1</code>, and so on up to <code>s[m - 1] - s[m - 2] = (-1)<sup>m</sup></code>.</li>
</ul>

<p>Return <em>the maximum length of all <strong>alternating</strong> subarrays present in </em><code>nums</code> <em>or </em><code>-1</code><em> if no such subarray exists</em><em>.</em></p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4,3,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The alternating subarrays are [3,4], [3,4,3], and [3,4,3,4]. The longest of these is [3,4,3,4], which is of length 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,6]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [4,5] and [5,6] are the only two alternating subarrays. They are both of length 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Enumeration

We can enumerate the left endpoint $i$ of the subarray, and for each $i$, we need to find the longest subarray that satisfies the condition. We can start traversing to the right from $i$, and each time we encounter adjacent elements whose difference does not satisfy the alternating condition, we find a subarray that satisfies the condition. We can use a variable $k$ to record whether the difference of the current element should be $1$ or $-1$. If the difference of the current element should be $-k$, then we take the opposite of $k$. When we find a subarray $nums[i..j]$ that satisfies the condition, we update the answer to $\max(ans, j - i + 1)$.

The time complexity is $O(n^2)$, where $n$ is the length of the array. We need to enumerate the left endpoint $i$ of the subarray, and for each $i$, we need $O(n)$ time to find the longest subarray that satisfies the condition. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def alternatingSubarray(self, nums: List[int]) -> int:
        ans, n = -1, len(nums)
        for i in range(n):
            k = 1
            j = i
            while j + 1 < n and nums[j + 1] - nums[j] == k:
                j += 1
                k *= -1
            if j - i + 1 > 1:
                ans = max(ans, j - i + 1)
        return ans
```

```java
class Solution {
    public int alternatingSubarray(int[] nums) {
        int ans = -1, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int k = 1;
            int j = i;
            for (; j + 1 < n && nums[j + 1] - nums[j] == k; ++j) {
                k *= -1;
            }
            if (j - i + 1 > 1) {
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int alternatingSubarray(vector<int>& nums) {
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int k = 1;
            int j = i;
            for (; j + 1 < n && nums[j + 1] - nums[j] == k; ++j) {
                k *= -1;
            }
            if (j - i + 1 > 1) {
                ans = max(ans, j - i + 1);
            }
        }
        return ans;
    }
};
```

```go
func alternatingSubarray(nums []int) int {
	ans, n := -1, len(nums)
	for i := range nums {
		k := 1
		j := i
		for ; j+1 < n && nums[j+1]-nums[j] == k; j++ {
			k *= -1
		}
		if t := j - i + 1; t > 1 && ans < t {
			ans = t
		}
	}
	return ans
}
```

```ts
function alternatingSubarray(nums: number[]): number {
    let ans = -1;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let k = 1;
        let j = i;
        for (; j + 1 < n && nums[j + 1] - nums[j] === k; ++j) {
            k *= -1;
        }
        if (j - i + 1 > 1) {
            ans = Math.max(ans, j - i + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
