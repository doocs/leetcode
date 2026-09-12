---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0266.Palindrome%20Permutation/README.md
tags:
    - 位运算
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [266. 回文排列 🔒](https://leetcode.cn/problems/palindrome-permutation)

[English Version](/solution/0200-0299/0266.Palindrome%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，如果该字符串的某个排列是 <span data-keyword="palindrome-string">回文串</span> ，则返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "code"
<strong>输出：</strong>false
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>true
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "carerac"
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

<!-- thinking:start -->

> **思考**
>
> 能排成回文当且仅当最多一个字符出现奇数次。统计频次后看奇数个计数是否少于 $2$。

<!-- thinking:end -->

如果一个字符串是回文串，那么至多只有一个字符出现奇数次数，其余字符都出现偶数次数。因此我们只需要统计每个字符出现的次数，然后判断是否满足这个条件即可。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串的长度，而 $|\Sigma|$ 是字符集的大小，本题中字符集为小写字母，因此 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        return sum(v & 1 for v in Counter(s).values()) < 2
```

#### Java

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int odd = 0;
        for (int x : cnt) {
            odd += x & 1;
        }
        return odd < 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> cnt(26);
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int odd = 0;
        for (int x : cnt) {
            odd += x & 1;
        }
        return odd < 2;
    }
};
```

#### Go

```go
func canPermutePalindrome(s string) bool {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	odd := 0
	for _, x := range cnt {
		odd += x & 1
	}
	return odd < 2
}
```

#### TypeScript

```ts
function canPermutePalindrome(s: string): boolean {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    return cnt.filter(c => c % 2 === 1).length < 2;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var canPermutePalindrome = function (s) {
    const cnt = new Map();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    return [...cnt.values()].filter(v => v % 2 === 1).length < 2;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
