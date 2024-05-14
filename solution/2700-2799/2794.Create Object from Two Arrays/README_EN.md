# [2794. Create Object from Two Arrays 🔒](https://leetcode.com/problems/create-object-from-two-arrays)

[中文文档](/solution/2700-2799/2794.Create%20Object%20from%20Two%20Arrays/README.md)

<!-- tags: -->

<!-- difficulty:Easy -->

## Description

<p>Given two arrays <code>keysArr</code> and <code>valuesArr</code>, return a new object <code>obj</code>. Each key-value pair in&nbsp;<code>obj</code>&nbsp;should come from&nbsp;<code>keysArr[i]</code>&nbsp;and&nbsp;<code>valuesArr[i]</code>.</p>

<p>If a duplicate key exists at a previous index, that key-value should be excluded. In other words, only the first key should be added to the object.</p>

<p>If the key is not a string, it should be converted into a string by calling <code>String()</code>&nbsp;on it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> keysArr = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], valuesArr = [1, 2, 3]
<strong>Output:</strong> {&quot;a&quot;: 1, &quot;b&quot;: 2, &quot;c&quot;: 3}
<strong>Explanation:</strong> The keys &quot;a&quot;, &quot;b&quot;, and &quot;c&quot; are paired with the values 1, 2, and 3 respectively.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> keysArr = [&quot;1&quot;, 1, false], valuesArr = [4, 5, 6]
<strong>Output:</strong> {&quot;1&quot;: 4, &quot;false&quot;: 6}
<strong>Explanation:</strong> First, all the elements in keysArr are converted into strings. We can see there are two occurrences of &quot;1&quot;. The value associated with the first occurrence of &quot;1&quot; is used: 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> keysArr = [], valuesArr = []
<strong>Output:</strong> {}
<strong>Explanation:</strong> There are no keys so an empty object is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>keysArr</code> and <code>valuesArr</code> are valid JSON arrays</li>
	<li><code>2 &lt;= JSON.stringify(keysArr).length,&nbsp;JSON.stringify(valuesArr).length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>keysArr.length === valuesArr.length</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
function createObject(keysArr: any[], valuesArr: any[]): Record<string, any> {
    const ans: Record<string, any> = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = String(keysArr[i]);
        if (ans[k] === undefined) {
            ans[k] = valuesArr[i];
        }
    }
    return ans;
}
```

```js
/**
 * @param {Array} keysArr
 * @param {Array} valuesArr
 * @return {Object}
 */
var createObject = function (keysArr, valuesArr) {
    const ans = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = keysArr[i] + '';
        if (ans[k] === undefined) {
            ans[k] = valuesArr[i];
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
