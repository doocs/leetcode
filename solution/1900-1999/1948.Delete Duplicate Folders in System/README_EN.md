---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/README_EN.md
rating: 2533
source: Weekly Contest 251 Q4
tags:
    - Trie
    - Array
    - Hash Table
    - String
    - Hash Function
---

<!-- problem:start -->

# [1948. Delete Duplicate Folders in System](https://leetcode.com/problems/delete-duplicate-folders-in-system)

[中文文档](/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/README.md)

## Description

<!-- description:start -->

<p>Due to a bug, there are many duplicate folders in a file system. You are given a 2D array <code>paths</code>, where <code>paths[i]</code> is an array representing an absolute path to the <code>i<sup>th</sup></code> folder in the file system.</p>

<ul>
	<li>For example, <code>[&quot;one&quot;, &quot;two&quot;, &quot;three&quot;]</code> represents the path <code>&quot;/one/two/three&quot;</code>.</li>
</ul>

<p>Two folders (not necessarily on the same level) are <strong>identical</strong> if they contain the <strong>same non-empty</strong> set of identical subfolders and underlying subfolder structure. The folders <strong>do not</strong> need to be at the root level to be identical. If two or more folders are <strong>identical</strong>, then <strong>mark</strong> the folders as well as all their subfolders.</p>

<ul>
	<li>For example, folders <code>&quot;/a&quot;</code> and <code>&quot;/b&quot;</code> in the file structure below are identical. They (as well as their subfolders) should <strong>all</strong> be marked:

    <ul>
    	<li><code>/a</code></li>
    	<li><code>/a/x</code></li>
    	<li><code>/a/x/y</code></li>
    	<li><code>/a/z</code></li>
    	<li><code>/b</code></li>
    	<li><code>/b/x</code></li>
    	<li><code>/b/x/y</code></li>
    	<li><code>/b/z</code></li>
    </ul>
    </li>
    <li>However, if the file structure also included the path <code>&quot;/b/w&quot;</code>, then the folders <code>&quot;/a&quot;</code> and <code>&quot;/b&quot;</code> would not be identical. Note that <code>&quot;/a/x&quot;</code> and <code>&quot;/b/x&quot;</code> would still be considered identical even with the added folder.</li>

</ul>

<p>Once all the identical folders and their subfolders have been marked, the file system will <strong>delete</strong> all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.</p>

<p>Return <em>the 2D array </em><code>ans</code> <em>containing the paths of the <strong>remaining</strong> folders after deleting all the marked folders. The paths may be returned in <strong>any</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder1.jpg" style="width: 200px; height: 218px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;],[&quot;c&quot;],[&quot;d&quot;],[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;d&quot;,&quot;a&quot;]]
<strong>Output:</strong> [[&quot;d&quot;],[&quot;d&quot;,&quot;a&quot;]]
<strong>Explanation:</strong> The file structure is as shown.
Folders &quot;/a&quot; and &quot;/c&quot; (and their subfolders) are marked for deletion because they both contain an empty
folder named &quot;b&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder2.jpg" style="width: 200px; height: 355px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;],[&quot;c&quot;],[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;x&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;x&quot;,&quot;y&quot;],[&quot;w&quot;],[&quot;w&quot;,&quot;y&quot;]]
<strong>Output:</strong> [[&quot;c&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;]]
<strong>Explanation: </strong>The file structure is as shown. 
Folders &quot;/a/b/x&quot; and &quot;/w&quot; (and their subfolders) are marked for deletion because they both contain an empty folder named &quot;y&quot;.
Note that folders &quot;/a&quot; and &quot;/c&quot; are identical after the deletion, but they are not deleted because they were not marked beforehand.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder3.jpg" style="width: 200px; height: 201px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;c&quot;],[&quot;a&quot;]]
<strong>Output:</strong> [[&quot;c&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;]]
<strong>Explanation:</strong> All folders are unique in the file system.
Note that the returned array can be in a different order as the order does not matter.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= paths[i].length &lt;= 500</code></li>
	<li><code>1 &lt;= paths[i][j].length &lt;= 10</code></li>
	<li><code>1 &lt;= sum(paths[i][j].length) &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>path[i][j]</code> consists of lowercase English letters.</li>
	<li>No two paths lead to the same folder.</li>
	<li>For any folder not at the root level, its parent folder will also be in the input.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie + DFS

