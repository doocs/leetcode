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
        def dfs(i, j, k):
            if k == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or word[k] != board[i][j]:
                return False
            board[i][j] = ''
            ans = any(dfs(i + a, j + b, k + 1) for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]])
            board[i][j] = word[k]
            return ans

        m, n = len(board), len(board[0])
        return any(dfs(i, j, 0) for i in range(m) for j in range(n))
```

### **Java**

```java
class Solution {
    private char[][] board;
    private String word;
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        this.word = word;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || word.charAt(k) != board[i][j]) {
            return false;
        }
        board[i][j] = ' ';
        int[] dirs = {-1, 0, 1, 0, -1};
        boolean ans = false;
        for (int l = 0; l < 4; ++l) {
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1);
        }
        board[i][j] = word.charAt(k);
        return ans;
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
    const m = board.length;
    const n = board[0].length;
    let dfs = function (i, j, k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k]) {
            return false;
        }
        board[i][j] = ' ';
        let ans = false;
        let dirs = [-1, 0, 1, 0, -1];
        for (let l = 0; l < 4; ++l) {
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1);
        }
        board[i][j] = word[k];
        return ans;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
};
```

### **Go**

```go
func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if k == len(word) {
			return true
		}
		if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k] {
			return false
		}
		board[i][j] = ' '
		dirs := []int{-1, 0, 1, 0, -1}
		ans := false
		for l := 0; l < 4; l++ {
			ans = ans || dfs(i+dirs[l], j+dirs[l+1], k+1)
		}
		board[i][j] = word[k]
		return ans
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if dfs(i, j, 0) {
				return true
			}
		}
	}
	return false
}
```

### **C++**

```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                if (dfs(i, j, 0, board, word))
                    return 1;
        return 0;    
    }

    bool dfs(int i, int j, int k, vector<vector<char>>& board, string word) {
        if (k == word.size()) return 1;
        if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size() || board[i][j] != word[k]) return 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        board[i][j] = ' ';
        bool ans = 0;
        for (int l = 0; l < 4; ++l)
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1, board, word);
        board[i][j] = word[k];
        return ans;
    }
};
```

### **TypeScript**

```ts
function exist(board: string[][], word: string): boolean {
    const m = board.length;
    const n = board[0].length;
    const dfs = (y: number, x: number, i: number) => {
        if (i === word.length) {
            return true;
        }
        if ((board[y] || [])[x] !== word[i]) {
            return false;
        }
        const temp = board[y][x];
        board[y][x] = '';
        if (
            dfs(y + 1, x, i + 1) ||
            dfs(y, x + 1, i + 1) ||
            dfs(y - 1, x, i + 1) ||
            dfs(y, x - 1, i + 1)
        ) {
            return true;
        }
        board[y][x] = temp;
        return false;
    };
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(board: &mut Vec<Vec<char>>, chars: &Vec<char>, y: usize, x: usize, i: usize) -> bool {
        if board[y][x] != chars[i] {
            return false;
        }
        if i + 1 == chars.len() {
            return true;
        }
        let temp = board[y][x];
        board[y][x] = ' ';
        if y != 0 && Solution::dfs(board, chars, y - 1, x, i + 1)
            || x != 0 && Solution::dfs(board, chars, y, x - 1, i + 1)
            || y != board.len() - 1 && Solution::dfs(board, chars, y + 1, x, i + 1)
            || x != board[0].len() - 1 && Solution::dfs(board, chars, y, x + 1, i + 1)
        {
            return true;
        }
        board[y][x] = temp;
        false
    }

    pub fn exist(mut board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let chars = word.chars().collect::<Vec<char>>();
        for i in 0..m {
            for j in 0..n {
                if Solution::dfs(&mut board, &chars, i, j, 0) {
                    return true;
                }
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
