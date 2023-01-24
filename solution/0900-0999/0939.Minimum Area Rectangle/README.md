# [939. 最小面积矩形](https://leetcode.cn/problems/minimum-area-rectangle)

[English Version](/solution/0900-0999/0939.Minimum%20Area%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，其中矩形的边平行于 x 轴和 y 轴。</p>

<p>如果没有任何矩形，就返回 0。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[[1,1],[1,3],[3,1],[3,3],[2,2]]
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>0 &lt;=&nbsp;points[i][0] &lt;=&nbsp;40000</code></li>
	<li><code>0 &lt;=&nbsp;points[i][1] &lt;=&nbsp;40000</code></li>
	<li>所有的点都是不同的。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 排序 + 枚举**

对于每个点，我们将其横坐标作为键，纵坐标作为值存入哈希表 $d$ 中。对于哈希表中的每个键，我们将其对应的纵坐标按照从小到大的顺序排序。

然后我们从小到大枚举横坐标，对于每个横坐标，我们枚举其对应的纵坐标中的所有点对 $(y_1, y_2)$，其中 $y_1 \lt y_2$。如果我们之前已经遇到过点对 $(y_1, y_2)$，那么就可以用当前的横坐标和之前的横坐标计算出一个矩形的面积。我们用哈希表 $pos$ 记录每个点对 $(y_1, y_2)$ 第一次出现的横坐标，这样我们就可以在常数时间内找到这个横坐标。

最后，我们返回所有矩形的面积中的最小值。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n^2)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAreaRect(self, points: List[List[int]]) -> int:
        d = defaultdict(list)
        for x, y in points:
            d[x].append(y)
        pos = {}
        ans = inf
        for x in sorted(d):
            ys = d[x]
            ys.sort()
            n = len(ys)
            for i, y1 in enumerate(ys):
                for y2 in ys[i + 1 :]:
                    if (y1, y2) in pos:
                        ans = min(ans, (x - pos[(y1, y2)]) * (y2 - y1))
                    pos[(y1, y2)] = x
        return 0 if ans == inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> d = new TreeMap<>();
        for (var p : points) {
            int x = p[0], y = p[1];
            d.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
        }
        Map<Integer, Integer> pos = new HashMap<>();
        int ans = 1 << 30;
        for (var e : d.entrySet()) {
            int x = e.getKey();
            var ys = e.getValue();
            Collections.sort(ys);
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                int y1 = ys.get(i);
                for (int j = i + 1; j < n; ++j) {
                    int y2 = ys.get(j);
                    int p = y1 * 40001 + y2;
                    if (pos.containsKey(p)) {
                        ans = Math.min(ans, (x - pos.get(p)) * (y2 - y1));
                    }
                    pos.put(p, x);
                }
            }
        }
        return ans == 1 << 30 ? 0 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minAreaRect(vector<vector<int>>& points) {
        map<int, vector<int>> d;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            d[x].emplace_back(y);
        }
        unordered_map<int, int> pos;
        int ans = 1 << 30;
        for (auto& [x, ys] : d) {
            sort(ys.begin(), ys.end());
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                int y1 = ys[i];
                for (int j = i + 1; j < n; ++j) {
                    int y2 = ys[j];
                    int p = y1 * 40001 + y2;
                    if (pos.count(p)) {
                        ans = min(ans, (x - pos[p]) * (y2 - y1));
                    }
                    pos[p] = x;
                }
            }
        }
        return ans == 1 << 30 ? 0 : ans;
    }
};
```

### **Go**

```go
func minAreaRect(points [][]int) int {
	d := map[int][]int{}
	xs := []int{}
	for _, p := range points {
		x, y := p[0], p[1]
		d[x] = append(d[x], y)
	}
	for x := range d {
		xs = append(xs, x)
	}
	sort.Ints(xs)
	type pair struct{ x, y int }
	pos := map[pair]int{}
	ans := 1 << 30
	for _, x := range xs {
		ys := d[x]
		sort.Ints(ys)
		for i, y1 := range ys {
			for _, y2 := range ys[i+1:] {
				p := pair{y1, y2}
				if x1, ok := pos[p]; ok {
					ans = min(ans, (x-x1)*(y2-y1))
				}
				pos[p] = x
			}
		}
	}
	if ans == 1<<30 {
		return 0
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
