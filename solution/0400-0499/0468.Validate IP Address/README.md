# [468. 验证 IP 地址](https://leetcode-cn.com/problems/validate-ip-address)

[English Version](/solution/0400-0499/0468.Validate%20IP%20Address/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数来验证输入的字符串是否是有效的 IPv4 或&nbsp;IPv6 地址。</p>

<ul>
	<li>如果是有效的 IPv4 地址，返回 <code>&quot;IPv4&quot;</code> ；</li>
	<li>如果是有效的 IPv6 地址，返回 <code>&quot;IPv6&quot;</code> ；</li>
	<li>如果不是上述类型的 IP 地址，返回 <code>&quot;Neither&quot;</code> 。</li>
</ul>

<p><strong>IPv4</strong>&nbsp;地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为&nbsp;0 -&nbsp;255，&nbsp;用(&quot;.&quot;)分割。比如，<code>172.16.254.1</code>；</p>

<p>同时，IPv4 地址内的数不会以 0 开头。比如，地址&nbsp;<code>172.16.254.01</code> 是不合法的。</p>

<p><strong>IPv6</strong>&nbsp;地址由 8 组 16 进制的数字来表示，每组表示&nbsp;16 比特。这些组数字通过 (&quot;:&quot;)分割。比如,&nbsp;&nbsp;<code>2001:0db8:85a3:0000:0000:8a2e:0370:7334</code> 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以，&nbsp;<code>2001:db8:85a3:0:0:8A2E:0370:7334</code> 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。</p>

<p>然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。&nbsp;比如，&nbsp;<code>2001:0db8:85a3::8A2E:0370:7334</code> 是无效的 IPv6 地址。</p>

<p>同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如，&nbsp;<code>02001:0db8:85a3:0000:0000:8a2e:0370:7334</code> 是无效的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>IP = &quot;172.16.254.1&quot;
<strong>输出：</strong>&quot;IPv4&quot;
<strong>解释：</strong>有效的 IPv4 地址，返回 &quot;IPv4&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>IP = &quot;2001:0db8:85a3:0:0:8A2E:0370:7334&quot;
<strong>输出：</strong>&quot;IPv6&quot;
<strong>解释：</strong>有效的 IPv6 地址，返回 &quot;IPv6&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>IP = &quot;256.256.256.256&quot;
<strong>输出：</strong>&quot;Neither&quot;
<strong>解释：</strong>既不是 IPv4 地址，又不是 IPv6 地址
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>IP = &quot;2001:0db8:85a3:0:0:8A2E:0370:7334:&quot;
<strong>输出：</strong>&quot;Neither&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>IP = &quot;1e1.4.5.6&quot;
<strong>输出：</strong>&quot;Neither&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>IP</code> 仅由英文字母，数字，字符 <code>&#39;.&#39;</code> 和 <code>&#39;:&#39;</code> 组成。</li>
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
