# [1864. Minimum Number of Swaps to Make the Binary String Alternating](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating)

[中文文档](/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README.md)

## Description

<p>Given a binary string <code>s</code>, return <em>the <strong>minimum</strong> number of character swaps to make it <strong>alternating</strong>, or </em><code>-1</code><em> if it is impossible.</em></p>

<p>The string is called <strong>alternating</strong> if no two adjacent characters are equal. For example, the strings <code>&quot;010&quot;</code> and <code>&quot;1010&quot;</code> are alternating, while the string <code>&quot;0100&quot;</code> is not.</p>

<p>Any two characters may be swapped, even if they are&nbsp;<strong>not adjacent</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111000&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Swap positions 1 and 4: &quot;1<u>1</u>10<u>0</u>0&quot; -&gt; &quot;1<u>0</u>10<u>1</u>0&quot;
The string is now alternating.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already alternating, no swaps are needed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1110&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
