# [3007. 价值和小于等于 K 的最大数字](https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k)

[English Version](/solution/3000-3099/3007.Maximum%20Number%20That%20Sum%20of%20the%20Prices%20Is%20Less%20Than%20or%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>k</code>&nbsp;和一个整数&nbsp;<code>x</code>&nbsp;。</p>

<p>令 <code>s</code>&nbsp;为整数&nbsp;<code>num</code>&nbsp;的下标从 <strong>1</strong>&nbsp;开始的二进制表示。我们说一个整数&nbsp;<code>num</code>&nbsp;的 <strong>价值</strong>&nbsp;是满足&nbsp;<code>i % x == 0</code> 且&nbsp;<code><font face="monospace">s[i]</font></code>&nbsp;是 <strong>设置位</strong>&nbsp;的 <code>i</code>&nbsp;的数目。</p>

<p>请你返回<strong>&nbsp;最大</strong>&nbsp;整数<em>&nbsp;</em><code>num</code>&nbsp;，满足从 <code>1</code>&nbsp;到 <code>num</code>&nbsp;的所有整数的 <strong>价值</strong>&nbsp;和小于等于 <code>k</code>&nbsp;。</p>

<p><b>注意：</b></p>

<ul>
	<li>一个整数二进制表示下 <strong>设置位</strong>&nbsp;是值为 <code>1</code>&nbsp;的数位。</li>
	<li>一个整数的二进制表示下标从右到左编号，比方说如果&nbsp;<code>s == 11100</code>&nbsp;，那么&nbsp;<code>s[4] == 1</code> 且&nbsp;<code>s[2] == 0</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>k = 9, x = 1
<b>输出：</b>6
<b>解释：</b>数字 1 ，2 ，3 ，4 ，5 和 6 二进制表示分别为 "1" ，"10" ，"11" ，"100" ，"101" 和 "110" 。
由于 x 等于 1 ，每个数字的价值分别为所有设置位的数目。
这些数字的所有设置位数目总数是 9 ，所以前 6 个数字的价值和为 9 。
所以答案为 6 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>k = 7, x = 2
<b>输出：</b>9
<b>解释：</b>由于 x 等于 2 ，我们检查每个数字的偶数位。
2 和 3 在二进制表示下的第二个数位为设置位，所以它们的价值和为 2 。
6 和 7 在二进制表示下的第二个数位为设置位，所以它们的价值和为 2 。
8 和 9 在二进制表示下的第四个数位为设置位但第二个数位不是设置位，所以它们的价值和为 2 。
数字 1 ，4 和 5 在二进制下偶数位都不是设置位，所以它们的价值和为 0 。
10 在二进制表示下的第二个数位和第四个数位都是设置位，所以它的价值为 2 。
前 9 个数字的价值和为 6 。
前 10 个数字的价值和为 8，超过了 k = 7 ，所以答案为 9 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= x &lt;= 8</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaximumNumber(self, k: int, x: int) -> int:
        @cache
        def dfs(pos, limit, cnt):
            if pos == 0:
                return cnt
            ans = 0
            up = (self.num >> (pos - 1) & 1) if limit else 1
            for i in range(up + 1):
                ans += dfs(pos - 1, limit and i == up, cnt + (i == 1 and pos % x == 0))
            return ans

        l, r = 1, 10**18
        while l < r:
            mid = (l + r + 1) >> 1
            self.num = mid
            v = dfs(mid.bit_length(), True, 0)
            dfs.cache_clear()
            if v <= k:
                l = mid
            else:
                r = mid - 1
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int x;
    private long num;
    private Long[][] f;

    public long findMaximumNumber(long k, int x) {
        this.x = x;
        long l = 1, r = (long) 1e17;
        while (l < r) {
            long mid = (l + r + 1) >>> 1;
            num = mid;
            f = new Long[65][65];
            int pos = 64 - Long.numberOfLeadingZeros(mid);
            if (dfs(pos, 0, true) <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private long dfs(int pos, int cnt, boolean limit) {
        if (pos == 0) {
            return cnt;
        }
        if (!limit && f[pos][cnt] != null) {
            return f[pos][cnt];
        }
        long ans = 0;
        int up = limit ? (int) (num >> (pos - 1) & 1) : 1;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 && pos % x == 0 ? 1 : 0), limit && i == up);
        }
        if (!limit) {
            f[pos][cnt] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long findMaximumNumber(long long k, int x) {
        using ll = long long;
        ll l = 1, r = 1e17;
        ll num = 0;
        ll f[65][65];
        function<ll(int, int, bool)> dfs = [&](int pos, int cnt, bool limit) -> ll {
            if (pos == 0) {
                return cnt;
            }
            if (!limit && f[pos][cnt] != -1) {
                return f[pos][cnt];
            }
            int up = limit ? num >> (pos - 1) & 1 : 1;
            ll ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos - 1, cnt + (i == 1 && pos % x == 0), limit && i == up);
            }
            if (!limit) {
                f[pos][cnt] = ans;
            }
            return ans;
        };
        while (l < r) {
            ll mid = (l + r + 1) >> 1;
            num = mid;
            memset(f, -1, sizeof(f));
            int pos = 64 - __builtin_clzll(mid);
            if (dfs(pos, 0, true) <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func findMaximumNumber(k int64, x int) int64 {
	var l, r int64 = 1, 1e17
	var num int64
	var f [65][65]int64
	var dfs func(pos, cnt int, limit bool) int64
	dfs = func(pos, cnt int, limit bool) int64 {
		if pos == 0 {
			return int64(cnt)
		}
		if !limit && f[pos][cnt] != -1 {
			return f[pos][cnt]
		}
		var ans int64
		up := 1
		if limit {
			up = int(num >> (pos - 1) & 1)
		}
		for i := 0; i <= up; i++ {
			v := cnt
			if i == 1 && pos%x == 0 {
				v++
			}
			ans += dfs(pos-1, v, limit && i == up)
		}
		if !limit {
			f[pos][cnt] = ans
		}
		return ans
	}
	for l < r {
		mid := (l + r + 1) >> 1
		num = mid
		m := bits.Len(uint(num))
		for i := range f {
			for j := range f[i] {
				f[i][j] = -1
			}
		}
		if dfs(m, 0, true) <= k {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

### **...**

```

```

<!-- tabs:end -->
