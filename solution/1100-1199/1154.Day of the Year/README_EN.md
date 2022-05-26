# [1154. Day of the Year](https://leetcode.com/problems/day-of-the-year)

[中文文档](/solution/1100-1199/1154.Day%20of%20the%20Year/README.md)

## Description

<p>Given a string <code>date</code> representing a <a href="https://en.wikipedia.org/wiki/Gregorian_calendar" target="_blank">Gregorian calendar</a> date formatted as <code>YYYY-MM-DD</code>, return <em>the day number of the year</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2019-01-09&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> Given date is the 9th day of the year in 2019.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2019-02-10&quot;
<strong>Output:</strong> 41
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == &#39;-&#39;</code>, and all other <code>date[i]</code>&#39;s are digits</li>
	<li><code>date</code> represents a calendar date between Jan 1<sup>st</sup>, 1900 and Dec 31<sup>th</sup>, 2019.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dayOfYear(self, date: str) -> int:
        year, month, day = (int(e) for e in date.split('-'))
        d = 29 if year % 400 == 0 or (year % 4 == 0 and year % 100 != 0) else 28
        days = [31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: month - 1]) + day
```

### **Java**

```java
class Solution {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        int d = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? 29 : 28;
        int[] days = new int[]{31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = day;
        for (int i = 0; i < month - 1; ++i) {
            ans += days[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int dayOfYear(string date) {
        int year = stoi(date.substr(0, 4));
        int month = stoi(date.substr(5, 7));
        int day = stoi(date.substr(8));
        int d = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? 29 : 28;
        int days[] = {31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = day;
        for (int i = 0; i < month - 1; ++i) ans += days[i];
        return ans;
    }
};
```

### **Go**

```go
func dayOfYear(date string) int {
	year, _ := strconv.Atoi(date[:4])
	month, _ := strconv.Atoi(date[5:7])
	day, _ := strconv.Atoi(date[8:])
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if year%400 == 0 || (year%4 == 0 && year%100 != 0) {
		days[1]++
	}
	ans := day
	for i := 0; i < month-1; i++ {
		ans += days[i]
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {string} date
 * @return {number}
 */
var dayOfYear = function (date) {
    const year = +date.slice(0, 4);
    const month = +date.slice(5, 7);
    const day = +date.slice(8);
    const d =
        year % 400 === 0 || (year % 4 === 0 && year % 100 !== 0) ? 29 : 28;
    const days = [31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let ans = day;
    for (let i = 0; i < month - 1; ++i) {
        ans += days[i];
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
