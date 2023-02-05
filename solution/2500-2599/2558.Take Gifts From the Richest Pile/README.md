# [2558. 从数量最多的堆取走礼物](https://leetcode.cn/problems/take-gifts-from-the-richest-pile)

[English Version](/solution/2500-2599/2558.Take%20Gifts%20From%20the%20Richest%20Pile/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>gifts</code> ，表示各堆礼物的数量。每一秒，你需要执行以下操作：</p>

<ul>
	<li>选择礼物数量最多的那一堆。</li>
	<li>如果不止一堆都符合礼物数量最多，从中选择任一堆即可。</li>
	<li>选中的那一堆留下平方根数量的礼物（向下取整），取走其他的礼物。</li>
</ul>

<p>返回在 <code>k</code> 秒后剩下的礼物数量<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>gifts = [25,64,9,4,100], k = 4
<strong>输出：</strong>29
<strong>解释：</strong> 
按下述方式取走礼物：
- 在第一秒，选中最后一堆，剩下 10 个礼物。
- 接着第二秒选中第二堆礼物，剩下 8 个礼物。
- 然后选中第一堆礼物，剩下 5 个礼物。
- 最后，再次选中最后一堆礼物，剩下 3 个礼物。
最后剩下的礼物数量分别是 [5,8,9,4,3] ，所以，剩下礼物的总数量是 29 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>gifts = [1,1,1,1], k = 4
<strong>输出：</strong>4
<strong>解释：</strong>
在本例中，不管选中哪一堆礼物，都必须剩下 1 个礼物。 
也就是说，你无法获取任一堆中的礼物。 
所以，剩下礼物的总数量是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= gifts.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= gifts[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
