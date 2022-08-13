# [433. 最小基因变化](https://leetcode.cn/problems/minimum-genetic-mutation)

[English Version](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 和 <code>'T'</code> 之一。</p>

<p>假设我们需要调查从基因序列&nbsp;<code>start</code> 变为 <code>end</code> 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。</p>

<ul>
	<li>例如，<code>"AACCGGTT" --&gt; "AACCGGTA"</code> 就是一次基因变化。</li>
</ul>

<p>另有一个基因库 <code>bank</code> 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 <code>bank</code> 中）</p>

<p>给你两个基因序列 <code>start</code> 和 <code>end</code> ，以及一个基因库 <code>bank</code> ，请你找出并返回能够使&nbsp;<code>start</code> 变化为 <code>end</code> 所需的最少变化次数。如果无法完成此基因变化，返回 <code>-1</code> 。</p>

<p>注意：起始基因序列&nbsp;<code>start</code> 默认是有效的，但是它并不一定会出现在基因库中。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>start.length == 8</code></li>
	<li><code>end.length == 8</code></li>
	<li><code>0 &lt;= bank.length &lt;= 10</code></li>
	<li><code>bank[i].length == 8</code></li>
	<li><code>start</code>、<code>end</code> 和 <code>bank[i]</code> 仅由字符 <code>['A', 'C', 'G', 'T']</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

**方法二：DFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        s = set(bank)
        q = deque([(start, 0)])
        mp = {'A': 'TCG', 'T': 'ACG', 'C': 'ATG', 'G': 'ATC'}
        while q:
            t, step = q.popleft()
            if t == end:
                return step
            for i, v in enumerate(t):
                for j in mp[v]:
                    next = t[:i] + j + t[i + 1 :]
                    if next in s:
                        q.append((next, step + 1))
                        s.remove(next)
        return -1
```

```python
class Solution:
    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        def dfs(start, t):
            if start == end:
                nonlocal ans
                ans = min(ans, t)
                return
            for i, x in enumerate(start):
                for y in 'ACGT':
                    if x != y:
                        nxt = start[:i] + y + start[i + 1:]
                        if nxt in s:
                            s.remove(nxt)
                            dfs(nxt, t + 1)

        s = set(bank)
        ans = inf
        dfs(start, 0)
        return -1 if ans == inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> s = new HashSet<>();
        for (String b : bank) {
            s.add(b);
        }
        Map<Character, String> mp = new HashMap<>(4);
        mp.put('A', "TCG");
        mp.put('T', "ACG");
        mp.put('C', "ATG");
        mp.put('G', "ATC");
        Deque<Pair<String, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(start, 0));
        while (!q.isEmpty()) {
            Pair<String, Integer> p = q.poll();
            String t = p.getKey();
            int step = p.getValue();
            if (end.equals(t)) {
                return step;
            }
            for (int i = 0; i < t.length(); ++i) {
                for (char c : mp.get(t.charAt(i)).toCharArray()) {
                    String next = t.substring(0, i) + c + t.substring(i + 1);
                    if (s.contains(next)) {
                        q.offer(new Pair<>(next, step + 1));
                        s.remove(next);
                    }
                }
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    private int ans;
    private Set<String> s;
    private static final char[] seq = {'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        s = new HashSet<>();
        for (String b : bank) {
            s.add(b);
        }
        ans = Integer.MAX_VALUE;
        dfs(start, end, 0);
        s.remove(start);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(String start, String end, int t) {
        if (start.equals(end)) {
            ans = Math.min(ans, t);
            return;
        }
        for (int i = 0; i < start.length(); ++i) {
            for (char c : seq) {
                if (start.charAt(i) == c) {
                    continue;
                }
                String nxt = start.substring(0, i) + c + start.substring(i + 1);
                if (s.contains(nxt)) {
                    s.remove(nxt);
                    dfs(nxt, end, t + 1);
                }
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMutation(string start, string end, vector<string>& bank) {
        unordered_set<string> s;
        for (auto& b : bank) s.insert(b);
        unordered_map<char, string> mp;
        mp['A'] = "TCG";
        mp['T'] = "ACG";
        mp['C'] = "ATG";
        mp['G'] = "ATC";
        queue<pair<string, int>> q;
        q.push({start, 0});
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            string t = p.first;
            int step = p.second;
            if (t == end) return step;
            for (int i = 0; i < t.size(); ++i) {
                for (char c : mp[t[i]]) {
                    string next = t.substr(0, i) + c + t.substr(i + 1, t.size() - i - 1);
                    if (s.count(next)) {
                        q.push({next, step + 1});
                        s.erase(next);
                    }
                }
            }
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int ans;
    string seq = "ACGT";

    int minMutation(string start, string end, vector<string>& bank) {
        unordered_set<string> s;
        for (auto& b : bank) s.insert(b);
        ans = INT_MAX;
        s.erase(start);
        dfs(start, end, s, 0);
        return ans == INT_MAX ? -1 : ans;
    }

    void dfs(string& start, string& end, unordered_set<string>& s, int t) {
        if (start == end)
        {
            ans = min(ans, t);
            return;
        }
        for (int i = 0; i < start.size(); ++i)
        {
            for (char& c : seq)
            {
                if (start[i] == c) continue;
                string nxt = start.substr(0, i) + c + start.substr(i + 1, start.size() - i - 1);
                if (s.count(nxt))
                {
                    s.erase(nxt);
                    dfs(nxt, end, s, t + 1);
                }
            }
        }
    }
};
```

### **Go**

```go
func minMutation(start string, end string, bank []string) int {
	s := make(map[string]bool)
	for _, b := range bank {
		s[b] = true
	}
	mp := make(map[byte]string)
	mp['A'] = "TCG"
	mp['T'] = "ACG"
	mp['C'] = "ATG"
	mp['G'] = "ATC"
	type pair struct {
		first  string
		second int
	}
	q := []pair{{start, 0}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		t, step := p.first, p.second
		if t == end {
			return step
		}
		for i := 0; i < len(t); i++ {
			for _, c := range mp[t[i]] {
				next := t[:i] + string(c) + t[i+1:]
				if s[next] {
					q = append(q, pair{next, step + 1})
					s[next] = false
				}
			}
		}
	}
	return -1
}
```

```go
func minMutation(start string, end string, bank []string) int {
	s := make(map[string]bool)
	for _, b := range bank {
		s[b] = true
	}
	ans := math.MaxInt32
	s[start] = false
	seq := []rune{'A', 'C', 'G', 'T'}
	var dfs func(start string, t int)
	dfs = func(start string, t int) {
		if start == end {
			if ans > t {
				ans = t
			}
			return
		}
		for i, x := range start {
			for _, y := range seq {
				if x == y {
					continue
				}
				nxt := start[:i] + string(y) + start[i+1:]
				if s[nxt] {
					s[nxt] = false
					dfs(nxt, t+1)
				}
			}
		}
	}
	dfs(start, 0)
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
function minMutation(start: string, end: string, bank: string[]): number {
    const queue = [start];
    let res = 0;
    while (queue.length !== 0) {
        const n = queue.length;
        for (let i = 0; i < n; i++) {
            const s1 = queue.shift();
            if (s1 === end) {
                return res;
            }

            for (let j = bank.length - 1; j >= 0; j--) {
                const s2 = bank[j];
                let count = 0;
                for (let k = 0; k < 8; k++) {
                    if (s1[k] !== s2[k]) {
                        count++;
                    }
                }
                if (count <= 1) {
                    queue.push(...bank.splice(j, 1));
                }
            }
        }
        res++;
    }
    return -1;
}
```

### **Rust**

```rust
use std::collections::VecDeque;
impl Solution {
    pub fn min_mutation(start: String, end: String, mut bank: Vec<String>) -> i32 {
        let mut queue = vec![start].into_iter().collect::<VecDeque<String>>();
        let mut res = 0;
        while !queue.is_empty() {
            let n = queue.len();
            for _ in 0..n {
                let s1 = queue.pop_front().unwrap();
                if s1 == end {
                    return res;
                }

                for i in (0..bank.len()).rev() {
                    let s1 = s1.as_bytes();
                    let s2 = bank[i].as_bytes();
                    let mut count = 0;
                    for j in 0..8 {
                        if s1[j] != s2[j] {
                            count += 1;
                        }
                    }
                    if count <= 1 {
                        queue.push_back(bank.remove(i));
                    }
                }
            }
            res += 1;
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
