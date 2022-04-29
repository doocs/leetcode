# [948. 令牌放置](https://leetcode.cn/problems/bag-of-tokens)

[English Version](/solution/0900-0999/0948.Bag%20of%20Tokens/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的初始 <strong>能量</strong> 为 <code>P</code>，初始 <strong>分数</strong> 为 <code>0</code>，只有一包令牌 <code>tokens</code> 。其中 <code>tokens[i]</code> 是第 <code>i</code> 个令牌的值（下标从 0 开始）。</p>

<p>令牌可能的两种使用方法如下：</p>

<ul>
	<li>如果你至少有 <code>token[i]</code> 点 <strong>能量</strong> ，可以将令牌 <code>i</code> 置为正面朝上，失去 <code>token[i]</code> 点 <strong>能量</strong> ，并得到 <code>1</code> <strong>分</strong> 。</li>
	<li>如果我们至少有 <code>1</code> <strong>分 </strong>，可以将令牌 <code>i</code> 置为反面朝上，获得 <code>token[i]</code> 点 <strong>能量</strong> ，并失去 <code>1</code> <strong>分</strong> 。</li>
</ul>

<p>每个令牌 <strong>最多</strong> 只能使用一次，使用 <strong>顺序不限</strong> ，<strong>不需</strong> 使用所有令牌。</p>

<p>在使用任意数量的令牌后，返回我们可以得到的最大 <strong>分数</strong> 。</p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100], P = 50
<strong>输出：</strong>0
<strong>解释：</strong>无法使用唯一的令牌，因为能量和分数都太少了。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100,200], P = 150
<strong>输出：</strong>1
<strong>解释：</strong>令牌 0 正面朝上，能量变为 50，分数变为 1 。不必使用令牌 1 ，因为你无法使用它来提高分数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>tokens = [100,200,300,400], P = 200
<strong>输出：</strong>2
<strong>解释：</strong>按下面顺序使用令牌可以得到 2 分：
1. 令牌 0 正面朝上，能量变为 100 ，分数变为 1
2. 令牌 3 正面朝下，能量变为 500 ，分数变为 0
3. 令牌 1 正面朝上，能量变为 300 ，分数变为 1
4. 令牌 2 正面朝上，能量变为 0 ，分数变为 2</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= tokens.length <= 1000</code></li>
	<li><code>0 <= tokens[i], P < 10<sup>4</sup></code></li>
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
