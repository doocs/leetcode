# [588. Design In-Memory File System](https://leetcode.com/problems/design-in-memory-file-system)

[中文文档](/solution/0500-0599/0588.Design%20In-Memory%20File%20System/README.md)

## Description

<p>Design a data structure that simulates an in-memory file system.</p>

<p>Implement the FileSystem class:</p>

<ul>
	<li><code>FileSystem()</code> Initializes the object of the system.</li>
	<li><code>List&lt;String&gt; ls(String path)</code>
	<ul>
		<li>If <code>path</code> is a file path, returns a list that only contains this file&#39;s name.</li>
		<li>If <code>path</code> is a directory path, returns the list of file and directory names <strong>in this directory</strong>.</li>
	</ul>
	The answer should in <strong>lexicographic order</strong>.</li>
	<li><code>void mkdir(String path)</code> Makes a new directory according to the given <code>path</code>. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.</li>
	<li><code>void addContentToFile(String filePath, String content)</code>
	<ul>
		<li>If <code>filePath</code> does not exist, creates that file containing given <code>content</code>.</li>
		<li>If <code>filePath</code> already exists, appends the given <code>content</code> to original content.</li>
	</ul>
	</li>
	<li><code>String readContentFromFile(String filePath)</code> Returns the content in the file at <code>filePath</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0588.Design%20In-Memory%20File%20System/images/filesystem.png" style="width: 650px; height: 315px;" />
<pre>
<strong>Input</strong>
[&quot;FileSystem&quot;, &quot;ls&quot;, &quot;mkdir&quot;, &quot;addContentToFile&quot;, &quot;ls&quot;, &quot;readContentFromFile&quot;]
[[], [&quot;/&quot;], [&quot;/a/b/c&quot;], [&quot;/a/b/c/d&quot;, &quot;hello&quot;], [&quot;/&quot;], [&quot;/a/b/c/d&quot;]]
<strong>Output</strong>
[null, [], null, null, [&quot;a&quot;], &quot;hello&quot;]

<strong>Explanation</strong>
FileSystem fileSystem = new FileSystem();
fileSystem.ls(&quot;/&quot;); // return []
fileSystem.mkdir(&quot;/a/b/c&quot;);
fileSystem.addContentToFile(&quot;/a/b/c/d&quot;, &quot;hello&quot;);
fileSystem.ls(&quot;/&quot;); // return [&quot;a&quot;]
fileSystem.readContentFromFile(&quot;/a/b/c/d&quot;); // return &quot;hello&quot;

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= path.length,&nbsp;filePath.length &lt;= 100</code></li>
	<li><code>path</code> and <code>filePath</code>&nbsp;are absolute paths which begin with <code>&#39;/&#39;</code>&nbsp;and do not end with <code>&#39;/&#39;</code>&nbsp;except that the path is just&nbsp;<code>&quot;/&quot;</code>.</li>
	<li>You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.</li>
	<li>You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.</li>
	<li><code>1 &lt;= content.length &lt;= 50</code></li>
	<li>At most <code>300</code> calls will be made to <code>ls</code>, <code>mkdir</code>,&nbsp;<code>addContentToFile</code>, and&nbsp;<code>readContentFromFile</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
