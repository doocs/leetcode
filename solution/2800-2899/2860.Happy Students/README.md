---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2860.Happy%20Students/README.md
rating: 1625
source: 第 363 场周赛 Q2
tags:
    - 数组
    - 枚举
    - 排序
---

<!-- problem:start -->

# [2860. 让所有学生保持开心的分组方法数](https://leetcode.cn/problems/happy-students)

[English Version](/solution/2800-2899/2860.Happy%20Students/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>nums</code> ，其中 <code>n</code> 是班级中学生的总数。班主任希望能够在让所有学生保持开心的情况下选出一组学生：</p>

<p>如果能够满足下述两个条件之一，则认为第 <code>i</code> 位学生将会保持开心：</p>

<ul>
	<li>这位学生被选中，并且被选中的学生人数 <strong>严格大于</strong> <code>nums[i]</code> 。</li>
	<li>这位学生没有被选中，并且被选中的学生人数 <strong>严格小于</strong> <code>nums[i]</code> 。</li>
</ul>

<p>返回能够满足让所有学生保持开心的分组方法的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>2
<strong>解释：</strong>
有两种可行的方法：
班主任没有选中学生。
班主任选中所有学生形成一组。 
如果班主任仅选中一个学生来完成分组，那么两个学生都无法保持开心。因此，仅存在两种可行的方法。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,0,3,3,6,7,2,7]
<strong>输出：</strong>3
<strong>解释：</strong>
存在三种可行的方法：
班主任选中下标为 1 的学生形成一组。
班主任选中下标为 1、2、3、6 的学生形成一组。
班主任选中所有学生形成一组。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 枚举

假设选出了 $k$ 个学生，那么以下情况成立：

-   如果 $nums[i] = k$，那么不存在分组方法；
-   如果 $nums[i] \gt k$，那么学生 $i$ 不被选中；
-   如果 $nums[i] \lt k$，那么学生 $i$ 被选中。

因此，被选中的学生一定是排序后的 $nums$ 数组中的前 $k$ 个元素。

我们在 $[0,..n]$ 范围内枚举 $k$，对于当前选出的学生人数 $i$，我们可以得到组内最大的学生编号 $i-1$，数字为 $nums[i-1]$。如果 $i \gt 0$ 并且 $nums[i-1] \ge i$，那么不存在分组方法；如果 $i \lt n$ 并且 $nums[i] \le i$，那么不存在分组方法。否则，存在分组方法，答案加一。

枚举结束后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countWays(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        ans = 0
        for i in range(n + 1):
            if i and nums[i - 1] >= i:
                continue
            if i < n and nums[i] <= i:
                continue
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if ((i == 0 || nums.get(i - 1) < i) && (i == n || nums.get(i) > i)) {
                ans++;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countWays(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i <= n; ++i) {
            if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
                continue;
            }
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func countWays(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i := 0; i <= n; i++ {
		if (i > 0 && nums[i-1] >= i) || (i < n && nums[i] <= i) {
			continue
		}
		ans++
	}
	return
}
```

#### TypeScript

```ts
function countWays(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i <= n; ++i) {
        if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
            continue;
        }
        ++ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
