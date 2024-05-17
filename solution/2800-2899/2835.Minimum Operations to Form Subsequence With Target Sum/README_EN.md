---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2835.Minimum%20Operations%20to%20Form%20Subsequence%20With%20Target%20Sum/README_EN.md
rating: 2207
source: Weekly Contest 360 Q3
tags:
    - Greedy
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [2835. Minimum Operations to Form Subsequence With Target Sum](https://leetcode.com/problems/minimum-operations-to-form-subsequence-with-target-sum)

[中文文档](/solution/2800-2899/2835.Minimum%20Operations%20to%20Form%20Subsequence%20With%20Target%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>non-negative</strong> powers of <code>2</code>, and an integer <code>target</code>.</p>

<p>In one operation, you must apply the following changes to the array:</p>

<ul>
	<li>Choose any element of the array <code>nums[i]</code> such that <code>nums[i] &gt; 1</code>.</li>
	<li>Remove <code>nums[i]</code> from the array.</li>
	<li>Add <strong>two</strong> occurrences of <code>nums[i] / 2</code> to the <strong>end</strong> of <code>nums</code>.</li>
</ul>

<p>Return the <em><strong>minimum number of operations</strong> you need to perform so that </em><code>nums</code><em> contains a <strong>subsequence</strong> whose elements sum to</em> <code>target</code>. If it is impossible to obtain such a subsequence, return <code>-1</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,8], target = 7
<strong>Output:</strong> 1
<strong>Explanation:</strong> In the first operation, we choose element nums[2]. The array becomes equal to nums = [1,2,4,4].
At this stage, nums contains the subsequence [1,2,4] which sums up to 7.
It can be shown that there is no shorter sequence of operations that results in a subsequnce that sums up to 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,32,1,2], target = 12
<strong>Output:</strong> 2
<strong>Explanation:</strong> In the first operation, we choose element nums[1]. The array becomes equal to nums = [1,1,2,16,16].
In the second operation, we choose element nums[3]. The array becomes equal to nums = [1,1,2,16,8,8]
At this stage, nums contains the subsequence [1,1,2,8] which sums up to 12.
It can be shown that there is no shorter sequence of operations that results in a subsequence that sums up to 12.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,32,1], target = 35
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that no sequence of operations results in a subsequence that sums up to 35.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>30</sup></code></li>
	<li><code>nums</code> consists only of non-negative powers of two.</li>
	<li><code>1 &lt;= target &lt; 2<sup>31</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Bit Manipulation

Observing the operation in the problem, we find that each operation actually splits a number greater than $1$ into two equal numbers, which means that the sum of the elements in the array will not change after the operation. Therefore, if the sum of the elements in the array $s$ is less than $target$, it is impossible to obtain a subsequence with a sum of $target$ through the operation described in the problem, and we can directly return $-1$. Otherwise, we can definitely make the sum of some subsequences in the array equal to $target$ through the split operation.

In addition, the split operation will actually set the binary high bit of the number to $0$ and add $2$ to the lower bit. Therefore, we first use an array of length $32$ to record the number of times $1$ appears on each binary bit in the binary representation of all elements in the array $nums$.

Next, starting from the low bit of $target$, for the $i$th bit of $target$, if the current bit number is $0$, skip it directly, that is, $i = i + 1$. If the current bit number is $1$, we need to find the smallest number $j$ (where $j \ge i$) in the array $cnt$ such that $cnt[j] > 0$, and then we split the number $1$ at this bit to the lower bit $i$, that is, subtract $1$ from $cnt[j]$, and set each bit from $i$ to $j-1$ in $cnt$ to $1$, and the number of operations is $j-i$. Next, we let $j = i$, and then $i = i + 1$. Repeat the above operation until $i$ exceeds the index range of the array $cnt$, and return the number of operations at this time.

