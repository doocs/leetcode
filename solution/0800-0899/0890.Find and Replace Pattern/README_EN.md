# [890. Find and Replace Pattern](https://leetcode.com/problems/find-and-replace-pattern)

[中文文档](/solution/0800-0899/0890.Find%20and%20Replace%20Pattern/README.md)

## Description

<p>You have a list of&nbsp;<code>words</code> and a <code>pattern</code>, and you want to know which words in <code>words</code> matches the pattern.</p>

<p>A word matches the pattern if there exists a permutation of letters <code>p</code> so that after replacing every letter <code>x</code> in the pattern with <code>p(x)</code>, we get the desired word.</p>

<p>(<em>Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.</em>)</p>

<p>Return a list of the words in <code>words</code>&nbsp;that match the given pattern.&nbsp;</p>

<p>You may return the answer in any order.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>words = <span id="example-input-1-1">[&quot;abc&quot;,&quot;deq&quot;,&quot;mee&quot;,&quot;aqq&quot;,&quot;dkd&quot;,&quot;ccc&quot;]</span>, pattern = <span id="example-input-1-2">&quot;abb&quot;</span>

<strong>Output: </strong><span id="example-output-1">[&quot;mee&quot;,&quot;aqq&quot;]</span>

<strong><span>Explanation: </span></strong>&quot;mee&quot; matches the pattern because there is a permutation {a -&gt; m, b -&gt; e, ...}. 

&quot;ccc&quot; does not match the pattern because {a -&gt; c, b -&gt; c, ...} is not a permutation,

since a and b map to the same letter.</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= pattern.length = words[i].length&nbsp;&lt;= 20</code></li>
</ul>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        def match(s, t):
            m1, m2 = [0] * 128, [0] * 128
            for i in range(n):
                if m1[ord(s[i])] != m2[ord(t[i])]:
                    return False
                m1[ord(s[i])] = m2[ord(t[i])] = i + 1
            return True

        n = len(pattern)
        return [word for word in words if match(word, pattern)]
```

### **Java**

```java
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String s, String t) {
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (m1[c1] != m2[c2]) {
                return false;
            }
            m1[c1] = i + 1;
            m2[c2] = i + 1;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        for (auto& word : words)
            if (match(word, pattern))
                ans.push_back(word);
        return ans;
    }

    bool match(string s, string t) {
        vector<int> m1(128);
        vector<int> m2(128);
        for (int i = 0; i < s.size(); ++i)
        {
            if (m1[s[i]] != m2[t[i]]) return 0;
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return 1;
    }
};
```

### **Go**

```go
func findAndReplacePattern(words []string, pattern string) []string {
	match := func(s, t string) bool {
		m1, m2 := make([]int, 128), make([]int, 128)
		for i := 0; i < len(s); i++ {
			if m1[s[i]] != m2[t[i]] {
				return false
			}
			m1[s[i]] = i + 1
			m2[t[i]] = i + 1
		}
		return true
	}
	var ans []string
	for _, word := range words {
		if match(word, pattern) {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
