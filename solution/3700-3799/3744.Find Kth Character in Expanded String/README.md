---
comments: true
difficulty: 中等
tags:
    - 字符串
---

<!-- problem:start -->

# [3744. 在展开字符串中查找第 K 个字符 🔒](https://leetcode.cn/problems/find-kth-character-in-expanded-string)

[English Version](/solution/3700-3799/3744.Find%20Kth%20Character%20in%20Expanded%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>s</code>，该字符串由一个或多个单词组成，单词之间用单个空格分隔。<code>s</code> 中的每个单词均由小写的英文字母组成。</p>

<p>我们按如下步骤从&nbsp;<code>s</code>&nbsp;得到 <strong>展开</strong>&nbsp;字符串 <code>t</code>：</p>

<ul>
	<li>对于&nbsp;<code>s</code>&nbsp;中的每个 <strong>单词</strong>，重复一次它的第一个字符，然后重复两次它的第二个字符，以此类推。</li>
</ul>

<p>例如，如果&nbsp;<code>s = "hello world"</code>，那么&nbsp;<code>t = "heelllllllooooo woorrrllllddddd"</code>。</p>

<p>同时给定一个整数&nbsp;<code>k</code>，表示字符串&nbsp;<code>t</code>&nbsp;的一个 <strong>合法</strong>&nbsp;下标。</p>

<p>返回字符串&nbsp;<code>t</code>&nbsp;的第&nbsp;<code>k</code> 个字符。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "hello world", k = 0</span></p>

<p><span class="example-io"><b>输出：</b>"h"</span></p>

<p><strong>解释：</strong></p>

<p><code>t = "heelllllllooooo woorrrllllddddd"</code>。因此，答案是&nbsp;<code>t[0] = "h"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "hello world", k = 15</span></p>

<p><span class="example-io"><b>输出：</b>" "</span></p>

<p><strong>解释：</strong></p>

<p><code>t = "heelllllllooooo woorrrllllddddd"</code>。因此，答案是 <code>t[15] = " "</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母和空格&nbsp;<code>' '</code>。</li>
	<li><code>s</code> <strong>不包含</strong>&nbsp;任何前导和后缀空格。</li>
	<li><code>s</code>&nbsp;中的所有单词都由 <strong>一个空格</strong>&nbsp;分隔。</li>
	<li><code>0 &lt;= k &lt; t.length</code>。即&nbsp;<code>k</code>&nbsp;是 <code>t</code>&nbsp;的一个&nbsp;<strong>合法&nbsp;</strong>下标。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 模拟

<!-- thinking:start -->

> **思考**
>
> 展开串可能远长于 $k$，不应真正构造。第 $i$ 个字母重复 $i+1$ 次，单词展开长度是三角形数。按单词扣除长度（含空格），落入某单词后再扫一遍字母累计重复次数即可定位。

<!-- thinking:end -->

我们首先将字符串 $\textit{s}$ 按空格拆分成若干单词。对于每个单词 $\textit{w}$，我们可以计算出它在展开字符串 $\textit{t}$ 中所占的长度 $m=\frac{(1+|\textit{w}|)\cdot |\textit{w}|}{2}$。

如果 $k = m$，说明第 $k$ 个字符是空格，直接返回空格即可。

如果 $k > m$，说明第 $k$ 个字符不在当前单词的展开部分，我们将 $k$ 减去当前单词的展开长度 $m$ 和空格的长度 $1$，继续处理下一个单词。

否则，第 $k$ 个字符在当前单词的展开部分。我们可以通过模拟展开过程来找到第 $k$ 个字符：

- 初始化变量 $\textit{cur} = 0$，表示当前已经展开的字符数。
- 遍历单词 $\textit{w}$ 的每个字符 $\textit{w}[i]$：
    - 将 $\textit{cur}$ 增加 $i + 1$。
    - 如果 $k < \textit{cur}$，说明第 $k$ 个字符就是 $\textit{w}[i]$，返回该字符。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $\textit{s}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthCharacter(self, s: str, k: int) -> str:
        for w in s.split():
            m = (1 + len(w)) * len(w) // 2
            if k == m:
                return " "
            if k > m:
                k -= m + 1
            else:
                cur = 0
                for i in range(len(w)):
                    cur += i + 1
                    if k < cur:
                        return w[i]
```

#### Java

```java
class Solution {
    public char kthCharacter(String s, long k) {
        for (String w : s.split(" ")) {
            long m = (1L + w.length()) * w.length() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w.charAt(i);
                    }
                }
            }
        }
        return ' ';
    }
}
```

#### C++

```cpp
class Solution {
public:
    char kthCharacter(string s, long long k) {
        stringstream ss(s);
        string w;
        while (ss >> w) {
            long long m = (1 + (long long) w.size()) * (long long) w.size() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w[i];
                    }
                }
            }
        }
        return ' ';
    }
};
```

#### Go

```go
func kthCharacter(s string, k int64) byte {
	for _, w := range strings.Split(s, " ") {
		m := (1 + int64(len(w))) * int64(len(w)) / 2
		if k == m {
			return ' '
		}
		if k > m {
			k -= m + 1
		} else {
			var cur int64
			for i := 0; ; i++ {
				cur += int64(i + 1)
				if k < cur {
					return w[i]
				}
			}
		}
	}
	return ' '
}
```

#### TypeScript

```ts
function kthCharacter(s: string, k: number): string {
    for (const w of s.split(' ')) {
        const m = ((1 + w.length) * w.length) / 2;
        if (k === m) {
            return ' ';
        }
        if (k > m) {
            k -= m + 1;
        } else {
            let cur = 0;
            for (let i = 0; ; ++i) {
                cur += i + 1;
                if (k < cur) {
                    return w[i];
                }
            }
        }
    }
    return ' ';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
