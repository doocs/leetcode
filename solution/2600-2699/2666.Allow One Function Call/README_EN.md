# [2666. Allow One Function Call](https://leetcode.com/problems/allow-one-function-call)

[中文文档](/solution/2600-2699/2666.Allow%20One%20Function%20Call/README.md)

## Description

<p>Given a function <code>fn</code>, return a new function that is identical to the original function except that it ensures&nbsp;<code>fn</code>&nbsp;is&nbsp;called at most once.</p>

<ul>
	<li>The first time the returned function is called, it should return the same result as&nbsp;<code>fn</code>.</li>
	<li>Every subsequent time it is called, it should return&nbsp;<code>undefined</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (a,b,c) =&gt; (a + b + c), calls = [[1,2,3],[2,3,6]]
<strong>Output:</strong> [{&quot;calls&quot;:1,&quot;value&quot;:6}]
<strong>Explanation:</strong>
const onceFn = once(fn);
onceFn(1, 2, 3); // 6
onceFn(2, 3, 6); // undefined, fn was not called
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fn = (a,b,c) =&gt; (a * b * c), calls = [[5,7,4],[2,3,6],[4,6,8]]
<strong>Output:</strong> [{&quot;calls&quot;:1,&quot;value&quot;:140}]
<strong>Explanation:</strong>
const onceFn = once(fn);
onceFn(5, 7, 4); // 140
onceFn(2, 3, 6); // undefined, fn was not called
onceFn(4, 6, 8); // undefined, fn was not called
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>calls</code> is a valid JSON array</li>
	<li><code>1 &lt;= calls.length &lt;= 10</code></li>
	<li><code>1 &lt;= calls[i].length &lt;= 100</code></li>
	<li><code>2 &lt;= JSON.stringify(calls).length &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
function once<T extends (...args: any[]) => any>(
    fn: T,
): (...args: Parameters<T>) => ReturnType<T> | undefined {
    let called = false;
    return function (...args) {
        if (!called) {
            called = true;
            return fn(...args);
        }
    };
}

/**
 * let fn = (a,b,c) => (a + b + c)
 * let onceFn = once(fn)
 *
 * onceFn(1,2,3); // 6
 * onceFn(2,3,6); // returns undefined without calling fn
 */
```

<!-- tabs:end -->

<!-- end -->
