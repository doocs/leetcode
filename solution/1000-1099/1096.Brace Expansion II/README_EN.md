---
comments: true
difficulty: Hard
rating: 2348
source: Weekly Contest 142 Q4
tags:
    - Stack
    - Breadth-First Search
    - Hash Table
    - String
    - Backtracking
    - Sorting
---

<!-- problem:start -->

# [1096. Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii)

[中文文档](/solution/1000-1099/1096.Brace%20Expansion%20II/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The expression mixes union, concatenation, and nested braces. Length $\le 60$ lets us expand the innermost brace, splice, and recurse.
>
> Find the first `}` and its matching `{`. Prefix $a$, each alternative $b_i$, and suffix $c$ become $a+b_i+c$. A brace-free string is inserted into a set.
>
> The set is sorted for the answer.

<!-- thinking:end -->

Define a recursive function $\textit{dfs}(\textit{exp})$ that expands $\textit{exp}$ and stores every word in a set $s$.

Find the index $j$ of the first `}`. If there is none, $\textit{exp}$ is already a single word, so insert it into $s$.

Otherwise search left from $j$ for the matching `{` at index $i$. The prefix $\textit{exp}[:i]$ and the suffix $\textit{exp}[j + 1:]$ are $a$ and $c$. Split the brace body $\textit{exp}[i + 1: j]$ on commas into $b_1, b_2, \cdots, b_k$, and recurse on $\textit{dfs}(a + b_i + c)$ for each $b_i$. The first `}` always closes a brace group with no nested braces, so splitting that body on commas is exact.

Sort $s$ in lexicographical order to obtain the answer.

The time complexity is $O(3^{n/6})$ and the space complexity is $O(n \times 3^{n/7})$, where $n$ is the length of $\textit{expression}$. The worst-case time comes from a nested three-way union such as $\{\ldots\{a,b,c\},a,b\}$: every extra $6$ characters multiplies the recursion tree by about $3$, and the total length of the strings processed is $\Theta(3^{n/6})$. Concatenating copies of $\{a,b,c\}$ produces $\Theta(3^{n/7})$ words of length $O(n)$. The deduplicated set occupies $O(n \times 3^{n/7})$ space, and sorting it costs $O(n^2 \times 3^{n/7})$, which is within the time bound above. The recursion depth is $O(n)$, so the strings on the stack use $O(n^2)$ extra space.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def braceExpansionII(self, expression: str) -> List[str]:
        def dfs(exp):
            j = exp.find('}')
            if j == -1:
                s.add(exp)
                return
            i = exp.rfind('{', 0, j)
            a, c = exp[:i], exp[j + 1 :]
            for b in exp[i + 1 : j].split(','):
                dfs(a + b + c)

        s = set()
        dfs(expression)
        return sorted(s)
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Grammar Parsing

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 copies the still-unexpanded suffix into every alternative. A nested three-way union such as $\{\ldots\{a,b,c\},a,b\}$ therefore repeats the same suffix and takes $O(3^{n/6})$ time, even when only a constant number of words are distinct.
>
> Commas occur only inside braces and mean union. Adjacent factors outside commas mean concatenation. A left-to-right parse evaluates each subexpression once.
>
> A factor is a run of lowercase letters or a brace expression. Concatenation is the Cartesian product of the current set with the factor, and commas take the union of those products. A hash set removes duplicates, and the final set is sorted.

<!-- thinking:end -->

Let $s$ be the expression. Starting at index $i$, define two functions.

$\textit{expr}(i)$ parses a union of terms. It parses one $\textit{term}$, and while the next character is a comma it skips the comma, parses another $\textit{term}$, and unions the sets together. It stops at `}` or the end of $s$.

$\textit{term}(i)$ parses a concatenation of factors, starting from $\{\varepsilon\}$. A `{` recursively parses the inner $\textit{expr}$ and then skips the matching `}`. Otherwise the factor is the following run of lowercase letters. The current set is replaced by its Cartesian product with that factor. The function returns at a comma, a `}`, or the end of $s$.

Sort the set returned by $\textit{expr}(0)$. Each subexpression is parsed once, so the suffix is no longer copied and rescanned on every branch.

The time complexity is $O(n^2 \times 3^{n/7})$ and the space complexity is $O(n \times 3^{n/7})$, where $n$ is the length of the expression. The worst case is a concatenation of `{a,b,c}` groups, which produces $\Theta(3^{n/7})$ words of length $O(n)$. Building them costs $O(n \times 3^{n/7})$ and sorting them costs $O(n^2 \times 3^{n/7})$. A nested three-way union keeps every intermediate set at size $O(1)$, so that input takes only $O(n)$ time.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def braceExpansionII(self, expression: str) -> List[str]:
        def expr(i: int):
            res, i = term(i)
            while i < len(expression) and expression[i] == ',':
                other, i = term(i + 1)
                res |= other
            return res, i

        def term(i: int):
            res = {''}
            while i < len(expression) and expression[i] not in ',}':
                if expression[i] == '{':
                    cur, i = expr(i + 1)
                    i += 1
                else:
                    j = i + 1
                    while j < len(expression) and expression[j].islower():
                        j += 1
                    cur = {expression[i:j]}
                    i = j
                res = {a + b for a in res for b in cur}
            return res, i

        ans, _ = expr(0)
        return sorted(ans)
```

#### Java

```java
class Solution {
    private String exp;
    private int i;

    public List<String> braceExpansionII(String expression) {
        exp = expression;
        i = 0;
        List<String> ans = new ArrayList<>(expr());
        Collections.sort(ans);
        return ans;
    }

    private Set<String> expr() {
        Set<String> res = term();
        while (i < exp.length() && exp.charAt(i) == ',') {
            ++i;
            res.addAll(term());
        }
        return res;
    }

    private Set<String> term() {
        Set<String> res = new HashSet<>();
        res.add("");
        while (i < exp.length() && exp.charAt(i) != ',' && exp.charAt(i) != '}') {
            Set<String> cur = new HashSet<>();
            if (exp.charAt(i) == '{') {
                ++i;
                cur = expr();
                ++i;
            } else {
                int j = i + 1;
                while (j < exp.length() && exp.charAt(j) >= 'a' && exp.charAt(j) <= 'z') {
                    ++j;
                }
                cur.add(exp.substring(i, j));
                i = j;
            }
            Set<String> nxt = new HashSet<>();
            for (String a : res) {
                for (String b : cur) {
                    nxt.add(a + b);
                }
            }
            res = nxt;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        exp = std::move(expression);
        i = 0;
        set<string> ans = parseExpr();
        return vector<string>(ans.begin(), ans.end());
    }

private:
    string exp;
    int i = 0;

    set<string> parseExpr() {
        set<string> res = parseTerm();
        while (i < exp.size() && exp[i] == ',') {
            ++i;
            set<string> other = parseTerm();
            res.insert(other.begin(), other.end());
        }
        return res;
    }

    set<string> parseTerm() {
        set<string> res{""};
        while (i < (int) exp.size() && exp[i] != ',' && exp[i] != '}') {
            set<string> cur;
            if (exp[i] == '{') {
                ++i;
                cur = parseExpr();
                ++i;
            } else {
                int j = i + 1;
                while (j < (int) exp.size() && exp[j] >= 'a' && exp[j] <= 'z') {
                    ++j;
                }
                cur.insert(exp.substr(i, j - i));
                i = j;
            }
            set<string> nxt;
            for (const string& a : res) {
                for (const string& b : cur) {
                    nxt.insert(a + b);
                }
            }
            res.swap(nxt);
        }
        return res;
    }
};
```

#### Go

```go
func braceExpansionII(expression string) []string {
	exp := expression
	i := 0
	var parseExpr func() map[string]struct{}
	var parseTerm func() map[string]struct{}
	parseExpr = func() map[string]struct{} {
		res := parseTerm()
		for i < len(exp) && exp[i] == ',' {
			i++
			for w := range parseTerm() {
				res[w] = struct{}{}
			}
		}
		return res
	}
	parseTerm = func() map[string]struct{} {
		res := map[string]struct{}{"": {}}
		for i < len(exp) && exp[i] != ',' && exp[i] != '}' {
			cur := map[string]struct{}{}
			if exp[i] == '{' {
				i++
				cur = parseExpr()
				i++
			} else {
				j := i + 1
				for j < len(exp) && exp[j] >= 'a' && exp[j] <= 'z' {
					j++
				}
				cur[exp[i:j]] = struct{}{}
				i = j
			}
			nxt := map[string]struct{}{}
			for a := range res {
				for b := range cur {
					nxt[a+b] = struct{}{}
				}
			}
			res = nxt
		}
		return res
	}
	all := parseExpr()
	ans := make([]string, 0, len(all))
	for w := range all {
		ans = append(ans, w)
	}
	sort.Strings(ans)
	return ans
}
```

#### TypeScript

```ts
function braceExpansionII(expression: string): string[] {
    let i = 0;
    const expr = (): Set<string> => {
        const res = term();
        while (i < expression.length && expression[i] === ',') {
            ++i;
            for (const w of term()) {
                res.add(w);
            }
        }
        return res;
    };
    const term = (): Set<string> => {
        let res = new Set<string>(['']);
        while (i < expression.length && expression[i] !== ',' && expression[i] !== '}') {
            let cur: Set<string>;
            if (expression[i] === '{') {
                ++i;
                cur = expr();
                ++i;
            } else {
                let j = i + 1;
                while (j < expression.length && expression[j] >= 'a' && expression[j] <= 'z') {
                    ++j;
                }
                cur = new Set([expression.slice(i, j)]);
                i = j;
            }
            const nxt = new Set<string>();
            for (const a of res) {
                for (const b of cur) {
                    nxt.add(a + b);
                }
            }
            res = nxt;
        }
        return res;
    };
    return Array.from(expr()).sort();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
