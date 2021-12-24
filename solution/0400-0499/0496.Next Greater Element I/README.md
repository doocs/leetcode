# [496. 下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i)

[English Version](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个<strong> 没有重复元素</strong> 的数组 <code>nums1</code> 和 <code>nums2</code> ，其中<code>nums1</code> 是 <code>nums2</code> 的子集。</p>

<p>请你找出 <code>nums1</code> 中每个元素在 <code>nums2</code> 中的下一个比其大的值。</p>

<p><code>nums1</code> 中数字 <code>x</code> 的下一个更大元素是指 <code>x</code> 在 <code>nums2</code> 中对应位置的右边的第一个比 <code>x</code><strong> </strong>大的元素。如果不存在，对应位置输出 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [4,1,2], nums2 = [1,3,4,2].
<strong>输出:</strong> [-1,3,-1]
<strong>解释:</strong>
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [2,4], nums2 = [1,2,3,4].
<strong>输出:</strong> [3,-1]
<strong>解释:</strong>
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums1.length <= nums2.length <= 1000</code></li>
	<li><code>0 <= nums1[i], nums2[i] <= 10<sup>4</sup></code></li>
	<li><code>nums1</code>和<code>nums2</code>中所有整数 <strong>互不相同</strong></li>
	<li><code>nums1</code> 中的所有整数同样出现在 <code>nums2</code> 中</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(nums1.length + nums2.length)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先对将 nums2 中的每一个元素，求出其下一个更大的元素。随后对于将这些答案放入哈希映射（HashMap）中，再遍历数组 nums1，并直接找出答案。对于 nums2，可以使用单调栈来解决这个问题。

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        mp = {}
        stk = []
        for num in nums2:
            while stk and stk[-1] < num:
                mp[stk.pop()] = num
            stk.append(num)
        return [mp.get(num, -1) for num in nums1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : nums2) {
            while (!stk.isEmpty() && stk.peek() < num) {
                mp.put(stk.pop(), num);
            }
            stk.push(num);
        }
        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = mp.getOrDefault(nums1[i], -1);
        }
        return ans;
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
var nextGreaterElement = function (nums1, nums2) {
    let stk = [];
    let nextGreater = {};
    for (let num of nums2) {
        while (stk && stk[stk.length - 1] < num) {
            nextGreater[stk.pop()] = num;
        }
        stk.push(num);
    }
    return nums1.map(e => nextGreater[e] || -1);
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> mp;
        for (int num : nums2)
        {
            while (!stk.empty() && stk.top() < num)
            {
                mp[stk.top()] = num;
                stk.pop();
            }
            stk.push(num);
        }
        vector<int> ans;
        for (int num : nums1) ans.push_back(mp.count(num) ? mp[num] : -1);
        return ans;
    }
};
```

### **Go**

```go
func nextGreaterElement(nums1 []int, nums2 []int) []int {
	var stk []int
	mp := make(map[int]int)
	for _, num := range nums2 {
		for len(stk) > 0 && stk[len(stk)-1] < num {
			mp[stk[len(stk)-1]] = num
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, num)
	}
	var ans []int
	for _, num := range nums1 {
		val, ok := mp[num]
		if !ok {
			val = -1
		}
		ans = append(ans, val)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
