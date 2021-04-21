# [170. 两数之和 III - 数据结构设计](https://leetcode-cn.com/problems/two-sum-iii-data-structure-design)

[English Version](/solution/0100-0199/0170.Two%20Sum%20III%20-%20Data%20structure%20design/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。</p>

<p>实现 <code>TwoSum</code> 类：</p>

<ul>
	<li><code>TwoSum()</code> 使用空数组初始化 <code>TwoSum</code> 对象</li>
	<li><code>void add(int number)</code> 向数据结构添加一个数 <code>number</code></li>
	<li><code>boolean find(int value)</code> 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
<strong>输出：</strong>
[null, null, null, null, true, false]

<strong>解释：</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4，返回 true
twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>5</sup> <= number <= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= value <= 2<sup>31</sup> - 1</code></li>
	<li>最多调用 <code>5 * 10<sup>4</sup></code> 次 <code>add</code> 和 <code>find</code></li>
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
