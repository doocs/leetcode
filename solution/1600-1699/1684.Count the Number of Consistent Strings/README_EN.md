# [1684. Count the Number of Consistent Strings](https://leetcode.com/problems/count-the-number-of-consistent-strings)

[中文文档](/solution/1600-1699/1684.Count%20the%20Number%20of%20Consistent%20Strings/README.md)

## Description

<p>You are given a string <code>allowed</code> consisting of <strong>distinct</strong> characters and an array of strings <code>words</code>. A string is <strong>consistent </strong>if all characters in the string appear in the string <code>allowed</code>.</p>

<p>Return<em> the number of <strong>consistent</strong> strings in the array </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;ab&quot;, words = [&quot;ad&quot;,&quot;bd&quot;,&quot;aaab&quot;,&quot;baa&quot;,&quot;badab&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Strings &quot;aaab&quot; and &quot;baa&quot; are consistent since they only contain characters &#39;a&#39; and &#39;b&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;abc&quot;, words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;ab&quot;,&quot;ac&quot;,&quot;bc&quot;,&quot;abc&quot;]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All strings are consistent.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;cad&quot;, words = [&quot;cc&quot;,&quot;acd&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bac&quot;,&quot;bad&quot;,&quot;ac&quot;,&quot;d&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Strings &quot;cc&quot;, &quot;acd&quot;, &quot;ac&quot;, and &quot;d&quot; are consistent.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= allowed.length &lt;=<sup> </sup>26</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li>The characters in <code>allowed</code> are <strong>distinct</strong>.</li>
	<li><code>words[i]</code> and <code>allowed</code> contain only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        s = set(allowed)
        return sum(all(c in s for c in w) for w in words)
```

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        def f(w):
            return reduce(or_, (1 << (ord(c) - ord('a')) for c in w))
        
        mask = f(allowed)
        return sum((mask | f(w)) == mask for w in words)
```

### **Java**

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] s = new boolean[26];
        for (char c : allowed.toCharArray()) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (String w : words) {
            if (check(w, s)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String w, boolean[] s) {
        for (int i = 0; i < w.length(); ++i) {
            if (!s[w.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = f(allowed);
        int ans = 0;
        for (String w : words) {
            if ((mask | f(w)) == mask) {
                ++ans;
            }
        }
        return ans;
    }

    private int f(String w) {
        int mask = 0;
        for (int i = 0; i < w.length(); ++i) {
            mask |= 1 << (w.charAt(i) - 'a');
        }
        return mask;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        bitset<26> s;
        for (auto& c : allowed) s[c - 'a'] = 1;
        int ans = 0;
        auto check = [&](string& w) {
            for (auto& c : w) if (!s[c - 'a']) return false;
            return true;
        };
        for (auto& w : words) ans += check(w);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        auto f = [](string& w) {
            int mask = 0;
            for (auto& c : w) mask |= 1 << (c - 'a');
            return mask;
        };
        int mask = f(allowed);
        int ans = 0;
        for (auto& w : words) ans += (mask | f(w)) == mask;
        return ans;
    }
};
```

### **Go**

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	s := [26]bool{}
	for _, c := range allowed {
		s[c-'a'] = true
	}
	check := func(w string) bool {
		for _, c := range w {
			if !s[c-'a'] {
				return false
			}
		}
		return true
	}
	for _, w := range words {
		if check(w) {
			ans++
		}
	}
	return ans
}
```

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	f := func(w string) (mask int) {
		for _, c := range w {
			mask |= 1 << (c - 'a')
		}
		return
	}

	mask := f(allowed)
	for _, w := range words {
		if (mask | f(w)) == mask {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
