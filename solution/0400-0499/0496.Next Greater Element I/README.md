# [496. 下一个更大元素 I](https://leetcode.cn/problems/next-greater-element-i)

[English Version](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>nums1</code>&nbsp;中数字&nbsp;<code>x</code>&nbsp;的 <strong>下一个更大元素</strong> 是指&nbsp;<code>x</code>&nbsp;在&nbsp;<code>nums2</code> 中对应位置 <strong>右侧</strong> 的 <strong>第一个</strong> 比&nbsp;<code>x</code><strong>&nbsp;</strong>大的元素。</p>

<p>给你两个<strong> 没有重复元素</strong> 的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code> ，下标从 <strong>0</strong> 开始计数，其中<code>nums1</code>&nbsp;是&nbsp;<code>nums2</code>&nbsp;的子集。</p>

<p>对于每个 <code>0 &lt;= i &lt; nums1.length</code> ，找出满足 <code>nums1[i] == nums2[j]</code> 的下标 <code>j</code> ，并且在 <code>nums2</code> 确定 <code>nums2[j]</code> 的 <strong>下一个更大元素</strong> 。如果不存在下一个更大元素，那么本次查询的答案是 <code>-1</code> 。</p>

<p>返回一个长度为&nbsp;<code>nums1.length</code> 的数组<em> </em><code>ans</code><em> </em>作为答案，满足<em> </em><code>ans[i]</code><em> </em>是如上所述的 <strong>下一个更大元素</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,1,2], nums2 = [1,3,4,2].
<strong>输出：</strong>[-1,3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 4 ，用加粗斜体标识，nums2 = [1,3,<strong>4</strong>,2]。不存在下一个更大元素，所以答案是 -1 。
- 1 ，用加粗斜体标识，nums2 = [<em><strong>1</strong></em>,3,4,2]。下一个更大元素是 3 。
- 2 ，用加粗斜体标识，nums2 = [1,3,4,<em><strong>2</strong></em>]。不存在下一个更大元素，所以答案是 -1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,4], nums2 = [1,2,3,4].
<strong>输出：</strong>[3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 2 ，用加粗斜体标识，nums2 = [1,<em><strong>2</strong></em>,3,4]。下一个更大元素是 3 。
- 4 ，用加粗斜体标识，nums2 = [1,2,3,<em><strong>4</strong></em>]。不存在下一个更大元素，所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums1</code>和<code>nums2</code>中所有整数 <strong>互不相同</strong></li>
	<li><code>nums1</code> 中的所有整数同样出现在 <code>nums2</code> 中</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(nums1.length + nums2.length)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

对于本题，先对将 `nums2` 中的每一个元素，求出其下一个更大的元素。随后对于将这些答案放入哈希表 $m$ 中，再遍历数组 `nums1`，并直接找出答案。对于 `nums2`，可以使用单调栈来解决这个问题。

时间复杂度 $O(M+N)$，其中 $M$ 和 $N$ 分别为数组 `nums1` 和 `nums2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        m = {}
        stk = []
        for v in nums2:
            while stk and stk[-1] < v:
                m[stk.pop()] = v
            stk.append(v)
        return [m.get(v, -1) for v in nums1]
```

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        m = {}
        stk = []
        for v in nums2[::-1]:
            while stk and stk[-1] <= v:
                stk.pop()
            if stk:
                m[v] = stk[-1]
            stk.append(v)
        return [m.get(x, -1) for x in nums1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : nums2) {
            while (!stk.isEmpty() && stk.peek() < v) {
                m.put(stk.pop(), v);
            }
            stk.push(v);
        }
        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = m.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() <= nums2[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                m.put(nums2[i], stk.peek());
            }
            stk.push(nums2[i]);
        }
        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = m.getOrDefault(nums1[i], -1);
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
    let m = {};
    for (let v of nums2) {
        while (stk && stk[stk.length - 1] < v) {
            m[stk.pop()] = v;
        }
        stk.push(v);
    }
    return nums1.map(e => m[e] || -1);
};
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var nextGreaterElement = function (nums1, nums2) {
    let stk = [];
    let m = {};
    for (let v of nums2.reverse()) {
        while (stk && stk[stk.length - 1] <= v) {
            stk.pop();
        }
        if (stk) {
            m[v] = stk[stk.length - 1];
        }
        stk.push(v);
    }
    return nums1.map(e => m[e] || -1);
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> m;
        for (int& v : nums2) {
            while (!stk.empty() && stk.top() < v) {
                m[stk.top()] = v;
                stk.pop();
            }
            stk.push(v);
        }
        vector<int> ans;
        for (int& v : nums1) ans.push_back(m.count(v) ? m[v] : -1);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> m;
        for (int i = nums2.size() - 1; ~i; --i)
        {
            while (!stk.empty() && stk.top() <= nums2[i]) stk.pop();
            if (!stk.empty()) m[nums2[i]] = stk.top();
            stk.push(nums2[i]);
        }
        vector<int> ans;
        for (int& v : nums1) ans.push_back(m.count(v) ? m[v] : -1);
        return ans;
    }
};
```

### **Go**

```go
func nextGreaterElement(nums1 []int, nums2 []int) []int {
	stk := []int{}
	m := map[int]int{}
	for _, v := range nums2 {
		for len(stk) > 0 && stk[len(stk)-1] < v {
			m[stk[len(stk)-1]] = v
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, v)
	}
	var ans []int
	for _, v := range nums1 {
		val, ok := m[v]
		if !ok {
			val = -1
		}
		ans = append(ans, val)
	}
	return ans
}
```

```go
func nextGreaterElement(nums1 []int, nums2 []int) []int {
	stk := []int{}
	m := map[int]int{}
	for i := len(nums2) - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] <= nums2[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			m[nums2[i]] = stk[len(stk)-1]
		}
		stk = append(stk, nums2[i])
	}
	var ans []int
	for _, v := range nums1 {
		val, ok := m[v]
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
