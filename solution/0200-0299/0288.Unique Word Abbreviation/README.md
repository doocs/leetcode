# [288. 单词的唯一缩写](https://leetcode-cn.com/problems/unique-word-abbreviation)

[English Version](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README_EN.md)

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
["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique"]
[[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"]]
<strong>输出
</strong>[null, false, true, false, true]

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

<!-- 这里可写通用的实现逻辑 -->

哈希表实现，其中 key 存放单词缩写，value 存放单词缩写所对应的所有单词的集合。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class ValidWordAbbr:

    def __init__(self, dictionary: List[str]):
        self.words = {}
        for word in dictionary:
            abbr = self._word_abbr(word)
            vals = self.words.get(abbr, set())
            vals.add(word)
            self.words[abbr] = vals

    def isUnique(self, word: str) -> bool:
        abbr = self._word_abbr(word)
        vals = self.words.get(abbr)
        return vals is None or (len(vals) == 1 and word in vals)

    def _word_abbr(self, word: str) -> str:
        n = len(word)
        if n < 3:
            return word
        return f'{word[0]}{n - 2}{word[n - 1]}'


# Your ValidWordAbbr object will be instantiated and called as such:
# obj = ValidWordAbbr(dictionary)
# param_1 = obj.isUnique(word)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class ValidWordAbbr {
    private Map<String, Set<String>> words;

    public ValidWordAbbr(String[] dictionary) {
        words = new HashMap<>();
        for (String word : dictionary) {
            String abbr = wordAbbr(word);
            words.computeIfAbsent(abbr, k -> new HashSet<>()).add(word);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = wordAbbr(word);
        Set<String> vals = words.get(abbr);
        return vals == null || (vals.size() == 1 && vals.contains(word));
    }

    private String wordAbbr(String word) {
        int n = word.length();
        if (n < 3) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(n - 2).append(word.charAt(n - 1));
        return sb.toString();
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
