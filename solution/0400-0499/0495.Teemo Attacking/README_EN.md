# [495. Teemo Attacking](https://leetcode.com/problems/teemo-attacking)

[中文文档](/solution/0400-0499/0495.Teemo%20Attacking/README.md)

## Description

<p>You are given an integer array <code>timeSeries</code> and an integer <code>duration</code>. Our hero Teemo has attacked an enemy where the <code>i<sup>th</sup></code> attack was done at the <code>timeSeries[i]</code>. When Teemo attacks their enemy, the enemy gets poisoned for <code>duration</code> time (i.e., the enemy is poisoned for the time interval <code>[timeSeries[i], timeSeries[i] + duration - 1]</code> inclusive).</p>

<p>Return <em>the total time that the enemy is in a poisoned condition</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> timeSeries = [1,4], duration = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> At time point 1, Teemo starts attacking the enemy and makes them be poisoned immediately. 
This poisoned status will last 2 seconds until the end of time point 2. 
And at time point 4, Teemo attacks the enemy again and causes them to be in poisoned status for another 2 seconds. 
So you finally need to output 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> timeSeries = [1,2], duration = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> At time point 1, Teemo starts attacking the enemy and makes them be poisoned. 
This poisoned status will last 2 seconds until the end of time point 2. 
However, at the beginning of time point 2, Teemo attacks the enemy again who is already in poisoned status. 
Since the poisoned status won&#39;t add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3. 
So you finally need to output 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= timeSeries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= timeSeries[i], duration &lt;= 10<sup>7</sup></code></li>
	<li><code>timeSeries</code> is sorted in non-decreasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findPoisonedDuration(self, timeSeries: List[int], duration: int) -> int:
        n, res = len(timeSeries), duration
        for i in range(n - 1):
            res += min(duration, timeSeries[i + 1] - timeSeries[i])
        return res
```

### **Java**

```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length, res = duration;
        for (int i = 0; i < n - 1; ++i) {
            res += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPoisonedDuration(vector<int>& timeSeries, int duration) {
        int n = timeSeries.size(), res = duration;
        for (int i = 0; i < n - 1; ++i) {
            res += min(duration, timeSeries[i + 1] - timeSeries[i]);
        }
        return res;
    }
};
```

### **Go**

```go
func findPoisonedDuration(timeSeries []int, duration int) int {
	n, res := len(timeSeries), duration
	for i := 0; i < n-1; i++ {
		res += min(duration, timeSeries[i+1]-timeSeries[i])
	}
	return res
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
