---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20111.%20%E8%AE%A1%E7%AE%97%E9%99%A4%E6%B3%95/README.md
---

<!-- problem:start -->

# [剑指 Offer II 111. 计算除法](https://leetcode.cn/problems/vlzXQL)

## 题目描述

<!-- description:start -->

<p>给定一个变量对数组 <code>equations</code> 和一个实数值数组 <code>values</code> 作为已知条件，其中 <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code> 和 <code>values[i]</code> 共同表示等式 <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code> 。每个 <code>A<sub>i</sub></code> 或 <code>B<sub>i</sub></code> 是一个表示单个变量的字符串。</p>

<p>另有一些以数组 <code>queries</code> 表示的问题，其中 <code>queries[j] = [C<sub>j</sub>, D<sub>j</sub>]</code> 表示第 <code>j</code> 个问题，请你根据已知条件找出 <code>C<sub>j</sub> / D<sub>j</sub> = ?</code> 的结果作为答案。</p>

<p>返回 <strong>所有问题的答案</strong> 。如果存在某个无法确定的答案，则用 <code>-1.0</code> 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 <code>-1.0</code> 替代这个答案。</p>

<p><strong>注意：</strong>输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>equations = [[&quot;a&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;c&quot;]], values = [2.0,3.0], queries = [[&quot;a&quot;,&quot;c&quot;],[&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;e&quot;],[&quot;a&quot;,&quot;a&quot;],[&quot;x&quot;,&quot;x&quot;]]
<strong>输出：</strong>[6.00000,0.50000,-1.00000,1.00000,-1.00000]
<strong>解释：</strong>
条件：<em>a / b = 2.0</em>, <em>b / c = 3.0</em>
问题：<em>a / c = ?</em>, <em>b / a = ?</em>, <em>a / e = ?</em>, <em>a / a = ?</em>, <em>x / x = ?</em>
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>equations = [[&quot;a&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;c&quot;],[&quot;bc&quot;,&quot;cd&quot;]], values = [1.5,2.5,5.0], queries = [[&quot;a&quot;,&quot;c&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;bc&quot;,&quot;cd&quot;],[&quot;cd&quot;,&quot;bc&quot;]]
<strong>输出：</strong>[3.75000,0.40000,5.00000,0.20000]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>equations = [[&quot;a&quot;,&quot;b&quot;]], values = [0.5], queries = [[&quot;a&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;c&quot;],[&quot;x&quot;,&quot;y&quot;]]
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

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 399&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/evaluate-division/">https://leetcode.cn/problems/evaluate-division/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calcEquation(
        self, equations: List[List[str]], values: List[float], queries: List[List[str]]
    ) -> List[float]:
        n = len(equations)
        p = list(range(n << 1))
        w = [1.0] * (n << 1)

        def find(x):
            if p[x] != x:
                origin = p[x]
                p[x] = find(p[x])
                w[x] *= w[origin]
            return p[x]

        mp = {}
        idx = 0
        for i, e in enumerate(equations):
            a, b = e[0], e[1]
            if a not in mp:
                mp[a] = idx
                idx += 1
            if b not in mp:
                mp[b] = idx
                idx += 1
            pa, pb = find(mp[a]), find(mp[b])
            if pa == pb:
                continue
            p[pa] = pb
            w[pa] = w[mp[b]] * values[i] / w[mp[a]]

        res = []
        for q in queries:
            c, d = q[0], q[1]
            if c not in mp or d not in mp:
                res.append(-1.0)
            else:
                pa, pb = find(mp[c]), find(mp[d])
                res.append(w[mp[c]] / w[mp[d]] if pa == pb else -1.0)
        return res
```

#### Java

```java
class Solution {
    private int[] p;
    private double[] w;

