---
comments: true
difficulty: 困难
rating: 2348
source: 第 142 场周赛 Q4
tags:
    - 栈
    - 广度优先搜索
    - 哈希表
    - 字符串
    - 回溯
    - 排序
---

<!-- problem:start -->

# [1096. 花括号展开 II](https://leetcode.cn/problems/brace-expansion-ii)

[English Version](/solution/1000-1099/1096.Brace%20Expansion%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。</p>

<p>花括号展开的表达式可以看作一个由 <strong>花括号</strong>、<strong>逗号</strong> 和 <strong>小写英文字母</strong> 组成的字符串，定义下面几条语法规则：</p>

<ul>
	<li>如果只给出单一的元素&nbsp;<code>x</code>，那么表达式表示的字符串就只有&nbsp;<code>"x"</code>。<code>R(x) = {x}</code>

    <ul>
    	<li>例如，表达式 <code>"a"</code> 表示字符串 <code>"a"</code>。</li>
    	<li>而表达式 <code>"w"</code> 就表示字符串 <code>"w"</code>。</li>
    </ul>
    </li>
    <li>当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。<code>R({e_1,e_2,...}) = R(e_1)&nbsp;∪ R(e_2)&nbsp;∪ ...</code>
    <ul>
    	<li>例如，表达式 <code>"{a,b,c}"</code> 表示字符串&nbsp;<code>"a","b","c"</code>。</li>
    	<li>而表达式 <code>"{{a,b},{b,c}}"</code> 也可以表示字符串&nbsp;<code>"a","b","c"</code>。</li>
    </ul>
    </li>
    <li>要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。<code>R(e_1 + e_2) = {a + b for (a, b) in&nbsp;R(e_1)&nbsp;× R(e_2)}</code>
    <ul>
    	<li>例如，表达式 <code>"{a,b}{c,d}"</code> 表示字符串&nbsp;<code>"ac","ad","bc","bd"</code>。</li>
    </ul>
    </li>
    <li>表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
    <ul>
    	<li>例如，表达式 <code>"a{b,c,d}"</code> 表示字符串&nbsp;<code>"ab","ac","ad"​​​​​​</code>。</li>
    	<li>例如，表达式 <code>"a{b,c}{d,e}f{g,h}"</code> 可以表示字符串&nbsp;<code>"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"</code>。</li>
    </ul>
    </li>

</ul>

<p>给出表示基于给定语法规则的表达式&nbsp;<code>expression</code>，返回它所表示的所有字符串组成的有序列表。</p>

<p>假如你希望以「集合」的概念了解此题，也可以通过点击 “<strong>显示英文描述</strong>” 获取详情。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "{a,b}{c,{d,e}}"
<strong>输出：</strong>["ac","ad","ae","bc","bd","be"]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "{{a,z},a{b,c},{ab,z}}"
<strong>输出：</strong>["a","ab","ac","z"]
<strong>解释：</strong>输出中 <strong>不应 </strong>出现重复的组合结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 60</code></li>
	<li><code>expression[i]</code> 由 <code>'{'</code>，<code>'}'</code>，<code>','</code>&nbsp;或小写英文字母组成</li>
	<li>给出的表达式&nbsp;<code>expression</code>&nbsp;用以表示一组基于题目描述中语法构造的字符串</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

<!-- thinking:start -->

> **思考**
>
> 表达式含并、连接与多层花括号，求展开后的去重有序集合。表达式长 $\le 60$，可递归把最内层花括号展开后拼回再处理。
>
> 找到第一个 `}` 及其匹配的 `{`，前缀 $a$、选项 $b_i$、后缀 $c$ 拼成 $a+b_i+c$ 继续递归；不再含括号则加入集合。
>
> 集合去重后排序输出。

<!-- thinking:end -->

我们设计一个递归函数 $dfs(exp)$，用于处理表达式 $exp$，并将结果存入集合 $s$ 中。

对于表达式 $exp$，我们首先找到第一个右花括号的位置 $j$，如果找不到，说明 $exp$ 中没有右花括号，即 $exp$ 为单一元素，直接将 $exp$ 加入集合 $s$ 中即可。

否则，我们从位置 $j$ 开始往左找到第一个左花括号的位置 $i$，此时 $exp[:i]$ 和 $exp[j + 1:]$ 分别为 $exp$ 的前缀和后缀，记为 $a$ 和 $c$。而 $exp[i + 1: j]$ 为 $exp$ 中花括号内的部分，即 $exp$ 中的子表达式，我们将其按照逗号分割成多个字符串 $b_1, b_2, \cdots, b_k$，然后对每个 $b_i$，我们将 $a + b_i + c$ 拼接成新的表达式，递归调用 $dfs$ 函数处理新的表达式，即 $dfs(a + b_i + c)$。

最后，我们将集合 $s$ 中的元素按照字典序排序，即可得到答案。

时间复杂度 $O(3^{n/6})$，空间复杂度 $O(n \times 3^{n/7})$，其中 $n$ 为表达式 $expression$ 的长度。最坏时间出现在 $\{\ldots\{a,b,c\},a,b\}$ 这种逐层套上三路并集的表达式上：每增加 $6$ 个字符，递归树就大约变成原来的 $3$ 倍，各层字符串长度之和为 $\Theta(3^{n/6})$。连续拼接的 $\{a,b,c\}$ 会生成 $\Theta(3^{n/7})$ 个长度为 $O(n)$ 的字符串，去重后的结果集占 $O(n \times 3^{n/7})$ 空间，排序它们的开销是 $O(n^2 \times 3^{n/7})$，仍低于上面的时间上界。递归栈深度为 $O(n)$，栈上字符串共 $O(n^2)$。

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

### 方法二：语法分析

<!-- thinking:start -->

> **思考**
>
> 方法一每选中一个选项，就把还没展开的后缀再复制一份递归处理。像 $\{\ldots\{a,b,c\},a,b\}$ 这样层层嵌套时，同一段后缀被重复扫描，时间达到 $O(3^{n/6})$，不同单词却可能只有常数个。
>
> 逗号只出现在花括号里，表示并；括号外相邻的因子表示连接。按这个文法从左到右解析，每个子表达式只求一次集合。
>
> 因子是一段连续小写字母，或一对花括号中的表达式。连接把当前集合与因子集合做笛卡尔积，逗号把若干连接结果取并。哈希集合负责去重，全部解析完再按字典序排序。

<!-- thinking:end -->

记表达式为 $s$。从下标 $i$ 出发定义两个函数。

$\textit{expr}(i)$ 解析若干个 $\textit{term}$ 的并集：先解析一个 $\textit{term}$，若后面是逗号，就跳过逗号再解析下一个 $\textit{term}$，把集合并进去，直到遇到 `}` 或结尾。

$\textit{term}(i)$ 解析若干个因子的连接，初始集合为 $\{\varepsilon\}$。若当前字符是 `{`，则递归解析内部的 $\textit{expr}$ 并跳过匹配的 `}`；否则读出一段连续小写字母。然后把当前集合与该因子的集合做笛卡尔积。遇到逗号、`}` 或结尾时返回。

最后对 $\textit{expr}(0)$ 的结果按字典序排序。同一段子表达式只解析一次，方法一里把后缀复制到每一支再重扫的开销不再出现。

时间复杂度 $O(n^2 \times 3^{n/7})$，空间复杂度 $O(n \times 3^{n/7})$，其中 $n$ 为表达式长度。最坏情况是连续拼接的 `{a,b,c}`，需要生成 $\Theta(3^{n/7})$ 个长度为 $O(n)$ 的字符串；构造这些字符串是 $O(n \times 3^{n/7})$，排序是 $O(n^2 \times 3^{n/7})$。嵌套的三路并集在本方法里每层集合大小为 $O(1)$，时间只需 $O(n)$。

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
