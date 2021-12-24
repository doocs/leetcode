# [831. 隐藏个人信息](https://leetcode-cn.com/problems/masking-personal-information)

[English Version](/solution/0800-0899/0831.Masking%20Personal%20Information/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一条个人信息字符串 <code>S</code>，它可能是一个 <strong>邮箱地址</strong> ，也可能是一串 <strong>电话号码</strong> 。</p>

<p>我们将隐藏它的隐私信息，通过如下规则:</p>

<p>&nbsp;</p>

<p><strong>1. 电子邮箱</strong></p>

<p>定义名称 <strong>name</strong> 是长度大于等于 2 （<code>length &ge; 2</code>），并且只包含小写字母 <code>a-z</code> 和大写字母 <code>A-Z</code> 的字符串。</p>

<p>电子邮箱地址由名称 <strong>name</strong> 开头，紧接着是符号 <code>&#39;@&#39;</code>，后面接着一个名称 <strong>name</strong>，再接着一个点号 <code>&#39;.&#39;</code>，然后是一个名称 <strong>name</strong>。</p>

<p>电子邮箱地址确定为有效的，并且格式是 <code>&quot;name1@name2.name3&quot;</code>。</p>

<p>为了隐藏电子邮箱，所有的名称 <strong>name</strong> 必须被转换成小写的，并且第一个名称&nbsp;<strong>name</strong> 的第一个字母和最后一个字母的中间的所有字母由 5 个 <code>&#39;*&#39;</code> 代替。</p>

<p>&nbsp;</p>

<p><strong>2. 电话号码</strong></p>

<p>电话号码是一串包括数字&nbsp;<code>0-9</code>，以及 <code>{&#39;+&#39;, &#39;-&#39;, &#39;(&#39;, &#39;)&#39;, &#39;&nbsp;&#39;}</code> 这几个字符的字符串。你可以假设电话号码包含 10 到 13 个数字。</p>

<p>电话号码的最后 10 个数字组成本地号码，在这之前的数字组成国际号码。注意，国际号码是可选的。我们只暴露最后 4 个数字并隐藏所有其他数字。</p>

<p>本地号码是有格式的，并且如 <code>&quot;***-***-1111&quot;</code> 这样显示，这里的 1 表示暴露的数字。</p>

<p>为了隐藏有国际号码的电话号码，像&nbsp;<code>&quot;+111 111 111 1111&quot;</code>，我们以 <code>&quot;+***-***-***-1111&quot;</code> 的格式来显示。在本地号码前面的 <code>&#39;+&#39;</code> 号和第一个 <code>&#39;-&#39;</code> 号仅当电话号码中包含国际号码时存在。例如，一个 12 位的电话号码应当以 <code>&quot;+**-&quot;</code> 开头进行显示。</p>

<p>注意：像 <code>&quot;(&quot;，&quot;)&quot;，&quot; &quot;</code> 这样的不相干的字符以及不符合上述格式的额外的减号或者加号都应当被删除。</p>

<p>&nbsp;</p>

<p>最后，将提供的信息正确隐藏后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>&quot;LeetCode@LeetCode.com&quot;
<strong>输出: </strong>&quot;l*****e@leetcode.com&quot;
<strong>解释： 
</strong>所有的名称转换成小写, 第一个名称的第一个字符和最后一个字符中间由 5 个星号代替。
因此，&quot;leetcode&quot; -&gt; &quot;l*****e&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>&quot;AB@qq.com&quot;
<strong>输出: </strong>&quot;a*****b@qq.com&quot;
<strong>解释:&nbsp;
</strong>第一个名称&quot;ab&quot;的第一个字符和最后一个字符的中间必须有 5 个星号
因此，&quot;ab&quot; -&gt; &quot;a*****b&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入: </strong>&quot;1(234)567-890&quot;
<strong>输出: </strong>&quot;***-***-7890&quot;
<strong>解释:</strong>&nbsp;
10 个数字的电话号码，那意味着所有的数字都是本地号码。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入: </strong>&quot;86-(10)12345678&quot;
<strong>输出: </strong>&quot;+**-***-***-5678&quot;
<strong>解释:</strong>&nbsp;
12 位数字，2 个数字是国际号码另外 10 个数字是本地号码 。
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li><code>S.length&nbsp;&lt;=&nbsp;40</code>。</li>
	<li>邮箱的长度至少是 8。</li>
	<li>电话号码的长度至少是 10。</li>
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
