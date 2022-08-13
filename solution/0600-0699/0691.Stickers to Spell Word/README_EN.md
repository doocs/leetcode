# [691. Stickers to Spell Word](https://leetcode.com/problems/stickers-to-spell-word)

[中文文档](/solution/0600-0699/0691.Stickers%20to%20Spell%20Word/README.md)

## Description

<p>We are given <code>n</code> different types of <code>stickers</code>. Each sticker has a lowercase English word on it.</p>

<p>You would like to spell out the given string <code>target</code> by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.</p>

<p>Return <em>the minimum number of stickers that you need to spell out </em><code>target</code>. If the task is impossible, return <code>-1</code>.</p>

<p><strong>Note:</strong> In all test cases, all words were chosen randomly from the <code>1000</code> most common US English words, and <code>target</code> was chosen as a concatenation of two random words.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> stickers = [&quot;with&quot;,&quot;example&quot;,&quot;science&quot;], target = &quot;thehat&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can use 2 &quot;with&quot; stickers, and 1 &quot;example&quot; sticker.
After cutting and rearrange the letters of those stickers, we can form the target &quot;thehat&quot;.
Also, this is the minimum number of stickers necessary to form the target string.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> stickers = [&quot;notice&quot;,&quot;possible&quot;], target = &quot;basicbasic&quot;
<strong>Output:</strong> -1
Explanation:
We cannot form the target &quot;basicbasic&quot; from cutting letters from the given stickers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stickers.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= stickers[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= target.length &lt;= 15</code></li>
	<li><code>stickers[i]</code> and <code>target</code> consist of lowercase English letters.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

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
