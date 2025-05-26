---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1399.Count%20Largest%20Group/README_EN.md
rating: 1341
source: Biweekly Contest 23 Q1
tags:
    - Hash Table
    - Math
---

<!-- problem:start -->

# [1399. Count Largest Group](https://leetcode.com/problems/count-largest-group)

[中文文档](/solution/1300-1399/1399.Count%20Largest%20Group/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>We need to group the numbers from <code>1</code> to <code>n</code> according to the sum of its digits. For example, the numbers 14 and 5 belong to the <strong>same</strong> group, whereas 13 and 3 belong to <strong>different</strong> groups.</p>

<p>Return the number of groups that have the largest size, i.e. the <strong>maximum</strong> number of elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 groups [1], [2] of size 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We note that the number does not exceed $10^4$, so the sum of the digits also does not exceed $9 \times 4 = 36$. Therefore, we can use a hash table or an array of length $40$, denoted as $cnt$, to count the number of each sum of digits, and use a variable $mx$ to represent the maximum count of the sum of digits.

We enumerate each number in $[1,..n]$, calculate its sum of digits $s$, then increment $cnt[s]$ by $1$. If $mx < cnt[s]$, we update $mx = cnt[s]$ and set $ans$ to $1$. If $mx = cnt[s]$, we increment $ans$ by $1$.

Finally, we return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the given number.

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
