# [165. 比较版本号](https://leetcode-cn.com/problems/compare-version-numbers)

## 题目描述
<!-- 这里写题目描述 -->
<p>比较两个版本号 <em>version1&nbsp;</em>和 <em>version2</em>。<br>
如果&nbsp;<code><em>version1&nbsp;</em>&gt;&nbsp;<em>version2</em></code>&nbsp;返回&nbsp;<code>1</code>，如果&nbsp;<code><em>version1&nbsp;</em>&lt;&nbsp;<em>version2</em></code> 返回 <code>-1</code>， 除此之外返回 <code>0</code>。</p>

<p>你可以假设版本字符串非空，并且只包含数字和&nbsp;<code>.</code> 字符。</p>

<p>&nbsp;<code>.</code> 字符不代表小数点，而是用于分隔数字序列。</p>

<p>例如，<code>2.5</code> 不是&ldquo;两个半&rdquo;，也不是&ldquo;差一半到三&rdquo;，而是第二版中的第五个小版本。</p>

<p>你可以假设版本号的每一级的默认修订版号为 <code>0</code>。例如，版本号 <code>3.4</code> 的第一级（大版本）和第二级（小版本）修订号分别为 <code>3</code> 和 <code>4</code>。其第三级和第四级修订号均为 <code>0</code>。<br>
&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> <code><em>version1</em></code> = &quot;0.1&quot;, <code><em>version2</em></code> = &quot;1.1&quot;
<strong>输出:</strong> -1</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong><code><em>version1</em></code> = &quot;1.0.1&quot;, <code><em>version2</em></code> = &quot;1&quot;
<strong>输出:</strong> 1</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> <code><em>version1</em></code> = &quot;7.5.2.4&quot;, <code><em>version2</em></code> = &quot;7.5.3&quot;
<strong>输出:</strong> -1</pre>

<p><strong>示例&nbsp;4：</strong></p>

<pre><code><strong>输入：</strong><em>version1</em></code> = &quot;1.01&quot;, <code><em>version2</em></code> = &quot;1.001&quot;
<strong>输出：</strong>0
<strong>解释：</strong>忽略前导零，&ldquo;01&rdquo; 和 &ldquo;001&rdquo; 表示相同的数字 &ldquo;1&rdquo;。</pre>

<p><strong>示例 5：</strong></p>

<pre><code><strong>输入：</strong><em>version1</em></code> = &quot;1.0&quot;, <code><em>version2</em></code> = &quot;1.0.0&quot;
<strong>输出：</strong>0
<strong>解释：</strong><code><em>version1 </em></code>没有第三级修订号，这意味着它的第三级修订号默认为 &ldquo;0&rdquo;。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>版本字符串由以点&nbsp;（<code>.</code>）&nbsp;分隔的数字字符串组成。这个数字字符串<strong>可能</strong>有前导零。</li>
	<li>版本字符串不以点开始或结束，并且其中不会有两个连续的点。</li>
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