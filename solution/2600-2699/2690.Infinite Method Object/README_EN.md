# [2690. Infinite Method Object](https://leetcode.com/problems/infinite-method-object)

[中文文档](/solution/2600-2699/2690.Infinite%20Method%20Object/README.md)

<!-- tags: -->

## Description

<p>Write a function that&nbsp;returns an&nbsp;<strong>infinite-method</strong><strong>&nbsp;object</strong>.</p>

<p>An&nbsp;<strong>infinite-method</strong><strong>&nbsp;object</strong>&nbsp;is defined as an object that allows you to call any method and it will always return the name of the method.</p>

<p>For example, if you execute&nbsp;<code>obj.abc123()</code>, it will return&nbsp;<code>&quot;abc123&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> method = &quot;abc123&quot;
<strong>Output:</strong> &quot;abc123&quot;
<strong>Explanation:</strong>
const obj = createInfiniteObject();
obj[&#39;abc123&#39;](); // &quot;abc123&quot;
The returned string should always match the method name.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> method = &quot;.-qw73n|^2It&quot;
<strong>Output:</strong> &quot;.-qw73n|^2It&quot;
<strong>Explanation:</strong> The returned string should always match the method name.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= method.length &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
function createInfiniteObject(): Record<string, () => string> {
    return new Proxy(
        {},
        {
            get: (_, prop) => () => prop.toString(),
        },
    );
}

/**
 * const obj = createInfiniteObject();
 * obj['abc123'](); // "abc123"
 */
```

<!-- tabs:end -->

<!-- end -->
