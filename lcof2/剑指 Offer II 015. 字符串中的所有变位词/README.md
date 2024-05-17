---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20015.%20%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B8%AD%E7%9A%84%E6%89%80%E6%9C%89%E5%8F%98%E4%BD%8D%E8%AF%8D/README.md
---

<!-- problem:start -->

# [剑指 Offer II 015. 字符串中的所有变位词](https://leetcode.cn/problems/VabMRr)

## 题目描述

<!-- description:start -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和<b>&nbsp;</b><code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有 <code>p</code> 的&nbsp;<strong>变位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>

<p><strong>变位词 </strong>指字母相同，但排列不同的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;cbaebabacd&quot;, p = &quot;abc&quot;
<strong>输出: </strong>[0,6]
<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;cba&quot;, 它是 &quot;abc&quot; 的变位词。
起始索引等于 6 的子串是 &quot;bac&quot;, 它是 &quot;abc&quot; 的变位词。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;abab&quot;, p = &quot;ab&quot;
<strong>输出: </strong>[0,1,2]
<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的变位词。
起始索引等于 1 的子串是 &quot;ba&quot;, 它是 &quot;ab&quot; 的变位词。
起始索引等于 2 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的变位词。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;和 <code>p</code> 仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 438&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/" style="background-color: rgb(255, 255, 255);">https://leetcode.cn/problems/find-all-anagrams-in-a-string/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

不妨记字符串 $s$ 的长度为 $m$，字符串 $p$ 的长度为 $n$。

我们观察发现，题目实际上等价于判断字符串 $s$ 中是否存在窗口大小为 $n$，且窗口内的字符及其个数与字符串 $p$ 相同的子串。

因此，我们先用哈希表或数组 $cnt2$ 统计字符串 $p$ 中每个字符出现的次数，然后遍历字符串 $s$，维护一个窗口大小为 $n$ 的滑动窗口，用哈希表或数组 $cnt1$ 统计窗口内每个字符出现的次数，当 $cnt1 = cnt2$ 时，说明窗口内的字符及其个数与字符串 $p$ 相同，我们将窗口的起始索引加入答案数组。

遍历结束后，返回答案数组。

时间复杂度 $(m + n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $m$ 和 $n$ 分别为字符串 $s$ 和 $p$ 的长度；而 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if m < n:
            return []
        cnt1 = Counter(s[:n])
        cnt2 = Counter(p)
        ans = []
        if cnt1 == cnt2:
            ans.append(0)
        for i in range(n, m):
            cnt1[s[i]] += 1
            cnt1[s[i - n]] -= 1
            if cnt1 == cnt2:
                ans.append(i - n + 1)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s.charAt(i) - 'a'];
            ++cnt2[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            ans.add(0);
        }
        for (int i = n; i < m; ++i) {
            ++cnt1[s.charAt(i) - 'a'];
            --cnt1[s.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                ans.add(i - n + 1);
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
        int m = s.size();
        int n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt1(26), cnt2(26);
        for (int i = 0; i < n; ++i) {
            ++cnt1[s[i] - 'a'];
            ++cnt2[p[i] - 'a'];
        }
        if (cnt1 == cnt2) {
            ans.push_back(0);
        }
        for (int i = n; i < m; ++i) {
            ++cnt1[s[i] - 'a'];
            --cnt1[s[i - n] - 'a'];
            if (cnt1 == cnt2) {
                ans.push_back(i - n + 1);
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
	var cnt1, cnt2 [26]int
	for i, ch := range p {
		cnt1[s[i]-'a']++
		cnt2[ch-'a']++
	}
	if cnt1 == cnt2 {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		cnt1[s[i]-'a']++
		cnt1[s[i-n]-'a']--
		if cnt1 == cnt2 {
			ans = append(ans, i-n+1)
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
    const cnt1: number[] = new Array(26).fill(0);
    const cnt2: number[] = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        ++cnt1[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt2[p[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    if (cnt1.toString() === cnt2.toString()) {
        ans.push(0);
    }
    for (let i = n; i < m; ++i) {
        ++cnt1[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt1[s[i - n].charCodeAt(0) - 'a'.charCodeAt(0)];
        if (cnt1.toString() === cnt2.toString()) {
            ans.push(i - n + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：滑动窗口优化

在方法一中，我们每次加入和移除一个字符时，都需要比较两个哈希表或数组，时间复杂度较高。我们可以维护一个变量 $diff$，表示两个大小为 $n$ 的字符串中，有多少种字符出现的个数不同。当 $diff=0$ 时，说明两个字符串中的字符个数相同。

时间复杂度 $O(m + n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $m$ 和 $n$ 分别为字符串 $s$ 和 $p$ 的长度；而 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if m < n:
            return []
        cnt = Counter()
        for a, b in zip(s, p):
            cnt[a] += 1
            cnt[b] -= 1
        diff = sum(x != 0 for x in cnt.values())
        ans = []
        if diff == 0:
            ans.append(0)
        for i in range(n, m):
            a, b = s[i - n], s[i]
            if cnt[a] == 0:
                diff += 1
            cnt[a] -= 1
            if cnt[a] == 0:
                diff -= 1
            if cnt[b] == 0:
                diff += 1
            cnt[b] += 1
            if cnt[b] == 0:
                diff -= 1
            if diff == 0:
                ans.append(i - n + 1)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[p.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            ans.add(0);
        }
        for (int i = n; i < m; ++i) {
            int a = s.charAt(i - n) - 'a';
            int b = s.charAt(i) - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                ans.add(i - n + 1);
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
        vector<int> cnt(26);
        for (int i = 0; i < n; ++i) {
            ++cnt[s[i] - 'a'];
            --cnt[p[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            ans.push_back(0);
        }
        for (int i = n; i < m; ++i) {
            int a = s[i - n] - 'a';
            int b = s[i] - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                ans.push_back(i - n + 1);
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
	cnt := [26]int{}
	for i := 0; i < n; i++ {
		cnt[s[i]-'a']++
		cnt[p[i]-'a']--
	}
	diff := 0
	for _, x := range cnt {
		if x != 0 {
			diff++
		}
	}
	if diff == 0 {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		a, b := s[i-n]-'a', s[i]-'a'
		if cnt[a] == 0 {
			diff++
		}
		cnt[a]--
		if cnt[a] == 0 {
			diff--
		}
		if cnt[b] == 0 {
			diff++
		}
		cnt[b]++
		if cnt[b] == 0 {
			diff--
		}
		if diff == 0 {
			ans = append(ans, i-n+1)
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
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        --cnt[p[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let diff = 0;
    for (const x of cnt) {
        if (x !== 0) {
            ++diff;
        }
    }
    if (diff === 0) {
        ans.push(0);
    }
    for (let i = n; i < m; ++i) {
        const a = s[i - n].charCodeAt(0) - 'a'.charCodeAt(0);
        const b = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (cnt[a] === 0) {
            ++diff;
        }
        if (--cnt[a] === 0) {
            --diff;
        }
        if (cnt[b] === 0) {
            ++diff;
        }
        if (++cnt[b] === 0) {
            --diff;
        }
        if (diff === 0) {
            ans.push(i - n + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
