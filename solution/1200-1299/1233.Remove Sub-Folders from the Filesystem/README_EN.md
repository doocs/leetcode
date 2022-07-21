# [1233. Remove Sub-Folders from the Filesystem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem)

[中文文档](/solution/1200-1299/1233.Remove%20Sub-Folders%20from%20the%20Filesystem/README.md)

## Description

<p>Given a list of folders <code>folder</code>, return <em>the folders after removing all <strong>sub-folders</strong> in those folders</em>. You may return the answer in <strong>any order</strong>.</p>

<p>If a <code>folder[i]</code> is located within another <code>folder[j]</code>, it is called a <strong>sub-folder</strong> of it.</p>

<p>The format of a path is one or more concatenated strings of the form: <code>&#39;/&#39;</code> followed by one or more lowercase English letters.</p>

<ul>
	<li>For example, <code>&quot;/leetcode&quot;</code> and <code>&quot;/leetcode/problems&quot;</code> are valid paths while an empty string and <code>&quot;/&quot;</code> are not.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b&quot;,&quot;/c/d&quot;,&quot;/c/d/e&quot;,&quot;/c/f&quot;]
<strong>Output:</strong> [&quot;/a&quot;,&quot;/c/d&quot;,&quot;/c/f&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b&quot; is a subfolder of &quot;/a&quot; and &quot;/c/d/e&quot; is inside of folder &quot;/c/d&quot; in our filesystem.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b/c&quot;,&quot;/a/b/d&quot;]
<strong>Output:</strong> [&quot;/a&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b/c&quot; and &quot;/a/b/d&quot; will be removed because they are subfolders of &quot;/a&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a/b/c&quot;,&quot;/a/b/ca&quot;,&quot;/a/b/d&quot;]
<strong>Output:</strong> [&quot;/a/b/c&quot;,&quot;/a/b/ca&quot;,&quot;/a/b/d&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= folder.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= folder[i].length &lt;= 100</code></li>
	<li><code>folder[i]</code> contains only lowercase letters and <code>&#39;/&#39;</code>.</li>
	<li><code>folder[i]</code> always starts with the character <code>&#39;/&#39;</code>.</li>
	<li>Each folder name is <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = {}
        self.is_end = False

    def insert(self, w):
        node = self
        ps = w.split('/')
        for p in ps[1:]:
            if p not in node.children:
                node.children[p] = Trie()
            node = node.children[p]
        node.is_end = True

    def search(self, w):
        node = self
        ps = w.split('/')
        for p in ps[1:]:
            if p not in node.children:
                return False
            node = node.children[p]
            if node.is_end:
                return True
        return False


class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        trie = Trie()
        folder.sort(key=lambda x: len(x.split('/')))
        ans = []
        for v in folder:
            if not trie.search(v):
                trie.insert(v)
                ans.append(v)
        return ans
```

### **Java**

```java
class Trie {
    Map<String, Trie> children = new HashMap<>();
    boolean isEnd;

    void insert(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.isEnd = true;
    }

    boolean search(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
            if (node.isEnd) {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, (a, b) -> a.split("/").length - b.split("/").length);
        Trie trie = new Trie();
        List<String> ans = new ArrayList<>();
        for (String v : folder) {
            if (!trie.search(v)) {
                trie.insert(v);
                ans.add(v);
            }
        }
        return ans;
    }
}
```

### **Go**

```go
type Trie struct {
	children map[string]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(w string) {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node, _ = node.children[p]
	}
	node.isEnd = true
}

func (this *Trie) search(w string) bool {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node, _ = node.children[p]
		if node.isEnd {
			return true
		}
	}
	return false
}

func removeSubfolders(folder []string) []string {
	sort.Slice(folder, func(i, j int) bool {
		return len(strings.Split(folder[i], "/")) < len(strings.Split(folder[j], "/"))
	})
	trie := newTrie()
	var ans []string
	for _, v := range folder {
		if !trie.search(v) {
			trie.insert(v)
			ans = append(ans, v)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
