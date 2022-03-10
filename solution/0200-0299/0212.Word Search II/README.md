# [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii)

[English Version](/solution/0200-0299/0212.Word%20Search%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<code>m x n</code> 二维字符网格&nbsp;<code>board</code><strong>&nbsp;</strong>和一个单词（字符串）列表 <code>words</code>，&nbsp;<em>返回所有二维网格上的单词</em>&nbsp;。</p>

<p>单词必须按照字母顺序，通过 <strong>相邻的单元格</strong> 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search1.jpg" />
<pre>
<strong>输入：</strong>board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
<strong>输出：</strong>["eat","oath"]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search2.jpg" />
<pre>
<strong>输入：</strong>board = [["a","b"],["c","d"]], words = ["abcb"]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>board[i][j]</code> 是一个小写英文字母</li>
	<li><code>1 &lt;= words.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
	<li><code>words</code> 中的所有字符串互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
