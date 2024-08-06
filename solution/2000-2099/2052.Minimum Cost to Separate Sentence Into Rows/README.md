---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2052. 将句子分隔成行的最低成本 🔒](https://leetcode.cn/problems/minimum-cost-to-separate-sentence-into-rows)

[English Version](/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 记忆化搜索

我们用一个数组 $\textit{nums}$ 记录每个单词的长度，数组的长度记为 $n$。然后我们定义一个长度为 $n + 1$ 的前缀和数组 $\textit{s}$，其中 $\textit{s}[i]$ 表示前 $i$ 个单词的长度之和。

接下来，我们设计一个函数 $\textit{dfs}(i)$，表示从第 $i$ 个单词开始分隔句子的最小成本。那么答案为 $\textit{dfs}(0)$。

函数 $\textit{dfs}(i)$ 的执行过程如下：

-   如果从第 $i$ 个单词开始到最后一个单词的长度之和加上单词之间的空格数小于等于 $k$，那么这些单词可以放在最后一行，成本为 $0$。
-   否则，我们枚举下一个开始分隔的单词的位置 $j$，使得从第 $i$ 个单词到第 $j-1$ 个单词的长度之和加上单词之间的空格数小于等于 $k$。那么 $\textit{dfs}(j)$ 表示从第 $j$ 个单词开始分隔句子的最小成本，而 $(k - m)^2$ 表示将第 $i$ 个单词到第 $j-1$ 个单词放在一行的成本，其中 $m$ 表示从第 $i$ 个单词到第 $j-1$ 个单词的长度之和加上单词之间的空格数。我们枚举所有的 $j$，取最小值即可。

答案即为 $\textit{dfs}(0)$。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为单词的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(self, sentence: str, k: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if s[n] - s[i] + n - i - 1 <= k:
                return 0
            ans = inf
            j = i + 1
            while j < n and (m := s[j] - s[i] + j - i - 1) <= k:
                ans = min(ans, dfs(j) + (k - m) ** 2)
                j += 1
            return ans

        nums = [len(s) for s in sentence.split()]
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] s;
    private int k;
    private int n;

    public int minimumCost(String sentence, int k) {
        this.k = k;
        String[] words = sentence.split(" ");
        n = words.length;
        f = new Integer[n];
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + words[i].length();
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (s[n] - s[i] + n - i - 1 <= k) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
            int m = s[j] - s[i] + j - i - 1;
            ans = Math.min(ans, dfs(j) + (k - m) * (k - m));
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumCost(string sentence, int k) {
        istringstream iss(sentence);
        vector<int> s = {0};
        string w;
        while (iss >> w) {
            s.push_back(s.back() + w.size());
        }
        int n = s.size() - 1;
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i) -> int {
            if (s[n] - s[i] + n - i - 1 <= k) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int ans = INT_MAX;
            for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
                int m = s[j] - s[i] + j - i - 1;
                ans = min(ans, dfs(dfs, j) + (k - m) * (k - m));
            }
            return f[i] = ans;
        };
        return dfs(dfs, 0);
    }
};
```

#### Go

```go
func minimumCost(sentence string, k int) int {
	s := []int{0}
	for _, w := range strings.Split(sentence, " ") {
		s = append(s, s[len(s)-1]+len(w))
	}
	n := len(s) - 1
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if s[n]-s[i]+n-i-1 <= k {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans := math.MaxInt32
		for j := i + 1; j < n && s[j]-s[i]+j-i-1 <= k; j++ {
			m := s[j] - s[i] + j - i - 1
			ans = min(ans, dfs(j)+(k-m)*(k-m))
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function minimumCost(sentence: string, k: number): number {
    const s: number[] = [0];
    for (const w of sentence.split(' ')) {
        s.push(s.at(-1)! + w.length);
    }
    const n = s.length - 1;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (s[n] - s[i] + n - i - 1 <= k) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        let ans = Infinity;
        for (let j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
            const m = s[j] - s[i] + j - i - 1;
            ans = Math.min(ans, dfs(j) + (k - m) ** 2);
        }
        return (f[i] = ans);
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
