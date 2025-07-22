---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3481.Apply%20Substitutions/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [3481. Apply Substitutions 🔒](https://leetcode.com/problems/apply-substitutions)

[中文文档](/solution/3400-3499/3481.Apply%20Substitutions/README.md)

## Description

<!-- description:start -->

<p data-end="384" data-start="34">You are given a <code>replacements</code> mapping and a <code>text</code> string that may contain <strong>placeholders</strong> formatted as <code data-end="139" data-start="132">%var%</code>, where each <code>var</code> corresponds to a key in the <code>replacements</code> mapping. Each replacement value may itself contain <strong>one or more</strong> such <strong>placeholders</strong>. Each <strong>placeholder</strong> is replaced by the value associated with its corresponding replacement key.</p>

<p data-end="353" data-start="34">Return the fully substituted <code>text</code> string which <strong>does not</strong> contain any <strong>placeholders</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">replacements = [[&quot;A&quot;,&quot;abc&quot;],[&quot;B&quot;,&quot;def&quot;]], text = &quot;%A%_%B%&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abc_def&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="238" data-start="71">
	<li data-end="138" data-start="71">The mapping associates <code data-end="101" data-start="96">&quot;A&quot;</code> with <code data-end="114" data-start="107">&quot;abc&quot;</code> and <code data-end="124" data-start="119">&quot;B&quot;</code> with <code data-end="137" data-start="130">&quot;def&quot;</code>.</li>
	<li data-end="203" data-start="139">Replace <code data-end="154" data-start="149">%A%</code> with <code data-end="167" data-start="160">&quot;abc&quot;</code> and <code data-end="177" data-start="172">%B%</code> with <code data-end="190" data-start="183">&quot;def&quot;</code> in the text.</li>
	<li data-end="238" data-start="204">The final text becomes <code data-end="237" data-start="226">&quot;abc_def&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">replacements = [[&quot;A&quot;,&quot;bce&quot;],[&quot;B&quot;,&quot;ace&quot;],[&quot;C&quot;,&quot;abc%B%&quot;]], text = &quot;%A%_%B%_%C%&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bce_ace_abcace&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="541" data-is-last-node="" data-is-only-node="" data-start="255">
	<li data-end="346" data-start="255">The mapping associates <code data-end="285" data-start="280">&quot;A&quot;</code> with <code data-end="298" data-start="291">&quot;bce&quot;</code>, <code data-end="305" data-start="300">&quot;B&quot;</code> with <code data-end="318" data-start="311">&quot;ace&quot;</code>, and <code data-end="329" data-start="324">&quot;C&quot;</code> with <code data-end="345" data-start="335">&quot;abc%B%&quot;</code>.</li>
	<li data-end="411" data-start="347">Replace <code data-end="362" data-start="357">%A%</code> with <code data-end="375" data-start="368">&quot;bce&quot;</code> and <code data-end="385" data-start="380">%B%</code> with <code data-end="398" data-start="391">&quot;ace&quot;</code> in the text.</li>
	<li data-end="496" data-start="412">Then, for <code data-end="429" data-start="424">%C%</code>, substitute <code data-end="447" data-start="442">%B%</code> in <code data-end="461" data-start="451">&quot;abc%B%&quot;</code> with <code data-end="474" data-start="467">&quot;ace&quot;</code> to obtain <code data-end="495" data-start="485">&quot;abcace&quot;</code>.</li>
	<li data-end="541" data-is-last-node="" data-start="497">The final text becomes <code data-end="540" data-start="522">&quot;bce_ace_abcace&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="1432" data-start="1398"><code>1 &lt;= replacements.length &lt;= 10</code></li>
	<li data-end="1683" data-start="1433">Each element of <code data-end="1465" data-start="1451">replacements</code> is a two-element list <code data-end="1502" data-start="1488">[key, value]</code>, where:
	<ul data-end="1683" data-start="1513">
		<li data-end="1558" data-start="1513"><code data-end="1520" data-start="1515">key</code> is a single uppercase English letter.</li>
		<li data-end="1683" data-start="1561"><code data-end="1570" data-start="1563">value</code> is a non-empty string of at most 8 characters that may contain zero or more placeholders formatted as <code data-end="1682" data-start="1673">%&lt;key&gt;%</code>.</li>
	</ul>
	</li>
	<li data-end="726" data-start="688">All replacement keys are unique.</li>
	<li data-end="1875" data-start="1723">The <code>text</code> string is formed by concatenating all key placeholders (formatted as <code data-end="1808" data-start="1799">%&lt;key&gt;%</code>) randomly from the replacements mapping, separated by underscores.</li>
	<li data-end="1942" data-start="1876"><code>text.length == 4 * replacements.length - 1</code></li>
	<li data-end="2052" data-start="1943">Every placeholder in the <code>text</code> or in any replacement value corresponds to a key in the <code>replacements</code> mapping.</li>
	<li data-end="2265" data-start="2205">There are no cyclic dependencies between replacement keys.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Recursion

We use a hash table $\textit{d}$ to store the substitution mapping, and then define a function $\textit{dfs}$ to recursively replace the placeholders in the string.

The execution logic of the function $\textit{dfs}$ is as follows:

1. Find the starting position $i$ of the first placeholder in the string $\textit{s}$. If not found, return $\textit{s}$;
2. Find the ending position $j$ of the first placeholder in the string $\textit{s}$. If not found, return $\textit{s}$;
3. Extract the key of the placeholder, and then recursively replace the value of the placeholder $d[key]$;
4. Return the replaced string.

In the main function, we call the $\textit{dfs}$ function, pass in the text string $\textit{text}$, and return the result.

The time complexity is $O(m + n \times L)$, and the space complexity is $O(m + n \times L)$. Where $m$ is the length of the substitution mapping, and $n$ and $L$ are the length of the text string and the average length of the placeholders, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def applySubstitutions(self, replacements: List[List[str]], text: str) -> str:
        def dfs(s: str) -> str:
            i = s.find("%")
            if i == -1:
                return s
            j = s.find("%", i + 1)
            if j == -1:
                return s
            key = s[i + 1 : j]
            replacement = dfs(d[key])
            return s[:i] + replacement + dfs(s[j + 1 :])

        d = {s: t for s, t in replacements}
        return dfs(text)
```

#### Java

```java
class Solution {
    private final Map<String, String> d = new HashMap<>();

    public String applySubstitutions(List<List<String>> replacements, String text) {
        for (List<String> e : replacements) {
            d.put(e.get(0), e.get(1));
        }
        return dfs(text);
    }

    private String dfs(String s) {
        int i = s.indexOf("%");
        if (i == -1) {
            return s;
        }
        int j = s.indexOf("%", i + 1);
        if (j == -1) {
            return s;
        }
        String key = s.substring(i + 1, j);
        String replacement = dfs(d.getOrDefault(key, ""));
        return s.substring(0, i) + replacement + dfs(s.substring(j + 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    string applySubstitutions(vector<vector<string>>& replacements, string text) {
        unordered_map<string, string> d;
        for (const auto& e : replacements) {
            d[e[0]] = e[1];
        }
        auto dfs = [&](this auto&& dfs, const string& s) -> string {
            size_t i = s.find('%');
            if (i == string::npos) {
                return s;
            }
            size_t j = s.find('%', i + 1);
            if (j == string::npos) {
                return s;
            }
            string key = s.substr(i + 1, j - i - 1);
            string replacement = dfs(d[key]);
            return s.substr(0, i) + replacement + dfs(s.substr(j + 1));
        };
        return dfs(text);
    }
};
```

#### Go

```go
func applySubstitutions(replacements [][]string, text string) string {
	d := make(map[string]string)
	for _, e := range replacements {
		d[e[0]] = e[1]
	}
	var dfs func(string) string
	dfs = func(s string) string {
		i := strings.Index(s, "%")
		if i == -1 {
			return s
		}
		j := strings.Index(s[i+1:], "%")
		if j == -1 {
			return s
		}
		j += i + 1
		key := s[i+1 : j]
		replacement := dfs(d[key])
		return s[:i] + replacement + dfs(s[j+1:])
	}

	return dfs(text)
}
```

#### TypeScript

```ts
function applySubstitutions(replacements: string[][], text: string): string {
    const d: Record<string, string> = {};
    for (const [key, value] of replacements) {
        d[key] = value;
    }

    const dfs = (s: string): string => {
        const i = s.indexOf('%');
        if (i === -1) {
            return s;
        }
        const j = s.indexOf('%', i + 1);
        if (j === -1) {
            return s;
        }
        const key = s.slice(i + 1, j);
        const replacement = dfs(d[key] ?? '');
        return s.slice(0, i) + replacement + dfs(s.slice(j + 1));
    };

    return dfs(text);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
