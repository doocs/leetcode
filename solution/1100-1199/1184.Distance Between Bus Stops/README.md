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

**方法一：一次遍历**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        if start > destination:
            start, destination = destination, start
        a = sum(distance[start:destination])
        b = sum(distance[:start]) + sum(distance[destination:])
        return min(a, b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int a = 0, b = 0;
        for (int i = 0; i < distance.length; ++i) {
            if (i >= start && i < destination) {
                a += distance[i];
            } else {
                b += distance[i];
            }
        }
        return Math.min(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        if (start > destination) return distanceBetweenBusStops(distance, destination, start);
        int a = 0, b = 0;
        for (int i = 0; i < distance.size(); ++i) {
            if (i >= start && i < destination)
                a += distance[i];
            else
                b += distance[i];
        }
        return min(a, b);
    }
};
```

### **Go**

```go
func distanceBetweenBusStops(distance []int, start int, destination int) int {
	if start > destination {
		return distanceBetweenBusStops(distance, destination, start)
	}
	a, b := 0, 0
	for i, v := range distance {
		if i >= start && i < destination {
			a += v
		} else {
			b += v
		}
	}
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
    if (start > destination) {
        return distanceBetweenBusStops(distance, destination, start);
    }
    let a = 0;
    let b = 0;
    for (let i = 0; i < distance.length; ++i) {
        if (i >= start && i < destination) {
            a += distance[i];
        } else {
            b += distance[i];
        }
    }
    return Math.min(a, b);
};
```

### **...**

```

```

<!-- tabs:end -->
