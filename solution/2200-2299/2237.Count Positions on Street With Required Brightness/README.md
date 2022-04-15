# [2237. Count Positions on Street With Required Brightness](https://leetcode-cn.com/problems/count-positions-on-street-with-required-brightness)

[English Version](/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given an integer <code>n</code>. A perfectly straight street is represented by a number line ranging from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>lights</code> representing the street lamp(s) on the street. Each <code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code> indicates that there is a street lamp at position <code>position<sub>i</sub></code> that lights up the area from <code>[max(0, position<sub>i</sub> - range<sub>i</sub>), min(n - 1, position<sub>i</sub> + range<sub>i</sub>)]</code> (<strong>inclusive</strong>).</p>

<p>The <strong>brightness</strong> of a position <code>p</code> is defined as the number of street lamps that light up the position <code>p</code>. You are given a <strong>0-indexed</strong> integer array <code>requirement</code> of size <code>n</code> where <code>requirement[i]</code> is the minimum <strong>brightness</strong> of the <code>i<sup>th</sup></code> position on the street.</p>

<p>Return <em>the number of positions </em><code>i</code><em> on the street between </em><code>0</code><em> and </em><code>n - 1</code><em> that have a <strong>brightness</strong> </em><em>of <strong>at least</strong> </em><code>requirement[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/images/screenshot-2022-04-11-at-22-24-43-diagramdrawio-diagramsnet.png" style="height: 150px; width: 579px;" />
<pre>
<strong>Input:</strong> n = 5, lights = [[0,1],[2,1],[3,2]], requirement = [0,2,1,4,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 1] (inclusive).
- The second street lamp lights up the area from [max(0, 2 - 1), min(n - 1, 2 + 1)] = [1, 3] (inclusive).
- The third street lamp lights up the area from [max(0, 3 - 2), min(n - 1, 3 + 2)] = [1, 4] (inclusive).

-   Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is greater than requirement[0].
-   Position 1 is covered by the first, second, and third street lamps. It is covered by 3 street lamps which is greater than requirement[1].
-   Position 2 is covered by the second and third street lamps. It is covered by 2 street lamps which is greater than requirement[2].
-   Position 3 is covered by the second and third street lamps. It is covered by 2 street lamps which is less than requirement[3].
-   Position 4 is covered by the third street lamp. It is covered by 1 street lamp which is equal to requirement[4].

Positions 0, 1, 2, and 4 meet the requirement so we return 4.

</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, lights = [[0,1]], requirement = [2]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 0] (inclusive).
- Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is less than requirement[0].
- We return 0 because no position meets their brightness requirement.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= position<sub>i</sub> &lt; n</code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>requirement.length == n</code></li>
	<li><code>0 &lt;= requirement[i] &lt;= 10<sup>5</sup></code></li>
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
