# [1061. 按字典序排列最小的等效字符串](https://leetcode-cn.com/problems/lexicographically-smallest-equivalent-string)

[English Version](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出长度相同的两个字符串：<code>A</code> 和&nbsp;<code>B</code>，其中 A[i] 和 B[i] 是一组等价字符。举个例子，如果&nbsp;<code>A = &quot;abc&quot;</code> 且&nbsp;<code>B = &quot;cde&quot;</code>，那么就有&nbsp;<code>&#39;a&#39; == &#39;c&#39;, &#39;b&#39; == &#39;d&#39;, &#39;c&#39; == &#39;e&#39;</code>。</p>

<p>等价字符遵循任何等价关系的一般规则：</p>

<ul>
	<li>自反性：&#39;a&#39; == &#39;a&#39;</li>
	<li>对称性：&#39;a&#39; == &#39;b&#39; 则必定有 &#39;b&#39; == &#39;a&#39;</li>
	<li>传递性：&#39;a&#39; == &#39;b&#39; 且 &#39;b&#39; == &#39;c&#39; 就表明 &#39;a&#39; == &#39;c&#39;</li>
</ul>

<p>例如，<code>A</code> 和&nbsp;<code>B</code>&nbsp;的等价信息和之前的例子一样，那么&nbsp;<code>S = &quot;eed&quot;</code>, <code>&quot;acd&quot;</code>&nbsp;或&nbsp;<code>&quot;aab&quot;</code>，这三个字符串都是等价的，而&nbsp;<code>&quot;aab&quot;</code>&nbsp;是 <code>S</code>&nbsp;的按字典序最小的等价字符串</p>

<p>利用&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;的等价信息，找出并返回 <code>S</code>&nbsp;的按字典序排列最小的等价字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = &quot;parker&quot;, B = &quot;morris&quot;, S = &quot;parser&quot;
<strong>输出：</strong>&quot;makkek&quot;
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i] 共 4 组</code>。每组中的字符都是等价的，并按字典序排列。所以答案是 <code>&quot;makkek&quot;</code>。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = &quot;hello&quot;, B = &quot;world&quot;, S = &quot;hold&quot;
<strong>输出：</strong>&quot;hdld&quot;
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r] 共 3 组</code>。所以只有 S 中的第二个字符 <code>&#39;o&#39;</code> 变成 <code>&#39;d&#39;，最后答案为<span style=""> </span></code><code>&quot;hdld&quot;</code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = &quot;leetcode&quot;, B = &quot;programs&quot;, S = &quot;sourcecode&quot;
<strong>输出：</strong>&quot;aauaaaaada&quot;
<strong>解释：</strong>我们可以把 A 和 B 中的等价字符分为 <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> 和 <code>[d,m] 共 4 组</code>，因此 <code>S</code> 中除了 <code>&#39;u&#39;</code> 和 <code>&#39;d&#39;</code> 之外的所有字母都转化成了 <code>&#39;a&#39;</code>，最后答案为 <code>&quot;aauaaaaada&quot;</code>。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串&nbsp;<code>A</code>，<code>B</code>&nbsp;和&nbsp;<code>S</code>&nbsp;仅有从&nbsp;<code>&#39;a&#39;</code> 到&nbsp;<code>&#39;z&#39;</code>&nbsp;的小写英文字母组成。</li>
	<li>字符串&nbsp;<code>A</code>，<code>B</code>&nbsp;和&nbsp;<code>S</code>&nbsp;的长度在&nbsp;<code>1</code> 到&nbsp;<code>1000</code>&nbsp;之间。</li>
	<li>字符串&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;长度相同。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

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

对于本题，套用并查集模板时，将数值较大的祖宗节点指向数值较小的祖宗节点，这样可以保证祖宗节点存放的是本集合的最小值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        p = list(range(26))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(len(s1)):
            a, b = ord(s1[i]) - ord('a'), ord(s2[i]) - ord('a')
            pa, pb = find(a), find(b)
            if pa < pb:
                p[pb] = pa
            else:
                p[pa] = pb

        res = []
        for a in baseStr:
            a = ord(a) - ord('a')
            res.append(chr(find(a) + ord('a')))
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        p = new int[26];
        for (int i = 0; i < 26; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < s1.length(); ++i) {
            int a = s1.charAt(i) - 'a', b = s2.charAt(i) - 'a';
            int pa = find(a), pb = find(b);
            if (pa < pb) {
                p[pb] = pa;
            } else {
                p[pa] = pb;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char a : baseStr.toCharArray()) {
            char b = (char) (find(a - 'a') + 'a');
            sb.append(b);
        }
        return sb.toString();
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

    string smallestEquivalentString(string s1, string s2, string baseStr) {
        p.resize(26);
        for (int i = 0; i < 26; ++i)
            p[i] = i;
        for (int i = 0; i < s1.size(); ++i)
        {
            int a = s1[i] - 'a', b = s2[i] - 'a';
            int pa = find(a), pb = find(b);
            if (pa < pb)
                p[pb] = pa;
            else
                p[pa] = pb;
        }
        string res = "";
        for (char a : baseStr)
        {
            char b = (char)(find(a - 'a') + 'a');
            res += b;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func smallestEquivalentString(s1 string, s2 string, baseStr string) string {
	p = make([]int, 26)
	for i := 0; i < 26; i++ {
		p[i] = i
	}
	for i := 0; i < len(s1); i++ {
		a, b := int(s1[i]-'a'), int(s2[i]-'a')
		pa, pb := find(a), find(b)
		if pa < pb {
			p[pb] = pa
		} else {
			p[pa] = pb
		}
	}
	var res []byte
	for _, a := range baseStr {
		b := byte(find(int(a-'a'))) + 'a'
		res = append(res, b)
	}
	return string(res)
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
