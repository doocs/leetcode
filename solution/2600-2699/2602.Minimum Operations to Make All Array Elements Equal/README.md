# [2602. 使数组元素全部相等的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal)

[English Version](/solution/2600-2699/2602.Minimum%20Operations%20to%20Make%20All%20Array%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>同时给你一个长度为 <code>m</code>&nbsp;的整数数组&nbsp;<code>queries</code>&nbsp;。第 <code>i</code>&nbsp;个查询中，你需要将 <code>nums</code>&nbsp;中所有元素变成&nbsp;<code>queries[i]</code>&nbsp;。你可以执行以下操作&nbsp;<strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>将数组里一个元素&nbsp;<strong>增大</strong>&nbsp;或者&nbsp;<strong>减小</strong>&nbsp;<code>1</code>&nbsp;。</li>
</ul>

<p>请你返回一个长度为 <code>m</code>&nbsp;的数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code>是将&nbsp;<code>nums</code>&nbsp;中所有元素变成&nbsp;<code>queries[i]</code>&nbsp;的&nbsp;<strong>最少</strong>&nbsp;操作次数。</p>

<p><strong>注意</strong>，每次查询后，数组变回最开始的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,1,6,8], queries = [1,5]
<b>输出：</b>[14,10]
<b>解释：</b>第一个查询，我们可以执行以下操作：
- 将 nums[0] 减小 2 次，nums = [1,1,6,8] 。
- 将 nums[2] 减小 5 次，nums = [1,1,1,8] 。
- 将 nums[3] 减小 7 次，nums = [1,1,1,1] 。
第一个查询的总操作次数为 2 + 5 + 7 = 14 。
第二个查询，我们可以执行以下操作：
- 将 nums[0] 增大 2 次，nums = [5,1,6,8] 。
- 将 nums[1] 增大 4 次，nums = [5,5,6,8] 。
- 将 nums[2] 减小 1 次，nums = [5,5,5,8] 。
- 将 nums[3] 减小 3 次，nums = [5,5,5,5] 。
第二个查询的总操作次数为 2 + 4 + 1 + 3 = 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,9,6,3], queries = [10]
<b>输出：</b>[20]
<b>解释：</b>我们可以将数组中所有元素都增大到 10 ，总操作次数为 8 + 1 + 4 + 7 = 20 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀和 + 二分查找**

我们先将数组 $nums$ 进行排序，并计算出长度为 $n+1$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums$ 中前 $i$ 个元素的和。

接下来，遍历每个查询 $queries[i]$，我们需要将所有大于 $queries[i]$ 的元素减小到 $queries[i]$，将所有小于 $queries[i]$ 的元素增大到 $queries[i]$。

我们可以通过二分查找找到数组 $nums$ 中第一个大于 $queries[i]$ 的元素的下标 $i$，则有 $n-i$ 个元素需要减小到 $queries[i]$，这些元素的和为 $s[n]-s[i]$，这些元素的和需要减去 $n-i$ 个 $queries[i]$，因此，这些元素减小到 $queries[i]$ 的总操作次数为 $s[n]-s[i]-(n-i)\times queries[i]$。

同理，我们可以找到数组 $nums$ 中第一个大于等于 $queries[i]$ 的元素的下标 $i$，则有 $i$ 个元素需要增大到 $queries[i]$，这些元素的和为 $s[i]$，因此，这些元素增大到 $queries[i]$ 的总操作次数为 $queries[i]\times i-s[i]$。

最后，将这两个总操作次数相加，即为将数组 $nums$ 中所有元素变成 $queries[i]$ 的最少操作次数，即 $ans[i]=s[n]-s[i]-(n-i)\times queries[i]+queries[i]\times i-s[i]$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        ans = []
        for x in queries:
            i = bisect_left(nums, x + 1)
            t = s[-1] - s[i] - (len(nums) - i) * x
            i = bisect_left(nums, x)
            t += x * i - s[i]
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        List<Long> ans = new ArrayList<>();
        for (int x : queries) {
            int i = search(nums, x + 1);
            long t = s[n] - s[i] - 1L * (n - i) * x;
            i = search(nums, x);
            t += 1L * x * i - s[i];
            ans.add(t);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<long long> minOperations(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        vector<long long> ans;
        for (auto& x : queries) {
            int i = lower_bound(nums.begin(), nums.end(), x + 1) - nums.begin();
            long long t = s[n] - s[i] - 1LL * (n - i) * x;
            i = lower_bound(nums.begin(), nums.end(), x) - nums.begin();
            t += 1LL * x * i - s[i];
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func minOperations(nums []int, queries []int) (ans []int64) {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for _, x := range queries {
		i := sort.SearchInts(nums, x+1)
		t := s[n] - s[i] - (n-i)*x
		i = sort.SearchInts(nums, x)
		t += x*i - s[i]
		ans = append(ans, int64(t))
	}
	return
}
```

### **TypeScript**

```ts
function minOperations(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const search = (x: number): number => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const x of queries) {
        const i = search(x + 1);
        let t = s[n] - s[i] - (n - i) * x;
        const j = search(x);
        t += x * j - s[j];
        ans.push(t);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
