# [1160. Find Words That Can Be Formed by Characters](https://leetcode.com/problems/find-words-that-can-be-formed-by-characters)

[中文文档](/solution/1100-1199/1160.Find%20Words%20That%20Can%20Be%20Formed%20by%20Characters/README.md)

## Description

<p>You are given an array of strings <code>words</code> and a string <code>chars</code>.</p>

<p>A string is <strong>good</strong> if it can be formed by characters from chars (each character can only be used once).</p>

<p>Return <em>the sum of lengths of all good strings in words</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cat&quot;,&quot;bt&quot;,&quot;hat&quot;,&quot;tree&quot;], chars = &quot;atach&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The strings that can be formed are &quot;cat&quot; and &quot;hat&quot; so the answer is 3 + 3 = 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;hello&quot;,&quot;world&quot;,&quot;leetcode&quot;], chars = &quot;welldonehoneyr&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> The strings that can be formed are &quot;hello&quot; and &quot;world&quot; so the answer is 5 + 5 = 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length, chars.length &lt;= 100</code></li>
	<li><code>words[i]</code> and <code>chars</code> consist of lowercase English letters.</li>
</ul>

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
        for (auto& word : words) {
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
