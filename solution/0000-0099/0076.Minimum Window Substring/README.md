---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0076.Minimum%20Window%20Substring/README.md
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring)

[English Version](/solution/0000-0099/0076.Minimum%20Window%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li>
	<li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
<strong>解释：</strong>最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
<strong>解释：</strong>整个字符串 s 是最小覆盖子串。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", t = "aa"
<strong>输出:</strong> ""
<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code><sup>m == s.length</sup></code></li>
	<li><code><sup>n == t.length</sup></code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p>&nbsp;</p>
<strong>进阶：</strong>你能设计一个在 <code>o(m+n)</code> 时间内解决此问题的算法吗？

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 双指针

我们用一个哈希表或数组 $\textit{need}$ 统计字符串 $t$ 中每个字符出现的次数，用另一个哈希表或数组 $\textit{window}$ 统计滑动窗口中每个字符出现的次数。另外，定义两个指针 $l$ 和 $r$ 分别指向窗口的左右边界，变量 $\textit{cnt}$ 表示窗口中已经包含了 $t$ 中的多少个字符，变量 $k$ 和 $\textit{mi}$ 分别表示最小覆盖子串的起始位置和长度。

我们从左到右遍历字符串 $s$，对于当前遍历到的字符 $s[r]$：

-   我们将其加入窗口中，即 $\textit{window}[s[r]] = \textit{window}[s[r]] + 1$，如果此时 $\textit{need}[s[r]] \geq \textit{window}[s[r]]$，则说明 $s[r]$ 是一个「必要的字符」，我们将 $\textit{cnt}$ 加一。
-   如果 $\textit{cnt}$ 等于 $t$ 的长度，说明此时窗口中已经包含了 $t$ 中的所有字符，我们就可以尝试更新最小覆盖子串的起始位置和长度了。如果 $r - l + 1 < \textit{mi}$，说明当前窗口表示的子串更短，我们就更新 $\textit{mi} = r - l + 1$ 和 $k = l$。
-   然后，我们尝试移动左边界 $l$，如果此时 $\textit{need}[s[l]] \geq \textit{window}[s[l]]$，则说明 $s[l]$ 是一个「必要的字符」，移动左边界时会把 $s[l]$ 这个字符从窗口中移除，因此我们需要将 $\textit{cnt}$ 减一，然后更新 $\textit{window}[s[l]] = \textit{window}[s[l]] - 1$，并将 $l$ 右移一位。
-   如果 $\textit{cnt}$ 与 $t$ 的长度不相等，说明此时窗口中还没有包含 $t$ 中的所有字符，我们就不需要移动左边界了，直接将 $r$ 右移一位，继续遍历即可。

遍历结束，如果没有找到最小覆盖子串，返回空字符串，否则返回 $s[k:k+\textit{mi}]$ 即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(|\Sigma|)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度；而 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma| = 128$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        need = Counter(t)
        window = Counter()
        cnt = l = 0
        k, mi = -1, inf
        for r, c in enumerate(s):
            window[c] += 1
            if need[c] >= window[c]:
                cnt += 1
            while cnt == len(t):
                if r - l + 1 < mi:
                    mi = r - l + 1
                    k = l
                if need[s[l]] >= window[s[l]]:
                    cnt -= 1
                window[s[l]] -= 1
                l += 1
        return "" if k < 0 else s[k : k + mi]
```

#### Java

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        for (char c : t.toCharArray()) {
            ++need[c];
        }
        int m = s.length(), n = t.length();
        int k = -1, mi = m + 1, cnt = 0;
        for (int l = 0, r = 0; r < m; ++r) {
            char c = s.charAt(r);
            if (++window[c] <= need[c]) {
                ++cnt;
            }
            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }
                c = s.charAt(l);
                if (window[c] <= need[c]) {
                    --cnt;
                }
                --window[c];
                ++l;
            }
        }
        return k < 0 ? "" : s.substring(k, k + mi);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string minWindow(string s, string t) {
        vector<int> need(128, 0);
        vector<int> window(128, 0);
        for (char c : t) {
            ++need[c];
        }

        int m = s.length(), n = t.length();
        int k = -1, mi = m + 1, cnt = 0;

        for (int l = 0, r = 0; r < m; ++r) {
            char c = s[r];
            if (++window[c] <= need[c]) {
                ++cnt;
            }

            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }

                c = s[l];
                if (window[c] <= need[c]) {
                    --cnt;
                }
                --window[c];
                ++l;
            }
        }

        return k < 0 ? "" : s.substr(k, mi);
    }
};
```

#### Go

```go
func minWindow(s string, t string) string {
	need := make([]int, 128)
	window := make([]int, 128)
	for i := 0; i < len(t); i++ {
		need[t[i]]++
	}

	m, n := len(s), len(t)
	k, mi, cnt := -1, m+1, 0

	for l, r := 0, 0; r < m; r++ {
		c := s[r]
		if window[c]++; window[c] <= need[c] {
			cnt++
		}
		for cnt == n {
			if r-l+1 < mi {
				mi = r - l + 1
				k = l
			}

			c = s[l]
			if window[c] <= need[c] {
				cnt--
			}
			window[c]--
			l++
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mi]
}
```

#### TypeScript

```ts
function minWindow(s: string, t: string): string {
    const need: number[] = Array(128).fill(0);
    const window: number[] = Array(128).fill(0);
    for (let i = 0; i < t.length; i++) {
        need[t.charCodeAt(i)]++;
    }
    const [m, n] = [s.length, t.length];
    let [k, mi, cnt] = [-1, m + 1, 0];
    for (let l = 0, r = 0; r < m; r++) {
        let c = s.charCodeAt(r);
        if (++window[c] <= need[c]) {
            cnt++;
        }
        while (cnt === n) {
            if (r - l + 1 < mi) {
                mi = r - l + 1;
                k = l;
            }

            c = s.charCodeAt(l);
            if (window[c] <= need[c]) {
                cnt--;
            }
            window[c]--;
            l++;
        }
    }
    return k < 0 ? '' : s.substring(k, k + mi);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let mut need: HashMap<char, usize> = HashMap::new();
        let mut window: HashMap<char, usize> = HashMap::new();
        for c in t.chars() {
            *need.entry(c).or_insert(0) += 1;
        }
        let m = s.len();
        let n = t.len();
        let mut k = -1;
        let mut mi = m + 1;
        let mut cnt = 0;

        let s_bytes = s.as_bytes();
        let mut l = 0;
        for r in 0..m {
            let c = s_bytes[r] as char;
            *window.entry(c).or_insert(0) += 1;
            if window[&c] <= *need.get(&c).unwrap_or(&0) {
                cnt += 1;
            }
            while cnt == n {
                if r - l + 1 < mi {
                    mi = r - l + 1;
                    k = l as i32;
                }

                let c = s_bytes[l] as char;
                if window[&c] <= *need.get(&c).unwrap_or(&0) {
                    cnt -= 1;
                }
                *window.entry(c).or_insert(0) -= 1;
                l += 1;
            }
        }
        if k < 0 {
            return String::new();
        }
        s[k as usize..(k as usize + mi)].to_string()
    }
}
```

#### C#

```cs
public class Solution {
    public string MinWindow(string s, string t) {
        int[] need = new int[128];
        int[] window = new int[128];

        foreach (var c in t) {
            need[c]++;
        }

        int m = s.Length, n = t.Length;
        int k = -1, mi = m + 1, cnt = 0;

        int l = 0;
        for (int r = 0; r < m; r++) {
            char c = s[r];
            window[c]++;

            if (window[c] <= need[c]) {
                cnt++;
            }

            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }

                c = s[l];
                if (window[c] <= need[c]) {
                    cnt--;
                }
                window[c]--;
                l++;
            }
        }

        return k < 0 ? "" : s.Substring(k, mi);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
