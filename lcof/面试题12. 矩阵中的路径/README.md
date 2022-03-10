# [面试题 12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

## 题目描述

<p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code> 和一个字符串单词 <code>word</code> 。如果 <code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p> </p>

<p>例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9812.%20%E7%9F%A9%E9%98%B5%E4%B8%AD%E7%9A%84%E8%B7%AF%E5%BE%84/images/word2.jpg" style="width: 322px; height: 242px;" /></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [["a","b"],["c","d"]], word = "abcd"
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= board.length <= 200</code></li>
	<li><code>1 <= board[i].length <= 200</code></li>
	<li><code>board</code> 和 <code>word</code> 仅由大小写英文字母组成</li>
</ul>

<p> </p>

<p><strong>注意：</strong>本题与主站 79 题相同：<a href="https://leetcode-cn.com/problems/word-search/">https://leetcode-cn.com/problems/word-search/</a></p>

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
