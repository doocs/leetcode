---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [17.26. Sparse Similarity](https://leetcode.cn/problems/sparse-similarity-lcci)

[中文文档](/lcci/17.26.Sparse%20Similarity/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The direct approach enumerates every pair of documents, builds a set for each, and computes the intersection and the union. Both the number of documents and the length of a document reach $500$, and the similarity of two arbitrary documents is close to $0$, so most of that work constructs and scans empty intersections.
>
> At most $1000$ pairs actually need a similarity. The running time should touch only documents that share at least one word.
>
> The documents that contain one word form pairs whose intersection includes at least that word. Summing these contributions yields the intersection size. Each document length is already known, so the union is the sum of the two lengths minus the intersection, and the words do not need to be scanned again.
>
> We therefore group document ids by word. Ids are appended from small to large, so each inverted list is ordered and the smaller id already comes first. The hash map stores only pairs with a nonempty intersection, and the similarity is computed from that key.

<!-- thinking:end -->

We use a hash map $d$ to record the document ids that contain each word. Documents are scanned in increasing id order, and the integers inside one document are distinct, so the ids in $d[x]$ are strictly increasing.

Two documents have similarity greater than $0$ exactly when they share at least one word. For every document list in $d$, we enumerate its id pairs and accumulate them in a hash map $cnt$. The key is the pair $(i, j)$ with $i < j$, and the value is the size of the intersection. The size of the union is $|docs[i]| + |docs[j]| - |\cap|$. An empty document never enters a list, so it is skipped.

While walking through $cnt$, the similarity is the ratio of the intersection to the union. Floating-point division can fall slightly short of the true value, so we add $10^{-9}$ before formatting the result to four decimal places. Each inverted list is already sorted by id, so the smaller id is the first component of the key.

The time complexity is $O(m \times n^2)$ and the space complexity is $O(S)$, where $n$ is the number of documents, $m$ is the maximum document length, and $S$ is the total number of words. At most $1000$ pairs have similarity greater than $0$, and the inner loops run once per element of those intersections, so the running time on the given inputs is below this bound.

<!-- tabs:start -->

#### Python3

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

#### Java

```java
class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        int n = docs.length;
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int x : docs[i]) {
                d.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
            }
        }
        Map<Long, Integer> cnt = new HashMap<>();
        for (List<Integer> ids : d.values()) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    long key = 1L * ids.get(i) * n + ids.get(j);
                    cnt.merge(key, 1, Integer::sum);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            long key = e.getKey();
            int v = e.getValue();
            int i = (int) (key / n), j = (int) (key % n);
            int tot = docs[i].length + docs[j].length - v;
            double x = (double) v / tot + 1e-9;
            ans.add(String.format("%d,%d: %.4f", i, j, x));
        }
        return ans;
    }
}
```

#### C++

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

#### Go

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
		ans = append(ans, fmt.Sprintf("%d,%d: %.4f", i, j, x))
	}
	return ans
}
```

#### TypeScript

```ts
function computeSimilarities(docs: number[][]): string[] {
    const n = docs.length;
    const d = new Map<number, number[]>();
    for (let i = 0; i < n; ++i) {
        for (const x of docs[i]) {
            if (!d.has(x)) {
                d.set(x, []);
            }
            d.get(x)!.push(i);
        }
    }
    const cnt = new Map<number, number>();
    for (const ids of d.values()) {
        const m = ids.length;
        for (let i = 0; i < m; ++i) {
            for (let j = i + 1; j < m; ++j) {
                const key = ids[i] * n + ids[j];
                cnt.set(key, (cnt.get(key) ?? 0) + 1);
            }
        }
    }
    const ans: string[] = [];
    for (const [key, v] of cnt) {
        const i = Math.floor(key / n);
        const j = key % n;
        const tot = docs[i].length + docs[j].length - v;
        const x = v / tot + 1e-9;
        ans.push(`${i},${j}: ${x.toFixed(4)}`);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
