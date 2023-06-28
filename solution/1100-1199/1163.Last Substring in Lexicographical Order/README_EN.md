# [1163. Last Substring in Lexicographical Order](https://leetcode.com/problems/last-substring-in-lexicographical-order)

[中文文档](/solution/1100-1199/1163.Last%20Substring%20in%20Lexicographical%20Order/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the last substring of</em> <code>s</code> <em>in lexicographical order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> &quot;bab&quot;
<strong>Explanation:</strong> The substrings are [&quot;a&quot;, &quot;ab&quot;, &quot;aba&quot;, &quot;abab&quot;, &quot;b&quot;, &quot;ba&quot;, &quot;bab&quot;]. The lexicographically maximum substring is &quot;bab&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;
<strong>Output:</strong> &quot;tcode&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4 * 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

**Solution 1: Two pointers**

We notice that if a substring starts from position $i$, then the largest substring with the largest dictionary order must be $s[i,..n-1]$, which is the longest suffix starting from position $i$. Therefore, we only need to find the largest suffix substring.

We use two pointers $i$ and $j$, where pointer $i$ points to the starting position of the current largest substring with the largest dictionary order, and pointer $j$ points to the starting position of the current substring being considered. In addition, we use a variable $k$ to record the current position being compared. Initially, $i = 0$, $j=1$, $k=0$.

Each time, we compare $s[i+k]$ and $s[j+k]$:

If $s[i + k] = s[j + k]$, it means that $s[i,..i+k]$ and $s[j,..j+k]$ are the same, and we add $k$ by $1$ and continue to compare $s[i+k]$ and $s[j+k]$;

If $s[i + k] \lt s[j + k]$, it means that the dictionary order of $s[j,..j+k]$ is larger. At this time, we update $i = i + k + 1$, and reset $k$ to $0$. If $i \geq j$ at this time, we update pointer $j$ to $i + 1$, that is, $j = i + 1$. Here we skip all suffix substrings with $s[i,..,i+k]$ as the starting position, because their dictionary orders are smaller than the suffix substrings with $s[j,..,j+k]$ as the starting position.

Similarly, if $s[i + k] \gt s[j + k]$, it means that the dictionary order of $s[i,..,i+k]$ is larger. At this time, we update $j = j + k + 1$ and reset $k$ to $0$. Here we skip all suffix substrings with $s[j,..,j+k]$ as the starting position, because their dictionary orders are smaller than the suffix substrings with $s[i,..,i+k]$ as the starting position.

Finally, we return the suffix substring starting from $i$, that is, $s[i,..,n-1]$.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lastSubstring(self, s: str) -> str:
        i, j, k = 0, 1, 0
        while j + k < len(s):
            if s[i + k] == s[j + k]:
                k += 1
            elif s[i + k] < s[j + k]:
                i += k + 1
                k = 0
                if i >= j:
                    j = i + 1
            else:
                j += k + 1
                k = 0
        return s[i:]
```

### **Java**

```java
class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0;
        for (int j = 1, k = 0; j + k < n;) {
            int d = s.charAt(i + k) - s.charAt(j + k);
            if (d == 0) {
                ++k;
            } else if (d < 0) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string lastSubstring(string s) {
        int n = s.size();
        int i = 0;
        for (int j = 1, k = 0; j + k < n;) {
            if (s[i + k] == s[j + k]) {
                ++k;
            } else if (s[i + k] < s[j + k]) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substr(i);
    }
};
```

### **Go**

```go
func lastSubstring(s string) string {
	i, n := 0, len(s)
	for j, k := 1, 0; j+k < n; {
		if s[i+k] == s[j+k] {
			k++
		} else if s[i+k] < s[j+k] {
			i += k + 1
			k = 0
			if i >= j {
				j = i + 1
			}
		} else {
			j += k + 1
			k = 0
		}
	}
	return s[i:]
}
```

### **TypeScript**

```ts
function lastSubstring(s: string): string {
    const n = s.length;
    let i = 0;
    for (let j = 1, k = 0; j + k < n; ) {
        if (s[i + k] === s[j + k]) {
            ++k;
        } else if (s[i + k] < s[j + k]) {
            i += k + 1;
            k = 0;
            if (i >= j) {
                j = i + 1;
            }
        } else {
            j += k + 1;
            k = 0;
        }
    }
    return s.slice(i);
}
```

### **...**

```

```

<!-- tabs:end -->
