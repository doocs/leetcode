# [1799. N 次操作后的最大分数和](https://leetcode.cn/problems/maximize-score-after-n-operations)

[English Version](/solution/1700-1799/1799.Maximize%20Score%20After%20N%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>nums</code> ，它是一个大小为 <code>2 * n</code> 的正整数数组。你必须对这个数组执行 <code>n</code> 次操作。</p>

<p>在第 <code>i</code> 次操作时（操作编号从 <strong>1</strong> 开始），你需要：</p>

<ul>
	<li>选择两个元素 <code>x</code> 和 <code>y</code> 。</li>
	<li>获得分数 <code>i * gcd(x, y)</code> 。</li>
	<li>将 <code>x</code> 和 <code>y</code> 从 <code>nums</code> 中删除。</li>
</ul>

<p>请你返回 <code>n</code> 次操作后你能获得的分数和最大为多少。</p>

<p>函数 <code>gcd(x, y)</code> 是 <code>x</code> 和 <code>y</code> 的最大公约数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2]
<b>输出：</b>1
<b>解释：</b>最优操作是：
(1 * gcd(1, 2)) = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [3,4,6,8]
<b>输出：</b>11
<b>解释：</b>最优操作是：
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5,6]
<b>输出：</b>14
<b>解释：</b>最优操作是：
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 动态规划**

我们可以先预处理得到数组 `nums` 中任意两个数的最大公约数，存储在二维数组 $g$ 中，其中 $g[i][j]$ 表示 $nums[i]$ 和 $nums[j]$ 的最大公约数。

然后定义 $f[k]$ 表示当前操作后的状态为 $k$ 时，可以获得的最大分数和。假设 $m$ 为数组 `nums` 中的元素个数，那么状态一共有 $2^m$ 种，即 $k$ 的取值范围为 $[0, 2^m - 1]$。

从小到大枚举所有状态，对于每个状态 $k$，先判断此状态的二进制位中 $1$ 的个数 $cnt$ 是否为偶数，是则进行如下操作：

枚举 $k$ 中二进制位为 1 的位置，假设为 $i$ 和 $j$，则 $i$ 和 $j$ 两个位置的元素可以进行一次操作，此时可以获得的分数为 $\frac{cnt}{2} \times g[i][j]$，更新 $f[k]$ 的最大值。

最终答案即为 $f[2^m - 1]$。

时间复杂度 $O(2^m \times m^2)$，空间复杂度 $O(2^m)$。其中 $m$ 为数组 `nums` 中的元素个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        m = len(nums)
        g = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(i + 1, m):
                g[i][j] = gcd(nums[i], nums[j])
        f = [0] * (1 << m)
        for k in range(1 << m):
            if (cnt := k.bit_count()) % 2 == 0:
                for i in range(m):
                    if k >> i & 1:
                        for j in range(i + 1, m):
                            if k >> j & 1:
                                f[k] = max(
                                    f[k],
                                    f[k ^ (1 << i) ^ (1 << j)] + cnt // 2 * g[i][j],
                                )
        return f[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxScore(int[] nums) {
        int m = nums.length;
        int[][] g = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                g[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] f = new int[1 << m];
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = Integer.bitCount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (((k >> i) & 1) == 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (((k >> j) & 1) == 1) {
                                f[k] = Math.max(
                                    f[k], f[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * g[i][j]);
                            }
                        }
                    }
                }
            }
        }
        return f[(1 << m) - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int m = nums.size();
        int g[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                g[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int f[1 << m];
        memset(f, 0, sizeof f);
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = __builtin_popcount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (k >> i & 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (k >> j & 1) {
                                f[k] = max(f[k], f[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * g[i][j]);
                            }
                        }
                    }
                }
            }
        }
        return f[(1 << m) - 1];
    }
};
```

### **Go**

```go
func maxScore(nums []int) int {
	m := len(nums)
	g := [14][14]int{}
	for i := 0; i < m; i++ {
		for j := i + 1; j < m; j++ {
			g[i][j] = gcd(nums[i], nums[j])
		}
	}
	f := make([]int, 1<<m)
	for k := 0; k < 1<<m; k++ {
		cnt := bits.OnesCount(uint(k))
		if cnt%2 == 0 {
			for i := 0; i < m; i++ {
				if k>>i&1 == 1 {
					for j := i + 1; j < m; j++ {
						if k>>j&1 == 1 {
							f[k] = max(f[k], f[k^(1<<i)^(1<<j)]+cnt/2*g[i][j])
						}
					}
				}
			}
		}
	}
	return f[1<<m-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
