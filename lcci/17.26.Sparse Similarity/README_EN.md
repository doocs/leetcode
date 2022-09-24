# [17.26. Sparse Similarity](https://leetcode.cn/problems/sparse-similarity-lcci)

[中文文档](/lcci/17.26.Sparse%20Similarity/README.md)

## Description

<p>The similarity of two documents (each with distinct words) is defined to be the size of the intersection divided by the size of the union. For example, if the documents consist of integers, the similarity of {1, 5, 3} and {1, 7, 2, 3} is 0.4, because the intersection has size 2 and the union has size 5.&nbsp;We have a long list of documents (with distinct values and each with an associated ID) where the similarity is believed to be &quot;sparse&quot;. That is, any two arbitrarily selected documents are very likely to have similarity 0. Design an algorithm that returns a list of pairs of document IDs and the associated similarity.</p>
<p>Input is a 2D array&nbsp;<code>docs</code>, where&nbsp;<code>docs[i]</code>&nbsp;is the document with id&nbsp;<code>i</code>. Return an array of strings, where each string represents a pair of documents with similarity greater than 0. The string should be formatted as&nbsp; <code>{id1},{id2}: {similarity}</code>, where <code>id1</code>&nbsp;is the smaller id in the two documents, and <code>similarity</code> is the similarity rounded to four decimal places. You can return the array in any order.</p>
<p><strong>Example:</strong></p>
<pre>

<strong>Input:</strong>

<code>[

&nbsp; [14, 15, 100, 9, 3],

&nbsp; [32, 1, 9, 3, 5],

&nbsp; [15, 29, 2, 6, 8, 7],

&nbsp; [7, 10]

]</code>

<strong>Output:</strong>

[

&nbsp; &quot;0,1: 0.2500&quot;,

&nbsp; &quot;0,2: 0.1000&quot;,

&nbsp; &quot;2,3: 0.1429&quot;

]</pre>

<p><strong>Note: </strong></p>
<ul>
	<li><code>docs.length &lt;= 500</code></li>
	<li><code>docs[i].length &lt;= 500</code></li>
	<li>The number of document pairs with similarity greater than 0 will not exceed 1000.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def computeSimilarities(self, docs: List[List[int]]) -> List[str]:
        eps = 1e-9
        d = defaultdict(list)
        for i, v in enumerate(docs):
            for x in v:
                d[x].append(i)
        cnt = Counter()
        for ids in d.values():
            n = len(ids)
            for i in range(n):
                for j in range(i + 1, n):
                    cnt[(ids[i], ids[j])] += 1
        ans = []
        for (i, j), v in cnt.items():
            tot = len(docs[i]) + len(docs[j]) - v
            x = v / tot + eps
            ans.append(f'{i},{j}: {x:.4f}')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < docs.length; ++i) {
            for (int v : docs[i]) {
                d.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            }
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (var ids : d.values()) {
            int n = ids.size();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    String k = ids.get(i) + "," + ids.get(j);
                    cnt.put(k, cnt.getOrDefault(k, 0) + 1);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            String k = e.getKey();
            int v = e.getValue();
            String[] t = k.split(",");
            int i = Integer.parseInt(t[0]), j = Integer.parseInt(t[1]);
            int tot = docs[i].length + docs[j].length - v;
            double x = (double) v / tot;
            ans.add(String.format("%s: %.4f", k, x));
        }
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<string> computeSimilarities(vector<vector<int>>& docs) {
        double eps = 1e-9;
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < docs.size(); ++i) {
            for (int v : docs[i]) {
                d[v].push_back(i);
            }
        }
        map<pii, int> cnt;
        for (auto& [_, ids] : d) {
            int n = ids.size();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    cnt[{ids[i], ids[j]}]++;
                }
            }
        }
        vector<string> ans;
        for (auto& [k, v] : cnt) {
            auto [i, j] = k;
            int tot = docs[i].size() + docs[j].size() - v;
            double x = (double) v / tot + eps;
            char t[20];
            sprintf(t, "%d,%d: %0.4lf", i, j, x);
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func computeSimilarities(docs [][]int) []string {
	d := map[int][]int{}
	for i, v := range docs {
		for _, x := range v {
			d[x] = append(d[x], i)
		}
	}
	type pair struct{ i, j int }
	cnt := map[pair]int{}
	for _, ids := range d {
		n := len(ids)
		for i := 0; i < n; i++ {
			for j := i + 1; j < n; j++ {
				k := pair{ids[i], ids[j]}
				cnt[k]++
			}
		}
	}
	ans := []string{}
	for k, v := range cnt {
		i, j := k.i, k.j
		tot := len(docs[i]) + len(docs[j]) - v
		x := float64(v)/float64(tot) + 1e-9
		ans = append(ans, strconv.Itoa(i)+","+strconv.Itoa(j)+": "+fmt.Sprintf("%.4f", x))
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
