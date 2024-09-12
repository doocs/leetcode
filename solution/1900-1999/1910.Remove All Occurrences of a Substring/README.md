---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1910.Remove%20All%20Occurrences%20of%20a%20Substring/README.md
rating: 1460
source: 第 55 场双周赛 Q2
tags:
    - 栈
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [1910. 删除一个字符串中所有出现的给定子字符串](https://leetcode.cn/problems/remove-all-occurrences-of-a-substring)

[English Version](/solution/1900-1999/1910.Remove%20All%20Occurrences%20of%20a%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>part</code> ，请你对 <code>s</code> 反复执行以下操作直到 <b>所有</b> 子字符串 <code>part</code> 都被删除：</p>

<ul>
	<li>找到 <code>s</code> 中 <strong>最左边</strong> 的子字符串 <code>part</code> ，并将它从 <code>s</code> 中删除。</li>
</ul>

<p>请你返回从 <code>s</code> 中删除所有 <code>part</code> 子字符串以后得到的剩余字符串。</p>

<p>一个 <strong>子字符串</strong> 是一个字符串中连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "daabcbaabcbc", part = "abc"
<b>输出：</b>"dab"
<b>解释：</b>以下操作按顺序执行：
- s = "da<strong>abc</strong>baabcbc" ，删除下标从 2 开始的 "abc" ，得到 s = "dabaabcbc" 。
- s = "daba<strong>abc</strong>bc" ，删除下标从 4 开始的 "abc" ，得到 s = "dababc" 。
- s = "dab<strong>abc</strong>" ，删除下标从 3 开始的 "abc" ，得到 s = "dab" 。
此时 s 中不再含有子字符串 "abc" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "axxxxyyyyb", part = "xy"
<b>输出：</b>"ab"
<b>解释：</b>以下操作按顺序执行：
- s = "axxx<strong>xy</strong>yyyb" ，删除下标从 4 开始的 "xy" ，得到 s = "axxxyyyb" 。
- s = "axx<strong>xy</strong>yyb" ，删除下标从 3 开始的 "xy" ，得到 s = "axxyyb" 。
- s = "ax<strong>xy</strong>yb" ，删除下标从 2 开始的 "xy" ，得到 s = "axyb" 。
- s = "a<strong>xy</strong>b" ，删除下标从 1 开始的 "xy" ，得到 s = "ab" 。
此时 s 中不再含有子字符串 "xy" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= part.length &lt;= 1000</code></li>
	<li><code>s</code>​​​​​​ 和 <code>part</code> 只包小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：暴力替换

我们循环判断 $s$ 中是否存在字符串 $part$，是则进行一次替换，继续循环此操作，直至 $s$ 中不存在字符串 $part$，返回此时的 $s$ 作为答案字符串。

时间复杂度 $O(n^2 + n \times m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是字符串 $s$ 和字符串 $part$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeOccurrences(self, s: str, part: str) -> str:
        while part in s:
            s = s.replace(part, '', 1)
        return s
```

#### Java

```java
class Solution {
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            s = s.replaceFirst(part, "");
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeOccurrences(string s, string part) {
        int m = part.size();
        while (s.find(part) != -1) {
            s = s.erase(s.find(part), m);
        }
        return s;
    }
};
```

#### Go

```go
func removeOccurrences(s string, part string) string {
	for strings.Contains(s, part) {
		s = strings.Replace(s, part, "", 1)
	}
	return s
}
```

#### TypeScript

```ts
function removeOccurrences(s: string, part: string): string {
    while (s.includes(part)) {
        s = s.replace(part, '');
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
