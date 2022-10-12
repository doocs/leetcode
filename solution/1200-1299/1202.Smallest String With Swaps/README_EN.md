# [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps)

[中文文档](/solution/1200-1299/1202.Smallest%20String%20With%20Swaps/README.md)

## Description

<p>You are given a string <code>s</code>, and an array of pairs of indices in the string&nbsp;<code>pairs</code>&nbsp;where&nbsp;<code>pairs[i] =&nbsp;[a, b]</code>&nbsp;indicates 2 indices(0-indexed) of the string.</p>

<p>You can&nbsp;swap the characters at any pair of indices in the given&nbsp;<code>pairs</code>&nbsp;<strong>any number of times</strong>.</p>

<p>Return the&nbsp;lexicographically smallest string that <code>s</code>&nbsp;can be changed to after using the swaps.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dcab&quot;, pairs = [[0,3],[1,2]]
<strong>Output:</strong> &quot;bacd&quot;
<strong>Explaination:</strong> 
Swap s[0] and s[3], s = &quot;bcad&quot;
Swap s[1] and s[2], s = &quot;bacd&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dcab&quot;, pairs = [[0,3],[1,2],[0,2]]
<strong>Output:</strong> &quot;abcd&quot;
<strong>Explaination: </strong>
Swap s[0] and s[3], s = &quot;bcad&quot;
Swap s[0] and s[2], s = &quot;acbd&quot;
Swap s[1] and s[2], s = &quot;abcd&quot;</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cba&quot;, pairs = [[0,1],[1,2]]
<strong>Output:</strong> &quot;abc&quot;
<strong>Explaination: </strong>
Swap s[0] and s[1], s = &quot;bca&quot;
Swap s[1] and s[2], s = &quot;bac&quot;
Swap s[0] and s[1], s = &quot;abc&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= pairs.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= pairs[i][0], pairs[i][1] &lt;&nbsp;s.length</code></li>
	<li><code>s</code>&nbsp;only contains lower case English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(s)
        p = list(range(n))
        for a, b in pairs:
            p[find(a)] = find(b)
        mp = defaultdict(list)
        for i, c in enumerate(s):
            heappush(mp[find(i)], c)
        return ''.join(heappop(mp[find(i)]) for i in range(n))
```

### **Java**

```java
class Solution {
    private int[] p;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (List<Integer> pair : pairs) {
            p[find(pair.get(0))] = find(pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> mp = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            mp.computeIfAbsent(find(i), k -> new PriorityQueue<>()).offer(chars[i]);
        }
        for (int i = 0; i < n; ++i) {
            chars[i] = mp.get(find(i)).poll();
        }
        return new String(chars);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        int n = s.length();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& pair : pairs) p[find(pair[0])] = find(pair[1]);
        unordered_map<int, vector<char>> mp;
        for (int i = 0; i < n; ++i) mp[find(i)].push_back(s[i]);
        for (auto& [k, v] : mp) sort(v.rbegin(), v.rend());
        string ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(mp[find(i)].back());
            mp[find(i)].pop_back();
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func smallestStringWithSwaps(s string, pairs [][]int) string {
	n := len(s)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, pair := range pairs {
		p[find(pair[0])] = find(pair[1])
	}
	mp := make(map[int][]rune)
	for i, c := range s {
		mp[find(i)] = append(mp[find(i)], c)
	}
	for _, v := range mp {
		sort.Slice(v, func(i, j int) bool {
			return v[i] < v[j]
		})
	}
	var ans []rune
	for i := 0; i < n; i++ {
		ans = append(ans, mp[find(i)][0])
		mp[find(i)] = mp[find(i)][1:]
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
