---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2695.Array%20Wrapper/README.md
---

# [2695. 包装数组](https://leetcode.cn/problems/array-wrapper)

[English Version](/solution/2600-2699/2695.Array%20Wrapper/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>创建一个名为 <code>ArrayWrapper</code> 的类，它在其构造函数中接受一个整数数组作为参数。该类应具有以下两个特性：</p>

<ul>
	<li>当使用 <code>+</code> 运算符将两个该类的实例相加时，结果值为两个数组中所有元素的总和。</li>
	<li>当在实例上调用 <code>String()</code> 函数时，它将返回一个由逗号分隔的括在方括号中的字符串。例如，<code>[1,2,3]</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [[1,2],[3,4]], operation = "Add"
<b>输出：</b>10
<b>解释：</b>
const obj1 = new ArrayWrapper([1,2]);
const obj2 = new ArrayWrapper([3,4]);
obj1 + obj2; // 10
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [[23,98,42,70]], operation = "String"
<b>输出：</b>"[23,98,42,70]"
<strong>解释：</strong>
const obj = new ArrayWrapper([23,98,42,70]);
String(obj); // "[23,98,42,70]"
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [[],[]], operation = "Add"
<b>输出：</b>0
<strong>解释：</strong>
const obj1 = new ArrayWrapper([]);
const obj2 = new ArrayWrapper([]);
obj1 + obj2; // 0
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 1000</code></li>
	<li><code>注意：nums 是传递给构造函数的数组。</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
class ArrayWrapper {
    private nums: number[];
    private s: number;

    constructor(nums: number[]) {
        this.nums = nums;
        this.s = nums.reduce((a, b) => a + b, 0);
    }

    valueOf() {
        return this.s;
    }

    toString() {
        return `[${this.nums}]`;
    }
}

/**
 * const obj1 = new ArrayWrapper([1,2]);
 * const obj2 = new ArrayWrapper([3,4]);
 * obj1 + obj2; // 10
 * String(obj1); // "[1,2]"
 * String(obj2); // "[3,4]"
 */
```

<!-- tabs:end -->

<!-- end -->
