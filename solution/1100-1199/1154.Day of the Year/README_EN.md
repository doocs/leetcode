---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1154.Day%20of%20the%20Year/README_EN.md
rating: 1199
source: Weekly Contest 149 Q1
tags:
    - Math
    - String
---

<!-- problem:start -->

# [1154. Day of the Year](https://leetcode.com/problems/day-of-the-year)

[中文文档](/solution/1100-1199/1154.Day%20of%20the%20Year/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>date</code> representing a <a href="https://en.wikipedia.org/wiki/Gregorian_calendar" target="_blank">Gregorian calendar</a> date formatted as <code>YYYY-MM-DD</code>, return <em>the day number of the year</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2019-01-09&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> Given date is the 9th day of the year in 2019.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2019-02-10&quot;
<strong>Output:</strong> 41
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == &#39;-&#39;</code>, and all other <code>date[i]</code>&#39;s are digits</li>
	<li><code>date</code> represents a calendar date between Jan 1<sup>st</sup>, 1900 and Dec 31<sup>st</sup>, 2019.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Calculation

According to the problem, the given date is in the Gregorian calendar, so we can directly calculate which day of the year it is.

First, calculate the year, month, and day from the given date, denoted as $y$, $m$, $d$.

Then, calculate the number of days in February of that year according to the leap year rules of the Gregorian calendar. There are $29$ days in February of a leap year and $28$ days in a non-leap year.

> The leap year calculation rule is: the year can be divided by $400$, or the year can be divided by $4$ but not by $100$.

Finally, calculate which day of the year it is according to the given date, that is, add up the number of days in each previous month, and then add the number of days in the current month.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dayOfYear(self, date: str) -> int:
        y, m, d = (int(s) for s in date.split('-'))
        v = 29 if y % 400 == 0 or (y % 4 == 0 and y % 100) else 28
        days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: m - 1]) + d
```

#### Java

```java
class Solution {
    public int dayOfYear(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(5, 7));
        int d = Integer.parseInt(date.substring(8));
        int v = y % 400 == 0 || (y % 4 == 0 && y % 100 != 0) ? 29 : 28;
        int[] days = {31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = d;
        for (int i = 0; i < m - 1; ++i) {
            ans += days[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dayOfYear(string date) {
        int y, m, d;
        sscanf(date.c_str(), "%d-%d-%d", &y, &m, &d);
        int v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
        int days[] = {31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = d;
        for (int i = 0; i < m - 1; ++i) {
            ans += days[i];
        }
        return ans;
    }
};
```

#### Go

```go
func dayOfYear(date string) (ans int) {
	var y, m, d int
	fmt.Sscanf(date, "%d-%d-%d", &y, &m, &d)
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if y%400 == 0 || (y%4 == 0 && y%100 != 0) {
		days[1] = 29
	}
	ans += d
	for _, v := range days[:m-1] {
		ans += v
	}
	return
}
```

#### TypeScript

```ts
function dayOfYear(date: string): number {
    const y = +date.slice(0, 4);
    const m = +date.slice(5, 7);
    const d = +date.slice(8);
    const v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
    const days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days.slice(0, m - 1).reduce((a, b) => a + b, d);
}
```

#### JavaScript

```js
/**
 * @param {string} date
 * @return {number}
 */
var dayOfYear = function (date) {
    const y = +date.slice(0, 4);
    const m = +date.slice(5, 7);
    const d = +date.slice(8);
    const v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
    const days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days.slice(0, m - 1).reduce((a, b) => a + b, d);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
