---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Design
    - Trie
    - String
---

<!-- problem:start -->

# [211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure)

[中文文档](/solution/0200-0299/0211.Design%20Add%20and%20Search%20Words%20Data%20Structure/README.md)

## Mô tả

<!-- description:start -->

<p>Thiết kế một cấu trúc dữ liệu hỗ trợ thêm các từ mới và tìm xem một chuỗi có khớp với bất kỳ chuỗi nào đã được thêm trước đó hay không.</p>

<p>Triển khai lớp <code>WordDictionary</code>:</p>

<ul>
	<li><code>WordDictionary()</code>&nbsp;Khởi tạo đối tượng.</li>
	<li><code>void addWord(word)</code> Thêm <code>word</code> vào cấu trúc dữ liệu để có thể khớp sau này.</li>
	<li><code>bool search(word)</code>&nbsp;Trả về <code>true</code> nếu có bất kỳ chuỗi nào trong cấu trúc dữ liệu khớp với <code>word</code>, hoặc <code>false</code> trong trường hợp ngược lại. <code>word</code> có thể chứa dấu chấm <code>&#39;.&#39;</code>, trong đó dấu chấm có thể khớp với bất kỳ chữ cái nào.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;WordDictionary&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;]
[[],[&quot;bad&quot;],[&quot;dad&quot;],[&quot;mad&quot;],[&quot;pad&quot;],[&quot;bad&quot;],[&quot;.ad&quot;],[&quot;b..&quot;]]
<strong>Đầu ra</strong>
[null,null,null,null,false,true,true,true]

<strong>Giải thích</strong>
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord(&quot;bad&quot;);
wordDictionary.addWord(&quot;dad&quot;);
wordDictionary.addWord(&quot;mad&quot;);
wordDictionary.search(&quot;pad&quot;); // trả về False
wordDictionary.search(&quot;bad&quot;); // trả về True
wordDictionary.search(&quot;.ad&quot;); // trả về True
wordDictionary.search(&quot;b..&quot;); // trả về True
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 25</code></li>
	<li><code>word</code> trong <code>addWord</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li><code>word</code> trong các truy vấn <code>search</code> gồm dấu <code>&#39;.&#39;</code> hoặc các chữ cái tiếng Anh viết thường.</li>
	<li>Sẽ có nhiều nhất <code>2</code> dấu chấm trong <code>word</code> đối với các truy vấn <code>search</code>.</li>
	<li>Sẽ có nhiều nhất <code>10<sup>4</sup></code> lần gọi đến <code>addWord</code> và <code>search</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Tra cứu chính xác có thể dùng hash set, nhưng $.$ khớp với bất kỳ chữ cái nào, nên việc quét mọi từ khá bất tiện. Các từ chỉ dùng chữ cái viết thường, phù hợp với một trie.
>
> Thao tác thêm duyệt theo một đường đi gồm $26$ nhánh. Thao tác tìm kiếm đi theo một cạnh cố định khi gặp chữ cái và rẽ nhánh qua mọi nút con không rỗng khi gặp $.$; một kết quả khớp phải kết thúc tại một nút biểu diễn cuối từ.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class WordDictionary:
    def __init__(self):
        self.trie = Trie()

    def addWord(self, word: str) -> None:
        node = self.trie
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word: str) -> bool:
        def search(word, node):
            for i in range(len(word)):
                c = word[i]
                idx = ord(c) - ord('a')
                if c != '.' and node.children[idx] is None:
                    return False
                if c == '.':
                    for child in node.children:
                        if child is not None and search(word[i + 1 :], child):
                            return True
                    return False
                node = node.children[idx]
            return node.is_end

        return search(word, self.trie)


# Đối tượng WordDictionary của bạn sẽ được khởi tạo và gọi như sau:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;
}

class WordDictionary {
    private Trie trie;

