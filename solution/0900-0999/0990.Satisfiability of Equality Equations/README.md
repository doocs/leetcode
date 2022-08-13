# [990. 等式方程的可满足性](https://leetcode.cn/problems/satisfiability-of-equality-equations)

[English Version](/solution/0900-0999/0990.Satisfiability%20of%20Equality%20Equations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 <code>equations[i]</code> 的长度为 <code>4</code>，并采用两种不同的形式之一：<code>&quot;a==b&quot;</code> 或&nbsp;<code>&quot;a!=b&quot;</code>。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。</p>

<p>只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回&nbsp;<code>true</code>，否则返回 <code>false</code>。&nbsp;</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;a==b&quot;,&quot;b!=a&quot;]
<strong>输出：</strong>false
<strong>解释：</strong>如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;b==a&quot;,&quot;a==b&quot;]
<strong>输出：</strong>true
<strong>解释：</strong>我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[&quot;a==b&quot;,&quot;b==c&quot;,&quot;a==c&quot;]
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>[&quot;a==b&quot;,&quot;b!=c&quot;,&quot;c==a&quot;]
<strong>输出：</strong>false
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>[&quot;c==c&quot;,&quot;b==d&quot;,&quot;x!=z&quot;]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= equations.length &lt;= 500</code></li>
	<li><code>equations[i].length == 4</code></li>
	<li><code>equations[i][0]</code> 和&nbsp;<code>equations[i][3]</code>&nbsp;是小写字母</li>
	<li><code>equations[i][1]</code> 要么是&nbsp;<code>&#39;=&#39;</code>，要么是&nbsp;<code>&#39;!&#39;</code></li>
	<li><code>equations[i][2]</code>&nbsp;是&nbsp;<code>&#39;=&#39;</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。对于本题，先遍历所有的等式，构造并查集。接着遍历所有不等式，如果不等式的两个变量处于同一个集合，说明发生矛盾，返回 false。否则遍历结束返回 true。

以下是并查集的几个常用模板。

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def equationsPossible(self, equations: List[str]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(26))
        for e in equations:
            a, b = ord(e[0]) - ord('a'), ord(e[-1]) - ord('a')
            if e[1] == '=':
                p[find(a)] = find(b)
        for e in equations:
            a, b = ord(e[0]) - ord('a'), ord(e[-1]) - ord('a')
            if e[1] == '!' and find(a) == find(b):
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean equationsPossible(String[] equations) {
        p = new int[26];
        for (int i = 0; i < 26; ++i) {
            p[i] = i;
        }
        for (String e : equations) {
            int a = e.charAt(0) - 'a', b = e.charAt(3) - 'a';
            if (e.charAt(1) == '=') {
                p[find(a)] = find(b);
            }
        }
        for (String e : equations) {
            int a = e.charAt(0) - 'a', b = e.charAt(3) - 'a';
            if (e.charAt(1) == '!' && find(a) == find(b)) {
                return false;
            }
        }
        return true;
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

    bool equationsPossible(vector<string>& equations) {
        p.resize(26);
        for (int i = 0; i < 26; ++i) p[i] = i;
        for (auto& e : equations) {
            int a = e[0] - 'a', b = e[3] - 'a';
            if (e[1] == '=') p[find(a)] = find(b);
        }
        for (auto& e : equations) {
            int a = e[0] - 'a', b = e[3] - 'a';
            if (e[1] == '!' && find(a) == find(b)) return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func equationsPossible(equations []string) bool {
	p := make([]int, 26)
	for i := 1; i < 26; i++ {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		if e[1] == '=' {
			p[find(a)] = find(b)
		}
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		if e[1] == '!' && find(a) == find(b) {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
class UnionFind {
    private parent: number[];

    constructor() {
        this.parent = Array.from({ length: 26 }).map((_, i) => i);
    }

    find(index: number) {
        if (this.parent[index] === index) {
            return index;
        }
        this.parent[index] = this.find(this.parent[index]);
        return this.parent[index];
    }

    union(index1: number, index2: number) {
        this.parent[this.find(index1)] = this.find(index2);
    }
}

function equationsPossible(equations: string[]): boolean {
    const uf = new UnionFind();
    for (const [a, s, _, b] of equations) {
        if (s === '=') {
            const index1 = a.charCodeAt(0) - 'a'.charCodeAt(0);
            const index2 = b.charCodeAt(0) - 'a'.charCodeAt(0);
            uf.union(index1, index2);
        }
    }
    for (const [a, s, _, b] of equations) {
        if (s === '!') {
            const index1 = a.charCodeAt(0) - 'a'.charCodeAt(0);
            const index2 = b.charCodeAt(0) - 'a'.charCodeAt(0);
            if (uf.find(index1) === uf.find(index2)) {
                return false;
            }
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
