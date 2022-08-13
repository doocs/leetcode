# [1376. Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees)

[中文文档](/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/README.md)

## Description

<p>A company has <code>n</code> employees with a unique ID for each employee from <code>0</code> to <code>n - 1</code>. The head of the company is the one with <code>headID</code>.</p>

<p>Each employee has one direct manager given in the <code>manager</code> array where <code>manager[i]</code> is the direct manager of the <code>i-th</code> employee, <code>manager[headID] = -1</code>. Also, it is guaranteed that the subordination relationships have a tree structure.</p>

<p>The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.</p>

<p>The <code>i-th</code> employee needs <code>informTime[i]</code> minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).</p>

<p>Return <em>the number of minutes</em> needed to inform all the employees about the urgent news.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, headID = 0, manager = [-1], informTime = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The head of the company is the only employee in the company.
</pre>

<p><strong>Example 2:</strong></p>
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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numOfMinutes(
        self, n: int, headID: int, manager: List[int], informTime: List[int]
    ) -> int:
        def dfs(i):
            ans = 0
            for j in g[i]:
                ans = max(ans, informTime[i] + dfs(j))
            return ans

        g = defaultdict(list)
        for i, m in enumerate(manager):
            g[m].append(i)
        return dfs(headID)
```

### **Java**

```java
class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] manager;
    private int[] informTime;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        g = new HashMap<>();
        this.manager = manager;
        this.informTime = informTime;
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(manager[i], k -> new ArrayList<>()).add(i);
        }
        return dfs(headID);
    }

    private int dfs(int i) {
        int ans = 0;
        for (int j : g.getOrDefault(i, new ArrayList<>())) {
            ans = Math.max(ans, informTime[i] + dfs(j));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, vector<int>> g;
    vector<int> manager;
    vector<int> informTime;

    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        this->manager = manager;
        this->informTime = informTime;
        for (int i = 0; i < n; ++i) g[manager[i]].push_back(i);
        return dfs(headID);
    }

    int dfs(int i) {
        int ans = 0;
        for (int j : g[i]) ans = max(ans, informTime[i] + dfs(j));
        return ans;
    }
};
```

### **Go**

```go
func numOfMinutes(n int, headID int, manager []int, informTime []int) int {
	g := make(map[int][]int)
	for i, m := range manager {
		g[m] = append(g[m], i)
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		ans := 0
		if v, ok := g[i]; ok {
			for _, j := range v {
				ans = max(ans, informTime[i]+dfs(j))
			}
		}
		return ans
	}
	return dfs(headID)
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
function numOfMinutes(
    n: number,
    headID: number,
    manager: number[],
    informTime: number[],
): number {
    if (n === 1) {
        return 0;
    }
    let res = 0;
    const time = new Array(n).fill(0);
    time[headID] = -1;
    const dfs = (i: number) => {
        const aim = manager[i];
        if (time[aim] === -1) {
            return informTime[aim];
        }
        if (time[aim] === 0) {
            time[aim] = dfs(aim);
        }
        return time[aim] + informTime[aim];
    };
    for (let i = 0; i < n; i++) {
        if (time[i] === 0) {
            time[i] = dfs(i);
        }
        res = Math.max(res, time[i]);
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
