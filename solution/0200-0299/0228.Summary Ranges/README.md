# [228. 汇总区间](https://leetcode-cn.com/problems/summary-ranges)

[English Version](/solution/0200-0299/0228.Summary%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个无重复元素的有序整数数组 <code>nums</code> 。</p>

<p>返回 <strong>恰好覆盖数组中所有数字</strong> 的 <strong>最小有序</strong> 区间范围列表。也就是说，<code>nums</code> 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 <code>nums</code> 的数字 <code>x</code> 。</p>

<p>列表中的每个区间范围 <code>[a,b]</code> 应该按如下格式输出：</p>

<ul>
	<li><code>"a->b"</code> ，如果 <code>a != b</code></li>
	<li><code>"a"</code> ，如果 <code>a == b</code></li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,2,4,5,7]
<strong>输出：</strong>["0->2","4->5","7"]
<strong>解释：</strong>区间范围是：
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,2,3,4,6,8,9]
<strong>输出：</strong>["0","2->4","6","8->9"]
<strong>解释：</strong>区间范围是：
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1]
<strong>输出：</strong>["-1"]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>["0"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 20</code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
	<li><code>nums</code> 中的所有值都 <strong>互不相同</strong></li>
	<li><code>nums</code> 按升序排列</li>
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
