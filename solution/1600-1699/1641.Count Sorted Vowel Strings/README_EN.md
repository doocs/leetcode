# [1641. Count Sorted Vowel Strings](https://leetcode.com/problems/count-sorted-vowel-strings)

[中文文档](/solution/1600-1699/1641.Count%20Sorted%20Vowel%20Strings/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>the number of strings of length </em><code>n</code><em> that consist only of vowels (</em><code>a</code><em>, </em><code>e</code><em>, </em><code>i</code><em>, </em><code>o</code><em>, </em><code>u</code><em>) and are <strong>lexicographically sorted</strong>.</em></p>

<p>A string <code>s</code> is <strong>lexicographically sorted</strong> if for all valid <code>i</code>, <code>s[i]</code> is the same as or comes before <code>s[i+1]</code> in the alphabet.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> The 5 sorted strings that consist of vowels only are <code>[&quot;a&quot;,&quot;e&quot;,&quot;i&quot;,&quot;o&quot;,&quot;u&quot;].</code>
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 15
<strong>Explanation:</strong> The 15 sorted strings that consist of vowels only are
[&quot;aa&quot;,&quot;ae&quot;,&quot;ai&quot;,&quot;ao&quot;,&quot;au&quot;,&quot;ee&quot;,&quot;ei&quot;,&quot;eo&quot;,&quot;eu&quot;,&quot;ii&quot;,&quot;io&quot;,&quot;iu&quot;,&quot;oo&quot;,&quot;ou&quot;,&quot;uu&quot;].
Note that &quot;ea&quot; is not a valid string since &#39;e&#39; comes after &#39;a&#39; in the alphabet.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 33
<strong>Output:</strong> 66045
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code>&nbsp;</li>
</ul>

## Solutions

```bash
a	e	i	o 	u
1	1	1	1	1		n=1
5	4	3	2	1		n=2
15	10	6	3	1		n=3
...						n=...
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countVowelStrings(self, n: int) -> int:
        cnt = [1] * 5
        for i in range(2, n + 1):
            for j in range(3, -1, -1):
                cnt[j] += cnt[j + 1]
        return sum(cnt)
```

### **Java**

```java
class Solution {
    public int countVowelStrings(int n) {
        int[] cnt = new int[5];
        Arrays.fill(cnt, 1);
        for (int i = 2; i <= n; ++i) {
            for (int j = 3; j >= 0; --j) {
                cnt[j] += cnt[j + 1];
            }
        }
        return Arrays.stream(cnt).sum();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countVowelStrings(int n) {
        vector<int> cnt(5, 1);
        for (int i = 2; i <= n; ++i)
            for (int j = 3; j >= 0; --j)
                cnt[j] += cnt[j + 1];
        return accumulate(cnt.begin(), cnt.end(), 0);
    }
};
```

### **Go**

```go
func countVowelStrings(n int) int {
	cnt := make([]int, 5)
	for i := range cnt {
		cnt[i] = 1
	}
	for i := 2; i <= n; i++ {
		for j := 3; j >= 0; j-- {
			cnt[j] += cnt[j+1]
		}
	}
	ans := 0
	for _, v := range cnt {
		ans += v
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
