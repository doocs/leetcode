---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2847.Smallest%20Number%20With%20Given%20Digit%20Product/README.md
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [2847. 给定数字乘积的最小数字 🔒](https://leetcode.cn/problems/smallest-number-with-given-digit-product)

[English Version](/solution/2800-2899/2847.Smallest%20Number%20With%20Given%20Digit%20Product/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>正</strong> 整数 <code>n</code>，返回一个字符串，表示 <strong>最小的正整数</strong>，使其各位数字的乘积等于 <code>n</code>&nbsp;，如果不存在这样的数字，则返回 <code>"-1"</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>n = 105
<b>输出：</b>"357"
<b>解释：</b>3 * 5 * 7 = 105。可以证明，357 是乘积等于 105 的最小数字。因此答案为 "105"。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 7
<b>输出：</b>"7"
<b>解释：</b>由于 7 只有一位数字，其各位数字的乘积为 7。由于数字 1 到 6 的乘积分别为 1 到 6，所以答案为 "7"。可以证明 7 是乘积等于 7 的最小数字。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 44
<b>输出：</b>"-1"
<b>解释：</b>可以证明，没有数字的各位数字乘积等于 44。因此答案为 "-1"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>18</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：质因数分解 + 贪心

我们考虑对数字 $n$ 进行质因数分解，如果 $n$ 的质因数中存在大于 $9$ 的质数，那么一定无法找到符合条件的数字，因为大于 $9$ 的质数无法通过 $1$ 到 $9$ 的数字相乘得到，例如 $11$ 无法通过 $1$ 到 $9$ 的数字相乘得到，因此我们只需要考虑 $n$ 的质因数中是否存在大于 $9$ 的质数即可，如果存在，直接返回 $-1$。

否则，如果质因数中包含 $7$ 和 $5$，那么数字 $n$ 首先可以拆分为若干个 $7$ 和 $5$，两个数字 $3$ 可以合成一个数字 $9$，三个数字 $2$ 可以合成一个数字 $8$，数字 $2$ 和数字 $3$ 可以合成一个数字 $6$，因此我们只需要将数字拆分为 $[2,..9]$ 的数字即可，我们可以使用贪心的方法，优先拆分出数字 $9$，然后拆分出数字 $8$，依次类推。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def smallestNumber(self, n: int) -> str:
        cnt = [0] * 10
        for i in range(9, 1, -1):
            while n % i == 0:
                n //= i
                cnt[i] += 1
        if n > 1:
            return "-1"
        ans = "".join(str(i) * cnt[i] for i in range(2, 10))
        return ans if ans else "1"
```

```java
class Solution {
    public String smallestNumber(long n) {
        int[] cnt = new int[10];
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                ++cnt[i];
                n /= i;
            }
        }
        if (n > 1) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10; ++i) {
            while (cnt[i] > 0) {
                sb.append(i);
                --cnt[i];
            }
        }
        String ans = sb.toString();
        return ans.isEmpty() ? "1" : ans;
    }
}
```

```cpp
class Solution {
public:
    string smallestNumber(long long n) {
        int cnt[10]{};
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                n /= i;
                ++cnt[i];
            }
        }
        if (n > 1) {
            return "-1";
        }
        string ans;
        for (int i = 2; i < 10; ++i) {
            ans += string(cnt[i], '0' + i);
        }
        return ans == "" ? "1" : ans;
    }
};
```

```go
func smallestNumber(n int64) string {
	cnt := [10]int{}
	for i := 9; i > 1; i-- {
		for n%int64(i) == 0 {
			cnt[i]++
			n /= int64(i)
		}
	}
	if n != 1 {
		return "-1"
	}
	sb := &strings.Builder{}
	for i := 2; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			sb.WriteByte(byte(i) + '0')
		}
	}
	ans := sb.String()
	if len(ans) > 0 {
		return ans
	}
	return "1"
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
