# [2052. 将句子分隔成行的最低成本](https://leetcode.cn/problems/minimum-cost-to-separate-sentence-into-rows)

[English Version](/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由空格分隔的单词组成的字符串&nbsp;<code>sentence</code>&nbsp;和一个整数 <code>k</code>。你的任务是将&nbsp;<code>sentence</code> 分成<strong>多行</strong>，每行中的字符数<strong>最多</strong>为 <code>k</code>。你可以假设&nbsp;<code>sentence</code> 不以空格开头或结尾，并且&nbsp;<code>sentence</code> 中的单词由单个空格分隔。</p>

<p>你可以通过在&nbsp;<code>sentence</code>&nbsp;中的单词间插入换行来分隔&nbsp;<code>sentence</code> 。一个单词<strong>不能</strong>被分成两行。每个单词只能使用一次，并且单词顺序不能重排。同一行中的相邻单词应该由单个空格分隔，并且每行都不应该以空格开头或结尾。</p>

<p>一行长度为&nbsp;<code>n</code> 的字符串的<strong>分隔成本</strong>是&nbsp;<code>(k - n)<sup>2</sup></code> ，<strong>总成本</strong>就是<strong>除开</strong>最后一行以外的<strong>其它所有行的分隔成本</strong>之和。</p>

<ul>
	<li>以&nbsp;<code>sentence = "i love leetcode"</code> 和<code>k = 12</code>为例：
    <ul>
    	<li>将<code>sentence</code> 分成&nbsp;<code>"i"</code>, <code>"love"</code>, 和<code>"leetcode"</code> 的成本为&nbsp;<code>(12 - 1)<sup>2</sup> + (12 - 4)<sup>2</sup> = 185</code>。</li>
    	<li>将<code>sentence</code> 分成&nbsp;<code>"i love"</code>, 和<code>"leetcode"</code> 的成本为 <code>(12 - 6)<sup>2</sup> = 36</code>。</li>
    	<li>将<code>sentence</code> 分成&nbsp;<code>"i"</code>, 和<code>"love leetcode"</code>&nbsp;是不可能的，因为&nbsp;<code>"love leetcode"</code>&nbsp;的长度大于&nbsp;<code>k</code>。</li>
    </ul>
    </li>
</ul>

<p>返回<em>将</em><code>sentence</code><em>分隔成行的<strong>最低的</strong>可能总成本。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> sentence = "i love leetcode", k = 12
<strong>输出:</strong> 36
<strong>解释:</strong>
将 sentence 分成"i", "love", 和"leetcode" 的成本为 (12 - 1)<sup>2</sup> + (12 - 4)<sup>2</sup> = 185.
将 sentence 分成"i love", 和"leetcode" 的成本为 (12 - 6)<sup>2</sup> = 36.
将 sentence 分成"i", "love leetcode" 是不可能的，因为 "love leetcode" 的长度为 13.
36是最低的可能总成本，因此返回它
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> sentence = "apples and bananas taste great", k = 7
<strong>输出:</strong> 21
<strong>解释:</strong>
将 sentence 分成"apples", "and", "bananas", "taste", 和"great" 的成本为 (7 - 6)<sup>2</sup> + (7 - 3)<sup>2</sup> + (7 - 7)<sup>2</sup> + (7 - 5)<sup>2 </sup>= 21.
21是最低的可能总成本，因此返回它
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> sentence = "a", k = 5
<strong>输出:</strong> 0
<strong>解释:</strong>
最后一行的成本不包括在总成本中，而sentence只有一行，所以返回0</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 5000</code></li>
	<li><code>1 &lt;= k &lt;= 5000</code></li>
	<li><code>sentence</code>&nbsp;中每个单词长度最大为&nbsp;<code>k</code>.</li>
	<li><code>sentence</code> 只包含小写字母和空格.</li>
	<li><code>sentence</code> 不会以空格开头或结尾.</li>
	<li><code>sentence</code>&nbsp;中的单词以单个空格分隔.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumCost(self, sentence: str, k: int) -> int:
        @cache
        def dfs(i):
            if s[-1] - s[i] + n - i - 1 <= k:
                return 0
            ans, j = inf, i + 1
            while j < n and (t := s[j] - s[i] + j - i - 1) <= k:
                ans = min(ans, (k - t) ** 2 + dfs(j))
                j += 1
            return ans

        t = [len(w) for w in sentence.split()]
        n = len(t)
        s = list(accumulate(t, initial=0))
        return dfs(0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private int[] memo;
    private int[] s;
    private int n;

    public int minimumCost(String sentence, int k) {
        String[] words = sentence.split(" ");
        n = words.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + words[i].length();
        }
        memo = new int[n];
        Arrays.fill(memo, INF);
        return dfs(0, k);
    }

    private int dfs(int i, int k) {
        if (memo[i] != INF) {
            return memo[i];
        }
        if (s[n] - s[i] + n - i - 1 <= k) {
            memo[i] = 0;
            return 0;
        }
        int ans = INF;
        for (int j = i + 1; j < n; ++j) {
            int t = s[j] - s[i] + j - i - 1;
            if (t <= k) {
                ans = Math.min(ans, (k - t) * (k - t) + dfs(j, k));
            }
        }
        memo[i] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int inf = INT_MAX;
    int n;

    int minimumCost(string sentence, int k) {
        istringstream is(sentence);
        vector<string> words;
        string word;
        while (is >> word) words.push_back(word);
        n = words.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + words[i].size();
        vector<int> memo(n, inf);
        return dfs(0, k, s, memo);
    }

    int dfs(int i, int k, vector<int>& s, vector<int>& memo) {
        if (memo[i] != inf) return memo[i];
        if (s[n] - s[i] + n - i - 1 <= k) {
            memo[i] = 0;
            return 0;
        }
        int ans = inf;
        for (int j = i + 1; j < n; ++j) {
            int t = s[j] - s[i] + j - i - 1;
            if (t <= k) ans = min(ans, (k - t) * (k - t) + dfs(j, k, s, memo));
        }
        memo[i] = ans;
        return ans;
    }
};
```

### **Go**

```go
func minimumCost(sentence string, k int) int {
	words := strings.Split(sentence, " ")
	n := len(words)
	inf := math.MaxInt32
	s := make([]int, n+1)
	for i, word := range words {
		s[i+1] = s[i] + len(word)
	}
	memo := make([]int, n)
	for i := range memo {
		memo[i] = inf
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if memo[i] != inf {
			return memo[i]
		}
		if s[n]-s[i]+n-i-1 <= k {
			memo[i] = 0
			return 0
		}
		ans := inf
		for j := i + 1; j < n; j++ {
			t := s[j] - s[i] + j - i - 1
			if t <= k {
				ans = min(ans, (k-t)*(k-t)+dfs(j))
			}
		}
		memo[i] = ans
		return ans
	}
	return dfs(0)
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
