# [1096. 花括号展开 II](https://leetcode.cn/problems/brace-expansion-ii)

[English Version](/solution/1000-1099/1096.Brace%20Expansion%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

我们设计一个递归函数 $dfs(exp)$，用于处理表达式 $exp$，并将结果存入集合 $s$ 中。

对于表达式 $exp$，我们首先找到第一个右花括号的位置 $j$，如果找不到，说明 $exp$ 中没有右花括号，即 $exp$ 为单一元素，直接将 $exp$ 加入集合 $s$ 中即可。

否则，我们从位置 $j$ 开始往左找到第一个左花括号的位置 $i$，此时 $exp[:i]$ 和 $exp[j + 1:]$ 分别为 $exp$ 的前缀和后缀，记为 $a$ 和 $c$。而 $exp[i + 1: j]$ 为 $exp$ 中花括号内的部分，即 $exp$ 中的子表达式，我们将其按照逗号分割成多个字符串 $b_1, b_2, \cdots, b_k$，然后对每个 $b_i$，我们将 $a + b_i + c$ 拼接成新的表达式，递归调用 $dfs$ 函数处理新的表达式，即 $dfs(a + b_i + c)$。

最后，我们将集合 $s$ 中的元素按照字典序排序，即可得到答案。

时间复杂度约为 $O(n \times 2^{n / 4})$，其中 $n$ 为表达式 $expression$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
