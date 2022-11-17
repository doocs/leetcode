# [774. Minimize Max Distance to Gas Station](https://leetcode.com/problems/minimize-max-distance-to-gas-station)

[中文文档](/solution/0700-0799/0774.Minimize%20Max%20Distance%20to%20Gas%20Station/README.md)

## Description

<p>You are given an integer array <code>stations</code> that represents the positions of the gas stations on the <strong>x-axis</strong>. You are also given an integer <code>k</code>.</p>

<p>You should add <code>k</code> new gas stations. You can add the stations anywhere on the <strong>x-axis</strong>, and not necessarily on an integer position.</p>

<p>Let <code>penalty()</code> be the maximum distance between <strong>adjacent</strong> gas stations after adding the <code>k</code> new stations.</p>

<p>Return <em>the smallest possible value of</em> <code>penalty()</code>. Answers within <code>10<sup>-6</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> stations = [1,2,3,4,5,6,7,8,9,10], k = 9
<strong>Output:</strong> 0.50000
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> stations = [23,24,36,39,46,56,57,65,84,98], k = 1
<strong>Output:</strong> 14.00000
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>10 &lt;= stations.length &lt;= 2000</code></li>
	<li><code>0 &lt;= stations[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>stations</code> is sorted in a <strong>strictly increasing</strong> order.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minmaxGasDist(self, stations: List[int], k: int) -> float:
        def check(x):
            return sum(int((b - a) / x) for a, b in pairwise(stations)) <= k

        left, right = 0, 1e8
        while right - left > 1e-6:
            mid = (left + right) / 2
            if check(mid):
                right = mid
            else:
                left = mid
        return left
```

### **Java**

```java
class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0, right = 1e8;
        while (right - left > 1e-6) {
            double mid = (left + right) / 2.0;
            if (check(mid, stations, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private boolean check(double x, int[] stations, int k) {
        int s = 0;
        for (int i = 0; i < stations.length - 1; ++i) {
            s += (int) ((stations[i + 1] - stations[i]) / x);
        }
        return s <= k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double minmaxGasDist(vector<int>& stations, int k) {
        double left = 0, right = 1e8;
        auto check = [&](double x) {
            int s = 0;
            for (int i = 0; i < stations.size() - 1; ++i) {
                s += (int) ((stations[i + 1] - stations[i]) / x);
            }
            return s <= k;
        };
        while (right - left > 1e-6) {
            double mid = (left + right) / 2.0;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func minmaxGasDist(stations []int, k int) float64 {
	check := func(x float64) bool {
		s := 0
		for i, v := range stations[:len(stations)-1] {
			s += int(float64(stations[i+1]-v) / x)
		}
		return s <= k
	}
	var left, right float64 = 0, 1e8
	for right-left > 1e-6 {
		mid := (left + right) / 2.0
		if check(mid) {
			right = mid
		} else {
			left = mid
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->
