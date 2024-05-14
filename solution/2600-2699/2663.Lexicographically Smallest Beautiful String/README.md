# [2663. 字典序最小的美丽字符串](https://leetcode.cn/problems/lexicographically-smallest-beautiful-string)

[English Version](/solution/2600-2699/2663.Lexicographically%20Smallest%20Beautiful%20String/README_EN.md)

<!-- tags:贪心,字符串 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个字符串满足以下条件，则称其为 <strong>美丽字符串</strong> ：</p>

<ul>
	<li>它由英语小写字母表的前 <code>k</code> 个字母组成。</li>
	<li>它不包含任何长度为 <code>2</code> 或更长的回文子字符串。</li>
</ul>

<p>给你一个长度为 <code>n</code> 的美丽字符串 <code>s</code> 和一个正整数 <code>k</code> 。</p>

<p>请你找出并返回一个长度为 <code>n</code> 的美丽字符串，该字符串还满足：在字典序大于 <code>s</code> 的所有美丽字符串中字典序最小。如果不存在这样的字符串，则返回一个空字符串。</p>

<p>对于长度相同的两个字符串 <code>a</code> 和 <code>b</code> ，如果字符串 <code>a</code> 在与字符串 <code>b</code> 不同的第一个位置上的字符字典序更大，则字符串 <code>a</code> 的字典序大于字符串 <code>b</code> 。</p>

<ul>
	<li>例如，<code>"abcd"</code> 的字典序比 <code>"abcc"</code> 更大，因为在不同的第一个位置（第四个字符）上 <code>d</code> 的字典序大于 <code>c</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcz", k = 26
<strong>输出：</strong>"abda"
<strong>解释：</strong>字符串 "abda" 既是美丽字符串，又满足字典序大于 "abcz" 。
可以证明不存在字符串同时满足字典序大于 "abcz"、美丽字符串、字典序小于 "abda" 这三个条件。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "dc", k = 4
<strong>输出：</strong>""
<strong>解释：</strong>可以证明，不存在既是美丽字符串，又字典序大于 "dc" 的字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>4 &lt;= k &lt;= 26</code></li>
	<li><code>s</code> 是一个美丽字符串</li>
</ul>

## 解法

### 方法一：贪心

我们可以发现，一个长度为 $2$ 的回文字符串，其相邻两个字符一定相同；而一个长度为 $3$ 的回文字符串，其首尾两个字符一定相同。因此，一个美丽字符串不包含任何长度为 $2$ 或更长的回文子字符串，意味着该字符串的每个字符与其相邻的前两个字符都不相同。

我们可以贪心地从字符串的最后一个下标开始往前查找，找到一个下标 $i$，使得下标 $i$ 处的字符可以被替换为一个比其稍大的字符，同时保证其与其相邻的前两个字符都不相同的字符 $c$。

-   若找到了这样的下标 $i$，那么我们将 $s[i]$ 替换为 $c$，并将 $s[i+1]$ 到 $s[n-1]$ 的字符依次替换为字典序尽可能小的、不与前面相邻两个字符相同的、且在字母表前 $k$ 个字符中的字符，替换完成后，我们就得到了一个字典序最小的且大于 $s$ 的美丽字符串。
-   若找不到这样的下标 $i$，那么我们就无法构造出字典序大于 $s$ 的美丽字符串，因此返回空字符串。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def smallestBeautifulString(self, s: str, k: int) -> str:
        n = len(s)
        cs = list(s)
        for i in range(n - 1, -1, -1):
            p = ord(cs[i]) - ord('a') + 1
            for j in range(p, k):
                c = chr(ord('a') + j)
                if (i > 0 and cs[i - 1] == c) or (i > 1 and cs[i - 2] == c):
                    continue
                cs[i] = c
                for l in range(i + 1, n):
                    for m in range(k):
                        c = chr(ord('a') + m)
                        if (l > 0 and cs[l - 1] == c) or (l > 1 and cs[l - 2] == c):
                            continue
                        cs[l] = c
                        break
                return ''.join(cs)
        return ''
```

```java
class Solution {
    public String smallestBeautifulString(String s, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = n - 1; i >= 0; --i) {
            int p = cs[i] - 'a' + 1;
            for (int j = p; j < k; ++j) {
                char c = (char) ('a' + j);
                if ((i > 0 && cs[i - 1] == c) || (i > 1 && cs[i - 2] == c)) {
                    continue;
                }
                cs[i] = c;
                for (int l = i + 1; l < n; ++l) {
                    for (int m = 0; m < k; ++m) {
                        c = (char) ('a' + m);
                        if ((l > 0 && cs[l - 1] == c) || (l > 1 && cs[l - 2] == c)) {
                            continue;
                        }
                        cs[l] = c;
                        break;
                    }
                }
                return String.valueOf(cs);
            }
        }
        return "";
    }
}
```

```cpp
class Solution {
public:
    string smallestBeautifulString(string s, int k) {
        int n = s.size();
        for (int i = n - 1; i >= 0; --i) {
            int p = s[i] - 'a' + 1;
            for (int j = p; j < k; ++j) {
                char c = (char) ('a' + j);
                if ((i > 0 && s[i - 1] == c) || (i > 1 && s[i - 2] == c)) {
                    continue;
                }
                s[i] = c;
                for (int l = i + 1; l < n; ++l) {
                    for (int m = 0; m < k; ++m) {
                        c = (char) ('a' + m);
                        if ((l > 0 && s[l - 1] == c) || (l > 1 && s[l - 2] == c)) {
                            continue;
                        }
                        s[l] = c;
                        break;
                    }
                }
                return s;
            }
        }
        return "";
    }
};
```

```go
func smallestBeautifulString(s string, k int) string {
	cs := []byte(s)
	n := len(cs)
	for i := n - 1; i >= 0; i-- {
		p := int(cs[i] - 'a' + 1)
		for j := p; j < k; j++ {
			c := byte('a' + j)
			if (i > 0 && cs[i-1] == c) || (i > 1 && cs[i-2] == c) {
				continue
			}
			cs[i] = c
			for l := i + 1; l < n; l++ {
				for m := 0; m < k; m++ {
					c = byte('a' + m)
					if (l > 0 && cs[l-1] == c) || (l > 1 && cs[l-2] == c) {
						continue
					}
					cs[l] = c
					break
				}
			}
			return string(cs)
		}
	}
	return ""
}
```

```ts
function smallestBeautifulString(s: string, k: number): string {
    const cs: string[] = s.split('');
    const n = cs.length;
    for (let i = n - 1; i >= 0; --i) {
        const p = cs[i].charCodeAt(0) - 97 + 1;
        for (let j = p; j < k; ++j) {
            let c = String.fromCharCode(j + 97);
            if ((i > 0 && cs[i - 1] === c) || (i > 1 && cs[i - 2] === c)) {
                continue;
            }
            cs[i] = c;
            for (let l = i + 1; l < n; ++l) {
                for (let m = 0; m < k; ++m) {
                    c = String.fromCharCode(m + 97);
                    if ((l > 0 && cs[l - 1] === c) || (l > 1 && cs[l - 2] === c)) {
                        continue;
                    }
                    cs[l] = c;
                    break;
                }
            }
            return cs.join('');
        }
    }
    return '';
}
```

<!-- tabs:end -->

<!-- end -->
