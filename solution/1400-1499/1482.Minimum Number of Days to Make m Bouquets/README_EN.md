# [1482. Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets)

[中文文档](/solution/1400-1499/1482.Minimum%20Number%20of%20Days%20to%20Make%20m%20Bouquets/README.md)

## Description

<p>You are given an integer array <code>bloomDay</code>, an integer <code>m</code> and an integer <code>k</code>.</p>

<p>You want to make <code>m</code> bouquets. To make a bouquet, you need to use <code>k</code> <strong>adjacent flowers</strong> from the garden.</p>

<p>The garden consists of <code>n</code> flowers, the <code>i<sup>th</sup></code> flower will bloom in the <code>bloomDay[i]</code> and then can be used in <strong>exactly one</strong> bouquet.</p>

<p>Return <em>the minimum number of days you need to wait to be able to make </em><code>m</code><em> bouquets from the garden</em>. If it is impossible to make m bouquets return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [1,10,3,10,2], m = 3, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [1,10,3,10,2], m = 3, k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> We need 2 bouquets each should have 3 flowers.
Here is the garden after the 7 and 12 days:
After day 7: [x, x, x, x, _, x, x]
We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
After day 12: [x, x, x, x, x, x, x]
It is obvious that we can make two bouquets in different ways.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>bloomDay.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= bloomDay[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

Quickly locate using binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        if m * k > len(bloomDay):
            return -1

        def check(day: int) -> bool:
            cnt = cur = 0
            for bd in bloomDay:
                cur = cur + 1 if bd <= day else 0
                if cur == k:
                    cnt += 1
                    cur = 0
            return cnt >= m

        left, right = min(bloomDay), max(bloomDay)
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
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int bd : bloomDay) {
            min = Math.min(min, bd);
            max = Math.max(max, bd);
        }
        int left = min, right = max;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (check(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] bloomDay, int m, int k, int day) {
        int cnt = 0, cur = 0;
        for (int bd : bloomDay) {
            cur = bd <= day ? cur + 1 : 0;
            if (cur == k) {
                cnt++;
                cur = 0;
            }
        }
        return cnt >= m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDays(vector<int>& bloomDay, int m, int k) {
        if (m * k > bloomDay.size()) {
            return -1;
        }
        int mi = INT_MIN, mx = INT_MAX;
        for (int& bd : bloomDay) {
            mi = min(mi, bd);
            mx = max(mx, bd);
        }
        int left = mi, right = mx;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(vector<int>& bloomDay, int m, int k, int day) {
        int cnt = 0, cur = 0;
        for (int& bd : bloomDay) {
            cur = bd <= day ? cur + 1 : 0;
            if (cur == k) {
                ++cnt;
                cur = 0;
            }
        }
        return cnt >= m;
    }
};
```

### **Go**

```go
func minDays(bloomDay []int, m int, k int) int {
	if m*k > len(bloomDay) {
		return -1
	}
	mi, mx := 0, 1000000000
	for _, bd := range bloomDay {
		mi = min(mi, bd)
		mx = max(mx, bd)
	}
	left, right := mi, mx
	for left < right {
		mid := (left + right) >> 1
		if check(bloomDay, m, k, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(bloomDay []int, m, k, day int) bool {
	cnt, cur := 0, 0
	for _, bd := range bloomDay {
		if bd <= day {
			cur++
		} else {
			cur = 0
		}
		if cur == k {
			cnt++
			cur = 0
		}
	}
	return cnt >= m
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
