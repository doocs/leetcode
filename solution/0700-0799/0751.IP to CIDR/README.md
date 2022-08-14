# [751. IP 到 CIDR](https://leetcode.cn/problems/ip-to-cidr)

[English Version](/solution/0700-0799/0751.IP%20to%20CIDR/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>IP地址</strong> 是一个格式化的 32位 无符号整数，每组 8位 被打印为一个十进制数字和，点字符&nbsp;<code>'.'</code>&nbsp;起到了分组的作用。</p>

<ul>
	<li>例如，二进制数 <code>00001111 10001000 11111111 01101011</code>&nbsp;( 为清晰起见增加了空格)被格式化为IP地址将是 <code>“15.136.255.107”</code> 。</li>
</ul>

<p><strong>CIDR块</strong> 是一种用来表示一组特定IP地址的格式。字符串形式，由基础IP地址、斜杠和前缀长度 <code>k</code> 组成。它所覆盖的地址是所有IP地址的 <strong>前 <code>k</code> 位</strong> 与基础IP地址相同的IP地址。</p>

<ul>
	<li>例如， <code>“123.45.67.89/20”</code> 是一个前缀长度为 <code>20</code> 的&nbsp;<strong>CIDR块</strong>。任何二进制表示形式匹配 <code>01111011 00101101 0100xxxx xxxxxxxx</code> 的IP地址，其中 <code>x</code> 可以是 <code>0</code> 或 <code>1</code> ，都在CIDR块覆盖的集合中。</li>
</ul>

<p>给你一个起始IP地址&nbsp;<code>ip</code>&nbsp;和我们需要覆盖的IP地址数量 <code>n</code> 。你的目标是使用 <strong>尽可能少的CIDR块</strong> 来&nbsp;<strong>覆盖范围&nbsp;<code>[ip, ip + n - 1]</code>&nbsp;内的所有IP地址</strong>&nbsp;。不应该覆盖范围之外的其他IP地址。</p>

<p>返回 <em>包含IP地址范围的 <strong>CIDR块</strong> 的 <strong>最短</strong> 列表。如果有多个答案，返回其中 <strong>任何</strong> 一个&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ip = "255.0.0.7", n = 10
<strong>输出：</strong>["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
<strong>解释：
</strong>需要覆盖的IP地址有:
- 255.0.0.7 -&gt; 11111111 00000000 00000000 00000111
- 255.0.0.8 -&gt; 11111111 00000000 00000000 00001000
- 255.0.0.9 -&gt; 11111111 00000000 00000000 00001001
- 255.0.0.10 -&gt; 11111111 00000000 00000000 00001010
- 255.0.0.11 -&gt; 11111111 00000000 00000000 00001011
- 255.0.0.12 -&gt; 11111111 00000000 00000000 00001100
- 255.0.0.13 -&gt; 11111111 00000000 00000000 00001101
- 255.0.0.14 -&gt; 11111111 00000000 00000000 00001110
- 255.0.0.15 -&gt; 11111111 00000000 00000000 00001111
- 255.0.0.16 -&gt; 11111111 00000000 00000000 00010000
CIDR区块“255.0.0.7/32”包含第一个地址。
CIDR区块255.0.0.8/29包含中间的8个地址(二进制格式为11111111 00000000 00000000 00001xxx)。
CIDR区块“255.0.0.16/32”包含最后一个地址。
请注意，虽然CIDR区块“255.0.0.0/28”覆盖了所有的地址，但它也包括范围之外的地址，所以我们不能使用它。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>ip = "117.145.102.62", n = 8
<b>输出：</b>["117.145.102.62/31","117.145.102.64/30","117.145.102.68/31"]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>7 &lt;= ip.length &lt;= 15</code></li>
	<li><code>ip</code>&nbsp;是一个有效的&nbsp;<strong>IPv4</strong>&nbsp;，形式为&nbsp;<code>"a.b.c.d"</code>&nbsp;，其中&nbsp;<code>a</code>,&nbsp;<code>b</code>,&nbsp;<code>c</code>,&nbsp;&nbsp;<code>d</code>&nbsp;是&nbsp;<code>[0, 255]</code>&nbsp;范围内的整数</li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li>每个隐含地址&nbsp;<code>ip + x</code>&nbsp;(&nbsp;<code>x &lt; n</code>) 都是有效的 IPv4 地址</li>
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
