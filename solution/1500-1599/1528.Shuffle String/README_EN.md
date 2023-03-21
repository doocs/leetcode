# [1528. Shuffle String](https://leetcode.com/problems/shuffle-string)

[中文文档](/solution/1500-1599/1528.Shuffle%20String/README.md)

## Description

<p>You are given a string <code>s</code> and an integer array <code>indices</code> of the <strong>same length</strong>. The string <code>s</code> will be shuffled such that the character at the <code>i<sup>th</sup></code> position moves to <code>indices[i]</code> in the shuffled string.</p>

<p>Return <em>the shuffled string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1528.Shuffle%20String/images/q1.jpg" style="width: 321px; height: 243px;" />
<pre>
<strong>Input:</strong> s = &quot;codeleet&quot;, <code>indices</code> = [4,5,6,7,0,2,1,3]
<strong>Output:</strong> &quot;leetcode&quot;
<strong>Explanation:</strong> As shown, &quot;codeleet&quot; becomes &quot;leetcode&quot; after shuffling.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, <code>indices</code> = [0,1,2]
<strong>Output:</strong> &quot;abc&quot;
<strong>Explanation:</strong> After shuffling, each character remains in its position.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == indices.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>0 &lt;= indices[i] &lt; n</code></li>
	<li>All values of <code>indices</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def restoreString(self, s: str, indices: List[int]) -> str:
        ans = [0] * len(s)
        for i, c in enumerate(s):
            ans[indices[i]] = c
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] ans = new char[n];
        for (int i = 0; i < n; ++i) {
            ans[indices[i]] = s.charAt(i);
        }
        return String.valueOf(ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string restoreString(string s, vector<int>& indices) {
        int n = s.size();
        string ans(n, 0);
        for (int i = 0; i < n; ++i) {
            ans[indices[i]] = s[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func restoreString(s string, indices []int) string {
	ans := make([]rune, len(s))
	for i, c := range s {
		ans[indices[i]] = c
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
