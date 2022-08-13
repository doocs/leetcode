# [839. 相似字符串组](https://leetcode.cn/problems/similar-string-groups)

[English Version](/solution/0800-0899/0839.Similar%20String%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果交换字符串&nbsp;<code>X</code> 中的两个不同位置的字母，使得它和字符串&nbsp;<code>Y</code> 相等，那么称 <code>X</code> 和 <code>Y</code> 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。</p>

<p>例如，<code>"tars"</code> 和 <code>"rats"</code> 是相似的 (交换 <code>0</code> 与 <code>2</code> 的位置)；&nbsp;<code>"rats"</code> 和 <code>"arts"</code> 也是相似的，但是 <code>"star"</code> 不与 <code>"tars"</code>，<code>"rats"</code>，或 <code>"arts"</code> 相似。</p>

<p>总之，它们通过相似性形成了两个关联组：<code>{"tars", "rats", "arts"}</code> 和 <code>{"star"}</code>。注意，<code>"tars"</code> 和 <code>"arts"</code> 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。</p>

<p>给你一个字符串列表 <code>strs</code>。列表中的每个字符串都是 <code>strs</code> 中其它所有字符串的一个字母异位词。请问 <code>strs</code> 中有多少个相似字符串组？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["tars","rats","arts","star"]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["omv","ovm"]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 300</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 300</code></li>
	<li><code>strs[i]</code> 只包含小写字母。</li>
	<li><code>strs</code> 中的所有单词都具有相同的长度，且是彼此的字母异位词。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集模板题。

模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]


# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

对于本题，先遍历所有字符串对，判断两字符串是否相似，若相似，则将两字符串合并到同一个集合中，从而形成并查集。最后统计集合的数量即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSimilarGroups(self, strs: List[str]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n, l = len(strs), len(strs[0])
        p = list(range(n))
        for i in range(n):
            for j in range(i + 1, n):
                if sum(strs[i][k] != strs[j][k] for k in range(l)) <= 2:
                    p[find(i)] = find(j)
        return sum(i == find(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(strs[i], strs[j])) {
                    p[find(i)] = find(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++res;
            }
        }
        return res;
    }

    private boolean check(String a, String b) {
        int cnt = 0;
        int n = a.length();
        for (int i = 0; i < n; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt <= 2;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (check(strs[i], strs[j]))
                    p[find(i)] = find(j);
        int ans = 0;
        for (int i = 0; i < n; ++i)
            if (i == find(i))
                ++ans;
        return ans;
    }

    bool check(string a, string b) {
        int cnt = 0;
        for (int i = 0; i < a.size(); ++i)
            if (a[i] != b[i])
                ++cnt;
        return cnt <= 2;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func numSimilarGroups(strs []string) int {
	n := len(strs)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	check := func(a, b string) bool {
		cnt := 0
		for i := range a {
			if a[i] != b[i] {
				cnt++
			}
		}
		return cnt <= 2
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(strs[i], strs[j]) {
				p[find(i)] = find(j)
			}
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
