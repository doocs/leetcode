# [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii)

[English Version](/solution/0000-0099/0045.Jump%20Game%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的 <strong>0 索引</strong>整数数组 <code>nums</code>。初始位置为 <code>nums[0]</code>。</p>

<p>每个元素 <code>nums[i]</code> 表示从索引 <code>i</code> 向前跳转的最大长度。换句话说，如果你在 <code>nums[i]</code> 处，你可以跳转到任意 <code>nums[i + j]</code> 处:</p>

<ul>
	<li><code>0 &lt;= j &lt;= nums[i]</code>&nbsp;</li>
	<li><code>i + j &lt; n</code></li>
</ul>

<p>返回到达&nbsp;<code>nums[n - 1]</code> 的最小跳跃次数。生成的测试用例可以到达 <code>nums[n - 1]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,1,1,4]
<strong>输出:</strong> 2
<strong>解释:</strong> 跳到最后一个位置的最小跳跃数是 <code>2</code>。
&nbsp;    从下标为 0 跳到下标为 1 的位置，跳&nbsp;<code>1</code>&nbsp;步，然后跳&nbsp;<code>3</code>&nbsp;步到达数组的最后一个位置。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,0,1,4]
<strong>输出:</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li>题目保证可以到达&nbsp;<code>nums[n-1]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

**方法二：贪心**

我们可以用变量 `mx` 记录当前位置能够到达的最远位置，用变量 `end` 记录上一次跳跃的位置，用变量 `steps` 记录跳跃的次数。

接下来，我们从 $0$ 开始枚举所有位置，用 $i+nums[i]$ 来更新 `mx`，当 $i=end$ 时，我们就需要进行一次跳跃，此时我们将 `end` 更新为 `mx`，并将 `steps` 加 $1$。

遍历结束，返回 `steps` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

相似题目：

-   [55. 跳跃游戏](/solution/0000-0099/0055.Jump%20Game/README.md)
-   [1024. 视频拼接](/solution/1000-1099/1024.Video%20Stitching/README.md)
-   [1326. 灌溉花园的最少水龙头数目](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

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
