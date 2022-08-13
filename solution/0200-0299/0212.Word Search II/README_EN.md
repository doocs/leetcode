# [212. Word Search II](https://leetcode.com/problems/word-search-ii)

[中文文档](/solution/0200-0299/0212.Word%20Search%20II/README.md)

## Description

<p>Given an <code>m x n</code> <code>board</code>&nbsp;of characters and a list of strings <code>words</code>, return <em>all words on the board</em>.</p>

<p>Each word must be constructed from letters of sequentially adjacent cells, where <strong>adjacent cells</strong> are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search1.jpg" style="width: 322px; height: 322px;" />
<pre>
<strong>Input:</strong> board = [[&quot;o&quot;,&quot;a&quot;,&quot;a&quot;,&quot;n&quot;],[&quot;e&quot;,&quot;t&quot;,&quot;a&quot;,&quot;e&quot;],[&quot;i&quot;,&quot;h&quot;,&quot;k&quot;,&quot;r&quot;],[&quot;i&quot;,&quot;f&quot;,&quot;l&quot;,&quot;v&quot;]], words = [&quot;oath&quot;,&quot;pea&quot;,&quot;eat&quot;,&quot;rain&quot;]
<strong>Output:</strong> [&quot;eat&quot;,&quot;oath&quot;]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Input:</strong> board = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;]], words = [&quot;abcb&quot;]
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>board[i][j]</code> is a lowercase English letter.</li>
	<li><code>1 &lt;= words.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>words</code> are unique.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.w = ''

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.w = w


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        def dfs(node, i, j):
            idx = ord(board[i][j]) - ord('a')
            if node.children[idx] is None:
                return
            node = node.children[idx]
            if node.w:
                ans.add(node.w)
            c = board[i][j]
            board[i][j] = '0'
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and board[x][y] != '0':
                    dfs(node, x, y)
            board[i][y] = c

        trie = Trie()
        for w in words:
            trie.insert(w)
        ans = set()
        m, n = len(board), len(board[0])
        for i in range(m):
            for j in range(n):
                dfs(trie, i, j)
        return list(ans)
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    String w;

    void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.w = w;
    }
}

class Solution {
    private Set<String> ans = new HashSet<>();
    private int m;
    private int n;
    private char[][] board;

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(trie, i, j);
            }
        }
        return new ArrayList<>(ans);
    }

    private void dfs(Trie node, int i, int j) {
        int idx = board[i][j] - 'a';
        if (node.children[idx] == null) {
            return;
        }
        node = node.children[idx];
        if (node.w != null) {
            ans.add(node.w);
        }
        char c = board[i][j];
        board[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0') {
                dfs(node, x, y);
            }
        }
        board[i][j] = c;
    }
}
```

### **C++**

```cpp
class Trie {
public:
    vector<Trie*> children;
    string w;
    Trie()
        : children(26)
        , w("") { }

    void insert(string& w) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->w = w;
    }
};

class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        Trie* trie = new Trie();
        unordered_set<string> res;
        for (auto& w : words) trie->insert(w);
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                dfs(trie, i, j, board, res);
        vector<string> ans;
        for (auto& w : res) ans.emplace_back(w);
        return ans;
    }

    void dfs(Trie* node, int i, int j, vector<vector<char>>& board, unordered_set<string>& res) {
        int idx = board[i][j] - 'a';
        if (!node->children[idx]) return;
        node = node->children[idx];
        if (node->w != "") res.insert(node->w);
        char c = board[i][j];
        board[i][j] = '0';

        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] != '0') dfs(node, x, y, board, res);
        }
        board[i][j] = c;
    }
};
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	w        string
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.w = word
}

func findWords(board [][]byte, words []string) []string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	m, n := len(board), len(board[0])
	res := map[string]bool{}
	var dfs func(node *Trie, i, j int)
	dfs = func(node *Trie, i, j int) {
		idx := board[i][j] - 'a'
		if node.children[idx] == nil {
			return
		}
		node = node.children[idx]
		if node.w != "" {
			res[node.w] = true
		}
		dirs := []int{-1, 0, 1, 0, -1}
		c := board[i][j]
		board[i][j] = '0'
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' {
				dfs(node, x, y)
			}
		}
		board[i][j] = c
	}
	for i, row := range board {
		for j := range row {
			dfs(trie, i, j)
		}
	}
	var ans []string
	for v := range res {
		ans = append(ans, v)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
