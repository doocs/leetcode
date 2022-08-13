# [剑指 Offer II 063. 替换单词](https://leetcode.cn/problems/UhWRSj)

## 题目描述

<!-- 这里写题目描述 -->

<p>在英语中，有一个叫做&nbsp;<code>词根(root)</code> 的概念，它可以跟着其他一些词组成另一个较长的单词&mdash;&mdash;我们称这个词为&nbsp;<code>继承词(successor)</code>。例如，词根<code>an</code>，跟随着单词&nbsp;<code>other</code>(其他)，可以形成新的单词&nbsp;<code>another</code>(另一个)。</p>

<p>现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有<code>继承词</code>用<code>词根</code>替换掉。如果<code>继承词</code>有许多可以形成它的<code>词根</code>，则用最短的词根替换它。</p>

<p>需要输出替换之后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dictionary = [&quot;cat&quot;,&quot;bat&quot;,&quot;rat&quot;], sentence = &quot;the cattle was rattled by the battery&quot;
<strong>输出：</strong>&quot;the cat was rat by the bat&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], sentence = &quot;aadsfasf absbs bbab cadsfafs&quot;
<strong>输出：</strong>&quot;a a b c&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dictionary = [&quot;a&quot;, &quot;aa&quot;, &quot;aaa&quot;, &quot;aaaa&quot;], sentence = &quot;a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa&quot;
<strong>输出：</strong>&quot;a a a a a a a a bbb baba a&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>dictionary = [&quot;catt&quot;,&quot;cat&quot;,&quot;bat&quot;,&quot;rat&quot;], sentence = &quot;the cattle was rattled by the battery&quot;
<strong>输出：</strong>&quot;the cat was rat by the bat&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>dictionary = [&quot;ac&quot;,&quot;ab&quot;], sentence = &quot;it is abnormal that this solution is accepted&quot;
<strong>输出：</strong>&quot;it is ab that this solution is ac&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code>&nbsp;仅由小写字母组成。</li>
	<li><code>1 &lt;= sentence.length &lt;= 10^6</code></li>
	<li><code>sentence</code>&nbsp;仅由小写字母和空格组成。</li>
	<li><code>sentence</code> 中单词的总量在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中每个单词的长度在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中单词之间由一个空格隔开。</li>
	<li><code>sentence</code>&nbsp;没有前导或尾随空格。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 648&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/replace-words/">https://leetcode.cn/problems/replace-words/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

**方法二：前缀树**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    Trie()
        : children(26)
        , v("") { }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->v = word;
    }

    string search(string word) {
        Trie* node = this;
        for (char c : word) {
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
