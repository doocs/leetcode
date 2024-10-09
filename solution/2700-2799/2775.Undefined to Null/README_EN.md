---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2775.Undefined%20to%20Null/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2775. Undefined to Null 🔒](https://leetcode.com/problems/undefined-to-null)

[中文文档](/solution/2700-2799/2775.Undefined%20to%20Null/README.md)

## Description

<!-- description:start -->

<p>Given a deeply nested object or array <code>obj</code>, return the object <code>obj</code> with any <code>undefined</code> values replaced by <code>null</code>.</p>

<p><code>undefined</code> values are handled differently than <code>null</code> values when objects are converted to a JSON string using <code>JSON.stringify()</code>. This function helps ensure serialized data is free of unexpected errors.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: undefined, &quot;b&quot;: 3}
<strong>Output:</strong> {&quot;a&quot;: null, &quot;b&quot;: 3}
<strong>Explanation:</strong> The value for obj.a has been changed from undefined to null
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: undefined, &quot;b&quot;: [&quot;a&quot;, undefined]}
<strong>Output:</strong> {&quot;a&quot;: null,&quot;b&quot;: [&quot;a&quot;, null]}
<strong>Explanation:</strong> The values for obj.a and obj.b[1] have been changed from undefined to null
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj</code> is a valid JSON object or array</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

```ts
function undefinedToNull(obj: Record<any, any>): Record<any, any> {
    for (const key in obj) {
        if (typeof obj[key] === 'object') {
            obj[key] = undefinedToNull(obj[key]);
        }
        if (obj[key] === undefined) {
            obj[key] = null;
        }
    }
    return obj;
}

/**
 * undefinedToNull({"a": undefined, "b": 3}) // {"a": null, "b": 3}
 * undefinedToNull([undefined, undefined]) // [null, null]
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
