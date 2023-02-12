# [467. 环绕字符串中唯一的子字符串](https://leetcode.cn/problems/unique-substrings-in-wraparound-string)

[English Version](/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>定义字符串&nbsp;<code>base</code>&nbsp;为一个&nbsp;<code>"abcdefghijklmnopqrstuvwxyz"</code>&nbsp;无限环绕的字符串，所以&nbsp;<code>base</code>&nbsp;看起来是这样的：</p>

<ul>
	<li><code>"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...."</code>.</li>
</ul>

<p>给你一个字符串&nbsp;<code>s</code> ，请你统计并返回&nbsp;<code>s</code>&nbsp;中有多少&nbsp;<strong>不同</strong><strong>非空子串</strong>&nbsp;也在&nbsp;<code>base</code>&nbsp;中出现。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>1
<strong>解释：</strong>字符串 s 的子字符串 "a" 在 base 中出现。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "cac"
<strong>输出：</strong>2
<strong>解释：</strong>字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "zab"
<strong>输出：</strong>6
<strong>解释：</strong>字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

不妨设 `dp[α]` 表示 p 中以字符 α 结尾且在 s 中的子串的最大长度，将 dp 求和可以得到最终结果。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 p 的长度。

> 成为子串的一个标准，需要是连续的，`a` 与 `c` 之间少了一个 `b`，所以不能算一个子字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSubstringInWraproundString(self, p: str) -> int:
        dp = [0] * 26
        k = 0
        for i, c in enumerate(p):
            if i and (ord(c) - ord(p[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            idx = ord(c) - ord('a')
            dp[idx] = max(dp[idx], k)
        return sum(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            if (i > 0 && (c - p.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            dp[c - 'a'] = Math.max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int v : dp) {
            ans += v;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function findSubstringInWraproundString(p: string): number {
    const n = p.length;
    const dp = new Array(26).fill(0);
    let cur = 1;
    dp[p.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    for (let i = 1; i < n; i++) {
        if ((p.charCodeAt(i) - p.charCodeAt(i - 1) + 25) % 26 == 0) {
            cur++;
        } else {
            cur = 1;
        }
        const index = p.charCodeAt(i) - 'a'.charCodeAt(0);
        dp[index] = Math.max(dp[index], cur);
    }
    return dp.reduce((r, v) => r + v);
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_substring_in_wrapround_string(p: String) -> i32 {
        let n = p.len();
        let p = p.as_bytes();
        let mut dp = [0; 26];
        let mut cur = 1;
        dp[(p[0] - b'a') as usize] = 1;
        for i in 1..n {
            if (p[i] - p[i - 1] + 25) % 26 == 0 {
                cur += 1;
            } else {
                cur = 1;
            }
            let index = (p[i] - b'a') as usize;
            dp[index] = dp[index].max(cur);
        }
        dp.into_iter().sum()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findSubstringInWraproundString(string p) {
        vector<int> dp(26);
        int k = 0;
        for (int i = 0; i < p.size(); ++i) {
            char c = p[i];
            if (i && (c - p[i - 1] + 26) % 26 == 1)
                ++k;
            else
                k = 1;
            dp[c - 'a'] = max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int& v : dp) ans += v;
        return ans;
    }
};
```

### **Go**

```go
func findSubstringInWraproundString(p string) int {
	dp := make([]int, 26)
	k := 0
	for i := range p {
		c := p[i]
		if i > 0 && (c-p[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		dp[c-'a'] = max(dp[c-'a'], k)
	}
	ans := 0
	for _, v := range dp {
		ans += v
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
