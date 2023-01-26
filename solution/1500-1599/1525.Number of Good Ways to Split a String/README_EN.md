# [1525. Number of Good Ways to Split a String](https://leetcode.com/problems/number-of-good-ways-to-split-a-string)

[中文文档](/solution/1500-1599/1525.Number%20of%20Good%20Ways%20to%20Split%20a%20String/README.md)

## Description

<p>You are given a string <code>s</code>.</p>

<p>A split is called <strong>good</strong> if you can split <code>s</code> into two non-empty strings <code>s<sub>left</sub></code> and <code>s<sub>right</sub></code> where their concatenation is equal to <code>s</code> (i.e., <code>s<sub>left</sub> + s<sub>right</sub> = s</code>) and the number of distinct letters in <code>s<sub>left</sub></code> and <code>s<sub>right</sub></code> is the same.</p>

<p>Return <em>the number of <strong>good splits</strong> you can make in <code>s</code></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aacaba&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 5 ways to split <code>&quot;aacaba&quot;</code> and 2 of them are good. 
(&quot;a&quot;, &quot;acaba&quot;) Left string and right string contains 1 and 3 different letters respectively.
(&quot;aa&quot;, &quot;caba&quot;) Left string and right string contains 1 and 3 different letters respectively.
(&quot;aac&quot;, &quot;aba&quot;) Left string and right string contains 2 and 2 different letters respectively (good split).
(&quot;aaca&quot;, &quot;ba&quot;) Left string and right string contains 2 and 2 different letters respectively (good split).
(&quot;aacab&quot;, &quot;a&quot;) Left string and right string contains 3 and 1 different letters respectively.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Split the string as follows (&quot;ab&quot;, &quot;cd&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSplits(self, s: str) -> int:
        cnt = Counter(s)
        vis = set()
        ans = 0
        for c in s:
            vis.add(c)
            cnt[c] -= 1
            if cnt[c] == 0:
                cnt.pop(c)
            ans += len(vis) == len(cnt)
        return ans
```

### **Java**

```java
class Solution {
    public int numSplits(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
            cnt.merge(c, 1, Integer::sum);
        }
        Set<Character> vis = new HashSet<>();
        int ans = 0;
        for (char c : s.toCharArray()) {
            vis.add(c);
            if (cnt.merge(c, -1, Integer::sum) == 0) {
                cnt.remove(c);
            }
            if (vis.size() == cnt.size()) {
                ++ans;
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
    int numSplits(string s) {
        unordered_map<char, int> cnt;
        for (char& c : s) {
            ++cnt[c];
        }
        unordered_set<char> vis;
        int ans = 0;
        for (char& c : s) {
            vis.insert(c);
            if (--cnt[c] == 0) {
                cnt.erase(c);
            }
            ans += vis.size() == cnt.size();
        }
        return ans;
    }
};
```

### **Go**

```go
func numSplits(s string) (ans int) {
	cnt := map[rune]int{}
	for _, c := range s {
		cnt[c]++
	}
	vis := map[rune]bool{}
	for _, c := range s {
		vis[c] = true
		cnt[c]--
		if cnt[c] == 0 {
			delete(cnt, c)
		}
		if len(vis) == len(cnt) {
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
