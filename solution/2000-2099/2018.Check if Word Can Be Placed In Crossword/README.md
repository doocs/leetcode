# [2018. 判断单词是否能放入填字游戏内](https://leetcode.cn/problems/check-if-word-can-be-placed-in-crossword)

[English Version](/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的矩阵&nbsp;<code>board</code>&nbsp;，它代表一个填字游戏&nbsp;<strong>当前</strong>&nbsp;的状态。填字游戏格子中包含小写英文字母（已填入的单词），表示&nbsp;<strong>空</strong>&nbsp;格的&nbsp;<code>' '</code>&nbsp;和表示&nbsp;<strong>障碍</strong>&nbsp;格子的&nbsp;<code>'#'</code>&nbsp;。</p>

<p>如果满足以下条件，那么我们可以 <strong>水平</strong>&nbsp;（从左到右 <strong>或者</strong>&nbsp;从右到左）或 <strong>竖直</strong>&nbsp;（从上到下 <strong>或者</strong>&nbsp;从下到上）填入一个单词：</p>

<ul>
	<li>该单词不占据任何&nbsp;<code>'#'</code>&nbsp;对应的格子。</li>
	<li>每个字母对应的格子要么是&nbsp;<code>' '</code>&nbsp;（空格）要么与 <code>board</code>&nbsp;中已有字母 <strong>匹配</strong>&nbsp;。</li>
	<li>如果单词是 <strong>水平</strong>&nbsp;放置的，那么该单词左边和右边 <strong>相邻</strong>&nbsp;格子不能为&nbsp;<code>' '</code>&nbsp;或小写英文字母。</li>
	<li>如果单词是&nbsp;<strong>竖直</strong>&nbsp;放置的，那么该单词上边和下边&nbsp;<strong>相邻</strong><strong>&nbsp;</strong>格子不能为&nbsp;<code>' '</code>&nbsp;或小写英文字母。</li>
</ul>

<p>给你一个字符串&nbsp;<code>word</code>&nbsp;，如果&nbsp;<code>word</code>&nbsp;可以被放入&nbsp;<code>board</code>&nbsp;中，请你返回&nbsp;<code>true</code>&nbsp;，否则请返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-1.png" style="width: 170px; height: 150px;" /></p>

<pre>
<b>输入：</b>board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
<b>输出：</b>true
<b>解释：</b>单词 "abc" 可以如上图放置（从上往下）。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/c2.png" style="width: 170px; height: 151px;" /></p>

<pre>
<b>输入：</b>board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
<b>输出：</b>false
<b>解释：</b>无法放置单词，因为放置该单词后上方或者下方相邻格会有空格。</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-2.png" style="width: 171px; height: 146px;" /></p>

<pre>
<b>输入：</b>board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
<b>输出：</b>true
<b>解释：</b>单词 "ca" 可以如上图放置（从右到左）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>board[i][j]</code>&nbsp;可能为&nbsp;<code>' '</code>&nbsp;，<code>'#'</code>&nbsp;或者一个小写英文字母。</li>
	<li><code>1 &lt;= word.length &lt;= max(m, n)</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们可以枚举矩阵的每个位置 $(i, j)$，判断是否能以该位置为起点，从左到右或从右到左放置单词 `word`，或者从上到下或从下到上放置单词 `word`。

该位置能作为起点需要满足以下条件：

1. 如果要从从左到右放置单词 `word`，那么该位置必须是左边界，或者该位置左边的格子 `board[i][j - 1]` 是 `'#'`；
2. 如果要从从右到左放置单词 `word`，那么该位置必须是右边界，或者该位置右边的格子 `board[i][j + 1]` 是 `'#'`；
3. 如果要从从上到下放置单词 `word`，那么该位置必须是上边界，或者该位置上边的格子 `board[i - 1][j]` 是 `'#'`；
4. 如果要从从下到上放置单词 `word`，那么该位置必须是下边界，或者该位置下边的格子 `board[i + 1][j]` 是 `'#'`。

在满足上述条件的情况下，我们可以从该位置开始，判断是否能放置单词 `word`。我们设计一个函数 $check(i, j, a, b)$，表示从位置 $(i, j)$ 开始，沿着方向 $(a, b)$ 放置单词 `word` 是否合法。如果合法，返回 `true`，否则返回 `false`。

函数 $check(i, j, a, b)$ 的实现如下：

我们先获取当前方向的另一个边界位置 $(x, y)$，即 $(x, y) = (i + a \times k, j + b \times k)$，其中 $k$ 为单词 `word` 的长度。如果 $(x, y)$ 在矩阵内，并且 $(x, y)$ 的格子不是 `'#'`，则说明当前方向的另一个边界位置不是 `'#'`，因此不能放置单词 `word`，返回 `false`。

否则，我们从位置 $(i, j)$ 开始，沿着方向 $(a, b)$ 遍历单词 `word`，如果遇到格子 `board[i][j]` 不是空格或者不是单词 `word` 的当前字符，说明不能放置单词 `word`，返回 `false`。如果遍历完单词 `word`，说明能放置单词 `word`，返回 `true`。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
