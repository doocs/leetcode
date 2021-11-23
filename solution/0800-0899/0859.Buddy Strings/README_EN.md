# [859. Buddy Strings](https://leetcode.com/problems/buddy-strings)

[中文文档](/solution/0800-0899/0859.Buddy%20Strings/README.md)

## Description

<p>Given two strings <code>a</code> and <code>b</code>, return <code>true</code><em> if you can swap two letters in </em><code>a</code><em> so the result is equal to </em><code>b</code><em>, otherwise, return </em><code>false</code><em>.</em></p>

<p>Swapping letters is defined as taking two indices <code>i</code> and <code>j</code> (0-indexed) such that <code>i != j</code> and swapping the characters at <code>a[i]</code> and <code>a[j]</code>.</p>

<ul>
	<li>For example, swapping at indices <code>0</code> and <code>2</code> in <code>&quot;abcd&quot;</code> results in <code>&quot;cbad&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;ab&quot;, b = &quot;ba&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can swap a[0] = &#39;a&#39; and a[1] = &#39;b&#39; to get &quot;ba&quot;, which is equal to b.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;ab&quot;, b = &quot;ab&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The only letters you can swap are a[0] = &#39;a&#39; and a[1] = &#39;b&#39;, which results in &quot;ba&quot; != b.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aa&quot;, b = &quot;aa&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can swap a[0] = &#39;a&#39; and a[1] = &#39;a&#39; to get &quot;aa&quot;, which is equal to b.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aaaaaaabc&quot;, b = &quot;aaaaaaacb&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>a</code> and <code>b</code> consist of lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        m, n = len(s), len(goal)
        if m != n:
            return False
        diff = sum(1 for i in range(n) if s[i] != goal[i])
        cnt1, cnt2 = Counter(s), Counter(goal)
        if cnt1 != cnt2:
            return False
        return diff == 2 or (diff == 0 and any(e > 1 for e in cnt1.values()))
```

### **Java**

```java
class Solution {
    public boolean buddyStrings(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        int diff = 0;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            int a = s.charAt(i), b = goal.charAt(i);
            ++cnt1[a - 'a'];
            ++cnt2[b - 'a'];
            if (a != b) {
                ++diff;
            }
        }
        boolean f = false;
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
            if (cnt1[i] > 1) {
                f = true;
            }
        }
        return diff == 2 || (diff == 0 && f);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool buddyStrings(string s, string goal) {
        int m = s.size(), n = goal.size();
        if (m != n) return false;
        int diff = 0;
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (int i = 0; i < n; ++i)
        {
            ++cnt1[s[i] - 'a'];
            ++cnt2[goal[i] - 'a'];
            if (s[i] != goal[i]) ++diff;
        }
        bool f = false;
        for (int i = 0; i < 26; ++i)
        {
            if (cnt1[i] != cnt2[i]) return false;
            if (cnt1[i] > 1) f = true;
        }
        return diff == 2 || (diff == 0 && f);
    }
};
```

### **Go**

```go
func buddyStrings(s string, goal string) bool {
	m, n := len(s), len(goal)
	if m != n {
		return false
	}
	diff := 0
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for i := 0; i < n; i++ {
		cnt1[s[i]-'a']++
		cnt2[goal[i]-'a']++
		if s[i] != goal[i] {
			diff++
		}
	}
	f := false
	for i := 0; i < 26; i++ {
		if cnt1[i] != cnt2[i] {
			return false
		}
		if cnt1[i] > 1 {
			f = true
		}
	}
	return diff == 2 || (diff == 0 && f)
}
```

### **...**

```

```

<!-- tabs:end -->
