# [10.05. Sparse Array Search](https://leetcode.cn/problems/sparse-array-search-lcci)

## Description

<p>Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a given string.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ta&quot;

<strong> Output</strong>: -1

<strong> Explanation</strong>: Return -1 if <code>s</code> is not in <code>words</code>.

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ball&quot;

<strong> Output</strong>: 4

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= words.length &lt;= 1000000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findString(self, words: List[str], s: str) -> int:
        left, right = 0, len(words) - 1
        while left < right:
            mid = (left + right) >> 1
            while left < mid and words[mid] == '':
                mid -= 1
            if s <= words[mid]:
                right = mid
            else:
                left = mid + 1
        return -1 if words[left] != s else left
```

### **Java**

```java
class Solution {
    public int findString(String[] words, String s) {
        int left = 0, right = words.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            while (left < mid && "".equals(words[mid])) {
                --mid;
            }
            if (s.compareTo(words[mid]) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return s.equals(words[left]) ? left : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findString(vector<string>& words, string s) {
        int left = 0, right = words.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            while (left < mid && words[mid] == "") --mid;
            if (s <= words[mid])
                right = mid;
            else
                left = mid + 1;
        }
        return words[left] == s ? left : -1;
    }
};
```

### **Go**

```go
func findString(words []string, s string) int {
	left, right := 0, len(words)-1
	for left < right {
		mid := (left + right) >> 1
		for left < mid && words[mid] == "" {
			mid--
		}
		if s <= words[mid] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if words[left] == s {
		return left
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
