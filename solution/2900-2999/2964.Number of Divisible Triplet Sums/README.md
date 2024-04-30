# [2964. 可被整除的三元组数量 🔒](https://leetcode.cn/problems/number-of-divisible-triplet-sums)

[English Version](/solution/2900-2999/2964.Number%20of%20Divisible%20Triplet%20Sums/README_EN.md)

<!-- tags:数组,哈希表 -->

## 题目描述

<!-- 这里写题目描述 -->

给定一个 <b>下标从 0 开始</b>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>d</code>，请返回满足 <code>i &lt; j &lt; k</code> 且 <code>(nums[i] + nums[j] + nums[k]) % d == 0</code> 的三元组 <code>(i, j, k)</code> 的数量。

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<strong>输入：</strong>nums = [3,3,4,7,8], d = 5
<strong>输出：</strong>3
<strong>解释：</strong>可以被5整除的三元组有：(0, 1, 2),(0, 2, 4),(1, 2, 4)。其他没有其他能被5整除的三元组。因此，答案是3。
</pre>

<p><b>示例 2：</b></p>

<pre>
<strong>输入：</strong>nums = [3,3,3,3], d = 3
<strong>输出：</strong>4
<strong>解释：</strong>这里选择的任何三元组的和都是9，可以被3整除。因此，答案是所有三元组的总数，即4。
</pre>

<p><b>示例 3:</b></p>

<pre>
<strong>输入：</strong>nums = [3,3,3,3], d = 6
<strong>输出：</strong>0
<strong>解释：</strong>这里选择的任何三元组的和都是9，不能被6整除。因此，答案是0。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= d &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 枚举

我们可以用哈希表 $cnt$ 记录 $nums[i] \bmod d$ 出现的次数，然后枚举 $j$ 和 $k$，计算使得等式 $(nums[i] + nums[j] + nums[k]) \bmod d = 0$ 成立的 $nums[i] \bmod d$ 的值，即 $(d - (nums[j] + nums[k]) \bmod d) \bmod d$，并将其出现次数累加到答案中。然后我们将 $nums[j] \bmod d$ 的出现次数加一。继续枚举 $j$ 和 $k$，直到 $j$ 到达数组末尾。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def divisibleTripletCount(self, nums: List[int], d: int) -> int:
        cnt = defaultdict(int)
        ans, n = 0, len(nums)
        for j in range(n):
            for k in range(j + 1, n):
                x = (d - (nums[j] + nums[k]) % d) % d
                ans += cnt[x]
            cnt[nums[j] % d] += 1
        return ans
```

```java
class Solution {
    public int divisibleTripletCount(int[] nums, int d) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, n = nums.length;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int x = (d - (nums[j] + nums[k]) % d) % d;
                ans += cnt.getOrDefault(x, 0);
            }
            cnt.merge(nums[j] % d, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int divisibleTripletCount(vector<int>& nums, int d) {
        unordered_map<int, int> cnt;
        int ans = 0, n = nums.size();
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int x = (d - (nums[j] + nums[k]) % d) % d;
                ans += cnt[x];
            }
            cnt[nums[j] % d]++;
        }
        return ans;
    }
};
```

```go
func divisibleTripletCount(nums []int, d int) (ans int) {
	n := len(nums)
	cnt := map[int]int{}
	for j := 0; j < n; j++ {
		for k := j + 1; k < n; k++ {
			x := (d - (nums[j]+nums[k])%d) % d
			ans += cnt[x]
		}
		cnt[nums[j]%d]++
	}
	return
}
```

```ts
function divisibleTripletCount(nums: number[], d: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let j = 0; j < n; ++j) {
        for (let k = j + 1; k < n; ++k) {
            const x = (d - ((nums[j] + nums[k]) % d)) % d;
            ans += cnt.get(x) || 0;
        }
        cnt.set(nums[j] % d, (cnt.get(nums[j] % d) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
