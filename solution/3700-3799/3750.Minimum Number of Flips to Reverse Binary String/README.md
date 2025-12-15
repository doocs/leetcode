---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3750.Minimum%20Number%20of%20Flips%20to%20Reverse%20Binary%20String/README.md
rating: 1288
source: 第 170 场双周赛 Q1
tags:
    - 位运算
    - 数学
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [3750. 最少反转次数得到翻转二进制字符串](https://leetcode.cn/problems/minimum-number-of-flips-to-reverse-binary-string)

[English Version](/solution/3700-3799/3750.Minimum%20Number%20of%20Flips%20to%20Reverse%20Binary%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>正</strong> 整数 <code>n</code>。</p>

<p>令 <code>s</code> 为 <code>n</code> 的 <strong>二进制表示</strong>（不含前导零）。</p>

<p>二进制字符串 <code>s</code> 的 <strong>反转</strong> 是指将 <code>s</code> 中的字符按相反顺序排列得到的字符串。</p>

<p>你可以翻转 <code>s</code> 中的任意一位（将 <code>0 → 1</code> 或 <code>1 → 0</code>）。每次翻转 <strong>仅</strong> 影响一位。</p>

<p>请返回使 <code>s</code> 等于其原始形式的反转所需的 <strong>最少</strong> 翻转次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 7</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>7 的二进制表示为 <code>"111"</code>。其反转也是 <code>"111"</code>，两者相同。因此，不需要翻转。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 10</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>10 的二进制表示为 <code>"1010"</code>。其反转为 <code>"0101"</code>。必须翻转所有四位才能使它们相等。因此，所需的最少翻转次数为 4。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们首先将整数 $n$ 转换成二进制字符串 $s$，然后我们使用双指针分别从字符串的两端向中间遍历，统计对应位置字符不同的个数 $cnt$。由于每次翻转只能影响一位，因此总的翻转次数为 $cnt \times 2$。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$，其中 $n$ 是输入的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumFlips(self, n: int) -> int:
        s = bin(n)[2:]
        m = len(s)
        return sum(s[i] != s[m - i - 1] for i in range(m // 2)) * 2
```

#### Java

```java
class Solution {
    public int minimumFlips(int n) {
        String s = Integer.toBinaryString(n);
        int m = s.length();
        int cnt = 0;
        for (int i = 0; i < m / 2; i++) {
            if (s.charAt(i) != s.charAt(m - i - 1)) {
                cnt++;
            }
        }
        return cnt * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumFlips(int n) {
        vector<int> s;
        while (n > 0) {
            s.push_back(n & 1);
            n >>= 1;
        }

        int m = s.size();
        int cnt = 0;
        for (int i = 0; i < m / 2; i++) {
            if (s[i] != s[m - i - 1]) {
                cnt++;
            }
        }
        return cnt * 2;
    }
};
```

#### Go

```go
func minimumFlips(n int) int {
    s := strconv.FormatInt(int64(n), 2)
    m := len(s)
    cnt := 0
    for i := 0; i < m/2; i++ {
        if s[i] != s[m-i-1] {
            cnt++
        }
    }
    return cnt * 2
}
```

#### TypeScript

```ts
function minimumFlips(n: number): number {
    const s = n.toString(2);
    const m = s.length;
    let cnt = 0;
    for (let i = 0; i < m / 2; i++) {
        if (s[i] !== s[m - i - 1]) {
            cnt++;
        }
    }
    return cnt * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
