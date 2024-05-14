# [399. 除法求值](https://leetcode.cn/problems/evaluate-division)

[English Version](/solution/0300-0399/0399.Evaluate%20Division/README_EN.md)

<!-- tags:深度优先搜索,广度优先搜索,并查集,图,数组,最短路 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个变量对数组 <code>equations</code> 和一个实数值数组 <code>values</code> 作为已知条件，其中 <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code> 和 <code>values[i]</code> 共同表示等式 <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code> 。每个 <code>A<sub>i</sub></code> 或 <code>B<sub>i</sub></code> 是一个表示单个变量的字符串。</p>

<p>另有一些以数组 <code>queries</code> 表示的问题，其中 <code>queries[j] = [C<sub>j</sub>, D<sub>j</sub>]</code> 表示第 <code>j</code> 个问题，请你根据已知条件找出 <code>C<sub>j</sub> / D<sub>j</sub> = ?</code> 的结果作为答案。</p>

<p>返回 <strong>所有问题的答案</strong> 。如果存在某个无法确定的答案，则用 <code>-1.0</code> 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 <code>-1.0</code> 替代这个答案。</p>

<p><strong>注意：</strong>输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。</p>

<p><strong>注意：</strong>未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
<strong>输出：</strong>[6.00000,0.50000,-1.00000,1.00000,-1.00000]
<strong>解释：</strong>
条件：<em>a / b = 2.0</em>, <em>b / c = 3.0</em>
问题：<em>a / c = ?</em>, <em>b / a = ?</em>, <em>a / e = ?</em>, <em>a / a = ?</em>, <em>x / x = ?</em>
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
注意：x 是未定义的 =&gt; -1.0</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
<strong>输出：</strong>[3.75000,0.40000,5.00000,0.20000]
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
<strong>输出：</strong>[0.50000,2.00000,-1.00000,-1.00000]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= equations.length &lt;= 20</code></li>
	<li><code>equations[i].length == 2</code></li>
	<li><code>1 &lt;= A<sub>i</sub>.length, B<sub>i</sub>.length &lt;= 5</code></li>
	<li><code>values.length == equations.length</code></li>
	<li><code>0.0 &lt; values[i] &lt;= 20.0</code></li>
	<li><code>1 &lt;= queries.length &lt;= 20</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= C<sub>j</sub>.length, D<sub>j</sub>.length &lt;= 5</code></li>
	<li><code>A<sub>i</sub>, B<sub>i</sub>, C<sub>j</sub>, D<sub>j</sub></code> 由小写英文字母与数字组成</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def calcEquation(
        self, equations: List[List[str]], values: List[float], queries: List[List[str]]
    ) -> List[float]:
        def find(x):
            if p[x] != x:
                origin = p[x]
                p[x] = find(p[x])
                w[x] *= w[origin]
            return p[x]

        w = defaultdict(lambda: 1)
        p = defaultdict()
        for a, b in equations:
            p[a], p[b] = a, b
        for i, v in enumerate(values):
            a, b = equations[i]
            pa, pb = find(a), find(b)
            if pa == pb:
                continue
            p[pa] = pb
            w[pa] = w[b] * v / w[a]
        return [
            -1 if c not in p or d not in p or find(c) != find(d) else w[c] / w[d]
            for c, d in queries
        ]
```

```java
class Solution {
    private Map<String, String> p;
    private Map<String, Double> w;

