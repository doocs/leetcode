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

### Solution 1

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
