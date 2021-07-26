# [807. 保持城市天际线](https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline)

[English Version](/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在二维数组<code>grid</code>中，<code>grid[i][j]</code>代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。</p>

<p>最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的&ldquo;天际线&rdquo;必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。</p>

<p>建筑物高度可以增加的最大总和是多少？</p>

<pre>
<strong>例子：</strong>
<strong>输入：</strong> grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
<strong>输出：</strong> 35
<strong>解释：</strong> 
The grid is:
[ [3, 0, 8, 4], 
  [2, 4, 5, 7],
  [9, 2, 6, 3],
  [0, 3, 1, 0] ]

从数组竖直方向（即顶部，底部）看&ldquo;天际线&rdquo;是：[9, 4, 8, 7]
从水平水平方向（即左侧，右侧）看&ldquo;天际线&rdquo;是：[8, 7, 9, 3]

在不影响天际线的情况下对建筑物进行增高后，新数组如下：

gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
</pre>

<p><strong>说明: </strong></p>

<ul>
	<li><code>1 &lt; grid.length = grid[0].length &lt;= 50</code>。</li>
	<li>&nbsp;<code>grid[i][j]</code> 的高度范围是： <code>[0, 100]</code>。</li>
	<li>一座建筑物占据一个<code>grid[i][j]</code>：换言之，它们是 <code>1 x 1 x grid[i][j]</code> 的长方体。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先求出东西方向 we 每一行的最大值、南北方向 ns 每一列的最大值。

然后遍历二维数组 grid，对于每个元素，能增加的最大高度是 `min(we[i], ns[j]) - grid[i][j]`。累加所有元素能增加的最大高度即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        we = [max(item) for item in grid]
        ns = [max([grid[i][j] for i in range(m)]) for j in range(n)]
        res = 0
        for i in range(m):
            for j in range(n):
                res += min(we[i], ns[j]) - grid[i][j]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] we = new int[m];
        int[] ns = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                we[i] = Math.max(we[i], grid[i][j]);
                ns[j] = Math.max(ns[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += Math.min(we[i], ns[j]) - grid[i][j];
            }
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function maxIncreaseKeepingSkyline(grid: number[][]): number {
    let rows = grid.map(arr => Math.max(...arr)),
    cols = [];
    let m = grid.length, n = grid[0].length;
    for (let j = 0; j < n; ++j) {
        cols[j] = grid[0][j]
        for (let i = 1; i < m; ++i) {
            cols[j] = Math.max(cols[j], grid[i][j]);
        }
    }

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rows[i], cols[j]) - grid[i][j];
        }
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> we(m, 0);
        vector<int> ns(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                we[i] = max(we[i], grid[i][j]);
                ns[j] = max(ns[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += min(we[i], ns[j]) - grid[i][j];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func maxIncreaseKeepingSkyline(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	we := make([]int, m)
	ns := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			we[i] = max(we[i], grid[i][j])
			ns[j] = max(ns[j], grid[i][j])
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			res += min(we[i], ns[j]) - grid[i][j]
		}
	}
	return res
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
