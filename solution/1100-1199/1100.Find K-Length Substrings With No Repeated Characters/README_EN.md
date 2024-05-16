---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README_EN.md
rating: 1348
source: Biweekly Contest 3 Q2
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [1100. Find K-Length Substrings With No Repeated Characters 🔒](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters)

[中文文档](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, return <em>the number of substrings in </em><code>s</code><em> of length </em><code>k</code><em> with no repeated characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;havefunonleetcode&quot;, k = 5
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 substrings they are: &#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;home&quot;, k = 5
<strong>Output:</strong> 0
<strong>Explanation:</strong> Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window + Hash Table

We maintain a sliding window of length $k$, and use a hash table $cnt$ to count the occurrences of each character in the window.

First, we add the first $k$ characters of the string $s$ to the hash table $cnt$, and check whether the size of $cnt$ is equal to $k$. If it is, it means that all characters in the window are different, and the answer $ans$ is incremented by one.

Next, we start to traverse the string $s$ from $k$. Each time we add $s[i]$ to the hash table $cnt$, and at the same time subtract $s[i-k]$ from the hash table $cnt$ by one. If $cnt[s[i-k]]$ is equal to $0$ after subtraction, we remove $s[i-k]$ from the hash table $cnt$. If the size of the hash table $cnt$ is equal to $k$ at this time, it means that all characters in the window are different, and the answer $ans$ is incremented by one.

Finally, return the answer $ans$.

The time complexity is $O(n)$, and the space complexity is $O(\min(k, |\Sigma|))$, where $n$ is the length of the string $s$; and $\Sigma$ is the character set, in this problem the character set is lowercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        cnt = Counter(s[:k])
        ans = int(len(cnt) == k)
        for i in range(k, len(s)):
            cnt[s[i]] += 1
            cnt[s[i - k]] -= 1
            if cnt[s[i - k]] == 0:
                cnt.pop(s[i - k])
            ans += int(len(cnt) == k)
        return ans
```

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (n < k) {
            return 0;
        }
        Map<Character, Integer> cnt = new HashMap<>(k);
        for (int i = 0; i < k; ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
        }
        int ans = cnt.size() == k ? 1 : 0;
        for (int i = k; i < n; ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
            if (cnt.merge(s.charAt(i - k), -1, Integer::sum) == 0) {
                cnt.remove(s.charAt(i - k));
            }
            ans += cnt.size() == k ? 1 : 0;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int n = s.size();
        if (n < k) {
            return 0;
        }
        unordered_map<char, int> cnt;
        for (int i = 0; i < k; ++i) {
            ++cnt[s[i]];
        }
        int ans = cnt.size() == k;
        for (int i = k; i < n; ++i) {
            ++cnt[s[i]];
            if (--cnt[s[i - k]] == 0) {
                cnt.erase(s[i - k]);
            }
            ans += cnt.size() == k;
        }
        return ans;
    }
};
```

```go
func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	n := len(s)
	if n < k {
		return
	}
	cnt := map[byte]int{}
	for i := 0; i < k; i++ {
		cnt[s[i]]++
	}
	if len(cnt) == k {
		ans++
	}
	for i := k; i < n; i++ {
		cnt[s[i]]++
		cnt[s[i-k]]--
		if cnt[s[i-k]] == 0 {
			delete(cnt, s[i-k])
		}
		if len(cnt) == k {
			ans++
		}
	}
	return
}
```

```ts
function numKLenSubstrNoRepeats(s: string, k: number): number {
    const n = s.length;
    if (n < k) {
        return 0;
    }
    const cnt: Map<string, number> = new Map();
    for (let i = 0; i < k; ++i) {
        cnt.set(s[i], (cnt.get(s[i]) ?? 0) + 1);
    }
    let ans = cnt.size === k ? 1 : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(s[i], (cnt.get(s[i]) ?? 0) + 1);
        cnt.set(s[i - k], (cnt.get(s[i - k]) ?? 0) - 1);
        if (cnt.get(s[i - k]) === 0) {
            cnt.delete(s[i - k]);
        }
        ans += cnt.size === k ? 1 : 0;
    }
    return ans;
}
```

```php
class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function numKLenSubstrNoRepeats($s, $k) {
        $n = strlen($s);
        if ($n < $k) {
            return 0;
        }
        $cnt = [];
        for ($i = 0; $i < $k; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
        }
        $ans = count($cnt) == $k ? 1 : 0;
        for ($i = $k; $i < $n; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
            if ($cnt[$s[$i - $k]] - 1 == 0) {
                unset($cnt[$s[$i - $k]]);
            } else {
                $cnt[$s[$i - $k]]--;
            }
            $ans += count($cnt) == $k ? 1 : 0;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
