# [3048. 标记所有下标的最早秒数 I](https://leetcode.cn/problems/earliest-second-to-mark-indices-i)

[English Version](/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README_EN.md)

<!-- tags:数组,二分查找 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>1</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>changeIndices</code>&nbsp;，数组的长度分别为&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;。</p>

<p>一开始，<code>nums</code>&nbsp;中所有下标都是未标记的，你的任务是标记 <code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标。</p>

<p>从第 <code>1</code>&nbsp;秒到第 <code>m</code>&nbsp;秒（<b>包括&nbsp;</b>第&nbsp;<code>m</code>&nbsp;秒），对于每一秒 <code>s</code>&nbsp;，你可以执行以下操作 <strong>之一</strong>&nbsp;：</p>

<ul>
	<li>选择范围&nbsp;<code>[1, n]</code>&nbsp;中的一个下标 <code>i</code>&nbsp;，并且将&nbsp;<code>nums[i]</code> <strong>减少</strong>&nbsp;<code>1</code>&nbsp;。</li>
	<li>如果&nbsp;<code>nums[changeIndices[s]]</code>&nbsp;<strong>等于</strong>&nbsp;<code>0</code>&nbsp;，<strong>标记</strong>&nbsp;下标&nbsp;<code>changeIndices[s]</code>&nbsp;。</li>
	<li>什么也不做。</li>
</ul>

<p>请你返回范围 <code>[1, m]</code>&nbsp;中的一个整数，表示最优操作下，标记&nbsp;<code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标的 <strong>最早秒数</strong>&nbsp;，如果无法标记所有下标，返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
<b>输出：</b>8
<b>解释：</b>这个例子中，我们总共有 8 秒。按照以下操作标记所有下标：
第 1 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [1,2,0] 。
第 2 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [0,2,0] 。
第 3 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [0,1,0] 。
第 4 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [0,0,0] 。
第 5 秒，标​​​​​记 changeIndices[5] ，也就是标记下标 3 ，因为 nums[3] 等于 0 。
第 6 秒，标​​​​​记 changeIndices[6] ，也就是标记下标 2 ，因为 nums[2] 等于 0 。
第 7 秒，什么也不做。
第 8 秒，标记 changeIndices[8] ，也就是标记下标 1 ，因为 nums[1] 等于 0 。
现在所有下标已被标记。
最早可以在第 8 秒标记所有下标。
所以答案是 8 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
<b>输出：</b>6
<b>解释：</b>这个例子中，我们总共有 7 秒。按照以下操作标记所有下标：
第 1 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,2] 。
第 2 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,1] 。
第 3 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,0] 。
第 4 秒：标​​​​​记 changeIndices[4] ，也就是标记下标 2 ，因为 nums[2] 等于 0 。
第 5 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [0,0] 。
第 6 秒：标​​​​​记 changeIndices[6] ，也就是标记下标 1 ，因为 nums[1] 等于 0 。
现在所有下标已被标记。
最早可以在第 6 秒标记所有下标。
所以答案是 6 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1], changeIndices = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 这个例子中，无法标记所有下标，因为下标 1 不在 changeIndices 中。
所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == changeIndices.length &lt;= 2000</code></li>
	<li><code>1 &lt;= changeIndices[i] &lt;= n</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java
class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int l = 0;
        int r = changeIndices.length + 1;
        while (l < r) {
            final int m = (l + r) / 2;
            if (canMark(nums, changeIndices, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l <= changeIndices.length ? l : -1;
    }

    private boolean canMark(int[] nums, int[] changeIndices, int second) {
        int numMarked = 0;
        int decrement = 0;
        // indexToLastSecond[i] := the last second to mark the index i
        int[] indexToLastSecond = new int[nums.length];
        Arrays.fill(indexToLastSecond, -1);

        for (int i = 0; i < second; ++i) {
            indexToLastSecond[changeIndices[i] - 1] = i;
        }

        for (int i = 0; i < second; ++i) {
            // Convert to 0-indexed.
            final int index = changeIndices[i] - 1;
            if (i == indexToLastSecond[index]) {
                // Reach the last occurrence of the number.
                // So, the current second will be used to mark the index.
                if (nums[index] > decrement) {
                    // The decrement is less than the number to be marked.
                    return false;
                }
                decrement -= nums[index];
                ++numMarked;
            } else {
                ++decrement;
            }
        }
        return numMarked == nums.length;
    }
}
```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
