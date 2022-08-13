# [2052. Minimum Cost to Separate Sentence Into Rows](https://leetcode.com/problems/minimum-cost-to-separate-sentence-into-rows)

[中文文档](/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README.md)

## Description

<p>You are given a string <code>sentence</code> containing words separated by spaces, and an integer <code>k</code>. Your task is to separate <code>sentence</code> into <strong>rows</strong> where the number of characters in each row is <strong>at most </strong><code>k</code>. You may assume that <code>sentence</code> does not begin or end with a space, and the words in <code>sentence</code> are separated by a single space.</p>

<p>You can split <code>sentence</code> into rows by inserting line breaks between words in <code>sentence</code>. A word <strong>cannot</strong> be split between two rows. Each word must be used exactly once, and the word order cannot be rearranged. Adjacent words in a row should be separated by a single space, and rows should not begin or end with spaces.</p>

<p>The <strong>cost</strong> of a row with length <code>n</code> is <code>(k - n)<sup>2</sup></code>, and the <strong>total cost</strong> is the sum of the <strong>costs</strong> for all rows <strong>except</strong> the last one.</p>

<ul>
	<li>For example if <code>sentence = &quot;i love leetcode&quot;</code> and <code>k = 12</code>:
    <ul>
    	<li>Separating <code>sentence</code> into <code>&quot;i&quot;</code>, <code>&quot;love&quot;</code>, and <code>&quot;leetcode&quot;</code> has a cost of <code>(12 - 1)<sup>2</sup> + (12 - 4)<sup>2</sup> = 185</code>.</li>
    	<li>Separating <code>sentence</code> into <code>&quot;i love&quot;</code>, and <code>&quot;leetcode&quot;</code> has a cost of <code>(12 - 6)<sup>2</sup> = 36</code>.</li>
    	<li>Separating <code>sentence</code> into <code>&quot;i&quot;</code>, and <code>&quot;love leetcode&quot;</code> is not possible because the length of <code>&quot;love leetcode&quot;</code> is greater than <code>k</code>.</li>
    </ul>
    </li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible total cost of separating</em><em> </em><code>sentence</code><em> into rows.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;i love leetcode&quot;, k = 12
<strong>Output:</strong> 36
<strong>Explanation:</strong>
Separating sentence into &quot;i&quot;, &quot;love&quot;, and &quot;leetcode&quot; has a cost of (12 - 1)<sup>2</sup> + (12 - 4)<sup>2</sup> = 185.
Separating sentence into &quot;i love&quot;, and &quot;leetcode&quot; has a cost of (12 - 6)<sup>2</sup> = 36.
Separating sentence into &quot;i&quot;, &quot;love leetcode&quot; is not possible because &quot;love leetcode&quot; has length 13.
36 is the minimum possible total cost so return it.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;apples and bananas taste great&quot;, k = 7
<strong>Output:</strong> 21
<strong>Explanation</strong>
Separating sentence into &quot;apples&quot;, &quot;and&quot;, &quot;bananas&quot;, &quot;taste&quot;, and &quot;great&quot; has a cost of (7 - 6)<sup>2</sup> + (7 - 3)<sup>2</sup> + (7 - 7)<sup>2</sup> + (7 - 5)<sup>2 </sup>= 21.
21 is the minimum possible total cost so return it.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;a&quot;, k = 5
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The cost of the last row is not included in the total cost, and since there is only one row, return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 5000</code></li>
	<li><code>1 &lt;= k &lt;= 5000</code></li>
	<li>The length of each word in <code>sentence</code> is at most <code>k</code>.</li>
	<li><code>sentence</code> consists of only lowercase English letters and spaces.</li>
	<li><code>sentence</code> does not begin or end with a space.</li>
	<li>Words in <code>sentence</code> are separated by a single space.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
