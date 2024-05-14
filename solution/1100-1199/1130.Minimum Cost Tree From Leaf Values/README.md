# [1130. 叶值的最小代价生成树](https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values)

[English Version](/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/README_EN.md)

<!-- tags:栈,贪心,数组,动态规划,单调栈 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>arr</code>，考虑所有满足以下条件的二叉树：</p>

<ul>
	<li>每个节点都有 <code>0</code> 个或是 <code>2</code> 个子节点。</li>
	<li>数组&nbsp;<code>arr</code>&nbsp;中的值与树的中序遍历中每个叶节点的值一一对应。</li>
	<li>每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。</li>
</ul>

<p>在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个&nbsp;32 位整数。</p>

<p>如果一个节点有 0 个子节点，那么该节点为叶节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree1.jpg" style="width: 500px; height: 169px;" />
<pre>
<strong>输入：</strong>arr = [6,2,4]
<strong>输出：</strong>32
<strong>解释：</strong>有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。 
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree2.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>输入：</strong>arr = [4,11]
<strong>输出：</strong>44
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>答案保证是一个 32 位带符号整数，即小于&nbsp;<code>2<sup>31</sup></code> 。</li>
</ul>

## 解法

### 方法一：记忆化搜索

根据题目描述，数组 $arr$ 中的值与树的中序遍历中每个叶节点的值一一对应，我们可以将数组划分为左右两个非空子数组，分别对应树的左右子树，递归地求解每个子树的所有非叶节点的值的最小可能总和。

我们设计一个函数 $dfs(i, j)$，表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有非叶节点的值的最小可能总和，那么答案就是 $dfs(0, n - 1)$，其中 $n$ 为数组 $arr$ 的长度。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $i = j$，说明数组 $arr[i..j]$ 中只有一个元素，没有非叶节点，因此 $dfs(i, j) = 0$。
-   否则，我们枚举 $k \in [i, j - 1]$，将数组 $arr$ 划分为两个子数组 $arr[i \cdots k]$ 和 $arr[k + 1 \cdots j]$，对于每个 $k$，我们递归计算 $dfs(i, k)$ 和 $dfs(k + 1, j)$，其中 $dfs(i, k)$ 表示数组 $arr$ 中下标范围 $[i, k]$ 内的所有非叶节点的值的最小可能总和，而 $dfs(k + 1, j)$ 表示数组 $arr$ 中下标范围 $[k + 1, j]$ 内的所有非叶节点的值的最小可能总和，那么 $dfs(i, j) = \min_{i \leq k < j} \{dfs(i, k) + dfs(k + 1, j) + \max_{i \leq t \leq k} \{arr[t]\} \max_{k < t \leq j} \{arr[t]\}\}$。

综上所述，我们可以得到：

$$
dfs(i, j) = \begin{cases}
0, & \text{if } i = j \\
\min_{i \leq k < j} \{dfs(i, k) + dfs(k + 1, j) + \max_{i \leq t \leq k} \{arr[t]\} \max_{k < t \leq j} \{arr[t]\}\}, & \text{if } i < j
\end{cases}
$$

上述递归过程中，我们可以使用记忆化搜索的方法，避免重复计算。另外，我们还可以使用数组 $g$ 记录数组 $arr$ 中下标范围 $[i, j]$ 内的所有叶节点的最大值，那么 $dfs(i, j)$ 的计算过程可以优化为：

$$
dfs(i, j) = \begin{cases}
0, & \text{if } i = j \\
\min_{i \leq k < j} \{dfs(i, k) + dfs(k + 1, j) + g[i][k] \cdot g[k + 1][j]\}, & \text{if } i < j
\end{cases}
$$

最后，我们返回 $dfs(0, n - 1)$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> Tuple:
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

```java
class Solution {
    private Integer[][] f;
    private int[][] g;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        f = new Integer[n][n];
        g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
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

```cpp
class Solution {
public:
    int mctFromLeafValues(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        int g[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
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
```

```ts
function mctFromLeafValues(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const g: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        g[i][i] = arr[i];
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = Math.max(g[i][j - 1], arr[j]);
        }
    }
    const dfs = (i: number, j: number): number => {
        if (i === j) {
            return 0;
        }
        if (f[i][j] > 0) {
            return f[i][j];
        }
        let ans = 1 << 30;
        for (let k = i; k < j; ++k) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
        }
        return (f[i][j] = ans);
    };
    return dfs(0, n - 1);
}
```

<!-- tabs:end -->

### 方法二：动态规划

我们可以将方法一中的记忆化搜索改为动态规划的方式进行求解。

定义 $f[i][j]$ 表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有非叶节点的值的最小可能总和，而 $g[i][j]$ 表示数组 $arr$ 中下标范围 $[i, j]$ 内的所有叶节点的最大值，那么状态转移方程为：

$$
f[i][j] = \begin{cases}
0, & \text{if } i = j \\
\min_{i \leq k < j} \{f[i][k] + f[k + 1][j] + g[i][k] \cdot g[k + 1][j]\}, & \text{if } i < j
\end{cases}
$$

最后，我们返回 $f[0][n - 1]$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i == j:
                return 0
            return min(
                dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j] for k in range(i, j)
            )

        n = len(arr)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            g[i][i] = arr[i]
            for j in range(i + 1, n):
                g[i][j] = max(g[i][j - 1], arr[j])
        return dfs(0, n - 1)
```

```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
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

```cpp
class Solution {
public:
    int mctFromLeafValues(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        int g[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = max(g[i][j - 1], arr[j]);
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

```go
func mctFromLeafValues(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range g {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		g[i][i] = arr[i]
		for j := i + 1; j < n; j++ {
			g[i][j] = max(g[i][j-1], arr[j])
			f[i][j] = 1 << 30
			for k := i; k < j; k++ {
				f[i][j] = min(f[i][j], f[i][k]+f[k+1][j]+g[i][k]*g[k+1][j])
			}
		}
	}
	return f[0][n-1]
}
```

```ts
function mctFromLeafValues(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const g: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        g[i][i] = arr[i];
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = Math.max(g[i][j - 1], arr[j]);
            f[i][j] = 1 << 30;
            for (let k = i; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
            }
        }
    }
    return f[0][n - 1];
}
```

<!-- tabs:end -->

### 方法三

<!-- tabs:start -->

```python
class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            g[i][i] = arr[i]
            for j in range(i + 1, n):
                g[i][j] = max(g[i][j - 1], arr[j])
                f[i][j] = min(
                    f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j] for k in range(i, j)
                )
        return f[0][n - 1]
```

<!-- tabs:end -->

<!-- end -->
