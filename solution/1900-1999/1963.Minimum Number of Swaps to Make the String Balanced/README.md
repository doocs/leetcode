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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        ans = 0
        for c in s:
            if c == '[':
                ans += 1
            elif ans:
                ans -= 1
        return (ans + 1) >> 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSwaps(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                ++ans;
            } else if (ans > 0) {
                --ans;
            }
        }
        return (ans + 1) >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int ans = 0;
        for (char& c : s) {
            if (c == '[')
                ++ans;
            else if (ans)
                --ans;
        }
        return (ans + 1) >> 1;
    }
};
```

### **Go**

```go
func minSwaps(s string) int {
	ans := 0
	for _, c := range s {
		if c == '[' {
			ans++
		} else if ans > 0 {
			ans--
		}
	}
	return (ans + 1) >> 1
}
```

### **...**

```

```

<!-- tabs:end -->
