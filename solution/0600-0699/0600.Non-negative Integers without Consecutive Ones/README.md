---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md
tags:
    - 动态规划
---

<!-- problem:start -->

# [600. 不含连续1的非负整数](https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones)

[English Version](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>n</code> ，请你统计在&nbsp;<code>[0, n]</code> 范围的非负整数中，有多少个整数的二进制表示中不存在 <strong>连续的 1 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 5
<strong>输出:</strong> 5
<strong>解释:</strong> 
下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> 2
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 3
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

这道题实际上是求在给定区间 $[l,..r]$ 中，数字的二进制表示不包含连续的 $1$ 的个数。个数与数的位数以及每个二进制位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[0,..r]$ 然后再减去 $[0,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=0}^{r} ans_i -  \sum_{i=0}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[0,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。基本步骤如下：

1. 将数字 $n$ 转为二进制字符串 $s$；
1. 根据题目信息，设计函数 $\textit{dfs}()$，对于本题，我们定义 $\textit{dfs}(\textit{pos}, \textit{pre}, \textit{limit})$，答案为 $\textit{dfs}(\textit{0}, 0, \textit{true})$。

其中：

-   `pos` 表示数字的位数，我们从数字的最高位开始，即二进制字符串的首字符；
-   `pre` 表示当前数字二进制位上的数字，对于本题，`pre` 的初始值为 `0`；
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1]$，否则，只能选择 $[0,..s[\textit{pos}]]$。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为题目给定的数字。

相似题目：

-   [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(pos: int, pre: int, limit: bool) -> int:
            if pos == len(s):
                return 1
            up = int(s[pos]) if limit else 1
            ans = 0
            for i in range(up + 1):
                if pre == 1 and i == 1:
                    continue
                ans += dfs(pos + 1, i, limit and i == up)
            return ans

        s = bin(n)[2:]
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int pre, boolean limit) {
        if (pos >= s.length) {
            return 1;
        }
        if (!limit && f[pos][pre] != null) {
            return f[pos][pre];
        }
        int up = limit ? s[pos] - '0' : 1;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos + 1, i, limit && i == up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findIntegers(int n) {
        string s = bitset<32>(n).to_string();
        s = s.substr(s.find('1'));
        int m = s.size();
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int pos, int pre, bool limit) -> int {
            if (pos >= m) {
                return 1;
            }
            if (!limit && f[pos][pre] != -1) {
                return f[pos][pre];
            }
            int up = limit ? s[pos] - '0' : 1;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (!(pre == 1 && i == 1)) {
                    ans += dfs(dfs, pos + 1, i, limit && i == up);
                }
            }
            if (!limit) {
                f[pos][pre] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, 0, true);
    }
};
```

#### Go

```go
func findIntegers(n int) int {
	s := strconv.FormatInt(int64(n), 2)
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = []int{-1, -1}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos int, pre int, limit bool) int {
		if pos >= m {
			return 1
		}
		if !limit && f[pos][pre] != -1 {
			return f[pos][pre]
		}
		up := 1
		if limit {
			up = int(s[pos] - '0')
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if !(pre == 1 && i == 1) {
				ans += dfs(pos+1, i, limit && i == up)
			}
		}
		if !limit {
			f[pos][pre] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function findIntegers(n: number): number {
    const s = n.toString(2);
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => [-1, -1]);

    function dfs(pos: number, pre: number, limit: boolean): number {
        if (pos >= m) {
            return 1;
        }
        if (!limit && f[pos][pre] !== -1) {
            return f[pos][pre];
        }
        const up = limit ? parseInt(s[pos]) : 1;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if (!(pre === 1 && i === 1)) {
                ans += dfs(pos + 1, i, limit && i === up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }

    return dfs(0, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
