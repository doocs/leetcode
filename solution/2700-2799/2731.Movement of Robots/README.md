---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2731.Movement%20of%20Robots/README.md
rating: 1922
source: 第 106 场双周赛 Q3
tags:
    - 脑筋急转弯
    - 数组
    - 前缀和
    - 排序
---

<!-- problem:start -->

# [2731. 移动机器人](https://leetcode.cn/problems/movement-of-robots)

[English Version](/solution/2700-2799/2731.Movement%20of%20Robots/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;表示。当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，每个字符按顺序分别表示每个机器人移动的方向。<code>'L'</code>&nbsp;表示机器人往左或者数轴的负方向移动，<code>'R'</code>&nbsp;表示机器人往右或者数轴的正方向移动。</p>

<p>当两个机器人相撞时，它们开始沿着原本相反的方向移动。</p>

<p>请你返回指令重复执行 <code>d</code>&nbsp;秒后，所有机器人之间两两距离之和。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p><b>注意：</b></p>

<ul>
	<li>对于坐标在&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;的两个机器人，<code>(i,j)</code>&nbsp;和&nbsp;<code>(j,i)</code>&nbsp;视为相同的坐标对。也就是说，机器人视为无差别的。</li>
	<li>当机器人相撞时，它们 <strong>立即改变</strong>&nbsp;它们的前进方向，这个过程不消耗任何时间。</li>
	<li>
	<p>当两个机器人在同一时刻占据相同的位置时，就会相撞。</p>

    <ul>
    	<li>
    	<p>例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，下一秒，它们都将占据位置 1，并改变方向。再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。</p>
    	</li>
    	<li>
    	<p>例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。</p>
    	</li>
    </ul>
    </li>

</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [-2,0,2], s = "RLL", d = 3
<b>输出：</b>8
<b>解释：</b>
1 秒后，机器人的位置为 [-1,-1,1] 。现在下标为 0 的机器人开始往左移动，下标为 1 的机器人开始往右移动。
2 秒后，机器人的位置为 [-2,0,0] 。现在下标为 1 的机器人开始往左移动，下标为 2 的机器人开始往右移动。
3 秒后，机器人的位置为 [-3,-1,1] 。
下标为 0 和 1 的机器人之间距离为 abs(-3 - (-1)) = 2 。
下标为 0 和 2 的机器人之间的距离为 abs(-3 - 1) = 4 。
下标为 1 和 2 的机器人之间的距离为 abs(-1 - 1) = 2 。
所有机器人对之间的总距离为 2 + 4 + 2 = 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,0], s = "RL", d = 2
<b>输出：</b>5
<b>解释：</b>
1 秒后，机器人的位置为 [2,-1] 。
2 秒后，机器人的位置为 [3,-2] 。
两个机器人的距离为 abs(-2 - 3) = 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2 * 10<sup>9</sup>&nbsp;&lt;= nums[i] &lt;= 2 * 10<sup>9</sup></code></li>
	<li><code>0 &lt;= d &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length == s.length&nbsp;</code></li>
	<li><code>s</code>&nbsp;只包含&nbsp;<code>'L'</code> 和 <code>'R'</code>&nbsp;。</li>
	<li><code>nums[i]</code>&nbsp;互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯 + 排序

两个机器人相撞后，它们会立即改变方向，实际上相当于两个机器人继续往原来的方向移动。因此，我们遍历数组 $nums$，按照字符串 $s$ 的指令，将每个机器人的位置加上或减去 $d$，然后对数组 $nums$ 进行排序。

接下来，我们从小到大枚举每个机器人的位置，计算出当前机器人与前面所有机器人的距离之和，即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是机器人的数目。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumDistance(self, nums: List[int], s: str, d: int) -> int:
        mod = 10**9 + 7
        for i, c in enumerate(s):
            nums[i] += d if c == "R" else -d
        nums.sort()
        ans = s = 0
        for i, x in enumerate(nums):
            ans += i * x - s
            s += x
        return ans % mod
```

#### Java

```java
class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (long) nums[i] + (s.charAt(i) == 'L' ? -d : d);
        }
        Arrays.sort(arr);
        long ans = 0, sum = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + i * arr[i] - sum) % mod;
            sum += arr[i];
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumDistance(vector<int>& nums, string s, int d) {
        int n = nums.size();
        vector<long long> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = 1LL * nums[i] + (s[i] == 'L' ? -d : d);
        }
        sort(arr.begin(), arr.end());
        long long ans = 0;
        long long sum = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + i * arr[i] - sum) % mod;
            sum += arr[i];
        }
        return ans;
    }
};
```

#### Go

```go
func sumDistance(nums []int, s string, d int) (ans int) {
	for i, c := range s {
		if c == 'R' {
			nums[i] += d
		} else {
			nums[i] -= d
		}
	}
	sort.Ints(nums)
	sum := 0
	const mod int = 1e9 + 7
	for i, x := range nums {
		ans = (ans + i*x - sum) % mod
		sum += x
	}
	return
}
```

#### TypeScript

```ts
function sumDistance(nums: number[], s: string, d: number): number {
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        nums[i] += s[i] === 'L' ? -d : d;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let sum = 0;
    const mod = 1e9 + 7;
    for (let i = 0; i < n; ++i) {
        ans = (ans + i * nums[i] - sum) % mod;
        sum += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
