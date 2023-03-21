# [2018. Check if Word Can Be Placed In Crossword](https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword)

[中文文档](/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/README.md)

## Description

<p>You are given an <code>m x n</code> matrix <code>board</code>, representing the<strong> current </strong>state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), <code>&#39; &#39;</code> to represent any <strong>empty </strong>cells, and <code>&#39;#&#39;</code> to represent any <strong>blocked</strong> cells.</p>

<p>A word can be placed<strong> horizontally</strong> (left to right <strong>or</strong> right to left) or <strong>vertically</strong> (top to bottom <strong>or</strong> bottom to top) in the board if:</p>

<ul>
	<li>It does not occupy a cell containing the character <code>&#39;#&#39;</code>.</li>
	<li>The cell each letter is placed in must either be <code>&#39; &#39;</code> (empty) or <strong>match</strong> the letter already on the <code>board</code>.</li>
	<li>There must not be any empty cells <code>&#39; &#39;</code> or other lowercase letters <strong>directly left or right</strong><strong> </strong>of the word if the word was placed <strong>horizontally</strong>.</li>
	<li>There must not be any empty cells <code>&#39; &#39;</code> or other lowercase letters <strong>directly above or below</strong> the word if the word was placed <strong>vertically</strong>.</li>
</ul>

<p>Given a string <code>word</code>, return <code>true</code><em> if </em><code>word</code><em> can be placed in </em><code>board</code><em>, or </em><code>false</code><em> <strong>otherwise</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex1-1.png" style="width: 478px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot;#&quot;, &quot; &quot;, &quot;#&quot;], [&quot; &quot;, &quot; &quot;, &quot;#&quot;], [&quot;#&quot;, &quot;c&quot;, &quot; &quot;]], word = &quot;abc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;abc&quot; can be placed as shown above (top to bottom).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex2-1.png" style="width: 180px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot; &quot;, &quot;#&quot;, &quot;a&quot;], [&quot; &quot;, &quot;#&quot;, &quot;c&quot;], [&quot; &quot;, &quot;#&quot;, &quot;a&quot;]], word = &quot;ac&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to place the word because there will always be a space/letter above or below it.</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex3-1.png" style="width: 478px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot;#&quot;, &quot; &quot;, &quot;#&quot;], [&quot; &quot;, &quot; &quot;, &quot;#&quot;], [&quot;#&quot;, &quot; &quot;, &quot;c&quot;]], word = &quot;ca&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;ca&quot; can be placed as shown above (right to left). 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>board[i][j]</code> will be <code>&#39; &#39;</code>, <code>&#39;#&#39;</code>, or a lowercase English letter.</li>
	<li><code>1 &lt;= word.length &lt;= max(m, n)</code></li>
	<li><code>word</code> will contain only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def placeWordInCrossword(self, board: List[List[str]], word: str) -> bool:
        def check(i, j, a, b):
            x, y = i + a * k, j + b * k
            if 0 <= x < m and 0 <= y < n and board[x][y] != '#':
                return False
            for c in word:
                if (
                    i < 0
                    or i >= m
                    or j < 0
                    or j >= n
                    or (board[i][j] != ' ' and board[i][j] != c)
                ):
                    return False
                i, j = i + a, j + b
            return True

        m, n = len(board), len(board[0])
        k = len(word)
        for i in range(m):
            for j in range(n):
                left_to_right = (j == 0 or board[i][j - 1] == '#') and check(i, j, 0, 1)
                right_to_left = (j == n - 1 or board[i][j + 1] == '#') and check(
                    i, j, 0, -1
                )
                up_to_down = (i == 0 or board[i - 1][j] == '#') and check(i, j, 1, 0)
                down_to_up = (i == m - 1 or board[i + 1][j] == '#') and check(
                    i, j, -1, 0
                )
                if left_to_right or right_to_left or up_to_down or down_to_up:
                    return True
        return False
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private char[][] board;
    private String word;
    private int k;

    public boolean placeWordInCrossword(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        this.word = word;
        k = word.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean leftToRight = (j == 0 || board[i][j - 1] == '#') && check(i, j, 0, 1);
                boolean rightToLeft = (j == n - 1 || board[i][j + 1] == '#') && check(i, j, 0, -1);
                boolean upToDown = (i == 0 || board[i - 1][j] == '#') && check(i, j, 1, 0);
                boolean downToUp = (i == m - 1 || board[i + 1][j] == '#') && check(i, j, -1, 0);
                if (leftToRight || rightToLeft || upToDown || downToUp) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int i, int j, int a, int b) {
        int x = i + a * k, y = j + b * k;
        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
            return false;
        }
        for (int p = 0; p < k; ++p) {
            if (i < 0 || i >= m || j < 0 || j >= n
                || (board[i][j] != ' ' && board[i][j] != word.charAt(p))) {
                return false;
            }
            i += a;
            j += b;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool placeWordInCrossword(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        int k = word.size();
        auto check = [&](int i, int j, int a, int b) {
            int x = i + a * k, y = j + b * k;
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
                return false;
            }
            for (char& c : word) {
                if (i < 0 || i >= m || j < 0 || j >= n || (board[i][j] != ' ' && board[i][j] != c)) {
                    return false;
                }
                i += a;
                j += b;
            }
            return true;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                bool leftToRight = (j == 0 || board[i][j - 1] == '#') && check(i, j, 0, 1);
                bool rightToLeft = (j == n - 1 || board[i][j + 1] == '#') && check(i, j, 0, -1);
                bool upToDown = (i == 0 || board[i - 1][j] == '#') && check(i, j, 1, 0);
                bool downToUp = (i == m - 1 || board[i + 1][j] == '#') && check(i, j, -1, 0);
                if (leftToRight || rightToLeft || upToDown || downToUp) {
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
func placeWordInCrossword(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	k := len(word)
	check := func(i, j, a, b int) bool {
		x, y := i+a*k, j+b*k
		if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#' {
			return false
		}
		for _, c := range word {
			if i < 0 || i >= m || j < 0 || j >= n || (board[i][j] != ' ' && board[i][j] != byte(c)) {
				return false
			}
			i, j = i+a, j+b
		}
		return true
	}
	for i := range board {
		for j := range board[i] {
			leftToRight := (j == 0 || board[i][j-1] == '#') && check(i, j, 0, 1)
			rightToLeft := (j == n-1 || board[i][j+1] == '#') && check(i, j, 0, -1)
			upToDown := (i == 0 || board[i-1][j] == '#') && check(i, j, 1, 0)
			downToUp := (i == m-1 || board[i+1][j] == '#') && check(i, j, -1, 0)
			if leftToRight || rightToLeft || upToDown || downToUp {
				return true
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
