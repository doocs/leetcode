---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2910.Minimum%20Number%20of%20Groups%20to%20Create%20a%20Valid%20Assignment/README_EN.md
rating: 2132
tags:
    - Greedy
    - Array
    - Hash Table
---

# [2910. Minimum Number of Groups to Create a Valid Assignment](https://leetcode.com/problems/minimum-number-of-groups-to-create-a-valid-assignment)

[中文文档](/solution/2900-2999/2910.Minimum%20Number%20of%20Groups%20to%20Create%20a%20Valid%20Assignment/README.md)

## Description

<p>You are given a collection of numbered <code>balls</code>&nbsp;and instructed to sort them into boxes for a nearly balanced distribution. There are two rules you must follow:</p>

<ul>
	<li>Balls with the same&nbsp;box must have the same value. But, if you have more than one ball with the same number, you can put them in different boxes.</li>
	<li>The biggest box can only have one more ball than the smallest box.</li>
</ul>

<p>​Return the <em>fewest number of boxes</em> to sort these balls following these rules.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> balls = [3,2,3,2,3] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 2 </span></p>

<p><strong>Explanation:</strong></p>

<p>We can sort <code>balls</code> into boxes as follows:</p>

<ul>
	<li><code>[3,3,3]</code></li>
	<li><code>[2,2]</code></li>
</ul>

<p>The size difference between the two boxes doesn&#39;t exceed one.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> balls = [10,10,10,3,1,1] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 4 </span></p>

<p><strong>Explanation:</strong></p>

<p>We can sort <code>balls</code> into boxes as follows:</p>

<ul>
</ul>

<ul>
	<li><code>[10]</code></li>
	<li><code>[10,10]</code></li>
	<li><code>[3]</code></li>
	<li><code>[1,1]</code></li>
</ul>

<p>You can&#39;t use fewer than four boxes while still following the rules. For example, putting all three balls numbered 10 in one box would break the rule about the maximum size difference between boxes.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Enumeration

We use a hash table $cnt$ to count the number of occurrences of each number in the array $nums$. Let $k$ be the minimum value of the number of occurrences, and then we can enumerate the size of the groups in the range $[k,..1]$. Since the difference in size between each group is not more than $1$, the group size can be either $k$ or $k+1$.

For the current group size $k$ being enumerated, we traverse each occurrence $v$ in the hash table. If $\lfloor \frac{v}{k} \rfloor < v \bmod k$, it means that we cannot divide the occurrence $v$ into $k$ or $k+1$ groups with the same value, so we can skip this group size $k$ directly. Otherwise, it means that we can form groups, and we only need to form as many groups of size $k+1$ as possible to ensure the minimum number of groups. Therefore, we can divide $v$ numbers into $\lceil \frac{v}{k+1} \rceil$ groups and add them to the current enumerated answer. Since we enumerate $k$ from large to small, as long as we find a valid grouping scheme, it must be optimal.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minGroupsForValidAssignment(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        for k in range(min(cnt.values()), 0, -1):
            ans = 0
            for v in cnt.values():
                if v // k < v % k:
                    ans = 0
                    break
                ans += (v + k) // (k + 1)
            if ans:
                return ans
```

```java
class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int k = nums.length;
        for (int v : cnt.values()) {
            k = Math.min(k, v);
        }
        for (;; --k) {
            int ans = 0;
            for (int v : cnt.values()) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans > 0) {
                return ans;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int minGroupsForValidAssignment(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        int k = 1e9;
        for (auto& [_, v] : cnt) {
            ans = min(ans, v);
        }
        for (;; --k) {
            int ans = 0;
            for (auto& [_, v] : cnt) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans) {
                return ans;
            }
        }
    }
};
```

```go
func minGroupsForValidAssignment(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	k := len(nums)
	for _, v := range cnt {
		k = min(k, v)
	}
	for ; ; k-- {
		ans := 0
		for _, v := range cnt {
			if v/k < v%k {
				ans = 0
				break
			}
			ans += (v + k) / (k + 1)
		}
		if ans > 0 {
			return ans
		}
	}
}
```

```ts
function minGroupsForValidAssignment(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    for (let k = Math.min(...cnt.values()); ; --k) {
        let ans = 0;
        for (const [_, v] of cnt) {
            if (((v / k) | 0) < v % k) {
                ans = 0;
                break;
            }
            ans += Math.ceil(v / (k + 1));
        }
        if (ans) {
            return ans;
        }
    }
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_groups_for_valid_assignment(nums: Vec<i32>) -> i32 {
        let mut cnt: HashMap<i32, i32> = HashMap::new();

        for x in nums.iter() {
            let count = cnt.entry(*x).or_insert(0);
            *count += 1;
        }

        let mut k = i32::MAX;

        for &v in cnt.values() {
            k = k.min(v);
        }

        for k in (1..=k).rev() {
            let mut ans = 0;

            for &v in cnt.values() {
                if v / k < v % k {
                    ans = 0;
                    break;
                }

                ans += (v + k) / (k + 1);
            }

            if ans > 0 {
                return ans;
            }
        }

        0
    }
}
```

<!-- tabs:end -->

<!-- end -->
