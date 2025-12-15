---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/README.md
rating: 2533
source: 第 251 场周赛 Q4
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
    - 哈希函数
---

<!-- problem:start -->

# [1948. 删除系统中的重复文件夹](https://leetcode.cn/problems/delete-duplicate-folders-in-system)

[English Version](/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/README_EN.md)

## 题目描述

<!-- description:start -->

<p>由于一个漏洞，文件系统中存在许多重复文件夹。给你一个二维数组 <code>paths</code>，其中 <code>paths[i]</code> 是一个表示文件系统中第 <code>i</code> 个文件夹的绝对路径的数组。</p>

<ul>
	<li>例如，<code>["one", "two", "three"]</code> 表示路径 <code>"/one/two/three"</code> 。</li>
</ul>

<p>如果两个文件夹（不需要在同一层级）包含 <strong>非空且</strong><b>相同的&nbsp;</b>子文件夹&nbsp;<strong>集合</strong> 并具有相同的子文件夹结构，则认为这两个文件夹是相同文件夹。相同文件夹的根层级 <strong>不</strong> 需要相同。如果存在两个（或两个以上）<strong>相同</strong> 文件夹，则需要将这些文件夹和所有它们的子文件夹 <strong>标记</strong> 为待删除。</p>

<ul>
	<li>例如，下面文件结构中的文件夹 <code>"/a"</code> 和 <code>"/b"</code> 相同。它们（以及它们的子文件夹）应该被 <strong>全部</strong> 标记为待删除：

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
    <li>然而，如果文件结构中还包含路径 <code>"/b/w"</code> ，那么文件夹 <code>"/a"</code> 和 <code>"/b"</code> 就不相同。注意，即便添加了新的文件夹 <code>"/b/w"</code> ，仍然认为 <code>"/a/x"</code> 和 <code>"/b/x"</code> 相同。</li>

</ul>

<p>一旦所有的相同文件夹和它们的子文件夹都被标记为待删除，文件系统将会 <strong>删除</strong> 所有上述文件夹。文件系统只会执行一次删除操作。执行完这一次删除操作后，不会删除新出现的相同文件夹。</p>

<p>返回二维数组<em> </em><code>ans</code> ，该数组包含删除所有标记文件夹之后剩余文件夹的路径。路径可以按 <strong>任意顺序</strong> 返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder1.jpg" style="width: 200px; height: 218px;" />
<pre>
<strong>输入：</strong>paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
<strong>输出：</strong>[["d"],["d","a"]]
<strong>解释：</strong>文件结构如上所示。
文件夹 "/a" 和 "/c"（以及它们的子文件夹）都会被标记为待删除，因为它们都包含名为 "b" 的空文件夹。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder2.jpg" style="width: 200px; height: 355px;" />
<pre>
<strong>输入：</strong>paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
<strong>输出：</strong>[["c"],["c","b"],["a"],["a","b"]]
<strong>解释：</strong>文件结构如上所示。
文件夹 "/a/b/x" 和 "/w"（以及它们的子文件夹）都会被标记为待删除，因为它们都包含名为 "y" 的空文件夹。
注意，文件夹 "/a" 和 "/c" 在删除后变为相同文件夹，但这两个文件夹不会被删除，因为删除只会进行一次，且它们没有在删除前被标记。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder3.jpg" style="width: 200px; height: 201px;" />
<pre>
<strong>输入：</strong>paths = [["a","b"],["c","d"],["c"],["a"]]
<strong>输出：</strong>[["c"],["c","d"],["a"],["a","b"]]
<strong>解释：</strong>文件系统中所有文件夹互不相同。
注意，返回的数组可以按不同顺序返回文件夹路径，因为题目对顺序没有要求。
</pre>

<p><strong>示例 4：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder4_.jpg" style="width: 300px; height: 290px;" />
<pre>
<strong>输入：</strong>paths = [["a"],["a","x"],["a","x","y"],["a","z"],["b"],["b","x"],["b","x","y"],["b","z"]]
<strong>输出：</strong>[]
<strong>解释：</strong>文件结构如上所示。
文件夹 "/a/x" 和 "/b/x"（以及它们的子文件夹）都会被标记为待删除，因为它们都包含名为 "y" 的空文件夹。
文件夹 "/a" 和 "/b"（以及它们的子文件夹）都会被标记为待删除，因为它们都包含一个名为 "z" 的空文件夹以及上面提到的文件夹 "x" 。
</pre>

<p><strong>示例 5：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder5_.jpg" style="width: 300px; height: 282px;" />
<pre>
<strong>输入：</strong>paths = [["a"],["a","x"],["a","x","y"],["a","z"],["b"],["b","x"],["b","x","y"],["b","z"],["b","w"]]
<strong>输出：</strong>[["b"],["b","w"],["b","z"],["a"],["a","z"]]
<strong>解释：</strong>本例与上例的结构基本相同，除了新增 "/b/w" 文件夹。
文件夹 "/a/x" 和 "/b/x" 仍然会被标记，但 "/a" 和 "/b" 不再被标记，因为 "/b" 中有名为 "w" 的空文件夹而 "/a" 没有。
注意，"/a/z" 和 "/b/z" 不会被标记，因为相同子文件夹的集合必须是非空集合，但这两个文件夹都是空的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= paths[i].length &lt;= 500</code></li>
	<li><code>1 &lt;= paths[i][j].length &lt;= 10</code></li>
	<li><code>1 &lt;= sum(paths[i][j].length) &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>path[i][j]</code> 由小写英文字母组成</li>
	<li>不会存在两个路径都指向同一个文件夹的情况</li>
	<li>对于不在根层级的任意文件夹，其父文件夹也会包含在输入中</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树 + DFS

我们可以使用字典树来存储文件夹的结构，字典树的每个节点数据如下：

- `children`：一个字典，键为子文件夹的名称，值为对应的子节点。
- `deleted`：一个布尔值，表示该节点是否被标记为待删除。

我们将所有路径插入到字典树中，然后使用 DFS 遍历字典树，构建每个子树的字符串表示。对于每个子树，如果它的字符串表示已经存在于一个全局字典中，则将该节点和全局字典中的对应节点都标记为待删除。最后，再次使用 DFS 遍历字典树，将未被标记的节点的路径添加到结果列表中。

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
