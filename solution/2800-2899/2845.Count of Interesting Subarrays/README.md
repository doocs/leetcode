# [2845. 统计趣味子数组的数目](https://leetcode.cn/problems/count-of-interesting-subarrays)

[English Version](/solution/2800-2899/2845.Count%20of%20Interesting%20Subarrays/README_EN.md)

<!-- tags:数组,哈希表,前缀和 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，以及整数 <code>modulo</code> 和整数 <code>k</code> 。</p>

<p>请你找出并统计数组中 <strong>趣味子数组</strong> 的数目。</p>

<p>如果 <strong>子数组</strong> <code>nums[l..r]</code> 满足下述条件，则称其为 <strong>趣味子数组</strong> ：</p>

<ul>
	<li>在范围 <code>[l, r]</code> 内，设 <code>cnt</code> 为满足 <code>nums[i] % modulo == k</code> 的索引 <code>i</code> 的数量。并且 <code>cnt % modulo == k</code> 。</li>
</ul>

<p>以整数形式表示并返回趣味子数组的数目。<em> </em></p>

<p><span><strong>注意：</strong>子数组是数组中的一个连续非空的元素序列。</span></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4], modulo = 2, k = 1
<strong>输出：</strong>3
<strong>解释：</strong>在这个示例中，趣味子数组分别是： 
子数组 nums[0..0] ，也就是 [3] 。 
- 在范围 [0, 0] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
- 因此 cnt = 1 ，且 cnt % modulo == k 。
子数组 nums[0..1] ，也就是 [3,2] 。
- 在范围 [0, 1] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
- 因此 cnt = 1 ，且 cnt % modulo == k 。
子数组 nums[0..2] ，也就是 [3,2,4] 。
- 在范围 [0, 2] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
- 因此 cnt = 1 ，且 cnt % modulo == k 。
可以证明不存在其他趣味子数组。因此，答案为 3 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,9,6], modulo = 3, k = 0
<strong>输出：</strong>2
<strong>解释：</strong>在这个示例中，趣味子数组分别是： 
子数组 nums[0..3] ，也就是 [3,1,9,6] 。
- 在范围 [0, 3] 内，只存在 3 个下标 i = 0, 2, 3 满足 nums[i] % modulo == k 。
- 因此 cnt = 3 ，且 cnt % modulo == k 。
子数组 nums[1..1] ，也就是 [1] 。
- 在范围 [1, 1] 内，不存在下标满足 nums[i] % modulo == k 。
- 因此 cnt = 0 ，且 cnt % modulo == k 。
可以证明不存在其他趣味子数组，因此答案为 2 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5 </sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt; modulo</code></li>
</ul>

## 解法

### 方法一：哈希表 + 前缀和

题目要求一个区间内满足 $nums[i] \bmod modulo = k$ 的索引 $i$ 的数量，我们可以将数组 $nums$ 转换为一个 $0-1$ 数组 $arr$，其中 $arr[i] = 1$ 表示 $nums[i] \bmod modulo = k$，否则 $arr[i] = 0$。

那么对于一个区间 $[l, r]$，我们可以通过前缀和数组 $s$ 来计算 $arr[l..r]$ 中 $1$ 的数量，即 $s[r] - s[l - 1]$，其中 $s[0] = 0$。

我们用哈希表 $cnt$ 记录前缀和 $s \bmod modulo$ 出现的次数，初始时 $cnt[0]=1$。

接下来，我们遍历数组 $arr$，计算前缀和 $s$，将 $(s-k) \bmod modulo$ 出现的次数累加到答案中，然后将 $s \bmod modulo$ 出现的次数加 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def countInterestingSubarrays(self, nums: List[int], modulo: int, k: int) -> int:
        arr = [int(x % modulo == k) for x in nums]
        cnt = Counter()
        cnt[0] = 1
        ans = s = 0
        for x in arr:
            s += x
            ans += cnt[(s - k) % modulo]
            cnt[s % modulo] += 1
        return ans
```

```java
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nums.get(i) % modulo == k ? 1 : 0;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            ans += cnt.getOrDefault((s - k + modulo) % modulo, 0);
            cnt.merge(s % modulo, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countInterestingSubarrays(vector<int>& nums, int modulo, int k) {
        int n = nums.size();
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = int(nums[i] % modulo == k);
        }
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        long long ans = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            ans += cnt[(s - k + modulo) % modulo];
            cnt[s % modulo]++;
        }
        return ans;
    }
};
```

```go
func countInterestingSubarrays(nums []int, modulo int, k int) (ans int64) {
	arr := make([]int, len(nums))
	for i, x := range nums {
		if x%modulo == k {
			arr[i] = 1
		}
	}
	cnt := map[int]int{}
	cnt[0] = 1
	s := 0
	for _, x := range arr {
		s += x
		ans += int64(cnt[(s-k+modulo)%modulo])
		cnt[s%modulo]++
	}
	return
}
```

```ts
function countInterestingSubarrays(nums: number[], modulo: number, k: number): number {
    const arr: number[] = [];
    for (const x of nums) {
        arr.push(x % modulo === k ? 1 : 0);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    let s = 0;
    for (const x of arr) {
        s += x;
        ans += cnt.get((s - k + modulo) % modulo) || 0;
        cnt.set(s % modulo, (cnt.get(s % modulo) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