    public double[] calcEquation(
        List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        p = new int[n << 1];
        w = new double[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            w[i] = 1.0;
        }
        Map<String, Integer> mp = new HashMap<>(n << 1);
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            List<String> e = equations.get(i);
            String a = e.get(0), b = e.get(1);
            if (!mp.containsKey(a)) {
                mp.put(a, idx++);
            }
            if (!mp.containsKey(b)) {
                mp.put(b, idx++);
            }
            int pa = find(mp.get(a)), pb = find(mp.get(b));
            if (pa == pb) {
                continue;
            }
            p[pa] = pb;
            w[pa] = w[mp.get(b)] * values[i] / w[mp.get(a)];
        }
        int m = queries.size();
        double[] res = new double[m];
        for (int i = 0; i < m; ++i) {
            String c = queries.get(i).get(0), d = queries.get(i).get(1);
            Integer id1 = mp.get(c), id2 = mp.get(d);
            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                int pa = find(id1), pb = find(id2);
                res[i] = pa == pb ? w[id1] / w[id2] : -1.0;
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            int origin = p[x];
            p[x] = find(p[x]);
            w[x] *= w[origin];
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;
    vector<double> w;

    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        int n = equations.size();
        for (int i = 0; i < (n << 1); ++i) {
            p.push_back(i);
            w.push_back(1.0);
        }
        unordered_map<string, int> mp;
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            auto e = equations[i];
            string a = e[0], b = e[1];
            if (mp.find(a) == mp.end()) mp[a] = idx++;
            if (mp.find(b) == mp.end()) mp[b] = idx++;
            int pa = find(mp[a]), pb = find(mp[b]);
            if (pa == pb) continue;
            p[pa] = pb;
            w[pa] = w[mp[b]] * values[i] / w[mp[a]];
        }
        int m = queries.size();
        vector<double> res;
        for (int i = 0; i < m; ++i) {
            string c = queries[i][0], d = queries[i][1];
            if (mp.find(c) == mp.end() || mp.find(d) == mp.end())
                res.push_back(-1.0);
            else {
                int pa = find(mp[c]), pb = find(mp[d]);
                res.push_back(pa == pb ? w[mp[c]] / w[mp[d]] : -1.0);
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) {
            int origin = p[x];
            p[x] = find(p[x]);
            w[x] *= w[origin];
        }
        return p[x];
    }
};
```

#### Go

```go
var p []int
var w []float64

func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	n := len(equations)
	p = make([]int, (n<<1)+10)
	w = make([]float64, (n<<1)+10)
	for i := 0; i < (n<<1)+10; i++ {
		p[i] = i
		w[i] = 1.0
	}
	mp := make(map[string]int)
	idx := 1
	for i, e := range equations {
		a, b := e[0], e[1]
		if mp[a] == 0 {
			mp[a] = idx
			idx++
		}
		if mp[b] == 0 {
			mp[b] = idx
			idx++
		}
		pa, pb := find(mp[a]), find(mp[b])
		if pa == pb {
			continue
		}
		p[pa] = pb
		w[pa] = w[mp[b]] * values[i] / w[mp[a]]
	}
	var res []float64
	for _, q := range queries {
		c, d := q[0], q[1]
		if mp[c] == 0 || mp[d] == 0 {
			res = append(res, -1.0)
		} else {
			pa, pb := find(mp[c]), find(mp[d])
			if pa == pb {
				res = append(res, w[mp[c]]/w[mp[d]])
			} else {
				res = append(res, -1.0)
			}
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		origin := p[x]
		p[x] = find(p[x])
		w[x] *= w[origin]
	}
	return p[x]
}
```

#### Swift

```swift
class Solution {
    private var parent = [Int]()
    private var weight = [Double]()

    func calcEquation(
        _ equations: [[String]],
        _ values: [Double],
        _ queries: [[String]]
    ) -> [Double] {
        let n = equations.count
        parent = Array(0..<(n * 2))
        weight = Array(repeating: 1.0, count: n * 2)

        var map = [String: Int]()
        var index = 0

        for i in 0..<n {
            let a = equations[i][0]
            let b = equations[i][1]

            if map[a] == nil {
                map[a] = index
                index += 1
            }
            if map[b] == nil {
                map[b] = index
                index += 1
            }

            let pa = find(map[a]!)
            let pb = find(map[b]!)

            if pa != pb {
                parent[pa] = pb
                weight[pa] = weight[map[b]!] * values[i] / weight[map[a]!]
            }
        }

        var result = [Double]()

        for query in queries {
            let (c, d) = (query[0], query[1])
            if let id1 = map[c], let id2 = map[d], find(id1) == find(id2) {
                result.append(weight[id1] / weight[id2])
            } else {
                result.append(-1.0)
            }
        }

        return result
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            let origin = parent[x]
            parent[x] = find(parent[x])
            weight[x] *= weight[origin]
        }
        return parent[x]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
