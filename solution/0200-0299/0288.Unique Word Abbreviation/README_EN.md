---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [288. Unique Word Abbreviation 🔒](https://leetcode.com/problems/unique-word-abbreviation)

[中文文档](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README.md)

## Description

<!-- description:start -->

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
<p><strong class="example">Example 1:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

According to the problem description, we define a function $abbr(s)$, which calculates the abbreviation of the word $s$. If the length of the word $s$ is less than $3$, then its abbreviation is itself; otherwise, its abbreviation is its first letter + (its length - 2) + its last letter.

Next, we define a hash table $d$, where the key is the abbreviation of the word, and the value is a set, the elements of which are all words abbreviated as that key. We traverse the given word dictionary, and for each word $s$ in the dictionary, we calculate its abbreviation $abbr(s)$, and add $s$ to $d[abbr(s)]$.

When judging whether the word $word$ meets the requirements of the problem, we calculate its abbreviation $abbr(word)$. If $abbr(word)$ is not in the hash table $d$, then $word$ meets the requirements of the problem; otherwise, we judge whether there is only one element in $d[abbr(word)]$. If there is only one element in $d[abbr(word)]$ and that element is $word$, then $word$ meets the requirements of the problem.

In terms of time complexity, the time complexity of initializing the hash table is $O(n)$, where $n$ is the length of the word dictionary; the time complexity of judging whether a word meets the requirements of the problem is $O(1)$. In terms of space complexity, the space complexity of the hash table is $O(n)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
