# [1634. 求两个多项式链表的和](https://leetcode-cn.com/problems/add-two-polynomials-represented-as-linked-lists)

[English Version](/solution/1600-1699/1634.Add%20Two%20Polynomials%20Represented%20as%20Linked%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>多项式链表是一种特殊形式的链表，每个节点表示多项式的一项。</p>

<p>每个节点有三个属性：</p>

<ul>
	<li><code>coefficient</code>：该项的系数。项 <code><strong>9</strong>x<sup>4</sup></code> 的系数是 <code>9</code> 。</li>
	<li><code>power</code>：该项的指数。项 <code>9x<strong><sup>4</sup></strong></code> 的指数是 <code>4</code> 。</li>
	<li><code>next</code>：指向下一个节点的指针（引用），如果当前节点为链表的最后一个节点则为 <code>null</code> 。</li>
</ul>

<p>例如，多项式 <code>5x<sup>3</sup> + 4x - 7</code> 可以表示成如下图所示的多项式链表：</p>

<p><img alt="" src="/solution/1600-1699/1634.Add Two Polynomials Represented as Linked Lists/images/polynomial2.png" style="width: 500px; height: 91px;" /></p>

<p>多项式链表必须是标准形式的，即多项式必须<strong> 严格 </strong>按指数 <code>power</code> 的递减顺序排列（即降幂排列）。另外，系数 <code>coefficient</code> 为 <code>0</code> 的项需要省略。</p>

<p>给定两个多项式链表的头节点 <code>poly1</code> 和 <code>poly2</code>，返回它们的和的头节点。</p>

<p><strong><code>PolyNode</code> 格式：</strong></p>

<p>输入/输出格式表示为 <code>n</code> 个节点的列表，其中每个节点表示为 <code>[coefficient, power]</code> 。例如，多项式 <code>5x<sup>3</sup> + 4x - 7</code> 表示为： <code>[[5,3],[4,1],[-7,0]]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="/solution/1600-1699/1634.Add Two Polynomials Represented as Linked Lists/images/ex1.png" style="width: 600px; height: 322px;" /></p>

<pre>
<strong>输入：</strong>poly1 = [[1,1]], poly2 = [[1,0]]
<strong>输出：</strong>[[1,1],[1,0]]
<strong>解释：</strong>poly1 = x. poly2 = 1. 和为 x + 1.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>poly1 = [[2,2],[4,1],[3,0]], poly2 = [[3,2],[-4,1],[-1,0]]
<strong>输出：</strong>[[5,2],[2,0]]
<strong>解释：</strong>poly1 = 2x<sup>2</sup> + 4x + 3. poly2 = 3x<sup>2</sup> - 4x - 1. 和为 5x<sup>2</sup> + 2. 注意，我们省略 "0x" 项。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>poly1 = [[1,2]], poly2 = [[-1,2]]
<strong>输出：</strong>[]
<strong>解释：</strong>和为 0。我们返回空链表。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= PolyNode.coefficient <= 10<sup>9</sup></code></li>
	<li><code>PolyNode.coefficient != 0</code></li>
	<li><code>0 <= PolyNode.power <= 10<sup>9</sup></code></li>
	<li><code>PolyNode.power > PolyNode.next.power</code></li>
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
