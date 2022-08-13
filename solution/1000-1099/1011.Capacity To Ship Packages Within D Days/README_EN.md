# [1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days)

[中文文档](/solution/1000-1099/1011.Capacity%20To%20Ship%20Packages%20Within%20D%20Days/README.md)

## Description

<p>A conveyor belt has packages that must be shipped from one port to another within <code>days</code> days.</p>

<p>The <code>i<sup>th</sup></code> package on the conveyor belt has a weight of <code>weights[i]</code>. Each day, we load the ship with packages on the conveyor belt (in the order given by <code>weights</code>). We may not load more weight than the maximum weight capacity of the ship.</p>

<p>Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within <code>days</code> days.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> weights = [1,2,3,4,5,6,7,8,9,10], days = 5

<strong>Output:</strong> 15

<strong>Explanation:</strong> A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:

1st day: 1, 2, 3, 4, 5

2nd day: 6, 7

3rd day: 8

4th day: 9

5th day: 10



Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> weights = [3,2,2,4,1,4], days = 3

<strong>Output:</strong> 6

<strong>Explanation:</strong> A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:

1st day: 3, 2

2nd day: 2, 4

3rd day: 1, 4

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> weights = [1,2,3,1,1], days = 4

<strong>Output:</strong> 3

<strong>Explanation:</strong>

1st day: 1

2nd day: 2

3rd day: 3

4th day: 1, 1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= days &lt;= weights.length &lt;= 5 * 10<sup>4</sup></code></li>
    <li><code>1 &lt;= weights[i] &lt;= 500</code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shipWithinDays(self, weights: List[int], D: int) -> int:
        def check(capacity):
            cnt, t = 1, 0
            for w in weights:
                if w > capacity:
                    return False
                if t + w <= capacity:
                    t += w
                else:
                    cnt += 1
                    t = w
            return cnt <= D

        left, right = 1, 25000000
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

```java
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canCarry(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public boolean canCarry(int[] weights, int days, int carry) {
        int useDay = 1;
        int curCarry = 0;
        for (int weight : weights) {
            if (weight > carry) {
                return false;
            }
            if ((carry - curCarry) >= weight) {
                curCarry += weight;
            } else {
                curCarry = weight;
                useDay++;
            }
        }
        return useDay <= days;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int left = 1, right = 25000000;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(vector<int>& weights, int days, int capacity) {
        int cnt = 1, t = 0;
        for (auto w : weights) {
            if (w > capacity) {
                return false;
            }
            if (t + w <= capacity) {
                t += w;
            } else {
                ++cnt;
                t = w;
            }
        }
        return cnt <= days;
    }
};
```

### **Go**

```go
func shipWithinDays(weights []int, days int) int {
	left, right := 1, 25000000
	for left < right {
		mid := (left + right) >> 1
		if check(weights, days, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(weights []int, days, capacity int) bool {
	cnt, t := 1, 0
	for _, w := range weights {
		if w > capacity {
			return false
		}
		if t+w <= capacity {
			t += w
		} else {
			cnt++
			t = w
		}
	}
	return cnt <= days
}
```

### **...**

```

```

<!-- tabs:end -->
