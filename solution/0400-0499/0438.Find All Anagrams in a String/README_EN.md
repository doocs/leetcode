---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README_EN.md
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string)

[中文文档](/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>s</code> and <code>p</code>, return an array of all the start indices of <code>p</code>&#39;s <span data-keyword="anagram">anagrams</span> in <code>s</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbaebabacd&quot;, p = &quot;abc&quot;
<strong>Output:</strong> [0,6]
<strong>Explanation:</strong>
The substring with start index = 0 is &quot;cba&quot;, which is an anagram of &quot;abc&quot;.
The substring with start index = 6 is &quot;bac&quot;, which is an anagram of &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;, p = &quot;ab&quot;
<strong>Output:</strong> [0,1,2]
<strong>Explanation:</strong>
The substring with start index = 0 is &quot;ab&quot;, which is an anagram of &quot;ab&quot;.
The substring with start index = 1 is &quot;ba&quot;, which is an anagram of &quot;ab&quot;.
The substring with start index = 2 is &quot;ab&quot;, which is an anagram of &quot;ab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> and <code>p</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        ans = []
        if m < n:
            return ans
        cnt1 = Counter(p)
        cnt2 = Counter(s[: n - 1])
        for i in range(n - 1, m):
            cnt2[s[i]] += 1
            if cnt1 == cnt2:
                ans.append(i - n + 1)
            cnt2[s[i - n + 1]] -= 1
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[p.charAt(i) - 'a'];
        }
        int[] cnt2 = new int[26];
        for (int i = 0; i < n - 1; ++i) {
            ++cnt2[s.charAt(i) - 'a'];
        }
        for (int i = n - 1; i < m; ++i) {
            ++cnt2[s.charAt(i) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                ans.add(i - n + 1);
            }
            --cnt2[s.charAt(i - n + 1) - 'a'];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        int m = s.size(), n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt1(26);
        for (char& c : p) {
            ++cnt1[c - 'a'];
        }
        vector<int> cnt2(26);
        for (int i = 0; i < n - 1; ++i) {
            ++cnt2[s[i] - 'a'];
        }
        for (int i = n - 1; i < m; ++i) {
            ++cnt2[s[i] - 'a'];
            if (cnt1 == cnt2) {
                ans.push_back(i - n + 1);
            }
            --cnt2[s[i - n + 1] - 'a'];
        }
        return ans;
    }
};
```

#### Go

```go
func findAnagrams(s string, p string) (ans []int) {
	m, n := len(s), len(p)
	if m < n {
		return
	}
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range p {
		cnt1[c-'a']++
	}
	for _, c := range s[:n-1] {
		cnt2[c-'a']++
	}
	for i := n - 1; i < m; i++ {
		cnt2[s[i]-'a']++
		if cnt1 == cnt2 {
			ans = append(ans, i-n+1)
		}
		cnt2[s[i-n+1]-'a']--
	}
	return
}
```

#### TypeScript

```ts
function findAnagrams(s: string, p: string): number[] {
    const m = s.length;
    const n = p.length;
    const ans: number[] = [];
    if (m < n) {
        return ans;
    }
    const cnt1: number[] = new Array(26).fill(0);
    const cnt2: number[] = new Array(26).fill(0);
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    for (const c of p) {
        ++cnt1[idx(c)];
    }
    for (const c of s.slice(0, n - 1)) {
        ++cnt2[idx(c)];
    }
    for (let i = n - 1; i < m; ++i) {
        ++cnt2[idx(s[i])];
        if (cnt1.toString() === cnt2.toString()) {
            ans.push(i - n + 1);
        }
        --cnt2[idx(s[i - n + 1])];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_anagrams(s: String, p: String) -> Vec<i32> {
        let (s, p) = (s.as_bytes(), p.as_bytes());
        let (m, n) = (s.len(), p.len());
        let mut ans = vec![];
        if m < n {
            return ans;
        }

        let mut cnt = [0; 26];
        for i in 0..n {
            cnt[(p[i] - b'a') as usize] += 1;
            cnt[(s[i] - b'a') as usize] -= 1;
        }
        for i in n..m {
            if cnt.iter().all(|&v| v == 0) {
                ans.push((i - n) as i32);
            }
            cnt[(s[i] - b'a') as usize] -= 1;
            cnt[(s[i - n] - b'a') as usize] += 1;
        }
        if cnt.iter().all(|&v| v == 0) {
            ans.push((m - n) as i32);
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public IList<int> FindAnagrams(string s, string p) {
        int m = s.Length, n = p.Length;
        IList<int> ans = new List<int>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[p[i] - 'a'];
        }
        for (int i = 0, j = 0; i < m; ++i) {
            int k = s[i] - 'a';
            ++cnt2[k];
            while (cnt2[k] > cnt1[k]) {
                --cnt2[s[j++] - 'a'];
            }
            if (i - j + 1 == n) {
                ans.Add(j);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        ans = []
        if m < n:
            return ans
        cnt1 = Counter(p)
        cnt2 = Counter()
        j = 0
        for i, c in enumerate(s):
            cnt2[c] += 1
            while cnt2[c] > cnt1[c]:
                cnt2[s[j]] -= 1
                j += 1
            if i - j + 1 == n:
                ans.append(j)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[p.charAt(i) - 'a'];
        }
        int[] cnt2 = new int[26];
        for (int i = 0, j = 0; i < m; ++i) {
            int k = s.charAt(i) - 'a';
            ++cnt2[k];
            while (cnt2[k] > cnt1[k]) {
                --cnt2[s.charAt(j++) - 'a'];
            }
            if (i - j + 1 == n) {
                ans.add(j);
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
    vector<int> findAnagrams(string s, string p) {
        int m = s.size(), n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt1(26);
        for (char& c : p) {
            ++cnt1[c - 'a'];
        }
        vector<int> cnt2(26);
        for (int i = 0, j = 0; i < m; ++i) {
            int k = s[i] - 'a';
            ++cnt2[k];
            while (cnt2[k] > cnt1[k]) {
                --cnt2[s[j++] - 'a'];
            }
            if (i - j + 1 == n) {
                ans.push_back(j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findAnagrams(s string, p string) (ans []int) {
	m, n := len(s), len(p)
	if m < n {
		return
	}
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range p {
		cnt1[c-'a']++
	}
	j := 0
	for i, c := range s {
		cnt2[c-'a']++
		for cnt2[c-'a'] > cnt1[c-'a'] {
			cnt2[s[j]-'a']--
			j++
		}
		if i-j+1 == n {
			ans = append(ans, j)
		}
	}
	return
}
```

#### TypeScript

```ts
function findAnagrams(s: string, p: string): number[] {
    const m = s.length;
    const n = p.length;
    const ans: number[] = [];
    if (m < n) {
        return ans;
    }
    const cnt1: number[] = Array(26).fill(0);
    const cnt2: number[] = Array(26).fill(0);
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    for (const c of p) {
        ++cnt1[idx(c)];
    }
    for (let i = 0, j = 0; i < m; ++i) {
        const k = idx(s[i]);
        ++cnt2[k];
        while (cnt2[k] > cnt1[k]) {
            --cnt2[idx(s[j++])];
        }
        if (i - j + 1 === n) {
            ans.push(j);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
