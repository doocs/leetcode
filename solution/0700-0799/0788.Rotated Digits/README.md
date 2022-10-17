# [788. 旋转数字](https://leetcode.cn/problems/rotated-digits)

[English Version](/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。</p>

<p>如果一个数的每位数字被旋转以后仍然还是一个数字，&nbsp;则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。</p>

<p>现在我们有一个正整数&nbsp;<code>N</code>, 计算从&nbsp;<code>1</code> 到&nbsp;<code>N</code> 中有多少个数&nbsp;X 是好数？</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> 10
<strong>输出:</strong> 4
<strong>解释:</strong> 
在[1, 10]中有四个好数： 2, 5, 6, 9。
注意 1 和 10 不是好数, 因为他们在旋转之后不变。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>N&nbsp;的取值范围是&nbsp;<code>[1, 10000]</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接枚举**

一种直观且有效的思路是，直接枚举 $[1,2,..n]$ 中的每个数，判断其是否为好数，若为好数，则答案加一。

那么题目的重点转化为如何判断一个数字 $x$ 是否为好数。判断的逻辑如下：

我们先用一个长度为 $10$ 的数组 $d$ 记录每个有效数字对应的旋转数字，在这道题中，有效数字有 $[0, 1, 8, 2, 5, 6, 9]$，分别对应旋转数字 $[0, 1, 8, 5, 2, 9, 6]$。如果不是有效数字，我们将对应的旋转数字设为 $-1$。

然后遍历数字 $x$ 的每一位数字 $v$，如果 $v$ 不是有效数字，说明 $x$ 不是好数，直接返回 `false`。否则，我们将数字 $v$ 对应的旋转数字 $d[v]$ 加入到 $y$ 中。最后，判断 $x$ 和 $y$ 是否相等，若不相等，则说明 $x$ 是好数，返回 `true`。

时间复杂度 $O(n\times \log n)$。

相似题目：[1056. 易混淆数](/solution/1000-1099/1056.Confusing%20Number/README.md)

**方法二：数位 DP**

方法一的做法足以通过本题，但时间复杂度较高。如果题目的数据范围达到 $10^9$ 级别，则方法一的做法会超出时间限制。

这道题实际上是求在给定区间 $[l,..r]$ 中，满足条件的数的个数。条件与数的大小无关，而只与数的组成有关，因此可以使用数位 DP 的思想求解。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为 int 数组 $a$，其中 $a[1]$ 为最低位，而 $a[len]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, ok, limit)$，答案为 $dfs(len, 0, true)$。

其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `ok` 表示当前数字是否满足题目要求（对于本题，如果数字出现 $[2, 5, 6, 9]$ 则满足）
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1,..9]$，否则，只能选择 $[0,..a[pos]]$。如果 `limit` 为 `true` 且已经取到了能取到的最大值，那么下一个 `limit` 同样为 `true`；如果 `limit` 为 `true` 但是还没有取到最大值，或者 `limit` 为 `false`，那么下一个 `limit` 为 `false`。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log n)$。

相似题目：

-   [233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [902. 最大为 N 的数字组合](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if d[v] == -1:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6]
        return sum(check(i) for i in range(1, n + 1))
```

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(pos, ok, limit):
            if pos <= 0:
                return ok
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i in (0, 1, 8):
                    ans += dfs(pos - 1, ok, limit and i == up)
                if i in (2, 5, 6, 9):
                    ans += dfs(pos - 1, 1, limit and i == up)
            return ans

        a = [0] * 6
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
    private int[] d = new int[] {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
```

```java
class Solution {
    private int[] a = new int[6];
    private int[][] dp = new int[6][2];

    public int rotatedDigits(int n) {
        int len = 0;
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int ok, boolean limit) {
        if (pos <= 0) {
            return ok;
        }
        if (!limit && dp[pos][ok] != -1) {
            return dp[pos][ok];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 || i == 1 || i == 8) {
                ans += dfs(pos - 1, ok, limit && i == up);
            }
            if (i == 2 || i == 5 || i == 6 || i == 9) {
                ans += dfs(pos - 1, 1, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][ok] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const vector<int> d = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }

    bool check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};
```

```cpp
class Solution {
public:
    int a[6];
    int dp[6][2];

    int rotatedDigits(int n) {
        memset(dp, -1, sizeof dp);
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true);
    }

    int dfs(int pos, int ok, bool limit) {
        if (pos <= 0) {
            return ok;
        }
        if (!limit && dp[pos][ok] != -1) {
            return dp[pos][ok];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 || i == 1 || i == 8) {
                ans += dfs(pos - 1, ok, limit && i == up);
            }
            if (i == 2 || i == 5 || i == 6 || i == 9) {
                ans += dfs(pos - 1, 1, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][ok] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func rotatedDigits(n int) int {
	d := []int{0, 1, 5, -1, -1, 2, 9, -1, 8, 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if d[v] == -1 {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}
```

```go
func rotatedDigits(n int) int {
	a := make([]int, 6)
	dp := make([][2]int, 6)
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
	dfs = func(pos, ok int, limit bool) int {
		if pos <= 0 {
			return ok
		}
		if !limit && dp[pos][ok] != -1 {
			return dp[pos][ok]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 || i == 1 || i == 8 {
				ans += dfs(pos-1, ok, limit && i == up)
			}
			if i == 2 || i == 5 || i == 6 || i == 9 {
				ans += dfs(pos-1, 1, limit && i == up)
			}
		}
		if !limit {
			dp[pos][ok] = ans
		}
		return ans
	}

	return dfs(l, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
