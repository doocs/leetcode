---
comments: true
difficulty: 中等
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3481. 应用替换 🔒](https://leetcode.cn/problems/apply-substitutions)

[English Version](/solution/3400-3499/3481.Apply%20Substitutions/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="384" data-start="34">给定一个&nbsp;<code>replacements</code>&nbsp;映射和一个可能包含格式为 <code>%var%</code> <strong>占位符&nbsp;</strong>的字符串&nbsp;<code>text</code>，其中每个&nbsp;<code>var</code>&nbsp;对应&nbsp;<code>replacements</code>&nbsp;中的一个键。每个替换值本身可能包含 <strong>一个或多个</strong> 此类<strong>占位符</strong>。每个 <strong>占位符</strong> 都被与其相应的替换键对应的值替换。</p>

<p data-end="353" data-start="34">返回完全替换后 <strong>不</strong> 含任何 <strong>占位符</strong> 的&nbsp;<code>text</code>&nbsp;字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>replacements = [["A","abc"],["B","def"]], text = "%A%_%B%"</span></p>

<p><strong>输出：</strong><span class="example-io">"abc_def"</span></p>

<p><strong>解释：</strong></p>

<ul data-end="238" data-start="71">
	<li data-end="138" data-start="71">映射将&nbsp;<code data-end="101" data-start="96">"A"</code> 与&nbsp;<code data-end="114" data-start="107">"abc"</code>&nbsp;关联，并将&nbsp;<code data-end="124" data-start="119">"B"</code> 与&nbsp;<code data-end="137" data-start="130">"def"</code>&nbsp;关联。</li>
	<li data-end="203" data-start="139">用&nbsp;<code data-end="167" data-start="160">"abc"</code>&nbsp;替换文本中的&nbsp;<code data-end="154" data-start="149">%A%</code>，并用&nbsp;<code data-end="190" data-start="183">"def"</code>&nbsp;替换文本中的&nbsp;<code data-end="177" data-start="172">%B%</code>。</li>
	<li data-end="238" data-start="204">最终文本变为&nbsp;<code data-end="237" data-start="226">"abc_def"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>replacements = [["A","bce"],["B","ace"],["C","abc%B%"]], text = "%A%_%B%_%C%"</span></p>

<p><span class="example-io"><b>输出：</b>"bce_ace_abcace"</span></p>

<p><strong>解释：</strong></p>

<ul data-end="541" data-is-last-node="" data-is-only-node="" data-start="255">
	<li data-end="346" data-start="255">映射将&nbsp;<code data-end="285" data-start="280">"A"</code> 与&nbsp;<code data-end="298" data-start="291">"bce"</code>&nbsp;关联，<code data-end="305" data-start="300">"B"</code> 与&nbsp;<code data-end="318" data-start="311">"ace"</code>&nbsp;关联，以及&nbsp;<code data-end="329" data-start="324">"C"</code> 与&nbsp;<code data-end="345" data-start="335">"abc%B%"</code>&nbsp;关联。</li>
	<li data-end="411" data-start="347">用&nbsp;<code data-end="375" data-start="368">"bce"</code>&nbsp;替换文本中的&nbsp;<code data-end="362" data-start="357">%A%</code>，并用&nbsp;<code data-end="398" data-start="391">"ace"</code>&nbsp;替换文本中的&nbsp;<code data-end="385" data-start="380">%B%</code>。</li>
	<li data-end="496" data-start="412">接着，对于&nbsp;<code data-end="429" data-start="424">%C%</code>，用&nbsp;<code data-end="474" data-start="467">"ace"</code> 替换&nbsp;<code data-end="461" data-start="451">"abc%B%"</code>&nbsp;中的&nbsp;<code data-end="447" data-start="442">%B%</code>&nbsp;来得到&nbsp;<code data-end="495" data-start="485">"abcace"</code>。</li>
	<li data-end="541" data-is-last-node="" data-start="497">最终文本变为&nbsp;<code data-end="540" data-start="522">"bce_ace_abcace"</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li data-end="1432" data-start="1398"><code>1 &lt;= replacements.length &lt;= 10</code></li>
	<li data-end="1683" data-start="1433"><code data-end="1465" data-start="1451">replacements</code>&nbsp;中的每个元素是一个双值列表&nbsp;<code data-end="1502" data-start="1488">[key, value]</code>，其中：
	<ul data-end="1683" data-start="1513">
		<li data-end="1558" data-start="1513"><code data-end="1520" data-start="1515">key</code>&nbsp;是一个大写英语字母。</li>
		<li data-end="1683" data-start="1561"><code data-end="1570" data-start="1563">value</code>&nbsp;是一个最多有 8 个字符，可能包含 0 个或更多格式为&nbsp;<code data-end="1682" data-start="1673">%&lt;key&gt;%</code> 的占位符的非空字符串。</li>
	</ul>
	</li>
	<li data-end="726" data-start="688">所有的替换键互不相同。</li>
	<li data-end="1875" data-start="1723"><code>text</code>&nbsp;字符串是通过从替换映射中随机串联所有 key 占位符（格式为 <code>%&lt;key&gt;%</code>）而形成的，以虚线分隔。</li>
	<li data-end="1942" data-start="1876"><code>text.length == 4 * replacements.length - 1</code></li>
	<li data-end="2052" data-start="1943"><code>text</code>&nbsp;或任何替换值中的每个占位符对应&nbsp;<code>replacements</code> 映射中的一个键。</li>
	<li data-end="2265" data-start="2205">替换键之间没有循环依赖。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 递归

<!-- thinking:start -->

> **思考**
>
> 占位符 $\%key\%$ 要换成映射值，而值里还可以再套占位符。替换键无环且至多 $10$ 个，递归展开即可。
>
> 若只做一层替换，嵌套占位符会留下百分号。先建哈希表，再对当前串找一对 $\%$。
>
> $\textit{dfs}$ 取出中间的键，先完全展开 $d[key]$，再拼回左右两侧并继续处理右段。题面保证每个占位符都有定义。

<!-- thinking:end -->

我们用一个哈希表 $\textit{d}$ 来存储替换的映射关系，然后定义一个函数 $\textit{dfs}$ 来递归地替换字符串中的占位符。

函数 $\textit{dfs}$ 的执行逻辑如下：

1. 在字符串 $\textit{s}$ 中查找第一个占位符的起始位置 $i$，如果找不到，则返回 $\textit{s}$；
2. 在字符串 $\textit{s}$ 中查找第一个占位符的结束位置 $j$，如果找不到，则返回 $\textit{s}$；
3. 截取占位符的键值 $key$，然后递归地替换占位符的值 $d[key]$；
4. 返回替换后的字符串。

在主函数中，我们调用 $\textit{dfs}$ 函数，传入文本字符串 $\textit{text}$，并返回结果。

时间复杂度 $O(m + n \times L)$，空间复杂度 $O(m + n \times L)$。其中 $m$ 为替换映射的长度，而 $n$ 和 $L$ 分别为文本字符串的长度和占位符的平均长度。

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
