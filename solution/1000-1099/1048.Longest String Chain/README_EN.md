# [1048. Longest String Chain](https://leetcode.com/problems/longest-string-chain)

[中文文档](/solution/1000-1099/1048.Longest%20String%20Chain/README.md)

## Description

<p>You are given an array of <code>words</code> where each word consists of lowercase English letters.</p>

<p><code>word<sub>A</sub></code> is a <strong>predecessor</strong> of <code>word<sub>B</sub></code> if and only if we can insert <strong>exactly one</strong> letter anywhere in <code>word<sub>A</sub></code> <strong>without changing the order of the other characters</strong> to make it equal to <code>word<sub>B</sub></code>.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> is a <strong>predecessor</strong> of <code>&quot;ab<u>a</u>c&quot;</code>, while <code>&quot;cba&quot;</code> is not a <strong>predecessor</strong> of <code>&quot;bcad&quot;</code>.</li>
</ul>

<p>A <strong>word chain</strong><em> </em>is a sequence of words <code>[word<sub>1</sub>, word<sub>2</sub>, ..., word<sub>k</sub>]</code> with <code>k &gt;= 1</code>, where <code>word<sub>1</sub></code> is a <strong>predecessor</strong> of <code>word<sub>2</sub></code>, <code>word<sub>2</sub></code> is a <strong>predecessor</strong> of <code>word<sub>3</sub></code>, and so on. A single word is trivially a <strong>word chain</strong> with <code>k == 1</code>.</p>

<p>Return <em>the <strong>length</strong> of the <strong>longest possible word chain</strong> with words chosen from the given list of </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bca&quot;,&quot;bda&quot;,&quot;bdca&quot;]
<strong>Output:</strong> 4
<strong>Explanation</strong>: One of the longest word chains is [&quot;a&quot;,&quot;<u>b</u>a&quot;,&quot;b<u>d</u>a&quot;,&quot;bd<u>c</u>a&quot;].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;xbc&quot;,&quot;pcxbcf&quot;,&quot;xb&quot;,&quot;cxbc&quot;,&quot;pcxbc&quot;]
<strong>Output:</strong> 5
<strong>Explanation:</strong> All the words can be put in a word chain [&quot;xb&quot;, &quot;xb<u>c</u>&quot;, &quot;<u>c</u>xbc&quot;, &quot;<u>p</u>cxbc&quot;, &quot;pcxbc<u>f</u>&quot;].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;dbqca&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The trivial word chain [&quot;abcd&quot;] is one of the longest word chains.
[&quot;abcd&quot;,&quot;dbqca&quot;] is not a valid word chain because the ordering of the letters is changed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 16</code></li>
	<li><code>words[i]</code> only consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        def check(w1, w2):
            if len(w2) - len(w1) != 1:
                return False
            i = j = cnt = 0
            while i < len(w1) and j < len(w2):
                if w1[i] != w2[j]:
                    cnt += 1
                else:
                    i += 1
                j += 1
            return cnt < 2 and i == len(w1)

        n = len(words)
        dp = [1] * (n + 1)
        words.sort(key=lambda x: len(x))
        res = 1
        for i in range(1, n):
            for j in range(i):
                if check(words[j], words[i]):
                    dp[i] = max(dp[i], dp[j] + 1)
            res = max(res, dp[i])
        return res
```

```python
class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        words.sort(key= lambda x: len(x))
        res = 0
        mp = {}
        for word in words:
            x = 1
            for i in range(len(word)):
                pre = word[:i] + word[i + 1:]
                x = max(x, mp.get(pre, 0) + 1)
            mp[word] = x
            res = max(res, x)
        return res
```

### **Java**

```java
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int x = 1;
            for (int i = 0; i < word.length(); ++i) {
                String pre = word.substring(0, i) + word.substring(i + 1);
                x = Math.max(x, map.getOrDefault(pre, 0) + 1);
            }
            map.put(word, x);
            res = Math.max(res, x);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function longestStrChain(words: string[]): number {
    words.sort((a, b) => a.length - b.length);
    let ans = 0;
    let hashTable = new Map();
    for (let word of words) {
        let c = 1;
        for (let i = 0; i < word.length; i++) {
            let pre = word.substring(0, i) + word.substring(i + 1);
            c = Math.max(c, (hashTable.get(pre) || 0) + 1);
        }
        hashTable.set(word, c);
        ans = Math.max(ans, c);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int longestStrChain(vector<string>& words) {
        sort(words.begin(), words.end(), [&](string a, string b) { return a.size() < b.size(); });
        int res = 0;
        unordered_map<string, int> map;
        for (auto word : words) {
            int x = 1;
            for (int i = 0; i < word.size(); ++i) {
                string pre = word.substr(0, i) + word.substr(i + 1);
                x = max(x, map[pre] + 1);
            }
            map[word] = x;
            res = max(res, x);
        }
        return res;
    }
};
```

### **Go**

```go
func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	res := 0
	mp := make(map[string]int)
	for _, word := range words {
		x := 1
		for i := 0; i < len(word); i++ {
			pre := word[0:i] + word[i+1:len(word)]
			x = max(x, mp[pre]+1)
		}
		mp[word] = x
		res = max(res, x)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
