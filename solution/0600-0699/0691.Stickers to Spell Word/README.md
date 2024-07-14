---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0691.Stickers%20to%20Spell%20Word/README.md
tags:
    - 位运算
    - 数组
    - 字符串
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [691. 贴纸拼词](https://leetcode.cn/problems/stickers-to-spell-word)

[English Version](/solution/0600-0699/0691.Stickers%20to%20Spell%20Word/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们有 <code>n</code> 种不同的贴纸。每个贴纸上都有一个小写的英文单词。</p>

<p>您想要拼写出给定的字符串 <code>target</code>&nbsp;，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。</p>

<p>返回你需要拼出 <code>target</code>&nbsp;的最小贴纸数量。如果任务不可能，则返回 <code>-1</code> 。</p>

<p><strong>注意：</strong>在所有的测试用例中，所有的单词都是从 <code>1000</code> 个最常见的美国英语单词中随机选择的，并且 <code>target</code>&nbsp;被选择为两个随机单词的连接。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> stickers = ["with","example","science"], target = "thehat"
<b>输出：</b>3
<strong>解释：
</strong>我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
此外，这是形成目标字符串所需的最小贴纸数量。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>stickers = ["notice","possible"], target = "basicbasic"
<b>输出：</b>-1
<strong>解释：</strong>我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == stickers.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= stickers[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= target.length &lt;= 15</code></li>
	<li><code>stickers[i]</code>&nbsp;和&nbsp;<code>target</code>&nbsp;由小写英文单词组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS + 状态压缩

我们注意到，字符串 $\text{target}$ 的长度不超过 $15$，我们可以使用一个长度为 $15$ 的二进制数来表示 $\text{target}$ 的每个字符是否被拼出，如果第 $i$ 位为 $1$，表示 $\text{target}$ 的第 $i$ 个字符已经被拼出，否则表示未被拼出。

我们定义一个初始状态 $0$，表示所有字符都未被拼出，然后我们使用广度优先搜索的方法，从初始状态开始，每次搜索时，我们枚举所有的贴纸，对于每一张贴纸，我们尝试拼出 $\text{target}$ 的每一个字符，如果拼出了某个字符，我们就将对应的二进制数的第 $i$ 位设置为 $1$，表示该字符已经被拼出，然后我们继续搜索，直到我们拼出了 $\text{target}$ 的所有字符。

时间复杂度 $O(2^n \times m \times (l + n))$，空间复杂度 $O(2^n)$。其中 $n$ 是字符串 $\text{target}$ 的长度，而 $m$ 和 $l$ 分别是贴纸的数量和贴纸的平均长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        n = len(target)
        q = deque([0])
        vis = [False] * (1 << n)
        vis[0] = True
        ans = 0
        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                if cur == (1 << n) - 1:
                    return ans
                for s in stickers:
                    cnt = Counter(s)
                    nxt = cur
                    for i, c in enumerate(target):
                        if (cur >> i & 1) == 0 and cnt[c] > 0:
                            cnt[c] -= 1
                            nxt |= 1 << i
                    if not vis[nxt]:
                        vis[nxt] = True
                        q.append(nxt)
            ans += 1
        return -1
```

#### Java

```java
class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] vis = new boolean[1 << n];
        vis[0] = true;
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int m = q.size(); m > 0; --m) {
                int cur = q.poll();
                if (cur == (1 << n) - 1) {
                    return ans;
                }
                for (String s : stickers) {
                    int[] cnt = new int[26];
                    int nxt = cur;
                    for (char c : s.toCharArray()) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int j = target.charAt(i) - 'a';
                        if ((cur >> i & 1) == 0 && cnt[j] > 0) {
                            --cnt[j];
                            nxt |= 1 << i;
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.offer(nxt);
                    }
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minStickers(vector<string>& stickers, string target) {
        int n = target.size();
        queue<int> q{{0}};
        vector<bool> vis(1 << n);
        vis[0] = true;
        for (int ans = 0; q.size(); ++ans) {
            for (int m = q.size(); m; --m) {
                int cur = q.front();
                q.pop();
                if (cur == (1 << n) - 1) {
                    return ans;
                }
                for (auto& s : stickers) {
                    int cnt[26]{};
                    int nxt = cur;
                    for (char& c : s) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int j = target[i] - 'a';
                        if ((cur >> i & 1) == 0 && cnt[j] > 0) {
                            nxt |= 1 << i;
                            --cnt[j];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.push(nxt);
                    }
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func minStickers(stickers []string, target string) (ans int) {
	n := len(target)
	q := []int{0}
	vis := make([]bool, 1<<n)
	vis[0] = true
	for ; len(q) > 0; ans++ {
		for m := len(q); m > 0; m-- {
			cur := q[0]
			q = q[1:]
			if cur == 1<<n-1 {
				return
			}
			for _, s := range stickers {
				cnt := [26]int{}
				for _, c := range s {
					cnt[c-'a']++
				}
				nxt := cur
				for i, c := range target {
					if cur>>i&1 == 0 && cnt[c-'a'] > 0 {
						nxt |= 1 << i
						cnt[c-'a']--
					}
				}
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function minStickers(stickers: string[], target: string): number {
    const n = target.length;
    const q: number[] = [0];
    const vis: boolean[] = Array(1 << n).fill(false);
    vis[0] = true;
    for (let ans = 0; q.length; ++ans) {
        const qq: number[] = [];
        for (const cur of q) {
            if (cur === (1 << n) - 1) {
                return ans;
            }
            for (const s of stickers) {
                const cnt: number[] = Array(26).fill(0);
                for (const c of s) {
                    cnt[c.charCodeAt(0) - 97]++;
                }
                let nxt = cur;
                for (let i = 0; i < n; ++i) {
                    const j = target.charCodeAt(i) - 97;
                    if (((cur >> i) & 1) === 0 && cnt[j]) {
                        nxt |= 1 << i;
                        cnt[j]--;
                    }
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    qq.push(nxt);
                }
            }
        }
        q.splice(0, q.length, ...qq);
    }
    return -1;
}
```

#### Rust

```rust
use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn min_stickers(stickers: Vec<String>, target: String) -> i32 {
        let mut q = VecDeque::new();
        q.push_back(0);
        let mut ans = 0;
        let n = target.len();
        let mut vis = HashSet::new();
        vis.insert(0);
        while !q.is_empty() {
            for _ in 0..q.len() {
                let state = q.pop_front().unwrap();
                if state == (1 << n) - 1 {
                    return ans;
                }
                for s in &stickers {
                    let mut nxt = state;
                    let mut cnt = [0; 26];
                    for &c in s.as_bytes() {
                        cnt[(c - b'a') as usize] += 1;
                    }
                    for (i, &c) in target.as_bytes().iter().enumerate() {
                        let idx = (c - b'a') as usize;
                        if (nxt & (1 << i)) == 0 && cnt[idx] > 0 {
                            nxt |= 1 << i;
                            cnt[idx] -= 1;
                        }
                    }
                    if !vis.contains(&nxt) {
                        q.push_back(nxt);
                        vis.insert(nxt);
                    }
                }
            }
            ans += 1;
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
