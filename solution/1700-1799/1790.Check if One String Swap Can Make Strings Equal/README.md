# [1790. 仅执行一次字符串交换能否使两个字符串相等](https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal)

[English Version](/solution/1700-1799/1790.Check%20if%20One%20String%20Swap%20Can%20Make%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你长度相等的两个字符串 <code>s1</code> 和 <code>s2</code> 。一次<strong> 字符串交换 </strong>操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。</p>

<p>如果对 <strong>其中一个字符串</strong> 执行 <strong>最多一次字符串交换</strong> 就可以使两个字符串相等，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s1 = "bank", s2 = "kanb"
<strong>输出：</strong>true
<strong>解释：</strong>例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s1 = "attack", s2 = "defend"
<strong>输出：</strong>false
<strong>解释：</strong>一次字符串交换无法使两个字符串相等
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s1 = "kelb", s2 = "kelb"
<strong>输出：</strong>true
<strong>解释：</strong>两个字符串已经相等，所以不需要进行字符串交换
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s1 = "abcd", s2 = "dcba"
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：简单计数**

我们用变量 $cnt$ 记录两个字符串中相同位置字符不同的个数，两个字符串若满足题目要求，那么 $cnt$ 一定为 $0$ 或 $2$。另外用两个字符变量 $c1$ 和 $c2$ 记录两个字符串中相同位置字符不同的字符。

同时遍历两个字符串，对于相同位置的两个字符 $a$ 和 $b$，如果 $a \ne b$，那么 $cnt$ 自增 $1$。如果此时 $cnt$ 大于 $2$，或者 $cnt$ 为 $2$ 且 $a \ne c2$ 或 $b \ne c1$，那么直接返回 `false`。注意记录一下 $c1$ 和 $c2$。

遍历结束，若 $cnt\neq 1$，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areAlmostEqual(self, s1: str, s2: str) -> bool:
        cnt = 0
        c1 = c2 = None
        for a, b in zip(s1, s2):
            if a != b:
                cnt += 1
                if cnt > 2 or (cnt == 2 and (a != c2 or b != c1)):
                    return False
                c1, c2 = a, b
        return cnt != 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int cnt = 0;
        char c1 = 0, c2 = 0;
        for (int i = 0; i < s1.length(); ++i) {
            char a = s1.charAt(i), b = s2.charAt(i);
            if (a != b) {
                if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                    return false;
                }
                c1 = a;
                c2 = b;
            }
        }
        return cnt != 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        int cnt = 0;
        char c1 = 0, c2 = 0;
        for (int i = 0; i < s1.size(); ++i) {
            char a = s1[i], b = s2[i];
            if (a != b) {
                if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                    return false;
                }
                c1 = a, c2 = b;
            }
        }
        return cnt != 1;
    }
};
```

### **Go**

```go
func areAlmostEqual(s1 string, s2 string) bool {
	cnt := 0
	var c1, c2 byte
	for i := range s1 {
		a, b := s1[i], s2[i]
		if a != b {
			cnt++
			if cnt > 2 || (cnt == 2 && (a != c2 || b != c1)) {
				return false
			}
			c1, c2 = a, b
		}
	}
	return cnt != 1
}
```

### **C**

```c
bool areAlmostEqual(char *s1, char *s2) {
    int n = strlen(s1);
    int i1 = -1;
    int i2 = -1;
    for (int i = 0; i < n; i++) {
        if (s1[i] != s2[i]) {
            if (i1 == -1) {
                i1 = i;
            } else if (i2 == -1) {
                i2 = i;
            } else {
                return 0;
            }
        }
    }
    if (i1 == -1 && i2 == -1) {
        return 1;
    }
    if (i1 == -1 || i2 == -1) {
        return 0;
    }
    return s1[i1] == s2[i2] && s1[i2] == s2[i1];
}

```

### **TypeScript**

```ts
function areAlmostEqual(s1: string, s2: string): boolean {
    let c1, c2;
    let cnt = 0;
    for (let i = 0; i < s1.length; ++i) {
        const a = s1.charAt(i);
        const b = s2.charAt(i);
        if (a != b) {
            if (++cnt > 2 || (cnt == 2 && (a != c2 || b != c1))) {
                return false;
            }
            c1 = a;
            c2 = b;
        }
    }
    return cnt != 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let mut idxs = vec![];
        for i in 0..s1.len() {
            if s1[i] != s2[i] {
                idxs.push(i);
            }
        }
        if idxs.len() != 2 {
            return false;
        }
        s1[idxs[0]] == s2[idxs[1]] && s2[idxs[0]] == s1[idxs[1]]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
