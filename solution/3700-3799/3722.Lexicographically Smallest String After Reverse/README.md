---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3722.Lexicographically%20Smallest%20String%20After%20Reverse/README.md
rating: 1414
source: 第 168 场双周赛 Q1
tags:
    - 双指针
    - 二分查找
    - 枚举
---

<!-- problem:start -->

# [3722. 反转后字典序最小的字符串](https://leetcode.cn/problems/lexicographically-smallest-string-after-reverse)

[English Version](/solution/3700-3799/3722.Lexicographically%20Smallest%20String%20After%20Reverse/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的、长度为 <code>n</code> 的字符串 <code>s</code>。</p>

<p>你 必须执行 <strong>恰好&nbsp;</strong>一次操作：选择一个整数 <code>k</code>，满足 <code>1 &lt;= k &lt;= n</code>，然后执行以下两个选项之一：</p>

<ul>
	<li>反转 <code>s</code> 的 <strong>前</strong>&nbsp;<code>k</code> 个字符，或</li>
	<li>反转 <code>s</code> 的&nbsp;<strong>后</strong>&nbsp;<code>k</code> 个字符。</li>
</ul>

<p>返回在 <strong>恰好</strong>&nbsp;执行一次此类操作后可以获得的 <strong>字典序最小&nbsp;</strong>的字符串。</p>

<p>如果字符串 <code>a</code> 和字符串 <code>b</code> 在第一个不同的位置上，<code>a</code> 中的字母在字母表中比 <code>b</code> 中对应的字母出现得更早，则称字符串 <code>a</code>&nbsp;<strong>字典序小于&nbsp;</strong>字符串 <code>b</code>。如果前 <code>min(a.length, b.length)</code> 个字符都相同，则较短的字符串字典序较小。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "dcab"</span></p>

<p><strong>输出:</strong> <span class="example-io">"acdb"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 3</code>，反转前 3 个字符。</li>
	<li>将 <code>"dca"</code> 反转为 <code>"acd"</code>，得到的字符串 <code>s = "acdb"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abba"</span></p>

<p><strong>输出:</strong> <span class="example-io">"aabb"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 3</code>，反转后 3 个字符。</li>
	<li>将 <code>"bba"</code> 反转为 <code>"abb"</code>，得到的字符串是 <code>"aabb"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "zxy"</span></p>

<p><strong>输出:</strong> <span class="example-io">"xzy"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 2</code>，反转前 2 个字符。</li>
	<li>将 <code>"zx"</code> 反转为 <code>"xz"</code>，得到的字符串是 <code>"xzy"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 1000</code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举所有可能的 $k$ 值（$1 \leq k \leq n$），对于每个 $k$，我们计算反转前 $k$ 个字符和反转后 $k$ 个字符所得到的字符串，然后取其中字典序最小的字符串作为最终答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexSmallest(self, s: str) -> str:
        ans = s
        for k in range(1, len(s) + 1):
            t1 = s[:k][::-1] + s[k:]
            t2 = s[:-k] + s[-k:][::-1]
            ans = min(ans, t1, t2)
        return ans
```

#### Java

```java
class Solution {
    public String lexSmallest(String s) {
        String ans = s;
        int n = s.length();
        for (int k = 1; k <= n; ++k) {
            String t1 = new StringBuilder(s.substring(0, k)).reverse().toString() + s.substring(k);
            String t2 = s.substring(0, n - k)
                + new StringBuilder(s.substring(n - k)).reverse().toString();
            if (t1.compareTo(ans) < 0) {
                ans = t1;
            }
            if (t2.compareTo(ans) < 0) {
                ans = t2;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string lexSmallest(string s) {
        string ans = s;
        int n = s.size();
        for (int k = 1; k <= n; ++k) {
            string t1 = s.substr(0, k);
            reverse(t1.begin(), t1.end());
            t1 += s.substr(k);

            string t2 = s.substr(0, n - k);
            string suffix = s.substr(n - k);
            reverse(suffix.begin(), suffix.end());
            t2 += suffix;

            ans = min({ans, t1, t2});
        }
        return ans;
    }
};
```

#### Go

```go
func lexSmallest(s string) string {
	ans := s
	n := len(s)
	for k := 1; k <= n; k++ {
		t1r := []rune(s[:k])
		slices.Reverse(t1r)
		t1 := string(t1r) + s[k:]

		t2r := []rune(s[n-k:])
		slices.Reverse(t2r)
		t2 := s[:n-k] + string(t2r)

		ans = min(ans, t1, t2)
	}
	return ans
}
```

#### TypeScript

```ts
function lexSmallest(s: string): string {
    let ans = s;
    const n = s.length;
    for (let k = 1; k <= n; ++k) {
        const t1 = reverse(s.slice(0, k)) + s.slice(k);
        const t2 = s.slice(0, n - k) + reverse(s.slice(n - k));
        ans = [ans, t1, t2].sort()[0];
    }
    return ans;
}

function reverse(s: string): string {
    return s.split('').reverse().join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
