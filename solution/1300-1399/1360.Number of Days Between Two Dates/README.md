# [1360. 日期之间隔几天](https://leetcode.cn/problems/number-of-days-between-two-dates)

[English Version](/solution/1300-1399/1360.Number%20of%20Days%20Between%20Two%20Dates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个程序来计算两个日期之间隔了多少天。</p>

<p>日期以字符串形式给出，格式为&nbsp;<code>YYYY-MM-DD</code>，如示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>date1 = &quot;2019-06-29&quot;, date2 = &quot;2019-06-30&quot;
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>date1 = &quot;2020-01-15&quot;, date2 = &quot;2019-12-31&quot;
<strong>输出：</strong>15
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定的日期是&nbsp;<code>1971</code>&nbsp;年到 <code>2100</code>&nbsp;年之间的有效日期。</li>
</ul>

## 解法

### 方法一：数学

我们先定义一个函数 `isLeapYear(year)` 来判断给定的年份 `year` 是否是闰年，如果是闰年则返回 `true`，否则返回 `false`。

接下来，我们再定义一个函数 `daysInMonth(year, month)` 来计算给定的年份 `year` 和月份 `month` 一共有多少天，我们可以使用一个数组 `days` 来存储每个月份的天数，其中 `days[1]` 表示二月份的天数，如果是闰年则为 $29$ 天，否则为 $28$ 天。

然后，我们再定义一个函数 `calcDays(date)` 来计算给定的日期 `date` 距离 `1971-01-01` 有多少天，我们可以使用 `date.split("-")` 来将日期 `date` 按照 `-` 分割成年份 `year`、月份 `month` 和日期 `day`，然后我们可以使用一个循环来计算从 `1971` 年到 `year` 年一共有多少天，然后再计算从 `1` 月到 `month` 月一共有多少天，最后再加上 `day` 天即可。

最后，我们只需要返回 `calcDays(date1) - calcDays(date2)` 的绝对值即可。

时间复杂度 $O(y + m)$，其中 $y$ 表示给定的日期距离 `1971-01-01` 的年数，而 $m$ 表示给定的日期的月数。空间复杂度 $O(1)$。

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
