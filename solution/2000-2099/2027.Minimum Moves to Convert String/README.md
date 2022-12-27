# [2027. 转换字符串的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-convert-string)

[English Version](/solution/2000-2099/2027.Minimum%20Moves%20to%20Convert%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，由 <code>n</code> 个字符组成，每个字符不是 <code>'X'</code> 就是 <code>'O'</code> 。</p>

<p>一次<strong> 操作</strong> 定义为从 <code>s</code> 中选出 <strong>三个连续字符 </strong>并将选中的每个字符都转换为 <code>'O'</code> 。注意，如果字符已经是 <code>'O'</code> ，只需要保持 <strong>不变</strong> 。</p>

<p>返回将 <code>s</code> 中所有字符均转换为 <code>'O'</code> 需要执行的&nbsp;<strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "XXX"
<strong>输出：</strong>1
<strong>解释：<em>XXX</em></strong> -&gt; OOO
一次操作，选中全部 3 个字符，并将它们转换为 <code>'O' 。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "XXOX"
<strong>输出：</strong>2
<strong>解释：<em>XXO</em></strong>X -&gt; O<em><strong>OOX</strong></em> -&gt; OOOO
第一次操作，选择前 3 个字符，并将这些字符转换为 <code>'O'</code> 。
然后，选中后 3 个字符，并执行转换。最终得到的字符串全由字符 <code>'O'</code> 组成。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "OOOO"
<strong>输出：</strong>0
<strong>解释：</strong>s 中不存在需要转换的 <code>'X' 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 为 <code>'X'</code> 或 <code>'O'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

遍历字符串 $s$，只要遇到 `'X'`，指针 $i$ 就直接往后移动三格，并且答案加 $1$；否则指针 $i$ 往后移动一格。

时间复杂度 $O(n)$。其中 $n$ 表示字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumMoves(self, s: str) -> int:
        ans = i = 0
        while i < len(s):
            if s[i] == "X":
                ans += 1
                i += 3
            else:
                i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumMoves(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'X') {
                ++ans;
                i += 2;
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
    int minimumMoves(string s) {
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == 'X') {
                ++ans;
                i += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumMoves(s string) (ans int) {
	for i := 0; i < len(s); i++ {
		if s[i] == 'X' {
			ans++
			i += 2
		}
	}
	return
}
```

### **TypeScript**

```ts
function minimumMoves(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        if (s[i] === 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_moves(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            if s[i] == b'X' {
                ans += 1;
                i += 3;
            } else {
                i += 1;
            }
        }
        ans
    }
}
```

### **C**

```c
int minimumMoves(char *s) {
    int n = strlen(s);
    int ans = 0;
    int i = 0;
    while (i < n) {
        if (s[i] == 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
