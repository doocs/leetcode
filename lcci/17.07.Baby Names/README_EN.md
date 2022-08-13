# [17.07. Baby Names](https://leetcode.cn/problems/baby-names-lcci)

[中文文档](/lcci/17.07.Baby%20Names/README.md)

## Description

<p>Each year, the government releases a list of the 10000 most common baby names and their frequencies (the number of babies with that name). The only problem with this is that some names have multiple spellings. For example,&quot;John&quot; and &#39;&#39;Jon&quot; are essentially the same name but would be listed separately in the list. Given two lists, one of names/frequencies and the other of pairs of equivalent names, write an algorithm to print a new list of the true frequency of each name. Note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then John and Johnny are synonyms. (It is both transitive and symmetric.) In the final list, choose the name that are <strong>lexicographically smallest</strong> as the &quot;real&quot; name.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>names = [&quot;John(15)&quot;,&quot;Jon(12)&quot;,&quot;Chris(13)&quot;,&quot;Kris(4)&quot;,&quot;Christopher(19)&quot;], synonyms = [&quot;(Jon,John)&quot;,&quot;(John,Johnny)&quot;,&quot;(Chris,Kris)&quot;,&quot;(Chris,Christopher)&quot;]

<strong>Output: </strong>[&quot;John(27)&quot;,&quot;Chris(36)&quot;]</pre>

<p>Note:</p>

<ul>
	<li><code>names.length &lt;= 100000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def trulyMostPopular(self, names: List[str], synonyms: List[str]) -> List[str]:
        mp = defaultdict(int)
        p = defaultdict(str)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            if pa > pb:
                mp[pb] += mp[pa]
                p[pa] = pb
            else:
                mp[pa] += mp[pb]
                p[pb] = pa

        for e in names:
            idx = e.find("(")
            name, w = e[:idx], int(e[idx + 1 : -1])
            mp[name] = w
            p[name] = name
        for e in synonyms:
            idx = e.find(",")
            name1, name2 = e[1:idx], e[idx + 1 : -1]
            mp[name1] += 0
            mp[name2] += 0
            p[name1] = name1
            p[name2] = name2

        for e in synonyms:
            idx = e.find(",")
            name1, name2 = e[1:idx], e[idx + 1 : -1]
            union(name1, name2)
        return [f'{name}({mp[name]})' for name, w in mp.items() if name == find(name)]
```

### **Java**

```java
class Solution {
    private Map<String, Integer> mp = new HashMap<>();
    private Map<String, String> p = new HashMap<>();

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (String e : names) {
            int idx = e.indexOf("(");
            String name = e.substring(0, idx);
            int w = Integer.parseInt(e.substring(idx + 1, e.length() - 1));
            mp.put(name, w);
            p.put(name, name);
        }
        for (String e : synonyms) {
            int idx = e.indexOf(",");
            String name1 = e.substring(1, idx);
            String name2 = e.substring(idx + 1, e.length() - 1);
            if (!mp.containsKey(name1)) {
                mp.put(name1, 0);
            }
            if (!mp.containsKey(name2)) {
                mp.put(name2, 0);
            }
            p.put(name1, name1);
            p.put(name2, name2);
        }
        for (String e : synonyms) {
            int idx = e.indexOf(",");
            String name1 = e.substring(1, idx);
            String name2 = e.substring(idx + 1, e.length() - 1);
            union(name1, name2);
        }
        List<String> t = new ArrayList<>();
        for (Map.Entry<String, Integer> e : mp.entrySet()) {
            String name = e.getKey();
            if (Objects.equals(name, find(name))) {
                t.add(name + "(" + e.getValue() + ")");
            }
        }
        String[] res = new String[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    private void union(String a, String b) {
        String pa = find(a), pb = find(b);
        if (Objects.equals(pa, pb)) {
            return;
        }
        if (pa.compareTo(pb) > 0) {
            mp.put(pb, mp.getOrDefault(pb, 0) + mp.getOrDefault(pa, 0));
            p.put(pa, pb);
        } else {
            mp.put(pa, mp.getOrDefault(pa, 0) + mp.getOrDefault(pb, 0));
            p.put(pb, pa);
        }
    }
}
```

### **TypeScript**

```ts
function trulyMostPopular(names: string[], synonyms: string[]): string[] {
    const map = new Map<string, string>();
    for (const synonym of synonyms) {
        const [k1, k2] = [...synonym]
            .slice(1, synonym.length - 1)
            .join('')
            .split(',');
        const [v1, v2] = [map.get(k1) ?? k1, map.get(k2) ?? k2];
        const min = v1 < v2 ? v1 : v2;
        const max = v1 < v2 ? v2 : v1;
        map.set(k1, min);
        map.set(k2, min);
        for (const [k, v] of map.entries()) {
            if (v === max) {
                map.set(k, min);
            }
        }
    }

    const keyCount = new Map<string, number>();
    for (const name of names) {
        const num = name.match(/\d+/)[0];
        const k = name.split('(')[0];
        const key = map.get(k) ?? k;
        keyCount.set(key, (keyCount.get(key) ?? 0) + Number(num));
    }
    return [...keyCount.entries()].map(([k, v]) => `${k}(${v})`);
}
```

### **...**

```

```

<!-- tabs:end -->
