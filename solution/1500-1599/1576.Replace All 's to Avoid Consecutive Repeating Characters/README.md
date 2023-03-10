# [1576. 替换所有的问号](https://leetcode.cn/problems/replace-all-s-to-avoid-consecutive-repeating-characters)

[English Version](/solution/1500-1599/1576.Replace%20All%20%27s%20to%20Avoid%20Consecutive%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅包含小写英文字母和 <code>'?'</code> 字符的字符串 <code>s</code>，请你将所有的 <code>'?'</code> 转换为若干小写字母，使最终的字符串不包含任何 <strong>连续重复</strong> 的字符。</p>

<p>注意：你 <strong>不能</strong> 修改非 <code>'?'</code> 字符。</p>

<p>题目测试用例保证 <strong>除</strong> <code>'?'</code> 字符 <strong>之外</strong>，不存在连续重复的字符。</p>

<p>在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "?zs"
<strong>输出：</strong>"azs"
<strong>解释：</strong>该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ubv?w"
<strong>输出：</strong>"ubvaw"
<strong>解释：</strong>该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>
	<p><code>1 &lt;= s.length&nbsp;&lt;= 100</code></p>
	</li>
	<li>
	<p><code>s</code> 仅包含小写英文字母和 <code>'?'</code> 字符</p>
	</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们遍历字符串，对于每个位置，如果该位置是 `?`，则枚举字符 `'a'`、`'b'`、`'c'`，如果该字符 $c$ 与前后字符都不相同，则将该位置替换为该字符，否则继续枚举下一个字符。

遍历结束后，返回字符串即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def modifyString(self, s: str) -> str:
        s = list(s)
        n = len(s)
        for i in range(n):
            if s[i] == "?":
                for c in "abc":
                    if (i and s[i - 1] == c) or (i + 1 < n and s[i + 1] == c):
                        continue
                    s[i] = c
                    break
        return "".join(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; ++i) {
            if (cs[i] == '?') {
                for (char c = 'a'; c <= 'c'; ++c) {
                    if ((i > 0 && cs[i - 1] == c) || (i + 1 < n && cs[i + 1] == c)) {
                        continue;
                    }
                    cs[i] = c;
                    break;
                }
            }
        }
        return String.valueOf(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string modifyString(string s) {
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '?') {
                for (char c : "abc") {
                    if ((i && s[i - 1] == c) || (i + 1 < n && s[i + 1] == c)) {
                        continue;
                    }
                    s[i] = c;
                    break;
                }
            }
        }
        return s;
    }
};
```

### **Go**

```go
func modifyString(s string) string {
	n := len(s)
	cs := []byte(s)
	for i := range s {
		if cs[i] == '?' {
			for c := byte('a'); c <= byte('c'); c++ {
				if (i > 0 && cs[i-1] == c) || (i+1 < n && cs[i+1] == c) {
					continue
				}
				cs[i] = c
				break
			}
		}
	}
	return string(cs)
}
```

### **TypeScript**

```ts
function modifyString(s: string): string {
    const cs = s.split('');
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (cs[i] === '?') {
            for (const c of 'abc') {
                if (
                    (i > 0 && cs[i - 1] === c) ||
                    (i + 1 < n && cs[i + 1] === c)
                ) {
                    continue;
                }
                cs[i] = c;
                break;
            }
        }
    }
    return cs.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
