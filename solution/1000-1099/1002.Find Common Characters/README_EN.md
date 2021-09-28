# [1002. Find Common Characters](https://leetcode.com/problems/find-common-characters)

[中文文档](/solution/1000-1099/1002.Find%20Common%20Characters/README.md)

## Description

<p>Given an array&nbsp;<code>A</code> of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list <strong>(including duplicates)</strong>.&nbsp;&nbsp;For example, if a character occurs 3 times&nbsp;in all strings but not 4 times, you need to include that character three times&nbsp;in the final answer.</p>

<p>You may return the answer in any order.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;bella&quot;,&quot;label&quot;,&quot;roller&quot;]</span>

<strong>Output: </strong><span id="example-output-1">[&quot;e&quot;,&quot;l&quot;,&quot;l&quot;]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[&quot;cool&quot;,&quot;lock&quot;,&quot;cook&quot;]</span>

<strong>Output: </strong><span id="example-output-2">[&quot;c&quot;,&quot;o&quot;]</span>

</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 100</code></li>
	<li><code>A[i][j]</code> is a lowercase letter</li>
</ol>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        freq = [10000] * 26
        for word in words:
            t = [0] * 26
            for c in word:
                t[ord(c) - ord('a')] += 1
            for i in range(26):
                freq[i] = min(freq[i], t[i])
        res = []
        for i in range(26):
            if freq[i] > 0:
                res.extend([chr(i + ord("a"))] * freq[i])
        return res
```

### **Java**

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        Arrays.fill(freq, 10000);
        for (String word : words) {
            int[] t = new int[26];
            for (char c : word.toCharArray()) {
                ++t[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                freq[i] = Math.min(freq[i], t[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (freq[i]-- > 0) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        vector<int> freq(26, 10000);
        for (auto word : words)
        {
            vector<int> t(26);
            for (char c : word)
                ++t[c - 'a'];
            for (int i = 0; i < 26; ++i)
                freq[i] = min(freq[i], t[i]);
        }
        vector<string> res;
        for (int i = 0; i < 26; i++)
        {
            while (freq[i]--)
                res.emplace_back(1, i + 'a');
        }
        return res;
    }
};
```

### **Go**

```go
func commonChars(words []string) []string {
	freq := make([]int, 26)
	for i := 0; i < 26; i++ {
		freq[i] = 10000
	}
	for _, word := range words {
		t := make([]int, 26)
		for _, c := range word {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			freq[i] = min(freq[i], t[i])
		}
	}
	var res []string
	for i := 0; i < 26; i++ {
		for j := 0; j < freq[i]; j++ {
			res = append(res, string('a'+i))
		}
	}
	return res
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
