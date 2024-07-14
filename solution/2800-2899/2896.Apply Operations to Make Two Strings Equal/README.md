---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2896.Apply%20Operations%20to%20Make%20Two%20Strings%20Equal/README.md
rating: 2172
source: 第 366 场周赛 Q3
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2896. 执行操作使两个字符串相等](https://leetcode.cn/problems/apply-operations-to-make-two-strings-equal)

[English Version](/solution/2800-2899/2896.Apply%20Operations%20to%20Make%20Two%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的二进制字符串&nbsp;<code>s1</code> 和&nbsp;<code>s2</code>&nbsp;，两个字符串的长度都是&nbsp;<code>n</code>&nbsp;，再给你一个正整数&nbsp;<code>x</code>&nbsp;。</p>

<p>你可以对字符串 <code>s1</code>&nbsp;执行以下操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>选择两个下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;，将&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[j]</code>&nbsp;都反转，操作的代价为&nbsp;<code>x</code>&nbsp;。</li>
	<li>选择满足 <code>i &lt; n - 1</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，反转&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[i + 1]</code>&nbsp;，操作的代价为&nbsp;<code>1</code>&nbsp;。</li>
</ul>

<p>请你返回使字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;相等的&nbsp;<strong>最小</strong>&nbsp;操作代价之和，如果无法让二者相等，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;，反转字符的意思是将&nbsp;<code>0</code>&nbsp;变成&nbsp;<code>1</code>&nbsp;，或者 <code>1</code>&nbsp;变成 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s1 = "1100011000", s2 = "0101001010", x = 2
<b>输出：</b>4
<b>解释：</b>我们可以执行以下操作：
- 选择 i = 3 执行第二个操作。结果字符串是 s1 = "110<em><strong>11</strong></em>11000" 。
- 选择 i = 4 执行第二个操作。结果字符串是 s1 = "1101<em><strong>00</strong></em>1000" 。
- 选择 i = 0 和 j = 8 ，执行第一个操作。结果字符串是 s1 = "<em><strong>0</strong></em>1010010<em><strong>1</strong></em>0" = s2 。
总代价是 1 + 1 + 2 = 4 。这是最小代价和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s1 = "10110", s2 = "00011", x = 4
<b>输出：</b>-1
<b>解释：</b>无法使两个字符串相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n, x &lt;= 500</code></li>
	<li><code>s1</code> 和&nbsp;<code>s2</code>&nbsp;只包含字符&nbsp;<code>'0'</code> 和&nbsp;<code>'1'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们注意到，由于每次操作都是反转两个字符，因此，如果不同的字符个数为奇数，那么无法使两个字符串相等，直接返回 $-1$。否则，我们将两个字符串中不同的字符的下标存入数组 $idx$ 中，记数组长度为 $m$。

接下来，我们设计一个函数 $dfs(i, j)$，表示将 $idx[i..j]$ 中的字符反转的最小操作代价。答案即为 $dfs(0, m - 1)$。

函数 $dfs(i, j)$ 的计算过程如下：

如果 $i \gt j$，那么不需要进行任何操作，返回 $0$；

否则，我们考虑对区间 $[i, j]$ 的端点进行操作：

-   如果我们对端点 $i$ 进行第一种操作，由于代价 $x$ 已经固定，因此，我们最优的选择是将 $idx[i]$ 和 $idx[j]$ 反转，然后递归计算 $dfs(i + 1, j - 1)$，总代价为 $dfs(i + 1, j - 1) + x$；
-   如果我们对端点 $i$ 进行第二种操作，那么我们需要将 $[idx[i]..idx[i + 1]]$ 中的字符全部反转，然后递归计算 $dfs(i + 2, j)$，总代价为 $dfs(i + 2, j) + idx[i + 1] - idx[i]$；
-   如果我们对端点 $j$ 进行第二种操作，那么我们需要将 $[idx[j - 1]..idx[j]]$ 中的字符全部反转，然后递归计算 $dfs(i, j - 2)$，总代价为 $dfs(i, j - 2) + idx[j] - idx[j - 1]$。

我们取上述三种操作的最小值，即为 $dfs(i, j)$ 的值。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, s1: str, s2: str, x: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            a = dfs(i + 1, j - 1) + x
            b = dfs(i + 2, j) + idx[i + 1] - idx[i]
            c = dfs(i, j - 2) + idx[j] - idx[j - 1]
            return min(a, b, c)

        n = len(s1)
        idx = [i for i in range(n) if s1[i] != s2[i]]
        m = len(idx)
        if m & 1:
            return -1
        return dfs(0, m - 1)
```

#### Java

```java
class Solution {
    private List<Integer> idx = new ArrayList<>();
    private Integer[][] f;
    private int x;

    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                idx.add(i);
            }
        }
        int m = idx.size();
        if (m % 2 == 1) {
            return -1;
        }
        this.x = x;
        f = new Integer[m][m];
        return dfs(0, m - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + x;
        f[i][j] = Math.min(f[i][j], dfs(i + 2, j) + idx.get(i + 1) - idx.get(i));
        f[i][j] = Math.min(f[i][j], dfs(i, j - 2) + idx.get(j) - idx.get(j - 1));
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(string s1, string s2, int x) {
        vector<int> idx;
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] != s2[i]) {
                idx.push_back(i);
            }
        }
        int m = idx.size();
        if (m & 1) {
            return -1;
        }
        if (m == 0) {
            return 0;
        }
        int f[m][m];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = min({dfs(i + 1, j - 1) + x, dfs(i + 2, j) + idx[i + 1] - idx[i], dfs(i, j - 2) + idx[j] - idx[j - 1]});
            return f[i][j];
        };
        return dfs(0, m - 1);
    }
};
```

#### Go

```go
func minOperations(s1 string, s2 string, x int) int {
	idx := []int{}
	for i := range s1 {
		if s1[i] != s2[i] {
			idx = append(idx, i)
		}
	}
	m := len(idx)
	if m&1 == 1 {
		return -1
	}
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = dfs(i+1, j-1) + x
		f[i][j] = min(f[i][j], dfs(i+2, j)+idx[i+1]-idx[i])
		f[i][j] = min(f[i][j], dfs(i, j-2)+idx[j]-idx[j-1])
		return f[i][j]
	}
	return dfs(0, m-1)
}
```

#### TypeScript

```ts
function minOperations(s1: string, s2: string, x: number): number {
    const idx: number[] = [];
    for (let i = 0; i < s1.length; ++i) {
        if (s1[i] !== s2[i]) {
            idx.push(i);
        }
    }
    const m = idx.length;
    if (m % 2 === 1) {
        return -1;
    }
    if (m === 0) {
        return 0;
    }
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: m }, () => -1));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + x;
        f[i][j] = Math.min(f[i][j], dfs(i + 2, j) + idx[i + 1] - idx[i]);
        f[i][j] = Math.min(f[i][j], dfs(i, j - 2) + idx[j] - idx[j - 1]);
        return f[i][j];
    };
    return dfs(0, m - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        int inf = 50_000;
        int one = inf, two = inf, last = inf;
        int done = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                one = Math.min(one, last);
                last = last + 1;
                two = two + 1;
                continue;
            }
            if (done < n) {
                one = Math.min(two + 1, done + x);
                last = Math.min(two + x, done);
                done = two = inf;
                continue;
            }
            done = Math.min(one + x, last + 1);
            two = one;
            one = last = inf;
            continue;
        }
        return done == inf ? -1 : done;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
