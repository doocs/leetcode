# [2666. 只允许一次函数调用](https://leetcode.cn/problems/allow-one-function-call)

[English Version](/solution/2600-2699/2666.Allow%20One%20Function%20Call/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个函数 <code>fn</code> ，它返回一个新的函数，返回的函数与原始函数完全相同，只不过它确保 <code>fn</code> 最多被调用一次。</p>

<ul>
	<li>第一次调用返回的函数时，它应该返回与 <code>fn</code> 相同的结果。</li>
	<li>第一次后的每次调用，它应该返回 <code>undefined</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>fn = (a,b,c) =&gt; (a + b + c), calls = [[1,2,3],[2,3,6]]
<b>输出：</b>[{"calls":1,"value":6}]
<strong>解释：</strong>
const onceFn = once(fn);
onceFn(1, 2, 3); // 6
onceFn(2, 3, 6); // undefined, fn 没有被调用
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (a,b,c) =&gt; (a * b * c), calls = [[5,7,4],[2,3,6],[4,6,8]]
<b>输出：</b>[{"calls":1,"value":140}]
<strong>解释：</strong>
const onceFn = once(fn);
onceFn(5, 7, 4); // 140
onceFn(2, 3, 6); // undefined, fn 没有被调用
onceFn(4, 6, 8); // undefined, fn 没有被调用
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>calls</code> 是一个有效的 JSON 数组</li>
	<li><code>1 &lt;= calls.length &lt;= 10</code></li>
	<li><code>1 &lt;= calls[i].length &lt;= 100</code></li>
	<li><code>2 &lt;= JSON.stringify(calls).length &lt;= 1000</code></li>
</ul>

## 解法

### 方法一

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
