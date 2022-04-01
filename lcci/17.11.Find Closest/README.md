# [面试题 17.11. 单词距离](https://leetcode-cn.com/problems/find-closest-lcci)

[English Version](/lcci/17.11.Find%20Closest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>words = [&quot;I&quot;,&quot;am&quot;,&quot;a&quot;,&quot;student&quot;,&quot;from&quot;,&quot;a&quot;,&quot;university&quot;,&quot;in&quot;,&quot;a&quot;,&quot;city&quot;], word1 = &quot;a&quot;, word2 = &quot;student&quot;
<strong>输出：</strong>1</pre>

<p>提示：</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>

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

### **TypeScript**

```ts
function findClosest(words: string[], word1: string, word2: string): number {
    let index1 = 100000;
    let index2 = -100000;
    let res = 100000;
    const n = words.length;
    for (let i = 0; i < n; i++) {
        const word = words[i];
        if (word === word1) {
            index1 = i;
        } else if (word === word2) {
            index2 = i;
        }
        res = Math.min(res, Math.abs(index1 - index2));
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
