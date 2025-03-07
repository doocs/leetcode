---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1257.Smallest%20Common%20Region/README.md
rating: 1654
source: 第 13 场双周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1257. 最小公共区域 🔒](https://leetcode.cn/problems/smallest-common-region)

[English Version](/solution/1200-1299/1257.Smallest%20Common%20Region/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一些区域列表&nbsp;<code>regions</code> ，每个列表的第一个区域都包含这个列表内所有其他区域。</p>

<p>很自然地，如果区域&nbsp;<code>x</code> 包含区域&nbsp;<code>y</code> ，那么区域&nbsp;<code>x</code> &nbsp;比区域&nbsp;<code>y</code> 大。同时根据定义，区域&nbsp;<code>x</code> 包含自身。</p>

<p>给定两个区域&nbsp;<code>region1</code>&nbsp;和&nbsp;<code>region2</code> ，找到同时包含这两个区域的&nbsp;<strong>最小&nbsp;</strong>区域。</p>

<p>如果给定区域&nbsp;<code>r1</code>，<code>r2</code>&nbsp;和&nbsp;<code>r3</code>，使得&nbsp;<code>r1</code>&nbsp;包含&nbsp;<code>r3</code>，那么数据保证&nbsp;<code>r2</code> 不会包含&nbsp;<code>r3</code>&nbsp;。</p>

<p>数据同样保证最小区域一定存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
<strong>输出：</strong>"North America"
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]], region1 = "Canada", region2 = "South America"
<b>输出：</b>"Earth"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= regions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= regions[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= regions[i][j].length, region1.length, region2.length &lt;= 20</code></li>
	<li><code>region1 != region2</code></li>
	<li><code>regions[i][j]</code>，<code>region1</code>&nbsp;和&nbsp;<code>region2</code> 由英语字母组成。</li>
	<li>输入保证存在一个区域直接或间接包含所有其他区域。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{g}$ 来存储每个区域的父区域，然后从 $\textit{region1}$ 开始，不断向上找到所有的父区域，直到根区域，将这些区域放入集合 $\textit{s}$ 中。然后从 $\textit{region2}$ 开始，不断向上找到第一个在 $\textit{s}$ 中的区域，即为最小公共区域。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为区域列表 $\textit{regions}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSmallestRegion(
        self, regions: List[List[str]], region1: str, region2: str
    ) -> str:
        g = {}
        for r in regions:
            x = r[0]
            for y in r[1:]:
                g[y] = x
        s = set()
        x = region1
        while x in g:
            s.add(x)
            x = g[x]
        x = region2
        while x in g and x not in s:
            x = g[x]
        return x
```

#### Java

```java
class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> g = new HashMap<>();
        for (var r : regions) {
            String x = r.get(0);
            for (String y : r.subList(1, r.size())) {
                g.put(y, x);
            }
        }
        Set<String> s = new HashSet<>();
        for (String x = region1; x != null; x = g.get(x)) {
            s.add(x);
        }
        String x = region2;
        while (g.get(x) != null && !s.contains(x)) {
            x = g.get(x);
        }
        return x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findSmallestRegion(vector<vector<string>>& regions, string region1, string region2) {
        unordered_map<string, string> g;
        for (const auto& r : regions) {
            string x = r[0];
            for (size_t i = 1; i < r.size(); ++i) {
                g[r[i]] = x;
            }
        }
        unordered_set<string> s;
        for (string x = region1; !x.empty(); x = g[x]) {
            s.insert(x);
        }
        string x = region2;
        while (!g[x].empty() && s.find(x) == s.end()) {
            x = g[x];
        }
        return x;
    }
};
```

#### Go

```go
func findSmallestRegion(regions [][]string, region1 string, region2 string) string {
	g := make(map[string]string)

	for _, r := range regions {
		x := r[0]
		for _, y := range r[1:] {
			g[y] = x
		}
	}

	s := make(map[string]bool)
	for x := region1; x != ""; x = g[x] {
		s[x] = true
	}

	x := region2
	for g[x] != "" && !s[x] {
		x = g[x]
	}

	return x
}
```

#### TypeScript

```ts
function findSmallestRegion(regions: string[][], region1: string, region2: string): string {
    const g: Record<string, string> = {};

    for (const r of regions) {
        const x = r[0];
        for (const y of r.slice(1)) {
            g[y] = x;
        }
    }

    const s: Set<string> = new Set();
    for (let x: string = region1; x !== undefined; x = g[x]) {
        s.add(x);
    }

    let x: string = region2;
    while (g[x] !== undefined && !s.has(x)) {
        x = g[x];
    }

    return x;
}
```

#### Rust

```rust
use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn find_smallest_region(regions: Vec<Vec<String>>, region1: String, region2: String) -> String {
        let mut g: HashMap<String, String> = HashMap::new();

        for r in &regions {
            let x = &r[0];
            for y in &r[1..] {
                g.insert(y.clone(), x.clone());
            }
        }

        let mut s: HashSet<String> = HashSet::new();
        let mut x = Some(region1);
        while let Some(region) = x {
            s.insert(region.clone());
            x = g.get(&region).cloned();
        }

        let mut x = Some(region2);
        while let Some(region) = x {
            if s.contains(&region) {
                return region;
            }
            x = g.get(&region).cloned();
        }

        String::new()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
