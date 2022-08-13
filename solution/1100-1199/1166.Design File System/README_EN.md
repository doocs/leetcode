# [1166. Design File System](https://leetcode.com/problems/design-file-system)

[中文文档](/solution/1100-1199/1166.Design%20File%20System/README.md)

## Description

<p>You are asked to design a file system&nbsp;that allows you to create new paths and associate them with different values.</p>

<p>The format of a path is&nbsp;one or more concatenated strings of the form:&nbsp;<code>/</code> followed by one or more lowercase English letters. For example, &quot;<code>/leetcode&quot;</code>&nbsp;and &quot;<code>/leetcode/problems&quot;</code>&nbsp;are valid paths while an empty&nbsp;string <code>&quot;&quot;</code> and <code>&quot;/&quot;</code>&nbsp;are not.</p>

<p>Implement the&nbsp;<code>FileSystem</code> class:</p>

<ul>
	<li><code>bool createPath(string path, int value)</code>&nbsp;Creates a new <code>path</code> and associates a <code>value</code> to it if possible and returns <code>true</code>.&nbsp;Returns <code>false</code>&nbsp;if the path <strong>already exists</strong> or its parent path <strong>doesn&#39;t exist</strong>.</li>
	<li><code>int get(string path)</code>&nbsp;Returns the value associated with <code>path</code> or returns&nbsp;<code>-1</code>&nbsp;if the path doesn&#39;t exist.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
[&quot;FileSystem&quot;,&quot;createPath&quot;,&quot;get&quot;]
[[],[&quot;/a&quot;,1],[&quot;/a&quot;]]
<strong>Output:</strong> 
[null,true,1]
<strong>Explanation:</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath(&quot;/a&quot;, 1); // return true
fileSystem.get(&quot;/a&quot;); // return 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
[&quot;FileSystem&quot;,&quot;createPath&quot;,&quot;createPath&quot;,&quot;get&quot;,&quot;createPath&quot;,&quot;get&quot;]
[[],[&quot;/leet&quot;,1],[&quot;/leet/code&quot;,2],[&quot;/leet/code&quot;],[&quot;/c/d&quot;,1],[&quot;/c&quot;]]
<strong>Output:</strong> 
[null,true,true,2,false,-1]
<strong>Explanation:</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath(&quot;/leet&quot;, 1); // return true
fileSystem.createPath(&quot;/leet/code&quot;, 2); // return true
fileSystem.get(&quot;/leet/code&quot;); // return 2
fileSystem.createPath(&quot;/c/d&quot;, 1); // return false because the parent path &quot;/c&quot; doesn&#39;t exist.
fileSystem.get(&quot;/c&quot;); // return -1 because this path doesn&#39;t exist.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of&nbsp;calls to the two functions&nbsp;is less than or equal to <code>10<sup>4</sup></code> in total.</li>
	<li><code>2 &lt;= path.length &lt;= 100</code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
