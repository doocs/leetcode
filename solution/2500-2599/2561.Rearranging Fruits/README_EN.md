---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2561.Rearranging%20Fruits/README_EN.md
rating: 2221
source: Weekly Contest 331 Q4
tags:
    - Greedy
    - Sort
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2561. Rearranging Fruits](https://leetcode.com/problems/rearranging-fruits)

[中文文档](/solution/2500-2599/2561.Rearranging%20Fruits/README.md)

## Description

<!-- description:start -->

<p>You have two fruit baskets containing <code>n</code> fruits each. You are given two <strong>0-indexed</strong> integer arrays <code>basket1</code> and <code>basket2</code> representing the cost of fruit in each basket. You want to make both baskets <strong>equal</strong>. To do so, you can use the following operation as many times as you want:</p>

<ul>
	<li>Choose two indices <code>i</code> and <code>j</code>, and swap the <code>i<sup><font size="1">th</font></sup></code> fruit of <code>basket1</code> with the <code>j<sup><font size="1">th</font></sup></code> fruit of <code>basket2</code>.</li>
	<li>The cost of the swap is <code>min(basket1[i], basket2[j])</code>.</li>
</ul>

<p>Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.</p>

<p>Return <em>the minimum cost to make both the baskets equal or </em><code>-1</code><em> if impossible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> basket1 = [4,2,2,2], basket2 = [1,4,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> basket1 = [2,3,4,1], basket2 = [3,2,5,1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that it is impossible to make both the baskets equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>basket1.length == basket2.length</code></li>
	<li><code>1 &lt;= basket1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= basket1[i], basket2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Construction

First, we can remove the common elements from both arrays. For the remaining numbers, the occurrence of each number must be even, otherwise, it is impossible to construct identical arrays. Let's denote the arrays after removing common elements as $a$ and $b$.

Next, we consider how to perform the swaps.

If we want to swap the smallest number in $a$, we need to find the largest number in $b$ to swap with it; similarly, if we want to swap the smallest number in $b$, we need to find the largest number in $a$ to swap with it. This can be achieved by sorting.

However, there is another swapping scheme. We can use a smallest number $mi$ as a transition element, first swap the number in $a$ with $mi$, and then swap $mi$ with the number in $b$. In this way, the cost of swapping is $2 \times mi$.

In the code implementation, we can directly merge arrays $a$ and $b$ into an array $nums$, and then sort the array $nums$. Next, we enumerate the first half of the numbers, calculate the minimum cost each time, and add it to the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, basket1: List[int], basket2: List[int]) -> int:
        cnt = Counter()
        for a, b in zip(basket1, basket2):
            cnt[a] += 1
            cnt[b] -= 1
        mi = min(cnt)
        nums = []
        for x, v in cnt.items():
            if v % 2:
                return -1
            nums.extend([x] * (abs(v) // 2))
        nums.sort()
        m = len(nums) // 2
        return sum(min(x, mi * 2) for x in nums[:m])
```

#### Java

```java
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            cnt.merge(basket1[i], 1, Integer::sum);
            cnt.merge(basket2[i], -1, Integer::sum);
        }
        int mi = 1 << 30;
        List<Integer> nums = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v % 2 != 0) {
                return -1;
            }
            for (int i = Math.abs(v) / 2; i > 0; --i) {
                nums.add(x);
            }
            mi = Math.min(mi, x);
        }
        Collections.sort(nums);
        int m = nums.size();
        long ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            ans += Math.min(nums.get(i), mi * 2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(vector<int>& basket1, vector<int>& basket2) {
        int n = basket1.size();
        unordered_map<int, int> cnt;
        for (int i = 0; i < n; ++i) {
            cnt[basket1[i]]++;
            cnt[basket2[i]]--;
        }
        int mi = 1 << 30;
        vector<int> nums;
        for (auto& [x, v] : cnt) {
            if (v % 2) {
                return -1;
            }
            for (int i = abs(v) / 2; i; --i) {
                nums.push_back(x);
            }
            mi = min(mi, x);
        }
        ranges::sort(nums);
        int m = nums.size();
        long long ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            ans += min(nums[i], mi * 2);
        }
        return ans;
    }
};
```

#### Go

```go
func minCost(basket1 []int, basket2 []int) (ans int64) {
	cnt := map[int]int{}
	for i, a := range basket1 {
		cnt[a]++
		cnt[basket2[i]]--
	}
	mi := 1 << 30
	nums := []int{}
	for x, v := range cnt {
		if v%2 != 0 {
			return -1
		}
		for i := abs(v) / 2; i > 0; i-- {
			nums = append(nums, x)
		}
		mi = min(mi, x)
	}
	sort.Ints(nums)
	m := len(nums)
	for i := 0; i < m/2; i++ {
		ans += int64(min(nums[i], mi*2))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minCost(basket1: number[], basket2: number[]): number {
    const n = basket1.length;
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < n; i++) {
        cnt.set(basket1[i], (cnt.get(basket1[i]) || 0) + 1);
        cnt.set(basket2[i], (cnt.get(basket2[i]) || 0) - 1);
    }
    let mi = Number.MAX_SAFE_INTEGER;
    const nums: number[] = [];
    for (const [x, v] of cnt.entries()) {
        if (v % 2 !== 0) {
            return -1;
        }
        for (let i = 0; i < Math.abs(v) / 2; i++) {
            nums.push(x);
        }
        mi = Math.min(mi, x);
    }

    nums.sort((a, b) => a - b);
    const m = nums.length;
    let ans = 0;
    for (let i = 0; i < m / 2; i++) {
        ans += Math.min(nums[i], mi * 2);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_cost(basket1: Vec<i32>, basket2: Vec<i32>) -> i64 {
        let n = basket1.len();
        let mut cnt: HashMap<i32, i32> = HashMap::new();

        for i in 0..n {
            *cnt.entry(basket1[i]).or_insert(0) += 1;
            *cnt.entry(basket2[i]).or_insert(0) -= 1;
        }

        let mut mi = i32::MAX;
        let mut nums = Vec::new();

        for (x, v) in cnt {
            if v % 2 != 0 {
                return -1;
            }
            for _ in 0..(v.abs() / 2) {
                nums.push(x);
            }
            mi = mi.min(x);
        }

        nums.sort();

        let m = nums.len();
        let mut ans = 0;

        for i in 0..(m / 2) {
            ans += nums[i].min(mi * 2) as i64;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
