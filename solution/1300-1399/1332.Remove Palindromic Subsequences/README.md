---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1332.Remove%20Palindromic%20Subsequences/README.md
rating: 1628
source: 第 173 场周赛 Q1
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [1332. 删除回文子序列](https://leetcode.cn/problems/remove-palindromic-subsequences)

[English Version](/solution/1300-1399/1332.Remove%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>，它仅由字母&nbsp;<code>'a'</code> 和 <code>'b'</code>&nbsp;组成。每一次删除操作都可以从 <code>s</code> 中删除一个回文 <strong>子序列</strong>。</p>

<p>返回删除给定字符串中所有字符（字符串为空）的最小删除次数。</p>

<p>「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。</p>

<p>「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ababa"
<strong>输出：</strong>1
<strong>解释：</strong>字符串本身就是回文序列，只需要删除一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abb"
<strong>输出：</strong>2
<strong>解释：</strong>"<strong>a</strong>bb" -&gt; "<strong>bb</strong>" -&gt; "". 
先删除回文子序列 "a"，然后再删除 "bb"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "baabb"
<strong>输出：</strong>2
<strong>解释：</strong>"<strong>baa</strong>b<strong>b</strong>" -&gt; "b" -&gt; "". 
先删除回文子序列 "baab"，然后再删除 "b"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 仅包含字母&nbsp;<code>'a'</code>&nbsp; 和 <code>'b'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

如果字符串 $s$ 本身是个回文串，那么只需要删除 $1$ 次。

如果字符串 $s$ 不是个回文串，那么先删除所有的 `'a'`，再删除所有的 `'b'`，总共需要删除 $2$ 次。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def removePalindromeSub(self, s: str) -> int:
        return 1 if s[::-1] == s else 2
```

```java
class Solution {
    public int removePalindromeSub(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return 2;
            }
        }
        return 1;
    }
}
```

```cpp
class Solution {
public:
    int removePalindromeSub(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return 2;
            }
        }
        return 1;
    }
};
```

```go
func removePalindromeSub(s string) int {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return 2
		}
	}
	return 1
}
```

```ts
function removePalindromeSub(s: string): number {
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return 2;
        }
    }
    return 1;
}
```

```rust
impl Solution {
    pub fn remove_palindrome_sub(s: String) -> i32 {
        let mut l = 0;
        let mut r = s.len() - 1;
        let s: Vec<char> = s.chars().collect();
        while l < r {
            if s[l] != s[r] {
                return 2;
            }
            l += 1;
            r -= 1;
        }
        1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
