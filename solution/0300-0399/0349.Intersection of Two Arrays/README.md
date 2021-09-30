# [349. 两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays)

[English Version](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个数组，编写一个函数来计算它们的交集。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出：</strong>[2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出：</strong>[9,4]</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong></p>

<ul>
	<li>输出结果中的每个元素一定是唯一的。</li>
	<li>我们可以不考虑输出结果的顺序。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
var intersection = function(nums1, nums2) {
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
        for (int num : nums2)
        {
            if (s.count(num) && !t.count(num))
            {
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
