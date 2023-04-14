# [1749. 任意子数组和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray)

[English Version](/solution/1700-1799/1749.Maximum%20Absolute%20Sum%20of%20Any%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。一个子数组 <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> 的 <strong>和的绝对值</strong> 为 <code>abs(nums<sub>l</sub> + nums<sub>l+1</sub> + ... + nums<sub>r-1</sub> + nums<sub>r</sub>)</code> 。</p>

<p>请你找出 <code>nums</code> 中 <strong>和的绝对值</strong> 最大的任意子数组（<b>可能为空</b>），并返回该 <strong>最大值</strong> 。</p>

<p><code>abs(x)</code> 定义如下：</p>

<ul>
	<li>如果 <code>x</code> 是负整数，那么 <code>abs(x) = -x</code> 。</li>
	<li>如果 <code>x</code> 是非负整数，那么 <code>abs(x) = x</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,-3,2,3,-4]
<b>输出：</b>5
<b>解释：</b>子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,-5,1,-4,3,-2]
<b>输出：</b>8
<b>解释：</b>子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示以 $nums[i]$ 结尾的子数组的和的最大值，定义 $g[i]$ 表示以 $nums[i]$ 结尾的子数组的和的最小值。那么 $f[i]$ 和 $g[i]$ 的状态转移方程如下：

$$
\begin{aligned}
f[i] &= \max(f[i - 1], 0) + nums[i] \\
g[i] &= \min(g[i - 1], 0) + nums[i]
\end{aligned}
$$

最后答案为 $max(f[i], |g[i]|)$ 的最大值。

由于 $f[i]$ 和 $g[i]$ 只与 $f[i - 1]$ 和 $g[i - 1]$ 有关，因此我们可以使用两个变量代替数组，将空间复杂度降低到 $O(1)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxAbsoluteSum(self, nums: List[int]) -> int:
        f = g = 0
        ans = 0
        for x in nums:
            f = max(f, 0) + x
            g = min(g, 0) + x
            ans = max(ans, f, abs(g))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int f = 0, g = 0;
        int ans = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + x;
            g = Math.min(g, 0) + x;
            ans = Math.max(ans, Math.max(f, Math.abs(g)));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxAbsoluteSum(vector<int>& nums) {
        int f = 0, g = 0;
        int ans = 0;
        for (int& x : nums) {
            f = max(f, 0) + x;
            g = min(g, 0) + x;
            ans = max({ans, f, abs(g)});
        }
        return ans;
    }
};
```

### **Go**

```go
func maxAbsoluteSum(nums []int) (ans int) {
	var f, g int
	for _, x := range nums {
		f = max(f, 0) + x
		g = min(g, 0) + x
		ans = max(ans, max(f, abs(g)))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxAbsoluteSum(nums: number[]): number {
    let f = 0;
    let g = 0;
    let ans = 0;
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        g = Math.min(g, 0) + x;
        ans = Math.max(ans, f, -g);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
