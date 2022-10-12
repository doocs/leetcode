# [1184. Distance Between Bus Stops](https://leetcode.com/problems/distance-between-bus-stops)

[中文文档](/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/README.md)

## Description

<p>A bus&nbsp;has <code>n</code> stops numbered from <code>0</code> to <code>n - 1</code> that form&nbsp;a circle. We know the distance between all pairs of neighboring stops where <code>distance[i]</code> is the distance between the stops number&nbsp;<code>i</code> and <code>(i + 1) % n</code>.</p>

<p>The bus goes along both directions&nbsp;i.e. clockwise and counterclockwise.</p>

<p>Return the shortest distance between the given&nbsp;<code>start</code>&nbsp;and <code>destination</code>&nbsp;stops.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1.jpg" style="width: 388px; height: 240px;" /></p>

<pre>

<strong>Input:</strong> distance = [1,2,3,4], start = 0, destination = 1

<strong>Output:</strong> 1

<strong>Explanation:</strong> Distance between 0 and 1 is 1 or 9, minimum is 1.</pre>

<p>&nbsp;</p>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-1.jpg" style="width: 388px; height: 240px;" /></p>

<pre>

<strong>Input:</strong> distance = [1,2,3,4], start = 0, destination = 2

<strong>Output:</strong> 3

<strong>Explanation:</strong> Distance between 0 and 2 is 3 or 7, minimum is 3.

</pre>

<p>&nbsp;</p>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-2.jpg" style="width: 388px; height: 240px;" /></p>

<pre>

<strong>Input:</strong> distance = [1,2,3,4], start = 0, destination = 3

<strong>Output:</strong> 4

<strong>Explanation:</strong> Distance between 0 and 3 is 6 or 4, minimum is 4.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= n&nbsp;&lt;= 10^4</code></li>

    <li><code>distance.length == n</code></li>

    <li><code>0 &lt;= start, destination &lt; n</code></li>

    <li><code>0 &lt;= distance[i] &lt;= 10^4</code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
