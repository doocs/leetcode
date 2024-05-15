---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1698.Number%20of%20Distinct%20Substrings%20in%20a%20String/README.md
tags:
    - 字典树
    - 字符串
    - 后缀数组
    - 哈希函数
    - 滚动哈希
---

# [1698. 字符串的不同子字符串个数 🔒](https://leetcode.cn/problems/number-of-distinct-substrings-in-a-string)

[English Version](/solution/1600-1699/1698.Number%20of%20Distinct%20Substrings%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code>，返回 <code>s</code> 的不同子字符串的个数。</p>

<p>字符串的 <strong>子字符串 </strong>是由原字符串删除开头若干个字符（可能是 0 个）并删除结尾若干个字符（可能是 0 个）形成的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabbaba"
<strong>输出：</strong>21
<strong>解释：</strong>不同子字符串的集合是 ["a","b","aa","bb","ab","ba","aab","abb","bab","bba","aba","aabb","abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcdefg"
<strong>输出：</strong>28
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 500</code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
</ul>

<p><b>进阶：</b>你可以以 <code>O(n)</code> 时间复杂度解决此问题吗？</p>

## 解法

### 方法一：暴力枚举

枚举所有子串，使用哈希表记录不同子串的个数。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

```python
class Solution:
    def countDistinct(self, s: str) -> int:
        n = len(s)
        return len({s[i:j] for i in range(n) for j in range(i + 1, n + 1)})
```

```java
class Solution {
    public int countDistinct(String s) {
        Set<String> ss = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                ss.add(s.substring(i, j));
            }
        }
        return ss.size();
    }
}
```

```cpp
class Solution {
public:
    int countDistinct(string s) {
        unordered_set<string_view> ss;
        int n = s.size();
        string_view t, v = s;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                t = v.substr(i, j - i);
                ss.insert(t);
            }
        }
        return ss.size();
    }
};
```

```go
func countDistinct(s string) int {
	ss := map[string]struct{}{}
	for i := range s {
		for j := i + 1; j <= len(s); j++ {
			ss[s[i:j]] = struct{}{}
		}
	}
	return len(ss)
}
```

<!-- tabs:end -->

### 方法二：字符串哈希

**字符串哈希**是把一个任意长度的字符串映射成一个非负整数，并且其冲突的概率几乎为 0。字符串哈希用于计算字符串哈希值，快速判断两个字符串是否相等。

取一固定值 BASE，把字符串看作是 BASE 进制数，并分配一个大于 0 的数值，代表每种字符。一般来说，我们分配的数值都远小于 BASE。例如，对于小写字母构成的字符串，可以令 a=1, b=2, ..., z=26。取一固定值 MOD，求出该 BASE 进制对 M 的余数，作为该字符串的 hash 值。

一般来说，取 BASE=131 或者 BASE=13331，此时 hash 值产生的冲突概率极低。只要两个字符串 hash 值相同，我们就认为两个字符串是相等的。通常 MOD 取 2^64，C++ 里，可以直接使用 unsigned long long 类型存储这个 hash 值，在计算时不处理算术溢出问题，产生溢出时相当于自动对 2^64 取模，这样可以避免低效取模运算。

除了在极特殊构造的数据上，上述 hash 算法很难产生冲突，一般情况下上述 hash 算法完全可以出现在题目的标准答案中。我们还可以多取一些恰当的 BASE 和 MOD 的值（例如大质数），多进行几组 hash 运算，当结果都相同时才认为原字符串相等，就更加难以构造出使这个 hash 产生错误的数据。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

```python
class Solution:
    def countDistinct(self, s: str) -> int:
        base = 131
        n = len(s)
        p = [0] * (n + 10)
        h = [0] * (n + 10)
        p[0] = 1
        for i, c in enumerate(s):
            p[i + 1] = p[i] * base
            h[i + 1] = h[i] * base + ord(c)
        ss = set()
        for i in range(1, n + 1):
            for j in range(i, n + 1):
                t = h[j] - h[i - 1] * p[j - i + 1]
                ss.add(t)
        return len(ss)
```

```java
class Solution {
    public int countDistinct(String s) {
        int base = 131;
        int n = s.length();
        long[] p = new long[n + 10];
        long[] h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        Set<Long> ss = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                long t = h[j] - h[i - 1] * p[j - i + 1];
                ss.add(t);
            }
        }
        return ss.size();
    }
}
```

```cpp
class Solution {
public:
    int countDistinct(string s) {
        using ull = unsigned long long;
        int n = s.size();
        ull p[n + 10];
        ull h[n + 10];
        int base = 131;
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s[i];
        }
        unordered_set<ull> ss;
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                ss.insert(h[j] - h[i - 1] * p[j - i + 1]);
            }
        }
        return ss.size();
    }
};
```

```go
func countDistinct(s string) int {
	n := len(s)
	p := make([]int, n+10)
	h := make([]int, n+10)
	p[0] = 1
	base := 131
	for i, c := range s {
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + int(c)
	}
	ss := map[int]struct{}{}
	for i := 1; i <= n; i++ {
		for j := i; j <= n; j++ {
			ss[h[j]-h[i-1]*p[j-i+1]] = struct{}{}
		}
	}
	return len(ss)
}
```

<!-- tabs:end -->

<!-- end -->
