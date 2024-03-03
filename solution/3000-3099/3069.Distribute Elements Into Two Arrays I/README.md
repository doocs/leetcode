# [3069. 将元素分配到两个数组中 I](https://leetcode.cn/problems/distribute-elements-into-two-arrays-i)

[English Version](/solution/3000-3099/3069.Distribute%20Elements%20Into%20Two%20Arrays%20I/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、包含<strong> 不同 </strong>整数的数组 <code>nums</code> ，数组长度为 <code>n</code> 。</p>

<p>你需要通过 <code>n</code> 次操作，将 <code>nums</code> 中的所有元素分配到两个数组 <code>arr1</code> 和 <code>arr2</code> 中。在第一次操作中，将 <code>nums[1]</code> 追加到 <code>arr1</code> 。在第二次操作中，将 <code>nums[2]</code> 追加到 <code>arr2</code> 。之后，在第 <code>i</code> 次操作中：</p>

<ul>
	<li>如果 <code>arr1</code> 的最后一个元素 <strong>大于 </strong><code>arr2</code> 的最后一个元素，就将 <code>nums[i]</code> 追加到 <code>arr1</code> 。否则，将 <code>nums[i]</code> 追加到 <code>arr2</code> 。</li>
</ul>

<p>通过连接数组 <code>arr1</code> 和 <code>arr2</code> 形成数组 <code>result</code> 。例如，如果 <code>arr1 == [1,2,3]</code> 且 <code>arr2 == [4,5,6]</code> ，那么 <code>result = [1,2,3,4,5,6]</code> 。</p>

<p>返回数组 <code>result</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>[2,3,1]
<strong>解释：</strong>在前两次操作后，arr1 = [2] ，arr2 = [1] 。
在第 3 次操作中，由于 arr1 的最后一个元素大于 arr2 的最后一个元素（2 &gt; 1），将 nums[3] 追加到 arr1 。
3 次操作后，arr1 = [2,3] ，arr2 = [1] 。
因此，连接形成的数组 result 是 [2,3,1] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,3,8]
<strong>输出：</strong>[5,3,4,8]
<strong>解释：</strong>在前两次操作后，arr1 = [5] ，arr2 = [4] 。
在第 3 次操作中，由于 arr1 的最后一个元素大于 arr2 的最后一个元素（5 &gt; 4），将 nums[3] 追加到 arr1 ，因此 arr1 变为 [5,3] 。
在第 4 次操作中，由于 arr2 的最后一个元素大于 arr1 的最后一个元素（4 &gt; 3），将 nums[4] 追加到 arr2 ，因此 arr2 变为 [4,8] 。
4 次操作后，arr1 = [5,3] ，arr2 = [4,8] 。
因此，连接形成的数组 result 是 [5,3,4,8] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code>中的所有元素都互不相同。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
