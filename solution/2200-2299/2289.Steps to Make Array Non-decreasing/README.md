# [2289. 使数组按非递减顺序排列](https://leetcode.cn/problems/steps-to-make-array-non-decreasing)

[English Version](/solution/2200-2299/2289.Steps%20to%20Make%20Array%20Non-decreasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。在一步操作中，移除所有满足&nbsp;<code>nums[i - 1] &gt; nums[i]</code> 的 <code>nums[i]</code> ，其中 <code>0 &lt; i &lt; nums.length</code> 。</p>

<p>重复执行步骤，直到 <code>nums</code> 变为 <strong>非递减</strong> 数组，返回所需执行的操作数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,3,4,4,7,3,6,11,8,5,11]
<strong>输出：</strong>3
<strong>解释：</strong>执行下述几个步骤：
- 步骤 1 ：[5,<em><strong>3</strong></em>,4,4,7,<em><strong>3</strong></em>,6,11,<em><strong>8</strong></em>,<em><strong>5</strong></em>,11] 变为 [5,4,4,7,6,11,11]
- 步骤 2 ：[5,<em><strong>4</strong></em>,4,7,<em><strong>6</strong></em>,11,11] 变为 [5,4,7,11,11]
- 步骤 3 ：[5,<em><strong>4</strong></em>,7,11,11] 变为 [5,7,11,11]
[5,7,11,11] 是一个非递减数组，因此，返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,7,7,13]
<strong>输出：</strong>0
<strong>解释：</strong>nums 已经是一个非递减数组，因此，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def totalSteps(self, nums: List[int]) -> int:
        stk = []
        ans, n = 0, len(nums)
        dp = [0] * n
        for i in range(n - 1, -1, -1):
            while stk and nums[i] > nums[stk[-1]]:
                dp[i] = max(dp[i] + 1, dp[stk.pop()])
            stk.append(i)
        return max(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int totalSteps(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        int ans = 0;
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[i] > nums[stk.peek()]) {
                dp[i] = Math.max(dp[i] + 1, dp[stk.pop()]);
                ans = Math.max(ans, dp[i]);
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalSteps(vector<int>& nums) {
        stack<int> stk;
        int ans = 0, n = nums.size();
        vector<int> dp(n);
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && nums[i] > nums[stk.top()]) {
                dp[i] = max(dp[i] + 1, dp[stk.top()]);
                ans = max(ans, dp[i]);
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func totalSteps(nums []int) int {
	stk := []int{}
	ans, n := 0, len(nums)
	dp := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[i] > nums[stk[len(stk)-1]] {
			dp[i] = max(dp[i]+1, dp[stk[len(stk)-1]])
			stk = stk[:len(stk)-1]
			ans = max(ans, dp[i])
		}
		stk = append(stk, i)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function totalSteps(nums: number[]): number {
    let ans = 0;
    let stack = [];
    for (let num of nums) {
        let max = 0;
        while (stack.length && stack[0][0] <= num) {
            max = Math.max(stack[0][1], max);
            stack.shift();
        }
        if (stack.length) max++;
        ans = Math.max(max, ans);
        stack.unshift([num, max]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
