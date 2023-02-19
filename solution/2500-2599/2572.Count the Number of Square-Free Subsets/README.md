# [2572. 无平方子集计数](https://leetcode.cn/problems/count-the-number-of-square-free-subsets)

[English Version](/solution/2500-2599/2572.Count%20the%20Number%20of%20Square-Free%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>nums</code> 。</p>

<p>如果数组 <code>nums</code> 的子集中的元素乘积是一个 <strong>无平方因子数</strong> ，则认为该子集是一个 <strong>无平方</strong> 子集。</p>

<p><strong>无平方因子数</strong> 是无法被除 <code>1</code> 之外任何平方数整除的数字。</p>

<p>返回数组 <code>nums</code> 中 <strong>无平方</strong> 且 <strong>非空</strong> 的子集数目。因为答案可能很大，返回对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p><code>nums</code> 的 <strong>非空子集</strong> 是可以由删除 <code>nums</code> 中一些元素（可以不删除，但不能全部删除）得到的一个数组。如果构成两个子集时选择删除的下标不同，则认为这两个子集不同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,4,5]
<strong>输出：</strong>3
<strong>解释：</strong>示例中有 3 个无平方子集：
- 由第 0 个元素 [3] 组成的子集。其元素的乘积是 3 ，这是一个无平方因子数。
- 由第 3 个元素 [5] 组成的子集。其元素的乘积是 5 ，这是一个无平方因子数。
- 由第 0 个和第 3 个元素 [3,5] 组成的子集。其元素的乘积是 15 ，这是一个无平方因子数。
可以证明给定数组中不存在超过 3 个无平方子集。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
<strong>解释：</strong>示例中有 1 个无平方子集：
- 由第 0 个元素 [1] 组成的子集。其元素的乘积是 1 ，这是一个无平方因子数。
可以证明给定数组中不存在超过 1 个无平方子集。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩动态规划**

注意到题目中 $nums[i]$ 的范围为 $[1, 30]$，因此我们可以预处理出所有小于等于 $30$ 的质数，即 $[2, 3, 5, 7, 11, 13, 17, 19, 23, 29]$。

无平方子集中，所有元素的乘积可以表示为一个或多个互不相同的质数的乘积，也即是说，每个质因数最多只能出现一次。因此，我们可以使用一个二进制数来表示一个子集中的质因数，其中二进制数的第 $i$ 位表示质数 $primes[i]$ 是否出现在子集中。

我们可以使用状态压缩动态规划的方法来求解本题。设 $f[i]$ 表示二进制数 $i$ 表示的子集中的质因数的乘积为一个或多个互不相同的质数的乘积的方案数。初始时 $f[0]=1$。

我们在 $[2,..30]$ 的范围内枚举一个数 $x$，如果 $x$ 不在 $nums$ 中，或者 $x$ 为 $4, 9, 25$ 的倍数，那么我们可以直接跳过。否则，我们可以将 $x$ 的质因数用一个二进制数 $mask$ 表示，然后我们从大到小枚举当前的状态 $state$，如果 $state$ 与 $mask$ 按位与的结果为 $mask$，那么我们可以从状态 $f[state \oplus mask]$ 转移到状态 $f[state]$，转移方程为 $f[state] = f[state] + cnt[x] \times f[state \oplus mask]$，其中 $cnt[x]$ 表示 $x$ 在 $nums$ 中出现的次数。

注意，我们没有从数字 $1$ 开始枚举，因为我们可以选择任意个数字 $1$，加入到无平方子集中。也可以只选择任意个数字 $1$，不加入到无平方子集中，这两种情况都是合法的。那么答案就是 $(\sum_{i=0}^{2^{10}-1} f[i]) - 1$。

时间复杂度 $O(n + C \times M)$，空间复杂度 $O(M)$。其中 $n$ 为 $nums$ 的长度；而 $C$ 和 $M$ 分别为题目中 $nums[i]$ 的范围和状态的个数，本题中 $C=30$, $M=2^{10}$。

相似题目：

-   [1994. 好子集的数目](/solution/1900-1999/1994.The%20Number%20of%20Good%20Subsets/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def squareFreeSubsets(self, nums: List[int]) -> int:
        primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        cnt = Counter(nums)
        mod = 10**9 + 7
        n = len(primes)
        f = [0] * (1 << n)
        f[0] = pow(2, cnt[1])
        for x in range(2, 31):
            if cnt[x] == 0 or x % 4 == 0 or x % 9 == 0 or x % 25 == 0:
                continue
            mask = 0
            for i, p in enumerate(primes):
                if x % p == 0:
                    mask |= 1 << i
            for state in range((1 << n) - 1, 0, -1):
                if state & mask == mask:
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod
        return sum(v for v in f) % mod - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int squareFreeSubsets(int[] nums) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] cnt = new int[31];
        for (int x : nums) {
            ++cnt[x];
        }
        final int mod = (int) 1e9 + 7;
        int n = primes.length;
        long[] f = new long[1 << n];
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = (f[0] * 2) % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state > 0; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        ans -= 1;
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int squareFreeSubsets(vector<int>& nums) {
        int primes[10] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int cnt[31]{};
        for (int& x : nums) {
            ++cnt[x];
        }
        int n = 10;
        const int mod = 1e9 + 7;
        vector<long long> f(1 << n);
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = f[0] * 2 % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + 1LL * cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long long ans = -1;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func squareFreeSubsets(nums []int) (ans int) {
	primes := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
	cnt := [31]int{}
	for _, x := range nums {
		cnt[x]++
	}
	const mod int = 1e9 + 7
	n := 10
	f := make([]int, 1<<n)
	f[0] = 1
	for i := 0; i < cnt[1]; i++ {
		f[0] = f[0] * 2 % mod
	}
	for x := 2; x < 31; x++ {
		if cnt[x] == 0 || x%4 == 0 || x%9 == 0 || x%25 == 0 {
			continue
		}
		mask := 0
		for i, p := range primes {
			if x%p == 0 {
				mask |= 1 << i
			}
		}
		for state := 1<<n - 1; state > 0; state-- {
			if state&mask == mask {
				f[state] = (f[state] + f[state^mask]*cnt[x]) % mod
			}
		}
	}
	ans = -1
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
