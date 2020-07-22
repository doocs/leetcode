# [399. 除法求值](https://leetcode-cn.com/problems/evaluate-division)

[English Version](/solution/0300-0399/0399.Evaluate%20Division/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给出方程式&nbsp;<code>A / B = k</code>, 其中&nbsp;<code>A</code> 和&nbsp;<code>B</code> 均为代表字符串的变量，&nbsp;<code>k</code> 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回&nbsp;<code>-1.0</code>。</p>

<p><strong>示例 :</strong><br />
给定&nbsp;<code>a / b = 2.0, b / c = 3.0</code><br />
问题: <code> a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?&nbsp;</code><br />
返回&nbsp;<code>[6.0, 0.5, -1.0, 1.0, -1.0 ]</code></p>

<p>输入为: <code> vector&lt;pair&lt;string, string&gt;&gt; equations, vector&lt;double&gt;&amp; values, vector&lt;pair&lt;string, string&gt;&gt; queries</code>(方程式，方程式结果，问题方程式)，&nbsp;其中&nbsp;<code>equations.size() == values.size()</code>，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。&nbsp;返回<code>vector&lt;double&gt;</code>类型。</p>

<p>基于上述例子，输入如下：</p>

<pre>
equations(方程式) = [ [&quot;a&quot;, &quot;b&quot;], [&quot;b&quot;, &quot;c&quot;] ],
values(方程式结果) = [2.0, 3.0],
queries(问题方程式) = [ [&quot;a&quot;, &quot;c&quot;], [&quot;b&quot;, &quot;a&quot;], [&quot;a&quot;, &quot;e&quot;], [&quot;a&quot;, &quot;a&quot;], [&quot;x&quot;, &quot;x&quot;] ]. 
</pre>

<p>输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。</p>



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