# [918. 环形子数组的最大和](https://leetcode.cn/problems/maximum-sum-circular-subarray)

[English Version](/solution/0900-0999/0918.Maximum%20Sum%20Circular%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的<strong>环形整数数组</strong>&nbsp;<code>nums</code>&nbsp;，返回<em>&nbsp;<code>nums</code>&nbsp;的非空 <strong>子数组</strong> 的最大可能和&nbsp;</em>。</p>

<p><strong>环形数组</strong><em>&nbsp;</em>意味着数组的末端将会与开头相连呈环状。形式上， <code>nums[i]</code> 的下一个元素是 <code>nums[(i + 1) % n]</code> ， <code>nums[i]</code>&nbsp;的前一个元素是 <code>nums[(i - 1 + n) % n]</code> 。</p>

<p><strong>子数组</strong> 最多只能包含固定缓冲区&nbsp;<code>nums</code>&nbsp;中的每个元素一次。形式上，对于子数组&nbsp;<code>nums[i], nums[i + 1], ..., nums[j]</code>&nbsp;，不存在&nbsp;<code>i &lt;= k1, k2 &lt;= j</code>&nbsp;其中&nbsp;<code>k1 % n == k2 % n</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-2,3,-2]
<strong>输出：</strong>3
<strong>解释：</strong>从子数组 [3] 得到最大和 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,-3,5]
<strong>输出：</strong>10
<strong>解释：</strong>从子数组 [5,5] 得到最大和 5 + 5 = 10
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-2,2,-3]
<strong>输出：</strong>3
<strong>解释：</strong>从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code>​​​​​​​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：维护前缀最值**

求环形子数组的最大和，可以分为两种情况：

-   情况一：最大和的子数组不包含环形部分，即为普通的最大子数组和；
-   情况二：最大和的子数组包含环形部分，我们可以转换为：求数组总和减去最小子数组和。

因此，我们维护以下几个变量：

-   前缀和最小值 $pmi$，初始值为 $0$；
-   前缀和最大值 $pmx$，初始值为 $-\infty$；
-   前缀和 $s$，初始值为 $0$；
-   最小子数组和 $smi$，初始值为 $\infty$；
-   答案 $ans$，初始值为 $-\infty$。

接下来，我们只需要遍历数组 $nums$，对于当前遍历到的元素 $x$，我们做以下更新操作：

-   更新前缀和 $s = s + x$；
-   更新答案 $ans = \max(ans, s - pmi)$，即为情况一的答案（前缀和 $s$ 减去最小前缀和 $pmi$，可以得到最大子数组和）；
-   更新 $smi = \min(smi, s - pmx)$，即为情况二的最小子数组和；
-   更新 $pmi = \min(pmi, s)$，即为最小前缀和；
-   更新 $pmx = \max(pmx, s)$，即为最大前缀和。

遍历结束，我们取 $ans$ 以及 $s - smi$ 的最大值作为答案返回即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        s1 = s2 = f1 = f2 = nums[0]
        for num in nums[1:]:
            f1 = num + max(f1, 0)
            f2 = num + min(f2, 0)
            s1 = max(s1, f1)
            s2 = min(s2, f2)
        return s1 if s1 <= 0 else max(s1, sum(nums) - s2)
```

```python
class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        pmi, pmx = 0, -inf
        ans, s, smi = -inf, 0, inf
        for x in nums:
            s += x
            ans = max(ans, s - pmi)
            smi = min(smi, s - pmx)
            pmi = min(pmi, s)
            pmx = max(pmx, s)
        return max(ans, s - smi)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            total += nums[i];
            f1 = nums[i] + Math.max(f1, 0);
            f2 = nums[i] + Math.min(f2, 0);
            s1 = Math.max(s1, f1);
            s2 = Math.min(s2, f2);
        }
        return s1 > 0 ? Math.max(s1, total - s2) : s1;
    }
}
```

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        final int inf = 1 << 30;
        int pmi = 0, pmx = -inf;
        int ans = -inf, s = 0, smi = inf;
        for (int x : nums) {
            s += x;
            ans = Math.max(ans, s - pmi);
            smi = Math.min(smi, s - pmx);
            pmi = Math.min(pmi, s);
            pmx = Math.max(pmx, s);
        }
        return Math.max(ans, s - smi);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            total += nums[i];
            f1 = nums[i] + max(f1, 0);
            f2 = nums[i] + min(f2, 0);
            s1 = max(s1, f1);
            s2 = min(s2, f2);
        }
        return s1 > 0 ? max(s1, total - s2) : s1;
    }
};
```

```cpp
class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        const int inf = 1 << 30;
        int pmi = 0, pmx = -inf;
        int ans = -inf, s = 0, smi = inf;
        for (int x : nums) {
            s += x;
            ans = max(ans, s - pmi);
            smi = min(smi, s - pmx);
            pmi = min(pmi, s);
            pmx = max(pmx, s);
        }
        return max(ans, s - smi);
    }
};
```

### **Go**

```go
func maxSubarraySumCircular(nums []int) int {
	s1, s2, f1, f2, total := nums[0], nums[0], nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		total += nums[i]
		f1 = nums[i] + max(f1, 0)
		f2 = nums[i] + min(f2, 0)
		s1 = max(s1, f1)
		s2 = min(s2, f2)
	}
	if s1 <= 0 {
		return s1
	}
	return max(s1, total-s2)
}
```

```go
func maxSubarraySumCircular(nums []int) int {
	const inf = 1 << 30
	pmi, pmx := 0, -inf
	ans, s, smi := -inf, 0, inf
	for _, x := range nums {
		s += x
		ans = max(ans, s-pmi)
		smi = min(smi, s-pmx)
		pmi = min(pmi, s)
		pmx = max(pmx, s)
	}
	return max(ans, s-smi)
}
```

### **TypeScript**

```ts
function maxSubarraySumCircular(nums: number[]): number {
    let pre1 = nums[0],
        pre2 = nums[0];
    let ans1 = nums[0],
        ans2 = nums[0];
    let sum = nums[0];

    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        sum += cur;
        pre1 = Math.max(pre1 + cur, cur);
        ans1 = Math.max(pre1, ans1);

        pre2 = Math.min(pre2 + cur, cur);
        ans2 = Math.min(pre2, ans2);
    }
    return ans1 > 0 ? Math.max(ans1, sum - ans2) : ans1;
}
```

```ts
function maxSubarraySumCircular(nums: number[]): number {
    const inf = 1 << 30;
    let [pmi, pmx] = [0, -inf];
    let [ans, s, smi] = [-inf, 0, inf];
    for (const x of nums) {
        s += x;
        ans = Math.max(ans, s - pmi);
        smi = Math.min(smi, s - pmx);
        pmi = Math.min(pmi, s);
        pmx = Math.max(pmx, s);
    }
    return Math.max(ans, s - smi);
}
```

### **...**

```

```

<!-- tabs:end -->
