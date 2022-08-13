# [17.13. Re-Space](https://leetcode.cn/problems/re-space-lcci)

[中文文档](/lcci/17.13.Re-Space/README.md)

## Description

<p>Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A sentence like &quot;I reset the computer. It still didn&#39;t boot!&quot; became &quot;iresetthecomputeritstilldidntboot&#39;&#39;. You&#39;ll deal with the punctuation and capi&shy;talization later; right now you need to re-insert the spaces. Most of the words are in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm to unconcatenate the document in a way that minimizes the number of unrecognized characters. Return the number of unrecognized characters.</p>

<p><strong>Note: </strong>This&nbsp;problem is slightly different from the original one in the book.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

dictionary = [&quot;looked&quot;,&quot;just&quot;,&quot;like&quot;,&quot;her&quot;,&quot;brother&quot;]

sentence = &quot;jesslookedjustliketimherbrother&quot;

<strong>Output: </strong> 7

<strong>Explanation: </strong> After unconcatenating, we got &quot;<strong>jess</strong> looked just like <strong>tim</strong> her brother&quot;, which containing 7 unrecognized characters.

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt;= len(sentence) &lt;= 1000</code></li>
	<li><code><font face="sans-serif, Arial, Verdana, Trebuchet MS">The total number of characters in&nbsp;</font>dictionary</code>&nbsp;is less than or equal to 150000.</li>
	<li>There are only lowercase letters in&nbsp;<code>dictionary</code>&nbsp;and&nbsp;<code>sentence</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def respace(self, dictionary: List[str], sentence: str) -> int:
        s = set(dictionary)
        n = len(sentence)
        dp = [0] * (n + 1)
        for i in range(1, n + 1):
            dp[i] = dp[i - 1] + 1
            for j in range(i):
                if sentence[j:i] in s:
                    dp[i] = min(dp[i], dp[j])
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int respace(String[] dictionary, String sentence) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (dict.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int respace(vector<string>& dictionary, string sentence) {
        unordered_set<string> s(dictionary.begin(), dictionary.end());
        int n = sentence.size();
        vector<int> dp(n + 1);
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (s.count(sentence.substr(j, i - j))) {
                    dp[i] = min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func respace(dictionary []string, sentence string) int {
	s := map[string]bool{}
	for _, v := range dictionary {
		s[v] = true
	}
	n := len(sentence)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		dp[i] = dp[i-1] + 1
		for j := 0; j < i; j++ {
			if s[sentence[j:i]] {
				dp[i] = min(dp[i], dp[j])
			}
		}
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
