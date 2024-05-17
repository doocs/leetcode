---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/README.md
rating: 1561
source: 第 179 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
---

<!-- problem:start -->

# [1376. 通知所有员工所需的时间](https://leetcode.cn/problems/time-needed-to-inform-all-employees)

[English Version](/solution/1300-1399/1376.Time%20Needed%20to%20Inform%20All%20Employees/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们先根据 $manager$ 数组构建邻接表 $g$，其中 $g[i]$ 表示员工 $i$ 的所有直接下属。

接下来，我们设计一个函数 $dfs(i)$，表示从员工 $i$ 开始，将消息通知给他的所有下属（包括直接下属、间接下属）所需要的时间，那么答案就是 $dfs(headID)$。

在函数 $dfs(i)$ 中，我们需要遍历 $i$ 的所有直接下属 $j$，对于每个下属，员工 $i$ 需要将消息通知给他，这需要花费 $informTime[i]$ 的时间，而他的所有下属需要将消息通知给他们的下属，这需要花费 $dfs(j)$ 的时间，取 $informTime[i] + dfs(j)$ 的最大值作为函数 $dfs(i)$ 的返回值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为员工数量。

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

<!-- solution:end -->

<!-- problem:end -->
