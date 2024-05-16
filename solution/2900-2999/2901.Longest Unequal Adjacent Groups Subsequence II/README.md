---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2901.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20II/README.md
rating: 1898
source: 第 115 场双周赛 Q3
tags:
    - 数组
    - 字符串
    - 动态规划
---

# [2901. 最长相邻不相等子序列 II](https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-ii)

[English Version](/solution/2900-2999/2901.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个下标从&nbsp;<strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>words</code>&nbsp;，和一个下标从&nbsp;<strong>0</strong>&nbsp;开始的数组&nbsp;<code>groups</code>&nbsp;，两个数组长度都是&nbsp;<code>n</code>&nbsp;。</p>

<p>两个长度相等字符串的 <strong>汉明距离</strong>&nbsp;定义为对应位置字符&nbsp;<strong>不同</strong>&nbsp;的数目。</p>

<p>你需要从下标&nbsp;<code>[0, 1, ..., n - 1]</code>&nbsp;中选出一个&nbsp;<strong>最长<span data-keyword="subsequence-array">子序列</span></strong>&nbsp;，将这个子序列记作长度为 <code>k</code> 的&nbsp;<code>[i<sub>0</sub>, i<sub>1</sub>, ..., i<sub>k - 1</sub>]</code>&nbsp;，它需要满足以下条件：</p>

<ul>
	<li><strong>相邻</strong> 下标对应的 <code>groups</code> 值 <strong>不同</strong>。即，对于所有满足&nbsp;<code>0 &lt; j + 1 &lt; k</code>&nbsp;的&nbsp;<code>j</code>&nbsp;都有&nbsp;<code>groups[i<sub>j</sub>] != groups[i<sub>j + 1</sub>]</code>&nbsp;。</li>
	<li>对于所有&nbsp;<code>0 &lt; j + 1 &lt; k</code>&nbsp;的下标&nbsp;<code>j</code>&nbsp;，都满足&nbsp;<code>words[i<sub>j</sub>]</code> 和&nbsp;<code>words[i<sub>j + 1</sub>]</code>&nbsp;的长度 <strong>相等</strong>&nbsp;，且两个字符串之间的 <strong>汉明距离</strong>&nbsp;为 <code>1</code>&nbsp;。</li>
</ul>

<p>请你返回一个字符串数组，它是下标子序列&nbsp;<strong>依次</strong>&nbsp;对应&nbsp;<code>words</code>&nbsp;数组中的字符串连接形成的字符串数组。如果有多个答案，返回任意一个。</p>

<p><strong>子序列</strong>&nbsp;指的是从原数组中删掉一些（也可能一个也不删掉）元素，剩余元素不改变相对位置得到的新的数组。</p>

<p><b>注意：</b><code>words</code>&nbsp;中的字符串长度可能&nbsp;<strong>不相等</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 3, words = ["bab","dab","cab"], groups = [1,2,2]
<b>输出：</b>["bab","cab"]
<b>解释：</b>一个可行的子序列是 [0,2] 。
- groups[0] != groups[2]
- words[0].length == words[2].length 且它们之间的汉明距离为 1 。
所以一个可行的答案是 [words[0],words[2]] = ["bab","cab"] 。
另一个可行的子序列是 [0,1] 。
- groups[0] != groups[1]
- words[0].length = words[1].length 且它们之间的汉明距离为 1 。
所以另一个可行的答案是 [words[0],words[1]] = ["bab","dab"] 。
符合题意的最长子序列的长度为 2 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 4, words = ["a","b","c","d"], groups = [1,2,3,4]
<b>输出：</b>["a","b","c","d"]
<b>解释：</b>我们选择子序列 [0,1,2,3] 。
它同时满足两个条件。
所以答案为 [words[0],words[1],words[2],words[3]] = ["a","b","c","d"] 。
它是所有下标子序列里最长且满足所有条件的。
所以它是唯一的答案。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= n == words.length == groups.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= groups[i] &lt;= n</code></li>
	<li><code>words</code>&nbsp;中的字符串&nbsp;<strong>互不相同</strong>&nbsp;。</li>
	<li><code>words[i]</code> 只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示以第 $i$ 个单词结尾的最长相邻不相等子序列的长度，定义 $g[i]$ 表示以第 $i$ 个单词结尾的最长相邻不相等子序列的前驱下标。初始时 $f[i] = 1$, $g[i] = -1$。

另外，我们定义一个变量 $mx$ 表示最长相邻不相等子序列的长度。

我们枚举 $i$，枚举 $j \in [0, i)$，如果 $groups[i] \neq groups[j]$ 且 $f[i] \lt f[j] + 1$ 且 $words[i]$ 和 $words[j]$ 之间的汉明距离为 $1$，则更新 $f[i] = f[j] + 1$，$g[i] = j$，并且更新 $mx = \max(mx, f[i])$。

最后，我们从 $f$ 数组中找到最大值对应的下标 $i$，然后从 $i$ 开始不断往前找，直到找到 $g[i] = -1$，这样就找到了最长相邻不相等子序列。

时间复杂度 $O(n^2 \times L)$，空间复杂度 $O(n)$。其中 $L$ 表示单词的最大长度。

**优化：空间换时间**

**方法一**中，我们需要枚举所有的 $i$ 和 $j$ 组合, 这一步可以通过维护一个通配符哈希表来优化. 对于每个字符串 $word[i]$, 我们枚举它的每个字符, 将其替换为通配符, 然后将替换后的字符串作为键, 将其下标作为值存入哈希表中. 这样我们可以在 $O(L)$ 时间内找到所有距离 $word[i]$ 汉明距离为 1 的 $word[j]$. 尽管时间复杂度仍然是 $O(n^2 \times L)$, 但平均复杂度会有所降低.

<!-- tabs:start -->

```python
class Solution:
    def getWordsInLongestSubsequence(
        self, n: int, words: List[str], groups: List[int]
    ) -> List[str]:
        def check(s: str, t: str) -> bool:
            return len(s) == len(t) and sum(a != b for a, b in zip(s, t)) == 1

        f = [1] * n
        g = [-1] * n
        mx = 1
        for i, x in enumerate(groups):
            for j, y in enumerate(groups[:i]):
                if x != y and f[i] < f[j] + 1 and check(words[i], words[j]):
                    f[i] = f[j] + 1
                    g[i] = j
                    mx = max(mx, f[i])
        ans = []
        for i in range(n):
            if f[i] == mx:
                j = i
                while j >= 0:
                    ans.append(words[j])
                    j = g[j]
                break
        return ans[::-1]
```

```java
class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[] f = new int[n];
        int[] g = new int[n];
        Arrays.fill(f, 1);
        Arrays.fill(g, -1);
        int mx = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (groups[i] != groups[j] && f[i] < f[j] + 1 && check(words[i], words[j])) {
                    f[i] = f[j] + 1;
                    g[i] = j;
                    mx = Math.max(mx, f[i]);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (f[i] == mx) {
                for (int j = i; j >= 0; j = g[j]) {
                    ans.add(words[j]);
                }
                break;
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

```cpp
class Solution {
public:
    vector<string> getWordsInLongestSubsequence(int n, vector<string>& words, vector<int>& groups) {
        auto check = [](string& s, string& t) {
            if (s.size() != t.size()) {
                return false;
            }
            int cnt = 0;
            for (int i = 0; i < s.size(); ++i) {
                cnt += s[i] != t[i];
            }
            return cnt == 1;
        };
        vector<int> f(n, 1);
        vector<int> g(n, -1);
        int mx = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (groups[i] != groups[j] && f[i] < f[j] + 1 && check(words[i], words[j])) {
                    f[i] = f[j] + 1;
                    g[i] = j;
                    mx = max(mx, f[i]);
                }
            }
        }
        vector<string> ans;
        for (int i = 0; i < n; ++i) {
            if (f[i] == mx) {
                for (int j = i; ~j; j = g[j]) {
                    ans.emplace_back(words[j]);
                }
                break;
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func getWordsInLongestSubsequence(n int, words []string, groups []int) []string {
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return false
		}
		cnt := 0
		for i := range s {
			if s[i] != t[i] {
				cnt++
			}
		}
		return cnt == 1
	}
	f := make([]int, n)
	g := make([]int, n)
	for i := range f {
		f[i] = 1
		g[i] = -1
	}
	mx := 1
	for i, x := range groups {
		for j, y := range groups[:i] {
			if x != y && f[i] < f[j]+1 && check(words[i], words[j]) {
				f[i] = f[j] + 1
				g[i] = j
				if mx < f[i] {
					mx = f[i]
				}
			}
		}
	}
	ans := make([]string, 0, mx)
	for i, x := range f {
		if x == mx {
			for j := i; j >= 0; j = g[j] {
				ans = append(ans, words[j])
			}
			break
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}
```

```ts
function getWordsInLongestSubsequence(n: number, words: string[], groups: number[]): string[] {
    const f: number[] = Array(n).fill(1);
    const g: number[] = Array(n).fill(-1);
    let mx = 1;
    const check = (s: string, t: string) => {
        if (s.length !== t.length) {
            return false;
        }
        let cnt = 0;
        for (let i = 0; i < s.length; ++i) {
            if (s[i] !== t[i]) {
                ++cnt;
            }
        }
        return cnt === 1;
    };
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (groups[i] !== groups[j] && f[i] < f[j] + 1 && check(words[i], words[j])) {
                f[i] = f[j] + 1;
                g[i] = j;
                mx = Math.max(mx, f[i]);
            }
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        if (f[i] === mx) {
            for (let j = i; ~j; j = g[j]) {
                ans.push(words[j]);
            }
            break;
        }
    }
    return ans.reverse();
}
```

```rust
impl Solution {
    pub fn get_words_in_longest_subsequence(
        n: i32,
        words: Vec<String>,
        groups: Vec<i32>
    ) -> Vec<String> {
        fn check(s: &str, t: &str) -> bool {
            s.len() == t.len() &&
                s
                    .chars()
                    .zip(t.chars())
                    .filter(|(a, b)| a != b)
                    .count() == 1
        }

        let n = n as usize;

        let mut f = vec![1; n];
        let mut g = vec![-1; n];

        let mut mx = 1;

        for i in 0..n {
            let x = groups[i] as usize;
            for j in 0..i {
                let y = groups[j] as usize;
                if x != y && f[i] < f[j] + 1 && check(&words[i], &words[j]) {
                    f[i] = f[j] + 1;
                    g[i] = j as i32;
                    mx = mx.max(f[i]);
                }
            }
        }

        let mut ans = vec![];
        let mut i = n - 1;

        while f[i] != mx {
            i -= 1;
        }

        let mut j = i as i32;
        while j >= 0 {
            ans.push(words[j as usize].clone());
            j = g[j as usize];
        }

        ans.reverse();
        ans
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```java
class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[] dp = new int[n];
        int[] next = new int[n];
        Map<String, List<Integer>> strToIdxMap = new HashMap<>();
        int maxIdx = n;
        for (int i = n - 1; i >= 0; i--) {
            int prevIdx = n;
            char[] word = words[i].toCharArray();
            for (int j = 0; j < word.length; j++) {
                // convert word to pattern with '*'.
                char temp = word[j];
                word[j] = '*';
                String curr = new String(word);

                // search matches and update dp.
                List<Integer> prevList = strToIdxMap.getOrDefault(curr, List.of());
                for (int prev : prevList) {
                    if (groups[prev] == groups[i] || dp[prev] < dp[i]) {
                        continue;
                    }
                    dp[i] = dp[prev] + 1;
                    prevIdx = prev;
                }

                // append current pattern to dictionary.
                strToIdxMap.computeIfAbsent(curr, k -> new ArrayList<>()).add(i);

                // restore pattern to orignal word.
                word[j] = temp;
            }
            if (maxIdx >= n || dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
            next[i] = prevIdx;
        }
        int curr = maxIdx;
        List<String> ans = new ArrayList<>();
        while (curr < n) {
            ans.add(words[curr]);
            curr = next[curr];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
