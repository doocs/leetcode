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

**Solution 1: Direct Search**

First, we store all the positions of the queens in a hash table or a two-dimensional array $s$.

Next, starting from the position of the king, we search in the eight directions: up, down, left, right, upper left, upper right, lower left, and lower right. If there is a queen in a certain direction, we add its position to the answer and stop continuing to search in that direction.

After the search is over, we return the answer.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. In this problem, $n = 8$.

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
        for a in range(-1, 2):
            for b in range(-1, 2):
                if a or b:
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
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int n = 8;
        var s = new boolean[n][n];
        for (var q : queens) {
            s[q[0]][q[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a != 0 || b != 0) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.add(List.of(x, y));
                            break;
                        }
                        x += a;
                        y += b;
                    }
                }
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
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        int n = 8;
        bool s[8][8]{};
        for (auto& q : queens) {
            s[q[0]][q[1]] = true;
        }
        vector<vector<int>> ans;
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a || b) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.push_back({x, y});
                            break;
                        }
                        x += a;
                        y += b;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func queensAttacktheKing(queens [][]int, king []int) (ans [][]int) {
	n := 8
	s := [8][8]bool{}
	for _, q := range queens {
		s[q[0]][q[1]] = true
	}
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a != 0 || b != 0 {
				x, y := king[0]+a, king[1]+b
				for 0 <= x && x < n && 0 <= y && y < n {
					if s[x][y] {
						ans = append(ans, []int{x, y})
						break
					}
					x += a
					y += b
				}
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function queensAttacktheKing(queens: number[][], king: number[]): number[][] {
    const n = 8;
    const s: boolean[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => false));
    queens.forEach(([x, y]) => (s[x][y] = true));
    const ans: number[][] = [];
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a || b) {
                let [x, y] = [king[0] + a, king[1] + b];
                while (x >= 0 && x < n && y >= 0 && y < n) {
                    if (s[x][y]) {
                        ans.push([x, y]);
                        break;
                    }
                    x += a;
                    y += b;
                }
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
