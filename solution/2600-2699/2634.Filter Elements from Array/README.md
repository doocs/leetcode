# [2634. 过滤数组中的元素](https://leetcode.cn/problems/filter-elements-from-array)

[English Version](/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，该函数接受一个整数数组参数 <code>arr</code> 和一个过滤函数 <code>fn</code>，并返回一个过滤后元素数量较少或元素数量相等的新数组。</p>

<p>返回的数组应该只包含通过过滤函数&nbsp;<code>fn(arr[i]， i)</code> 计算后为真值的元素。</p>

<p>请你在不使用内置函数&nbsp;<code>Array.filter</code>&nbsp;的前提下解决该问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,10,20,30], fn = function greaterThan10(n) { return n &gt; 10; }
<b>输出：</b> [20,30]
<b>解释：</b>
const newArray = filter(arr, fn); // [20, 30]
过滤函数过滤掉不大于 10 的值</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [1,2,3], fn = function firstIndex(n, i) { return i === 0; }
<b>输出：</b>[1]
<strong>解释：</strong>
过滤函数 fn 也可以接受每个元素的索引
在这种情况下，过滤函数删除索引不为 0 的元素
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [-2,-1,0,1,2], fn = function plusOne(n) { return n + 1 }
<b>输出：</b>[-2,0,1,2]
<strong>解释：</strong>
像 0 这样的假值应被过滤掉
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></font></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

我们遍历数组 $arr$，对于每个元素 $arr[i]$，如果 $fn(arr[i], i)$ 为真，则将其加入答案数组中。最后返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $arr$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function filter(arr: number[], fn: (n: number, i: number) => any): number[] {
    const ans: number[] = [];
    for (let i = 0; i < arr.length; ++i) {
        if (fn(arr[i], i)) {
            ans.push(arr[i]);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
