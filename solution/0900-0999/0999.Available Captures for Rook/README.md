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

**方法一：模拟**

我们先遍历棋盘，找到车的位置 $(x, y)$，然后从 $(x, y)$ 出发，向上下左右四个方向遍历：

-   如果遇到象或者边界，那么该方向停止遍历；
-   如果遇到卒，那么答案加一，然后该方向停止遍历；
-   否则，继续遍历。

遍历完四个方向后，即可得到答案。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是棋盘的行数和列数，本题中 $m = n = 8$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        for i in range(8):
            for j in range(8):
                if board[i][j] == "R":
                    for a, b in pairwise(dirs):
                        x, y = i, j
                        while 0 <= x + a < 8 and 0 <= y + b < 8:
                            x, y = x + a, y + b
                            if board[x][y] == "p":
                                ans += 1
                                break
                            if board[x][y] == "B":
                                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int k = 0; k < 4; ++k) {
                        int x = i, y = j;
                        int a = dirs[k], b = dirs[k + 1];
                        while (x + a >= 0 && x + a < 8 && y + b >= 0 && y + b < 8 && board[x + a][y + b] != 'B') {
                            x += a;
                            y += b;
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                        }
                    }
                }
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
    int numRookCaptures(vector<vector<char>>& board) {
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int k = 0; k < 4; ++k) {
                        int x = i, y = j;
                        int a = dirs[k], b = dirs[k + 1];
                        while (x + a >= 0 && x + a < 8 && y + b >= 0 && y + b < 8 && board[x + a][y + b] != 'B') {
                            x += a;
                            y += b;
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numRookCaptures(board [][]byte) (ans int) {
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := 0; i < 8; i++ {
		for j := 0; j < 8; j++ {
			if board[i][j] == 'R' {
				for k := 0; k < 4; k++ {
					x, y := i, j
					a, b := dirs[k], dirs[k+1]
					for x+a >= 0 && x+a < 8 && y+b >= 0 && y+b < 8 && board[x+a][y+b] != 'B' {
						x, y = x+a, y+b
						if board[x][y] == 'p' {
							ans++
							break
						}
					}
				}
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
