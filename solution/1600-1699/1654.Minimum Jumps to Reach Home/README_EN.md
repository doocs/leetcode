# [1654. Minimum Jumps to Reach Home](https://leetcode.com/problems/minimum-jumps-to-reach-home)

[中文文档](/solution/1600-1699/1654.Minimum%20Jumps%20to%20Reach%20Home/README.md)

## Description

<p>A certain bug&#39;s home is on the x-axis at position <code>x</code>. Help them get there from position <code>0</code>.</p>

<p>The bug jumps according to the following rules:</p>

<ul>
	<li>It can jump exactly <code>a</code> positions <strong>forward</strong> (to the right).</li>
	<li>It can jump exactly <code>b</code> positions <strong>backward</strong> (to the left).</li>
	<li>It cannot jump backward twice in a row.</li>
	<li>It cannot jump to any <code>forbidden</code> positions.</li>
</ul>

<p>The bug may jump forward <strong>beyond</strong> its home, but it <strong>cannot jump</strong> to positions numbered with <strong>negative</strong> integers.</p>

<p>Given an array of integers <code>forbidden</code>, where <code>forbidden[i]</code> means that the bug cannot jump to the position <code>forbidden[i]</code>, and integers <code>a</code>, <code>b</code>, and <code>x</code>, return <em>the minimum number of jumps needed for the bug to reach its home</em>. If there is no possible sequence of jumps that lands the bug on position <code>x</code>, return <code>-1.</code></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 jumps forward (0 -&gt; 3 -&gt; 6 -&gt; 9) will get the bug home.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
<strong>Output:</strong> -1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
<strong>Output:</strong> 2
<strong>Explanation:</strong> One jump forward (0 -&gt; 16) then one jump backward (16 -&gt; 7) will get the bug home.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= forbidden.length &lt;= 1000</code></li>
	<li><code>1 &lt;= a, b, forbidden[i] &lt;= 2000</code></li>
	<li><code>0 &lt;= x &lt;= 2000</code></li>
	<li>All the elements in <code>forbidden</code> are distinct.</li>
	<li>Position <code>x</code> is not forbidden.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        s = set(forbidden)
        q = deque([(0, 0)])
        vis = {(0, 1), (0, -1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, dir = q.popleft()
                if i == x:
                    return ans
                nxt = [(i + a, 1)]
                if dir != -1:
                    nxt.append((i - b, -1))
                for j, dir in nxt:
                    if 0 <= j <= 6000 and j not in s and (j, dir) not in vis:
                        vis.add((j, dir))
                        q.append((j, dir))
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    private static final int N = 6010;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> s = new HashSet<>();
        for (int v : forbidden) {
            s.add(v);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 2});
        boolean[][] vis = new boolean[N][2];
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0], dir = p[1];
                if (i == x) {
                    return ans;
                }
                List<int[]> nxt = new ArrayList<>();
                nxt.add(new int[]{i + a, 1});
                if (dir != 0) {
                    nxt.add(new int[]{i - b, 0});
                }
                for (int[] e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.contains(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.offer(new int[]{j, dir});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int N = 6010;

    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<vector<int>> q;
        q.push({0, 0});
        vector<vector<bool>> vis(N, vector<bool>(2));
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto p = q.front();
                q.pop();
                int i = p[0], dir = p[1];
                if (i == x) return ans;
                vector<vector<int>> nxt;
                nxt.push_back({i + a, 1});
                if (dir) nxt.push_back({i - b, 0});
                for (auto& e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.count(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.push({j, dir});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func minimumJumps(forbidden []int, a int, b int, x int) int {
	n := 6010
	s := make(map[int]bool)
	for _, v := range forbidden {
		s[v] = true
	}
	q := [][]int{[]int{0, 0}}
	vis := make([][]bool, n)
	for i := range vis {
		vis[i] = make([]bool, 2)
	}
	vis[0][0] = true
	vis[0][1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, dir := p[0], p[1]
			if i == x {
				return ans
			}
			nxt := [][]int{[]int{i + a, 1}}
			if dir != 0 {
				nxt = append(nxt, []int{i - b, 0})
			}
			for _, e := range nxt {
				j := e[0]
				dir = e[1]
				if j >= 0 && j < n && !s[j] && !vis[j][dir] {
					vis[j][dir] = true
					q = append(q, []int{j, dir})
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
