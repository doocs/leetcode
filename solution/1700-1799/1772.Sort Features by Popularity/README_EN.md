# [1772. Sort Features by Popularity](https://leetcode.com/problems/sort-features-by-popularity)

[中文文档](/solution/1700-1799/1772.Sort%20Features%20by%20Popularity/README.md)

## Description

<p>You are given a string array <code>features</code> where <code>features[i]</code> is a single word that represents the name of a feature of the latest product you are working on. You have made a survey where users have reported which features they like. You are given a string array <code>responses</code>, where each <code>responses[i]</code> is a string containing space-separated words.</p>

<p>The <strong>popularity</strong> of a feature is the number of <code>responses[i]</code> that contain the feature. You want to sort the features in non-increasing order by their popularity. If two features have the same popularity, order them by their original index in <code>features</code>. Notice that one response could contain the same feature multiple times; this feature is only counted once in its popularity.</p>

<p>Return <em>the features in sorted order.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> features = [&quot;cooler&quot;,&quot;lock&quot;,&quot;touch&quot;], responses = [&quot;i like cooler cooler&quot;,&quot;lock touch cool&quot;,&quot;locker like touch&quot;]
<strong>Output:</strong> [&quot;touch&quot;,&quot;cooler&quot;,&quot;lock&quot;]
<strong>Explanation:</strong> appearances(&quot;cooler&quot;) = 1, appearances(&quot;lock&quot;) = 1, appearances(&quot;touch&quot;) = 2. Since &quot;cooler&quot; and &quot;lock&quot; both had 1 appearance, &quot;cooler&quot; comes first because &quot;cooler&quot; came first in the features array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> features = [&quot;a&quot;,&quot;aa&quot;,&quot;b&quot;,&quot;c&quot;], responses = [&quot;a&quot;,&quot;a aa&quot;,&quot;a a a a a&quot;,&quot;b a&quot;]
<strong>Output:</strong> [&quot;a&quot;,&quot;aa&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= features.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= features[i].length &lt;= 10</code></li>
	<li><code>features</code> contains no duplicates.</li>
	<li><code>features[i]</code> consists of lowercase letters.</li>
	<li><code>1 &lt;= responses.length &lt;= 10<sup>2</sup></code></li>
	<li><code>1 &lt;= responses[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>responses[i]</code> consists of lowercase letters and spaces.</li>
	<li><code>responses[i]</code> contains no two consecutive spaces.</li>
	<li><code>responses[i]</code> has no leading or trailing spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for r in responses:
            ws = set(r.split())
            for s in ws:
                cnt[s] += 1
        return sorted(features, key=lambda x: -cnt[x])
```

### **Java**

```java
class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String r : responses) {
            Set<String> ws = new HashSet<>();
            for (String w : r.split(" ")) {
                ws.add(w);
            }
            for (String w : ws) {
                cnt.put(w, cnt.getOrDefault(w, 0) + 1);
            }
        }
        int n = features.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> {
            int d = cnt.getOrDefault(features[j], 0) - cnt.getOrDefault(features[i], 0);
            return d == 0 ? i - j : d;
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> sortFeatures(vector<string>& features, vector<string>& responses) {
        unordered_map<string, int> cnt;
        for (auto& r : responses) {
            stringstream ss(r);
            string t;
            unordered_set<string> ws;
            while (ss >> t) {
                ws.insert(t);
            }
            for (auto& w : ws) {
                cnt[w]++;
            }
        }
        int n = features.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) -> bool {
            int d = cnt[features[i]] - cnt[features[j]];
            return d > 0 || (d == 0 && i < j);
        });
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
};
```

### **Go**

```go
func sortFeatures(features []string, responses []string) []string {
	cnt := map[string]int{}
	for _, r := range responses {
		ws := map[string]bool{}
		for _, s := range strings.Split(r, " ") {
			ws[s] = true
		}
		for w := range ws {
			cnt[w]++
		}
	}
	n := len(features)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		d := cnt[features[idx[i]]] - cnt[features[idx[j]]]
		return d > 0 || (d == 0 && idx[i] < idx[j])
	})
	ans := make([]string, n)
	for i := range ans {
		ans[i] = features[idx[i]]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
