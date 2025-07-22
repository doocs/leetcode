---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [2052. Minimum Cost to Separate Sentence Into Rows 🔒](https://leetcode.com/problems/minimum-cost-to-separate-sentence-into-rows)

[中文文档](/solution/2000-2099/2052.Minimum%20Cost%20to%20Separate%20Sentence%20Into%20Rows/README.md)

## Description

<!-- description:start -->

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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;i love leetcode&quot;, k = 12
<strong>Output:</strong> 36
<strong>Explanation:</strong>
Separating sentence into &quot;i&quot;, &quot;love&quot;, and &quot;leetcode&quot; has a cost of (12 - 1)<sup>2</sup> + (12 - 4)<sup>2</sup> = 185.
Separating sentence into &quot;i love&quot;, and &quot;leetcode&quot; has a cost of (12 - 6)<sup>2</sup> = 36.
Separating sentence into &quot;i&quot;, &quot;love leetcode&quot; is not possible because &quot;love leetcode&quot; has length 13.
36 is the minimum possible total cost so return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;apples and bananas taste great&quot;, k = 7
<strong>Output:</strong> 21
<strong>Explanation</strong>
Separating sentence into &quot;apples&quot;, &quot;and&quot;, &quot;bananas&quot;, &quot;taste&quot;, and &quot;great&quot; has a cost of (7 - 6)<sup>2</sup> + (7 - 3)<sup>2</sup> + (7 - 7)<sup>2</sup> + (7 - 5)<sup>2 </sup>= 21.
21 is the minimum possible total cost so return it.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Memoized Search

We use an array $\textit{nums}$ to record the length of each word, and let the length of the array be $n$. Then we define a prefix sum array $\textit{s}$ of length $n + 1$, where $\textit{s}[i]$ represents the sum of the lengths of the first $i$ words.

Next, we design a function $\textit{dfs}(i)$, which represents the minimum cost of splitting the sentence starting from the $i$-th word. The answer is $\textit{dfs}(0)$.

The execution process of the function $\textit{dfs}(i)$ is as follows:

-   If the sum of the lengths of the words from the $i$-th word to the last word plus the number of spaces between the words is less than or equal to $k$, then these words can be placed on the last line, and the cost is $0$.
-   Otherwise, we enumerate the position $j$ of the next word to start splitting, such that the sum of the lengths of the words from the $i$-th word to the $(j-1)$-th word plus the number of spaces between the words is less than or equal to $k$. Then $\textit{dfs}(j)$ represents the minimum cost of splitting the sentence starting from the $j$-th word, and $(k - m)^2$ represents the cost of placing the words from the $i$-th word to the $(j-1)$-th word on one line, where $m$ represents the sum of the lengths of the words from the $i$-th word to the $(j-1)$-th word plus the number of spaces between the words. We enumerate all $j$ and take the minimum value.

The answer is $\textit{dfs}(0)$.

To avoid repeated calculations, we can use memoized search.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the number of words.

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
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (s[n] - s[i] + n - i - 1 <= k) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int ans = INT_MAX;
            for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
                int m = s[j] - s[i] + j - i - 1;
                ans = min(ans, dfs(j) + (k - m) * (k - m));
            }
            return f[i] = ans;
        };
        return dfs(0);
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
