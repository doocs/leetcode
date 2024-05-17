---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2705.Compact%20Object/README_EN.md
---

<!-- problem:start -->

# [2705. Compact Object](https://leetcode.com/problems/compact-object)

[中文文档](/solution/2700-2799/2705.Compact%20Object/README.md)

## Description

<!-- description:start -->

<p>Given an object or array&nbsp;<code>obj</code>, return a <strong>compact object</strong>.</p>

<p>A <strong>compact object</strong>&nbsp;is the same as the original object, except with keys containing <strong>falsy</strong> values removed. This operation applies to the object and any nested objects. Arrays are considered objects where&nbsp;the indices are&nbsp;keys. A value is&nbsp;considered <strong>falsy</strong>&nbsp;when <code>Boolean(value)</code> returns <code>false</code>.</p>

<p>You may assume the&nbsp;<code>obj</code> is&nbsp;the output of&nbsp;<code>JSON.parse</code>. In other words, it is valid JSON.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj = [null, 0, false, 1]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> All falsy values have been removed from the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: null, &quot;b&quot;: [false, 1]}
<strong>Output:</strong> {&quot;b&quot;: [1]}
<strong>Explanation:</strong> obj[&quot;a&quot;] and obj[&quot;b&quot;][0] had falsy values and were removed.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> obj = [null, 0, 5, [0], [false, 16]]
<strong>Output:</strong> [5, [], [16]]
<strong>Explanation:</strong> obj[0], obj[1], obj[3][0], and obj[4][0] were falsy and removed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj</code> is a valid JSON object</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
