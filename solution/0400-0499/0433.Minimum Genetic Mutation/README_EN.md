# [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation)

[中文文档](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README.md)

## Description

<p>A gene string can be represented by an 8-character long string, with choices from <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<p>Suppose we need to investigate a mutation from a gene string <code>start</code> to a gene string <code>end</code> where one mutation is defined as one single character changed in the gene string.</p>

<ul>
	<li>For example, <code>&quot;AACCGGTT&quot; --&gt; &quot;AACCGGTA&quot;</code> is one mutation.</li>
</ul>

<p>There is also a gene bank <code>bank</code> that records all the valid gene mutations. A gene must be in <code>bank</code> to make it a valid gene string.</p>

<p>Given the two gene strings <code>start</code> and <code>end</code> and the gene bank <code>bank</code>, return <em>the minimum number of mutations needed to mutate from </em><code>start</code><em> to </em><code>end</code>. If there is no such a mutation, return <code>-1</code>.</p>

<p>Note that the starting point is assumed to be valid, so it might not be included in the bank.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AACCGGTT&quot;, end = &quot;AACCGGTA&quot;, bank = [&quot;AACCGGTA&quot;]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AACCGGTT&quot;, end = &quot;AAACGGTA&quot;, bank = [&quot;AACCGGTA&quot;,&quot;AACCGCTA&quot;,&quot;AAACGGTA&quot;]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AAAAACCC&quot;, end = &quot;AACCCCCC&quot;, bank = [&quot;AAAACCCC&quot;,&quot;AAACCCCC&quot;,&quot;AACCCCCC&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>start.length == 8</code></li>
	<li><code>end.length == 8</code></li>
	<li><code>0 &lt;= bank.length &lt;= 10</code></li>
	<li><code>bank[i].length == 8</code></li>
	<li><code>start</code>, <code>end</code>, and <code>bank[i]</code> consist of only the characters <code>[&#39;A&#39;, &#39;C&#39;, &#39;G&#39;, &#39;T&#39;]</code>.</li>
</ul>

## Solutions

BFS.

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
                    next = t[:i] + j + t[i + 1:]
                    if next in s:
                        q.append((next, step + 1))
                        s.remove(next)
        return -1
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
