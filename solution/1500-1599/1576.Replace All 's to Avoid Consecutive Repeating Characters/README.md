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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def modifyString(self, s: str) -> str:
        ans = list(s)
        for i, c in enumerate(ans):
            if c == '?':
                for cc in 'abc':
                    if i > 0 and ans[i - 1] == cc:
                        continue
                    if i < len(s) - 1 and ans[i + 1] == cc:
                        continue
                    ans[i] = cc
                    break
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (c == '?') {
                for (char cc = 'a'; cc <= 'c'; ++cc) {
                    if (i > 0 && chars[i - 1] == cc) {
                        continue;
                    }
                    if (i < chars.length - 1 && chars[i + 1] == cc) {
                        continue;
                    }
                    chars[i] = cc;
                    break;
                }
            }
        }
        return String.valueOf(chars);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string modifyString(string s) {
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '?') {
                for (char cc : "abc") {
                    if (i > 0 && s[i - 1] == cc) continue;
                    if (i < s.size() - 1 && s[i + 1] == cc) continue;
                    s[i] = cc;
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
	ans := []byte(s)
	for i, c := range ans {
		if c == '?' {
			for cc := byte('a'); cc <= 'c'; cc++ {
				if i > 0 && ans[i-1] == cc {
					continue
				}
				if i < len(s)-1 && ans[i+1] == cc {
					continue
				}
				ans[i] = cc
				break
			}
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function modifyString(s: string): string {
    const strArr = s.split('');
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (strArr[i] === '?') {
            const before = strArr[i - 1];
            const after = strArr[i + 1];

            if (after !== 'a' && before !== 'a') {
                strArr[i] = 'a';
            } else if (after !== 'b' && before !== 'b') {
                strArr[i] = 'b';
            } else {
                strArr[i] = 'c';
            }
        }
    }
    return strArr.join('');
}
```

<!-- tabs:end -->
