# [2822. Inversion of Object](https://leetcode.com/problems/inversion-of-object)

[中文文档](/solution/2800-2899/2822.Inversion%20of%20Object/README.md)

<!-- tags: -->

## Description

<p>Given an object or an array&nbsp;<code>obj</code>, return an inverted object or array&nbsp;<code>invertedObj</code>.</p>

<p>The <code>invertedObj</code> should have the keys of <code>obj</code> as values and the values of <code>obj</code> as keys.&nbsp;The indices of array&nbsp;should be treated as keys. It is guaranteed that the values in <code>obj</code> are only strings. The function should handle duplicates, meaning that if there are multiple keys in <code>obj</code> with the same value, the <code>invertedObj</code> should map the value to an array containing all corresponding keys.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: &quot;1&quot;, &quot;b&quot;: &quot;2&quot;, &quot;c&quot;: &quot;3&quot;, &quot;d&quot;: &quot;4&quot;}
<strong>Output:</strong> invertedObj = {&quot;1&quot;: &quot;a&quot;, &quot;2&quot;: &quot;b&quot;, &quot;3&quot;: &quot;c&quot;, &quot;4&quot;: &quot;d&quot;}
<strong>Explanation:</strong> The keys from obj become the values in invertedObj, and the values from obj become the keys in invertedObj.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: &quot;1&quot;, &quot;b&quot;: &quot;2&quot;, &quot;c&quot;: &quot;2&quot;, &quot;d&quot;: &quot;4&quot;}
<strong>Output:</strong> invertedObj = {&quot;1&quot;: &quot;a&quot;, &quot;2&quot;: [&quot;b&quot;, &quot;c&quot;], &quot;4&quot;: &quot;d&quot;}
<strong>Explanation:</strong> There are two keys in&nbsp;obj&nbsp;with the same value, the&nbsp;invertedObj mapped the value to an array containing all corresponding keys.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> obj = [&quot;1&quot;, &quot;2&quot;, &quot;3&quot;, &quot;4&quot;]
<strong>Output:</strong> invertedObj = {&quot;1&quot;: &quot;0&quot;, &quot;2&quot;: &quot;1&quot;, &quot;3&quot;: &quot;2&quot;, &quot;4&quot;: &quot;3&quot;}
<strong>Explanation:</strong> Arrays are also objects therefore array has changed to an object and the keys (indices) from obj become the values in invertedObj, and the values from obj become the keys in invertedObj.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj</code> is a valid JSON object or array</li>
	<li><code>typeof obj[key] === &quot;string&quot;</code></li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
function invertObject(obj: Record<any, any>): Record<any, any> {
    const ans: Record<any, any> = {};
    for (const key in obj) {
        if (ans.hasOwnProperty(obj[key])) {
            if (Array.isArray(ans[obj[key]])) {
                ans[obj[key]].push(key);
            } else {
                ans[obj[key]] = [ans[obj[key]], key];
            }
        } else {
            ans[obj[key]] = key;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
