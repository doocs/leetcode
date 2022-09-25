# [1067. 范围内的数字计数](https://leetcode.cn/problems/digit-count-in-range)

[English Version](/solution/1000-1099/1067.Digit%20Count%20in%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个在 <code>0</code>&nbsp;到&nbsp;<code>9</code> 之间的整数&nbsp;<code>d</code>，和两个正整数&nbsp;<code>low</code>&nbsp;和&nbsp;<code>high</code>&nbsp;分别作为上下界。返回&nbsp;<code>d</code> 在&nbsp;<code>low</code>&nbsp;和&nbsp;<code>high</code>&nbsp;之间的整数中出现的次数，包括边界&nbsp;<code>low</code> 和&nbsp;<code>high</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>d = 1, low = 1, high = 13
<strong>输出：</strong>6
<strong>解释： </strong>
数字 <code>d=1</code> 在 <code>1,10,11,12,13 中出现 6 次</code>。注意 <code>d=1</code> 在数字 11 中出现两次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>d = 3, low = 100, high = 250
<strong>输出：</strong>35
<strong>解释：</strong>
数字 <code>d=3</code> 在 <code>103,113,123,130,131,...,238,239,243 出现 35 次。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= d &lt;= 9</code></li>
	<li><code>1 &lt;= low &lt;= high &lt;= 2&times;10^8</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

这道题实际上是求在给定区间 $[l,..r]$ 中，数字中出现 $d$ 的个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为 int 数组 $a$，其中 $a[1]$ 为最低位，而 $a[len]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, cnt, lead, limit)$，答案为 $dfs(len, 0, true, true)$。

其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `cnt` 表示当前数字中包含 $d$ 的个数；
-   `lead` 表示当前数字是否有前导零，如果有前导零，则 `lead` 为 `true`，否则为 `false`，初始化为 `true`；
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1,..9]$，否则，只能选择 $[0,..a[pos]]$。如果 `limit` 为 `true` 且已经取到了能取到的最大值，那么下一个 `limit` 同样为 `true`；如果 `limit` 为 `true` 但是还没有取到最大值，或者 `limit` 为 `false`，那么下一个 `limit` 为 `false`。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log m + \log n)$。其中 $m$, $n$ 分别为题目中的 `low` 和 `high`。

相似题目：[233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def digitsCount(self, d: int, low: int, high: int) -> int:
        return self.f(high, d) - self.f(low - 1, d)

    def f(self, n, d):
        @cache
        def dfs(pos, cnt, lead, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos - 1, cnt, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, cnt + (i == d),
                               False, limit and i == up)
            return ans

        a = [0] * 11
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int d;
    private int[] a = new int[11];
    private int[][] dp = new int[11][11];

    public int digitsCount(int d, int low, int high) {
        this.d = d;
        return f(high) - f(low - 1);
    }

    private int f(int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    private int dfs(int pos, int cnt, boolean lead, boolean limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!lead && !limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, cnt, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, cnt + (i == d ? 1 : 0), false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int d;
    int a[11];
    int dp[11][11];

    int digitsCount(int d, int low, int high) {
        this->d = d;
        return f(high) - f(low - 1);
    }

    int f(int n) {
        memset(dp, -1, sizeof dp);
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    int dfs(int pos, int cnt, bool lead, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!lead && !limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, cnt, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, cnt + (i == d), false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func digitsCount(d int, low int, high int) int {
	f := func(n int) int {
		a := make([]int, 11)
		dp := make([][]int, 11)
		for i := range dp {
			dp[i] = make([]int, 11)
			for j := range dp[i] {
				dp[i][j] = -1
			}
		}
		l := 0
		for n > 0 {
			l++
			a[l] = n % 10
			n /= 10
		}

		var dfs func(int, int, bool, bool) int
		dfs = func(pos, cnt int, lead, limit bool) int {
			if pos <= 0 {
				return cnt
			}
			if !lead && !limit && dp[pos][cnt] != -1 {
				return dp[pos][cnt]
			}
			up := 9
			if limit {
				up = a[pos]
			}
			ans := 0
			for i := 0; i <= up; i++ {
				if i == 0 && lead {
					ans += dfs(pos-1, cnt, lead, limit && i == up)
				} else {
					t := cnt
					if d == i {
						t++
					}
					ans += dfs(pos-1, t, false, limit && i == up)
				}
			}
			if !lead && !limit {
				dp[pos][cnt] = ans
			}
			return ans
		}

		return dfs(l, 0, true, true)
	}
	return f(high) - f(low-1)
}
```

### **...**

```

```

<!-- tabs:end -->
