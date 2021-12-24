# [1320. 二指输入的的最小距离](https://leetcode-cn.com/problems/minimum-distance-to-type-a-word-using-two-fingers)

[English Version](/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/images/leetcode_keyboard.png" style="height: 250px; width: 417px;"></p>

<p>二指输入法定制键盘在 XY 平面上的布局如上图所示，其中每个大写英文字母都位于某个坐标处，例如字母&nbsp;<strong>A</strong>&nbsp;位于坐标&nbsp;<strong>(0,0)</strong>，字母&nbsp;<strong>B</strong>&nbsp;位于坐标&nbsp;<strong>(0,1)</strong>，字母&nbsp;<strong>P</strong>&nbsp;位于坐标&nbsp;<strong>(2,3)</strong>&nbsp;且字母 <strong>Z</strong>&nbsp;位于坐标&nbsp;<strong>(4,1)</strong>。</p>

<p>给你一个待输入字符串&nbsp;<code>word</code>，请你计算并返回在仅使用两根手指的情况下，键入该字符串需要的最小移动总距离。坐标&nbsp;<strong>(x<sub>1</sub>,y<sub>1</sub>)</strong> 和 <strong>(x<sub>2</sub>,y<sub>2</sub>)</strong> 之间的距离是&nbsp;<strong>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</strong>。&nbsp;</p>

<p>注意，两根手指的起始位置是零代价的，不计入移动总距离。你的两根手指的起始位置也不必从首字母或者前两个字母开始。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>word = &quot;CAKE&quot;
<strong>输出：</strong>3
<strong>解释： 
</strong>使用两根手指输入 &quot;CAKE&quot; 的最佳方案之一是： 
手指 1 在字母 &#39;C&#39; 上 -&gt; 移动距离 = 0 
手指 1 在字母 &#39;A&#39; 上 -&gt; 移动距离 = 从字母 &#39;C&#39; 到字母 &#39;A&#39; 的距离 = 2 
手指 2 在字母 &#39;K&#39; 上 -&gt; 移动距离 = 0 
手指 2 在字母 &#39;E&#39; 上 -&gt; 移动距离 = 从字母 &#39;K&#39; 到字母 &#39;E&#39; 的距离  = 1 
总距离 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>word = &quot;HAPPY&quot;
<strong>输出：</strong>6
<strong>解释： </strong>
使用两根手指输入 &quot;HAPPY&quot; 的最佳方案之一是：
手指 1 在字母 &#39;H&#39; 上 -&gt; 移动距离 = 0
手指 1 在字母 &#39;A&#39; 上 -&gt; 移动距离 = 从字母 &#39;H&#39; 到字母 &#39;A&#39; 的距离 = 2
手指 2 在字母 &#39;P&#39; 上 -&gt; 移动距离 = 0
手指 2 在字母 &#39;P&#39; 上 -&gt; 移动距离 = 从字母 &#39;P&#39; 到字母 &#39;P&#39; 的距离 = 0
手指 1 在字母 &#39;Y&#39; 上 -&gt; 移动距离 = 从字母 &#39;A&#39; 到字母 &#39;Y&#39; 的距离 = 4
总距离 = 6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>word = &quot;NEW&quot;
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>word = &quot;YEAR&quot;
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 300</code></li>
	<li>每个 <code>word[i]</code>&nbsp;都是一个大写英文字母。</li>
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
