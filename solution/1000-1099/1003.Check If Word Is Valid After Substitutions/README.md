# [1003. 检查替换后的词是否有效](https://leetcode-cn.com/problems/check-if-word-is-valid-after-substitutions)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定有效字符串&nbsp;<code>&quot;abc&quot;</code>。</p>

<p>对于任何有效的字符串 <code>V</code>，我们可以将 <code>V</code> 分成两个部分 <code>X</code> 和 <code>Y</code>，使得 <code>X + Y</code>（<code>X</code> 与 <code>Y</code> 连接）等于 <code>V</code>。（<code>X</code>&nbsp;或 <code>Y</code> 可以为空。）那么，<code>X + &quot;abc&quot; + Y</code> 也同样是有效的。</p>

<p>例如，如果 <code>S = &quot;abc&quot;</code>，则有效字符串的示例是：<code>&quot;abc&quot;</code>，<code>&quot;aabcbc&quot;</code>，<code>&quot;abcabc&quot;</code>，<code>&quot;abcabcababcc&quot;</code>。<strong>无效</strong>字符串的示例是：<code>&quot;abccba&quot;</code>，<code>&quot;ab&quot;</code>，<code>&quot;cababc&quot;</code>，<code>&quot;bac&quot;</code>。</p>

<p>如果给定字符串 <code>S</code> 有效，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;aabcbc&quot;
<strong>输出：</strong>true
<strong>解释：</strong>
从有效字符串 &quot;abc&quot; 开始。
然后我们可以在 &quot;a&quot; 和 &quot;bc&quot; 之间插入另一个 &quot;abc&quot;，产生 &quot;a&quot; + &quot;abc&quot; + &quot;bc&quot;，即 &quot;aabcbc&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;abcabcababcc&quot;
<strong>输出：</strong>true
<strong>解释：</strong>
&quot;abcabcabc&quot; 是有效的，它可以视作在原串后连续插入 &quot;abc&quot;。
然后我们可以在最后一个字母之前插入 &quot;abc&quot;，产生 &quot;abcabcab&quot; + &quot;abc&quot; + &quot;c&quot;，即 &quot;abcabcababcc&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>&quot;abccba&quot;
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>&quot;cababc&quot;
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 20000</code></li>
	<li><code>S[i]</code> 为&nbsp;<code>&#39;a&#39;</code>、<code>&#39;b&#39;</code>、或&nbsp;<code>&#39;c&#39;</code></li>
</ol>

<p>&nbsp;</p>



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
<!-- tabs:end -->