We can use a trie to store the folder structure, where each node in the trie contains the following data:

-   `children`: A dictionary where the key is the name of the subfolder and the value is the corresponding child node.
-   `deleted`: A boolean value indicating whether the node is marked for deletion.

We insert all paths into the trie, then use DFS to traverse the trie and build a string representation for each subtree. For each subtree, if its string representation already exists in a global dictionary, we mark both the current node and the corresponding node in the global dictionary for deletion. Finally, we use DFS again to traverse the trie and add the paths of unmarked nodes to the result list.

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: Dict[str, "Trie"] = defaultdict(Trie)
        self.deleted: bool = False


class Solution:
    def deleteDuplicateFolder(self, paths: List[List[str]]) -> List[List[str]]:
        root = Trie()
        for path in paths:
            cur = root
            for name in path:
                if cur.children[name] is None:
                    cur.children[name] = Trie()
                cur = cur.children[name]

        g: Dict[str, Trie] = {}

        def dfs(node: Trie) -> str:
            if not node.children:
                return ""
            subs: List[str] = []
            for name, child in node.children.items():
                subs.append(f"{name}({dfs(child)})")
            s = "".join(sorted(subs))
            if s in g:
                node.deleted = g[s].deleted = True
            else:
                g[s] = node
            return s

        def dfs2(node: Trie) -> None:
            if node.deleted:
                return
            if path:
                ans.append(path[:])
            for name, child in node.children.items():
                path.append(name)
                dfs2(child)
                path.pop()

        dfs(root)
        ans: List[List[str]] = []
        path: List[str] = []
        dfs2(root)
        return ans
```

#### Java

```java
class Trie {
    Map<String, Trie> children;
    boolean deleted;

    public Trie() {
        children = new HashMap<>();
        deleted = false;
    }
}

class Solution {
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie();
        for (List<String> path : paths) {
            Trie cur = root;
            for (String name : path) {
                if (!cur.children.containsKey(name)) {
                    cur.children.put(name, new Trie());
                }
                cur = cur.children.get(name);
            }
        }

        Map<String, Trie> g = new HashMap<>();

        var dfs = new Function<Trie, String>() {
            @Override
            public String apply(Trie node) {
                if (node.children.isEmpty()) {
                    return "";
                }
                List<String> subs = new ArrayList<>();
                for (var entry : node.children.entrySet()) {
                    subs.add(entry.getKey() + "(" + apply(entry.getValue()) + ")");
                }
                Collections.sort(subs);
                String s = String.join("", subs);
                if (g.containsKey(s)) {
                    node.deleted = true;
                    g.get(s).deleted = true;
                } else {
                    g.put(s, node);
                }
                return s;
            }
        };

        dfs.apply(root);

        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();

        var dfs2 = new Function<Trie, Void>() {
            @Override
            public Void apply(Trie node) {
                if (node.deleted) {
                    return null;
                }
                if (!path.isEmpty()) {
                    ans.add(new ArrayList<>(path));
                }
                for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
                    path.add(entry.getKey());
                    apply(entry.getValue());
                    path.remove(path.size() - 1);
                }
                return null;
            }
        };

        dfs2.apply(root);

        return ans;
    }
}
```

#### C++

```cpp
class Trie {
public:
    unordered_map<string, Trie*> children;
    bool deleted = false;
};

