# [2061. 扫地机器人清扫过的空间个数](https://leetcode.cn/problems/number-of-spaces-cleaning-robot-cleaned)

[English Version](/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个房间用一个<strong>从 0 开始索引</strong>的二维二进制矩阵 <code>room</code> 表示，其中 <code>0</code> 表示<strong>空闲</strong>空间， <code>1</code> 表示放有<strong>物体</strong>的空间。在每个测试用例中，房间左上角永远是空闲的。</p>

<p>一个扫地机器人面向右侧，从左上角开始清扫。机器人将一直前进，直到抵达房间边界或触碰到物体时，机器人将会<strong>顺时针</strong>旋转 90 度并重复以上步骤。初始位置和所有机器人走过的空间都会被它<strong>清扫干净</strong>。</p>

<p>若机器人持续运转下去，返回被<strong>清扫干净</strong>的空间数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204703-1.png" style="width: 250px; height: 242px;" /></p>

<pre>
<strong>输入:</strong> room = [[0,0,0],[1,1,0],[0,0,0]]
<strong>输出:</strong> 7
<strong>解释:</strong>
机器人清理了位于 (0, 0)、 (0, 1) 和 (0, 2) 的空间。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向下。
机器人清理了位于 (1, 2) 和 (2, 2) 的空间。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向左。
机器人清理了位于 (2, 1) 和 (2, 0) 的空间。
机器人已清理了所有 7 处空闲空间，所以返回 7。
</pre>

<p><strong>示例 2：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204736-2.png" style="width: 250px; height: 245px;" /></p>

<pre>
<strong>输入:</strong> room = [[0,1,0],[1,0,0],[0,0,0]]
<strong>输出t:</strong> 1
<strong>解释:</strong>
机器人清理了位于 (0, 0) 的空间。
机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向下。
机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向左。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向上。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向右。
机器人回到了起始位置。
机器人清理了 1 处空间，所以返回 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == room.length</code></li>
	<li><code>n == room[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>room[r][c]</code> 只会是 <code>0</code> 或 <code>1</code> 。</li>
	<li><code>room[0][0] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS 模拟**

我们从起点 $(0, 0)$ 开始模拟机器人的清扫过程，每次清扫当前位置，然后向前走一步，如果碰到墙壁或者已经清扫过的位置，就顺时针旋转 90 度，然后继续清扫。

过程中，我们用一个三元组 $(i, j, k)$ 表示机器人当前的位置 $(i, j)$ 和朝向 $k$，其中 $k$ 的取值范围为 $0, 1, 2, 3$，分别表示朝右、朝下、朝左、朝上。我们用一个集合 `vis` 记录所有访问过的状态三元组。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为房间的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfCleanRooms(self, room: List[List[int]]) -> int:
        def dfs(i, j, k):
            if (i, j, k) in vis:
                return
            nonlocal ans
            ans += room[i][j] == 0
            room[i][j] = -1
            vis.add((i, j, k))
            x, y = i + dirs[k], j + dirs[k + 1]
            if 0 <= x < len(room) and 0 <= y < len(room[0]) and room[x][y] != 1:
                dfs(x, y, k)
            else:
                dfs(i, j, (k + 1) % 4)

        vis = set()
        dirs = (0, 1, 0, -1, 0)
        ans = 0
        dfs(0, 0, 0)
        return ans
```

```python
class Solution:
    def numberOfCleanRooms(self, room: List[List[int]]) -> int:
        dirs = (0, 1, 0, -1, 0)
        i = j = k = 0
        ans = 0
        vis = set()
        while (i, j, k) not in vis:
            vis.add((i, j, k))
            ans += room[i][j] == 0
            room[i][j] = -1
            x, y = i + dirs[k], j + dirs[k + 1]
            if 0 <= x < len(room) and 0 <= y < len(room[0]) and room[x][y] != 1:
                i, j = x, y
            else:
                k = (k + 1) % 4
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[][][] vis;
    private int[][] room;
    private int ans;

    public int numberOfCleanRooms(int[][] room) {
        vis = new boolean[room.length][room[0].length][4];
        this.room = room;
        dfs(0, 0, 0);
        return ans;
    }

    private void dfs(int i, int j, int k) {
        if (vis[i][j][k]) {
            return;
        }
        int[] dirs = {0, 1, 0, -1, 0};
        ans += room[i][j] == 0 ? 1 : 0;
        room[i][j] = -1;
        vis[i][j][k] = true;
        int x = i + dirs[k], y = j + dirs[k + 1];
        if (x >= 0 && x < room.length && y >= 0 && y < room[0].length && room[x][y] != 1) {
            dfs(x, y, k);
        } else {
            dfs(i, j, (k + 1) % 4);
        }
    }
}
```

```java
class Solution {
    public int numberOfCleanRooms(int[][] room) {
        int[] dirs = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        int m = room.length, n = room[0].length;
        boolean[][][] vis = new boolean[m][n][4];
        int ans = 0;
        while (!vis[i][j][k]) {
            vis[i][j][k] = true;
            ans += room[i][j] == 0 ? 1 : 0;
            room[i][j] = -1;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1) {
                i = x;
                j = y;
            } else {
                k = (k + 1) % 4;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfCleanRooms(vector<vector<int>>& room) {
        int m = room.size(), n = room[0].size();
        bool vis[m][n][4];
        memset(vis, false, sizeof(vis));
        int dirs[5] = {0, 1, 0, -1, 0};
        int ans = 0;
        function<void(int, int, int)> dfs = [&](int i, int j, int k) {
            if (vis[i][j][k]) {
                return;
            }
            ans += room[i][j] == 0;
            room[i][j] = -1;
            vis[i][j][k] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1) {
                dfs(x, y, k);
            } else {
                dfs(i, j, (k + 1) % 4);
            }
        };
        dfs(0, 0, 0);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int numberOfCleanRooms(vector<vector<int>>& room) {
        int dirs[5] = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        int m = room.size(), n = room[0].size();
        bool vis[m][n][4];
        memset(vis, false, sizeof(vis));
        int ans = 0;
        while (!vis[i][j][k]) {
            vis[i][j][k] = true;
            ans += room[i][j] == 0 ? 1 : 0;
            room[i][j] = -1;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1) {
                i = x;
                j = y;
            } else {
                k = (k + 1) % 4;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfCleanRooms(room [][]int) (ans int) {
	m, n := len(room), len(room[0])
	vis := make([][][4]bool, m)
	for i := range vis {
		vis[i] = make([][4]bool, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	var dfs func(i, j, k int)
	dfs = func(i, j, k int) {
		if vis[i][j][k] {
			return
		}
		if room[i][j] == 0 {
			ans++
			room[i][j] = -1
		}
		vis[i][j][k] = true
		x, y := i+dirs[k], j+dirs[k+1]
		if x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1 {
			dfs(x, y, k)
		} else {
			dfs(i, j, (k+1)%4)
		}
	}
	dfs(0, 0, 0)
	return
}
```

```go
func numberOfCleanRooms(room [][]int) (ans int) {
	m, n := len(room), len(room[0])
	vis := make([][][4]bool, m)
	for i := range vis {
		vis[i] = make([][4]bool, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	var i, j, k int
	for !vis[i][j][k] {
		vis[i][j][k] = true
		if room[i][j] == 0 {
			ans++
			room[i][j] = -1
		}
		x, y := i+dirs[k], j+dirs[k+1]
		if x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1 {
			i, j = x, y
		} else {
			k = (k + 1) % 4
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
