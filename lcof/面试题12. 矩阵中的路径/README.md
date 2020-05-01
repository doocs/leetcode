# [面试题12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

## 题目描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

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
### Python3
```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if not word:
            return False
        rows, cols = len(board), len(board[0])
        visited = [[False for _ in range(cols)] for _ in range(rows)]
        for i in range(rows):
            for j in range(cols):
                if self.visit(board, visited, i, j, rows, cols, word):
                    return True
        return False
    
    def visit(self, board, visited, i, j, rows, cols, word) -> bool:
        if not word:
            return True
        if i < 0 or j < 0 or i >= rows or j >= cols or visited[i][j] or board[i][j] != word[0]:
            return False
        visited[i][j] = True
        res = self.visit(board, visited, i - 1, j, rows, cols, word[1:]) or self.visit(board, visited, i + 1, j, rows, cols, word[1:]) or self.visit(board, visited, i, j - 1, rows, cols, word[1:]) or self.visit(board, visited, i, j + 1, rows, cols, word[1:])
        visited[i][j] = res
        return res
```

### Java
```java
class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (visit(board, visited, i, j, rows, cols, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean visit(char[][] board, boolean[][] visited, int i, int j, int rows, int cols, String word) {
        if (word.length() == 0) {
            return true;
        }
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j] || board[i][j] != word.charAt(0)) {
            return false;
        }

        visited[i][j] = true;
        String sub = word.substring(1);
        boolean res = visit(board, visited, i + 1, j, rows, cols, sub)
                || visit(board, visited, i - 1, j, rows, cols, sub)
                || visit(board, visited, i, j + 1, rows, cols, sub)
                || visit(board, visited, i, j - 1, rows, cols, sub);
        visited[i][j] = res;
        return res;

    }
}
```

### JavaScript
```js
/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
var exist = function(board, word) {
    let row = board.length
    let col = board[0].length
    let res = false
    let isRead = [...new Array(row)].map(()=>Array(col).fill(0))
    for(let i=0;i<row;i++) {
        for(let j=0;j<col;j++) {
            if(res) break
            if(board[i][j] === word[0]) {
                dfs(i,j,word)
            }
        }
    }
    function dfs(i,j,word) {
        if(i < 0 || j < 0 || i >= row || j >= col || res || isRead[i][j] || board[i][j] !== word[0]) {
            return
        }
        isRead[i][j] = 1
        word = word.substring(1)
        if(word.length) {
            dfs(i-1,j,word)
            dfs(i+1,j,word)
            dfs(i,j-1,word)
            dfs(i,j+1,word)
        } else {
            res = true
            return
        }
        isRead[i][j] = 0
    }
    return res
};
```

### ...
```

```
