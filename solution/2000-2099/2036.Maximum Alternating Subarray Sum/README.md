# [2036. 最大交替子数组和](https://leetcode.cn/problems/maximum-alternating-subarray-sum)

[English Version](/solution/2000-2099/2036.Maximum%20Alternating%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>子数组</strong>是以<strong>0</strong>下标开始的数组的连续非空子序列，从 <code>i</code> 到 <code>j</code>（<code>0 &lt;= i &lt;= j &lt; nums.length</code>）的 <strong>子数组交替和</strong> 被定义为 <code>nums[i] - nums[i+1] + nums[i+2] - ... +/- nums[j]</code> 。</p>

<p>给定一个以<strong>0</strong>下标开始的整数数组<code>nums</code>，返回它所有可能的交替子数组和的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-1,1,2]
<strong>输出：</strong>5
<strong>解释：</strong>
子数组 [3,-1,1]有最大的交替子数组和3 - (-1) + 1 = 5.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
子数组 [2], [2,2,2]和 [2,2,2,2,2]有相同的最大交替子数组和为2
[2]: 2.
[2,2,2]: 2 - 2 + 2 = 2.
[2,2,2,2,2]: 2 - 2 + 2 - 2 + 2 = 2.
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
<strong>解释：</strong>
仅有一个非空子数组，为 [1]，它的交替子数组和为 1
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义状态 $a$ 表示以当前元素作为正结尾的最大交替子数组和，状态 $b$ 表示以当前元素作为负结尾的最大交替子数组和。初始时 $a = nums[0]$，$b = -\infty$。

遍历数组，对于当前元素 $nums[i]$，有

$$
\begin{aligned}
a = \max(nums[i], b + nums[i]) \\
b = a - nums[i]
\end{aligned}
$$

求出 $a$ 和 $b$ 后，将 $a$ 和 $b$ 中的最大值与当前最大交替子数组和进行比较，更新最大交替子数组和。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumAlternatingSubarraySum(self, nums: List[int]) -> int:
        ans = nums[0]
        a, b = nums[0], -inf
        for v in nums[1:]:
            a, b = max(v, b + v), a - v
            ans = max(ans, a, b)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumAlternatingSubarraySum(int[] nums) {
        long ans = nums[0];
        long a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.length; ++i) {
            long c = a, d = b;
            a = Math.max(nums[i], d + nums[i]);
            b = c - nums[i];
            ans = Math.max(ans, Math.max(a, b));
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long maximumAlternatingSubarraySum(vector<int>& nums) {
        ll ans = nums[0];
        ll a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.size(); ++i) {
            ll c = a, d = b;
            a = max(1ll * nums[i], d + nums[i]);
            b = c - nums[i];
            ans = max(ans, max(a, b));
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumAlternatingSubarraySum(nums []int) int64 {
	ans := nums[0]
	a, b := nums[0], -(1 << 30)
	for _, v := range nums[1:] {
		c, d := a, b
		a = max(v, d+v)
		b = c - v
		ans = max(ans, max(a, b))
	}
	return int64(ans)
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
