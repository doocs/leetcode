# [909. 蛇梯棋](https://leetcode.cn/problems/snakes-and-ladders)

[English Version](/solution/0900-0999/0909.Snakes%20and%20Ladders/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n x n</code> 的整数矩阵 <code>board</code> ，方格按从&nbsp;<code>1</code> 到 <code>n<sup>2</sup></code> 编号，编号遵循 <a href="https://baike.baidu.com/item/%E7%89%9B%E8%80%95%E5%BC%8F%E8%BD%AC%E8%A1%8C%E4%B9%A6%E5%86%99%E6%B3%95/17195786">转行交替方式</a><strong> </strong>，<strong>从左下角开始</strong>&nbsp;（即，从 <code>board[n - 1][0]</code> 开始）每一行交替方向。</p>

<p>玩家从棋盘上的方格&nbsp;<code>1</code> （总是在最后一行、第一列）开始出发。</p>

<p>每一回合，玩家需要从当前方格 <code>curr</code> 开始出发，按下述要求前进：</p>

<ul>
	<li>选定目标方格 <code>next</code> ，目标方格的编号符合范围&nbsp;<code>[curr + 1, min(curr + 6, n<sup>2</sup>)]</code> 。
    <ul>
    	<li>该选择模拟了掷 <strong>六面体骰子</strong> 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。</li>
    </ul>
    </li>
    <li>传送玩家：如果目标方格 <code>next</code> 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 <code>next</code> 。&nbsp;</li>
    <li>当玩家到达编号 <code>n<sup>2</sup></code> 的方格时，游戏结束。</li>
</ul>

<p><code>r</code> 行 <code>c</code> 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 <code>board[r][c] != -1</code>，那个蛇或梯子的目的地将会是 <code>board[r][c]</code>。编号为 <code>1</code> 和 <code>n<sup>2</sup></code> 的方格上没有蛇或梯子。</p>

<p>注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 <strong>不能</strong> 继续移动。</p>

<ul>
	<li>举个例子，假设棋盘是 <code>[[-1,4],[-1,3]]</code> ，第一次移动，玩家的目标方格是 <code>2</code> 。那么这个玩家将会顺着梯子到达方格 <code>3</code> ，但 <strong>不能</strong> 顺着方格 <code>3</code> 上的梯子前往方格 <code>4</code> 。</li>
</ul>

<p>返回达到编号为&nbsp;<code>n<sup>2</sup></code> 的方格所需的最少移动次数，如果不可能，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0909.Snakes%20and%20Ladders/images/snakes.png" style="width: 500px; height: 394px;" />
<pre>
<strong>输入：</strong>board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
<strong>输出：</strong>4
<strong>解释：</strong>
首先，从方格 1 [第 5 行，第 0 列] 开始。 
先决定移动到方格 2 ，并必须爬过梯子移动到到方格 15 。
然后决定移动到方格 17 [第 3 行，第 4 列]，必须爬过蛇到方格 13 。
接着决定移动到方格 14 ，且必须通过梯子移动到方格 35 。 
最后决定移动到方格 36 , 游戏结束。 
可以证明需要至少 4 次移动才能到达最后一个方格，所以答案是 4 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [[-1,-1],[-1,3]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == board.length == board[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>grid[i][j]</code> 的值是 <code>-1</code> 或在范围 <code>[1, n<sup>2</sup>]</code> 内</li>
	<li>编号为 <code>1</code> 和 <code>n<sup>2</sup></code> 的方格上没有蛇或梯子</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        def get(x):
            i, j = (x - 1) // n, (x - 1) % n
            if i & 1:
                j = n - 1 - j
            return n - 1 - i, j

        n = len(board)
        q = deque([1])
        vis = {1}
        ans = 0
        while q:
            for _ in range(len(q)):
                curr = q.popleft()
                if curr == n * n:
                    return ans
                for next in range(curr + 1, min(curr + 7, n * n + 1)):
                    i, j = get(next)
                    if board[i][j] != -1:
                        next = board[i][j]
                    if next not in vis:
                        q.append(next)
                        vis.add(next)
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int curr = q.poll();
                if (curr == n * n) {
                    return ans;
                }
                for (int k = curr + 1; k <= Math.min(curr + 6, n * n); ++k) {
                    int[] p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) {
                        next = board[i][j];
                    }
                    if (!vis[next]) {
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private int[] get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) {
            j = n - 1 - j;
        }
        return new int[] {n - 1 - i, j};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;

    int snakesAndLadders(vector<vector<int>>& board) {
        n = board.size();
        queue<int> q {{1}};
        vector<bool> vis(n * n + 1);
        vis[1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int curr = q.front();
                if (curr == n * n) return ans;
                q.pop();
                for (int k = curr + 1; k <= min(curr + 6, n * n); ++k) {
                    auto p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) next = board[i][j];
                    if (!vis[next]) {
                        vis[next] = true;
                        q.push(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<int> get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) j = n - 1 - j;
        return {n - 1 - i, j};
    }
};
```

### **Go**

```go
func snakesAndLadders(board [][]int) int {
	n := len(board)
	get := func(x int) []int {
		i, j := (x-1)/n, (x-1)%n
		if i%2 == 1 {
			j = n - 1 - j
		}
		return []int{n - 1 - i, j}
	}
	q := []int{1}
	vis := make([]bool, n*n+1)
	vis[1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			curr := q[0]
			if curr == n*n {
				return ans
			}
			q = q[1:]
			for k := curr + 1; k <= curr+6 && k <= n*n; k++ {
				p := get(k)
				next := k
				i, j := p[0], p[1]
				if board[i][j] != -1 {
					next = board[i][j]
				}
				if !vis[next] {
					vis[next] = true
					q = append(q, next)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
