# [1864. 构成交替字符串需要的最小交换次数](https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating)

[English Version](/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>s</code> ，现需要将其转化为一个 <strong>交替字符串</strong> 。请你计算并返回转化所需的 <strong>最小</strong> 字符交换次数，如果无法完成转化，返回<em> </em><code>-1</code><em> </em>。</p>

<p><strong>交替字符串</strong> 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 <code>"010"</code> 和 <code>"1010"</code> 属于交替字符串，但 <code>"0100"</code> 不是。</p>

<p>任意两个字符都可以进行交换，<strong>不必相邻</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "111000"
<strong>输出：</strong>1
<strong>解释：</strong>交换位置 1 和 4："1<em><strong>1</strong></em>10<em><strong>0</strong></em>0" -> "1<em><strong>0</strong></em>10<em><strong>1</strong></em>0" ，字符串变为交替字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "010"
<strong>输出：</strong>0
<strong>解释：</strong>字符串已经是交替字符串了，不需要交换。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "1110"
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s[i]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        s0n0 = s0n1 = s1n0 = s1n1 = 0
        for i in range(len(s)):
            if (i & 1) == 0:
                if s[i] != '0':
                    s0n0 += 1
                else:
                    s1n1 += 1
            else:
                if s[i] != '0':
                    s1n0 += 1
                else:
                    s0n1 += 1
        if s0n0 != s0n1 and s1n0 != s1n1:
            return -1
        if s0n0 != s0n1:
            return s1n0
        if s1n0 != s1n1:
            return s0n0
        return min(s0n0, s1n0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSwaps(String s) {
        int s0n0 = 0, s0n1 = 0;
        int s1n0 = 0, s1n1 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ((i & 1) == 0) {
                if (s.charAt(i) != '0') {
                    s0n0 += 1;
                } else {
                    s1n1 += 1;
                }
            } else {
                if (s.charAt(i) != '0') {
                    s1n0 += 1;
                } else {
                    s0n1 += 1;
                }
            }
        }
        if (s0n0 != s0n1 && s1n0 != s1n1) {
            return -1;
        }
        if (s0n0 != s0n1) {
            return s1n0;
        }
        if (s1n0 != s1n1) {
            return s0n0;
        }
        return Math.min(s0n0, s1n0);
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var minSwaps = function (s) {
    let n = s.length;
    let n1 = [...s].reduce((a, c) => parseInt(c) + a, 0);
    let n0 = n - n1;
    let count = Infinity;
    let half = n / 2;
    // 101、1010
    if (n1 == Math.ceil(half) && n0 == Math.floor(half)) {
        let cur = 0;
        for (let i = 0; i < n; i++) {
            if (i % 2 == 0 && s.charAt(i) != '1') cur++;
        }
        count = Math.min(count, cur);
    }
    // 010、0101
    if (n0 == Math.ceil(half) && n1 == Math.floor(half)) {
        let cur = 0;
        for (let i = 0; i < n; i++) {
            if (i % 2 == 0 && s.charAt(i) != '0') cur++;
        }
        count = Math.min(count, cur);
    }
    return count == Infinity ? -1 : count;
};
```

### **...**

```

```

<!-- tabs:end -->
