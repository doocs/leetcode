# [2700. 两个对象之间的差异](https://leetcode.cn/problems/differences-between-two-objects)

[English Version](/solution/2700-2799/2700.Differences%20Between%20Two%20Objects/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收两个深度嵌套的对象或数组 <code>obj1</code> 和 <code>obj2</code> ，并返回一个新对象表示它们之间差异。</p>

<p>该函数应该比较这两个对象的属性，并识别任何变化。返回的对象应仅包含从 <code>obj1</code> 到 <code>obj2</code> 的值不同的键。对于每个变化的键，值应表示为一个数组 <code>[obj1 value, obj2 value]</code> 。不存在于一个对象中但存在于另一个对象中的键不应包含在返回的对象中。在比较两个数组时，数组的索引被视为它们的键。最终结果应是一个深度嵌套的对象，其中每个叶子的值都是一个差异数组。</p>

<p>你可以假设这两个对象都是 <code>JSON.parse</code> 的输出结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
obj1 = {}
obj2 = {
&nbsp; "a": 1, 
  "b": 2
}
<b>输出：</b>{}
<b>解释：</b>obj1没有进行任何修改。obj2中出现了新的键 "a" 和 "b" ，但添加或删除的键应该被忽略。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>
obj1 = {
&nbsp; "a": 1,
&nbsp; "v": 3,
&nbsp; "x": [],
&nbsp; "z": {
&nbsp; &nbsp; "a": null
&nbsp; }
}
obj2 = {
&nbsp; "a": 2,
&nbsp; "v": 4,
&nbsp; "x": [],
&nbsp; "z": {
&nbsp; &nbsp; "a": 2
&nbsp; }
}
<b>输出：</b>
{
&nbsp; "a": [1, 2],
  "v": [3, 4],
&nbsp; "z": {
&nbsp;   "a": [null, 2]
&nbsp; }
}
<b>解释：</b>键 "a"、"v" 和 "z" 都有变化。"a" 从 1 变为 2，"v" 从 3 变为 4 ，"z" 的子对象 "a" 从 null 变为 2。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>
obj1 = {
&nbsp; "a": 5, 
&nbsp; "v": 6, 
&nbsp; "z": [1, 2, 4, [2, 5, 7]]
}
obj2 = {
&nbsp; "a": 5, 
&nbsp; "v": 7, 
&nbsp; "z": [1, 2, 3, [1]]
}
<b>输出：</b>
{
&nbsp; "v": [6, 7],
&nbsp; "z": {
&nbsp;   "2": [4, 3],
&nbsp;   "3": {
&nbsp;     "0": [2, 1]
&nbsp;   }
&nbsp; }
}
<b>解释：</b>在 obj1 和 obj2 中，键 "v" 和 "z" 的值不同。"a" 被忽略，因为值没有变化。在键 "z" 中，有一个嵌套的数组。数组被视为对象，其中索引被视为键。数组发生了两处变化：z[2] 和 z[3][0]。z[0] 和 z[1] 没有变化，因此没有包含在结果中。z[3][1] 和 z[3][2] 被删除，因此也没有包含在结果中。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>
obj1 = {
&nbsp; "a": {"b": 1}, 
}
obj2 = {
&nbsp; "a": [5],
}
<b>输出：</b>
{
  "a": [{"b": 1}, [5]]
}
<b>解释：</b>键 "a" 在两个对象中都存在。但由于两个相关值具有不同的类型，所以它们被放置在差异数组中。</pre>

<p><strong>示例 5：</strong></p>

<pre>
<b>输入：</b>
obj1 = {
&nbsp; "a": [1, 2, {}], 
&nbsp; "b": false
}
obj2 = { &nbsp; 
&nbsp; "b": false,
&nbsp; "a": [1, 2, {}]
}
<b>输出：</b>
{}
<b>解释：</b>除了键的顺序不同之外，两个对象是相同的，因此返回一个空对象。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obj1</code> 和 <code>obj2</code> 都是有效的 JSON 对象或数组</li>
	<li><code>2 &lt;= JSON.stringify(obj1).length &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= JSON.stringify(obj2).length &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function objDiff(obj1: any, obj2: any): any {
    if (type(obj1) !== type(obj2)) return [obj1, obj2];
    if (!isObject(obj1)) return obj1 === obj2 ? {} : [obj1, obj2];
    const diff: Record<string, unknown> = {};
    const sameKeys = Object.keys(obj1).filter(key => key in obj2);
    sameKeys.forEach(key => {
        const subDiff = objDiff(obj1[key], obj2[key]);
        if (Object.keys(subDiff).length) diff[key] = subDiff;
    });
    return diff;
}

function type(obj: unknown): string {
    return Object.prototype.toString.call(obj).slice(8, -1);
}

function isObject(obj: unknown): obj is Record<string, unknown> {
    return typeof obj === 'object' && obj !== null;
}
```

<!-- tabs:end -->
