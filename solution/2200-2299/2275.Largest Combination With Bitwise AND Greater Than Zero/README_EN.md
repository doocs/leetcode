---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README_EN.md
rating: 1642
source: Weekly Contest 293 Q3
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [2275. Largest Combination With Bitwise AND Greater Than Zero](https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero)

[中文文档](/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README.md)

## Description

<!-- description:start -->

<p>The <strong>bitwise AND</strong> of an array <code>nums</code> is the bitwise AND of all integers in <code>nums</code>.</p>

<ul>
	<li>For example, for <code>nums = [1, 5, 3]</code>, the bitwise AND is equal to <code>1 &amp; 5 &amp; 3 = 1</code>.</li>
	<li>Also, for <code>nums = [7]</code>, the bitwise AND is <code>7</code>.</li>
</ul>

<p>You are given an array of positive integers <code>candidates</code>. Compute the <strong>bitwise AND</strong> for all possible <strong>combinations</strong> of elements in the <code>candidates</code> array.</p>

<p>Return <em>the size of the <strong>largest</strong> combination of </em><code>candidates</code><em> with a bitwise AND <strong>greater</strong> than </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [16,17,71,62,12,24,14]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The combination [16,17,62,24] has a bitwise AND of 16 &amp; 17 &amp; 62 &amp; 24 = 16 &gt; 0.
The size of the combination is 4.
It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
Note that more than one combination may have the largest size.
For example, the combination [62,12,24,14] has a bitwise AND of 62 &amp; 12 &amp; 24 &amp; 14 = 8 &gt; 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [8,8]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The largest combination [8,8] has a bitwise AND of 8 &amp; 8 = 8 &gt; 0.
The size of the combination is 2, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

The problem requires finding the maximum length of a combination of numbers where the bitwise AND result is greater than $0$. This implies that there must be a certain binary bit where all numbers have a $1$ at that position. Therefore, we can enumerate each binary bit and count the number of $1$s at that bit position for all numbers. Finally, we take the maximum count.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array $\textit{candidates}$ and the maximum value in the array, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        ans = 0
        for i in range(max(candidates).bit_length()):
            ans = max(ans, sum(x >> i & 1 for x in candidates))
        return ans
```

#### Java

```java
class Solution {
    public int largestCombination(int[] candidates) {
        int mx = Arrays.stream(candidates).max().getAsInt();
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestCombination(vector<int>& candidates) {
        int mx = *max_element(candidates.begin(), candidates.end());
        int m = 32 - __builtin_clz(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

#### Go

```go
func largestCombination(candidates []int) (ans int) {
	mx := slices.Max(candidates)
	m := bits.Len(uint(mx))
	for i := 0; i < m; i++ {
		cnt := 0
		for _, x := range candidates {
			cnt += (x >> i) & 1
		}
		ans = max(ans, cnt)
	}
	return
}
```

#### TypeScript

```ts
function largestCombination(candidates: number[]): number {
    const mx = Math.max(...candidates);
    const m = mx.toString(2).length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        let cnt = 0;
        for (const x of candidates) {
            cnt += (x >> i) & 1;
        }
        ans = Math.max(ans, cnt);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
