# [2307. Check for Contradictions in Equations](https://leetcode.com/problems/check-for-contradictions-in-equations)

[中文文档](/solution/2300-2399/2307.Check%20for%20Contradictions%20in%20Equations/README.md)

## Description

<p>You are given a 2D array of strings <code>equations</code> and an array of real numbers <code>values</code>, where <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code> and <code>values[i]</code> means that <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code>.</p>

<p>Determine if there exists a contradiction in the equations. Return <code>true</code><em> if there is a contradiction, or </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>When checking if two numbers are equal, check that their <strong>absolute difference</strong> is less than <code>10<sup>-5</sup></code>.</li>
	<li>The testcases are generated such that there are no cases targeting precision, i.e. using <code>double</code> is enough to solve the problem.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> equations = [[&quot;a&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;c&quot;],[&quot;a&quot;,&quot;c&quot;]], values = [3,0.5,1.5]
<strong>Output:</strong> false
<strong>Explanation:
</strong>The given equations are: a / b = 3, b / c = 0.5, a / c = 1.5
There are no contradictions in the equations. One possible assignment to satisfy all equations is:
a = 3, b = 1 and c = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> equations = [[&quot;le&quot;,&quot;et&quot;],[&quot;le&quot;,&quot;code&quot;],[&quot;code&quot;,&quot;et&quot;]], values = [2,5,0.5]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The given equations are: le / et = 2, le / code = 5, code / et = 0.5
Based on the first two equations, we get code / et = 0.4.
Since the third equation is code / et = 0.5, we get a contradiction.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= equations.length &lt;= 100</code></li>
	<li><code>equations[i].length == 2</code></li>
	<li><code>1 &lt;= A<sub>i</sub>.length, B<sub>i</sub>.length &lt;= 5</code></li>
	<li><code>A<sub>i</sub></code>, <code>B<sub>i</sub></code> consist of lowercase English letters.</li>
	<li><code>equations.length == values.length</code></li>
	<li><code>0.0 &lt; values[i] &lt;= 10.0</code></li>
	<li><code>values[i]</code> has a maximum of 2 decimal places.</li>
</ul>

## Solutions

**Solution 1: Weighted Union-Find**

First, we convert the strings into integers starting from $0$. Then, we traverse all the equations, map the two strings in each equation to the corresponding integers $a$ and $b$. If these two integers are not in the same set, we merge them into the same set and record the weights of the two integers, which is the ratio of $a$ to $b$. If these two integers are in the same set, we check whether their weights satisfy the equation. If not, we return `true`.

The time complexity is $O(n \times \log n)$ or $O(n \times \alpha(n))$, and the space complexity is $O(n)$. Here, $n$ is the number of equations.

Similar problems:

-   [399. Evaluate Division](/solution/0300-0399/0399.Evaluate%20Division/README_EN.md)

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
