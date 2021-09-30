# [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i)

[中文文档](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README.md)

## Description

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> both of <strong>unique</strong> elements, where <code>nums1</code> is a subset of <code>nums2</code>.</p>

<p>Find all the next greater numbers for <code>nums1</code>&#39;s elements in the corresponding places of <code>nums2</code>.</p>

<p>The Next Greater Number of a number <code>x</code> in <code>nums1</code> is the first greater number to its right in <code>nums2</code>. If it does not exist, return <code>-1</code> for this number.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,1,2], nums2 = [1,3,4,2]
<strong>Output:</strong> [-1,3,-1]
<strong>Explanation:
</strong>For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
For number 1 in the first array, the next greater number for it in the second array is 3.
For number 2 in the first array, there is no next greater number for it in the second array, so output -1.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4], nums2 = [1,2,3,4]
<strong>Output:</strong> [3,-1]
<strong>Explanation:</strong>
For number 2 in the first array, the next greater number for it in the second array is 3.
For number 4 in the first array, there is no next greater number for it in the second array, so output -1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li>All integers in <code>nums1</code> and <code>nums2</code> are <strong>unique</strong>.</li>
	<li>All the integers of <code>nums1</code> also appear in <code>nums2</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an <code>O(nums1.length + nums2.length)</code> solution?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stk = []
        mp = {}
        for num in nums2[::-1]:
            while stk and stk[-1] <= num:
                stk.pop()
            mp[num] = stk[-1] if stk else -1
            stk.append(num)
        return [mp[num] for num in nums1]
```

### **Java**

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() <= nums2[i]) {
                stk.pop();
            }
            mp.put(nums2[i], stk.isEmpty() ? -1 : stk.peek());
            stk.push(nums2[i]);
        }
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = mp.get(nums1[i]);
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
var nextGreaterElement = function(nums1, nums2) {
    let stack = [];
    let nextGreater = {};
    for (let num of nums2) {
        while (stack.length > 0 && stack[stack.length - 1] < num) {
            nextGreater[stack.pop()] = num;
        }
        stack.push(num);
    }
    let res = nums1.map(d => nextGreater[d] || -1);
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2) {
        stack<int> stk;
        unordered_map<int, int> mp;
        for (int i = nums2.size() - 1; i >= 0; --i)
        {
            while (!stk.empty() && stk.top() <= nums2[i])
                stk.pop();
            mp[nums2[i]] = stk.empty() ? -1 : stk.top();
            stk.push(nums2[i]);
        }
        vector<int> res;
        for (int num : nums1)
            res.push_back(mp[num]);
        return res;
    }
};
```

### **Go**

```go
func nextGreaterElement(nums1 []int, nums2 []int) []int {
	var stk []int
	mp := make(map[int]int)
	for i := len(nums2) - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] <= nums2[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			mp[nums2[i]] = stk[len(stk)-1]
		} else {
			mp[nums2[i]] = -1
		}
		stk = append(stk, nums2[i])
	}
	var res []int
	for _, num := range nums1 {
		res = append(res, mp[num])
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
