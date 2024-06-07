---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3104.Find%20Longest%20Self-Contained%20Substring/README.md
tags:
    - 哈希表
    - 字符串
    - 二分查找
    - 前缀和
---

<!-- problem:start -->

# [3104. Find Longest Self-Contained Substring 🔒](https://leetcode.cn/problems/find-longest-self-contained-substring)

[English Version](/solution/3100-3199/3104.Find%20Longest%20Self-Contained%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Given a string <code>s</code>, your task is to find the length of the <strong>longest self-contained</strong> <span data-keyword="substring-nonempty">substring</span> of <code>s</code>.</p>

<p>A substring <code>t</code> of a string <code>s</code> is called <strong>self-contained </strong>if <code>t != s</code> and for every character in <code>t</code>, it doesn&#39;t exist in the <em>rest</em> of <code>s</code>.</p>

<p>Return the length of the <em>longest<strong> </strong>self-contained </em>substring of <code>s</code> if it exists, otherwise, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
Let&#39;s check the substring <code>&quot;bb&quot;</code>. You can see that no other <code>&quot;b&quot;</code> is outside of this substring. Hence the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong><br />
Every substring we choose does not satisfy the described property (there is some character which is inside and outside of that substring). So the answer would be -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abacd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong><br />
Let&#39;s check the substring <code>&quot;<span class="example-io">abac</span>&quot;</code>. There is only one character outside of this substring and that is <code>&quot;d&quot;</code>. There is no <code>&quot;d&quot;</code> inside the chosen substring, so it satisfies the condition and the answer is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们注意到，满足条件的子串的开头一定是某个字符第一次出现的位置。

因此，我们可以用两个数组或哈希表 `first` 和 `last` 分别记录每个字符第一次和最后一次出现的位置。

接下来，我们枚举每个字符 `c`，假设 `c` 第一次出现的位置是 $i$，最后一次出现的位置是 $mx$，那么我们可以从 $i$ 开始向后遍历，对于每个位置 $j$，我们找到 $s[j]$ 第一次出现的位置 $a$ 和最后一次出现的位置 $b$，如果 $a < i$，说明 $s[j]$ 在 $c$ 的左边，不符合枚举条件，我们可以直接退出循环。否则，我们更新 $mx = \max(mx, b)$。如果 $mx = j$ 且 $j - i + 1 < n$，我们更新答案为 $ans = \max(ans, j - i + 1)$。

最后返回答案即可。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度；而 $|\Sigma|$ 是字符集的大小，本题中字符集为小写字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubstringLength(self, s: str) -> int:
        first, last = {}, {}
        for i, c in enumerate(s):
            if c not in first:
                first[c] = i
            last[c] = i
        ans, n = -1, len(s)
        for c, i in first.items():
            mx = last[c]
            for j in range(i, n):
                a, b = first[s[j]], last[s[j]]
                if a < i:
                    break
                mx = max(mx, b)
                if mx == j and j - i + 1 < n:
                    ans = max(ans, j - i + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubstringLength(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s.charAt(i) - 'a';
            if (first[j] == -1) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = -1;
        for (int k = 0; k < 26; ++k) {
            int i = first[k];
            if (i == -1) {
                continue;
            }
            int mx = last[k];
            for (int j = i; j < n; ++j) {
                int a = first[s.charAt(j) - 'a'];
                int b = last[s.charAt(j) - 'a'];
                if (a < i) {
                    break;
                }
                mx = Math.max(mx, b);
                if (mx == j && j - i + 1 < n) {
                    ans = Math.max(ans, j - i + 1);
                }
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
    int maxSubstringLength(string s) {
        vector<int> first(26, -1);
        vector<int> last(26);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s[i] - 'a';
            if (first[j] == -1) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = -1;
        for (int k = 0; k < 26; ++k) {
            int i = first[k];
            if (i == -1) {
                continue;
            }
            int mx = last[k];
            for (int j = i; j < n; ++j) {
                int a = first[s[j] - 'a'];
                int b = last[s[j] - 'a'];
                if (a < i) {
                    break;
                }
                mx = max(mx, b);
                if (mx == j && j - i + 1 < n) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubstringLength(s string) int {
	first := [26]int{}
	last := [26]int{}
	for i := range first {
		first[i] = -1
	}
	n := len(s)
	for i, c := range s {
		j := int(c - 'a')
		if first[j] == -1 {
			first[j] = i
		}
		last[j] = i
	}
	ans := -1
	for k := 0; k < 26; k++ {
		i := first[k]
		if i == -1 {
			continue
		}
		mx := last[k]
		for j := i; j < n; j++ {
			a, b := first[s[j]-'a'], last[s[j]-'a']
			if a < i {
				break
			}
			mx = max(mx, b)
			if mx == j && j-i+1 < n {
				ans = max(ans, j-i+1)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxSubstringLength(s: string): number {
    const first: number[] = Array(26).fill(-1);
    const last: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        const j = s.charCodeAt(i) - 97;
        if (first[j] === -1) {
            first[j] = i;
        }
        last[j] = i;
    }
    let ans = -1;
    for (let k = 0; k < 26; ++k) {
        const i = first[k];
        if (i === -1) {
            continue;
        }
        let mx = last[k];
        for (let j = i; j < n; ++j) {
            const a = first[s.charCodeAt(j) - 97];
            if (a < i) {
                break;
            }
            const b = last[s.charCodeAt(j) - 97];
            mx = Math.max(mx, b);
            if (mx === j && j - i + 1 < n) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
