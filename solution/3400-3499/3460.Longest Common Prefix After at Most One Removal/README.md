---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3460.Longest%20Common%20Prefix%20After%20at%20Most%20One%20Removal/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [3460. 最多删除一次后的最长公共前缀 🔒](https://leetcode.cn/problems/longest-common-prefix-after-at-most-one-removal)

[English Version](/solution/3400-3499/3460.Longest%20Common%20Prefix%20After%20at%20Most%20One%20Removal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>。</p>

<p>返回从&nbsp;<code>s</code>&nbsp;<strong>最多 </strong>删除一个字母后，<code>s</code> 和&nbsp;<code>t</code>&nbsp;的 <strong>最长公共 <span data-keyword="string-prefix">前缀</span></strong>&nbsp;的 <strong>长度</strong>。</p>

<p><strong>注意：</strong>可以保留<strong>&nbsp;</strong><code>s</code>&nbsp;而不做任何删除。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "madxa", t = "madam"</span></p>

<p><strong>输出：</strong><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>从&nbsp;<code>s</code>&nbsp;删除&nbsp;<code>s[3]</code>&nbsp;得到&nbsp;<code>"mada"</code>，与 <code>t</code>&nbsp;的最长公共前缀长度为 4。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "leetcode", t = "eetcode"</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><strong>解释：</strong></p>

<p>从&nbsp;<code>s</code>&nbsp;删除&nbsp;<code>s[0]</code>&nbsp;得到&nbsp;<code>"eetcode"</code>，与&nbsp;<code>t</code>&nbsp;匹配。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "one", t = "one"</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>不需要删除。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "a", t = "b"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;不可能有公共前缀。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们记录字符串 $s$ 和 $t$ 的长度分别为 $n$ 和 $m$，然后用两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 和 $t$ 的开头，用一个布尔变量 $\textit{rem}$ 记录是否已经删除过字符。

接下来，我们开始遍历字符串 $s$ 和 $t$，如果 $s[i]$ 不等于 $t[j]$，我们就判断是否已经删除过字符，如果已经删除过字符，我们就退出循环，否则我们标记已经删除过字符，然后跳过 $s[i]$；否则，我们跳过 $s[i]$ 和 $t[j]$。继续遍历，直到 $i \geq n$ 或 $j \geq m$。

最后返回 $j$ 即可。

时间复杂度 $O(n+m)$，其中 $n$ 和 $m$ 分别是字符串 $s$ 和 $t$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonPrefix(self, s: str, t: str) -> int:
        n, m = len(s), len(t)
        i = j = 0
        rem = False
        while i < n and j < m:
            if s[i] != t[j]:
                if rem:
                    break
                rem = True
            else:
                j += 1
            i += 1
        return j
```

#### Java

```java
class Solution {
    public int longestCommonPrefix(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        boolean rem = false;
        while (i < n && j < m) {
            if (s.charAt(i) != t.charAt(j)) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestCommonPrefix(string s, string t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        bool rem = false;
        while (i < n && j < m) {
            if (s[i] != t[j]) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
};
```

#### Go

```go
func longestCommonPrefix(s string, t string) int {
	n, m := len(s), len(t)
	i, j := 0, 0
	rem := false
	for i < n && j < m {
		if s[i] != t[j] {
			if rem {
				break
			}
			rem = true
		} else {
			j++
		}
		i++
	}
	return j
}
```

#### TypeScript

```ts
function longestCommonPrefix(s: string, t: string): number {
    const [n, m] = [s.length, t.length];
    let [i, j] = [0, 0];
    let rem: boolean = false;
    while (i < n && j < m) {
        if (s[i] !== t[j]) {
            if (rem) {
                break;
            }
            rem = true;
        } else {
            ++j;
        }
        ++i;
    }
    return j;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_common_prefix(s: String, t: String) -> i32 {
        let (n, m) = (s.len(), t.len());
        let (mut i, mut j) = (0, 0);
        let mut rem = false;

        while i < n && j < m {
            if s.as_bytes()[i] != t.as_bytes()[j] {
                if rem {
                    break;
                }
                rem = true;
            } else {
                j += 1;
            }
            i += 1;
        }

        j as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var longestCommonPrefix = function (s, t) {
    const [n, m] = [s.length, t.length];
    let [i, j] = [0, 0];
    let rem = false;
    while (i < n && j < m) {
        if (s[i] !== t[j]) {
            if (rem) {
                break;
            }
            rem = true;
        } else {
            ++j;
        }
        ++i;
    }
    return j;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
