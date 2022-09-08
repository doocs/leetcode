# [面试题 08.08. 有重复字符串的排列组合](https://leetcode.cn/problems/permutation-ii-lcci)

[English Version](/lcci/08.08.Permutation%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：S = &quot;qqe&quot;
<strong> 输出</strong>：[&quot;eqq&quot;,&quot;qeq&quot;,&quot;qqe&quot;]
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：S = &quot;ab&quot;
<strong> 输出</strong>：[&quot;ab&quot;, &quot;ba&quot;]
</pre>
<p><strong>提示:</strong></p>
<ol>
	<li>字符都是英文字母。</li>
	<li>字符串长度在[1, 9]之间。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
