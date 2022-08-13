# [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays)

[中文文档](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README.md)

## Description

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>an array of their intersection</em>. Each element in the result must be <strong>unique</strong> and you may return the result in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2,1], nums2 = [2,2]
<strong>Output:</strong> [2]
</pre>

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s = set(nums1)
        res = set()
        for num in nums2:
            if num in s:
                res.add(num)
        return list(res)
```

### **Java**

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums1) {
            s.add(num);
        }
        Set<Integer> t = new HashSet<>();
        for (int num : nums2) {
            if (s.contains(num)) {
                t.add(num);
            }
        }
        int[] res = new int[t.size()];
        int i = 0;
        for (int num : t) {
            res[i++] = num;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    const s = new Set();
    for (const num of nums1) {
        s.add(num);
    }
    let res = new Set();
    for (const num of nums2) {
        if (s.has(num)) {
            res.add(num);
        }
    }
    return [...res];
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s;
        for (int num : nums1) s.insert(num);
        unordered_set<int> t;
        vector<int> res;
        for (int num : nums2) {
            if (s.count(num) && !t.count(num)) {
                t.insert(num);
                res.push_back(num);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func intersection(nums1 []int, nums2 []int) []int {
	s := make(map[int]bool)
	for _, num := range nums1 {
		s[num] = true
	}
	t := make(map[int]bool)
	var res []int
	for _, num := range nums2 {
		if s[num] && !t[num] {
			res = append(res, num)
			t[num] = true
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
