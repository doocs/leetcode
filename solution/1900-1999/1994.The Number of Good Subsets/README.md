# [1994. 好子集的数目](https://leetcode.cn/problems/the-number-of-good-subsets)

[English Version](/solution/1900-1999/1994.The%20Number%20of%20Good%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。如果&nbsp;<code>nums</code>&nbsp;的一个子集中，所有元素的乘积可以表示为一个或多个 <strong>互不相同的质数</strong> 的乘积，那么我们称它为&nbsp;<strong>好子集</strong>&nbsp;。</p>

<ul>
	<li>比方说，如果&nbsp;<code>nums = [1, 2, 3, 4]</code>&nbsp;：
    <ul>
    	<li><code>[2, 3]</code>&nbsp;，<code>[1, 2, 3]</code>&nbsp;和&nbsp;<code>[1, 3]</code>&nbsp;是 <strong>好</strong>&nbsp;子集，乘积分别为&nbsp;<code>6 = 2*3</code>&nbsp;，<code>6 = 2*3</code>&nbsp;和&nbsp;<code>3 = 3</code>&nbsp;。</li>
    	<li><code>[1, 4]</code> 和&nbsp;<code>[4]</code>&nbsp;不是 <strong>好</strong>&nbsp;子集，因为乘积分别为&nbsp;<code>4 = 2*2</code> 和&nbsp;<code>4 = 2*2</code>&nbsp;。</li>
    </ul>
    </li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中不同的&nbsp;<strong>好</strong>&nbsp;子集的数目对<em>&nbsp;</em><code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。</p>

<p><code>nums</code>&nbsp;中的 <strong>子集</strong>&nbsp;是通过删除 <code>nums</code>&nbsp;中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>6
<b>解释：</b>好子集为：
- [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
- [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [4,2,3,15]
<b>输出：</b>5
<b>解释：</b>好子集为：
- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
- [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
- [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

状态压缩 DP。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfGoodSubsets(self, nums: List[int]) -> int:
        counter = Counter(nums)
        mod = 10**9 + 7
        prime = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        n = len(prime)
        dp = [0] * (1 << n)
        dp[0] = 1
        for x in range(2, 31):
            if counter[x] == 0 or x % 4 == 0 or x % 9 == 0 or x % 25 == 0:
                continue
            mask = 0
            for i, p in enumerate(prime):
                if x % p == 0:
                    mask |= 1 << i
            for state in range(1 << n):
                if mask & state:
                    continue
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % mod
        ans = 0
        for i in range(1, 1 << n):
            ans = (ans + dp[i]) % mod
        for i in range(counter[1]):
            ans = (ans << 1) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfGoodSubsets(int[] nums) {
        int[] counter = new int[31];
        for (int x : nums) {
            ++counter[x];
        }
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int n = prime.length;
        long[] dp = new long[1 << n];
        dp[0] = 1;
        for (int x = 2; x <= 30; ++x) {
            if (counter[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % prime[i] == 0) {
                    mask |= (1 << i);
                }
            }
            for (int state = 0; state < 1 << n; ++state) {
                if ((mask & state) > 0) {
                    continue;
                }
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % MOD;
            }
        }
        long ans = 0;
        for (int i = 1; i < 1 << n; ++i) {
            ans = (ans + dp[i]) % MOD;
        }
        while (counter[1]-- > 0) {
            ans = (ans << 1) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfGoodSubsets(vector<int>& nums) {
        vector<int> counter(31);
        for (int& x : nums) ++counter[x];
        vector<int> prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        const int MOD = 1e9 + 7;
        int n = prime.size();
        vector<long long> dp(1 << n);
        dp[0] = 1;
        for (int x = 2; x <= 30; ++x) {
            if (counter[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) continue;
            int mask = 0;
            for (int i = 0; i < n; ++i)
                if (x % prime[i] == 0)
                    mask |= (1 << i);
            for (int state = 0; state < 1 << n; ++state) {
                if ((mask & state) > 0) continue;
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % MOD;
            }
        }
        long long ans = 0;
        for (int i = 1; i < 1 << n; ++i) ans = (ans + dp[i]) % MOD;
        while (counter[1]--) ans = (ans << 1) % MOD;
        return (int)ans;
    }
};
```

### **Go**

```go
func numberOfGoodSubsets(nums []int) int {
	counter := make([]int, 31)
	for _, x := range nums {
		counter[x]++
	}
	const mod int = 1e9 + 7
	prime := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
	n := len(prime)
	dp := make([]int, 1<<n)
	dp[0] = 1
	for x := 2; x <= 30; x++ {
		if counter[x] == 0 || x%4 == 0 || x%9 == 0 || x%25 == 0 {
			continue
		}
		mask := 0
		for i, p := range prime {
			if x%p == 0 {
				mask |= (1 << i)
			}
		}
		for state := 0; state < 1<<n; state++ {
			if (mask & state) > 0 {
				continue
			}
			dp[mask|state] = (dp[mask|state] + counter[x]*dp[state]) % mod
		}
	}
	ans := 0
	for i := 1; i < 1<<n; i++ {
		ans = (ans + dp[i]) % mod
	}
	for counter[1] > 0 {
		ans = (ans << 1) % mod
		counter[1]--
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
