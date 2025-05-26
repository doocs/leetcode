---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2131.Longest%20Palindrome%20by%20Concatenating%20Two%20Letter%20Words/README_EN.md
rating: 1556
source: Biweekly Contest 69 Q3
tags:
    - Greedy
    - Array
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [2131. Longest Palindrome by Concatenating Two Letter Words](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words)

[中文文档](/solution/2100-2199/2131.Longest%20Palindrome%20by%20Concatenating%20Two%20Letter%20Words/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code>. Each element of <code>words</code> consists of <strong>two</strong> lowercase English letters.</p>

<p>Create the <strong>longest possible palindrome</strong> by selecting some elements from <code>words</code> and concatenating them in <strong>any order</strong>. Each element can be selected <strong>at most once</strong>.</p>

<p>Return <em>the <strong>length</strong> of the longest palindrome that you can create</em>. If it is impossible to create any palindrome, return <code>0</code>.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forward and backward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;lc&quot;,&quot;cl&quot;,&quot;gg&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> One longest palindrome is &quot;lc&quot; + &quot;gg&quot; + &quot;cl&quot; = &quot;lcggcl&quot;, of length 6.
Note that &quot;clgglc&quot; is another longest palindrome that can be created.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;ty&quot;,&quot;yt&quot;,&quot;lc&quot;,&quot;cl&quot;,&quot;ab&quot;]
<strong>Output:</strong> 8
<strong>Explanation:</strong> One longest palindrome is &quot;ty&quot; + &quot;lc&quot; + &quot;cl&quot; + &quot;yt&quot; = &quot;tylcclyt&quot;, of length 8.
Note that &quot;lcyttycl&quot; is another longest palindrome that can be created.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cc&quot;,&quot;ll&quot;,&quot;xx&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One longest palindrome is &quot;cc&quot;, of length 2.
Note that &quot;ll&quot; is another longest palindrome that can be created, and so is &quot;xx&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Hash Table

First, we use a hash table $\textit{cnt}$ to count the occurrences of each word.

Iterate through each word $k$ and its count $v$ in $\textit{cnt}$:

-   If the two letters in $k$ are the same, we can concatenate $\left \lfloor \frac{v}{2}  \right \rfloor \times 2$ copies of $k$ to the front and back of the palindrome. If there is one $k$ left, we can record it in $x$ for now.

-   If the two letters in $k$ are different, we need to find a word $k'$ such that the two letters in $k'$ are the reverse of $k$, i.e., $k' = k[1] + k[0]$. If $k'$ exists, we can concatenate $\min(v, \textit{cnt}[k'])$ copies of $k$ to the front and back of the palindrome.

After the iteration, if $x$ is not empty, we can also place one word in the middle of the palindrome.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of words.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPalindrome(self, words: List[str]) -> int:
        cnt = Counter(words)
        ans = x = 0
        for k, v in cnt.items():
            if k[0] == k[1]:
                x += v & 1
                ans += v // 2 * 2 * 2
            else:
                ans += min(v, cnt[k[::-1]]) * 2
        ans += 2 if x else 0
        return ans
```

#### Java

```java
class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        int ans = 0, x = 0;
        for (var e : cnt.entrySet()) {
            var k = e.getKey();
            var rk = new StringBuilder(k).reverse().toString();
            int v = e.getValue();
            if (k.charAt(0) == k.charAt(1)) {
                x += v & 1;
                ans += v / 2 * 2 * 2;
            } else {
                ans += Math.min(v, cnt.getOrDefault(rk, 0)) * 2;
            }
        }
        ans += x > 0 ? 2 : 0;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPalindrome(vector<string>& words) {
        unordered_map<string, int> cnt;
        for (auto& w : words) cnt[w]++;
        int ans = 0, x = 0;
        for (auto& [k, v] : cnt) {
            string rk = k;
            reverse(rk.begin(), rk.end());
            if (k[0] == k[1]) {
                x += v & 1;
                ans += v / 2 * 2 * 2;
            } else if (cnt.count(rk)) {
                ans += min(v, cnt[rk]) * 2;
            }
        }
        ans += x ? 2 : 0;
        return ans;
    }
};
```

#### Go

```go
func longestPalindrome(words []string) int {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	ans, x := 0, 0
	for k, v := range cnt {
		if k[0] == k[1] {
			x += v & 1
			ans += v / 2 * 2 * 2
		} else {
			rk := string([]byte{k[1], k[0]})
			if y, ok := cnt[rk]; ok {
				ans += min(v, y) * 2
			}
		}
	}
	if x > 0 {
		ans += 2
	}
	return ans
}
```

#### TypeScript

```ts
function longestPalindrome(words: string[]): number {
    const cnt = new Map<string, number>();
    for (const w of words) cnt.set(w, (cnt.get(w) || 0) + 1);
    let [ans, x] = [0, 0];
    for (const [k, v] of cnt.entries()) {
        if (k[0] === k[1]) {
            x += v & 1;
            ans += Math.floor(v / 2) * 2 * 2;
        } else {
            ans += Math.min(v, cnt.get(k[1] + k[0]) || 0) * 2;
        }
    }
    ans += x ? 2 : 0;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
