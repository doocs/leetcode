# [2307. 检查方程中的矛盾之处](https://leetcode.cn/problems/check-for-contradictions-in-equations)

[English Version](/solution/2300-2399/2307.Check%20for%20Contradictions%20in%20Equations/README_EN.md)

<!-- tags:深度优先搜索,并查集,图,数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由字符串二维数组&nbsp;<code>equations</code> 和实数数组&nbsp;&nbsp;<code>values</code>&nbsp;，其中 <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code>，<code>values[i]</code> 表示 <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code>.。</p>

<p>确定方程中是否存在矛盾。<em>如果存在矛盾则返回 <code>true</code>，否则返回 <code>false</code></em>。</p>

<p><b>注意</b>:</p>

<ul>
	<li>当检查两个数字是否相等时，检查它们的&nbsp;<strong>绝对差值&nbsp;</strong>是否小于 <code>10<sup>-5</sup></code>.</li>
	<li>生成的测试用例没有针对精度的用例，即使用 <code>double</code> 就足以解决问题。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> equations = [["a","b"],["b","c"],["a","c"]], values = [3,0.5,1.5]
<strong>输出:</strong> false
<strong>解释:
</strong>给定的方程为: a / b = 3, b / c = 0.5, a / c = 1.5
方程中没有矛盾。满足所有方程的一个可能的分配是:
a = 3, b = 1 和 c = 2.
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> equations = [["le","et"],["le","code"],["code","et"]], values = [2,5,0.5]
<strong>输出:</strong> true
<strong>解释:</strong>
给定的方程为: le / et = 2, le / code = 5, code / et = 0.5
根据前两个方程，我们得到 code / et = 0.4.
因为第三个方程是 code / et = 0.5, 所以矛盾。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= equations.length &lt;= 100</code></li>
	<li><code>equations[i].length == 2</code></li>
	<li><code>1 &lt;= A<sub>i</sub>.length, B<sub>i</sub>.length &lt;= 5</code></li>
	<li><code>A<sub>i</sub></code>, <code>B<sub>i</sub></code> 由小写英文字母组成。</li>
	<li><code>equations.length == values.length</code></li>
	<li><code>0.0 &lt; values[i] &lt;= 10.0</code></li>
	<li><code>values[i]</code> 小数点后最多 2 位。</li>
</ul>

## 解法

### 方法一：带权并查集

我们先将字符串转换成从 $0$ 开始的整数，然后遍历所有的等式，将等式中的两个字符串映射成对应的整数 $a$ 和 $b$，如果这两个整数不在同一个集合中，就将它们合并到同一个集合中，并且记录下两个整数的权值，即 $a$ 与 $b$ 的比值。如果这两个整数在同一个集合中，就判断它们的权值是否满足等式，如果不满足就返回 `true`。

时间复杂度 $O(n \times \log n)$ 或 $O(n \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 是等式的数量。

相似题目：

-   [399. 除法求值](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0399.Evaluate%20Division/README.md)

<!-- tabs:start -->

```python
class Solution:
    def checkContradictions(
        self, equations: List[List[str]], values: List[float]
    ) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                root = find(p[x])
                w[x] *= w[p[x]]
                p[x] = root
            return p[x]

        d = defaultdict(int)
        n = 0
        for e in equations:
            for s in e:
                if s not in d:
                    d[s] = n
                    n += 1
        p = list(range(n))
        w = [1.0] * n
        eps = 1e-5
        for (a, b), v in zip(equations, values):
            a, b = d[a], d[b]
            pa, pb = find(a), find(b)
            if pa != pb:
                p[pb] = pa
                w[pb] = v * w[a] / w[b]
            elif abs(v * w[a] - w[b]) >= eps:
                return True
        return False
```

```java
class Solution {
    private int[] p;
    private double[] w;

    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        Map<String, Integer> d = new HashMap<>();
        int n = 0;
        for (var e : equations) {
            for (var s : e) {
                if (!d.containsKey(s)) {
                    d.put(s, n++);
                }
            }
        }
        p = new int[n];
        w = new double[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            w[i] = 1.0;
        }
        final double eps = 1e-5;
        for (int i = 0; i < equations.size(); ++i) {
            int a = d.get(equations.get(i).get(0)), b = d.get(equations.get(i).get(1));
            int pa = find(a), pb = find(b);
            double v = values[i];
            if (pa != pb) {
                p[pb] = pa;
                w[pb] = v * w[a] / w[b];
            } else if (Math.abs(v * w[a] - w[b]) >= eps) {
                return true;
            }
        }
        return false;
    }

    private int find(int x) {
        if (p[x] != x) {
            int root = find(p[x]);
            w[x] *= w[p[x]];
            p[x] = root;
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    bool checkContradictions(vector<vector<string>>& equations, vector<double>& values) {
        unordered_map<string, int> d;
        int n = 0;
        for (auto& e : equations) {
            for (auto& s : e) {
                if (!d.count(s)) {
                    d[s] = n++;
                }
            }
        }
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        vector<double> w(n, 1.0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                int root = find(p[x]);
                w[x] *= w[p[x]];
                p[x] = root;
            }
            return p[x];
        };
        for (int i = 0; i < equations.size(); ++i) {
            int a = d[equations[i][0]], b = d[equations[i][1]];
            double v = values[i];
            int pa = find(a), pb = find(b);
            if (pa != pb) {
                p[pb] = pa;
                w[pb] = v * w[a] / w[b];
            } else if (fabs(v * w[a] - w[b]) >= 1e-5) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func checkContradictions(equations [][]string, values []float64) bool {
	d := make(map[string]int)
	n := 0

	for _, e := range equations {
		for _, s := range e {
			if _, ok := d[s]; !ok {
				d[s] = n
				n++
			}
		}
	}

	p := make([]int, n)
	for i := range p {
		p[i] = i
	}

	w := make([]float64, n)
	for i := range w {
		w[i] = 1.0
	}

	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			root := find(p[x])
			w[x] *= w[p[x]]
			p[x] = root
		}
		return p[x]
	}
	for i, e := range equations {
		a, b := d[e[0]], d[e[1]]
		v := values[i]

		pa, pb := find(a), find(b)
		if pa != pb {
			p[pb] = pa
			w[pb] = v * w[a] / w[b]
		} else if v*w[a]-w[b] >= 1e-5 || w[b]-v*w[a] >= 1e-5 {
			return true
		}
	}

	return false
}
```

```ts
function checkContradictions(equations: string[][], values: number[]): boolean {
    const d: { [key: string]: number } = {};
    let n = 0;

    for (const e of equations) {
        for (const s of e) {
            if (!(s in d)) {
                d[s] = n;
                n++;
            }
        }
    }

    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const w: number[] = Array.from({ length: n }, () => 1.0);

    const find = (x: number): number => {
        if (p[x] !== x) {
            const root = find(p[x]);
            w[x] *= w[p[x]];
            p[x] = root;
        }
        return p[x];
    };

    for (let i = 0; i < equations.length; i++) {
        const a = d[equations[i][0]];
        const b = d[equations[i][1]];
        const v = values[i];

        const pa = find(a);
        const pb = find(b);

        if (pa !== pb) {
            p[pb] = pa;
            w[pb] = (v * w[a]) / w[b];
        } else if (Math.abs(v * w[a] - w[b]) >= 1e-5) {
            return true;
        }
    }

    return false;
}
```

<!-- tabs:end -->

<!-- end -->
