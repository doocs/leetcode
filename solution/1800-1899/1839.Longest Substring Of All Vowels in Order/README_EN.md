# [1839. Longest Substring Of All Vowels in Order](https://leetcode.com/problems/longest-substring-of-all-vowels-in-order)

[中文文档](/solution/1800-1899/1839.Longest%20Substring%20Of%20All%20Vowels%20in%20Order/README.md)

## Description

<p>A string is considered <strong>beautiful</strong> if it satisfies the following conditions:</p>

<ul>
	<li>Each of the 5 English vowels (<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>) must appear <strong>at least once</strong> in it.</li>
	<li>The letters must be sorted in <strong>alphabetical order</strong> (i.e. all <code>&#39;a&#39;</code>s before <code>&#39;e&#39;</code>s, all <code>&#39;e&#39;</code>s before <code>&#39;i&#39;</code>s, etc.).</li>
</ul>

<p>For example, strings <code>&quot;aeiou&quot;</code> and <code>&quot;aaaaaaeiiiioou&quot;</code> are considered <strong>beautiful</strong>, but <code>&quot;uaeio&quot;</code>, <code>&quot;aeoiu&quot;</code>, and <code>&quot;aaaeeeooo&quot;</code> are <strong>not beautiful</strong>.</p>

<p>Given a string <code>word</code> consisting of English vowels, return <em>the <strong>length of the longest beautiful substring</strong> of </em><code>word</code><em>. If no such substring exists, return </em><code>0</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aeiaaio<u>aaaaeiiiiouuu</u>ooaauuaeiu&quot;
<strong>Output:</strong> 13
<b>Explanation:</b> The longest beautiful substring in word is &quot;aaaaeiiiiouuu&quot; of length 13.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aeeeiiiioooauuu<u>aeiou</u>&quot;
<strong>Output:</strong> 5
<b>Explanation:</b> The longest beautiful substring in word is &quot;aeiou&quot; of length 5.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;a&quot;
<strong>Output:</strong> 0
<b>Explanation:</b> There is no beautiful substring, so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists of characters <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestBeautifulSubstring(self, word: str) -> int:
        arr = []
        n = len(word)
        i = 0
        while i < n:
            j = i
            while j < n and word[j] == word[i]:
                j += 1
            arr.append((word[i], j - i))
            i = j
        ans = 0
        for i in range(len(arr) - 4):
            a, b, c, d, e = arr[i : i + 5]
            if a[0] + b[0] + c[0] + d[0] + e[0] == "aeiou":
                ans = max(ans, a[1] + b[1] + c[1] + d[1] + e[1])
        return ans
```

### **Java**

```java
class Solution {
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        List<Node> arr = new ArrayList<>();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                ++j;
            }
            arr.add(new Node(word.charAt(i), j - i));
            i = j;
        }
        int ans = 0;
        for (int i = 0; i < arr.size() - 4; ++i) {
            Node a = arr.get(i), b = arr.get(i + 1), c = arr.get(i + 2), d = arr.get(i + 3),
                 e = arr.get(i + 4);
            if (a.c == 'a' && b.c == 'e' && c.c == 'i' && d.c == 'o' && e.c == 'u') {
                ans = Math.max(ans, a.v + b.v + c.v + d.v + e.v);
            }
        }
        return ans;
    }
}

class Node {
    char c;
    int v;

    Node(char c, int v) {
        this.c = c;
        this.v = v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestBeautifulSubstring(string word) {
        vector<pair<char, int>> arr;
        int n = word.size();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && word[j] == word[i]) ++j;
            arr.push_back({word[i], j - i});
            i = j;
        }
        int ans = 0;
        for (int i = 0; i < (int) arr.size() - 4; ++i) {
            auto& [a, v1] = arr[i];
            auto& [b, v2] = arr[i + 1];
            auto& [c, v3] = arr[i + 2];
            auto& [d, v4] = arr[i + 3];
            auto& [e, v5] = arr[i + 4];
            if (a == 'a' && b == 'e' && c == 'i' && d == 'o' && e == 'u') {
                ans = max(ans, v1 + v2 + v3 + v4 + v5);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestBeautifulSubstring(word string) (ans int) {
	arr := []pair{}
	n := len(word)
	for i := 0; i < n; {
		j := i
		for j < n && word[j] == word[i] {
			j++
		}
		arr = append(arr, pair{word[i], j - i})
		i = j
	}
	for i := 0; i < len(arr)-4; i++ {
		a, b, c, d, e := arr[i], arr[i+1], arr[i+2], arr[i+3], arr[i+4]
		if a.c == 'a' && b.c == 'e' && c.c == 'i' && d.c == 'o' && e.c == 'u' {
			ans = max(ans, a.v+b.v+c.v+d.v+e.v)
		}
	}
	return
}

type pair struct {
	c byte
	v int
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
