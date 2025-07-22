---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1898.Maximum%20Number%20of%20Removable%20Characters/README.md
rating: 1912
source: 第 245 场周赛 Q2
tags:
    - 数组
    - 双指针
    - 字符串
    - 二分查找
---

<!-- problem:start -->

# [1898. 可移除字符的最大数目](https://leetcode.cn/problems/maximum-number-of-removable-characters)

[English Version](/solution/1800-1899/1898.Maximum%20Number%20of%20Removable%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>p</code> ，其中 <code>p</code> 是 <code>s</code> 的一个 <strong>子序列</strong> 。同时，给你一个元素 <strong>互不相同</strong> 且下标 <strong>从 0 开始</strong> 计数的整数数组 <code>removable</code> ，该数组是 <code>s</code> 中下标的一个子集（<code>s</code> 的下标也 <strong>从 0 开始</strong> 计数）。</p>

<p>请你找出一个整数 <code>k</code>（<code>0 <= k <= removable.length</code>），选出 <code>removable</code> 中的 <strong>前</strong> <code>k</code> 个下标，然后从 <code>s</code> 中移除这些下标对应的 <code>k</code> 个字符。整数 <code>k</code> 需满足：在执行完上述步骤后， <code>p</code> 仍然是 <code>s</code> 的一个 <strong>子序列</strong> 。更正式的解释是，对于每个 <code>0 <= i < k</code> ，先标记出位于 <code>s[removable[i]]</code> 的字符，接着移除所有标记过的字符，然后检查 <code>p</code> 是否仍然是 <code>s</code> 的一个子序列。</p>

<p>返回你可以找出的 <strong>最大</strong><em> </em><code>k</code><em> </em>，满足在移除字符后<em> </em><code>p</code><em> </em>仍然是 <code>s</code> 的一个子序列。</p>

<p>字符串的一个 <strong>子序列</strong> 是一个由原字符串生成的新字符串，生成过程中可能会移除原字符串中的一些字符（也可能不移除）但不改变剩余字符之间的相对顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcacb", p = "ab", removable = [3,1,0]
<strong>输出：</strong>2
<strong>解释：</strong>在移除下标 3 和 1 对应的字符后，"a<strong>b</strong>c<strong>a</strong>cb" 变成 "accb" 。
"ab" 是 "<strong>a</strong>cc<strong>b</strong>" 的一个子序列。
如果移除下标 3、1 和 0 对应的字符后，"<strong>ab</strong>c<strong>a</strong>cb" 变成 "ccb" ，那么 "ab" 就不再是 s 的一个子序列。
因此，最大的 k 是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
<strong>输出：</strong>1
<strong>解释：</strong>在移除下标 3 对应的字符后，"abc<strong>b</strong>ddddd" 变成 "abcddddd" 。
"abcd" 是 "<strong>abcd</strong>dddd" 的一个子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcab", p = "abc", removable = [0,1,2,3,4]
<strong>输出：</strong>0
<strong>解释：</strong>如果移除数组 removable 的第一个下标，"abc" 就不再是 s 的一个子序列。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= p.length <= s.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= removable.length < s.length</code></li>
	<li><code>0 <= removable[i] < s.length</code></li>
	<li><code>p</code> 是 <code>s</code> 的一个 <strong>子字符串</strong></li>
	<li><code>s</code> 和 <code>p</code> 都由小写英文字母组成</li>
	<li><code>removable</code> 中的元素 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果移除 $\textit{removable}$ 前 $k$ 个下标对应的字符后，满足 $p$ 仍然是 $s$ 的一个子序列，那么移除 $k \lt k' \leq \textit{removable.length}$ 个下标对应的字符后，依然满足条件，这存在着单调性。因此，我们可以使用二分查找，找到最大的 $k$。

我们定义二分查找的左边界 $l = 0$，右边界 $r = \textit{removable.length}$，然后进行二分查找。在每次查找中，我们取中间值 $mid = \left\lfloor \frac{l + r + 1}{2} \right\rfloor$，然后检查移除 $\textit{removable}$ 的前 $mid$ 个下标对应的字符后，是否满足 $p$ 仍然是 $s$ 的一个子序列。如果满足，我们更新左边界 $l = mid$，否则更新右边界 $r = mid - 1$。

二分查找结束后，返回左边界 $l$ 即可。

时间复杂度 $O(k \times \log k)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度，而 $k$ 是 $\textit{removable}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def check(k: int) -> bool:
            rem = [False] * len(s)
            for i in removable[:k]:
                rem[i] = True
            i = j = 0
            while i < len(s) and j < len(p):
                if not rem[i] and p[j] == s[i]:
                    j += 1
                i += 1
            return j == len(p)

        l, r = 0, len(removable)
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    private char[] s;
    private char[] p;
    private int[] removable;

    public int maximumRemovals(String s, String p, int[] removable) {
        int l = 0, r = removable.length;
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        this.removable = removable;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int k) {
        boolean[] rem = new boolean[s.length];
        for (int i = 0; i < k; ++i) {
            rem[removable[i]] = true;
        }
        int i = 0, j = 0;
        while (i < s.length && j < p.length) {
            if (!rem[i] && p[j] == s[i]) {
                ++j;
            }
            ++i;
        }
        return j == p.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumRemovals(string s, string p, vector<int>& removable) {
        int m = s.size(), n = p.size();
        int l = 0, r = removable.size();
        bool rem[m];

        auto check = [&](int k) {
            memset(rem, false, sizeof(rem));
            for (int i = 0; i < k; i++) {
                rem[removable[i]] = true;
            }
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (!rem[i] && s[i] == p[j]) {
                    ++j;
                }
                ++i;
            }
            return j == n;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maximumRemovals(s string, p string, removable []int) int {
	m, n := len(s), len(p)
	l, r := 0, len(removable)
	check := func(k int) bool {
		rem := make([]bool, m)
		for i := 0; i < k; i++ {
			rem[removable[i]] = true
		}
		i, j := 0, 0
		for i < m && j < n {
			if !rem[i] && s[i] == p[j] {
				j++
			}
			i++
		}
		return j == n
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

#### TypeScript

```ts
function maximumRemovals(s: string, p: string, removable: number[]): number {
    const [m, n] = [s.length, p.length];
    let [l, r] = [0, removable.length];
    const rem: boolean[] = Array(m);

    const check = (k: number): boolean => {
        rem.fill(false);
        for (let i = 0; i < k; i++) {
            rem[removable[i]] = true;
        }

        let i = 0,
            j = 0;
        while (i < m && j < n) {
            if (!rem[i] && s[i] === p[j]) {
                j++;
            }
            i++;
        }
        return j === n;
    };

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_removals(s: String, p: String, removable: Vec<i32>) -> i32 {
        let m = s.len();
        let n = p.len();
        let s: Vec<char> = s.chars().collect();
        let p: Vec<char> = p.chars().collect();
        let mut l = 0;
        let mut r = removable.len();

        let check = |k: usize| -> bool {
            let mut rem = vec![false; m];
            for i in 0..k {
                rem[removable[i] as usize] = true;
            }
            let mut i = 0;
            let mut j = 0;
            while i < m && j < n {
                if !rem[i] && s[i] == p[j] {
                    j += 1;
                }
                i += 1;
            }
            j == n
        };

        while l < r {
            let mid = (l + r + 1) / 2;
            if check(mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} p
 * @param {number[]} removable
 * @return {number}
 */
var maximumRemovals = function (s, p, removable) {
    const [m, n] = [s.length, p.length];
    let [l, r] = [0, removable.length];
    const rem = Array(m);

    const check = k => {
        rem.fill(false);
        for (let i = 0; i < k; i++) {
            rem[removable[i]] = true;
        }

        let i = 0,
            j = 0;
        while (i < m && j < n) {
            if (!rem[i] && s[i] === p[j]) {
                j++;
            }
            i++;
        }
        return j === n;
    };

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
};
```

#### Kotlin

```kotlin
class Solution {
    fun maximumRemovals(s: String, p: String, removable: IntArray): Int {
        val m = s.length
        val n = p.length
        var l = 0
        var r = removable.size

        fun check(k: Int): Boolean {
            val rem = BooleanArray(m)
            for (i in 0 until k) {
                rem[removable[i]] = true
            }
            var i = 0
            var j = 0
            while (i < m && j < n) {
                if (!rem[i] && s[i] == p[j]) {
                    j++
                }
                i++
            }
            return j == n
        }

        while (l < r) {
            val mid = (l + r + 1) / 2
            if (check(mid)) {
                l = mid
            } else {
                r = mid - 1
            }
        }

        return l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
