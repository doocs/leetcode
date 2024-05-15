---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2675.Array%20of%20Objects%20to%20Matrix/README.md
---

# [2675. 将对象数组转换为矩阵 🔒](https://leetcode.cn/problems/array-of-objects-to-matrix)

[English Version](/solution/2600-2699/2675.Array%20of%20Objects%20to%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数，将对象数组&nbsp;<code>arr</code>&nbsp;转换为矩阵&nbsp;<code>m</code>&nbsp;。</p>

<p><code>arr</code>&nbsp;是一个由对象组成的数组或一个数组。数组中的每个项都可以包含深层嵌套的子数组和子对象。它还可以包含数字、字符串、布尔值和空值。</p>

<p>矩阵&nbsp;<code>m</code>&nbsp;的第一行应该是列名。如果没有嵌套，列名是对象中的唯一键。如果存在嵌套，列名是对象中相应路径，以点号&nbsp;<code>"."</code>&nbsp;分隔。</p>

<p>剩余的每一行对应&nbsp;<code>arr</code>&nbsp;中的一个对象。矩阵中的每个值对应对象中的一个值。如果给定对象在给定列中没有值，则应该包含空字符串 <code>""</code> 。</p>

<p>矩阵中的列应按 <strong>字典序升序</strong> 排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
arr = [
&nbsp; {"b": 1, "a": 2},
&nbsp; {"b": 3, "a": 4}
]
<strong>输出：</strong>
[
&nbsp; ["a", "b"],
&nbsp; [2, 1],
&nbsp; [4, 3]
]

<strong>解释：</strong>
两个对象中有两个唯一的列名："a"和"b"。 
"a"对应[2, 4]。 
"b"对应[1, 3]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>
arr = [
&nbsp; {"a": 1, "b": 2},
&nbsp; {"c": 3, "d": 4},
&nbsp; {}
]
<strong>输出：</strong>
[
&nbsp; ["a", "b", "c", "d"],
&nbsp; [1, 2, "", ""],
&nbsp; ["", "", 3, 4],
&nbsp; ["", "", "", ""]
]

<strong>解释：</strong>
有四个唯一的列名："a"、"b"、"c"、"d"。 
 第一个对象具有与"a"和"b"关联的值。 
第二个对象具有与"c"和"d"关联的值。 
第三个对象没有键，因此只是一行空字符串。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>
arr = [
&nbsp; {"a": {"b": 1, "c": 2}},
&nbsp; {"a": {"b": 3, "d": 4}}
]
<strong>输出：</strong>
[
&nbsp; ["a.b", "a.c", "a.d"],
&nbsp; [1, 2, ""],
&nbsp; [3, "", 4]
]

<strong>解释：</strong>
在这个例子中，对象是嵌套的。键表示每个值的完整路径，路径之间用句点分隔。 
有三个路径："a.b"、"a.c"、"a.d"。
</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<strong>输入：</strong>
arr = [
&nbsp; [{"a": null}],
&nbsp; [{"b": true}],
&nbsp; [{"c": "x"}]
]
<strong>输出： </strong>
[
&nbsp; ["0.a", "0.b", "0.c"],
&nbsp; [null, "", ""],
&nbsp; ["", true, ""],
&nbsp; ["", "", "x"]
]

<strong>解释：</strong>
数组也被视为具有索引为键的对象。 
每个数组只有一个元素，所以键是"0.a"、"0.b"和"0.c"。
</pre>

<p><strong class="example">示例 5：</strong></p>

<pre>
<strong>输入：</strong>
arr = [
  {},
&nbsp; {},
&nbsp; {},
]
<strong>输出：</strong>
[
&nbsp; [],
&nbsp; [],
&nbsp; [],
&nbsp; []
]

<strong>解释：</strong>
没有键，所以每一行都是一个空数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr</code> 是一个有效的 JSON 数组</li>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>unique keys &lt;= 1000</code></li>
</ul>

## 解法

### 方法一

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

<!-- end -->
