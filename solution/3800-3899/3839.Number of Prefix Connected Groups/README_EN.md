---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3839.Number%20of%20Prefix%20Connected%20Groups/README_EN.md
---

<!-- problem:start -->

# [3839. Number of Prefix Connected Groups](https://leetcode.com/problems/number-of-prefix-connected-groups)

[中文文档](/solution/3800-3899/3839.Number%20of%20Prefix%20Connected%20Groups/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code> and an integer <code>k</code>.</p>

<p>Two words <code>a</code> and <code>b</code> at <strong>distinct indices</strong> are <strong><span data-keyword="string-prefix">prefix</span>-connected</strong> if <code>a[0..k-1] == b[0..k-1]</code>.</p>

<p>A <strong>connected group</strong> is a set of words such that each pair of words is prefix-connected.</p>

<p>Return the <strong>number of connected groups</strong> that contain <strong>at least</strong> two words, formed from the given words.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Words with length less than <code>k</code> cannot join any group and are ignored.</li>
	<li>Duplicate strings are treated as separate words.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;apple&quot;,&quot;apply&quot;,&quot;banana&quot;,&quot;bandit&quot;], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Words sharing the same first <code>k = 2</code> letters are grouped together:</p>

<ul>
	<li><code>words[0] = &quot;apple&quot;</code> and <code>words[1] = &quot;apply&quot;</code> share prefix <code>&quot;ap&quot;</code>.</li>
	<li><code>words[2] = &quot;banana&quot;</code> and <code>words[3] = &quot;bandit&quot;</code> share prefix <code>&quot;ba&quot;</code>.</li>
</ul>

<p>Thus, there are 2 connected groups, each containing at least two words.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;car&quot;,&quot;cat&quot;,&quot;cartoon&quot;], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Words are evaluated for a prefix of length <code>k = 3</code>:</p>

<ul>
	<li><code>words[0] = &quot;car&quot;</code> and <code>words[2] = &quot;cartoon&quot;</code> share prefix <code>&quot;car&quot;</code>.</li>
	<li><code>words[1] = &quot;cat&quot;</code> does not share a 3-length prefix with any other word.</li>
</ul>

<p>Thus, there is 1 connected group.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = </span>[&quot;bat&quot;,&quot;dog&quot;,&quot;dog&quot;,&quot;doggy&quot;,&quot;bat&quot;]<span class="example-io">, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Words are evaluated for a prefix of length <code>k = 3</code>:</p>

<ul>
	<li><code>words[0] = &quot;bat&quot;</code> and <code>words[4] = &quot;bat&quot;</code> form a group.</li>
	<li><code>words[1] = &quot;dog&quot;</code>, <code>words[2] = &quot;dog&quot;</code> and <code>words[3] = &quot;doggy&quot;</code> share prefix <code>&quot;dog&quot;</code>.</li>
</ul>

<p>Thus, there are 2 connected groups, each containing at least two words.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li>All strings in <code>words</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{cnt}$ to count the number of occurrences of the prefix composed of the first $k$ characters of each string with length greater than or equal to $k$. Finally, we count the number of keys in $\textit{cnt}$ with values greater than $1$, which is the number of connected groups.

The time complexity is $O(n \times k)$, and the space complexity is $O(n)$, where $n$ is the length of $\textit{words}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        cnt = Counter()
        for w in words:
            if len(w) >= k:
                cnt[w[:k]] += 1
        return sum(v > 1 for v in cnt.values())
```

#### Java

```java
class Solution {
    public int prefixConnected(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            if (w.length() >= k) {
                cnt.merge(w.substring(0, k), 1, Integer::sum);
            }
        }
        int ans = 0;
        for (var v : cnt.values()) {
            if (v > 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int prefixConnected(vector<string>& words, int k) {
        unordered_map<string, int> cnt;
        for (const auto& w : words) {
            if (w.size() >= k) {
                ++cnt[w.substr(0, k)];
            }
        }
        int ans = 0;
        for (const auto& [_, v] : cnt) {
            if (v > 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func prefixConnected(words []string, k int) int {
	cnt := make(map[string]int)
	for _, w := range words {
		if len(w) >= k {
			cnt[w[:k]]++
		}
	}
	ans := 0
	for _, v := range cnt {
		if v > 1 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function prefixConnected(words: string[], k: number): number {
    const cnt = new Map<string, number>();

    for (const w of words) {
        if (w.length >= k) {
            const key = w.substring(0, k);
            cnt.set(key, (cnt.get(key) ?? 0) + 1);
        }
    }

    let ans = 0;
    for (const v of cnt.values()) {
        if (v > 1) {
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
