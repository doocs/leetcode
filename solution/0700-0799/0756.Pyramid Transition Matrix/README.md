# [756. 金字塔转换矩阵](https://leetcode-cn.com/problems/pyramid-transition-matrix)

## 题目描述
<!-- 这里写题目描述 -->
<p>现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示。</p>

<p>使用三元组表示金字塔的堆砌规则如下：</p>

<p>对于三元组(A, B, C) ，&ldquo;C&rdquo;为顶层方块，方块&ldquo;A&rdquo;、&ldquo;B&rdquo;分别作为方块&ldquo;C&rdquo;下一层的的左、右子块。当且仅当(A, B, C)是被允许的三元组，我们才可以将其堆砌上。</p>

<p>初始时，给定金字塔的基层&nbsp;<code>bottom</code>，用一个字符串表示。一个允许的三元组列表&nbsp;<code>allowed</code>，每个三元组用一个长度为 3 的字符串表示。</p>

<p>如果可以由基层一直堆到塔尖就返回 true，否则返回 false。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> bottom = &quot;BCD&quot;, allowed = [&quot;BCG&quot;, &quot;CDE&quot;, &quot;GEA&quot;, &quot;FFF&quot;]
<strong>输出:</strong> true
<strong>解析:</strong>
可以堆砌成这样的金字塔:
    A
   / \
  G   E
 / \ / \
B   C   D

因为符合(&#39;B&#39;, &#39;C&#39;, &#39;G&#39;), (&#39;C&#39;, &#39;D&#39;, &#39;E&#39;) 和 (&#39;G&#39;, &#39;E&#39;, &#39;A&#39;) 三种规则。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> bottom = &quot;AABA&quot;, allowed = [&quot;AAA&quot;, &quot;AAB&quot;, &quot;ABA&quot;, &quot;ABB&quot;, &quot;BAC&quot;]
<strong>输出:</strong> false
<strong>解析:</strong>
无法一直堆到塔尖。
注意, 允许存在像 (A, B, C) 和 (A, B, D) 这样的三元组，其中 C != D。
</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ol>
	<li><code>bottom</code> 的长度范围在&nbsp;<code>[2, 8]</code>。</li>
	<li><code>allowed</code> 的长度范围在<code>[0, 200]</code>。</li>
	<li>方块的标记字母范围为<code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;, &#39;G&#39;}</code>。</li>
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