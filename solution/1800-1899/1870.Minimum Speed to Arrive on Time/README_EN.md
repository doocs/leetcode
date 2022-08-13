# [1870. Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time)

[中文文档](/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README.md)

## Description

<p>You are given a floating-point number <code>hour</code>, representing the amount of time you have to reach the office. To commute to the office, you must take <code>n</code> trains in sequential order. You are also given an integer array <code>dist</code> of length <code>n</code>, where <code>dist[i]</code> describes the distance (in kilometers) of the <code>i<sup>th</sup></code> train ride.</p>

<p>Each train can only depart at an integer hour, so you may need to wait in between each train ride.</p>

<ul>
	<li>For example, if the <code>1<sup>st</sup></code> train ride takes <code>1.5</code> hours, you must wait for an additional <code>0.5</code> hours before you can depart on the <code>2<sup>nd</sup></code> train ride at the 2 hour mark.</li>
</ul>

<p>Return <em>the <strong>minimum positive integer</strong> speed <strong>(in kilometers per hour)</strong> that all the trains must travel at for you to reach the office on time, or </em><code>-1</code><em> if it is impossible to be on time</em>.</p>

<p>Tests are generated such that the answer will not exceed <code>10<sup>7</sup></code> and <code>hour</code> will have <strong>at most two digits after the decimal point</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 6
<strong>Output:</strong> 1
<strong>Explanation: </strong>At speed 1:
- The first train ride takes 1/1 = 1 hour.
- Since we are already at an integer hour, we depart immediately at the 1 hour mark. The second train takes 3/1 = 3 hours.
- Since we are already at an integer hour, we depart immediately at the 4 hour mark. The third train takes 2/1 = 2 hours.
- You will arrive at exactly the 6 hour mark.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 2.7
<strong>Output:</strong> 3
<strong>Explanation: </strong>At speed 3:
- The first train ride takes 1/3 = 0.33333 hours.
- Since we are not at an integer hour, we wait until the 1 hour mark to depart. The second train ride takes 3/3 = 1 hour.
- Since we are already at an integer hour, we depart immediately at the 2 hour mark. The third train takes 2/3 = 0.66667 hours.
- You will arrive at the 2.66667 hour mark.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 1.9
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible because the earliest the third train can depart is at the 2 hour mark.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= dist[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= hour &lt;= 10<sup>9</sup></code></li>
	<li>There will be at most two digits after the decimal point in <code>hour</code>.</li>
</ul>

## Solutions

Binary search.

Template 1:

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

Template 2:

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour

        left, right = 1, 10**7
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left if check(left) else -1
```

### **Java**

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 1, right = (int) 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    private boolean check(int[] dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.length; ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.length - 1 ? cost : Math.ceil(cost));
        }
        return res <= hour;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        int left = 1, right = 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    bool check(vector<int>& dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.size(); ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.size() - 1 ? cost : ceil(cost));
        }
        return res <= hour;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) return -1;
    let left = 1,
        right = 10 ** 7;
    while (left < right) {
        let mid = (left + right) >> 1;
        if (arriveOnTime(dist, mid, hour)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};

function arriveOnTime(dist, speed, hour) {
    let res = 0.0;
    let n = dist.length;
    for (let i = 0; i < n; i++) {
        let cost = parseFloat(dist[i]) / speed;
        if (i != n - 1) {
            cost = Math.ceil(cost);
        }
        res += cost;
    }
    return res <= hour;
}
```

### **Go**

```go
func minSpeedOnTime(dist []int, hour float64) int {
	n := len(dist)
	left, right := 1, int(1e7)
	check := func(speed float64) bool {
		var cost float64
		for _, v := range dist[:n-1] {
			cost += math.Ceil(float64(v) / speed)
		}
		cost += float64(dist[n-1]) / speed
		return cost <= hour

	}
	for left < right {
		mid := (left + right) >> 1
		if check(float64(mid)) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if check(float64(left)) {
		return left
	}
	return -1
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        let n = dist.len();

        let check = |speed| {
            let mut cur = 0.;
            for (i, &d) in dist.iter().enumerate() {
                if i == n - 1 {
                    cur += d as f64 / speed as f64;
                } else {
                    cur += (d as f64 / speed as f64).ceil();
                }
            }
            cur <= hour
        };

        let mut left = 1;
        let mut right = 1e7 as i32;
        while left < right {
            let mid = left + (right - left) / 2;
            if !check(mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if check(left) {
            return left;
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
