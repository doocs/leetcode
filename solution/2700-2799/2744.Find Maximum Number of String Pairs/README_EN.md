# [2744. Find Maximum Number of String Pairs](https://leetcode.com/problems/find-maximum-number-of-string-pairs)

[中文文档](/solution/2700-2799/2744.Find%20Maximum%20Number%20of%20String%20Pairs/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>words</code> consisting of <strong>distinct</strong> strings.</p>

<p>The string <code>words[i]</code> can be paired with the string <code>words[j]</code> if:</p>

<ul>
	<li>The string <code>words[i]</code> is equal to the reversed string of <code>words[j]</code>.</li>
	<li><code>0 &lt;= i &lt; j &lt; words.length</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of pairs that can be formed from the array </em><code>words</code><em>.</em></p>

<p>Note that&nbsp;each string can belong in&nbsp;<strong>at most one</strong> pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cd&quot;,&quot;ac&quot;,&quot;dc&quot;,&quot;ca&quot;,&quot;zz&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, we can form 2 pair of strings in the following way:
- We pair the 0<sup>th</sup> string with the 2<sup>nd</sup> string, as the reversed string of word[0] is &quot;dc&quot; and is equal to words[2].
- We pair the 1<sup>st</sup> string with the 3<sup>rd</sup> string, as the reversed string of word[1] is &quot;ca&quot; and is equal to words[3].
It can be proven that 2 is the maximum number of pairs that can be formed.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;ba&quot;,&quot;cc&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, we can form 1 pair of strings in the following way:
- We pair the 0<sup>th</sup> string with the 1<sup>st</sup> string, as the reversed string of words[1] is &quot;ab&quot; and is equal to words[0].
It can be proven that 1 is the maximum number of pairs that can be formed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aa&quot;,&quot;ab&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this example, we are unable to form any pair of strings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words</code>&nbsp;consists of distinct strings.</li>
	<li><code>words[i]</code>&nbsp;contains only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Hash Table

We can use a hash table $cnt$ to store the number of occurrences of each string's reversed string in the array $words$.

Traverse the array $words$, for each string $w$, we directly add the value of $cnt[w]$ to the answer, then increase the occurrence count of $w$'s reversed string by $1$.

After the traversal ends, we can get the maximum number of matches.

The time complexity is $O(L)$, and the space complexity is $O(L)$, where $L$ is the sum of the lengths of the strings in the array $words$.

<!-- tabs:start -->

```python
class Solution:
    def maximumNumberOfStringPairs(self, words: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for w in words:
            ans += cnt[w]
            cnt[w[::-1]] += 1
        return ans
```

```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>(words.length);
        int ans = 0;
        for (String w : words) {
            ans += cnt.getOrDefault(w, 0);
            cnt.merge(new StringBuilder(w).reverse().toString(), 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            ans += cnt[w];
            reverse(w.begin(), w.end());
            cnt[w]++;
        }
        return ans;
    }
};
```

```go
func maximumNumberOfStringPairs(words []string) (ans int) {
	cnt := map[string]int{}
	for _, w := range words {
		ans += cnt[w]
		s := []byte(w)
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			s[i], s[j] = s[j], s[i]
		}
		cnt[string(s)]++
	}
	return
}
```

```ts
function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: Map<string, number> = new Map();
    let ans = 0;
    for (const w of words) {
        ans += cnt.get(w) || 0;
        const s = w.split('').reverse().join('');
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
