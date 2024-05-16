---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README_EN.md
rating: 1423
source: Biweekly Contest 29 Q3
tags:
    - Array
    - Dynamic Programming
    - Sliding Window
---

<!-- problem:start -->

# [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element)

[中文文档](/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README.md)

## Description

<!-- description:start -->

<p>Given a binary array <code>nums</code>, you should delete one element from it.</p>

<p>Return <em>the size of the longest non-empty subarray containing only </em><code>1</code><em>&#39;s in the resulting array</em>. Return <code>0</code> if there is no such subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,1,0,1,1,0,1]
<strong>Output:</strong> 5
<strong>Explanation:</strong> After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1&#39;s is [1,1,1,1,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You must delete one element.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each position $i$ to be deleted, then calculate the number of consecutive 1s on the left and right, and finally take the maximum value.

Specifically, we use two arrays $left$ and $right$ of length $n+1$, where $left[i]$ represents the number of consecutive 1s ending with $nums[i-1]$, and $right[i]$ represents the number of consecutive 1s starting with $nums[i]$.

The final answer is $\max_{0 \leq i < n} \{left[i] + right[i+1]\}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [0] * (n + 1)
        right = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            if x:
                left[i] = left[i - 1] + 1
        for i in range(n - 1, -1, -1):
            if nums[i]:
                right[i] = right[i + 1] + 1
        return max(left[i] + right[i + 1] for i in range(n))
```

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, left[i] + right[i + 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n + 1);
        vector<int> right(n + 1);
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (nums[i]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, left[i] + right[i + 1]);
        }
        return ans;
    }
};
```

```go
func longestSubarray(nums []int) (ans int) {
	n := len(nums)
	left := make([]int, n+1)
	right := make([]int, n+1)
	for i := 1; i <= n; i++ {
		if nums[i-1] == 1 {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] == 1 {
			right[i] = right[i+1] + 1
		}
	}
	for i := 0; i < n; i++ {
		ans = max(ans, left[i]+right[i+1])
	}
	return
}
```

```ts
function longestSubarray(nums: number[]): number {
    const n = nums.length;
    const left: number[] = Array(n + 1).fill(0);
    const right: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        if (nums[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }
    for (let i = n - 1; ~i; --i) {
        if (nums[i]) {
            right[i] = right[i + 1] + 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, left[i] + right[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

The problem is actually asking us to find the longest subarray that contains at most one $0$. The remaining length after deleting one element from this subarray is the answer.

Therefore, we can use two pointers $j$ and $i$ to point to the left and right boundaries of the subarray, initially $j = 0$, $i = 0$. In addition, we use a variable $cnt$ to record the number of $0$s in the subarray.

Next, we move the right pointer $i$. If $nums[i] = 0$, then $cnt$ is incremented by $1$. When $cnt > 1$, we need to move the left pointer $j$ until $cnt \leq 1$. Then, we update the answer, i.e., $ans = \max(ans, i - j)$. Continue to move the right pointer $i$ until $i$ reaches the end of the array.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        ans = 0
        cnt = j = 0
        for i, x in enumerate(nums):
            cnt += x ^ 1
            while cnt > 1:
                cnt -= nums[j] ^ 1
                j += 1
            ans = max(ans, i - j)
        return ans
```

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            cnt += nums[i] ^ 1;
            while (cnt > 1) {
                cnt -= nums[j++] ^ 1;
            }
            ans = Math.max(ans, i - j);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            cnt += nums[i] ^ 1;
            while (cnt > 1) {
                cnt -= nums[j++] ^ 1;
            }
            ans = max(ans, i - j);
        }
        return ans;
    }
};
```

```go
func longestSubarray(nums []int) (ans int) {
	cnt, j := 0, 0
	for i, x := range nums {
		cnt += x ^ 1
		for ; cnt > 1; j++ {
			cnt -= nums[j] ^ 1
		}
		ans = max(ans, i-j)
	}
	return
}
```

```ts
function longestSubarray(nums: number[]): number {
    let [ans, cnt, j] = [0, 0, 0];
    for (let i = 0; i < nums.length; ++i) {
        cnt += nums[i] ^ 1;
        while (cnt > 1) {
            cnt -= nums[j++] ^ 1;
        }
        ans = Math.max(ans, i - j);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
