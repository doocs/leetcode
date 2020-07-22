# [514. 自由之路](https://leetcode-cn.com/problems/freedom-trail)

## 题目描述
<!-- 这里写题目描述 -->
<p>视频游戏&ldquo;辐射4&rdquo;中，任务&ldquo;通向自由&rdquo;要求玩家到达名为&ldquo;Freedom Trail Ring&rdquo;的金属表盘，并使用表盘拼写特定关键词才能开门。</p>



<p>给定一个字符串&nbsp;<strong>ring</strong>，表示刻在外环上的编码；给定另一个字符串&nbsp;<strong>key</strong>，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的<strong>最少</strong>步数。</p>



<p>最初，<strong>ring&nbsp;</strong>的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使&nbsp;<strong>key&nbsp;</strong>的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完&nbsp;<strong>key&nbsp;</strong>中的所有字符。</p>



<p>旋转&nbsp;<strong>ring&nbsp;</strong>拼出 key 字符&nbsp;<strong>key[i]&nbsp;</strong>的阶段中：</p>



<ol>

	<li>您可以将&nbsp;<strong>ring&nbsp;</strong>顺时针或逆时针旋转<strong>一个位置</strong>，计为1步。旋转的最终目的是将字符串&nbsp;<strong>ring&nbsp;</strong>的一个字符与 12:00 方向对齐，并且这个字符必须等于字符&nbsp;<strong>key[i] 。</strong></li>

	<li>如果字符&nbsp;<strong>key[i]&nbsp;</strong>已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作&nbsp;<strong>1 步</strong>。按完之后，您可以开始拼写&nbsp;<strong>key&nbsp;</strong>的下一个字符（下一阶段）, 直至完成所有拼写。</li>

</ol>



<p><strong>示例：</strong></p>



<p>&nbsp;</p>



<center><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/ring.jpg" style="width: 26%;"></center>

&nbsp;



<pre><strong>输入:</strong> ring = &quot;godding&quot;, key = &quot;gd&quot;

<strong>输出:</strong> 4

<strong>解释:</strong>

 对于 key 的第一个字符 &#39;g&#39;，已经在正确的位置, 我们只需要1步来拼写这个字符。 

 对于 key 的第二个字符 &#39;d&#39;，我们需要逆时针旋转 ring &quot;godding&quot; 2步使它变成 &quot;ddinggo&quot;。

 当然, 我们还需要1步进行拼写。

 因此最终的输出是 4。

</pre>



<p><strong>提示：</strong></p>



<ol>

	<li><strong>ring</strong> 和&nbsp;<strong>key</strong>&nbsp;的字符串长度取值范围均为&nbsp;1 至&nbsp;100；</li>

	<li>两个字符串中都只有小写字符，并且均可能存在重复字符；</li>

	<li>字符串&nbsp;<strong>key</strong>&nbsp;一定可以由字符串 <strong>ring</strong>&nbsp;旋转拼出。</li>

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