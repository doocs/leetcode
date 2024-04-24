# [2722. Join Two Arrays by ID](https://leetcode.com/problems/join-two-arrays-by-id)

[中文文档](/solution/2700-2799/2722.Join%20Two%20Arrays%20by%20ID/README.md)

<!-- tags: -->

## Description

<p>Given two arrays <code>arr1</code> and <code>arr2</code>, return a new&nbsp;array <code>joinedArray</code>. All the objects in each&nbsp;of the two inputs arrays will contain an&nbsp;<code>id</code>&nbsp;field that has an integer value.&nbsp;</p>

<p><code>joinedArray</code>&nbsp;is an array formed by merging&nbsp;<code>arr1</code> and <code>arr2</code> based on&nbsp;their <code>id</code>&nbsp;key. The length of&nbsp;<code>joinedArray</code> should be the length of unique values of <code>id</code>. The returned array should be sorted in&nbsp;<strong>ascending</strong>&nbsp;order based on the <code>id</code>&nbsp;key.</p>

<p>If a given&nbsp;<code>id</code>&nbsp;exists in one array but not the other, the single object with that&nbsp;<code>id</code> should be included in the result array without modification.</p>

<p>If two objects share an <code>id</code>, their properties should be merged into a single&nbsp;object:</p>

<ul>
	<li>If a key only exists in one object, that single key-value pair should be included in the object.</li>
	<li>If a key is included in both objects, the value in the object from <code>arr2</code>&nbsp;should override the value from <code>arr1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
arr1 = [
&nbsp;   {&quot;id&quot;: 1, &quot;x&quot;: 1},
&nbsp;   {&quot;id&quot;: 2, &quot;x&quot;: 9}
], 
arr2 = [
    {&quot;id&quot;: 3, &quot;x&quot;: 5}
]
<strong>Output:</strong> 
[
&nbsp;   {&quot;id&quot;: 1, &quot;x&quot;: 1},
&nbsp;   {&quot;id&quot;: 2, &quot;x&quot;: 9},
    {&quot;id&quot;: 3, &quot;x&quot;: 5}
]
<strong>Explanation:</strong> There are no duplicate ids so arr1 is simply concatenated with arr2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
arr1 = [
    {&quot;id&quot;: 1, &quot;x&quot;: 2, &quot;y&quot;: 3},
    {&quot;id&quot;: 2, &quot;x&quot;: 3, &quot;y&quot;: 6}
], 
arr2 = [
    {&quot;id&quot;: 2, &quot;x&quot;: 10, &quot;y&quot;: 20},
    {&quot;id&quot;: 3, &quot;x&quot;: 0, &quot;y&quot;: 0}
]
<strong>Output:</strong> 
[
    {&quot;id&quot;: 1, &quot;x&quot;: 2, &quot;y&quot;: 3},
    {&quot;id&quot;: 2, &quot;x&quot;: 10, &quot;y&quot;: 20},
&nbsp;   {&quot;id&quot;: 3, &quot;x&quot;: 0, &quot;y&quot;: 0}
]
<strong>Explanation:</strong> The two objects with id=1 and id=3 are included in the result array without modifiction. The two objects with id=2 are merged together. The keys from arr2 override the values in arr1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
arr1 = [
    {&quot;id&quot;: 1, &quot;b&quot;: {&quot;b&quot;: 94},&quot;v&quot;: [4, 3], &quot;y&quot;: 48}
]
arr2 = [
    {&quot;id&quot;: 1, &quot;b&quot;: {&quot;c&quot;: 84}, &quot;v&quot;: [1, 3]}
]
<strong>Output:</strong> [
    {&quot;id&quot;: 1, &quot;b&quot;: {&quot;c&quot;: 84}, &quot;v&quot;: [1, 3], &quot;y&quot;: 48}
]
<strong>Explanation:</strong> The two objects with id=1 are merged together. For the keys &quot;b&quot; and &quot;v&quot; the values from arr2 are used. Since the key &quot;y&quot; only exists in arr1, that value is taken form arr1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>arr1</code> and <code>arr2</code> are valid JSON arrays</li>
	<li>Each object in <code>arr1</code> and <code>arr2</code> has a unique&nbsp;integer <code>id</code> key</li>
	<li><code>2 &lt;= JSON.stringify(arr1).length &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= JSON.stringify(arr2).length &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1

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
