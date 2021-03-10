# [259. 3Sum Smaller](https://leetcode.com/problems/3sum-smaller)

[中文文档](/solution/0200-0299/0259.3Sum%20Smaller/README.md)

## Description

<p>Given an array of <i>n</i> integers <i>nums</i> and a <i>target</i>, find the number of index triplets <code>i, j, k</code> with <code>0 <= i < j < k < n</code> that satisfy the condition <code>nums[i] + nums[j] + nums[k] < target</code>.</p>

<p><strong>Example:</strong></p>

<pre>
<strong>Input:</strong> <i>nums</i> = <code>[-2,0,1,3]</code>, and <i>target</i> = 2
<strong>Output:</strong> 2 
<strong>Explanation:</strong> Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
</pre>

<p><b style="font-family: sans-serif, Arial, Verdana, "Trebuchet MS";">Follow up:</b> Could you solve it in <i>O</i>(<i>n</i><sup>2</sup>) runtime?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        def threeSumSmaller(nums, start, end, target):
            count = 0
            while start < end:
                if nums[start] + nums[end] < target:
                    count += (end - start)
                    start += 1
                else:
                    end -= 1
            return count

        nums.sort()
        n, count = len(nums), 0
        for i in range(n - 2):
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i])
        return count
```

### **Java**

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; ++i) {
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i]);
        }
        return count;
    }

    private int threeSumSmaller(int[] nums, int start, int end, int target) {
        int count = 0;
        while (start < end) {
            if (nums[start] + nums[end] < target) {
                count += (end - start);
                ++start;
            } else {
                --end;
            }
        }
        return count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
