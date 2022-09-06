# [588. 设计内存文件系统](https://leetcode.cn/problems/design-in-memory-file-system)

[English Version](/solution/0500-0599/0588.Design%20In-Memory%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个内存文件系统，模拟以下功能：</p>

<p>实现文件系统类:</p>

<ul>
	<li><code>FileSystem()</code>&nbsp;初始化系统对象</li>
	<li><code>List&lt;String&gt; ls(String path)</code>
	<ul>
		<li>如果 <code>path</code> 是一个文件路径，则返回一个仅包含该文件名称的列表。</li>
		<li>如果 <code>path</code> 是一个目录路径，则返回该目录中文件和 <strong>目录名</strong> 的列表。</li>
	</ul>
	</li>
</ul>

<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 答案应该 按<strong>字典顺序</strong> 排列。</p>

<ul>
	<li><code>void mkdir(String path)</code>&nbsp;根据给定的路径创建一个新目录。给定的目录路径不存在。如果路径中的中间目录不存在，您也应该创建它们。</li>
	<li><code>void addContentToFile(String filePath, String content)</code>
	<ul>
		<li>如果 <code>filePath</code> 不存在，则创建包含给定内容&nbsp;<code>content</code>的文件。</li>
		<li>如果 <code>filePath</code> 已经存在，将给定的内容&nbsp;<code>content</code>附加到原始内容。</li>
	</ul>
	</li>
	<li><code>String readContentFromFile(String filePath)</code>&nbsp;返回 <code>filePath</code>下的文件内容。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0588.Design%20In-Memory%20File%20System/images/filesystem.png" style="height: 315px; width: 650px;" /></p>

<pre>
<strong>输入:</strong> 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
<strong>输出:</strong>
[null,[],null,null,["a"],"hello"]

<strong>解释:</strong>
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // 返回 []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // 返回 ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // 返回 "hello"</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>1 &lt;= path.length,&nbsp;filePath.length &lt;= 100</code></li>
	<li><code>path</code>&nbsp;和&nbsp;<code>filePath</code>&nbsp;都是绝对路径，除非是根目录&nbsp;<code>‘/’</code>&nbsp;自身，其他路径都是以&nbsp;<code>‘/’</code>&nbsp;开头且 <strong>不</strong> 以&nbsp;<code>‘/’</code>&nbsp;结束。</li>
	<li>你可以假定所有操作的参数都是有效的，即用户不会获取不存在文件的内容，或者获取不存在文件夹和文件的列表。</li>
	<li>你可以假定所有文件夹名字和文件名字都只包含小写字母，且同一文件夹下不会有相同名字的文件夹或文件。</li>
	<li><code>1 &lt;= content.length &lt;= 50</code></li>
	<li><code>ls</code>,&nbsp;<code>mkdir</code>,&nbsp;<code>addContentToFile</code>, and&nbsp;<code>readContentFromFile</code>&nbsp;最多被调用&nbsp;<code>300</code>&nbsp;次</li>
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
        self.name = None
        self.isFile = False
        self.content = []
        self.children = {}

    def insert(self, path, isFile):
        node = self
        ps = path.split('/')
        for p in ps[1:]:
            if p not in node.children:
                node.children[p] = Trie()
            node = node.children[p]
        node.isFile = isFile
        if isFile:
            node.name = ps[-1]
        return node

    def search(self, path):
        node = self
        if path == '/':
            return node
        ps = path.split('/')
        for p in ps[1:]:
            if p not in node.children:
                return None
            node = node.children[p]
        return node


class FileSystem:
    def __init__(self):
        self.root = Trie()

    def ls(self, path: str) -> List[str]:
        node = self.root.search(path)
        if node is None:
            return []
        if node.isFile:
            return [node.name]
        return sorted(node.children.keys())

    def mkdir(self, path: str) -> None:
        self.root.insert(path, False)

    def addContentToFile(self, filePath: str, content: str) -> None:
        node = self.root.insert(filePath, True)
        node.content.append(content)

    def readContentFromFile(self, filePath: str) -> str:
        node = self.root.search(filePath)
        return ''.join(node.content)


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.ls(path)
# obj.mkdir(path)
# obj.addContentToFile(filePath,content)
# param_4 = obj.readContentFromFile(filePath)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    String name;
    boolean isFile;
    StringBuilder content = new StringBuilder();
    Map<String, Trie> children = new HashMap<>();

    Trie insert(String path, boolean isFile) {
        Trie node = this;
        String[] ps = path.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.isFile = isFile;
        if (isFile) {
            node.name = ps[ps.length - 1];
        }
        return node;
    }

    Trie search(String path) {
        Trie node = this;
        String[] ps = path.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return null;
            }
            node = node.children.get(p);
        }
        return node;
    }
}

class FileSystem {
    private Trie root = new Trie();

    public FileSystem() {
    }

    public List<String> ls(String path) {
        List<String> ans = new ArrayList<>();
        Trie node = root.search(path);
        if (node == null) {
            return ans;
        }
        if (node.isFile) {
            ans.add(node.name);
            return ans;
        }
        for (String v : node.children.keySet()) {
            ans.add(v);
        }
        Collections.sort(ans);
        return ans;
    }

    public void mkdir(String path) {
        root.insert(path, false);
    }

    public void addContentToFile(String filePath, String content) {
        Trie node = root.insert(filePath, true);
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        Trie node = root.search(filePath);
        return node.content.toString();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
```

### **Go**

```go
type Trie struct {
	name     string
	isFile   bool
	content  strings.Builder
	children map[string]*Trie
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(path string, isFile bool) *Trie {
	node := this
	ps := strings.Split(path, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node, _ = node.children[p]
	}
	node.isFile = isFile
	if isFile {
		node.name = ps[len(ps)-1]
	}
	return node
}

func (this *Trie) search(path string) *Trie {
	if path == "/" {
		return this
	}
	node := this
	ps := strings.Split(path, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			return nil
		}
		node, _ = node.children[p]
	}
	return node
}

type FileSystem struct {
	root *Trie
}

func Constructor() FileSystem {
	root := newTrie()
	return FileSystem{root}
}

func (this *FileSystem) Ls(path string) []string {
	var ans []string
	node := this.root.search(path)
	if node == nil {
		return ans
	}
	if node.isFile {
		ans = append(ans, node.name)
		return ans
	}
	for v := range node.children {
		ans = append(ans, v)
	}
	sort.Strings(ans)
	return ans
}

func (this *FileSystem) Mkdir(path string) {
	this.root.insert(path, false)
}

func (this *FileSystem) AddContentToFile(filePath string, content string) {
	node := this.root.insert(filePath, true)
	node.content.WriteString(content)
}

func (this *FileSystem) ReadContentFromFile(filePath string) string {
	node := this.root.search(filePath)
	return node.content.String()
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Ls(path);
 * obj.Mkdir(path);
 * obj.AddContentToFile(filePath,content);
 * param_4 := obj.ReadContentFromFile(filePath);
 */
```

### **...**

```

```

<!-- tabs:end -->
