---
comments: true
difficulty: 中等
tags:
    - 字符串
    - 字符串匹配
    - KMP 算法
    - Boyer–Moore 算法
    - 扩展 KMP
---

<!-- problem:start -->

# [686. 重复叠加字符串匹配](https://leetcode.cn/problems/repeated-string-match)

[English Version](/solution/0600-0699/0686.Repeated%20String%20Match/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个由小写英文字母组成的字符串&nbsp;<code>a</code> 和 <code>b</code>。</p>

<p>返回重复字符串 <code>a</code> 的 <strong>最小</strong> 次数，使得字符串 <code>b</code> 成为它的子字符串。如果 <code>b</code> 无法成为 <code>a</code> 的子字符串，则返回 -1。</p>

<p><strong>注意：</strong>字符串 <code>"abc"</code>&nbsp;重复叠加 0 次是 <code>""</code>，重复叠加 1 次是&nbsp;<code>"abc"</code>，重复叠加 2 次是&nbsp;<code>"abcabc"</code>。</p>

<p><strong>子数组</strong> 是数组中连续的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = "abcd", b = "cdabcdab"
<strong>输出：</strong>3
<strong>解释：</strong>a 重复叠加三遍后为 "ab<strong>cdabcdab</strong>cd", 此时 b 是其子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = "a", b = "aa"
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = "a", b = "a"
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>a = "abc", b = "wxyz"
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> $a$ 重复若干次后要包含 $b$。次数下界是 $\lceil |b|/|a|\rceil$，再多两三次即可覆盖跨边界的匹配； $|a|,|b|$ 不大，直接拼接判断即可。
>
> 从该下界起至多再追加 $3$ 个 $a$，若 `b in t` 则返回次数，否则无法构成。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def repeatedStringMatch(self, a: str, b: str) -> int:
        m, n = len(a), len(b)
        ans = ceil(n / m)
        t = [a] * ans
        for _ in range(3):
            if b in ''.join(t):
                return ans
            ans += 1
            t.append(a)
        return -1
```

#### Java

```java
class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        int ans = (n + m - 1) / m;
        StringBuilder t = new StringBuilder(a.repeat(ans));
        for (int i = 0; i < 3; ++i) {
            if (t.toString().contains(b)) {
                return ans;
            }
            ++ans;
            t.append(a);
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int repeatedStringMatch(string a, string b) {
        int m = a.size(), n = b.size();
        int ans = (n + m - 1) / m;
        string t = "";
        for (int i = 0; i < ans; ++i) t += a;
        for (int i = 0; i < 3; ++i) {
            if (t.find(b) != -1) return ans;
            ++ans;
            t += a;
        }
        return -1;
    }
};
```

#### Go

```go
func repeatedStringMatch(a string, b string) int {
	m, n := len(a), len(b)
	ans := (n + m - 1) / m
	t := strings.Repeat(a, ans)
	for i := 0; i < 3; i++ {
		if strings.Contains(t, b) {
			return ans
		}
		ans++
		t += a
	}
	return -1
}
```

#### TypeScript

```ts
function repeatedStringMatch(a: string, b: string): number {
    const m: number = a.length,
        n: number = b.length;
    let ans: number = Math.ceil(n / m);
    let t: string = a.repeat(ans);

    for (let i = 0; i < 3; i++) {
        if (t.includes(b)) {
            return ans;
        }
        ans++;
        t += a;
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
