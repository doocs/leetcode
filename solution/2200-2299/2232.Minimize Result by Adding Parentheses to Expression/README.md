# [2232. 向表达式添加括号后的最小结果](https://leetcode-cn.com/problems/minimize-result-by-adding-parentheses-to-expression)

[English Version](/solution/2200-2299/2232.Minimize%20Result%20by%20Adding%20Parentheses%20to%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>expression</code> ，格式为 <code>"&lt;num1&gt;+&lt;num2&gt;"</code> ，其中 <code>&lt;num1&gt;</code> 和 <code>&lt;num2&gt;</code> 表示正整数。</p>

<p>请你向 <code>expression</code> 中添加一对括号，使得在添加之后， <code>expression</code> 仍然是一个有效的数学表达式，并且计算后可以得到 <strong>最小</strong> 可能值。左括号 <strong>必须</strong> 添加在 <code>'+'</code> 的左侧，而右括号必须添加在 <code>'+'</code> 的右侧。</p>

<p>返回添加一对括号后形成的表达式&nbsp;<code>expression</code> ，且满足<em> </em><code>expression</code><em> </em>计算得到 <strong>最小</strong> 可能值<em>。</em>如果存在多个答案都能产生相同结果，返回任意一个答案。</p>

<p>生成的输入满足：<code>expression</code> 的原始值和添加满足要求的任一对括号之后 <code>expression</code> 的值，都符合 32-bit 带符号整数范围。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>expression = "247+38"
<strong>输出：</strong>"2(47+38)"
<strong>解释：</strong>表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 <code>'+' 的右侧。</code>
可以证明 170 是最小可能值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>expression = "12+34"
<strong>输出：</strong>"1(2+3)4"
<strong>解释：</strong>表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>expression = "999+999"
<strong>输出：</strong>"(999+999)"
<strong>解释：</strong>表达式计算得到 999 + 999 = 1998 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= expression.length &lt;= 10</code></li>
	<li><code>expression</code> 仅由数字 <code>'1'</code> 到 <code>'9'</code> 和 <code>'+'</code> 组成</li>
	<li><code>expression</code> 由数字开始和结束</li>
	<li><code>expression</code> 恰好仅含有一个 <code>'+'</code>.</li>
	<li><code>expression</code> 的原始值和添加满足要求的任一对括号之后 <code>expression</code> 的值，都符合 32-bit 带符号整数范围</li>
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