class Solution {
public:
    vector<vector<string>> deleteDuplicateFolder(vector<vector<string>>& paths) {
        Trie* root = new Trie();

        for (auto& path : paths) {
            Trie* cur = root;
            for (auto& name : path) {
                if (cur->children.find(name) == cur->children.end()) {
                    cur->children[name] = new Trie();
                }
                cur = cur->children[name];
            }
        }

        unordered_map<string, Trie*> g;

        auto dfs = [&](this auto&& dfs, Trie* node) -> string {
            if (node->children.empty()) return "";

            vector<string> subs;
            for (auto& child : node->children) {
                subs.push_back(child.first + "(" + dfs(child.second) + ")");
            }
            sort(subs.begin(), subs.end());
            string s = "";
            for (auto& sub : subs) s += sub;

            if (g.contains(s)) {
                node->deleted = true;
                g[s]->deleted = true;
            } else {
                g[s] = node;
            }
            return s;
        };

        dfs(root);

        vector<vector<string>> ans;
        vector<string> path;

        auto dfs2 = [&](this auto&& dfs2, Trie* node) -> void {
            if (node->deleted) return;
            if (!path.empty()) {
                ans.push_back(path);
            }
            for (auto& child : node->children) {
                path.push_back(child.first);
                dfs2(child.second);
                path.pop_back();
            }
        };

        dfs2(root);

        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children map[string]*Trie
	deleted  bool
}

func NewTrie() *Trie {
	return &Trie{
		children: make(map[string]*Trie),
	}
}

func deleteDuplicateFolder(paths [][]string) (ans [][]string) {
	root := NewTrie()
	for _, path := range paths {
		cur := root
		for _, name := range path {
			if _, exists := cur.children[name]; !exists {
				cur.children[name] = NewTrie()
			}
			cur = cur.children[name]
		}
	}

	g := make(map[string]*Trie)

	var dfs func(*Trie) string
	dfs = func(node *Trie) string {
		if len(node.children) == 0 {
			return ""
		}
		var subs []string
		for name, child := range node.children {
			subs = append(subs, name+"("+dfs(child)+")")
		}
		sort.Strings(subs)
		s := strings.Join(subs, "")
		if existingNode, exists := g[s]; exists {
			node.deleted = true
			existingNode.deleted = true
		} else {
			g[s] = node
		}
		return s
	}

	var dfs2 func(*Trie, []string)
	dfs2 = func(node *Trie, path []string) {
		if node.deleted {
			return
		}
		if len(path) > 0 {
			ans = append(ans, append([]string{}, path...))
		}
		for name, child := range node.children {
			dfs2(child, append(path, name))
		}
	}

	dfs(root)
	dfs2(root, []string{})
	return ans
}
```

#### TypeScript

```ts
function deleteDuplicateFolder(paths: string[][]): string[][] {
    class Trie {
        children: { [key: string]: Trie } = {};
        deleted: boolean = false;
    }

    const root = new Trie();

    for (const path of paths) {
        let cur = root;
        for (const name of path) {
            if (!cur.children[name]) {
                cur.children[name] = new Trie();
            }
            cur = cur.children[name];
        }
    }

    const g: { [key: string]: Trie } = {};

    const dfs = (node: Trie): string => {
        if (Object.keys(node.children).length === 0) return '';

        const subs: string[] = [];
        for (const [name, child] of Object.entries(node.children)) {
            subs.push(`${name}(${dfs(child)})`);
        }
        subs.sort();
        const s = subs.join('');

        if (g[s]) {
            node.deleted = true;
            g[s].deleted = true;
        } else {
            g[s] = node;
        }
        return s;
    };

    dfs(root);

    const ans: string[][] = [];
    const path: string[] = [];

    const dfs2 = (node: Trie): void => {
        if (node.deleted) return;
        if (path.length > 0) {
            ans.push([...path]);
        }
        for (const [name, child] of Object.entries(node.children)) {
            path.push(name);
            dfs2(child);
            path.pop();
        }
    };

    dfs2(root);

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
