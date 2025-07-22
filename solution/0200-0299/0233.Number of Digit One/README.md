---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md
tags:
    - 递归
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [233. 数字 1 的个数](https://leetcode.cn/problems/number-of-digit-one)

[English Version](/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

这道题实际上是求在给定区间 $[l,..r]$ 中，数字中出现 $1$ 个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

我们首先将数字 $n$ 转化为字符串 $s$。然后我们设计一个函数 $\textit{dfs}(i, \textit{cnt}, \textit{limit})$，其中：

-   数字 $i$ 表示当前搜索到的位置，我们从高位开始搜索，即 $i = 0$ 表示最高位。
-   数字 $\textit{cnt}$ 表示当前数字中 $1$ 出现的次数。
-   布尔值 $\textit{limit}$ 表示当前是否受到上界的限制。

函数的执行过程如下：

如果 $i$ 超过了数字 $n$ 的长度，说明搜索结束，直接返回 $cnt$。如果 $\textit{limit}$ 为真，那么 $up$ 为当前数字的第 $i$ 位，否则 $up = 9$。接下来，我们遍历 $j$ 从 $0$ 到 $up$，对于每一个 $j$：

-   如果 $j$ 等于 $1$，我们将 $cnt$ 加一。
-   递归调用 $\textit{dfs}(i + 1, \textit{cnt}, \textit{limit} \land j = up)$。

答案为 $\textit{dfs}(0, 0, \text{True})$。

时间复杂度 $O(m^2 \times D)$，空间复杂度 $O(m^2)$。其中 $m$ 为数字 $n$ 的长度，而 $D = 10$。

相似题目：

-   [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(i: int, cnt: int, limit: bool) -> int:
            if i >= len(s):
                return cnt
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                ans += dfs(i + 1, cnt + (j == 1), limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private int m;
    private char[] s;
    private Integer[][] f;

    public int countDigitOne(int n) {
        s = String.valueOf(n).toCharArray();
        m = s.length;
        f = new Integer[m][m];
        return dfs(0, 0, true);
    }

    private int dfs(int i, int cnt, boolean limit) {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i][cnt] != null) {
            return f[i][cnt];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            ans += dfs(i + 1, cnt + (j == 1 ? 1 : 0), limit && j == up);
        }
        if (!limit) {
            f[i][cnt] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDigitOne(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][m];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int cnt, bool limit) -> int {
            if (i >= m) {
                return cnt;
            }
            if (!limit && f[i][cnt] != -1) {
                return f[i][cnt];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                ans += dfs(i + 1, cnt + (j == 1), limit && j == up);
            }
            if (!limit) {
                f[i][cnt] = ans;
            }
            return ans;
        };
        return dfs(0, 0, true);
    }
};
```

#### Go

```go
func countDigitOne(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, cnt int, limit bool) int
	dfs = func(i, cnt int, limit bool) int {
		if i >= m {
			return cnt
		}
		if !limit && f[i][cnt] != -1 {
			return f[i][cnt]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			t := 0
			if j == 1 {
				t = 1
			}
			ans += dfs(i+1, cnt+t, limit && j == up)
		}
		if !limit {
			f[i][cnt] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function countDigitOne(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(m).fill(-1));
    const dfs = (i: number, cnt: number, limit: boolean): number => {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i][cnt] !== -1) {
            return f[i][cnt];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            ans += dfs(i + 1, cnt + (j === 1 ? 1 : 0), limit && j === up);
        }
        if (!limit) {
            f[i][cnt] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
```

#### C#

```cs
public class Solution {
    private int m;
    private char[] s;
    private int?[,] f;

    public int CountDigitOne(int n) {
        s = n.ToString().ToCharArray();
        m = s.Length;
        f = new int?[m, m];
        return Dfs(0, 0, true);
    }

    private int Dfs(int i, int cnt, bool limit) {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i, cnt] != null) {
            return f[i, cnt].Value;
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            ans += Dfs(i + 1, cnt + (j == 1 ? 1 : 0), limit && j == up);
        }
        if (!limit) {
            f[i, cnt] = ans;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
