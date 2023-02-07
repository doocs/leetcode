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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b&quot;,&quot;/c/d&quot;,&quot;/c/d/e&quot;,&quot;/c/f&quot;]
<strong>Output:</strong> [&quot;/a&quot;,&quot;/c/d&quot;,&quot;/c/f&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b&quot; is a subfolder of &quot;/a&quot; and &quot;/c/d/e&quot; is inside of folder &quot;/c/d&quot; in our filesystem.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b/c&quot;,&quot;/a/b/d&quot;]
<strong>Output:</strong> [&quot;/a&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b/c&quot; and &quot;/a/b/d&quot; will be removed because they are subfolders of &quot;/a&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        folder.sort()
        ans = [folder[0]]
        for f in folder[1:]:
            m, n = len(ans[-1]), len(f)
            if m >= n or not (ans[-1] == f[:m] and f[m] == '/'):
                ans.append(f)
        return ans
```

```python
class Trie:
    def __init__(self):
        self.children = {}
        self.fid = -1

    def insert(self, i, f):
        node = self
        ps = f.split('/')
        for p in ps[1:]:
            if p not in node.children:
                node.children[p] = Trie()
            node = node.children[p]
        node.fid = i

    def search(self):
        def dfs(root):
            if root.fid != -1:
                ans.append(root.fid)
                return
            for child in root.children.values():
                dfs(child)

        ans = []
        dfs(self)
        return ans

class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        trie = Trie()
        for i, f in enumerate(folder):
            trie.insert(i, f)
        return [folder[i] for i in trie.search()]
```

### **Java**

```java
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; ++i) {
            int m = ans.get(ans.size() - 1).length();
            int n = folder[i].length();
            if (m >= n || !(ans.get(ans.size() - 1).equals(folder[i].substring(0, m)) && folder[i].charAt(m) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }
}
```

```java
class Trie {
    private Map<String, Trie> children = new HashMap<>();
    private int fid = -1;

    public void insert(int fid, String f) {
        Trie node = this;
        String[] ps = f.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.fid = fid;
    }

    public List<Integer> search() {
        List<Integer> ans = new ArrayList<>();
        dfs(this, ans);
        return ans;
    }

    private void dfs(Trie root, List<Integer> ans) {
        if (root.fid != -1) {
            ans.add(root.fid);
            return;
        }
        for (var child : root.children.values()) {
            dfs(child, ans);
        }
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for (int i = 0; i < folder.length; ++i) {
            trie.insert(i, folder[i]);
        }
        List<String> ans = new ArrayList<>();
        for (int i : trie.search()) {
            ans.add(folder[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> removeSubfolders(vector<string>& folder) {
        sort(folder.begin(), folder.end());
        vector<string> ans = {folder[0]};
        for (int i = 1; i < folder.size(); ++i) {
            int m = ans.back().size();
            int n = folder[i].size();
            if (m >= n || !(ans.back() == folder[i].substr(0, m) && folder[i][m] == '/')) {
                ans.emplace_back(folder[i]);
            }
        }
        return ans;
    }
};
```

```cpp
class Trie {
public:
    void insert(int fid, string& f) {
        Trie* node = this;
        vector<string> ps = split(f, '/');
        for (int i = 1; i < ps.size(); ++i) {
            auto& p = ps[i];
            if (!node->children.count(p)) {
                node->children[p] = new Trie();
            }
            node = node->children[p];
        }
        node->fid = fid;
    }

    vector<int> search() {
        vector<int> ans;
        function<void(Trie*)> dfs = [&](Trie* root) {
            if (root->fid != -1) {
                ans.push_back(root->fid);
                return;
            }
            for (auto& [_, child] : root->children) {
                dfs(child);
            }
        };
        dfs(this);
        return ans;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }

private:
    unordered_map<string, Trie*> children;
    int fid = -1;
};

class Solution {
public:
    vector<string> removeSubfolders(vector<string>& folder) {
        Trie* trie = new Trie();
        for (int i = 0; i < folder.size(); ++i) {
            trie->insert(i, folder[i]);
        }
        vector<string> ans;
        for (int i : trie->search()) {
            ans.emplace_back(folder[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func removeSubfolders(folder []string) []string {
	sort.Strings(folder)
	ans := []string{folder[0]}
	for _, f := range folder[1:] {
		m, n := len(ans[len(ans)-1]), len(f)
		if m >= n || !(ans[len(ans)-1] == f[:m] && f[m] == '/') {
			ans = append(ans, f)
		}
	}
	return ans
}
```

```go
type Trie struct {
	children map[string]*Trie
	fid      int
}

func newTrie() *Trie {
	return &Trie{map[string]*Trie{}, -1}
}

func (this *Trie) insert(fid int, f string) {
	node := this
	ps := strings.Split(f, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node = node.children[p]
	}
	node.fid = fid
}

func (this *Trie) search() (ans []int) {
	var dfs func(*Trie)
	dfs = func(root *Trie) {
		if root.fid != -1 {
			ans = append(ans, root.fid)
			return
		}
		for _, child := range root.children {
			dfs(child)
		}
	}
	dfs(this)
	return
}

func removeSubfolders(folder []string) (ans []string) {
	trie := newTrie()
	for i, f := range folder {
		trie.insert(i, f)
	}
	for _, i := range trie.search() {
		ans = append(ans, folder[i])
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
