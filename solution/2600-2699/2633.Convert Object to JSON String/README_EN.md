# [2633. Convert Object to JSON String](https://leetcode.com/problems/convert-object-to-json-string)

[中文文档](/solution/2600-2699/2633.Convert%20Object%20to%20JSON%20String/README.md)

## Description

<p>Given an object, return a valid JSON string of that object. You may assume the object only inludes strings, integers, arrays, objects, booleans, and null. The returned string should not include extra spaces. The order of keys should be the same as the order returned by&nbsp;<code>Object.keys()</code>.</p>

<p>Please solve it without using the built-in <code>JSON.stringify</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> object = {&quot;y&quot;:1,&quot;x&quot;:2}
<strong>Output:</strong> {&quot;y&quot;:1,&quot;x&quot;:2}
<strong>Explanation:</strong> 
Return the JSON representation.
Note that the order of keys should be the same as the order returned by Object.keys().</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> object = {&quot;a&quot;:&quot;str&quot;,&quot;b&quot;:-12,&quot;c&quot;:true,&quot;d&quot;:null}
<strong>Output:</strong> {&quot;a&quot;:&quot;str&quot;,&quot;b&quot;:-12,&quot;c&quot;:true,&quot;d&quot;:null}
<strong>Explanation:</strong>
The primitives of JSON are strings, numbers, booleans, and null.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> object = {&quot;key&quot;:{&quot;a&quot;:1,&quot;b&quot;:[{},null,&quot;Hello&quot;]}}
<strong>Output:</strong> {&quot;key&quot;:{&quot;a&quot;:1,&quot;b&quot;:[{},null,&quot;Hello&quot;]}}
<strong>Explanation:</strong>
Objects and arrays can include other objects and arrays.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> object = true
<strong>Output:</strong> true
<strong>Explanation:</strong>
Primitive types are valid inputs.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>object includes strings, integers, booleans, arrays, objects, and null</code></li>
	<li><code>1 &lt;= JSON.stringify(object).length &lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingLevel &lt;= 1000</code></li>
	<li><code>all strings will only contain alphanumeric characters</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
function jsonStringify(object: any): string {
    if (object === null) {
        return 'null';
    }
    if (typeof object === 'string') {
        return `"${object}"`;
    }
    if (typeof object === 'number' || typeof object === 'boolean') {
        return object.toString();
    }
    if (Array.isArray(object)) {
        return `[${object.map(jsonStringify).join(',')}]`;
    }
    if (typeof object === 'object') {
        return `{${Object.entries(object)
            .map(
                ([key, value]) =>
                    `${jsonStringify(key)}:${jsonStringify(value)}`,
            )
            .join(',')}}`;
    }
    return '';
}
```

### **...**

```

```

<!-- tabs:end -->
