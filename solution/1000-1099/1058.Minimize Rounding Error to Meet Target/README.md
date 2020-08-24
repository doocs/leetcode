# [1058. 最小化舍入误差以满足目标](https://leetcode-cn.com/problems/minimize-rounding-error-to-meet-target)

[English Version](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一系列价格 <code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code> 和一个目标 <code>target</code>，将每个价格 <code>p<sub>i</sub></code> 舍入为 <code>Round<sub>i</sub>(p<sub>i</sub>)</code> 以使得舍入数组 <code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code> 之和达到给定的目标值 <code>target</code>。每次舍入操作 <code>Round<sub>i</sub>(p<sub>i</sub>)</code> 可以是向下舍 <code>Floor(p<sub>i</sub>)</code> 也可以是向上入 <code>Ceil(p<sub>i</sub>)</code>。</p>

<p>如果舍入数组之和无论如何都无法达到目标值 <code>target</code>，就返回 <code>-1</code>。否则，以保留到小数点后三位的字符串格式返回最小的舍入误差，其定义为 Σ |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)|（ i 从 1 到 n ）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prices = ["0.700","2.800","4.900"], target = 8
<strong>输出：</strong>"1.000"
<strong>解释： </strong>
使用 Floor，Ceil 和 Ceil 操作得到 (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prices = ["1.500","2.500","3.500"], target = 10
<strong>输出：</strong>"-1"
<strong>解释：</strong>
达到目标是不可能的。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 <= prices.length <= 500</code></li>
	<li>表示价格的每个字符串 <code>prices[i]</code> 都代表一个介于 0 和 1000 之间的实数，并且正好有 3 个小数位。</li>
	<li><code>target</code> 介于 0 和 1000000 之间。</li>
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