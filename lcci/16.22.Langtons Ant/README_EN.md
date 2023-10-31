# [16.22. Langtons Ant](https://leetcode.cn/problems/langtons-ant-lcci)

[中文文档](/lcci/16.22.Langtons%20Ant/README.md)

## Description

<p>An ant is sitting on an infinite grid of white and black squares. It initially faces right. All squares are white initially.</p>
<p>At each step, it does the following:</p>
<p>(1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.</p>
<p>(2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit.</p>
<p>Write a program to simulate the first K moves that the ant makes and print the final board as a grid.</p>
<p>The grid should be represented as an array of strings, where each element represents one row in the grid. The black square is represented as <code>&#39;X&#39;</code>, and the white square is represented as <code>&#39;_&#39;</code>, the square which is occupied by the ant is represented as <code>&#39;L&#39;</code>, <code>&#39;U&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;D&#39;</code>, which means the left, up, right and down orientations respectively. You only need to return the minimum matrix that is able to contain all squares that are passed through by the ant.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong> 0

<strong>Output: </strong>[&quot;R&quot;]

</pre>
<p><strong>Example 2:</strong></p>
<pre>

<strong>Input:</strong> 2

<strong>Output:

</strong>[

&nbsp; &quot;\_X&quot;,

&nbsp; &quot;LX&quot;

]

</pre>
<p><strong>Example 3:</strong></p>
<pre>

<strong>Input:</strong> 5

<strong>Output:

</strong>[

&nbsp; &quot;\_U&quot;,

&nbsp; &quot;X\_&quot;,

&nbsp; &quot;XX&quot;

]

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>K &lt;= 100000</code></li>
</ul>

## Solutions

**Solution 1: Hash Table + Simulation**

We use a hash table $black$ to record the positions of all black squares, and a hash table $dirs$ to record the four directions of the ant. We use variables $x, y$ to record the position of the ant, and variable $p$ to record the direction of the ant. We use variables $x1, y1, x2, y2$ to record the minimum horizontal coordinate, minimum vertical coordinate, maximum horizontal coordinate, and maximum vertical coordinate of all black squares.

We simulate the ant's walking process. If the square where the ant is located is white, the ant turns right by $90$ degrees, paints the square black, and moves forward one unit. If the square where the ant is located is black, the ant turns left by $90$ degrees, paints the square white, and moves forward one unit. During the simulation, we continuously update the values of $x1, y1, x2, y2$ so that they can contain all the squares the ant has walked through.

After the simulation is over, we construct the answer matrix $g$ based on the values of $x1, y1, x2, y2$. Then, we paint the direction of the ant on the square where the ant is located, paint all black squares with $X$, and finally return the answer matrix.

The time complexity is $O(K)$, and the space complexity is $O(K)$. Here, $K$ is the number of steps the ant walks.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def printKMoves(self, K: int) -> List[str]:
        x1 = y1 = x2 = y2 = 0
        dirs = (0, 1, 0, -1, 0)
        d = "RDLU"
        x = y = 0
        p = 0
        black = set()
        for _ in range(K):
            if (x, y) in black:
                black.remove((x, y))
                p = (p + 3) % 4
            else:
                black.add((x, y))
                p = (p + 1) % 4
            x += dirs[p]
            y += dirs[p + 1]
            x1 = min(x1, x)
            y1 = min(y1, y)
            x2 = max(x2, x)
            y2 = max(y2, y)
        m, n = x2 - x1 + 1, y2 - y1 + 1
        g = [["_"] * n for _ in range(m)]
        for i, j in black:
            g[i - x1][j - y1] = "X"
        g[x - x1][y - y1] = d[p]
        return ["".join(row) for row in g]
```

### **Java**

```java
class Solution {
    public List<String> printKMoves(int K) {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        String d = "RDLU";
        int x = 0, y = 0, p = 0;
        Set<List<Integer>> black = new HashSet<>();
        while (K-- > 0) {
            List<Integer> t = List.of(x, y);
            if (black.add(t)) {
                p = (p + 1) % 4;
            } else {
                black.remove(t);
                p = (p + 3) % 4;
            }
            x += dirs[p];
            y += dirs[p + 1];
            x1 = Math.min(x1, x);
            y1 = Math.min(y1, y);
            x2 = Math.max(x2, x);
            y2 = Math.max(y2, y);
        }
        int m = x2 - x1 + 1;
        int n = y2 - y1 + 1;
        char[][] g = new char[m][n];
        for (char[] row : g) {
            Arrays.fill(row, '_');
        }
        for (List<Integer> t : black) {
            int i = t.get(0) - x1;
            int j = t.get(1) - y1;
            g[i][j] = 'X';
        }
        g[x - x1][y - y1] = d.charAt(p);
        List<String> ans = new ArrayList<>();
        for (char[] row : g) {
            ans.add(String.valueOf(row));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> printKMoves(int K) {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        int dirs[5] = {0, 1, 0, -1, 0};
        string d = "RDLU";
        int x = 0, y = 0, p = 0;
        set<pair<int, int>> black;
        while (K--) {
            auto t = make_pair(x, y);
            if (black.count(t)) {
                black.erase(t);
                p = (p + 3) % 4;
            } else {
                black.insert(t);
                p = (p + 1) % 4;
            }
            x += dirs[p];
            y += dirs[p + 1];
            x1 = min(x1, x);
            y1 = min(y1, y);
            x2 = max(x2, x);
            y2 = max(y2, y);
        }
        int m = x2 - x1 + 1, n = y2 - y1 + 1;
        vector<string> g(m, string(n, '_'));
        for (auto& [i, j] : black) {
            g[i - x1][j - y1] = 'X';
        }
        g[x - x1][y - y1] = d[p];
        return g;
    }
};
```

### **Go**

```go
func printKMoves(K int) []string {
	var x1, y1, x2, y2, x, y, p int
	dirs := [5]int{0, 1, 0, -1, 0}
	d := "RDLU"
	type pair struct{ x, y int }
	black := map[pair]bool{}
	for K > 0 {
		t := pair{x, y}
		if black[t] {
			delete(black, t)
			p = (p + 3) % 4
		} else {
			black[t] = true
			p = (p + 1) % 4
		}
		x += dirs[p]
		y += dirs[p+1]
		x1 = min(x1, x)
		y1 = min(y1, y)
		x2 = max(x2, x)
		y2 = max(y2, y)
		K--
	}
	m, n := x2-x1+1, y2-y1+1
	g := make([][]byte, m)
	for i := range g {
		g[i] = make([]byte, n)
		for j := range g[i] {
			g[i][j] = '_'
		}
	}
	for t := range black {
		i, j := t.x-x1, t.y-y1
		g[i][j] = 'X'
	}
	g[x-x1][y-y1] = d[p]
	ans := make([]string, m)
	for i := range ans {
		ans[i] = string(g[i])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
