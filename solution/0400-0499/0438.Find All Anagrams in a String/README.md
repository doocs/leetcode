---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README.md
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

# [438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string)

[English Version](/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;<code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>

<p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = "cbaebabacd", p = "abc"
<strong>输出: </strong>[0,6]
<strong>解释:</strong>
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "abab", p = "ab"
<strong>输出: </strong>[0,1,2]
<strong>解释:</strong>
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li>
</ul>

## 解法

### 方法一：滑动窗口

我们不妨设字符串 $s$ 的长度为 $m$，字符串 $p$ 的长度为 $n$。

如果 $m \lt n$，那么 $s$ 中不可能存在任何一个子串同 $p$ 为异位词，返回空列表即可。

当 $m \ge n$ 时，我们可以使用一个固定长度为 $n$ 的滑动窗口来维护 $s$ 的子串。为了判断子串是否为 $p$ 的异位词，我们可以用一个固定长度为 $26$ 的数组 $cnt1$ 记录 $p$ 中每个字母的出现次数，再用另一个数组 $cnt2$ 记录当前滑动窗口中每个字母的出现次数，如果这两个数组相同，那么当前滑动窗口的子串就是 $p$ 的异位词，我们记录下起始位置。

时间复杂度 $O(m \times C)$，空间复杂度 $O(C)$。其中 $m$ 是字符串 $s$ 的长度；而 $C$ 是字符集大小，在本题中字符集为所有小写字母，所以 $C = 26$。

<!-- tabs:start -->

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

### 方法二：双指针（滑动窗口优化）

我们可以对方法一进行优化，与方法一类似，我们用一个固定长度为 $26$ 的数组 $cnt1$ 记录 $p$ 中每个字母的出现次数，用另一个数组 $cnt2$ 记录当前滑动窗口中每个字母的出现次数，用指针 $i$ 和 $j$ 分别指向滑动窗口的左右边界。每一次移动指针 $j$，将 $cnt2[s[j]]$ 的值加 $1$，如果当前 $cnt2[s[j]]$ 的值大于 $cnt1[s[j]]$，则将指针 $i$ 不断右移，直到 $cnt2[s[j]]$ 的值不大于 $cnt1[s[j]]$。此时，如果滑动窗口的长度等于 $p$ 的长度，我们就找到了一个异位词，将起始位置加入答案。继续移动指针 $j$，重复上述操作，直到指针 $j$ 移动到 $s$ 的末尾。

时间复杂度 $(m + n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $p$ 的长度；而 $C$ 是字符集大小，在本题中字符集为所有小写字母，所以 $C = 26$。

<!-- tabs:start -->

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

<!-- end -->
