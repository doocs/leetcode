# [1109. 航班预订统计](https://leetcode-cn.com/problems/corporate-flight-bookings)

[English Version](/solution/1100-1199/1109.Corporate%20Flight%20Bookings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这里有 <code>n</code> 个航班，它们分别从 <code>1</code> 到 <code>n</code> 进行编号。</p>

<p>有一份航班预订表 <code>bookings</code> ，表中第 <code>i</code> 条预订记录 <code>bookings[i] = [first<sub>i</sub>, last<sub>i</sub>, seats<sub>i</sub>]</code> 意味着在从 <code>first<sub>i</sub></code> 到 <code>last<sub>i</sub></code> （<strong>包含</strong> <code>first<sub>i</sub></code> 和 <code>last<sub>i</sub></code> ）的 <strong>每个航班</strong> 上预订了 <code>seats<sub>i</sub></code> 个座位。</p>

<p>请你返回一个长度为 <code>n</code> 的数组 <code>answer</code>，其中 <code>answer[i]</code> 是航班 <code>i</code> 上预订的座位总数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
<strong>输出：</strong>[10,55,45,25,25]
<strong>解释：</strong>
航班编号        1   2   3   4   5
预订记录 1 ：   10  10
预订记录 2 ：       20  20
预订记录 3 ：       25  25  25  25
总座位数：      10  55  45  25  25
因此，answer = [10,55,45,25,25]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>bookings = [[1,2,10],[2,2,15]], n = 2
<strong>输出：</strong>[10,25]
<strong>解释：</strong>
航班编号        1   2
预订记录 1 ：   10  10
预订记录 2 ：       15
总座位数：      10  25
因此，answer = [10,25]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 2 * 10<sup>4</sup></code></li>
	<li><code>1 <= bookings.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>bookings[i].length == 3</code></li>
	<li><code>1 <= first<sub>i</sub> <= last<sub>i</sub> <= n</code></li>
	<li><code>1 <= seats<sub>i</sub> <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

差分数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
var corpFlightBookings = function (bookings, n) {
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
