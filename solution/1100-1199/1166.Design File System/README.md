# [1166. 设计文件系统](https://leetcode.cn/problems/design-file-system)

[English Version](/solution/1100-1199/1166.Design%20File%20System/README_EN.md)

<!-- tags:设计,字典树,哈希表,字符串 -->

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

### 方法一：前缀树

我们可以使用前缀树来存储路径，每个节点存储一个值，表示该节点对应的路径的值。

定义前缀树的节点结构如下：

-   `children`：子节点，使用哈希表存储，键为子节点的路径，值为子节点的引用；
-   `v`：当前节点对应的路径的值。

定义前缀树的方法如下：

-   `insert(w, v)`：插入路径 $w$，并将其对应的值设为 $v$。如果路径 $w$ 已经存在或其父路径不存在，则返回 `false`，否则返回 `true`。时间复杂度为 $O(|w|)$，其中 $|w|$ 为路径 $w$ 的长度；
-   `search(w)`：返回路径 $w$ 对应的值。如果路径 $w$ 不存在，则返回 $-1$。时间复杂度为 $O(|w|)$。

总时间复杂度 $O(\sum_{w \in W}|w|)$，总空间复杂度 $O(\sum_{w \in W}|w|)$，其中 $W$ 为所有插入的路径的集合。

<!-- tabs:start -->

```python
class Trie:
    def __init__(self, v: int = -1):
        self.children = {}
        self.v = v

    def insert(self, w: str, v: int) -> bool:
        node = self
        ps = w.split("/")
        for p in ps[1:-1]:
            if p not in node.children:
                return False
            node = node.children[p]
        if ps[-1] in node.children:
            return False
        node.children[ps[-1]] = Trie(v)
        return True

    def search(self, w: str) -> int:
        node = self
        for p in w.split("/")[1:]:
            if p not in node.children:
                return -1
            node = node.children[p]
        return node.v


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

```java
class Trie {
    Map<String, Trie> children = new HashMap<>();
    int v;

    Trie(int v) {
        this.v = v;
    }

    boolean insert(String w, int v) {
        Trie node = this;
        var ps = w.split("/");
        for (int i = 1; i < ps.length - 1; ++i) {
            var p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
        }
        if (node.children.containsKey(ps[ps.length - 1])) {
            return false;
        }
        node.children.put(ps[ps.length - 1], new Trie(v));
        return true;
    }

    int search(String w) {
        Trie node = this;
        var ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            var p = ps[i];
            if (!node.children.containsKey(p)) {
                return -1;
            }
            node = node.children.get(p);
        }
        return node.v;
    }
}

class FileSystem {
    private Trie trie = new Trie(-1);

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

```cpp
class Trie {
public:
    unordered_map<string, Trie*> children;
    int v;

    Trie(int v) {
        this->v = v;
    }

    bool insert(string& w, int v) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size() - 1; ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return false;
            }
            node = node->children[p];
        }
        if (node->children.count(ps.back())) {
            return false;
        }
        node->children[ps.back()] = new Trie(v);
        return true;
    }

    int search(string& w) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size(); ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return -1;
            }
            node = node->children[p];
        }
        return node->v;
    }

private:
    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};

class FileSystem {
public:
    FileSystem() {
        trie = new Trie(-1);
    }

    bool createPath(string path, int value) {
        return trie->insert(path, value);
    }

    int get(string path) {
        return trie->search(path);
    }

private:
    Trie* trie;
};

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem* obj = new FileSystem();
 * bool param_1 = obj->createPath(path,value);
 * int param_2 = obj->get(path);
 */
```

```go
type trie struct {
	children map[string]*trie
	v        int
}

func newTrie(v int) *trie {
	return &trie{map[string]*trie{}, v}
}

func (t *trie) insert(w string, v int) bool {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1 : len(ps)-1] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node = node.children[p]
	}
	if _, ok := node.children[ps[len(ps)-1]]; ok {
		return false
	}
	node.children[ps[len(ps)-1]] = newTrie(v)
	return true
}

func (t *trie) search(w string) int {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			return -1
		}
		node = node.children[p]
	}
	return node.v
}

type FileSystem struct {
	trie *trie
}

func Constructor() FileSystem {
	return FileSystem{trie: newTrie(-1)}
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

```ts
class Trie {
    children: Map<string, Trie>;
    v: number;

    constructor(v: number) {
        this.children = new Map<string, Trie>();
        this.v = v;
    }

    insert(w: string, v: number): boolean {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (let i = 0; i < ps.length - 1; ++i) {
            const p = ps[i];
            if (!node.children.has(p)) {
                return false;
            }
            node = node.children.get(p)!;
        }
        if (node.children.has(ps[ps.length - 1])) {
            return false;
        }
        node.children.set(ps[ps.length - 1], new Trie(v));
        return true;
    }

    search(w: string): number {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (const p of ps) {
            if (!node.children.has(p)) {
                return -1;
            }
            node = node.children.get(p)!;
        }
        return node.v;
    }
}

class FileSystem {
    trie: Trie;

    constructor() {
        this.trie = new Trie(-1);
    }

    createPath(path: string, value: number): boolean {
        return this.trie.insert(path, value);
    }

    get(path: string): number {
        return this.trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * var obj = new FileSystem()
 * var param_1 = obj.createPath(path,value)
 * var param_2 = obj.get(path)
 */
```

<!-- tabs:end -->

<!-- end -->
