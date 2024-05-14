---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3007.Maximum%20Number%20That%20Sum%20of%20the%20Prices%20Is%20Less%20Than%20or%20Equal%20to%20K/README.md
rating: 2258
tags:
    - 位运算
    - 二分查找
    - 动态规划
---

# [3007. 价值和小于等于 K 的最大数字](https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k)

[English Version](/solution/3000-3099/3007.Maximum%20Number%20That%20Sum%20of%20the%20Prices%20Is%20Less%20Than%20or%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>k</code>&nbsp;和一个整数&nbsp;<code>x</code>&nbsp;。整数&nbsp;<code>num</code>&nbsp;的价值是它的二进制表示中在&nbsp;<code>x</code>，<code>2x</code>，<code>3x</code>&nbsp;等位置处&nbsp;<strong><span data-keyword="set-bit">设置位</span></strong>&nbsp;的数目（从最低有效位开始）。下面的表格包含了如何计算价值的例子。</p>

<table border="1">
	<tbody>
		<tr>
			<th>x</th>
			<th>num</th>
			<th>Binary Representation</th>
			<th>Price</th>
		</tr>
		<tr>
			<td>1</td>
			<td>13</td>
			<td><u>0</u><u>0</u><u>0</u><u>0</u><u>0</u><strong><u>1</u></strong><strong><u>1</u></strong><u>0</u><strong><u>1</u></strong></td>
			<td>3</td>
		</tr>
		<tr>
			<td>2</td>
			<td>13</td>
			<td>0<u>0</u>0<u>0</u>0<strong><u>1</u></strong>1<u>0</u>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
			<td>233</td>
			<td>0<strong><u>1</u></strong>1<strong><u>1</u></strong>0<strong><u>1</u></strong>0<u>0</u>1</td>
			<td>3</td>
		</tr>
		<tr>
			<td>3</td>
			<td>13</td>
			<td><u>0</u>00<u>0</u>01<strong><u>1</u></strong>01</td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>362</td>
			<td><strong><u>1</u></strong>01<strong><u>1</u></strong>01<u>0</u>10</td>
			<td>2</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><code>num</code>&nbsp;的 <strong>累加价值</strong> 是从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>num</code>&nbsp;的数字的 <strong>总</strong> 价值。如果&nbsp;<code>num</code>&nbsp;的累加价值小于或等于&nbsp;<code>k</code>&nbsp;则被认为是 <strong>廉价</strong> 的。</p>

<p>请你返回<strong>&nbsp;最大</strong>&nbsp;的廉价数字。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>k = 9, x = 1
<b>输出：</b>6
<b>解释：</b>由下表所示，6 是最大的廉价数字。
</pre>

<table border="1">
	<tbody>
		<tr>
			<th>x</th>
			<th>num</th>
			<th>Binary Representation</th>
			<th>Price</th>
			<th>Accumulated Price</th>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td><u>0</u><u>0</u><strong><u>1</u></strong></td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td><u>0</u><strong><u>1</u></strong><u>0</u></td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>1</td>
			<td>3</td>
			<td><u>0</u><strong><u>1</u></strong><strong><u>1</u></strong></td>
			<td>2</td>
			<td>4</td>
		</tr>
		<tr>
			<td>1</td>
			<td>4</td>
			<td><strong><u>1</u></strong><u>0</u><u>0</u></td>
			<td>1</td>
			<td>5</td>
		</tr>
		<tr>
			<td>1</td>
			<td>5</td>
			<td><strong><u>1</u></strong><u>0</u><strong><u>1</u></strong></td>
			<td>2</td>
			<td>7</td>
		</tr>
		<tr>
			<td>1</td>
			<td>6</td>
			<td><strong><u>1</u></strong><strong><u>1</u></strong><u>0</u></td>
			<td>2</td>
			<td>9</td>
		</tr>
		<tr>
			<td>1</td>
			<td>7</td>
			<td><strong><u>1</u></strong><strong><u>1</u></strong><strong><u>1</u></strong></td>
			<td>3</td>
			<td>12</td>
		</tr>
	</tbody>
</table>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>k = 7, x = 2
<b>输出：</b>9
<b>解释：</b>由下表所示，9 是最大的廉价数字。
</pre>

<table border="1">
	<tbody>
		<tr>
			<th>x</th>
			<th>num</th>
			<th>Binary Representation</th>
			<th>Price</th>
			<th>Accumulated Price</th>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td><u>0</u>0<u>0</u>1</td>
			<td>0</td>
			<td>0</td>
		</tr>
		<tr>
			<td>2</td>
			<td>2</td>
			<td><u>0</u>0<strong><u>1</u></strong>0</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
			<td>3</td>
			<td><u>0</u>0<strong><u>1</u></strong>1</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>4</td>
			<td><u>0</u>1<u>0</u>0</td>
			<td>0</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>5</td>
			<td><u>0</u>1<u>0</u>1</td>
			<td>0</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>6</td>
			<td><u>0</u>1<strong><u>1</u></strong>0</td>
			<td>1</td>
			<td>3</td>
		</tr>
		<tr>
			<td>2</td>
			<td>7</td>
			<td><u>0</u>1<strong><u>1</u></strong>1</td>
			<td>1</td>
			<td>4</td>
		</tr>
		<tr>
			<td>2</td>
			<td>8</td>
			<td><strong><u>1</u></strong>0<u>0</u>0</td>
			<td>1</td>
			<td>5</td>
		</tr>
		<tr>
			<td>2</td>
			<td>9</td>
			<td><strong><u>1</u></strong>0<u>0</u>1</td>
			<td>1</td>
			<td>6</td>
		</tr>
		<tr>
			<td>2</td>
			<td>10</td>
			<td><strong><u>1</u></strong>0<strong><u>1</u></strong>0</td>
			<td>2</td>
			<td>8</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= x &lt;= 8</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
