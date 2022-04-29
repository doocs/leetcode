# [2197. 替换数组中的非互质数](https://leetcode.cn/problems/replace-non-coprime-numbers-in-array)

[English Version](/solution/2100-2199/2197.Replace%20Non-Coprime%20Numbers%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。请你对数组执行下述操作：</p>

<ol>
	<li>从 <code>nums</code> 中找出 <strong>任意</strong> 两个 <strong>相邻</strong> 的 <strong>非互质</strong> 数。</li>
	<li>如果不存在这样的数，<strong>终止</strong> 这一过程。</li>
	<li>否则，删除这两个数，并 <strong>替换</strong> 为它们的 <strong>最小公倍数</strong>（Least Common Multiple，LCM）。</li>
	<li>只要还能找出两个相邻的非互质数就继续 <strong>重复</strong> 这一过程。</li>
</ol>

<p>返回修改后得到的 <strong>最终</strong> 数组。可以证明的是，以 <strong>任意</strong> 顺序替换相邻的非互质数都可以得到相同的结果。</p>

<p>生成的测试用例可以保证最终数组中的值 <strong>小于或者等于</strong> <code>10<sup>8</sup></code> 。</p>

<p>两个数字 <code>x</code> 和 <code>y</code> 满足 <strong>非互质数</strong> 的条件是：<code>GCD(x, y) &gt; 1</code> ，其中 <code>GCD(x, y)</code> 是 <code>x</code> 和 <code>y</code> 的 <strong>最大公约数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,4,3,2,7,6,2]
<strong>输出：</strong>[12,7,6]
<strong>解释：</strong>
- (6, 4) 是一组非互质数，且 LCM(6, 4) = 12 。得到 nums = [<em><strong>12</strong></em>,3,2,7,6,2] 。
- (12, 3) 是一组非互质数，且 LCM(12, 3) = 12 。得到 nums = [<em><strong>12</strong></em>,2,7,6,2] 。
- (12, 2) 是一组非互质数，且 LCM(12, 2) = 12 。得到 nums = [<em><strong>12</strong></em>,7,6,2] 。
- (6, 2) 是一组非互质数，且 LCM(6, 2) = 6 。得到 nums = [12,7,<em><strong>6</strong></em>] 。
现在，nums 中不存在相邻的非互质数。
因此，修改后得到的最终数组是 [12,7,6] 。
注意，存在其他方法可以获得相同的最终数组。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,1,1,3,3,3]
<strong>输出：</strong>[2,1,1,3]
<strong>解释：</strong>
- (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,<em><strong>3</strong></em>,3] 。
- (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,<em><strong>3</strong></em>] 。
- (2, 2) 是一组非互质数，且 LCM(2, 2) = 2 。得到 nums = [<em><strong>2</strong></em>,1,1,3] 。
现在，nums 中不存在相邻的非互质数。 
因此，修改后得到的最终数组是 [2,1,1,3] 。 
注意，存在其他方法可以获得相同的最终数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>生成的测试用例可以保证最终数组中的值 <strong>小于或者等于</strong> <code>10<sup>8</sup></code> 。</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
