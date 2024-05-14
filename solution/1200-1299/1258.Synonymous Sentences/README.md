# [1258. 近义词句子 🔒](https://leetcode.cn/problems/synonymous-sentences)

[English Version](/solution/1200-1299/1258.Synonymous%20Sentences/README_EN.md)

<!-- tags:并查集,数组,哈希表,字符串,回溯 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个近义词表&nbsp;<code>synonyms</code> 和一个句子&nbsp;<code>text</code>&nbsp;，&nbsp;<code>synonyms</code> 表中是一些近义词对 ，你可以将句子&nbsp;<code>text</code> 中每个单词用它的近义词来替换。</p>

<p>请你找出所有用近义词替换后的句子，按&nbsp;<strong>字典序排序</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]],
text = &quot;I am happy today but was sad yesterday&quot;
<strong>输出：
</strong>[&quot;I am cheerful today but was sad yesterday&quot;,
&quot;I am cheerful today but was sorrow yesterday&quot;,
&quot;I am happy today but was sad yesterday&quot;,
&quot;I am happy today but was sorrow yesterday&quot;,
&quot;I am joy today but was sad yesterday&quot;,
&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>synonyms[0] != synonyms[1]</code></li>
	<li>所有单词仅包含英文字母，且长度最多为&nbsp;<code>10</code> 。</li>
	<li><code>text</code>&nbsp;最多包含&nbsp;<code>10</code> 个单词，且单词间用单个空格分隔开。</li>
</ul>

## 解法

### 方法一：并查集 + DFS

我们可以发现，题目中的近义词是可以传递的，即如果 `a` 和 `b` 是近义词，`b` 和 `c` 是近义词，那么 `a` 和 `c` 也是近义词。因此，我们可以用并查集找出近义词的连通分量，每个连通分量中的单词都是近义词，并且按字典序从小到大排列。

接下来，我们将字符串 `text` 按空格分割成单词数组 `sentence`，对于每个单词 `sentence[i]`，如果它是近义词，那么我们就将它替换成连通分量中的所有单词，否则不替换。这样，我们就可以得到所有的句子。这可以通过 DFS 搜索实现。

我们设计一个函数 $dfs(i)$，表示从 `sentence` 的第 $i$ 个单词开始，将其替换成连通分量中的所有单词，然后递归地处理后面的单词。

如果 $i$ 大于等于 `sentence` 的长度，那么说明我们已经处理完了所有的单词，此时将当前的句子加入答案数组中。否则，如果 `sentence[i]` 不是近义词，那么我们不替换它，直接将它加入当前的句子中，然后递归地处理后面的单词。否则，我们将 `sentence[i]` 替换成连通分量中的所有单词，同样递归地处理后面的单词。

最后，返回答案数组即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是单词的数量。

<!-- tabs:start -->

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]


