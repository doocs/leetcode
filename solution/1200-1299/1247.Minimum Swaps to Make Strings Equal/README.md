# [1247. 交换字符使得字符串相同](https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal)

[English Version](/solution/1200-1299/1247.Minimum%20Swaps%20to%20Make%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两个长度相同的字符串&nbsp;<code>s1</code> 和&nbsp;<code>s2</code>，且它们其中&nbsp;<strong>只含有</strong>&nbsp;字符&nbsp;<code>"x"</code> 和&nbsp;<code>"y"</code>，你需要通过「交换字符」的方式使这两个字符串相同。</p>

<p>每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。</p>

<p>交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换&nbsp;<code>s1[i]</code> 和&nbsp;<code>s2[j]</code>，但不能交换&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[j]</code>。</p>

<p>最后，请你返回使 <code>s1</code> 和 <code>s2</code> 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "xx", s2 = "yy"
<strong>输出：</strong>1
<strong>解释：
</strong>交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "xy", s2 = "yx"
<strong>输出：</strong>2
<strong>解释：
</strong>交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s1 = "xx", s2 = "xy"
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 1000</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li><code>s1, s2</code>&nbsp;只包含&nbsp;<code>'x'</code>&nbsp;或&nbsp;<code>'y'</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

根据题目描述，两个字符串 $s1$ 和 $s2$ 都只包含字符 $x$ 和 $y$，且长度相同，因此可以将 $s1$ 和 $s2$ 中的字符一一对应起来，即 $s1[i]$ 和 $s2[i]$。

如果 $s1[i] = s2[i]$，则不需要交换，直接跳过即可。如果 $s1[i] \neq s2[i]$，则需要交换，我们统计 $s1[i]$ 和 $s2[i]$ 的组合情况，即 $s1[i] = x$ 且 $s2[i] = y$ 的情况，记为 $xy$，对于 $s1[i] = y$ 且 $s2[i] = x$ 的情况，记为 $yx$。

如果 $xy + yx$ 为奇数，则无法完成交换，返回 $-1$。如果 $xy + yx$ 为偶数，则需要交换的次数为 $\left \lfloor \frac{x}{2} \right \rfloor$ + $\left \lfloor \frac{y}{2} \right \rfloor$ + $xy \mod{2}$ + $yx \mod{2}$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s1$ 和 $s2$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSwap(self, s1: str, s2: str) -> int:
        xy = yx = 0
        for a, b in zip(s1, s2):
            xy += a < b
            yx += a > b
        if (xy + yx) % 2:
            return -1
        return xy // 2 + yx // 2 + xy % 2 + yx % 2
```

### **Java**

```java
class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); ++i) {
            char a = s1.charAt(i), b = s2.charAt(i);
            if (a < b) {
                ++xy;
            }
            if (a > b) {
                ++yx;
            }
        }
        if ((xy + yx) % 2 == 1) {
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSwap(string s1, string s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.size(); ++i) {
            char a = s1[i], b = s2[i];
            xy += a < b;
            yx += a > b;
        }
        if ((xy + yx) % 2) {
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
};
```

### **Go**

```go
func minimumSwap(s1 string, s2 string) int {
	xy, yx := 0, 0
	for i := range s1 {
		if s1[i] < s2[i] {
			xy++
		}
		if s1[i] > s2[i] {
			yx++
		}
	}
	if (xy+yx)%2 == 1 {
		return -1
	}
	return xy/2 + yx/2 + xy%2 + yx%2
}
```

### **JavaScript**

```js
var minimumSwap = function (s1, s2) {
    let xy = 0,
        yx = 0;
    for (let i = 0; i < s1.length; ++i) {
        const a = s1[i],
            b = s2[i];
        if (a < b) {
            ++xy;
        }
        if (a > b) {
            ++yx;
        }
    }
    if ((xy + yx) % 2 === 1) {
        return -1;
    }
    return Math.floor(xy / 2) + Math.floor(yx / 2) + (xy % 2) + (yx % 2);
};
```

### **...**

```

```

<!-- tabs:end -->
