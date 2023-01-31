# [面试题 12. 矩阵中的路径](https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/)

## 题目描述

<p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code> 和一个字符串单词 <code>word</code> 。如果 <code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p> </p>

<p>例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9812.%20%E7%9F%A9%E9%98%B5%E4%B8%AD%E7%9A%84%E8%B7%AF%E5%BE%84/images/word2.jpg" style="width: 322px; height: 242px;" /></p>

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

<p><strong>注意：</strong>本题与主站 79 题相同：<a href="https://leetcode.cn/problems/word-search/">https://leetcode.cn/problems/word-search/</a></p>

## 解法

**方法一：枚举 + DFS**

我们可以枚举矩阵的每个位置 $(i, j)$，以该位置为起点，采用深度优先搜索的方法寻找字符串 `word` 的路径。如果找到了一条路径，即可返回 `true`，否则在枚举完所有的位置后，返回 `false`。

那么问题的转换为如何采用深度优先搜索的方法寻找字符串 `word` 的路径。我们可以设计一个函数 $dfs(i, j, k)$，表示从位置 $(i, j)$ 开始，且当前将要匹配的字符为 `word[k]` 的情况下，是否能够找到字符串 `word` 的路径。如果能找到，返回 `true`，否则返回 `false`。

函数 $dfs(i, j, k)$ 的执行流程如下：

-   如果当前字符 `word[k]` 已经匹配到字符串 `word` 的末尾，说明已经找到了字符串 `word` 的路径，返回 `true`。
-   如果当前位置 $(i, j)$ 超出矩阵边界，或者当前位置的字符与 `word[k]` 不同，说明当前位置不在字符串 `word` 的路径上，返回 `false`。
-   否则，我们将当前位置的字符标记为已访问（防止重复搜索），然后分别向当前位置的上、下、左、右四个方向继续匹配字符 `word[k + 1]`，只要有一条路径能够匹配到字符串 `word` 的路径，就说明能够找到字符串 `word` 的路径，返回 `true`。在回溯时，我们要将当前位置的字符还原为未访问过的状态。

时间复杂度 $O(m \times n \times 3^k)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数，而 $k$ 为字符串 `word` 的长度。我们需要枚举矩阵中的每个位置，然后对于每个位置，我们最多需要搜索三个方向。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, k):
            if k == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] != word[k]:
                return False
            board[i][j] = ""
            dirs = (-1, 0, 1, 0, -1)
            ans = any(dfs(i + a, j + b, k + 1) for a, b in pairwise(dirs))
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

### **C++**

```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (k == word.size()) {
                return true;
            }
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k]) {
                return false;
            }
            board[i][j] = '.';
            bool ans = 0;
            for (int l = 0; l < 4; ++l) {
                ans |= dfs(i + dirs[l], j + dirs[l + 1], k + 1);
            }
            board[i][j] = word[k];
            return ans;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
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
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i, j, k) => {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k]) {
            return false;
        }
        board[i][j] = ' ';
        let ans = false;
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

### **TypeScript**

```ts
function exist(board: string[][], word: string): boolean {
    const m = board.length;
    const n = board[0].length;
    const dfs = (i: number, j: number, k: number) => {
        if ((board[i] ?? [])[j] !== word[k]) {
            return false;
        }
        if (++k === word.length) {
            return true;
        }
        const temp = board[i][j];
        board[i][j] = ' ';
        if (
            dfs(i + 1, j, k) ||
            dfs(i, j + 1, k) ||
            dfs(i - 1, j, k) ||
            dfs(i, j - 1, k)
        ) {
            return true;
        }
        board[i][j] = temp;
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
    fn dfs(board: &mut Vec<Vec<char>>, chars: &Vec<char>, i: usize, j: usize, mut k: usize) -> bool {
        if board[i][j] != chars[k] {
            return false;
        }
        k += 1;
        if k == chars.len() {
            return true;
        }
        let temp = board[i][j];
        board[i][j] = ' ';
        if i != 0 && Self::dfs(board, chars, i - 1, j, k)
            || j != 0 && Self::dfs(board, chars, i, j - 1, k)
            || i != board.len() - 1 && Self::dfs(board, chars, i + 1, j, k)
            || j != board[0].len() - 1 && Self::dfs(board, chars, i, j + 1, k)
        {
            return true;
        }
        board[i][j] = temp;
        false
    }

    pub fn exist(mut board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let chars = word.chars().collect::<Vec<char>>();
        for i in 0..m {
            for j in 0..n {
                if Self::dfs(&mut board, &chars, i, j, 0) {
                    return true;
                }
            }
        }
        false
    }
}
```

### **C#**

```cs
public class Solution {
    private char[][] board;
    private string word;
    private int m;
    private int n;

    public bool Exist(char[][] board, string word) {
        m = board.Length;
        n = board[0].Length;
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

    private bool dfs(int i, int j, int k) {
        if (k == word.Length) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || word[k] != board[i][j]) {
            return false;
        }
        board[i][j] = ' ';
        int[] dirs = {-1, 0, 1, 0, -1};
        bool ans = false;
        for (int l = 0; l < 4; ++l) {
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1);
        }
        board[i][j] = word[k];
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
