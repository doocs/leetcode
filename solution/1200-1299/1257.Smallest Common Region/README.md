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

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSmallestRegion(
        self, regions: List[List[str]], region1: str, region2: str
    ) -> str:
        m = {}
        for region in regions:
            for r in region[1:]:
                m[r] = region[0]
        s = set()
        while m.get(region1):
            s.add(region1)
            region1 = m[region1]
        while m.get(region2):
            if region2 in s:
                return region2
            region2 = m[region2]
        return region1
```

#### Java

```java
class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> m = new HashMap<>();
        for (List<String> region : regions) {
            for (int i = 1; i < region.size(); ++i) {
                m.put(region.get(i), region.get(0));
            }
        }
        Set<String> s = new HashSet<>();
        while (m.containsKey(region1)) {
            s.add(region1);
            region1 = m.get(region1);
        }
        while (m.containsKey(region2)) {
            if (s.contains(region2)) {
                return region2;
            }
            region2 = m.get(region2);
        }
        return region1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findSmallestRegion(vector<vector<string>>& regions, string region1, string region2) {
        unordered_map<string, string> m;
        for (auto& region : regions)
            for (int i = 1; i < region.size(); ++i)
                m[region[i]] = region[0];
        unordered_set<string> s;
        while (m.count(region1)) {
            s.insert(region1);
            region1 = m[region1];
        }
        while (m.count(region2)) {
            if (s.count(region2)) return region2;
            region2 = m[region2];
        }
        return region1;
    }
};
```

#### Go

```go
func findSmallestRegion(regions [][]string, region1 string, region2 string) string {
	m := make(map[string]string)
	for _, region := range regions {
		for i := 1; i < len(region); i++ {
			m[region[i]] = region[0]
		}
	}
	s := make(map[string]bool)
	for region1 != "" {
		s[region1] = true
		region1 = m[region1]
	}
	for region2 != "" {
		if s[region2] {
			return region2
		}
		region2 = m[region2]
	}
	return region1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
