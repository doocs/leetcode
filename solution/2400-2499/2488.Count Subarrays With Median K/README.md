# [2488. 统计中位数为 K 的子数组](https://leetcode.cn/problems/count-subarrays-with-median-k)

[English Version](/solution/2400-2499/2488.Count%20Subarrays%20With%20Median%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>nums</code> ，该数组由从 <code>1</code> 到 <code>n</code> 的 <strong>不同</strong> 整数组成。另给你一个正整数 <code>k</code> 。</p>

<p>统计并返回 <code>nums</code> 中的 <strong>中位数</strong> 等于 <code>k</code> 的非空子数组的数目。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>数组的中位数是按 <strong>递增</strong> 顺序排列后位于 <strong>中间</strong> 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 <strong>左</strong> 的那个元素。
    <ul>
    	<li>例如，<code>[2,3,1,4]</code> 的中位数是 <code>2</code> ，<code>[8,4,3,5,1]</code> 的中位数是 <code>4</code> 。</li>
    </ul>
    </li>
    <li>子数组是数组中的一个连续部分。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,4,5], k = 4
<strong>输出：</strong>3
<strong>解释：</strong>中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,1], k = 3
<strong>输出：</strong>1
<strong>解释：</strong>[3] 是唯一一个中位数等于 3 的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= n</code></li>
	<li><code>nums</code> 中的整数互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历 + 计数**

我们先找到中位数 $k$ 在数组中的位置 $i$，然后从 $i$ 开始向两边遍历，统计中位数为 $k$ 的子数组的数目。

定义一个答案变量 $ans$，表示中位数为 $k$ 的子数组的数目。初始时 $ans = 1$，表示当前有一个长度为 $1$ 的子数组，其中位数为 $k$。另外，定义一个计数器 $cnt$，用于统计当前遍历过的数组中，「比 $k$ 大的元素的个数」与「比 $k$ 小的元素的个数」的差值的个数。

接下来，从 $i + 1$ 开始向右遍历，我们维护一个变量 $x$，表示当前右侧子数组中「比 $k$ 大的元素的个数」与「比 $k$ 小的元素的个数」的差值。如果 $x \in [0, 1]$，则当前右侧子数组的中位数为 $k$，答案变量 $ans$ 自增 $1$。然后，我们将 $x$ 的值加入计数器 $cnt$ 中。

同理，从 $i - 1$ 开始向左遍历，同样维护一个变量 $x$，表示当前左侧子数组中「比 $k$ 大的元素的个数」与「比 $k$ 小的元素的个数」的差值。如果 $x \in [0, 1]$，则当前左侧子数组的中位数为 $k$，答案变量 $ans$ 自增 $1$。如果 $-x$ 或 $-x + 1$ 也在计数器中，说明当前存在跨越 $i$ 左右两侧的子数组，其中位数为 $k$，答案变量 $ans$ 增加计数器中对应的值，即 $ans += cnt[-x] + cnt[-x + 1]$。

最后，返回答案变量 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

> 在编码上，我们可以直接开一个长度为 $2 \times n + 1$ 的数组，用于统计当前数组中，比 $k$ 大的元素的个数与比 $k$ 小的元素的个数的差值，每一次我们将差值加上 $n$，即可将差值的范围从 $[-n, n]$ 转换为 $[0, 2n]$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        i = nums.index(k)
        cnt = Counter()
        ans = 1
        x = 0
        for v in nums[i + 1:]:
            x += 1 if v > k else -1
            ans += 0 <= x <= 1
            cnt[x] += 1
        x = 0
        for j in range(i - 1, -1, -1):
            x += 1 if nums[j] > k else -1
            ans += 0 <= x <= 1
            ans += cnt[-x] + cnt[-x + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        for (; nums[i] != k; ++i) {
        }
        int[] cnt = new int[n << 1 | 1];
        int ans = 1;
        int x = 0;
        for (int j = i + 1; j < n; ++j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ++cnt[x + n];
        }
        x = 0;
        for (int j = i - 1; j >= 0; --j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ans += cnt[-x + n] + cnt[-x + 1 + n];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), k) - nums.begin();
        int cnt[n << 1 | 1];
        memset(cnt, 0, sizeof(cnt));
        int ans = 1;
        int x = 0;
        for (int j = i + 1; j < n; ++j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ++cnt[x + n];
        }
        x = 0;
        for (int j = i - 1; ~j; --j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ans += cnt[-x + n] + cnt[-x + 1 + n];
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, k int) int {
    i, n := 0, len(nums)
    for nums[i] != k {
        i++
    }
    ans := 1
    cnt := make([]int, n << 1 | 1)
    x := 0
    for j := i + 1; j < n; j++ {
        if nums[j] > k {
            x++
        } else {
            x--
        }
        if x >= 0 && x <= 1 {
            ans++
        }
        cnt[x + n]++
    }
    x = 0
    for j := i - 1; j >= 0; j-- {
        if nums[j] > k {
            x++
        } else {
            x--
        }
        if x >= 0 && x <= 1 {
            ans++
        }
        ans += cnt[-x + n] + cnt[-x + 1 + n]
    }
    return ans
}
```

### **TypeScript**

```ts
function countSubarrays(nums: number[], k: number): number {
    const i = nums.indexOf(k);
    const n = nums.length;
    const cnt = new Array((n << 1) | 1).fill(0);
    let ans = 1;
    let x = 0;
    for (let j = i + 1; j < n; ++j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ++cnt[x + n];
    }
    x = 0;
    for (let j = i - 1; ~j; --j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ans += cnt[-x + n] + cnt[-x + 1 + n];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
