# [1130. Minimum Cost Tree From Leaf Values](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values)

[中文文档](/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/README.md)

## Description

<p>Given an array <code>arr</code> of positive integers, consider all binary trees such that:</p>

<ul>
	<li>Each node has either <code>0</code> or <code>2</code> children;</li>
	<li>The values of <code>arr</code> correspond to the values of each <strong>leaf</strong> in an in-order traversal of the tree.</li>
	<li>The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.</li>
</ul>

<p>Among all possible binary trees considered, return <em>the smallest possible sum of the values of each non-leaf node</em>. It is guaranteed this sum fits into a <strong>32-bit</strong> integer.</p>

<p>A node is a <strong>leaf</strong> if and only if it has zero children.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree1.jpg" style="width: 500px; height: 169px;" />
<pre>
<strong>Input:</strong> arr = [6,2,4]
<strong>Output:</strong> 32
<strong>Explanation:</strong> There are two possible trees shown.
The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree2.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>Input:</strong> arr = [4,11]
<strong>Output:</strong> 44
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>It is guaranteed that the answer fits into a <strong>32-bit</strong> signed integer (i.e., it is less than 2<sup>31</sup>).</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
