---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2953.Count%20Complete%20Substrings/README_EN.md
rating: 2449
tags:
    - Hash Table
    - String
    - Sliding Window
---

# [2953. Count Complete Substrings](https://leetcode.com/problems/count-complete-substrings)

[中文文档](/solution/2900-2999/2953.Count%20Complete%20Substrings/README.md)

## Description

<p>You are given a string <code>word</code> and an integer <code>k</code>.</p>

<p>A substring <code>s</code> of <code>word</code> is <strong>complete</strong> if:</p>

<ul>
	<li>Each character in <code>s</code> occurs <strong>exactly</strong> <code>k</code> times.</li>
	<li>The difference between two adjacent characters is <strong>at most</strong> <code>2</code>. That is, for any two adjacent characters <code>c1</code> and <code>c2</code> in <code>s</code>, the absolute difference in their positions in the alphabet is <strong>at most</strong> <code>2</code>.</li>
</ul>

<p>Return <em>the number of <strong>complete </strong>substrings of</em> <code>word</code>.</p>

<p>A <strong>substring</strong> is a <strong>non-empty</strong> contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;igigee&quot;, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The complete substrings where each character appears exactly twice and the difference between adjacent characters is at most 2 are: <u><strong>igig</strong></u>ee, igig<u><strong>ee</strong></u>, <u><strong>igigee</strong></u>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aaabbbccc&quot;, k = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> The complete substrings where each character appears exactly three times and the difference between adjacent characters is at most 2 are: <strong><u>aaa</u></strong>bbbccc, aaa<u><strong>bbb</strong></u>ccc, aaabbb<u><strong>ccc</strong></u>, <strong><u>aaabbb</u></strong>ccc, aaa<u><strong>bbbccc</strong></u>, <u><strong>aaabbbccc</strong></u>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
</ul>

## Solutions

### Solution 1: Enumerate Character Types + Sliding Window

According to condition 2 in the problem description, we can find that in a complete string, the difference between two adjacent characters does not exceed 2. Therefore, we traverse the string $word$, and we can use two pointers to split $word$ into several substrings. The number of character types in these substrings does not exceed 26, and the difference between adjacent characters does not exceed 2. Next, we only need to count the number of substrings in each substring where each character appears $k$ times.

We define a function $f(s)$, which is used to count the number of substrings in the string $s$ where each character appears $k$ times. Since the number of character types in $s$ does not exceed 26, we can enumerate each character type $i$, where $1 \le i \le 26$, then the length of the substring with character type $i$ is $l = i \times k$.

We can use an array or hash table $cnt$ to maintain the number of times each character appears in a sliding window of length $l$, and use another hash table $freq$ to maintain the number of times each frequency appears. If $freq[k] = i$, that is, there are $i$ characters that appear $k$ times, then we have found a substring that meets the conditions. We can use two pointers to maintain this sliding window. Each time we move the right pointer, we increase the number of times the character pointed to by the right pointer appears and update the $freq$ array; each time we move the left pointer, we decrease the number of times the character pointed to by the left pointer appears and update the $freq$ array. After each pointer movement, we judge whether $freq[k]$ is equal to $i$. If it is equal, it means that we have found a substring that meets the conditions.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(|\Sigma|)$, where $n$ is the length of the string $word$; and $\Sigma$ is the size of the character set. In this problem, the character set is lowercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def countCompleteSubstrings(self, word: str, k: int) -> int:
        def f(s: str) -> int:
            m = len(s)
            ans = 0
            for i in range(1, 27):
                l = i * k
                if l > m:
                    break
                cnt = Counter(s[:l])
                freq = Counter(cnt.values())
                ans += freq[k] == i
                for j in range(l, m):
                    freq[cnt[s[j]]] -= 1
                    cnt[s[j]] += 1
                    freq[cnt[s[j]]] += 1

                    freq[cnt[s[j - l]]] -= 1
                    cnt[s[j - l]] -= 1
                    freq[cnt[s[j - l]]] += 1

                    ans += freq[k] == i
            return ans

        n = len(word)
        ans = i = 0
        while i < n:
            j = i + 1
            while j < n and abs(ord(word[j]) - ord(word[j - 1])) <= 2:
                j += 1
            ans += f(word[i:j])
            i = j
        return ans
