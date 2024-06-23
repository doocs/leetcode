---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README.md
---

<!-- problem:start -->

# [3196. 最大化子数组的总成本](https://leetcode.cn/problems/maximize-total-cost-of-alternating-subarrays)

[English Version](/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>子数组 <code>nums[l..r]</code>（其中 <code>0 &lt;= l &lt;= r &lt; n</code>）的 <strong>成本 </strong>定义为：</p>

<p><code>cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)<sup>r − l</sup></code></p>

<p>你的任务是将 <code>nums</code> 分割成若干子数组，使得所有子数组的成本之和 <strong>最大化</strong>，并确保每个元素 <strong>正好 </strong>属于一个子数组。</p>

<p>具体来说，如果 <code>nums</code> 被分割成 <code>k</code> 个子数组，且分割点为索引 <code>i<sub>1</sub>, i<sub>2</sub>, ..., i<sub>k − 1</sub></code>（其中 <code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k - 1</sub> &lt; n - 1</code>），则总成本为：</p>

<p><code>cost(0, i<sub>1</sub>) + cost(i<sub>1</sub> + 1, i<sub>2</sub>) + ... + cost(i<sub>k − 1</sub> + 1, n − 1)</code></p>

<p>返回在最优分割方式下的子数组成本之和的最大值。</p>

<p><strong>注意：</strong>如果 <code>nums</code> 没有被分割，即 <code>k = 1</code>，则总成本即为 <code>cost(0, n - 1)</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>一种总成本最大化的方法是将 <code>[1, -2, 3, 4]</code> 分割成子数组 <code>[1, -2, 3]</code> 和 <code>[4]</code>。总成本为 <code>(1 + 2 + 3) + 4 = 10</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,1,-1]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>一种总成本最大化的方法是将 <code>[1, -1, 1, -1]</code> 分割成子数组 <code>[1, -1]</code> 和 <code>[1, -1]</code>。总成本为 <code>(1 + 1) + (1 + 1) = 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>输出：</strong> 0</p>

<p><strong>解释：</strong></p>

<p>无法进一步分割数组，因此答案为 0。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>选择整个数组，总成本为 <code>1 + 1 = 2</code>，这是可能的最大成本。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
