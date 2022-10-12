# [186. Reverse Words in a String II](https://leetcode.com/problems/reverse-words-in-a-string-ii)

[中文文档](/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README.md)

## Description

<p>Given a character array <code>s</code>, reverse the order of the <strong>words</strong>.</p>

<p>A <strong>word</strong> is defined as a sequence of non-space characters. The <strong>words</strong> in <code>s</code> will be separated by a single space.</p>

<p>Your code must solve the problem&nbsp;<strong>in-place,</strong> i.e. without allocating extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
<strong>Output:</strong> ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = ["a"]
<strong>Output:</strong> ["a"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is an English letter (uppercase or lowercase), digit, or space <code>&#39; &#39;</code>.</li>
	<li>There is <strong>at least one</strong> word in <code>s</code>.</li>
	<li><code>s</code> does not contain leading or trailing spaces.</li>
	<li>All the words in <code>s</code> are guaranteed to be separated by a single space.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseWords(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """

        def reverse(s, i, j):
            while i < j:
                s[i], s[j] = s[j], s[i]
                i += 1
                j -= 1

        i, j, n = 0, 0, len(s)
        while j < n:
            if s[j] == ' ':
                reverse(s, i, j - 1)
                i = j + 1
            elif j == n - 1:
                reverse(s, i, j)
            j += 1
        reverse(s, 0, n - 1)
```

### **Java**

```java
class Solution {
    public void reverseWords(char[] s) {
        int n = s.length;
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int i, int j) {
        for (; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void reverseWords(vector<char>& s) {
        int n = s.size();
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    void reverse(vector<char>& s, int i, int j) {
        for (; i < j; ++i, --j) {
            swap(s[i], s[j]);
        }
    }
};
```

### **Go**

```go
func reverseWords(s []byte) {
	n := len(s)
	for i, j := 0, 0; j < n; j++ {
		if s[j] == ' ' {
			reverse(s, i, j-1)
			i = j + 1
		} else if j == n-1 {
			reverse(s, i, j)
		}
	}
	reverse(s, 0, n-1)
}

func reverse(s []byte, i, j int) {
	for i < j {
		s[i], s[j] = s[j], s[i]
		i++
		j--
	}
}
```

### **...**

```

```

<!-- tabs:end -->
