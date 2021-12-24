# [1450. Number of Students Doing Homework at a Given Time](https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time)

[中文文档](/solution/1400-1499/1450.Number%20of%20Students%20Doing%20Homework%20at%20a%20Given%20Time/README.md)

## Description

<p>Given two integer arrays <code>startTime</code> and <code>endTime</code> and given an integer <code>queryTime</code>.</p>

<p>The <code>ith</code> student started doing their homework at the time <code>startTime[i]</code> and finished it at time <code>endTime[i]</code>.</p>

<p>Return <em>the number of students</em> doing their homework at time <code>queryTime</code>. More formally, return the number of students where <code>queryTime</code>&nbsp;lays in the interval <code>[startTime[i], endTime[i]]</code> inclusive.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> We have 3 students where:
The first student started doing homework at time 1 and finished at time 3 and wasn&#39;t doing anything at time 4.
The second student started doing homework at time 2 and finished at time 2 and also wasn&#39;t doing anything at time 4.
The third student started doing homework at time 3 and finished at time 7 and was the only student doing homework at time 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> startTime = [4], endTime = [4], queryTime = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only student was doing their homework at the queryTime.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> startTime = [4], endTime = [4], queryTime = 5
<strong>Output:</strong> 0
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
<strong>Output:</strong> 0
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>startTime.length == endTime.length</code></li>
	<li><code>1 &lt;= startTime.length &lt;= 100</code></li>
	<li><code>1 &lt;= startTime[i] &lt;= endTime[i] &lt;= 1000</code></li>
	<li><code>1 &lt;=&nbsp;queryTime &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def busyStudent(self, startTime: List[int], endTime: List[int], queryTime: int) -> int:
        count, n = 0, len(startTime)
        for i in range(n):
            count += startTime[i] <= queryTime <= endTime[i]
        return count
```

### **Java**

```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0, n = startTime.length;
        for (int i = 0; i < n; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++count;
            }
        }
        return count;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        int count = 0, n = startTime.size();
        for (int i = 0; i < n; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++count;
            }
        }
        return count;
    }
};
```

### **Go**

```go
func busyStudent(startTime []int, endTime []int, queryTime int) int {
	count, n := 0, len(startTime)
	for i := 0; i < n; i++ {
		if startTime[i] <= queryTime && queryTime <= endTime[i] {
			count++
		}
	}
	return count
}
```

### **...**

```

```

<!-- tabs:end -->
