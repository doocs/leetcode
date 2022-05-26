# [740. Delete and Earn](https://leetcode.com/problems/delete-and-earn)

[中文文档](/solution/0700-0799/0740.Delete%20and%20Earn/README.md)

## Description

<p>Given an array <code>nums</code> of integers, you can perform operations on the array.</p>

<p>In each operation, you pick any <code>nums[i]</code> and delete it to earn <code>nums[i]</code> points. After, you must delete <b>every</b> element equal to <code>nums[i] - 1</code> or <code>nums[i] + 1</code>.</p>

<p>You start with 0 points. Return the maximum number of points you can earn by applying such operations.</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> nums = [3, 4, 2]

<b>Output:</b> 6

<b>Explanation:</b> 

Delete 4 to earn 4 points, consequently 3 is also deleted.

Then, delete 2 to earn 2 points. 6 total points are earned.

</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> nums = [2, 2, 3, 3, 3, 4]

<b>Output:</b> 9

<b>Explanation:</b> 

Delete 3 to earn 3 points, deleting both 2&#39;s and the 4.

Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.

9 total points are earned.

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
    <li>The length of <code>nums</code> is at most <code>20000</code>.</li>
    <li>Each element <code>nums[i]</code> is an integer in the range <code>[1, 10000]</code>.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

Intuition: **If we take a number, we will take all of the copies of it**.

First calculate the sum of each number as **sums**, and keep updating two dp arrays: **select** and **nonSelect**

- sums[i] represents the sum of elements whose value is i;
- select[i] represents the maximum sum of processing from 0 to i if the number i is selected;
- nonSelect[i] represents the maximum sum of processing from 0 to i if the number i is not selected;

Then we have the following conclusions:

- If i is selected, then i-1 must not be selected;
- If you do not choose i, then i-1 can choose or not, so we choose the larger one;

```java
select[i] = nonSelect[i-1] + sums[i];
nonSelect[i] = Math.max(select[i-1], nonSelect[i-1]);
```

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] sums = new int[10010];
        int[] select = new int[10010];
        int[] nonSelect = new int[10010];

        int maxV = 0;
        for (int x : nums) {
            sums[x] += x;
            maxV = Math.max(maxV, x);
        }

        for (int i = 1; i <= maxV; i++) {
            select[i] = nonSelect[i - 1] + sums[i];
            nonSelect[i] = Math.max(select[i - 1], nonSelect[i - 1]);
        }
        return Math.max(select[maxV], nonSelect[maxV]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
