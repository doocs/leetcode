# [2901. Longest Unequal Adjacent Groups Subsequence II](https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii)

[中文文档](/solution/2900-2999/2901.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20II/README.md)

<!-- tags:Array,String,Dynamic Programming -->

## Description

<p>You are given an integer <code>n</code>, a <strong>0-indexed</strong> string array <code>words</code>, and a <strong>0-indexed</strong> array <code>groups</code>, both arrays having length <code>n</code>.</p>

<p>The <strong>hamming distance</strong> between two strings of equal length is the number of positions at which the corresponding characters are <strong>different</strong>.</p>

<p>You need to select the <strong>longest</strong> <strong>subsequence</strong> from an array of indices <code>[0, 1, ..., n - 1]</code>, such that for the subsequence denoted as <code>[i<sub>0</sub>, i<sub>1</sub>, ..., i<sub>k - 1</sub>]</code> having length <code>k</code>, the following holds:</p>

<ul>
	<li>For <strong>adjacent</strong> indices in the subsequence, their corresponding groups are <strong>unequal</strong>, i.e., <code>groups[i<sub>j</sub>] != groups[i<sub>j + 1</sub>]</code>, for each <code>j</code> where <code>0 &lt; j + 1 &lt; k</code>.</li>
	<li><code>words[i<sub>j</sub>]</code> and <code>words[i<sub>j + 1</sub>]</code> are <strong>equal</strong> in length, and the <strong>hamming distance</strong> between them is <code>1</code>, where <code>0 &lt; j + 1 &lt; k</code>, for all indices in the subsequence.</li>
</ul>

<p>Return <em>a string array containing the words corresponding to the indices <strong>(in order)</strong> in the selected subsequence</em>. If there are multiple answers, return <em>any of them</em>.</p>

<p>A <strong>subsequence</strong> of an array is a new array that is formed from the original array by deleting some (possibly none) of the elements without disturbing the relative positions of the remaining elements.</p>

<p><strong>Note:</strong> strings in <code>words</code> may be <strong>unequal</strong> in length.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, words = [&quot;bab&quot;,&quot;dab&quot;,&quot;cab&quot;], groups = [1,2,2]
<strong>Output:</strong> [&quot;bab&quot;,&quot;cab&quot;]
<strong>Explanation:</strong> A subsequence that can be selected is [0,2].
- groups[0] != groups[2]
- words[0].length == words[2].length, and the hamming distance between them is 1.
So, a valid answer is [words[0],words[2]] = [&quot;bab&quot;,&quot;cab&quot;].
Another subsequence that can be selected is [0,1].
- groups[0] != groups[1]
- words[0].length == words[1].length, and the hamming distance between them is 1.
So, another valid answer is [words[0],words[1]] = [&quot;bab&quot;,&quot;dab&quot;].
It can be shown that the length of the longest subsequence of indices that satisfies the conditions is 2.  </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;], groups = [1,2,3,4]
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;]
<strong>Explanation:</strong> We can select the subsequence [0,1,2,3].
It satisfies both conditions.
Hence, the answer is [words[0],words[1],words[2],words[3]] = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;].
It has the longest length among all subsequences of indices that satisfy the conditions.
Hence, it is the only answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length == groups.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= groups[i] &lt;= n</code></li>
	<li><code>words</code> consists of <strong>distinct</strong> strings.</li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ as the length of the longest adjacent non-equal subsequence ending with the $i$-th word, and $g[i]$ as the predecessor index of the longest adjacent non-equal subsequence ending with the $i$-th word. Initially, we set $f[i] = 1$ and $g[i] = -1$.

In addition, we define a variable $mx$ to represent the length of the longest adjacent non-equal subsequence.

We traverse $i$ and $j \in [0, i)$, and if $groups[i] \neq groups[j]$, $f[i] \lt f[j] + 1$, and the Hamming distance between $words[i]$ and $words[j]$ is $1$, we update $f[i] = f[j] + 1$, $g[i] = j$, and update $mx = \max(mx, f[i])$.

Finally, we find the index $i$ corresponding to the maximum value in the $f$ array, and then continuously search backwards from $i$ until we find $g[i] = -1$, which gives us the longest adjacent non-equal subsequence.

The time complexity is $O(n^2 \times L)$, and the space complexity is $O(n)$. Here, $L$ represents the maximum length of a word.

**Optimization: Space for Time**

In **Solution 1**, we need to enumerate all $i$ and $j$ combinations, a step that can be optimized by maintaining a wildcard hash table. For each string $word[i]$, we enumerate each character, replace it with a wildcard, and then use the replaced string as the key and add its subscript to the list which is the value in the hash table. This allows us to find all $word[j]$ with a Hamming distance of 1 from $word[i]$ in $O(L)$ time. Although the time complexity is still $O(n^2 \times L)$, the average complexity is reduced.

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

### Solution 2

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
