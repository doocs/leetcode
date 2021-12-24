# [172. 阶乘后的零](https://leetcode-cn.com/problems/factorial-trailing-zeroes)

[English Version](/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <em>n</em>，返回 <em>n</em>! 结果尾数中零的数量。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 3
<strong>输出:</strong> 0
<strong>解释:</strong>&nbsp;3! = 6, 尾数中没有零。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 5
<strong>输出:</strong> 1
<strong>解释:</strong>&nbsp;5! = 120, 尾数中有 1 个零.</pre>

<p><strong>说明: </strong>你算法的时间复杂度应为&nbsp;<em>O</em>(log&nbsp;<em>n</em>)<em>&nbsp;</em>。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

统计 5 的个数

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
function trailingZeroes(n: number): number {
    let count = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        count += n;
    }
    return count;
}
```

### **...**

```

```

<!-- tabs:end -->
