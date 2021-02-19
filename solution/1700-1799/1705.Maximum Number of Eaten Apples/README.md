# [1705. 吃苹果的最大数目](https://leetcode-cn.com/problems/maximum-number-of-eaten-apples)

[English Version](/solution/1500-1599/1705.Maximum%20Number%20of%20Eaten%20Apples/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有一棵特殊的苹果树，一连 <code>n</code> 天，每天都可以长出若干个苹果。在第 <code>i</code> 天，树上会长出 <code>apples[i]</code> 个苹果，这些苹果将会在 <code>days[i]</code> 天后（也就是说，第 <code>i + days[i]</code> 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 <code>apples[i] == 0</code> 且 <code>days[i] == 0</code> 表示。</p>

<p>你打算每天 <strong>最多</strong> 吃一个苹果来保证营养均衡。注意，你可以在这 <code>n</code> 天之后继续吃苹果。</p>

<p>给你两个长度为 <code>n</code> 的整数数组 <code>days</code> 和 <code>apples</code> ，返回你可以吃掉的苹果的最大数目<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>apples = [1,2,3,5,2], days = [3,2,1,4,2]
<strong>输出：</strong>7
<strong>解释：</strong>你可以吃掉 7 个苹果：
- 第一天，你吃掉第一天长出来的苹果。
- 第二天，你吃掉一个第二天长出来的苹果。
- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
- 第四天到第七天，你吃的都是第四天长出来的苹果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
<strong>输出：</strong>5
<strong>解释：</strong>你可以吃掉 5 个苹果：
- 第一天到第三天，你吃的都是第一天长出来的苹果。
- 第四天和第五天不吃苹果。
- 第六天和第七天，你吃的都是第六天长出来的苹果。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>apples.length == n</code></li>
	<li><code>days.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= apples[i], days[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li>只有在 <code>apples[i] = 0</code> 时，<code>days[i] = 0</code> 才成立</li>
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