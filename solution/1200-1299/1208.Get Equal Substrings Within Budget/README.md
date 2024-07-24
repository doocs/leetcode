---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README.md
rating: 1496
source: 第 156 场周赛 Q2
tags:
    - 字符串
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [1208. 尽可能使字符串相等](https://leetcode.cn/problems/get-equal-substrings-within-budget)

[English Version](/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的字符串，<code>s</code> 和 <code>t</code>。</p>

<p>将 <code>s</code> 中的第 <code>i</code> 个字符变到 <code>t</code> 中的第 <code>i</code> 个字符需要 <code>|s[i] - t[i]|</code> 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。</p>

<p>用于变更字符串的最大预算是 <code>maxCost</code>。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。</p>

<p>如果你可以将 <code>s</code> 的子字符串转化为它在 <code>t</code> 中对应的子字符串，则返回可以转化的最大长度。</p>

<p>如果 <code>s</code> 中没有子字符串可以转化成 <code>t</code> 中对应的子字符串，则返回 <code>0</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "bcdf", maxCost = 3
<strong>输出：</strong>3
<strong>解释：</strong>s<strong> </strong>中的<strong> </strong>"abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "cdef", maxCost = 3
<strong>输出：</strong>1
<strong>解释：</strong>s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为<code> 1。</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "acde", maxCost = 0
<strong>输出：</strong>1
<strong>解释：</strong>a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10^5</code></li>
	<li><code>0 <= maxCost <= 10^6</code></li>
	<li><code>s</code> 和 <code>t</code> 都只含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 二分查找

我们可以创建一个长度为 $n + 1$ 的数组 $f$，其中 $f[i]$ 表示字符串 $s$ 的前 $i$ 个字符与字符串 $t$ 的前 $i$ 个字符的 ASCII 码值的差的绝对值之和。这样，我们就可以通过 $f[j + 1] - f[i]$ 来计算字符串 $s$ 的第 $i$ 个字符到第 $j$ 个字符的 ASCII 码值的差的绝对值之和，其中 $0 \leq i \leq j < n$。

注意到长度具有单调性，即如果存在长度为 $x$ 的子串满足条件，那么长度为 $x - 1$ 的子串也一定满足条件。因此，我们可以使用二分查找的方法来求解最大长度。

我们定义函数 $check(x)$，表示是否存在长度为 $x$ 的子串满足条件。在该函数中，我们只需要枚举所有长度为 $x$ 的子串，判断其是否满足条件即可。如果存在满足条件的子串，那么函数返回 `true`，否则返回 `false`。

接下来，我们定义二分查找的左边界 $l$ 为 $0$，右边界 $r$ 为 $n$。在每一步中，我们令 $mid = \lfloor \frac{l + r + 1}{2} \rfloor$，如果函数 $check(mid)$ 的返回值为 `true`，那么我们将左边界更新为 $mid$，否则我们将右边界更新为 $mid - 1$。在二分查找结束后，我们得到的左边界即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        def check(x):
            for i in range(n):
                j = i + mid - 1
                if j < n and f[j + 1] - f[i] <= maxCost:
                    return True
            return False

        n = len(s)
        f = list(accumulate((abs(ord(a) - ord(b)) for a, b in zip(s, t)), initial=0))
        l, r = 0, n
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
    private int maxCost;
    private int[] f;
    private int n;

    public int equalSubstring(String s, String t, int maxCost) {
        n = s.length();
        f = new int[n + 1];
        this.maxCost = maxCost;
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(s.charAt(i) - t.charAt(i));
            f[i + 1] = f[i] + x;
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int x) {
        for (int i = 0; i + x - 1 < n; ++i) {
            int j = i + x - 1;
            if (f[j + 1] - f[i] <= maxCost) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            f[i + 1] = f[i] + abs(s[i] - t[i]);
        }
        auto check = [&](int x) -> bool {
            for (int i = 0; i + x - 1 < n; ++i) {
                int j = i + x - 1;
                if (f[j + 1] - f[i] <= maxCost) {
                    return true;
                }
            }
            return false;
        };
        int l = 0, r = n;
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
func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	f := make([]int, n+1)
	for i, a := range s {
		f[i+1] = f[i] + abs(int(a)-int(t[i]))
	}
	check := func(x int) bool {
		for i := 0; i+x-1 < n; i++ {
			if f[i+x]-f[i] <= maxCost {
				return true
			}
		}
		return false
	}
	l, r := 0, n
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

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function equalSubstring(s: string, t: string, maxCost: number): number {
    const n = s.length;
    const f = Array(n + 1).fill(0);

    for (let i = 0; i < n; i++) {
        f[i + 1] = f[i] + Math.abs(s.charCodeAt(i) - t.charCodeAt(i));
    }

    const check = (x: number): boolean => {
        for (let i = 0; i + x - 1 < n; i++) {
            if (f[i + x] - f[i] <= maxCost) {
                return true;
            }
        }
        return false;
    };

    let l = 0,
        r = n;
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针

我们可以维护两个指针 $l$ 和 $r$，初始时 $l = r = 0$；维护一个变量 $\textit{cost}$，表示下标区间 $[l,..r]$ 之间的 ASCII 码值的差的绝对值之和。在每一步中，我们将 $r$ 向右移动一位，然后更新 $\textit{cost} = \textit{cost} + |s[r] - t[r]|$。如果 $\textit{cost} \gt \textit{maxCost}$，那么我们就循环将 $l$ 向右移动一位，并且减少 $\textit{cost}$ 的值，直到 $\textit{cost} \leq \textit{maxCost}$。然后我们更新答案，即 $\textit{ans} = \max(\textit{ans}, r - l + 1)$。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        ans = cost = l = 0
        for r in range(n):
            cost += abs(ord(s[r]) - ord(t[r]))
            while cost > maxCost:
                cost -= abs(ord(s[l]) - ord(t[l]))
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int ans = 0, cost = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));
            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(l) - t.charAt(l));
                ++l;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.length();
        int ans = 0, cost = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            cost += abs(s[r] - t[r]);
            while (cost > maxCost) {
                cost -= abs(s[l] - t[l]);
                ++l;
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func equalSubstring(s string, t string, maxCost int) (ans int) {
	var cost, l int
	for r := range s {
		cost += abs(int(s[r]) - int(t[r]))
		for ; cost > maxCost; l++ {
			cost -= abs(int(s[l]) - int(t[l]))
		}
		ans = max(ans, r-l+1)
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

#### TypeScript

```ts
function equalSubstring(s: string, t: string, maxCost: number): number {
    const getCost = (i: number) => Math.abs(s[i].charCodeAt(0) - t[i].charCodeAt(0));
    const n = s.length;
    let ans = 0,
        cost = 0;
    for (let l = 0, r = 0; r < n; ++r) {
        cost += getCost(r);
        while (cost > maxCost) {
            cost -= getCost(l++);
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：双指针的另一种写法

在方法二中，双指针维护的区间可能变短，也可能变长，由于题目只需要求出最大长度，我们可以维护一个单调变长的区间。

具体地，我们用两个指针 $l$ 和 $r$ 指向区间的左右端点，初始时 $l = r = 0$。在每一步中，我们将 $r$ 向右移动一位，然后更新 $\textit{cost} = \textit{cost} + |s[r] - t[r]|$。如果 $\textit{cost} \gt \textit{maxCost}$，那么我们就将 $l$ 向右移动一位，并且减少 $\textit{cost}$ 的值。

最后返回 $n - l$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        cost = l = 0
        for a, b in zip(s, t):
            cost += abs(ord(a) - ord(b))
            if cost > maxCost:
                cost -= abs(ord(s[l]) - ord(t[l]))
                l += 1
        return len(s) - l
```

#### Java

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int cost = 0, l = 0;
        for (int r = 0; r < n; ++r) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));
            if (cost > maxCost) {
                cost -= Math.abs(s.charAt(l) - t.charAt(l));
                ++l;
            }
        }
        return n - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.length();
        int cost = 0, l = 0;
        for (int r = 0; r < n; ++r) {
            cost += abs(s[r] - t[r]);
            if (cost > maxCost) {
                cost -= abs(s[l] - t[l]);
                ++l;
            }
        }
        return n - l;
    }
};
```

#### Go

```go
func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	var cost, l int
	for r := range s {
		cost += abs(int(s[r]) - int(t[r]))
		if cost > maxCost {
			cost -= abs(int(s[l]) - int(t[l]))
			l++
		}
	}
	return n - l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function equalSubstring(s: string, t: string, maxCost: number): number {
    const getCost = (i: number) => Math.abs(s[i].charCodeAt(0) - t[i].charCodeAt(0));
    const n = s.length;
    let cost = 0;
    let l = 0;
    for (let r = 0; r < n; ++r) {
        cost += getCost(r);
        if (cost > maxCost) {
            cost -= getCost(l++);
        }
    }
    return n - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
