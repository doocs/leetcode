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

### 方法一

<!-- tabs:start -->

#### Python3

```python

class Trie:
    serial: str = ""
    children: dict

    def __init__(self):
        self.children = dict()


class Solution:
    def deleteDuplicateFolder(self, paths: List[List[str]]) -> List[List[str]]:
        root = Trie()
        for path in paths:
            cur = root
            for node in path:
                if node not in cur.children:
                    cur.children[node] = Trie()
                cur = cur.children[node]
        freq = Counter()
        def construct(node: Trie) -> None:
            if not node.children:
                return
            v = list()
            for folder, child in node.children.items():
                construct(child)
                v.append(folder + "(" + child.serial + ")")
            v.sort()
            node.serial = "".join(v)
            freq[node.serial] += 1
        construct(root)
        ans = list()
        path = list()
        def operate(node: Trie) -> None:
            if freq[node.serial] > 1:
                return
            if path:
                ans.append(path[:])
            for folder, child in node.children.items():
                path.append(folder)
                operate(child)
                path.pop()
        operate(root)
        return ans

```

#### Java

```java

class Solution {

    class Trie {

        String serial;
        Map<String, Trie> children = new HashMap<>();
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie();
        for (List<String> path : paths) {
            Trie cur = root;
            for (String node : path) {
                cur.children.putIfAbsent(node, new Trie());
                cur = cur.children.get(node);
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        construct(root, freq);
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        operate(root, freq, path, ans);
        return ans;
    }

    private void construct(Trie node, Map<String, Integer> freq) {
        if (node.children.isEmpty()) return;

        List<String> v = new ArrayList<>();
        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            construct(entry.getValue(), freq);
            v.add(entry.getKey() + "(" + entry.getValue().serial + ")");
        }

        Collections.sort(v);
        StringBuilder sb = new StringBuilder();
        for (String s : v) {
            sb.append(s);
        }
        node.serial = sb.toString();
        freq.put(node.serial, freq.getOrDefault(node.serial, 0) + 1);
    }

    private void operate(
        Trie node,
        Map<String, Integer> freq,
        List<String> path,
        List<List<String>> ans
    ) {
        if (freq.getOrDefault(node.serial, 0) > 1) return;

        if (!path.isEmpty()) {
            ans.add(new ArrayList<>(path));
        }

        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            path.add(entry.getKey());
            operate(entry.getValue(), freq, path, ans);
            path.remove(path.size() - 1);
        }
    }
}

```

#### C++

```cpp

struct Trie {
    string serial;
    unordered_map<string, Trie*> children;
};

class Solution {
public:
    vector<vector<string>> deleteDuplicateFolder(
        vector<vector<string>>& paths) {
        Trie* root = new Trie();

        for (const vector<string>& path : paths) {
            Trie* cur = root;
            for (const string& node : path) {
                if (!cur->children.count(node)) {
                    cur->children[node] = new Trie();
                }
                cur = cur->children[node];
            }
        }
        unordered_map<string, int> freq;
        function<void(Trie*)> construct = [&](Trie* node) {
            if (node->children.empty()) {
                return;
            }

            vector<string> v;
            for (const auto& [folder, child] : node->children) {
                construct(child);
                v.push_back(folder + "(" + child->serial + ")");
            }
            sort(v.begin(), v.end());
            for (string& s : v) {
                node->serial += move(s);
            }
            ++freq[node->serial];
        };

        construct(root);

        vector<vector<string>> ans;
        vector<string> path;

        function<void(Trie*)> operate = [&](Trie* node) {
            if (freq[node->serial] > 1) {
                return;
            }
            if (!path.empty()) {
                ans.push_back(path);
            }
            for (const auto& [folder, child] : node->children) {
                path.push_back(folder);
                operate(child);
                path.pop_back();
            }
        };

        operate(root);
        return ans;
    }
};

```

#### Go

```go

type Trie struct {
	serial   string
	children map[string]*Trie
}

func deleteDuplicateFolder(paths [][]string) [][]string {
	root := &Trie{children: make(map[string]*Trie)}
	for _, path := range paths {
		cur := root
		for _, node := range path {
			if _, ok := cur.children[node]; !ok {
				cur.children[node] = &Trie{children: make(map[string]*Trie)}
			}
			cur = cur.children[node]
		}
	}

	freq := make(map[string]int)
	var construct func(*Trie)
	construct = func(node *Trie) {
		if len(node.children) == 0 {
			return
		}
		v := make([]string, 0, len(node.children))
		for folder, child := range node.children {
			construct(child)
			v = append(v, folder+"("+child.serial+")")
		}
		sort.Strings(v)
		node.serial = strings.Join(v, "")
		freq[node.serial]++
	}
	construct(root)

	ans := make([][]string, 0)
	path := make([]string, 0)
	var operate func(*Trie)
	operate = func(node *Trie) {
		if freq[node.serial] > 1 {
			return
		}
		if len(path) > 0 {
			tmp := make([]string, len(path))
			copy(tmp, path)
			ans = append(ans, tmp)
		}
		for folder, child := range node.children {
			path = append(path, folder)
			operate(child)
			path = path[:len(path)-1]
		}
	}
	operate(root)

	return ans
}


```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
