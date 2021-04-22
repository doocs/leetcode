# [38. 外观数列](https://leetcode-cn.com/problems/count-and-say)

[English Version](/solution/0000-0099/0038.Count%20and%20Say/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code> ，输出外观数列的第 <code>n</code> 项。</p>

<p>「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。</p>

<p>你可以将其视作是由递归公式定义的数字字符串序列：</p>

<ul>
	<li><code>countAndSay(1) = "1"</code></li>
	<li><code>countAndSay(n)</code> 是对 <code>countAndSay(n-1)</code> 的描述，然后转换成另一个数字字符串。</li>
</ul>

<p>前五项如下：</p>

<pre>
1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1 
描述前一项，这个数是 <code>1</code> 即 “ 一 个 1 ”，记作 <code>"11"
</code>描述前一项，这个数是 <code>11</code> 即 “ 二 个 1 ” ，记作 <code>"21"
</code>描述前一项，这个数是 <code>21</code> 即 “ 一 个 2 + 一 个 1 ” ，记作 "<code>1211"
</code>描述前一项，这个数是 <code>1211</code> 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "<code>111221"</code>
</pre>

<p>要 <strong>描述</strong> 一个数字字符串，首先要将字符串分割为 <strong>最小</strong> 数量的组，每个组都由连续的最多 <strong>相同字符</strong> 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。</p>

<p>例如，数字字符串 <code>"3322251"</code> 的描述如下图：</p>
<img alt="" src="/solution/0000-0099/0038.Count and Say/images/countandsay.jpg" style="width: 581px; height: 172px;" />
<ul>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>"1"
<strong>解释：</strong>这是一个基本样例。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>"1211"
<strong>解释：</strong>
countAndSay(1) = "1"
countAndSay(2) = 读 "1" = 一 个 1 = "11"
countAndSay(3) = 读 "11" = 二 个 1 = "21"
countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 30</code></li>
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
