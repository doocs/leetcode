# [740. 删除并获得点数](https://leetcode.cn/problems/delete-and-earn)

[English Version](/solution/0700-0799/0740.Delete%20and%20Earn/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，你可以对它进行一些操作。</p>

<p>每次操作中，选择任意一个 <code>nums[i]</code> ，删除它并获得 <code>nums[i]</code> 的点数。之后，你必须删除 <strong>所有 </strong>等于 <code>nums[i] - 1</code> 和 <code>nums[i] + 1</code> 的元素。</p>

<p>开始你拥有 <code>0</code> 个点数。返回你能通过这些操作获得的最大点数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,2]
<strong>输出：</strong>6
<strong>解释：</strong>
删除 4 获得 4 个点数，因此 3 也被删除。
之后，删除 2 获得 2 个点数。总共获得 6 个点数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,3,3,4]
<strong>输出：</strong>9
<strong>解释：</strong>
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

核心思路: **一个数字要么不选，要么全选**

首先计算出每个数字的总和 sums，并维护两个 dp 数组：select 和 nonSelect

-   `sums[i]` 代表值为 i 的元素总和
-   `select[i]` 代表如果选数字 i，从 0 处理到 i 的最大和
-   `nonSelect[i]` 代表如果不选数字 i，从 0 处理到 i 的最大和

那么我们有以下逻辑：

-   如果选 i，那么 i-1 肯定不能选；
-   如果不选 i，那么 i-1 选不选都可以，因此我们选择其中较大的选法

```java
select[i] = nonSelect[i-1] + sums[i];
nonSelect[i] = Math.max(select[i-1], nonSelect[i-1]);
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        mx = -inf
        for num in nums:
            mx = max(mx, num)
        total = [0] * (mx + 1)
        for num in nums:
            total[num] += num
        first = total[0]
        second = max(total[0], total[1])
        for i in range(2, mx + 1):
            cur = max(first + total[i], second)
            first = second
            second = cur
        return second
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] sums = new int[10010];
        int[] select = new int[10010];
        int[] nonSelect = new int[10010];

        int maxV = 0;
        for (int x : nums) {
            sums[x] += x;
            maxV = Math.max(maxV, x);
        }

        for (int i = 1; i <= maxV; i++) {
            select[i] = nonSelect[i - 1] + sums[i];
            nonSelect[i] = Math.max(select[i - 1], nonSelect[i - 1]);
        }
        return Math.max(select[maxV], nonSelect[maxV]);
    }
}
```

### **Go**

```go
func deleteAndEarn(nums []int) int {

	max := func(x, y int) int {
		if x > y {
			return x
		}
		return y
	}

	mx := math.MinInt32
	for _, num := range nums {
		mx = max(mx, num)
	}
	total := make([]int, mx+1)
	for _, num := range nums {
		total[num] += num
	}
	first := total[0]
	second := max(total[0], total[1])
	for i := 2; i <= mx; i++ {
		cur := max(first+total[i], second)
		first = second
		second = cur
	}
	return second
}
```

### **C++**

```cpp
class Solution {
public:
    int deleteAndEarn(vector<int>& nums) {
        vector<int> vals(10010);
        for (int& num : nums) {
            vals[num] += num;
        }
        return rob(vals);
    }

    int rob(vector<int>& nums) {
        int a = 0, b = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