    /** Khởi tạo cấu trúc dữ liệu của bạn tại đây. */
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        Trie node = trie;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, trie);
    }

    private boolean search(String word, Trie node) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (c != '.' && node.children[idx] == null) {
                return false;
            }
            if (c == '.') {
                for (Trie child : node.children) {
                    if (child != null && search(word.substring(i + 1), child)) {
                        return true;
                    }
                }
                return false;
            }
            node = node.children[idx];
        }
        return node.isEnd;
    }
}

/**
 * Đối tượng WordDictionary của bạn sẽ được khởi tạo và gọi như sau:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```

#### C++

```cpp
class trie {
public:
    vector<trie*> children;
    bool is_end;

    trie() {
        children = vector<trie*>(26, nullptr);
        is_end = false;
    }

    void insert(const string& word) {
        trie* cur = this;
        for (char c : word) {
            c -= 'a';
            if (cur->children[c] == nullptr) {
                cur->children[c] = new trie;
            }
            cur = cur->children[c];
        }
        cur->is_end = true;
    }
};

class WordDictionary {
private:
    trie* root;

public:
    WordDictionary()
        : root(new trie) {}

    void addWord(string word) {
        root->insert(word);
    }

    bool search(string word) {
        return dfs(word, 0, root);
    }

private:
    bool dfs(const string& word, int i, trie* cur) {
        if (i == word.size()) {
            return cur->is_end;
        }
        char c = word[i];
        if (c != '.') {
            trie* child = cur->children[c - 'a'];
            if (child != nullptr && dfs(word, i + 1, child)) {
                return true;
            }
        } else {
            for (trie* child : cur->children) {
                if (child != nullptr && dfs(word, i + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
};

/**
 * Đối tượng WordDictionary của bạn sẽ được khởi tạo và gọi như sau:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */
```

#### Go

```go
type WordDictionary struct {
	root *trie
}

func Constructor() WordDictionary {
	return WordDictionary{new(trie)}
}

func (this *WordDictionary) AddWord(word string) {
	this.root.insert(word)
}

func (this *WordDictionary) Search(word string) bool {
	n := len(word)

	var dfs func(int, *trie) bool
	dfs = func(i int, cur *trie) bool {
		if i == n {
			return cur.isEnd
		}
		c := word[i]
		if c != '.' {
			child := cur.children[c-'a']
			if child != nil && dfs(i+1, child) {
				return true
			}
		} else {
			for _, child := range cur.children {
				if child != nil && dfs(i+1, child) {
					return true
				}
			}
		}
		return false
	}

	return dfs(0, this.root)
}

type trie struct {
	children [26]*trie
	isEnd    bool
}

func (t *trie) insert(word string) {
	cur := t
	for _, c := range word {
		c -= 'a'
		if cur.children[c] == nil {
			cur.children[c] = new(trie)
		}
		cur = cur.children[c]
	}
	cur.isEnd = true
}

/**
 * Đối tượng WordDictionary của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor();
 * obj.AddWord(word);
 * param_2 := obj.Search(word);
 */
```

#### C#

```cs
class TrieNode {
    public bool IsEnd { get; set; }
    public TrieNode[] Children { get; set; }
    public TrieNode() {
        Children = new TrieNode[26];
    }
}

public class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void AddWord(string word) {
        var node = root;
        for (var i = 0; i < word.Length; ++i) {
            TrieNode nextNode;
            var index = word[i] - 'a';
            nextNode = node.Children[index];
            if (nextNode == null) {
                nextNode = new TrieNode();
                node.Children[index] = nextNode;
            }
            node = nextNode;
        }
        node.IsEnd = true;
    }

    public bool Search(string word) {
        var queue = new Queue<TrieNode>();
        queue.Enqueue(root);
        for (var i = 0; i < word.Length; ++i) {
            var count = queue.Count;
            while (count-- > 0) {
                var node = queue.Dequeue();
                if (word[i] == '.') {
                    foreach (var nextNode in node.Children) {
                        if (nextNode != null) {
                            queue.Enqueue(nextNode);
                        }
                    }
                }
                else {
                    var nextNode = node.Children[word[i] - 'a'];
                    if (nextNode != null) {
                        queue.Enqueue(nextNode);
                    }
                }
            }
        }
        return queue.Any(n => n.IsEnd);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
