# [面试题 17.26. 稀疏相似度](https://leetcode.cn/problems/sparse-similarity-lcci)

[English Version](/lcci/17.26.Sparse%20Similarity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常&ldquo;稀疏&rdquo;，也就是说任选 2 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。</p>
<p>输入为一个二维数组 <code>docs</code>，<code>docs[i]</code>&nbsp;表示&nbsp;id 为 <code>i</code> 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 <code>{id1},{id2}: {similarity}</code>，其中 <code>id1</code> 为两个文档中较小的 id，<code>similarity</code> 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。</p>
<p><strong>示例:</strong></p>
<pre><strong>输入:</strong> 
<code>[
&nbsp; [14, 15, 100, 9, 3],
&nbsp; [32, 1, 9, 3, 5],
&nbsp; [15, 29, 2, 6, 8, 7],
&nbsp; [7, 10]
]</code>
<strong>输出:</strong>
[
&nbsp; &quot;0,1: 0.2500&quot;,
&nbsp; &quot;0,2: 0.1000&quot;,
&nbsp; &quot;2,3: 0.1429&quot;
]</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>docs.length &lt;= 500</code></li>
	<li><code>docs[i].length &lt;= 500</code></li>
	<li>相似度大于 0 的文档对数不会超过 1000</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表 $d$ 记录每个单词对应了哪些文档。

遍历 $d$ 的每一个文档列表，其任意两个文档都有相似度，我们用哈希表 $s$ 累加两个文档同时出现的单词个数。最后遍历 $s$，计算相似度。

时间复杂度 $O(n^3)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
