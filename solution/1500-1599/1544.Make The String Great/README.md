# [1544. 整理字符串](https://leetcode.cn/problems/make-the-string-great)

[English Version](/solution/1500-1599/1544.Make%20The%20String%20Great/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由大小写英文字母组成的字符串 <code>s</code> 。</p>

<p>一个整理好的字符串中，两个相邻字符 <code>s[i]</code> 和 <code>s[i+1]</code>，其中 <code>0<= i <= s.length-2</code> ，要满足如下条件:</p>

<ul>
	<li>若 <code>s[i]</code> 是小写字符，则 <code>s[i+1]</code> 不可以是相同的大写字符。</li>
	<li>若 <code>s[i]</code> 是大写字符，则 <code>s[i+1]</code> 不可以是相同的小写字符。</li>
</ul>

<p>请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 <strong>两个相邻</strong> 字符并删除，直到字符串整理好为止。</p>

<p>请返回整理好的 <strong>字符串</strong> 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。</p>

<p><strong>注意：</strong>空字符串也属于整理好的字符串，尽管其中没有任何字符。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leEeetcode"
<strong>输出：</strong>"leetcode"
<strong>解释：</strong>无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abBAcC"
<strong>输出：</strong>""
<strong>解释：</strong>存在多种不同情况，但所有的情况都会导致相同的结果。例如：
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "s"
<strong>输出：</strong>"s"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s</code> 只包含小写和大写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈模拟**

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeGood(self, s: str) -> str:
        stk = []
        for c in s:
            if not stk or abs(ord(stk[-1]) - ord(c)) != 32:
                stk.append(c)
            else:
                stk.pop()
        return "".join(stk)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() == 0 || Math.abs(sb.charAt(sb.length() - 1) - c) != 32) {
                sb.append(c);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string makeGood(string s) {
        string stk;
        for (char c : s) {
            if (stk.empty() || abs(stk.back() - c) != 32) {
                stk += c;
            } else {
                stk.pop_back();
            }
        }
        return stk;
    }
};
```

### **Go**

```go
func makeGood(s string) string {
	stk := []rune{}
	for _, c := range s {
		if len(stk) == 0 || abs(int(stk[len(stk)-1]-c)) != 32 {
			stk = append(stk, c)
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return string(stk)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
