# [1370. Increasing Decreasing String](https://leetcode.com/problems/increasing-decreasing-string)

[中文文档](/solution/1300-1399/1370.Increasing%20Decreasing%20String/README.md)

## Description

<p>You are given a string <code>s</code>. Reorder the string using the following algorithm:</p>

<ol>
	<li>Pick the <strong>smallest</strong> character from <code>s</code> and <strong>append</strong> it to the result.</li>
	<li>Pick the <strong>smallest</strong> character from <code>s</code> which is greater than the last appended character to the result and <strong>append</strong> it.</li>
	<li>Repeat step 2 until you cannot pick more characters.</li>
	<li>Pick the <strong>largest</strong> character from <code>s</code> and <strong>append</strong> it to the result.</li>
	<li>Pick the <strong>largest</strong> character from <code>s</code> which is smaller than the last appended character to the result and <strong>append</strong> it.</li>
	<li>Repeat step 5 until you cannot pick more characters.</li>
	<li>Repeat the steps from 1 to 6 until you pick all characters from <code>s</code>.</li>
</ol>

<p>In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.</p>

<p>Return <em>the result string after sorting </em><code>s</code><em> with this algorithm</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaabbbbcccc&quot;
<strong>Output:</strong> &quot;abccbaabccba&quot;
<strong>Explanation:</strong> After steps 1, 2 and 3 of the first iteration, result = &quot;abc&quot;
After steps 4, 5 and 6 of the first iteration, result = &quot;abccba&quot;
First iteration is done. Now s = &quot;aabbcc&quot; and we go back to step 1
After steps 1, 2 and 3 of the second iteration, result = &quot;abccbaabc&quot;
After steps 4, 5 and 6 of the second iteration, result = &quot;abccbaabccba&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;rat&quot;
<strong>Output:</strong> &quot;art&quot;
<strong>Explanation:</strong> The word &quot;rat&quot; becomes &quot;art&quot; after re-ordering it with the mentioned algorithm.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortString(self, s: str) -> str:
        counter = [0] * 26
        for c in s:
            counter[ord(c) - ord('a')] += 1
        ans = []
        while len(ans) < len(s):
            for i in range(26):
                if counter[i]:
                    ans.append(chr(i + ord('a')))
                    counter[i] -= 1
            for i in range(25, -1, -1):
                if counter[i]:
                    ans.append(chr(i + ord('a')))
                    counter[i] -= 1
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String sortString(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; ++i) {
                if (counter[i] > 0) {
                    sb.append((char) ('a' + i));
                    --counter[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (counter[i] > 0) {
                    sb.append((char) ('a' + i));
                    --counter[i];
                }
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string sortString(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        string ans = "";
        while (ans.size() < s.size()) {
            for (int i = 0; i < 26; ++i) {
                if (counter[i]) {
                    ans += (i + 'a');
                    --counter[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (counter[i]) {
                    ans += (i + 'a');
                    --counter[i];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sortString(s string) string {
	counter := ['z' + 1]int{}
	for _, c := range s {
		counter[c]++
	}
	var ans []byte
	for len(ans) < len(s) {
		for i := byte('a'); i <= 'z'; i++ {
			if counter[i] > 0 {
				ans = append(ans, i)
				counter[i]--
			}
		}
		for i := byte('z'); i >= 'a'; i-- {
			if counter[i] > 0 {
				ans = append(ans, i)
				counter[i]--
			}
		}
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
