# [1166. 设计文件系统](https://leetcode.cn/problems/design-file-system)

[English Version](/solution/1100-1199/1166.Design%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要设计一个文件系统，你可以创建新的路径并将它们与不同的值关联。</p>

<p>路径的格式是一个或多个连接在一起的字符串，形式为：&nbsp;<code>/</code> ，后面跟着一个或多个小写英文字母。例如， <code>" /leetcode"</code> 和 <code>"/leetcode/problems"</code> 是有效路径，而空字符串 <code>""</code> 和 <code>"/"</code> 不是。</p>

<p>实现 <code>FileSystem</code> 类:</p>

<ul>
	<li><meta charset="UTF-8" /><code>bool createPath(string path, int value)</code>&nbsp;创建一个新的&nbsp;<code>path</code> ，并在可能的情况下关联一个 <code>value</code> ，然后返回 <code>true</code> 。如果路径<strong>已经存在</strong>或其父路径<strong>不存在</strong>，则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li>&nbsp;<code>int get(string path)</code> 返回与 <code>path</code> 关联的值，如果路径不存在则返回 <code>-1</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
["FileSystem","create","get"]
[[],["/a",1],["/a"]]
<strong>输出：</strong> 
[null,true,1]
<strong>解释：</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.create("/a", 1); // 返回 true
fileSystem.get("/a"); // 返回 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
<strong>输出：</strong> 
[null,true,true,2,false,-1]
<strong>解释：</strong>
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // 返回 true
fileSystem.createPath("/leet/code", 2); // 返回 true
fileSystem.get("/leet/code"); // 返回 2
fileSystem.createPath("/c/d", 1); // 返回 false 因为父路径 "/c" 不存在。
fileSystem.get("/c"); // 返回 -1 因为该路径不存在。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>对两个函数的调用次数加起来小于等于&nbsp;<meta charset="UTF-8" /><code>10<sup>4</sup></code>&nbsp;</li>
	<li><code>2 &lt;= path.length &lt;= 100</code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

哈希表实现前缀树。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children = {}
        self.v = 0

    def insert(self, w, v):
        node = self
        ps = w.split('/')
        for p in ps[1:-1]:
            if p not in node.children:
                return False
            node = node.children[p]
        if ps[-1] in node.children:
            return False
        node.children[ps[-1]] = Trie()
        node = node.children[ps[-1]]
        node.v = v
        return True

    def search(self, w):
        node = self
        for p in w.split('/')[1:]:
            if p not in node.children:
                return -1
            node = node.children[p]
        return node.v or -1


class FileSystem:
    def __init__(self):
        self.trie = Trie()

    def createPath(self, path: str, value: int) -> bool:
        return self.trie.insert(path, value)

    def get(self, path: str) -> int:
        return self.trie.search(path)


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.createPath(path,value)
# param_2 = obj.get(path)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Map<String, Trie> children = new HashMap<>();
    int v;

    boolean insert(String w, int v) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length - 1; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
        }
        if (node.children.containsKey(ps[ps.length - 1])) {
            return false;
        }
        node.children.put(ps[ps.length - 1], new Trie());
        node = node.children.get(ps[ps.length - 1]);
        node.v = v;
        return true;
    }

    int search(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return -1;
            }
            node = node.children.get(p);
        }
        return node.v == 0 ? -1 : node.v;
    }
}

class FileSystem {
    private Trie trie = new Trie();

    public FileSystem() {
    }

    public boolean createPath(String path, int value) {
        return trie.insert(path, value);
    }

    public int get(String path) {
        return trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
```

### **Go**

```go
type Trie struct {
	children map[string]*Trie
	v        int
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(w string, v int) bool {
	node := this
	ps := strings.Split(w, "/")
	for _, p := range ps[1 : len(ps)-1] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node, _ = node.children[p]
	}
	x := ps[len(ps)-1]
	if _, ok := node.children[x]; ok {
		return false
	}
	node.children[x] = newTrie()
	node, _ = node.children[x]
	node.v = v
	return true
}

func (this *Trie) search(w string) int {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			return -1
		}
		node, _ = node.children[p]
	}
	if node.v == 0 {
		return -1
	}
	return node.v
}

type FileSystem struct {
	trie *Trie
}

func Constructor() FileSystem {
	return FileSystem{newTrie()}
}

func (this *FileSystem) CreatePath(path string, value int) bool {
	return this.trie.insert(path, value)
}

func (this *FileSystem) Get(path string) int {
	return this.trie.search(path)
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.CreatePath(path,value);
 * param_2 := obj.Get(path);
 */
```

### **...**

```

```

<!-- tabs:end -->
