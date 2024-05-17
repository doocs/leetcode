---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1071.Greatest%20Common%20Divisor%20of%20Strings/README.md
rating: 1397
source: 第 139 场周赛 Q1
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [1071. 字符串的最大公因子](https://leetcode.cn/problems/greatest-common-divisor-of-strings)

[English Version](/solution/1000-1099/1071.Greatest%20Common%20Divisor%20of%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对于字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>，只有在&nbsp;<code>s = t + t + t + ... + t + t</code>（<code>t</code> 自身连接 1 次或多次）时，我们才认定&nbsp;“<code>t</code> 能除尽 <code>s</code>”。</p>

<p>给定两个字符串&nbsp;<code>str1</code>&nbsp;和&nbsp;<code>str2</code>&nbsp;。返回 <em>最长字符串&nbsp;<code>x</code>，要求满足&nbsp;<code>x</code> 能除尽 <code>str1</code> 且 <code>x</code> 能除尽 <code>str2</code></em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>str1 = "ABCABC", str2 = "ABC"
<strong>输出：</strong>"ABC"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>str1 = "ABABAB", str2 = "ABAB"
<strong>输出：</strong>"AB"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>str1 = "LEET", str2 = "CODE"
<strong>输出：</strong>""
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str1.length, str2.length &lt;= 1000</code></li>
	<li><code>str1</code>&nbsp;和&nbsp;<code>str2</code>&nbsp;由大写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        def check(a, b):
            c = ""
            while len(c) < len(b):
                c += a
            return c == b

        for i in range(min(len(str1), len(str2)), 0, -1):
            t = str1[:i]
            if check(t, str1) and check(t, str2):
                return t
        return ''
```

#### Java

```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int len = gcd(str1.length(), str2.length());
        return str1.substring(0, len);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string gcdOfStrings(string str1, string str2) {
        if (str1 + str2 != str2 + str1) return "";
        int n = __gcd(str1.size(), str2.size());
        return str1.substr(0, n);
    }
};
```

#### Go

```go
func gcdOfStrings(str1 string, str2 string) string {
	if str1+str2 != str2+str1 {
		return ""
	}
	n := gcd(len(str1), len(str2))
	return str1[:n]
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### Rust

```rust
impl Solution {
    pub fn gcd_of_strings(str1: String, str2: String) -> String {
        if str1.clone() + &str2 != str2.clone() + &str1 {
            return String::from("");
        }
        fn gcd(a: usize, b: usize) -> usize {
            if b == 0 {
                return a;
            }
            gcd(b, a % b)
        }

        let (m, n) = (str1.len().max(str2.len()), str1.len().min(str2.len()));
        str1[..gcd(m, n)].to_string()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        if str1 + str2 != str2 + str1:
            return ''
        n = gcd(len(str1), len(str2))
        return str1[:n]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
