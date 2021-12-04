# [1160. Find Words That Can Be Formed by Characters](https://leetcode.com/problems/find-words-that-can-be-formed-by-characters)

[中文文档](/solution/1100-1199/1160.Find%20Words%20That%20Can%20Be%20Formed%20by%20Characters/README.md)

## Description

<p>You are given an array of strings&nbsp;<code>words</code>&nbsp;and a string&nbsp;<code>chars</code>.</p>

<p>A string is <em>good</em>&nbsp;if&nbsp;it can be formed by&nbsp;characters from <code>chars</code>&nbsp;(each character&nbsp;can only be used once).</p>

<p>Return the sum of lengths of all good strings in <code>words</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>words = <span id="example-input-1-1">[&quot;cat&quot;,&quot;bt&quot;,&quot;hat&quot;,&quot;tree&quot;]</span>, chars = <span id="example-input-1-2">&quot;atach&quot;</span>

<strong>Output: </strong><span id="example-output-1">6</span>

<strong>Explanation: </strong>

The strings that can be formed are &quot;cat&quot; and &quot;hat&quot; so the answer is 3 + 3 = 6.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>words = <span id="example-input-2-1">[&quot;hello&quot;,&quot;world&quot;,&quot;leetcode&quot;]</span>, chars = <span id="example-input-2-2">&quot;welldonehoneyr&quot;</span>

<strong>Output: </strong><span id="example-output-2">10</span>

<strong>Explanation: </strong>

The strings that can be formed are &quot;hello&quot; and &quot;world&quot; so the answer is 5 + 5 = 10.

</pre>

<p>&nbsp;</p>

<p><span><strong>Note:</strong></span></p>

<ol>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length, chars.length&nbsp;&lt;= 100</code></li>
	<li>All strings contain lowercase English letters only.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countCharacters(self, words: List[str], chars: str) -> int:
        counter = Counter(chars)
        ans = 0
        for word in words:
            cnt = Counter(word)
            if all([counter[c] >= v for c, v in cnt.items()]):
                ans += len(word)
        return ans
```

### **Java**

```java
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] counter = count(chars);
        int ans = 0;
        for (String word : words) {
            int[] cnt = count(word);
            if (check(counter, cnt)) {
                ans += word.length();
            }
        }
        return ans;
    }

    private int[] count(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        return counter;
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] < cnt2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countCharacters(vector<string>& words, string chars) {
        vector<int> counter = count(chars);
        int ans = 0;
        for (auto& word : words)
        {
            vector<int> cnt = count(word);
            if (check(counter, cnt)) ans += word.size();
        }
        return ans;
    }

    vector<int> count(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        return counter;
    }

    bool check(vector<int>& cnt1, vector<int>& cnt2) {
        for (int i = 0; i < 26; ++i)
            if (cnt1[i] < cnt2[i]) return false;
        return true;
    }
};
```

### **Go**

```go
func countCharacters(words []string, chars string) int {
	counter := count(chars)
	ans := 0
	for _, word := range words {
		cnt := count(word)
		if check(counter, cnt) {
			ans += len(word)
		}
	}
	return ans
}

func count(s string) []int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	return counter
}

func check(cnt1, cnt2 []int) bool {
	for i := 0; i < 26; i++ {
		if cnt1[i] < cnt2[i] {
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
