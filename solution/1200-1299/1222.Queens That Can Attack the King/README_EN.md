# [1222. Queens That Can Attack the King](https://leetcode.com/problems/queens-that-can-attack-the-king)

[中文文档](/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/README.md)

## Description

<p>On a <strong>0-indexed</strong> <code>8 x 8</code> chessboard, there can be multiple black queens ad one white king.</p>

<p>You are given a 2D integer array <code>queens</code> where <code>queens[i] = [xQueen<sub>i</sub>, yQueen<sub>i</sub>]</code> represents the position of the <code>i<sup>th</sup></code> black queen on the chessboard. You are also given an integer array <code>king</code> of length <code>2</code> where <code>king = [xKing, yKing]</code> represents the position of the white king.</p>

<p>Return <em>the coordinates of the black queens that can directly attack the king</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/chess1.jpg" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
<strong>Output:</strong> [[0,1],[1,0],[3,3]]
<strong>Explanation:</strong> The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/chess2.jpg" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
<strong>Output:</strong> [[2,2],[3,4],[4,4]]
<strong>Explanation:</strong> The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queens.length &lt; 64</code></li>
	<li><code>queens[i].length == king.length == 2</code></li>
	<li><code>0 &lt;= xQueen<sub>i</sub>, yQueen<sub>i</sub>, xKing, yKing &lt; 8</code></li>
	<li>All the given positions are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def queensAttacktheKing(
        self, queens: List[List[int]], king: List[int]
    ) -> List[List[int]]:
        n = 8
        s = {(i, j) for i, j in queens}
        ans = []
        for a, b in [
            [-1, 0],
            [1, 0],
            [0, -1],
            [0, 1],
            [1, 1],
            [1, -1],
            [-1, 1],
            [-1, -1],
        ]:
            x, y = king
            while 0 <= x + a < n and 0 <= y + b < n:
                x, y = x + a, y + b
                if (x, y) in s:
                    ans.append([x, y])
                    break
        return ans
```

### **Java**

```java
class Solution {
    private static final int N = 8;
    private int[][] dirs
        = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> s = get(queens);
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] dir : dirs) {
            int x = king[0], y = king[1];
            int a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < N && y + b >= 0 && y + b < N) {
                x += a;
                y += b;
                if (s.contains(x * N + y)) {
                    ans.add(Arrays.asList(x, y));
                    break;
                }
            }
        }
        return ans;
    }

    private Set<Integer> get(int[][] queens) {
        Set<Integer> ans = new HashSet<>();
        for (int[] queen : queens) {
            ans.add(queen[0] * N + queen[1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        unordered_set<int> s;
        int n = 8;
        for (auto& queen : queens) s.insert(queen[0] * n + queen[1]);
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        vector<vector<int>> ans;
        for (auto& dir : dirs) {
            int x = king[0], y = king[1];
            int a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n) {
                x += a;
                y += b;
                if (s.count(x * n + y)) {
                    ans.push_back({x, y});
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func queensAttacktheKing(queens [][]int, king []int) [][]int {
	s := make(map[int]bool)
	n := 8
	for _, queen := range queens {
		s[queen[0]*n+queen[1]] = true
	}
	dirs := [8][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	var ans [][]int
	for _, dir := range dirs {
		x, y := king[0], king[1]
		a, b := dir[0], dir[1]
		for x+a >= 0 && x+a < n && y+b >= 0 && y+b < n {
			x, y = x+a, y+b
			if s[x*n+y] {
				ans = append(ans, []int{x, y})
				break
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
