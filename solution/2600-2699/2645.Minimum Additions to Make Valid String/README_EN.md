# [2645. Minimum Additions to Make Valid String](https://leetcode.com/problems/minimum-additions-to-make-valid-string)

[中文文档](/solution/2600-2699/2645.Minimum%20Additions%20to%20Make%20Valid%20String/README.md)

## Description

<p>Given a string <code>word</code> to which you can insert letters &quot;a&quot;, &quot;b&quot; or &quot;c&quot; anywhere and any number of times, return <em>the minimum number of letters that must be inserted so that <code>word</code> becomes <strong>valid</strong>.</em></p>

<p>A string is called <strong>valid </strong>if it can be formed by concatenating the string &quot;abc&quot; several times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;b&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Insert the letter &quot;a&quot; right before &quot;b&quot;, and the letter &quot;c&quot; right next to &quot;a&quot; to obtain the valid string &quot;<strong>a</strong>b<strong>c</strong>&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aaa&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Insert letters &quot;b&quot; and &quot;c&quot; next to each &quot;a&quot; to obtain the valid string &quot;a<strong>bc</strong>a<strong>bc</strong>a<strong>bc</strong>&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abc&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> word is already valid. No modifications are needed. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>word</code> consists of letters &quot;a&quot;, &quot;b&quot;&nbsp;and &quot;c&quot; only.&nbsp;</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addMinimum(self, word: str) -> int:
        s = 'abc'
        ans, n = 0, len(word)
        i = j = 0
        while j < n:
            if word[j] != s[i]:
                ans += 1
            else:
                j += 1
            i = (i + 1) % 3
        if word[-1] != 'c':
            ans += 1 if word[-1] == 'b' else 2
        return ans
```

### **Java**

```java
class Solution {
    public int addMinimum(String word) {
        String s = "abc";
        int ans = 0, n = word.length();
        for (int i = 0, j = 0; j < n; i = (i + 1) % 3) {
            if (word.charAt(j) != s.charAt(i)) {
                ++ans;
            } else {
                ++j;
            }
        }
        if (word.charAt(n - 1) != 'c') {
            ans += word.charAt(n - 1) == 'b' ? 1 : 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int addMinimum(string word) {
        string s = "abc";
        int ans = 0, n = word.size();
        for (int i = 0, j = 0; j < n; i = (i + 1) % 3) {
            if (word[j] != s[i]) {
                ++ans;
            } else {
                ++j;
            }
        }
        if (word[n - 1] != 'c') {
            ans += word[n - 1] == 'b' ? 1 : 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func addMinimum(word string) (ans int) {
	s := "abc"
	n := len(word)
	for i, j := 0, 0; j < n; i = (i + 1) % 3 {
		if word[j] != s[i] {
			ans++
		} else {
			j++
		}
	}
	if word[n-1] == 'b' {
		ans++
	} else if word[n-1] == 'a' {
		ans += 2
	}
	return
}
```

### **TypeScript**

```ts
function addMinimum(word: string): number {
    const s: string = 'abc';
    let ans: number = 0;
    const n: number = word.length;
    for (let i = 0, j = 0; j < n; i = (i + 1) % 3) {
        if (word[j] !== s[i]) {
            ++ans;
        } else {
            ++j;
        }
    }
    if (word[n - 1] === 'b') {
        ++ans;
    } else if (word[n - 1] === 'a') {
        ans += 2;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
