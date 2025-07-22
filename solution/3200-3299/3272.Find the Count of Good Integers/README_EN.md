---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3272.Find%20the%20Count%20of%20Good%20Integers/README_EN.md
rating: 2382
source: Biweekly Contest 138 Q3
tags:
    - Hash Table
    - Math
    - Combinatorics
    - Enumeration
---

<!-- problem:start -->

# [3272. Find the Count of Good Integers](https://leetcode.com/problems/find-the-count-of-good-integers)

[中文文档](/solution/3200-3299/3272.Find%20the%20Count%20of%20Good%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>positive</strong> integers <code>n</code> and <code>k</code>.</p>

<p>An integer <code>x</code> is called <strong>k-palindromic</strong> if:</p>

<ul>
	<li><code>x</code> is a <span data-keyword="palindrome-integer">palindrome</span>.</li>
	<li><code>x</code> is divisible by <code>k</code>.</li>
</ul>

<p>An integer is called <strong>good</strong> if its digits can be <em>rearranged</em> to form a <strong>k-palindromic</strong> integer. For example, for <code>k = 2</code>, 2020 can be rearranged to form the <em>k-palindromic</em> integer 2002, whereas 1010 cannot be rearranged to form a <em>k-palindromic</em> integer.</p>

<p>Return the count of <strong>good</strong> integers containing <code>n</code> digits.</p>

<p><strong>Note</strong> that <em>any</em> integer must <strong>not</strong> have leading zeros, <strong>neither</strong> before <strong>nor</strong> after rearrangement. For example, 1010 <em>cannot</em> be rearranged to form 101.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">27</span></p>

<p><strong>Explanation:</strong></p>

<p><em>Some</em> of the good integers are:</p>

<ul>
	<li>551 because it can be rearranged to form 515.</li>
	<li>525 because it is already k-palindromic.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The two good integers are 4 and 8.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2468</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Combinatorics

We can consider enumerating all palindromic numbers of length $n$ and checking whether they are $k$-palindromic numbers. Due to the properties of palindromic numbers, we only need to enumerate the first half of the digits and then reverse and append them to form the full number.

The length of the first half of the digits is $\lfloor \frac{n - 1}{2} \rfloor$, so the range of the first half is $[10^{\lfloor \frac{n - 1}{2} \rfloor}, 10^{\lfloor \frac{n - 1}{2} \rfloor + 1})$. We can reverse the first half and append it to form a palindromic number of length $n$. Note that if $n$ is odd, the middle digit needs special handling.

Next, we check whether the palindromic number is $k$-palindromic. If it is, we count all unique permutations of the number. To avoid duplicates, we can use a set $\textit{vis}$ to store the smallest permutation of each palindromic number that has already been processed. If the smallest permutation of the current palindromic number is already in the set, we skip it. Otherwise, we calculate the number of unique permutations of the palindromic number and add it to the result.

We can use an array $\textit{cnt}$ to count the occurrences of each digit and use combinatorics to calculate the number of permutations. Specifically, if digit $0$ appears $x_0$ times, digit $1$ appears $x_1$ times, ..., and digit $9$ appears $x_9$ times, the number of permutations of the palindromic number is:

$$
\frac{(n - x_0) \cdot (n - 1)!}{x_0! \cdot x_1! \cdots x_9!}
$$

Here, $(n - x_0)$ represents the number of choices for the highest digit (excluding $0$), $(n - 1)!$ represents the permutations of the remaining digits, and we divide by the factorial of the occurrences of each digit to avoid duplicates.

Finally, we sum up all the permutation counts to get the final result.

Time complexity is $O(10^m \times n \times \log n)$, and space complexity is $O(10^m \times n)$, where $m = \lfloor \frac{n - 1}{2} \rfloor$.

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
