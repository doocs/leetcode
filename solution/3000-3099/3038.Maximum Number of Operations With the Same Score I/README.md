# [3038. 相同分数的最大操作数目 I](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-i)

[English Version](/solution/3000-3099/3038.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20I/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>nums</code>&nbsp;<strong>至少</strong>&nbsp;包含&nbsp;<code>2</code>&nbsp;个元素，你可以执行以下操作：</p>

<ul>
	<li>选择 <code>nums</code>&nbsp;中的前两个元素并将它们删除。</li>
</ul>

<p>一次操作的 <strong>分数</strong>&nbsp;是被删除元素的和。</p>

<p>在确保<strong>&nbsp;所有操作分数相同</strong>&nbsp;的前提下，请你求出 <strong>最多</strong>&nbsp;能进行多少次操作。</p>

<p>请你返回按照上述要求 <strong>最多</strong>&nbsp;可以进行的操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,1,4,5]
<b>输出：</b>2
<b>解释：</b>我们执行以下操作：
- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,4,5] 。
- 删除前两个元素，分数为 1 + 4 = 5 ，nums = [5] 。
由于只剩下 1 个元素，我们无法继续进行任何操作。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,6,1,4]
<b>输出：</b>1
<b>解释：</b>我们执行以下操作：
- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
由于下一次操作的分数与前一次不相等，我们无法继续进行任何操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        s = nums[0] + nums[1]
        ans, n = 0, len(nums)
        for i in range(0, n, 2):
            if i + 1 == n or nums[i] + nums[i + 1] != s:
                break
            ans += 1
        return ans
```

```java
class Solution {
    public int maxOperations(int[] nums) {
        int s = nums[0] + nums[1];
        int ans = 0, n = nums.length;
        for (int i = 0; i + 1 < n && nums[i] + nums[i + 1] == s; i += 2) {
            ++ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxOperations(vector<int>& nums) {
        int s = nums[0] + nums[1];
        int ans = 0, n = nums.size();
        for (int i = 0; i + 1 < n && nums[i] + nums[i + 1] == s; i += 2) {
            ++ans;
        }
        return ans;
    }
};
```

```go
func maxOperations(nums []int) (ans int) {
	s, n := nums[0]+nums[1], len(nums)
	for i := 0; i+1 < n && nums[i]+nums[i+1] == s; i += 2 {
		ans++
	}
	return
}
```

```ts
function maxOperations(nums: number[]): number {
    const s = nums[0] + nums[1];
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i + 1 < n && nums[i] + nums[i + 1] === s; i += 2) {
        ++ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
