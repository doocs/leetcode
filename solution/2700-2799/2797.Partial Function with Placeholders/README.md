# [2797. 带有占位符的部分函数](https://leetcode.cn/problems/partial-function-with-placeholders)

[English Version](/solution/2700-2799/2797.Partial%20Function%20with%20Placeholders/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定函数 <code>fn</code> 和数组 <code>args</code>，返回一个函数 <code>partialFn</code>。</p>

<p><code>args</code> 中的占位符 <code>"_"</code> 需要用&nbsp;<code>restArgs</code> 中索引从&nbsp;<code>0</code> 开始的值替换。 <code>restArgs</code> 中剩余的值则添加到 <code>args</code> 的末尾。</p>

<p><code>partialFn</code> 应该返回 <code>fn</code> 的结果。<code>fn</code> 应该使用修改后的 <code>args</code> 的元素作为单独的参数调用。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>fn = (...args) =&gt; args, args = [2,4,6], restArgs = [8,10]
<strong>输出：</strong>[2,4,6,8,10]
<strong>解释：</strong>
const partialFn = partial(fn, args)
const result = partialFn(...restArgs) 
console.log(result) //&nbsp;[2,4,6,8,10]

args 中没有占位符 "_"，因此 restArgs 只是添加到 args 的末尾。然后将 args 的元素作为单独的参数传递给 fn，fn 返回传递的参数作为数组。
</pre>

<strong class="example">示例 2：</strong>

<pre>
<strong>输入：</strong>fn = (...args) =&gt; args, args = [1,2,"_",4,"_",6], restArgs = [3,5]
<strong>输出：</strong>[1,2,3,4,5,6]
<strong>解释：</strong>
const partialFn = partial(fn, args) 
const result = partialFn(...restArgs) 
console.log(result) //&nbsp;[1,2,3,4,5,6] 

占位符 "_" 被 restArgs 中的值替换。然后将 args 的元素作为单独的参数传递给 fn，fn 返回传递的参数作为数组。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>fn = (a, b, c) =&gt; b + a - c, args = ["_", 5], restArgs = [5, 20]
<strong>输出：</strong>-10
<strong>解释：
</strong>const partialFn = partial(fn, args)
const result = partialFn(...restArgs)
console.log(result) //&nbsp;-10

占位符 "_" 被替换为 5，并将 20 添加到 args 的末尾。然后将 args 的元素作为单独的参数传递给 fn，fn 返回 -10（5 + 5 - 20）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>fn</code> 是一个函数</li>
	<li><code>args</code> 和 <code>restArgs</code> 都是有效的 JSON 数组</li>
	<li><code>1 &lt;= args.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;=&nbsp;restArgs.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= number of placeholders &lt;= restArgs.length</code></li>
</ul>

## 解法

### 方法一

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
