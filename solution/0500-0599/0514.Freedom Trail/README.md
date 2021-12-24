# [514. 自由之路](https://leetcode-cn.com/problems/freedom-trail)

[English Version](/solution/0500-0599/0514.Freedom%20Trail/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。</p>

<p>给定一个字符串 <strong>ring</strong>，表示刻在外环上的编码；给定另一个字符串 <strong>key</strong>，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的<strong>最少</strong>步数。</p>

<p>最初，<strong>ring </strong>的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 <strong>key </strong>的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 <strong>key </strong>中的所有字符。</p>

<p>旋转 <strong>ring </strong>拼出 key 字符 <strong>key[i] </strong>的阶段中：</p>

<ol>
	<li>您可以将 <strong>ring </strong>顺时针或逆时针旋转<strong>一个位置</strong>，计为1步。旋转的最终目的是将字符串 <strong>ring </strong>的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 <strong>key[i] 。</strong></li>
	<li>如果字符 <strong>key[i] </strong>已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 <strong>1 步</strong>。按完之后，您可以开始拼写 <strong>key </strong>的下一个字符（下一阶段）, 直至完成所有拼写。</li>
</ol>

<p><strong>示例：</strong></p>

<p> </p>

<center><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0514.Freedom%20Trail/images/ring.jpg" style="width: 26%;" /></center>
 

<pre>
<strong>输入:</strong> ring = "godding", key = "gd"
<strong>输出:</strong> 4
<strong>解释:</strong>
 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 当然, 我们还需要1步进行拼写。
 因此最终的输出是 4。
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><strong>ring</strong> 和 <strong>key</strong> 的字符串长度取值范围均为 1 至 100；</li>
	<li>两个字符串中都只有小写字符，并且均可能存在重复字符；</li>
	<li>字符串 <strong>key</strong> 一定可以由字符串 <strong>ring</strong> 旋转拼出。</li>
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
