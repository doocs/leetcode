# [1376. 通知所有员工所需的时间](https://leetcode.cn/problems/time-needed-to-inform-all-employees)

[English Version](/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>公司里有 <code>n</code> 名员工，每个员工的 ID 都是独一无二的，编号从 <code>0</code> 到 <code>n - 1</code>。公司的总负责人通过 <code>headID</code> 进行标识。</p>

<p>在 <code>manager</code> 数组中，每个员工都有一个直属负责人，其中 <code>manager[i]</code> 是第 <code>i</code> 名员工的直属负责人。对于总负责人，<code>manager[headID] = -1</code>。题目保证从属关系可以用树结构显示。</p>

<p>公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。</p>

<p>第 <code>i</code> 名员工需要 <code>informTime[i]</code> 分钟来通知它的所有直属下属（也就是说在 <code>informTime[i]</code> 分钟后，他的所有直属下属都可以开始传播这一消息）。</p>

<p>返回通知所有员工这一紧急消息所需要的 <strong>分钟数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, headID = 0, manager = [-1], informTime = [0]
<strong>输出：</strong>0
<strong>解释：</strong>公司总负责人是该公司的唯一一名员工。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/images/graph.png" style="height: 174px; width: 404px;" /></p>

<pre>
<strong>输入：</strong>n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
上图显示了公司员工的树结构。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>0 &lt;= headID &lt; n</code></li>
	<li><code>manager.length == n</code></li>
	<li><code>0 &lt;= manager[i] &lt; n</code></li>
	<li><code>manager[headID] == -1</code></li>
	<li><code>informTime.length&nbsp;== n</code></li>
	<li><code>0 &lt;= informTime[i] &lt;= 1000</code></li>
	<li>如果员工 <code>i</code> 没有下属，<code>informTime[i] == 0</code> 。</li>
	<li>题目 <strong>保证</strong> 所有员工都可以收到通知。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

找从根节点到叶子节点最长的路径即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
