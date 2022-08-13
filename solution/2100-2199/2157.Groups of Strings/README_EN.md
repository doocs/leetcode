# [2157. Groups of Strings](https://leetcode.com/problems/groups-of-strings)

[中文文档](/solution/2100-2199/2157.Groups%20of%20Strings/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>words</code>. Each string consists of <strong>lowercase English letters</strong> only. No letter occurs more than once in any string of <code>words</code>.</p>

<p>Two strings <code>s1</code> and <code>s2</code> are said to be <strong>connected</strong> if the set of letters of <code>s2</code> can be obtained from the set of letters of <code>s1</code> by any <strong>one</strong> of the following operations:</p>

<ul>
	<li>Adding exactly one letter to the set of the letters of <code>s1</code>.</li>
	<li>Deleting exactly one letter from the set of the letters of <code>s1</code>.</li>
	<li>Replacing exactly one letter from the set of the letters of <code>s1</code> with any letter, <strong>including</strong> itself.</li>
</ul>

<p>The array <code>words</code> can be divided into one or more non-intersecting <strong>groups</strong>. A string belongs to a group if any <strong>one</strong> of the following is true:</p>

<ul>
	<li>It is connected to <strong>at least one</strong> other string of the group.</li>
	<li>It is the <strong>only</strong> string present in the group.</li>
</ul>

<p>Note that the strings in <code>words</code> should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. It can be proved that such an arrangement is always unique.</p>

<p>Return <em>an array</em> <code>ans</code> <em>of size</em> <code>2</code> <em>where:</em></p>

<ul>
	<li><code>ans[0]</code> <em>is the <strong>maximum number</strong> of groups</em> <code>words</code> <em>can be divided into, and</em></li>
	<li><code>ans[1]</code> <em>is the <strong>size of the largest</strong> group</em>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;ab&quot;,&quot;cde&quot;]
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong>
- words[0] can be used to obtain words[1] (by replacing &#39;a&#39; with &#39;b&#39;), and words[2] (by adding &#39;b&#39;). So words[0] is connected to words[1] and words[2].
- words[1] can be used to obtain words[0] (by replacing &#39;b&#39; with &#39;a&#39;), and words[2] (by adding &#39;a&#39;). So words[1] is connected to words[0] and words[2].
- words[2] can be used to obtain words[0] (by deleting &#39;b&#39;), and words[1] (by deleting &#39;a&#39;). So words[2] is connected to words[0] and words[1].
- words[3] is not connected to any string in words.
Thus, words can be divided into 2 groups [&quot;a&quot;,&quot;b&quot;,&quot;ab&quot;] and [&quot;cde&quot;]. The size of the largest group is 3.  
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;ab&quot;,&quot;abc&quot;]
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong>
- words[0] is connected to words[1].
- words[1] is connected to words[0] and words[2].
- words[2] is connected to words[1].
Since all strings are connected to each other, they should be grouped together.
Thus, the size of the largest group is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 26</code></li>
	<li><code>words[i]</code> consists of lowercase English letters only.</li>
	<li>No letter occurs more than once in <code>words[i]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def groupStrings(self, words: List[str]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            nonlocal mx, n
            if b not in p:
                return
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]
            mx = max(mx, size[pb])
            n -= 1

        p = {}
        size = Counter()
        n = len(words)
        mx = 0
        for word in words:
            x = 0
            for c in word:
                x |= 1 << (ord(c) - ord('a'))
            p[x] = x
            size[x] += 1
            mx = max(mx, size[x])
            if size[x] > 1:
                n -= 1
        for x in p.keys():
            for i in range(26):
                union(x, x ^ (1 << i))
                if (x >> i) & 1:
                    for j in range(26):
                        if ((x >> j) & 1) == 0:
                            union(x, x ^ (1 << i) | (1 << j))
        return [n, mx]
```

### **Java**

```java
class Solution {
    private Map<Integer, Integer> p;
    private Map<Integer, Integer> size;
    private int mx;
    private int n;

    public int[] groupStrings(String[] words) {
        p = new HashMap<>();
        size = new HashMap<>();
        n = words.length;
        mx = 0;
        for (String word : words) {
            int x = 0;
            for (char c : word.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            p.put(x, x);
            size.put(x, size.getOrDefault(x, 0) + 1);
            mx = Math.max(mx, size.get(x));
            if (size.get(x) > 1) {
                --n;
            }
        }
        for (int x : p.keySet()) {
            for (int i = 0; i < 26; ++i) {
                union(x, x ^ (1 << i));
                if (((x >> i) & 1) != 0) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) {
                            union(x, x ^ (1 << i) | (1 << j));
                        }
                    }
                }
            }
        }
        return new int[]{n, mx};
    }

    private int find(int x) {
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    private void union(int a, int b) {
        if (!p.containsKey(b)) {
            return;
        }
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p.put(pa, pb);
        size.put(pb, size.get(pb) + size.get(pa));
        mx = Math.max(mx, size.get(pb));
        --n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mx, n;

    vector<int> groupStrings(vector<string>& words) {
        unordered_map<int, int> p;
        unordered_map<int, int> size;
        mx = 0;
        n = words.size();
        for (auto& word : words) {
            int x = 0;
            for (auto& c : word) x |= 1 << (c - 'a');
            p[x] = x;
            ++size[x];
            mx = max(mx, size[x]);
            if (size[x] > 1) --n;
        }
        for (auto& [x, _] : p) {
            for (int i = 0; i < 26; ++i) {
                unite(x, x ^ (1 << i), p, size);
                if ((x >> i) & 1) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) unite(x, x ^ (1 << i) | (1 << j), p, size);
                    }
                }
            }
        }
        return {n, mx};
    }

    int find(int x, unordered_map<int, int>& p) {
        if (p[x] != x) p[x] = find(p[x], p);
        return p[x];
    }

    void unite(int a, int b, unordered_map<int, int>& p, unordered_map<int, int>& size) {
        if (!p.count(b)) return;
        int pa = find(a, p), pb = find(b, p);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
        mx = max(mx, size[pb]);
        --n;
    }
};
```

### **Go**

```go
func groupStrings(words []string) []int {
	p := map[int]int{}
	size := map[int]int{}
	mx, n := 0, len(words)
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		if _, ok := p[b]; !ok {
			return
		}
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
		mx = max(mx, size[pb])
		n--
	}

	for _, word := range words {
		x := 0
		for _, c := range word {
			x |= 1 << (c - 'a')
		}
		p[x] = x
		size[x]++
		mx = max(mx, size[x])
		if size[x] > 1 {
			n--
		}
	}
	for x := range p {
		for i := 0; i < 26; i++ {
			union(x, x^(1<<i))
			if ((x >> i) & 1) != 0 {
				for j := 0; j < 26; j++ {
					if ((x >> j) & 1) == 0 {
						union(x, x^(1<<i)|(1<<j))
					}
				}
			}
		}
	}
	return []int{n, mx}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
