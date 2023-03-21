# [1590. 使数组和能被 P 整除](https://leetcode.cn/problems/make-sum-divisible-by-p)

[English Version](/solution/1500-1599/1590.Make%20Sum%20Divisible%20by%20P/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>nums</code>，请你移除 <strong>最短</strong>&nbsp;子数组（可以为 <strong>空</strong>），使得剩余元素的 <strong>和</strong>&nbsp;能被 <code>p</code>&nbsp;整除。 <strong>不允许</strong>&nbsp;将整个数组都移除。</p>

<p>请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 <code>-1</code>&nbsp;。</p>

<p><strong>子数组</strong>&nbsp;定义为原数组中连续的一组元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,4,2], p = 6
<strong>输出：</strong>1
<strong>解释：</strong>nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [6,3,5,2], p = 9
<strong>输出：</strong>2
<strong>解释：</strong>我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], p = 3
<strong>输出：</strong>0
<strong>解释：</strong>和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
</pre>

<p><strong>示例&nbsp; 4：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], p = 7
<strong>输出：</strong>-1
<strong>解释：</strong>没有任何方案使得移除子数组后剩余元素的和被 7 整除。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>nums = [1000000000,1000000000,1000000000], p = 3
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 哈希表**

我们可以先求出数组 $nums$ 所有元素之和模 $p$ 的值，记为 $k$。如果 $k$ 为 $0$，说明数组 $nums$ 所有元素之和就是 $p$ 的倍数，直接返回 $0$ 即可。

如果 $k$ 不为 $0$，我们需要找到一个最短的子数组，使得删除该子数组后，剩余元素之和模 $p$ 的值为 $0$。

我们可以遍历数组 $nums$，维护当前的前缀和模 $p$ 的值，记为 $cur$。用哈希表 $last$ 记录每个前缀和模 $p$ 的值最后一次出现的位置。

如果当前存在一个以 $nums[i]$ 结尾的子数组，使得删除该子数组后，剩余元素之和模 $p$ 的值为 $0$。也就是说，我们需要找到此前的一个前缀和模 $p$ 的值为 $target$ 的位置 $j$，使得 $(target + k - cur) \bmod p = 0$。如果找到，我们就可以将 $j + 1$ 到 $i$ 这一段闭区间子数组 $nums[j+1,..i]$ 删除，使得剩余元素之和模 $p$ 的值为 $0$。

因此，如果存在一个 $target = (cur - k + p) \bmod p$，那么我们可以更新答案为 $\min(ans, i - j)$。接下来，我们更新 $last[cur]$ 的值为 $i$。继续遍历数组 $nums$，直到遍历结束，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSubarray(self, nums: List[int], p: int) -> int:
        k = sum(nums) % p
        if k == 0:
            return 0
        last = {0: -1}
        cur = 0
        ans = len(nums)
        for i, x in enumerate(nums):
            cur = (cur + x) % p
            target = (cur - k + p) % p
            if target in last:
                ans = min(ans, i - last[target])
            last[cur] = i
        return -1 if ans == len(nums) else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSubarray(int[] nums, int p) {
        int k = 0;
        for (int x : nums) {
            k = (k + x) % p;
        }
        if (k == 0) {
            return 0;
        }
        Map<Integer, Integer> last = new HashMap<>();
        last.put(0, -1);
        int n = nums.length;
        int ans = n;
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            cur = (cur + nums[i]) % p;
            int target = (cur - k + p) % p;
            if (last.containsKey(target)) {
                ans = Math.min(ans, i - last.get(target));
            }
            last.put(cur, i);
        }
        return ans == n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSubarray(vector<int>& nums, int p) {
        int k = 0;
        for (int& x : nums) {
            k = (k + x) % p;
        }
        if (k == 0) {
            return 0;
        }
        unordered_map<int, int> last;
        last[0] = -1;
        int n = nums.size();
        int ans = n;
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            cur = (cur + nums[i]) % p;
            int target = (cur - k + p) % p;
            if (last.count(target)) {
                ans = min(ans, i - last[target]);
            }
            last[cur] = i;
        }
        return ans == n ? -1 : ans;
    }
};
```

### **Go**

```go
func minSubarray(nums []int, p int) int {
	k := 0
	for _, x := range nums {
		k = (k + x) % p
	}
	if k == 0 {
		return 0
	}
	last := map[int]int{0: -1}
	n := len(nums)
	ans := n
	cur := 0
	for i, x := range nums {
		cur = (cur + x) % p
		target := (cur - k + p) % p
		if j, ok := last[target]; ok {
			ans = min(ans, i-j)
		}
		last[cur] = i
	}
	if ans == n {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minSubarray(nums: number[], p: number): number {
    let k = 0;
    for (const x of nums) {
        k = (k + x) % p;
    }
    if (k === 0) {
        return 0;
    }
    const last = new Map<number, number>();
    last.set(0, -1);
    const n = nums.length;
    let ans = n;
    let cur = 0;
    for (let i = 0; i < n; ++i) {
        cur = (cur + nums[i]) % p;
        const target = (cur - k + p) % p;
        if (last.has(target)) {
            const j = last.get(target)!;
            ans = Math.min(ans, i - j);
        }
        last.set(cur, i);
    }
    return ans === n ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
