---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2755.Deep%20Merge%20of%20Two%20Objects/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2755. Deep Merge of Two Objects 🔒](https://leetcode.com/problems/deep-merge-of-two-objects)

[中文文档](/solution/2700-2799/2755.Deep%20Merge%20of%20Two%20Objects/README.md)

## Description

<!-- description:start -->

<p>Given two values&nbsp;<code>obj1</code> and <code>obj2</code>, return a&nbsp;<strong>deepmerged</strong>&nbsp;value.</p>

<p>Values should be <strong>deepmerged</strong> according to these rules:</p>

<ul>
	<li>If the two values are objects, the resulting object should have all the keys that exist on either object.&nbsp;If a key belongs to both objects, <strong>deepmerge</strong> the two associated values. Otherwise, add the key-value pair to the resulting object.</li>
	<li>If the two values are arrays, the resulting array should be the same length as the longer array.&nbsp;Apply the same logic as you would with objects, but treat the indices as keys.</li>
	<li>Otherwise the resulting value is&nbsp;<code>obj2</code>.</li>
</ul>

<p>You can assume&nbsp;<code>obj1</code> and <code>obj2</code>&nbsp;are the output of&nbsp;<code>JSON.parse()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj1 = {&quot;a&quot;: 1, &quot;c&quot;: 3}, obj2 = {&quot;a&quot;: 2, &quot;b&quot;: 2}
<strong>Output:</strong> {&quot;a&quot;: 2, &quot;c&quot;: 3, &quot;b&quot;: 2}
<strong>Explanation:</strong> The value of obj1[&quot;a&quot;] changed to 2 because if both objects have the same key and their value is not an array or object then we change the obj1 value to the obj2 value. Key &quot;b&quot; with value was added to obj1 as it doesn&#39;t exist in obj1. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj1 = [{}, 2, 3], obj2 = [[], 5]
<strong>Output:</strong> [[], 5, 3]
<strong>Explanation:</strong> result[0] = obj2[0] because obj1[0] and obj2[0] have different types. result[2] = obj1[2] because obj2[2] does not exist.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
obj1 = {&quot;a&quot;: 1, &quot;b&quot;: {&quot;c&quot;: [1 , [2, 7], 5], &quot;d&quot;: 2}}, 
obj2 = {&quot;a&quot;: 1, &quot;b&quot;: {&quot;c&quot;: [6, [6], [9]], &quot;e&quot;: 3}}
<strong>Output:</strong> {&quot;a&quot;: 1, &quot;b&quot;: {&quot;c&quot;: [6, [6, 7], [9]], &quot;d&quot;: 2, &quot;e&quot;: 3}}
<strong>Explanation:</strong> 
Arrays obj1[&quot;b&quot;][&quot;c&quot;] and obj2[&quot;b&quot;][&quot;c&quot;] have been merged in way that obj2 values overwrite obj1 values deeply only if they are not arrays or objects.
obj2[&quot;b&quot;][&quot;c&quot;] has key &quot;e&quot; that obj1 doesn&#39;t have so it&#39;s added to obj1.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> obj1 = true, obj2 = null
<strong>Output:</strong> null
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj1</code> and <code>obj2</code> are valid JSON values</li>
	<li><code>1 &lt;= JSON.stringify(obj1).length &lt;= 5&nbsp;* 10<sup>5</sup></code></li>
	<li><code>1 &lt;= JSON.stringify(obj2).length &lt;= 5&nbsp;* 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
