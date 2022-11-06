# [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation)

[中文文档](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README.md)

## Description

<p>A gene string can be represented by an 8-character long string, with choices from <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<p>Suppose we need to investigate a mutation from a gene string <code>startGene</code> to a gene string <code>endGene</code> where one mutation is defined as one single character changed in the gene string.</p>

<ul>
	<li>For example, <code>&quot;AACCGGTT&quot; --&gt; &quot;AACCGGTA&quot;</code> is one mutation.</li>
</ul>

<p>There is also a gene bank <code>bank</code> that records all the valid gene mutations. A gene must be in <code>bank</code> to make it a valid gene string.</p>

<p>Given the two gene strings <code>startGene</code> and <code>endGene</code> and the gene bank <code>bank</code>, return <em>the minimum number of mutations needed to mutate from </em><code>startGene</code><em> to </em><code>endGene</code>. If there is no such a mutation, return <code>-1</code>.</p>

<p>Note that the starting point is assumed to be valid, so it might not be included in the bank.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> startGene = &quot;AACCGGTT&quot;, endGene = &quot;AACCGGTA&quot;, bank = [&quot;AACCGGTA&quot;]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startGene = &quot;AACCGGTT&quot;, endGene = &quot;AAACGGTA&quot;, bank = [&quot;AACCGGTA&quot;,&quot;AACCGCTA&quot;,&quot;AAACGGTA&quot;]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= bank.length &lt;= 10</code></li>
	<li><code>startGene.length == endGene.length == bank[i].length == 8</code></li>
	<li><code>startGene</code>, <code>endGene</code>, and <code>bank[i]</code> consist of only the characters <code>[&#39;A&#39;, &#39;C&#39;, &#39;G&#39;, &#39;T&#39;]</code>.</li>
</ul>

## Solutions

BFS or DFS.

<!-- tabs:start -->

### **Python3**

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
