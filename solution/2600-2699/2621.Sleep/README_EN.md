---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2621.Sleep/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2621. Sleep](https://leetcode.com/problems/sleep)

[中文文档](/solution/2600-2699/2621.Sleep/README.md)

## Description

<!-- description:start -->

<p>Given&nbsp;a positive integer <code>millis</code>, write an asynchronous function that sleeps for <code>millis</code>&nbsp;milliseconds. It can resolve any value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> millis = 100
<strong>Output:</strong> 100
<strong>Explanation:</strong> It should return a promise that resolves after 100ms.
let t = Date.now();
sleep(100).then(() =&gt; {
  console.log(Date.now() - t); // 100
});
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> millis = 200
<strong>Output:</strong> 200
<strong>Explanation:</strong> It should return a promise that resolves after 200ms.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= millis &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

```ts
async function sleep(millis: number): Promise<void> {
    return new Promise(r => setTimeout(r, millis));
}

/**
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */
```

#### JavaScript

```js
/**
 * @param {number} millis
 * @return {Promise}
 */
async function sleep(millis) {
    return new Promise(r => setTimeout(r, millis));
}

/**
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
