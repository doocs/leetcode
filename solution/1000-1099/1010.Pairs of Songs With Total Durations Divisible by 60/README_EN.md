# [1010. Pairs of Songs With Total Durations Divisible by 60](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60)

[中文文档](/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README.md)

## Description

<p>You are given a list of songs where the <code>i<sup>th</sup></code> song has a duration of <code>time[i]</code> seconds.</p>

<p>Return <em>the number of pairs of songs for which their total duration in seconds is divisible by</em> <code>60</code>. Formally, we want the number of indices <code>i</code>, <code>j</code> such that <code>i &lt; j</code> with <code>(time[i] + time[j]) % 60 == 0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = [30,20,150,100,40]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = [60,60,60]
<strong>Output:</strong> 3
<strong>Explanation:</strong> All three pairs have a total duration of 120, which is divisible by 60.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = defaultdict(int)
        ans = 0
        for t in time:
            s = 60
            for _ in range(17):
                ans += cnt[s - t]
                s += 60
            cnt[t] += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[501];
        int ans = 0;
        for (int t : time) {
            int s = 60;
            for (int i = 0; i < 17; ++i) {
                if (s - t >= 0 && s - t < cnt.length) {
                    ans += cnt[s - t];
                }
                s += 60;
            }
            cnt[t]++;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[501]{};
        int ans = 0;
        for (int& t : time) {
            int s = 60;
            for (int i = 0; i < 17; ++i) {
                if (s - t >= 0 && s - t < 501) {
                    ans += cnt[s - t];
                }
                s += 60;
            }
            cnt[t]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [501]int{}
	for _, t := range time {
		s := 60
		for i := 0; i < 17; i++ {
			if s-t >= 0 && s-t < 501 {
				ans += cnt[s-t]
			}
			s += 60
		}
		cnt[t]++
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
