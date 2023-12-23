# [1963. 使字符串平衡的最小交换次数](https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced)

[English Version](/solution/1900-1999/1963.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20String%20Balanced/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，<strong>下标从 0 开始</strong> ，且长度为偶数 <code>n</code> 。字符串 <strong>恰好</strong> 由 <code>n / 2</code> 个开括号 <code>'['</code> 和 <code>n / 2</code> 个闭括号 <code>']'</code> 组成。</p>

<p>只有能满足下述所有条件的字符串才能称为 <strong>平衡字符串</strong> ：</p>

<ul>
	<li>字符串是一个空字符串，或者</li>
	<li>字符串可以记作 <code>AB</code> ，其中 <code>A</code> 和 <code>B</code> 都是 <strong>平衡字符串</strong> ，或者</li>
	<li>字符串可以写成 <code>[C]</code> ，其中 <code>C</code> 是一个 <strong>平衡字符串</strong> 。</li>
</ul>

<p>你可以交换 <strong>任意</strong> 两个下标所对应的括号 <strong>任意</strong> 次数。</p>

<p>返回使<em> </em><code>s</code> 变成 <strong>平衡字符串</strong> 所需要的 <strong>最小</strong> 交换次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "][]["
<strong>输出：</strong>1
<strong>解释：</strong>交换下标 0 和下标 3 对应的括号，可以使字符串变成平衡字符串。
最终字符串变成 "[[]]" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "]]][[["
<strong>输出：</strong>2
<strong>解释：</strong>执行下述操作可以使字符串变成平衡字符串：
- 交换下标 0 和下标 4 对应的括号，s = "[]][][" 。
- 交换下标 1 和下标 5 对应的括号，s = "[[][]]" 。
最终字符串变成 "[[][]]" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "[]"
<strong>输出：</strong>0
<strong>解释：</strong>这个字符串已经是平衡字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>n</code> 为偶数</li>
	<li><code>s[i]</code> 为<code>'['</code> 或 <code>']'</code></li>
	<li>开括号 <code>'['</code> 的数目为 <code>n / 2</code> ，闭括号 <code>']'</code> 的数目也是 <code>n / 2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们用一个变量 $x$ 记录当前未匹配的左括号的数量，遍历字符串 $s$，对于每个字符 $c$：

-   如果 $c$ 是左括号，那么 $x$ 加一；
-   如果 $c$ 是右括号，那么我们需要判断 $x$ 是否大于零，如果大于零，那么将当前右括号与左侧最近的一个未匹配的左括号匹配，即 $x$ 减一。

遍历结束后，得到的一定是形如 `"]]]...[[[..."`的字符串，我们再贪心地每次将两端的括号交换，这样一次可以消去 $2$ 个不匹配的左括号。因此，一共需要交换的次数为 $\left\lfloor \frac{x + 1}{2} \right\rfloor$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        x = 0
        for c in s:
            if c == "[":
                x += 1
            elif x:
                x -= 1
        return (x + 1) >> 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSwaps(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '[') {
                ++x;
            } else if (x > 0) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int x = 0;
        for (char& c : s) {
            if (c == '[') {
                ++x;
            } else if (x) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
};
```

### **Go**

```go
func minSwaps(s string) int {
	x := 0
	for _, c := range s {
		if c == '[' {
			x++
		} else if x > 0 {
			x--
		}
	}
	return (x + 1) / 2
}
```

### **TypeScript**

```ts
function minSwaps(s: string): number {
    let x = 0;
    for (const c of s) {
        if (c === '[') {
            ++x;
        } else if (x) {
            --x;
        }
    }
    return (x + 1) >> 1;
}
```

### **...**

```

```

<!-- tabs:end -->
