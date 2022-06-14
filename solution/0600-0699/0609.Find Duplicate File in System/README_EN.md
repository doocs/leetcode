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
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
<strong>Output:</strong> [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
</pre><p><strong>Example 2:</strong></p>
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
        m = defaultdict(list)
        for path in paths:
            a = path.split(" ")
            for i in range(1, len(a)):
                j = a[i].find("(")
                content = a[i][j + 1 : -1]
                name = a[0] + "/" + a[i][:j]
                m[content].append(name)

        ans = []
        for names in m.values():
            if len(names) > 1:
                ans.append(names)
        return ans
```

### **Java**

```java
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] a = path.split(" ");
            for (int i = 1; i < a.length; i++) {
                int j = a[i].indexOf('(');
                String content = a[i].substring(j + 1, a[i].length() - 1);
                String name = a[0] + '/' + a[i].substring(0, j);
                List<String> list = map.getOrDefault(content, new ArrayList<>());
                list.add(name);
                map.put(content, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> names : map.values()) {
            if (names.size() > 1) {
                ans.add(names);
            }
        }
        return ans;
    }
}
```

### **Go**

```go
func findDuplicate(paths []string) [][]string {
	m := make(map[string][]string)
	for _, path := range paths {
		a := strings.Split(path, " ")
		for i := 1; i < len(a); i++ {
			j := strings.Index(a[i], "(")
			content := a[i][j+1 : len(a[i])-1]
			name := a[0] + "/" + a[i][:j]
			m[content] = append(m[content], name)
		}
	}

	var ans [][]string
	for _, names := range m {
		if len(names) > 1 {
			ans = append(ans, names)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
    vector<string> split(const string& s, char delim) {
        vector<string> result;
        stringstream ss(s);
        string item;
        while (getline(ss, item, delim)) {
            result.push_back(item);
        }
        return result;
    }

public:
    vector<vector<string>> findDuplicate(vector<string>& paths) {
        unordered_map<string, vector<string>> m;
        for (auto& path : paths) {
            auto a = split(path, ' ');
            for (int i = 1; i < a.size(); ++i) {
                int j = a[i].find('(');
                auto content = a[i].substr(j + 1, a[i].size() - j - 2);
                auto name = a[0] + '/' + a[i].substr(0, j);
                if (m.find(content) == m.end()) {
                    m[content] = vector<string>();
                }
                m[content].emplace_back(name);
            }
        }

        vector<vector<string>> ans;
        for (auto& [_, names] : m) {
            if (names.size() > 1) ans.emplace_back(names);
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
