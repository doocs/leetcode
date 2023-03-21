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
        def dfs(a):
            vis.add(a)
            mi, x = a, cnt[a]
            for b in g[a]:
                if b not in vis:
                    t, y = dfs(b)
                    if mi > t:
                        mi = t
                    x += y
            return mi, x

        g = defaultdict(list)
        for e in synonyms:
            a, b = e[1:-1].split(',')
            g[a].append(b)
            g[b].append(a)
        s = set()
        cnt = defaultdict(int)
        for x in names:
            name, freq = x[:-1].split("(")
            s.add(name)
            cnt[name] = int(freq)
        vis = set()
        ans = []
        for name in s:
            if name not in vis:
                name, freq = dfs(name)
                ans.append(f"{name}({freq})")
        return ans
```

### **Java**

```java
class Solution {
    private Map<String, List<String>> g = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<>();
    private Set<String> vis = new HashSet<>();
    private int freq;

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (String pairs : synonyms) {
            String[] pair = pairs.substring(1, pairs.length() - 1).split(",");
            String a = pair[0], b = pair[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        Set<String> s = new HashSet<>();
        for (String x : names) {
            int i = x.indexOf('(');
            String name = x.substring(0, i);
            s.add(name);
            cnt.put(name, Integer.parseInt(x.substring(i + 1, x.length() - 1)));
        }
        List<String> res = new ArrayList<>();
        for (String name : s) {
            if (!vis.contains(name)) {
                freq = 0;
                name = dfs(name);
                res.add(name + "(" + freq + ")");
            }
        }
        return res.toArray(new String[0]);
    }

    private String dfs(String a) {
        String mi = a;
        vis.add(a);
        freq += cnt.getOrDefault(a, 0);
        for (String b : g.getOrDefault(a, new ArrayList<>())) {
            if (!vis.contains(b)) {
                String t = dfs(b);
                if (t.compareTo(mi) < 0) {
                    mi = t;
                }
            }
        }
        return mi;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> trulyMostPopular(vector<string>& names, vector<string>& synonyms) {
        unordered_map<string, vector<string>> g;
        unordered_map<string, int> cnt;
        for (auto& e : synonyms) {
            int i = e.find(',');
            string a = e.substr(1, i - 1);
            string b = e.substr(i + 1, e.size() - i - 2);
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        unordered_set<string> s;
        for (auto& e : names) {
            int i = e.find('(');
            string name = e.substr(0, i);
            s.insert(name);
            cnt[name] += stoi(e.substr(i + 1, e.size() - i - 2));
        }
        unordered_set<string> vis;
        int freq = 0;

        function<string(string)> dfs = [&](string a) -> string {
            string res = a;
            vis.insert(a);
            freq += cnt[a];
            for (auto& b : g[a]) {
                if (!vis.count(b)) {
                    string t = dfs(b);
                    if (t < res) {
                        res = move(t);
                    }
                }
            }
            return move(res);
        };

        vector<string> ans;
        for (auto& name : s) {
            if (!vis.count(name)) {
                freq = 0;
                string x = dfs(name);
                ans.emplace_back(x + "(" + to_string(freq) + ")");
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func trulyMostPopular(names []string, synonyms []string) (ans []string) {
	g := map[string][]string{}
	for _, s := range synonyms {
		i := strings.Index(s, ",")
		a, b := s[1:i], s[i+1:len(s)-1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s := map[string]struct{}{}
	cnt := map[string]int{}
	for _, e := range names {
		i := strings.Index(e, "(")
		name, num := e[:i], e[i+1:len(e)-1]
		x, _ := strconv.Atoi(num)
		cnt[name] += x
		s[name] = struct{}{}
	}
	freq := 0
	vis := map[string]struct{}{}
	var dfs func(string) string
	dfs = func(a string) string {
		vis[a] = struct{}{}
		freq += cnt[a]
		res := a
		for _, b := range g[a] {
			if _, ok := vis[b]; !ok {
				t := dfs(b)
				if t < res {
					res = t
				}
			}
		}
		return res
	}
	for name := range s {
		if _, ok := vis[name]; !ok {
			freq = 0
			root := dfs(name)
			ans = append(ans, root+"("+strconv.Itoa(freq)+")")
		}
	}
	return
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
