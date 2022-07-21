# [1233. 删除子文件夹](https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem)

[English Version](/solution/1200-1299/1233.Remove%20Sub-Folders%20from%20the%20Filesystem/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一位系统管理员，手里有一份文件夹列表 <code>folder</code>，你的任务是要删除该列表中的所有 <strong>子文件夹</strong>，并以 <strong>任意顺序</strong> 返回剩下的文件夹。</p>

<p>如果文件夹&nbsp;<code>folder[i]</code>&nbsp;位于另一个文件夹&nbsp;<code>folder[j]</code>&nbsp;下，那么&nbsp;<code>folder[i]</code>&nbsp;就是&nbsp;<code>folder[j]</code>&nbsp;的 <strong>子文件夹</strong> 。</p>

<p>文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：<font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'/'</span></span></font></font>&nbsp;后跟一个或者多个小写英文字母。</p>

<ul>
	<li>例如，<code>"/leetcode"</code>&nbsp;和&nbsp;<code>"/leetcode/problems"</code>&nbsp;都是有效的路径，而空字符串和&nbsp;<code>"/"</code>&nbsp;不是。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
<strong>输出：</strong>["/a","/c/d","/c/f"]
<strong>解释：</strong>"/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>folder = ["/a","/a/b/c","/a/b/d"]
<strong>输出：</strong>["/a"]
<strong>解释：</strong>文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> folder = ["/a/b/c","/a/b/ca","/a/b/d"]
<strong>输出:</strong> ["/a/b/c","/a/b/ca","/a/b/d"]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= folder.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= folder[i].length &lt;= 100</code></li>
	<li><code>folder[i]</code>&nbsp;只包含小写字母和 <code>'/'</code></li>
	<li><code>folder[i]</code>&nbsp;总是以字符 <code>'/'</code>&nbsp;起始</li>
	<li>每个文件夹名都是 <strong>唯一</strong> 的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀树**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
