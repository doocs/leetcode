# [799. Champagne Tower](https://leetcode.com/problems/champagne-tower)

[中文文档](/solution/0700-0799/0799.Champagne%20Tower/README.md)

## Description

<p>We stack glasses in a pyramid, where the <strong>first</strong> row has <code>1</code> glass, the <strong>second</strong> row has <code>2</code> glasses, and so on until the 100<sup>th</sup> row.&nbsp; Each glass holds one cup&nbsp;of champagne.</p>

<p>Then, some champagne is poured into the first glass at the top.&nbsp; When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.&nbsp; When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.&nbsp; (A glass at the bottom row has its excess champagne fall on the floor.)</p>

<p>For example, after one cup of champagne is poured, the top most glass is full.&nbsp; After two cups of champagne are poured, the two glasses on the second row are half full.&nbsp; After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.&nbsp; After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0799.Champagne%20Tower/images/tower.png" style="height: 241px; width: 350px;" /></p>

<p>Now after pouring some non-negative integer cups of champagne, return how full the <code>j<sup>th</sup></code> glass in the <code>i<sup>th</sup></code> row is (both <code>i</code> and <code>j</code> are 0-indexed.)</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> poured = 1, query_row = 1, query_glass = 1

<strong>Output:</strong> 0.00000

<strong>Explanation:</strong> We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> poured = 2, query_row = 1, query_glass = 1

<strong>Output:</strong> 0.50000

<strong>Explanation:</strong> We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> poured = 100000009, query_row = 33, query_glass = 17

<strong>Output:</strong> 1.00000

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>0 &lt;=&nbsp;poured &lt;= 10<sup>9</sup></code></li>

    <li><code>0 &lt;= query_glass &lt;= query_row&nbsp;&lt; 100</code></li>

</ul>

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
