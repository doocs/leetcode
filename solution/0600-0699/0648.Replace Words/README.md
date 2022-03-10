# [648. 单词替换](https://leetcode-cn.com/problems/replace-words)

[English Version](/solution/0600-0699/0648.Replace%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在英语中，我们有一个叫做&nbsp;<code>词根</code>(root) 的概念，可以词根<strong>后面</strong>添加其他一些词组成另一个较长的单词——我们称这个词为&nbsp;<code>继承词</code>(successor)。例如，词根<code>an</code>，跟随着单词&nbsp;<code>other</code>(其他)，可以形成新的单词&nbsp;<code>another</code>(另一个)。</p>

<p>现在，给定一个由许多<strong>词根</strong>组成的词典 <code>dictionary</code> 和一个用空格分隔单词形成的句子 <code>sentence</code>。你需要将句子中的所有<strong>继承词</strong>用<strong>词根</strong>替换掉。如果<strong>继承词</strong>有许多可以形成它的<strong>词根</strong>，则用<strong>最短</strong>的词根替换它。</p>

<p>你需要输出替换之后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>输出：</strong>"the cat was rat by the bat"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
<strong>输出：</strong>"a a b c"
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀树实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
