# [891. 子序列宽度之和](https://leetcode.cn/problems/sum-of-subsequence-widths)

[English Version](/solution/0800-0899/0891.Sum%20of%20Subsequence%20Widths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个序列的 <strong>宽度</strong> 定义为该序列中最大元素和最小元素的差值。</p>

<p>给你一个整数数组 <code>nums</code> ，返回 <code>nums</code> 的所有非空 <strong>子序列</strong> 的 <strong>宽度之和</strong> 。由于答案可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的结果。</p>

<p><strong>子序列</strong> 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，<code>[3,6,2,7]</code> 就是数组 <code>[0,3,1,6,2,2,7]</code> 的一个子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>6
<strong>解释：</strong>子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
宽度之和是 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 枚举元素计算贡献**

题目求解的是数组 `nums` 中所有子序列中最大值与最小值差值之和，注意到“子序列”，并且涉及到“最大值”与“最小值”，我们考虑先对数组 `nums` 进行排序。

然后我们枚举数组 `nums` 中的每个元素 $nums[i]$，该元素左侧的元素个数为 $i$，右侧的元素个数为 $n-i-1$。

如果我们将元素 $nums[i]$ 作为子序列的最大值，总共有多少个满足条件的子序列呢？显然，子序列的其他元素应该从左侧的 $i$ 个元素中选取，每个元素有两种选择，即选或不选，因此总共有 $2^i$ 个子序列。同理，如果我们将元素 $nums[i]$ 作为子序列的最小值，那么总共有 $2^{n-i-1}$ 个满足条件的子序列。因此 $nums[i]$ 对答案的贡献为：

$$
\begin{aligned}
nums[i] \times (2^i - 2^{n-i-1})
\end{aligned}
$$

我们将数组 `nums` 中所有元素的贡献累加，即为答案：

$$
\begin{aligned}
\sum_{i=0}^{n-1} nums[i] \times (2^i - 2^{n-i-1})
\end{aligned}
$$

我们将上式展开，可以得到：

$$
\begin{aligned}
nums[0] \times (2^0 - 2^{n-1}) + nums[1] \times (2^1 - 2^{n-2}) + ... + nums[n-1] \times (2^{n-1} - 2^0)
\end{aligned}
$$

再将式子中相同的幂次项合并，可以得到：

$$
\begin{aligned}
(nums[0] - nums[n-1]) \times 2^0 + (nums[1] - nums[n-2]) \times 2^1 + ... + (nums[n-1] - nums[0]) \times 2^{n-1}
\end{aligned}
$$

即：

$$
\begin{aligned}
\sum_{i=0}^{n-1} (nums[i] - nums[n-i-1]) \times 2^i
\end{aligned}
$$

因此我们只需要对数组 `nums` 进行排序，然后计算上述的贡献即可。注意答案的取模操作。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumSubseqWidths(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        nums.sort()
        ans, p = 0, 1
        for i, v in enumerate(nums):
            ans = (ans + (v - nums[-i - 1]) * p) % mod
            p = (p << 1) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long ans = 0, p = 1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            ans = (ans + (nums[i] - nums[n - i - 1]) * p + MOD) % MOD;
            p = (p << 1) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int sumSubseqWidths(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        long ans = 0, p = 1;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ans = (ans + (nums[i] - nums[n - i - 1]) * p + mod) % mod;
            p = (p << 1) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func sumSubseqWidths(nums []int) (ans int) {
	const mod int = 1e9 + 7
	sort.Ints(nums)
	p, n := 1, len(nums)
	for i, v := range nums {
		ans = (ans + (v-nums[n-i-1])*p + mod) % mod
		p = (p << 1) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
