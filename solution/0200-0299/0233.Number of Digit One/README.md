# [233. 数字 1 的个数](https://leetcode.cn/problems/number-of-digit-one)

[English Version](/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <code>n</code>，计算所有小于等于 <code>n</code> 的非负整数中数字 <code>1</code> 出现的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

这道题实际上是求在给定区间 $[l,..r]$ 中，数字中出现 $1$ 个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为 int 数组 $a$，其中 $a[1]$ 为最低位，而 $a[len]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, cnt, limit)$，答案为 $dfs(len, 0, true)$。

其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `cnt` 表示当前数字中包含的 $1$ 的个数。
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1,..9]$，否则，只能选择 $[0,..a[pos]]$。如果 `limit` 为 `true` 且已经取到了能取到的最大值，那么下一个 `limit` 同样为 `true`；如果 `limit` 为 `true` 但是还没有取到最大值，或者 `limit` 为 `false`，那么下一个 `limit` 为 `false`。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log n)$。

相似题目：

-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 1), limit and i == up)
            return ans

        a = [0] * 12
        l = 1
        while n:
            a[l] = n % 10
            n //= 10
            l += 1
        return dfs(l, 0, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][12];

    public int countDigitOne(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        if (!limit) {
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
    int a[12];
    int dp[12][12];

    int countDigitOne(int n) {
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int cnt, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countDigitOne(n int) int {
	a := make([]int, 12)
	dp := make([][]int, 12)
	for i := range dp {
		dp[i] = make([]int, 12)
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
	var dfs func(int, int, bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos <= 0 {
			return cnt
		}
		if !limit && dp[pos][cnt] != -1 {
			return dp[pos][cnt]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			t := cnt
			if i == 1 {
				t++
			}
			ans += dfs(pos-1, t, limit && i == up)
		}
		if !limit {
			dp[pos][cnt] = ans
		}
		return ans
	}
	return dfs(l, 0, true)
}
```

### **C#**

```cs
public class Solution {
    public int CountDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        return CountDigitOne(n / 10 - 1) * 10 + n / 10 + CountDigitOneOfN(n / 10) * (n % 10 + 1) + (n % 10 >= 1 ? 1 : 0);
    }

    private int CountDigitOneOfN(int n) {
        var count = 0;
        while (n > 0)
        {
            if (n % 10 == 1) ++count;
            n /= 10;
        }
        return count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
