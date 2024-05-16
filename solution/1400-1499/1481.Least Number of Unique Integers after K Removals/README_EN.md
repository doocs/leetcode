---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1481.Least%20Number%20of%20Unique%20Integers%20after%20K%20Removals/README_EN.md
rating: 1284
source: Weekly Contest 193 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Counting
    - Sorting
---

# [1481. Least Number of Unique Integers after K Removals](https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals)

[中文文档](/solution/1400-1499/1481.Least%20Number%20of%20Unique%20Integers%20after%20K%20Removals/README.md)

## Description

<p>Given an array of integers&nbsp;<code>arr</code>&nbsp;and an integer <code>k</code>.&nbsp;Find the <em>least number of unique integers</em>&nbsp;after removing <strong>exactly</strong> <code>k</code> elements<b>.</b></p>

<ol>

</ol>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input: </strong>arr = [5,5,4], k = 1

<strong>Output: </strong>1

<strong>Explanation</strong>: Remove the single 4, only 5 is left.

</pre>

<strong class="example">Example 2:</strong>

<pre>

<strong>Input: </strong>arr = [4,3,1,1,3,3,2], k = 3

<strong>Output: </strong>2

<strong>Explanation</strong>: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= arr.length&nbsp;&lt;= 10^5</code></li>

    <li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>

    <li><code>0 &lt;= k&nbsp;&lt;= arr.length</code></li>

</ul>

## Solutions

### Solution 1: Hash Table + Sorting

We use the hash table $cnt$ to count the number of times each integer in the array $arr$ appears, and then sort the values in $cnt$ in ascending order, and record them in the array $nums$.

Next, we traverse the array $nums$. For the current value that we traverse to $nums[i]$, we subtract $k$ by $nums[i]$. If $k \lt 0$, it means that we have removed $k$ elements, and the minimum number of different integers in the array is the length of $nums$ minus the index $i$ that we traverse to at the current time. Return directly.

If we traverse to the end, it means that we have removed all the elements, and the minimum number of different integers in the array is $0$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $arr$.

<!-- tabs:start -->

```python
class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        cnt = Counter(arr)
        for i, v in enumerate(sorted(cnt.values())):
            k -= v
            if k < 0:
                return len(cnt) - i
        return 0
```

```java
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) {
            cnt.merge(x, 1, Integer::sum);
        }
        List<Integer> nums = new ArrayList<>(cnt.values());
        Collections.sort(nums);
        for (int i = 0, m = nums.size(); i < m; ++i) {
            k -= nums.get(i);
            if (k < 0) {
                return m - i;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& arr, int k) {
        unordered_map<int, int> cnt;
        for (int& x : arr) {
            ++cnt[x];
        }
        vector<int> nums;
        for (auto& [_, c] : cnt) {
            nums.push_back(c);
        }
        sort(nums.begin(), nums.end());
        for (int i = 0, m = nums.size(); i < m; ++i) {
            k -= nums[i];
            if (k < 0) {
                return m - i;
            }
        }
        return 0;
    }
};
```

```go
func findLeastNumOfUniqueInts(arr []int, k int) int {
	cnt := map[int]int{}
	for _, x := range arr {
		cnt[x]++
	}
	nums := make([]int, 0, len(cnt))
	for _, v := range cnt {
		nums = append(nums, v)
	}
	sort.Ints(nums)
	for i, v := range nums {
		k -= v
		if k < 0 {
			return len(nums) - i
		}
	}
	return 0
}
```

```ts
function findLeastNumOfUniqueInts(arr: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of arr) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const nums: number[] = [];
    for (const [_, v] of cnt) {
        nums.push(v);
    }
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length; ++i) {
        k -= nums[i];
        if (k < 0) {
            return nums.length - i;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- end -->
