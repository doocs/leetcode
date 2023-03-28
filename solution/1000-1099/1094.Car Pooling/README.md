# [1094. 拼车](https://leetcode.cn/problems/car-pooling)

[English Version](/solution/1000-1099/1094.Car%20Pooling/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>车上最初有&nbsp;<code>capacity</code>&nbsp;个空座位。车&nbsp;<strong>只能&nbsp;</strong>向一个方向行驶（也就是说，<strong>不允许掉头或改变方向</strong>）</p>

<p>给定整数&nbsp;<code>capacity</code>&nbsp;和一个数组 <code>trips</code> , &nbsp;<code>trip[i] = [numPassengers<sub>i</sub>, from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;表示第 <code>i</code> 次旅行有&nbsp;<code>numPassengers<sub>i</sub></code>&nbsp;乘客，接他们和放他们的位置分别是&nbsp;<code>from<sub>i</sub></code>&nbsp;和&nbsp;<code>to<sub>i</sub></code>&nbsp;。这些位置是从汽车的初始位置向东的公里数。</p>

<p>当且仅当你可以在所有给定的行程中接送所有乘客时，返回&nbsp;<code>true</code>，否则请返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>trips = [[2,1,5],[3,3,7]], capacity = 4
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>trips = [[2,1,5],[3,3,7]], capacity = 5
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= trips.length &lt;= 1000</code></li>
	<li><code>trips[i].length == 3</code></li>
	<li><code>1 &lt;= numPassengers<sub>i</sub>&nbsp;&lt;= 100</code></li>
	<li><code>0 &lt;= from<sub>i</sub>&nbsp;&lt; to<sub>i</sub>&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= capacity &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组**

我们可以利用差分数组的思想，将每个行程的乘客数加到起点，减去终点，最后遍历差分数组，若当前乘客数大于容量，则返回 `false`，否则返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为行程数和行程中的最大终点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        d = [0] * 1001
        for x, f, t in trips:
            d[f] += x
            d[t] -= x
        return all(s <= capacity for s in accumulate(d))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] d = new int[1001];
        for (var trip : trips) {
            int x = trip[0], f = trip[1], t = trip[2];
            d[f] += x;
            d[t] -= x;
        }
        int s = 0;
        for (int x : d) {
            s += x;
            if (s > capacity) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        int d[1001]{};
        for (auto& trip : trips) {
            int x = trip[0], f = trip[1], t = trip[2];
            d[f] += x;
            d[t] -= x;
        }
        int s = 0;
        for (int x : d) {
            s += x;
            if (s > capacity) {
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
	d := [1001]int{}
	for _, trip := range trips {
		x, f, t := trip[0], trip[1], trip[2]
		d[f] += x
		d[t] -= x
	}
	s := 0
	for _, x := range d {
		s += x
		if s > capacity {
			return false
		}
	}
	return true
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
    const d = new Array(1001).fill(0);
    for (const [x, f, t] of trips) {
        d[f] += x;
        d[t] -= x;
    }
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > capacity) {
            return false;
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function carPooling(trips: number[][], capacity: number): boolean {
    const d = new Array(1001).fill(0);
    for (const [x, f, t] of trips) {
        d[f] += x;
        d[t] -= x;
    }
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > capacity) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
