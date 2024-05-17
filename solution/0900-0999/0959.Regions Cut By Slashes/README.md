---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [959. 由斜杠划分区域](https://leetcode.cn/problems/regions-cut-by-slashes)

[English Version](/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在由 <code>1 x 1</code> 方格组成的 <code>n&nbsp;x n</code>&nbsp;网格&nbsp;<code>grid</code> 中，每个 <code>1 x 1</code>&nbsp;方块由 <code>'/'</code>、<code>'\'</code> 或空格构成。这些字符会将方块划分为一些共边的区域。</p>

<p>给定网格&nbsp;<code>grid</code>&nbsp;表示为一个字符串数组，返回 <em>区域的数量</em> 。</p>

<p>请注意，反斜杠字符是转义的，因此&nbsp;<code>'\'</code> 用 <code>'\\'</code>&nbsp;表示。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/1.png" style="height: 200px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>grid = [" /","/ "]
<strong>输出：</strong>2</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/2.png" style="height: 198px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>grid = [" /","  "]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/4.png" style="height: 200px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>grid = ["/\\","\\/"]
<strong>输出：</strong>5
<strong>解释：</strong>回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>grid[i][j]</code> 是&nbsp;<code>'/'</code>、<code>'\'</code>、或&nbsp;<code>' '</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                p[pa] = pb
                nonlocal size
                size -= 1

        n = len(grid)
        size = n * n * 4
        p = list(range(size))
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                k = i * n + j
                if i < n - 1:
                    union(4 * k + 2, (k + n) * 4)
                if j < n - 1:
                    union(4 * k + 1, (k + 1) * 4 + 3)
                if v == '/':
                    union(4 * k, 4 * k + 3)
                    union(4 * k + 1, 4 * k + 2)
                elif v == '\\':
                    union(4 * k, 4 * k + 1)
                    union(4 * k + 2, 4 * k + 3)
                else:
                    union(4 * k, 4 * k + 1)
                    union(4 * k + 1, 4 * k + 2)
                    union(4 * k + 2, 4 * k + 3)
        return size
```

#### Java

```java
class Solution {
    private int[] p;
    private int size;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        size = n * n * 4;
        p = new int[size];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i * n + j;
                if (i < n - 1) {
                    union(4 * k + 2, (k + n) * 4);
                }
                if (j < n - 1) {
                    union(4 * k + 1, (k + 1) * 4 + 3);
                }
                char v = grid[i].charAt(j);
                if (v == '/') {
                    union(4 * k, 4 * k + 3);
                    union(4 * k + 1, 4 * k + 2);
                } else if (v == '\\') {
                    union(4 * k, 4 * k + 1);
                    union(4 * k + 2, 4 * k + 3);
                } else {
                    union(4 * k, 4 * k + 1);
                    union(4 * k + 1, 4 * k + 2);
                    union(4 * k + 2, 4 * k + 3);
                }
            }
        }
        return size;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        --size;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;
    int size;

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        size = n * n * 4;
        p.resize(size);
        for (int i = 0; i < size; ++i) p[i] = i;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i * n + j;
                if (i < n - 1) merge(4 * k + 2, (k + n) * 4);
                if (j < n - 1) merge(4 * k + 1, (k + 1) * 4 + 3);
                char v = grid[i][j];
                if (v == '/') {
                    merge(4 * k, 4 * k + 3);
                    merge(4 * k + 1, 4 * k + 2);
                } else if (v == '\\') {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 2, 4 * k + 3);
                } else {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 1, 4 * k + 2);
                    merge(4 * k + 2, 4 * k + 3);
                }
            }
        }
        return size;
    }

    void merge(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        --size;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func regionsBySlashes(grid []string) int {
	n := len(grid)
	size := n * n * 4
	p := make([]int, size)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size--
	}
	for i, row := range grid {
		for j, v := range row {
			k := i*n + j
			if i < n-1 {
				union(4*k+2, (k+n)*4)
			}
			if j < n-1 {
				union(4*k+1, (k+1)*4+3)
			}
			if v == '/' {
				union(4*k, 4*k+3)
				union(4*k+1, 4*k+2)
			} else if v == '\\' {
				union(4*k, 4*k+1)
				union(4*k+2, 4*k+3)
			} else {
				union(4*k, 4*k+1)
				union(4*k+1, 4*k+2)
				union(4*k+2, 4*k+3)
			}
		}
	}
	return size
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
