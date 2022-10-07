# [609. 在系统中查找重复文件](https://leetcode.cn/problems/find-duplicate-file-in-system)

[English Version](/solution/0600-0699/0609.Find%20Duplicate%20File%20in%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个目录信息列表&nbsp;<code>paths</code> ，包括目录路径，以及该目录中的所有文件及其内容，请你按路径返回文件系统中的所有重复文件。答案可按 <strong>任意顺序</strong> 返回。</p>

<p>一组重复的文件至少包括 <strong>两个 </strong>具有完全相同内容的文件。</p>

<p><strong>输入 </strong>列表中的单个目录信息字符串的格式如下：</p>

<ul>
	<li><code>"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"</code></li>
</ul>

<p>这意味着，在目录&nbsp;<code>root/d1/d2/.../dm</code>&nbsp;下，有 <code>n</code> 个文件 ( <code>f1.txt</code>,&nbsp;<code>f2.txt</code>&nbsp;...&nbsp;<code>fn.txt</code> ) 的内容分别是 ( <code>f1_content</code>,&nbsp;<code>f2_content</code>&nbsp;...&nbsp;<code>fn_content</code> ) 。注意：<code>n &gt;= 1</code> 且 <code>m &gt;= 0</code> 。如果 <code>m = 0</code> ，则表示该目录是根目录。</p>

<p><strong>输出 </strong>是由 <strong>重复文件路径组</strong> 构成的列表。其中每个组由所有具有相同内容文件的文件路径组成。文件路径是具有下列格式的字符串：</p>

<ul>
	<li><code>"directory_path/file_name.txt"</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
<strong>输出：</strong>[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
<strong>输出：</strong>[["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= paths[i].length &lt;= 3000</code></li>
	<li><code>1 &lt;= sum(paths[i].length) &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>paths[i]</code> 由英文字母、数字、字符 <code>'/'</code>、<code>'.'</code>、<code>'('</code>、<code>')'</code> 和 <code>' '</code> 组成</li>
	<li>你可以假设在同一目录中没有任何文件或目录共享相同的名称。</li>
	<li>你可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用单个空格分隔。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>假设您有一个真正的文件系统，您将如何搜索文件？广度搜索还是宽度搜索？</li>
	<li>如果文件内容非常大（GB级别），您将如何修改您的解决方案？</li>
	<li>如果每次只能读取 1 kb 的文件，您将如何修改解决方案？</li>
	<li>修改后的解决方案的时间复杂度是多少？其中最耗时的部分和消耗内存的部分是什么？如何优化？</li>
	<li>如何确保您发现的重复文件不是误报？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们创建哈希表 `d`，其中键是文件内容，值是具有相同内容的文件路径列表。

遍历 `paths`，我们处理出每个文件的路径和内容，然后将其添加到哈希表 `d` 中。

最后，我们返回哈希表 `d` 中所有具有多个文件路径的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `paths` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
