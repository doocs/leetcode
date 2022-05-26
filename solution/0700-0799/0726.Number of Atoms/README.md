# [726. 原子的数量](https://leetcode.cn/problems/number-of-atoms)

[English Version](/solution/0700-0799/0726.Number%20of%20Atoms/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串化学式 <code>formula</code> ，返回 <strong>每种原子的数量</strong> 。</p>

<p>原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。</p>

<p>如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。</p>

<ul>
	<li>例如，<code>"H2O"</code> 和 <code>"H2O2"</code> 是可行的，但 <code>"H1O2"</code> 这个表达是不可行的。</li>
</ul>

<p>两个化学式连在一起可以构成新的化学式。</p>

<ul>
	<li>例如 <code>"H2O2He3Mg4"</code> 也是化学式。</li>
</ul>

<p>由括号括起的化学式并佐以数字（可选择性添加）也是化学式。</p>

<ul>
	<li>例如 <code>"(H2O2)"</code> 和 <code>"(H2O2)3"</code> 是化学式。</li>
</ul>

<p>返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>formula = "H2O"
<strong>输出：</strong>"H2O"
<strong>解释：</strong>原子的数量是 {'H': 2, 'O': 1}。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>formula = "Mg(OH)2"
<strong>输出：</strong>"H2MgO2"
<strong>解释：</strong>原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>formula = "K4(ON(SO3)2)2"
<strong>输出：</strong>"K4N2O14S4"
<strong>解释：</strong>原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= formula.length&nbsp;&lt;= 1000</code></li>
	<li><code>formula</code> 由英文字母、数字、<code>'('</code> 和 <code>')'</code> 组成</li>
	<li><code>formula</code> 总是有效的化学式</li>
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
