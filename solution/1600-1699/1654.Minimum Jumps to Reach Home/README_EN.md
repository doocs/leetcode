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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 jumps forward (0 -&gt; 3 -&gt; 6 -&gt; 9) will get the bug home.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        q = deque([(0, 1)])
        vis = {(0, 1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, k = q.popleft()
                if i == x:
                    return ans
                nxt = [(i + a, 1)]
                if k & 1:
                    nxt.append((i - b, 0))
                for j, k in nxt:
                    if 0 <= j < 6000 and j not in s and (j, k) not in vis:
                        q.append((j, k))
                        vis.add((j, k))
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> s = new HashSet<>();
        for (int v : forbidden) {
            s.add(v);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 1});
        final int n = 6000;
        boolean[][] vis = new boolean[n][2];
        vis[0][1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                int i = p[0], k = p[1];
                if (i == x) {
                    return ans;
                }
                List<int[]> nxt = new ArrayList<>();
                nxt.add(new int[] {i + a, 1});
                if ((k & 1) == 1) {
                    nxt.add(new int[] {i - b, 0});
                }
                for (var e : nxt) {
                    int j = e[0];
                    k = e[1];
                    if (j >= 0 && j < n && !s.contains(j) && !vis[j][k]) {
                        q.offer(new int[] {j, k});
                        vis[j][k] = true;
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
    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<pair<int, int>> q;
        q.emplace(0, 1);
        const int n = 6000;
        bool vis[n][2];
        memset(vis, false, sizeof(vis));
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto [i, k] = q.front();
                q.pop();
                if (i == x) {
                    return ans;
                }
                vector<pair<int, int>> nxts = {{i + a, 1}};
                if (k & 1) {
                    nxts.emplace_back(i - b, 0);
                }
                for (auto [j, l] : nxts) {
                    if (j >= 0 && j < n && !s.count(j) && !vis[j][l]) {
                        vis[j][l] = true;
                        q.emplace(j, l);
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
func minimumJumps(forbidden []int, a int, b int, x int) (ans int) {
	s := map[int]bool{}
	for _, v := range forbidden {
		s[v] = true
	}
	q := [][2]int{[2]int{0, 1}}
	const n = 6000
	vis := make([][2]bool, n)
	vis[0][1] = true
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, k := p[0], p[1]
			if i == x {
				return
			}
			nxt := [][2]int{[2]int{i + a, 1}}
			if k&1 == 1 {
				nxt = append(nxt, [2]int{i - b, 0})
			}
			for _, e := range nxt {
				j, l := e[0], e[1]
				if j >= 0 && j < n && !s[j] && !vis[j][l] {
					q = append(q, [2]int{j, l})
					vis[j][l] = true
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
