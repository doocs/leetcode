# [1444. 切披萨的方案数](https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza)

[English Version](/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>rows x cols</code>&nbsp;大小的矩形披萨和一个整数 <code>k</code>&nbsp;，矩形包含两种字符：&nbsp;<code>&#39;A&#39;</code> （表示苹果）和&nbsp;<code>&#39;.&#39;</code>&nbsp;（表示空白格子）。你需要切披萨 <code>k-1</code> 次，得到&nbsp;<code>k</code>&nbsp;块披萨并送给别人。</p>

<p>切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。</p>

<p>请你返回确保每一块披萨包含&nbsp;<strong>至少</strong>&nbsp;一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/images/ways_to_cut_apple_1.png" style="height: 378px; width: 500px;"></strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;AAA&quot;,&quot;...&quot;], k = 3
<strong>输出：</strong>3 
<strong>解释：</strong>上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;AA.&quot;,&quot;...&quot;], k = 3
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;A..&quot;,&quot;...&quot;], k = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 50</code></li>
	<li><code>rows ==&nbsp;pizza.length</code></li>
	<li><code>cols ==&nbsp;pizza[i].length</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>pizza</code>&nbsp;只包含字符&nbsp;<code>&#39;A&#39;</code>&nbsp;和&nbsp;<code>&#39;.&#39;</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二维前缀和 + 记忆化搜索**

时间复杂度 $O(mnk*(m+n))$。

相似题目：[2312. 卖木头块](/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def ways(self, pizza: List[str], k: int) -> int:
        @cache
        def dfs(i, j, k):
            if k == 0:
                return int(s[-1][-1] - s[-1][j] - s[i][-1] + s[i][j] > 0)
            res = 0
            for x in range(i + 1, m):
                if s[x][-1] - s[x][j] - s[i][-1] + s[i][j]:
                    res += dfs(x, j, k - 1)
            for y in range(j + 1, n):
                if s[-1][y] - s[-1][j] - s[i][y] + s[i][j]:
                    res += dfs(i, y, k - 1)
            return res % mod

        mod = 10**9 + 7
        m, n = len(pizza), len(pizza[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(pizza):
            for j, v in enumerate(row):
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + int(v == 'A')
        return dfs(0, 0, k - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][][] f;
    private int[][] s;
    private int m;
    private int n;

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        s = new int[m + 1][n + 1];
        f = new int[m][n][k];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1]
                    = s[i + 1][j] + s[i][j + 1] - s[i][j] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
                Arrays.fill(f[i][j], -1);
            }
        }
        return dfs(0, 0, k - 1);
    }

    private int dfs(int i, int j, int k) {
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        if (k == 0) {
            return s[m][n] - s[m][j] - s[i][n] + s[i][j] > 0 ? 1 : 0;
        }
        int res = 0;
        for (int x = i + 1; x < m; ++x) {
            if (s[x][n] - s[x][j] - s[i][n] + s[i][j] > 0) {
                res = (res + dfs(x, j, k - 1)) % MOD;
            }
        }
        for (int y = j + 1; y < n; ++y) {
            if (s[m][y] - s[m][j] - s[i][y] + s[i][j] > 0) {
                res = (res + dfs(i, y, k - 1)) % MOD;
            }
        }
        f[i][j][k] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;
    vector<vector<vector<int>>> f;
    vector<vector<int>> s;
    int m;
    int n;

    int ways(vector<string>& pizza, int k) {
        m = pizza.size();
        n = pizza[0].size();
        s.assign(m + 1, vector<int>(n + 1, 0));
        f.assign(m, vector<vector<int>>(n, vector<int>(k, -1)));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + (pizza[i][j] == 'A');
        return dfs(0, 0, k - 1);
    }

    int dfs(int i, int j, int k) {
        if (f[i][j][k] != -1) return f[i][j][k];
        if (k == 0) return s[m][n] - s[m][j] - s[i][n] + s[i][j] > 0;
        int res = 0;
        for (int x = i + 1; x < m; ++x)
            if (s[x][n] - s[x][j] - s[i][n] + s[i][j])
                res = (res + dfs(x, j, k - 1)) % mod;
        for (int y = j + 1; y < n; ++y)
            if (s[m][y] - s[m][j] - s[i][y] + s[i][j])
                res = (res + dfs(i, y, k - 1)) % mod;
        f[i][j][k] = res;
        return res;
    }
};
```

### **Go**

```go
func ways(pizza []string, k int) int {
	mod := int(1e9) + 7
	m, n := len(pizza), len(pizza[0])
	f := make([][][]int, m)
	s := make([][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k)
			for h := range f[i][j] {
				f[i][j][h] = -1
			}
		}
	}
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i, p := range pizza {
		for j, v := range p {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j]
			if v == 'A' {
				s[i+1][j+1]++
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		if k == 0 {
			if s[m][n]-s[m][j]-s[i][n]+s[i][j] > 0 {
				return 1
			}
			return 0
		}
		res := 0
		for x := i + 1; x < m; x++ {
			if s[x][n]-s[x][j]-s[i][n]+s[i][j] > 0 {
				res = (res + dfs(x, j, k-1)) % mod
			}
		}
		for y := j + 1; y < n; y++ {
			if s[m][y]-s[m][j]-s[i][y]+s[i][j] > 0 {
				res = (res + dfs(i, y, k-1)) % mod
			}
		}
		f[i][j][k] = res
		return res
	}
	return dfs(0, 0, k-1)
}
```

### **...**

```

```

<!-- tabs:end -->
