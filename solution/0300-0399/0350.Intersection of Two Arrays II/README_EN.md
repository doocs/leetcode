---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README_EN.md
tags:
    - Array
    - Hash Table
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii)

[中文文档](/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README.md)

## Description

<!-- description:start -->

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>an array of their intersection</em>. Each element in the result must appear as many times as it shows in both arrays and you may return the result in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2,1], nums2 = [2,2]
<strong>Output:</strong> [2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>Output:</strong> [4,9]
<strong>Explanation:</strong> [9,4] is also accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>What if the given array is already sorted? How would you optimize your algorithm?</li>
	<li>What if <code>nums1</code>&#39;s size is small compared to <code>nums2</code>&#39;s size? Which algorithm is better?</li>
	<li>What if elements of <code>nums2</code> are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\text{cnt}$ to count the occurrences of each element in the array $\text{nums1}$. Then, we iterate through the array $\text{nums2}$. If an element $x$ is in $\text{cnt}$ and the occurrence of $x$ is greater than $0$, we add $x$ to the answer and then decrement the occurrence of $x$ by one.

After the iteration is finished, we return the answer array.

The time complexity is $O(m + n)$, and the space complexity is $O(m)$. Here, $m$ and $n$ are the lengths of the arrays $\text{nums1}$ and $\text{nums2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        cnt = Counter(nums1)
        ans = []
        for x in nums2:
            if cnt[x]:
                ans.append(x)
                cnt[x] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] cnt = new int[1001];
        for (int x : nums1) {
            ++cnt[x];
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                ans.add(x);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> cnt;
        for (int x : nums1) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intersect(nums1 []int, nums2 []int) (ans []int) {
	cnt := map[int]int{}
	for _, x := range nums1 {
		cnt[x]++
	}
	for _, x := range nums2 {
		if cnt[x] > 0 {
			ans = append(ans, x)
			cnt[x]--
		}
	}
	return
}
```

#### TypeScript

```ts
function intersect(nums1: number[], nums2: number[]): number[] {
    const cnt: Record<number, number> = {};
    for (const x of nums1) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const ans: number[] = [];
    for (const x of nums2) {
        if (cnt[x]-- > 0) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for &x in &nums1 {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = Vec::new();
        for &x in &nums2 {
            if let Some(count) = cnt.get_mut(&x) {
                if *count > 0 {
                    ans.push(x);
                    *count -= 1;
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function (nums1, nums2) {
    const cnt = {};
    for (const x of nums1) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const ans = [];
    for (const x of nums2) {
        if (cnt[x]-- > 0) {
            ans.push(x);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int[] Intersect(int[] nums1, int[] nums2) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        foreach (int x in nums1) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
        }
        List<int> ans = new List<int>();
        foreach (int x in nums2) {
            if (cnt.ContainsKey(x) && cnt[x] > 0) {
                ans.Add(x);
                cnt[x]--;
            }
        }
        return ans.ToArray();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersect($nums1, $nums2) {
        $cnt = [];
        foreach ($nums1 as $x) {
            if (isset($cnt[$x])) {
                $cnt[$x]++;
            } else {
                $cnt[$x] = 1;
            }
        }

        $ans = [];
        foreach ($nums2 as $x) {
            if (isset($cnt[$x]) && $cnt[$x] > 0) {
                $ans[] = $x;
                $cnt[$x]--;
            }
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
