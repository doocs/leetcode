# [2187. 完成旅途的最少时间](https://leetcode.cn/problems/minimum-time-to-complete-trips)

[English Version](/solution/2100-2199/2187.Minimum%20Time%20to%20Complete%20Trips/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>time</code>&nbsp;，其中&nbsp;<code>time[i]</code>&nbsp;表示第 <code>i</code>&nbsp;辆公交车完成 <strong>一趟</strong><strong>旅途</strong>&nbsp;所需要花费的时间。</p>

<p>每辆公交车可以 <strong>连续</strong> 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 <strong>立马开始</strong>&nbsp;下一趟旅途。每辆公交车 <strong>独立</strong>&nbsp;运行，也就是说可以同时有多辆公交车在运行且互不影响。</p>

<p>给你一个整数&nbsp;<code>totalTrips</code>&nbsp;，表示所有公交车&nbsp;<strong>总共</strong>&nbsp;需要完成的旅途数目。请你返回完成 <strong>至少</strong>&nbsp;<code>totalTrips</code>&nbsp;趟旅途需要花费的 <strong>最少</strong>&nbsp;时间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>time = [1,2,3], totalTrips = 5
<b>输出：</b>3
<strong>解释：</strong>
- 时刻 t = 1 ，每辆公交车完成的旅途数分别为 [1,0,0] 。
  已完成的总旅途数为 1 + 0 + 0 = 1 。
- 时刻 t = 2 ，每辆公交车完成的旅途数分别为 [2,1,0] 。
  已完成的总旅途数为 2 + 1 + 0 = 3 。
- 时刻 t = 3 ，每辆公交车完成的旅途数分别为 [3,1,1] 。
  已完成的总旅途数为 3 + 1 + 1 = 5 。
所以总共完成至少 5 趟旅途的最少时间为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>time = [2], totalTrips = 1
<b>输出：</b>2
<strong>解释：</strong>
只有一辆公交车，它将在时刻 t = 2 完成第一趟旅途。
所以完成 1 趟旅途的最少时间为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= time[i], totalTrips &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTime(self, time: List[int], totalTrips: int) -> int:
        mx = min(time) * totalTrips
        return bisect_left(
            range(mx), totalTrips, key=lambda x: sum(x // v for v in time)
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
