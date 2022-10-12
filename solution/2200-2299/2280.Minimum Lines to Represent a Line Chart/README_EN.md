# [2280. Minimum Lines to Represent a Line Chart](https://leetcode.com/problems/minimum-lines-to-represent-a-line-chart)

[中文文档](/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/README.md)

## Description

<p>You are given a 2D integer array <code>stockPrices</code> where <code>stockPrices[i] = [day<sub>i</sub>, price<sub>i</sub>]</code> indicates the price of the stock on day <code>day<sub>i</sub></code> is <code>price<sub>i</sub></code>. A <strong>line chart</strong> is created from the array by plotting the points on an XY plane with the X-axis representing the day and the Y-axis representing the price and connecting adjacent points. One such example is shown below:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/1920px-pushkin_population_historysvg.png" style="width: 500px; height: 313px;" />
<p>Return <em>the <strong>minimum number of lines</strong> needed to represent the line chart</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/ex0.png" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The diagram above represents the input, with the X-axis representing the day and Y-axis representing the price.
The following 3 lines can be drawn to represent the line chart:
- Line 1 (in red) from (1,7) to (4,4) passing through (1,7), (2,6), (3,5), and (4,4).
- Line 2 (in blue) from (4,4) to (5,4).
- Line 3 (in green) from (5,4) to (8,1) passing through (5,4), (6,3), (7,2), and (8,1).
It can be shown that it is not possible to represent the line chart using less than 3 lines.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/ex1.png" style="width: 325px; height: 325px;" />
<pre>
<strong>Input:</strong> stockPrices = [[3,4],[1,2],[7,8],[2,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
As shown in the diagram above, the line chart can be represented with a single line.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stockPrices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>stockPrices[i].length == 2</code></li>
	<li><code>1 &lt;= day<sub>i</sub>, price<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>All <code>day<sub>i</sub></code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumLines(self, stockPrices: List[List[int]]) -> int:
        stockPrices.sort()
        dx, dy = 0, 1
        ans = 0
        for (x, y), (x1, y1) in pairwise(stockPrices):
            dx1, dy1 = x1 - x, y1 - y
            if dy * dx1 != dx * dy1:
                ans += 1
            dx, dy = dx1, dy1
        return ans
```

### **Java**

```java
class Solution {
    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
        int dx = 0, dy = 1;
        int ans = 0;
        for (int i = 1; i < stockPrices.length; ++i) {
            int x = stockPrices[i - 1][0], y = stockPrices[i - 1][1];
            int x1 = stockPrices[i][0], y1 = stockPrices[i][1];
            int dx1 = x1 - x, dy1 = y1 - y;
            if (dy * dx1 != dx * dy1) {
                ++ans;
            }
            dx = dx1;
            dy = dy1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumLines(vector<vector<int>>& stockPrices) {
        sort(stockPrices.begin(), stockPrices.end());
        int dx = 0, dy = 1;
        int ans = 0;
        for (int i = 1; i < stockPrices.size(); ++i) {
            int x = stockPrices[i - 1][0], y = stockPrices[i - 1][1];
            int x1 = stockPrices[i][0], y1 = stockPrices[i][1];
            int dx1 = x1 - x, dy1 = y1 - y;
            if ((long long)dy * dx1 != (long long)dx * dy1) ++ans;
            dx = dx1;
            dy = dy1;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumLines(stockPrices [][]int) int {
	ans := 0
	sort.Slice(stockPrices, func(i, j int) bool { return stockPrices[i][0] < stockPrices[j][0] })
	for i, dx, dy := 1, 0, 1; i < len(stockPrices); i++ {
		x, y := stockPrices[i-1][0], stockPrices[i-1][1]
		x1, y1 := stockPrices[i][0], stockPrices[i][1]
		dx1, dy1 := x1-x, y1-y
		if dy*dx1 != dx*dy1 {
			ans++
		}
		dx, dy = dx1, dy1
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumLines(stockPrices: number[][]): number {
    const n = stockPrices.length;
    stockPrices.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    let pre = [BigInt(0), BigInt(0)];
    for (let i = 1; i < n; i++) {
        const [x1, y1] = stockPrices[i - 1];
        const [x2, y2] = stockPrices[i];
        const dx = BigInt(x2 - x1),
            dy = BigInt(y2 - y1);
        if (i == 1 || dx * pre[1] !== dy * pre[0]) ans++;
        pre = [dx, dy];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
