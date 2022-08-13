# [2090. 半径为 k 的子数组平均值](https://leetcode.cn/problems/k-radius-subarray-averages)

[English Version](/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组中有 <code>n</code> 个整数，另给你一个整数 <code>k</code> 。</p>

<p><strong>半径为 k 的子数组平均值</strong> 是指：<code>nums</code> 中一个以下标 <code>i</code> 为 <strong>中心</strong> 且 <strong>半径</strong> 为 <code>k</code> 的子数组中所有元素的平均值，即下标在&nbsp;<code>i - k</code> 和 <code>i + k</code> 范围（<strong>含</strong> <code>i - k</code> 和 <code>i + k</code>）内所有元素的平均值。如果在下标 <code>i</code> 前或后不足 <code>k</code> 个元素，那么<strong> 半径为 k 的子数组平均值 </strong>是 <code>-1</code> 。</p>

<p>构建并返回一个长度为 <code>n</code> 的数组<em> </em><code>avgs</code><em> </em>，其中<em> </em><code>avgs[i]</code><em> </em>是以下标 <code>i</code> 为中心的子数组的<strong> 半径为 k 的子数组平均值 </strong>。</p>

<p><code>x</code> 个元素的 <strong>平均值</strong> 是 <code>x</code> 个元素相加之和除以 <code>x</code> ，此时使用截断式 <strong>整数除法</strong> ，即需要去掉结果的小数部分。</p>

<ul>
	<li>例如，四个元素 <code>2</code>、<code>3</code>、<code>1</code> 和 <code>5</code> 的平均值是 <code>(2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75</code>，截断后得到 <code>2</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/images/eg1.png" style="width: 343px; height: 119px;" /></p>

<pre>
<strong>输入：</strong>nums = [7,4,3,9,1,8,5,2,6], k = 3
<strong>输出：</strong>[-1,-1,-1,5,4,4,-1,-1,-1]
<strong>解释：</strong>
- avg[0]、avg[1] 和 avg[2] 是 -1 ，因为在这几个下标前的元素数量都不足 k 个。
- 中心为下标 3 且半径为 3 的子数组的元素总和是：7 + 4 + 3 + 9 + 1 + 8 + 5 = 37 。
  使用截断式 <strong>整数除法</strong>，avg[3] = 37 / 7 = 5 。
- 中心为下标 4 的子数组，avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4 。
- 中心为下标 5 的子数组，avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4 。
- avg[6]、avg[7] 和 avg[8] 是 -1 ，因为在这几个下标后的元素数量都不足 k 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [100000], k = 0
<strong>输出：</strong>[100000]
<strong>解释：</strong>
- 中心为下标 0 且半径 0 的子数组的元素总和是：100000 。
  avg[0] = 100000 / 1 = 100000 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [8], k = 100000
<strong>输出：</strong>[-1]
<strong>解释：</strong>
- avg[0] 是 -1 ，因为在下标 0 前后的元素数量均不足 k 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + nums[i]
        return [
            -1
            if i - k < 0 or i + k >= n
            else (presum[i + k + 1] - presum[i - k]) // (k * 2 + 1)
            for i in range(n)
        ]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i - k < 0 || i + k >= n) {
                ans[i] = -1;
            } else {
                ans[i] = (int) ((presum[i + k + 1] - presum[i - k]) / (k * 2 + 1));
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const l = 2 * k + 1;
    let sum = 0;
    let ans = new Array(n).fill(-1);
    for (let i = 0; i < n; i++) {
        sum += nums[i];
        let shiftIndex = i - l;
        if (shiftIndex > -1) {
            sum -= nums[shiftIndex];
        }
        if (i + 1 >= l) {
            ans[i - k] = Math.floor(sum / l);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long long> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + nums[i];
        vector<int> ans(n, -1);
        for (int i = 0; i < n; ++i)
            if (i - k >= 0 && i + k < n)
                ans[i] = (presum[i + k + 1] - presum[i - k]) * 1ll / (k * 2 + 1);
        return ans;
    }
};
```

### **Go**

```go
func getAverages(nums []int, k int) []int {
	n := len(nums)
	presum := make([]int64, n+1)
	for i, num := range nums {
		presum[i+1] = presum[i] + int64(num)
	}
	var ans []int
	for i := 0; i < n; i++ {
		if i-k < 0 || i+k >= n {
			ans = append(ans, -1)
		} else {
			ans = append(ans, int((presum[i+k+1]-presum[i-k])/int64(k*2+1)))
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
