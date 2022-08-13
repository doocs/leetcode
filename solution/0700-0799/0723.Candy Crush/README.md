# [723. 粉碎糖果](https://leetcode.cn/problems/candy-crush)

[English Version](/solution/0700-0799/0723.Candy%20Crush/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这个问题是实现一个简单的消除算法。</p>

<p>给定一个&nbsp;<code>m x n</code>&nbsp;的二维整数数组 <code>board</code> 代表糖果所在的方格，不同的正整数 <code>board[i][j]</code> 代表不同种类的糖果，如果 <code>board[i][j] == 0</code> 代表&nbsp;<code>(i, j)</code> 这个位置是空的。</p>

<p>给定的方格是玩家移动后的游戏状态，现在需要你根据以下规则粉碎糖果，使得整个方格处于稳定状态并最终输出：</p>

<ul>
	<li>如果有三个及以上水平或者垂直相连的同种糖果，同一时间将它们粉碎，即将这些位置变成空的。</li>
	<li>在同时粉碎掉这些糖果之后，如果有一个空的位置上方还有糖果，那么上方的糖果就会下落直到碰到下方的糖果或者底部，这些糖果都是同时下落，也不会有新的糖果从顶部出现并落下来。</li>
	<li>通过前两步的操作，可能又会出现可以粉碎的糖果，请继续重复前面的操作。</li>
	<li>当不存在可以粉碎的糖果，也就是状态稳定之后，请输出最终的状态。</li>
</ul>

<p>你需要模拟上述规则并使整个方格达到稳定状态，并输出。</p>

<p>&nbsp;</p>

<p><strong>示例 1 :</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0723.Candy%20Crush/images/candy_crush_example_2.png" style="height: 411px; width: 600px;" /></p>

<pre>
<strong>输入: </strong>board = [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
<strong>输出: </strong>[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> board = [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
<strong>输出:</strong> [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>3 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= board[i][j] &lt;= 2000</code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

循环消除，先标记每一行需要消除的元素，再标记每一列需要消除的元素（使用元素的负相反数进行标记），然后执行消除。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def candyCrush(self, board: List[List[int]]) -> List[List[int]]:
        m, n = len(board), len(board[0])
        run = True
        while run:
            run = False
            for i in range(m):
                for j in range(n - 2):
                    if (
                        board[i][j] != 0
                        and abs(board[i][j]) == abs(board[i][j + 1])
                        and abs(board[i][j]) == abs(board[i][j + 2])
                    ):
                        run = True
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -abs(
                            board[i][j]
                        )
            for j in range(n):
                for i in range(m - 2):
                    if (
                        board[i][j] != 0
                        and abs(board[i][j]) == abs(board[i + 1][j])
                        and abs(board[i][j]) == abs(board[i + 2][j])
                    ):
                        run = True
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -abs(
                            board[i][j]
                        )
            if run:
                for j in range(n):
                    curr = m - 1
                    for i in range(m - 1, -1, -1):
                        if board[i][j] > 0:
                            board[curr][j] = board[i][j]
                            curr -= 1
                    while curr > -1:
                        board[curr][j] = 0
                        curr -= 1
        return board
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean run = true;
        while (run) {
            run = false;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n - 2; ++j) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i][j + 1]) && Math.abs(board[i][j]) == Math.abs(board[i][j + 2])) {
                        run = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -Math.abs(board[i][j]);
                    }
                }
            }
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < m - 2; ++i) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i + 1][j]) && Math.abs(board[i][j]) == Math.abs(board[i + 2][j])) {
                        run = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -Math.abs(board[i][j]);
                    }
                }
            }
            if (run) {
                for (int j = 0; j < n; ++j) {
                    int curr = m - 1;
                    for (int i = m - 1; i >= 0; --i) {
                        if (board[i][j] > 0) {
                            board[curr][j] = board[i][j];
                            --curr;
                        }
                    }
                    while (curr > -1) {
                        board[curr][j] = 0;
                        --curr;
                    }
                }
            }
        }
        return board;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> candyCrush(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        bool run = true;
        while (run) {
            run = false;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n - 2; ++j) {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j + 1]) && abs(board[i][j]) == abs(board[i][j + 2])) {
                        run = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -abs(board[i][j]);
                    }
                }
            }
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < m - 2; ++i) {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i + 1][j]) && abs(board[i][j]) == abs(board[i + 2][j])) {
                        run = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -abs(board[i][j]);
                    }
                }
            }
            if (run) {
                for (int j = 0; j < n; ++j) {
                    int curr = m - 1;
                    for (int i = m - 1; i >= 0; --i) {
                        if (board[i][j] > 0) {
                            board[curr][j] = board[i][j];
                            --curr;
                        }
                    }
                    while (curr > -1) {
                        board[curr][j] = 0;
                        --curr;
                    }
                }
            }
        }
        return board;
    }
};
```

### **Go**

```go
func candyCrush(board [][]int) [][]int {
	m, n := len(board), len(board[0])
	run := true
	for run {
		run = false
		for i := 0; i < m; i++ {
			for j := 0; j < n-2; j++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j+1]) && abs(board[i][j]) == abs(board[i][j+2]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i][j+1], board[i][j+2] = t, t, t
				}
			}
		}
		for j := 0; j < n; j++ {
			for i := 0; i < m-2; i++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i+1][j]) && abs(board[i][j]) == abs(board[i+2][j]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i+1][j], board[i+2][j] = t, t, t
				}
			}
		}
		if run {
			for j := 0; j < n; j++ {
				curr := m - 1
				for i := m - 1; i >= 0; i-- {
					if board[i][j] > 0 {
						board[curr][j] = board[i][j]
						curr--
					}
				}
				for curr > -1 {
					board[curr][j] = 0
					curr--
				}
			}
		}
	}
	return board
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
