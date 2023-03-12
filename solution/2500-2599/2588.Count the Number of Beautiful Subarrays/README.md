# [2588. 统计美丽子数组数目](https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays)

[English Version](/solution/2500-2599/2588.Count%20the%20Number%20of%20Beautiful%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组<code>nums</code>&nbsp;。每次操作中，你可以：</p>

<ul>
	<li>选择两个满足&nbsp;<code>0 &lt;= i, j &lt; nums.length</code>&nbsp;的不同下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;。</li>
	<li>选择一个非负整数&nbsp;<code>k</code>&nbsp;，满足 <code>nums[i]</code>&nbsp;和 <code>nums[j]</code>&nbsp;在二进制下的第 <code>k</code>&nbsp;位（下标编号从 <strong>0</strong>&nbsp;开始）是 <code>1</code>&nbsp;。</li>
	<li>将 <code>nums[i]</code>&nbsp;和 <code>nums[j]</code>&nbsp;都减去&nbsp;<code>2<sup>k</sup></code>&nbsp;。</li>
</ul>

<p>如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 <code>0</code>&nbsp;的数组，那么我们称它是一个 <strong>美丽</strong>&nbsp;的子数组。</p>

<p>请你返回数组 <code>nums</code>&nbsp;中 <strong>美丽子数组</strong>&nbsp;的数目。</p>

<p>子数组是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [4,3,1,2,4]
<b>输出：</b>2
<b>解释：</b>nums 中有 2 个美丽子数组：[4,<em><strong>3,1,2</strong></em>,4] 和 [<em><strong>4,3,1,2,4</strong></em>] 。
- 按照下述步骤，我们可以将子数组 [3,1,2] 中所有元素变成 0 ：
  - 选择 [<em><strong>3</strong></em>, 1, <em><strong>2</strong></em>] 和 k = 1 。将 2 个数字都减去 2<sup>1</sup> ，子数组变成 [1, 1, 0] 。
  - 选择 [<em><strong>1</strong></em>, <em><strong>1</strong></em>, 0] 和 k = 0 。将 2 个数字都减去 2<sup>0</sup> ，子数组变成 [0, 0, 0] 。
- 按照下述步骤，我们可以将子数组 [4,3,1,2,4] 中所有元素变成 0 ：
  - 选择 [<em><strong>4</strong></em>, 3, 1, 2, <em><strong>4</strong></em>] 和 k = 2 。将 2 个数字都减去 2<sup>2</sup> ，子数组变成 [0, 3, 1, 2, 0] 。
  - 选择 [0, <em><strong>3</strong></em>, <em><strong>1</strong></em>, 2, 0] 和 k = 0 。将 2 个数字都减去 2<sup>0</sup> ，子数组变成 [0, 2, 0, 2, 0] 。
  - 选择 [0, <em><strong>2</strong></em>, 0, <em><strong>2</strong></em>, 0] 和 k = 1 。将 2 个数字都减去 2<sup>1</sup> ，子数组变成 [0, 0, 0, 0, 0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,10,4]
<b>输出：</b>0
<b>解释：</b>nums 中没有任何美丽子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀异或 + 哈希表**

我们观察发现，一个子数组能变成一个全为 $0$ 的数组，当且仅当该子数组中的所有元素，每一个二进制位上的 $1$ 的个数都是偶数个。

如果存在下标 $i$ 和 $j$，满足 $i \lt j$，且子数组 $nums[0,..,i]$ 和 $nums[0,..,j]$ 二进制位上的 $1$ 的个数同奇同偶，那么就可以将子数组 $nums[i + 1,..,j]$ 变成一个全为 $0$ 的数组。

因此，我们可以用前缀异或的方法，用哈希表 $cnt$ 统计每个前缀异或值出现的次数。遍历数组，对于每个元素 $x$，我们计算出它的前缀异或值 $mask$，然后将 $mask$ 出现的次数加到答案中。然后，我们将 $mask$ 的出现次数加 $1$。

最后，我们返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beautifulSubarrays(self, nums: List[int]) -> int:
        cnt = Counter({0: 1})
        ans = mask = 0
        for x in nums:
            mask ^= x
            ans += cnt[mask]
            cnt[mask] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt.getOrDefault(mask, 0);
            cnt.merge(mask, 1, Integer::sum);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long beautifulSubarrays(vector<int>& nums) {
        unordered_map<int, int> cnt{{0, 1}};
        long long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt[mask];
            ++cnt[mask];
        }
        return ans;
    }
};
```

### **Go**

```go
func beautifulSubarrays(nums []int) (ans int64) {
	cnt := map[int]int{0: 1}
	mask := 0
	for _, x := range nums {
		mask ^= x
		ans += int64(cnt[mask])
		cnt[mask]++
	}
	return
}
```

### **TypeScript**

```ts
function beautifulSubarrays(nums: number[]): number {
    const cnt = new Map();
    cnt.set(0, 1);
    let ans = 0;
    let mask = 0;
    for (const x of nums) {
        mask ^= x;
        ans += cnt.get(mask) || 0;
        cnt.set(mask, (cnt.get(mask) || 0) + 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
