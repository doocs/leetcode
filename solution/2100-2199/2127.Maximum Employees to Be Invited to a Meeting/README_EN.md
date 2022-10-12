# [2127. Maximum Employees to Be Invited to a Meeting](https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting)

[中文文档](/solution/2100-2199/2127.Maximum%20Employees%20to%20Be%20Invited%20to%20a%20Meeting/README.md)

## Description

<p>A company is organizing a meeting and has a list of <code>n</code> employees, waiting to be invited. They have arranged for a large <strong>circular</strong> table, capable of seating <strong>any number</strong> of employees.</p>

<p>The employees are numbered from <code>0</code> to <code>n - 1</code>. Each employee has a <strong>favorite</strong> person and they will attend the meeting <strong>only if</strong> they can sit next to their favorite person at the table. The favorite person of an employee is <strong>not</strong> themself.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>favorite</code>, where <code>favorite[i]</code> denotes the favorite person of the <code>i<sup>th</sup></code> employee, return <em>the <strong>maximum number of employees</strong> that can be invited to the meeting</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2127.Maximum%20Employees%20to%20Be%20Invited%20to%20a%20Meeting/images/ex1.png" style="width: 236px; height: 195px;" />
<pre>
<strong>Input:</strong> favorite = [2,2,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> favorite = [1,2,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2127.Maximum%20Employees%20to%20Be%20Invited%20to%20a%20Meeting/images/ex2.png" style="width: 219px; height: 220px;" />
<pre>
<strong>Input:</strong> favorite = [3,0,1,4,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == favorite.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= favorite[i] &lt;=&nbsp;n - 1</code></li>
	<li><code>favorite[i] != i</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumInvitations(self, favorite: List[int]) -> int:
        def max_cycle(fa):
            n = len(fa)
            vis = [False] * n
            ans = 0
            for i in range(n):
                if vis[i]:
                    continue
                cycle = []
                j = i
                while not vis[j]:
                    cycle.append(j)
                    vis[j] = True
                    j = fa[j]
                for k, v in enumerate(cycle):
                    if v == j:
                        ans = max(ans, len(cycle) - k)
                        break
            return ans

        def topological_sort(fa):
            n = len(fa)
            indeg = [0] * n
            dist = [1] * n
            for v in fa:
                indeg[v] += 1
            q = deque([i for i, v in enumerate(indeg) if v == 0])
            while q:
                i = q.popleft()
                dist[fa[i]] = max(dist[fa[i]], dist[i] + 1)
                indeg[fa[i]] -= 1
                if indeg[fa[i]] == 0:
                    q.append(fa[i])
            return sum(dist[i] for i, v in enumerate(fa) if i == fa[fa[i]])

        return max(max_cycle(favorite), topological_sort(favorite))
```

### **Java**

```java
class Solution {
    public int maximumInvitations(int[] favorite) {
        return Math.max(maxCycle(favorite), topologicalSort(favorite));
    }

    private int maxCycle(int[] fa) {
        int n = fa.length;
        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            List<Integer> cycle = new ArrayList<>();
            int j = i;
            while (!vis[j]) {
                cycle.add(j);
                vis[j] = true;
                j = fa[j];
            }
            for (int k = 0; k < cycle.size(); ++k) {
                if (cycle.get(k) == j) {
                    ans = Math.max(ans, cycle.size() - k);
                }
            }
        }
        return ans;
    }

    private int topologicalSort(int[] fa) {
        int n = fa.length;
        int[] indeg = new int[n];
        int[] dist = new int[n];
        Arrays.fill(dist, 1);
        for (int v : fa) {
            indeg[v]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            dist[fa[i]] = Math.max(dist[fa[i]], dist[i] + 1);
            if (--indeg[fa[i]] == 0) {
                q.offer(fa[i]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i == fa[fa[i]]) {
                ans += dist[i];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumInvitations(vector<int>& favorite) {
        return max(maxCycle(favorite), topologicalSort(favorite));
    }

    int maxCycle(vector<int>& fa) {
        int n = fa.size();
        vector<bool> vis(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            vector<int> cycle;
            int j = i;
            while (!vis[j]) {
                cycle.push_back(j);
                vis[j] = true;
                j = fa[j];
            }
            for (int k = 0; k < cycle.size(); ++k) {
                if (cycle[k] == j) {
                    ans = max(ans, (int)cycle.size() - k);
                    break;
                }
            }
        }
        return ans;
    }

    int topologicalSort(vector<int>& fa) {
        int n = fa.size();
        vector<int> indeg(n);
        vector<int> dist(n, 1);
        for (int v : fa) ++indeg[v];
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) q.push(i);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            dist[fa[i]] = max(dist[fa[i]], dist[i] + 1);
            if (--indeg[fa[i]] == 0) q.push(fa[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i)
            if (i == fa[fa[i]]) ans += dist[i];
        return ans;
    }
};
```

### **Go**

```go
func maximumInvitations(favorite []int) int {
	a, b := maxCycle(favorite), topologicalSort(favorite)
	return max(a, b)
}

func maxCycle(fa []int) int {
	n := len(fa)
	vis := make([]bool, n)
	ans := 0
	for i := range fa {
		if vis[i] {
			continue
		}
		j := i
		cycle := []int{}
		for !vis[j] {
			cycle = append(cycle, j)
			vis[j] = true
			j = fa[j]
		}
		for k, v := range cycle {
			if v == j {
				ans = max(ans, len(cycle)-k)
				break
			}
		}
	}
	return ans
}

func topologicalSort(fa []int) int {
	n := len(fa)
	indeg := make([]int, n)
	dist := make([]int, n)
	for i := range fa {
		dist[i] = 1
	}
	for _, v := range fa {
		indeg[v]++
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		dist[fa[i]] = max(dist[fa[i]], dist[i]+1)
		indeg[fa[i]]--
		if indeg[fa[i]] == 0 {
			q = append(q, fa[i])
		}
	}
	ans := 0
	for i := range fa {
		if i == fa[fa[i]] {
			ans += dist[i]
		}
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
