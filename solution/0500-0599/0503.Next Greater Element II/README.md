# [503. 下一个更大元素 II](https://leetcode-cn.com/problems/next-greater-element-ii)

[English Version](/solution/0500-0599/0503.Next%20Greater%20Element%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,2,1]
<strong>输出:</strong> [2,-1,2]
<strong>解释:</strong> 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数； 
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
</pre>

<p><strong>注意:</strong> 输入数组的长度不会超过 10000。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“单调栈 + 循环数组”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [-1] * n
        stk = []
        for i in range(n << 1):
            while stk and nums[stk[-1]] < nums[i % n]:
                res[stk.pop()] = nums[i % n]
            stk.append(i % n)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < (n << 1); ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n]) {
                res[stk.pop()] = nums[i % n];
            }
            stk.push(i % n);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
 var nextGreaterElements = function(nums) {
    let n = nums.length;
    let stack = [];
    let res = new Array(n).fill(-1);
    for (let i = 0; i < 2 * n; i++) {
        let cur = nums[i % n];
        while (stack.length > 0 && nums[stack[stack.length - 1]] < cur) {
            res[stack.pop()] = cur;
        }
        stack.push(i % n);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nextGreaterElements(vector<int> &nums) {
        int n = nums.size();
        vector<int> res(n, -1);
        stack<int> stk;
        for (int i = 0; i < (n << 1); ++i)
        {
            while (!stk.empty() && nums[stk.top()] < nums[i % n])
            {
                res[stk.top()] = nums[i % n];
                stk.pop();
            }
            stk.push(i % n);
        }
        return res;
    }
};
```

### **Go**

```go
func nextGreaterElements(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i := range res {
		res[i] = -1
	}
	var stk []int
	for i := 0; i < (n << 1); i++ {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < nums[i%n] {
			res[stk[len(stk)-1]] = nums[i%n]
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i%n)
	} 
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
