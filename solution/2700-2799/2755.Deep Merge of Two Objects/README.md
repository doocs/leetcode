# [2755. 深度合并两个对象](https://leetcode.cn/problems/deep-merge-of-two-objects)

[English Version](/solution/2700-2799/2755.Deep%20Merge%20of%20Two%20Objects/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个值 <code>obj1</code> 和 <code>obj2</code>，返回一个 <strong>深度合并</strong> 的值。</p>

<p>你应该遵循以下规则进行值的 <strong>深度合并</strong>：</p>

<ul>
	<li>如果两个值都是对象，则结果对象应包含两个对象上存在的所有键。</li>
	<li>如果一个键同时存在于两个对象中，则 <strong>深度合并</strong> 两个关联的值。否则，将键值对添加到结果对象中。</li>
	<li>如果两个值都是数组，则结果数组的长度应与较长的数组相同。对于对象的合并逻辑，将索引视为键。</li>
	<li>否则，结果值为 <code>obj2</code>。</li>
</ul>

<p>你可以假设 <code>obj1</code> 和 <code>obj2</code> 是 <code>JSON.parse()</code> 的输出结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>obj1 = {"a": 1, "c": 3}, obj2 = {"a": 2, "b": 2}
<b>输出：</b>{"a": 2, "c": 3, "b": 2}
<b>解释：</b><code>obj1["a"]</code> 的值变为 2，因为如果两个对象具有相同的键且它们的值不是数组或对象，则将 <code>obj1</code> 的值更改为 <code>obj2</code> 的值。键 "b" 和其值被添加到 <code>obj1</code> 中，因为它在 <code>obj1</code> 中不存在。 
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>obj1 = [{}, 2, 3], obj2 = [[], 5]
<b>输出：</b>[[], 5, 3]
<b>解释：</b><code>result[0] = obj2[0]</code>，因为 <code>obj1[0]</code> 和 <code>obj2[0]</code> 类型不同。<code>result[2] = obj1[2]</code>，因为 <code>obj2[2]</code> 不存在。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
obj1 = {"a": 1, "b": {"c": [1 , [2, 7], 5], "d": 2}}, 
obj2 = {"a": 1, "b": {"c": [6, [6], [9]], "e": 3}}
<b>输出：</b>{"a": 1, "b": {"c": [6, [6, 7], [9]], "d": 2, "e": 3}}
<b>解释：</b>
数组 <code>obj1["b"]["c"]</code> 和 <code>obj2["b"]["c"]</code> 已合并，如果 <code>obj2</code> 的值不是数组或对象，则深度覆盖 <code>obj1</code> 的值。 <code>obj2["b"]["c"]</code> 有键 "e"，而 <code>obj1</code> 中没有，所以将其添加到 <code>obj1</code> 中。
</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<b>输入：</b>obj1 = true, obj2 = null
<b>输出：</b>null
</pre>

<p>&nbsp;</p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>obj1</code> 和 <code>obj2</code> 都是有效的 JSON 值</li>
	<li><code>1 &lt;= JSON.stringify(obj1).length &lt;= 5&nbsp;* 10<sup>5</sup></code></li>
	<li><code>1 &lt;= JSON.stringify(obj2).length &lt;= 5&nbsp;* 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function deepMerge(obj1: any, obj2: any): any {
    const isObj = (obj: any) => obj && typeof obj === 'object';
    const isArr = (obj: any) => Array.isArray(obj);
    if (!isObj(obj1) || !isObj(obj2)) {
        return obj2;
    }
    if (isArr(obj1) !== isArr(obj2)) {
        return obj2;
    }
    for (const key in obj2) {
        obj1[key] = deepMerge(obj1[key], obj2[key]);
    }
    return obj1;
}

/**
 * let obj1 = {"a": 1, "c": 3}, obj2 = {"a": 2, "b": 2};
 * deepMerge(obj1, obj2); // {"a": 2, "c": 3, "b": 2}
 */
```

<!-- tabs:end -->
