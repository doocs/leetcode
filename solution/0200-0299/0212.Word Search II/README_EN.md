# [212. Word Search II](https://leetcode.com/problems/word-search-ii)

[中文文档](/solution/0200-0299/0212.Word%20Search%20II/README.md)

## Description

<p>Given an <code>m x n</code> <code>board</code>&nbsp;of characters and a list of strings <code>words</code>, return <em>all words on the board</em>.</p>

<p>Each word must be constructed from letters of sequentially adjacent cells, where <strong>adjacent cells</strong> are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search1.jpg" style="width: 322px; height: 322px;" />
<pre>
<strong>Input:</strong> board = [[&quot;o&quot;,&quot;a&quot;,&quot;a&quot;,&quot;n&quot;],[&quot;e&quot;,&quot;t&quot;,&quot;a&quot;,&quot;e&quot;],[&quot;i&quot;,&quot;h&quot;,&quot;k&quot;,&quot;r&quot;],[&quot;i&quot;,&quot;f&quot;,&quot;l&quot;,&quot;v&quot;]], words = [&quot;oath&quot;,&quot;pea&quot;,&quot;eat&quot;,&quot;rain&quot;]
<strong>Output:</strong> [&quot;eat&quot;,&quot;oath&quot;]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search2.jpg" style="width: 162px; height: 162px;" />
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
class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        def check(word):
            cnt = Counter(word)
            return all(counter[c] >= i for c, i in cnt.items())

        def dfs(i, j, l, word):
            if l == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] != word[l]:
                return False
            c = board[i][j]
            board[i][j] = '0'
            ans = False
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                ans = ans or dfs(x, y, l + 1, word)
            board[i][j] = c
            return ans

        def find(word):
            if not check(word):
                return False
            for i in range(m):
                for j in range(n):
                    if dfs(i, j, 0, word):
                        return True
            return False

        m, n = len(board), len(board[0])
        words = set(words)
        counter = Counter(c for b in board for c in b)
        return [word for word in words if find(word)]
```

### **Java**

```java
class Solution {
    private int[] counter;
    private char[][] board;

    public List<String> findWords(char[][] board, String[] words) {
        counter = new int[26];
        this.board = board;
        for (char[] b : board) {
            for (char c : b) {
                ++counter[c - 'a'];
            }
        }
        Set<String> s = new HashSet<>(Arrays.asList(words));
        List<String> ans = new ArrayList<>();
        for (String word : s) {
            if (find(word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean find(String word) {
        if (!check(word)) {
            return false;
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (dfs(i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int l, String word) {
        if (l == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(l)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        boolean ans = false;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            ans = ans || dfs(x, y, l + 1, word);
        }
        board[i][j] = c;
        return ans;
    }

    private boolean check(String word) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (counter[i] < cnt[i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> counter;

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        counter.resize(26);
        for (auto& b : board)
            for (auto& c : b)
                ++counter[c - 'a'];
        unordered_set<string> s(words.begin(), words.end());
        vector<string> ans;
        for (string word : s)
            if (find(word, board))
                ans.push_back(word);
        return ans;
    }

    bool find(string& word, vector<vector<char>>& board) {
        if (!check(word)) return false;
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                if (dfs(i, j, 0, word, board))
                    return true;
        return false;
    }

    bool dfs(int i, int j, int l, string& word, vector<vector<char>>& board) {
        if (l == word.size()) return true;
        if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size() || board[i][j] != word[l]) return false;
        char c = board[i][j];
        board[i][j] = '0';
        bool ans = false;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k)
        {
            int x = i + dirs[k], y = j + dirs[k + 1];
            ans = ans || dfs(x, y, l + 1, word, board);
        }
        board[i][j] = c;
        return ans;
    }

    bool check(string word) {
        vector<int> cnt(26);
        for (char c : word)
            ++cnt[c - 'a'];
        for (int i = 0; i < 26; ++i)
            if (counter[i] < cnt[i])
                return false;
        return true;
    }
};
```

### **Go**

```go
func findWords(board [][]byte, words []string) []string {
	counter := make([]int, 26)
	for _, b := range board {
		for _, c := range b {
			counter[c-'a']++
		}
	}
	s := make(map[string]bool)
	for _, word := range words {
		s[word] = true
	}

	check := func(word string) bool {
		cnt := make([]int, 26)
		for i := range word {
			cnt[word[i]-'a']++
		}
		for i := 0; i < 26; i++ {
			if counter[i] < cnt[i] {
				return false
			}
		}
		return true
	}

	var dfs func(i, j, l int, word string) bool
	dfs = func(i, j, l int, word string) bool {
		if l == len(word) {
			return true
		}
		if i < 0 || i >= len(board) || j < 0 || j >= len(board[0]) || board[i][j] != word[l] {
			return false
		}
		c := board[i][j]
		board[i][j] = '0'
		ans := false
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			ans = ans || dfs(x, y, l+1, word)
		}
		board[i][j] = c
		return ans
	}

	find := func(word string) bool {
		if !check(word) {
			return false
		}
		for i, b := range board {
			for j, _ := range b {
				if dfs(i, j, 0, word) {
					return true
				}
			}
		}
		return false
	}

	var ans []string
	for word, _ := range s {
		if find(word) {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
