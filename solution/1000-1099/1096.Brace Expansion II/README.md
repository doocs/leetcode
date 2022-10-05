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

设计递归函数 `dfs(exp)`：

-   如果 `exp` 不包含 `{}`，将 `exp` 中的字符加入结果集 `s` 中，返回；
-   否则，找到第一个 `}` 的位置 $j$，从 $j$ 往前找到第一个 `{` 的位置 $i$，将 $exp[i+1..j-1]$ 之间的字符串按照 `,` 分割，得到一个字符串数组 `bs`。将 `exp[0..i-1]` 和 `exp[j+1..]` 作为前缀 $a$ 和后缀 $c$，分别与 `bs` 中的每个字符串 $b$ 拼接，然后递归调用 `dfs(a+b+c)`。

最后对结果集排序，返回。

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
            i = j
            while exp[i] != '{':
                i -= 1
            a, c, = exp[:i], exp[j + 1:]
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
        int i = j;
        while (exp.charAt(i) != '{') {
            --i;
        }
        String a = exp.substring(0, i);
        String c  = exp.substring(j + 1);
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
    set<string> s;
    vector<string> braceExpansionII(string expression) {
        dfs(expression);
        return vector<string>(s.begin(), s.end());
    }

    void dfs(string exp) {
        int j = exp.find('}');
        if (j == -1) {
            s.insert(exp);
            return;
        }
        int i = j;
        while (exp[i] != '{') {
            --i;
        }
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
	var dfs func(exp string)
	dfs = func(exp string) {
		j := strings.IndexByte(exp, '}')
		if j == -1 {
			s[exp] = struct{}{}
			return
		}
		i := j
		for exp[i] != '{' {
			i--
		}
		a, c := exp[:i], exp[j+1:]
		for _, b := range strings.Split(exp[i+1:j], ",") {
			dfs(a + b + c)
		}
	}
	dfs(expression)
	ans := []string{}
	for v := range s {
		ans = append(ans, v)
	}
	sort.Strings(ans)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
