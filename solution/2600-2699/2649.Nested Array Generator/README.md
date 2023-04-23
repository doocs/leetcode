# [2649. 嵌套数组生成器](https://leetcode.cn/problems/nested-array-generator)

[English Version](/solution/2600-2699/2649.Nested%20Array%20Generator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个整数的 <strong>多维数组</strong> ，请你返回一个生成器对象，按照&nbsp;<strong>中序遍历</strong> 的顺序逐个生成整数。</p>

<p><strong>多维数组</strong> 是一个递归数据结构，包含整数和其他 <strong>多维数组</strong>。</p>

<p><strong>中序遍历</strong> 是从左到右遍历每个数组，在遇到任何整数时生成它，遇到任何数组时递归应用 <strong>中序遍历</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>arr = [[[6]],[1,3],[]]
<b>输出：</b>[6,1,3]
<strong>解释：</strong>
const generator = inorderTraversal(arr);
generator.next().value; // 6
generator.next().value; // 1
generator.next().value; // 3
generator.next().done; // true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>arr = []
<b>输出：</b>[]
<b>解释：</b>输入的多维数组没有任何参数，所以生成器不需要生成任何值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.flat().length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr.flat()[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingDepth &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

```ts
type MultidimensionalArray = (MultidimensionalArray | number)[];

function* inorderTraversal(
    arr: MultidimensionalArray,
): Generator<number, void, unknown> {
    for (const e of arr) {
        if (Array.isArray(e)) {
            yield* inorderTraversal(e);
        } else {
            yield e;
        }
    }
}

/**
 * const gen = inorderTraversal([1, [2, 3]]);
 * gen.next().value; // 1
 * gen.next().value; // 2
 * gen.next().value; // 3
 */
```

### **...**

```

```

<!-- tabs:end -->
