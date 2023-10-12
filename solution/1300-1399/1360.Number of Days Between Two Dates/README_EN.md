# [1360. Number of Days Between Two Dates](https://leetcode.com/problems/number-of-days-between-two-dates)

[中文文档](/solution/1300-1399/1360.Number%20of%20Days%20Between%20Two%20Dates/README.md)

## Description

<p>Write a program to count the number of days between two dates.</p>

<p>The two dates are given as strings, their format is <code>YYYY-MM-DD</code>&nbsp;as shown in the examples.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> date1 = "2019-06-29", date2 = "2019-06-30"
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> date1 = "2020-01-15", date2 = "2019-12-31"
<strong>Output:</strong> 15
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The given dates are valid&nbsp;dates between the years <code>1971</code> and <code>2100</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def daysBetweenDates(self, date1: str, date2: str) -> int:
        def isLeapYear(year: int) -> bool:
            return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)

        def daysInMonth(year: int, month: int) -> int:
            days = [
                31,
                28 + int(isLeapYear(year)),
                31,
                30,
                31,
                30,
                31,
                31,
                30,
                31,
                30,
                31,
            ]
            return days[month - 1]

        def calcDays(date: str) -> int:
            year, month, day = map(int, date.split("-"))
            days = 0
            for y in range(1971, year):
                days += 365 + int(isLeapYear(y))
            for m in range(1, month):
                days += daysInMonth(year, m)
            days += day
            return days

        return abs(calcDays(date1) - calcDays(date2))
```

### **Java**

```java
class Solution {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(calcDays(date1) - calcDays(date2));
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private int daysInMonth(int year, int month) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        days[1] += isLeapYear(year) ? 1 : 0;
        return days[month - 1];
    }

    private int calcDays(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        int days = 0;
        for (int y = 1971; y < year; ++y) {
            days += isLeapYear(y) ? 366 : 365;
        }
        for (int m = 1; m < month; ++m) {
            days += daysInMonth(year, m);
        }
        days += day;
        return days;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int daysBetweenDates(string date1, string date2) {
        return abs(calcDays(date1) - calcDays(date2));
    }

    bool isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    int daysInMonth(int year, int month) {
        int days[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        days[1] += isLeapYear(year);
        return days[month - 1];
    }

    int calcDays(string date) {
        int year = stoi(date.substr(0, 4));
        int month = stoi(date.substr(5, 2));
        int day = stoi(date.substr(8, 2));
        int days = 0;
        for (int y = 1971; y < year; ++y) {
            days += 365 + isLeapYear(y);
        }
        for (int m = 1; m < month; ++m) {
            days += daysInMonth(year, m);
        }
        days += day;
        return days;
    }
};
```

### **Go**

```go
func daysBetweenDates(date1 string, date2 string) int {
	return abs(calcDays(date1) - calcDays(date2))
}

func isLeapYear(year int) bool {
	return year%4 == 0 && (year%100 != 0 || year%400 == 0)
}

func daysInMonth(year, month int) int {
	days := [12]int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if isLeapYear(year) {
		days[1] = 29
	}
	return days[month-1]
}

func calcDays(date string) int {
	year, _ := strconv.Atoi(date[:4])
	month, _ := strconv.Atoi(date[5:7])
	day, _ := strconv.Atoi(date[8:])
	days := 0
	for y := 1971; y < year; y++ {
		days += 365
		if isLeapYear(y) {
			days++
		}
	}
	for m := 1; m < month; m++ {
		days += daysInMonth(year, m)
	}
	days += day
	return days
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function daysBetweenDates(date1: string, date2: string): number {
    return Math.abs(calcDays(date1) - calcDays(date2));
}

function isLeapYear(year: number): boolean {
    return year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0);
}

function daysOfMonth(year: number, month: number): number {
    const days = [31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days[month - 1];
}

function calcDays(date: string): number {
    let days = 0;
    const [year, month, day] = date.split('-').map(Number);
    for (let y = 1971; y < year; ++y) {
        days += isLeapYear(y) ? 366 : 365;
    }
    for (let m = 1; m < month; ++m) {
        days += daysOfMonth(year, m);
    }
    days += day - 1;
    return days;
}
```

### **...**

```

```

<!-- tabs:end -->
