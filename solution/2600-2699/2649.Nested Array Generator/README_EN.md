# [2649. Nested Array Generator](https://leetcode.com/problems/nested-array-generator)

[中文文档](/solution/2600-2699/2649.Nested%20Array%20Generator/README.md)

## Description

<p>Given a&nbsp;<strong>multi-dimensional array</strong> of integers, return&nbsp;a generator object which&nbsp;yields integers in the same order as&nbsp;<strong>inorder traversal</strong>.</p>

<p>A&nbsp;<strong>multi-dimensional array</strong>&nbsp;is a recursive data structure that contains both integers and other&nbsp;<strong>multi-dimensional arrays</strong>.</p>

<p><strong>inorder traversal</strong>&nbsp;iterates over&nbsp;each array from left to right, yielding any integers it encounters or applying&nbsp;<strong>inorder traversal</strong>&nbsp;to any arrays it encounters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [[[6]],[1,3],[]]
<strong>Output:</strong> [6,1,3]
<strong>Explanation:</strong>
const generator = inorderTraversal(arr);
generator.next().value; // 6
generator.next().value; // 1
generator.next().value; // 3
generator.next().done; // true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = []
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no integers so the generator doesn&#39;t yield anything.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.flat().length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr.flat()[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingDepth &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

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
