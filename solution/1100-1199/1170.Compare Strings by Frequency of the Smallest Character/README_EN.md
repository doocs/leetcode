# [1170. Compare Strings by Frequency of the Smallest Character](https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character)

[中文文档](/solution/1100-1199/1170.Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character/README.md)

<!-- tags:Array,Hash Table,String,Binary Search,Sorting -->

## Description

<p>Let the function <code>f(s)</code> be the <strong>frequency of the lexicographically smallest character</strong> in a non-empty string <code>s</code>. For example, if <code>s = &quot;dcce&quot;</code> then <code>f(s) = 2</code> because the lexicographically smallest character is <code>&#39;c&#39;</code>, which has a frequency of 2.</p>

<p>You are given an array of strings <code>words</code> and another array of query strings <code>queries</code>. For each query <code>queries[i]</code>, count the <strong>number of words</strong> in <code>words</code> such that <code>f(queries[i])</code> &lt; <code>f(W)</code> for each <code>W</code> in <code>words</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em>, where each </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;cbd&quot;], words = [&quot;zaaaz&quot;]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> On the first query we have f(&quot;cbd&quot;) = 1, f(&quot;zaaaz&quot;) = 3 so f(&quot;cbd&quot;) &lt; f(&quot;zaaaz&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;bbb&quot;,&quot;cc&quot;], words = [&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;,&quot;aaaa&quot;]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> On the first query only f(&quot;bbb&quot;) &lt; f(&quot;aaaa&quot;). On the second query both f(&quot;aaa&quot;) and f(&quot;aaaa&quot;) are both &gt; f(&quot;cc&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= queries[i].length, words[i].length &lt;= 10</code></li>
	<li><code>queries[i][j]</code>, <code>words[i][j]</code> consist of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Sorting + Binary Search

First, according to the problem description, we implement a function $f(s)$, which returns the frequency of the smallest letter in the string $s$ in lexicographical order.

Next, we calculate $f(w)$ for each string $w$ in $words$, sort them, and store them in an array $nums$.

Then, we traverse each string $q$ in $queries$, and binary search in $nums$ for the first position $i$ that is greater than $f(q)$. Then, the elements at index $i$ and after in $nums$ all satisfy $f(q) < f(W)$, so the answer to the current query is $n - i$.

The time complexity is $O((n + q) \times M)$, and the space complexity is $O(n)$. Here, $n$ and $q$ are the lengths of the arrays $words$ and $queries$ respectively, and $M$ is the maximum length of the strings.

<!-- tabs:start -->

```python
class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s: str) -> int:
            cnt = Counter(s)
            return next(cnt[c] for c in ascii_lowercase if cnt[c])

        n = len(words)
        nums = sorted(f(w) for w in words)
        return [n - bisect_right(nums, f(q)) for q in queries]
```

```java
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = f(words[i]);
        }
        Arrays.sort(nums);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (nums[mid] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = n - l;
        }
        return ans;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x > 0) {
                return x;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string s) {
            int cnt[26] = {0};
            for (char c : s) {
                cnt[c - 'a']++;
            }
            for (int x : cnt) {
                if (x) {
                    return x;
                }
            }
            return 0;
        };
        int n = words.size();
        int nums[n];
        for (int i = 0; i < n; i++) {
            nums[i] = f(words[i]);
        }
        sort(nums, nums + n);
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.push_back(n - (upper_bound(nums, nums + n, x) - nums));
        }
        return ans;
    }
};
```

```go
func numSmallerByFrequency(queries []string, words []string) (ans []int) {
	f := func(s string) int {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, x := range cnt {
			if x > 0 {
				return x
			}
		}
		return 0
	}
	n := len(words)
	nums := make([]int, n)
	for i, w := range words {
		nums[i] = f(w)
	}
	sort.Ints(nums)
	for _, q := range queries {
		x := f(q)
		ans = append(ans, n-sort.SearchInts(nums, x+1))
	}
	return
}
```

```ts
function numSmallerByFrequency(queries: string[], words: string[]): number[] {
    const f = (s: string): number => {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        return cnt.find(x => x > 0);
    };
    const nums = words.map(f).sort((a, b) => a - b);
    const ans: number[] = [];
    for (const q of queries) {
        const x = f(q);
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans.push(nums.length - l);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