```

```java
class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && Math.abs(word.charAt(j) - word.charAt(j - 1)) <= 2) {
                ++j;
            }
            ans += f(word.substring(i, j), k);
            i = j;
        }
        return ans;
    }

    private int f(String s, int k) {
        int m = s.length();
        int ans = 0;
        for (int i = 1; i <= 26 && i * k <= m; ++i) {
            int l = i * k;
            int[] cnt = new int[26];
            for (int j = 0; j < l; ++j) {
                ++cnt[s.charAt(j) - 'a'];
            }
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : cnt) {
                if (x > 0) {
                    freq.merge(x, 1, Integer::sum);
                }
            }
            if (freq.getOrDefault(k, 0) == i) {
                ++ans;
            }
            for (int j = l; j < m; ++j) {
                int a = s.charAt(j) - 'a';
                int b = s.charAt(j - l) - 'a';
                freq.merge(cnt[a], -1, Integer::sum);
                ++cnt[a];
                freq.merge(cnt[a], 1, Integer::sum);

                freq.merge(cnt[b], -1, Integer::sum);
                --cnt[b];
                freq.merge(cnt[b], 1, Integer::sum);
                if (freq.getOrDefault(k, 0) == i) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countCompleteSubstrings(string word, int k) {
        int n = word.length();
        int ans = 0;
        auto f = [&](string s) {
            int m = s.length();
            int ans = 0;
            for (int i = 1; i <= 26 && i * k <= m; ++i) {
                int l = i * k;
                int cnt[26]{};
                for (int j = 0; j < l; ++j) {
                    ++cnt[s[j] - 'a'];
                }
                unordered_map<int, int> freq;
                for (int x : cnt) {
                    if (x > 0) {
                        freq[x]++;
                    }
                }
                if (freq[k] == i) {
                    ++ans;
                }
                for (int j = l; j < m; ++j) {
                    int a = s[j] - 'a';
                    int b = s[j - l] - 'a';
                    freq[cnt[a]]--;
                    cnt[a]++;
                    freq[cnt[a]]++;

                    freq[cnt[b]]--;
                    cnt[b]--;
                    freq[cnt[b]]++;

                    if (freq[k] == i) {
                        ++ans;
                    }
                }
            }
            return ans;
        };
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && abs(word[j] - word[j - 1]) <= 2) {
                ++j;
            }
            ans += f(word.substr(i, j - i));
            i = j;
        }
        return ans;
    }
};
```

```go
func countCompleteSubstrings(word string, k int) (ans int) {
	n := len(word)
	f := func(s string) (ans int) {
		m := len(s)
		for i := 1; i <= 26 && i*k <= m; i++ {
			l := i * k
			cnt := [26]int{}
			for j := 0; j < l; j++ {
				cnt[int(s[j]-'a')]++
			}
			freq := map[int]int{}
			for _, x := range cnt {
				if x > 0 {
					freq[x]++
				}
			}
			if freq[k] == i {
				ans++
			}
			for j := l; j < m; j++ {
				a := int(s[j] - 'a')
				b := int(s[j-l] - 'a')
				freq[cnt[a]]--
				cnt[a]++
				freq[cnt[a]]++

				freq[cnt[b]]--
				cnt[b]--
				freq[cnt[b]]++

				if freq[k] == i {
					ans++
				}
			}
		}
		return
	}
	for i := 0; i < n; {
		j := i + 1
		for j < n && abs(int(word[j])-int(word[j-1])) <= 2 {
			j++
		}
		ans += f(word[i:j])
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countCompleteSubstrings(word: string, k: number): number {
    const f = (s: string): number => {
        const m = s.length;
        let ans = 0;
        for (let i = 1; i <= 26 && i * k <= m; i++) {
            const l = i * k;
            const cnt: number[] = new Array(26).fill(0);
            for (let j = 0; j < l; j++) {
                cnt[s.charCodeAt(j) - 'a'.charCodeAt(0)]++;
            }
            const freq: { [key: number]: number } = {};
            for (const x of cnt) {
                if (x > 0) {
                    freq[x] = (freq[x] || 0) + 1;
                }
            }
            if (freq[k] === i) {
                ans++;
            }

            for (let j = l; j < m; j++) {
                const a = s.charCodeAt(j) - 'a'.charCodeAt(0);
                const b = s.charCodeAt(j - l) - 'a'.charCodeAt(0);

                freq[cnt[a]]--;
                cnt[a]++;
                freq[cnt[a]] = (freq[cnt[a]] || 0) + 1;

                freq[cnt[b]]--;
                cnt[b]--;
                freq[cnt[b]] = (freq[cnt[b]] || 0) + 1;

                if (freq[k] === i) {
                    ans++;
                }
            }
        }

        return ans;
    };

    let n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && Math.abs(word.charCodeAt(j) - word.charCodeAt(j - 1)) <= 2) {
            j++;
        }
        ans += f(word.substring(i, j));
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
