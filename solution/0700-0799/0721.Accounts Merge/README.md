# [721. 账户合并](https://leetcode-cn.com/problems/accounts-merge)

[English Version](/solution/0700-0799/0721.Accounts%20Merge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个列表 <code>accounts</code>，每个元素 <code>accounts[i]</code> 是一个字符串列表，其中第一个元素 <code>accounts[i][0]</code> 是 <em>名称 (name)</em>，其余元素是 <em>emails </em>表示该账户的邮箱地址。</p>

<p>现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。</p>

<p>合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
<b>输出：
</b>[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
<b>解释：</b>
第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>accounts</code>的长度将在<code>[1，1000]</code>的范围内。</li>
	<li><code>accounts[i]</code>的长度将在<code>[1，10]</code>的范围内。</li>
	<li><code>accounts[i][j]</code>的长度将在<code>[1，30]</code>的范围内。</li>
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