    public double[] calcEquation(
        List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        p = new HashMap<>();
        w = new HashMap<>();
        for (List<String> e : equations) {
            p.put(e.get(0), e.get(0));
            p.put(e.get(1), e.get(1));
            w.put(e.get(0), 1.0);
            w.put(e.get(1), 1.0);
        }
        for (int i = 0; i < n; ++i) {
            List<String> e = equations.get(i);
            String a = e.get(0), b = e.get(1);
            String pa = find(a), pb = find(b);
            if (Objects.equals(pa, pb)) {
                continue;
            }
            p.put(pa, pb);
            w.put(pa, w.get(b) * values[i] / w.get(a));
        }
        int m = queries.size();
        double[] ans = new double[m];
        for (int i = 0; i < m; ++i) {
            String c = queries.get(i).get(0), d = queries.get(i).get(1);
            ans[i] = !p.containsKey(c) || !p.containsKey(d) || !Objects.equals(find(c), find(d))
                ? -1.0
                : w.get(c) / w.get(d);
        }
        return ans;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            String origin = p.get(x);
            p.put(x, find(p.get(x)));
            w.put(x, w.get(x) * w.get(origin));
        }
        return p.get(x);
    }
}
```

```cpp
class Solution {
public:
    unordered_map<string, string> p;
    unordered_map<string, double> w;

    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        int n = equations.size();
        for (auto e : equations) {
            p[e[0]] = e[0];
            p[e[1]] = e[1];
            w[e[0]] = 1.0;
            w[e[1]] = 1.0;
        }
        for (int i = 0; i < n; ++i) {
            vector<string> e = equations[i];
            string a = e[0], b = e[1];
            string pa = find(a), pb = find(b);
            if (pa == pb) continue;
            p[pa] = pb;
            w[pa] = w[b] * values[i] / w[a];
        }
        int m = queries.size();
        vector<double> ans(m);
        for (int i = 0; i < m; ++i) {
            string c = queries[i][0], d = queries[i][1];
            ans[i] = p.find(c) == p.end() || p.find(d) == p.end() || find(c) != find(d) ? -1.0 : w[c] / w[d];
        }
        return ans;
    }

    string find(string x) {
        if (p[x] != x) {
            string origin = p[x];
            p[x] = find(p[x]);
            w[x] *= w[origin];
        }
        return p[x];
    }
};
```

```go
func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	p := make(map[string]string)
	w := make(map[string]float64)
	for _, e := range equations {
		a, b := e[0], e[1]
		p[a], p[b] = a, b
		w[a], w[b] = 1.0, 1.0
	}

	var find func(x string) string
	find = func(x string) string {
		if p[x] != x {
			origin := p[x]
			p[x] = find(p[x])
			w[x] *= w[origin]
		}
		return p[x]
	}

	for i, v := range values {
		a, b := equations[i][0], equations[i][1]
		pa, pb := find(a), find(b)
		if pa == pb {
			continue
		}
		p[pa] = pb
		w[pa] = w[b] * v / w[a]
	}
	var ans []float64
	for _, e := range queries {
		c, d := e[0], e[1]
		if p[c] == "" || p[d] == "" || find(c) != find(d) {
			ans = append(ans, -1.0)
		} else {
			ans = append(ans, w[c]/w[d])
		}
	}
	return ans
}
```

```rust
use std::collections::HashMap;

#[derive(Debug)]
pub struct DSUNode {
    parent: String,
    weight: f64,
}

pub struct DisjointSetUnion {
    nodes: HashMap<String, DSUNode>,
}

impl DisjointSetUnion {
    pub fn new(equations: &Vec<Vec<String>>) -> DisjointSetUnion {
        let mut nodes = HashMap::new();
        for equation in equations.iter() {
            for iter in equation.iter() {
                nodes.insert(iter.clone(), DSUNode {
                    parent: iter.clone(),
                    weight: 1.0,
                });
            }
        }
        DisjointSetUnion { nodes }
    }

    pub fn find(&mut self, v: &String) -> String {
        let origin = self.nodes[v].parent.clone();
        if origin == *v {
            return origin;
        }

        let root = self.find(&origin);
        self.nodes.get_mut(v).unwrap().parent = root.clone();
        self.nodes.get_mut(v).unwrap().weight *= self.nodes[&origin].weight;
        root
    }

    pub fn union(&mut self, a: &String, b: &String, v: f64) {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            return;
        }
        let (wa, wb) = (self.nodes[a].weight, self.nodes[b].weight);
        self.nodes.get_mut(&pa).unwrap().parent = pb;
        self.nodes.get_mut(&pa).unwrap().weight = (wb * v) / wa;
    }

    pub fn exist(&mut self, k: &String) -> bool {
        self.nodes.contains_key(k)
    }

    pub fn calc_value(&mut self, a: &String, b: &String) -> f64 {
        if !self.exist(a) || !self.exist(b) || self.find(a) != self.find(b) {
            -1.0
        } else {
            let wa = self.nodes[a].weight;
            let wb = self.nodes[b].weight;
            wa / wb
        }
    }
}

impl Solution {
    pub fn calc_equation(
        equations: Vec<Vec<String>>,
        values: Vec<f64>,
        queries: Vec<Vec<String>>
    ) -> Vec<f64> {
        let mut dsu = DisjointSetUnion::new(&equations);
        for (i, &v) in values.iter().enumerate() {
            let (a, b) = (&equations[i][0], &equations[i][1]);
            dsu.union(a, b, v);
        }

        let mut ans = vec![];
        for querie in queries {
            let (c, d) = (&querie[0], &querie[1]);
            ans.push(dsu.calc_value(c, d));
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
