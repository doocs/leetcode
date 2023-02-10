# [1221. 分割平衡字符串](https://leetcode.cn/problems/split-a-string-in-balanced-strings)

[English Version](/solution/1200-1299/1221.Split%20a%20String%20in%20Balanced%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>平衡字符串</strong> 中，<code>'L'</code> 和 <code>'R'</code> 字符的数量是相同的。</p>

<p>给你一个平衡字符串&nbsp;<code>s</code>，请你将它分割成尽可能多的子字符串，并满足：</p>

<ul>
	<li>每个子字符串都是平衡字符串。</li>
</ul>

<p>返回可以通过分割得到的平衡字符串的 <strong>最大数量</strong> <strong>。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "RLRRLLRLRL"
<strong>输出：</strong>4
<strong>解释：</strong>s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "RLRRRLLRLL"
<strong>输出：</strong>2
<strong>解释：</strong>s 可以分割为 "RL"、"RRRLLRLL"，每个子字符串中都包含相同数量的 'L' 和 'R' 。
注意，s 无法分割为 "RL"、"RR"、"RL"、"LR"、"LL" 因为第 2 个和第 5 个子字符串不是平衡字符串。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "LLLLRRRR"
<strong>输出：</strong>1
<strong>解释：</strong>s 只能保持原样 "LLLLRRRR" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i] = 'L' 或 'R'</code></li>
	<li><code>s</code> 是一个 <strong>平衡</strong> 字符串</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们用变量 $l$ 维护当前字符串的平衡度，即 $l$ 的值为当前字符串中 $L$ 的数量减去 $R$ 的数量。当 $l$ 的值为 0 时，我们就找到了一个平衡字符串。

遍历字符串 $s$，当遍历到第 $i$ 个字符时，如果 $s[i] = L$，则 $l$ 的值加 1，否则 $l$ 的值减 1。当 $l$ 的值为 0 时，我们将答案加 1。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def balancedStringSplit(self, s: str) -> int:
        ans = l = 0
        for c in s:
            if c == 'L':
                l += 1
            else:
                l -= 1
            if l == 0:
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int balancedStringSplit(String s) {
        int ans = 0, l = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                ++l;
            } else {
                --l;
            }
            if (l == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int balancedStringSplit(string s) {
        int ans = 0, l = 0;
        for (char c : s) {
            if (c == 'L')
                ++l;
            else
                --l;
            if (l == 0) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func balancedStringSplit(s string) int {
	ans, l := 0, 0
	for _, c := range s {
		if c == 'L' {
			l++
		} else {
			l--
		}
		if l == 0 {
			ans++
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var balancedStringSplit = function (s) {
    let ans = 0;
    let l = 0;
    for (let c of s) {
        if (c == 'L') {
            ++l;
        } else {
            --l;
        }
        if (l == 0) {
            ++ans;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
