# [面试题 12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

## 题目描述

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的 3×4 的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

```
[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]
```

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

**示例 1：**

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

**示例 2：**

```
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```

**提示：**

- `1 <= board.length <= 200`
- `1 <= board[i].length <= 200`

## 解法

深度优先搜索 DFS 解决。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, cur):
            if cur == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or visited[i][j] or word[cur] != board[i][j]:
                return False
            visited[i][j] = True
            next = cur + 1
            res = dfs(i + 1, j, next) or dfs(i - 1, j, next) or dfs(i, j + 1, next) or dfs(i, j - 1, next)
            visited[i][j] = False
            return res
        m, n = len(board), len(board[0])
        visited = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            for j in range(n):
                res = dfs(i, j, 0)
                if res:
                    return True
        return False
```

### **Java**

```java
class Solution {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        char[] chars = word.toCharArray();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean res = dfs(board, i, j, chars, 0);
                if (res) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, char[] chars, int cur) {
        if (cur == chars.length) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (visited[i][j] || board[i][j] != chars[cur]) return false;
        visited[i][j] = true;
        int next = cur + 1;
        boolean res = dfs(board, i + 1, j, chars, next)
                || dfs(board, i - 1, j, chars, next)
                || dfs(board, i, j + 1, chars, next)
                || dfs(board, i, j - 1, chars, next);
        visited[i][j] = false;
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
var exist = function (board, word) {
    let row = board.length;
    let col = board[0].length;
    let res = false;
    let isRead = [...new Array(row)].map(() => Array(col).fill(0));
    for (let i = 0; i < row; i++) {
        for (let j = 0; j < col; j++) {
            if (res) break;
            if (board[i][j] === word[0]) {
                dfs(i, j, word);
            }
        }
    }
    function dfs(i, j, word) {
        if (
            i < 0 ||
            j < 0 ||
            i >= row ||
            j >= col ||
            res ||
            isRead[i][j] ||
            board[i][j] !== word[0]
        ) {
            return;
        }
        isRead[i][j] = 1;
        word = word.substring(1);
        if (word.length) {
            dfs(i - 1, j, word);
            dfs(i + 1, j, word);
            dfs(i, j - 1, word);
            dfs(i, j + 1, word);
        } else {
            res = true;
            return;
        }
        isRead[i][j] = 0;
    }
    return res;
};
```

### **Go**

```go
func exist(board [][]byte, word string) bool {
	if len(board) == 0 {
		return false
	}
	//标记数组
	isVisited := make([][]bool, len(board))
	for i := 0; i < len(board); i++ {
		isVisited[i] = make([]bool, len(board[0]))
	}
	for i := 0; i < len(board); i++ {
		for j := 0; j < len(board[0]); j++ {
			if board[i][j] == word[0] {
				if bfs(board, i, j, isVisited, word, 0) {
					return true
				}
			}
		}
	}
	return false
}

func bfs(board [][]byte, i, j int, isVisited [][]bool, word string, index int) bool {
	if index == len(word) {
		return true
	}
	if i < 0 || j < 0 || i == len(board) || j == len(board[0]) || isVisited[i][j] || board[i][j] != word[index] {
		return false
	}
	isVisited[i][j] = true
	res := bfs(board, i+1, j, isVisited, word, index+1) ||
		bfs(board, i, j+1, isVisited, word, index+1) ||
		bfs(board, i-1, j, isVisited, word, index+1) ||
		bfs(board, i, j-1, isVisited, word, index+1)
	isVisited[i][j] = false
	return res
}
```

### **C++**

```cpp
class Solution {
public:
    bool dfs(vector<vector<char>>& board, string& word, int cur, int x, int y) {
        if (board[x][y] != word[cur]) {
            return false;
        }

        if (cur == word.size()-1) {
            return true;
        }

        char t = board[x][y];
        board[x][y] = '*';    // 表示查询过了这个字段
        int dx[4] = {-1, 0, 1, 0};
        int dy[4] = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            // 从上、右、下、左四个方向，开始dfs
            int a = x + dx[k], b = y + dy[k];
            if (a >= 0 && a < board.size() && b >= 0 && b < board[0].size()) {
                if (dfs(board, word, cur+1, a, b)) {
                    return true;
                }
            }
        }

        board[x][y] = t;
        return false;
    }

    bool exist(vector<vector<char>>& board, string word) {
        int x = board.size();
        int y = board[0].size();
        if (0 == x || 0 == y) {
            return false;
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
