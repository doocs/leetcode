# [731. 我的日程安排表 II](https://leetcode-cn.com/problems/my-calendar-ii)

[English Version](/solution/0700-0799/0731.My%20Calendar%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现一个 <code>MyCalendar</code> 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。</p>

<p><code>MyCalendar</code> 有一个 <code>book(int start, int end)</code>方法。它意味着在 <code>start</code> 到 <code>end</code> 时间内增加一个日程安排，注意，这里的时间是半开区间，即 <code>[start, end)</code>, 实数&nbsp;<code>x</code> 的范围为， &nbsp;<code>start &lt;= x &lt; end</code>。</p>

<p>当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。</p>

<p>每次调用 <code>MyCalendar.book</code>方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 <code>true</code>。否则，返回 <code>false</code> 并且不要将该日程安排添加到日历中。</p>

<p>请按照以下步骤调用<code>MyCalendar</code> 类: <code>MyCalendar cal = new MyCalendar();</code> <code>MyCalendar.book(start, end)</code></p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
<strong>解释：</strong> 
前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每个测试用例，调用&nbsp;<code>MyCalendar.book</code>&nbsp;函数最多不超过&nbsp;<code>1000</code>次。</li>
	<li>调用函数&nbsp;<code>MyCalendar.book(start, end)</code>时，&nbsp;<code>start</code> 和&nbsp;<code>end</code> 的取值范围为&nbsp;<code>[0, 10^9]</code>。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MyCalendarTwo {
    List<int[]> calendar;
    List<int[]> duplicationList;

    MyCalendarTwo() {
        calendar = new ArrayList<>();
        duplicationList = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] item : duplicationList) {
            if (item[0] < end && item[1] > start) {
                return false;
            }
        }
        for (int[] item : calendar) {
            if (item[0] < end && item[1] > start) {
                duplicationList.add(new int[]{Math.max(start, item[0]), Math.min(end, item[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
