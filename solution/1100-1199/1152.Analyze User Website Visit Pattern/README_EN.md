# [1152. Analyze User Website Visit Pattern](https://leetcode.com/problems/analyze-user-website-visit-pattern)

[中文文档](/solution/1100-1199/1152.Analyze%20User%20Website%20Visit%20Pattern/README.md)

## Description

<p>You are given two string arrays <code>username</code> and <code>website</code> and an integer array <code>timestamp</code>. All the given arrays are of the same length and the tuple <code>[username[i], website[i], timestamp[i]]</code> indicates that the user <code>username[i]</code> visited the website <code>website[i]</code> at time <code>timestamp[i]</code>.</p>

<p>A <strong>pattern</strong> is a list of three websites (not necessarily distinct).</p>

<ul>
	<li>For example, <code>[&quot;home&quot;, &quot;away&quot;, &quot;love&quot;]</code>, <code>[&quot;leetcode&quot;, &quot;love&quot;, &quot;leetcode&quot;]</code>, and <code>[&quot;luffy&quot;, &quot;luffy&quot;, &quot;luffy&quot;]</code> are all patterns.</li>
</ul>

<p>The <strong>score</strong> of a <strong>pattern</strong> is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.</p>

<ul>
	<li>For example, if the pattern is <code>[&quot;home&quot;, &quot;away&quot;, &quot;love&quot;]</code>, the score is the number of users <code>x</code> such that <code>x</code> visited <code>&quot;home&quot;</code> then visited <code>&quot;away&quot;</code> and visited <code>&quot;love&quot;</code> after that.</li>
	<li>Similarly, if the pattern is <code>[&quot;leetcode&quot;, &quot;love&quot;, &quot;leetcode&quot;]</code>, the score is the number of users <code>x</code> such that <code>x</code> visited <code>&quot;leetcode&quot;</code> then visited <code>&quot;love&quot;</code> and visited <code>&quot;leetcode&quot;</code> <strong>one more time</strong> after that.</li>
	<li>Also, if the pattern is <code>[&quot;luffy&quot;, &quot;luffy&quot;, &quot;luffy&quot;]</code>, the score is the number of users <code>x</code> such that <code>x</code> visited <code>&quot;luffy&quot;</code> three different times at different timestamps.</li>
</ul>