Note that if $j < i$, actually two lower bits of $1$ can be combined into a higher bit of $1$. Therefore, if $j < i$, we add $\frac{cnt[j]}{2}$ to $cnt[j+1]$, and take $cnt[j]$ modulo $2$, then let $j = j + 1$, and continue the above operation.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $n$ is the length of the array $nums$, and $M$ is the maximum value in the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target:
            return -1
        cnt = [0] * 32
        for x in nums:
            for i in range(32):
                if x >> i & 1:
                    cnt[i] += 1
        i = j = 0
        ans = 0
        while 1:
            while i < 32 and (target >> i & 1) == 0:
                i += 1
            if i == 32:
                break
            while j < i:
                cnt[j + 1] += cnt[j] // 2
                cnt[j] %= 2
                j += 1
            while cnt[j] == 0:
                cnt[j] = 1
                j += 1
            ans += j - i
            cnt[j] -= 1
            j = i
            i += 1
        return ans
```

```java
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        long s = 0;
        int[] cnt = new int[32];
        for (int x : nums) {
            s += x;
            for (int i = 0; i < 32; ++i) {
                if ((x >> i & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        if (s < target) {
            return -1;
        }
        int i = 0, j = 0;
        int ans = 0;
        while (true) {
            while (i < 32 && (target >> i & 1) == 0) {
                ++i;
            }
            if (i == 32) {
                return ans;
            }
            while (j < i) {
                cnt[j + 1] += cnt[j] / 2;
                cnt[j] %= 2;
                ++j;
            }
            while (cnt[j] == 0) {
                cnt[j] = 1;
                ++j;
            }
            ans += j - i;
            --cnt[j];
            j = i;
            ++i;
        }
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int target) {
        long long s = 0;
        int cnt[32]{};
        for (int x : nums) {
            s += x;
            for (int i = 0; i < 32; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        if (s < target) {
            return -1;
        }
        int i = 0, j = 0;
        int ans = 0;
        while (1) {
            while (i < 32 && (target >> i & 1) == 0) {
                ++i;
            }
            if (i == 32) {
                return ans;
            }
            while (j < i) {
                cnt[j + 1] += cnt[j] / 2;
                cnt[j] %= 2;
                ++j;
            }
            while (cnt[j] == 0) {
                cnt[j] = 1;
                ++j;
            }
            ans += j - i;
            --cnt[j];
            j = i;
            ++i;
        }
    }
};
```

```go
func minOperations(nums []int, target int) (ans int) {
	s := 0
	cnt := [32]int{}
	for _, x := range nums {
		s += x
		for i := 0; i < 32; i++ {
			if x>>i&1 > 0 {
				cnt[i]++
			}
		}
	}
	if s < target {
		return -1
	}
	var i, j int
	for {
		for i < 32 && target>>i&1 == 0 {
			i++
		}
		if i == 32 {
			return
		}
		for j < i {
			cnt[j+1] += cnt[j] >> 1
			cnt[j] %= 2
			j++
		}
		for cnt[j] == 0 {
			cnt[j] = 1
			j++
		}
		ans += j - i
		cnt[j]--
		j = i
		i++
	}
}
```

```ts
function minOperations(nums: number[], target: number): number {
    let s = 0;
    const cnt: number[] = Array(32).fill(0);
    for (const x of nums) {
        s += x;
        for (let i = 0; i < 32; ++i) {
            if ((x >> i) & 1) {
                ++cnt[i];
            }
        }
    }
    if (s < target) {
        return -1;
    }
    let [ans, i, j] = [0, 0, 0];
    while (1) {
        while (i < 32 && ((target >> i) & 1) === 0) {
            ++i;
        }
        if (i === 32) {
            return ans;
        }
        while (j < i) {
            cnt[j + 1] += cnt[j] >> 1;
            cnt[j] %= 2;
            ++j;
        }
        while (cnt[j] == 0) {
            cnt[j] = 1;
            j++;
        }
        ans += j - i;
        cnt[j]--;
        j = i;
        i++;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
