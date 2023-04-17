# [2626. 数组归约运算](https://leetcode.cn/problems/array-reduce-transformation)

[English Version](/solution/2600-2699/2626.Array%20Reduce%20Transformation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它的参数为一个整数数组&nbsp;<code>nums</code>&nbsp;、一个计算函数&nbsp;<code>fn</code>&nbsp;和初始值&nbsp;<font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">init&nbsp;</span></span></font></font>。返回一个数组&nbsp;<strong>归约后 </strong>的值。</p>

<p>你可以定义一个数组&nbsp;<strong>归约后 </strong>的值，然后应用以下操作： <code>val = fn(init, nums[0])</code>&nbsp;， <code>val = fn(val, nums[1])</code>&nbsp;， <code>val = fn(val, arr[2])</code>&nbsp;，<code>...</code>&nbsp;直到数组中的每个元素都被处理完毕。返回 <code>val</code> 的最终值。</p>

<p>如果数组的长度为 0，它应该返回 <code>init</code>&nbsp;的值。</p>

<p>请你在不使用内置数组方法的&nbsp;<code>Array.reduce</code>&nbsp;前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
nums = [1,2,3,4]
fn = function sum(accum, curr) { return accum + curr; }
init = 0
<strong>输出：</strong>10
<strong>解释：</strong>
初始值为 init=0 。
(0) + nums[0] = 1
(1) + nums[1] = 3
(3) + nums[2] = 6
(6) + nums[3] = 10
Val 最终值为 10。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong> 
nums = [1,2,3,4]
fn = function sum(accum, curr) { return accum + curr * curr; }
init = 100
<strong>输出：</strong>130
<strong>解释：</strong>
初始值为 init=0 。
(100) + nums[0]^2 = 101
(101) + nums[1]^2 = 105
(105) + nums[2]^2 = 114
(114) + nums[3]^2 = 130
Val 最终值为 130。
</pre>

<p><strong class="example">示例3:</strong></p>

<pre>
<strong>输入：</strong> 
nums = []
fn = function sum(accum, curr) { return 0; }
init = 25
<strong>输出：</strong>25
<b>解释：</b>这是一个空数组，所以返回 init 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= init &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
type Fn = (accum: number, curr: number) => number;

function reduce(nums: number[], fn: Fn, init: number): number {
    let acc: number = init;
    for (const x of nums) {
        acc = fn(acc, x);
    }
    return acc;
}
```

### **...**

```

```

<!-- tabs:end -->
