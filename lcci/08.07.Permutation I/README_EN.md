# [08.07. Permutation I](https://leetcode-cn.com/problems/permutation-i-lcci)

[中文文档](/lcci/08.07.Permutation%20I/README.md)

## Description

<p>Write a method to compute all permutations of a string of unique characters.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;qwe&quot;

<strong> Output</strong>: [&quot;qwe&quot;, &quot;qew&quot;, &quot;wqe&quot;, &quot;weq&quot;, &quot;ewq&quot;, &quot;eqw&quot;]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;ab&quot;

<strong> Output</strong>: [&quot;ab&quot;, &quot;ba&quot;]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>All charaters are English letters.</li>
	<li><code>1 &lt;= S.length &lt;= 9</code></li>
</ol>

## Solutions

Backtracking

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **JavaSript**

```js
/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    let res = [];
    let arr = [...S];
    let prev = [];
    let record = new Array(S.length).fill(false);
    dfs(arr, 0, prev, record, res);
    return res;
};

function dfs(arr, depth, prev, record, res) {
    if (depth == arr.length) {
        res.push(prev.join(""));
        return;
    }
    for (let i = 0; i < arr.length; i++) {
        if (record[i]) {
            continue;
        }
        prev.push(arr[i]);
        record[i] = true;
        dfs(arr, depth + 1, prev, record, res);
        prev.pop();
        record[i] = false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
