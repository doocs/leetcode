---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2705.Compact%20Object/README.md
---

<!-- problem:start -->

# [2705. 精简对象](https://leetcode.cn/problems/compact-object)

[English Version](/solution/2700-2799/2705.Compact%20Object/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现给定一个对象或数组 <code>obj</code>，返回一个 <strong>精简对象</strong> 。</p>

<p><strong>精简对象</strong> 与原始对象相同，只是将包含 <strong>假</strong> 值的键移除。该操作适用于对象及其嵌套对象。数组被视为索引作为键的对象。当 <code>Boolean(value)</code> 返回 <code>false</code> 时，值被视为 <strong>假</strong> 值。</p>

<p>你可以假设 <code>obj</code> 是 <code>JSON.parse</code> 的输出结果。换句话说，它是有效的 JSON。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>obj = [null, 0, false, 1]
<b>输出：</b>[1]
<b>解释：</b>数组中的所有假值已被移除。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>obj = {"a": null, "b": [false, 1]}
<b>输出：</b>{"b": [1]}
<b>解释：</b>obj["a"] 和 obj["b"][0] 包含假值，因此被移除。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>obj = [null, 0, 5, [0], [false, 16]]
<b>输出：</b>[5, [], [16]]
<b>解释：</b>obj[0], obj[1], obj[3][0], 和 obj[4][0] 包含假值，因此被移除。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obj</code> 是一个有效的 JSON 对象</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

如果 `obj` 不是对象或为空，函数会原封不动地返回，因为无需检查非对象值中的键。

如果 `obj` 是一个数组，它会使用 `obj.filter(Boolean)` 过滤掉虚假值（如 `null`、`undefined`、`false`、0、""），然后使用 `map(compactObject)` 对每个元素递归调用 `compactObject`。这样可以确保嵌套数组也被压缩。

如果 `obj` 是一个对象，则会创建一个新的空对象 `compactedObj`。它会遍历 `obj` 的所有键，并对每个键在相应的值上递归调用 `compactObject`，然后将结果存储在值变量中。如果值是真实的（即不是虚假的），它就会将其赋值给具有相应键的压缩对象。

时间复杂度 $O(n)$, 空间复杂度 $O(n)$。

<!-- tabs:start -->

#### TypeScript

```ts
type Obj = Record<any, any>;

function compactObject(obj: Obj): Obj {
    if (Array.isArray(obj)) {
        const temp = [];
        for (const item of obj) {
            if (item) {
                if (typeof item === 'object') temp.push(compactObject(item));
                else temp.push(item);
            }
        }
        return temp;
    }
    for (const [key, value] of Object.entries(obj)) {
        if (!value) delete obj[key];
        else if (typeof value === 'object') obj[key] = compactObject(value);
    }
    return obj;
}
```

#### JavaScript

```js
var compactObject = function (obj) {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }

    if (Array.isArray(obj)) {
        return obj.filter(Boolean).map(compactObject);
    }

    const result = {};
    for (const key in obj) {
        const value = compactObject(obj[key]);
        if (Boolean(value)) {
            result[key] = value;
        }
    }
    return result;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