<p>Return <em>the <strong>pattern</strong> with the largest <strong>score</strong></em>. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> username = [&quot;joe&quot;,&quot;joe&quot;,&quot;joe&quot;,&quot;james&quot;,&quot;james&quot;,&quot;james&quot;,&quot;james&quot;,&quot;mary&quot;,&quot;mary&quot;,&quot;mary&quot;], timestamp = [1,2,3,4,5,6,7,8,9,10], website = [&quot;home&quot;,&quot;about&quot;,&quot;career&quot;,&quot;home&quot;,&quot;cart&quot;,&quot;maps&quot;,&quot;home&quot;,&quot;home&quot;,&quot;about&quot;,&quot;career&quot;]
<strong>Output:</strong> [&quot;home&quot;,&quot;about&quot;,&quot;career&quot;]
<strong>Explanation:</strong> The tuples in this example are:
[&quot;joe&quot;,&quot;home&quot;,1],[&quot;joe&quot;,&quot;about&quot;,2],[&quot;joe&quot;,&quot;career&quot;,3],[&quot;james&quot;,&quot;home&quot;,4],[&quot;james&quot;,&quot;cart&quot;,5],[&quot;james&quot;,&quot;maps&quot;,6],[&quot;james&quot;,&quot;home&quot;,7],[&quot;mary&quot;,&quot;home&quot;,8],[&quot;mary&quot;,&quot;about&quot;,9], and [&quot;mary&quot;,&quot;career&quot;,10].
The pattern (&quot;home&quot;, &quot;about&quot;, &quot;career&quot;) has score 2 (joe and mary).
The pattern (&quot;home&quot;, &quot;cart&quot;, &quot;maps&quot;) has score 1 (james).
The pattern (&quot;home&quot;, &quot;cart&quot;, &quot;home&quot;) has score 1 (james).
The pattern (&quot;home&quot;, &quot;maps&quot;, &quot;home&quot;) has score 1 (james).
The pattern (&quot;cart&quot;, &quot;maps&quot;, &quot;home&quot;) has score 1 (james).
The pattern (&quot;home&quot;, &quot;home&quot;, &quot;home&quot;) has score 0 (no user visited home 3 times).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> username = [&quot;ua&quot;,&quot;ua&quot;,&quot;ua&quot;,&quot;ub&quot;,&quot;ub&quot;,&quot;ub&quot;], timestamp = [1,2,3,4,5,6], website = [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= username.length &lt;= 50</code></li>
	<li><code>1 &lt;= username[i].length &lt;= 10</code></li>
	<li><code>timestamp.length == username.length</code></li>
	<li><code>1 &lt;= timestamp[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>website.length == username.length</code></li>
	<li><code>1 &lt;= website[i].length &lt;= 10</code></li>
	<li><code>username[i]</code> and <code>website[i]</code> consist of lowercase English letters.</li>
	<li>It is guaranteed that there is at least one user who visited at least three websites.</li>
	<li>All the tuples <code>[username[i], timestamp[i], website[i]]</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostVisitedPattern(self, username: List[str], timestamp: List[int], website: List[str]) -> List[str]:
        d = defaultdict(list)
        for user, _, site in sorted(zip(username, timestamp, website), key=lambda x: x[1]):
            d[user].append(site)

        cnt = Counter()
        for sites in d.values():
            m = len(sites)
            s = set()
            if m > 2:
                for i in range(m - 2):
                    for j in range(i + 1, m - 1):
                        for k in range(j + 1, m):
                            s.add((sites[i], sites[j], sites[k]))
            for t in s:
                cnt[t] += 1
        return sorted(cnt.items(), key=lambda x: (-x[1], x[0]))[0][0]
```

### **Java**

```java
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Node>> d = new HashMap<>();
        int n = username.length;
        for (int i = 0; i < n; ++i) {
            String user = username[i];
            int ts = timestamp[i];
            String site = website[i];
            d.computeIfAbsent(user, k -> new ArrayList<>()).add(new Node(user, ts, site));
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (var sites : d.values()) {
            int m = sites.size();
            Set<String> s = new HashSet<>();
            if (m > 2) {
                Collections.sort(sites, (a, b) -> a.ts - b.ts);
                for (int i = 0; i < m - 2; ++i) {
                    for (int j = i + 1; j < m - 1; ++j) {
                        for (int k = j + 1; k < m; ++k) {
                            s.add(sites.get(i).site + "," + sites.get(j).site + ","
                                + sites.get(k).site);
                        }
                    }
                }
            }
            for (String t : s) {
                cnt.put(t, cnt.getOrDefault(t, 0) + 1);
            }
        }
        int mx = 0;
        String t = "";
        for (var e : cnt.entrySet()) {
            if (mx < e.getValue() || (mx == e.getValue() && e.getKey().compareTo(t) < 0)) {
                mx = e.getValue();
                t = e.getKey();
            }
        }
        return Arrays.asList(t.split(","));
    }
}

class Node {
    String user;
    int ts;
    String site;

    Node(String user, int ts, String site) {
        this.user = user;
        this.ts = ts;
        this.site = site;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> mostVisitedPattern(vector<string>& username, vector<int>& timestamp, vector<string>& website) {
        unordered_map<string, vector<pair<int, string>>> d;
        int n = username.size();
        for (int i = 0; i < n; ++i) {
            auto user = username[i];
            int ts = timestamp[i];
            auto site = website[i];
            d[user].emplace_back(ts, site);
        }
        unordered_map<string, int> cnt;
        for (auto& [_, sites] : d) {
            int m = sites.size();
            unordered_set<string> s;
            if (m > 2) {
                sort(sites.begin(), sites.end());
                for (int i = 0; i < m - 2; ++i) {
                    for (int j = i + 1; j < m - 1; ++j) {
                        for (int k = j + 1; k < m; ++k) {
                            s.insert(sites[i].second + "," + sites[j].second + "," + sites[k].second);
                        }
                    }
                }
            }
            for (auto& t : s) {
                cnt[t]++;
            }
        }
        int mx = 0;
        string t;
        for (auto& [p, v] : cnt) {
            if (mx < v || (mx == v && t > p)) {
                mx = v;
                t = p;
            }
        }
        return split(t, ',');
    }

    vector<string> split(string& s, char c) {
        vector<string> res;
        stringstream ss(s);
        string t;
        while (getline(ss, t, c)) {
            res.push_back(t);
        }
        return res;
    }
};
```

### **Go**

```go
func mostVisitedPattern(username []string, timestamp []int, website []string) []string {
	d := map[string][]pair{}
	for i, user := range username {
		ts := timestamp[i]
		site := website[i]
		d[user] = append(d[user], pair{ts, site})
	}
	cnt := map[string]int{}
	for _, sites := range d {
		m := len(sites)
		s := map[string]bool{}
		if m > 2 {
			sort.Slice(sites, func(i, j int) bool { return sites[i].ts < sites[j].ts })
			for i := 0; i < m-2; i++ {
				for j := i + 1; j < m-1; j++ {
					for k := j + 1; k < m; k++ {
						s[sites[i].site+","+sites[j].site+","+sites[k].site] = true
					}
				}
			}
		}
		for t := range s {
			cnt[t]++
		}
	}
	mx, t := 0, ""
	for p, v := range cnt {
		if mx < v || (mx == v && p < t) {
			mx = v
			t = p
		}
	}
	return strings.Split(t, ",")
}

type pair struct {
	ts   int
	site string
}
```

### **...**

```

```

<!-- tabs:end -->
