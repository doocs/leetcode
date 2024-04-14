# [3117. 划分数组得到最小的值之和](https://leetcode.cn/problems/minimum-sum-of-values-by-dividing-array)

[English Version](/solution/3100-3199/3117.Minimum%20Sum%20of%20Values%20by%20Dividing%20Array/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个数组 <code>nums</code> 和 <code>andValues</code>，长度分别为 <code>n</code> 和 <code>m</code>。</p>

<p>数组的 <strong>值 </strong>等于该数组的 <strong>最后一个 </strong>元素。</p>

<p>你需要将 <code>nums</code> 划分为 <code>m</code> 个 <strong>不相交的连续 </strong>子数组，对于第 <code>i<sup>th</sup></code> 个子数组 <code>[l<sub>i</sub>, r<sub>i</sub>]</code>，子数组元素的按位<code>AND</code>运算结果等于 <code>andValues[i]</code>，换句话说，对所有的 <code>1 &lt;= i &lt;= m</code>，<code>nums[l<sub>i</sub>] &amp; nums[l<sub>i</sub> + 1] &amp; ... &amp; nums[r<sub>i</sub>] == andValues[i]</code> ，其中 <code>&amp;</code> 表示按位<code>AND</code>运算符。</p>

<p>返回将 <code>nums</code> 划分为 <code>m</code> 个子数组所能得到的可能的 <strong>最小 </strong>子数组 <strong>值</strong> 之和。如果无法完成这样的划分，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,4,3,3,2], andValues = [0,3,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>唯一可能的划分方法为：</p>

<ol>
	<li><code>[1,4]</code> 因为 <code>1 &amp; 4 == 0</code></li>
	<li><code>[3]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
	<li><code>[3]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
	<li><code>[2]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
</ol>

<p>这些子数组的值之和为 <code>4 + 3 + 3 + 2 = 12</code></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,5,7,7,7,5], andValues = [0,7,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">17</span></p>

<p><strong>解释：</strong></p>

<p>划分 <code>nums</code> 的三种方式为：</p>

<ol>
	<li><code>[[2,3,5],[7,7,7],[5]]</code> 其中子数组的值之和为 <code>5 + 7 + 5 = 17</code></li>
	<li><code>[[2,3,5,7],[7,7],[5]]</code> 其中子数组的值之和为 <code>7 + 7 + 5 = 19</code></li>
	<li><code>[[2,3,5,7,7],[7],[5]]</code> 其中子数组的值之和为 <code>7 + 7 + 5 = 19</code></li>
</ol>

<p>子数组值之和的最小可能值为 <code>17</code></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4], andValues = [2]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>整个数组 <code>nums</code> 的按位 <code>AND</code> 结果为 <code>0</code>。由于无法将 <code>nums</code> 划分为单个子数组使得元素的按位 <code>AND</code> 结果为 <code>2</code>，因此返回 <code>-1</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m == andValues.length &lt;= min(n, 10)</code></li>
	<li><code>1 &lt;= nums[i] &lt; 10<sup>5</sup></code></li>
	<li><code>0 &lt;= andValues[j] &lt; 10<sup>5</sup></code></li>
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
