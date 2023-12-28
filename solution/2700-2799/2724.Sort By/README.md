# [2724. 排序方式](https://leetcode.cn/problems/sort-by)

[English Version](/solution/2700-2799/2724.Sort%20By/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>arr</code> 和一个函数 <code>fn</code>，返回一个排序后的数组 <code>sortedArr</code>。你可以假设 <code>fn</code> 只返回数字，并且这些数字决定了 <code>sortedArr</code> 的排序顺序。<code>sortedArr</code> 必须按照 <code>fn</code> 的输出值&nbsp;<strong>升序</strong> 排序。</p>

<p>你可以假设对于给定的数组，<code>fn</code> 不会返回重复的数字。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>arr = [5, 4, 1, 2, 3], fn = (x) =&gt; x
<b>输出：</b>[1, 2, 3, 4, 5]
<b>解释：</b>fn 只是返回传入的数字，因此数组按升序排序。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [{"x": 1}, {"x": 0}, {"x": -1}], fn = (d) =&gt; d.x
<b>输出：</b>[{"x": -1}, {"x": 0}, {"x": 1}]
<b>解释：</b>fn 返回 "x" 键的值，因此数组根据该值排序。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [[3, 4], [5, 2], [10, 1]], fn = (x) =&gt; x[1]
<b>输出：</b>[[10, 1], [5, 2], [3, 4]]
<b>解释：</b>数组按照索引为 1 处的数字升序排序。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr</code> 是一个有效的 JSON 数组</li>
	<li><code>fn</code> 是一个函数，返回一个数字</li>
	<li><code>1 &lt;=&nbsp;arr.length &lt;= 5 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function sortBy(arr: any[], fn: Function): any[] {
    return arr.sort((a, b) => fn(a) - fn(b));
}
```

<!-- tabs:end -->
