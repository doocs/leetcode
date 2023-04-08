# [1130. 叶值的最小代价生成树](https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values)

[English Version](/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>arr</code>，考虑所有满足以下条件的二叉树：</p>

<ul>
	<li>每个节点都有 0 个或是 2 个子节点。</li>
	<li>数组&nbsp;<code>arr</code>&nbsp;中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）</li>
	<li>每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。</li>
</ul>

<p>在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个&nbsp;32 位整数。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>arr = [6,2,4]
<strong>输出：</strong>32
<strong>解释：</strong>
有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>答案保证是一个 32 位带符号整数，即小于&nbsp;<code>2^31</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

根据题目描述，数组 $arr$ 中的值与树的中序遍历中每个叶节点的值一一对应，因此可以将数组 $arr$ 中的值看作是树的叶节点，我们可以将数组划分为左右两个子数组，分别对应树的左右子树，递归地每个子树的所有非叶子节点的值的最小可能总和。

我们设计一个函数 $dfs(i, j)$，表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有叶节点的值的最小可能总和，那么答案就是 $dfs(0, n - 1)$，其中 $n$ 为数组 $arr$ 的长度。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $i = j$，说明数组 $arr$ 中只有一个元素，因此 $dfs(i, j) = 0$。
-   否则，我们枚举 $k \in [i, j - 1]$，将数组 $arr$ 划分为两个子数组 $arr[i \cdots k]$ 和 $arr[k + 1 \cdots j]$，对于每个 $k$，我们计算 $dfs(i, k)$ 和 $dfs(k + 1, j)$，其中 $dfs(i, k)$ 表示数组 $arr$ 中下标范围 $[i, k]$ 内的所有叶节点的值的最小可能总和，而 $dfs(k + 1, j)$ 表示数组 $arr$ 中下标范围 $[k + 1, j]$ 内的所有叶节点的值的最小可能总和，那么 $dfs(i, j) = \min_{i \leq k < j} \{dfs(i, k) + dfs(k + 1, j) + \max_{i \leq t \leq k} \{arr[t]\} \max_{k < t \leq j} \{arr[t]\}\}$。

上述递归过程中，我们可以使用记忆化搜索的方法进行优化，避免重复计算。

最后，我们返回 $dfs(0, n - 1)$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $arr$ 的长度。

**方法二：动态规划**

我们可以将方法一中的记忆化搜索改为动态规划的方式进行求解。

定义 $f[i][j]$ 表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有叶节点的值的最小可能总和，而 $g[i][j]$ 表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有叶节点的最大值，那么 $f[i][j] = \min_{i \leq k < j} \{f[i][k] + f[k + 1][j] + g[i][k] \cdot g[k + 1][j]\}$，其中 $g[i][j] = \max_{i \leq k \leq j} \{arr[k]\}$。

最后，我们返回 $f[0][n - 1]$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, j: int):
            if i == j:
                return 0, arr[i]
            s, mx = inf, -1
            for k in range(i, j):
                s1, mx1 = dfs(i, k)
                s2, mx2 = dfs(k + 1, j)
                t = s1 + s2 + mx1 * mx2
                if s > t:
                    s = t
                    mx = max(mx1, mx2)
            return s, mx

        return dfs(0, len(arr) - 1)[0]
```

```python
class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for i in range(n):
            g[i][i] = arr[i]
            for j in range(i + 1, n):
                g[i][j] = max(g[i][j - 1], arr[j])
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = inf
                for k in range(i, j):
                    f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j])
        return f[0][n - 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[][] f;
    private int[][] g;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        f = new Integer[n][n];
        g = new int[n][n];
        for (int i = 0; i < n; i++) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 1 << 30;
        for (int k = i; k < j; k++) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
        }
        return f[i][j] = ans;
    }
}
```

```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
                }
            }
        }
        return f[0][n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mctFromLeafValues(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        int g[n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = max(g[i][j - 1], arr[j]);
            }
        }
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i == j) {
                return 0;
            }
            if (f[i][j] > 0) {
                return f[i][j];
            }
            int ans = 1 << 30;
            for (int k = i; k < j; ++k) {
                ans = min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
```

```cpp
class Solution {
public:
    int mctFromLeafValues(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        int g[n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = max(g[i][j - 1], arr[j]);
            }
        }
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i; k < j; ++k) {
                    f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
                }
            }
        }
        return f[0][n - 1];
    }
};
```

### **Go**

```go
func mctFromLeafValues(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range g {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
		g[i][i] = arr[i]
		for j := i + 1; j < n; j++ {
			g[i][j] = max(g[i][j-1], arr[j])
		}
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i == j {
			return 0
		}
		if f[i][j] > 0 {
			return f[i][j]
		}
		f[i][j] = 1 << 30
		for k := i; k < j; k++ {
			f[i][j] = min(f[i][j], dfs(i, k)+dfs(k+1, j)+g[i][k]*g[k+1][j])
		}
		return f[i][j]
	}
	return dfs(0, n-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func mctFromLeafValues(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range g {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
		g[i][i] = arr[i]
		for j := i + 1; j < n; j++ {
			g[i][j] = max(g[i][j-1], arr[j])
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = 1 << 30
			for k := i; k < j; k++ {
				f[i][j] = min(f[i][j], f[i][k]+f[k+1][j]+g[i][k]*g[k+1][j])
			}
		}
	}
	return f[0][n-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
