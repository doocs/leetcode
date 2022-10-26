# [1820. Maximum Number of Accepted Invitations](https://leetcode.com/problems/maximum-number-of-accepted-invitations)

[中文文档](/solution/1800-1899/1820.Maximum%20Number%20of%20Accepted%20Invitations/README.md)

## Description

<p>There are <code>m</code> boys and <code>n</code> girls in a class attending an upcoming party.</p>

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>, where <code>grid[i][j]</code> equals <code>0</code> or <code>1</code>. If <code>grid[i][j] == 1</code>, then that means the <code>i<sup>th</sup></code> boy can invite the <code>j<sup>th</sup></code> girl to the party. A boy can invite at most<strong> one girl</strong>, and a girl can accept at most <strong>one invitation</strong> from a boy.</p>

<p>Return <em>the <strong>maximum</strong> possible number of accepted invitations.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1],
               [1,0,1],
               [0,0,1]]
<strong>Output:</strong> 3<strong>
Explanation:</strong> The invitations are sent as follows:
- The 1<sup>st</sup> boy invites the 2<sup>nd</sup> girl.
- The 2<sup>nd</sup> boy invites the 1<sup>st</sup> girl.
- The 3<sup>rd</sup> boy invites the 3<sup>rd</sup> girl.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,1,0],
               [1,0,0,0],
               [0,0,1,0],
               [1,1,1,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The invitations are sent as follows:
-The 1<sup>st</sup> boy invites the 3<sup>rd</sup> girl.
-The 2<sup>nd</sup> boy invites the 1<sup>st</sup> girl.
-The 3<sup>rd</sup> boy invites no one.
-The 4<sup>th</sup> boy invites the 2<sup>nd</sup> girl.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == m</code></li>
	<li><code>grid[i].length == n</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumInvitations(self, grid: List[List[int]]) -> int:
        def find(i):
            for j, v in enumerate(grid[i]):
                if v and j not in vis:
                    vis.add(j)
                    if match[j] == -1 or find(match[j]):
                        match[j] = i
                        return True
            return False

        m, n = len(grid), len(grid[0])
        match = [-1] * n
        ans = 0
        for i in range(m):
            vis = set()
            ans += find(i)
        return ans
```

### **Java**

```java
class Solution {
    private int[][] grid;
    private boolean[] vis;
    private int[] match;
    private int n;

    public int maximumInvitations(int[][] grid) {
        int m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        vis = new boolean[n];
        match = new int[n];
        Arrays.fill(match, -1);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(vis, false);
            if (find(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean find(int i) {
        for (int j = 0; j < n; ++j) {
            if (grid[i][j] == 1 && !vis[j]) {
                vis[j] = true;
                if (match[j] == -1 || find(match[j])) {
                    match[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumInvitations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        bool vis[210];
        int match[210];
        memset(match, -1, sizeof match);
        int ans = 0;
        function<bool(int)> find = [&](int i) -> bool {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && !vis[j]) {
                    vis[j] = true;
                    if (match[j] == -1 || find(match[j])) {
                        match[j] = i;
                        return true;
                    }
                }
            }
            return false;
        };
        for (int i = 0; i < m; ++i) {
            memset(vis, 0, sizeof vis);
            ans += find(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumInvitations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var vis map[int]bool
	match := make([]int, n)
	for i := range match {
		match[i] = -1
	}
	var find func(i int) bool
	find = func(i int) bool {
		for j, v := range grid[i] {
			if v == 1 && !vis[j] {
				vis[j] = true
				if match[j] == -1 || find(match[j]) {
					match[j] = i
					return true
				}
			}
		}
		return false
	}
	ans := 0
	for i := 0; i < m; i++ {
		vis = map[int]bool{}
		if find(i) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