class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        def dfs(i):
            if i >= len(sentence):
                ans.append(' '.join(t))
                return
            if sentence[i] not in d:
                t.append(sentence[i])
                dfs(i + 1)
                t.pop()
            else:
                root = uf.find(d[sentence[i]])
                for j in g[root]:
                    t.append(words[j])
                    dfs(i + 1)
                    t.pop()

        words = list(set(chain.from_iterable(synonyms)))
        d = {w: i for i, w in enumerate(words)}
        uf = UnionFind(len(d))
        for a, b in synonyms:
            uf.union(d[a], d[b])
        g = defaultdict(list)
        for i in range(len(words)):
            g[uf.find(i)].append(i)
        for k in g.keys():
            g[k].sort(key=lambda i: words[i])
        sentence = text.split()
        ans = []
        t = []
        dfs(0)
        return ans
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    private List<String> ans = new ArrayList<>();
    private List<String> t = new ArrayList<>();
    private List<String> words;
    private Map<String, Integer> d;
    private UnionFind uf;
    private List<Integer>[] g;
    private String[] sentence;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Set<String> ss = new HashSet<>();
        for (List<String> pairs : synonyms) {
            ss.addAll(pairs);
        }
        words = new ArrayList<>(ss);
        int n = words.size();
        d = new HashMap<>(n);
        for (int i = 0; i < words.size(); ++i) {
            d.put(words.get(i), i);
        }
        uf = new UnionFind(n);
        for (List<String> pairs : synonyms) {
            uf.union(d.get(pairs.get(0)), d.get(pairs.get(1)));
        }
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            g[uf.find(i)].add(i);
        }
        for (List<Integer> e : g) {
            e.sort((i, j) -> words.get(i).compareTo(words.get(j)));
        }
        sentence = text.split(" ");
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= sentence.length) {
            ans.add(String.join(" ", t));
            return;
        }
        if (!d.containsKey(sentence[i])) {
            t.add(sentence[i]);
            dfs(i + 1);
            t.remove(t.size() - 1);
        } else {
            for (int j : g[uf.find(d.get(sentence[i]))]) {
                t.add(words.get(j));
                dfs(i + 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    vector<string> generateSentences(vector<vector<string>>& synonyms, string text) {
        unordered_set<string> ss;
        for (auto& pairs : synonyms) {
            ss.insert(pairs[0]);
            ss.insert(pairs[1]);
        }
        vector<string> words{ss.begin(), ss.end()};
        unordered_map<string, int> d;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            d[words[i]] = i;
        }
        UnionFind uf(n);
        for (auto& pairs : synonyms) {
            uf.unite(d[pairs[0]], d[pairs[1]]);
        }
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            g[uf.find(i)].push_back(i);
        }
        for (int i = 0; i < n; ++i) {
            sort(g[i].begin(), g[i].end(), [&](int a, int b) {
                return words[a] < words[b];
            });
        }
        vector<string> sentence;
        string s;
        istringstream iss(text);
        while (iss >> s) {
            sentence.emplace_back(s);
        }
        vector<string> ans;
        vector<string> t;
        function<void(int)> dfs = [&](int i) {
            if (i >= sentence.size()) {
                string s;
                for (int j = 0; j < t.size(); ++j) {
                    if (j) {
                        s += " ";
                    }
                    s += t[j];
                }
                ans.emplace_back(s);
                return;
            }
            if (!d.count(sentence[i])) {
                t.emplace_back(sentence[i]);
                dfs(i + 1);
                t.pop_back();
            } else {
                for (int j : g[uf.find(d[sentence[i]])]) {
                    t.emplace_back(words[j]);
                    dfs(i + 1);
                    t.pop_back();
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func generateSentences(synonyms [][]string, text string) (ans []string) {
	ss := map[string]bool{}
	for _, pairs := range synonyms {
		ss[pairs[0]] = true
		ss[pairs[1]] = true
	}
	words := []string{}
	for w := range ss {
		words = append(words, w)
	}
	d := map[string]int{}
	for i, w := range words {
		d[w] = i
	}
	uf := newUnionFind(len(words))
	for _, pairs := range synonyms {
		uf.union(d[pairs[0]], d[pairs[1]])
	}
	g := make([][]int, len(words))
	for i := range g {
		g[uf.find(i)] = append(g[uf.find(i)], i)
	}
	for i := range g {
		sort.Slice(g[i], func(a, b int) bool { return words[g[i][a]] < words[g[i][b]] })
	}
	t := []string{}
	sentences := strings.Split(text, " ")
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(sentences) {
			ans = append(ans, strings.Join(t, " "))
			return
		}
		if _, ok := ss[sentences[i]]; !ok {
			t = append(t, sentences[i])
			dfs(i + 1)
			t = t[:len(t)-1]
			return
		} else {
			for _, j := range g[uf.find(d[sentences[i]])] {
				t = append(t, words[j])
				dfs(i + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}
```

<!-- tabs:end -->

<!-- end -->
