# [1109. Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings)

[中文文档](/solution/1100-1199/1109.Corporate%20Flight%20Bookings/README.md)

## Description

<p>There are <code>n</code> flights that are labeled from <code>1</code> to <code>n</code>.</p>

<p>You are given an array of flight bookings <code>bookings</code>, where <code>bookings[i] = [first<sub>i</sub>, last<sub>i</sub>, seats<sub>i</sub>]</code> represents a booking for flights <code>first<sub>i</sub></code> through <code>last<sub>i</sub></code> (<strong>inclusive</strong>) with <code>seats<sub>i</sub></code> seats reserved for <strong>each flight</strong> in the range.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the total number of seats reserved for flight </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
<strong>Output:</strong> [10,55,45,25,25]
<strong>Explanation:</strong>
Flight labels:        1   2   3   4   5
Booking 1 reserved:  10  10
Booking 2 reserved:      20  20
Booking 3 reserved:      25  25  25  25
Total seats:         10  55  45  25  25
Hence, answer = [10,55,45,25,25]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> bookings = [[1,2,10],[2,2,15]], n = 2
<strong>Output:</strong> [10,25]
<strong>Explanation:</strong>
Flight labels:        1   2
Booking 1 reserved:  10  10
Booking 2 reserved:      15
Total seats:         10  25
Hence, answer = [10,25]

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= bookings.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>bookings[i].length == 3</code></li>
	<li><code>1 &lt;= first<sub>i</sub> &lt;= last<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= seats<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def corpFlightBookings(self, bookings: List[List[int]], n: int) -> List[int]:
        delta = [0] * n
        for first, last, seats in bookings:
            delta[first - 1] += seats
            if last < n:
                delta[last] -= seats
        for i in range(n - 1):
            delta[i + 1] += delta[i]
        return delta
```

### **Java**

```java
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            delta[first - 1] += seats;
            if (last < n) {
                delta[last] -= seats;
            }
        }
        for (int i = 0; i < n - 1; ++i) {
            delta[i + 1] += delta[i];
        }
        return delta;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} bookings
 * @param {number} n
 * @return {number[]}
 */
var corpFlightBookings = function(bookings, n) {
    let delta = new Array(n).fill(0);
    for (let book of bookings) {
        let [start, end, num] = book;
        start -= 1;
        delta[start] += num;
        if (end != n) {
            delta[end] -= num;
        }
    }
    for (let i = 1; i < n; i++) {
        delta[i] += delta[i - 1];
    }
    return delta;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
        vector<int> delta(n);
        for (auto &booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            delta[first - 1] += seats;
            if (last < n) {
                delta[last] -= seats;
            }
        }
        for (int i = 0; i < n - 1; ++i) {
            delta[i + 1] += delta[i];
        }
        return delta;
    }
};
```

### **Go**

```go
func corpFlightBookings(bookings [][]int, n int) []int {
	delta := make([]int, n)
	for _, booking := range bookings {
		first, last, seats := booking[0], booking[1], booking[2]
		delta[first-1] += seats
		if last < n {
			delta[last] -= seats
		}
	}
	for i := 0; i < n-1; i++ {
		delta[i+1] += delta[i]
	}
	return delta
}
```

### **...**

```

```

<!-- tabs:end -->
