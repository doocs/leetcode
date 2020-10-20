# [901. Online Stock Span](https://leetcode.com/problems/online-stock-span)

[中文文档](/solution/0900-0999/0901.Online%20Stock%20Span/README.md)

## Description

<p>Write a class <code>StockSpanner</code> which collects daily price quotes for some stock, and returns the <em>span</em>&nbsp;of that stock&#39;s price for the current day.</p>

<p>The span of the stock&#39;s price today&nbsp;is defined as the maximum number of consecutive days (starting from today and going backwards)&nbsp;for which the price of the stock was less than or equal to today&#39;s price.</p>

<p>For example, if the price of a stock over the next 7 days were <code>[100, 80, 60, 70, 60, 75, 85]</code>, then the stock spans would be <code>[1, 1, 1, 2, 1, 4, 6]</code>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;StockSpanner&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;]</span>, <span id="example-input-1-2">[[],[100],[80],[60],[70],[60],[75],[85]]</span>

<strong>Output: </strong><span id="example-output-1">[null,1,1,1,2,1,4,6]</span>

<strong>Explanation: </strong>

First, S = StockSpanner() is initialized.  Then:

S.next(100) is called and returns 1,

S.next(80) is called and returns 1,

S.next(60) is called and returns 1,

S.next(70) is called and returns 2,

S.next(60) is called and returns 1,

S.next(75) is called and returns 4,

S.next(85) is called and returns 6.



Note that (for example) S.next(75) returned 4, because the last 4 prices

(including today&#39;s price of 75) were less than or equal to today&#39;s price.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>

    <li>Calls to <code>StockSpanner.next(int price)</code> will have <code>1 &lt;= price &lt;= 10^5</code>.</li>

    <li>There will be at most <code>10000</code> calls to <code>StockSpanner.next</code>&nbsp;per test case.</li>

    <li>There will be at most <code>150000</code> calls to <code>StockSpanner.next</code> across all test cases.</li>

    <li>The total&nbsp;time limit for this problem has been reduced by 75% for&nbsp;C++, and 50% for all other languages.</li>

</ol>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
