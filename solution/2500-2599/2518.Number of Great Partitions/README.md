# [2518. 好分区的数目](https://leetcode.cn/problems/number-of-great-partitions)

[English Version](/solution/2500-2599/2518.Number%20of%20Great%20Partitions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p><strong>分区</strong> 的定义是：将数组划分成两个有序的 <strong>组</strong> ，并满足每个元素 <strong>恰好</strong> 存在于 <strong>某一个</strong> 组中。如果分区中每个组的元素和都大于等于 <code>k</code> ，则认为分区是一个好分区。</p>

<p>返回 <strong>不同</strong> 的好分区的数目。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的结果。</p>

<p>如果在两个分区中，存在某个元素 <code>nums[i]</code> 被分在不同的组中，则认为这两个分区不同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 4
<strong>输出：</strong>6
<strong>解释：</strong>好分区的情况是 ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) 和 ([4], [1,2,3]) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3,3], k = 4
<strong>输出：</strong>0
<strong>解释：</strong>数组中不存在好分区。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,6], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>可以将 nums[0] 放入第一个分区或第二个分区中。
好分区的情况是 ([6], [6]) 和 ([6], [6]) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向思维 + 动态规划**

对于一个长度为 $n$ 的数组 `nums`，每个元素都可以选择放入第一个分区或第二个分区，因此一共有 $2^n$ 种分区方式。每一种分区方式，得到的结果可以是“好分区”或者“坏分区”，题目要我们求“好分区”的个数，我们可以转换为求“坏分区”的个数。那么“好分区”的个数就是 $2^n$ 减去“坏分区”的个数。

“坏分区”实际上就是从数组 `nums` 中选出若干个元素，使得这若干个元素之和不超过 $k$。这可以通过动态规划（0-1 背包问题）来求解。

我们用 $f[i][j]$ 表示从数组 `nums` 的前 $i$ 个元素中选出若干个元素，使得这若干个元素之和为 $j$ 的方案数。那么 $f[i][j]$ 的状态转移方程为：

$$
f[i][j] = \left\{
\begin{aligned}
&f[i - 1][j] & \text{如果不选第 } i \text{ 个元素} \\
&f[i - 1][j - nums[i - 1]] & \text{如果选第 } i \text{ 个元素}
\end{aligned}
\right.
$$

那么“坏分区”的个数就是 $\sum_{j=0}^{k-1} f[n][j] \times 2$，其中 $n$ 为数组 `nums` 的长度。最后，我们用 $2^n$ 减去“坏分区”的个数，即可得到“好分区”的个数。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 为数组 `nums` 的长度，而 $k$ 为整数 $k$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPartitions(self, nums: List[int], k: int) -> int:
        if sum(nums) < k * 2:
            return 0
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * k for _ in range(n + 1)]
        f[0][0] = 1
        ans = 1
        for i in range(1, n + 1):
            ans = ans * 2 % mod
            for j in range(k):
                f[i][j] = f[i - 1][j]
                if j >= nums[i - 1]:
                    f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod
        return (ans - sum(f[-1]) * 2 + mod) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPartitions(int[] nums, int k) {
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s < k * 2) {
            return 0;
        }
        int n = nums.length;
        long[][] f = new long[n + 1][k];
        f[0][0] = 1;
        long ans = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % MOD;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % MOD;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % MOD + MOD) % MOD;
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

    int countPartitions(vector<int>& nums, int k) {
        long s = accumulate(nums.begin(), nums.end(), 0l);
        if (s < k * 2) return 0;
        int n = nums.size();
        long f[n + 1][k];
        int ans = 1;
        memset(f, 0, sizeof f);
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % mod;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % mod;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % mod + mod) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func countPartitions(nums []int, k int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < k*2 {
		return 0
	}
	const mod int = 1e9 + 7
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k)
	}
	f[0][0] = 1
	ans := 1
	for i := 1; i <= n; i++ {
		v := nums[i-1]
		ans = ans * 2 % mod
		for j := 0; j < k; j++ {
			f[i][j] = f[i-1][j]
			if j >= v {
				f[i][j] = (f[i][j] + f[i-1][j-v]) % mod
			}
		}
	}
	for j := 0; j < k; j++ {
		ans = (ans - f[n][j]*2%mod + mod) % mod
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
