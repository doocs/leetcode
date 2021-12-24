# [1258. Synonymous Sentences](https://leetcode.com/problems/synonymous-sentences)

[中文文档](/solution/1200-1299/1258.Synonymous%20Sentences/README.md)

## Description

Given a list of pairs of equivalent words <code>synonyms</code> and a sentence <code>text</code>, Return all possible synonymous sentences <strong>sorted lexicographically</strong>.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:
</strong>synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]],
text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:
</strong>[&quot;I am cheerful today but was sad yesterday&quot;,
&quot;I am cheerful today but was sorrow yesterday&quot;,
&quot;I am happy today but was sad yesterday&quot;,
&quot;I am happy today but was sorrow yesterday&quot;,
&quot;I am joy today but was sad yesterday&quot;,
&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;cheerful&quot;,&quot;glad&quot;]], text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:</strong> [&quot;I am happy today but was sad yesterday&quot;,&quot;I am joy today but was sad yesterday&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;e&quot;,&quot;f&quot;]], text = &quot;a c e&quot;
<strong>Output:</strong> [&quot;a c e&quot;,&quot;a c f&quot;,&quot;a d e&quot;,&quot;a d f&quot;,&quot;b c e&quot;,&quot;b c f&quot;,&quot;b d e&quot;,&quot;b d f&quot;]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;a&quot;,&quot;QrbCl&quot;]], text = &quot;d QrbCl ya ya NjZQ&quot;
<strong>Output:</strong> [&quot;d QrbCl ya ya NjZQ&quot;,&quot;d a ya ya NjZQ&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>synonyms[i][0] != synonyms[i][1]</code></li>
	<li>All words consist of at most <code>10</code> English letters only.</li>
	<li><code>text</code>&nbsp;is a single space separated sentence of at most <code>10</code> words.</li>
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
