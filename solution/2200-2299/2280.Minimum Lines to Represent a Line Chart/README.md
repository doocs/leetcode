# [2280. 表示一个折线图的最少线段数](https://leetcode.cn/problems/minimum-lines-to-represent-a-line-chart)

[English Version](/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>stockPrices</code> ，其中&nbsp;<code>stockPrices[i] = [day<sub>i</sub>, price<sub>i</sub>]</code>&nbsp;表示股票在&nbsp;<code>day<sub>i</sub></code>&nbsp;的价格为&nbsp;<code>price<sub>i</sub></code>&nbsp;。<strong>折线图</strong>&nbsp;是一个二维平面上的若干个点组成的图，横坐标表示日期，纵坐标表示价格，折线图由相邻的点连接而成。比方说下图是一个例子：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/1920px-pushkin_population_historysvg.png" style="width: 500px; height: 313px;">
<p>请你返回要表示一个折线图所需要的 <strong>最少线段数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/ex0.png" style="width: 400px; height: 400px;"></p>

<pre><b>输入：</b>stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
<b>输出：</b>3
<strong>解释：</strong>
上图为输入对应的图，横坐标表示日期，纵坐标表示价格。
以下 3 个线段可以表示折线图：
- 线段 1 （红色）从 (1,7) 到 (4,4) ，经过 (1,7) ，(2,6) ，(3,5) 和 (4,4) 。
- 线段 2 （蓝色）从 (4,4) 到 (5,4) 。
- 线段 3 （绿色）从 (5,4) 到 (8,1) ，经过 (5,4) ，(6,3) ，(7,2) 和 (8,1) 。
可以证明，无法用少于 3 条线段表示这个折线图。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2280.Minimum%20Lines%20to%20Represent%20a%20Line%20Chart/images/ex1.png" style="width: 325px; height: 325px;"></p>

<pre><b>输入：</b>stockPrices = [[3,4],[1,2],[7,8],[2,3]]
<b>输出：</b>1
<strong>解释：</strong>
如上图所示，折线图可以用一条线段表示。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stockPrices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>stockPrices[i].length == 2</code></li>
	<li><code>1 &lt;= day<sub>i</sub>, price<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>所有&nbsp;<code>day<sub>i</sub></code>&nbsp;<strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：斜率比较**

需要注意：

1. 只有一个点时，需要的线段数为 0；
1. 利用除法计算斜率时，会有浮点误差，可以改成乘法比较。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
