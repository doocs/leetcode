# [1881. 插入后的最大值](https://leetcode-cn.com/problems/maximum-value-after-insertion)

[English Version](/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非常大的整数 <code>n</code> 和一个整数数字 <code>x</code> ，大整数 <code>n</code> 用一个字符串表示。<code>n</code> 中每一位数字和数字 <code>x</code> 都处于闭区间 <code>[1, 9]</code> 中，且 <code>n</code> 可能表示一个 <strong>负数</strong> 。</p>

<p>你打算通过在 <code>n</code> 的十进制表示的任意位置插入 <code>x</code> 来 <strong>最大化</strong> <code>n</code> 的 <strong>数值</strong> ​​​​​​。但 <strong>不能</strong> 在负号的左边插入 <code>x</code> 。</p>

<ul>
	<li>例如，如果 <code>n = 73</code> 且 <code>x = 6</code> ，那么最佳方案是将 <code>6</code> 插入 <code>7</code> 和 <code>3</code> 之间，使 <code>n = 763</code> 。</li>
	<li>如果 <code>n = -55</code> 且 <code>x = 2</code> ，那么最佳方案是将 <code>2</code> 插在第一个 <code>5</code> 之前，使 <code>n = -255</code> 。</li>
</ul>

<p>返回插入操作后，用字符串表示的 <code>n</code> 的最大值。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = "99", x = 9
<strong>输出：</strong>"999"
<strong>解释：</strong>不管在哪里插入 9 ，结果都是相同的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = "-13", x = 2
<strong>输出：</strong>"-123"
<strong>解释：</strong>向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= x <= 9</code></li>
	<li><code>n</code>​​​ 中每一位的数字都在闭区间 <code>[1, 9]</code> 中。</li>
	<li><code>n</code> 代表一个有效的整数。</li>
	<li>当 <code>n</code> 表示负数时，将会以字符 <code>'-'</code> 开始。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxValue(self, n: str, x: int) -> str:
        negative = n[0] == '-'
        i, res = 0, []
        if negative:
            i += 1
            res.append('-')
        find = False
        while i < len(n):
            num = int(n[i])
            if (negative and x < num) or (not negative and x > num):
                res.append(str(x))
                find = True
                break
            res.append(n[i])
            i += 1
        res.append(n[i:] if find else str(x))
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String maxValue(String n, int x) {
        boolean negative = n.charAt(0) == '-';
        StringBuilder res = new StringBuilder();
        int i = 0;
        if (negative) {
            ++i;
            res.append("-");
        }
        boolean find = false;
        for (; i < n.length(); ++i) {
            int num = n.charAt(i) - '0';
            if ((negative && x < num) || (!negative && x > num)) {
                res.append(x);
                find = true;
                break;
            }
            res.append(n.charAt(i));
        }
        res.append(find ? n.substring(i) : x);
        return res.toString();
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
var maxValue = function (n, x) {
    let nums = [...n];
    let sign = 1,
        i = 0;
    if (nums[0] == "-") {
        sign = -1;
        i++;
    }
    while (i < n.length && (nums[i] - x) * sign >= 0) {
        i++;
    }
    nums.splice(i, 0, x);
    return nums.join("");
};
```

### **...**

```

```

<!-- tabs:end -->
