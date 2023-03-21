# [1275. 找出井字棋的获胜者](https://leetcode.cn/problems/find-winner-on-a-tic-tac-toe-game)

[English Version](/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><em>A</em> 和&nbsp;<em>B</em>&nbsp;在一个&nbsp;<em>3</em>&nbsp;x&nbsp;<em>3</em>&nbsp;的网格上玩井字棋。</p>

<p>井字棋游戏的规则如下：</p>

<ul>
	<li>玩家轮流将棋子放在空方格 (&quot; &quot;) 上。</li>
	<li>第一个玩家 A 总是用&nbsp;&quot;X&quot; 作为棋子，而第二个玩家 B 总是用 &quot;O&quot; 作为棋子。</li>
	<li>&quot;X&quot; 和 &quot;O&quot; 只能放在空方格中，而不能放在已经被占用的方格上。</li>
	<li>只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。</li>
	<li>如果所有方块都放满棋子（不为空），游戏也会结束。</li>
	<li>游戏结束后，棋子无法再进行任何移动。</li>
</ul>

<p>给你一个数组 <code>moves</code>，其中每个元素是大小为 <code>2</code> 的另一个数组（元素分别对应网格的行和列），它按照 <em>A</em> 和 <em>B</em> 的行动顺序（先 <em>A</em> 后 <em>B</em>）记录了两人各自的棋子位置。</p>

<p>如果游戏存在获胜者（<em>A</em> 或 <em>B</em>），就返回该游戏的获胜者；如果游戏以平局结束，则返回 &quot;Draw&quot;；如果仍会有行动（游戏未结束），则返回 &quot;Pending&quot;。</p>

<p>你可以假设&nbsp;<code>moves</code>&nbsp;都 <strong>有效</strong>（遵循井字棋规则），网格最初是空的，<em>A</em> 将先行动。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
<strong>输出：</strong>&quot;A&quot;
<strong>解释：</strong>&quot;A&quot; 获胜，他总是先走。
&quot;X  &quot;    &quot;X  &quot;    &quot;X  &quot;    &quot;X  &quot;    &quot;<strong>X</strong>  &quot;
&quot;   &quot; -&gt; &quot;   &quot; -&gt; &quot; X &quot; -&gt; &quot; X &quot; -&gt; &quot; <strong>X</strong> &quot;
&quot;   &quot;    &quot;O  &quot;    &quot;O  &quot;    &quot;OO &quot;    &quot;OO<strong>X</strong>&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
<strong>输出：</strong>&quot;B&quot;
<strong>解释：</strong>&quot;B&quot; 获胜。
&quot;X  &quot;    &quot;X  &quot;    &quot;XX &quot;    &quot;XXO&quot;    &quot;XXO&quot;    &quot;XX<strong>O</strong>&quot;
&quot;   &quot; -&gt; &quot; O &quot; -&gt; &quot; O &quot; -&gt; &quot; O &quot; -&gt; &quot;XO &quot; -&gt; &quot;X<strong>O</strong> &quot; 
&quot;   &quot;    &quot;   &quot;    &quot;   &quot;    &quot;   &quot;    &quot;   &quot;    &quot;<strong>O</strong>  &quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
<strong>输出：</strong>&quot;Draw&quot;
<strong>输出：</strong>由于没有办法再行动，游戏以平局结束。
&quot;XXO&quot;
&quot;OOX&quot;
&quot;XOX&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>moves = [[0,0],[1,1]]
<strong>输出：</strong>&quot;Pending&quot;
<strong>解释：</strong>游戏还没有结束。
&quot;X  &quot;
&quot; O &quot;
&quot;   &quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 9</code></li>
	<li><code>moves[i].length == 2</code></li>
	<li><code>0 &lt;= moves[i][j] &lt;= 2</code></li>
	<li><code>moves</code>&nbsp;里没有重复的元素。</li>
	<li><code>moves</code> 遵循井字棋的规则。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：判断最后一个落棋的人能否获胜**

由于 `moves` 都有效，也即是说，不存在某个人获胜后，其他人仍然落棋的情况。因此，只需判断最后一个落棋的人能否获胜即可。

我们用一个长度为 $8$ 的数组 `cnt` 记录行、列以及对角线的落棋次数。其中 $cnt[0, 1, 2]$ 分别表示第 $0, 1, 2$ 行的落棋次数，而 $cnt[3, 4, 5]$ 分别表示第 $0, 1, 2$ 列的落棋次数，另外 $cnt[6]$ 和 $cnt[7]$ 分别表示两条对角线的落棋次数。落棋过程中，如果某个人在某一行、列或对角线上落棋次数达到 $3$ 次，则该人获胜。

如果最后一个落棋的人没有获胜，那么我们判断棋盘是否已满，如果已满，则平局；否则，游戏尚未结束。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `moves` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def tictactoe(self, moves: List[List[int]]) -> str:
        n = len(moves)
        cnt = [0] * 8
        for k in range(n - 1, -1, -2):
            i, j = moves[k]
            cnt[i] += 1
            cnt[j + 3] += 1
            if i == j:
                cnt[6] += 1
            if i + j == 2:
                cnt[7] += 1
            if any(v == 3 for v in cnt):
                return "B" if k & 1 else "A"
        return "Draw" if n == 9 else "Pending"
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int[] cnt = new int[8];
        for (int k = n - 1; k >= 0; k -= 2) {
            int i = moves[k][0], j = moves[k][1];
            cnt[i]++;
            cnt[j + 3]++;
            if (i == j) {
                cnt[6]++;
            }
            if (i + j == 2) {
                cnt[7]++;
            }
            if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
                return k % 2 == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string tictactoe(vector<vector<int>>& moves) {
        int n = moves.size();
        int cnt[8]{};
        for (int k = n - 1; k >= 0; k -= 2) {
            int i = moves[k][0], j = moves[k][1];
            cnt[i]++;
            cnt[j + 3]++;
            if (i == j) {
                cnt[6]++;
            }
            if (i + j == 2) {
                cnt[7]++;
            }
            if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
                return k % 2 == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
};
```

### **Go**

```go
func tictactoe(moves [][]int) string {
	n := len(moves)
	cnt := [8]int{}
	for k := n - 1; k >= 0; k -= 2 {
		i, j := moves[k][0], moves[k][1]
		cnt[i]++
		cnt[j+3]++
		if i == j {
			cnt[6]++
		}
		if i+j == 2 {
			cnt[7]++
		}
		if cnt[i] == 3 || cnt[j+3] == 3 || cnt[6] == 3 || cnt[7] == 3 {
			if k%2 == 0 {
				return "A"
			}
			return "B"
		}
	}
	if n == 9 {
		return "Draw"
	}
	return "Pending"
}
```

### **TypeScript**

```ts
function tictactoe(moves: number[][]): string {
    const n = moves.length;
    const cnt = new Array(8).fill(0);
    for (let k = n - 1; k >= 0; k -= 2) {
        const [i, j] = moves[k];
        cnt[i]++;
        cnt[j + 3]++;
        if (i == j) {
            cnt[6]++;
        }
        if (i + j == 2) {
            cnt[7]++;
        }
        if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
            return k % 2 == 0 ? 'A' : 'B';
        }
    }
    return n == 9 ? 'Draw' : 'Pending';
}
```

### **...**

```

```

<!-- tabs:end -->
