---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2675.Array%20of%20Objects%20to%20Matrix/README_EN.md
---

<!-- problem:start -->

# [2675. Array of Objects to Matrix 🔒](https://leetcode.com/problems/array-of-objects-to-matrix)

[中文文档](/solution/2600-2699/2675.Array%20of%20Objects%20to%20Matrix/README.md)

## Description

<p>Write a function that converts an array of objects&nbsp;<code>arr</code> into a matrix <code>m</code>.</p>

<p><code>arr</code>&nbsp;is an array of objects or arrays. Each item in the array can be deeply nested with child arrays and child objects. It can also contain numbers, strings, booleans, and&nbsp;null values.</p>

<p>The first row <code>m</code>&nbsp;should be the column names. If there is no nesting, the column names are the unique keys within the objects. If there is nesting, the column names&nbsp;are the respective paths in the object separated by <code>&quot;.&quot;</code>.</p>

<p>Each of the remaining rows corresponds to an object in&nbsp;<code>arr</code>. Each value in the matrix corresponds to a value in an object. If a given object doesn&#39;t contain a value for a given column, the cell should contain an empty string&nbsp;<code>&quot;&quot;</code>.</p>

<p>The columns in the matrix should be in <strong>lexographically ascending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [
&nbsp; {&quot;b&quot;: 1, &quot;a&quot;: 2},
&nbsp; {&quot;b&quot;: 3, &quot;a&quot;: 4}
]
<strong>Output:</strong> 
[
&nbsp; [&quot;a&quot;, &quot;b&quot;],
&nbsp; [2, 1],
&nbsp; [4, 3]
]

<strong>Explanation:</strong>
There are two unique column names in the two objects: &quot;a&quot; and &quot;b&quot;.
&quot;a&quot; corresponds with [2, 4].
&quot;b&quot; coresponds with [1, 3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [
&nbsp; {&quot;a&quot;: 1, &quot;b&quot;: 2},
&nbsp; {&quot;c&quot;: 3, &quot;d&quot;: 4},
&nbsp; {}
]
<strong>Output:</strong> 
[
&nbsp; [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;d&quot;],
&nbsp; [1, 2, &quot;&quot;, &quot;&quot;],
&nbsp; [&quot;&quot;, &quot;&quot;, 3, 4],
&nbsp; [&quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;]
]

<strong>Explanation:</strong>
There are 4 unique column names: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;d&quot;.
The first object has values associated with &quot;a&quot; and &quot;b&quot;.
The second object has values associated with &quot;c&quot; and &quot;d&quot;.
The third object has no keys, so it is just a row of empty strings.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [
&nbsp; {&quot;a&quot;: {&quot;b&quot;: 1, &quot;c&quot;: 2}},
&nbsp; {&quot;a&quot;: {&quot;b&quot;: 3, &quot;d&quot;: 4}}
]
<strong>Output:</strong> 
[
&nbsp; [&quot;a.b&quot;, &quot;a.c&quot;, &quot;a.d&quot;],
&nbsp; [1, 2, &quot;&quot;],
&nbsp; [3, &quot;&quot;, 4]
]

<strong>Explanation:</strong>
In this example, the objects are nested. The keys represent the full path to each value separated by periods.
There are three paths: &quot;a.b&quot;, &quot;a.c&quot;, &quot;a.d&quot;.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [
&nbsp; [{&quot;a&quot;: null}],
&nbsp; [{&quot;b&quot;: true}],
&nbsp; [{&quot;c&quot;: &quot;x&quot;}]
]
<strong>Output:</strong> 
[
&nbsp; [&quot;0.a&quot;, &quot;0.b&quot;, &quot;0.c&quot;],
&nbsp; [null, &quot;&quot;, &quot;&quot;],
&nbsp; [&quot;&quot;, true, &quot;&quot;],
&nbsp; [&quot;&quot;, &quot;&quot;, &quot;x&quot;]
]

<strong>Explanation:</strong>
Arrays are also considered objects with their keys being their indices.
Each array has one element so the keys are &quot;0.a&quot;, &quot;0.b&quot;, and &quot;0.c&quot;.
</pre>

<p><strong class="example">Example 5:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [
  {},
&nbsp; {},
&nbsp; {},
]
<strong>Output:</strong> 
[
&nbsp; [],
&nbsp; [],
&nbsp; [],
&nbsp; []
]

<strong>Explanation:</strong>
There are no keys so every row is an empty array.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>arr</code> is a valid JSON array</li>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>unique keys &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```ts
function jsonToMatrix(arr: any[]): (string | number | boolean | null)[] {
    const dfs = (key: string, obj: any) => {
        if (
            typeof obj === 'number' ||
            typeof obj === 'string' ||
            typeof obj === 'boolean' ||
            obj === null
        ) {
            return { [key]: obj };
        }
        const res: any[] = [];
        for (const [k, v] of Object.entries(obj)) {
            const newKey = key ? `${key}.${k}` : `${k}`;
            res.push(dfs(newKey, v));
        }
        return res.flat();
    };

    const kv = arr.map(obj => dfs('', obj));
    const keys = [
        ...new Set(
            kv
                .flat()
                .map(obj => Object.keys(obj))
                .flat(),
        ),
    ].sort();
    const ans: any[] = [keys];
    for (const row of kv) {
        const newRow: any[] = [];
        for (const key of keys) {
            const v = row.find(r => r.hasOwnProperty(key))?.[key];
            newRow.push(v === undefined ? '' : v);
        }
        ans.push(newRow);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
