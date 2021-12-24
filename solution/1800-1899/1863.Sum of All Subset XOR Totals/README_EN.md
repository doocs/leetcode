# [1863. Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals)

[中文文档](/solution/1800-1899/1863.Sum%20of%20All%20Subset%20XOR%20Totals/README.md)

## Description

<p>The <strong>XOR total</strong> of an array is defined as the bitwise <code>XOR</code> of<strong> all its elements</strong>, or <code>0</code> if the array is<strong> empty</strong>.</p>

<ul>
	<li>For example, the <strong>XOR total</strong> of the array <code>[2,5,6]</code> is <code>2 XOR 5 XOR 6 = 1</code>.</li>
</ul>

<p>Given an array <code>nums</code>, return <em>the <strong>sum</strong> of all <strong>XOR totals</strong> for every <strong>subset</strong> of </em><code>nums</code>.&nbsp;</p>

<p><strong>Note:</strong> Subsets with the <strong>same</strong> elements should be counted <strong>multiple</strong> times.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3]
<strong>Output:</strong> 6
<strong>Explanation: </strong>The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,1,6]
<strong>Output:</strong> 28
<strong>Explanation: </strong>The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,6,7,8]
<strong>Output:</strong> 480
<strong>Explanation:</strong> The sum of all XOR totals for every subset is 480.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 12</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 20</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        def dfs(nums, depth, prev):
            self.res += prev
            for num in nums[depth:]:
                prev ^= num
                depth += 1
                dfs(nums, depth, prev)
                prev ^= num

        self.res = 0
        dfs(nums, 0, 0)
        return self.res
```

### **Java**

```java
class Solution {
    private int res;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int depth, int prev) {
        res += prev;
        for (int i = depth; i < nums.length; ++i) {
            prev ^= nums[i];
            dfs(nums, ++depth, prev);
            prev ^= nums[i];
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var subsetXORSum = function (nums) {
    let res = [];
    let prev = 0;
    dfs(nums, 0, prev, res);
    return res.reduce((a, c) => a + c, 0);
};

function dfs(nums, depth, prev, res) {
    res.push(prev);
    for (let i = depth; i < nums.length; i++) {
        prev ^= nums[i];
        depth++;
        dfs(nums, depth, prev, res);
        // bracktrack
        prev ^= nums[i];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
