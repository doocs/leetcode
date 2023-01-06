# [2496. 数组中字符串的最大值](https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array)

[English Version](/solution/2400-2499/2496.Maximum%20Value%20of%20a%20String%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个由字母和数字组成的字符串的 <strong>值</strong>&nbsp;定义如下：</p>

<ul>
	<li>如果字符串 <strong>只</strong> 包含数字，那么值为该字符串在 <code>10</code>&nbsp;进制下的所表示的数字。</li>
	<li>否则，值为字符串的 <strong>长度&nbsp;</strong>。</li>
</ul>

<p>给你一个字符串数组&nbsp;<code>strs</code>&nbsp;，每个字符串都只由字母和数字组成，请你返回 <code>strs</code>&nbsp;中字符串的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["alic3","bob","3","4","00000"]
<b>输出：</b>5
<b>解释：</b>
- "alic3" 包含字母和数字，所以值为长度 5 。
- "bob" 只包含字母，所以值为长度 3 。
- "3" 只包含数字，所以值为 3 。
- "4" 只包含数字，所以值为 4 。
- "00000" 只包含数字，所以值为 0 。
所以最大的值为 5 ，是字符串 "alic3" 的值。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>strs = ["1","01","001","0001"]
<b>输出：</b>1
<b>解释：</b>
数组中所有字符串的值都是 1 ，所以我们返回 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 9</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母和数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意模拟即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `strs` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumValue(self, strs: List[str]) -> int:
        def f(s):
            return int(s) if all(c.isdigit() for c in s) else len(s)

        return max(f(s) for s in strs)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumValue(String[] strs) {
        int ans = 0;
        for (String s : strs) {
            ans = Math.max(ans, f(s));
        }
        return ans;
    }

    private int f(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                return s.length();
            }
        }
        return Integer.parseInt(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumValue(vector<string>& strs) {
        auto f = [](string& s) {
            int n = s.size(), m = 0;
            for (char& c : s) {
                if (!isdigit(c)) return n;
                m = m * 10 + (c - '0');
            }
            return m;
        };
        int ans = 0;
        for (auto& s : strs) ans = max(ans, f(s));
        return ans;
    }
};
```

### **Go**

```go
func maximumValue(strs []string) (ans int) {
	f := func(s string) int {
		n, m := len(s), 0
		for _, c := range s {
			if c >= 'a' && c <= 'z' {
				return n
			}
			m = m*10 + int(c-'0')
		}
		return m
	}
	for _, s := range strs {
		if t := f(s); ans < t {
			ans = t
		}
	}
	return
}
```

### **TypeScript**

```ts
function maximumValue(strs: string[]): number {
    let ans = 0;
    for (const s of strs) {
        const num = Number(s);
        ans = Math.max(ans, Number.isNaN(num) ? s.length : num);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_value(strs: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in strs.iter() {
            let num = s.parse().unwrap_or(s.len());
            ans = ans.max(num);
        }
        ans as i32
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int parseInt(char *s) {
    int n = strlen(s);
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (!isdigit(s[i])) {
            return n;
        }
        res = res * 10 + s[i] - '0';
    }
    return res;
}

int maximumValue(char **strs, int strsSize) {
    int ans = 0;
    for (int i = 0; i < strsSize; i++) {
        int num = parseInt(strs[i]);
        ans = max(ans, num);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
