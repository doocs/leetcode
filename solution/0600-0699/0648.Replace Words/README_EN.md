# [648. Replace Words](https://leetcode.com/problems/replace-words)

[中文文档](/solution/0600-0699/0648.Replace%20Words/README.md)

## Description

<p>In English, we have a concept called <strong>root</strong>, which can be followed by some other word to form another longer word - let&#39;s call this word <strong>successor</strong>. For example, when the <strong>root</strong> <code>&quot;an&quot;</code> is followed by the <strong>successor</strong> word <code>&quot;other&quot;</code>, we can form a new word <code>&quot;another&quot;</code>.</p>

<p>Given a <code>dictionary</code> consisting of many <strong>roots</strong> and a <code>sentence</code> consisting of words separated by spaces, replace all the <strong>successors</strong> in the sentence with the <strong>root</strong> forming it. If a <strong>successor</strong> can be replaced by more than one <strong>root</strong>, replace it with the <strong>root</strong> that has <strong>the shortest length</strong>.</p>

<p>Return <em>the <code>sentence</code></em> after the replacement.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;cat&quot;,&quot;bat&quot;,&quot;rat&quot;], sentence = &quot;the cattle was rattled by the battery&quot;
<strong>Output:</strong> &quot;the cat was rat by the bat&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], sentence = &quot;aadsfasf absbs bbab cadsfafs&quot;
<strong>Output:</strong> &quot;a a b c&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code> consists of only lower-case letters.</li>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>6</sup></code></li>
	<li><code>sentence</code> consists of only lower-case letters and spaces.</li>
	<li>The number of words in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>The length of each word in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>Every two consecutive words in <code>sentence</code> will be separated by exactly one space.</li>
	<li><code>sentence</code> does not have leading or trailing spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        s = set(dictionary)
        words = sentence.split()
        for i, word in enumerate(words):
            for j in range(1, len(word) + 1):
                if word[:j] in s:
                    words[i] = word[:j]
                    break
        return ' '.join(words)
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = None

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.v = word

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                break
            node = node.children[idx]
            if node.v:
                return node.v
        return word


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for v in dictionary:
            trie.insert(v)
        return ' '.join(trie.search(v) for v in sentence.split())
```

### **Java**

```java
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> s = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 1; j <= word.length(); ++j) {
                String t = word.substring(0, j);
                if (s.contains(t)) {
                    words[i] = t;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
}
```

```java
class Trie {
    Trie[] children = new Trie[26];
    String v;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.v = word;
    }

    String search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return word;
            }
            node = node.children[c];
            if (node.v != null) {
                return node.v;
            }
        }
        return word;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String v : dictionary) {
            trie.insert(v);
        }
        List<String> ans = new ArrayList<>();
        for (String v : sentence.split("\\s")) {
            ans.add(trie.search(v));
        }
        return String.join(" ", ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        unordered_set<string> s(dictionary.begin(), dictionary.end());
        istringstream is(sentence);
        vector<string> words;
        string ss;
        while (is >> ss) words.push_back(ss);
        for (int i = 0; i < words.size(); ++i) {
            string word = words[i];
            for (int j = 1; j <= word.size(); ++j) {
                string t = word.substr(0, j);
                if (s.count(t)) {
                    words[i] = t;
                    break;
                }
            }
        }
        string ans = "";
        for (string& word : words) ans += word + " ";
        ans.pop_back();
        return ans;
    }
};
```

```cpp
class Trie {
public:
    vector<Trie*> children;
    string v;
    Trie() : children(26), v("") {}

    void insert(string word) {
        Trie* node = this;
        for (char c : word)
        {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->v = word;
    }

    string search(string word) {
        Trie* node = this;
        for (char c : word)
        {
            c -= 'a';
            if (!node->children[c]) break;
            node = node->children[c];
            if (node->v != "") return node->v;
        }
        return word;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie* trie = new Trie();
        for (auto& v : dictionary) trie->insert(v);
        string ans = "";
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.push_back(s);
        for (auto word : ss) ans += trie->search(word) + " ";
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func replaceWords(dictionary []string, sentence string) string {
	s := map[string]bool{}
	for _, v := range dictionary {
		s[v] = true
	}
	words := strings.Split(sentence, " ")
	for i, word := range words {
		for j := 1; j <= len(word); j++ {
			t := word[:j]
			if s[t] {
				words[i] = t
				break
			}
		}
	}
	return strings.Join(words, " ")
}
```

```go
type Trie struct {
	children [26]*Trie
	v        string
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.v = word
}

func (this *Trie) search(word string) string {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			break
		}
		node = node.children[c]
		if node.v != "" {
			return node.v
		}
	}
	return word
}

func replaceWords(dictionary []string, sentence string) string {
	trie := newTrie()
	for _, v := range dictionary {
		trie.insert(v)
	}
	var ans []string
	for _, v := range strings.Split(sentence, " ") {
		ans = append(ans, trie.search(v))
	}
	return strings.Join(ans, " ")
}
```

### **...**

```

```

<!-- tabs:end -->
