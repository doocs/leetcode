# [395. 至少有 K 个重复字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters)

[English Version](/solution/0300-0399/0395.Longest%20Substring%20with%20At%20Least%20K%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出 <code>s</code> 中的最长子串， 要求该子串中的每一字符出现次数都不少于 <code>k</code> 。返回这一子串的长度。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabb", k = 3
<strong>输出：</strong>3
<strong>解释：</strong>最长子串为 "aaa" ，其中 'a' 重复了 3 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ababbc", k = 2
<strong>输出：</strong>5
<strong>解释：</strong>最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分治**

对于字符串 $s$，如果存在某个字符 $c$，其出现次数小于 $k$，则任何包含 $c$ 的子串都不可能满足题目要求。因此我们可以将 $s$ 按照每个不满足条件的字符 $c$ 进行分割，分割得到的每个子串都是原字符串的一个「子问题」，我们可以递归地求解每个子问题，最终的答案即为所有子问题的最大值。

时间复杂度 $O(n \times C)$，空间复杂度 $O(C^2)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集的大小。本题中 $C \leq 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubstring(self, s: str, k: int) -> int:
        def dfs(l, r):
            cnt = Counter(s[l: r + 1])
            split = next((c for c, v in cnt.items() if v < k), '')
            if not split:
                return r - l + 1
            i = l
            ans = 0
            while i <= r:
                while i <= r and s[i] == split:
                    i += 1
                if i >= r:
                    break
                j = i
                while j <= r and s[j] != split:
                    j += 1
                t = dfs(i, j - 1)
                ans = max(ans, t)
                i = j
            return ans

        return dfs(0, len(s) - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String s;
    private int k;

    public int longestSubstring(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, s.length() - 1);
    }

    private int dfs(int l, int r) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        char split = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }
        int i = l;
        int ans = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                ++i;
            }
            if (i > r) {
                break;
            }
            int j = i;
            while (j <= r && s.charAt(j) != split) {
                ++j;
            }
            int t = dfs(i, j - 1);
            ans = Math.max(ans, t);
            i = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubstring(string s, int k) {
        function<int(int, int)> dfs = [&](int l, int r) -> int {
            int cnt[26] = {0};
            for (int i = l; i <= r; ++i) {
                cnt[s[i] - 'a']++;
            }
            char split = 0;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0 && cnt[i] < k) {
                    split = 'a' + i;
                    break;
                }
            }
            if (split == 0) {
                return r - l + 1;
            }
            int i = l;
            int ans = 0;
            while (i <= r) {
                while (i <= r && s[i] == split) {
                    ++i;
                }
                if (i >= r) {
                    break;
                }
                int j = i;
                while (j <= r && s[j] != split) {
                    ++j;
                }
                int t = dfs(i, j - 1);
                ans = max(ans, t);
                i = j;
            }
            return ans;
        };
        return dfs(0, s.size() - 1);
    }
};
```

### **Go**

```go
func longestSubstring(s string, k int) int {
	var dfs func(l, r int) int
	dfs = func(l, r int) int {
		cnt := [26]int{}
		for i := l; i <= r; i++ {
			cnt[s[i]-'a']++
		}
		var split byte
		for i, v := range cnt {
			if v > 0 && v < k {
				split = byte(i + 'a')
				break
			}
		}
		if split == 0 {
			return r - l + 1
		}
		i := l
		ans := 0
		for i <= r {
			for i <= r && s[i] == split {
				i++
			}
			if i > r {
				break
			}
			j := i
			for j <= r && s[j] != split {
				j++
			}
			t := dfs(i, j-1)
			ans = max(ans, t)
			i = j
		}
		return ans
	}
	return dfs(0, len(s)-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
