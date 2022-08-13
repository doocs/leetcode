# [999. 可以被一步捕获的棋子数](https://leetcode.cn/problems/available-captures-for-rook)

[English Version](/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 8 x 8 的棋盘上，有一个白色的车（<code>Rook</code>），用字符 <code>&#39;R&#39;</code> 表示。棋盘上还可能存在空方块，白色的象（<code>Bishop</code>）以及黑色的卒（<code>pawn</code>），分别用字符 <code>&#39;.&#39;</code>，<code>&#39;B&#39;</code> 和 <code>&#39;p&#39;</code> 表示。不难看出，大写字符表示的是白棋，小写字符表示的是黑棋。</p>

<p>车按国际象棋中的规则移动。东，西，南，北四个基本方向任选其一，然后一直向选定的方向移动，直到满足下列四个条件之一：</p>

<ul>
	<li>棋手选择主动停下来。</li>
	<li>棋子因到达棋盘的边缘而停下。</li>
	<li>棋子移动到某一方格来捕获位于该方格上敌方（黑色）的卒，停在该方格内。</li>
	<li>车不能进入/越过已经放有其他友方棋子（白色的象）的方格，停在友方棋子前。</li>
</ul>

<p>你现在可以控制车移动一次，请你统计有多少敌方的卒处于你的捕获范围内（即，可以被一步捕获的棋子数）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_1_improved.png" style="height: 305px; width: 300px;"></p>

<pre><strong>输入：</strong>[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>输出：</strong>3
<strong>解释：
</strong>在本例中，车能够捕获所有的卒。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_2_improved.png" style="height: 306px; width: 300px;"></p>

<pre><strong>输入：</strong>[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;R&quot;,&quot;B&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>输出：</strong>0
<strong>解释：
</strong>象阻止了车捕获任何卒。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_3_improved.png" style="height: 305px; width: 300px;"></p>

<pre><strong>输入：</strong>[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>输出：</strong>3
<strong>解释： </strong>
车可以捕获位置 b5，d6 和 f5 的卒。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>board.length == board[i].length == 8</code></li>
	<li><code>board[i][j]</code> 可以是&nbsp;<code>&#39;R&#39;</code>，<code>&#39;.&#39;</code>，<code>&#39;B&#39;</code>&nbsp;或&nbsp;<code>&#39;p&#39;</code></li>
	<li>只有一个格子上存在&nbsp;<code>board[i][j] == &#39;R&#39;</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先找到 R 的位置，之后向“上、下、左、右”四个方向查找，累加结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        x, y, n = 0, 0, 8
        for i in range(n):
            for j in range(n):
                if board[i][j] == 'R':
                    x, y = i, j
                    break
        ans = 0
        for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
            i, j = x, y
            while 0 <= i + a < n and 0 <= j + b < n and board[i + a][j + b] != 'B':
                i, j = i + a, j + b
                if board[i][j] == 'p':
                    ans += 1
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int[] pos = find(board);
        int ans = 0, n = 8;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    private int[] find(char[][] board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numRookCaptures(vector<vector<char>>& board) {
        vector<int> pos = find(board);
        int ans = 0, n = 8;
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    vector<int> find(vector<vector<char>>& board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return {i, j};
                }
            }
        }
        return {};
    }
};
```

### **Go**

```go
func numRookCaptures(board [][]byte) int {
	n := 8

	find := func() []int {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if board[i][j] == 'R' {
					return []int{i, j}
				}
			}
		}
		return []int{}
	}

	pos := find()
	ans := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, dir := range dirs {
		x, y, a, b := pos[0], pos[1], dir[0], dir[1]
		for x+a >= 0 && x+a < n && y+b >= 0 && y+b < n && board[x+a][y+b] != 'B' {
			x += a
			y += b
			if board[x][y] == 'p' {
				ans++
				break
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
