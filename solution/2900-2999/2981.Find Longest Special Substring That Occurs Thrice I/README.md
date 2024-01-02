# [2981. 找出出现至少三次的最长特殊子字符串 I](https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-i)

[English Version](/solution/2900-2999/2981.Find%20Longest%20Special%20Substring%20That%20Occurs%20Thrice%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code> 。</p>

<p>如果一个字符串仅由单一字符组成，那么它被称为 <strong>特殊 </strong>字符串。例如，字符串 <code>"abc"</code> 不是特殊字符串，而字符串 <code>"ddd"</code>、<code>"zz"</code> 和 <code>"f"</code> 是特殊字符串。</p>

<p>返回在 <code>s</code> 中出现 <strong>至少三次 </strong>的<strong> 最长特殊子字符串 </strong>的长度，如果不存在出现至少三次的特殊子字符串，则返回 <code>-1</code> 。</p>

<p><strong>子字符串 </strong>是字符串中的一个连续<strong> 非空 </strong>字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaa"
<strong>输出：</strong>2
<strong>解释：</strong>出现三次的最长特殊子字符串是 "aa" ：子字符串 "<em><strong>aa</strong></em>aa"、"a<em><strong>aa</strong></em>a" 和 "aa<em><strong>aa</strong></em>"。
可以证明最大长度是 2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcdef"
<strong>输出：</strong>-1
<strong>解释：</strong>不存在出现至少三次的特殊子字符串。因此返回 -1 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcaba"
<strong>输出：</strong>1
<strong>解释：</strong>出现三次的最长特殊子字符串是 "a" ：子字符串 "<em><strong>a</strong></em>bcaba"、"abc<em><strong>a</strong></em>ba" 和 "abcab<em><strong>a</strong></em>"。
可以证明最大长度是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 50</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找 + 滑动窗口计数**

我们注意到，如果一个长度为 $x$ 且出现至少三次的特殊子字符串存在，那么长度为 $x-1$ 的特殊子字符串也一定存在，这存在着单调性，因此我们可以使用二分查找的方法来找到最长的特殊子字符串。

我们定义二分查找的左边界 $l = 0$，右边界 $r = n$，其中 $n$ 是字符串的长度。每次二分查找的过程中，我们取 $mid = \lfloor \frac{l + r + 1}{2} \rfloor$，如果长度为 $mid$ 的特殊子字符串存在，那么我们就将左边界更新为 $mid$，否则我们就将右边界更新为 $mid - 1$。在二分查找的过程中，我们使用滑动窗口来计算特殊子字符串的个数。

具体地，我们设计一个函数 $check(x)$，表示长度为 $x$ 且出现至少三次的特殊子字符串是否存在。

在函数 $check(x)$ 中，我们定义一个哈希表或长度为 $26$ 的数组 $cnt$，其中 $cnt[i]$ 表示长度为 $x$，且由第 $i$ 个小写字母组成的特殊子字符串的个数。我们遍历字符串 $s$，如果当前遍历到的字符为 $s[i]$，那么我们将指针 $j$ 向右移动，直到 $s[j] \neq s[i]$，此时 $s[i \cdots j-1]$ 就是一个长度为 $x$ 的特殊子字符串，我们将 $cnt[s[i]]$ 增加 $\max(0, j - i - x + 1)$，然后将指针 $i$ 更新为 $j$。

在遍历结束之后，我们遍历数组 $cnt$，如果存在 $cnt[i] \geq 3$，那么就说明长度为 $x$ 且出现至少三次的特殊子字符串存在，我们返回 $true$，否则返回 $false$。

时间复杂度 $O((n + |\Sigma|) \times \log n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 $s$ 的长度，而 $|\Sigma|$ 表示字符集的大小，本题中字符集为小写英文字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumLength(self, s: str) -> int:
        def check(x: int) -> bool:
            cnt = defaultdict(int)
            i = 0
            while i < n:
                j = i + 1
                while j < n and s[j] == s[i]:
                    j += 1
                cnt[s[i]] += max(0, j - i - x + 1)
                i = j
            return max(cnt.values()) >= 3

        n = len(s)
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return -1 if l == 0 else l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String s;
    private int n;

    public int maximumLength(String s) {
        this.s = s;
        n = s.length();
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == 0 ? -1 : l;
    }

    private boolean check(int x) {
        int[] cnt = new int[26];
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int k = s.charAt(i) - 'a';
            cnt[k] += Math.max(0, j - i - x + 1);
            if (cnt[k] >= 3) {
                return true;
            }
            i = j;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumLength(string s) {
        int n = s.size();
        int l = 0, r = n;
        auto check = [&](int x) {
            int cnt[26]{};
            for (int i = 0; i < n;) {
                int j = i + 1;
                while (j < n && s[j] == s[i]) {
                    ++j;
                }
                int k = s[i] - 'a';
                cnt[k] += max(0, j - i - x + 1);
                if (cnt[k] >= 3) {
                    return true;
                }
                i = j;
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == 0 ? -1 : l;
    }
};
```

### **Go**

```go
func maximumLength(s string) int {
	n := len(s)
	l, r := 0, n
	check := func(x int) bool {
		cnt := [26]int{}
		for i := 0; i < n; {
			j := i + 1
			for j < n && s[j] == s[i] {
				j++
			}
			k := s[i] - 'a'
			cnt[k] += max(0, j-i-x+1)
			if cnt[k] >= 3 {
				return true
			}
			i = j
		}
		return false
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	if l == 0 {
		return -1
	}
	return l
}
```

### **TypeScript**

```ts
function maximumLength(s: string): number {
    const n = s.length;
    let [l, r] = [0, n];
    const check = (x: number): boolean => {
        const cnt: number[] = Array(26).fill(0);
        for (let i = 0; i < n; ) {
            let j = i + 1;
            while (j < n && s[j] === s[i]) {
                j++;
            }
            const k = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
            cnt[k] += Math.max(0, j - i - x + 1);
            if (cnt[k] >= 3) {
                return true;
            }
            i = j;
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l === 0 ? -1 : l;
}
```

### **...**

```

```

<!-- tabs:end -->
