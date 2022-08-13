# [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii)

[English Version](/solution/0000-0099/0045.Jump%20Game%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数数组 <code>nums</code> ，你最初位于数组的第一个位置。</p>

<p>数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>

<p>你的目标是使用最少的跳跃次数到达数组的最后一个位置。</p>

<p>假设你总是可以到达数组的最后一个位置。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,1,1,4]
<strong>输出:</strong> 2
<strong>解释:</strong> 跳到最后一个位置的最小跳跃数是 <code>2</code>。
     从下标为 0 跳到下标为 1 的位置，跳 <code>1</code> 步，然后跳 <code>3</code> 步到达数组的最后一个位置。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,0,1,4]
<strong>输出:</strong> 2
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>0 <= nums[i] <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

**方法二：贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
