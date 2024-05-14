---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2825.Make%20String%20a%20Subsequence%20Using%20Cyclic%20Increments/README.md
rating: 1414
tags:
    - 双指针
    - 字符串
---

# [2825. 循环增长使字符串子序列等于另一个字符串](https://leetcode.cn/problems/make-string-a-subsequence-using-cyclic-increments)

[English Version](/solution/2800-2899/2825.Make%20String%20a%20Subsequence%20Using%20Cyclic%20Increments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>str1</code> 和&nbsp;<code>str2</code>&nbsp;。</p>

<p>一次操作中，你选择&nbsp;<code>str1</code>&nbsp;中的若干下标。对于选中的每一个下标&nbsp;<code>i</code>&nbsp;，你将&nbsp;<code>str1[i]</code>&nbsp;<strong>循环</strong>&nbsp;递增，变成下一个字符。也就是说&nbsp;<code>'a'</code>&nbsp;变成&nbsp;<code>'b'</code>&nbsp;，<code>'b'</code> 变成&nbsp;<code>'c'</code>&nbsp;，以此类推，<code>'z'</code> 变成&nbsp;<code>'a'</code>&nbsp;。</p>

<p>如果执行以上操作 <strong>至多一次</strong>&nbsp;，可以让 <code>str2</code>&nbsp;成为 <code>str1</code>&nbsp;的子序列，请你返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><b>注意：</b>一个字符串的子序列指的是从原字符串中删除一些（可以一个字符也不删）字符后，剩下字符按照原本先后顺序组成的新字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>str1 = "abc", str2 = "ad"
<b>输出：</b>true
<b>解释：</b>选择 str1 中的下标 2 。
将 str1[2] 循环递增，得到 'd' 。
因此，str1 变成 "abd" 且 str2 现在是一个子序列。所以返回 true 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>str1 = "zc", str2 = "ad"
<b>输出：</b>true
<b>解释：</b>选择 str1 中的下标 0 和 1 。
将 str1[0] 循环递增得到 'a' 。
将 str1[1] 循环递增得到 'd' 。
因此，str1 变成 "ad" 且 str2 现在是一个子序列。所以返回 true 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>str1 = "ab", str2 = "d"
<b>输出：</b>false
<b>解释：</b>这个例子中，没法在执行一次操作的前提下，将 str2 变为 str1 的子序列。
所以返回 false 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= str2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>str1</code>&nbsp;和&nbsp;<code>str2</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：双指针

本题实际上需要我们判断一个字符串 $s$ 是否为另一个字符串 $t$ 的子序列，只不过字符之间可以不完全匹配，如果两个字符相同，或者一个字符是另一个字符的下一个字符，就可以匹配。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是字符串 $str1$ 和 $str2$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def canMakeSubsequence(self, str1: str, str2: str) -> bool:
        i = 0
        for c in str1:
            d = "a" if c == "z" else chr(ord(c) + 1)
            if i < len(str2) and str2[i] in (c, d):
                i += 1
        return i == len(str2)
```

```java
class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int i = 0, n = str2.length();
        for (char c : str1.toCharArray()) {
            char d = c == 'z' ? 'a' : (char) (c + 1);
            if (i < n && (str2.charAt(i) == c || str2.charAt(i) == d)) {
                ++i;
            }
        }
        return i == n;
    }
}
```

```cpp
class Solution {
public:
    bool canMakeSubsequence(string str1, string str2) {
        int i = 0, n = str2.size();
        for (char c : str1) {
            char d = c == 'z' ? 'a' : c + 1;
            if (i < n && (str2[i] == c || str2[i] == d)) {
                ++i;
            }
        }
        return i == n;
    }
};
```

```go
func canMakeSubsequence(str1 string, str2 string) bool {
	i, n := 0, len(str2)
	for _, c := range str1 {
		d := byte('a')
		if c != 'z' {
			d = byte(c + 1)
		}
		if i < n && (str2[i] == byte(c) || str2[i] == d) {
			i++
		}
	}
	return i == n
}
```

```ts
function canMakeSubsequence(str1: string, str2: string): boolean {
    let i = 0;
    const n = str2.length;
    for (const c of str1) {
        const d = c === 'z' ? 'a' : String.fromCharCode(c.charCodeAt(0) + 1);
        if (i < n && (str2[i] === c || str2[i] === d)) {
            ++i;
        }
    }
    return i === n;
}
```

<!-- tabs:end -->

<!-- end -->
