# [303. 区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable)

[English Version](/solution/0300-0399/0303.Range%20Sum%20Query%20-%20Immutable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个整数数组 &nbsp;<em>nums</em>，求出数组从索引&nbsp;<em>i&nbsp;</em>到&nbsp;<em>j&nbsp;&nbsp;</em>(<em>i</em>&nbsp;&le;&nbsp;<em>j</em>) 范围内元素的总和，包含&nbsp;<em>i,&nbsp; j&nbsp;</em>两点。</p>

<p><strong>示例：</strong></p>

<pre>给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -&gt; 1
sumRange(2, 5) -&gt; -1
sumRange(0, 5) -&gt; -3</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>你可以假设数组不可变。</li>
	<li>会多次调用&nbsp;<em>sumRange</em>&nbsp;方法。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
