# [01.02. Check Permutation](https://leetcode-cn.com/problems/check-permutation-lcci)

[中文文档](/lcci/01.02.Check%20Permutation/README.md)

## Description

<p>Given two strings,write a method to decide if one is a permutation of the other.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bca&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bad&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Note:</strong></p>
<ol>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        n1, n2 = len(s1), len(s2)
        if n1 != n2:
            return False
        counter = Counter()
        for i in range(n1):
            counter[s1[i]] += 1
            counter[s2[i]] -= 1
        for val in counter.values():
            if val != 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 != n2) {
            return false;
        }
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < n1; ++i) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);
        }
        for (int val : counter.values()) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
var CheckPermutation = function (s1, s2) {
    let n1 = s1.length,
        n2 = s2.length;
    if (n1 != n2) return false;
    let counter = {};
    for (let i = 0; i < n1; i++) {
        let cur1 = s1.charAt(i),
            cur2 = s2.charAt(i);
        counter[cur1] = (counter[cur1] || 0) + 1;
        counter[cur2] = (counter[cur2] || 0) - 1;
    }
    return Object.values(counter).every(v => v == 0);
};
```

### **Go**

```go
func CheckPermutation(s1 string, s2 string) bool {
	freq := make(map[rune]int)
	for _, r := range s1 {
		freq[r]++
	}
	for _, r := range s2 {
		if freq[r] == 0 {
			return false
		}
		freq[r]--
	}
	for _, v := range freq {
		if v != 0 {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
