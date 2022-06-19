# [1258. Synonymous Sentences](https://leetcode.com/problems/synonymous-sentences)

[中文文档](/solution/1200-1299/1258.Synonymous%20Sentences/README.md)

## Description

<p>You are given a list of equivalent string pairs <code>synonyms</code> where <code>synonyms[i] = [s<sub>i</sub>, t<sub>i</sub>]</code> indicates that <code>s<sub>i</sub></code> and <code>t<sub>i</sub></code> are equivalent strings. You are also given a sentence <code>text</code>.</p>

<p>Return <em>all possible synonymous sentences <strong>sorted lexicographically</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]], text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:</strong> [&quot;I am cheerful today but was sad yesterday&quot;,&quot;I am cheerful today but was sorrow yesterday&quot;,&quot;I am happy today but was sad yesterday&quot;,&quot;I am happy today but was sorrow yesterday&quot;,&quot;I am joy today but was sad yesterday&quot;,&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;cheerful&quot;,&quot;glad&quot;]], text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:</strong> [&quot;I am happy today but was sad yesterday&quot;,&quot;I am joy today but was sad yesterday&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>1 &lt;= s<sub>i</sub>.length,<sub> </sub>t<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>s<sub>i</sub> != t<sub>i</sub></code></li>
	<li><code>text</code> consists of at most <code>10</code> words.</li>
	<li>All the pairs of&nbsp;<code>synonyms</code> are <strong>unique</strong>.</li>
	<li>The words of <code>text</code> are separated by single spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        p = {}

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in synonyms:
            p[a] = a
            p[b] = b
        for a, b in synonyms:
            p[find(a)] = find(b)

        s = defaultdict(set)
        for a, b in synonyms:
            s[find(a)].add(a)
            s[find(b)].add(b)
        res = []
        for word in text.split(' '):
            if word not in p:
                if not res:
                    res.append([word])
                else:
                    for a in res:
                        a.append(word)
            else:
                words = sorted(s[find(word)])
                if not res:
                    for b in words:
                        res.append([b])
                else:
                    res = [a + [b] for a in res for b in words]
        return [' '.join(sentence) for sentence in res]
```

### **Java**

```java
class Solution {
    private Map<String, String> p;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        p = new HashMap<>();
        for (List<String> item : synonyms) {
            p.put(item.get(0), item.get(0));
            p.put(item.get(1), item.get(1));
        }
        for (List<String> item : synonyms) {
            p.put(find(item.get(0)), find(item.get(1)));
        }
        Map<String, Set<String>> s = new HashMap<>();
        for (List<String> item : synonyms) {
            String a = find(item.get(0)), b = find(item.get(1));
            s.computeIfAbsent(a, k -> new HashSet<>()).add(item.get(0));
            s.computeIfAbsent(b, k -> new HashSet<>()).add(item.get(1));
        }
        List<List<String>> all = new ArrayList<>();
        for (String word : text.split(" ")) {
            if (!p.containsKey(word)) {
                if (all.isEmpty()) {
                    List<String> t = new ArrayList<>();
                    t.add(word);
                    all.add(t);
                } else {
                    for (List<String> a : all) {
                        a.add(word);
                    }
                }
            } else {
                Set<String> words = s.get(find(word));
                if (all.isEmpty()) {
                    for (String b : words) {
                        List<String> t = new ArrayList<>();
                        t.add(b);
                        all.add(t);
                    }
                } else {
                    List<List<String>> t = new ArrayList<>();
                    for (List<String> a : all) {
                        for (String b : words) {
                            List<String> c = new ArrayList<>(a);
                            c.add(b);
                            t.add(c);
                        }
                    }
                    all = t;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (List<String> item : all) {
            res.add(String.join(" ", item));
        }
        Collections.sort(res);
        return res;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
