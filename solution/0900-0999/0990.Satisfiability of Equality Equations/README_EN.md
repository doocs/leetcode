# [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations)

[中文文档](/solution/0900-0999/0990.Satisfiability%20of%20Equality%20Equations/README.md)

## Description

<p>Given an array <font face="monospace">equations</font>&nbsp;of strings that represent relationships between variables, each string <code>equations[i]</code>&nbsp;has length <code>4</code> and takes one of two different forms: <code>&quot;a==b&quot;</code> or <code>&quot;a!=b&quot;</code>.&nbsp; Here, <code>a</code> and <code>b</code> are lowercase letters (not necessarily different) that represent one-letter variable names.</p>

<p>Return <code>true</code>&nbsp;if and only if it is possible to assign integers to variable names&nbsp;so as to satisfy all the given equations.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;a==b&quot;,&quot;b!=a&quot;]</span>

<strong>Output: </strong><span id="example-output-1">false</span>

<strong>Explanation: </strong>If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[&quot;b==a&quot;,&quot;a==b&quot;]</span>

<strong>Output: </strong><span id="example-output-2">true</span>

<strong>Explanation: </strong>We could assign a = 1 and b = 1 to satisfy both equations.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[&quot;a==b&quot;,&quot;b==c&quot;,&quot;a==c&quot;]</span>

<strong>Output: </strong><span id="example-output-3">true</span>

</pre>

<div>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-4-1">[&quot;a==b&quot;,&quot;b!=c&quot;,&quot;c==a&quot;]</span>

<strong>Output: </strong><span id="example-output-4">false</span>

</pre>

<div>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-5-1">[&quot;c==c&quot;,&quot;b==d&quot;,&quot;x!=z&quot;]</span>

<strong>Output: </strong><span id="example-output-5">true</span>

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= equations.length &lt;= 500</code></li>
	<li><code>equations[i].length == 4</code></li>
	<li><code>equations[i][0]</code> and <code>equations[i][3]</code> are lowercase letters</li>
	<li><code>equations[i][1]</code> is either <code>&#39;=&#39;</code> or <code>&#39;!&#39;</code></li>
	<li><code>equations[i][2]</code> is&nbsp;<code>&#39;=&#39;</code></li>
</ol>

</div>

</div>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def equationsPossible(self, equations: List[str]) -> bool:
        p = [i for i in range(26)]

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for e in equations:
            a, r, b = ord(e[0]) - ord('a'), e[1:3], ord(e[3]) - ord('a')
            if r == '==':
                p[find(a)] = find(b)
        for e in equations:
            a, r, b = ord(e[0]) - ord('a'), e[1:3], ord(e[3]) - ord('a')
            if r == '!=' and find(a) == find(b):
                return False
        return True
```

### **Java**

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
            String r = e.substring(1, 3);
            if ("==".equals(r)) {
                p[find(a)] = find(b);
            }
        }
        for (String e : equations) {
            int a = e.charAt(0) - 'a', b = e.charAt(3) - 'a';
            String r = e.substring(1, 3);
            if ("!=".equals(r) && find(a) == find(b)) {
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
        for (int i = 0; i < 26; ++i)
            p[i] = i;
        for (auto e : equations)
        {
            int a = e[0] - 'a', b = e[3] - 'a';
            char r = e[1];
            if (r == '=')
                p[find(a)] = find(b);
        }
        for (auto e : equations)
        {
            int a = e[0] - 'a', b = e[3] - 'a';
            char r = e[1];
            if (r == '!' && find(a) == find(b))
                return false;
        }
        return true;
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

func equationsPossible(equations []string) bool {
	p = make([]int, 26)
	for i := 1; i < 26; i++ {
		p[i] = i
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		r := e[1]
		if r == '=' {
			p[find(a)] = find(b)
		}
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		r := e[1]
		if r == '!' && find(a) == find(b) {
			return false
		}
	}
	return true
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
