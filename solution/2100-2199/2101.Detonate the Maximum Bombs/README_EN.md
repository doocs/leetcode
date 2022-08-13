# [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs)

[中文文档](/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README.md)

## Description

<p>You are given a list of bombs. The <strong>range</strong> of a bomb is defined as the area where its effect can be felt. This area is in the shape of a <strong>circle</strong> with the center as the location of the bomb.</p>

<p>The bombs are represented by a <strong>0-indexed</strong> 2D integer array <code>bombs</code> where <code>bombs[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>. <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> denote the X-coordinate and Y-coordinate of the location of the <code>i<sup>th</sup></code> bomb, whereas <code>r<sub>i</sub></code> denotes the <strong>radius</strong> of its range.</p>

<p>You may choose to detonate a <strong>single</strong> bomb. When a bomb is detonated, it will detonate <strong>all bombs</strong> that lie in its range. These bombs will further detonate the bombs that lie in their ranges.</p>

<p>Given the list of <code>bombs</code>, return <em>the <strong>maximum</strong> number of bombs that can be detonated if you are allowed to detonate <strong>only one</strong> bomb</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-3.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[2,1,3],[6,1,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-2.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,1,5],[10,10,5]]
<strong>Output:</strong> 1
<strong>Explanation:
</strong>Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg1.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bombs.length&nbsp;&lt;= 100</code></li>
	<li><code>bombs[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        def check(i, j):
            if i == j:
                return False
            x, y = bombs[i][0] - bombs[j][0], bombs[i][1] - bombs[j][1]
            r = bombs[i][2]
            return r * r >= x * x + y * y

        g = defaultdict(list)
        n = len(bombs)
        for i in range(n):
            for j in range(n):
                if check(i, j):
                    g[i].append(j)
        ans = 0
        for k in range(n):
            q = deque([k])
            vis = [False] * n
            vis[k] = True
            cnt = 0
            while q:
                i = q.popleft()
                cnt += 1
                for j in g[i]:
                    if not vis[j]:
                        vis[j] = True
                        q.append(j)
            ans = max(ans, cnt)
        return ans
```

### **Java**

```java
class Solution {
    private int[][] bombs;

    public int maximumDetonation(int[][] bombs) {
        this.bombs = bombs;
        int n = bombs.length;
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = check(i, j);
            }
        }
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(k);
            boolean[] vis = new boolean[n];
            vis[k] = true;
            int cnt = 0;
            while (!q.isEmpty()) {
                int i = q.poll();
                ++cnt;
                for (int j = 0; j < n; ++j) {
                    if (g[i][j] && !vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    private boolean check(int i, int j) {
        if (i == j) {
            return false;
        }
        long x = bombs[i][0] - bombs[j][0];
        long y = bombs[i][1] - bombs[j][1];
        long r = bombs[i][2];
        return r * r >= x * x + y * y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumDetonation(vector<vector<int>>& bombs) {
        int n = bombs.size();
        vector<vector<bool>> g(n, vector<bool>(n));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                g[i][j] = check(i, j, bombs);
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            queue<int> q {{k}};
            vector<bool> vis(n);
            vis[k] = true;
            int cnt = 0;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ++cnt;
                for (int j = 0; j < n; ++j) {
                    if (g[i][j] && !vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
            ans = max(ans, cnt);
        }
        return ans;
    }

    bool check(int i, int j, vector<vector<int>>& bombs) {
        if (i == j) return false;
        long long x = bombs[i][0] - bombs[j][0];
        long long y = bombs[i][1] - bombs[j][1];
        long long r = bombs[i][2];
        return r * r >= x * x + y * y;
    }
};
```

### **Go**

```go
func maximumDetonation(bombs [][]int) int {
	check := func(i, j int) bool {
		if i == j {
			return false
		}
		x, y := bombs[i][0]-bombs[j][0], bombs[i][1]-bombs[j][1]
		r := bombs[i][2]
		return r*r >= x*x+y*y
	}
	n := len(bombs)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = check(i, j)
		}
	}

	ans := 0
	for k := 0; k < n; k++ {
		q := []int{k}
		vis := make([]bool, n)
		vis[k] = true
		cnt := 0
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			cnt++
			for j := 0; j < n; j++ {
				if g[i][j] && !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		ans = max(ans, cnt)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
