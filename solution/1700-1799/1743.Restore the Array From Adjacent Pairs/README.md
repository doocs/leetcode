# [1743. 从相邻元素对还原数组](https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs)

[English Version](/solution/1700-1799/1743.Restore%20the%20Array%20From%20Adjacent%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个由 <code>n</code> 个不同元素组成的整数数组 <code>nums</code> ，但你已经记不清具体内容。好在你还记得 <code>nums</code> 中的每一对相邻元素。</p>

<p>给你一个二维整数数组 <code>adjacentPairs</code> ，大小为 <code>n - 1</code> ，其中每个 <code>adjacentPairs[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示元素 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 在 <code>nums</code> 中相邻。</p>

<p>题目数据保证所有由元素 <code>nums[i]</code> 和 <code>nums[i+1]</code> 组成的相邻元素对都存在于 <code>adjacentPairs</code> 中，存在形式可能是 <code>[nums[i], nums[i+1]]</code> ，也可能是 <code>[nums[i+1], nums[i]]</code> 。这些相邻元素对可以 <strong>按任意顺序</strong> 出现。</p>

<p>返回 <strong>原始数组</strong><em> </em><code>nums</code><em> </em>。如果存在多种解答，返回 <strong>其中任意一个</strong> 即可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[2,1],[3,4],[3,2]]
<strong>输出：</strong>[1,2,3,4]
<strong>解释：</strong>数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[4,-2],[1,4],[-3,1]]
<strong>输出：</strong>[-2,4,1,-3]
<strong>解释：</strong>数组中可能存在负数。
另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[100000,-100000]]
<strong>输出：</strong>[100000,-100000]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>adjacentPairs.length == n - 1</code></li>
	<li><code>adjacentPairs[i].length == 2</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> <= nums[i], u<sub>i</sub>, v<sub>i</sub> <= 10<sup>5</sup></code></li>
	<li>题目数据保证存在一些以 <code>adjacentPairs</code> 作为元素对的数组 <code>nums</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

从度为一的点开始遍历图

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        graph = defaultdict(list)
        for pair in adjacentPairs:
            graph[pair[0]].append(pair[1])
            graph[pair[1]].append(pair[0])
        ans = []
        vis = set()

        def dfs(idx):
            if idx in vis:
                return
            vis.add(idx)
            ans.append(idx)
            for nxt in graph[idx]:
                dfs(nxt)

        start = -1
        for idx, adj in graph.items():
            if len(adj) == 1:
                start = idx
                break

        dfs(start)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        List<Integer> ans = new ArrayList<>();
        Set<Integer> vis = new HashSet<>();
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        dfs(graph, ans, vis, start);
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> ans, Set<Integer> vis, int idx) {
        if (vis.contains(idx)) {
            return;
        }
        vis.add(idx);
        ans.add(idx);
        for (Integer next : graph.get(idx)) {
            dfs(graph, ans, vis, next);
        }
    }
}
```

### **Go**

```go
func restoreArray(adjacentPairs [][]int) []int {
	graph := make(map[int][]int)
	for _, pair := range adjacentPairs {
		graph[pair[0]] = append(graph[pair[0]], pair[1])
		graph[pair[1]] = append(graph[pair[1]], pair[0])
	}
	ans := make([]int, 0)
	vis := make(map[int]bool)
	var start int
	for idx, adj := range graph {
		if len(adj) == 1 {
			start = idx
			break
		}
	}
	dfs(graph, &ans, vis, start)
	return ans
}

func dfs(graph map[int][]int, ans *[]int, vis map[int]bool, idx int) {
	if vis[idx] {
		return
	}
	vis[idx] = true
	*ans = append(*ans, idx)
	for _, next := range graph[idx] {
		dfs(graph, ans, vis, next)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
