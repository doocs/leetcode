---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3805.Count%20Caesar%20Cipher%20Pairs/README_EN.md
---

<!-- problem:start -->

# [3805. Count Caesar Cipher Pairs](https://leetcode.com/problems/count-caesar-cipher-pairs)

[中文文档](/solution/3800-3899/3805.Count%20Caesar%20Cipher%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>words</code> of <code>n</code> strings. Each string has length <code>m</code> and contains only lowercase English letters.</p>

<p>Two strings <code>s</code> and <code>t</code> are <strong>similar</strong> if we can apply the following operation any number of times (possibly zero times) so that <code>s</code> and <code>t</code> become <strong>equal</strong>.</p>

<ul>
	<li>Choose either <code>s</code> or <code>t</code>.</li>
	<li>Replace <strong>every</strong> letter in the chosen string with the next letter in the alphabet cyclically. The next letter after <code>&#39;z&#39;</code> is <code>&#39;a&#39;</code>.</li>
</ul>

<p>Count the number of pairs of indices <code>(i, j)</code> such that:</p>

<ul>
	<li><code>i &lt; j</code></li>
	<li><code>words[i]</code> and <code>words[j]</code> are <strong>similar</strong>.</li>
</ul>

<p>Return an integer denoting the number of such pairs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;fusion&quot;,&quot;layout&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><code>words[0] = &quot;fusion&quot;</code> and <code>words[1] = &quot;layout&quot;</code> are similar because we can apply the operation to <code>&quot;fusion&quot;</code> 6 times. The string <code>&quot;fusion&quot;</code> changes as follows.</p>

<ul>
	<li><code>&quot;fusion&quot;</code></li>
	<li><code>&quot;gvtjpo&quot;</code></li>
	<li><code>&quot;hwukqp&quot;</code></li>
	<li><code>&quot;ixvlrq&quot;</code></li>
	<li><code>&quot;jywmsr&quot;</code></li>
	<li><code>&quot;kzxnts&quot;</code></li>
	<li><code>&quot;layout&quot;</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;ab&quot;,&quot;aa&quot;,&quot;za&quot;,&quot;aa&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>words[0] = &quot;ab&quot;</code> and <code>words[2] = &quot;za&quot;</code> are similar. <code>words[1] = &quot;aa&quot;</code> and <code>words[3] = &quot;aa&quot;</code> are similar.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == words[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n * m &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Transformation + Counting

We can transform each string into a unified form. Specifically, we convert the first character of the string to `'z'`, and then transform the other characters in the string with the same offset. This way, all similar strings will be transformed into the same form. We use a hash table $\textit{cnt}$ to record the number of occurrences of each transformed string.

Finally, we iterate through the hash table, calculate the combination number $\frac{v(v-1)}{2}$ for each string's occurrence count $v$, and add it to the answer.

The time complexity is $O(n \times m)$ and the space complexity is $O(n \times m)$, where $n$ is the length of the string array and $m$ is the length of the strings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, words: List[str]) -> int:
        cnt = defaultdict(int)
        for s in words:
            t = list(s)
            k = ord("z") - ord(t[0])
            for i in range(1, len(t)):
                t[i] = chr((ord(t[i]) - ord("a") + k) % 26 + ord("a"))
            t[0] = "z"
            cnt["".join(t)] += 1
        return sum(v * (v - 1) // 2 for v in cnt.values())
```

#### Java

```java
class Solution {
    public long countPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (String s : words) {
            char[] t = s.toCharArray();
            int k = 'z' - t[0];
            for (int i = 1; i < t.length; i++) {
                t[i] = (char)('a' + (t[i] - 'a' + k) % 26);
            }
            t[0] = 'z';
            cnt.merge(new String(t), 1, Integer::sum);
        }
        for (int v : cnt.values()) {
            ans += 1L * v * (v - 1) / 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPairs(vector<string>& words) {
        unordered_map<string, int> cnt;
        long long ans = 0;
        for (auto& s : words) {
            string t = s;
            int k = 'z' - t[0];
            for (int i = 1; i < t.size(); i++) {
                t[i] = 'a' + (t[i] - 'a' + k) % 26;
            }
            t[0] = 'z';
            cnt[t]++;
        }
        for (auto& [key, v] : cnt) {
            ans += 1LL * v * (v - 1) / 2;
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(words []string) int64 {
	cnt := make(map[string]int)
	var ans int64 = 0
	for _, s := range words {
		t := []rune(s)
		k := 'z' - t[0]
		for i := 1; i < len(t); i++ {
			t[i] = 'a' + (t[i]-'a'+k)%26
		}
		t[0] = 'z'
		key := string(t)
		cnt[key]++
	}
	for _, v := range cnt {
		ans += int64(v) * int64(v-1) / 2
	}
	return ans
}
```

#### TypeScript

```ts
function countPairs(words: string[]): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (const s of words) {
        const t = s.split('');
        const k = 'z'.charCodeAt(0) - t[0].charCodeAt(0);
        for (let i = 1; i < t.length; i++) {
            t[i] = String.fromCharCode(97 + ((t[i].charCodeAt(0) - 97 + k) % 26));
        }
        t[0] = 'z';
        const key = t.join('');
        cnt.set(key, (cnt.get(key) || 0) + 1);
    }
    for (const v of cnt.values()) {
        ans += (v * (v - 1)) / 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
