# [451. Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency)

[中文文档](/solution/0400-0499/0451.Sort%20Characters%20By%20Frequency/README.md)

## Description

<p>Given a string <code>s</code>, sort it in decreasing order based on the frequency of characters, and return <em>the sorted string</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;tree&quot;
<strong>Output:</strong> &quot;eert&quot;
<strong>Explanation:</strong> &#39;e&#39; appears twice while &#39;r&#39; and &#39;t&#39; both appear once.
So &#39;e&#39; must appear before both &#39;r&#39; and &#39;t&#39;. Therefore &quot;eetr&quot; is also a valid answer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cccaaa&quot;
<strong>Output:</strong> &quot;aaaccc&quot;
<strong>Explanation:</strong> Both &#39;c&#39; and &#39;a&#39; appear three times, so &quot;aaaccc&quot; is also a valid answer.
Note that &quot;cacaca&quot; is incorrect, as the same characters must be together.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Aabb&quot;
<strong>Output:</strong> &quot;bbAa&quot;
<strong>Explanation:</strong> &quot;bbaA&quot; is also a valid answer, but &quot;Aabb&quot; is incorrect.
Note that &#39;A&#39; and &#39;a&#39; are treated as two different characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of English letters and digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def frequencySort(self, s: str) -> str:
        counter = Counter(s)
        buckets = defaultdict(list)
        for c, freq in counter.items():
            buckets[freq].append(c)
        res = []
        for i in range(len(s), -1, -1):
            if buckets[i]:
                for c in buckets[i]:
                    res.append(c * i)
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        List<Character>[] buckets = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 0; --i) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; ++j) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
```

### **Go**

Simulation with structure sorting.

```go
type pair struct {
	b   byte
	cnt int
}

func frequencySort(s string) string {
	freq := make(map[byte]int)
	for _, r := range s {
		freq[byte(r)]++
	}
	a := make([]pair, 0)
	for k, v := range freq {
		a = append(a, pair{b: k, cnt: v})
	}
	sort.Slice(a, func(i, j int) bool { return a[i].cnt > a[j].cnt })
	var sb strings.Builder
	for _, p := range a {
		sb.Write(bytes.Repeat([]byte{p.b}, p.cnt))
	}
	return sb.String()
}
```

### **...**

```

```

<!-- tabs:end -->
