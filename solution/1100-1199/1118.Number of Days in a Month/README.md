---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1118.Number%20of%20Days%20in%20a%20Month/README.md
rating: 1227
source: 第 4 场双周赛 Q1
tags:
    - 数学
---

# [1118. 一月有多少天 🔒](https://leetcode.cn/problems/number-of-days-in-a-month)

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

### 方法一：判断闰年

我们可以先判断给定的年份是否为闰年，如果年份能被 $4$ 整除但不能被 $100$ 整除，或者能被 $400$ 整除，那么这一年就是闰年。

闰年的二月有 $29$ 天，平年的二月有 $28$ 天。

我们可以用一个数组 $days$ 存储当前年份每个月的天数，其中 $days[0]=0$，$days[i]$ 表示当前年份第 $i$ 个月的天数。那么答案就是 $days[month]$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfDays(self, year: int, month: int) -> int:
        leap = (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)
        days = [0, 31, 29 if leap else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return days[month]
```

```java
class Solution {
    public int numberOfDays(int year, int month) {
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] days = new int[] {0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month];
    }
}
```

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

```ts
function numberOfDays(year: number, month: number): number {
    const leap = (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0;
    const days = [0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days[month];
}
```

<!-- tabs:end -->

<!-- end -->
