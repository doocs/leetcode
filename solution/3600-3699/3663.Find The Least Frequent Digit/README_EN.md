---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3663.Find%20The%20Least%20Frequent%20Digit/README_EN.md
rating: 1284
source: Biweekly Contest 164 Q1
tags:
    - Array
    - Hash Table
    - Math
    - Counting
---

<!-- problem:start -->

# [3663. Find The Least Frequent Digit](https://leetcode.com/problems/find-the-least-frequent-digit)

[中文文档](/solution/3600-3699/3663.Find%20The%20Least%20Frequent%20Digit/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, find the digit that occurs <strong>least</strong> frequently in its decimal representation. If multiple digits have the same frequency, choose the <strong>smallest</strong> digit.</p>

<p>Return the chosen digit as an integer.</p>
The <strong>frequency</strong> of a digit <code>x</code> is the number of times it appears in the decimal representation of <code>n</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1553322</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>The least frequent digit in <code>n</code> is 1, which appears only once. All other digits appear twice.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 723344511</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p>The least frequent digits in <code>n</code> are 7, 2, and 5; each appears only once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup>​​​​​​​ - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We use an array $\textit{cnt}$ to count the frequency of each digit. We iterate through each digit of the number $n$ and update the $\textit{cnt}$ array.

Then, we use a variable $f$ to record the current lowest frequency among the digits, and a variable $\textit{ans}$ to record the corresponding digit.

Next, we iterate through the $\textit{cnt}$ array. If $0 < \textit{cnt}[x] < f$, it means we have found a digit with a lower frequency, so we update $f = \textit{cnt}[x]$ and $\textit{ans} = x$.

After the iteration, we return $\textit{ans}$ as the answer.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLeastFrequentDigit(self, n: int) -> int:
        cnt = [0] * 10
        while n:
            n, x = divmod(n, 10)
            cnt[x] += 1
        ans, f = 0, inf
        for x, v in enumerate(cnt):
            if 0 < v < f:
                f = v
                ans = x
        return ans
```

#### Java

```java
class Solution {
    public int getLeastFrequentDigit(int n) {
        int[] cnt = new int[10];
        for (; n > 0; n /= 10) {
            ++cnt[n % 10];
        }
        int ans = 0, f = 1 << 30;
        for (int x = 0; x < 10; ++x) {
            if (cnt[x] > 0 && cnt[x] < f) {
                f = cnt[x];
                ans = x;
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
    int getLeastFrequentDigit(int n) {
        int cnt[10]{};
        for (; n > 0; n /= 10) {
            ++cnt[n % 10];
        }
        int ans = 0, f = 1 << 30;
        for (int x = 0; x < 10; ++x) {
            if (cnt[x] > 0 && cnt[x] < f) {
                f = cnt[x];
                ans = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLeastFrequentDigit(n int) (ans int) {
	cnt := [10]int{}
	for ; n > 0; n /= 10 {
		cnt[n%10]++
	}
	f := 1 << 30
	for x, v := range cnt {
		if v > 0 && v < f {
			f = v
			ans = x
		}
	}
	return
}
```

#### TypeScript

```ts
function getLeastFrequentDigit(n: number): number {
    const cnt: number[] = Array(10).fill(0);
    for (; n; n = (n / 10) | 0) {
        cnt[n % 10]++;
    }
    let [ans, f] = [0, Number.MAX_SAFE_INTEGER];
    for (let x = 0; x < 10; ++x) {
        if (cnt[x] > 0 && cnt[x] < f) {
            f = cnt[x];
            ans = x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
