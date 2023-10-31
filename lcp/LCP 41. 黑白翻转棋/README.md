# [LCP 41. 黑白翻转棋](https://leetcode.cn/problems/fHi6rV)

## 题目描述

<!-- 这里写题目描述 -->

在 `n*m` 大小的棋盘中，有黑白两种棋子，黑棋记作字母 `"X"`, 白棋记作字母 `"O"`，空余位置记作 `"."`。当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。

![1.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2041.%20黑白翻转棋/images/1630396029-eTgzpN-6da662e67368466a96d203f67bb6e793.gif)![2.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2041.%20黑白翻转棋/images/1630396240-nMvdcc-8e4261afe9f60e05a4f740694b439b6b.gif)![3.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2041.%20黑白翻转棋/images/1630396291-kEtzLL-6fcb682daeecb5c3f56eb88b23c81d33.gif)

「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 `chessboard`。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。

**注意：**

-   若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 **继续** 翻转白棋
-   输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置

**示例 1：**

> 输入：`chessboard = ["....X.","....X.","XOOO..","......","......"]`
>
> 输出：`3`
>
> 解释：
> 可以选择下在 `[2,4]` 处，能够翻转白方三枚棋子。

**示例 2：**

> 输入：`chessboard = [".X.",".O.","XO."]`
>
> 输出：`2`
>
> 解释：
> 可以选择下在 `[2,2]` 处，能够翻转白方两枚棋子。
> ![2126c1d21b1b9a9924c639d449cc6e65.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2041.%20黑白翻转棋/images/1626683255-OBtBud-2126c1d21b1b9a9924c639d449cc6e65.gif)

**示例 3：**

> 输入：`chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]`
>
> 输出：`4`
>
> 解释：
> 可以选择下在 `[6,3]` 处，能够翻转白方四枚棋子。
> ![803f2f04098b6174397d6c696f54d709.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2041.%20黑白翻转棋/images/1630393770-Puyked-803f2f04098b6174397d6c696f54d709.gif)

**提示：**

-   `1 <= chessboard.length, chessboard[i].length <= 8`
-   `chessboard[i]` 仅包含 `"."、"O"` 和 `"X"`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

我们注意到，题目中棋盘的大小最大为 $8 \times 8$，因此，我们可以尝试枚举所有的空余位置作为下一步放置黑棋的位置，然后使用广度优先搜索的方法计算在该位置下可以翻转的白棋的数量，找出最大值即可。

我们定义一个函数 $bfs(i, j)$，表示在棋盘上放置黑棋在 $(i, j)$ 位置后，可以翻转的白棋的数量。

在函数中，我们使用队列来进行广度优先搜索，初始时将 $(i, j)$ 放入队列中，然后不断取出队首位置，遍历棋盘的八个方向，如果该方向上是一段连续的白棋，且在末尾是黑棋，则将该黑棋之前的所有白棋都可以翻转，将这些白棋的位置放入队列中，继续进行广度优先搜索。最后，我们返回可以翻转的白棋的数量。

时间复杂度 $O(m^2 \times n^2 \times \max(m, n))$，空间复杂度 $O(m^2 \times n^2)$。其中 $m$ 和 $n$ 分别是棋盘的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def flipChess(self, chessboard: List[str]) -> int:
        def bfs(i: int, j: int) -> int:
            q = deque([(i, j)])
            g = [list(row) for row in chessboard]
            g[i][j] = "X"
            cnt = 0
            while q:
                i, j = q.popleft()
                for a, b in dirs:
                    x, y = i + a, j + b
                    while 0 <= x < m and 0 <= y < n and g[x][y] == "O":
                        x, y = x + a, y + b
                    if 0 <= x < m and 0 <= y < n and g[x][y] == "X":
                        x, y = x - a, y - b
                        cnt += max(abs(x - i), abs(y - j))
                        while x != i or y != j:
                            g[x][y] = "X"
                            q.append((x, y))
                            x, y = x - a, y - b
            return cnt

        m, n = len(chessboard), len(chessboard[0])
        dirs = [(a, b) for a in range(-1, 2) for b in range(-1, 2) if a != 0 or b != 0]
        return max(
            bfs(i, j) for i in range(m) for j in range(n) if chessboard[i][j] == "."
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private String[] chessboard;

    public int flipChess(String[] chessboard) {
        m = chessboard.length;
        n = chessboard[0].length();
        this.chessboard = chessboard;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (chessboard[i].charAt(j) == '.') {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }
        return ans;
    }

    private int bfs(int i, int j) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        char[][] g = new char[m][0];
        for (int k = 0; k < m; ++k) {
            g[k] = chessboard[k].toCharArray();
        }
        g[i][j] = 'X';
        int cnt = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            i = p[0];
            j = p[1];
            for (int a = -1; a <= 1; ++a) {
                for (int b = -1; b <= 1; ++b) {
                    if (a == 0 && b == 0) {
                        continue;
                    }
                    int x = i + a, y = j + b;
                    while (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O') {
                        x += a;
                        y += b;
                    }
                    if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X') {
                        x -= a;
                        y -= b;
                        cnt += Math.max(Math.abs(x - i), Math.abs(y - j));
                        while (x != i || y != j) {
                            g[x][y] = 'X';
                            q.offer(new int[] {x, y});
                            x -= a;
                            y -= b;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int flipChess(vector<string>& chessboard) {
        int m = chessboard.size();
        int n = chessboard[0].size();
        auto bfs = [&](int i, int j) -> int {
            queue<pair<int, int>> q;
            q.emplace(i, j);
            auto g = chessboard;
            g[i][j] = 'X';
            int cnt = 0;
            while (q.size()) {
                auto p = q.front();
                q.pop();
                i = p.first;
                j = p.second;
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        if (a == 0 && b == 0) {
                            continue;
                        }
                        int x = i + a, y = j + b;
                        while (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O') {
                            x += a;
                            y += b;
                        }
                        if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X') {
                            x -= a;
                            y -= b;
                            cnt += max(abs(x - i), abs(y - j));
                            while (x != i || y != j) {
                                g[x][y] = 'X';
                                q.emplace(x, y);
                                x -= a;
                                y -= b;
                            }
                        }
                    }
                }
            }
            return cnt;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (chessboard[i][j] == '.') {
                    ans = max(ans, bfs(i, j));
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func flipChess(chessboard []string) (ans int) {
	m, n := len(chessboard), len(chessboard[0])
	bfs := func(i, j int) int {
		q := [][2]int{{i, j}}
		g := make([][]byte, m)
		for i := range g {
			g[i] = make([]byte, n)
			copy(g[i], []byte(chessboard[i]))
		}
		g[i][j] = 'X'
		cnt := 0
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			i, j = p[0], p[1]
			for a := -1; a <= 1; a++ {
				for b := -1; b <= 1; b++ {
					if a == 0 && b == 0 {
						continue
					}
					x, y := i+a, j+b
					for x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O' {
						x, y = x+a, y+b
					}
					if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X' {
						x -= a
						y -= b
						cnt += max(abs(x-i), abs(y-j))
						for x != i || y != j {
							g[x][y] = 'X'
							q = append(q, [2]int{x, y})
							x -= a
							y -= b
						}
					}
				}
			}
		}
		return cnt
	}
	for i, row := range chessboard {
		for j, c := range row {
			if c == '.' {
				ans = max(ans, bfs(i, j))
			}
		}
	}
	return
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
