# [732. 我的日程安排表 III](https://leetcode-cn.com/problems/my-calendar-iii)

## 题目描述
<!-- 这里写题目描述 -->
<p>实现一个 <code>MyCalendar</code> 类来存放你的日程安排，你可以一直添加新的日程安排。</p>

<p><code>MyCalendar</code> 有一个 <code>book(int start, int end)</code>方法。它意味着在start到end时间内增加一个日程安排，注意，这里的时间是半开区间，即 <code>[start, end)</code>, 实数&nbsp;<code>x</code> 的范围为， &nbsp;<code>start &lt;= x &lt; end</code>。</p>

<p>当 <strong>K</strong> 个日程安排有一些时间上的交叉时（例如K个日程安排都在同一时间内），就会产生 <strong>K</strong> 次预订。</p>

<p>每次调用 <code>MyCalendar.book</code>方法时，返回一个整数 <code>K</code> ，表示最大的 <code>K</code> 次预订。</p>

<p>请按照以下步骤调用<code>MyCalendar</code> 类: <code>MyCalendar cal = new MyCalendar();</code> <code>MyCalendar.book(start, end)</code></p>

<p><strong>示例 1:</strong></p>

<pre>
MyCalendarThree();
MyCalendarThree.book(10, 20); // returns 1
MyCalendarThree.book(50, 60); // returns 1
MyCalendarThree.book(10, 40); // returns 2
MyCalendarThree.book(5, 15); // returns 3
MyCalendarThree.book(5, 10); // returns 3
MyCalendarThree.book(25, 55); // returns 3
<strong>解释:</strong> 
前两个日程安排可以预订并且不相交，所以最大的K次预订是1。
第三个日程安排[10,40]与第一个日程安排相交，最高的K次预订为2。
其余的日程安排的最高K次预订仅为3。
请注意，最后一次日程安排可能会导致局部最高K次预订为2，但答案仍然是3，原因是从开始到最后，时间[10,20]，[10,40]和[5,15]仍然会导致3次预订。
</pre>

<p><strong>说明:</strong></p>

<ul>
	<li>每个测试用例，调用&nbsp;<code>MyCalendar.book</code>&nbsp;函数最多不超过&nbsp;<code>400</code>次。</li>
	<li>调用函数&nbsp;<code>MyCalendar.book(start, end)</code>时，&nbsp;<code>start</code> 和&nbsp;<code>end</code> 的取值范围为&nbsp;<code>[0, 10^9]</code>。</li>
</ul>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
