---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0909.Snakes%20and%20Ladders/README.md
tags:
    - 广度优先搜索
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [909. 蛇梯棋](https://leetcode.cn/problems/snakes-and-ladders)

[English Version](/solution/0900-0999/0909.Snakes%20and%20Ladders/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x n</code> 的整数矩阵 <code>board</code> ，方格按从&nbsp;<code>1</code> 到 <code>n<sup>2</sup></code> 编号，编号遵循 <a href="https://baike.baidu.com/item/%E7%89%9B%E8%80%95%E5%BC%8F%E8%BD%AC%E8%A1%8C%E4%B9%A6%E5%86%99%E6%B3%95/17195786">转行交替方式</a><strong> </strong>，<strong>从左下角开始</strong>&nbsp;（即，从 <code>board[n - 1][0]</code> 开始）的每一行改变方向。</p>

<p>你一开始位于棋盘上的方格&nbsp; <code>1</code>。每一回合，玩家需要从当前方格 <code>curr</code> 开始出发，按下述要求前进：</p>

<ul>
	<li>选定目标方格 <code>next</code> ，目标方格的编号在范围&nbsp;<code>[curr + 1, min(curr + 6, n<sup>2</sup>)]</code> 。

    <ul>
    	<li>该选择模拟了掷 <strong>六面体骰子</strong> 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。</li>
    </ul>
    </li>
    <li>传送玩家：如果目标方格 <code>next</code> 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 <code>next</code> 。&nbsp;</li>
    <li>当玩家到达编号 <code>n<sup>2</sup></code> 的方格时，游戏结束。</li>

</ul>

<p>如果 <code>board[r][c] != -1</code>&nbsp;，位于&nbsp;<code>r</code> 行 <code>c</code> 列的棋盘格中可能存在 “蛇” 或 “梯子”。那个蛇或梯子的目的地将会是 <code>board[r][c]</code>。编号为 <code>1</code> 和 <code>n<sup>2</sup></code> 的方格不是任何蛇或梯子的起点。</p>

<p>注意，玩家在每次掷骰的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 <strong>不能</strong> 继续移动。</p>

<ul>
	<li>举个例子，假设棋盘是 <code>[[-1,4],[-1,3]]</code> ，第一次移动，玩家的目标方格是 <code>2</code> 。那么这个玩家将会顺着梯子到达方格 <code>3</code> ，但 <strong>不能</strong> 顺着方格 <code>3</code> 上的梯子前往方格 <code>4</code> 。（简单来说，类似飞行棋，玩家掷出骰子点数后移动对应格数，遇到单向的路径（即梯子或蛇）可以直接跳到路径的终点，但如果多个路径首尾相连，也不能连续跳多个路径）</li>
</ul>

<p>返回达到编号为&nbsp;<code>n<sup>2</sup></code> 的方格所需的最少掷骰次数，如果不可能，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
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

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [[-1,-1],[-1,3]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == board.length == board[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>board[i][j]</code> 的值是 <code>-1</code> 或在范围 <code>[1, n<sup>2</sup>]</code> 内</li>
	<li>编号为 <code>1</code> 和 <code>n<sup>2</sup></code> 的方格上没有蛇或梯子</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以使用广度优先搜索的方法，从起点开始，每次向前走 1 到 6 步，然后判断是否有蛇或梯子，如果有，就走到蛇或梯子的目的地，否则就走到下一个方格。

具体地，我们使用一个队列 $\textit{q}$ 来存储当前可以到达的方格编号，初始时将编号 $1$ 放入队列。同时我们使用一个集合 $\textit{vis}$ 来记录已经到达过的方格，避免重复访问，初始时将编号 $1$ 加入集合 $\textit{vis}$。

在每一次的操作中，我们取出队首的方格编号 $x$，如果 $x$ 是终点，那么我们就可以返回当前的步数。否则我们将 $x$ 向前走 $1$ 到 $6$ 步，设新的编号为 $y$，如果 $y$ 落在棋盘外，那么我们就直接跳过。否则，我们需要找到 $y$ 对应的行和列，由于行的编号是从下到上递减的，而列的编号与行的奇偶性有关，因此我们需要进行一些计算得到 $y$ 对应的行和列。

如果 $y$ 对应的方格上有蛇或梯子，那么我们需要额外走到蛇或梯子的目的地，设其为 $z$。如果 $z$ 没有被访问过，我们就将 $z$ 加入队列和集合中，这样我们就可以继续进行广度优先搜索。

如果我们最终无法到达终点，那么我们就返回 $-1$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是棋盘的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        q = deque([1])
        vis = {1}
        ans = 0
        m = n * n
        while q:
            for _ in range(len(q)):
                x = q.popleft()
                if x == m:
                    return ans
                for y in range(x + 1, min(x + 6, m) + 1):
                    i, j = divmod(y - 1, n)
                    if i & 1:
                        j = n - j - 1
                    i = n - i - 1
                    z = y if board[i][j] == -1 else board[i][j]
                    if z not in vis:
                        vis.add(z)
                        q.append(z)
            ans += 1
        return -1
```

#### Java

```java
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        int m = n * n;
        boolean[] vis = new boolean[m + 1];
        vis[1] = true;
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int x = q.poll();
                if (x == m) {
                    return ans;
                }
                for (int y = x + 1; y <= Math.min(x + 6, m); ++y) {
                    int i = (y - 1) / n, j = (y - 1) % n;
                    if (i % 2 == 1) {
                        j = n - j - 1;
                    }
                    i = n - i - 1;
                    int z = board[i][j] == -1 ? y : board[i][j];
                    if (!vis[z]) {
                        vis[z] = true;
                        q.offer(z);
                    }
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        queue<int> q{{1}};
        int m = n * n;
        vector<bool> vis(m + 1);
        vis[1] = true;

        for (int ans = 0; !q.empty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int x = q.front();
                q.pop();
                if (x == m) {
                    return ans;
                }
                for (int y = x + 1; y <= min(x + 6, m); ++y) {
                    int i = (y - 1) / n, j = (y - 1) % n;
                    if (i % 2 == 1) {
                        j = n - j - 1;
                    }
                    i = n - i - 1;
                    int z = board[i][j] == -1 ? y : board[i][j];
                    if (!vis[z]) {
                        vis[z] = true;
                        q.push(z);
                    }
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func snakesAndLadders(board [][]int) int {
	n := len(board)
	q := []int{1}
	m := n * n
	vis := make([]bool, m+1)
	vis[1] = true

	for ans := 0; len(q) > 0; ans++ {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			if x == m {
				return ans
			}
			for y := x + 1; y <= min(x+6, m); y++ {
				i, j := (y-1)/n, (y-1)%n
				if i%2 == 1 {
					j = n - j - 1
				}
				i = n - i - 1
				z := y
				if board[i][j] != -1 {
					z = board[i][j]
				}
				if !vis[z] {
					vis[z] = true
					q = append(q, z)
				}
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function snakesAndLadders(board: number[][]): number {
    const n = board.length;
    const q: number[] = [1];
    const m = n * n;
    const vis: boolean[] = Array(m + 1).fill(false);
    vis[1] = true;

    for (let ans = 0; q.length > 0; ans++) {
        const nq: number[] = [];
        for (const x of q) {
            if (x === m) {
                return ans;
            }
            for (let y = x + 1; y <= Math.min(x + 6, m); y++) {
                let i = Math.floor((y - 1) / n);
                let j = (y - 1) % n;
                if (i % 2 === 1) {
                    j = n - j - 1;
                }
                i = n - i - 1;
                const z = board[i][j] === -1 ? y : board[i][j];
                if (!vis[z]) {
                    vis[z] = true;
                    nq.push(z);
                }
            }
        }
        q.length = 0;
        for (const x of nq) {
            q.push(x);
        }
    }
    return -1;
}
```

#### Rust

```rust
use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn snakes_and_ladders(board: Vec<Vec<i32>>) -> i32 {
        let n = board.len();
        let m = (n * n) as i32;
        let mut q = VecDeque::new();
        q.push_back(1);
        let mut vis = HashSet::new();
        vis.insert(1);
        let mut ans = 0;

        while !q.is_empty() {
            for _ in 0..q.len() {
                let x = q.pop_front().unwrap();
                if x == m {
                    return ans;
                }
                for y in x + 1..=i32::min(x + 6, m) {
                    let (mut i, mut j) = ((y - 1) / n as i32, (y - 1) % n as i32);
                    if i % 2 == 1 {
                        j = (n as i32 - 1) - j;
                    }
                    i = (n as i32 - 1) - i;
                    let z = if board[i as usize][j as usize] == -1 {
                        y
                    } else {
                        board[i as usize][j as usize]
                    };
                    if !vis.contains(&z) {
                        vis.insert(z);
                        q.push_back(z);
                    }
                }
            }
            ans += 1;
        }

        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
