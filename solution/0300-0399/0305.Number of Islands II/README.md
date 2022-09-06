# [305. 岛屿数量 II](https://leetcode.cn/problems/number-of-islands-ii)

[English Version](/solution/0300-0399/0305.Number%20of%20Islands%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的二进制网格 <code>grid</code> 。网格表示一个地图，其中，<code>0</code> 表示水，<code>1</code> 表示陆地。最初，<code>grid</code> 中的所有单元格都是水单元格（即，所有单元格都是 <code>0</code>）。</p>

<p>可以通过执行 <code>addLand</code> 操作，将某个位置的水转换成陆地。给你一个数组 <code>positions</code> ，其中 <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> 是要执行第 <code>i</code> 次操作的位置 <code>(r<sub>i</sub>, c<sub>i</sub>)</code> 。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer[i]</code> 是将单元格 <code>(r<sub>i</sub>, c<sub>i</sub>)</code> 转换为陆地后，地图中岛屿的数量。</p>

<p><strong>岛屿</strong> 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0305.Number%20of%20Islands%20II/images/tmp-grid.jpg" style="width: 500px; height: 294px;" />
<pre>
<strong>输入：</strong>m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
<strong>输出：</strong>[1,1,2,3]
<strong>解释：</strong>
起初，二维网格&nbsp;<code>grid</code>&nbsp;被全部注入「水」。（0 代表「水」，1 代表「陆地」）
- 操作&nbsp;#1：<code>addLand(0, 0)</code> 将&nbsp;<code>grid[0][0]</code> 的水变为陆地。此时存在 1 个岛屿。
- 操作&nbsp;#2：<code>addLand(0, 1)</code> 将&nbsp;<code>grid[0][1]</code> 的水变为陆地。此时存在 1 个岛屿。
- 操作&nbsp;#3：<code>addLand(1, 2)</code> 将&nbsp;<code>grid[1][2]</code> 的水变为陆地。此时存在 2 个岛屿。
- 操作&nbsp;#4：<code>addLand(2, 1)</code> 将&nbsp;<code>grid[2][1]</code> 的水变为陆地。此时存在 3 个岛屿。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>m = 1, n = 1, positions = [[0,0]]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n, positions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; m</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; n</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度 <code>O(k log(mn))</code> 的算法解决此问题吗？（其中 <code>k == positions.length</code>）</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

并查集模板：

模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]


# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        grid = [[0] * n for _ in range(m)]
        cnt = 0
        p = list(range(m * n))
        ans = []
        for i, j in positions:
            if grid[i][j] == 1:
                ans.append(cnt)
                continue
            grid[i][j] = 1
            cnt += 1
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1 and find(i * n + j) != find(x * n + y):
                    p[find(i * n + j)] = find(x * n + y)
                    cnt -= 1
            ans.append(cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[][] grid = new int[m][n];
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            if (grid[i][j] == 1) {
                ans.add(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1
                    && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n));
        vector<int> ans;
        int cnt = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (auto& pos : positions) {
            int i = pos[0], j = pos[1];
            if (grid[i][j] == 1) {
                ans.push_back(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.push_back(cnt);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func numIslands2(m int, n int, positions [][]int) []int {
	p := make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	var ans []int
	cnt := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for _, pos := range positions {
		i, j := pos[0], pos[1]
		if grid[i][j] == 1 {
			ans = append(ans, cnt)
			continue
		}
		grid[i][j] = 1
		cnt++
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x*n+y) != find(i*n+j) {
				p[find(x*n+y)] = find(i*n + j)
				cnt--
			}
		}
		ans = append(ans, cnt)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
