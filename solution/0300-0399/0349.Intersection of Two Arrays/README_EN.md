# [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays)

[中文文档](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README.md)

<!-- tags:Array,Hash Table,Two Pointers,Binary Search,Sorting -->

## Description

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>an array of their <span data-keyword="array-intersection">intersection</span></em>. Each element in the result must be <strong>unique</strong> and you may return the result in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2,1], nums2 = [2,2]
<strong>Output:</strong> [2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>Output:</strong> [9,4]
<strong>Explanation:</strong> [4,9] is also accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        return list(set(nums1) & set(nums2))
```

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] s = new boolean[1001];
        for (int x : nums1) {
            s[x] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : nums2) {
            if (s[x]) {
                ans.add(x);
                s[x] = false;
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        bool s[1001];
        memset(s, false, sizeof(s));
        for (int x : nums1) {
            s[x] = true;
        }
        vector<int> ans;
        for (int x : nums2) {
            if (s[x]) {
                ans.push_back(x);
                s[x] = false;
            }
        }
        return ans;
    }
};
```

```go
func intersection(nums1 []int, nums2 []int) (ans []int) {
	s := [1001]bool{}
	for _, x := range nums1 {
		s[x] = true
	}
	for _, x := range nums2 {
		if s[x] {
			ans = append(ans, x)
			s[x] = false
		}
	}
	return
}
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    const s = Array(1001).fill(false);
    for (const x of nums1) {
        s[x] = true;
    }
    const ans = [];
    for (const x of nums2) {
        if (s[x]) {
            ans.push(x);
            s[x] = false;
        }
    }
    return ans;
};
```

```cs
public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        List<int> result = new List<int>();
        HashSet<int> arr1 = new(nums1);
        HashSet<int> arr2 = new(nums2);
        foreach (int x in arr1) {
            if (arr2.Contains(x)) {
                result.Add(x);
            }
        }
        return result.ToArray();
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersection($nums1, $nums2) {
        $rs = [];
        $set1 = array_values(array_unique($nums1));
        $set2 = array_values(array_unique($nums2));
        for ($i = 0; $i < count($set1); $i++) {
            $hashmap[$set1[$i]] = 1;
        }
        for ($j = 0; $j < count($set2); $j++) {
            if ($hashmap[$set2[$j]]) {
                array_push($rs, $set2[$j]);
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    return Array.from(new Set(nums1)).filter(num => new Set(nums2).has(num));
};
```

<!-- tabs:end -->

<!-- end -->
