---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1399.Count%20Largest%20Group/README.md
rating: 1341
source: 第 23 场双周赛 Q1
tags:
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [1399. 统计最大组的数目](https://leetcode.cn/problems/count-largest-group)

[English Version](/solution/1300-1399/1399.Count%20Largest%20Group/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数 <code>n</code>&nbsp;。</p>

<p>我们需要根据数字的数位和将 <code>1</code> 到 <code>n</code> 的数字分组。例如，数字 14 和 5 属于 <strong>同一</strong>&nbsp;组，而数字 13 和 3 属于 <strong>不同</strong>&nbsp;组。</p>

<p>返回最大组的数字数量，即元素数量 <strong>最多</strong> 的组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>4
<strong>解释：</strong>总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
[1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>2
<strong>解释：</strong>总共有 2 个大小为 1 的组 [1]，[2]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表或数组

我们注意到数字范围不超过 $10^4$，因此数位和的范围也不超过 $9 \times 4 = 36$，因此我们可以用哈希表或者一个长度为 $40$ 的数组 $cnt$ 来统计每个数位和的个数，用一个变量 $mx$ 表示最大的数位和个数。

我们在 $[1,..n]$ 中枚举每个数，计算其数位和 $s$，然后将 $cnt[s]$ 加 $1$，如果 $mx \lt cnt[s]$，则更新 $mx = cnt[s]$，并将 $ans$ 置为 $1$，如果 $mx = cnt[s]$，则将 $ans$ 加 $1$。

最后返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $(\log n)$。其中 $n$ 为给定的数字。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countLargestGroup(self, n: int) -> int:
        cnt = Counter()
        ans = mx = 0
        for i in range(1, n + 1):
            s = 0
            while i:
                s += i % 10
                i //= 10
            cnt[s] += 1
            if mx < cnt[s]:
                mx = cnt[s]
                ans = 1
            elif mx == cnt[s]:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countLargestGroup(int n) {
        int[] cnt = new int[40];
        int ans = 0, mx = 0;
        for (int i = 1; i <= n; ++i) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            ++cnt[s];
            if (mx < cnt[s]) {
                mx = cnt[s];
                ans = 1;
            } else if (mx == cnt[s]) {
                ++ans;
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
    int countLargestGroup(int n) {
        int cnt[40]{};
        int ans = 0, mx = 0;
        for (int i = 1; i <= n; ++i) {
            int s = 0;
            for (int x = i; x; x /= 10) {
                s += x % 10;
            }
            ++cnt[s];
            if (mx < cnt[s]) {
                mx = cnt[s];
                ans = 1;
            } else if (mx == cnt[s]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countLargestGroup(n int) (ans int) {
	cnt := [40]int{}
	mx := 0
	for i := 1; i <= n; i++ {
		s := 0
		for x := i; x > 0; x /= 10 {
			s += x % 10
		}
		cnt[s]++
		if mx < cnt[s] {
			mx = cnt[s]
			ans = 1
		} else if mx == cnt[s] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countLargestGroup(n: number): number {
    const cnt: number[] = Array(40).fill(0);
    let mx = 0;
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        let s = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            s += x % 10;
        }
        ++cnt[s];
        if (mx < cnt[s]) {
            mx = cnt[s];
            ans = 1;
        } else if (mx === cnt[s]) {
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_largest_group(n: i32) -> i32 {
        let mut cnt = vec![0; 40];
        let mut ans = 0;
        let mut mx = 0;

        for i in 1..=n {
            let mut s = 0;
            let mut x = i;
            while x > 0 {
                s += x % 10;
                x /= 10;
            }
            cnt[s as usize] += 1;
            if mx < cnt[s as usize] {
                mx = cnt[s as usize];
                ans = 1;
            } else if mx == cnt[s as usize] {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
