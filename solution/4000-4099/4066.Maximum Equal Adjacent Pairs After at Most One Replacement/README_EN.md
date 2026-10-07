---
comments: true
difficulty: Medium
rating: 1570
source: Weekly Contest 521 Q2
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [4066. Maximum Equal Adjacent Pairs After at Most One Replacement](https://leetcode.com/problems/maximum-equal-adjacent-pairs-after-at-most-one-replacement)

[中文文档](/solution/4000-4099/4066.Maximum%20Equal%20Adjacent%20Pairs%20After%20at%20Most%20One%20Replacement/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>1-indexed</strong> integer array <code>nums</code>.</p>

<p>You can choose two <strong>distinct</strong> values <code>x</code> and <code>y</code> and perform the following operation <strong>at most</strong> once:</p>

<ul>
	<li>Replace every occurrence of <code>x</code> in <code>nums</code> with <code>y</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> possible number of pairs of adjacent elements that are equal after performing the operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal solution is to choose <code>x = 3</code> and <code>y = 2</code>.</li>
	<li>The resulting array is <code>[1, 2, 2, 2]</code>.</li>
	<li>There are 2 pairs of adjacent elements that are equal: <code>(nums[2], nums[3])</code> and <code>(nums[3], nums[4])</code>.</li>
	<li>Therefore, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal solution is to choose <code>x = 1</code> and <code>y = 2</code>.</li>
	<li>The resulting array is <code>[2, 2, 2, 2, 2]</code>.</li>
	<li>There are 4 pairs of adjacent elements that are equal: <code>(nums[1], nums[2])</code>, <code>(nums[2], nums[3])</code>, <code>(nums[3], nums[4])</code>, and <code>(nums[4], nums[5])</code>.</li>
	<li>Therefore, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One optimal solution is to perform no operation.</li>
	<li>Thus, the resulting array is <code>[1, 1, 1]</code>.</li>
	<li>There are 2 pairs of adjacent elements that are equal: <code>(nums[1], nums[2])</code> and <code>(nums[2], nums[3])</code>.</li>
	<li>Therefore, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

<!-- thinking:start -->

> **Thinking**
>
> $n$ can be $10^5$ and values can be $10^9$. Trying every distinct pair $x,y$, replacing, and recounting adjacent equals is too many candidates.
>
> An adjacent pair that is already equal stays equal no matter which value is replaced by another. One replacement only equalizes adjacent positions whose values are exactly that pair $x,y$. A different unordered pair is a different operation.
>
> Count the adjacent positions that are already equal, then count unequal adjacent positions by unordered pair, and add the largest of those counts. Doing nothing corresponds to a maximum of $0$.

<!-- thinking:end -->

Adjacent positions that are already equal stay equal after any replacement: if both hold $x$, both become $y$, and every other value is unchanged. Let $\textit{ans}$ be the number of such pairs.

One operation picks two distinct values and replaces every occurrence of one with the other. An adjacent pair that newly becomes equal must already have been exactly those two values. For an unequal adjacent pair $x,y$, place the smaller value first and encode

$$
\textit{key}=(x\ll 30)\mid y.
$$

Since $x,y\le 10^9$, the key fits in a $64$-bit integer. $\textit{cnt}[\textit{key}]$ is how often that pair occurs in adjacent positions. Over every candidate operation, the number of newly equal adjacent pairs is the maximum of these counts, $\textit{mx}$. Skipping the operation leaves $\textit{mx}=0$. The answer is $\textit{ans}+\textit{mx}$.

The time complexity is $O(n)$ and the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEqualAdjacentPairs(self, nums: list[int]) -> int:
        cnt = defaultdict(int)
        ans = mx = 0
        for x, y in pairwise(nums):
            if x == y:
                ans += 1
            else:
                if x > y:
                    x, y = y, x
                key = x << 30 | y
                cnt[key] += 1
                mx = max(mx, cnt[key])
        ans += mx
        return ans
```

#### Java

```java
class Solution {
    public int maxEqualAdjacentPairs(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        int ans = 0, mx = 0;

        for (int i = 0; i + 1 < nums.length; i++) {
            int x = nums[i], y = nums[i + 1];
            if (x == y) {
                ans++;
            } else {
                if (x > y) {
                    int t = x;
                    x = y;
                    y = t;
                }
                long key = ((long) x << 30) | y;
                int v = cnt.merge(key, 1, Integer::sum);
                mx = Math.max(mx, v);
            }
        }
        ans += mx;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxEqualAdjacentPairs(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        int ans = 0, mx = 0;

        for (int i = 0; i + 1 < nums.size(); i++) {
            int x = nums[i], y = nums[i + 1];
            if (x == y) {
                ans++;
            } else {
                if (x > y) {
                    swap(x, y);
                }
                long long key = ((long long) x << 30) | y;
                mx = max(mx, ++cnt[key]);
            }
        }
        ans += mx;
        return ans;
    }
};
```

#### Go

```go
func maxEqualAdjacentPairs(nums []int) int {
	cnt := map[int64]int{}
	ans, mx := 0, 0

	for i := 0; i+1 < len(nums); i++ {
		x, y := nums[i], nums[i+1]
		if x == y {
			ans++
		} else {
			if x > y {
				x, y = y, x
			}
			key := int64(x)<<30 | int64(y)
			cnt[key]++
			if cnt[key] > mx {
				mx = cnt[key]
			}
		}
	}
	ans += mx
	return ans
}
```

#### TypeScript

```ts
function maxEqualAdjacentPairs(nums: number[]): number {
    const cnt = new Map<bigint, number>();
    let ans = 0;
    let mx = 0;

    for (let i = 0; i + 1 < nums.length; i++) {
        let x = nums[i];
        let y = nums[i + 1];
        if (x === y) {
            ans++;
        } else {
            if (x > y) {
                [x, y] = [y, x];
            }
            const key = (BigInt(x) << 30n) | BigInt(y);
            cnt.set(key, (cnt.get(key) || 0) + 1);
            mx = Math.max(mx, cnt.get(key)!);
        }
    }
    ans += mx;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
