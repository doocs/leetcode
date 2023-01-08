# [2531. Make Number of Distinct Characters Equal](https://leetcode.com/problems/make-number-of-distinct-characters-equal)

[中文文档](/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> strings <code>word1</code> and <code>word2</code>.</p>

<p>A <strong>move</strong> consists of choosing two indices <code>i</code> and <code>j</code> such that <code>0 &lt;= i &lt; word1.length</code> and <code>0 &lt;= j &lt; word2.length</code> and swapping <code>word1[i]</code> with <code>word2[j]</code>.</p>

<p>Return <code>true</code> <em>if it is possible to get the number of distinct characters in</em> <code>word1</code> <em>and</em> <code>word2</code> <em>to be equal with <strong>exactly one</strong> move. </em>Return <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;ac&quot;, word2 = &quot;b&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcc&quot;, word2 = &quot;aab&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = &quot;abac&quot; and word2 = &quot;cab&quot;, which both have 3 distinct characters.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcde&quot;, word2 = &quot;fghij&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> and <code>word2</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = [0] * 26
        cnt2 = [0] * 26
        for c in word1:
            cnt1[ord(c) - ord('a')] += 1
        for c in word2:
            cnt2[ord(c) - ord('a')] += 1
        for i, a in enumerate(cnt1):
            for j, b in enumerate(cnt2):
                if a and b:
                    cnt1[i], cnt2[j] = cnt1[i] - 1, cnt2[j] - 1
                    cnt1[j], cnt2[i] = cnt1[j] + 1, cnt2[i] + 1
                    if sum(v > 0 for v in cnt1) == sum(v > 0 for v in cnt2):
                        return True
                    cnt1[i], cnt2[j] = cnt1[i] + 1, cnt2[j] + 1
                    cnt1[j], cnt2[i] = cnt1[j] - 1, cnt2[i] - 1
        return False
```

### **Java**

```java
class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < word1.length(); ++i) {
            ++cnt1[word1.charAt(i) - 'a'];
        }
        for (int i = 0; i < word2.length(); ++i) {
            ++cnt2[word2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    --cnt1[i];
                    --cnt2[j];
                    ++cnt1[j];
                    ++cnt2[i];
                    int d = 0;
                    for (int k = 0; k < 26; ++k) {
                        if (cnt1[k] > 0) {
                            ++d;
                        }
                        if (cnt2[k] > 0) {
                            --d;
                        }
                    }
                    if (d == 0) {
                        return true;
                    }
                    ++cnt1[i];
                    ++cnt2[j];
                    --cnt1[j];
                    --cnt2[i];
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : word1) {
            ++cnt1[c - 'a'];
        }
        for (char& c : word2) {
            ++cnt2[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    --cnt1[i];
                    --cnt2[j];
                    ++cnt1[j];
                    ++cnt2[i];
                    int d = 0;
                    for (int k = 0; k < 26; ++k) {
                        if (cnt1[k] > 0) {
                            ++d;
                        }
                        if (cnt2[k] > 0) {
                            --d;
                        }
                    }
                    if (d == 0) {
                        return true;
                    }
                    ++cnt1[i];
                    ++cnt2[j];
                    --cnt1[j];
                    --cnt2[i];
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func isItPossible(word1 string, word2 string) bool {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	for i := range cnt1 {
		for j := range cnt2 {
			if cnt1[i] > 0 && cnt2[j] > 0 {
				cnt1[i], cnt2[j] = cnt1[i]-1, cnt2[j]-1
				cnt1[j], cnt2[i] = cnt1[j]+1, cnt2[i]+1
				d := 0
				for k, a := range cnt1 {
					if a > 0 {
						d++
					}
					if cnt2[k] > 0 {
						d--
					}
				}
				if d == 0 {
					return true
				}
				cnt1[i], cnt2[j] = cnt1[i]+1, cnt2[j]+1
				cnt1[j], cnt2[i] = cnt1[j]-1, cnt2[i]-1
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
