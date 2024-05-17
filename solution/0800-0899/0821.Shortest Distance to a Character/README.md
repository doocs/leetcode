---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0821.Shortest%20Distance%20to%20a%20Character/README.md
tags:
    - 数组
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [821. 字符的最短距离](https://leetcode.cn/problems/shortest-distance-to-a-character)

[English Version](/solution/0800-0899/0821.Shortest%20Distance%20to%20a%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个字符 <code>c</code> ，且 <code>c</code> 是 <code>s</code> 中出现过的字符。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer.length == s.length</code> 且 <code>answer[i]</code> 是 <code>s</code> 中从下标 <code>i</code> 到离它 <strong>最近</strong> 的字符 <code>c</code> 的 <strong>距离</strong> 。</p>

<p>两个下标&nbsp;<code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>abs(i - j)</code> ，其中 <code>abs</code> 是绝对值函数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "loveleetcode", c = "e"
<strong>输出：</strong>[3,2,1,0,1,0,0,1,2,2,1,0]
<strong>解释：</strong>字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaab", c = "b"
<strong>输出：</strong>[3,2,1,0]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 和 <code>c</code> 均为小写英文字母</li>
	<li>题目数据保证 <code>c</code> 在 <code>s</code> 中至少出现一次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次遍历

我们先创建一个长度为 $n$ 的答案数组 $ans$。

接下来，我们从左到右遍历字符串 $s$，记录最近出现的字符 $c$ 的位置 $pre$，那么对于位置 $i$，答案就是 $i - pre$，即 $ans[i] = i - pre$。

然后，我们从右到左遍历字符串 $s$，记录最近出现的字符 $c$ 的位置 $suf$，那么对于位置 $i$，答案就是 $suf - i$，即 $ans[i] = \min(ans[i], suf - i)$。

最后返回答案数组 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        n = len(s)
        ans = [n] * n
        pre = -inf
        for i, ch in enumerate(s):
            if ch == c:
                pre = i
            ans[i] = min(ans[i], i - pre)
        suf = inf
        for i in range(n - 1, -1, -1):
            if s[i] == c:
                suf = i
            ans[i] = min(ans[i], suf - i)
        return ans
```

```java
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(ans, inf);
        for (int i = 0, pre = -inf; i < n; ++i) {
            if (s.charAt(i) == c) {
                pre = i;
            }
            ans[i] = Math.min(ans[i], i - pre);
        }
        for (int i = n - 1, suf = inf; i >= 0; --i) {
            if (s.charAt(i) == c) {
                suf = i;
            }
            ans[i] = Math.min(ans[i], suf - i);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        const int inf = 1 << 30;
        vector<int> ans(n, inf);
        for (int i = 0, pre = -inf; i < n; ++i) {
            if (s[i] == c) {
                pre = i;
            }
            ans[i] = min(ans[i], i - pre);
        }
        for (int i = n - 1, suf = inf; ~i; --i) {
            if (s[i] == c) {
                suf = i;
            }
            ans[i] = min(ans[i], suf - i);
        }
        return ans;
    }
};
```

```go
func shortestToChar(s string, c byte) []int {
	n := len(s)
	ans := make([]int, n)
	const inf int = 1 << 30
	pre := -inf
	for i := range s {
		if s[i] == c {
			pre = i
		}
		ans[i] = i - pre
	}
	suf := inf
	for i := n - 1; i >= 0; i-- {
		if s[i] == c {
			suf = i
		}
		ans[i] = min(ans[i], suf-i)
	}
	return ans
}
```

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    const inf = 1 << 30;
    const ans: number[] = new Array(n).fill(inf);
    for (let i = 0, pre = -inf; i < n; ++i) {
        if (s[i] === c) {
            pre = i;
        }
        ans[i] = i - pre;
    }
    for (let i = n - 1, suf = inf; i >= 0; --i) {
        if (s[i] === c) {
            suf = i;
        }
        ans[i] = Math.min(ans[i], suf - i);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn shortest_to_char(s: String, c: char) -> Vec<i32> {
        let c = c as u8;
        let s = s.as_bytes();
        let n = s.len();
        let mut res = vec![i32::MAX; n];
        let mut pre = i32::MAX;
        for i in 0..n {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = i32::abs((i as i32) - pre);
        }
        pre = i32::MAX;
        for i in (0..n).rev() {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = res[i].min(i32::abs((i as i32) - pre));
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
