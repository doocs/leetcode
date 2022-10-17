# [902. 最大为 N 的数字组合](https://leetcode.cn/problems/numbers-at-most-n-given-digit-set)

[English Version](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个按&nbsp;<strong>非递减顺序</strong>&nbsp;排列的数字数组<meta charset="UTF-8" />&nbsp;<code>digits</code>&nbsp;。你可以用任意次数&nbsp;<code>digits[i]</code>&nbsp;来写的数字。例如，如果<meta charset="UTF-8" />&nbsp;<code>digits = ['1','3','5']</code>，我们可以写数字，如<meta charset="UTF-8" />&nbsp;<code>'13'</code>,&nbsp;<code>'551'</code>, 和&nbsp;<code>'1351315'</code>。</p>

<p>返回 <em>可以生成的小于或等于给定整数 <code>n</code> 的正整数的个数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = ["1","3","5","7"], n = 100
<strong>输出：</strong>20
<strong>解释：</strong>
可写出的 20 个数字是：
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = ["1","4","9"], n = 1000000000
<strong>输出：</strong>29523
<strong>解释：</strong>
我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
81 个四位数字，243 个五位数字，729 个六位数字，
2187 个七位数字，6561 个八位数字和 19683 个九位数字。
总共，可以使用D中的数字写出 29523 个整数。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>digits = ["7"], n = 8
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= digits.length &lt;= 9</code></li>
	<li><code>digits[i].length == 1</code></li>
	<li><code>digits[i]</code>&nbsp;是从&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code> 的数</li>
	<li><code>digits</code>&nbsp;中的所有值都 <strong>不同</strong>&nbsp;</li>
	<li><code>digits</code>&nbsp;按&nbsp;<strong>非递减顺序</strong>&nbsp;排列</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

这道题实际上是求在给定区间 $[l,..r]$ 中，由 `digits` 中的数字生成的正整数的个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为 int 数组 $a$，其中 $a[1]$ 为最低位，而 $a[len]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, lead, limit)$，答案为 $dfs(len, 1, true)$。

其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `lead` 表示当前数字中是否包含前导零，如果包含，则为 `1`，否则为 `0`；初始化为 `1`；
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1,..9]$，否则，只能选择 $[0,..a[pos]]$。如果 `limit` 为 `true` 且已经取到了能取到的最大值，那么下一个 `limit` 同样为 `true`；如果 `limit` 为 `true` 但是还没有取到最大值，或者 `limit` 为 `false`，那么下一个 `limit` 为 `false`。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log n)$。

相似题目：

-   [233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [1012. 至少有 1 位重复的数字](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def atMostNGivenDigitSet(self, digits: List[str], n: int) -> int:
        @cache
        def dfs(pos, lead, limit):
            if pos <= 0:
                return lead == False
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos - 1, lead, limit and i == up)
                elif i in s:
                    ans += dfs(pos - 1, False, limit and i == up)
            return ans

        l = 0
        a = [0] * 12
        s = {int(d) for d in digits}
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, True, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][2];
    private Set<Integer> s = new HashSet<>();

    public int atMostNGivenDigitSet(String[] digits, int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        for (String d : digits) {
            s.add(Integer.parseInt(d));
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    private int dfs(int pos, int lead, boolean limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && lead != 1 && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead == 1) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.contains(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && lead == 0) {
            dp[pos][lead] = ans;
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
    int dp[12][2];
    unordered_set<int> s;

    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        memset(dp, -1, sizeof dp);
        for (auto& d : digits) {
            s.insert(stoi(d));
        }
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    int dfs(int pos, int lead, bool limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && !lead && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.count(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && !lead) {
            dp[pos][lead] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func atMostNGivenDigitSet(digits []string, n int) int {
	s := map[int]bool{}
	for _, d := range digits {
		i, _ := strconv.Atoi(d)
		s[i] = true
	}
	a := make([]int, 12)
	dp := make([][2]int, 12)
	for i := range a {
		dp[i] = [2]int{-1, -1}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, lead int, limit bool) int {
		if pos <= 0 {
			return lead ^ 1
		}
		if !limit && lead == 0 && dp[pos][lead] != -1 {
			return dp[pos][lead]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 && lead == 1 {
				ans += dfs(pos-1, lead, limit && i == up)
			} else if s[i] {
				ans += dfs(pos-1, 0, limit && i == up)
			}
		}
		if !limit {
			dp[pos][lead] = ans
		}
		return ans
	}
	return dfs(l, 1, true)
}
```

### **...**

```

```

<!-- tabs:end -->
