# [1776. Car Fleet II](https://leetcode.com/problems/car-fleet-ii)

[中文文档](/solution/1700-1799/1776.Car%20Fleet%20II/README.md)

## Description

<p>There are <code>n</code> cars traveling at different speeds in the same direction along a one-lane road. You are given an array <code>cars</code> of length <code>n</code>, where <code>cars[i] = [position<sub>i</sub>, speed<sub>i</sub>]</code> represents:</p>

<ul>
	<li><code>position<sub>i</sub></code> is the distance between the <code>i<sup>th</sup></code> car and the beginning of the road in meters. It is guaranteed that <code>position<sub>i</sub> &lt; position<sub>i+1</sub></code>.</li>
	<li><code>speed<sub>i</sub></code> is the initial speed of the <code>i<sup>th</sup></code> car in meters per second.</li>
</ul>

<p>For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the <strong>slowest</strong> car in the fleet.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the time, in seconds, at which the <code>i<sup>th</sup></code> car collides with the next car, or <code>-1</code> if the car does not collide with the next car. Answers within <code>10<sup>-5</sup></code> of the actual answers are accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cars = [[1,2],[2,1],[4,3],[7,2]]
<strong>Output:</strong> [1.00000,-1.00000,3.00000,-1.00000]
<strong>Explanation:</strong> After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cars = [[3,4],[5,4],[6,3],[9,1]]
<strong>Output:</strong> [2.00000,1.00000,1.50000,-1.00000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cars.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= position<sub>i</sub>, speed<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>position<sub>i</sub> &lt; position<sub>i+1</sub></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getCollisionTimes(self, cars: List[List[int]]) -> List[float]:
        stk = []
        n = len(cars)
        ans = [-1] * n
        for i in range(n - 1, -1, -1):
            while stk:
                j = stk[-1]
                if cars[i][1] > cars[j][1]:
                    t = (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1])
                    if ans[j] == -1 or t <= ans[j]:
                        ans[i] = t
                        break
                stk.pop()
            stk.append(i)
        return ans
```

### **Java**

```java
class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1.0);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty()) {
                int j = stk.peek();
                if (cars[i][1] > cars[j][1]) {
                    double t = (cars[j][0] - cars[i][0]) * 1.0 / (cars[i][1] - cars[j][1]);
                    if (ans[j] < 0 || t <= ans[j]) {
                        ans[i] = t;
                        break;
                    }
                }
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<double> getCollisionTimes(vector<vector<int>>& cars) {
        int n = cars.size();
        vector<double> ans(n, -1.0);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size()) {
                int j = stk.top();
                if (cars[i][1] > cars[j][1]) {
                    double t = (cars[j][0] - cars[i][0]) * 1.0 / (cars[i][1] - cars[j][1]);
                    if (ans[j] < 0 || t <= ans[j]) {
                        ans[i] = t;
                        break;
                    }
                }
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func getCollisionTimes(cars [][]int) []float64 {
	n := len(cars)
	ans := make([]float64, n)
	for i := range ans {
		ans[i] = -1.0
	}
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 {
			j := stk[len(stk)-1]
			if cars[i][1] > cars[j][1] {
				t := float64(cars[j][0]-cars[i][0]) / float64(cars[i][1]-cars[j][1])
				if ans[j] < 0 || t <= ans[j] {
					ans[i] = t
					break
				}
			}
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
