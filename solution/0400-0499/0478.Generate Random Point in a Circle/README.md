# [478. 在圆内随机生成点](https://leetcode.cn/problems/generate-random-point-in-a-circle)

[English Version](/solution/0400-0499/0478.Generate%20Random%20Point%20in%20a%20Circle/README_EN.md)

<!-- tags:几何,数学,拒绝采样,随机化 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定圆的半径和圆心的位置，实现函数 <code>randPoint</code> ，在圆中产生均匀随机点。</p>

<p>实现&nbsp;<code>Solution</code>&nbsp;类:</p>

<ul>
	<li><code>Solution(double radius, double x_center, double y_center)</code>&nbsp;用圆的半径&nbsp;<code>radius</code>&nbsp;和圆心的位置<code> (x_center, y_center)</code> 初始化对象</li>
	<li><code>randPoint()</code>&nbsp;返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 <code>[x, y]</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: 
</strong>["Solution","randPoint","randPoint","randPoint"]
[[1.0, 0.0, 0.0], [], [], []]
<strong>输出: </strong>[null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
<strong>解释:</strong>
Solution solution = new Solution(1.0, 0.0, 0.0);
solution.randPoint ();//返回[-0.02493，-0.38077]
solution.randPoint ();//返回[0.82314,0.38945]
solution.randPoint ();//返回[0.36572,0.17248]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;&nbsp;radius &lt;= 10<sup>8</sup></code></li>
	<li><code>-10<sup>7</sup>&nbsp;&lt;= x_center, y_center &lt;= 10<sup>7</sup></code></li>
	<li><code>randPoint</code> 最多被调用&nbsp;<code>3 * 10<sup>4</sup></code>&nbsp;次</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def __init__(self, radius: float, x_center: float, y_center: float):
        self.radius = radius
        self.x_center = x_center
        self.y_center = y_center

    def randPoint(self) -> List[float]:
        length = math.sqrt(random.uniform(0, self.radius**2))
        degree = random.uniform(0, 1) * 2 * math.pi
        x = self.x_center + length * math.cos(degree)
        y = self.y_center + length * math.sin(degree)
        return [x, y]
```

<!-- tabs:end -->

<!-- end -->
