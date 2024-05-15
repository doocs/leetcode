---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1144.Decrease%20Elements%20To%20Make%20Array%20Zigzag/README_EN.md
rating: 1558
tags:
    - Greedy
    - Array
---

# [1144. Decrease Elements To Make Array Zigzag](https://leetcode.com/problems/decrease-elements-to-make-array-zigzag)

[中文文档](/solution/1100-1199/1144.Decrease%20Elements%20To%20Make%20Array%20Zigzag/README.md)

## Description

<p>Given an array <code>nums</code> of integers, a <em>move</em>&nbsp;consists of choosing any element and <strong>decreasing it by 1</strong>.</p>

<p>An array <code>A</code> is a&nbsp;<em>zigzag array</em>&nbsp;if either:</p>

<ul>
	<li>Every even-indexed element is greater than adjacent elements, ie.&nbsp;<code>A[0] &gt; A[1] &lt; A[2] &gt; A[3] &lt; A[4] &gt; ...</code></li>
	<li>OR, every odd-indexed element is greater than adjacent elements, ie.&nbsp;<code>A[0] &lt; A[1] &gt; A[2] &lt; A[3] &gt; A[4] &lt; ...</code></li>
</ul>

<p>Return the minimum number of moves to transform the given array <code>nums</code> into a zigzag array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can decrease 2 to 0 or 3 to 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,6,1,6,2]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Enumeration + Greedy

We can separately enumerate the even and odd positions as the elements "smaller than adjacent elements", and then calculate the required number of operations. The minimum of the two is taken.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def movesToMakeZigzag(self, nums: List[int]) -> int:
        ans = [0, 0]
        n = len(nums)
        for i in range(2):
            for j in range(i, n, 2):
                d = 0
                if j:
                    d = max(d, nums[j] - nums[j - 1] + 1)
                if j < n - 1:
                    d = max(d, nums[j] - nums[j + 1] + 1)
                ans[i] += d
        return min(ans)
```

```java
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int[] ans = new int[2];
        int n = nums.length;
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j > 0) {
                    d = Math.max(d, nums[j] - nums[j - 1] + 1);
                }
                if (j < n - 1) {
                    d = Math.max(d, nums[j] - nums[j + 1] + 1);
                }
                ans[i] += d;
            }
        }
        return Math.min(ans[0], ans[1]);
    }
}
```

```cpp
class Solution {
public:
    int movesToMakeZigzag(vector<int>& nums) {
        vector<int> ans(2);
        int n = nums.size();
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j) d = max(d, nums[j] - nums[j - 1] + 1);
                if (j < n - 1) d = max(d, nums[j] - nums[j + 1] + 1);
                ans[i] += d;
            }
        }
        return min(ans[0], ans[1]);
    }
};
```

```go
func movesToMakeZigzag(nums []int) int {
	ans := [2]int{}
	n := len(nums)
	for i := 0; i < 2; i++ {
		for j := i; j < n; j += 2 {
			d := 0
			if j > 0 {
				d = max(d, nums[j]-nums[j-1]+1)
			}
			if j < n-1 {
				d = max(d, nums[j]-nums[j+1]+1)
			}
			ans[i] += d
		}
	}
	return min(ans[0], ans[1])
}
```

```ts
function movesToMakeZigzag(nums: number[]): number {
    const ans: number[] = Array(2).fill(0);
    const n = nums.length;
    for (let i = 0; i < 2; ++i) {
        for (let j = i; j < n; j += 2) {
            let d = 0;
            if (j > 0) {
                d = Math.max(d, nums[j] - nums[j - 1] + 1);
            }
            if (j < n - 1) {
                d = Math.max(d, nums[j] - nums[j + 1] + 1);
            }
            ans[i] += d;
        }
    }
    return Math.min(...ans);
}
```

```cs
public class Solution {
    public int MovesToMakeZigzag(int[] nums) {
        int[] ans = new int[2];
        int n = nums.Length;
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j > 0) {
                    d = Math.Max(d, nums[j] - nums[j - 1] + 1);
                }
                if (j < n - 1) {
                    d = Math.Max(d, nums[j] - nums[j + 1] + 1);
                }
                ans[i] += d;
            }
        }
        return Math.Min(ans[0], ans[1]);
    }
}
```

<!-- tabs:end -->

<!-- end -->
