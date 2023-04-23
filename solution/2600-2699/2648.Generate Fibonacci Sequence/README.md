# [2648. 生成斐波那契数列](https://leetcode.cn/problems/generate-fibonacci-sequence)

[English Version](/solution/2600-2699/2648.Generate%20Fibonacci%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个生成器函数，并返回一个可以生成 <strong>斐波那契数列</strong> 的生成器对象。</p>

<p><strong>斐波那契数列</strong> 的递推公式为 <code>X<sub>n</sub>&nbsp;= X<sub>n-1</sub>&nbsp;+ X<sub>n-2</sub></code> 。</p>

<p>这个数列的前几个数字是 <code>0, 1, 1, 2, 3, 5, 8, 13</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>callCount = 5
<b>输出：</b>[0,1,1,2,3]
<strong>解释：</strong>
const gen = fibGenerator();
gen.next().value; // 0
gen.next().value; // 1
gen.next().value; // 1
gen.next().value; // 2
gen.next().value; // 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>callCount = 0
<strong>输出：</strong>[]
<b>解释：</b>gen.next() 永远不会被调用，所以什么也不会输出
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= callCount &lt;= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

```ts
function* fibGenerator(): Generator<number, any, number> {
    let a = 0;
    let b = 1;
    while (true) {
        yield a;
        [a, b] = [b, a + b];
    }
}

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */
```

### **...**

```

```

<!-- tabs:end -->
