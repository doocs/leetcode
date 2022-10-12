# [609. Find Duplicate File in System](https://leetcode.com/problems/find-duplicate-file-in-system)

[中文文档](/solution/0600-0699/0609.Find%20Duplicate%20File%20in%20System/README.md)

## Description

<p>Given a list <code>paths</code> of directory info, including the directory path, and all the files with contents in this directory, return <em>all the duplicate files in the file system in terms of their paths</em>. You may return the answer in <strong>any order</strong>.</p>

<p>A group of duplicate files consists of at least two files that have the same content.</p>

<p>A single directory info string in the input list has the following format:</p>

<ul>
	<li><code>&quot;root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)&quot;</code></li>
</ul>

<p>It means there are <code>n</code> files <code>(f1.txt, f2.txt ... fn.txt)</code> with content <code>(f1_content, f2_content ... fn_content)</code> respectively in the directory &quot;<code>root/d1/d2/.../dm&quot;</code>. Note that <code>n &gt;= 1</code> and <code>m &gt;= 0</code>. If <code>m = 0</code>, it means the directory is just the root directory.</p>

<p>The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:</p>

<ul>
	<li><code>&quot;directory_path/file_name.txt&quot;</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
<strong>Output:</strong> [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
<strong>Output:</strong> [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= paths[i].length &lt;= 3000</code></li>
	<li><code>1 &lt;= sum(paths[i].length) &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>paths[i]</code> consist of English letters, digits, <code>&#39;/&#39;</code>, <code>&#39;.&#39;</code>, <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, and <code>&#39; &#39;</code>.</li>
	<li>You may assume no files or directories share the same name in the same directory.</li>
	<li>You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Imagine you are given a real file system, how will you search files? DFS or BFS?</li>
	<li>If the file content is very large (GB level), how will you modify your solution?</li>
	<li>If you can only read the file by 1kb each time, how will you modify your solution?</li>
	<li>What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?</li>
	<li>How to make sure the duplicated files you find are not false positive?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findDuplicate(self, paths: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for p in paths:
            ps = p.split()
            for f in ps[1:]:
                i = f.find('(')
                name, content = f[:i], f[i + 1: -1]
                d[content].append(ps[0] + '/' + name)
        return [v for v in d.values() if len(v) > 1]
```

### **Java**

```java
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> d = new HashMap<>();
        for (String p : paths) {
            String[] ps = p.split(" ");
            for (int i = 1; i < ps.length; ++i) {
                int j = ps[i].indexOf('(');
                String content = ps[i].substring(j + 1, ps[i].length() - 1);
                String name = ps[0] + '/' + ps[i].substring(0, j);
                d.computeIfAbsent(content, k -> new ArrayList<>()).add(name);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (var e : d.values()) {
            if (e.size() > 1) {
                ans.add(e);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> findDuplicate(vector<string>& paths) {
        unordered_map<string, vector<string>> d;
        for (auto& p : paths) {
            auto ps = split(p, ' ');
            for (int i = 1; i < ps.size(); ++i) {
                int j = ps[i].find('(');
                auto content = ps[i].substr(j + 1, ps[i].size() - j - 2);
                auto name = ps[0] + '/' + ps[i].substr(0, j);
                d[content].push_back(name);
            }
        }
        vector<vector<string>> ans;
        for (auto& [_, e] : d) {
            if (e.size() > 1) {
                ans.push_back(e);
            }
        }
        return ans;
    }

    vector<string> split(string& s, char c) {
        vector<string> res;
        stringstream ss(s);
        string t;
        while (getline(ss, t, c)) {
            res.push_back(t);
        }
        return res;
    }
};
```

### **Go**

```go
func findDuplicate(paths []string) [][]string {
	d := map[string][]string{}
	for _, p := range paths {
		ps := strings.Split(p, " ")
		for i := 1; i < len(ps); i++ {
			j := strings.IndexByte(ps[i], '(')
			content := ps[i][j+1 : len(ps[i])-1]
			name := ps[0] + "/" + ps[i][:j]
			d[content] = append(d[content], name)
		}
	}
	ans := [][]string{}
	for _, e := range d {
		if len(e) > 1 {
			ans = append(ans, e)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findDuplicate(paths: string[]): string[][] {
    const d = new Map<string, string[]>();
    for (const p of paths) {
        const [root, ...fs] = p.split(' ');
        for (const f of fs) {
            const [name, content] = f.split(/\(|\)/g).filter(Boolean);
            const t = d.get(content) ?? [];
            t.push(root + '/' + name);
            d.set(content, t);
        }
    }
    return [...d.values()].filter(e => e.length > 1);
}
```

### **...**

```

```

<!-- tabs:end -->
