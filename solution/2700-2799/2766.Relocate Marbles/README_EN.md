---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2766.Relocate%20Marbles/README_EN.md
rating: 1613
source: Biweekly Contest 108 Q2
tags:
    - Array
    - Hash Table
    - Sorting
    - Simulation
---

# [2766. Relocate Marbles](https://leetcode.com/problems/relocate-marbles)

[中文文档](/solution/2700-2799/2766.Relocate%20Marbles/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> representing the initial positions of some marbles. You are also given two <strong>0-indexed </strong>integer arrays <code>moveFrom</code> and <code>moveTo</code> of <strong>equal</strong> length.</p>

<p>Throughout <code>moveFrom.length</code> steps, you will change the positions of the marbles. On the <code>i<sup>th</sup></code> step, you will move <strong>all</strong> marbles at position <code>moveFrom[i]</code> to position <code>moveTo[i]</code>.</p>

<p>After completing all the steps, return <em>the sorted list of <strong>occupied</strong> positions</em>.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li>We call a position <strong>occupied</strong> if there is at least one marble in that position.</li>
	<li>There may be multiple marbles in a single position.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,6,7,8], moveFrom = [1,7,2], moveTo = [2,9,5]
<strong>Output:</strong> [5,6,8,9]
<strong>Explanation:</strong> Initially, the marbles are at positions 1,6,7,8.
At the i = 0th step, we move the marbles at position 1 to position 2. Then, positions 2,6,7,8 are occupied.
At the i = 1st step, we move the marbles at position 7 to position 9. Then, positions 2,6,8,9 are occupied.
At the i = 2nd step, we move the marbles at position 2 to position 5. Then, positions 5,6,8,9 are occupied.
At the end, the final positions containing at least one marbles are [5,6,8,9].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,3,3], moveFrom = [1,3], moveTo = [2,2]
<strong>Output:</strong> [2]
<strong>Explanation:</strong> Initially, the marbles are at positions [1,1,3,3].
At the i = 0th step, we move all the marbles at position 1 to position 2. Then, the marbles are at positions [2,2,3,3].
At the i = 1st step, we move all the marbles at position 3 to position 2. Then, the marbles are at positions [2,2,2,2].
Since 2 is the only occupied position, we return [2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= moveFrom.length &lt;= 10<sup>5</sup></code></li>
	<li><code>moveFrom.length == moveTo.length</code></li>
	<li><code>1 &lt;= nums[i], moveFrom[i], moveTo[i] &lt;= 10<sup>9</sup></code></li>
	<li>The test cases are generated such that there is at least a marble in&nbsp;<code>moveFrom[i]</code>&nbsp;at the moment we want to apply&nbsp;the <code>i<sup>th</sup></code>&nbsp;move.</li>
</ul>

## Solutions

### Solution 1: Hash Table

Let's use a hash table $pos$ to record all stone positions. Initially, $pos$ contains all elements of $nums$. Then we iterate through $moveFrom$ and $moveTo$. Each time, we remove $moveFrom[i]$ from $pos$ and add $moveTo[i]$ to $pos$. Finally, we sort the elements in $pos$ and return.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$. Here, $n$ is the length of array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def relocateMarbles(
        self, nums: List[int], moveFrom: List[int], moveTo: List[int]
    ) -> List[int]:
        pos = set(nums)
        for f, t in zip(moveFrom, moveTo):
            pos.remove(f)
            pos.add(t)
        return sorted(pos)
```

```java
class Solution {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> pos = new HashSet<>();
        for (int x : nums) {
            pos.add(x);
        }
        for (int i = 0; i < moveFrom.length; ++i) {
            pos.remove(moveFrom[i]);
            pos.add(moveTo[i]);
        }
        List<Integer> ans = new ArrayList<>(pos);
        ans.sort((a, b) -> a - b);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> relocateMarbles(vector<int>& nums, vector<int>& moveFrom, vector<int>& moveTo) {
        unordered_set<int> pos(nums.begin(), nums.end());
        for (int i = 0; i < moveFrom.size(); ++i) {
            pos.erase(moveFrom[i]);
            pos.insert(moveTo[i]);
        }
        vector<int> ans(pos.begin(), pos.end());
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func relocateMarbles(nums []int, moveFrom []int, moveTo []int) (ans []int) {
	pos := map[int]bool{}
	for _, x := range nums {
		pos[x] = true
	}
	for i, f := range moveFrom {
		t := moveTo[i]
		pos[f] = false
		pos[t] = true
	}
	for x, ok := range pos {
		if ok {
			ans = append(ans, x)
		}
	}
	sort.Ints(ans)
	return
}
```

```ts
function relocateMarbles(nums: number[], moveFrom: number[], moveTo: number[]): number[] {
    const pos: Set<number> = new Set(nums);
    for (let i = 0; i < moveFrom.length; i++) {
        pos.delete(moveFrom[i]);
        pos.add(moveTo[i]);
    }
    const ans = [...pos];
    ans.sort((a, b) => a - b);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
