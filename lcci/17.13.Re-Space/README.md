# [面试题 17.13. 恢复空格](https://leetcode.cn/problems/re-space-lcci)

[English Version](/lcci/17.13.Re-Space/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子<code>&quot;I reset the computer. It still didn&rsquo;t boot!&quot;</code>已经变成了<code>&quot;iresetthecomputeritstilldidntboot&quot;</code>。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典<code>dictionary</code>，不过，有些词没在词典里。假设文章用<code>sentence</code>表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。</p>

<p><strong>注意：</strong>本题相对原题稍作改动，只需返回未识别的字符数</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
dictionary = [&quot;looked&quot;,&quot;just&quot;,&quot;like&quot;,&quot;her&quot;,&quot;brother&quot;]
sentence = &quot;jesslookedjustliketimherbrother&quot;
<strong>输出：</strong> 7
<strong>解释：</strong> 断句后为&quot;<strong>jess</strong> looked just like <strong>tim</strong> her brother&quot;，共7个未识别字符。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= len(sentence) &lt;= 1000</code></li>
	<li><code>dictionary</code>中总字符数不超过 150000。</li>
	<li>你可以认为<code>dictionary</code>和<code>sentence</code>中只包含小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
