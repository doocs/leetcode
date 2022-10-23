# [296. 最佳的碰头地点](https://leetcode.cn/problems/best-meeting-point)

[English Version](/solution/0200-0299/0296.Best%20Meeting%20Point/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;&nbsp;的二进制网格&nbsp;<code>grid</code>&nbsp;，其中 <code>1</code> 表示某个朋友的家所处的位置。返回 <em>最小的 <strong>总行走距离</strong></em> 。</p>

<p><strong>总行走距离</strong> 是朋友们家到碰头地点的距离之和。</p>

<p>我们将使用&nbsp;<a href="https://baike.baidu.com/item/%E6%9B%BC%E5%93%88%E9%A1%BF%E8%B7%9D%E7%A6%BB" target="_blank">曼哈顿距离</a>&nbsp;来计算，其中&nbsp;<code>distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0296.Best%20Meeting%20Point/images/meetingpoint-grid.jpg" /></p>

<pre>
<strong>输入:</strong> grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
<strong>输出: </strong>6 <strong>
解释: </strong>给定的三个人分别住在<code>(0,0)，</code><code>(0,4) </code>和 <code>(2,2)</code>:
&nbsp;    <code>(0,2)</code> 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,1]]
<strong>输出:</strong> 1</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j] ==</code>&nbsp;<code>0</code>&nbsp;or&nbsp;<code>1</code>.</li>
	<li><code>grid</code>&nbsp;中 <strong>至少</strong> 有两个朋友</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 中位数**

对于每一行，我们可以将所有的 $1$ 的下标排序，然后取中位数 $i$ 作为碰头地点的横坐标。

对于每一列，我们可以将所有的 $1$ 的下标排序，然后取中位数 $i$ 作为碰头地点的纵坐标。

最后，我们计算所有 $1$ 到碰头地点 $(i, j)$ 的曼哈顿距离之和即可。

时间复杂度 $O(m\times n\times \log(m\times n))$。最多有 $m\times n$ 个 $1$，排序的时间复杂度为 $\log(m\times n)$。

相似题目：

-   [462. 最少移动次数使数组元素相等 II](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README.md)
-   [2448. 使数组相等的最小开销](/solution/2400-2499/2448.Minimum%20Cost%20to%20Make%20Array%20Equal/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minTotalDistance(self, grid: List[List[int]]) -> int:
        def f(arr, x):
            return sum(abs(v - x) for v in arr)

        rows, cols = [], []
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    rows.append(i)
                    cols.append(j)
        cols.sort()
        i = rows[len(rows) >> 1]
        j = cols[len(cols) >> 1]
        return f(rows, i) + f(cols, j)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);
        int i = rows.get(rows.size() >> 1);
        int j = cols.get(cols.size() >> 1);
        return f(rows, i) + f(cols, j);
    }

    private int f(List<Integer> arr, int x) {
        int s = 0;
        for (int v : arr) {
            s += Math.abs(v - x);
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTotalDistance(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rows;
        vector<int> cols;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    rows.emplace_back(i);
                    cols.emplace_back(j);
                }
            }
        }
        sort(cols.begin(), cols.end());
        int i = rows[rows.size() / 2];
        int j = cols[cols.size() / 2];
        auto f = [](vector<int>& arr, int x) {
            int s = 0;
            for (int v : arr) {
                s += abs(v - x);
            }
            return s;
        };
        return f(rows, i) + f(cols, j);
    }
};
```

### **Go**

```go
func minTotalDistance(grid [][]int) int {
	rows, cols := []int{}, []int{}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				rows = append(rows, i)
				cols = append(cols, j)
			}
		}
	}
	sort.Ints(cols)
	i := rows[len(rows)>>1]
	j := cols[len(cols)>>1]
	f := func(arr []int, x int) int {
		s := 0
		for _, v := range arr {
			s += abs(v - x)
		}
		return s
	}
	return f(rows, i) + f(cols, j)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
