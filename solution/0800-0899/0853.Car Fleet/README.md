# [853. 车队](https://leetcode-cn.com/problems/car-fleet)

[English Version](/solution/0800-0899/0853.Car%20Fleet/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>N</code> &nbsp;辆车沿着一条车道驶向位于&nbsp;<code>target</code>&nbsp;英里之外的共同目的地。</p>

<p>每辆车&nbsp;<code>i</code>&nbsp;以恒定的速度&nbsp;<code>speed[i]</code>&nbsp;（英里/小时），从初始位置&nbsp;<code>position[i]</code>&nbsp;（英里） 沿车道驶向目的地。</p>

<p>一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。</p>

<p>此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。</p>

<p><em>车队&nbsp;</em>是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。</p>

<p>即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。</p>

<p>&nbsp;</p>

<p>会有多少车队到达目的地?</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
<strong>输出：</strong>3
<strong>解释：</strong>
从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
</pre>

<p><br>
<strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= N &lt;= 10 ^ 4</code></li>
	<li><code>0 &lt; target&nbsp;&lt;= 10 ^ 6</code></li>
	<li><code>0 &lt;&nbsp;speed[i] &lt;= 10 ^ 6</code></li>
	<li><code>0 &lt;= position[i] &lt; target</code></li>
	<li>所有车的初始位置各不相同。</li>
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
