# [816. Ambiguous Coordinates](https://leetcode.com/problems/ambiguous-coordinates)

[中文文档](/solution/0800-0899/0816.Ambiguous%20Coordinates/README.md)

## Description

<p>We had some 2-dimensional coordinates, like <code>&quot;(1, 3)&quot;</code> or <code>&quot;(2, 0.5)&quot;</code>. Then, we removed all commas, decimal points, and spaces and ended up with the string s.</p>

<ul>
	<li>For example, <code>&quot;(1, 3)&quot;</code> becomes <code>s = &quot;(13)&quot;</code> and <code>&quot;(2, 0.5)&quot;</code> becomes <code>s = &quot;(205)&quot;</code>.</li>
</ul>

<p>Return <em>a list of strings representing all possibilities for what our original coordinates could have been</em>.</p>

<p>Our original representation never had extraneous zeroes, so we never started with numbers like <code>&quot;00&quot;</code>, <code>&quot;0.0&quot;</code>, <code>&quot;0.00&quot;</code>, <code>&quot;1.0&quot;</code>, <code>&quot;001&quot;</code>, <code>&quot;00.01&quot;</code>, or any other number that can be represented with fewer digits. Also, a decimal point within a number never occurs without at least one digit occurring before it, so we never started with numbers like <code>&quot;.1&quot;</code>.</p>

<p>The final answer list can be returned in any order. All coordinates in the final answer have exactly one space between them (occurring after the comma.)</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(123)&quot;
<strong>Output:</strong> [&quot;(1, 2.3)&quot;,&quot;(1, 23)&quot;,&quot;(1.2, 3)&quot;,&quot;(12, 3)&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(0123)&quot;
<strong>Output:</strong> [&quot;(0, 1.23)&quot;,&quot;(0, 12.3)&quot;,&quot;(0, 123)&quot;,&quot;(0.1, 2.3)&quot;,&quot;(0.1, 23)&quot;,&quot;(0.12, 3)&quot;]
<strong>Explanation:</strong> 0.0, 00, 0001 or 00.01 are not allowed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(00011)&quot;
<strong>Output:</strong> [&quot;(0, 0.011)&quot;,&quot;(0.001, 1)&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= s.length &lt;= 12</code></li>
	<li><code>s[0] == &#39;(&#39;</code> and <code>s[s.length - 1] == &#39;)&#39;</code>.</li>
	<li>The rest of <code>s</code> are digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        def f(i, j):
            res = []
            for k in range(1, j - i + 1):
                l, r = s[i : i + k], s[i + k : j]
                ok = (l == '0' or not l.startswith('0')) and not r.endswith('0')
                if ok:
                    res.append(l + ('.' if k < j - i else '') + r)
            return res

        n = len(s)
        return [
            f'({x}, {y})' for i in range(2, n - 1) for x in f(1, i) for y in f(i, n - 1)
        ]
```

### **Java**

```java
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n - 1; ++i) {
            for (String x : f(s, 1, i)) {
                for (String y : f(s, i, n - 1)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private List<String> f(String s, int i, int j) {
        List<String> res = new ArrayList<>();
        for (int k = 1; k <= j - i; ++k) {
            String l = s.substring(i, i + k);
            String r = s.substring(i + k, j);
            boolean ok = ("0".equals(l) || !l.startsWith("0")) && !r.endsWith("0");
            if (ok) {
                res.add(l + (k < j - i ? "." : "") + r);
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> ambiguousCoordinates(string s) {
        int n = s.size();
        vector<string> ans;
        auto f = [&](int i, int j) {
            vector<string> res;
            for (int k = 1; k <= j - i; ++k) {
                string l = s.substr(i, k);
                string r = s.substr(i + k, j - i - k);
                bool ok = (l == "0" || l[0] != '0') && r.back() != '0';
                if (ok) {
                    res.push_back(l + (k < j - i ? "." : "") + r);
                }
            }
            return res;
        };
        for (int i = 2; i < n - 1; ++i) {
            for (auto& x : f(1, i)) {
                for (auto& y : f(i, n - 1)) {
                    ans.emplace_back("(" + x + ", " + y + ")");
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func ambiguousCoordinates(s string) []string {
	f := func(i, j int) []string {
		res := []string{}
		for k := 1; k <= j-i; k++ {
			l, r := s[i:i+k], s[i+k:j]
			ok := (l == "0" || l[0] != '0') && (r == "" || r[len(r)-1] != '0')
			if ok {
				t := ""
				if k < j-i {
					t = "."
				}
				res = append(res, l+t+r)
			}
		}
		return res
	}

	n := len(s)
	ans := []string{}
	for i := 2; i < n-1; i++ {
		for _, x := range f(1, i) {
			for _, y := range f(i, n-1) {
				ans = append(ans, "("+x+", "+y+")")
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function ambiguousCoordinates(s: string): string[] {
    s = s.slice(1, s.length - 1);
    const n = s.length;
    const dfs = (s: string) => {
        const res: string[] = [];
        for (let i = 1; i < s.length; i++) {
            const t = `${s.slice(0, i)}.${s.slice(i)}`;
            if (`${Number(t)}` === t) {
                res.push(t);
            }
        }
        if (`${Number(s)}` === s) {
            res.push(s);
        }
        return res;
    };
    const ans: string[] = [];
    for (let i = 1; i < n; i++) {
        for (const left of dfs(s.slice(0, i))) {
            for (const right of dfs(s.slice(i))) {
                ans.push(`(${left}, ${right})`);
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
