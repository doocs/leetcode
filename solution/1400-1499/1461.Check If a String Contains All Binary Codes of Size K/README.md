---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1461.Check%20If%20a%20String%20Contains%20All%20Binary%20Codes%20of%20Size%20K/README.md
rating: 1504
source: 第 27 场双周赛 Q2
tags:
    - 位运算
    - 哈希表
    - 字符串
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [1461. 检查一个字符串是否包含所有长度为 K 的二进制子串](https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k)

[English Version](/solution/1400-1499/1461.Check%20If%20a%20String%20Contains%20All%20Binary%20Codes%20of%20Size%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。如果所有长度为 <code>k</code>&nbsp;的二进制字符串都是 <code>s</code>&nbsp;的子串，请返回 <code>true</code> ，否则请返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110110", k = 2
<strong>输出：</strong>true
<strong>解释：</strong>长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", k = 1
<strong>输出：</strong>true
<strong>解释：</strong>长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", k = 2
<strong>输出：</strong>false
<strong>解释：</strong>长度为 2 的二进制串 "00" 没有出现在 s 中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 不是<code>'0'</code> 就是 <code>'1'</code></li>
	<li><code>1 &lt;= k &lt;= 20</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

首先，对于一个长度为 $n$ 的字符串 $s$，长度为 $k$ 的子串的个数为 $n - k + 1$，如果 $n - k + 1 < 2^k$，则一定存在长度为 $k$ 的二进制串不是 $s$ 的子串，返回 `false`。

接下来，我们遍历字符串 $s$，将所有长度为 $k$ 的子串存入集合 $ss$，最后判断集合 $ss$ 的大小是否等于 $2^k$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        n = len(s)
        m = 1 << k
        if n - k + 1 < m:
            return False
        ss = {s[i : i + k] for i in range(n - k + 1)}
        return len(ss) == m
```

#### Java

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        int m = 1 << k;
        if (n - k + 1 < m) {
            return false;
        }
        Set<String> ss = new HashSet<>();
        for (int i = 0; i < n - k + 1; ++i) {
            ss.add(s.substring(i, i + k));
        }
        return ss.size() == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        int m = 1 << k;
        if (n - k + 1 < m) {
            return false;
        }
        unordered_set<string> ss;
        for (int i = 0; i + k <= n; ++i) {
            ss.insert(move(s.substr(i, k)));
        }
        return ss.size() == m;
    }
};
```

#### Go

```go
func hasAllCodes(s string, k int) bool {
	n, m := len(s), 1<<k
	if n-k+1 < m {
		return false
	}
	ss := map[string]bool{}
	for i := 0; i+k <= n; i++ {
		ss[s[i:i+k]] = true
	}
	return len(ss) == m
}
```

#### TypeScript

```ts
function hasAllCodes(s: string, k: number): boolean {
    const n = s.length;
    const m = 1 << k;
    if (n - k + 1 < m) {
        return false;
    }
    const ss = new Set<string>();
    for (let i = 0; i + k <= n; ++i) {
        ss.add(s.slice(i, i + k));
    }
    return ss.size === m;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：滑动窗口

方法一中，我们存储了所有长度为 $k$ 的不同子串，子串的处理需要 $O(k)$ 的时间，我们可以改用滑动窗口，每次添加最新字符时，删除窗口最左边的字符。此过程中用一个整型数字 $x$ 来存放子串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        n = len(s)
        m = 1 << k
        if n - k + 1 < m:
            return False
        ss = set()
        x = int(s[:k], 2)
        ss.add(x)
        for i in range(k, n):
            a = int(s[i - k]) << (k - 1)
            b = int(s[i])
            x = (x - a) << 1 | b
            ss.add(x)
        return len(ss) == m
```

#### Java

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        int m = 1 << k;
        if (n - k + 1 < m) {
            return false;
        }
        boolean[] ss = new boolean[m];
        int x = Integer.parseInt(s.substring(0, k), 2);
        ss[x] = true;
        for (int i = k; i < n; ++i) {
            int a = (s.charAt(i - k) - '0') << (k - 1);
            int b = s.charAt(i) - '0';
            x = (x - a) << 1 | b;
            ss[x] = true;
        }
        for (boolean v : ss) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        int m = 1 << k;
        if (n - k + 1 < m) {
            return false;
        }
        bool ss[m];
        memset(ss, false, sizeof(ss));
        int x = stoi(s.substr(0, k), nullptr, 2);
        ss[x] = true;
        for (int i = k; i < n; ++i) {
            int a = (s[i - k] - '0') << (k - 1);
            int b = s[i] - '0';
            x = (x - a) << 1 | b;
            ss[x] = true;
        }
        return all_of(ss, ss + m, [](bool v) { return v; });
    }
};
```

#### Go

```go
func hasAllCodes(s string, k int) bool {
	n, m := len(s), 1<<k
	if n-k+1 < m {
		return false
	}
	ss := make([]bool, m)
	x, _ := strconv.ParseInt(s[:k], 2, 64)
	ss[x] = true
	for i := k; i < n; i++ {
		a := int64(s[i-k]-'0') << (k - 1)
		b := int64(s[i] - '0')
		x = (x-a)<<1 | b
		ss[x] = true
	}
	for _, v := range ss {
		if !v {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function hasAllCodes(s: string, k: number): boolean {
    const n = s.length;
    const m = 1 << k;
    if (n - k + 1 < m) {
        return false;
    }
    let x = +`0b${s.slice(0, k)}`;
    const ss = new Set<number>();
    ss.add(x);
    for (let i = k; i < n; ++i) {
        const a = +s[i - k] << (k - 1);
        const b = +s[i];
        x = ((x - a) << 1) | b;
        ss.add(x);
    }
    return ss.size === m;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
