---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2150.Find%20All%20Lonely%20Numbers%20in%20the%20Array/README_EN.md
rating: 1275
source: Weekly Contest 277 Q3
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [2150. Find All Lonely Numbers in the Array](https://leetcode.com/problems/find-all-lonely-numbers-in-the-array)

[中文文档](/solution/2100-2199/2150.Find%20All%20Lonely%20Numbers%20in%20the%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. A number <code>x</code> is <strong>lonely</strong> when it appears only <strong>once</strong>, and no <strong>adjacent</strong> numbers (i.e. <code>x + 1</code> and <code>x - 1)</code> appear in the array.</p>

<p>Return <em><strong>all</strong> lonely numbers in </em><code>nums</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,6,5,8]
<strong>Output:</strong> [10,8]
<strong>Explanation:</strong> 
- 10 is a lonely number since it appears exactly once and 9 and 11 does not appear in nums.
- 8 is a lonely number since it appears exactly once and 7 and 9 does not appear in nums.
- 5 is not a lonely number since 6 appears in nums and vice versa.
Hence, the lonely numbers in nums are [10, 8].
Note that [8, 10] may also be returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,3]
<strong>Output:</strong> [1,5]
<strong>Explanation:</strong> 
- 1 is a lonely number since it appears exactly once and 0 and 2 does not appear in nums.
- 5 is a lonely number since it appears exactly once and 4 and 6 does not appear in nums.
- 3 is not a lonely number since it appears twice.
Hence, the lonely numbers in nums are [1, 5].
Note that [5, 1] may also be returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{cnt}$ to record the occurrence count of each number. Then, we iterate through the hash table. For each number and its occurrence count $(x, v)$, if $v = 1$ and $\textit{cnt}[x - 1] = 0$ and $\textit{cnt}[x + 1] = 0$, then $x$ is a lonely number, and we add it to the answer array.

After finishing the iteration, we return the answer array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLonely(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return [
            x for x, v in cnt.items() if v == 1 and cnt[x - 1] == 0 and cnt[x + 1] == 0
        ]
```

#### Java

```java
class Solution {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v == 1 && !cnt.containsKey(x - 1) && !cnt.containsKey(x + 1)) {
                ans.add(x);
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
    vector<int> findLonely(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        vector<int> ans;
        for (auto& [x, v] : cnt) {
            if (v == 1 && !cnt.contains(x - 1) && !cnt.contains(x + 1)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLonely(nums []int) (ans []int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x, v := range cnt {
		if v == 1 && cnt[x-1] == 0 && cnt[x+1] == 0 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findLonely(nums: number[]): number[] {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const ans: number[] = [];
    for (const [x, v] of cnt) {
        if (v === 1 && !cnt.has(x - 1) && !cnt.has(x + 1)) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
