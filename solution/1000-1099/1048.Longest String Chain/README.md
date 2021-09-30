# [1048. 最长字符串链](https://leetcode-cn.com/problems/longest-string-chain)

[English Version](/solution/1000-1099/1048.Longest%20String%20Chain/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个单词列表，其中每个单词都由小写英文字母组成。</p>

<p>如果我们可以在&nbsp;<code>word1</code>&nbsp;的任何地方添加一个字母使其变成&nbsp;<code>word2</code>，那么我们认为&nbsp;<code>word1</code>&nbsp;是&nbsp;<code>word2</code>&nbsp;的前身。例如，<code>&quot;abc&quot;</code>&nbsp;是&nbsp;<code>&quot;abac&quot;</code>&nbsp;的前身。</p>

<p><strong>词链</strong>是单词&nbsp;<code>[word_1, word_2, ..., word_k]</code>&nbsp;组成的序列，<code>k &gt;= 1</code>，其中&nbsp;<code>word_1</code>&nbsp;是&nbsp;<code>word_2</code>&nbsp;的前身，<code>word_2</code>&nbsp;是&nbsp;<code>word_3</code>&nbsp;的前身，依此类推。</p>

<p>从给定单词列表 <code>words</code> 中选择单词组成词链，返回词链的最长可能长度。<br>
&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;a&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bca&quot;,&quot;bda&quot;,&quot;bdca&quot;]
<strong>输出：</strong>4
<strong>解释：</strong>最长单词链之一为 &quot;a&quot;,&quot;ba&quot;,&quot;bda&quot;,&quot;bdca&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 16</code></li>
	<li><code>words[i]</code>&nbsp;仅由小写英文字母组成。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先按字符串长度升序排列，再利用动态规划或者哈希表求解。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划：

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
        words.sort(key= lambda x: len(x))
        res = 1
        for i in range(1, n):
            for j in range(i):
                if check(words[j], words[i]):
                    dp[i] = max(dp[i], dp[j] + 1)
            res = max(res, dp[i])
        return res
```

哈希表：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

哈希表：

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
            let pre = word.substring(0, i) + word.substring(i+1);
            c = Math.max(c, (hashTable.get(pre) || 0) + 1);
        }
        hashTable.set(word, c);
        ans = Math.max(ans, c);
    }
    return ans;
};
```

### **C++**

哈希表：

```cpp
class Solution {
public:
    int longestStrChain(vector<string> &words) {
        sort(words.begin(), words.end(), [&](string a, string b)
             { return a.size() < b.size(); });
        int res = 0;
        unordered_map<string, int> map;
        for (auto word : words)
        {
            int x = 1;
            for (int i = 0; i < word.size(); ++i)
            {
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

哈希表：

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
