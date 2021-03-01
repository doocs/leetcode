# [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable)

[中文文档](/solution/0300-0399/0303.Range%20Sum%20Query%20-%20Immutable/README.md)

## Description

<p>Given an integer array <i>nums</i>, find the sum of the elements between indices <i>i</i> and <i>j</i> (<i>i</i> &le; <i>j</i>), inclusive.</p>

<p><b>Example:</b><br>

<pre>

Given nums = [-2, 0, 3, -5, 2, -1]



sumRange(0, 2) -> 1

sumRange(2, 5) -> -1

sumRange(0, 5) -> -3

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>You may assume that the array does not change.</li>

<li>There are many calls to <i>sumRange</i> function.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class NumArray:

    def __init__(self, nums: List[int]):
        n = len(nums)
        self.sums = [0] * (n + 1)
        for i in range(n):
            self.sums[i + 1] = nums[i] + self.sums[i]


    def sumRange(self, i: int, j: int) -> int:
        return self.sums[j + 1] - self.sums[i]


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# param_1 = obj.sumRange(i,j)
```

### **Java**

```java
class NumArray {

    private int[] sums;

    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = nums[i] + sums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
```

### **...**

```

```

<!-- tabs:end -->
