---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0564.Find%20the%20Closest%20Palindrome/README.md
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [564. 寻找最近的回文数](https://leetcode.cn/problems/find-the-closest-palindrome)

[English Version](/solution/0500-0599/0564.Find%20the%20Closest%20Palindrome/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个表示整数的字符串&nbsp;<code>n</code> ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。</p>

<p>“最近的”定义为两个整数<strong>差的绝对值</strong>最小。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = "123"
<strong>输出:</strong> "121"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = "1"
<strong>输出:</strong> "0"
<strong>解释:</strong> 0 和 2是最近的回文，但我们返回最小的，也就是 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 18</code></li>
	<li><code>n</code>&nbsp;只由数字组成</li>
	<li><code>n</code>&nbsp;不含前导 0</li>
	<li><code>n</code>&nbsp;代表在&nbsp;<code>[1, 10<sup>18</sup>&nbsp;- 1]</code> 范围内的整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nearestPalindromic(self, n: str) -> str:
        x = int(n)
        l = len(n)
        res = {10 ** (l - 1) - 1, 10**l + 1}
        left = int(n[: (l + 1) >> 1])
        for i in range(left - 1, left + 2):
            j = i if l % 2 == 0 else i // 10
            while j:
                i = i * 10 + j % 10
                j //= 10
            res.add(i)
        res.discard(x)

        ans = -1
        for t in res:
            if (
                ans == -1
                or abs(t - x) < abs(ans - x)
                or (abs(t - x) == abs(ans - x) and t < ans)
            ):
                ans = t
        return str(ans)
```

#### Java

```java
class Solution {
    public String nearestPalindromic(String n) {
        long x = Long.parseLong(n);
        long ans = -1;
        for (long t : get(n)) {
            if (ans == -1 || Math.abs(t - x) < Math.abs(ans - x)
                || (Math.abs(t - x) == Math.abs(ans - x) && t < ans)) {
                ans = t;
            }
        }
        return Long.toString(ans);
    }

    private Set<Long> get(String n) {
        int l = n.length();
        Set<Long> res = new HashSet<>();
        res.add((long) Math.pow(10, l - 1) - 1);
        res.add((long) Math.pow(10, l) + 1);
        long left = Long.parseLong(n.substring(0, (l + 1) / 2));
        for (long i = left - 1; i <= left + 1; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(new StringBuilder(i + "").reverse().substring(l & 1));
            res.add(Long.parseLong(sb.toString()));
        }
        res.remove(Long.parseLong(n));
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string nearestPalindromic(string n) {
        long x = stol(n);
        long ans = -1;
        for (long t : get(n))
            if (ans == -1 || abs(t - x) < abs(ans - x) || (abs(t - x) == abs(ans - x) && t < ans))
                ans = t;
        return to_string(ans);
    }

    unordered_set<long> get(string& n) {
        int l = n.size();
        unordered_set<long> res;
        res.insert((long) pow(10, l - 1) - 1);
        res.insert((long) pow(10, l) + 1);
        long left = stol(n.substr(0, (l + 1) / 2));
        for (long i = left - 1; i <= left + 1; ++i) {
            string prefix = to_string(i);
            string t = prefix + string(prefix.rbegin() + (l & 1), prefix.rend());
            res.insert(stol(t));
        }
        res.erase(stol(n));
        return res;
    }
};
```

#### Go

```go
func nearestPalindromic(n string) string {
	l := len(n)
	res := []int{int(math.Pow10(l-1)) - 1, int(math.Pow10(l)) + 1}
	left, _ := strconv.Atoi(n[:(l+1)/2])
	for _, x := range []int{left - 1, left, left + 1} {
		y := x
		if l&1 == 1 {
			y /= 10
		}
		for ; y > 0; y /= 10 {
			x = x*10 + y%10
		}
		res = append(res, x)
	}
	ans := -1
	x, _ := strconv.Atoi(n)
	for _, t := range res {
		if t != x {
			if ans == -1 || abs(t-x) < abs(ans-x) || abs(t-x) == abs(ans-x) && t < ans {
				ans = t
			}
		}
	}
	return strconv.Itoa(ans)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### JavaScript

```js
/**
 * @param {string} n
 * @return {string}
 */

function nearestPalindromic(n) {
    const x = BigInt(n);
    let ans = null;

    for (const t of getCandidates(n)) {
        if (
            ans === null ||
            absDiff(t, x) < absDiff(ans, x) ||
            (absDiff(t, x) === absDiff(ans, x) && t < ans)
        ) {
            ans = t;
        }
    }

    return ans.toString();
}

function getCandidates(n) {
    const length = n.length;
    const res = new Set();

    res.add(BigInt(Math.pow(10, length - 1) - 1));
    res.add(BigInt(Math.pow(10, length) + 1));

    const left = BigInt(n.substring(0, Math.ceil(length / 2)));

    for (let i = left - 1n; i <= left + 1n; i++) {
        const prefix = i.toString();
        const t =
            prefix +
            prefix
                .split('')
                .reverse()
                .slice(length % 2)
                .join('');
        res.add(BigInt(t));
    }

    res.delete(BigInt(n));
    return res;
}

function absDiff(a, b) {
    return a > b ? a - b : b - a;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
