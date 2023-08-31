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
        a, n = 0, len(distance)
        while start != destination:
            a += distance[start]
            start = (start + 1) % n
        return min(a, sum(distance) - a)
```

### **Java**

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
