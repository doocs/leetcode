# [3019. Number of Changing Keys](https://leetcode.com/problems/number-of-changing-keys)

[中文文档](/solution/3000-3099/3019.Number%20of%20Changing%20Keys/README.md)

## Description

<p>You are given a <strong>0-indexed </strong>string <code>s</code> typed by a user. Changing a key is defined as using a key different from the last used key. For example, <code>s = &quot;ab&quot;</code> has a change of a key while <code>s = &quot;bBBb&quot;</code> does not have any.</p>

<p>Return <em>the number of times the user had to change the key. </em></p>

<p><strong>Note: </strong>Modifiers like <code>shift</code> or <code>caps lock</code> won&#39;t be counted in changing the key that is if a user typed the letter <code>&#39;a&#39;</code> and then the letter <code>&#39;A&#39;</code> then it will not be considered as a changing of key.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aAbBcC&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
From s[0] = &#39;a&#39; to s[1] = &#39;A&#39;, there is no change of key as caps lock or shift is not counted.
From s[1] = &#39;A&#39; to s[2] = &#39;b&#39;, there is a change of key.
From s[2] = &#39;b&#39; to s[3] = &#39;B&#39;, there is no change of key as caps lock or shift is not counted.
From s[3] = &#39;B&#39; to s[4] = &#39;c&#39;, there is a change of key.
From s[4] = &#39;c&#39; to s[5] = &#39;C&#39;, there is no change of key as caps lock or shift is not counted.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AaAaAaaA&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no change of key since only the letters &#39;a&#39; and &#39;A&#39; are<!-- notionvc: 8849fe75-f31e-41dc-a2e0-b7d33d8427d2 --> pressed which does not require change of key.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of only upper case and lower case English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(s.lower()[i] != s.lower()[i-1] for i in range(1, len(s)))
```

```java
class Solution {
    public int countKeyChanges(String s) {
        int n = s.length();
        int count = 0;
        String lowerCaseString = s.toLowerCase();
        for (int i = 0; i < n - 1; i++) {
            if (lowerCaseString.charAt(i) != lowerCaseString.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }
}
```

```cpp
class Solution {
public:
    int countKeyChanges(string s) {
        int n = s.size();
        int c = 0;
        transform(s.begin(), s.end(), s.begin(), ::tolower);
        for (int i = 0; i < n - 1; i++) {
            if (s[i] != s[i + 1]) {
                c++;
            }
        }
        return c;
    }
};
```

```go
func countKeyChanges(s string) int {
    n := len(s)
	count := 0
	s = strings.ToLower(s)
	for i := 0; i < n-1; i++ {
		if s[i] != s[i+1] {
			count++
		}
	}
	return count
}
```

<!-- tabs:end -->

<!-- end -->
