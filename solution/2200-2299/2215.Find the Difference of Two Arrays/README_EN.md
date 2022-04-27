# [2215. Find the Difference of Two Arrays](https://leetcode.com/problems/find-the-difference-of-two-arrays)

[中文文档](/solution/2200-2299/2215.Find%20the%20Difference%20of%20Two%20Arrays/README.md)

## Description

<p>Given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, return <em>a list</em> <code>answer</code> <em>of size</em> <code>2</code> <em>where:</em></p>

<ul>
	<li><code>answer[0]</code> <em>is a list of all <strong>distinct</strong> integers in</em> <code>nums1</code> <em>which are <strong>not</strong> present in</em> <code>nums2</code><em>.</em></li>
	<li><code>answer[1]</code> <em>is a list of all <strong>distinct</strong> integers in</em> <code>nums2</code> <em>which are <strong>not</strong> present in</em> <code>nums1</code>.</li>
</ul>

<p><strong>Note</strong> that the integers in the lists may be returned in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3], nums2 = [2,4,6]
<strong>Output:</strong> [[1,3],[4,6]]
<strong>Explanation:
</strong>For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].</pre>

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findDifference(self, nums1: List[int], nums2: List[int]) -> List[List[int]]:
        s1, s2 = set(nums1), set(nums2)
        return [list(s1 - s2), list(s2 - s1)]
```

### **Java**

```java
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = convert(nums1);
        Set<Integer> s2 = convert(nums2);

        List<List<Integer>> ans = new ArrayList<>();
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
        ans.add(l1);
        ans.add(l2);
        return ans;
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

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[][]}
 */
var findDifference = function (nums1, nums2) {
    let ans1 = new Set(nums1),
        ans2 = new Set(nums2);
    for (let num of nums1) {
        ans2.delete(num);
    }
    for (let num of nums2) {
        ans1.delete(num);
    }
    return [Array.from(ans1), Array.from(ans2)];
};
```

### **TypeScript**

```ts
function findDifference(nums1: number[], nums2: number[]): number[][] {
    return [
        [...new Set<number>(nums1.filter(v => !nums2.includes(v)))],
        [...new Set<number>(nums2.filter(v => !nums1.includes(v)))],
    ];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findDifference(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s1(nums1.begin(), nums1.end());
        unordered_set<int> s2(nums2.begin(), nums2.end());
        vector<vector<int>> ans(2);
        for (int v : s1)
            if (!s2.count(v))
                ans[0].push_back(v);
        for (int v : s2)
            if (!s1.count(v))
                ans[1].push_back(v);
        return ans;
    }
};
```

### **Go**

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

### **Rust**

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
                .collect(),
        ]
    }
}
```

```rust
impl Solution {
    pub fn find_difference(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<Vec<i32>> {
        const N: usize = 2001;
        let to_index = |i| i as usize + 1000;

        let mut is_in_nums1 = [false; N];
        let mut is_in_nums2 = [false; N];
        let mut res1 = vec![];
        let mut res2 = vec![];
        for &num in nums1.iter() {
            is_in_nums1[to_index(num)] = true;
        }
        for &num in nums2.iter() {
            is_in_nums2[to_index(num)] = true;
            if !is_in_nums1[to_index(num)] {
                res2.push(num);
                is_in_nums1[to_index(num)] = true;
            }
        }
        for &num in nums1.iter() {
            if !is_in_nums2[to_index(num)] {
                res1.push(num);
                is_in_nums2[to_index(num)] = true;
            }
        }
        vec![res1, res2]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
