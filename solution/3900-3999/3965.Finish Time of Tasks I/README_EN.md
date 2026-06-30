---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3965.Finish%20Time%20of%20Tasks%20I/README_EN.md
rating: 1698
source: Biweekly Contest 185 Q3
---

<!-- problem:start -->

# [3965. Finish Time of Tasks I](https://leetcode.com/problems/finish-time-of-tasks-i)

[中文文档](/solution/3900-3999/3965.Finish%20Time%20of%20Tasks%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of tasks in a project, numbered from 0 to <code>n - 1</code>. These tasks are connected as a <strong>tree</strong> rooted at task 0. This is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that task <code>u<sub>i</sub></code> is the parent of task <code>v<sub>i</sub></code>.</p>

<p>You are also given an array <code>baseTime</code> of length <code>n</code>, where <code>baseTime[i]</code> represents the time to complete task <code>i</code>.</p>

<p>The <strong>finish time</strong> of each task is calculated as follows:</p>

<ul>
	<li>Leaf task: The finish time is <code>baseTime[i]</code>.</li>
	<li>Non-leaf task:
	<ul>
		<li>Let <code>earliest</code> be the <strong>minimum</strong> finish time among its children, and <code>latest</code> be the <strong>maximum</strong> finish time among its children.</li>
		<li>Let <code>ownDuration</code> be <code>(latest - earliest) + baseTime[i]</code>.</li>
		<li>The finish time of task <code>i</code> is <code>latest + ownDuration</code>.</li>
	</ul>
	</li>
</ul>

<p>Return the finish time of the root task 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], baseTime = [9,5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>
<svg height="100" viewbox="0 0 420 140" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="100%" width="100%"></rect> <line stroke="black" stroke-width="2" x1="80" x2="210" y1="60" y2="60"></line> <line stroke="black" stroke-width="2" x1="210" x2="340" y1="60" y2="60"></line> <circle cx="80" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="80" y="65">0</text> <text fill="black" font-size="14" text-anchor="middle" x="80" y="100">9</text> <circle cx="210" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="210" y="65">1</text> <text fill="black" font-size="14" text-anchor="middle" x="210" y="100">5</text> <circle cx="340" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="340" y="65">2</text> <text fill="black" font-size="14" text-anchor="middle" x="340" y="100">3</text> </svg>

<ul>
	<li>Task 2 is a leaf, so its finish time is <code>baseTime[2] = 3</code>.</li>
	<li>Task 1 has one child task 2:
	<ul>
		<li><code>earliest = latest = 3</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[1] = 5</code></li>
		<li>Finish time of task 1 is <code>3 + 5 = 8</code></li>
	</ul>
	</li>
	<li>Task 0 has one child with finish time 8:
	<ul>
		<li><code>earliest = latest = 8</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = 9</code></li>
		<li>Finish time of task 0 is <code>8 + 9 = 17</code></li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>
<svg height="130" viewbox="0 0 420 180" width="300" xmlns="http://www.w3.org/2000/svg"> <rect fill="white" height="100%" width="100%"></rect> <line stroke="black" stroke-width="2" x1="210" x2="110" y1="60" y2="130"></line> <line stroke="black" stroke-width="2" x1="210" x2="310" y1="60" y2="130"></line> <circle cx="210" cy="60" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="210" y="65">0</text> <text fill="black" font-size="14" text-anchor="middle" x="210" y="100">4</text> <circle cx="110" cy="130" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="110" y="135">1</text> <text fill="black" font-size="14" text-anchor="middle" x="110" y="170">7</text> <circle cx="310" cy="130" fill="white" r="24" stroke="black" stroke-width="2"></circle> <text fill="black" font-size="16" text-anchor="middle" x="310" y="135">2</text> <text fill="black" font-size="14" text-anchor="middle" x="310" y="170">6</text> </svg>

<ul>
	<li>Task 1 is a leaf, so its finish time is <code>baseTime[1] = 7</code>.</li>
	<li>Task 2 is a leaf, so its finish time is <code>baseTime[2] = 6</code>.</li>
	<li>Task 0 has two children with finish times 7 and 6:
	<ul>
		<li><code>earliest = 6</code>, <code>latest = 7</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = (7 - 6) + 4 = 5</code></li>
		<li>Finish time of task 0 is <code>latest + ownDuration = 7 + 5 = 12</code></li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Task 1 is a leaf, so its finish time is <code>baseTime[1] = 8</code>.</li>
	<li>Task 3 is a leaf, so its finish time is <code>baseTime[3] = 1</code>.</li>
	<li>Task 2 has one child task 3:
	<ul>
		<li><code>earliest = latest = 1</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[2] = 0 + 2 = 2</code></li>
		<li>Finish time of task 2 is <code>latest + ownDuration = 1 + 2 = 3</code></li>
	</ul>
	</li>
	<li>Task 0 has two children with finish times 8 and 3:
	<ul>
		<li><code>earliest = 3</code>, <code>latest = 8</code></li>
		<li><code>ownDuration = (latest - earliest) + baseTime[0] = (8 - 3) + 5 = 10</code></li>
		<li>Finish time of task 0 is <code>latest + ownDuration = 8 + 10 = 18</code></li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length = n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
	<li><code>baseTime.length == n</code></li>
	<li><code>1 &lt;= baseTime[i] &lt;= 10<sup>5</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def finishTime(
        self,
        n: int,
        edges: list[list[int]],
        b: list[int]
    ) -> int:
        parent = [-1] * n
        for u, v in edges:
            parent[v] = u
        minimum = [0] * n
        maximum = [None] * n
        for u in range(n - 1, -1, -1):
            current_max = maximum[u]
            if current_max is not None:
                b[u] += (current_max << 1) - minimum[u]
            p = parent[u]
            if p >= 0:
                value = b[u]
                parent_max = maximum[p]
                if parent_max is None:
                    minimum[p] = value
                    maximum[p] = value
                else:
                    if value > parent_max:
                        maximum[p] = value

                    if value < minimum[p]:
                        minimum[p] = value
        return b[0]
```

#### Java

```java
class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        int[][] torqavemi = edges;
        int[] outDegree = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
        for (int[] edge : torqavemi) {
            int u = edge[0];
            int v = edge[1];
            parent[v] = u;
            outDegree[u]++;
        }        
        long[] earliest = new long[n];
        long[] latest = new long[n];
        for (int i = 0; i < n; i++) {
            earliest[i] = Long.MAX_VALUE;
            latest[i] = Long.MIN_VALUE;
        }
        long[] finish = new long[n];
        int[] queue = new int[n];
        int head = 0, tail = 0;
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue[tail++] = i;
            }
        }
        while (head < tail) {
            int u = queue[head++];
            if (earliest[u] == Long.MAX_VALUE) { 
                finish[u] = baseTime[u];
            } else { 
                finish[u] = 2 * latest[u] - earliest[u] + baseTime[u];
            }
            
            int p = parent[u];
            if (p != -1) {
                if (finish[u] < earliest[p]) {
                    earliest[p] = finish[u];
                }
                if (finish[u] > latest[p]) {
                    latest[p] = finish[u];
                }
                outDegree[p]--;
                if (outDegree[p] == 0) {
                    queue[tail++] = p;
                }
            }
        }
        return finish[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long finishTime(int n, vector<vector<int>>& edges, vector<int>& baseTime) {
        vector<vector<int>>adj(n);
        for(int i=0;i<edges.size();i++){
            int u=edges[i][0],v=edges[i][1];
            adj[u].push_back(v);
        }
        vector<int>temp;
        stack<int>st;st.push(0);
        while(!st.empty()){
            int u=st.top();st.pop();
            temp.push_back(u);
            for(int v:adj[u])st.push(v);
        }
        vector<long long>ft(n,0);

        for(int idx=temp.size()-1;idx>=0;idx--){
            int u=temp[idx];
            if(adj[u].size()==0)ft[u]=baseTime[u];
            else{
                long long mini=LLONG_MAX,maxi=0;
                for(int v:adj[u]){
                    long long f=ft[v];
                    mini=min(mini,f);
                    maxi=max(maxi,f);
                }
                 long long own=(maxi-mini)+baseTime[u];
             ft[u]=own+maxi;
            }
        }
       return ft[0]; 
    }
};
```

#### Go

```go
const MAXN = 100000

var adj, deg, stack [MAXN]uint32
var times [MAXN][2]int

func cutleaf(u uint32) uint32 {
	v := adj[u]
	adj[v] ^= u
	deg[u] = 0
	deg[v]--
	return v
}

func finishTime(n int, edges [][]int, basetime []int) int64 {
	clear(adj[:n])
	clear(deg[:n])
	for _, e := range edges {
		u, v := uint32(e[0]), uint32(e[1])
		adj[u] ^= v
		adj[v] ^= u
		deg[u]++
		deg[v]++
	}
	deg[0]++
	s := 0
	for u := range uint32(n) {
		times[u] = [2]int{1 << 60, 0}
		if deg[u] == 1 {
			stack[s] = u
			s++
		}
	}
	for s > 0 && deg[0] > 0 {
		s--
		u := stack[s]
		v := cutleaf(u)
		finish := basetime[u]
		times[v][0] = min(times[v][0], finish)
		times[v][1] = max(times[v][1], finish)
		if deg[v] == 1 {
			basetime[v] += 2*times[v][1] - times[v][0]
			stack[s] = v
			s++
		}
	}
	return int64(basetime[0])
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
