# [149. Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line)

[中文文档](/solution/0100-0199/0149.Max%20Points%20on%20a%20Line/README.md)

## Description

<p>Given an array of <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a point on the <strong>X-Y</strong> plane, return <em>the maximum number of points that lie on the same straight line</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0149.Max%20Points%20on%20a%20Line/images/plane1.jpg" style="width: 300px; height: 294px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3]]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0149.Max%20Points%20on%20a%20Line/images/plane2.jpg" style="width: 300px; height: 294px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 300</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>All the <code>points</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        def gcd(a, b) -> int:
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        if n < 3:
            return n
        res = 0
        for i in range(n - 1):
            counter = Counter()
            t_max = duplicate = 0
            for j in range(i + 1, n):
                delta_x = points[i][0] - points[j][0]
                delta_y = points[i][1] - points[j][1]
                if delta_x == 0 and delta_y == 0:
                    duplicate += 1
                    continue
                g = gcd(delta_x, delta_y)
                d_x = delta_x // g
                d_y = delta_y // g
                key = f'{d_x}.{d_y}'
                counter[key] += 1
                t_max = max(t_max, counter[key])
            res = max(res, t_max + duplicate + 1)
        return res
```

### **Java**

```java
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return n;
        }
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            Map<String, Integer> kCounter = new HashMap<>();
            int max = 0;
            int duplicate = 0;
            for (int j = i + 1; j < n; ++j) {
                int deltaX = points[i][0] - points[j][0];
                int deltaY = points[i][1] - points[j][1];
                if (deltaX == 0 && deltaY == 0) {
                    ++duplicate;
                    continue;
                }
                int gcd = gcd(deltaX, deltaY);
                int dX = deltaX / gcd;
                int dY = deltaY / gcd;
                String key = dX + "." + dY;
                kCounter.put(key, kCounter.getOrDefault(key, 0) + 1);
                max = Math.max(max, kCounter.get(key));
            }
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **Go**

```go
func maxPoints(points [][]int) int {
	type pair struct {
		first  int
		second int
	}
	n := len(points)
	if n <= 2 {
		return n
	}
	ans := 0
	for i := 0; i < n-1; i++ {
		freq := make(map[pair]int)
		for j := i + 1; j < n; j++ {
			x1, y1, x2, y2 := points[i][0], points[i][1], points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1
			g := gcd(dx, dy)
			p := pair{dx / g, dy / g}
			freq[p]++
			ans = max(ans, freq[p]+1)
		}
	}
	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
