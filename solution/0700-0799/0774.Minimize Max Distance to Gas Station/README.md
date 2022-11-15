# [774. 最小化去加油站的最大距离](https://leetcode.cn/problems/minimize-max-distance-to-gas-station)

[English Version](/solution/0700-0799/0774.Minimize%20Max%20Distance%20to%20Gas%20Station/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>整数数组 <code>stations</code> 表示 <strong>水平数轴</strong> 上各个加油站的位置。给你一个整数 <code>k</code> 。</p>

<p>请你在数轴上增设 <code>k</code> 个加油站，新增加油站可以位于 <strong>水平数轴</strong> 上的任意位置，而不必放在整数位置上。</p>

<p>设 <code>penalty()</code> 是：增设 <code>k</code> 个新加油站后，<strong>相邻</strong> 两个加油站间的最大距离。</p>
请你返回 <code>penalty()</code><strong> </strong>可能的最小值。与实际答案误差在 <code>10<sup>-6</sup></code> 范围内的答案将被视作正确答案。

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stations = [1,2,3,4,5,6,7,8,9,10], k = 9
<strong>输出：</strong>0.50000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stations = [23,24,36,39,46,56,57,65,84,98], k = 1
<strong>输出：</strong>14.00000
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>10 <= stations.length <= 2000</code></li>
	<li><code>0 <= stations[i] <= 10<sup>8</sup></code></li>
	<li><code>stations</code> 按 <strong>严格递增</strong> 顺序排列</li>
	<li><code>1 <= k <= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找（浮点数二分）**

我们二分枚举相邻两个加油站间的距离，找到最小的距离，使得加油站的数量不超过 $k$。

时间复杂度 $O(n\log M)$。其中 $n$ 为加油站的数量；而 $M$ 为答案的范围，即 $10^8$ 除以答案的精度 $10^{-6}$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
