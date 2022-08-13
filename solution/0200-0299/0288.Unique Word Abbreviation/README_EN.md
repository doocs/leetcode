# [288. Unique Word Abbreviation](https://leetcode.com/problems/unique-word-abbreviation)

[中文文档](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README.md)

## Description

<p>The <strong>abbreviation</strong> of a word is a concatenation of its first letter, the number of characters between the first and last letter, and its last letter. If a word has only two characters, then it is an <strong>abbreviation</strong> of itself.</p>

<p>For example:</p>

<ul>
	<li><code>dog --&gt; d1g</code> because there is one letter between the first letter <code>&#39;d&#39;</code> and the last letter <code>&#39;g&#39;</code>.</li>
	<li><code>internationalization --&gt; i18n</code> because there are 18 letters between the first letter <code>&#39;i&#39;</code> and the last letter <code>&#39;n&#39;</code>.</li>
	<li><code>it --&gt; it</code> because any word with only two characters is an <strong>abbreviation</strong> of itself.</li>
</ul>

<p>Implement the <code>ValidWordAbbr</code> class:</p>

<ul>
	<li><code>ValidWordAbbr(String[] dictionary)</code> Initializes the object with a <code>dictionary</code> of words.</li>
	<li><code>boolean isUnique(string word)</code> Returns <code>true</code> if <strong>either</strong> of the following conditions are met (otherwise returns <code>false</code>):
	<ul>
		<li>There is no word in <code>dictionary</code> whose <strong>abbreviation</strong> is equal to <code>word</code>&#39;s <strong>abbreviation</strong>.</li>
		<li>For any word in <code>dictionary</code> whose <strong>abbreviation</strong> is equal to <code>word</code>&#39;s <strong>abbreviation</strong>, that word and <code>word</code> are <strong>the same</strong>.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ValidWordAbbr&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;]
[[[&quot;deer&quot;, &quot;door&quot;, &quot;cake&quot;, &quot;card&quot;]], [&quot;dear&quot;], [&quot;cart&quot;], [&quot;cane&quot;], [&quot;make&quot;], [&quot;cake&quot;]]
<strong>Output</strong>
[null, false, true, false, true, true]

<strong>Explanation</strong>
ValidWordAbbr validWordAbbr = new ValidWordAbbr([&quot;deer&quot;, &quot;door&quot;, &quot;cake&quot;, &quot;card&quot;]);
validWordAbbr.isUnique(&quot;dear&quot;); // return false, dictionary word &quot;deer&quot; and word &quot;dear&quot; have the same abbreviation &quot;d2r&quot; but are not the same.
validWordAbbr.isUnique(&quot;cart&quot;); // return true, no words in the dictionary have the abbreviation &quot;c2t&quot;.
validWordAbbr.isUnique(&quot;cane&quot;); // return false, dictionary word &quot;cake&quot; and word &quot;cane&quot; have the same abbreviation  &quot;c2e&quot; but are not the same.
validWordAbbr.isUnique(&quot;make&quot;); // return true, no words in the dictionary have the abbreviation &quot;m2e&quot;.
validWordAbbr.isUnique(&quot;cake&quot;); // return true, because &quot;cake&quot; is already in the dictionary and no other word in the dictionary has &quot;c2e&quot; abbreviation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 20</code></li>
	<li><code>dictionary[i]</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
	<li>At most <code>5000</code> calls will be made to <code>isUnique</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class ValidWordAbbr:
    def __init__(self, dictionary: List[str]):
        self.words = defaultdict(set)
        for word in dictionary:
            abbr = self.word_abbr(word)
            self.words[abbr].add(word)

    def isUnique(self, word: str) -> bool:
        abbr = self.word_abbr(word)
        words = self.words[abbr]
        return not words or (len(words) == 1 and word in words)

    def word_abbr(self, s):
        return s if len(s) < 3 else f'{s[0]}{len(s) - 2}{s[-1]}'


# Your ValidWordAbbr object will be instantiated and called as such:
# obj = ValidWordAbbr(dictionary)
# param_1 = obj.isUnique(word)
```

### **Java**

```java
class ValidWordAbbr {
    private Map<String, Set<String>> words;

    public ValidWordAbbr(String[] dictionary) {
        words = new HashMap<>();
        for (String word : dictionary) {
            String abbr = abbr(word);
            words.computeIfAbsent(abbr, k -> new HashSet<>()).add(word);
        }
    }

    public boolean isUnique(String word) {
        String abbr = abbr(word);
        Set<String> vals = words.get(abbr);
        return vals == null || (vals.size() == 1 && vals.contains(word));
    }

    private String abbr(String s) {
        int n = s.length();
        return n < 3 ? s : s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
```

### **C++**

```cpp
class ValidWordAbbr {
public:
    unordered_map<string, unordered_set<string>> words;

    ValidWordAbbr(vector<string>& dictionary) {
        for (auto word : dictionary) {
            auto abbr = wordAbbr(word);
            words[abbr].insert(word);
        }
    }

    bool isUnique(string word) {
        auto abbr = wordAbbr(word);
        if (!words.count(abbr)) return true;
        auto vals = words[abbr];
        return vals.size() == 1 && vals.count(word);
    }

    string wordAbbr(string s) {
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

### **Go**

```go
type ValidWordAbbr struct {
	words map[string]map[string]bool
}

func Constructor(dictionary []string) ValidWordAbbr {
	words := make(map[string]map[string]bool)
	for _, word := range dictionary {
		abbr := wordAbbr(word)
		if words[abbr] == nil {
			words[abbr] = make(map[string]bool)
		}
		words[abbr][word] = true
	}
	return ValidWordAbbr{words}
}

func (this *ValidWordAbbr) IsUnique(word string) bool {
	abbr := wordAbbr(word)
	words := this.words[abbr]
	return words == nil || (len(words) == 1 && words[word])
}

func wordAbbr(s string) string {
	n := len(s)
	if n <= 2 {
		return s
	}
	return s[0:1] + strconv.Itoa(n-2) + s[n-1:]
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * obj := Constructor(dictionary);
 * param_1 := obj.IsUnique(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
