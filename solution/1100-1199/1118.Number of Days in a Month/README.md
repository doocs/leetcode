# [1118. 一月有多少天](https://leetcode.cn/problems/number-of-days-in-a-month)

[English Version](/solution/1100-1199/1118.Number%20of%20Days%20in%20a%20Month/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>指定年份&nbsp;<code>year</code> 和月份&nbsp;<code>month</code>，返回 <em>该月天数&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>year = 1992, month = 7
<strong>输出：</strong>31
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>year = 2000, month = 2
<strong>输出：</strong>29
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>year = 1900, month = 2
<strong>输出：</strong>28
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1583 &lt;= year &lt;= 2100</code></li>
	<li><code>1 &lt;= month &lt;= 12</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfDays(self, year: int, month: int) -> int:
        leap = (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)
        days = [0, 31, 29 if leap else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return days[month]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfDays(int year, int month) {
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] days = new int[]{0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
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
