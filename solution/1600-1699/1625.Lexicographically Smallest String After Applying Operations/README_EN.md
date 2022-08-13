# [1625. Lexicographically Smallest String After Applying Operations](https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations)

[中文文档](/solution/1600-1699/1625.Lexicographically%20Smallest%20String%20After%20Applying%20Operations/README.md)

## Description

<p>You are given a string <code>s</code> of <strong>even length</strong> consisting of digits from <code>0</code> to <code>9</code>, and two integers <code>a</code> and <code>b</code>.</p>

<p>You can apply either of the following two operations any number of times and in any order on <code>s</code>:</p>

<ul>
	<li>Add <code>a</code> to all odd indices of <code>s</code> <strong>(0-indexed)</strong>. Digits post <code>9</code> are cycled back to <code>0</code>. For example, if <code>s = &quot;3456&quot;</code> and <code>a = 5</code>, <code>s</code> becomes <code>&quot;3951&quot;</code>.</li>
	<li>Rotate <code>s</code> to the right by <code>b</code> positions. For example, if <code>s = &quot;3456&quot;</code> and <code>b = 1</code>, <code>s</code> becomes <code>&quot;6345&quot;</code>.</li>
</ul>

<p>Return <em>the <strong>lexicographically smallest</strong> string you can obtain by applying the above operations any number of times on</em> <code>s</code>.</p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>. For example, <code>&quot;0158&quot;</code> is lexicographically smaller than <code>&quot;0190&quot;</code> because the first position they differ is at the third letter, and <code>&#39;5&#39;</code> comes before <code>&#39;9&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;5525&quot;, a = 9, b = 2
<strong>Output:</strong> &quot;2050&quot;
<strong>Explanation:</strong> We can apply the following operations:
Start:  &quot;5525&quot;
Rotate: &quot;2555&quot;
Add:    &quot;2454&quot;
Add:    &quot;2353&quot;
Rotate: &quot;5323&quot;
Add:    &quot;5222&quot;
Add:    &quot;5121&quot;
Rotate: &quot;2151&quot;
​​​​​​​Add:    &quot;2050&quot;​​​​​​​​​​​​
There is no way to obtain a string that is lexicographically smaller then &quot;2050&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;74&quot;, a = 5, b = 1
<strong>Output:</strong> &quot;24&quot;
<strong>Explanation:</strong> We can apply the following operations:
Start:  &quot;74&quot;
Rotate: &quot;47&quot;
​​​​​​​Add:    &quot;42&quot;
​​​​​​​Rotate: &quot;24&quot;​​​​​​​​​​​​
There is no way to obtain a string that is lexicographically smaller then &quot;24&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0011&quot;, a = 4, b = 2
<strong>Output:</strong> &quot;0011&quot;
<strong>Explanation:</strong> There are no sequence of operations that will give us a lexicographically smaller string than &quot;0011&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of digits from <code>0</code> to <code>9</code> only.</li>
	<li><code>1 &lt;= a &lt;= 9</code></li>
	<li><code>1 &lt;= b &lt;= s.length - 1</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        q = deque([s])
        vis = {s}
        ans = s
        while q:
            s = q.popleft()
            if s < ans:
                ans = s
            nxt1 = ''.join(
                [str((int(c) + a) % 10) if i & 1 else c for i, c in enumerate(s)]
            )
            nxt2 = s[-b:] + s[:-b]
            for nxt in (nxt1, nxt2):
                if nxt not in vis:
                    vis.add(nxt)
                    q.append(nxt)
        return ans
```

### **Java**

```java
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        String ans = s;
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.compareTo(ans) < 0) {
                ans = s;
            }
            char[] cs = s.toCharArray();
            for (int i = 1; i < cs.length; i += 2) {
                cs[i] = (char) (((cs[i] - '0' + a) % 10) + '0');
            }
            String nxt1 = String.valueOf(cs);
            String nxt2 = s.substring(b) + s.substring(0, b);
            for (String nxt : new String[]{nxt1, nxt2}) {
                if (!vis.contains(nxt)) {
                    vis.add(nxt);
                    q.offer(nxt);
                }
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
    string findLexSmallestString(string s, int a, int b) {
        unordered_set<string> vis {{s}};
        queue<string> q {{s}};
        string ans = s;
        int n = s.size();
        while (!q.empty()) {
            s = q.front();
            q.pop();
            if (s < ans) ans = s;
            string nxt1 = s;
            for (int i = 1; i < n; i += 2) nxt1[i] = ((nxt1[i] - '0' + a) % 10) + '0';
            string nxt2 = s.substr(n - b) + s.substr(0, n - b);
            for (string nxt : {nxt1, nxt2}) {
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLexSmallestString(s string, a int, b int) string {
	q := []string{s}
	vis := map[string]bool{s: true}
	ans := s
	for len(q) > 0 {
		s = q[0]
		q = q[1:]
		if s < ans {
			ans = s
		}
		for _, nxt := range []string{op1(s, a), op2(s, b)} {
			if !vis[nxt] {
				vis[nxt] = true
				q = append(q, nxt)
			}
		}
	}
	return ans
}

func op1(s string, a int) string {
	res := []byte(s)
	for i := 1; i < len(s); i += 2 {
		res[i] = byte((int(res[i]-'0')+a)%10 + '0')
	}
	return string(res)
}

func op2(s string, b int) string {
	return s[len(s)-b:] + s[:len(s)-b]
}
```

### **...**

```

```

<!-- tabs:end -->
