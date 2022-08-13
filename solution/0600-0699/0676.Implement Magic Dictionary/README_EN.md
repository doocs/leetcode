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
        self.d = None

    def buildDict(self, dictionary: List[str]) -> None:
        self.d = dictionary

    def search(self, searchWord: str) -> bool:
        for w in self.d:
            if len(w) != len(searchWord):
                continue
            diff = sum(a != b for a, b in zip(w, searchWord))
            if diff == 1:
                return True
        return False


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
```

```python
class MagicDictionary:

    def __init__(self):
        """
        Initialize your data structure here.
        """

    def gen(self, word):
        return [word[:i] + '*' + word[i + 1:] for i in range(len(word))]

    def buildDict(self, dictionary: List[str]) -> None:
        self.s = set(dictionary)
        self.cnt = Counter(p for word in dictionary for p in self.gen(word))

    def search(self, searchWord: str) -> bool:
        for p in self.gen(searchWord):
            if self.cnt[p] > 1 or (self.cnt[p] == 1 and searchWord not in self.s):
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
    private String[] d;

    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        d = dictionary;
    }

    public boolean search(String searchWord) {
        for (String w : d) {
            if (w.length() != searchWord.length()) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < w.length(); ++i) {
                if (w.charAt(i) != searchWord.charAt(i)) {
                    ++diff;
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
```

```java
class MagicDictionary {
    private Set<String> s = new HashSet<>();
    private Map<String, Integer> cnt = new HashMap<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            s.add(word);
            for (String p : gen(word)) {
                cnt.put(p, cnt.getOrDefault(p, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (String p : gen(searchWord)) {
            int v = cnt.getOrDefault(p, 0);
            if (v > 1 || (v == 1 && !s.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

    private List<String> gen(String word) {
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
    vector<string> d;

    MagicDictionary() {
    }

    void buildDict(vector<string> dictionary) {
        d = move(dictionary);
    }

    bool search(string searchWord) {
        for (auto&& w : d) {
            if (w.size() != searchWord.size()) continue;
            int diff = 0;
            for (int i = 0; i < w.size(); ++i) diff += w[i] != searchWord[i];
            if (diff == 1) return true;
        }
        return false;
    }
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */
```

```cpp
class MagicDictionary {
public:
    /** Initialize your data structure here. */
    MagicDictionary() {

    }

    void buildDict(vector<string> dictionary) {
        for (string word : dictionary)
        {
            s.insert(word);
            for (string p : gen(word)) ++cnt[p];
        }
    }

    bool search(string searchWord) {
        for (string p : gen(searchWord))
        {
            if (cnt[p] > 1 || (cnt[p] == 1 && !s.count(searchWord))) return true;
        }
        return false;
    }

private:
    unordered_set<string> s;
    unordered_map<string, int> cnt;

    vector<string> gen(string word) {
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
	d []string
}

func Constructor() MagicDictionary {
	return MagicDictionary{[]string{}}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	this.d = dictionary
}

func (this *MagicDictionary) Search(searchWord string) bool {
	for _, w := range this.d {
		if len(w) != len(searchWord) {
			continue
		}
		diff := 0
		for i := range w {
			if w[i] != searchWord[i] {
				diff++
			}
		}
		if diff == 1 {
			return true
		}
	}
	return false
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */
```

```go
type MagicDictionary struct {
	s   map[string]bool
	cnt map[string]int
}

/** Initialize your data structure here. */
func Constructor() MagicDictionary {
	return MagicDictionary{map[string]bool{}, map[string]int{}}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	for _, word := range dictionary {
		this.s[word] = true
		for _, p := range gen(word) {
			this.cnt[p]++
		}
	}
}

func (this *MagicDictionary) Search(searchWord string) bool {
	for _, p := range gen(searchWord) {
		if this.cnt[p] > 1 || (this.cnt[p] == 1 && !this.s[searchWord]) {
			return true
		}
	}
	return false
}

func gen(word string) []string {
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
