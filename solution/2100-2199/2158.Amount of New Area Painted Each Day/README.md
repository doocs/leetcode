# [2158. Amount of New Area Painted Each Day](https://leetcode-cn.com/problems/amount-of-new-area-painted-each-day)

[English Version](/solution/2100-2199/2158.Amount%20of%20New%20Area%20Painted%20Each%20Day/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>There is a long and thin painting that can be represented by a number line. You are given a <strong>0-indexed</strong> 2D integer array <code>paint</code> of length <code>n</code>, where <code>paint[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>. This means that on the <code>i<sup>th</sup></code> day you need to paint the area <strong>between</strong> <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code>.</p>

<p>Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most <strong>once</strong>.</p>

<p>Return <em>an integer array </em><code>worklog</code><em> of length </em><code>n</code><em>, where </em><code>worklog[i]</code><em> is the amount of <strong>new</strong> area that you painted on the </em><code>i<sup>th</sup></code><em> day.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2158.Amount%20of%20New%20Area%20Painted%20Each%20Day/images/screenshot-2022-02-01-at-17-16-16-diagram-drawio-diagrams-net.png" style="height: 300px; width: 620px;" />
<pre>
<strong>Input:</strong> paint = [[1,4],[4,7],[5,8]]
<strong>Output:</strong> [3,3,1]
<strong>Explanation:</strong>
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 4 and 7.
The amount of new area painted on day 1 is 7 - 4 = 3.
On day 2, paint everything between 7 and 8.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 8 - 7 = 1. 
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2158.Amount%20of%20New%20Area%20Painted%20Each%20Day/images/screenshot-2022-02-01-at-17-17-45-diagram-drawio-diagrams-net.png" style="width: 604px; height: 300px;" />
<pre>
<strong>Input:</strong> paint = [[1,4],[5,8],[4,7]]
<strong>Output:</strong> [3,3,1]
<strong>Explanation:</strong>
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 5 and 8.
The amount of new area painted on day 1 is 8 - 5 = 3.
On day 2, paint everything between 4 and 5.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 5 - 4 = 1. 
</pre>

<p><strong>Example 3:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2158.Amount%20of%20New%20Area%20Painted%20Each%20Day/images/screenshot-2022-02-01-at-17-19-49-diagram-drawio-diagrams-net.png" style="width: 423px; height: 275px;" />
<pre>
<strong>Input:</strong> paint = [[1,5],[2,4]]
<strong>Output:</strong> [4,0]
<strong>Explanation:</strong>
On day 0, paint everything between 1 and 5.
The amount of new area painted on day 0 is 5 - 1 = 4.
On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
The amount of new area painted on day 1 is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paint.length &lt;= 10<sup>5</sup></code></li>
	<li><code>paint[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
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

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
