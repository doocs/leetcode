---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1180.Count%20Substrings%20with%20Only%20One%20Distinct%20Letter/README.md
rating: 1315
tags:
    - 数学
    - 字符串
---

# [1180. 统计只含单一字母的子串 🔒](https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter)

[English Version](/solution/1100-1199/1180.Count%20Substrings%20with%20Only%20One%20Distinct%20Letter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，返回 <em>只含 <strong>单一字母</strong> 的子串个数</em> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>s = "aaaba"
<strong>输出： </strong>8
<strong>解释： </strong>只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
"aaa" 出现 1 次。
"aa" 出现 2 次。
"a" 出现 4 次。
"b" 出现 1 次。
所以答案是 1 + 2 + 4 + 1 = 8。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入： </strong>s = "aaaaaaaaaa"
<strong>输出： </strong>55
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：双指针

我们可以使用双指针，用指针 $i$ 指向当前子串的起始位置，指针 $j$ 向右移动到第一个与 $s[i]$ 不同的位置，那么 $[i,..j-1]$ 就是以 $s[i]$ 为唯一字母的子串，长度为 $j-i$，那么以 $s[i]$ 为唯一字母的子串的个数就是 $\frac{(j-i+1)(j-i)}{2}$，累加到答案中。然后令 $i=j$，继续遍历，直到 $i$ 超出字符串 $s$ 的范围。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countLetters(self, s: str) -> int:
        n = len(s)
        i = ans = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            ans += (1 + j - i) * (j - i) // 2
            i = j
        return ans
```

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        for (int i = 0, n = s.length(); i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countLetters(string s) {
        int ans = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
};
```

```go
func countLetters(s string) int {
	ans := 0
	for i, n := 0, len(s); i < n; {
		j := i
		for j < n && s[j] == s[i] {
			j++
		}
		ans += (1 + j - i) * (j - i) / 2
		i = j
	}
	return ans
}
```

```ts
function countLetters(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let i = 0; i < n; ) {
        let j = i;
        let cnt = 0;
        while (j < n && s[j] === s[i]) {
            ++j;
            ans += ++cnt;
        }
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def countLetters(self, s: str) -> int:
        ans = 0
        i, n = 0, len(s)
        while i < n:
            j = i
            cnt = 0
            while j < n and s[j] == s[i]:
                j += 1
                cnt += 1
                ans += cnt
            i = j
        return ans
```

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        int i = 0, n = s.length();
        while (i < n) {
            int j = i;
            int cnt = 0;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
                ans += ++cnt;
            }
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countLetters(string s) {
        int ans = 0;
        int i = 0, n = s.size();
        while (i < n) {
            int j = i;
            int cnt = 0;
            while (j < n && s[j] == s[i]) {
                ++j;
                ans += ++cnt;
            }
            i = j;
        }
        return ans;
    }
};
```

```go
func countLetters(s string) (ans int) {
	i, n := 0, len(s)
	for i < n {
		j := i
		cnt := 0
		for j < n && s[j] == s[i] {
			j++
			cnt++
			ans += cnt
		}
		i = j
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
