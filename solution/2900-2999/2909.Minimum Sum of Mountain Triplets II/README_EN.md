# [2909. Minimum Sum of Mountain Triplets II](https://leetcode.com/problems/minimum-sum-of-mountain-triplets-ii)

[中文文档](/solution/2900-2999/2909.Minimum%20Sum%20of%20Mountain%20Triplets%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of integers.</p>

<p>A triplet of indices <code>(i, j, k)</code> is a <strong>mountain</strong> if:</p>

<ul>
	<li><code>i &lt; j &lt; k</code></li>
	<li><code>nums[i] &lt; nums[j]</code> and <code>nums[k] &lt; nums[j]</code></li>
</ul>

<p>Return <em>the <strong>minimum possible sum</strong> of a mountain triplet of</em> <code>nums</code>. <em>If no such triplet exists, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,6,1,5,3]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Triplet (2, 3, 4) is a mountain triplet of sum 9 since: 
- 2 &lt; 3 &lt; 4
- nums[2] &lt; nums[3] and nums[4] &lt; nums[3]
And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9. It can be shown that there are no mountain triplets with a sum of less than 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,8,7,10,2]
<strong>Output:</strong> 13
<strong>Explanation:</strong> Triplet (1, 3, 5) is a mountain triplet of sum 13 since: 
- 1 &lt; 3 &lt; 5
- nums[1] &lt; nums[3] and nums[5] &lt; nums[3]
And the sum of this triplet is nums[1] + nums[3] + nums[5] = 13. It can be shown that there are no mountain triplets with a sum of less than 13.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,3,4,5]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that there are no mountain triplets in nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

**Solution 1: Preprocessing + Enumeration**

We can preprocess the minimum value on the right side of each position and record it in the array $right[i]$, where $right[i]$ represents the minimum value in $nums[i+1..n-1]$.

Next, we enumerate the middle element $nums[i]$ of the mountain triplet from left to right, and use a variable $left$ to represent the minimum value in $ums[0..i-1]$, and a variable $ans$ to represent the current minimum element sum found. For each $i$, we need to find the element $nums[i]$ that satisfies $left < nums[i]$ and $right[i+1] < nums[i]$, and update $ans$.

Finally, if $ans$ is still the initial value, it means that there is no mountain triplet, and we return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumSum(self, nums: List[int]) -> int:
        n = len(nums)
        right = [inf] * (n + 1)
        for i in range(n - 1, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        ans = left = inf
        for i, x in enumerate(nums):
            if left < x and right[i + 1] < x:
                ans = min(ans, left + x + right[i + 1])
            left = min(left, x)
        return -1 if ans == inf else ans
```

### **Java**

```java
class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] right = new int[n + 1];
        final int inf = 1 << 30;
        right[n] = inf;
        for (int i = n - 1; i >= 0; --i) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }
        int ans = inf, left = inf;
        for (int i = 0; i < n; ++i) {
            if (left < nums[i] && right[i + 1] < nums[i]) {
                ans = Math.min(ans, left + nums[i] + right[i + 1]);
            }
            left = Math.min(left, nums[i]);
        }
        return ans == inf ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSum(vector<int>& nums) {
        int n = nums.size();
        const int inf = 1 << 30;
        int right[n + 1];
        right[n] = inf;
        for (int i = n - 1; ~i; --i) {
            right[i] = min(right[i + 1], nums[i]);
        }
        int ans = inf, left = inf;
        for (int i = 0; i < n; ++i) {
            if (left < nums[i] && right[i + 1] < nums[i]) {
                ans = min(ans, left + nums[i] + right[i + 1]);
            }
            left = min(left, nums[i]);
        }
        return ans == inf ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumSum(nums []int) int {
	n := len(nums)
	const inf = 1 << 30
	right := make([]int, n+1)
	right[n] = inf
	for i := n - 1; i >= 0; i-- {
		right[i] = min(right[i+1], nums[i])
	}
	ans, left := inf, inf
	for i, x := range nums {
		if left < x && right[i+1] < x {
			ans = min(ans, left+x+right[i+1])
		}
		left = min(left, x)
	}
	if ans == inf {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumSum(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n + 1).fill(Infinity);
    for (let i = n - 1; ~i; --i) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }
    let [ans, left] = [Infinity, Infinity];
    for (let i = 0; i < n; ++i) {
        if (left < nums[i] && right[i + 1] < nums[i]) {
            ans = Math.min(ans, left + nums[i] + right[i + 1]);
        }
        left = Math.min(left, nums[i]);
    }
    return ans === Infinity ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
