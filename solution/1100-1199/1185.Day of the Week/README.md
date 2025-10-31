---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1185.Day%20of%20the%20Week/README.md
rating: 1382
source: 第 153 场周赛 Q2
tags:
    - 数学
---

<!-- problem:start -->

# [1185. 一周中的第几天](https://leetcode.cn/problems/day-of-the-week)

[English Version](/solution/1100-1199/1185.Day%20of%20the%20Week/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。</p>

<p>输入为三个整数：<code>day</code>、<code>month</code> 和&nbsp;<code>year</code>，分别表示日、月、年。</p>

<p>您返回的结果必须是这几个值中的一个&nbsp;<code>{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}</code>。</p>

<p><strong>注意</strong>：1971 年 1 月 1 日是星期五。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>day = 31, month = 8, year = 2019
<strong>输出：</strong>"Saturday"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>day = 18, month = 7, year = 1999
<strong>输出：</strong>"Sunday"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>day = 15, month = 8, year = 1993
<strong>输出：</strong>"Sunday"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给出的日期一定是在&nbsp;<code>1971</code> 到&nbsp;<code>2100</code>&nbsp;年之间的有效日期。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：蔡勒公式

我们可以使用蔡勒公式来计算星期几，蔡勒公式如下：

$$
w = (\left \lfloor \frac{c}{4} \right \rfloor - 2c + y + \left \lfloor \frac{y}{4} \right \rfloor + \left \lfloor \frac{13(m+1)}{5} \right \rfloor + d - 1) \bmod 7
$$

其中：

-   `w`: 星期（从 Sunday 开始）
-   `c`: 年份前两位
-   `y`: 年份后两位
-   `m`: 月（m 的取值范围是 3 至 14，即在蔡勒公式中，某年的 1、2 月要看作上一年的 13、14 月来计算，比如 2003 年 1 月 1 日要看作 2002 年的 13 月 1 日来计算）
-   `d`: 日
-   `⌊⌋`: 向下取整
-   `mod`: 取余

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dayOfTheWeek(self, day: int, month: int, year: int) -> str:
        return datetime.date(year, month, day).strftime('%A')
```

#### Java

```java
import java.util.Calendar;

class Solution {
    private static final String[] WEEK
        = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static String dayOfTheWeek(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string dayOfTheWeek(int d, int m, int y) {
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        int c = y / 100;
        y %= 100;
        int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
        vector<string> weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return weeks[(w + 7) % 7];
    }
};
```

#### Go

```go
func dayOfTheWeek(d int, m int, y int) string {
	if m < 3 {
		m += 12
		y -= 1
	}
	c := y / 100
	y %= 100
	w := (c/4 - 2*c + y + y/4 + 13*(m+1)/5 + d - 1) % 7
	weeks := []string{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}
	return weeks[(w+7)%7]
}
```

#### TypeScript

```ts
function dayOfTheWeek(d: number, m: number, y: number): string {
    if (m < 3) {
        m += 12;
        y -= 1;
    }
    const c: number = (y / 100) | 0;
    y %= 100;
    const w = (((c / 4) | 0) - 2 * c + y + ((y / 4) | 0) + (((13 * (m + 1)) / 5) | 0) + d - 1) % 7;
    const weeks: string[] = [
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
    ];
    return weeks[(w + 7) % 7];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dayOfTheWeek(self, d: int, m: int, y: int) -> str:
        if m < 3:
            m += 12
            y -= 1
        c = y // 100
        y = y % 100
        w = (c // 4 - 2 * c + y + y // 4 + 13 * (m + 1) // 5 + d - 1) % 7
        return [
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
        ][w]
```

#### Java

```java
class Solution {
    public String dayOfTheWeek(int d, int m, int y) {
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        int c = y / 100;
        y %= 100;
        int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
        return new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday"}[(w + 7) % 7];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
