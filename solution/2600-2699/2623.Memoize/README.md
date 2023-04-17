# [2623. 记忆函数](https://leetcode.cn/problems/memoize)

[English Version](/solution/2600-2699/2623.Memoize/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收另一个函数作为输入，并返回该函数的 <strong>记忆化</strong> 后的结果。</p>

<p><strong>记忆函数</strong> 是一个对于相同的输入永远不会被调用两次的函数。相反，它将返回一个缓存值。</p>

<p>你可以假设有 <strong>3</strong> 个可能的输入函数：<code>sum</code> 、<code>fib</code> 和 <code>factorial</code> 。</p>

<ul>
	<li>&nbsp;<code>sum</code> 接收两个整型参数 <code>a</code> 和 <code>b</code> ，并返回 <code>a + b</code> 。</li>
	<li>&nbsp;<code>fib</code> 接收一个整型参数&nbsp;<code>n</code> ，如果 <code>n &lt;= 1</code> 则返回 <code>1</code>，否则返回 <code>fib (n - 1) + fib (n - 2)</code>。</li>
	<li>&nbsp;<code>factorial</code> 接收一个整型参数 <code>n</code> ，如果 <code>n &lt;= 1</code> 则返回&nbsp;&nbsp;<code>1</code>&nbsp;，否则返回 <code>factorial(n - 1) * n</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
"sum"
["call","call","getCallCount","call","getCallCount"]
[[2,2],[2,2],[],[1,2],[]]
<strong>输出：</strong>
[4,4,1,3,2]

<strong>解释：</strong>
const sum = (a, b) =&gt; a + b;
const memoizedSum = memoize(sum);
memoizedSum (2, 2);// 返回 4。sum() 被调用，因为之前没有使用参数 (2, 2) 调用过。
memoizedSum (2, 2);// 返回 4。没有调用 sum()，因为前面有相同的输入。
//总调用数： 1
memoizedSum(1、2);// 返回 3。sum() 被调用，因为之前没有使用参数 (1, 2) 调用过。
//总调用数： 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：
</strong>"factorial"
["call","call","call","getCallCount","call","getCallCount"]
[[2],[3],[2],[],[3],[]]
<strong>输出：</strong>
[2,6,2,2,6,2]

<strong>解释：</strong>
const factorial = (n) =&gt; (n &lt;= 1) ? 1 : (n * factorial(n - 1));
const memoFactorial = memoize(factorial);
memoFactorial(2); // 返回 2。
memoFactorial(3); // 返回 6。
memoFactorial(2); // 返回 2。 没有调用 factorial()，因为前面有相同的输入。
// 总调用数：2
memoFactorial(3); // 返回 6。 没有调用 factorial()，因为前面有相同的输入。
// 总调用数：2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：
</strong>"fib"
["call","getCallCount"]
[[5],[]]
<strong>输出：</strong>
[8,1]

<strong>解释：
</strong>fib(5) = 8
// 总调用数：1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>at most 10<sup>5</sup>&nbsp;function calls</code></li>
	<li><code>at most 10<sup>5</sup>&nbsp;attempts to access callCount</code></li>
	<li><code>input function is sum, fib, or factorial</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以使用哈希表来存储函数的参数和返回值，当再次调用函数时，如果参数已经存在于哈希表中，则直接返回哈希表中的值，否则调用函数并将返回值存入哈希表中。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 为函数的参数个数。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
type Fn = (...params: any) => any;

function memoize(fn: Fn): Fn {
    const cache: Record<any, any> = {};

    return function (...args) {
        if (args in cache) {
            return cache[args];
        }
        const result = fn(...args);
        cache[args] = result;
        return result;
    };
}

/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */
```

### **...**

```

```

<!-- tabs:end -->
