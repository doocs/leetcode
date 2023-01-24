# [939. Minimum Area Rectangle](https://leetcode.com/problems/minimum-area-rectangle)

[中文文档](/solution/0900-0999/0939.Minimum%20Area%20Rectangle/README.md)

## Description

<p>You are given an array of points in the <strong>X-Y</strong> plane <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>Return <em>the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes</em>. If there is not any such rectangle, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0939.Minimum%20Area%20Rectangle/images/rec1.jpg" style="width: 500px; height: 447px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0939.Minimum%20Area%20Rectangle/images/rec2.jpg" style="width: 500px; height: 477px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 4 * 10<sup>4</sup></code></li>
	<li>All the given points are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
