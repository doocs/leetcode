# [1094. Car Pooling](https://leetcode.com/problems/car-pooling)

[中文文档](/solution/1000-1099/1094.Car%20Pooling/README.md)

## Description

<p>There is a car with <code>capacity</code> empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).</p>

<p>You are given the integer <code>capacity</code> and an array <code>trips</code> where <code>trips[i] = [numPassengers<sub>i</sub>, from<sub>i</sub>, to<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> trip has <code>numPassengers<sub>i</sub></code> passengers and the locations to pick them up and drop them off are <code>from<sub>i</sub></code> and <code>to<sub>i</sub></code> respectively. The locations are given as the number of kilometers due east from the car&#39;s initial location.</p>

<p>Return <code>true</code><em> if it is possible to pick up and drop off all passengers for all the given trips, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> trips = [[2,1,5],[3,3,7]], capacity = 4
<strong>Output:</strong> false
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> trips = [[2,1,5],[3,3,7]], capacity = 5
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= trips.length &lt;= 1000</code></li>
	<li><code>trips[i].length == 3</code></li>
	<li><code>1 &lt;= numPassengers<sub>i</sub> &lt;= 100</code></li>
	<li><code>0 &lt;= from<sub>i</sub> &lt; to<sub>i</sub> &lt;= 1000</code></li>
	<li><code>1 &lt;= capacity &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        delta = [0] * 1001
        for num, start, end in trips:
            delta[start] += num
            delta[end] -= num
        return all(s <= capacity for s in accumulate(delta))
```

### **Java**

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] delta = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (int num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} trips
 * @param {number} capacity
 * @return {boolean}
 */
var carPooling = function (trips, capacity) {
    let delta = new Array(1001).fill(0);
    for (let [num, start, end] of trips) {
        delta[start] += num;
        delta[end] -= num;
    }
    let s = 0;
    for (let num of delta) {
        s += num;
        if (s > capacity) {
            return false;
        }
    }
    return true;
};
```

### **C++**

```cpp
class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        vector<int> delta(1001);
        for (auto& trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (auto& num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func carPooling(trips [][]int, capacity int) bool {
	delta := make([]int, 1010)
	for _, trip := range trips {
		num, start, end := trip[0], trip[1], trip[2]
		delta[start] += num
		delta[end] -= num
	}
	cur := 0
	for _, num := range delta {
		cur += num
		if cur > capacity {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
