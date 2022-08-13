# [1185. 一周中的第几天](https://leetcode.cn/problems/day-of-the-week)

[English Version](/solution/1100-1199/1185.Day%20of%20the%20Week/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。</p>

<p>输入为三个整数：<code>day</code>、<code>month</code> 和&nbsp;<code>year</code>，分别表示日、月、年。</p>

<p>您返回的结果必须是这几个值中的一个&nbsp;<code>{&quot;Sunday&quot;, &quot;Monday&quot;, &quot;Tuesday&quot;, &quot;Wednesday&quot;, &quot;Thursday&quot;, &quot;Friday&quot;, &quot;Saturday&quot;}</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>day = 31, month = 8, year = 2019
<strong>输出：</strong>&quot;Saturday&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>day = 18, month = 7, year = 1999
<strong>输出：</strong>&quot;Sunday&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>day = 15, month = 8, year = 1993
<strong>输出：</strong>&quot;Sunday&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给出的日期一定是在&nbsp;<code>1971</code> 到&nbsp;<code>2100</code>&nbsp;年之间的有效日期。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

直接调库或者应用蔡勒公式。

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1185.Day%20of%20the%20Week/images/zeller.svg">

-   `w`: 星期（从 Sunday 开始）
-   `c`: 年份前两位
-   `y`: 年份后两位
-   `m`: 月（m 的取值范围是 3 至 14，即在蔡勒公式中，某年的 1、2 月要看作上一年的 13、14 月来计算，比如 2003 年 1 月 1 日要看作 2002 年的 13 月 1 日来计算）
-   `d`: 日
-   `[ ]`: 向下取整
-   `mod`: 取余

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dayOfTheWeek(self, day: int, month: int, year: int) -> str:
        return datetime.date(year, month, day).strftime('%A')
```

```python
class Solution:
    def dayOfTheWeek(self, d: int, m: int, y: int) -> str:
        if m < 3:
            m += 12
            y -= 1
        c = y // 100
        y = y % 100
        w = (c // 4 - 2 * c + y + y // 4 + 13 * (m + 1) // 5 + d - 1) % 7
        return ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"][w]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
import java.util.Calendar;

class Solution {
    private static final String[] WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static String dayOfTheWeek(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
}
```

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
        return new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}[(w + 7) % 7];
    }
}
```

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
