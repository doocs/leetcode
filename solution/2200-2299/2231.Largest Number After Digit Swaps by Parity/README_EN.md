---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README_EN.md
rating: 1365
source: Weekly Contest 288 Q1
tags:
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2231. Largest Number After Digit Swaps by Parity](https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity)

[中文文档](/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>num</code>. You may swap any two digits of <code>num</code> that have the same <strong>parity</strong> (i.e. both odd digits or both even digits).</p>

<p>Return<em> the <strong>largest</strong> possible value of </em><code>num</code><em> after <strong>any</strong> number of swaps.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 1234
<strong>Output:</strong> 3412
<strong>Explanation:</strong> Swap the digit 3 with the digit 1, this results in the number 3214.
Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 65875
<strong>Output:</strong> 87655
<strong>Explanation:</strong> Swap the digit 8 with the digit 6, this results in the number 85675.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use an array $\textit{cnt}$ of length $10$ to count the occurrences of each digit in the integer $\textit{num}$. We also use an index array $\textit{idx}$ to record the largest available even and odd digits, initially set to $[8, 9]$.

Next, we traverse each digit of the integer $\textit{num}$. If the digit is odd, we take the digit corresponding to index $1$ in $\textit{idx}$; otherwise, we take the digit corresponding to index $0$. If the count of the digit is $0$, we decrement the digit by $2$ and continue checking until we find a digit that meets the condition. Then, we update the answer and the count of the digit, and continue traversing until we have processed all digits of the integer $\textit{num}$.

The time complexity is $O(\log \textit{num})$, and the space complexity is $O(\log \textit{num})$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, num: int) -> int:
        nums = [int(c) for c in str(num)]
        cnt = Counter(nums)
        idx = [8, 9]
        ans = 0
        for x in nums:
            while cnt[idx[x & 1]] == 0:
                idx[x & 1] -= 2
            ans = ans * 10 + idx[x & 1]
            cnt[idx[x & 1]] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int largestInteger(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int[] cnt = new int[10];
        for (char c : s) {
            ++cnt[c - '0'];
        }
        int[] idx = {8, 9};
        int ans = 0;
        for (char c : s) {
            int x = c - '0';
            while (cnt[idx[x & 1]] == 0) {
                idx[x & 1] -= 2;
            }
            ans = ans * 10 + idx[x & 1];
            cnt[idx[x & 1]]--;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(int num) {
        string s = to_string(num);
        int cnt[10] = {0};
        for (char c : s) {
            cnt[c - '0']++;
        }
        int idx[2] = {8, 9};
        int ans = 0;
        for (char c : s) {
            int x = c - '0';
            while (cnt[idx[x & 1]] == 0) {
                idx[x & 1] -= 2;
            }
            ans = ans * 10 + idx[x & 1];
            cnt[idx[x & 1]]--;
        }
        return ans;
    }
};
```

#### Go

```go
func largestInteger(num int) int {
	s := []byte(fmt.Sprint(num))
	cnt := [10]int{}

	for _, c := range s {
		cnt[c-'0']++
	}

	idx := [2]int{8, 9}
	ans := 0

	for _, c := range s {
		x := int(c - '0')
		for cnt[idx[x&1]] == 0 {
			idx[x&1] -= 2
		}
		ans = ans*10 + idx[x&1]
		cnt[idx[x&1]]--
	}

	return ans
}
```

#### TypeScript

```ts
function largestInteger(num: number): number {
    const s = num.toString().split('');
    const cnt = Array(10).fill(0);

    for (const c of s) {
        cnt[+c]++;
    }

    const idx = [8, 9];
    let ans = 0;

    for (const c of s) {
        const x = +c;
        while (cnt[idx[x % 2]] === 0) {
            idx[x % 2] -= 2;
        }
        ans = ans * 10 + idx[x % 2];
        cnt[idx[x % 2]]--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
