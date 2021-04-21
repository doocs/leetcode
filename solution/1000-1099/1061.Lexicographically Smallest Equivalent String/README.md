# [1061. 按字典序排列最小的等效字符串](https://leetcode-cn.com/problems/lexicographically-smallest-equivalent-string)

[English Version](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出长度相同的两个字符串：<code>A</code> 和&nbsp;<code>B</code>，其中 A[i] 和 B[i] 是一组等价字符。举个例子，如果&nbsp;<code>A = &quot;abc&quot;</code> 且&nbsp;<code>B = &quot;cde&quot;</code>，那么就有&nbsp;<code>&#39;a&#39; == &#39;c&#39;, &#39;b&#39; == &#39;d&#39;, &#39;c&#39; == &#39;e&#39;</code>。</p>

<p>等价字符遵循任何等价关系的一般规则：</p>

<ul>
	<li>自反性：&#39;a&#39; == &#39;a&#39;</li>
	<li>对称性：&#39;a&#39; == &#39;b&#39; 则必定有 &#39;b&#39; == &#39;a&#39;</li>
	<li>传递性：&#39;a&#39; == &#39;b&#39; 且 &#39;b&#39; == &#39;c&#39; 就表明 &#39;a&#39; == &#39;c&#39;</li>
</ul>

<p>例如，<code>A</code> 和&nbsp;<code>B</code>&nbsp;的等价信息和之前的例子一样，那么&nbsp;<code>S = &quot;eed&quot;</code>, <code>&quot;acd&quot;</code>&nbsp;或&nbsp;<code>&quot;aab&quot;</code>，这三个字符串都是等价的，而&nbsp;<code>&quot;aab&quot;</code>&nbsp;是 <code>S</code>&nbsp;的按字典序最小的等价字符串</p>

<p>利用&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;的等价信息，找出并返回 <code>S</code>&nbsp;的按字典序排列最小的等价字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = &quot;parker&quot;, B = &quot;morris&quot;, S = &quot;parser&quot;
<strong>输出：</strong>&quot;makkek&quot;
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i] 共 4 组</code>。每组中的字符都是等价的，并按字典序排列。所以答案是 <code>&quot;makkek&quot;</code>。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = &quot;hello&quot;, B = &quot;world&quot;, S = &quot;hold&quot;
<strong>输出：</strong>&quot;hdld&quot;
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r] 共 3 组</code>。所以只有 S 中的第二个字符 <code>&#39;o&#39;</code> 变成 <code>&#39;d&#39;，最后答案为<span style=""> </span></code><code>&quot;hdld&quot;</code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = &quot;leetcode&quot;, B = &quot;programs&quot;, S = &quot;sourcecode&quot;
<strong>输出：</strong>&quot;aauaaaaada&quot;
<strong>解释：</strong>我们可以把 A 和 B 中的等价字符分为 <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> 和 <code>[d,m] 共 4 组</code>，因此 <code>S</code> 中除了 <code>&#39;u&#39;</code> 和 <code>&#39;d&#39;</code> 之外的所有字母都转化成了 <code>&#39;a&#39;</code>，最后答案为 <code>&quot;aauaaaaada&quot;</code>。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串&nbsp;<code>A</code>，<code>B</code>&nbsp;和&nbsp;<code>S</code>&nbsp;仅有从&nbsp;<code>&#39;a&#39;</code> 到&nbsp;<code>&#39;z&#39;</code>&nbsp;的小写英文字母组成。</li>
	<li>字符串&nbsp;<code>A</code>，<code>B</code>&nbsp;和&nbsp;<code>S</code>&nbsp;的长度在&nbsp;<code>1</code> 到&nbsp;<code>1000</code>&nbsp;之间。</li>
	<li>字符串&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;长度相同。</li>
</ol>


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
