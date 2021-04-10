# [1769. 移动所有球到每个盒子所需的最小操作数](https://leetcode-cn.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box)

[English Version](/solution/1700-1799/1769.Minimum%20Number%20of%20Operations%20to%20Move%20All%20Balls%20to%20Each%20Box/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个盒子。给你一个长度为 <code>n</code> 的二进制字符串 <code>boxes</code> ，其中 <code>boxes[i]</code> 的值为 <code>'0'</code> 表示第 <code>i</code> 个盒子是 <strong>空</strong> 的，而 <code>boxes[i]</code> 的值为 <code>'1'</code> 表示盒子里有 <strong>一个</strong> 小球。</p>

<p>在一步操作中，你可以将 <strong>一个</strong> 小球从某个盒子移动到一个与之相邻的盒子中。第 <code>i</code> 个盒子和第 <code>j</code> 个盒子相邻需满足 <code>abs(i - j) == 1</code> 。注意，操作执行后，某些盒子中可能会存在不止一个小球。</p>

<p>返回一个长度为 <code>n</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是将所有小球移动到第 <code>i</code> 个盒子所需的 <strong>最小</strong> 操作数。</p>

<p>每个 <code>answer[i]</code> 都需要根据盒子的 <strong>初始状态</strong> 进行计算。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>boxes = "110"
<strong>输出：</strong>[1,1,3]
<strong>解释：</strong>每个盒子对应的最小操作数如下：
1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>boxes = "001011"
<strong>输出：</strong>[11,8,5,4,3,4]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == boxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>boxes[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
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
