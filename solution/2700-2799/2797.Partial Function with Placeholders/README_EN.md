# [2797. Partial Function with Placeholders 🔒](https://leetcode.com/problems/partial-function-with-placeholders)

[中文文档](/solution/2700-2799/2797.Partial%20Function%20with%20Placeholders/README.md)

<!-- tags: -->

## Description

<p>Given a function <code>fn</code>&nbsp;and an array <code>args</code>, return a function <code>partialFn</code>.&nbsp;</p>

<p>Placeholders <code>&quot;_&quot;</code> in the&nbsp;<code>args</code>&nbsp;should be replaced with values from <code>restArgs</code> starting from index <code>0</code>. Any remaining values in the <code>restArgs</code>&nbsp;should be added at the end of the <code>args</code>.</p>

<p><code>partialFn</code>&nbsp;should return a result of <code>fn</code>.&nbsp;<code>fn</code> should be called with the elements of the modified&nbsp;<code>args</code>&nbsp;passed as separate arguments.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (...args) =&gt; args, args = [2,4,6], restArgs = [8,10]
<strong>Output:</strong> [2,4,6,8,10]
<strong>Explanation:</strong> 
const partialFn = partial(fn, args)
const result = partialFn(...restArgs) 
console.log(result) //&nbsp;[2,4,6,8,10]

There are no placeholders &quot;_&quot; in args therefore restArgs is just added at the end of args. Then the elements of the&nbsp;args&nbsp;are passed as separate arguments to fn, which returns passed arguments as an array.
</pre>

<strong class="example">Example 2:</strong>

<pre>
<strong>Input:</strong> fn = (...args) =&gt; args, args = [1,2,&quot;_&quot;,4,&quot;_&quot;,6], restArgs = [3,5]
<strong>Output:</strong> [1,2,3,4,5,6]
<strong>Explanation:</strong> 
const partialFn = partial(fn, args) 
const result = partialFn(...restArgs) 
console.log(result) //&nbsp;[1,2,3,4,5,6] 

Placeholders &quot;_&quot; are replaced with values from the restArgs. Then the elements of the&nbsp;args&nbsp;are passed as separate arguments to fn, which returns passed arguments as an array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fn = (a, b, c) =&gt; b + a - c, args = [&quot;_&quot;, 5], restArgs = [5, 20]
<strong>Output:</strong> -10
<strong>Explanation:</strong> 
const partialFn = partial(fn, args)
const result = partialFn(...restArgs)
console.log(result) //&nbsp;-10

Placeholder &quot;_&quot; is replaced with 5 and 20 is added at the end of args. Then the elements of the&nbsp;args&nbsp;are passed as separate arguments to fn, which returns -10 (5 + 5 - 20).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>fn</code> is a function</li>
	<li><code>args</code> and <code>restArgs</code> are valid JSON arrays</li>
	<li><code>1 &lt;= args.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;=&nbsp;restArgs.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= number of placeholders &lt;= restArgs.length</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
function partial(fn: Function, args: any[]): Function {
    return function (...restArgs) {
        let i = 0;
        for (let j = 0; j < args.length; ++j) {
            if (args[j] === '_') {
                args[j] = restArgs[i++];
            }
        }
        while (i < restArgs.length) {
            args.push(restArgs[i++]);
        }
        return fn(...args);
    };
}
```

```js
/**
 * @param {Function} fn
 * @param {Array} args
 * @return {Function}
 */
var partial = function (fn, args) {
    return function (...restArgs) {
        let i = 0;
        for (let j = 0; j < args.length; ++j) {
            if (args[j] === '_') {
                args[j] = restArgs[i++];
            }
        }
        while (i < restArgs.length) {
            args.push(restArgs[i++]);
        }
        return fn.apply(this, args);
    };
};
```

<!-- tabs:end -->

<!-- end -->
