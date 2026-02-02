---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3794.Reverse%20String%20Prefix/README.md
rating: 1229
source: 第 173 场双周赛 Q1
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [3794. 反转字符串前缀](https://leetcode.cn/problems/reverse-string-prefix)

[English Version](/solution/3700-3799/3794.Reverse%20String%20Prefix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>反转 <code>s</code> 的前 <code>k</code> 个字符，并返回结果字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abcd", k = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">"bacd"</span></p>

<p><strong>解释:</strong></p>

<p>前 <code>k = 2</code> 个字符 <code>"ab"</code> 反转为 <code>"ba"</code>。最终得到的结果字符串为 <code>"bacd"</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "xyz", k = 3</span></p>

<p><strong>输出:</strong> <span class="example-io">"zyx"</span></p>

<p><strong>解释:</strong></p>

<p>前 <code>k = 3</code> 个字符 <code>"xyz"</code> 反转为 <code>"zyx"</code>。最终得到的结果字符串为 <code>"zyx"</code>。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "hey", k = 1</span></p>

<p><strong>输出:</strong> <span class="example-io">"hey"</span></p>

<p><strong>解释:</strong></p>

<p>前 <code>k = 1</code> 个字符 <code>"h"</code> 在反转后保持不变。最终得到的结果字符串为 <code>"hey"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们按照题目描述，将字符串的前 $k$ 个字符反转，然后与剩余的字符串拼接即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reversePrefix(self, s: str, k: int) -> str:
        return s[:k][::-1] + s[k:]
```

#### Java

```java
class Solution {
    public String reversePrefix(String s, int k) {
        StringBuilder sb = new StringBuilder(s.substring(0, k));
        return sb.reverse().toString() + s.substring(k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reversePrefix(string s, int k) {
        string t = s.substr(0, k);
        reverse(t.begin(), t.end());
        return t + s.substr(k);
    }
};
```

#### Go

```go
func reversePrefix(s string, k int) string {
	t := []byte(s[:k])
	slices.Reverse(t)
	return string(t) + s[k:]
}
```

#### TypeScript

```ts
function reversePrefix(s: string, k: number): string {
    return s.slice(0, k).split('').reverse().join('') + s.slice(k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
