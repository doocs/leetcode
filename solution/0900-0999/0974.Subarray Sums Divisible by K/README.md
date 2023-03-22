# [974. 和可被 K 整除的子数组](https://leetcode.cn/problems/subarray-sums-divisible-by-k)

[English Version](/solution/0900-0999/0974.Subarray%20Sums%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数 <code>k</code> ，返回其中元素之和可被 <code>k</code>&nbsp;整除的（连续、非空） <strong>子数组</strong> 的数目。</p>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,0,-2,-3,1], k = 5
<strong>输出：</strong>7
<strong>解释：
</strong>有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [5], k = 9
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 前缀和**

假设存在 $i \leq j$，使得 $nums[i,..j]$ 的和能被 $k$ 整除，如果我们令 $s_i$ 表示 $nums[0,..i]$ 的和，令 $s_j$ 表示 $nums[0,..j]$ 的和，那么 $s_j - s_i$ 能被 $k$ 整除，即 $(s_j - s_i) \bmod k = 0$，也即 $s_j \bmod k = s_i \bmod k$。因此，我们可以用哈希表统计前缀和模 $k$ 的值的个数，从而快速判断是否存在满足条件的子数组。

我们用一个哈希表 $cnt$ 统计前缀和模 $k$ 的值的个数，即 $cnt[i]$ 表示前缀和模 $k$ 的值为 $i$ 的个数。初始时 $cnt[0]=1$。用变量 $s$ 表示前缀和，初始时 $s = 0$。

接下来，从左到右遍历数组 $nums$，对于遍历到的每个元素 $x$，我们计算 $s = (s + x) \bmod k$，然后更新答案 $ans = ans + cnt[s]$，其中 $cnt[s]$ 表示前缀和模 $k$ 的值为 $s$ 的个数。最后我们将 $cnt[s]$ 的值加 $1$，继续遍历下一个元素。

最终，我们返回答案 $ans$。

> 注意，由于 $s$ 的值可能为负数，因此我们可以将 $s$ 模 $k$ 的结果加上 $k$，再对 $k$ 取模，以确保 $s$ 的值为非负数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarraysDivByK(self, nums: List[int], k: int) -> int:
        cnt = Counter({0: 1})
        ans = s = 0
        for x in nums:
            s = (s + x) % k
            ans += cnt[s]
            cnt[s] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int ans = 0, s = 0;
        for (int x : nums) {
            s = ((s + x) % k + k) % k;
            ans += cnt.getOrDefault(s, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        unordered_map<int, int> cnt{{0, 1}};
        int ans = 0, s = 0;
        for (int& x : nums) {
            s = ((s + x) % k + k) % k;
            ans += cnt[s]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func subarraysDivByK(nums []int, k int) (ans int) {
	cnt := map[int]int{0: 1}
	s := 0
	for _, x := range nums {
		s = ((s+x)%k + k) % k
		ans += cnt[s]
		cnt[s]++
	}
	return
}
```

### **TypeScript**

```ts
function subarraysDivByK(nums: number[], k: number): number {
    const counter = new Map();
    counter.set(0, 1);
    let s = 0,
        ans = 0;
    for (const num of nums) {
        s += num;
        const t = ((s % k) + k) % k;
        ans += counter.get(t) || 0;
        counter.set(t, (counter.get(t) || 0) + 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
