---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0647.Palindromic%20Substrings/README.md
tags:
    - 双指针
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [647. 回文子串](https://leetcode.cn/problems/palindromic-substrings)

[English Version](/solution/0600-0699/0647.Palindromic%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，请你统计并返回这个字符串中 <strong>回文子串</strong> 的数目。</p>

<p><strong>回文字符串</strong> 是正着读和倒过来读一样的字符串。</p>

<p><strong>子字符串</strong> 是字符串中的由连续字符组成的一个序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abc"
<strong>输出：</strong>3
<strong>解释：</strong>三个回文子串: "a", "b", "c"
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaa"
<strong>输出：</strong>6
<strong>解释：</strong>6个回文子串: "a", "a", "a", "aa", "aa", "aaa"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：从中心向两侧扩展回文串

我们可以枚举回文串的中心位置，然后向两侧扩展，统计回文串的数量。对于长度为 $n$ 的字符串，回文串的中心位置共有 $2n-1$ 个（包括奇数长度和偶数长度的回文串）。对于每个中心位置，我们向两侧扩展，直到不满足回文串的条件为止，统计回文串的数量。

时间复杂度 $O(n^2)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        ans, n = 0, len(s)
        for k in range(n * 2 - 1):
            i, j = k // 2, (k + 1) // 2
            while ~i and j < n and s[i] == s[j]:
                ans += 1
                i, j = i - 1, j + 1
        return ans
```

#### Java

```java
class Solution {
    public int countSubstrings(String s) {
        int ans = 0;
        int n = s.length();
        for (int k = 0; k < n * 2 - 1; ++k) {
            int i = k / 2, j = (k + 1) / 2;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                ++ans;
                --i;
                ++j;
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
    int countSubstrings(string s) {
        int ans = 0;
        int n = s.size();
        for (int k = 0; k < n * 2 - 1; ++k) {
            int i = k / 2, j = (k + 1) / 2;
            while (~i && j < n && s[i] == s[j]) {
                ++ans;
                --i;
                ++j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSubstrings(s string) int {
	ans, n := 0, len(s)
	for k := 0; k < n*2-1; k++ {
		i, j := k/2, (k+1)/2
		for i >= 0 && j < n && s[i] == s[j] {
			ans++
			i, j = i-1, j+1
		}
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var countSubstrings = function (s) {
    let ans = 0;
    const n = s.length;
    for (let k = 0; k < n * 2 - 1; ++k) {
        let i = k >> 1;
        let j = (k + 1) >> 1;
        while (~i && j < n && s[i] == s[j]) {
            ++ans;
            --i;
            ++j;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：Manacher 算法

在 Manacher 算法的计算过程中，用 $p[i]-1$ 表示以第 $i$ 位为中心的最大回文长度，以第 $i$ 位为中心的回文串数量为 $\left \lceil \frac{p[i]-1}{2}  \right \rceil$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        t = '^#' + '#'.join(s) + '#$'
        n = len(t)
        p = [0 for _ in range(n)]
        pos, maxRight = 0, 0
        ans = 0
        for i in range(1, n - 1):
            p[i] = min(maxRight - i, p[2 * pos - i]) if maxRight > i else 1
            while t[i - p[i]] == t[i + p[i]]:
                p[i] += 1
            if i + p[i] > maxRight:
                maxRight = i + p[i]
                pos = i
            ans += p[i] // 2
        return ans
```

#### Java

```java
class Solution {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder("^#");
        for (char ch : s.toCharArray()) {
            sb.append(ch).append('#');
        }
        String t = sb.append('$').toString();
        int n = t.length();
        int[] p = new int[n];
        int pos = 0, maxRight = 0;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            p[i] = maxRight > i ? Math.min(maxRight - i, p[2 * pos - i]) : 1;
            while (t.charAt(i - p[i]) == t.charAt(i + p[i])) {
                p[i]++;
            }
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }
            ans += p[i] / 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSubstrings(string s) {
        string t = "^#";
        for (char c : s) {
            t += c;
            t += '#';
        }
        t += "$";

        int n = t.size();
        vector<int> p(n, 0);
        int pos = 0, maxRight = 0;
        int ans = 0;

        for (int i = 1; i < n - 1; ++i) {
            if (maxRight > i) {
                p[i] = min(maxRight - i, p[2 * pos - i]);
            } else {
                p[i] = 1;
            }

            while (t[i - p[i]] == t[i + p[i]]) {
                ++p[i];
            }

            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }

            ans += p[i] / 2;
        }

        return ans;
    }
};
```

#### Go

```go
func countSubstrings(s string) int {
	t := "^#"
	for _, c := range s {
		t += string(c)
		t += "#"
	}
	t += "$"

	n := len(t)
	p := make([]int, n)
	pos, maxRight := 0, 0
	ans := 0

	for i := 1; i < n-1; i++ {
		if maxRight > i {
			mirror := 2*pos - i
			if p[mirror] < maxRight-i {
				p[i] = p[mirror]
			} else {
				p[i] = maxRight - i
			}
		} else {
			p[i] = 1
		}

		for t[i-p[i]] == t[i+p[i]] {
			p[i]++
		}

		if i+p[i] > maxRight {
			maxRight = i + p[i]
			pos = i
		}

		ans += p[i] / 2
	}

	return ans
}
```

#### TypeScript

```ts
function countSubstrings(s: string): number {
    let t = "^#";
    for (const c of s) {
        t += c + "#";
    }
    t += "$";

    const n = t.length;
    const p: number[] = new Array(n).fill(0);
    let pos = 0, maxRight = 0;
    let ans = 0;

    for (let i = 1; i < n - 1; i++) {
        if (maxRight > i) {
            p[i] = Math.min(maxRight - i, p[2 * pos - i]);
        } else {
            p[i] = 1;
        }

        while (t[i - p[i]] === t[i + p[i]]) {
            p[i]++;
        }

        if (i + p[i] > maxRight) {
            maxRight = i + p[i];
            pos = i;
        }

        ans += Math.floor(p[i] / 2);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
