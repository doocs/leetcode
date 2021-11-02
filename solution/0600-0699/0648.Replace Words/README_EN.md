# [648. Replace Words](https://leetcode.com/problems/replace-words)

[中文文档](/solution/0600-0699/0648.Replace%20Words/README.md)

## Description

<p>In English, we have a concept called <strong>root</strong>, which can be followed by some other word&nbsp;to form another longer word - let&#39;s call this word <strong>successor</strong>. For example, when the <strong>root</strong> <code>&quot;an&quot;</code> is&nbsp;followed by the <strong>successor</strong>&nbsp;word&nbsp;<code>&quot;other&quot;</code>, we&nbsp;can form a new word <code>&quot;another&quot;</code>.</p>

<p>Given a <code>dictionary</code> consisting of many <strong>roots</strong> and a <code>sentence</code>&nbsp;consisting of words separated by spaces, replace all the <strong>successors</strong> in the sentence with the <strong>root</strong> forming it. If a <strong>successor</strong> can be replaced by more than one <strong>root</strong>,&nbsp;replace it with the <strong>root</strong> that has&nbsp;<strong>the shortest length</strong>.</p>

<p>Return <em>the <code>sentence</code></em> after the replacement.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>Output:</strong> "the cat was rat by the bat"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
<strong>Output:</strong> "a a b c"
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
<strong>Output:</strong> "a a a a a a a a bbb baba a"
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>Output:</strong> "the cat was rat by the bat"
</pre><p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong> dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
<strong>Output:</strong> "it is ab that this solution is ac"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code>&nbsp;consists of only lower-case letters.</li>
	<li><code>1 &lt;= sentence.length &lt;= 10^6</code></li>
	<li><code>sentence</code>&nbsp;consists of only lower-case letters and spaces.</li>
	<li>The number of words in&nbsp;<code>sentence</code>&nbsp;is in the range <code>[1, 1000]</code></li>
	<li>The length of each word in&nbsp;<code>sentence</code>&nbsp;is in the range <code>[1, 1000]</code></li>
	<li>Each two consecutive words in&nbsp;<code>sentence</code>&nbsp;will be separated by exactly one space.</li>
	<li><code>sentence</code>&nbsp;does not have leading or trailing spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self) -> None:
        self.children = [None] * 26
        self.root = None


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for root in dictionary:
            cur = trie
            for c in root:
                idx = ord(c) - ord('a')
                if cur.children[idx] is None:
                    cur.children[idx] = Trie()
                cur = cur.children[idx]
            cur.root = root

        ans = []
        for word in sentence.split():
            cur = trie
            for c in word:
                idx = ord(c) - ord('a')
                if cur.children[idx] is None or cur.root is not None:
                    break
                cur = cur.children[idx]
            ans.append(word if cur.root is None else cur.root)
        return ' '.join(ans)
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    String root;
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String root : dictionary) {
            Trie cur = trie;
            for (char c : root.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Trie();
                }
                cur = cur.children[c - 'a'];
            }
            cur.root = root;
        }
        List<String> ans = new ArrayList<>();
        for (String word : sentence.split("\\s+")) {
            Trie cur = trie;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null || cur.root != null) {
                    break;
                }
                cur = cur.children[c - 'a'];
            }
            ans.add(cur.root == null ? word : cur.root);
        }
        return String.join(" ", ans);
    }
}
```

### **C++**

```cpp
class Trie {
public:
    string root;
    vector<Trie*> children;

    Trie() {
        root = "";
        children.resize(26);
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie* trie = new Trie();
        for (auto root : dictionary)
        {
            Trie* cur = trie;
            for (char c : root)
            {
                if (!cur->children[c - 'a']) cur->children[c - 'a'] = new Trie();
                cur = cur->children[c - 'a'];
            }
            cur->root = root;
        }

        string ans = "";
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.push_back(s);
        for (auto word : ss)
        {
            Trie* cur = trie;
            for (char c : word)
            {
                if (!cur->children[c - 'a'] || cur->root != "") break;
                cur = cur->children[c - 'a'];
            }
            ans += cur->root == "" ? word : cur->root;
            ans += " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func replaceWords(dictionary []string, sentence string) string {
	trie := &Trie{}
	for _, root := range dictionary {
		cur := trie
		for _, c := range root {
			c -= 'a'
			if cur.children[c] == nil {
				cur.children[c] = &Trie{}
			}
			cur = cur.children[c]
		}
		cur.root = root
	}

	var ans []string
	for _, word := range strings.Split(sentence, " ") {
		cur := trie
		for _, c := range word {
			c -= 'a'
			if cur.children[c] == nil || cur.root != "" {
				break
			}
			cur = cur.children[c]
		}
		if cur.root == "" {
			ans = append(ans, word)
		} else {
			ans = append(ans, cur.root)
		}
	}
	return strings.Join(ans, " ")
}

type Trie struct {
	children [26]*Trie
	root     string
}
```

### **...**

```

```

<!-- tabs:end -->
