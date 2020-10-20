# [519. 随机翻转矩阵](https://leetcode-cn.com/problems/random-flip-matrix)

[English Version](/solution/0500-0599/0519.Random%20Flip%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>题中给出一个 <code>n</code> 行 <code>n</code> 列的二维矩阵<code> (n_rows,n_cols)</code>，且所有值被初始化为 0。要求编写一个 <code>flip</code> 函数，<a href="https://en.wikipedia.org/wiki/Discrete_uniform_distribution">均匀随机</a>的将矩阵中的 0 变为 1，并返回该值的位置下标 <code>[row_id,col_id]</code>；同样编写一个 <code>reset</code> 函数，将所有的值都重新置为 0。<strong>尽量最少调用随机函数 Math.random()</strong>，并且优化时间和空间复杂度。</p>

<p>注意:</p>

<p>1.1 &lt;= n_rows, n_cols &lt;= 10000</p>

<p>2. 0 &lt;= row.id &lt; n_rows 并且 0 &lt;= col.id &lt; n_cols</p>

<p>3.当矩阵中没有值为 0 时，不可以调用 flip 函数</p>

<p>4.调用 flip 和 reset 函数的次数加起来不会超过 1000 次</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;flip&quot;]
[[2,3],[],[],[],[]]
<strong>输出: </strong>[null,[0,1],[1,2],[1,0],[1,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;reset&quot;,&quot;flip&quot;]
[[1,2],[],[],[],[]]
<strong>输出: </strong>[null,[0,0],[0,1],null,[0,0]]</pre>

<p><strong>输入语法解释：</strong></p>

<p>输入包含两个列表：被调用的子程序和他们的参数。<code>Solution</code> 的构造函数有两个参数，分别为 <code>n_rows</code> 和 <code>n_cols</code>。<code>flip</code>&nbsp;和 <code>reset</code> 没有参数，参数总会以列表形式给出，哪怕该列表为空</p>

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
