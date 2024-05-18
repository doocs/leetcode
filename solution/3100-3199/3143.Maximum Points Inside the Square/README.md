---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3143.Maximum%20Points%20Inside%20the%20Square/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [3143. 正方形中的最多点数](https://leetcode.cn/problems/maximum-points-inside-the-square)

[English Version](/solution/3100-3199/3143.Maximum%20Points%20Inside%20the%20Square/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维数组&nbsp;<code>points</code>&nbsp;和一个字符串&nbsp;<code>s</code>&nbsp;，其中&nbsp;<code>points[i]</code>&nbsp;表示第 <code>i</code>&nbsp;个点的坐标，<code>s[i]</code>&nbsp;表示第 <code>i</code>&nbsp;个点的 <strong>标签</strong>&nbsp;。</p>

<p>如果一个正方形的中心在&nbsp;<code>(0, 0)</code>&nbsp;，所有边都平行于坐标轴，且正方形内&nbsp;<strong>不</strong>&nbsp;存在标签相同的两个点，那么我们称这个正方形是&nbsp;<strong>合法</strong>&nbsp;的。</p>

<p>请你返回 <strong>合法</strong>&nbsp;正方形中可以包含的 <strong>最多</strong>&nbsp;点数。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>如果一个点位于正方形的边上或者在边以内，则认为该点位于正方形内。</li>
	<li>正方形的边长可以为零。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3143.Maximum%20Points%20Inside%20the%20Square/images/3708-tc1.png" style="width: 303px; height: 303px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>边长为 4 的正方形包含两个点&nbsp;<code>points[0]</code> 和&nbsp;<code>points[1]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3143.Maximum%20Points%20Inside%20the%20Square/images/3708-tc2.png" style="width: 302px; height: 302px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>points = [[1,1],[-2,-2],[-2,2]], s = "abb"</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>边长为 2 的正方形包含 1 个点&nbsp;<code>points[0]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>points = [[1,1],[-1,-1],[2,-2]], s = "ccd"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>任何正方形都无法只包含&nbsp;<code>points[0]</code> 和&nbsp;<code>points[1]</code>&nbsp;中的一个点，所以合法正方形中都不包含任何点。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>9</sup> &lt;= points[i][0], points[i][1] &lt;= 10<sup>9</sup></code></li>
	<li><code>s.length == points.length</code></li>
	<li><code>points</code>&nbsp;中的点坐标互不相同。</li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

对于一个点 $(x, y)$，我们可以将其映射到以原点为中心的第一象限，即 $(\max(|x|, |y|), \max(|x|, |y|))$。这样，我们就可以将所有的点映射到第一象限，然后按照点到原点的距离进行排序。

我们可以使用哈希表 $g$ 来存储所有点到原点的距离，然后按照距离进行排序。对于每个距离 $d$，我们将所有距离为 $d$ 的点放在一起，然后遍历这些点，如果有两个点的标签相同，那么这个正方形是不合法的，直接返回答案。否则，我们将这些点加入到答案中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPointsInsideSquare(self, points: List[List[int]], s: str) -> int:
        g = defaultdict(list)
        for i, (x, y) in enumerate(points):
            g[max(abs(x), abs(y))].append(i)
        vis = set()
        ans = 0
        for d in sorted(g):
            idx = g[d]
            for i in idx:
                if s[i] in vis:
                    return ans
                vis.add(s[i])
            ans += len(idx)
        return ans
```

#### Java

```java
class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {
        TreeMap<Integer, List<Integer>> g = new TreeMap<>();
        for (int i = 0; i < points.length; ++i) {
            int x = points[i][0], y = points[i][1];
            int key = Math.max(Math.abs(x), Math.abs(y));
            g.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
        }
        boolean[] vis = new boolean[26];
        int ans = 0;
        for (var idx : g.values()) {
            for (int i : idx) {
                int j = s.charAt(i) - 'a';
                if (vis[j]) {
                    return ans;
                }
                vis[j] = true;
            }
            ans += idx.size();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPointsInsideSquare(vector<vector<int>>& points, string s) {
        map<int, vector<int>> g;
        for (int i = 0; i < points.size(); ++i) {
            auto& p = points[i];
            int key = max(abs(p[0]), abs(p[1]));
            g[key].push_back(i);
        }
        bool vis[26]{};
        int ans = 0;
        for (auto& [_, idx] : g) {
            for (int i : idx) {
                int j = s[i] - 'a';
                if (vis[j]) {
                    return ans;
                }
                vis[j] = true;
            }
            ans += idx.size();
        }
        return ans;
    }
};
```

#### Go

```go
func maxPointsInsideSquare(points [][]int, s string) (ans int) {
	g := map[int][]int{}
	for i, p := range points {
		key := max(p[0], -p[0], p[1], -p[1])
		g[key] = append(g[key], i)
	}
	vis := [26]bool{}
	keys := []int{}
	for k := range g {
		keys = append(keys, k)
	}
	sort.Ints(keys)
	for _, k := range keys {
		idx := g[k]
		for _, i := range idx {
			j := s[i] - 'a'
			if vis[j] {
				return
			}
			vis[j] = true
		}
		ans += len(idx)
	}
	return
}
```

#### TypeScript

```ts
function maxPointsInsideSquare(points: number[][], s: string): number {
    const n = points.length;
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < n; ++i) {
        const [x, y] = points[i];
        const key = Math.max(Math.abs(x), Math.abs(y));
        if (!g.has(key)) {
            g.set(key, []);
        }
        g.get(key)!.push(i);
    }
    const keys = Array.from(g.keys()).sort((a, b) => a - b);
    const vis: boolean[] = Array(26).fill(false);
    let ans = 0;
    for (const key of keys) {
        const idx = g.get(key)!;
        for (const i of idx) {
            const j = s.charCodeAt(i) - 'a'.charCodeAt(0);
            if (vis[j]) {
                return ans;
            }
            vis[j] = true;
        }
        ans += idx.length;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
