---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2836.Maximize%20Value%20of%20Function%20in%20a%20Ball%20Passing%20Game/README_EN.md
rating: 2768
source: Weekly Contest 360 Q4
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [2836. Maximize Value of Function in a Ball Passing Game](https://leetcode.com/problems/maximize-value-of-function-in-a-ball-passing-game)

[中文文档](/solution/2800-2899/2836.Maximize%20Value%20of%20Function%20in%20a%20Ball%20Passing%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>receiver</code> of length <code>n</code> and an integer <code>k</code>. <code>n</code> players are playing a ball-passing game.</p>

<p>You choose the starting player, <code>i</code>. The game proceeds as follows: player <code>i</code> passes the ball to player <code>receiver[i]</code>, who then passes it to <code>receiver[receiver[i]]</code>, and so on, for <code>k</code> passes in total. The game&#39;s score is the sum of the indices of the players who touched the ball, including repetitions, i.e. <code>i + receiver[i] + receiver[receiver[i]] + ... + receiver<sup>(k)</sup>[i]</code>.</p>

<p>Return&nbsp;the <strong>maximum</strong>&nbsp;possible score.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li><code>receiver</code> may contain duplicates.</li>
	<li><code>receiver[i]</code> may be equal to <code>i</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">receiver = [2,0,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Starting with player <code>i = 2</code> the initial score is 2:</p>

<table>
	<tbody>
		<tr>
			<th>Pass</th>
			<th>Sender Index</th>
			<th>Receiver Index</th>
			<th>Score</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>1</td>
			<td>3</td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td>0</td>
			<td>3</td>
		</tr>
		<tr>
			<td>3</td>
			<td>0</td>
			<td>2</td>
			<td>5</td>
		</tr>
		<tr>
			<td>4</td>
			<td>2</td>
			<td>1</td>
			<td>6</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">receiver = [1,1,1,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>Starting with player <code>i = 4</code> the initial score is 4:</p>

<table>
	<tbody>
		<tr>
			<th>Pass</th>
			<th>Sender Index</th>
			<th>Receiver Index</th>
			<th>Score</th>
		</tr>
		<tr>
			<td>1</td>
			<td>4</td>
			<td>3</td>
			<td>7</td>
		</tr>
		<tr>
			<td>2</td>
			<td>3</td>
			<td>2</td>
			<td>9</td>
		</tr>
		<tr>
			<td>3</td>
			<td>2</td>
			<td>1</td>
			<td>10</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= receiver.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= receiver[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>10</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Binary Lifting

The problem asks us to find the maximum sum of the player IDs who have touched the ball within $k$ passes starting from each player $i$. If we solve it by brute force, we need to traverse upwards $k$ times starting from $i$, with a time complexity of $O(k)$, which will obviously time out.

We can use dynamic programming combined with binary lifting to handle this.

We define $f[i][j]$ as the player ID that can be reached by passing the ball $2^j$ times starting from player $i$, and define $g[i][j]$ as the sum of the player IDs that can be reached by passing the ball $2^j$ times starting from player $i$ (excluding the last player).

When $j=0$, the number of passes is $1$, so $f[i][0] = receiver[i]$, and $g[i][0] = i$.

When $j > 0$, the number of passes is $2^j$, which is equivalent to passing the ball $2^{j-1}$ times starting from player $i$, and then passing the ball $2^{j-1}$ times starting from player $f[i][j-1]$, so $f[i][j] = f[f[i][j-1]][j-1]$, and $g[i][j] = g[i][j-1] + g[f[i][j-1]][j-1]$.

Next, we can enumerate each player $i$ as the starting player, then accumulate upwards according to the binary representation of $k$, and finally get the maximum sum of the player IDs who have touched the ball within $k$ passes starting from player $i$.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(n \times \log k)$. Here, $n$ is the number of players.

Similar problems:

-   [1483. Kth Ancestor of a Tree Node](https://github.com/doocs/leetcode/blob/main/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMaxFunctionValue(self, receiver: List[int], k: int) -> int:
        n, m = len(receiver), k.bit_length()
        f = [[0] * m for _ in range(n)]
        g = [[0] * m for _ in range(n)]
        for i, x in enumerate(receiver):
            f[i][0] = x
            g[i][0] = i
        for j in range(1, m):
            for i in range(n):
                f[i][j] = f[f[i][j - 1]][j - 1]
                g[i][j] = g[i][j - 1] + g[f[i][j - 1]][j - 1]
        ans = 0
        for i in range(n):
            p, t = i, 0
            for j in range(m):
                if k >> j & 1:
                    t += g[p][j]
                    p = f[p][j]
            ans = max(ans, t + p)
        return ans
```

#### Java

```java
class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size(), m = 64 - Long.numberOfLeadingZeros(k);
        int[][] f = new int[n][m];
        long[][] g = new long[n][m];
        for (int i = 0; i < n; ++i) {
            f[i][0] = receiver.get(i);
            g[i][0] = i;
        }
        for (int j = 1; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                f[i][j] = f[f[i][j - 1]][j - 1];
                g[i][j] = g[i][j - 1] + g[f[i][j - 1]][j - 1];
            }
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = i;
            long t = 0;
            for (int j = 0; j < m; ++j) {
                if ((k >> j & 1) == 1) {
                    t += g[p][j];
                    p = f[p][j];
                }
            }
            ans = Math.max(ans, p + t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long getMaxFunctionValue(vector<int>& receiver, long long k) {
        int n = receiver.size(), m = 64 - __builtin_clzll(k);
        int f[n][m];
        long long g[n][m];
        for (int i = 0; i < n; ++i) {
            f[i][0] = receiver[i];
            g[i][0] = i;
        }
        for (int j = 1; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                f[i][j] = f[f[i][j - 1]][j - 1];
                g[i][j] = g[i][j - 1] + g[f[i][j - 1]][j - 1];
            }
        }
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = i;
            long long t = 0;
            for (int j = 0; j < m; ++j) {
                if (k >> j & 1) {
                    t += g[p][j];
                    p = f[p][j];
                }
            }
            ans = max(ans, p + t);
        }
        return ans;
    }
};
```

#### Go

```go
func getMaxFunctionValue(receiver []int, k int64) (ans int64) {
	n, m := len(receiver), bits.Len(uint(k))
	f := make([][]int, n)
	g := make([][]int64, n)
	for i := range f {
		f[i] = make([]int, m)
		g[i] = make([]int64, m)
		f[i][0] = receiver[i]
		g[i][0] = int64(i)
	}
	for j := 1; j < m; j++ {
		for i := 0; i < n; i++ {
			f[i][j] = f[f[i][j-1]][j-1]
			g[i][j] = g[i][j-1] + g[f[i][j-1]][j-1]
		}
	}
	for i := 0; i < n; i++ {
		p := i
		t := int64(0)
		for j := 0; j < m; j++ {
			if k>>j&1 == 1 {
				t += g[p][j]
				p = f[p][j]
			}
		}
		ans = max(ans, t+int64(p))
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
