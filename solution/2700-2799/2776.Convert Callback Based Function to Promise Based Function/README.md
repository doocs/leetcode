# [2776. 转换回调函数为 Promise 函数](https://leetcode.cn/problems/convert-callback-based-function-to-promise-based-function)

[English Version](/solution/2700-2799/2776.Convert%20Callback%20Based%20Function%20to%20Promise%20Based%20Function/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数，接受另一个函数 <code>fn</code> ，并将基于回调函数的函数转换为基于 Promise 的函数。</p>

<p><code>promisify</code> 函数接受一个函数 <code>fn</code> ，<code>fn</code> 将回调函数作为其第一个参数，并且还可以接受其他额外的参数。</p>

<p><code>promisfy</code> 返回一个新函数，新函数会返回一个 Promise 对象。当回调函数被成功调用时，新函数返回的 Promise 对象应该使用原始函数的结果进行解析；当回调函数被调用出现错误时，返回的 Promise 对象应该被拒绝并携带错误信息。最终返回的基于 Promise 的函数应该接受额外的参数作为输入。</p>

<p>以下是一个可以传递给 <code>promisify</code> 的函数示例：</p>

<pre>
function sum(callback, a, b) {
  if (a &lt; 0 || b &lt; 0) {
&nbsp;   const err = Error('a and b must be positive');
    callback(undefined, err);
&nbsp; } else {
    callback(a + b);
&nbsp; }
}
</pre>

<p>这是基于 Promise 的等效代码：</p>

<pre>
async function sum(a, b) {
  if (a &lt; 0 || b &lt; 0) {
    throw Error('a and b must be positive');
&nbsp; } else {
    return a + b;
&nbsp; }
}
</pre>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
fn = (callback, a, b, c) =&gt; {
  return callback(a * b * c);
}
args = [1, 2, 3]
<b>输出：</b>{"resolved": 6}
<b>解释：</b>
const asyncFunc = promisify(fn);
asyncFunc(1, 2, 3).then(console.log); // 6

fn 以回调函数作为第一个参数和 args 作为其余参数进行调用。当使用 (1, 2, 3) 调用时，基于 Promise 的 fn 将解析为值 6。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
fn = (callback, a, b, c) =&gt; {
&nbsp; callback(a * b * c, "Promise Rejected");
}
args = [4, 5, 6]
<b>输出：</b>{"rejected": "Promise Rejected"}
<b>解释：</b>
const asyncFunc = promisify(fn);
asyncFunc(4, 5, 6).catch(console.log); // "Promise Rejected"

fn 以回调函数作为第一个参数和 args 作为其余参数进行调用。在回调函数的第二个参数中，接受一个错误消息，因此当调用 fn 时，Promise 被拒绝并携带回调函数中提供的错误消息。请注意，不管将什么作为回调函数的第一个参数传递都无关紧要。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= args.length &lt;= 100</code></li>
	<li><code>0 &lt;= args[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
type CallbackFn = (next: (data: number, error: string) => void, ...args: number[]) => void;
type Promisified = (...args: number[]) => Promise<number>;

function promisify(fn: CallbackFn): Promisified {
    return async function (...args) {
        return new Promise((resolve, reject) => {
            fn((data, error) => {
                if (error) {
                    reject(error);
                } else {
                    resolve(data);
                }
            }, ...args);
        });
    };
}

/**
 * const asyncFunc = promisify(callback => callback(42));
 * asyncFunc().then(console.log); // 42
 */
```

<!-- tabs:end -->

<!-- end -->
