# [2187. Minimum Time to Complete Trips](https://leetcode.com/problems/minimum-time-to-complete-trips)

[中文文档](/solution/2100-2199/2187.Minimum%20Time%20to%20Complete%20Trips/README.md)

## Description

<p>You are given an array <code>time</code> where <code>time[i]</code> denotes the time taken by the <code>i<sup>th</sup></code> bus to complete <strong>one trip</strong>.</p>

<p>Each bus can make multiple trips <strong>successively</strong>; that is, the next trip can start <strong>immediately after</strong> completing the current trip. Also, each bus operates <strong>independently</strong>; that is, the trips of one bus do not influence the trips of any other bus.</p>

<p>You are also given an integer <code>totalTrips</code>, which denotes the number of trips all buses should make <strong>in total</strong>. Return <em>the <strong>minimum time</strong> required for all buses to complete <strong>at least</strong> </em><code>totalTrips</code><em> trips</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = [1,2,3], totalTrips = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- At time t = 1, the number of trips completed by each bus are [1,0,0]. 
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0]. 
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1]. 
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = [2], totalTrips = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong>
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= time[i], totalTrips &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumTime(self, time: List[int], totalTrips: int) -> int:
        mx = min(time) * totalTrips
        return bisect_left(
            range(mx), totalTrips, key=lambda x: sum(x // v for v in time)
        )
```

### **Java**

```java
class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int mi = time[0];
        for (int v : time) {
            mi = Math.min(mi, v);
        }
        long left = 1, right = (long) mi * totalTrips;
        while (left < right) {
            long cnt = 0;
            long mid = (left + right) >> 1;
            for (int v : time) {
                cnt += mid / v;
            }
            if (cnt >= totalTrips) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumTime(vector<int>& time, int totalTrips) {
        int mi = *min_element(time.begin(), time.end());
        long long left = 1, right = (long long)mi * totalTrips;
        while (left < right) {
            long long cnt = 0;
            long long mid = (left + right) >> 1;
            for (int v : time) cnt += mid / v;
            if (cnt >= totalTrips)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func minimumTime(time []int, totalTrips int) int64 {
	left, right := 1, 10000000*totalTrips
	for left < right {
		mid := (left + right) >> 1
		cnt := 0
		for _, v := range time {
			cnt += mid / v
		}
		if cnt >= totalTrips {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return int64(left)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
