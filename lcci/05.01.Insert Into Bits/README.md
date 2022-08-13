# [面试题 05.01. 插入](https://leetcode.cn/problems/insert-into-bits-lcci)

[English Version](/lcci/05.01.Insert%20Into%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>插入。给定两个32位的整数<code>N</code>与<code>M</code>，以及表示比特位置的<code>i</code>与<code>j</code>。编写一种方法，将<code>M</code>插入<code>N</code>，使得M从N的第j位开始，到第<code>i</code>位结束。假定从<code>j</code>位到<code>i</code>位足以容纳<code>M</code>，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：N = 10000000000, M = 10011, i = 2, j = 6
<strong> 输出</strong>：N = 10001001100
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>： N = 0, M = 11111, i = 0, j = 4
<strong> 输出</strong>：N = 11111
</pre>

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
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; k++) {
            N &= ~(1 << k);
        }
        return N ^ (M << i);
    }
}
```

### **...**

```


```

<!-- tabs:end -->
