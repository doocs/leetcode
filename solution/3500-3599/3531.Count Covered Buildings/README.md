---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3531.Count%20Covered%20Buildings/README.md
rating: 1518
source: 第 447 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [3531. 统计被覆盖的建筑](https://leetcode.cn/problems/count-covered-buildings)

[English Version](/solution/3500-3599/3531.Count%20Covered%20Buildings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>，表示一个 <code>n x n</code> 的城市，同时给定一个二维数组 <code>buildings</code>，其中 <code>buildings[i] = [x, y]</code> 表示位于坐标 <code>[x, y]</code> 的一个&nbsp;<strong>唯一&nbsp;</strong>建筑。</p>

<p>如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑&nbsp;<strong>被覆盖&nbsp;</strong>。</p>

<p>返回&nbsp;<strong>被覆盖&nbsp;</strong>的建筑数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-qtNUjI-telegram-cloud-photo-size-5-6212982906394101085-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>只有建筑 <code>[2,2]</code> 被覆盖，因为它在每个方向上都至少存在一个建筑：

    <ul>
    	<li>上方 (<code>[1,2]</code>)</li>
    	<li>下方 (<code>[3,2]</code>)</li>
    	<li>左方 (<code>[2,1]</code>)</li>
    	<li>右方 (<code>[2,3]</code>)</li>
    </ul>
    </li>
    <li>因此，被覆盖的建筑数量是 1。</li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-tUMUKl-telegram-cloud-photo-size-5-6212982906394101086-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>没有任何一个建筑在每个方向上都有至少一个建筑。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-bQIwBX-telegram-cloud-photo-size-5-6248862251436067566-x.jpg" style="width: 202px; height: 205px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>只有建筑 <code>[3,3]</code> 被覆盖，因为它在每个方向上至少存在一个建筑：

    <ul>
    	<li>上方 (<code>[1,3]</code>)</li>
    	<li>下方 (<code>[5,3]</code>)</li>
    	<li>左方 (<code>[3,2]</code>)</li>
    	<li>右方 (<code>[3,5]</code>)</li>
    </ul>
    </li>
    <li>因此，被覆盖的建筑数量是 1。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i] = [x, y]</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
	<li><code>buildings</code> 中所有坐标均&nbsp;<strong>唯一&nbsp;</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

我们可以将建筑按照横坐标和纵坐标进行分组，分别记录在哈希表 $\text{g1}$ 和 $\text{g2}$ 中，其中 $\text{g1[x]}$ 表示所有横坐标为 $x$ 的纵坐标，而 $\text{g2[y]}$ 表示所有纵坐标为 $y$ 的横坐标，然后我们将其进行排序。

接下来，我们遍历所有建筑，对于当前建筑 $(x, y)$，我们通过哈希表获取对应的纵坐标列表 $l_1$ 和横坐标列表 $l_2$，并检查条件以确定建筑是否被覆盖。覆盖的条件是 $l_2[0] < x < l_2[-1]$ 且 $l_1[0] < y < l_1[-1]$，若是，我们将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是建筑物的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCoveredBuildings(self, n: int, buildings: List[List[int]]) -> int:
        g1 = defaultdict(list)
        g2 = defaultdict(list)
        for x, y in buildings:
            g1[x].append(y)
            g2[y].append(x)
        for x in g1:
            g1[x].sort()
        for y in g2:
            g2[y].sort()
        ans = 0
        for x, y in buildings:
            l1 = g1[x]
            l2 = g2[y]
            if l2[0] < x < l2[-1] and l1[0] < y < l1[-1]:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> g1 = new HashMap<>();
        Map<Integer, List<Integer>> g2 = new HashMap<>();

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            g1.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            g2.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        for (var e : g1.entrySet()) {
            Collections.sort(e.getValue());
        }
        for (var e : g2.entrySet()) {
            Collections.sort(e.getValue());
        }

        int ans = 0;

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            List<Integer> l1 = g1.get(x);
            List<Integer> l2 = g2.get(y);

            if (l2.get(0) < x && x < l2.get(l2.size() - 1) && l1.get(0) < y
                && y < l1.get(l1.size() - 1)) {
                ans++;
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCoveredBuildings(int n, vector<vector<int>>& buildings) {
        unordered_map<int, vector<int>> g1;
        unordered_map<int, vector<int>> g2;

        for (const auto& building : buildings) {
            int x = building[0], y = building[1];
            g1[x].push_back(y);
            g2[y].push_back(x);
        }

        for (auto& e : g1) {
            sort(e.second.begin(), e.second.end());
        }
        for (auto& e : g2) {
            sort(e.second.begin(), e.second.end());
        }

        int ans = 0;

        for (const auto& building : buildings) {
            int x = building[0], y = building[1];
            const vector<int>& l1 = g1[x];
            const vector<int>& l2 = g2[y];

            if (l2[0] < x && x < l2[l2.size() - 1] && l1[0] < y && y < l1[l1.size() - 1]) {
                ans++;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func countCoveredBuildings(n int, buildings [][]int) (ans int) {
	g1 := make(map[int][]int)
	g2 := make(map[int][]int)

	for _, building := range buildings {
		x, y := building[0], building[1]
		g1[x] = append(g1[x], y)
		g2[y] = append(g2[y], x)
	}

	for _, list := range g1 {
		sort.Ints(list)
	}
	for _, list := range g2 {
		sort.Ints(list)
	}

	for _, building := range buildings {
		x, y := building[0], building[1]
		l1 := g1[x]
		l2 := g2[y]

		if l2[0] < x && x < l2[len(l2)-1] && l1[0] < y && y < l1[len(l1)-1] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countCoveredBuildings(n: number, buildings: number[][]): number {
    const g1: Map<number, number[]> = new Map();
    const g2: Map<number, number[]> = new Map();

    for (const [x, y] of buildings) {
        if (!g1.has(x)) g1.set(x, []);
        g1.get(x)?.push(y);

        if (!g2.has(y)) g2.set(y, []);
        g2.get(y)?.push(x);
    }

    for (const list of g1.values()) {
        list.sort((a, b) => a - b);
    }
    for (const list of g2.values()) {
        list.sort((a, b) => a - b);
    }

    let ans = 0;

    for (const [x, y] of buildings) {
        const l1 = g1.get(x)!;
        const l2 = g2.get(y)!;

        if (l2[0] < x && x < l2[l2.length - 1] && l1[0] < y && y < l1[l1.length - 1]) {
            ans++;
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn count_covered_buildings(_n: i32, buildings: Vec<Vec<i32>>) -> i32 {
        let mut g1: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut g2: HashMap<i32, Vec<i32>> = HashMap::new();

        for b in &buildings {
            let x = b[0];
            let y = b[1];
            g1.entry(x).or_insert(Vec::new()).push(y);
            g2.entry(y).or_insert(Vec::new()).push(x);
        }

        for v in g1.values_mut() {
            v.sort();
        }
        for v in g2.values_mut() {
            v.sort();
        }

        let mut ans: i32 = 0;

        for b in &buildings {
            let x = b[0];
            let y = b[1];

            let l1 = g1.get(&x).unwrap();
            let l2 = g2.get(&y).unwrap();

            if l2[0] < x && x < l2[l2.len() - 1] && l1[0] < y && y < l1[l1.len() - 1] {
                ans += 1;
            }
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int CountCoveredBuildings(int n, int[][] buildings) {
        var g1 = new Dictionary<int, List<int>>();
        var g2 = new Dictionary<int, List<int>>();

        foreach (var b in buildings) {
            int x = b[0], y = b[1];

            if (!g1.ContainsKey(x)) {
                g1[x] = new List<int>();
            }
            g1[x].Add(y);

            if (!g2.ContainsKey(y)) {
                g2[y] = new List<int>();
            }
            g2[y].Add(x);
        }

        foreach (var kv in g1) {
            kv.Value.Sort();
        }
        foreach (var kv in g2) {
            kv.Value.Sort();
        }

        int ans = 0;

        foreach (var b in buildings) {
            int x = b[0], y = b[1];
            var l1 = g1[x];
            var l2 = g2[y];

            if (l2[0] < x && x < l2[l2.Count - 1] &&
                l1[0] < y && y < l1[l1.Count - 1]) {
                ans++;
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
