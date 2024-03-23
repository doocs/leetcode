# [2215. Find the Difference of Two Arrays](https://leetcode.com/problems/find-the-difference-of-two-arrays)

[中文文档](/solution/2200-2299/2215.Find%20the%20Difference%20of%20Two%20Arrays/README.md)

<!-- tags:Array,Hash Table -->

## Description

<p>Given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, return <em>a list</em> <code>answer</code> <em>of size</em> <code>2</code> <em>where:</em></p>

<ul>
	<li><code>answer[0]</code> <em>is a list of all <strong>distinct</strong> integers in</em> <code>nums1</code> <em>which are <strong>not</strong> present in</em> <code>nums2</code><em>.</em></li>
	<li><code>answer[1]</code> <em>is a list of all <strong>distinct</strong> integers in</em> <code>nums2</code> <em>which are <strong>not</strong> present in</em> <code>nums1</code>.</li>
</ul>

<p><strong>Note</strong> that the integers in the lists may be returned in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3], nums2 = [2,4,6]
<strong>Output:</strong> [[1,3],[4,6]]
<strong>Explanation:
</strong>For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,3], nums2 = [1,1,2,2]
<strong>Output:</strong> [[3],[]]
<strong>Explanation:
</strong>For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Hash Table

We define two hash tables $s1$ and $s2$ to store the elements in arrays $nums1$ and $nums2$ respectively. Then we traverse each element in $s1$. If this element is not in $s2$, we add it to the first list in the answer. Similarly, we traverse each element in $s2$. If this element is not in $s1$, we add it to the second list in the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def findDifference(self, nums1: List[int], nums2: List[int]) -> List[List[int]]:
        s1, s2 = set(nums1), set(nums2)
        return [list(s1 - s2), list(s2 - s1)]
```

```java
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = convert(nums1);
        Set<Integer> s2 = convert(nums2);
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int v : s1) {
            if (!s2.contains(v)) {
                l1.add(v);
            }
        }
        for (int v : s2) {
            if (!s1.contains(v)) {
                l2.add(v);
            }
        }
        return List.of(l1, l2);
    }

    private Set<Integer> convert(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> findDifference(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s1(nums1.begin(), nums1.end());
        unordered_set<int> s2(nums2.begin(), nums2.end());
        vector<vector<int>> ans(2);
        for (int v : s1) {
            if (!s2.contains(v)) {
                ans[0].push_back(v);
            }
        }
        for (int v : s2) {
            if (!s1.contains(v)) {
                ans[1].push_back(v);
            }
        }
        return ans;
    }
};
```

```go
func findDifference(nums1 []int, nums2 []int) [][]int {
	s1, s2 := make(map[int]bool), make(map[int]bool)
	for _, v := range nums1 {
		s1[v] = true
	}
	for _, v := range nums2 {
		s2[v] = true
	}
	ans := make([][]int, 2)
	for v := range s1 {
		if !s2[v] {
			ans[0] = append(ans[0], v)
		}
	}
	for v := range s2 {
		if !s1[v] {
			ans[1] = append(ans[1], v)
		}
	}
	return ans
}
```

```ts
function findDifference(nums1: number[], nums2: number[]): number[][] {
    const s1: Set<number> = new Set(nums1);
    const s2: Set<number> = new Set(nums2);
    nums1.forEach(num => s2.delete(num));
    nums2.forEach(num => s1.delete(num));
    return [Array.from(s1), Array.from(s2)];
}
```

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_difference(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<Vec<i32>> {
        vec![
            nums1
                .iter()
                .filter_map(|&v| if nums2.contains(&v) { None } else { Some(v) })
                .collect::<HashSet<i32>>()
                .into_iter()
                .collect(),
            nums2
                .iter()
                .filter_map(|&v| if nums1.contains(&v) { None } else { Some(v) })
                .collect::<HashSet<i32>>()
                .into_iter()
                .collect()
        ]
    }
}
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[][]}
 */
var findDifference = function (nums1, nums2) {
    const s1 = new Set(nums1);
    const s2 = new Set(nums2);
    nums1.forEach(num => s2.delete(num));
    nums2.forEach(num => s1.delete(num));
    return [Array.from(s1), Array.from(s2)];
};
```

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[][]
     */
    function findDifference($nums1, $nums2) {
        $s1 = array_flip($nums1);
        $s2 = array_flip($nums2);

        $diff1 = array_diff_key($s1, $s2);
        $diff2 = array_diff_key($s2, $s1);

        return [array_keys($diff1), array_keys($diff2)];
    }
}
```

<!-- tabs:end -->

<!-- end -->
