---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.22.Langtons%20Ant/README.md
---

# [面试题 16.22. 兰顿蚂蚁](https://leetcode.cn/problems/langtons-ant-lcci)

[English Version](/lcci/16.22.Langtons%20Ant/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。</p>
<p>(1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。<br>
(2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。</p>
<p>编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。</p>
<p>网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由&nbsp;<code>&#39;X&#39;</code>&nbsp;表示，白色方格由&nbsp;<code>&#39;_&#39;</code>&nbsp;表示，蚂蚁所在的位置由&nbsp;<code>&#39;L&#39;</code>, <code>&#39;U&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;D&#39;</code>&nbsp;表示，分别表示蚂蚁&nbsp;左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong> 0
<strong>输出: </strong>[&quot;R&quot;]
</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong> 2
<strong>输出:
</strong>[
&nbsp; &quot;_X&quot;,
&nbsp; &quot;LX&quot;
]
</pre>
<p><strong>示例 3:</strong></p>
<pre><strong>输入:</strong> 5
<strong>输出:
</strong>[
&nbsp; &quot;_U&quot;,
&nbsp; &quot;X_&quot;,
&nbsp; &quot;XX&quot;
]
</pre>
<p><strong>说明：</strong></p>
<ul>
	<li><code>K &lt;= 100000</code></li>
</ul>

## 解法

### 方法一：哈希表 + 模拟

我们使用哈希表 $\textit{black}$ 来记录所有黑色方格的位置，哈希表 $\textit{dirs}$ 来记录蚂蚁的四个方向。我们使用变量 $x$, $y$ 来记录蚂蚁的位置，使用变量 $p$ 来记录蚂蚁的方向。我们使用变量 $x_1$, $y_1$, $x_2$, $y_2$ 来记录所有黑色方格的最小横坐标、最小纵坐标、最大横坐标、最大纵坐标。

我们模拟蚂蚁的行走过程。如果蚂蚁所在的方格是白色的，那么蚂蚁向右转 $90$ 度，将方格涂黑，向前移动一个单位。如果蚂蚁所在的方格是黑色的，那么蚂蚁向左转 $90$ 度，将方格涂白，向前移动一个单位。在模拟的过程中，我们不断更新 $x_1$, $y_1$, $x_2$, $y_2$ 的值，使得它们能够包含蚂蚁走过的所有方格。

模拟结束后，我们根据 $x_1$, $y_1$, $x_2$, $y_2$ 的值，构造出答案矩阵 $g$。然后，我们将蚂蚁所在的位置涂上蚂蚁的方向，同时将所有黑色方格涂上 $X$，最后返回答案矩阵。

时间复杂度 $O(K)$，空间复杂度 $O(K)$。其中 $K$ 是蚂蚁行走的步数。

<!-- tabs:start -->

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

```swift
class Solution {
    func printKMoves(_ K: Int) -> [String] {
        var x1 = 0, y1 = 0, x2 = 0, y2 = 0
        let dirs = [0, 1, 0, -1, 0]
        let d = "RDLU"
        var x = 0, y = 0, p = 0
        var black = Set<[Int]>()
        var K = K

        while K > 0 {
            let t = [x, y]
            if black.insert(t).inserted {
                p = (p + 1) % 4
            } else {
                black.remove(t)
                p = (p + 3) % 4
            }
            x += dirs[p]
            y += dirs[p + 1]
            x1 = min(x1, x)
            y1 = min(y1, y)
            x2 = max(x2, x)
            y2 = max(y2, y)
            K -= 1
        }

        let m = x2 - x1 + 1
        let n = y2 - y1 + 1
        var g = Array(repeating: Array(repeating: "_", count: n), count: m)

        for t in black {
            let i = t[0] - x1
            let j = t[1] - y1
            g[i][j] = "X"
        }

        g[x - x1][y - y1] = String(d[d.index(d.startIndex, offsetBy: p)])

        return g.map { $0.joined() }
    }
}
```

<!-- tabs:end -->

<!-- end -->
