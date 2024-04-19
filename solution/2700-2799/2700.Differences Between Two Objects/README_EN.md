# [2700. Differences Between Two Objects 🔒](https://leetcode.com/problems/differences-between-two-objects)

[中文文档](/solution/2700-2799/2700.Differences%20Between%20Two%20Objects/README.md)

<!-- tags: -->

## Description

<p>Write a function that accepts two deeply nested objects or arrays&nbsp;<code>obj1</code> and&nbsp;<code>obj2</code>&nbsp;and returns a new&nbsp;object representing their differences.</p>

<p>The function should compare the properties of the two objects and identify any changes.&nbsp;The returned object should only contains keys where the value is different from&nbsp;<code>obj1</code> to&nbsp;<code>obj2</code>.</p>

<p>For each changed key, the value should be represented as an&nbsp;array <code>[obj1 value, obj2&nbsp;value]</code>. Keys that exist in one object but not in the other should not be included in the returned object. When comparing two arrays, the indices of the arrays are considered to be their keys.&nbsp;The end result should be a deeply nested object where each leaf value is a difference array.</p>

<p>You may assume that both objects are the output of <code>JSON.parse</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {}
obj2 = {
&nbsp; &quot;a&quot;: 1, 
  &quot;b&quot;: 2
}
<strong>Output:</strong> {}
<strong>Explanation:</strong> There were no modifications made to obj1. New keys &quot;a&quot; and &quot;b&quot; appear in obj2, but keys that are added or removed should be ignored.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {
&nbsp; &quot;a&quot;: 1,
&nbsp; &quot;v&quot;: 3,
&nbsp; &quot;x&quot;: [],
&nbsp; &quot;z&quot;: {
&nbsp; &nbsp; &quot;a&quot;: null
&nbsp; }
}
obj2 = {
&nbsp; &quot;a&quot;: 2,
&nbsp; &quot;v&quot;: 4,
&nbsp; &quot;x&quot;: [],
&nbsp; &quot;z&quot;: {
&nbsp; &nbsp; &quot;a&quot;: 2
&nbsp; }
}
<strong>Output:</strong> 
{
&nbsp; &quot;a&quot;: [1, 2],
  &quot;v&quot;: [3, 4],
&nbsp; &quot;z&quot;: {
&nbsp;   &quot;a&quot;: [null, 2]
&nbsp; }
}
<strong>Explanation:</strong> The keys &quot;a&quot;, &quot;v&quot;, and &quot;z&quot; all had changes applied. &quot;a&quot; was changed from 1 to 2. &quot;v&quot; was changed from 3 to 4. &quot;z&quot; had a change applied to a child object. &quot;z.a&quot; was changed from null to 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {
&nbsp; &quot;a&quot;: 5, 
&nbsp; &quot;v&quot;: 6, 
&nbsp; &quot;z&quot;: [1, 2, 4, [2, 5, 7]]
}
obj2 = {
&nbsp; &quot;a&quot;: 5, 
&nbsp; &quot;v&quot;: 7, 
&nbsp; &quot;z&quot;: [1, 2, 3, [1]]
}
<strong>Output:</strong> 
{
&nbsp; &quot;v&quot;: [6, 7],
&nbsp; &quot;z&quot;: {
&nbsp;   &quot;2&quot;: [4, 3],
&nbsp;   &quot;3&quot;: {
&nbsp;     &quot;0&quot;: [2, 1]
&nbsp;   }
&nbsp; }
}
<strong>Explanation:</strong> In obj1 and obj2, the keys &quot;v&quot; and &quot;z&quot; have different assigned values. &quot;a&quot; is ignored because the value is unchanged. In the key &quot;z&quot;, there is a nested array. Arrays are treated like objects where the indices are keys. There were two alterations to the the array: z[2] and z[3][0]. z[0] and z[1] were unchanged and thus not included. z[3][1] and z[3][2] were removed and thus not included.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {
&nbsp; &quot;a&quot;: {&quot;b&quot;: 1}, 
}
obj2 = {
&nbsp; &quot;a&quot;: [5],
}
<strong>Output:</strong> 
{
  &quot;a&quot;: [{&quot;b&quot;: 1}, [5]]
}
<strong>Explanation:</strong> The key &quot;a&quot; exists in both objects. Since the two associated values have different types, they are placed in the difference array.</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {
&nbsp; &quot;a&quot;: [1, 2, {}], 
&nbsp; &quot;b&quot;: false
}
obj2 = { &nbsp; 
&nbsp; &quot;b&quot;: false,
&nbsp; &quot;a&quot;: [1, 2, {}]
}
<strong>Output:</strong> 
{}
<strong>Explanation:</strong> Apart from a different ordering of keys, the two objects are identical so an empty object is returned.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj1</code> and <code>obj2</code> are valid JSON objects or arrays</li>
	<li><code>2 &lt;= JSON.stringify(obj1).length &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= JSON.stringify(obj2).length &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

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

<!-- end -->
