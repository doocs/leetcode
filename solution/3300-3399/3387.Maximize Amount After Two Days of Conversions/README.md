---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3387.Maximize%20Amount%20After%20Two%20Days%20of%20Conversions/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 数组
    - 字符串
---

<!-- problem:start -->

# [3387. 两天自由外汇交易后的最大货币数](https://leetcode.cn/problems/maximize-amount-after-two-days-of-conversions)

[English Version](/solution/3300-3399/3387.Maximize%20Amount%20After%20Two%20Days%20of%20Conversions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>initialCurrency</code>，表示初始货币类型，并且你一开始拥有 <code>1.0</code> 单位的 <code>initialCurrency</code>。</p>

<p>另给你四个数组，分别表示货币对（字符串）和汇率（实数）：</p>

<ul>
	<li><code>pairs1[i] = [startCurrency<sub>i</sub>, targetCurrency<sub>i</sub>]</code> 表示在&nbsp;<strong>第 1 天</strong>，可以按照汇率 <code>rates1[i]</code> 将 <code>startCurrency<sub>i</sub></code> 转换为 <code>targetCurrency<sub>i</sub></code>。</li>
	<li><code>pairs2[i] = [startCurrency<sub>i</sub>, targetCurrency<sub>i</sub>]</code> 表示在&nbsp;<strong>第 2 天</strong>，可以按照汇率 <code>rates2[i]</code> 将 <code>startCurrency<sub>i</sub></code> 转换为 <code>targetCurrency<sub>i</sub></code>。</li>
	<li>此外，每种 <code>targetCurrency</code> 都可以以汇率 <code>1 / rate</code> 转换回对应的 <code>startCurrency</code>。</li>
</ul>

<p>你可以在&nbsp;<strong>第 1 天&nbsp;</strong>使用 <code>rates1</code> 进行任意次数的兑换（包括 0 次），然后在&nbsp;<strong>第 2 天&nbsp;</strong>使用 <code>rates2</code> 再进行任意次数的兑换（包括 0 次）。</p>

<p>返回在两天兑换后，最大可能拥有的 <code>initialCurrency</code> 的数量。</p>

<p><strong>注意：</strong>汇率是有效的，并且第 1 天和第 2 天的汇率之间相互独立，不会产生矛盾。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">initialCurrency = "EUR", pairs1 = [["EUR","USD"],["USD","JPY"]], rates1 = [2.0,3.0], pairs2 = [["JPY","USD"],["USD","CHF"],["CHF","EUR"]], rates2 = [4.0,5.0,6.0]</span></p>

<p><strong>输出：</strong> <span class="example-io">720.00000</span></p>

<p><strong>解释：</strong></p>

<p>根据题目要求，需要最大化最终的 <strong>EUR</strong> 数量，从 1.0 <strong>EUR</strong> 开始：</p>

<ul>
	<li><strong>第 1 天：</strong>

    <ul>
    	<li>将 <strong>EUR</strong> 换成 <strong>USD</strong>，得到 2.0&nbsp;<strong>USD</strong>。</li>
    	<li>将 <strong>USD</strong> 换成 <strong>JPY</strong>，得到 6.0 <strong>JPY</strong>。</li>
    </ul>
    </li>
    <li><strong>第 2 天：</strong>
    <ul>
    	<li>将 <strong>JPY</strong> 换成 <strong>USD</strong>，得到 24.0 <strong>USD</strong>。</li>
    	<li>将 <strong>USD</strong> 换成 <strong>CHF</strong>，得到 120.0 <strong>CHF</strong>。</li>
    	<li>最后将 <strong>CHF</strong> 换回 <strong>EUR</strong>，得到 720.0 <strong>EUR</strong>。</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">initialCurrency = "NGN", pairs1 = [["NGN","EUR"]], rates1 = [9.0], pairs2 = [["NGN","EUR"]], rates2 = [6.0]</span></p>

<p><strong>输出：</strong> <span class="example-io">1.50000</span></p>

<p><strong>解释：</strong></p>

<p>在第 1 天将 <strong>NGN</strong> 换成 <strong>EUR</strong>，并在第 2 天用反向汇率将 <strong>EUR</strong> 换回 <strong>NGN</strong>，可以最大化最终的 <strong>NGN</strong> 数量。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">initialCurrency = "USD", pairs1 = [["USD","EUR"]], rates1 = [1.0], pairs2 = [["EUR","JPY"]], rates2 = [10.0]</span></p>

<p><strong>输出：</strong> <span class="example-io">1.00000</span></p>

<p><strong>解释：</strong></p>

<p>在这个例子中，不需要在任何一天进行任何兑换。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= initialCurrency.length &lt;= 3</code></li>
	<li><code>initialCurrency</code> 仅由大写英文字母组成。</li>
	<li><code>1 &lt;= n == pairs1.length &lt;= 10</code></li>
	<li><code>1 &lt;= m == pairs2.length &lt;= 10</code></li>
	<li><code>pairs1[i] == [startCurrency<sub>i</sub>, targetCurrency<sub>i</sub>]</code></li>
	<li><code>pairs2[i] == [startCurrency<sub>i</sub>, targetCurrency<sub>i</sub>]</code></li>
	<li><code>1 &lt;= startCurrency<sub>i</sub>.length, targetCurrency<sub>i</sub>.length &lt;= 3</code></li>
	<li><code>startCurrency<sub>i</sub></code> 和 <code>targetCurrency<sub>i</sub></code> 仅由大写英文字母组成。</li>
	<li><code>rates1.length == n</code></li>
	<li><code>rates2.length == m</code></li>
	<li><code>1.0 &lt;= rates1[i], rates2[i] &lt;= 10.0</code></li>
	<li>输入保证两个转换图在各自的天数中没有矛盾或循环。</li>
	<li>输入保证输出&nbsp;<strong>最大</strong>&nbsp;为&nbsp;<code>5 * 10<sup>10</sup></code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAmount(
        self,
        initialCurrency: str,
        pairs1: List[List[str]],
        rates1: List[float],
        pairs2: List[List[str]],
        rates2: List[float],
    ) -> float:
        d1 = self.build(pairs1, rates1, initialCurrency)
        d2 = self.build(pairs2, rates2, initialCurrency)
        return max(d1.get(a, 0) / r2 for a, r2 in d2.items())

    def build(
        self, pairs: List[List[str]], rates: List[float], init: str
    ) -> Dict[str, float]:
        def dfs(a: str, v: float):
            d[a] = v
            for b, r in g[a]:
                if b not in d:
                    dfs(b, v * r)

        g = defaultdict(list)
        for (a, b), r in zip(pairs, rates):
            g[a].append((b, r))
            g[b].append((a, 1 / r))
        d = {}
        dfs(init, 1)
        return d
```

#### Java

```java
class Solution {
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1,
        List<List<String>> pairs2, double[] rates2) {
        Map<String, Double> d1 = build(pairs1, rates1, initialCurrency);
        Map<String, Double> d2 = build(pairs2, rates2, initialCurrency);
        double ans = 0;
        for (Map.Entry<String, Double> entry : d2.entrySet()) {
            String currency = entry.getKey();
            double rate = entry.getValue();
            if (d1.containsKey(currency)) {
                ans = Math.max(ans, d1.get(currency) / rate);
            }
        }
        return ans;
    }

    private Map<String, Double> build(List<List<String>> pairs, double[] rates, String init) {
        Map<String, List<Pair<String, Double>>> g = new HashMap<>();
        Map<String, Double> d = new HashMap<>();
        for (int i = 0; i < pairs.size(); ++i) {
            String a = pairs.get(i).get(0);
            String b = pairs.get(i).get(1);
            double r = rates[i];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(new Pair<>(b, r));
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(new Pair<>(a, 1 / r));
        }
        dfs(g, d, init, 1.0);
        return d;
    }

    private void dfs(
        Map<String, List<Pair<String, Double>>> g, Map<String, Double> d, String a, double v) {
        if (d.containsKey(a)) {
            return;
        }

        d.put(a, v);
        for (Pair<String, Double> pair : g.getOrDefault(a, List.of())) {
            String b = pair.getKey();
            double r = pair.getValue();
            if (!d.containsKey(b)) {
                dfs(g, d, b, v * r);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    double maxAmount(string initialCurrency, vector<vector<string>>& pairs1, vector<double>& rates1, vector<vector<string>>& pairs2, vector<double>& rates2) {
        unordered_map<string, double> d1 = build(pairs1, rates1, initialCurrency);
        unordered_map<string, double> d2 = build(pairs2, rates2, initialCurrency);
        double ans = 0;
        for (const auto& [currency, rate] : d2) {
            if (d1.find(currency) != d1.end()) {
                ans = max(ans, d1[currency] / rate);
            }
        }
        return ans;
    }

private:
    unordered_map<string, double> build(vector<vector<string>>& pairs, vector<double>& rates, const string& init) {
        unordered_map<string, vector<pair<string, double>>> g;
        unordered_map<string, double> d;
        for (int i = 0; i < pairs.size(); ++i) {
            const string& a = pairs[i][0];
            const string& b = pairs[i][1];
            double r = rates[i];
            g[a].push_back({b, r});
            g[b].push_back({a, 1 / r});
        }

        auto dfs = [&](this auto&& dfs, const string& a, double v) -> void {
            if (d.find(a) != d.end()) {
                return;
            }

            d[a] = v;
            for (const auto& [b, r] : g[a]) {
                if (d.find(b) == d.end()) {
                    dfs(b, v * r);
                }
            }
        };
        dfs(init, 1.0);
        return d;
    }
};
```

#### Go

```go
type Pair struct {
	Key   string
	Value float64
}

func maxAmount(initialCurrency string, pairs1 [][]string, rates1 []float64, pairs2 [][]string, rates2 []float64) (ans float64) {
	d1 := build(pairs1, rates1, initialCurrency)
	d2 := build(pairs2, rates2, initialCurrency)
	for currency, rate := range d2 {
		if val, found := d1[currency]; found {
			ans = max(ans, val/rate)
		}
	}
	return
}

func build(pairs [][]string, rates []float64, init string) map[string]float64 {
	g := make(map[string][]Pair)
	d := make(map[string]float64)

	for i := 0; i < len(pairs); i++ {
		a := pairs[i][0]
		b := pairs[i][1]
		r := rates[i]
		g[a] = append(g[a], Pair{Key: b, Value: r})
		g[b] = append(g[b], Pair{Key: a, Value: 1.0 / r})
	}

	dfs(g, d, init, 1.0)
	return d
}

func dfs(g map[string][]Pair, d map[string]float64, a string, v float64) {
	if _, found := d[a]; found {
		return
	}

	d[a] = v
	for _, pair := range g[a] {
		b := pair.Key
		r := pair.Value
		if _, found := d[b]; !found {
			dfs(g, d, b, v*r)
		}
	}
}
```

#### TypeScript

```ts
class Pair {
    constructor(
        public key: string,
        public value: number,
    ) {}
}

function maxAmount(
    initialCurrency: string,
    pairs1: string[][],
    rates1: number[],
    pairs2: string[][],
    rates2: number[],
): number {
    const d1 = build(pairs1, rates1, initialCurrency);
    const d2 = build(pairs2, rates2, initialCurrency);
    let ans = 0;
    for (const [currency, rate] of Object.entries(d2)) {
        if (currency in d1) {
            ans = Math.max(ans, d1[currency] / rate);
        }
    }
    return ans;
}

function build(pairs: string[][], rates: number[], init: string): { [key: string]: number } {
    const g: { [key: string]: Pair[] } = {};
    const d: { [key: string]: number } = {};
    for (let i = 0; i < pairs.length; ++i) {
        const a = pairs[i][0];
        const b = pairs[i][1];
        const r = rates[i];
        if (!g[a]) g[a] = [];
        if (!g[b]) g[b] = [];
        g[a].push(new Pair(b, r));
        g[b].push(new Pair(a, 1 / r));
    }
    dfs(g, d, init, 1.0);
    return d;
}

function dfs(
    g: { [key: string]: Pair[] },
    d: { [key: string]: number },
    a: string,
    v: number,
): void {
    if (a in d) {
        return;
    }

    d[a] = v;
    for (const pair of g[a] || []) {
        const b = pair.key;
        const r = pair.value;
        if (!(b in d)) {
            dfs(g, d, b, v * r);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
