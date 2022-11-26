# [828. Count Unique Characters of All Substrings of a Given String](https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string)

[中文文档](/solution/0800-0899/0828.Count%20Unique%20Characters%20of%20All%20Substrings%20of%20a%20Given%20String/README.md)

## Description

<p>Let&#39;s define a function <code>countUniqueChars(s)</code> that returns the number of unique characters on <code>s</code>.</p>

<ul>
	<li>For example, calling <code>countUniqueChars(s)</code> if <code>s = &quot;LEETCODE&quot;</code> then <code>&quot;L&quot;</code>, <code>&quot;T&quot;</code>, <code>&quot;C&quot;</code>, <code>&quot;O&quot;</code>, <code>&quot;D&quot;</code> are the unique characters since they appear only once in <code>s</code>, therefore <code>countUniqueChars(s) = 5</code>.</li>
</ul>

<p>Given a string <code>s</code>, return the sum of <code>countUniqueChars(t)</code> where <code>t</code> is a substring of <code>s</code>. The test cases are generated such that the answer fits in a 32-bit integer.</p>

<p>Notice that some substrings can be repeated so in this case you have to count the repeated ones too.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABC&quot;
<strong>Output:</strong> 10
<strong>Explanation: </strong>All possible substrings are: &quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;AB&quot;,&quot;BC&quot; and &quot;ABC&quot;.
Every substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABA&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The same as example 1, except <code>countUniqueChars</code>(&quot;ABA&quot;) = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LEETCODE&quot;
<strong>Output:</strong> 92
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of uppercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniqueLetterString(self, s: str) -> int:
        d = defaultdict(list)
        for i, c in enumerate(s):
            d[c].append(i)
        ans = 0
        for v in d.values():
            v = [-1] + v + [len(s)]
            for i in range(1, len(v) - 1):
                ans += (v[i] - v[i - 1]) * (v[i + 1] - v[i])
        return ans
```

### **Java**

```java
class Solution {
    public int uniqueLetterString(String s) {
        List<Integer>[] d = new List[26];
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 0; i < 26; ++i) {
            d[i].add(-1);
        }
        for (int i = 0; i < s.length(); ++i) {
            d[s.charAt(i) - 'A'].add(i);
        }
        int ans = 0;
        for (var v : d) {
            v.add(s.length());
            for (int i = 1; i < v.size() - 1; ++i) {
                ans += (v.get(i) - v.get(i - 1)) * (v.get(i + 1) - v.get(i));
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
    int uniqueLetterString(string s) {
        vector<vector<int>> d(26, {-1});
        for (int i = 0; i < s.size(); ++i) {
            d[s[i] - 'A'].push_back(i);
        }
        int ans = 0;
        for (auto& v : d) {
            v.push_back(s.size());
            for (int i = 1; i < v.size() - 1; ++i) {
                ans += (v[i] - v[i - 1]) * (v[i + 1] - v[i]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func uniqueLetterString(s string) int {
	d := make([][]int, 26)
	for i := range d {
		d[i] = []int{-1}
	}
	for i, c := range s {
		d[c-'A'] = append(d[c-'A'], i)
	}
	ans := 0
	for _, v := range d {
		v = append(v, len(s))
		for i := 1; i < len(v)-1; i++ {
			ans += (v[i] - v[i-1]) * (v[i+1] - v[i])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
