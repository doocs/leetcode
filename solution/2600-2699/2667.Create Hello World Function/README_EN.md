---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2667.Create%20Hello%20World%20Function/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2667. Create Hello World Function](https://leetcode.com/problems/create-hello-world-function)

[中文文档](/solution/2600-2699/2667.Create%20Hello%20World%20Function/README.md)

## Description

<!-- description:start -->

Write a function&nbsp;<code>createHelloWorld</code>.&nbsp;It should return a new function that always returns&nbsp;<code>&quot;Hello World&quot;</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> args = []
<strong>Output:</strong> &quot;Hello World&quot;
<strong>Explanation:</strong>
const f = createHelloWorld();
f(); // &quot;Hello World&quot;

The function returned by createHelloWorld should always return &quot;Hello World&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> args = [{},null,42]
<strong>Output:</strong> &quot;Hello World&quot;
<strong>Explanation:</strong>
const f = createHelloWorld();
f({}, null, 42); // &quot;Hello World&quot;

Any arguments could be passed to the function but it should still always return &quot;Hello World&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= args.length &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

```ts
function createHelloWorld() {
    return function (...args): string {
        return 'Hello World';
    };
}

/**
 * const f = createHelloWorld();
 * f(); // "Hello World"
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
