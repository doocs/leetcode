# [893. 特殊等价字符串组](https://leetcode-cn.com/problems/groups-of-special-equivalent-strings)

[English Version](/solution/0800-0899/0893.Groups%20of%20Special-Equivalent%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你将得到一个字符串数组 <code>A</code>。</p>

<p>每次移动都可以交换 S 的任意两个偶数下标的字符或任意两个奇数下标的字符。</p>

<p>如果经过任意次数的移动，S == T，那么两个字符串 <code>S</code> 和 <code>T</code> 是 <strong>特殊等价 </strong>的。</p>

<p>例如，<code>S = &quot;zzxy&quot;</code> 和 <code>T = &quot;xyzz&quot;</code> 是一对特殊等价字符串，因为可以先交换 <code>S[0]</code> 和 <code>S[2]</code>，然后交换 <code>S[1]</code> 和 <code>S[3]</code>，使得 <code>&quot;zzxy&quot; -&gt; &quot;xzzy&quot; -&gt; &quot;xyzz&quot;</code> 。</p>

<p>现在规定，<strong><code>A</code> </strong>的 <strong>一组特殊等价字符串 </strong>就是 <code>A</code> 的一个同时满足下述条件的非空子集：</p>

<ol>
	<li>该组中的每一对字符串都是<strong> 特殊等价 </strong>的</li>
	<li>该组字符串已经涵盖了该类别中的所有特殊等价字符串，容量达到理论上的最大值（也就是说，如果一个字符串不在该组中，那么这个字符串就 <strong>不会</strong> 与该组内任何字符串特殊等价）</li>
</ol>

<p>返回 <code>A</code>&nbsp;中特殊等价字符串组的数量。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;abcd&quot;,&quot;cdab&quot;,&quot;cbad&quot;,&quot;xyzz&quot;,&quot;zzxy&quot;,&quot;zzyx&quot;]
<strong>输出：</strong>3
<strong>解释：</strong>
其中一组为 [&quot;abcd&quot;, &quot;cdab&quot;, &quot;cbad&quot;]，因为它们是成对的特殊等价字符串，且没有其他字符串与这些字符串特殊等价。
另外两组分别是 [&quot;xyzz&quot;, &quot;zzxy&quot;] 和 [&quot;zzyx&quot;]。特别需要注意的是，&quot;zzxy&quot; 不与 &quot;zzyx&quot; 特殊等价。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;]
<strong>输出：</strong>3
<strong>解释：</strong>3 组 [&quot;abc&quot;,&quot;cba&quot;]，[&quot;acb&quot;,&quot;bca&quot;]，[&quot;bac&quot;,&quot;cab&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= A.length &lt;= 1000</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 20</code></li>
	<li>所有&nbsp;<code>A[i]</code>&nbsp;都具有相同的长度。</li>
	<li>所有&nbsp;<code>A[i]</code>&nbsp;都只由小写字母组成。</li>
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
