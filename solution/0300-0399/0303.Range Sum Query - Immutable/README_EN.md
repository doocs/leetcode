# [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable)

[中文文档](/solution/0300-0399/0303.Range%20Sum%20Query%20-%20Immutable/README.md)

## Description

<p>Given an integer array <code>nums</code>, find the sum of the elements between indices <code>left</code> and <code>right</code> inclusive, where <code>(left &lt;= right)</code>.</p>

<p>Implement the <code>NumArray</code> class:</p>

<ul>
	<li><code>NumArray(int[] nums)</code> initializes the object with the integer array <code>nums</code>.</li>
	<li><code>int sumRange(int left, int right)</code> returns the sum of the elements of the <code>nums</code> array in the range <code>[left, right]</code> inclusive (i.e., <code>sum(nums[left], nums[left + 1], ... , nums[right])</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumArray&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
<strong>Output</strong>
[null, 1, -1, -3]

<strong>Explanation</strong>
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; nums.length</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>sumRange</code>.</li>
</ul>

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
