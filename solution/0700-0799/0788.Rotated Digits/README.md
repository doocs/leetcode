---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0788.Rotated%20Digits/README.md
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [788. 旋转数字](https://leetcode.cn/problems/rotated-digits)

[English Version](/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接枚举

一种直观且有效的思路是，直接枚举 $[1,2,..n]$ 中的每个数，判断其是否为好数，若为好数，则答案加一。

那么题目的重点转化为如何判断一个数字 $x$ 是否为好数。判断的逻辑如下：

我们先用一个长度为 $10$ 的数组 $d$ 记录每个有效数字对应的旋转数字，在这道题中，有效数字有 $[0, 1, 8, 2, 5, 6, 9]$，分别对应旋转数字 $[0, 1, 8, 5, 2, 9, 6]$。如果不是有效数字，我们将对应的旋转数字设为 $-1$。

然后遍历数字 $x$ 的每一位数字 $v$，如果 $v$ 不是有效数字，说明 $x$ 不是好数，直接返回 $\textit{false}$。否则，我们将数字 $v$ 对应的旋转数字 $d[v]$ 加入到 $y$ 中。最后，判断 $x$ 和 $y$ 是否相等，若不相等，则说明 $x$ 是好数，返回 $\textit{true}$。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为题目给定的数字。空间复杂度 $O(1)$。

相似题目：

-   [1056. 易混淆数](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1056.Confusing%20Number/README.md)

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    int rotatedDigits(int n) {
        int d[10] = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};
        auto check = [&](int x) -> bool {
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
        };
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }
};
```

#### Go

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

#### TypeScript

```ts
function rotatedDigits(n: number): number {
    const d: number[] = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6];
    const check = (x: number): boolean => {
        let y = 0;
        let t = x;
        let k = 1;

        while (t > 0) {
            const v = t % 10;
            if (d[v] === -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t = Math.floor(t / 10);
        }
        return x !== y;
    };
    return Array.from({ length: n }, (_, i) => i + 1).filter(check).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数位 DP

方法一的做法足以通过本题，但时间复杂度较高。如果题目的数据范围达到 $10^9$ 级别，则方法一的做法会超出时间限制。

这道题实际上是求在给定区间 $[l,..r]$ 中，满足条件的数的个数。条件与数的大小无关，而只与数的组成有关，因此可以使用数位 DP 的思想求解。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

我们将数字 $n$ 转为字符串 $s$。然后定义函数 $\textit{dfs}(i, \textit{ok}, \textit{limit})$，其中 $i$ 表示数字的位数，数字 $\textit{ok}$ 表示当前数字是否满足题目要求，布尔值 $\textit{limit}$ 表示可填的数字的限制。

函数的执行逻辑如下：

如果 $i$ 大于等于字符串 $s$ 的长度，返回 $\textit{ok}$；

否则，我们获取当前位的数字 $up$，如果 $\textit{limit}$ 为 $\textit{true}$，则 $up$ 为当前位的数字，否则 $up$ 为 $9$；

接下来，我们遍历 $[0,..up]$，如果 $j$ 是有效数字 $[0, 1, 8]$，则递归调用 $\textit{dfs}(i + 1, \textit{ok}, \textit{limit} \land j = \textit{up})$；如果 $j$ 是有效数字 $[2, 5, 6, 9]$，则递归调用 $\textit{dfs}(i + 1, 1, \textit{limit} \land j = \textit{up})$；将所有递归调用的结果累加并返回。

时间复杂度 $O(\log n \times D)$，空间复杂度 $O(\log n)$。其中 $D = 10$。

相似题目：

-   [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(i: int, ok: int, limit: bool) -> int:
            if i >= len(s):
                return ok
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if j in (0, 1, 8):
                    ans += dfs(i + 1, ok, limit and j == up)
                elif j in (2, 5, 6, 9):
                    ans += dfs(i + 1, 1, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int rotatedDigits(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int i, int ok, boolean limit) {
        if (i >= s.length) {
            return ok;
        }
        if (!limit && f[i][ok] != null) {
            return f[i][ok];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 0 || j == 1 || j == 8) {
                ans += dfs(i + 1, ok, limit && j == up);
            } else if (j == 2 || j == 5 || j == 6 || j == 9) {
                ans += dfs(i + 1, 1, limit && j == up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rotatedDigits(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int ok, bool limit) -> int {
            if (i >= m) {
                return ok;
            }
            if (!limit && f[i][ok] != -1) {
                return f[i][ok];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j == 0 || j == 1 || j == 8) {
                    ans += dfs(dfs, i + 1, ok, limit && j == up);
                } else if (j == 2 || j == 5 || j == 6 || j == 9) {
                    ans += dfs(dfs, i + 1, 1, limit && j == up);
                }
            }
            if (!limit) {
                f[i][ok] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, 0, true);
    }
};
```

#### Go

```go
func rotatedDigits(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, ok int, limit bool) int
	dfs = func(i, ok int, limit bool) int {
		if i >= m {
			return ok
		}
		if !limit && f[i][ok] != -1 {
			return f[i][ok]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 0 || j == 1 || j == 8 {
				ans += dfs(i+1, ok, limit && j == up)
			} else if j == 2 || j == 5 || j == 6 || j == 9 {
				ans += dfs(i+1, 1, limit && j == up)
			}
		}
		if !limit {
			f[i][ok] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function rotatedDigits(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, ok: number, limit: boolean): number => {
        if (i >= m) {
            return ok;
        }
        if (!limit && f[i][ok] !== -1) {
            return f[i][ok];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ([0, 1, 8].includes(j)) {
                ans += dfs(i + 1, ok, limit && j === up);
            } else if ([2, 5, 6, 9].includes(j)) {
                ans += dfs(i + 1, 1, limit && j === up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
