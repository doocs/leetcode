# [1618. 找出适应屏幕的最大字号](https://leetcode-cn.com/problems/maximum-font-to-fit-a-sentence-in-a-screen)

[English Version](/solution/1600-1699/1618.Maximum%20Font%20to%20Fit%20a%20Sentence%20in%20a%20Screen/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>text</code>。并能够在 宽为 <code>w</code> 高为 <code>h</code> 的屏幕上显示该文本。</p>

<p>字体数组中包含按<strong>升序排列</strong>的可用字号，您可以从该数组中选择任何字体大小。</p>

<p>您可以使用<code>FontInfo</code>接口来获取任何<strong>可用字体大小</strong>的任何字符的宽度和高度。</p>

<p><code>FontInfo</code>接口定义如下：</p>

<pre>interface FontInfo {
  // 返回 fontSize 大小的字符 ch 在屏幕上的宽度。
  // 每调用该函数复杂度为 O(1)
  public int getWidth(int fontSize, char ch);

  // 返回 fontSize 大小的任意字符在屏幕上的高度。
  // 每调用该函数复杂度为 O(1)
  public int getHeight(int fontSize);
}</pre>

<p>一串字符的文本宽度应该是<strong>每一个字符</strong>在对应字号<code>(fontSize)</code>下返回的宽度<code>getHeight(fontSize)</code>的<strong>总和</strong>。</p>

<p><strong>请注意：文本最多只能排放一排</strong></p>

<p>如果使用相同的参数调用 <code>getHeight</code> 或 <code>getWidth</code> ，则可以保证 <code>FontInfo</code> 将返回相同的值。</p>

<p>同时，对于任何字体大小的 <code>fontSize</code> 和任何字符 <code>ch</code> ：</p>

<ul>
	<li><code>getHeight(fontSize) &lt;= getHeight(fontSize+1)</code></li>
	<li><code>getWidth(fontSize, ch) &lt;= getWidth(fontSize+1, ch)</code></li>
</ul>

<p>返回可用于在屏幕上显示文本的最大字体大小。<strong>如果文本不能以任何字体大小显示，则返回-1</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> text = "helloworld", w = 80, h = 20, fonts = [6,8,10,12,14,16,18,24,36]
<strong>输出:</strong> 6
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>输入:</strong> text = "leetcode", w = 1000, h = 50, fonts = [1,2,4]
<strong>输出:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>输入:</strong> text = "easyquestion", w = 100, h = 100, fonts = [10,15,20,25]
<strong>输出:</strong> -1
</pre>

<p> </p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 50000</code></li>
	<li><code>text</code> 只包含小写字母</li>
	<li><code>1 &lt;= w &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= h &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= fonts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= fonts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>fonts </code>已经按升序排序，且不包含重复项。</li>
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
