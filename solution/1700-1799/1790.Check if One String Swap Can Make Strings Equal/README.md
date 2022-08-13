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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areAlmostEqual(self, s1: str, s2: str) -> bool:
        cnt, n = 0, len(s1)
        c1 = c2 = None
        for i in range(n):
            if s1[i] != s2[i]:
                cnt += 1
                if (cnt == 2 and (s1[i] != c2 or s2[i] != c1)) or cnt > 2:
                    return False
                c1, c2 = s1[i], s2[i]
        return cnt == 0 or cnt == 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        char c1 = 0;
        char c2 = 0;
        for (int i = 0; i < n; ++i) {
            char t1 = s1.charAt(i), t2 = s2.charAt(i);
            if (t1 != t2) {
                ++cnt;
                if ((cnt == 2 && (c1 != t2 || c2 != t1)) || cnt > 2) {
                    return false;
                }
                c1 = t1;
                c2 = t2;
            }
        }
        return cnt == 0 || cnt == 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        char c1 = 0, c2 = 0;
        int n = s1.size();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s1[i] != s2[i]) {
                ++cnt;
                if ((cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2) return false;
                c1 = s1[i];
                c2 = s2[i];
            }
        }
        return cnt == 0 || cnt == 2;
    }
};
```

### **Go**

```go
func areAlmostEqual(s1 string, s2 string) bool {
	var c1, c2 byte
	cnt, n := 0, len(s1)
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			cnt++
			if (cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2 {
				return false
			}
			c1, c2 = s1[i], s2[i]
		}
	}
	return cnt == 0 || cnt == 2
}
```

### **Rust**

```rust
impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let n = s1.len();
        let mut indexs = vec![];
        for i in 0..n {
            let (c1, c2) = (s1[i], s2[i]);
            if c1 != c2 {
                indexs.push(i);
                if indexs.len() > 2 {
                    return false;
                }
            }
        }
        let size = indexs.len();
        if size == 2 {
            return s1[indexs[0]] == s2[indexs[1]] && s2[indexs[0]] == s1[indexs[1]];
        }
        size != 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
