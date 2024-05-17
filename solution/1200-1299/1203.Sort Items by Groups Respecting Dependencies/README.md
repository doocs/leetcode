---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1203.Sort%20Items%20by%20Groups%20Respecting%20Dependencies/README.md
rating: 2418
source: 第 155 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
---

<!-- problem:start -->

# [1203. 项目管理](https://leetcode.cn/problems/sort-items-by-groups-respecting-dependencies)

[English Version](/solution/1200-1299/1203.Sort%20Items%20by%20Groups%20Respecting%20Dependencies/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 个项目，每个项目或者不属于任何小组，或者属于 <code>m</code> 个小组之一。<code>group[i]</code> 表示第 <code>i</code> 个项目所属的小组，如果第 <code>i</code> 个项目不属于任何小组，则 <code>group[i]</code> 等于 <code>-1</code>。项目和小组都是从零开始编号的。可能存在小组不负责任何项目，即没有任何项目属于这个小组。</p>

<p>请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：</p>

<ul>
	<li>同一小组的项目，排序后在列表中彼此相邻。</li>
	<li>项目之间存在一定的依赖关系，我们用一个列表 <code>beforeItems</code> 来表示，其中 <code>beforeItems[i]</code> 表示在进行第 <code>i</code> 个项目前（位于第 <code>i</code> 个项目左侧）应该完成的所有项目。</li>
</ul>

<p>如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 <strong>空列表 </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1203.Sort%20Items%20by%20Groups%20Respecting%20Dependencies/images/1359_ex1.png" style="height: 181px; width: 191px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
<strong>输出：</strong>[6,3,4,1,5,2,0,7]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
<strong>输出：</strong>[]
<strong>解释：</strong>与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= m <= n <= 3 * 10<sup>4</sup></code></li>
	<li><code>group.length == beforeItems.length == n</code></li>
	<li><code>-1 <= group[i] <= m - 1</code></li>
	<li><code>0 <= beforeItems[i].length <= n - 1</code></li>
	<li><code>0 <= beforeItems[i][j] <= n - 1</code></li>
	<li><code>i != beforeItems[i][j]</code></li>
	<li><code>beforeItems[i]</code> 不含重复元素</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：拓扑排序

我们先遍历数组 $group$，对于每个项目，如果其不属于任何小组，则将其新建一个小组，编号为 $m$，并将 $m$ 的值加一。这样我们就能保证所有项目都属于某个小组了。然后，我们用一个数组 $groupItems$ 记录每个小组包含的项目，数组下标为小组编号，数组值为包含的项目列表。

接下来，我们需要建图。对于每个项目，我们需要建立两种图，一种是项目间的图，一种是小组间的图。我们遍历数组 $group$，对于当前项目 $i$，它所在的小组为 $group[i]$，我们遍历 $beforeItems[i]$，对于其中的每个项目 $j$，如果 $group[i] = group[j]$，说明 $i$ 和 $j$ 属于同一小组，我们在项目图中添加一条 $j \to i$ 的边，否则说明 $i$ 和 $j$ 属于不同的小组，我们在小组间的图中添加一条 $group[j] \to group[i]$ 的边，并且更新对应的入度数组。

接下来，我们先对小组间的图进行拓扑排序，得到排序后的小组列表 $groupOrder$，如果排序后的列表长度不等于小组总数，说明无法完成排序，返回空列表。否则，我们遍历 $groupOrder$，对于其中的每个小组 $gi$，我们将该小组包含的项目列表 $groupItems[gi]$ 进行拓扑排序，得到排序后的项目列表 $itemOrder$，如果排序后的列表长度不等于该小组包含的项目总数，说明无法完成排序，返回空列表。否则，我们将 $itemOrder$ 中的项目加入答案数组中，最终返回该答案数组即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是项目总数和小组总数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortItems(
        self, n: int, m: int, group: List[int], beforeItems: List[List[int]]
    ) -> List[int]:
        def topo_sort(degree, graph, items):
            q = deque(i for _, i in enumerate(items) if degree[i] == 0)
            res = []
            while q:
                i = q.popleft()
                res.append(i)
                for j in graph[i]:
                    degree[j] -= 1
                    if degree[j] == 0:
                        q.append(j)
            return res if len(res) == len(items) else []

        idx = m
        group_items = [[] for _ in range(n + m)]
        for i, g in enumerate(group):
            if g == -1:
                group[i] = idx
                idx += 1
            group_items[group[i]].append(i)

        item_degree = [0] * n
        group_degree = [0] * (n + m)
        item_graph = [[] for _ in range(n)]
        group_graph = [[] for _ in range(n + m)]
        for i, gi in enumerate(group):
            for j in beforeItems[i]:
                gj = group[j]
                if gi == gj:
                    item_degree[i] += 1
                    item_graph[j].append(i)
                else:
                    group_degree[gi] += 1
                    group_graph[gj].append(gi)

        group_order = topo_sort(group_degree, group_graph, range(n + m))
        if not group_order:
            return []
        ans = []
        for gi in group_order:
            items = group_items[gi]
            item_order = topo_sort(item_degree, item_graph, items)
            if len(items) != len(item_order):
                return []
            ans.extend(item_order)
        return ans
```

#### Java

```java
class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int idx = m;
        List<Integer>[] groupItems = new List[n + m];
        int[] itemDegree = new int[n];
        int[] groupDegree = new int[n + m];
        List<Integer>[] itemGraph = new List[n];
        List<Integer>[] groupGraph = new List[n + m];
        Arrays.setAll(groupItems, k -> new ArrayList<>());
        Arrays.setAll(itemGraph, k -> new ArrayList<>());
        Arrays.setAll(groupGraph, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = idx++;
            }
            groupItems[group[i]].add(i);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : beforeItems.get(i)) {
                if (group[i] == group[j]) {
                    ++itemDegree[i];
                    itemGraph[j].add(i);
                } else {
                    ++groupDegree[group[i]];
                    groupGraph[group[j]].add(group[i]);
                }
            }
        }
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < n + m; ++i) {
            items.add(i);
        }
        var groupOrder = topoSort(groupDegree, groupGraph, items);
        if (groupOrder.isEmpty()) {
            return new int[0];
        }
        List<Integer> ans = new ArrayList<>();
        for (int gi : groupOrder) {
            items = groupItems[gi];
            var itemOrder = topoSort(itemDegree, itemGraph, items);
            if (itemOrder.size() != items.size()) {
                return new int[0];
            }
            ans.addAll(itemOrder);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> topoSort(int[] degree, List<Integer>[] graph, List<Integer> items) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i : items) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int i = q.poll();
            ans.add(i);
            for (int j : graph[i]) {
                if (--degree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans.size() == items.size() ? ans : List.of();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortItems(int n, int m, vector<int>& group, vector<vector<int>>& beforeItems) {
        int idx = m;
        vector<vector<int>> groupItems(n + m);
        vector<int> itemDegree(n);
        vector<int> groupDegree(n + m);
        vector<vector<int>> itemGraph(n);
        vector<vector<int>> groupGraph(n + m);
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = idx++;
            }
            groupItems[group[i]].push_back(i);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : beforeItems[i]) {
                if (group[i] == group[j]) {
                    ++itemDegree[i];
                    itemGraph[j].push_back(i);
                } else {
                    ++groupDegree[group[i]];
                    groupGraph[group[j]].push_back(group[i]);
                }
            }
        }
        vector<int> items(n + m);
        iota(items.begin(), items.end(), 0);
        auto topoSort = [](vector<vector<int>>& graph, vector<int>& degree, vector<int>& items) -> vector<int> {
            queue<int> q;
            for (int& i : items) {
                if (degree[i] == 0) {
                    q.push(i);
                }
            }
            vector<int> ans;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ans.push_back(i);
                for (int& j : graph[i]) {
                    if (--degree[j] == 0) {
                        q.push(j);
                    }
                }
            }
            return ans.size() == items.size() ? ans : vector<int>();
        };
        auto groupOrder = topoSort(groupGraph, groupDegree, items);
        if (groupOrder.empty()) {
            return vector<int>();
        }
        vector<int> ans;
        for (int& gi : groupOrder) {
            items = groupItems[gi];
            auto itemOrder = topoSort(itemGraph, itemDegree, items);
            if (items.size() != itemOrder.size()) {
                return vector<int>();
            }
            ans.insert(ans.end(), itemOrder.begin(), itemOrder.end());
        }
        return ans;
    }
};
```

#### Go

```go
func sortItems(n int, m int, group []int, beforeItems [][]int) []int {
	idx := m
	groupItems := make([][]int, n+m)
	itemDegree := make([]int, n)
	groupDegree := make([]int, n+m)
	itemGraph := make([][]int, n)
	groupGraph := make([][]int, n+m)
	for i, g := range group {
		if g == -1 {
			group[i] = idx
			idx++
		}
		groupItems[group[i]] = append(groupItems[group[i]], i)
	}
	for i, gi := range group {
		for _, j := range beforeItems[i] {
			gj := group[j]
			if gi == gj {
				itemDegree[i]++
				itemGraph[j] = append(itemGraph[j], i)
			} else {
				groupDegree[gi]++
				groupGraph[gj] = append(groupGraph[gj], gi)
			}
		}
	}
	items := make([]int, n+m)
	for i := range items {
		items[i] = i
	}
	topoSort := func(degree []int, graph [][]int, items []int) []int {
		q := []int{}
		for _, i := range items {
			if degree[i] == 0 {
				q = append(q, i)
			}
		}
		ans := []int{}
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			ans = append(ans, i)
			for _, j := range graph[i] {
				degree[j]--
				if degree[j] == 0 {
					q = append(q, j)
				}
			}
		}
		return ans
	}
	groupOrder := topoSort(groupDegree, groupGraph, items)
	if len(groupOrder) != len(items) {
		return nil
	}
	ans := []int{}
	for _, gi := range groupOrder {
		items = groupItems[gi]
		itemOrder := topoSort(itemDegree, itemGraph, items)
		if len(items) != len(itemOrder) {
			return nil
		}
		ans = append(ans, itemOrder...)
	}
	return ans
}
```

#### TypeScript

```ts
function sortItems(n: number, m: number, group: number[], beforeItems: number[][]): number[] {
    let idx = m;
    const groupItems: number[][] = new Array(n + m).fill(0).map(() => []);
    const itemDegree: number[] = new Array(n).fill(0);
    const gorupDegree: number[] = new Array(n + m).fill(0);
    const itemGraph: number[][] = new Array(n).fill(0).map(() => []);
    const groupGraph: number[][] = new Array(n + m).fill(0).map(() => []);
    for (let i = 0; i < n; ++i) {
        if (group[i] === -1) {
            group[i] = idx++;
        }
        groupItems[group[i]].push(i);
    }
    for (let i = 0; i < n; ++i) {
        for (const j of beforeItems[i]) {
            if (group[i] === group[j]) {
                ++itemDegree[i];
                itemGraph[j].push(i);
            } else {
                ++gorupDegree[group[i]];
                groupGraph[group[j]].push(group[i]);
            }
        }
    }
    let items = new Array(n + m).fill(0).map((_, i) => i);
    const topoSort = (graph: number[][], degree: number[], items: number[]): number[] => {
        const q: number[] = [];
        for (const i of items) {
            if (degree[i] === 0) {
                q.push(i);
            }
        }
        const ans: number[] = [];
        while (q.length) {
            const i = q.pop()!;
            ans.push(i);
            for (const j of graph[i]) {
                if (--degree[j] === 0) {
                    q.push(j);
                }
            }
        }
        return ans.length === items.length ? ans : [];
    };
    const groupOrder = topoSort(groupGraph, gorupDegree, items);
    if (groupOrder.length === 0) {
        return [];
    }
    const ans: number[] = [];
    for (const gi of groupOrder) {
        items = groupItems[gi];
        const itemOrder = topoSort(itemGraph, itemDegree, items);
        if (itemOrder.length !== items.length) {
            return [];
        }
        ans.push(...itemOrder);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
