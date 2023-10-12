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

我们定义 $f$ 表示以 $nums[i]$ 结尾的交替子数组的最大和，定义 $g$ 表示以 $-nums[i]$ 结尾的交替子数组的最大和，初始时 $f$ 和 $g$ 均为 $-\infty$。

接下来，我们遍历数组 $nums$，对于位置 $i$，我们需要维护 $f$ 和 $g$ 的值，即 $f = \max(g, 0) + nums[i]$，而 $g = f - nums[i]$。答案即为所有 $f$ 和 $g$ 中的最大值。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumAlternatingSubarraySum(self, nums: List[int]) -> int:
        ans = f = g = -inf
        for x in nums:
            f, g = max(g, 0) + x, f - x
            ans = max(ans, f, g)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumAlternatingSubarraySum(int[] nums) {
        final long inf = 1L << 60;
        long ans = -inf, f = -inf, g = -inf;
        for (int x : nums) {
            long ff = Math.max(g, 0) + x;
            g = f - x;
            f = ff;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumAlternatingSubarraySum(vector<int>& nums) {
        using ll = long long;
        const ll inf = 1LL << 60;
        ll ans = -inf, f = -inf, g = -inf;
        for (int x : nums) {
            ll ff = max(g, 0LL) + x;
            g = f - x;
            f = ff;
            ans = max({ans, f, g});
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumAlternatingSubarraySum(nums []int) int64 {
	const inf = 1 << 60
	ans, f, g := -inf, -inf, -inf
	for _, x := range nums {
		f, g = max(g, 0)+x, f-x
		ans = max(ans, max(f, g))
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

### **TypeScript**

```ts
function maximumAlternatingSubarraySum(nums: number[]): number {
    let [ans, f, g] = [-Infinity, -Infinity, -Infinity];
    for (const x of nums) {
        [f, g] = [Math.max(g, 0) + x, f - x];
        ans = Math.max(ans, f, g);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
