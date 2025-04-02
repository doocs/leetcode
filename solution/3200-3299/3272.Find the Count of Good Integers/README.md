---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3272.Find%20the%20Count%20of%20Good%20Integers/README.md
rating: 2382
source: 第 138 场双周赛 Q3
tags:
    - 哈希表
    - 数学
    - 组合数学
    - 枚举
---

<!-- problem:start -->

# [3272. 统计好整数的数目](https://leetcode.cn/problems/find-the-count-of-good-integers)

[English Version](/solution/3200-3299/3272.Find%20the%20Count%20of%20Good%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>正</strong>&nbsp;整数&nbsp;<code>n</code> 和&nbsp;<code>k</code>&nbsp;。</p>

<p>如果一个整数&nbsp;<code>x</code>&nbsp;满足以下条件，那么它被称为 <strong>k</strong><strong>&nbsp;回文</strong>&nbsp;整数&nbsp;。</p>

<ul>
	<li><code>x</code>&nbsp;是一个&nbsp;<span data-keyword="palindrome-integer">回文整数 。</span></li>
	<li><code>x</code>&nbsp;能被 <code>k</code>&nbsp;整除。</li>
</ul>

<p>如果一个整数的数位重新排列后能得到一个 <strong>k 回文整数</strong>&nbsp;，那么我们称这个整数为&nbsp;<strong>好 </strong>整数。比方说，<code>k = 2</code>&nbsp;，那么&nbsp;2020 可以重新排列得到 2002 ，2002 是一个 k 回文串，所以 2020 是一个好整数。而 1010 无法重新排列数位得到一个 k 回文整数。</p>

<p>请你返回 <code>n</code>&nbsp;个数位的整数中，有多少个 <strong>好</strong>&nbsp;整数。</p>

<p><b>注意</b>&nbsp;，任何整数在重新排列数位之前或者之后 <strong>都不能</strong> 有前导 0 。比方说 1010 不能重排列得到&nbsp;101 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, k = 5</span></p>

<p><span class="example-io"><b>输出：</b>27</span></p>

<p><b>解释：</b></p>

<p>部分好整数如下：</p>

<ul>
	<li>551 ，因为它可以重排列得到 515 。</li>
	<li>525 ，因为它已经是一个 k 回文整数。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 1, k = 4</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>两个好整数分别是 4 和 8 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 5, k = 6</span></p>

<p><span class="example-io"><b>输出：</b>2468</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 组合数学

我们可以考虑枚举所有长度为 $n$ 的回文数，判断它们是否是 $k$ 回文数。由于回文数的性质，我们只需要枚举前半部分的数字，然后将其反转拼接到后面即可。

前半部分的数字长度为 $\lfloor \frac{n - 1}{2} \rfloor$，那么前半部分的数字范围是 $[10^{\lfloor \frac{n - 1}{2} \rfloor}, 10^{\lfloor \frac{n - 1}{2} \rfloor + 1})$。我们可以将前半部分的数字拼接到后面，形成一个长度为 $n$ 的回文数。注意到，如果 $n$ 是奇数，则需要将中间的数字做特殊处理。

然后，我们判断该回文数是否是 $k$ 回文数，如果是，则统计该回文数的所有排列组合。为了避免重复，我们可以使用一个集合 $\textit{vis}$ 来存储已经出现过的回文数的每个最小排列。如果该回文数的最小排列已经出现过，则跳过该回文数。否则，我们统计该回文数有多少个不重复的排列组合，添加到答案中。

我们可以使用一个数组 $\textit{cnt}$ 来统计每个数字出现的次数，然后使用组合数学的公式计算排列组合的数量。具体来说，假设数字 $0$ 出现了 $x_0$ 次，数字 $1$ 出现了 $x_1$ 次，...，数字 $9$ 出现了 $x_9$ 次，那么该回文数的排列组合数量为：

$$
\frac{(n - x_0) \cdot (n - 1)!}{x_0! \cdot x_1! \cdots x_9!}
$$

其中 $(n - x_0)$ 表示最高位可以选择除 $0$ 以外的所有数字，而 $(n - 1)!$ 表示除最高位以外的所有数字的排列组合数量，然后我们除以每个数字出现的次数的阶乘，避免重复。

最后，我们将所有的排列组合数量相加，得到最终的答案。

时间复杂度 $({10}^m \times n \times \log n)$，空间复杂度 $O({10}^m \times n)$，其中 $m = \lfloor \frac{n - 1}{2} \rfloor$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodIntegers(self, n: int, k: int) -> int:
        fac = [factorial(i) for i in range(n + 1)]
        ans = 0
        vis = set()
        base = 10 ** ((n - 1) // 2)
        for i in range(base, base * 10):
            s = str(i)
            s += s[::-1][n % 2 :]
            if int(s) % k:
                continue
            t = "".join(sorted(s))
            if t in vis:
                continue
            vis.add(t)
            cnt = Counter(t)
            res = (n - cnt["0"]) * fac[n - 1]
            for x in cnt.values():
                res //= fac[x]
            ans += res
        return ans
```

#### Java

```java
class Solution {
    public long countGoodIntegers(int n, int k) {
        long[] fac = new long[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i;
        }

        long ans = 0;
        Set<String> vis = new HashSet<>();
        int base = (int) Math.pow(10, (n - 1) / 2);

        for (int i = base; i < base * 10; i++) {
            String s = String.valueOf(i);
            StringBuilder sb = new StringBuilder(s).reverse();
            s += sb.substring(n % 2);
            if (Long.parseLong(s) % k != 0) {
                continue;
            }

            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String t = new String(arr);
            if (vis.contains(t)) {
                continue;
            }
            vis.add(t);
            int[] cnt = new int[10];
            for (char c : arr) {
                cnt[c - '0']++;
            }

            long res = (n - cnt[0]) * fac[n - 1];
            for (int x : cnt) {
                res /= fac[x];
            }
            ans += res;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countGoodIntegers(int n, int k) {
        vector<long long> fac(n + 1, 1);
        for (int i = 1; i <= n; ++i) {
            fac[i] = fac[i - 1] * i;
        }

        long long ans = 0;
        unordered_set<string> vis;
        int base = pow(10, (n - 1) / 2);

        for (int i = base; i < base * 10; ++i) {
            string s = to_string(i);
            string rev = s;
            reverse(rev.begin(), rev.end());
            s += rev.substr(n % 2);
            if (stoll(s) % k) {
                continue;
            }
            string t = s;
            sort(t.begin(), t.end());
            if (vis.count(t)) {
                continue;
            }
            vis.insert(t);
            vector<int> cnt(10);
            for (char c : t) {
                cnt[c - '0']++;
            }
            long long res = (n - cnt[0]) * fac[n - 1];
            for (int x : cnt) {
                res /= fac[x];
            }
            ans += res;
        }
        return ans;
    }
};
```

#### Go

```go
func factorial(n int) []int64 {
	fac := make([]int64, n+1)
	fac[0] = 1
	for i := 1; i <= n; i++ {
		fac[i] = fac[i-1] * int64(i)
	}
	return fac
}

func countGoodIntegers(n int, k int) (ans int64) {
	fac := factorial(n)
	vis := make(map[string]bool)
	base := int(math.Pow(10, float64((n-1)/2)))

	for i := base; i < base*10; i++ {
		s := strconv.Itoa(i)
		rev := reverseString(s)
		s += rev[n%2:]
		num, _ := strconv.ParseInt(s, 10, 64)
		if num%int64(k) != 0 {
			continue
		}
		bs := []byte(s)
		slices.Sort(bs)
		t := string(bs)

		if vis[t] {
			continue
		}
		vis[t] = true
		cnt := make([]int, 10)
		for _, c := range t {
			cnt[c-'0']++
		}
		res := (int64(n) - int64(cnt[0])) * fac[n-1]
		for _, x := range cnt {
			res /= fac[x]
		}
		ans += res
	}

	return
}

func reverseString(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}
```

#### TypeScript

```ts
function countGoodIntegers(n: number, k: number): number {
    const fac = factorial(n);
    let ans = 0;
    const vis = new Set<string>();
    const base = Math.pow(10, Math.floor((n - 1) / 2));

    for (let i = base; i < base * 10; i++) {
        let s = `${i}`;
        const rev = reverseString(s);
        if (n % 2 === 1) {
            s += rev.substring(1);
        } else {
            s += rev;
        }

        if (+s % k !== 0) {
            continue;
        }

        const bs = Array.from(s).sort();
        const t = bs.join('');

        if (vis.has(t)) {
            continue;
        }

        vis.add(t);

        const cnt = Array(10).fill(0);
        for (const c of t) {
            cnt[+c]++;
        }

        let res = (n - cnt[0]) * fac[n - 1];
        for (const x of cnt) {
            res /= fac[x];
        }
        ans += res;
    }

    return ans;
}

function factorial(n: number): number[] {
    const fac = Array(n + 1).fill(1);
    for (let i = 1; i <= n; i++) {
        fac[i] = fac[i - 1] * i;
    }
    return fac;
}

function reverseString(s: string): string {
    return s.split('').reverse().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn count_good_integers(n: i32, k: i32) -> i64 {
        use std::collections::HashSet;
        let n = n as usize;
        let k = k as i64;
        let mut fac = vec![1_i64; n + 1];
        for i in 1..=n {
            fac[i] = fac[i - 1] * i as i64;
        }

        let mut ans = 0;
        let mut vis = HashSet::new();
        let base = 10_i64.pow(((n - 1) / 2) as u32);

        for i in base..base * 10 {
            let s = i.to_string();
            let rev: String = s.chars().rev().collect();
            let full_s = if n % 2 == 0 {
                format!("{}{}", s, rev)
            } else {
                format!("{}{}", s, &rev[1..])
            };

            let num: i64 = full_s.parse().unwrap();
            if num % k != 0 {
                continue;
            }

            let mut arr: Vec<char> = full_s.chars().collect();
            arr.sort_unstable();
            let t: String = arr.iter().collect();
            if vis.contains(&t) {
                continue;
            }
            vis.insert(t);

            let mut cnt = vec![0; 10];
            for c in arr {
                cnt[c as usize - '0' as usize] += 1;
            }

            let mut res = (n - cnt[0]) as i64 * fac[n - 1];
            for &x in &cnt {
                if x > 0 {
                    res /= fac[x];
                }
            }
            ans += res;
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var countGoodIntegers = function (n, k) {
    const fac = factorial(n);
    let ans = 0;
    const vis = new Set();
    const base = Math.pow(10, Math.floor((n - 1) / 2));

    for (let i = base; i < base * 10; i++) {
        let s = String(i);
        const rev = reverseString(s);
        if (n % 2 === 1) {
            s += rev.substring(1);
        } else {
            s += rev;
        }

        if (parseInt(s, 10) % k !== 0) {
            continue;
        }

        const bs = Array.from(s).sort();
        const t = bs.join('');

        if (vis.has(t)) {
            continue;
        }

        vis.add(t);

        const cnt = Array(10).fill(0);
        for (const c of t) {
            cnt[parseInt(c, 10)]++;
        }

        let res = (n - cnt[0]) * fac[n - 1];
        for (const x of cnt) {
            res /= fac[x];
        }
        ans += res;
    }

    return ans;
};

function factorial(n) {
    const fac = Array(n + 1).fill(1);
    for (let i = 1; i <= n; i++) {
        fac[i] = fac[i - 1] * i;
    }
    return fac;
}

function reverseString(s) {
    return s.split('').reverse().join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
