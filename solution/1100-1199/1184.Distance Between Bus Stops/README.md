# [1184. 公交站间的距离](https://leetcode.cn/problems/distance-between-bus-stops)

[English Version](/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>环形公交路线上有&nbsp;<code>n</code>&nbsp;个站，按次序从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;进行编号。我们已知每一对相邻公交站之间的距离，<code>distance[i]</code>&nbsp;表示编号为&nbsp;<code>i</code>&nbsp;的车站和编号为&nbsp;<code>(i + 1) % n</code>&nbsp;的车站之间的距离。</p>

<p>环线上的公交车都可以按顺时针和逆时针的方向行驶。</p>

<p>返回乘客从出发点&nbsp;<code>start</code>&nbsp;到目的地&nbsp;<code>destination</code>&nbsp;之间的最短距离。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 1
<strong>输出：</strong>1
<strong>解释：</strong>公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-1.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 2
<strong>输出：</strong>3
<strong>解释：</strong>公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-2.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 3
<strong>输出：</strong>4
<strong>解释：</strong>公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n&nbsp;&lt;= 10^4</code></li>
	<li><code>distance.length == n</code></li>
	<li><code>0 &lt;= start, destination &lt; n</code></li>
	<li><code>0 &lt;= distance[i] &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以先统计出公交车的总行驶距离 $s$，然后模拟公交车的行驶过程，从出发点开始，每次向右移动一站，直到到达目的地为止。在模拟的过程中，我们可以记录从出发点到目的地的距离 $a$，那么从目的地到出发点的最短距离就是 $\min(a, s - a)$。

时间复杂度 $O(n)$，其中 $n$ 是公交车站的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        a, n = 0, len(distance)
        while start != destination:
            a += distance[start]
            start = (start + 1) % n
        return min(a, sum(distance) - a)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int s = Arrays.stream(distance).sum();
        int n = distance.length;
        int a = 0;
        while (start != destination) {
            a += distance[start];
            start = (start + 1) % n;
        }
        return Math.min(a, s - a);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        int s = accumulate(distance.begin(), distance.end(), 0);
        int a = 0, n = distance.size();
        while (start != destination) {
            a += distance[start];
            start = (start + 1) % n;
        }
        return min(a, s - a);
    }
};
```

### **Go**

```go
func distanceBetweenBusStops(distance []int, start int, destination int) int {
	s := 0
	for _, x := range distance {
		s += x
	}
	a, n := 0, len(distance)
	for start != destination {
		a += distance[start]
		start = (start + 1) % n
	}
	return min(a, s-a)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} distance
 * @param {number} start
 * @param {number} destination
 * @return {number}
 */
var distanceBetweenBusStops = function (distance, start, destination) {
    const s = distance.reduce((a, b) => a + b, 0);
    let a = 0;
    const n = distance.length;
    while (start != destination) {
        a += distance[start];
        start = (start + 1) % n;
    }
    return Math.min(a, s - a);
};
```

### **TypeScript**

```ts
function distanceBetweenBusStops(distance: number[], start: number, destination: number): number {
    const s = distance.reduce((a, b) => a + b, 0);
    let a = 0;
    const n = distance.length;
    while (start != destination) {
        a += distance[start];
        start = (start + 1) % n;
    }
    return Math.min(a, s - a);
}
```

### **...**

```

```

<!-- tabs:end -->
