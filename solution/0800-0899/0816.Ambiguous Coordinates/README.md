# [816. 模糊坐标](https://leetcode.cn/problems/ambiguous-coordinates)

[English Version](/solution/0800-0899/0816.Ambiguous%20Coordinates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一些二维坐标，如&nbsp;<code>&quot;(1, 3)&quot;</code>&nbsp;或&nbsp;<code>&quot;(2, 0.5)&quot;</code>，然后我们移除所有逗号，小数点和空格，得到一个字符串<code>S</code>。返回所有可能的原始字符串到一个列表中。</p>

<p>原始的坐标表示法不会存在多余的零，所以不会出现类似于&quot;00&quot;, &quot;0.0&quot;, &quot;0.00&quot;, &quot;1.0&quot;, &quot;001&quot;, &quot;00.01&quot;或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现&ldquo;.1&rdquo;形式的数字。</p>

<p>最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。</p>

<p>&nbsp;</p>

<pre>
<strong>示例 1:</strong>
<strong>输入:</strong> &quot;(123)&quot;
<strong>输出:</strong> [&quot;(1, 23)&quot;, &quot;(12, 3)&quot;, &quot;(1.2, 3)&quot;, &quot;(1, 2.3)&quot;]
</pre>

<pre>
<strong>示例 2:</strong>
<strong>输入:</strong> &quot;(00011)&quot;
<strong>输出:</strong> &nbsp;[&quot;(0.001, 1)&quot;, &quot;(0, 0.011)&quot;]
<strong>解释:</strong> 
0.0, 00, 0001 或 00.01 是不被允许的。
</pre>

<pre>
<strong>示例 3:</strong>
<strong>输入:</strong> &quot;(0123)&quot;
<strong>输出:</strong> [&quot;(0, 123)&quot;, &quot;(0, 12.3)&quot;, &quot;(0, 1.23)&quot;, &quot;(0.1, 23)&quot;, &quot;(0.1, 2.3)&quot;, &quot;(0.12, 3)&quot;]
</pre>

<pre>
<strong>示例 4:</strong>
<strong>输入:</strong> &quot;(100)&quot;
<strong>输出:</strong> [(10, 0)]
<strong>解释:</strong> 
1.0 是不被允许的。
</pre>

<p>&nbsp;</p>

<p><strong>提示: </strong></p>

<ul>
	<li><code>4 &lt;= S.length &lt;= 12</code>.</li>
	<li><code>S[0]</code> = &quot;(&quot;, <code>S[S.length - 1]</code> = &quot;)&quot;, 且字符串&nbsp;<code>S</code>&nbsp;中的其他元素都是数字。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力模拟**

枚举纵坐标的起始位置，然后分别获取横、纵坐标的所有可能的表示形式，最后将横、纵坐标的所有可能的表示形式组合起来。

我们将一个坐标值 $x$ 或 $y$ 按照小数点的位置分成左右两部分，那么两部分应该满足以下条件：

1. 左半部分不能以 0 开头，除非左半部分只有 0；
2. 右半部分不能以 0 结尾。

时间复杂度 $O(n^3)$，其中 $n$ 为字符串 $S$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
