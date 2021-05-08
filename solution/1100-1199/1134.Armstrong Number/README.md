# [1134. 阿姆斯特朗数](https://leetcode-cn.com/problems/armstrong-number)

[English Version](/solution/1100-1199/1134.Armstrong%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设存在一个 <code>k</code> 位数&nbsp;<code>N</code>，其每一位上的数字的 <code>k</code> 次幂的总和也是&nbsp;<code>N</code>，那么这个数是阿姆斯特朗数。</p>

<p>给你一个正整数&nbsp;<code>N</code>，让你来判定他是否是阿姆斯特朗数，是则返回 <code>true</code>，不是则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>153
<strong>输出：</strong>true
<strong>示例： </strong>
153 是一个 3 位数，且 153 = 1^3 + 5^3 + 3^3。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>123
<strong>输出：</strong>false
<strong>解释： </strong>
123 是一个 3 位数，且 123 != 1^3 + 2^3 + 3^3 = 36。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10^8</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先求数字 n 的长度 k，然后累加 n 上每一位的数字的 k 次幂。最后判断累加的结果是否与 n 相等即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isArmstrong(self, n: int) -> bool:
        k = len(str(n))
        s, t = 0, n
        while t:
            t, v = divmod(t, 10)
            s += pow(v, k)
        return n == s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isArmstrong(int n) {
        int k = String.valueOf(n).length();
        int s = 0, t = n;
        while (t != 0) {
            s += Math.pow(t % 10, k);
            t /= 10;
        }
        return n == s;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isArmstrong = function (n) {
  const k = String(n).length;
  let s = 0;
  let t = n;
  while (t) {
    s += Math.pow(t % 10, k);
    t = Math.floor(t / 10);
  }
  return n == s;
};
```

### **...**

```

```

<!-- tabs:end -->
