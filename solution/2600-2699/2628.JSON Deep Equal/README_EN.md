---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2628.JSON%20Deep%20Equal/README_EN.md
---

<!-- problem:start -->

# [2628. JSON Deep Equal 🔒](https://leetcode.com/problems/json-deep-equal)

[中文文档](/solution/2600-2699/2628.JSON%20Deep%20Equal/README.md)

## Description

<p>Given two values&nbsp;<code>o1</code>&nbsp;and <code>o2</code>, return a boolean value indicating whether two values, <code>o1</code> and <code>o2</code>, are <strong>deeply equal</strong>.</p>

<p>For two values to be <strong>deeply equal</strong>, the following conditions must be met:</p>

<ul>
	<li>
	<p>If both values are primitive types,&nbsp;they are <strong>deeply equal</strong> if they pass the <code>===</code> equality check.</p>
	</li>
	<li>
	<p>If both values are arrays, they are <strong>deeply equal</strong> if they have the same elements in the same order, and each element is also <strong>deeply equal</strong> according to these conditions.</p>
	</li>
	<li>
	<p>If both values are objects, they are <strong>deeply equal</strong> if they have the same keys, and the associated values for each key are also <strong>deeply equal</strong> according to these conditions.</p>
	</li>
</ul>

<p>You may assume both values are the output of&nbsp;<code>JSON.parse</code>. In other words, they are valid JSON.</p>

<p>Please solve it without using lodash&#39;s&nbsp;<code>_.isEqual()</code>&nbsp;function</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;x&quot;:1,&quot;y&quot;:2}, o2 = {&quot;x&quot;:1,&quot;y&quot;:2}
<strong>Output:</strong> true
<strong>Explanation:</strong> The keys and values match exactly.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;y&quot;:2,&quot;x&quot;:1}, o2 = {&quot;x&quot;:1,&quot;y&quot;:2}
<strong>Output:</strong> true
<strong>Explanation:</strong> Although the keys are in a different order, they still match exactly.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;x&quot;:null,&quot;L&quot;:[1,2,3]}, o2 = {&quot;x&quot;:null,&quot;L&quot;:[&quot;1&quot;,&quot;2&quot;,&quot;3&quot;]}
<strong>Output:</strong> false
<strong>Explanation:</strong> The array of numbers is different from the array of strings.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> o1 = true, o2 = false
<strong>Output:</strong> false
<strong>Explanation:</strong> true !== false</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= JSON.stringify(o1).length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= JSON.stringify(o2).length &lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingDepth &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```ts
function areDeeplyEqual(o1: any, o2: any): boolean {
    if (o1 === null || typeof o1 !== 'object') {
        return o1 === o2;
    }
    if (typeof o1 !== typeof o2) {
        return false;
    }
    if (Array.isArray(o1) !== Array.isArray(o2)) {
        return false;
    }
    if (Array.isArray(o1)) {
        if (o1.length !== o2.length) {
            return false;
        }
        for (let i = 0; i < o1.length; i++) {
            if (!areDeeplyEqual(o1[i], o2[i])) {
                return false;
            }
        }
        return true;
    } else {
        const keys1 = Object.keys(o1);
        const keys2 = Object.keys(o2);
        if (keys1.length !== keys2.length) {
            return false;
        }
        for (const key of keys1) {
            if (!areDeeplyEqual(o1[key], o2[key])) {
                return false;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
