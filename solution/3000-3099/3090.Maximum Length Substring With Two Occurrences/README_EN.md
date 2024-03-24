# [3090. Maximum Length Substring With Two Occurrences](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences)

[中文文档](/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README.md)

<!-- tags: -->

## Description

Given a string <code>s</code>, return the <strong>maximum</strong> length of a <span data-keyword="substring">substring</span>&nbsp;such that it contains <em>at most two occurrences</em> of each character.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bcbbbcba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>
The following substring has a length of 4 and contains at most two occurrences of each character: <code>&quot;bcbb<u>bcba</u>&quot;</code>.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>
The following substring has a length of 2 and contains at most two occurrences of each character: <code>&quot;<u>aa</u>aa&quot;</code>.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Two Pointers

We use two pointers $i$ and $j$ to maintain a sliding window, and an array $cnt$ to record the occurrence times of each character in the window.

In each iteration, we add the character $c$ at the pointer $j$ into the window, then check if $cnt[c]$ is greater than $2$. If it is, we move the pointer $i$ to the right until $cnt[c]$ is less than or equal to $2$. At this point, we update the answer $ans = \max(ans, j - i + 1)$.

Finally, we return the answer $ans$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, and in this problem, $\Sigma = 26$.

<!-- tabs:start -->

```python
class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        cnt = Counter()
        ans = i = 0
        for j, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 2:
                cnt[s[i]] -= 1
                i += 1
            ans = max(ans, j - i + 1)
        return ans
```

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] cnt = new int[26];
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s.charAt(j) - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s.charAt(i++) - 'a'];
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumLengthSubstring(string s) {
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s[j] - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s[i++] - 'a'];
            }
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};
```

```go
func maximumLengthSubstring(s string) (ans int) {
	cnt := [26]int{}
	i := 0
	for j, c := range s {
		idx := c - 'a'
		cnt[idx]++
		for cnt[idx] > 2 {
			cnt[s[i]-'a']--
			i++
		}
		ans = max(ans, j-i+1)
	}
	return
}
```

```ts
function maximumLengthSubstring(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (let i = 0, j = 0; j < s.length; ++j) {
        const idx = s[j].charCodeAt(0) - 'a'.charCodeAt(0);
        ++cnt[idx];
        while (cnt[idx] > 2) {
            --cnt[s[i++].charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
