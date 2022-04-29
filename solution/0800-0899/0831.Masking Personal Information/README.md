# [831. 隐藏个人信息](https://leetcode.cn/problems/masking-personal-information)

[English Version](/solution/0800-0899/0831.Masking%20Personal%20Information/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一条个人信息字符串 <code>s</code> ，可能表示一个 <strong>邮箱地址</strong> ，也可能表示一串 <strong>电话号码</strong> 。返回按如下规则 <strong>隐藏</strong> 个人信息后的结果：</p>

<p><em><strong>电子邮件地址：</strong></em></p>

<p>一个电子邮件地址由以下部分组成：</p>

<ul>
	<li>一个 <strong>名字</strong> ，由大小写英文字母组成，后面跟着</li>
	<li>一个 <code>'@'</code> 字符，后面跟着</li>
	<li>一个 <strong>域名</strong> ，由大小写英文字母和一个位于中间的 <code>'.'</code> 字符组成。<code>'.'</code> 不会是域名的第一个或者最后一个字符。</li>
</ul>

<p>要想隐藏电子邮件地址中的个人信息：</p>

<ul>
	<li><strong>名字</strong> 和 <strong>域名</strong> 部分的大写英文字母应当转换成小写英文字母。</li>
	<li><strong>名字</strong> 中间的字母（即，除第一个和最后一个字母外）必须用 5 个 <code>"*****"</code> 替换。</li>
</ul>

<p><em><strong>电话号码：</strong></em></p>

<p>一个电话号码应当按下述格式组成：</p>

<ul>
	<li>电话号码可以由 10-13 位数字组成</li>
	<li>后 10 位构成 <strong>本地号码</strong></li>
	<li>前面剩下的 0-3 位，构成 <strong>国家代码</strong></li>
	<li>利用 <code>{'+', '-', '(', ')', ' '}</code> 这些 <strong>分隔字符</strong> 按某种形式对上述数字进行分隔</li>
</ul>

<p>要想隐藏电话号码中的个人信息：</p>

<ul>
	<li>移除所有 <strong>分隔字符</strong></li>
	<li>隐藏个人信息后的电话号码应该遵从这种格式：
	<ul>
		<li><code>"***-***-XXXX"</code> 如果国家代码为 0 位数字</li>
		<li><code>"+*-***-***-XXXX"</code> 如果国家代码为 1 位数字</li>
		<li><code>"+**-***-***-XXXX"</code> 如果国家代码为 2 位数字</li>
		<li><code>"+***-***-***-XXXX"</code> 如果国家代码为 3 位数字</li>
	</ul>
	</li>
	<li><code>"XXXX"</code> 是最后 4 位 <strong>本地号码</strong></li>
</ul>
&nbsp;

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "LeetCode@LeetCode.com"
<strong>输出：</strong>"l*****e@leetcode.com"
<strong>解释：</strong>s 是一个电子邮件地址。
名字和域名都转换为小写，名字的中间用 5 个 * 替换。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AB@qq.com"
<strong>输出：</strong>"a*****b@qq.com"
<strong>解释：</strong>s 是一个电子邮件地址。
名字和域名都转换为小写，名字的中间用 5 个 * 替换。
注意，尽管 "ab" 只有两个字符，但中间仍然必须有 5 个 * 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "1(234)567-890"
<strong>输出：</strong>"***-***-7890"
<strong>解释：</strong>s 是一个电话号码。
共计 10 位数字，所以本地号码为 10 位数字，国家代码为 0 位数字。
因此，隐藏后的电话号码应该是 "***-***-7890" 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "86-(10)12345678"
<strong>输出：</strong>"+**-***-***-5678"
<strong>解释：</strong>s 是一个电话号码。
共计 12 位数字，所以本地号码为 10 位数字，国家代码为 2 位数字。
因此，隐藏后的电话号码应该是 "+**-***-***-7890" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s</code> 是一个 <strong>有效</strong> 的电子邮件或者电话号码</li>
	<li>如果 <code>s</code> 是一个电子邮件：
	<ul>
		<li><code>8 &lt;= s.length &lt;= 40</code></li>
		<li><code>s</code> 是由大小写英文字母，恰好一个 <code>'@'</code> 字符，以及 <code>'.'</code> 字符组成</li>
	</ul>
	</li>
	<li>如果 <code>s</code> 是一个电话号码：
	<ul>
		<li><code>10 &lt;= s.length &lt;= 20</code></li>
		<li><code>s</code> 是由数字、空格、字符 <code>'('</code>、<code>')'</code>、<code>'-'</code> 和 <code>'+'</code> 组成</li>
	</ul>
	</li>
</ul>
</div>
</div>
</div>

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
