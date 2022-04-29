# [1837. K 进制表示下的各位数字总和](https://leetcode.cn/problems/sum-of-digits-in-base-k)

[English Version](/solution/1800-1899/1837.Sum%20of%20Digits%20in%20Base%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>（<code>10</code> 进制）和一个基数 <code>k</code> ，请你将 <code>n</code> 从 <code>10</code> 进制表示转换为 <code>k</code> 进制表示，计算并返回转换后各位数字的 <strong>总和</strong> 。</p>

<p>转换后，各位数字应当视作是 <code>10</code> 进制数字，且它们的总和也应当按 <code>10</code> 进制表示返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 34, k = 6
<strong>输出：</strong>9
<strong>解释：</strong>34 (10 进制) 在 6 进制下表示为 54 。5 + 4 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10, k = 10
<strong>输出：</strong>1
<strong>解释：</strong>n 本身就是 10 进制。 1 + 0 = 1 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 100</code></li>
	<li><code>2 <= k <= 10</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将 n 除 k 取余，直至为 0，余数相加求得结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumBase(self, n: int, k: int) -> int:
        res = 0
        while n != 0:
            n, t = divmod(n, k)
            res += t
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumBase(int n, int k) {
        int res = 0;
        while (n != 0) {
            res += (n % k);
            n /= k;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
