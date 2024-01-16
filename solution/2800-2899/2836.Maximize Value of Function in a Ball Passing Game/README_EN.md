# [2836. Maximize Value of Function in a Ball Passing Game](https://leetcode.com/problems/maximize-value-of-function-in-a-ball-passing-game)

[中文文档](/solution/2800-2899/2836.Maximize%20Value%20of%20Function%20in%20a%20Ball%20Passing%20Game/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>receiver</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>There are <code>n</code> players having a <strong>unique id</strong> in the range <code>[0, n - 1]</code> who will play a ball passing game, and <code>receiver[i]</code> is the id of the player who receives passes from the player with id <code>i</code>. Players can pass to themselves, <strong>i.e.</strong> <code>receiver[i]</code> may be equal to <code>i</code>.</p>

<p>You must choose one of the <code>n</code> players as the starting player for the game, and the ball will be passed <strong>exactly</strong> <code>k</code> times starting from the chosen player.</p>

<p>For a chosen starting player having id <code>x</code>, we define a function <code>f(x)</code> that denotes the <strong>sum</strong> of <code>x</code> and the <strong>ids</strong> of all players who receive the ball during the <code>k</code> passes, <strong>including repetitions</strong>. In other words, <code>f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver<sup>(k)</sup>[x]</code>.</p>

<p>Your task is to choose a starting player having id <code>x</code> that <strong>maximizes</strong> the value of <code>f(x)</code>.</p>

<p>Return <em>an integer denoting the <strong>maximum</strong> value of the function.</em></p>

<p><strong>Note:</strong> <code>receiver</code> may contain duplicates.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">Pass Number</th>
			<th style="padding: 5px; border: 1px solid black;">Sender ID</th>
			<th style="padding: 5px; border: 1px solid black;">Receiver ID</th>
			<th style="padding: 5px; border: 1px solid black;">x + Receiver IDs</th>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">0</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">0</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">4</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">6</td>
		</tr>
	</tbody>
</table>

<pre>
<strong>Input:</strong> receiver = [2,0,1], k = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> The table above shows a simulation of the game starting with the player having id x = 2. 
From the table, f(2) is equal to 6. 
It can be shown that 6 is the maximum achievable value of the function. 
Hence, the output is 6. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">Pass Number</th>
			<th style="padding: 5px; border: 1px solid black;">Sender ID</th>
			<th style="padding: 5px; border: 1px solid black;">Receiver ID</th>
			<th style="padding: 5px; border: 1px solid black;">x + Receiver IDs</th>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">4</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">7</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">10</td>
		</tr>
	</tbody>
</table>

<pre>
<strong>Input:</strong> receiver = [1,1,1,2,3], k = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> The table above shows a simulation of the game starting with the player having id x = 4. 
From the table, f(4) is equal to 10. 
It can be shown that 10 is the maximum achievable value of the function. 
Hence, the output is 10. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= receiver.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= receiver[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>10</sup></code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming + Binary Lifting

The problem asks us to find the maximum sum of the player IDs who have touched the ball within $k$ passes starting from each player $i$. If we solve it by brute force, we need to traverse upwards $k$ times starting from $i$, with a time complexity of $O(k)$, which will obviously time out.

We can use dynamic programming combined with binary lifting to handle this.

We define $f[i][j]$ as the player ID that can be reached by passing the ball $2^j$ times starting from player $i$, and define $g[i][j]$ as the sum of the player IDs that can be reached by passing the ball $2^j$ times starting from player $i$ (excluding the last player).

When $j=0$, the number of passes is $1$, so $f[i][0] = receiver[i]$, and $g[i][0] = i$.

When $j > 0$, the number of passes is $2^j$, which is equivalent to passing the ball $2^{j-1}$ times starting from player $i$, and then passing the ball $2^{j-1}$ times starting from player $f[i][j-1]$, so $f[i][j] = f[f[i][j-1]][j-1]$, and $g[i][j] = g[i][j-1] + g[f[i][j-1]][j-1]$.

Next, we can enumerate each player $i$ as the starting player, then accumulate upwards according to the binary representation of $k$, and finally get the maximum sum of the player IDs who have touched the ball within $k$ passes starting from player $i$.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(n \times \log k)$. Here, $n$ is the number of players.

Similar problems:

-   [1483. Kth Ancestor of a Tree Node](/solution/1400-1499/1483.Kth%20Ancestor%20of%20a%20Tree%20Node/README_EN.md)

<!-- tabs:start -->

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

<!-- end -->
