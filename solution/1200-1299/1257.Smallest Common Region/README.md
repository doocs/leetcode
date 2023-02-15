# [1257. 最小公共区域](https://leetcode.cn/problems/smallest-common-region)

[English Version](/solution/1200-1299/1257.Smallest%20Common%20Region/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一些区域列表&nbsp;<code>regions</code> ，每个列表的第一个区域都包含这个列表内所有其他区域。</p>

<p>很自然地，如果区域&nbsp;<code>X</code> 包含区域&nbsp;<code>Y</code> ，那么区域&nbsp;<code>X</code> &nbsp;比区域&nbsp;<code>Y</code> 大。</p>

<p>给定两个区域&nbsp;<code>region1</code>&nbsp;和&nbsp;<code>region2</code> ，找到同时包含这两个区域的&nbsp;<strong>最小&nbsp;</strong>区域。</p>

<p>如果区域列表中&nbsp;<code>r1</code>&nbsp;包含&nbsp;<code>r2</code>&nbsp;和&nbsp;<code>r3</code> ，那么数据保证&nbsp;<code>r2</code> 不会包含&nbsp;<code>r3</code>&nbsp;。</p>

<p>数据同样保证最小公共区域一定存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>regions = [[&quot;Earth&quot;,&quot;North America&quot;,&quot;South America&quot;],
[&quot;North America&quot;,&quot;United States&quot;,&quot;Canada&quot;],
[&quot;United States&quot;,&quot;New York&quot;,&quot;Boston&quot;],
[&quot;Canada&quot;,&quot;Ontario&quot;,&quot;Quebec&quot;],
[&quot;South America&quot;,&quot;Brazil&quot;]],
region1 = &quot;Quebec&quot;,
region2 = &quot;New York&quot;
<strong>输出：</strong>&quot;North America&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= regions.length &lt;= 10^4</code></li>
	<li><code>region1 != region2</code></li>
	<li>所有字符串只包含英文字母和空格，且最多只有&nbsp;20 个字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目可转换为“求最近公共祖先”问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
