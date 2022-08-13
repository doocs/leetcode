# [1507. 转变日期格式](https://leetcode.cn/problems/reformat-date)

[English Version](/solution/1500-1599/1507.Reformat%20Date/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>date</code>&nbsp;，它的格式为&nbsp;<code>Day Month Year</code>&nbsp;，其中：</p>

<ul>
	<li><code>Day</code>&nbsp;是集合&nbsp;<code>{&quot;1st&quot;, &quot;2nd&quot;, &quot;3rd&quot;, &quot;4th&quot;, ..., &quot;30th&quot;, &quot;31st&quot;}</code>&nbsp;中的一个元素。</li>
	<li><code>Month</code>&nbsp;是集合&nbsp;<code>{&quot;Jan&quot;, &quot;Feb&quot;, &quot;Mar&quot;, &quot;Apr&quot;, &quot;May&quot;, &quot;Jun&quot;, &quot;Jul&quot;, &quot;Aug&quot;, &quot;Sep&quot;, &quot;Oct&quot;, &quot;Nov&quot;, &quot;Dec&quot;}</code>&nbsp;中的一个元素。</li>
	<li><code>Year</code>&nbsp;的范围在 ​<code>[1900, 2100]</code>&nbsp;之间。</li>
</ul>

<p>请你将字符串转变为&nbsp;<code>YYYY-MM-DD</code>&nbsp;的格式，其中：</p>

<ul>
	<li><code>YYYY</code>&nbsp;表示 4 位的年份。</li>
	<li><code>MM</code>&nbsp;表示 2 位的月份。</li>
	<li><code>DD</code>&nbsp;表示 2 位的天数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>date = &quot;20th Oct 2052&quot;
<strong>输出：</strong>&quot;2052-10-20&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>date = &quot;6th Jun 1933&quot;
<strong>输出：</strong>&quot;1933-06-06&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>date = &quot;26th May 1960&quot;
<strong>输出：</strong>&quot;1960-05-26&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定日期保证是合法的，所以不需要处理异常输入。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

切分 `date` 字符串，获取对应的 `year`, `month`, `day`，然后拼接起来即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reformatDate(self, date: str) -> str:
        months = [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
        ]
        mapper = {v: str(k + 1) for k, v in enumerate(months)}
        split = date.split(' ')
        year = split[2]
        month = mapper.get(split[1])
        day = split[0][: len(split[0]) - 2]
        return year + '-' + month.zfill(2) + '-' + day.zfill(2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reformatDate(String date) {
        Map<String, Integer> mapper = new HashMap<>();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; ++i) {
            mapper.put(months[i], i + 1);
        }
        String[] split = date.split(" ");
        int year = Integer.parseInt(split[2]);
        int month = mapper.get(split[1]);
        int day = Integer.parseInt(split[0].substring(0, split[0].length() -2));
        return String.format("%d-%02d-%02d", year, month, day);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
