# [962. 最大宽度坡](https://leetcode.cn/problems/maximum-width-ramp)

[English Version](/solution/0900-0999/0962.Maximum%20Width%20Ramp/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>A</code>，<em>坡</em>是元组&nbsp;<code>(i, j)</code>，其中&nbsp;&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>A[i] &lt;= A[j]</code>。这样的坡的宽度为&nbsp;<code>j - i</code>。</p>

<p>找出&nbsp;<code>A</code>&nbsp;中的坡的最大宽度，如果不存在，返回 0 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[6,0,8,2,1,5]
<strong>输出：</strong>4
<strong>解释：</strong>
最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[9,8,1,0,1,9,4,0,4,1]
<strong>输出：</strong>7
<strong>解释：</strong>
最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>2 &lt;= A.length &lt;= 50000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 50000</code></li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

根据题意，我们可以发现，所有可能的 $nums[i]$ 所构成的子序列一定是单调递减的。为什么呢？我们不妨用反证法证明一下。

假设存在 $i_1<i_2$，并且 $nums[i_1]<=nums[i_2]$，那么实际上 $nums[i_2]$一定不可能是一个候选值，因为 $nums[i_1]$ 更靠左，会是一个更优的值。因此 $nums[i]$ 所构成的子序列一定单调递减，并且 $i$ 一定是从 0 开始。

我们用一个从栈底到栈顶单调递减的栈 $stk$ 来存储所有可能的 $nums[i]$，然后我们从右边界开始遍历 $j$，若遇到 $nums[stk.top()]<=nums[j]$，说明此时构成一个坡，循环弹出栈顶元素，更新 ans。

时间复杂度 $O(n)$，其中 $n$ 表示 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxWidthRamp(self, nums: List[int]) -> int:
        stk = []
        for i, v in enumerate(nums):
            if not stk or nums[stk[-1]] > v:
                stk.append(i)
        ans = 0
        for i in range(len(nums) - 1, -1, -1):
            while stk and nums[stk[-1]] <= nums[i]:
                ans = max(ans, i - stk.pop())
            if not stk:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (stk.isEmpty() || nums[stk.peek()] > nums[i]) {
                stk.push(i);
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                ans = Math.max(ans, i - stk.pop());
            }
            if (stk.isEmpty()) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxWidthRamp(vector<int>& nums) {
        int n = nums.size();
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            if (stk.empty() || nums[stk.top()] > nums[i]) stk.push(i);
        }
        int ans = 0;
        for (int i = n - 1; i; --i) {
            while (!stk.empty() && nums[stk.top()] <= nums[i]) {
                ans = max(ans, i - stk.top());
                stk.pop();
            }
            if (stk.empty()) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxWidthRamp(nums []int) int {
	n := len(nums)
	stk := []int{}
	for i, v := range nums {
		if len(stk) == 0 || nums[stk[len(stk)-1]] > v {
			stk = append(stk, i)
		}
	}
	ans := 0
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= nums[i] {
			ans = max(ans, i-stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
		if len(stk) == 0 {
			break
		}
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

### **...**

```

```

<!-- tabs:end -->
