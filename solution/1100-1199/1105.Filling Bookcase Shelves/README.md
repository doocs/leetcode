# [1105. 填充书架](https://leetcode-cn.com/problems/filling-bookcase-shelves)

[English Version](/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。</p>

<p>你把要摆放的书 <code>books</code>&nbsp;都整理好，叠成一摞：从上往下，第 <code>i</code>&nbsp;本书的厚度为 <code>books[i][0]</code>，高度为 <code>books[i][1]</code>。</p>

<p><strong>按顺序</strong>&nbsp;将这些书摆放到总宽度为&nbsp;<code>shelf_width</code> 的书架上。</p>

<p>先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 <code>shelf_width</code>），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。</p>

<p>需要注意的是，在上述过程的每个步骤中，<strong>摆放书的顺序与你整理好的顺序相同</strong>。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。</p>

<p>每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。</p>

<p>以这种方式布置书架，返回书架整体可能的最小高度。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/images/shelves.png" style="width: 150px;"></p>

<pre><strong>输入：</strong>books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
<strong>输出：</strong>6
<strong>解释：</strong>
3 层书架的高度和为 1 + 3 + 2 = 6 。
第 2 本书不必放在第一层书架上。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= books.length &lt;= 1000</code></li>
	<li><code>1 &lt;= books[i][0] &lt;= shelf_width &lt;= 1000</code></li>
	<li><code>1 &lt;= books[i][1] &lt;= 1000</code></li>
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

### **...**

```

```

<!-- tabs:end -->
