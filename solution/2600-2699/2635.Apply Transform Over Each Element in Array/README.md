# [2635. 转换数组中的每个元素](https://leetcode.cn/problems/apply-transform-over-each-element-in-array)

[English Version](/solution/2600-2699/2635.Apply%20Transform%20Over%20Each%20Element%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数，这个函数接收一个整数数组&nbsp;<code>arr</code> 和一个映射函数&nbsp; <code>fn</code>&nbsp;，通过该映射函数返回一个新的数组。</p>

<p>返回数组的创建语句应为 <code>returnedArray[i] = fn(arr[i], i)</code>&nbsp;。</p>

<p>请你在不使用内置方法&nbsp;<code>Array.map</code>&nbsp;的前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3], fn = function plusone(n) { return n + 1; }
<strong>输出：</strong>[2,3,4]
<strong>解释： </strong>
const newArray = map(arr, plusone); // [2,3,4]
此映射函数返回值是将数组中每个元素的值加 1。
</pre>

<p><strong class="example">示例</strong><strong class="example"> 2:</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3], fn = function plusI(n, i) { return n + i; }
<strong>输出：</strong>[1,3,5]
<strong>解释：</strong>此映射函数返回值根据输入数组索引增加每个值。
</pre>

<p><strong class="example">示例&nbsp;3:</strong></p>

<pre>
<strong>输入：</strong>arr = [10,20,30], fn = function constant() { return 42; }
<strong>输出：</strong>[42,42,42]
<strong>解释：</strong>此映射函数返回值恒为 42。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></font></code></li>
	<li><font face="monospace"><code>fn 返回一个数</code></font></li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​​​</span></span></span>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

我们遍历数组 $arr$，对于每个元素 $arr[i]$，将其替换为 $fn(arr[i], i)$。最后返回数组 $arr$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $arr$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function map(arr: number[], fn: (n: number, i: number) => number): number[] {
    for (let i = 0; i < arr.length; ++i) {
        arr[i] = fn(arr[i], i);
    }
    return arr;
}
```

### **...**

```

```

<!-- tabs:end -->
