# [1804. 实现 Trie （前缀树） II](https://leetcode.cn/problems/implement-trie-ii-prefix-tree)

[English Version](/solution/1800-1899/1804.Implement%20Trie%20II%20%28Prefix%20Tree%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>前缀树（<strong><a href="https://en.wikipedia.org/wiki/Trie" target="_blank">trie</a></strong> ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。</p>

<p>实现前缀树 Trie 类：</p>

<ul>
	<li><code>Trie()</code> 初始化前缀树对象。</li>
	<li><code>void insert(String word)</code> 将字符串 <code>word</code> 插入前缀树中。</li>
	<li><code>int countWordsEqualTo(String word)</code> 返回前缀树中字符串 <code>word</code> 的实例个数。</li>
	<li><code>int countWordsStartingWith(String prefix)</code> 返回前缀树中以 <code>prefix</code> 为前缀的字符串个数。</li>
	<li><code>void erase(String word)</code> 从前缀树中移除字符串 <code>word</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><b>输入</b>
["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
[[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
<b>输出</b>
[null, null, null, 2, 2, null, 1, 1, null, 0]

<b>解释</b>
Trie trie = new Trie();
trie.insert("apple");               // 插入 "apple"。
trie.insert("apple");               // 插入另一个 "apple"。
trie.countWordsEqualTo("apple");    // 有两个 "apple" 实例，所以返回 2。
trie.countWordsStartingWith("app"); // "app" 是 "apple" 的前缀，所以返回 2。
trie.erase("apple");                // 移除一个 "apple"。
trie.countWordsEqualTo("apple");    // 现在只有一个 "apple" 实例，所以返回 1。
trie.countWordsStartingWith("app"); // 返回 1
trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
trie.countWordsStartingWith("app"); // 返回 0
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> 和 <code>prefix</code> 只包含小写英文字母。</li>
	<li><code>insert</code>、 <code>countWordsEqualTo</code>、 <code>countWordsStartingWith</code> 和 <code>erase</code> <strong>总共</strong>调用最多 <code>3 * 10<sup>4</sup></code> 次。</li>
	<li>保证每次调用 <code>erase</code> 时，字符串 <code>word</code> 总是存在于前缀树中。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组实现前缀树**

前缀树每个节点包括三部分：

1. 指向子节点的指针数组 children，对于本题而言，数组长度为 26，即小写英文字母的数量。`children[0]` 对应小写字母 a，...，`children[25]` 对应小写字母 z。
1. int 变量 `v`，表示以该节点结尾的字符串个数。
1. int 变量 `pv`，表示以该节点作为前缀节点的字符串个数。

### 1. 插入字符串

我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：

-   子节点存在。沿着指针移动到子节点，继续处理下一个字符。
-   子节点不存在。创建一个新的子节点，记录在 `children` 数组的对应位置上，然后沿着指针移动到子节点，让子节点的 `pv` 值加 1。继续搜索下一个字符。

重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点的 `v` 值加 1。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。

### 2. 查找前缀

我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：

-   子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
-   子节点不存在。说明字典树中不包含该前缀，返回空指针。

重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。

### 3. 移除字符串

我们从字典树的根节点开始，依次将对应的子节点的 `pv` 值减 1，直到搜索完字符串的最后一个字符。然后将当前节点的 `v` 值减 1。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = self.pv = 0

    def insert(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.pv += 1
        node.v += 1

    def countWordsEqualTo(self, word: str) -> int:
        node = self.search(word)
        return 0 if node is None else node.v

    def countWordsStartingWith(self, prefix: str) -> int:
        node = self.search(prefix)
        return 0 if node is None else node.pv

    def erase(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            node = node.children[idx]
            node.pv -= 1
        node.v -= 1

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.countWordsEqualTo(word)
# param_3 = obj.countWordsStartingWith(prefix)
# obj.erase(word)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int v;
    private int pv;

    public Trie() {
    }

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            ++node.pv;
        }
        ++node.v;
    }

    public int countWordsEqualTo(String word) {
        Trie node = search(word);
        return node == null ? 0 : node.v;
    }

    public int countWordsStartingWith(String prefix) {
        Trie node = search(prefix);
        return node == null ? 0 : node.pv;
    }

    public void erase(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            node = node.children[c];
            --node.pv;
        }
        --node.v;
    }

    private Trie search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return null;
            }
            node = node.children[c];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
```

### **C++**

```cpp
class Trie {
public:
    Trie()
        : children(26)
        , v(0)
        , pv(0) {
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
            ++node->pv;
        }
        ++node->v;
    }

    int countWordsEqualTo(string word) {
        Trie* node = search(word);
        return node ? node->v : 0;
    }

    int countWordsStartingWith(string prefix) {
        Trie* node = search(prefix);
        return node ? node->pv : 0;
    }

    void erase(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            node = node->children[c];
            --node->pv;
        }
        --node->v;
    }

private:
    vector<Trie*> children;
    int v, pv;

    Trie* search(string& word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                return nullptr;
            }
            node = node->children[c];
        }
        return node;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	v        int
	pv       int
}

func Constructor() (_ Trie) { return }

func (this *Trie) Insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = &Trie{}
		}
		node = node.children[c]
		node.pv++
	}
	node.v++
}

func (this *Trie) CountWordsEqualTo(word string) int {
	node := this.search(word)
	if node == nil {
		return 0
	}
	return node.v
}

func (this *Trie) CountWordsStartingWith(prefix string) int {
	node := this.search(prefix)
	if node == nil {
		return 0
	}
	return node.pv
}

func (this *Trie) Erase(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		node = node.children[c]
		node.pv--
	}
	node.v--
}

func (this *Trie) search(word string) *Trie {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return nil
		}
		node = node.children[c]
	}
	return node
}

/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.CountWordsEqualTo(word);
 * param_3 := obj.CountWordsStartingWith(prefix);
 * obj.Erase(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
