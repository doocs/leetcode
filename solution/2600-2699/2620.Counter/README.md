# [2620. 计数器](https://leetcode.cn/problems/counter)

[English Version](/solution/2600-2699/2620.Counter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写并返回一个&nbsp;<strong>计数器&nbsp;</strong>函数，它接收一个整型参数 n 。这个&nbsp;<strong>计数器&nbsp;</strong>函数最初返回 n，每次调用它时返回前一个值加 1 的值 ( <code>n</code> ,&nbsp; <code>n + 1</code> ,&nbsp; <code>n + 2</code> ，等等)。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
n = 10 
["call","call","call"]
<b>输出：</b>[10,11,12]
<strong>解释：
</strong>counter() = 10 // 第一次调用 counter()，返回 n。
counter() = 11 // 返回上次调用的值加 1。
counter() = 12 // 返回上次调用的值加 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>
n = -2
["call","call","call","call","call"]
<b>输出：</b>[-2,-1,0,1,2]
<b>解释：</b>counter() 最初返回 -2。然后在每个后续调用后增加 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-1000<sup>&nbsp;</sup>&lt;= n &lt;= 1000</code></li>
	<li><code>最多对 counter() 进行 1000 次调用</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function createCounter(n: number): () => number {
    let i = n;
    return function () {
        return i++;
    };
}

/**
 * const counter = createCounter(10)
 * counter() // 10
 * counter() // 11
 * counter() // 12
 */
```

### **...**

```

```

<!-- tabs:end -->
