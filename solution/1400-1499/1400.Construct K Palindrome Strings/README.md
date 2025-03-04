---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1400.Construct%20K%20Palindrome%20Strings/README.md
rating: 1530
source: 第 23 场双周赛 Q2
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1400. 构造 K 个回文字符串](https://leetcode.cn/problems/construct-k-palindrome-strings)

[English Version](/solution/1400-1499/1400.Construct%20K%20Palindrome%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>&nbsp;和一个整数 <code>k</code>&nbsp;。请你用 <code>s</code>&nbsp;字符串中 <strong>所有字符</strong>&nbsp;构造 <code>k</code>&nbsp;个<strong>非空</strong> <span data-keyword="palindrome-string">回文串</span>&nbsp;。</p>

<p>如果你可以用&nbsp;<code>s</code>&nbsp;中所有字符构造&nbsp;<code>k</code>&nbsp;个回文字符串，那么请你返回 <strong>True</strong>&nbsp;，否则返回&nbsp;<strong>False</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "annabelle", k = 2
<strong>输出：</strong>true
<strong>解释：</strong>可以用 s 中所有字符构造 2 个回文字符串。
一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode", k = 3
<strong>输出：</strong>false
<strong>解释：</strong>无法用 s 中所有字符构造 3 个回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "true", k = 4
<strong>输出：</strong>true
<strong>解释：</strong>唯一可行的方案是让 s 中每个字符单独构成一个字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;中所有字符都是小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们先判断字符串 $s$ 的长度是否小于 $k$，如果是，那么一定无法构造出 $k$ 个回文串，可以直接返回 `false`。

否则，我们用一个哈希表或数组 $cnt$ 统计字符串 $s$ 中每个字符出现的次数。最后，我们只需要统计 $cnt$ 中奇数次数的字符个数 $x$，如果 $x$ 大于 $k$，那么一定无法构造出 $k$ 个回文串，返回 `false`；否则，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度；而 $C$ 是字符集大小，这里 $C=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canConstruct(self, s: str, k: int) -> bool:
        if len(s) < k:
            return False
        cnt = Counter(s)
        return sum(v & 1 for v in cnt.values()) <= k
```

#### Java

```java
class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canConstruct(string s, int k) {
        if (s.size() < k) {
            return false;
        }
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
};
```

#### Go

```go
func canConstruct(s string, k int) bool {
	if len(s) < k {
		return false
	}
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	x := 0
	for _, v := range cnt {
		x += v & 1
	}
	return x <= k
}
```

#### TypeScript

```ts
function canConstruct(s: string, k: number): boolean {
    if (s.length < k) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let x = 0;
    for (const v of cnt) {
        x += v & 1;
    }
    return x <= k;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
