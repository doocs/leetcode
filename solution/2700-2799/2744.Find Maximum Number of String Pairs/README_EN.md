---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2744.Find%20Maximum%20Number%20of%20String%20Pairs/README_EN.md
rating: 1405
source: Biweekly Contest 107 Q1
tags:
    - Array
    - Hash Table
    - String
    - Simulation
---

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

We can use a hash table $cnt$ to store the number of occurrences of each reversed string in the array $words$.

We iterate through the array $words$. For each string $w$, we add the number of occurrences of its reversed string to the answer, then increment the count of $w$ by $1$.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $words$.

<!-- tabs:start -->

```python
class Solution:
    def maximumNumberOfStringPairs(self, words: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for w in words:
            ans += cnt[w[::-1]]
            cnt[w] += 1
        return ans
```

```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (var w : words) {
            int a = w.charAt(0) - 'a', b = w.charAt(1) - 'a';
            ans += cnt.getOrDefault(b << 5 | a, 0);
            cnt.merge(a << 5 | b, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            int a = w[0] - 'a', b = w[1] - 'a';
            ans += cnt[b << 5 | a];
            cnt[a << 5 | b]++;
        }
        return ans;
    }
};
```

```go
func maximumNumberOfStringPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, w := range words {
		a, b := int(w[0]-'a'), int(w[1]-'a')
		ans += cnt[b<<5|a]
		cnt[a<<5|b]++
	}
	return
}
```

```ts
function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: { [key: number]: number } = {};
    let ans = 0;
    for (const w of words) {
        const [a, b] = [w.charCodeAt(0) - 97, w.charCodeAt(w.length - 1) - 97];
        ans += cnt[(b << 5) | a] || 0;
        cnt[(a << 5) | b] = (cnt[(a << 5) | b] || 0) + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
