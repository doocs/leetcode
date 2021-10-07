# [676. Implement Magic Dictionary](https://leetcode.com/problems/implement-magic-dictionary)

[中文文档](/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README.md)

## Description

<p>Design a data structure that is initialized with a list of <strong>different</strong> words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.</p>

<p>Implement the&nbsp;<code>MagicDictionary</code>&nbsp;class:</p>

<ul>
	<li><code>MagicDictionary()</code>&nbsp;Initializes the object.</li>
	<li><code>void buildDict(String[]&nbsp;dictionary)</code>&nbsp;Sets the data structure&nbsp;with an array of distinct strings <code>dictionary</code>.</li>
	<li><code>bool search(String searchWord)</code> Returns <code>true</code> if you can change <strong>exactly one character</strong> in <code>searchWord</code> to match any string in the data structure, otherwise returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MagicDictionary&quot;, &quot;buildDict&quot;, &quot;search&quot;, &quot;search&quot;, &quot;search&quot;, &quot;search&quot;]
[[], [[&quot;hello&quot;, &quot;leetcode&quot;]], [&quot;hello&quot;], [&quot;hhllo&quot;], [&quot;hell&quot;], [&quot;leetcoded&quot;]]
<strong>Output</strong>
[null, null, false, true, false, false]

<strong>Explanation</strong>
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict([&quot;hello&quot;, &quot;leetcode&quot;]);
magicDictionary.search(&quot;hello&quot;); // return False
magicDictionary.search(&quot;hhllo&quot;); // We can change the second &#39;h&#39; to &#39;e&#39; to match &quot;hello&quot; so we return True
magicDictionary.search(&quot;hell&quot;); // return False
magicDictionary.search(&quot;leetcoded&quot;); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;dictionary.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code> consists of only lower-case English letters.</li>
	<li>All the strings in&nbsp;<code>dictionary</code>&nbsp;are <strong>distinct</strong>.</li>
	<li><code>1 &lt;=&nbsp;searchWord.length &lt;= 100</code></li>
	<li><code>searchWord</code>&nbsp;consists of only lower-case English letters.</li>
	<li><code>buildDict</code>&nbsp;will be called only once before <code>search</code>.</li>
	<li>At most <code>100</code> calls will be made to <code>search</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MagicDictionary:

    def __init__(self):
        """
        Initialize your data structure here.
        """

    def _patterns(self, word):
        return [word[:i] + '*' + word[i + 1:] for i in range(len(word))]

    def buildDict(self, dictionary: List[str]) -> None:
        self.words = set(dictionary)
        self.counter = collections.Counter(
            p for word in dictionary for p in self._patterns(word))

    def search(self, searchWord: str) -> bool:
        for p in self._patterns(searchWord):
            if self.counter[p] > 1 or (self.counter[p] == 1 and searchWord not in self.words):
                return True
        return False


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
```

### **Java**

```java
class MagicDictionary {
    List<char[]> dict;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        dict = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        for (String item : dictionary) {
            dict.add(item.toCharArray());
        }
    }

    public boolean search(String searchWord) {
        char[] target = searchWord.toCharArray();
        for (char[] item : dict) {
            if (item.length != target.length) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < target.length; i++) {
                if (target[i] != item[i]) {
                    diff += 1;
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class MagicDictionary {
    private Set<String> words;
    private Map<String, Integer> counter;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        words = new HashSet<>();
        counter = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            words.add(word);
            for (String p : patterns(word)) {
                counter.put(p, counter.getOrDefault(p, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (String p : patterns(searchWord)) {
            int cnt = counter.getOrDefault(p, 0);
            if (cnt > 1 || (cnt == 1 && !words.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

    private List<String> patterns(String word) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            chars[i] = '*';
            res.add(new String(chars));
            chars[i] = c;
        }
        return res;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
```

### **C++**

```cpp
class MagicDictionary {
public:
    /** Initialize your data structure here. */
    MagicDictionary() {

    }

    void buildDict(vector<string> dictionary) {
        for (string word : dictionary)
        {
            words.insert(word);
            for (string p : patterns(word)) ++counter[p];
        }
    }

    bool search(string searchWord) {
        for (string p : patterns(searchWord))
        {
            if (counter[p] > 1 || (counter[p] == 1 && !words.count(searchWord))) return true;
        }
        return false;
    }

private:
    unordered_set<string> words;
    unordered_map<string, int> counter;

    vector<string> patterns(string word) {
        vector<string> res;
        for (int i = 0; i < word.size(); ++i)
        {
            char c = word[i];
            word[i] = '*';
            res.push_back(word);
            word[i] = c;
        }
        return res;
    }
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */
```

### **Go**

```go
type MagicDictionary struct {
	words   map[string]bool
	counter map[string]int
}

/** Initialize your data structure here. */
func Constructor() MagicDictionary {
	return MagicDictionary{
		words:   make(map[string]bool),
		counter: make(map[string]int),
	}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	for _, word := range dictionary {
		this.words[word] = true
		for _, p := range patterns(word) {
			this.counter[p]++
		}
	}
}

func (this *MagicDictionary) Search(searchWord string) bool {
	for _, p := range patterns(searchWord) {
		if this.counter[p] > 1 || (this.counter[p] == 1 && !this.words[searchWord]) {
			return true
		}
	}
	return false
}

func patterns(word string) []string {
	var res []string
	for i := 0; i < len(word); i++ {
		res = append(res, word[:i]+"."+word[i+1:])
	}
	return res
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */
```

### **...**

```

```

<!-- tabs:end -->
