# [1237. 找出给定方程的正整数解](https://leetcode-cn.com/problems/find-positive-integer-solution-for-a-given-equation)

[English Version](/solution/1200-1299/1237.Find%20Positive%20Integer%20Solution%20for%20a%20Given%20Equation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个函数  <code>f(x, y)</code> 和一个目标结果 <code>z</code>，函数公式未知，请你计算方程 <code>f(x,y) == z</code> 所有可能的正整数 <strong>数对</strong> <code>x</code> 和 <code>y</code>。满足条件的结果数对可以按任意顺序返回。</p>

<p>尽管函数的具体式子未知，但它是单调递增函数，也就是说：</p>

<ul>
	<li><code>f(x, y) < f(x + 1, y)</code></li>
	<li><code>f(x, y) < f(x, y + 1)</code></li>
</ul>

<p>函数接口定义如下：</p>

<pre>
interface CustomFunction {
public:
  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
  int f(int x, int y);
};</pre>

<p>你的解决方案将按如下规则进行评判：</p>

<ul>
	<li>判题程序有一个由 <code>CustomFunction</code> 的 <code>9</code> 种实现组成的列表，以及一种为特定的 <code>z</code> 生成所有有效数对的答案的方法。</li>
	<li>判题程序接受两个输入：<code>function_id</code>（决定使用哪种实现测试你的代码）以及目标结果 <code>z</code> 。</li>
	<li>判题程序将会调用你实现的 <code>findSolution</code> 并将你的结果与答案进行比较。</li>
	<li>如果你的结果与答案相符，那么解决方案将被视作正确答案，即 <code>Accepted</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>function_id = 1, z = 5
<strong>输出：</strong>[[1,4],[2,3],[3,2],[4,1]]
<strong>解释：</strong>function_id = 1 暗含的函数式子为 f(x, y) = x + y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=4 -> f(1, 4) = 1 + 4 = 5
x=2, y=3 -> f(2, 3) = 2 + 3 = 5
x=3, y=2 -> f(3, 2) = 3 + 2 = 5
x=4, y=1 -> f(4, 1) = 4 + 1 = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>function_id = 2, z = 5
<strong>输出：</strong>[[1,5],[5,1]]
<strong>解释：</strong>function_id = 2 暗含的函数式子为 f(x, y) = x * y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=5 -> f(1, 5) = 1 * 5 = 5
x=5, y=1 -> f(5, 1) = 5 * 1 = 5</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= function_id <= 9</code></li>
	<li><code>1 <= z <= 100</code></li>
	<li>题目保证 <code>f(x, y) == z</code> 的解处于 <code>1 <= x, y <= 1000</code> 的范围内。</li>
	<li>在 <code>1 <= x, y <= 1000</code> 的前提下，题目保证 <code>f(x, y)</code> 是一个 32 位有符号整数。</li>
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
