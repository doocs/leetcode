# [2743. Count Substrings Without Repeating Character 🔒](https://leetcode.com/problems/count-substrings-without-repeating-character)

[中文文档](/solution/2700-2799/2743.Count%20Substrings%20Without%20Repeating%20Character/README.md)

<!-- tags:Hash Table,String,Sliding Window -->

<!-- difficulty:Medium -->

## Description

<p>You are given a string <code>s</code> consisting only of lowercase English letters. We call a substring <b>special</b> if it contains no character which has occurred at least twice (in other words, it does not contain a repeating character). Your task is to count the number of <b>special</b> substrings. For example, in the string <code>&quot;pop&quot;</code>, the substring <code>&quot;po&quot;</code> is a <strong>special</strong> substring, however, <code>&quot;pop&quot;</code> is not <strong>special</strong> (since <code>&#39;p&#39;</code> has occurred twice).</p>

<p>Return <em>the number of <b>special</b> substrings.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string. For example, <code>&quot;abc&quot;</code> is a substring of <code>&quot;abcd&quot;</code>, but <code>&quot;acd&quot;</code> is not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> Since each character occurs once, every substring is a special substring. We have 4 substrings of length one, 3 of length two, 2 of length three, and 1 substring of length four. So overall there are 4 + 3 + 2 + 1 = 10 special substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ooo&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Any substring with a length of at least two contains a repeating character. So we have to count the number of substrings of length one, which is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> Special substrings are as follows (sorted by their start positions):
Special substrings of length 1: &quot;a&quot;, &quot;b&quot;, &quot;a&quot;, &quot;b&quot;
Special substrings of length 2: &quot;ab&quot;, &quot;ba&quot;, &quot;ab&quot;
And it can be shown that there are no special substrings with a length of at least three. So the answer would be 4 + 3 = 7.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
</ul>

## Solutions

### Solution 1: Counting + Two Pointers

We use two pointers $j$ and $i$ to represent the left and right boundaries of the current substring, and an array $cnt$ of length $26$ to count the occurrence of each character in the current substring. We traverse the string from left to right. Each time we traverse to position $i$, we increase the occurrence of $s[i]$, and then check whether $s[i]$ appears at least twice. If so, we need to decrease the occurrence of $s[j]$ and move $j$ one step to the right, until the occurrence of $s[i]$ does not exceed once. In this way, we get the length of the longest special substring ending with $s[i]$, which is $i - j + 1$, so the number of special substrings ending with $s[i]$ is $i - j + 1$. Finally, we add up the number of special substrings ending at each position to get the answer.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string $s$, and $C$ is the size of the character set. In this problem, the character set consists of lowercase English letters, so $C = 26$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfSpecialSubstrings(self, s: str) -> int:
        cnt = Counter()
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[j]] -= 1
                j += 1
            ans += i - j + 1
        return ans
```

```java
class Solution {
    public int numberOfSpecialSubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s.charAt(i) - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s.charAt(j++) - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSpecialSubstrings(string s) {
        int n = s.size();
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s[i] - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s[j++] - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
};
```

```go
func numberOfSpecialSubstrings(s string) (ans int) {
	j := 0
	cnt := [26]int{}
	for i, c := range s {
		k := c - 'a'
		cnt[k]++
		for cnt[k] > 1 {
			cnt[s[j]-'a']--
			j++
		}
		ans += i - j + 1
	}
	return
}
```

```ts
function numberOfSpecialSubstrings(s: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    const cnt: number[] = Array(26).fill(0);
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        const k = idx(s[i]);
        ++cnt[k];
        while (cnt[k] > 1) {
            --cnt[idx(s[j++])];
        }
        ans += i - j + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
