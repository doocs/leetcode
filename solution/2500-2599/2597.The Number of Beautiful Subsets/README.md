# [2597. 美丽子集的数目](https://leetcode.cn/problems/the-number-of-beautiful-subsets)

[English Version](/solution/2500-2599/2597.The%20Number%20of%20Beautiful%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由正整数组成的数组 <code>nums</code> 和一个 <strong>正</strong> 整数 <code>k</code> 。</p>

<p>如果 <code>nums</code> 的子集中，任意两个整数的绝对差均不等于 <code>k</code> ，则认为该子数组是一个 <strong>美丽</strong> 子集。</p>

<p>返回数组 <code>nums</code> 中 <strong>非空</strong> 且 <strong>美丽</strong> 的子集数目。</p>

<p><code>nums</code> 的子集定义为：可以经由 <code>nums</code> 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,4,6], k = 2
<strong>输出：</strong>4
<strong>解释：</strong>数组 nums 中的美丽子集有：[2], [4], [6], [2, 6] 。
可以证明数组 [2,4,6] 中只存在 4 个美丽子集。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>数组 nums 中的美丽数组有：[1] 。
可以证明数组 [1] 中只存在 1 个美丽子集。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 回溯**

我们用哈希表或数组 $cnt$ 记录当前已经选择的数字以及它们的个数，用 $ans$ 记录美丽子集的数目，初始时 $ans = -1$，表示排除空集。

对于数组 $nums$ 中的每个数字 $x$，我们有两种选择：

-   不选择 $x$，此时直接递归到下一个数字；
-   选择 $x$，此时需要判断 $x + k$ 和 $x - k$ 是否已经在 $cnt$ 中出现过，如果都没有出现过，那么我们就可以选择 $x$，此时我们将 $x$ 的个数加一，然后递归到下一个数字，最后将 $x$ 的个数减一。

最后，我们返回 $ans$ 即可。

时间复杂度 $O(2^n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beautifulSubsets(self, nums: List[int], k: int) -> int:
        def dfs(i: int) -> None:
            nonlocal ans
            if i >= len(nums):
                ans += 1
                return
            dfs(i + 1)
            if cnt[nums[i] + k] == 0 and cnt[nums[i] - k] == 0:
                cnt[nums[i]] += 1
                dfs(i + 1)
                cnt[nums[i]] -= 1

        ans = -1
        cnt = Counter()
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;
    private int[] cnt = new int[1010];
    private int ans = -1;
    private int k;

    public int beautifulSubsets(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= nums.length) {
            ++ans;
            return;
        }
        dfs(i + 1);
        boolean ok1 = nums[i] + k >= cnt.length || cnt[nums[i] + k] == 0;
        boolean ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int beautifulSubsets(vector<int>& nums, int k) {
        int ans = -1;
        int cnt[1010]{};
        int n = nums.size();

        function<void(int)> dfs = [&](int i) {
            if (i >= n) {
                ++ans;
                return;
            }
            dfs(i + 1);
            bool ok1 = nums[i] + k >= 1010 || cnt[nums[i] + k] == 0;
            bool ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
            if (ok1 && ok2) {
                ++cnt[nums[i]];
                dfs(i + 1);
                --cnt[nums[i]];
            }
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func beautifulSubsets(nums []int, k int) int {
	ans := -1
	n := len(nums)
	cnt := [1010]int{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans++
			return
		}
		dfs(i + 1)
		ok1 := nums[i]+k >= len(cnt) || cnt[nums[i]+k] == 0
		ok2 := nums[i]-k < 0 || cnt[nums[i]-k] == 0
		if ok1 && ok2 {
			cnt[nums[i]]++
			dfs(i + 1)
			cnt[nums[i]]--
		}
	}
	dfs(0)
	return ans
}
```

### **TypeScript**

```ts
function beautifulSubsets(nums: number[], k: number): number {
    let ans: number = -1;
    const cnt: number[] = new Array(1010).fill(0);
    const n: number = nums.length;
    const dfs = (i: number) => {
        if (i >= n) {
            ++ans;
            return;
        }
        dfs(i + 1);
        const ok1: boolean = nums[i] + k >= 1010 || cnt[nums[i] + k] === 0;
        const ok2: boolean = nums[i] - k < 0 || cnt[nums[i] - k] === 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    };
    dfs(0);
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
