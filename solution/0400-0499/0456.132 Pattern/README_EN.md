# [456. 132 Pattern](https://leetcode.com/problems/132-pattern)

[中文文档](/solution/0400-0499/0456.132%20Pattern/README.md)

## Description

<p>

Given a sequence of n integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>, a 132 pattern is a subsequence a<sub><b>i</b></sub>, a<sub><b>j</b></sub>, a<sub><b>k</b></sub> such

that <b>i</b> < <b>j</b> < <b>k</b> and a<sub><b>i</b></sub> < a<sub><b>k</b></sub> < a<sub><b>j</b></sub>. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.</p>

<p><b>Note:</b> n will be less than 15,000.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [1, 2, 3, 4]



<b>Output:</b> False



<b>Explanation:</b> There is no 132 pattern in the sequence.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [3, 1, 4, 2]



<b>Output:</b> True



<b>Explanation:</b> There is a 132 pattern in the sequence: [1, 4, 2].

</pre>

</p>

<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> [-1, 3, 2, 0]



<b>Output:</b> True



<b>Explanation:</b> There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

</pre>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def find132pattern(self, nums: List[int]) -> bool:
        ak = float('-inf')
        stack = []
        for num in nums[::-1]:
            if num < ak:
                return True
            while stack and num > stack[-1]:
                ak = stack.pop()
            stack.append(num)
        return False
```

### **Java**

```java
class Solution {
    public boolean find132pattern(int[] nums) {
        int ak = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < ak) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                ak = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
