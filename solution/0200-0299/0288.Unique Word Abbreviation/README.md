# [288. 单词的唯一缩写 🔒](https://leetcode.cn/problems/unique-word-abbreviation)

[English Version](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README_EN.md)

<!-- tags:设计,数组,哈希表,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>单词的 <strong>缩写</strong> 需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。如果单词只有两个字符，那么它就是它自身的 <strong>缩写</strong> 。</p>

<p>以下是一些单词缩写的范例：</p>

<ul>
	<li><code>dog --> d1g</code> 因为第一个字母 <code>'d'</code> 和最后一个字母 <code>'g'</code> 之间有 <code>1</code> 个字母</li>
	<li><code>internationalization --> i18n</code> 因为第一个字母 <code>'i'</code> 和最后一个字母 <code>'n'</code> 之间有 <code>18</code> 个字母</li>
	<li><code>it --> it</code> 单词只有两个字符，它就是它自身的 <strong>缩写</strong></li>
</ul>

<p> </p>

<p>实现 <code>ValidWordAbbr</code> 类：</p>

<ul>
	<li><code>ValidWordAbbr(String[] dictionary)</code> 使用单词字典 <code>dictionary</code> 初始化对象</li>
	<li><code>boolean isUnique(string word)</code> 如果满足下述任意一个条件，返回 <code>true</code> ；否则，返回 <code>false</code> ：
	<ul>
		<li>字典 <code>dictionary</code> 中没有任何其他单词的 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同。</li>
		<li>字典 <code>dictionary</code> 中的所有 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同的单词都与 <code>word</code> <strong>相同</strong> 。</li>
	</ul>
	</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique", "isUnique"]
[[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"], ["cake"]]
<strong>输出
</strong>[null, false, true, false, true, true]

<strong>解释</strong>
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("dear"); // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
validWordAbbr.isUnique("cart"); // 返回 true，字典中不存在缩写为 "c2t" 的单词
validWordAbbr.isUnique("cane"); // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
validWordAbbr.isUnique("make"); // 返回 true，字典中不存在缩写为 "m2e" 的单词
validWordAbbr.isUnique("cake"); // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= dictionary.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>1 <= dictionary[i].length <= 20</code></li>
	<li><code>dictionary[i]</code> 由小写英文字母组成</li>
	<li><code>1 <= word <= 20</code></li>
	<li><code>word</code> 由小写英文字母组成</li>
	<li>最多调用 <code>5000</code> 次 <code>isUnique</code></li>
</ul>

## 解法

### 方法一：哈希表

根据题目描述，我们定义一个函数 $abbr(s)$，它的功能是计算单词 $s$ 的缩写。如果单词 $s$ 的长度小于 $3$，那么它的缩写就是它本身；否则，它的缩写是它的首字母 + (它的长度 - 2) + 它的尾字母。

接下来，我们定义一个哈希表 $d$，它的键是单词的缩写，值是一个集合，集合中的元素是所有缩写为该键的单词。我们遍历给定的单词字典，对于字典中的每个单词 $s$，我们求出它的缩写 $abbr(s)$，并将 $s$ 添加到 $d[abbr(s)]$ 中。

在判断单词 $word$ 是否满足题目要求时，我们求出它的缩写 $abbr(word)$，如果 $abbr(word)$ 不在哈希表 $d$ 中，那么 $word$ 满足题目要求；否则，我们判断 $d[abbr(word)]$ 中是否只有一个元素，如果 $d[abbr(word)]$ 中只有一个元素且该元素就是 $word$，那么 $word$ 满足题目要求。

时间复杂度方面，初始化哈希表的时间复杂度是 $O(n)$，其中 $n$ 是单词字典的长度；判断单词是否满足题目要求的时间复杂度是 $O(1)$。空间复杂度方面，哈希表的空间复杂度是 $O(n)$。

<!-- tabs:start -->

```python
class ValidWordAbbr:
    def __init__(self, dictionary: List[str]):
        self.d = defaultdict(set)
        for s in dictionary:
            self.d[self.abbr(s)].add(s)

    def isUnique(self, word: str) -> bool:
        s = self.abbr(word)
        return s not in self.d or all(word == t for t in self.d[s])

    def abbr(self, s: str) -> str:
        return s if len(s) < 3 else s[0] + str(len(s) - 2) + s[-1]


# Your ValidWordAbbr object will be instantiated and called as such:
# obj = ValidWordAbbr(dictionary)
# param_1 = obj.isUnique(word)
```

```java
class ValidWordAbbr {
    private Map<String, Set<String>> d = new HashMap<>();

    public ValidWordAbbr(String[] dictionary) {
        for (var s : dictionary) {
            d.computeIfAbsent(abbr(s), k -> new HashSet<>()).add(s);
        }
    }

    public boolean isUnique(String word) {
        var ws = d.get(abbr(word));
        return ws == null || (ws.size() == 1 && ws.contains(word));
    }

    private String abbr(String s) {
        int n = s.length();
        return n < 3 ? s : s.substring(0, 1) + (n - 2) + s.substring(n - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
```

```cpp
class ValidWordAbbr {
public:
    ValidWordAbbr(vector<string>& dictionary) {
        for (auto& s : dictionary) {
            d[abbr(s)].insert(s);
        }
    }

    bool isUnique(string word) {
        string s = abbr(word);
        return !d.count(s) || (d[s].size() == 1 && d[s].count(word));
    }

private:
    unordered_map<string, unordered_set<string>> d;

    string abbr(string& s) {
        int n = s.size();
        return n < 3 ? s : s.substr(0, 1) + to_string(n - 2) + s.substr(n - 1, 1);
    }
};

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr* obj = new ValidWordAbbr(dictionary);
 * bool param_1 = obj->isUnique(word);
 */
```

```go
type ValidWordAbbr struct {
	d map[string]map[string]bool
}

func Constructor(dictionary []string) ValidWordAbbr {
	d := make(map[string]map[string]bool)
	for _, s := range dictionary {
		abbr := abbr(s)
		if _, ok := d[abbr]; !ok {
			d[abbr] = make(map[string]bool)
		}
		d[abbr][s] = true
	}
	return ValidWordAbbr{d}
}

func (this *ValidWordAbbr) IsUnique(word string) bool {
	ws := this.d[abbr(word)]
	return ws == nil || (len(ws) == 1 && ws[word])
}

func abbr(s string) string {
	n := len(s)
	if n < 3 {
		return s
	}
	return fmt.Sprintf("%c%d%c", s[0], n-2, s[n-1])
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * obj := Constructor(dictionary);
 * param_1 := obj.IsUnique(word);
 */
```

```ts
class ValidWordAbbr {
    private d: Map<string, Set<string>> = new Map();

    constructor(dictionary: string[]) {
        for (const s of dictionary) {
            const abbr = this.abbr(s);
            if (!this.d.has(abbr)) {
                this.d.set(abbr, new Set());
            }
            this.d.get(abbr)!.add(s);
        }
    }

    isUnique(word: string): boolean {
        const ws = this.d.get(this.abbr(word));
        return ws === undefined || (ws.size === 1 && ws.has(word));
    }

    abbr(s: string): string {
        const n = s.length;
        return n < 3 ? s : s[0] + (n - 2) + s[n - 1];
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * var obj = new ValidWordAbbr(dictionary)
 * var param_1 = obj.isUnique(word)
 */
```

<!-- tabs:end -->

<!-- end -->
