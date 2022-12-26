# [1759. 统计同构子字符串的数目](https://leetcode.cn/problems/count-number-of-homogenous-substrings)

[English Version](/solution/1700-1799/1759.Count%20Number%20of%20Homogenous%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，返回<em> </em><code>s</code><em> </em>中 <strong>同构子字符串</strong> 的数目。由于答案可能很大，只需返回对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>后的结果。</p>

<p><strong>同构字符串</strong> 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abbcccaa"
<strong>输出：</strong>13
<strong>解释：</strong>同构子字符串如下所列：
"a"   出现 3 次。
"aa"  出现 1 次。
"b"   出现 2 次。
"bb"  出现 1 次。
"c"   出现 3 次。
"cc"  出现 2 次。
"ccc" 出现 1 次。
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "xy"
<strong>输出：</strong>2
<strong>解释：</strong>同构子字符串是 "x" 和 "y" 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "zzzzz"
<strong>输出：</strong>15
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写字符串组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

遍历字符串 $s$，用指针 $i$ 指向当前字符，指针 $j$ 指向下一个不同的字符，那么 $[i,..j-1]$ 区间内的字符都是相同的，假设 $cnt=j-i$，那么该区间内的同构子字符串个数为 $\frac{(1 + cnt) \times cnt}{2}$，将其累加到答案中即可。继续遍历，直到指针 $i$ 到达字符串末尾。

遍历完字符串 $s$ 后，返回答案即可。注意答案的取模操作。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countHomogenous(self, s: str) -> int:
        mod = 10**9 + 7
        i, n = 0, len(s)
        ans = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            ans %= mod
            i = j
        return ans
```

```python
class Solution:
    def countHomogenous(self, s: str) -> int:
        mod = 10**9 + 7
        ans = cnt = 1
        for a, b in pairwise(s):
            cnt = cnt + 1 if a == b else 1
            ans = (ans + cnt) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countHomogenous(String s) {
        int n = s.length();
        long ans = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int cnt = j - i;
            ans += (long) (1 + cnt) * cnt / 2;
            ans %= MOD;
        }
        return (int) ans;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countHomogenous(String s) {
        int n = s.length();
        int ans = 1, cnt = 1;
        for (int i = 1; i < n; ++i) {
            cnt = s.charAt(i) == s.charAt(i - 1) ? cnt + 1 : 1;
            ans = (ans + cnt) % MOD;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countHomogenous(string s) {
        int n = s.size();
        long ans = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            while (j < n && s[j] == s[i]) ++j;
            int cnt = j -  i;
            ans += 1ll * (1 + cnt) * cnt / 2;
            ans %= mod;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countHomogenous(string s) {
        int n = s.size();
        int ans = 1, cnt = 1;
        for (int i = 1; i < n; ++i) {
            cnt = s[i] == s[i - 1] ? cnt + 1 : 1;
            ans = (ans + cnt) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func countHomogenous(s string) (ans int) {
	n := len(s)
	const mod int = 1e9 + 7
	for i, j := 0, 0; i < n; i = j {
		j = i
		for j < n && s[j] == s[i] {
			j++
		}
		cnt := j - i
		ans += (1 + cnt) * cnt / 2
		ans %= mod
	}
	return
}
```

```go
func countHomogenous(s string) int {
	n := len(s)
	const mod int = 1e9 + 7
	ans, cnt := 1, 1
	for i := 1; i < n; i++ {
		if s[i] == s[i-1] {
			cnt++
		} else {
			cnt = 1
		}
		ans = (ans + cnt) % mod
	}
	return ans
}
```

### **TypeScript**

```ts
function countHomogenous(s: string): number {
    const mod = 1e9 + 7;
    const n = s.length;
    let ans = 0;
    for (let i = 0, j = 0; j < n; j++) {
        if (s[i] !== s[j]) {
            i = j;
        }
        ans = (ans + j - i + 1) % mod;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_homogenous(s: String) -> i32 {
        const MOD: usize = 1e9 as usize + 7;
        let s = s.as_bytes();
        let n = s.len();
        let mut ans = 0;
        let mut i = 0;
        for j in 0..n {
            if s[i] != s[j] {
                i = j;
            }
            ans = (ans + j - i + 1) % MOD;
        }
        ans as i32
    }
}
```

### **C**

```c
int countHomogenous(char *s) {
    int MOD = 1e9 + 7;
    int ans = 0;
    for (int i = 0, j = 0; s[j]; j++) {
        if (s[i] != s[j]) {
            i = j;
        }
        ans = (ans + j - i + 1) % MOD;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
