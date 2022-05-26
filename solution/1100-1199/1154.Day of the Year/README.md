# [1154. 一年中的第几天](https://leetcode.cn/problems/day-of-the-year)

[English Version](/solution/1100-1199/1154.Day%20of%20the%20Year/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>date</code> ，按 <code>YYYY-MM-DD</code> 格式表示一个 <a href="https://baike.baidu.com/item/公元/17855" target="_blank">现行公元纪年法</a> 日期。返回该日期是当年的第几天。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>date = "2019-01-09"
<strong>输出：</strong>9
<strong>解释：</strong>给定日期是2019年的第九天。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>date = "2019-02-10"
<strong>输出：</strong>41
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == '-'</code>，其他的&nbsp;<code>date[i]</code>&nbsp;都是数字</li>
	<li><code>date</code> 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

闰年 2 月有 29 天，平年 2 月有 28 天。

闰年的判断规则：`year % 100 == 0 || (year % 4 == 0 && year % 100 != 0)`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dayOfYear(self, date: str) -> int:
        year, month, day = (int(e) for e in date.split('-'))
        d = 29 if year % 400 == 0 or (year % 4 == 0 and year % 100 != 0) else 28
        days = [31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: month - 1]) + day
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
