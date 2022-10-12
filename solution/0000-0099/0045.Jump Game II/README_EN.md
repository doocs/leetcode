# [45. Jump Game II](https://leetcode.com/problems/jump-game-ii)

[中文文档](/solution/0000-0099/0045.Jump%20Game%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code> of length <code>n</code>. You are initially positioned at <code>nums[0]</code>.</p>

<p>Each element <code>nums[i]</code> represents the maximum length of a forward jump from index <code>i</code>. In other words, if you are at <code>nums[i]</code>, you can jump to any <code>nums[i + j]</code> where:</p>

<ul>
	<li><code>0 &lt;= j &lt;= nums[i]</code> and</li>
	<li><code>i + j &lt; n</code></li>
</ul>

<p>Return <em>the minimum number of jumps to reach </em><code>nums[n - 1]</code>. The test cases are generated such that you can reach <code>nums[n - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,0,1,4]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        end = mx = steps = 0
        for i, num in enumerate(nums[:-1]):
            mx = max(mx, i + num)
            if i == end:
                end = mx
                steps += 1
        return steps
```

### **Java**

```java
class Solution {
    public int jump(int[] nums) {
        int end = 0;
        int mx = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            mx = Math.max(mx, i + nums[i]);
            if (i == end) {
                end = mx;
                ++steps;
            }
        }
        return steps;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int jump(vector<int>& nums) {
        int mx = 0, steps = 0, end = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            mx = max(mx, i + nums[i]);
            if (i == end) {
                end = mx;
                ++steps;
            }
        }
        return steps;
    }
};
```

### **Go**

```go
func jump(nums []int) int {
	mx, steps, end := 0, 0, 0
	for i := 0; i < len(nums)-1; i++ {
		mx = max(mx, i+nums[i])
		if i == end {
			end = mx
			steps++
		}
	}
	return steps
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int Jump(int[] nums) {
        int end = 0;
        int mx = 0;
        int steps = 0;
        for (int i = 0; i < nums.Length - 1; ++i)
        {
            mx = Math.Max(mx, i + nums[i]);
            if (i == end)
            {
                end = mx;
                ++steps;
            }
        }
        return steps;
    }
}
```

### **C**

```c
#define min(a, b) a < b ? a : b
int jump(int* nums, int numsSize) {
    int dp[numsSize];
    for (int i = 0; i < numsSize; i++) {
        dp[i] = numsSize;
    }
    dp[0] = 0;
    for (int i = 0; i < numsSize - 1; i++) {
        for (int j = i + 1; j < (min(i + nums[i] + 1, numsSize)); j++) {
            dp[j] = min(dp[j], dp[i] + 1);
        }
    }
    return dp[numsSize - 1];
}
```

### **Rust**

```rust
impl Solution {
    pub fn jump(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut dp = vec![i32::MAX; n];
        dp[0] = 0;
        for i in 0..n - 1 {
            for j in 1..=nums[i] as usize {
                if i + j >= n {
                    break;
                }
                dp[i + j] = dp[i + j].min(dp[i] + 1);
            }
        }
        dp[n - 1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
