# [1376. Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees)

[中文文档](/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/README.md)

<!-- tags:Tree,Depth-First Search,Breadth-First Search -->

<!-- difficulty:Medium -->

## Description

<p>A company has <code>n</code> employees with a unique ID for each employee from <code>0</code> to <code>n - 1</code>. The head of the company is the one with <code>headID</code>.</p>

<p>Each employee has one direct manager given in the <code>manager</code> array where <code>manager[i]</code> is the direct manager of the <code>i-th</code> employee, <code>manager[headID] = -1</code>. Also, it is guaranteed that the subordination relationships have a tree structure.</p>

<p>The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.</p>

<p>The <code>i-th</code> employee needs <code>informTime[i]</code> minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).</p>

<p>Return <em>the number of minutes</em> needed to inform all the employees about the urgent news.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, headID = 0, manager = [-1], informTime = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The head of the company is the only employee in the company.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/images/graph.png" style="width: 404px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= headID &lt; n</code></li>
	<li><code>manager.length == n</code></li>
	<li><code>0 &lt;= manager[i] &lt; n</code></li>
	<li><code>manager[headID] == -1</code></li>
	<li><code>informTime.length == n</code></li>
	<li><code>0 &lt;= informTime[i] &lt;= 1000</code></li>
	<li><code>informTime[i] == 0</code> if employee <code>i</code> has no subordinates.</li>
	<li>It is <strong>guaranteed</strong> that all the employees can be informed.</li>
</ul>

## Solutions

### Solution 1: DFS

We first build an adjacent list $g$ according to the $manager$ array, where $g[i]$ represents all direct subordinates of employee $i$.

Next, we design a function $dfs(i)$, which means the time required for employee $i$ to notify all his subordinates (including direct subordinates and indirect subordinates), and then the answer is $dfs(headID)$.

In function $dfs(i)$, we need to traverse all direct subordinates $j$ of $i$. For each subordinate, employee $i$ needs to notify him, which takes $informTime[i]$ time, and his subordinates need to notify their subordinates, which takes $dfs(j)$ time. We take the maximum value of $informTime[i] + dfs(j)$ as the return value of function $dfs(i)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of employees.

<!-- tabs:start -->

```python
class Solution:
    def numOfMinutes(
        self, n: int, headID: int, manager: List[int], informTime: List[int]
    ) -> int:
        def dfs(i: int) -> int:
            ans = 0
            for j in g[i]:
                ans = max(ans, dfs(j) + informTime[i])
            return ans

        g = defaultdict(list)
        for i, x in enumerate(manager):
            g[x].append(i)
        return dfs(headID)
```

```java
class Solution {
    private List<Integer>[] g;
    private int[] informTime;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        this.informTime = informTime;
        for (int i = 0; i < n; ++i) {
            if (manager[i] >= 0) {
                g[manager[i]].add(i);
            }
        }
        return dfs(headID);
    }

    private int dfs(int i) {
        int ans = 0;
        for (int j : g[i]) {
            ans = Math.max(ans, dfs(j) + informTime[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            if (manager[i] >= 0) {
                g[manager[i]].push_back(i);
            }
        }
        function<int(int)> dfs = [&](int i) -> int {
            int ans = 0;
            for (int j : g[i]) {
                ans = max(ans, dfs(j) + informTime[i]);
            }
            return ans;
        };
        return dfs(headID);
    }
};
```

```go
func numOfMinutes(n int, headID int, manager []int, informTime []int) int {
	g := make([][]int, n)
	for i, x := range manager {
		if x != -1 {
			g[x] = append(g[x], i)
		}
	}
	var dfs func(int) int
	dfs = func(i int) (ans int) {
		for _, j := range g[i] {
			ans = max(ans, dfs(j)+informTime[i])
		}
		return
	}
	return dfs(headID)
}
```

```ts
function numOfMinutes(n: number, headID: number, manager: number[], informTime: number[]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (let i = 0; i < n; ++i) {
        if (manager[i] !== -1) {
            g[manager[i]].push(i);
        }
    }
    const dfs = (i: number): number => {
        let ans = 0;
        for (const j of g[i]) {
            ans = Math.max(ans, dfs(j) + informTime[i]);
        }
        return ans;
    };
    return dfs(headID);
}
```

```cs
public class Solution {
    private List<int>[] g;
    private int[] informTime;

    public int NumOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        this.informTime = informTime;
        for (int i = 0; i < n; ++i) {
            if (manager[i] != -1) {
                g[manager[i]].Add(i);
            }
        }
        return dfs(headID);
    }

    private int dfs(int i) {
        int ans = 0;
        foreach (int j in g[i]) {
            ans = Math.Max(ans, dfs(j) + informTime[i]);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
