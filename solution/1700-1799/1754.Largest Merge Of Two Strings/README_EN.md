# [1754. Largest Merge Of Two Strings](https://leetcode.com/problems/largest-merge-of-two-strings)

[中文文档](/solution/1700-1799/1754.Largest%20Merge%20Of%20Two%20Strings/README.md)

## Description

<p>You are given two strings <code>word1</code> and <code>word2</code>. You want to construct a string <code>merge</code> in the following way: while either <code>word1</code> or <code>word2</code> are non-empty, choose <strong>one</strong> of the following options:</p>

<ul>
	<li>If <code>word1</code> is non-empty, append the <strong>first</strong> character in <code>word1</code> to <code>merge</code> and delete it from <code>word1</code>.
    <ul>
    	<li>For example, if <code>word1 = &quot;abc&quot; </code>and <code>merge = &quot;dv&quot;</code>, then after choosing this operation, <code>word1 = &quot;bc&quot;</code> and <code>merge = &quot;dva&quot;</code>.</li>
    </ul>
    </li>
    <li>If <code>word2</code> is non-empty, append the <strong>first</strong> character in <code>word2</code> to <code>merge</code> and delete it from <code>word2</code>.
    <ul>
    	<li>For example, if <code>word2 = &quot;abc&quot; </code>and <code>merge = &quot;&quot;</code>, then after choosing this operation, <code>word2 = &quot;bc&quot;</code> and <code>merge = &quot;a&quot;</code>.</li>
    </ul>
    </li>
</ul>

<p>Return <em>the lexicographically <strong>largest</strong> </em><code>merge</code><em> you can construct</em>.</p>

<p>A string <code>a</code> is lexicographically larger than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly larger than the corresponding character in <code>b</code>. For example, <code>&quot;abcd&quot;</code> is lexicographically larger than <code>&quot;abcc&quot;</code> because the first position they differ is at the fourth character, and <code>d</code> is greater than <code>c</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;cabaa&quot;, word2 = &quot;bcaaa&quot;
<strong>Output:</strong> &quot;cbcabaaaaa&quot;
<strong>Explanation:</strong> One way to get the lexicographically largest merge is:
- Take from word1: merge = &quot;c&quot;, word1 = &quot;abaa&quot;, word2 = &quot;bcaaa&quot;
- Take from word2: merge = &quot;cb&quot;, word1 = &quot;abaa&quot;, word2 = &quot;caaa&quot;
- Take from word2: merge = &quot;cbc&quot;, word1 = &quot;abaa&quot;, word2 = &quot;aaa&quot;
- Take from word1: merge = &quot;cbca&quot;, word1 = &quot;baa&quot;, word2 = &quot;aaa&quot;
- Take from word1: merge = &quot;cbcab&quot;, word1 = &quot;aa&quot;, word2 = &quot;aaa&quot;
- Append the remaining 5 a&#39;s from word1 and word2 at the end of merge.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcabc&quot;, word2 = &quot;abdcaba&quot;
<strong>Output:</strong> &quot;abdcabcabcaba&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 3000</code></li>
	<li><code>word1</code> and <code>word2</code> consist only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestMerge(self, word1: str, word2: str) -> str:
        i = j = 0
        ans = []
        while i < len(word1) and j < len(word2):
            if word1[i:] > word2[j:]:
                ans.append(word1[i])
                i += 1
            else:
                ans.append(word2[j])
                j += 1
        ans.append(word1[i:])
        ans.append(word2[j:])
        return "".join(ans)
```

### **Java**

```java
class Solution {
    public String largestMerge(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < m && j < n) {
            boolean gt = word1.substring(i).compareTo(word2.substring(j)) > 0;
            ans.append(gt ? word1.charAt(i++) : word2.charAt(j++));
        }
        ans.append(word1.substring(i));
        ans.append(word2.substring(j));
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string largestMerge(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        int i = 0, j = 0;
        string ans;
        while (i < m && j < n) {
            bool gt = word1.substr(i) > word2.substr(j);
            ans += gt ? word1[i++] : word2[j++];
        }
        ans += word1.substr(i);
        ans += word2.substr(j);
        return ans;
    }
};
```

### **Go**

```go
func largestMerge(word1 string, word2 string) string {
	m, n := len(word1), len(word2)
	i, j := 0, 0
	var ans strings.Builder
	for i < m && j < n {
		if word1[i:] > word2[j:] {
			ans.WriteByte(word1[i])
			i++
		} else {
			ans.WriteByte(word2[j])
			j++
		}
	}
	ans.WriteString(word1[i:])
	ans.WriteString(word2[j:])
	return ans.String()
}
```

### **TypeScript**

```ts
function largestMerge(word1: string, word2: string): string {
    const m = word1.length;
    const n = word2.length;
    let ans = '';
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        ans += word1.slice(i) > word2.slice(j) ? word1[i++] : word2[j++];
    }
    ans += word1.slice(i);
    ans += word2.slice(j);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn largest_merge(word1: String, word2: String) -> String {
        let word1 = word1.as_bytes();
        let word2 = word2.as_bytes();
        let m = word1.len();
        let n = word2.len();
        let mut ans = String::new();
        let mut i = 0;
        let mut j = 0;
        while i < m && j < n {
            if word1[i..] > word2[j..] {
                ans.push(word1[i] as char);
                i += 1;
            } else {
                ans.push(word2[j] as char);
                j += 1;
            }
        }
        word1[i..].iter().for_each(|c| ans.push(*c as char));
        word2[j..].iter().for_each(|c| ans.push(*c as char));
        ans
    }
}
```

### **C**

```c
char *largestMerge(char *word1, char *word2) {
    int m = strlen(word1);
    int n = strlen(word2);
    int i = 0;
    int j = 0;
    char *ans = malloc((m + n + 1) * sizeof(char));
    while (i < m && j < n) {
        int k = 0;
        while (word1[i + k] && word2[j + k] && word1[i + k] == word2[j + k]) {
            k++;
        }
        if (word1[i + k] > word2[j + k]) {
            ans[i + j] = word1[i];
            i++;
        } else {
            ans[i + j] = word2[j];
            j++;
        };
    }
    while (word1[i]) {
        ans[i + j] = word1[i];
        i++;
    }
    while (word2[j]) {
        ans[i + j] = word2[j];
        j++;
    }
    ans[m + n] = '\0';
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
