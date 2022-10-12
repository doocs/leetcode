# [839. Similar String Groups](https://leetcode.com/problems/similar-string-groups)

[中文文档](/solution/0800-0899/0839.Similar%20String%20Groups/README.md)

## Description

<p>Two strings <code>X</code>&nbsp;and <code>Y</code>&nbsp;are similar if we can swap two letters (in different positions) of <code>X</code>, so that&nbsp;it equals <code>Y</code>. Also two strings <code>X</code> and <code>Y</code> are similar if they are equal.</p>

<p>For example, <code>&quot;tars&quot;</code>&nbsp;and <code>&quot;rats&quot;</code>&nbsp;are similar (swapping at positions <code>0</code> and <code>2</code>), and <code>&quot;rats&quot;</code> and <code>&quot;arts&quot;</code> are similar, but <code>&quot;star&quot;</code> is not similar to <code>&quot;tars&quot;</code>, <code>&quot;rats&quot;</code>, or <code>&quot;arts&quot;</code>.</p>

<p>Together, these form two connected groups by similarity: <code>{&quot;tars&quot;, &quot;rats&quot;, &quot;arts&quot;}</code> and <code>{&quot;star&quot;}</code>.&nbsp; Notice that <code>&quot;tars&quot;</code> and <code>&quot;arts&quot;</code> are in the same group even though they are not similar.&nbsp; Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.</p>

<p>We are given a list <code>strs</code> of strings where every string in <code>strs</code> is an anagram of every other string in <code>strs</code>. How many groups are there?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;tars&quot;,&quot;rats&quot;,&quot;arts&quot;,&quot;star&quot;]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;omv&quot;,&quot;ovm&quot;]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 300</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 300</code></li>
	<li><code>strs[i]</code> consists of lowercase letters only.</li>
	<li>All words in <code>strs</code> have the same length and are anagrams of each other.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private boolean check(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt <= 2;
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
