# [357. 统计各位数字都不同的数字个数](https://leetcode.cn/problems/count-numbers-with-unique-digits)

[English Version](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个整数 <code>n</code> ，统计并返回各位数字都不同的数字 <code>x</code> 的个数，其中 <code>0 &lt;= x &lt; 10<sup>n</sup></code><sup>&nbsp;</sup>。

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>91
<strong>解释：</strong>答案应为除去 <code>11、22、33、44、55、66、77、88、99 </code>外，在 0 ≤ x &lt; 100 范围内的所有数字。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>1
</pre>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 8</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排列组合**

当 $n=0$ 时，有 $0\le x \lt 1$，只有 $1$ 个数字，即 $0$。

当 $n=1$ 时，有 $0\le x \lt 10$，有 $10$ 个数字，即 $0,1,2,3,4,5,6,7,8,9$。

当 $n=2$ 时，有 $0\le x \lt 100$，那么 $x$ 的选择可以由两部分组成：只有一位数的数字和有两位数的数字。对于只有一位数的情况，可以由上述的边界情况计算；对于有两位数的情况，由于第一位数字不能为 $0$，所以第一位数字有 $9$ 种选择，第二位数字有 $9$ 种选择，所以有 $9 \times 9$ 种选择，即 $81$ 种选择。

更一般的情况，含有 $n$ 位数且各位数字都不同的数字 $x$ 的个数为 $9 \times A_{9}^{n-1}$。再加上含有小于 $n$ 位数且各位数字都不同的数字 $x$ 的个数，即为答案。

时间复杂度 $O(n)$。

**方法二：状态压缩 + 数位 DP**

这道题实际上是求在给定区间 $[l,..r]$ 中，满足条件的数的个数。条件与数的大小无关，而只与数的组成有关，因此可以使用数位 DP 的思想求解。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..10^n-1]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

我们根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, mask, lead)$，答案为 $dfs(len, 0, true)$。

其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `mask` 表示当前数字选取了哪些数字（状态压缩）；
-   `lead` 表示当前数字是否含有前导零；

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(n)$。

相似题目：

-   [233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
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
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        if n == 0:
            return 1
        if n == 1:
            return 10
        ans, cur = 10, 9
        for i in range(n - 1):
            cur *= 9 - i
            ans += cur
        return ans
```

```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        @cache
        def dfs(pos, mask, lead):
            if pos <= 0:
                return 1
            ans = 0
            for i in range(10):
                if (mask >> i) & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead)
                else:
                    ans += dfs(pos - 1, mask | (1 << i), False)
            return ans

        return dfs(n, 0, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[][] dp = new int[10][1 << 11];

    public int countNumbersWithUniqueDigits(int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(n, 0, true);
    }

    private int dfs(int pos, int mask, boolean lead) {
        if (pos <= 0) {
            return 1;
        }
        if (!lead && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if (((mask >> i) & 1) == 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead);
            } else {
                ans += dfs(pos - 1, mask | (1 << i), false);
            }
        }
        if (!lead) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int dp[10][1 << 11];

    int countNumbersWithUniqueDigits(int n) {
        memset(dp, -1, sizeof dp);
        return dfs(n, 0, true);
    }

    int dfs(int pos, int mask, bool lead) {
        if (pos <= 0) {
            return 1;
        }
        if (!lead && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if ((mask >> i) & 1) continue;
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false);
            }
        }
        if (!lead) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countNumbersWithUniqueDigits(n int) int {
	if n == 0 {
		return 1
	}
	if n == 1 {
		return 10
	}
	ans := 10
	for i, cur := 0, 9; i < n-1; i++ {
		cur *= (9 - i)
		ans += cur
	}
	return ans
}
```

```go
func countNumbersWithUniqueDigits(n int) int {
	dp := make([][]int, 10)
	for i := range dp {
		dp[i] = make([]int, 1<<11)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, mask int, lead bool) int {
		if pos <= 0 {
			return 1
		}
		if !lead && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		ans := 0
		for i := 0; i < 10; i++ {
			if ((mask >> i) & 1) == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead)
			} else {
				ans += dfs(pos-1, mask|1<<i, false)
			}
		}
		if !lead {
			dp[pos][mask] = ans
		}
		return ans
	}

	return dfs(n, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
