---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3170.Lexicographically%20Minimum%20String%20After%20Removing%20Stars/README.md
rating: 1772
source: 第 400 场周赛 Q3
tags:
    - 栈
    - 贪心
    - 哈希表
    - 字符串
    - 堆（优先队列）
---

<!-- problem:start -->

# [3170. 删除星号以后字典序最小的字符串](https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars)

[English Version](/solution/3100-3199/3170.Lexicographically%20Minimum%20String%20After%20Removing%20Stars/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。它可能包含任意数量的&nbsp;<code>'*'</code>&nbsp;字符。你的任务是删除所有的&nbsp;<code>'*'</code>&nbsp;字符。</p>

<p>当字符串还存在至少一个&nbsp;<code>'*'</code>&nbsp;字符时，你可以执行以下操作：</p>

<ul>
	<li>删除最左边的&nbsp;<code>'*'</code>&nbsp;字符，同时删除该星号字符左边一个字典序 <strong>最小</strong>&nbsp;的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。</li>
</ul>

<p>请你返回删除所有&nbsp;<code>'*'</code>&nbsp;字符以后，剩余字符连接而成的 <span data-keyword="lexicographically-smaller-string">字典序最小</span> 的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aaba*"</span></p>

<p><span class="example-io"><b>输出：</b>"aab"</span></p>

<p><strong>解释：</strong></p>

<p>删除 <code>'*'</code>&nbsp;号和它左边的其中一个&nbsp;<code>'a'</code>&nbsp;字符。如果我们选择删除&nbsp;<code>s[3]</code>&nbsp;，<code>s</code>&nbsp;字典序最小。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abc"</span></p>

<p><span class="example-io"><b>输出：</b>"abc"</span></p>

<p><strong>解释：</strong></p>

<p>字符串中没有&nbsp;<code>'*'</code>&nbsp;字符。<!-- notionvc: ff07e34f-b1d6-41fb-9f83-5d0ba3c1ecde --></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只含有小写英文字母和&nbsp;<code>'*'</code>&nbsp;字符。</li>
	<li>输入保证操作可以删除所有的&nbsp;<code>'*'</code>&nbsp;字符。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：按字符记录下标

我们定义一个数组 $g$，用于记录每个字符的下标列表，定义一个长度为 $n$ 的布尔数组 $rem$，用于记录每个字符是否需要删除。

遍历字符串 $s$：

如果当前字符是星号，我们就需要删除它，因此我们将 $rem[i]$ 标记为已删除。同时，我们需要删除此时字典序最小且下标最大的字符。我们从小到大遍历 $26$ 个小写字母，如果 $g[a]$ 不为空，我们就删除 $g[a]$ 中的最后一个下标，并将 $rem$ 中对应的下标置为已删除。

如果当前字符不是星号，我们就将当前字符的下标加入 $g$ 中。

最后，我们遍历 $s$，将未删除的字符拼接起来即可。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度，而 $|\Sigma|$ 为字符集大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def clearStars(self, s: str) -> str:
        g = defaultdict(list)
        n = len(s)
        rem = [False] * n
        for i, c in enumerate(s):
            if c == "*":
                rem[i] = True
                for a in ascii_lowercase:
                    if g[a]:
                        rem[g[a].pop()] = True
                        break
            else:
                g[c].append(i)
        return "".join(c for i, c in enumerate(s) if not rem[i])
```

#### Java

```java
class Solution {
    public String clearStars(String s) {
        Deque<Integer>[] g = new Deque[26];
        Arrays.setAll(g, k -> new ArrayDeque<>());
        int n = s.length();
        boolean[] rem = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].isEmpty()) {
                        rem[g[j].pop()] = true;
                        break;
                    }
                }
            } else {
                g[s.charAt(i) - 'a'].push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string clearStars(string s) {
        stack<int> g[26];
        int n = s.length();
        vector<bool> rem(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].empty()) {
                        rem[g[j].top()] = true;
                        g[j].pop();
                        break;
                    }
                }
            } else {
                g[s[i] - 'a'].push(i);
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                ans.push_back(s[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func clearStars(s string) string {
	g := make([][]int, 26)
	n := len(s)
	rem := make([]bool, n)
	for i, c := range s {
		if c == '*' {
			rem[i] = true
			for j := 0; j < 26; j++ {
				if len(g[j]) > 0 {
					rem[g[j][len(g[j])-1]] = true
					g[j] = g[j][:len(g[j])-1]
					break
				}
			}
		} else {
			g[c-'a'] = append(g[c-'a'], i)
		}
	}
	ans := []byte{}
	for i := range s {
		if !rem[i] {
			ans = append(ans, s[i])
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function clearStars(s: string): string {
    const g: number[][] = Array.from({ length: 26 }, () => []);
    const n = s.length;
    const rem: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        if (s[i] === '*') {
            rem[i] = true;
            for (let j = 0; j < 26; ++j) {
                if (g[j].length) {
                    rem[g[j].pop()!] = true;
                    break;
                }
            }
        } else {
            g[s.charCodeAt(i) - 97].push(i);
        }
    }
    return s
        .split('')
        .filter((_, i) => !rem[i])
        .join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
