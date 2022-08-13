# [691. 贴纸拼词](https://leetcode.cn/problems/stickers-to-spell-word)

[English Version](/solution/0600-0699/0691.Stickers%20to%20Spell%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS + 状态压缩**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        q = deque([0])
        ans = 0
        n = len(target)
        vis = [False] * (1 << n)
        vis[0] = True
        while q:
            for _ in range(len(q)):
                state = q.popleft()
                if state == (1 << n) - 1:
                    return ans
                for s in stickers:
                    nxt = state
                    cnt = Counter(s)
                    for i, c in enumerate(target):
                        if not (nxt & (1 << i)) and cnt[c]:
                            nxt |= 1 << i
                            cnt[c] -= 1
                    if not vis[nxt]:
                        vis[nxt] = True
                        q.append(nxt)
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minStickers(String[] stickers, String target) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        int n = target.length();
        boolean[] vis = new boolean[1 << n];
        vis[0] = true;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int state = q.poll();
                if (state == (1 << n) - 1) {
                    return ans;
                }
                for (String s : stickers) {
                    int nxt = state;
                    int[] cnt = new int[26];
                    for (char c : s.toCharArray()) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int idx = target.charAt(i) - 'a';
                        if ((nxt & (1 << i)) == 0 && cnt[idx] > 0) {
                            nxt |= 1 << i;
                            --cnt[idx];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minStickers(vector<string>& stickers, string target) {
        queue<int> q {{0}};
        int ans = 0;
        int n = target.size();
        vector<bool> vis(1 << n);
        vis[0] = true;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int state = q.front();
                if (state == (1 << n) - 1) return ans;
                q.pop();
                for (auto& s : stickers) {
                    int nxt = state;
                    vector<int> cnt(26);
                    for (char& c : s) ++cnt[c - 'a'];
                    for (int i = 0; i < n; ++i) {
                        int idx = target[i] - 'a';
                        if (!(nxt & (1 << i)) && cnt[idx]) {
                            nxt |= 1 << i;
                            --cnt[idx];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func minStickers(stickers []string, target string) int {
	q := []int{0}
	n := len(target)
	vis := make([]bool, 1<<n)
	vis[0] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			state := q[0]
			if state == (1<<n)-1 {
				return ans
			}
			q = q[1:]
			for _, s := range stickers {
				nxt := state
				cnt := make([]int, 26)
				for _, c := range s {
					cnt[c-'a']++
				}
				for i, c := range target {
					idx := c - 'a'
					if (nxt&(1<<i)) == 0 && cnt[idx] > 0 {
						nxt |= 1 << i
						cnt[idx]--
					}
				}
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
