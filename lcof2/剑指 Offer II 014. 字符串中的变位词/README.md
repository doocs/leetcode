# [剑指 Offer II 014. 字符串中的变位词](https://leetcode.cn/problems/MPnaiL)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的某个变位词。</p>

<p>换句话说，第一个字符串的排列之一是第二个字符串的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>s1 = &quot;ab&quot; s2 = &quot;eidbaooo&quot;
<strong>输出: </strong>True
<strong>解释:</strong> s2 包含 s1 的排列之一 (&quot;ba&quot;).
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>s1= &quot;ab&quot; s2 = &quot;eidboaoo&quot;
<strong>输出:</strong> False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 567&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/permutation-in-string/">https://leetcode.cn/problems/permutation-in-string/</a></p>

## 解法

### 方法一：滑动窗口

不妨记字符串 $s1$ 的长度为 $m$，字符串 $s2$ 的长度为 $n$。

我们观察发现，题目实际上等价于判断字符串 $s2$ 中是否存在窗口大小为 $m$，且窗口内的字符及其个数与字符串 $s1$ 相同的子串。

因此，我们先用哈希表或数组 $cnt1$ 统计字符串 $s1$ 中每个字符出现的次数，然后遍历字符串 $s2$，维护一个窗口大小为 $m$ 的滑动窗口，用哈希表或数组 $cnt2$ 统计窗口内每个字符出现的次数，当 $cnt1 = cnt2$ 时，说明窗口内的字符及其个数与字符串 $s1$ 相同，返回 `true` 即可。

否则，遍历结束后，返回 `false`。

时间复杂度 $(m + n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $m$ 和 $n$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        m, n = len(s1), len(s2)
        if m > n:
            return False
        cnt1 = Counter(s1)
        cnt2 = Counter(s2[:m])
        if cnt1 == cnt2:
            return True
        for i in range(m, n):
            cnt2[s2[i]] += 1
            cnt2[s2[i - m]] -= 1
            if cnt1 == cnt2:
                return True
        return False
```

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - m) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return false;
        }
        vector<int> cnt1(26), cnt2(26);
        for (int i = 0; i < m; ++i) {
            ++cnt1[s1[i] - 'a'];
            ++cnt2[s2[i] - 'a'];
        }
        if (cnt1 == cnt2) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            ++cnt2[s2[i] - 'a'];
            --cnt2[s2[i - m] - 'a'];
            if (cnt1 == cnt2) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func checkInclusion(s1 string, s2 string) bool {
	m, n := len(s1), len(s2)
	if m > n {
		return false
	}
	var cnt1, cnt2 [26]int
	for i := 0; i < m; i++ {
		cnt1[s1[i]-'a']++
		cnt2[s2[i]-'a']++
	}
	if cnt1 == cnt2 {
		return true
	}
	for i := m; i < n; i++ {
		cnt2[s2[i]-'a']++
		cnt2[s2[i-m]-'a']--
		if cnt1 == cnt2 {
			return true
		}
	}
	return false
}
```

```ts
function checkInclusion(s1: string, s2: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m > n) {
        return false;
    }
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        ++cnt1[s1[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt2[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    if (cnt1.toString() === cnt2.toString()) {
        return true;
    }
    for (let i = m; i < n; ++i) {
        ++cnt2[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt2[s2[i - m].charCodeAt(0) - 'a'.charCodeAt(0)];
        if (cnt1.toString() === cnt2.toString()) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

### 方法二：滑动窗口优化

在方法一中，我们每次加入和移除一个字符时，都需要比较两个哈希表或数组，时间复杂度较高。我们可以维护一个变量 $diff$，表示两个大小为 $m$ 的字符串中，有多少种字符出现的个数不同。当 $diff=0$ 时，说明两个字符串中的字符个数相同。

时间复杂度 $O(m + n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $m$ 和 $n$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        m, n = len(s1), len(s2)
        if m > n:
            return False
        cnt = Counter()
        for a, b in zip(s1, s2):
            cnt[a] -= 1
            cnt[b] += 1
        diff = sum(x != 0 for x in cnt.values())
        if diff == 0:
            return True
        for i in range(m, n):
            a, b = s2[i - m], s2[i]
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
                return True
        return False
```

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            int a = s2.charAt(i - m) - 'a';
            int b = s2.charAt(i) - 'a';
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
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < m; ++i) {
            --cnt[s1[i] - 'a'];
            ++cnt[s2[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            int a = s2[i - m] - 'a';
            int b = s2[i] - 'a';
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
                return true;
            }
        }
        return false;
    }
};
```

```go
func checkInclusion(s1 string, s2 string) bool {
	m, n := len(s1), len(s2)
	if m > n {
		return false
	}
	cnt := [26]int{}
	for i := 0; i < m; i++ {
		cnt[s1[i]-'a']--
		cnt[s2[i]-'a']++
	}
	diff := 0
	for _, x := range cnt {
		if x != 0 {
			diff++
		}
	}
	if diff == 0 {
		return true
	}
	for i := m; i < n; i++ {
		a, b := s2[i-m]-'a', s2[i]-'a'
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
			return true
		}
	}
	return false
}
```

```ts
function checkInclusion(s1: string, s2: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m > n) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        --cnt[s1[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let diff = 0;
    for (const x of cnt) {
        if (x !== 0) {
            ++diff;
        }
    }
    if (diff === 0) {
        return true;
    }
    for (let i = m; i < n; ++i) {
        const a = s2[i - m].charCodeAt(0) - 'a'.charCodeAt(0);
        const b = s2[i].charCodeAt(0) - 'a'.charCodeAt(0);
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
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
