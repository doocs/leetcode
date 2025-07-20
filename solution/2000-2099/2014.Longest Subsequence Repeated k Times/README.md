---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/README.md
rating: 2558
source: 第 259 场周赛 Q4
tags:
    - 贪心
    - 字符串
    - 回溯
    - 计数
    - 枚举
---

<!-- problem:start -->

# [2014. 重复 K 次的最长子序列](https://leetcode.cn/problems/longest-subsequence-repeated-k-times)

[English Version](/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的字符串 <code>s</code> ，和一个整数 <code>k</code> 。请你找出字符串 <code>s</code> 中 <strong>重复</strong> <code>k</code> 次的 <strong>最长子序列</strong> 。</p>

<p><strong>子序列</strong> 是由其他字符串删除某些（或不删除）字符派生而来的一个字符串。</p>

<p>如果&nbsp;<code>seq * k</code> 是 <code>s</code> 的一个子序列，其中 <code>seq * k</code> 表示一个由 <code>seq</code> 串联 <code>k</code>&nbsp;次构造的字符串，那么就称 <code>seq</code><strong> </strong>是字符串 <code>s</code> 中一个 <strong>重复 <code>k</code> 次</strong> 的子序列。</p>

<ul>
	<li>举个例子，<code>"bba"</code> 是字符串 <code>"bababcba"</code> 中的一个重复 <code>2</code> 次的子序列，因为字符串 <code>"bbabba"</code> 是由 <code>"bba"</code> 串联 <code>2</code> 次构造的，而&nbsp;<code>"bbabba"</code> 是字符串 <code>"<em><strong>b</strong></em>a<em><strong>bab</strong></em>c<em><strong>ba</strong></em>"</code> 的一个子序列。</li>
</ul>

<p>返回字符串 <code>s</code> 中 <strong>重复 k 次的最长子序列</strong>&nbsp; 。如果存在多个满足的子序列，则返回 <strong>字典序最大</strong> 的那个。如果不存在这样的子序列，返回一个 <strong>空</strong> 字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2014.Longest%20Subsequence%20Repeated%20k%20Times/images/longest-subsequence-repeat-k-times.png" style="width: 457px; height: 99px;" /></p>

<pre>
<strong>输入：</strong>s = "letsleetcode", k = 2
<strong>输出：</strong>"let"
<strong>解释：</strong>存在两个最长子序列重复 2 次：let" 和 "ete" 。
"let" 是其中字典序最大的一个。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "bb", k = 2
<strong>输出：</strong>"b"
<strong>解释：</strong>重复 2 次的最长子序列是 "b" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "ab", k = 2
<strong>输出：</strong>""
<strong>解释：</strong>不存在重复 2 次的最长子序列。返回空字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= k &lt;= 2000</code></li>
	<li><code>2 &lt;= n &lt; min(2001, k * 8)</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以先统计字符串中每个字符出现的次数，然后将出现次数大于等于 $k$ 的字符按从小到大的顺序存入一个列表 $\textit{cs}$ 中。接下来，我们可以使用 BFS 来枚举所有可能的子序列。

我们定义一个队列 $\textit{q}$，初始时将空字符串放入队列中。然后，我们从队列中取出一个字符串 $\textit{cur}$，并尝试将每个字符 $c \in \textit{cs}$ 添加到 $\textit{cur}$ 的末尾，形成一个新的字符串 $\textit{nxt}$。如果 $\textit{nxt}$ 是一个重复 $k$ 次的子序列，我们就将其加入到答案中，并将 $\textit{nxt}$ 放入队列中继续处理。

我们需要一个辅助函数 $\textit{check(t, k)}$ 来判断字符串 $\textit{t}$ 是否是字符串 $s$ 的一个重复 $k$ 次的子序列。具体地，我们可以使用两个指针来遍历字符串 $s$ 和 $\textit{t}$，如果在遍历过程中能够找到 $\textit{t}$ 的所有字符，并且能够重复 $k$ 次，那么就返回 $\textit{true}$，否则返回 $\textit{false}$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubsequenceRepeatedK(self, s: str, k: int) -> str:
        def check(t: str, k: int) -> bool:
            i = 0
            for c in s:
                if c == t[i]:
                    i += 1
                    if i == len(t):
                        k -= 1
                        if k == 0:
                            return True
                        i = 0
            return False

        cnt = Counter(s)
        cs = [c for c in ascii_lowercase if cnt[c] >= k]
        q = deque([""])
        ans = ""
        while q:
            cur = q.popleft()
            for c in cs:
                nxt = cur + c
                if check(nxt, k):
                    ans = nxt
                    q.append(nxt)
        return ans
```

#### Java

```java
class Solution {
    private char[] s;

    public String longestSubsequenceRepeatedK(String s, int k) {
        this.s = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : this.s) {
            cnt[c - 'a']++;
        }

        List<Character> cs = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            if (cnt[c - 'a'] >= k) {
                cs.add(c);
            }
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";
        while (!q.isEmpty()) {
            String cur = q.poll();
            for (char c : cs) {
                String nxt = cur + c;
                if (check(nxt, k)) {
                    ans = nxt;
                    q.offer(nxt);
                }
            }
        }
        return ans;
    }

    private boolean check(String t, int k) {
        int i = 0;
        for (char c : s) {
            if (c == t.charAt(i)) {
                i++;
                if (i == t.length()) {
                    if (--k == 0) {
                        return true;
                    }
                    i = 0;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestSubsequenceRepeatedK(string s, int k) {
        auto check = [&](const string& t, int k) -> bool {
            int i = 0;
            for (char c : s) {
                if (c == t[i]) {
                    i++;
                    if (i == t.size()) {
                        if (--k == 0) {
                            return true;
                        }
                        i = 0;
                    }
                }
            }
            return false;
        };
        int cnt[26] = {};
        for (char c : s) {
            cnt[c - 'a']++;
        }

        vector<char> cs;
        for (char c = 'a'; c <= 'z'; ++c) {
            if (cnt[c - 'a'] >= k) {
                cs.push_back(c);
            }
        }

        queue<string> q;
        q.push("");
        string ans;
        while (!q.empty()) {
            string cur = q.front();
            q.pop();
            for (char c : cs) {
                string nxt = cur + c;
                if (check(nxt, k)) {
                    ans = nxt;
                    q.push(nxt);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubsequenceRepeatedK(s string, k int) string {
	check := func(t string, k int) bool {
		i := 0
		for _, c := range s {
			if byte(c) == t[i] {
				i++
				if i == len(t) {
					k--
					if k == 0 {
						return true
					}
					i = 0
				}
			}
		}
		return false
	}

	cnt := [26]int{}
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}

	cs := []byte{}
	for c := byte('a'); c <= 'z'; c++ {
		if cnt[c-'a'] >= k {
			cs = append(cs, c)
		}
	}

	q := []string{""}
	ans := ""
	for len(q) > 0 {
		cur := q[0]
		q = q[1:]
		for _, c := range cs {
			nxt := cur + string(c)
			if check(nxt, k) {
				ans = nxt
				q = append(q, nxt)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestSubsequenceRepeatedK(s: string, k: number): string {
    const check = (t: string, k: number): boolean => {
        let i = 0;
        for (const c of s) {
            if (c === t[i]) {
                i++;
                if (i === t.length) {
                    k--;
                    if (k === 0) {
                        return true;
                    }
                    i = 0;
                }
            }
        }
        return false;
    };

    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }

    const cs: string[] = [];
    for (let i = 0; i < 26; ++i) {
        if (cnt[i] >= k) {
            cs.push(String.fromCharCode(97 + i));
        }
    }

    const q: string[] = [''];
    let ans = '';
    while (q.length > 0) {
        const cur = q.shift()!;
        for (const c of cs) {
            const nxt = cur + c;
            if (check(nxt, k)) {
                ans = nxt;
                q.push(nxt);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
