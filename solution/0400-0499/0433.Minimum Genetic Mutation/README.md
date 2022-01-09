# [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation)

[English Version](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 <code>"A"</code>, <code>"C"</code>, <code>"G"</code>, <code>"T"</code>中的任意一个。</p>

<p>假设我们要调查一个基因序列的变化。<strong>一次</strong>基因变化意味着这个基因序列中的<strong>一个</strong>字符发生了变化。</p>

<p>例如，基因序列由<code>"AACCGGTT"</code> 变化至 <code>"AACCGGTA" </code>即发生了一次基因变化。</p>

<p>与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。</p>

<p>现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>起始基因序列默认是合法的，但是它并不一定会出现在基因库中。</li>
	<li>如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。</li>
	<li>假定起始基因序列与目标基因序列是不一样的。</li>
</ol>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

返回值: 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

返回值: 2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

返回值: 3
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 找最值。

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
                    next = t[:i] + j + t[i + 1:]
                    if next in s:
                        q.append((next, step + 1))
                        s.remove(next)
        return -1
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
        while (!q.empty())
        {
            auto p = q.front();
            q.pop();
            string t = p.first;
            int step = p.second;
            if (t == end) return step;
            for (int i = 0; i < t.size(); ++i)
            {
                for (char c : mp[t[i]])
                {
                    string next = t.substr(0, i) + c + t.substr(i + 1, t.size() - i - 1);
                    if (s.count(next))
                    {
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

### **...**

```

```

<!-- tabs:end -->
