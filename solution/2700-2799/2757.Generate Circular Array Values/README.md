---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2757.Generate%20Circular%20Array%20Values/README.md
---

# [2757. 生成循环数组的值 🔒](https://leetcode.cn/problems/generate-circular-array-values)

[English Version](/solution/2700-2799/2757.Generate%20Circular%20Array%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定你一个 <strong>循环</strong> 数组 <code>arr</code> 和一个整数 <code>startIndex</code> ，返回一个生成器对象 <code>gen</code>&nbsp;，它从 <code>arr</code> 中生成值。第一次调用 <code>gen.next()</code> 时，它应该生成 <code>arr[startIndex]</code> 。每次调用 <code>gen.next()</code> 时，都会传入一个整数参数&nbsp;<code>jump</code>（例如：<code>gen.next(-3)</code> ）。</p>

<ul>
	<li>如果 <code>jump</code> 是正数，则索引应该增加该值，但如果当前索引是最后一个索引，则应跳转到第一个索引。</li>
	<li>如果 <code>jump</code> 是负数，则索引应减去该值的绝对值，但如果当前索引是第一个索引，则应跳转到最后一个索引。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>arr = [1,2,3,4,5], steps = [1,2,6], startIndex = 0
<b>输出：</b>[1,2,4,5]
<strong>解释：</strong> &nbsp;
&nbsp;const gen = cycleGenerator(arr, startIndex);
&nbsp;gen.next().value; &nbsp;// 1, index = startIndex = 0
&nbsp;gen.next(1).value; // 2, index = 1, 0 -&gt; 1
&nbsp;gen.next(2).value; // 4, index = 3, 1 -&gt; 2 -&gt; 3
&nbsp;gen.next(6).value; // 5, index = 4, 3 -&gt; 4 -&gt; 0 -&gt; 1 -&gt; 2 -&gt; 3 -&gt; 4
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [10,11,12,13,14,15], steps = [1,4,0,-1,-3], startIndex = 1
<b>输出：</b>[11,12,10,10,15,12]
<b>解释：</b>
&nbsp;const gen = cycleGenerator(arr, startIndex);
&nbsp;gen.next().value; &nbsp; // 11, index = 1
&nbsp;gen.next(1).value;  // 12, index = 2
&nbsp;gen.next(4).value;  // 10, index = 0
&nbsp;gen.next(0).value;  // 10, index = 0
&nbsp;gen.next(-1).value; // 15, index = 5
&nbsp;gen.next(-3).value; // 12, index = 2
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [2,4,6,7,8,10], steps = [-4,5,-3,10], startIndex = 3
<b>输出：</b>[7,10,8,4,10]
<b>解释：</b>
&nbsp;const gen = cycleGenerator(arr, startIndex);
&nbsp;gen.next().value &nbsp; // 7,  index = 3
&nbsp;gen.next(-4).value // 10, index = 5
&nbsp;gen.next(5).value  // 8,  index = 4
&nbsp;gen.next(-3).value // 4,  index = 1 &nbsp;
&nbsp;gen.next(10).value // 10, index = 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= steps.length &lt;= 100</code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= steps[i],&nbsp;arr[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= startIndex &lt;&nbsp;arr.length</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function* cycleGenerator(arr: number[], startIndex: number): Generator<number, void, number> {
    const n = arr.length;
    while (true) {
        const jump = yield arr[startIndex];
        startIndex = (((startIndex + jump) % n) + n) % n;
    }
}
/**
 *  const gen = cycleGenerator([1,2,3,4,5], 0);
 *  gen.next().value  // 1
 *  gen.next(1).value // 2
 *  gen.next(2).value // 4
 *  gen.next(6).value // 5
 */
```

<!-- tabs:end -->

<!-- end -->
