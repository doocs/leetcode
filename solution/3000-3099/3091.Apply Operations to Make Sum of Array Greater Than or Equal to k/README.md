# [3091. 执行操作使数据元素之和大于等于 K](https://leetcode.cn/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k)

[English Version](/solution/3000-3099/3091.Apply%20Operations%20to%20Make%20Sum%20of%20Array%20Greater%20Than%20or%20Equal%20to%20k/README_EN.md)

<!-- tags:贪心,数学,枚举 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<strong>正整数</strong> <code>k</code> 。最初，你有一个数组 <code>nums = [1]</code> 。</p>

<p>你可以对数组执行以下 <strong>任意 </strong>操作 <strong>任意 </strong>次数（<strong>可能为零</strong>）：</p>

<ul>
	<li>选择数组中的任何一个元素，然后将它的值<strong> 增加</strong> <code>1</code> 。</li>
	<li>复制数组中的任何一个元素，然后将它附加到数组的末尾。</li>
</ul>

<p>返回使得最终数组元素之<strong> 和 </strong>大于或等于 <code>k</code> 所需的 <strong>最少 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">k = 11</span></p>

<p><strong>输出：</strong><span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>可以对数组 <code>nums = [1]</code> 执行以下操作：</p>

<ul>
	<li>将元素的值增加 <code>1</code> 三次。结果数组为 <code>nums = [4]</code> 。</li>
	<li>复制元素两次。结果数组为 <code>nums = [4,4,4]</code> 。</li>
</ul>

<p>最终数组的和为 <code>4 + 4 + 4 = 12</code> ，大于等于 <code>k = 11</code> 。<br />
执行的总操作次数为 <code>3 + 2 = 5</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">k = 1</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>原始数组的和已经大于等于 <code>1</code> ，因此不需要执行操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：枚举

我们应该将复制的操作（即操作 $2$）放后面，这样可以减少操作次数。

因此，我们在 $[0, k]$ 的范围内枚举操作 $1$ 的次数 $a$，那么操作 $2$ 的次数 $b = \left\lceil \frac{k}{a+1} \right\rceil - 1$。取最小的 $a+b$ 即可。

时间复杂度 $O(k)$，其中 $k$ 为输入的正整数 $k$。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, k: int) -> int:
        ans = k
        for a in range(k):
            x = a + 1
            b = (k + x - 1) // x - 1
            ans = min(ans, a + b)
        return ans
```

```java
class Solution {
    public int minOperations(int k) {
        int ans = k;
        for (int a = 0; a < k; ++a) {
            int x = a + 1;
            int b = (k + x - 1) / x - 1;
            ans = Math.min(ans, a + b);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(int k) {
        int ans = k;
        for (int a = 0; a < k; ++a) {
            int x = a + 1;
            int b = (k + x - 1) / x - 1;
            ans = min(ans, a + b);
        }
        return ans;
    }
};
```

```go
func minOperations(k int) int {
	ans := k
	for a := 0; a < k; a++ {
		x := a + 1
		b := (k+x-1)/x - 1
		ans = min(ans, a+b)
	}
	return ans
}
```

```ts
function minOperations(k: number): number {
    let ans = k;
    for (let a = 0; a < k; ++a) {
        const x = a + 1;
        const b = Math.ceil(k / x) - 1;
        ans = Math.min(ans, a + b);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
