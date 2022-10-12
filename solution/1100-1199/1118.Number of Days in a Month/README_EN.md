# [1118. Number of Days in a Month](https://leetcode.com/problems/number-of-days-in-a-month)

[中文文档](/solution/1100-1199/1118.Number%20of%20Days%20in%20a%20Month/README.md)

## Description

<p>Given a year <code>year</code> and a month <code>month</code>, return <em>the number of days of that month</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> year = 1992, month = 7
<strong>Output:</strong> 31
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> year = 2000, month = 2
<strong>Output:</strong> 29
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> year = 1900, month = 2
<strong>Output:</strong> 28
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1583 &lt;= year &lt;= 2100</code></li>
	<li><code>1 &lt;= month &lt;= 12</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfDays(self, year: int, month: int) -> int:
        leap = (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)
        days = [0, 31, 29 if leap else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return days[month]
```

### **Java**

```java
class Solution {
    public int numberOfDays(int year, int month) {
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] days = new int[] {0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfDays(int year, int month) {
        bool leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        vector<int> days = {0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month];
    }
};
```

### **Go**

```go
func numberOfDays(year int, month int) int {
	leap := (year%4 == 0 && year%100 != 0) || (year%400 == 0)
	x := 28
	if leap {
		x = 29
	}
	days := []int{0, 31, x, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	return days[month]
}
```

### **...**

```

```

<!-- tabs:end -->
