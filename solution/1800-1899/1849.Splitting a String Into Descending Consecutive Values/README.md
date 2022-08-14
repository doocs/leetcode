# [1849. 将字符串拆分为递减的连续值](https://leetcode.cn/problems/splitting-a-string-into-descending-consecutive-values)

[English Version](/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由数字组成的字符串 <code>s</code> 。</p>

<p>请你判断能否将 <code>s</code> 拆分成两个或者多个 <strong>非空子字符串</strong> ，使子字符串的 <strong>数值</strong> 按 <strong>降序</strong> 排列，且每两个 <strong>相邻子字符串</strong> 的数值之 <strong>差 </strong>等于 <code>1</code> 。</p>

<ul>
	<li>例如，字符串 <code>s = "0090089"</code> 可以拆分成 <code>["0090", "089"]</code> ，数值为 <code>[90,89]</code> 。这些数值满足按降序排列，且相邻值相差 <code>1</code> ，这种拆分方法可行。</li>
	<li>另一个例子中，字符串 <code>s = "001"</code> 可以拆分成 <code>["0", "01"]</code>、<code>["00", "1"]</code> 或 <code>["0", "0", "1"]</code> 。然而，所有这些拆分方法都不可行，因为对应数值分别是 <code>[0,1]</code>、<code>[0,1]</code> 和 <code>[0,0,1]</code> ，都不满足按降序排列的要求。</li>
</ul>

<p>如果可以按要求拆分 <code>s</code> ，返回 <code>true</code> ；否则，返回 <code>false</code><em> </em>。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1234"
<strong>输出：</strong>false
<strong>解释：</strong>不存在拆分 s 的可行方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "050043"
<strong>输出：</strong>true
<strong>解释：</strong>s 可以拆分为 ["05", "004", "3"] ，对应数值为 [5,4,3] 。
满足按降序排列，且相邻值相差 <code>1</code> 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "9080701"
<strong>输出：</strong>false
<strong>解释：</strong>不存在拆分 s 的可行方法。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "10009998"
<strong>输出：</strong>true
<strong>解释：</strong>s 可以拆分为 ["100", "099", "98"] ，对应数值为 [100,99,98] 。
满足按降序排列，且相邻值相差 <code>1</code> 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 20</code></li>
	<li><code>s</code> 仅由数字组成</li>
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
