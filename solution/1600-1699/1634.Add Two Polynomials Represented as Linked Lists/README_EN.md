# [1634. Add Two Polynomials Represented as Linked Lists](https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists)

[中文文档](/solution/1600-1699/1634.Add%20Two%20Polynomials%20Represented%20as%20Linked%20Lists/README.md)

## Description

<p>A polynomial linked list is a special type of linked list where every node represents a term in a polynomial expression.</p>



<p>Each node has three attributes:</p>



<ul>
	<li><code>coefficient</code>: an integer representing the number multiplier of the term. The coefficient of the term <code><strong>9</strong>x<sup>4</sup></code> is <code>9</code>.</li>
	<li><code>power</code>: an integer representing the exponent. The power of the term <code>9x<strong><sup>4</sup></strong></code> is <code>4</code>.</li>
	<li><code>next</code>: a pointer to the next node in the list, or <code>null</code> if it is the last node of the list.</li>
</ul>



<p>For example, the polynomial <code>5x<sup>3</sup> + 4x - 7</code> is represented by the polynomial linked list illustrated below:</p>



<p><img alt="" src="/solution/1600-1699/1634.Add Two Polynomials Represented as Linked Lists/images/polynomial2.png" style="width: 500px; height: 91px;" /></p>



<p>The polynomial linked list must be in its standard form: the polynomial must be in <strong>strictly</strong> descending order by its <code>power</code> value. Also, terms with a <code>coefficient</code> of <code>0</code> are omitted.</p>



<p>Given two polynomial linked list heads, <code>poly1</code> and <code>poly2</code>, add the polynomials together and return <em>the head of the sum of the polynomials</em>.</p>



<p><strong><code>PolyNode</code> format:</strong></p>



<p>The input/output format is as a list of <code>n</code> nodes, where each node is represented as its <code>[coefficient, power]</code>. For example, the polynomial <code>5x<sup>3</sup> + 4x - 7</code> would be represented as: <code>[[5,3],[4,1],[-7,0]]</code>.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<p><img alt="" src="/solution/1600-1699/1634.Add Two Polynomials Represented as Linked Lists/images/ex1.png" style="width: 600px; height: 322px;" /></p>



<pre>

<strong>Input:</strong> poly1 = [[1,1]], poly2 = [[1,0]]

<strong>Output:</strong> [[1,1],[1,0]]

<strong>Explanation:</strong> poly1 = x. poly2 = 1. The sum is x + 1.

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input:</strong> poly1 = [[2,2],[4,1],[3,0]], poly2 = [[3,2],[-4,1],[-1,0]]

<strong>Output:</strong> [[5,2],[2,0]]

<strong>Explanation:</strong> poly1 = 2x<sup>2</sup> + 4x + 3. poly2 = 3x<sup>2</sup> - 4x - 1. The sum is 5x<sup>2</sup> + 2. Notice that we omit the &quot;0x&quot; term.

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> poly1 = [[1,2]], poly2 = [[-1,2]]

<strong>Output:</strong> []

<strong>Explanation:</strong> The sum is 0. We return an empty list.

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= PolyNode.coefficient &lt;= 10<sup>9</sup></code></li>
	<li><code>PolyNode.coefficient != 0</code></li>
	<li><code>0&nbsp;&lt;= PolyNode.power &lt;= 10<sup>9</sup></code></li>
	<li><code>PolyNode.power &gt; PolyNode.next.power</code></li>
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
