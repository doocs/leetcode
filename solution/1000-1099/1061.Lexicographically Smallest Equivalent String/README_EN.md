# [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string)

[中文文档](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README.md)

## Description

<p>Given strings <code>A</code> and <code>B</code> of the same length, we say A[i] and B[i] are equivalent characters. For example, if <code>A = &quot;abc&quot;</code> and <code>B = &quot;cde&quot;</code>, then we have <code>&#39;a&#39; == &#39;c&#39;, &#39;b&#39; == &#39;d&#39;, &#39;c&#39; == &#39;e&#39;</code>.</p>



<p>Equivalent characters follow the usual rules of any equivalence relation:</p>



<ul>
	<li>Reflexivity: &#39;a&#39; == &#39;a&#39;</li>
	<li>Symmetry: &#39;a&#39; == &#39;b&#39; implies &#39;b&#39; == &#39;a&#39;</li>
	<li>Transitivity: &#39;a&#39; == &#39;b&#39; and &#39;b&#39; == &#39;c&#39; implies &#39;a&#39; == &#39;c&#39;</li>
</ul>



<p>For example, given the equivalency information from <code>A</code> and <code>B</code> above, <code>S = &quot;eed&quot;</code>, <code>&quot;acd&quot;</code>, and <code>&quot;aab&quot;</code> are equivalent strings, and <code>&quot;aab&quot;</code> is the lexicographically smallest equivalent string of <code>S</code>.</p>



<p>Return the lexicographically smallest equivalent string of <code>S</code> by using the equivalency information from <code>A</code> and <code>B</code>.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">&quot;parker&quot;</span>, B = <span id="example-input-1-2">&quot;morris&quot;</span>, S = <span id="example-input-1-3">&quot;parser&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;makkek&quot;</span>

<strong>Explanation:</strong> Based on the equivalency information in <code>A</code> and <code>B</code>, we can group their characters as <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i]</code>. The characters in each group are equivalent and sorted in lexicographical order. So the answer is <code>&quot;makkek&quot;</code>.

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-2-1">&quot;hello&quot;</span>, B = <span id="example-input-2-2">&quot;world&quot;</span>, S = <span id="example-input-2-3">&quot;hold&quot;</span>

<strong>Output: </strong><span id="example-output-2">&quot;hdld&quot;</span>

<strong>Explanation: </strong> Based on the equivalency information in <code>A</code> and <code>B</code>, we can group their characters as <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r]</code>. So only the second letter <code>&#39;o&#39;</code> in <code>S</code> is changed to <code>&#39;d&#39;</code>, the answer is <code>&quot;hdld&quot;</code>.

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-3-1">&quot;leetcode&quot;</span>, B = <span id="example-input-3-2">&quot;programs&quot;</span>, S = <span id="example-input-3-3">&quot;sourcecode&quot;</span>

<strong>Output: </strong><span id="example-output-3">&quot;aauaaaaada&quot;</span>

<strong>Explanation: </strong> We group the equivalent characters in <code>A</code> and <code>B</code> as <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> and <code>[d,m]</code>, thus all letters in <code>S</code> except <code>&#39;u&#39;</code> and <code>&#39;d&#39;</code> are transformed to <code>&#39;a&#39;</code>, the answer is <code>&quot;aauaaaaada&quot;</code>.

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>
	<li>String <code>A</code>, <code>B</code> and <code>S</code> consist of only lowercase English letters from <code>&#39;a&#39;</code> - <code>&#39;z&#39;</code>.</li>
	<li>The lengths of string <code>A</code>, <code>B</code> and <code>S</code> are between <code>1</code> and <code>1000</code>.</li>
	<li>String <code>A</code> and <code>B</code> are of the same length.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

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
