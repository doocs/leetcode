---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0670.Maximum%20Swap/README.md
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [670. 最大交换](https://leetcode.cn/problems/maximum-swap)

[English Version](/solution/0600-0699/0670.Maximum%20Swap/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非负整数，你<strong>至多</strong>可以交换一次数字中的任意两位。返回你能得到的最大值。</p>

<p><strong>示例 1 :</strong></p>

<pre>
<strong>输入:</strong> 2736
<strong>输出:</strong> 7236
<strong>解释:</strong> 交换数字2和数字7。
</pre>

<p><strong>示例 2 :</strong></p>

<pre>
<strong>输入:</strong> 9973
<strong>输出:</strong> 9973
<strong>解释:</strong> 不需要交换。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数字的范围是&nbsp;[0, 10<sup>8</sup>]</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们先将数字转为字符串 $s$，然后从右往左遍历字符串 $s$，用数组或哈希表 $d$ 记录每个数字右侧的最大数字的位置（可以是数字本身的位置）。

接着从左到右遍历 $d$，如果 $s[i] \lt s[d[i]]$，则进行交换，并退出遍历的过程。

最后将字符串 $s$ 转为数字，即为答案。

时间复杂度 $O(\log M)$，空间复杂度 $O(\log M)$。其中 $M$ 是数字 $num$ 的取值范围。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        s = list(str(num))
        n = len(s)
        d = list(range(n))
        for i in range(n - 2, -1, -1):
            if s[i] <= s[d[i + 1]]:
                d[i] = d[i + 1]
        for i, j in enumerate(d):
            if s[i] < s[j]:
                s[i], s[j] = s[j], s[i]
                break
        return int(''.join(s))
```

#### Java

```java
class Solution {
    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = i;
        }
        for (int i = n - 2; i >= 0; --i) {
            if (s[i] <= s[d[i + 1]]) {
                d[i] = d[i + 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            int j = d[i];
            if (s[i] < s[j]) {
                char t = s[i];
                s[i] = s[j];
                s[j] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumSwap(int num) {
        string s = to_string(num);
        int n = s.size();
        vector<int> d(n);
        iota(d.begin(), d.end(), 0);
        for (int i = n - 2; ~i; --i) {
            if (s[i] <= s[d[i + 1]]) {
                d[i] = d[i + 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            int j = d[i];
            if (s[i] < s[j]) {
                swap(s[i], s[j]);
                break;
            }
        }
        return stoi(s);
    }
};
```

#### Go

```go
func maximumSwap(num int) int {
	s := []byte(strconv.Itoa(num))
	n := len(s)
	d := make([]int, n)
	for i := range d {
		d[i] = i
	}
	for i := n - 2; i >= 0; i-- {
		if s[i] <= s[d[i+1]] {
			d[i] = d[i+1]
		}
	}
	for i, j := range d {
		if s[i] < s[j] {
			s[i], s[j] = s[j], s[i]
			break
		}
	}
	ans, _ := strconv.Atoi(string(s))
	return ans
}
```

#### TypeScript

```ts
function maximumSwap(num: number): number {
    const list = new Array();
    while (num !== 0) {
        list.push(num % 10);
        num = Math.floor(num / 10);
    }
    const n = list.length;
    const idx = new Array();
    for (let i = 0, j = 0; i < n; i++) {
        if (list[i] > list[j]) {
            j = i;
        }
        idx.push(j);
    }
    for (let i = n - 1; i >= 0; i--) {
        if (list[idx[i]] !== list[i]) {
            [list[idx[i]], list[i]] = [list[i], list[idx[i]]];
            break;
        }
    }
    let res = 0;
    for (let i = n - 1; i >= 0; i--) {
        res = res * 10 + list[i];
    }
    return res;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_swap(mut num: i32) -> i32 {
        let mut list = {
            let mut res = Vec::new();
            while num != 0 {
                res.push(num % 10);
                num /= 10;
            }
            res
        };
        let n = list.len();
        let idx = {
            let mut i = 0;
            (0..n)
                .map(|j| {
                    if list[j] > list[i] {
                        i = j;
                    }
                    i
                })
                .collect::<Vec<usize>>()
        };
        for i in (0..n).rev() {
            if list[i] != list[idx[i]] {
                list.swap(i, idx[i]);
                break;
            }
        }
        let mut res = 0;
        for i in list.iter().rev() {
            res = res * 10 + i;
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：贪心 + 空间优化

<!-- tabs:start -->

#### TypeScript

```ts
function maximumSwap(num: number): number {
    const ans = [...String(num)];
    let [min, max, maybeMax, n] = [-1, -1, -1, ans.length];

    for (let i = n - 1; i >= 0; i--) {
        if (ans[i] > (ans[maybeMax] ?? -1)) maybeMax = i;
        if (i < maybeMax && ans[i] < ans[maybeMax]) {
            [min, max] = [i, maybeMax];
        }
    }

    if (~min && ~max && min < max) {
        [ans[min], ans[max]] = [ans[max], ans[min]];
    }

    return +ans.join('');
}
```

#### JavaScript

```js
function maximumSwap(num) {
    const ans = [...String(num)];
    let [min, max, maybeMax, n] = [-1, -1, -1, ans.length];

    for (let i = n - 1; i >= 0; i--) {
        if (ans[i] > (ans[maybeMax] ?? -1)) maybeMax = i;
        if (i < maybeMax && ans[i] < ans[maybeMax]) {
            [min, max] = [i, maybeMax];
        }
    }

    if (~min && ~max && min < max) {
        [ans[min], ans[max]] = [ans[max], ans[min]];
    }

    return +ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
