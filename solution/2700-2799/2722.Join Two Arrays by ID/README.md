---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2722.Join%20Two%20Arrays%20by%20ID/README.md
---

# [2722. 根据 ID 合并两个数组](https://leetcode.cn/problems/join-two-arrays-by-id)

[English Version](/solution/2700-2799/2722.Join%20Two%20Arrays%20by%20ID/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定两个数组 <code>arr1</code> 和 <code>arr2</code> ，返回一个新的数组 <code>joinedArray</code> 。两个输入数组中的每个对象都包含一个 <code>id</code> 字段。</p>

<p><code>joinedArray</code> 是一个通过 <code>id</code> 将 <code>arr1</code> 和 <code>arr2</code> 连接而成的数组。<code>joinedArray</code> 的长度应为唯一值 <code>id</code> 的长度。返回的数组应按 <code>id</code> <strong>升序</strong> 排序。</p>

<p>如果一个 <code>id</code> 存在于一个数组中但不存在于另一个数组中，则该对象应包含在结果数组中且不进行修改。</p>

<p>如果两个对象共享一个 <code>id</code> ，则它们的属性应进行合并：</p>

<ul>
	<li>如果一个键只存在于一个对象中，则该键值对应该包含在对象中。</li>
	<li>如果一个键在两个对象中都包含，则 <code>arr2</code> 中的值应覆盖 <code>arr1</code> 中的值。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
arr1 = [
&nbsp;   {"id": 1, "x": 1},
&nbsp;   {"id": 2, "x": 9}
], 
arr2 = [
    {"id": 3, "x": 5}
]
<b>输出：</b>
[
&nbsp;   {"id": 1, "x": 1},
&nbsp;   {"id": 2, "x": 9},
    {"id": 3, "x": 5}
]
<b>解释：</b>没有共同的 id，因此将 arr1 与 arr2 简单地连接起来。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
arr1 = [
    {"id": 1, "x": 2, "y": 3},
    {"id": 2, "x": 3, "y": 6}
], 
arr2 = [
    {"id": 2, "x": 10, "y": 20},
    {"id": 3, "x": 0, "y": 0}
]
<b>输出：</b>
[
    {"id": 1, "x": 2, "y": 3},
    {"id": 2, "x": 10, "y": 20},
&nbsp;   {"id": 3, "x": 0, "y": 0}
]
<b>解释：</b>id 为 1 和 id 为 3 的对象在结果数组中保持不变。id 为 2 的两个对象合并在一起。arr2 中的键覆盖 arr1 中的值。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
arr1 = [
    {"id": 1, "b": {"b": 94},"v": [4, 3], "y": 48}
]
arr2 = [
    {"id": 1, "b": {"c": 84}, "v": [1, 3]}
]
<strong>输出：</strong> [
    {"id": 1, "b": {"c": 84}, "v": [1, 3], "y": 48}
]
<b>解释：</b>具有 id 为 1 的对象合并在一起。对于键 "b" 和 "v" ，使用 arr2 中的值。由于键 "y" 只存在于 arr1 中，因此取 arr1 的值。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr1 和 arr2 都是有效的 JSON 数组</code></li>
	<li><code>在 arr1 和 arr2 中都有唯一的键值 id</code></li>
	<li><code>2 &lt;= JSON.stringify(arr1).length &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= JSON.stringify(arr2).length &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function join(arr1: any[], arr2: any[]): any[] {
    const d = new Map(arr1.map(x => [x.id, x]));
    arr2.forEach(x => {
        if (d.has(x.id)) {
            d.set(x.id, { ...d.get(x.id), ...x });
        } else {
            d.set(x.id, x);
        }
    });
    return [...d.values()].sort((a, b) => a.id - b.id);
}
```

<!-- tabs:end -->

<!-- end -->
