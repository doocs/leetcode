# [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i)

[中文文档](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README.md)

## Description

<p>The <strong>next greater element</strong> of some element <code>x</code> in an array is the <strong>first greater</strong> element that is <strong>to the right</strong> of <code>x</code> in the same array.</p>

<p>You are given two <strong>distinct 0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, where <code>nums1</code> is a subset of <code>nums2</code>.</p>

<p>For each <code>0 &lt;= i &lt; nums1.length</code>, find the index <code>j</code> such that <code>nums1[i] == nums2[j]</code> and determine the <strong>next greater element</strong> of <code>nums2[j]</code> in <code>nums2</code>. If there is no next greater element, then the answer for this query is <code>-1</code>.</p>

<p>Return <em>an array </em><code>ans</code><em> of length </em><code>nums1.length</code><em> such that </em><code>ans[i]</code><em> is the <strong>next greater element</strong> as described above.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,1,2], nums2 = [1,3,4,2]
<strong>Output:</strong> [-1,3,-1]
<strong>Explanation:</strong> The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,<u>4</u>,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [<u>1</u>,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,<u>2</u>]. There is no next greater element, so the answer is -1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4], nums2 = [1,2,3,4]
<strong>Output:</strong> [3,-1]
<strong>Explanation:</strong> The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,<u>2</u>,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,<u>4</u>]. There is no next greater element, so the answer is -1.
</pre>

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
        mp = {}
        stk = []
        for num in nums2:
            while stk and stk[-1] < num:
                mp[stk.pop()] = num
            stk.append(num)
        return [mp.get(num, -1) for num in nums1]
```

### **Java**

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

### **TypeScript**

```ts
function nextGreaterElement(nums1: number[], nums2: number[]): number[] {
    const map = new Map<number, number>();
    const stack: number[] = [Infinity];
    for (const num of nums2) {
        while (num > stack[stack.length - 1]) {
            map.set(stack.pop(), num);
        }
        stack.push(num);
    }
    return nums1.map(num => map.get(num) || -1);
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut stack = Vec::new();
        for num in nums2 {
            while num > *stack.last().unwrap_or(&i32::MAX) {
                map.insert(stack.pop().unwrap(), num);
            }
            stack.push(num);
        }
        nums1
            .iter()
            .map(|n| *map.get(n).unwrap_or(&-1))
            .collect::<Vec<i32>>()
    }
}
```

```rust
impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        nums1.iter().map(|target| {
            let mut res = -1;
            for num in nums2.iter().rev() {
                if num == target {
                    break;
                }
                if num > target {
                    res = *num;
                }
            }
            res
        }).collect::<Vec<i32>>()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
