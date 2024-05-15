---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.04.Tic-Tac-Toe/README.md
---

# [面试题 16.04. 井字游戏](https://leetcode.cn/problems/tic-tac-toe-lcci)

[English Version](/lcci/16.04.Tic-Tac-Toe/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符&quot; &quot;，&quot;X&quot;和&quot;O&quot;组成，其中字符&quot; &quot;代表一个空位。</p>
<p>以下是井字游戏的规则：</p>
<ul>
	<li>玩家轮流将字符放入空位（&quot; &quot;）中。</li>
	<li>第一个玩家总是放字符&quot;O&quot;，且第二个玩家总是放字符&quot;X&quot;。</li>
	<li>&quot;X&quot;和&quot;O&quot;只允许放置在空位中，不允许对已放有字符的位置进行填充。</li>
	<li>当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。</li>
	<li>当所有位置非空时，也算为游戏结束。</li>
	<li>如果游戏结束，玩家不允许再放置字符。</li>
</ul>
<p>如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（&quot;X&quot;或&quot;O&quot;）；如果游戏以平局结束，则返回 &quot;Draw&quot;；如果仍会有行动（游戏未结束），则返回 &quot;Pending&quot;。</p>
<p><strong>示例 1：</strong></p>
<pre><strong>输入：</strong> board = [&quot;O X&quot;,&quot; XO&quot;,&quot;X O&quot;]
<strong>输出：</strong> &quot;X&quot;
</pre>
<p><strong>示例 2：</strong></p>
<pre><strong>输入：</strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OXO&quot;]
<strong>输出：</strong> &quot;Draw&quot;
<strong>解释：</strong> 没有玩家获胜且不存在空位
</pre>
<p><strong>示例 3：</strong></p>
<pre><strong>输入：</strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OX &quot;]
<strong>输出：</strong> &quot;Pending&quot;
<strong>解释：</strong> 没有玩家获胜且仍存在空位
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>1 &lt;= board.length == board[i].length &lt;= 100</code></li>
	<li>输入一定遵循井字棋规则</li>
</ul>

## 解法

### 方法一：计数

对于每个格子，如果是 `X`，我们不妨将计数加 $1$，如果是 `O`，我们不妨将计数减 $1$。那么当某个格子所在的行、列或者对角线的计数的绝对值等于 $n$ 时，说明当前玩家在该行、列或者对角线上放置了 $n$ 个相同字符，游戏结束，返回对应的字符即可。

具体地，我们用一个长度为 $n$ 的一维数组 $rows$ 和 $cols$ 分别表示每一行和每一列的计数，用 $dg$ 和 $udg$ 分别表示两个对角线的计数。当玩家放置一个字符到 $(i, j)$ 时，根据字符是 `X` 还是 `O`，更新数组 $rows$，$cols$，$dg$ 以及 $udg$ 中对应元素的值。每次更新后，我们判断对应的元素的值的绝对值是否等于 $n$，如果等于 $n$，则说明当前玩家在行、列或者对角线上放置了 $n$ 个相同字符，游戏结束，返回对应的字符即可。

最后，我们遍历整个棋盘，如果棋盘中存在字符 ` `，说明游戏还未结束，返回 `Pending`，否则返回 `Draw`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是棋盘的边长。

<!-- tabs:start -->

```python
class Solution:
    def tictactoe(self, board: List[str]) -> str:
        n = len(board)
        rows = [0] * n
        cols = [0] * n
        dg = udg = 0
        has_empty_grid = False
        for i, row in enumerate(board):
            for j, c in enumerate(row):
                v = 1 if c == 'X' else -1
                if c == ' ':
                    has_empty_grid = True
                    v = 0
                rows[i] += v
                cols[j] += v
                if i == j:
                    dg += v
                if i + j + 1 == n:
                    udg += v
                if (
                    abs(rows[i]) == n
                    or abs(cols[j]) == n
                    or abs(dg) == n
                    or abs(udg) == n
                ):
                    return c
        return 'Pending' if has_empty_grid else 'Draw'
```

```java
class Solution {
    public String tictactoe(String[] board) {
        int n = board.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int dg = 0, udg = 0;
        boolean hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    hasEmptyGrid = true;
                    continue;
                }
                int v = c == 'X' ? 1 : -1;
                rows[i] += v;
                cols[j] += v;
                if (i == j) {
                    dg += v;
                }
                if (i + j + 1 == n) {
                    udg += v;
                }
                if (Math.abs(rows[i]) == n || Math.abs(cols[j]) == n || Math.abs(dg) == n
                    || Math.abs(udg) == n) {
                    return String.valueOf(c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
}
```

```cpp
class Solution {
public:
    string tictactoe(vector<string>& board) {
        int n = board.size();
        vector<int> rows(n), cols(n);
        int dg = 0, udg = 0;
        bool hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i][j];
                if (c == ' ') {
                    hasEmptyGrid = true;
                    continue;
                }
                int v = c == 'X' ? 1 : -1;
                rows[i] += v;
                cols[j] += v;
                if (i == j) {
                    dg += v;
                }
                if (i + j + 1 == n) {
                    udg += v;
                }
                if (abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n) {
                    return string(1, c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
};
```

```go
func tictactoe(board []string) string {
	n := len(board)
	rows := make([]int, n)
	cols := make([]int, n)
	dg, udg := 0, 0
	hasEmptyGrid := false
	for i, row := range board {
		for j, c := range row {
			if c == ' ' {
				hasEmptyGrid = true
				continue
			}
			v := 1
			if c == 'O' {
				v = -1
			}
			rows[i] += v
			cols[j] += v
			if i == j {
				dg += v
			}
			if i+j == n-1 {
				udg += v
			}
			if abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n {
				return string(c)
			}
		}
	}
	if hasEmptyGrid {
		return "Pending"
	}
	return "Draw"
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function tictactoe(board: string[]): string {
    const n = board.length;
    const rows = Array(n).fill(0);
    const cols = Array(n).fill(0);
    let [dg, udg] = [0, 0];
    let hasEmptyGrid = false;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = board[i][j];
            if (c === ' ') {
                hasEmptyGrid = true;
                continue;
            }
            const v = c === 'X' ? 1 : -1;
            rows[i] += v;
            cols[j] += v;
            if (i === j) {
                dg += v;
            }
            if (i + j === n - 1) {
                udg += v;
            }
            if (
                Math.abs(rows[i]) === n ||
                Math.abs(cols[j]) === n ||
                Math.abs(dg) === n ||
                Math.abs(udg) === n
            ) {
                return c;
            }
        }
    }
    return hasEmptyGrid ? 'Pending' : 'Draw';
}
```

```swift
class Solution {
    func tictactoe(_ board: [String]) -> String {
        let n = board.count
        var rows = Array(repeating: 0, count: n)
        var cols = Array(repeating: 0, count: n)
        var diagonal = 0, antiDiagonal = 0
        var hasEmptyGrid = false

        for i in 0..<n {
            for j in 0..<n {
                let c = Array(board[i])[j]
                if c == " " {
                    hasEmptyGrid = true
                    continue
                }
                let value = c == "X" ? 1 : -1
                rows[i] += value
                cols[j] += value
                if i == j {
                    diagonal += value
                }
                if i + j == n - 1 {
                    antiDiagonal += value
                }
                if abs(rows[i]) == n || abs(cols[j]) == n || abs(diagonal) == n || abs(antiDiagonal) == n {
                    return String(c)
                }
            }
        }

        return hasEmptyGrid ? "Pending" : "Draw"
    }
}
```

<!-- tabs:end -->

<!-- end -->
