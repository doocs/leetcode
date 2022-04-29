# [1898. 可移除字符的最大数目](https://leetcode.cn/problems/maximum-number-of-removable-characters)

[English Version](/solution/1800-1899/1898.Maximum%20Number%20of%20Removable%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找 + 判断子序列**

二分枚举整数 k，找到满足要求的最大 k 即可。

以下是二分查找的两个通用模板：

模板 1：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

模板 2：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

做二分题目时，可以按照以下步骤：

1. 写出循环条件：`while (left < right)`，注意是 `left < right`，而非 `left <= right`；
1. 循环体内，先无脑写出 `mid = (left + right) >> 1`；
1. 根据具体题目，实现 `check()` 函数（有时很简单的逻辑，可以不定义 `check`），想一下究竟要用 `right = mid`（模板 1） 还是 `left = mid`（模板 2）；
    - 如果 `right = mid`，那么无脑写出 else 语句 `left = mid + 1`，并且不需要更改 mid 的计算，即保持 `mid = (left + right) >> 1`；
    - 如果 `left = mid`，那么无脑写出 else 语句 `right = mid - 1`，并且在 mid 计算时补充 +1，即 `mid = (left + right + 1) >> 1`。
1. 循环结束时，left 与 right 相等。

注意，这两个模板的优点是始终保持答案位于二分区间内，二分结束条件对应的值恰好在答案所处的位置。 对于可能无解的情况，只要判断二分结束后的 left 或者 right 是否满足题意即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def check(k):
            i = j = 0
            ids = set(removable[:k])
            while i < m and j < n:
                if i not in ids and s[i] == p[j]:
                    j += 1
                i += 1
            return j == n

        m, n = len(s), len(p)
        left, right = 0, len(removable)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int left = 0, right = removable.length;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(String s, String p, int[] removable, int mid) {
        int m = s.length(), n = p.length(), i = 0, j = 0;
        Set<Integer> ids = new HashSet<>();
        for (int k = 0; k < mid; ++k) {
            ids.add(removable[k]);
        }
        while (i < m && j < n) {
            if (!ids.contains(i) && s.charAt(i) == p.charAt(j)) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
}
```

### **TypeScript**

```ts
function maximumRemovals(s: string, p: string, removable: number[]): number {
    let left = 0,
        right = removable.length;
    while (left < right) {
        let mid = (left + right + 1) >> 1;
        if (isSub(s, p, new Set(removable.slice(0, mid)))) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}

function isSub(str: string, sub: string, idxes: Set<number>): boolean {
    let m = str.length,
        n = sub.length;
    let i = 0,
        j = 0;
    while (i < m && j < n) {
        if (!idxes.has(i) && str.charAt(i) == sub.charAt(j)) {
            ++j;
        }
        ++i;
    }
    return j == n;
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumRemovals(string s, string p, vector<int>& removable) {
        int left = 0, right = removable.size();
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    bool check(string s, string p, vector<int>& removable, int mid) {
        int m = s.size(), n = p.size(), i = 0, j = 0;
        unordered_set<int> ids;
        for (int k = 0; k < mid; ++k) {
            ids.insert(removable[k]);
        }
        while (i < m && j < n) {
            if (ids.count(i) == 0 && s[i] == p[j]) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
};
```

### **Go**

```go
func maximumRemovals(s string, p string, removable []int) int {
	check := func(k int) bool {
		ids := make(map[int]bool)
		for _, r := range removable[:k] {
			ids[r] = true
		}
		var i, j int
		for i < len(s) && j < len(p) {
			if !ids[i] && s[i] == p[j] {
				j++
			}
			i++
		}
		return j == len(p)
	}

	left, right := 0, len(removable)
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    pub fn maximum_removals(s: String, p: String, removable: Vec<i32>) -> i32 {
        let m = s.len();
        let n = p.len();
        let s = s.as_bytes();
        let p = p.as_bytes();

        let check = |k| {
            let mut i = 0;
            let mut j = 0;
            let ids: HashSet<i32> = removable[..k].iter().cloned().collect();
            while i < m && j < n {
                if !ids.contains(&(i as i32)) && s[i] == p[j] {
                    j += 1;
                }
                i += 1;
            }
            j == n
        };

        let mut left = 0;
        let mut right = removable.len();
        while left + 1 < right {
            let mid = left + (right - left) / 2;
            if check(mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if check(right) {
            return right as i32;
        }
        left as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
