# [690. 员工的重要性](https://leetcode-cn.com/problems/employee-importance)

[English Version](/solution/0600-0699/0690.Employee%20Importance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个保存员工信息的数据结构，它包含了员工 <strong>唯一的 id </strong>，<strong>重要度 </strong>和 <strong>直系下属的 id </strong>。</p>

<p>比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 <strong>并不是直系</strong> 下属，因此没有体现在员工 1 的数据结构中。</p>

<p>现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
<strong>输出：</strong>11
<strong>解释：</strong>
员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>一个员工最多有一个<strong> 直系 </strong>领导，但是可以有多个 <strong>直系 </strong>下属</li>
	<li>员工数量不超过 2000 。</li>
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
