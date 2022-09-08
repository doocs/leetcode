# [08.08. Permutation II](https://leetcode.cn/problems/permutation-ii-lcci)

[中文文档](/lcci/08.08.Permutation%20II/README.md)

## Description

<p>Write a method to compute all permutations of a string whose charac&shy; ters are not necessarily unique. The list of permutations should not have duplicates.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: S = &quot;qqe&quot;

<strong> Output</strong>: [&quot;eqq&quot;,&quot;qeq&quot;,&quot;qqe&quot;]

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: S = &quot;ab&quot;

<strong> Output</strong>: [&quot;ab&quot;, &quot;ba&quot;]

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li>All characters are English letters.</li>
	<li><code>1 &lt;= S.length &lt;= 9</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **JavaScript**

```js
/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    let res = [];
    let arr = [...S];
    arr.sort();
    let prev = [];
    let record = new Array(S.length).fill(false);
    dfs(arr, 0, prev, record, res);
    return res;
};
function dfs(arr, depth, prev, record, res) {
    if (depth == arr.length) {
        res.push(prev.join(''));
        return;
    }
    for (let i = 0; i < arr.length; i++) {
        if (record[i]) {
            continue;
        }
        // 剪枝
        if (i > 0 && arr[i] == arr[i - 1] && record[i - 1]) {
            continue;
        }
        prev.push(arr[i]);
        record[i] = true;
        dfs(arr, depth + 1, prev, record, res);
        // 回溯
        prev.pop();
        record[i] = false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
