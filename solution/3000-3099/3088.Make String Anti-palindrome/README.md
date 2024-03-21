# [3088. 使字符串反回文](https://leetcode.cn/problems/make-string-anti-palindrome)

[English Version](/solution/3000-3099/3088.Make%20String%20Anti-palindrome/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个长度为偶数的字符串&nbsp;<code>s</code>&nbsp;为&nbsp;<strong>反回文</strong>&nbsp;的，如果对于每一个下标&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;，<code>s[i] != s[n - i - 1]</code>。</p>

<p>给定一个字符串&nbsp;<code>s</code>，你需要进行&nbsp;<strong>任意</strong> 次（包括 0）操作使&nbsp;<code>s</code>&nbsp;成为 <strong>反回文。</strong></p>

<p>在一次操作中，你可以选择&nbsp;<code>s</code>&nbsp;中的两个字符并且交换它们。</p>

<p>返回结果字符串。如果有多个字符串符合条件，返回 <span data-keyword="lexicographically-smaller-string">字典序最小</span> 的那个。如果它不能成为一个反回文，返回&nbsp;<code>"-1"</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "abca"</span></p>

<p><strong>输出：</strong><span class="example-io">"aabc"</span></p>

<p><strong>解释：</strong></p>

<p><code>"aabc"</code> 是一个反回文字符串，因为 <code>s[0] != s[3]</code> 并且&nbsp;<code>s[1] != s[2]</code>。同时，它也是 <code>"abca"</code>&nbsp;的一个重排。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "abba"</span></p>

<p><b>输出：</b><span class="example-io">"aabb"</span></p>

<p><b>解释：</b></p>

<p><code>"aabb"</code>&nbsp;是一个反回文字符串，因为&nbsp;<code>s[0] != s[3]</code>&nbsp;并且&nbsp;<code>s[1] != s[2]</code>。同时，它也是&nbsp;<code>"abba"</code>&nbsp;的一个重排。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "cccd"</span></p>

<p><strong>输出：</strong><span class="example-io">"-1"</span></p>

<p><strong>解释：</strong></p>

<p>你可以发现无论你如何重排&nbsp;<code>"cccd"</code>&nbsp;的字符，都有&nbsp;<code>s[0] == s[3]</code>&nbsp;或&nbsp;<code>s[1] == s[2]</code>。所以它不能形成一个反回文字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s.length % 2 == 0</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：贪心 + 排序

题目要求我们将字符串 $s$ 变成字典序最小的反回文字符串，我们不妨先对字符串 $s$ 进行排序。

接下来，我们只需要比较中间的两个字符 $s[m]$ 和 $s[m-1]$ 是否相等，如果相等，我们就在后半部分找到第一个不等于 $s[m]$ 的字符 $s[i]$，用一个指针 $j$ 指向 $m$，然后交换 $s[i]$ 和 $s[j]$。如果找不到这样的字符 $s[i]$，说明字符串 $s$ 无法变成反回文字符串，返回 `"1"`。否则，执行交换操作，并向右移动 $i$ 和 $j$，比较 $s[j]$ 和 $s[n-j-1]$ 是否相等，如果相等，继续执行交换操作，直到 $i$ 超出字符串长度。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def makeAntiPalindrome(self, s: str) -> str:
        cs = sorted(s)
        n = len(cs)
        m = n // 2
        if cs[m] == cs[m - 1]:
            i = m
            while i < n and cs[i] == cs[i - 1]:
                i += 1
            j = m
            while j < n and cs[j] == cs[n - j - 1]:
                if i >= n:
                    return "-1"
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j + 1
        return "".join(cs)
```

```java
class Solution {
    public String makeAntiPalindrome(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        int n = cs.length;
        int m = n / 2;
        if (cs[m] == cs[m - 1]) {
            int i = m;
            while (i < n && cs[i] == cs[i - 1]) {
                ++i;
            }
            for (int j = m; j < n && cs[j] == cs[n - j - 1]; ++i, ++j) {
                if (i >= n) {
                    return "-1";
                }
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
            }
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string makeAntiPalindrome(string s) {
        sort(s.begin(), s.end());
        int n = s.length();
        int m = n / 2;
        if (s[m] == s[m - 1]) {
            int i = m;
            while (i < n && s[i] == s[i - 1]) {
                ++i;
            }
            for (int j = m; j < n && s[j] == s[n - j - 1]; ++i, ++j) {
                if (i >= n) {
                    return "-1";
                }
                swap(s[i], s[j]);
            }
        }
        return s;
    }
};
```

```go
func makeAntiPalindrome(s string) string {
	cs := []byte(s)
	sort.Slice(cs, func(i, j int) bool { return cs[i] < cs[j] })
	n := len(cs)
	m := n / 2
	if cs[m] == cs[m-1] {
		i := m
		for i < n && cs[i] == cs[i-1] {
			i++
		}
		for j := m; j < n && cs[j] == cs[n-j-1]; i, j = i+1, j+1 {
			if i >= n {
				return "-1"
			}
			cs[i], cs[j] = cs[j], cs[i]
		}
	}
	return string(cs)
}
```

```ts
function makeAntiPalindrome(s: string): string {
    const cs: string[] = s.split('').sort();
    const n: number = cs.length;
    const m = n >> 1;
    if (cs[m] === cs[m - 1]) {
        let i = m;
        for (; i < n && cs[i] === cs[i - 1]; i++);
        for (let j = m; j < n && cs[j] === cs[n - j - 1]; ++i, ++j) {
            if (i >= n) {
                return '-1';
            }
            [cs[j], cs[i]] = [cs[i], cs[j]];
        }
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- end -->
