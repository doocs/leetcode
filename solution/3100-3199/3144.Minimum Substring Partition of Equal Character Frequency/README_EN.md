---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3144.Minimum%20Substring%20Partition%20of%20Equal%20Character%20Frequency/README_EN.md
---

<!-- problem:start -->

# [3144. Minimum Substring Partition of Equal Character Frequency](https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency)

[中文文档](/solution/3100-3199/3144.Minimum%20Substring%20Partition%20of%20Equal%20Character%20Frequency/README.md)

## Description

<p>Given a string <code>s</code>, you need to partition it into one or more <strong>balanced</strong> <span data-keyword="substring">substrings</span>. For example, if <code>s == &quot;ababcc&quot;</code> then <code>(&quot;abab&quot;, &quot;c&quot;, &quot;c&quot;)</code>, <code>(&quot;ab&quot;, &quot;abc&quot;, &quot;c&quot;)</code>, and <code>(&quot;ababcc&quot;)</code> are all valid partitions, but <code>(&quot;a&quot;, <strong>&quot;bab&quot;</strong>, &quot;cc&quot;)</code>, <code>(<strong>&quot;aba&quot;</strong>, &quot;bc&quot;, &quot;c&quot;)</code>, and <code>(&quot;ab&quot;, <strong>&quot;abcc&quot;</strong>)</code> are not. The unbalanced substrings are bolded.</p>

<p>Return the <strong>minimum</strong> number of substrings that you can partition <code>s</code> into.</p>

<p><strong>Note:</strong> A <strong>balanced</strong> string is a string where each character in the string occurs the same number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;fabccddg&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We can partition the string <code>s</code> into 3 substrings in one of the following ways: <code>(&quot;fab, &quot;ccdd&quot;, &quot;g&quot;)</code>, or <code>(&quot;fabc&quot;, &quot;cd&quot;, &quot;dg&quot;)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abababaccddb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can partition the string <code>s</code> into 2 substrings like so: <code>(&quot;abab&quot;, &quot;abaccddb&quot;)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists only of English lowercase letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search + Hash Table

We design a function $\text{dfs}(i)$, which represents the minimum number of substrings starting from the string $s[i]$. So the answer is $\text{dfs}(0)$.

The calculation process of the function $\text{dfs}(i)$ is as follows:

If $i \geq n$, it means that all characters have been processed, return $0$.

Otherwise, we maintain a hash table $\text{cnt}$, which represents the number of occurrences of each character in the current substring. In addition, we also maintain a hash table $\text{freq}$, which represents the frequency of the number of occurrences of each character.

Then we enumerate $j$ from $i$ to $n-1$, which represents the end position of the current substring. For each $j$, we update $\text{cnt}$ and $\text{freq}$, then check whether the size of $\text{freq}$ is $1$. If so, we can start splitting from $j+1$, at this time the answer is $1 + \text{dfs}(j+1)$. We take the minimum value of the answer among all $j$ as the return value of the function.

To avoid repeated calculations, we use memoization search.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            cnt = defaultdict(int)
            freq = defaultdict(int)
            ans = n - i
            for j in range(i, n):
                if cnt[s[j]]:
                    freq[cnt[s[j]]] -= 1
                    if not freq[cnt[s[j]]]:
                        freq.pop(cnt[s[j]])
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1
                if len(freq) == 1 and (t := 1 + dfs(j + 1)) < ans:
                    ans = t
            return ans

        n = len(s)
        return dfs(0)
```

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int minimumSubstringsInPartition(String s) {
        n = s.length();
        f = new Integer[n];
        this.s = s.toCharArray();
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[26];
        Map<Integer, Integer> freq = new HashMap<>(26);
        int ans = n - i;
        for (int j = i; j < n; ++j) {
            int k = s[j] - 'a';
            if (cnt[k] > 0) {
                if (freq.merge(cnt[k], -1, Integer::sum) == 0) {
                    freq.remove(cnt[k]);
                }
            }
            ++cnt[k];
            freq.merge(cnt[k], 1, Integer::sum);
            if (freq.size() == 1) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = n - i;
            int cnt[26]{};
            unordered_map<int, int> freq;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (cnt[k]) {
                    freq[cnt[k]]--;
                    if (freq[cnt[k]] == 0) {
                        freq.erase(cnt[k]);
                    }
                }
                ++cnt[k];
                ++freq[cnt[k]];
                if (freq.size() == 1) {
                    f[i] = min(f[i], 1 + dfs(j + 1));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		cnt := [26]int{}
		freq := map[int]int{}
		f[i] = n - i
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			if cnt[k] > 0 {
				freq[cnt[k]]--
				if freq[cnt[k]] == 0 {
					delete(freq, cnt[k])
				}
			}
			cnt[k]++
			freq[cnt[k]]++
			if len(freq) == 1 {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        const cnt: Map<number, number> = new Map();
        const freq: Map<number, number> = new Map();
        f[i] = n - i;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (freq.has(cnt.get(k)!)) {
                freq.set(cnt.get(k)!, freq.get(cnt.get(k)!)! - 1);
                if (freq.get(cnt.get(k)!) === 0) {
                    freq.delete(cnt.get(k)!);
                }
            }
            cnt.set(k, (cnt.get(k) || 0) + 1);
            freq.set(cnt.get(k)!, (freq.get(cnt.get(k)!) || 0) + 1);
            if (freq.size === 1) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
