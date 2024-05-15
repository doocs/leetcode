---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0906.Super%20Palindromes/README.md
tags:
    - 数学
    - 枚举
---

# [906. 超级回文数](https://leetcode.cn/problems/super-palindromes)

[English Version](/solution/0900-0999/0906.Super%20Palindromes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。</p>

<p>现在，给定两个正整数&nbsp;<code>L</code> 和&nbsp;<code>R</code> （以字符串形式表示），返回包含在范围 <code>[L, R]</code> 中的超级回文数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>L = &quot;4&quot;, R = &quot;1000&quot;
<strong>输出：</strong>4
<strong>解释：
</strong>4，9，121，以及 484 是超级回文数。
注意 676 不是一个超级回文数： 26 * 26 = 676，但是 26 不是回文数。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= len(L) &lt;= 18</code></li>
	<li><code>1 &lt;= len(R) &lt;= 18</code></li>
	<li><code>L</code> 和&nbsp;<code>R</code>&nbsp;是表示&nbsp;<code>[1, 10^18)</code>&nbsp;范围的整数的字符串。</li>
	<li><code>int(L) &lt;= int(R)</code></li>
</ol>

<p>&nbsp;</p>

## 解法

### 方法一：预处理 + 枚举

根据题目描述，我们假设超级回文数 $x = p^2 \in [1, 10^{18})$，其中 $p$ 是回文数，那么 $p \in [1, 10^9)$。我们可以枚举回文数 $p$ 的前半部分，然后将其翻转后拼接，得到所有的回文数，记录在数组 $ps$ 中。

接下来，我们遍历数组 $ps$，对于每个 $p$，我们计算 $p^2$，判断是否在区间 $[L, R]$ 中，同时判断 $p^2$ 是否是回文数，若是，答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(M^{\frac{1}{4}} \times \log M)$，空间复杂度 $O(M^{\frac{1}{4}})$。其中 $M$ 是 $L$ 和 $R$ 的上界，本题中 $M \leq 10^{18}$。

相似题目：

-   [2967. 使数组成为等数数组的最小代价](https://github.com/doocs/leetcode/blob/main/solution/2900-2999/2967.Minimum%20Cost%20to%20Make%20Array%20Equalindromic/README.md)

<!-- tabs:start -->

```python
ps = []
for i in range(1, 10**5 + 1):
    s = str(i)
    t1 = s[::-1]
    t2 = s[:-1][::-1]
    ps.append(int(s + t1))
    ps.append(int(s + t2))


class Solution:
    def superpalindromesInRange(self, left: str, right: str) -> int:
        def is_palindrome(x: int) -> bool:
            y, t = 0, x
            while t:
                y = y * 10 + t % 10
                t //= 10
            return x == y

        l, r = int(left), int(right)
        return sum(l <= x <= r and is_palindrome(x) for x in map(lambda x: x * x, ps))
```

```java
class Solution {
    private static long[] ps;

    static {
        ps = new long[2 * (int) 1e5];
        for (int i = 1; i <= 1e5; i++) {
            String s = Integer.toString(i);
            String t1 = new StringBuilder(s).reverse().toString();
            String t2 = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
            ps[2 * i - 2] = Long.parseLong(s + t1);
            ps[2 * i - 1] = Long.parseLong(s + t2);
        }
    }

    public int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        int ans = 0;
        for (long p : ps) {
            long x = p * p;
            if (x >= l && x <= r && isPalindrome(x)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean isPalindrome(long x) {
        long y = 0;
        for (long t = x; t > 0; t /= 10) {
            y = y * 10 + t % 10;
        }
        return x == y;
    }
}
```

```cpp
using ll = unsigned long long;

ll ps[2 * 100000];

int init = [] {
    for (int i = 1; i <= 100000; i++) {
        string s = to_string(i);
        string t1 = s;
        reverse(t1.begin(), t1.end());
        string t2 = s.substr(0, s.length() - 1);
        reverse(t2.begin(), t2.end());
        ps[2 * i - 2] = stoll(s + t1);
        ps[2 * i - 1] = stoll(s + t2);
    }
    return 0;
}();

class Solution {
public:
    int superpalindromesInRange(string left, string right) {
        ll l = stoll(left), r = stoll(right);
        int ans = 0;
        for (ll p : ps) {
            ll x = p * p;
            if (x >= l && x <= r && is_palindrome(x)) {
                ++ans;
            }
        }
        return ans;
    }

    bool is_palindrome(ll x) {
        ll y = 0;
        for (ll t = x; t; t /= 10) {
            y = y * 10 + t % 10;
        }
        return x == y;
    }
};
```

```go
var ps [2 * 100000]int64

func init() {
	for i := 1; i <= 100000; i++ {
		s := strconv.Itoa(i)
		t1 := reverseString(s)
		t2 := reverseString(s[:len(s)-1])
		ps[2*i-2], _ = strconv.ParseInt(s+t1, 10, 64)
		ps[2*i-1], _ = strconv.ParseInt(s+t2, 10, 64)
	}
}

func reverseString(s string) string {
	cs := []rune(s)
	for i, j := 0, len(cs)-1; i < j; i, j = i+1, j-1 {
		cs[i], cs[j] = cs[j], cs[i]
	}
	return string(cs)
}

func superpalindromesInRange(left string, right string) (ans int) {
	l, _ := strconv.ParseInt(left, 10, 64)
	r, _ := strconv.ParseInt(right, 10, 64)
	isPalindrome := func(x int64) bool {
		var y int64
		for t := x; t > 0; t /= 10 {
			y = y*10 + int64(t%10)
		}
		return x == y
	}
	for _, p := range ps {
		x := p * p
		if x >= l && x <= r && isPalindrome(x) {
			ans++
		}
	}
	return
}
```

```ts
const ps = Array(2e5).fill(0);

const init = (() => {
    for (let i = 1; i <= 1e5; ++i) {
        const s: string = i.toString();
        const t1: string = s.split('').reverse().join('');
        const t2: string = s.slice(0, -1).split('').reverse().join('');
        ps[2 * i - 2] = parseInt(s + t1, 10);
        ps[2 * i - 1] = parseInt(s + t2, 10);
    }
})();

function superpalindromesInRange(left: string, right: string): number {
    const l = BigInt(left);
    const r = BigInt(right);
    const isPalindrome = (x: bigint): boolean => {
        const s: string = x.toString();
        return s === s.split('').reverse().join('');
    };
    let ans = 0;
    for (const p of ps) {
        const x = BigInt(p) * BigInt(p);
        if (x >= l && x <= r && isPalindrome(x)) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
