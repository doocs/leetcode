# [771. Jewels and Stones](https://leetcode.com/problems/jewels-and-stones)

[中文文档](/solution/0700-0799/0771.Jewels%20and%20Stones/README.md)

## Description

<p>You&#39;re given strings <code>jewels</code> representing the types of stones that are jewels, and <code>stones</code> representing the stones you have. Each character in <code>stones</code> is a type of stone you have. You want to know how many of the stones you have are also jewels.</p>

<p>Letters are case sensitive, so <code>&quot;a&quot;</code> is considered a different type of stone from <code>&quot;A&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> jewels = "aA", stones = "aAAbbbb"
<strong>Output:</strong> 3
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> jewels = "z", stones = "ZZ"
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;jewels.length, stones.length &lt;= 50</code></li>
	<li><code>jewels</code> and <code>stones</code> consist of only English letters.</li>
	<li>All the characters of&nbsp;<code>jewels</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        s = set(jewels)
        return sum([1 for c in stones if c in s])
```

### **Java**

```java
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> s = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            s.add(c);
        }
        int res = 0;
        for (char c : stones.toCharArray()) {
            res += (s.contains(c) ? 1 : 0);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        unordered_set<char> s;
        for (char c : jewels) {
            s.insert(c);
        }
        int res = 0;
        for (char c : stones) {
            res += s.count(c);
        }
        return res;
    }
};
```

### **Go**

```go
func numJewelsInStones(jewels string, stones string) int {
	s := make(map[rune]bool)
	for _, c := range jewels {
		s[c] = true
	}
	res := 0
	for _, c := range stones {
		if s[c] {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
