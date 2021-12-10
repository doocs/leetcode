# [419. 甲板上的战舰](https://leetcode-cn.com/problems/battleships-in-a-board)

[English Version](/solution/0400-0499/0419.Battleships%20in%20a%20Board/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维的甲板， 请计算其中有多少艘战舰。&nbsp;战舰用&nbsp;<code>&#39;X&#39;</code>表示，空位用&nbsp;<code>&#39;.&#39;</code>表示。&nbsp;你需要遵守以下规则：</p>

<ul>
	<li>给你一个有效的甲板，仅由战舰或者空位组成。</li>
	<li>战舰只能水平或者垂直放置。换句话说,战舰只能由&nbsp;<code>1xN</code> (1 行, N 列)组成，或者&nbsp;<code>Nx1</code> (N 行, 1 列)组成，其中N可以是任意大小。</li>
	<li>两艘战舰之间至少有一个水平或垂直的空位分隔&nbsp;- 即没有相邻的战舰。</li>
</ul>

<p><strong>示例 :</strong></p>

<pre>
X..X
...X
...X
</pre>

<p>在上面的甲板中有2艘战舰。</p>

<p><strong>无效样例 :</strong></p>

<pre>
...X
XXXX
...X
</pre>

<p>你不会收到这样的无效甲板&nbsp;- 因为战舰之间至少会有一个空位将它们分开。</p>

<p><strong>进阶:</strong></p>

<p>你可以用<strong>一次扫描算法</strong>，只使用<strong>O(1)额外空间，</strong>并且<strong>不修改</strong>甲板的值来解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

初始化结果数 ans = 0。

遍历二维甲板，若 X 的左方、上方不为 X，则结果 ans 加 1。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countBattleships(self, board: List[List[str]]) -> int:
        m, n = len(board), len(board[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                if board[i][j] == '.':
                    continue
                if i > 0 and board[i - 1][j] == 'X':
                    continue
                if j > 0 and board[i][j - 1] == 'X':
                    continue
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countBattleships(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i - 1][j] == 'X') continue;
                if (j > 0 && board[i][j - 1] == 'X') continue;
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countBattleships(board [][]byte) int {
	m, n := len(board), len(board[0])
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == '.' {
				continue
			}
			if i > 0 && board[i-1][j] == 'X' {
				continue
			}
			if j > 0 && board[i][j-1] == 'X' {
				continue
			}
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
