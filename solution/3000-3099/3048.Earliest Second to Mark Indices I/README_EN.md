# [3048. Earliest Second to Mark Indices I](https://leetcode.com/problems/earliest-second-to-mark-indices-i)

[中文文档](/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README.md)

<!-- tags: -->

## Description

<p>You are given two <strong>1-indexed</strong> integer arrays, <code>nums</code> and, <code>changeIndices</code>, having lengths <code>n</code> and <code>m</code>, respectively.</p>

<p>Initially, all indices in <code>nums</code> are unmarked. Your task is to mark <strong>all</strong> indices in <code>nums</code>.</p>

<p>In each second, <code>s</code>, in order from <code>1</code> to <code>m</code> (<strong>inclusive</strong>), you can perform <strong>one</strong> of the following operations:</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>[1, n]</code> and <strong>decrement</strong> <code>nums[i]</code> by <code>1</code>.</li>
	<li>If <code>nums[changeIndices[s]]</code> is <strong>equal</strong> to <code>0</code>, <strong>mark</strong> the index <code>changeIndices[s]</code>.</li>
	<li>Do nothing.</li>
</ul>

<p>Return <em>an integer denoting the <strong>earliest second</strong> in the range </em><code>[1, m]</code><em> when <strong>all</strong> indices in </em><code>nums</code><em> can be marked by choosing operations optimally, or </em><code>-1</code><em> if it is impossible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> In this example, we have 8 seconds. The following operations can be performed to mark all indices:
Second 1: Choose index 1 and decrement nums[1] by one. nums becomes [1,2,0].
Second 2: Choose index 1 and decrement nums[1] by one. nums becomes [0,2,0].
Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [0,1,0].
Second 4: Choose index 2 and decrement nums[2] by one. nums becomes [0,0,0].
Second 5: Mark the index changeIndices[5], which is marking index 3, since nums[3] is equal to 0.
Second 6: Mark the index changeIndices[6], which is marking index 2, since nums[2] is equal to 0.
Second 7: Do nothing.
Second 8: Mark the index changeIndices[8], which is marking index 1, since nums[1] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 8th second.
Hence, the answer is 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> In this example, we have 7 seconds. The following operations can be performed to mark all indices:
Second 1: Choose index 2 and decrement nums[2] by one. nums becomes [1,2].
Second 2: Choose index 2 and decrement nums[2] by one. nums becomes [1,1].
Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [1,0].
Second 4: Mark the index changeIndices[4], which is marking index 2, since nums[2] is equal to 0.
Second 5: Choose index 1 and decrement nums[1] by one. nums becomes [0,0].
Second 6: Mark the index changeIndices[6], which is marking index 1, since nums[1] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 6th second.
Hence, the answer is 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1], changeIndices = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> In this example, it is impossible to mark all indices because index 1 isn&#39;t in changeIndices.
Hence, the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == changeIndices.length &lt;= 2000</code></li>
	<li><code>1 &lt;= changeIndices[i] &lt;= n</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```
class Solution {
  public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
    int l = 0;
    int r = changeIndices.length + 1;

    while (l < r) {
      final int m = (l + r) / 2;
      if (canMark(nums, changeIndices, m))
        r = m;
      else
        l = m + 1;
    }

    return l <= changeIndices.length ? l : -1;
  }

  // Returns true if all indices of `nums` can be marked within `second`.
  private boolean canMark(int[] nums, int[] changeIndices, int second) {
    int numMarked = 0;
    int decrement = 0;
    // indexToLastSecond[i] := the last second to mark the index i
    int[] indexToLastSecond = new int[nums.length];
    Arrays.fill(indexToLastSecond, -1);

    for (int i = 0; i < second; ++i)
      indexToLastSecond[changeIndices[i] - 1] = i;

    for (int i = 0; i < second; ++i) {
      final int index = changeIndices[i] - 1; // Convert to 0-indexed.
      if (i == indexToLastSecond[index]) {
        // Reach the last occurrence of the number.
        // So, the current second will be used to mark the index.
        if (nums[index] > decrement)
          // The decrement is less than the number to be marked.
          return false;
        decrement -= nums[index];
        ++numMarked;
      } else {
        ++decrement;
      }
    }

    return numMarked == nums.length;
  }
}
```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
