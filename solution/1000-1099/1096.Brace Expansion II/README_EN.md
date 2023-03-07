# [1096. Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii)

[中文文档](/solution/1000-1099/1096.Brace%20Expansion%20II/README.md)

## Description

<p>Under the grammar given below, strings can represent a set of lowercase words. Let&nbsp;<code>R(expr)</code>&nbsp;denote the set of words the expression represents.</p>

<p>The grammar can best be understood through simple examples:</p>

<ul>
	<li>Single letters represent a singleton set containing that word.
	<ul>
		<li><code>R(&quot;a&quot;) = {&quot;a&quot;}</code></li>
		<li><code>R(&quot;w&quot;) = {&quot;w&quot;}</code></li>
	</ul>
	</li>
	<li>When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
	<ul>
		<li><code>R(&quot;{a,b,c}&quot;) = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;}</code></li>
		<li><code>R(&quot;{{a,b},{b,c}}&quot;) = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;}</code> (notice the final set only contains each word at most once)</li>
	</ul>
	</li>
	<li>When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
	<ul>
		<li><code>R(&quot;{a,b}{c,d}&quot;) = {&quot;ac&quot;,&quot;ad&quot;,&quot;bc&quot;,&quot;bd&quot;}</code></li>
		<li><code>R(&quot;a{b,c}{d,e}f{g,h}&quot;) = {&quot;abdfg&quot;, &quot;abdfh&quot;, &quot;abefg&quot;, &quot;abefh&quot;, &quot;acdfg&quot;, &quot;acdfh&quot;, &quot;acefg&quot;, &quot;acefh&quot;}</code></li>
	</ul>
	</li>
</ul>

<p>Formally, the three rules for our grammar:</p>

<ul>
	<li>For every lowercase letter <code>x</code>, we have <code>R(x) = {x}</code>.</li>
	<li>For expressions <code>e<sub>1</sub>, e<sub>2</sub>, ... , e<sub>k</sub></code> with <code>k &gt;= 2</code>, we have <code>R({e<sub>1</sub>, e<sub>2</sub>, ...}) = R(e<sub>1</sub>) &cup; R(e<sub>2</sub>) &cup; ...</code></li>
	<li>For expressions <code>e<sub>1</sub></code> and <code>e<sub>2</sub></code>, we have <code>R(e<sub>1</sub> + e<sub>2</sub>) = {a + b for (a, b) in R(e<sub>1</sub>) &times; R(e<sub>2</sub>)}</code>, where <code>+</code> denotes concatenation, and <code>&times;</code> denotes the cartesian product.</li>
</ul>

<p>Given an expression representing a set of words under the given grammar, return <em>the sorted list of words that the expression represents</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;{a,b}{c,{d,e}}&quot;
<strong>Output:</strong> [&quot;ac&quot;,&quot;ad&quot;,&quot;ae&quot;,&quot;bc&quot;,&quot;bd&quot;,&quot;be&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;{{a,z},a{b,c},{ab,z}}&quot;
<strong>Output:</strong> [&quot;a&quot;,&quot;ab&quot;,&quot;ac&quot;,&quot;z&quot;]
<strong>Explanation:</strong> Each distinct word is written only once in the final answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 60</code></li>
	<li><code>expression[i]</code> consists of <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;,&#39;</code>or lowercase English letters.</li>
	<li>The given&nbsp;<code>expression</code>&nbsp;represents a set of words based on the grammar given in the description.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def braceExpansionII(self, expression: str) -> List[str]:
        def dfs(exp):
            j = exp.find('}')
            if j == -1:
                s.add(exp)
                return
            i = exp.rfind('{', 0, j - 1)
            a, c = exp[:i], exp[j + 1:]
            for b in exp[i + 1: j].split(','):
                dfs(a + b + c)

        s = set()
        dfs(expression)
        return sorted(s)
```

### **Java**

```java
class Solution {
    private TreeSet<String> s = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(s);
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');
        if (j == -1) {
            s.add(exp);
            return;
        }
        int i = exp.lastIndexOf('{', j);
        String a = exp.substring(0, i);
        String c = exp.substring(j + 1);
        for (String b : exp.substring(i + 1, j).split(",")) {
            dfs(a + b + c);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        dfs(expression);
        return vector<string>(s.begin(), s.end());
    }

private:
    set<string> s;

    void dfs(string exp) {
        int j = exp.find_first_of('}');
        if (j == string::npos) {
            s.insert(exp);
            return;
        }
        int i = exp.rfind('{', j);
        string a = exp.substr(0, i);
        string c = exp.substr(j + 1);
        stringstream ss(exp.substr(i + 1, j - i - 1));
        string b;
        while (getline(ss, b, ',')) {
            dfs(a + b + c);
        }
    }
};
```

### **Go**

```go
func braceExpansionII(expression string) []string {
	s := map[string]struct{}{}
	var dfs func(string)
	dfs = func(exp string) {
		j := strings.Index(exp, "}")
		if j == -1 {
			s[exp] = struct{}{}
			return
		}
		i := strings.LastIndex(exp[:j], "{")
		a, c := exp[:i], exp[j+1:]
		for _, b := range strings.Split(exp[i+1:j], ",") {
			dfs(a + b + c)
		}
	}
	dfs(expression)
	ans := make([]string, 0, len(s))
	for k := range s {
		ans = append(ans, k)
	}
	sort.Strings(ans)
	return ans
}
```

### **TypeScript**

```ts
function braceExpansionII(expression: string): string[] {
    const dfs = (exp: string) => {
        const j = exp.indexOf('}');
        if (j === -1) {
            s.add(exp);
            return;
        }
        const i = exp.lastIndexOf('{', j);
        const a = exp.substring(0, i);
        const c = exp.substring(j + 1);
        for (const b of exp.substring(i + 1, j).split(',')) {
            dfs(a + b + c);
        }
    };
    const s: Set<string> = new Set();
    dfs(expression);
    return Array.from(s).sort();
}
```

### **...**

```

```

<!-- tabs:end -->
