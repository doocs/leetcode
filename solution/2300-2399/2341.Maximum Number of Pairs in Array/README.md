# [2341. 数组能形成多少数对](https://leetcode.cn/problems/maximum-number-of-pairs-in-array)

[English Version](/solution/2300-2399/2341.Maximum%20Number%20of%20Pairs%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。在一步操作中，你可以执行以下步骤：</p>

<ul>
	<li>从 <code>nums</code> 选出 <strong>两个</strong> <strong>相等的</strong> 整数</li>
	<li>从 <code>nums</code> 中移除这两个整数，形成一个 <strong>数对</strong></li>
</ul>

<p>请你在 <code>nums</code> 上多次执行此操作直到无法继续执行。</p>

<p>返回一个下标从 <strong>0</strong> 开始、长度为 <code>2</code> 的整数数组 <code>answer</code> 作为答案，其中<em> </em><code>answer[0]</code><em> </em>是形成的数对数目，<code>answer[1]</code> 是对 <code>nums</code> 尽可能执行上述操作后剩下的整数数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,2,1,3,2,2]
<strong>输出：</strong>[3,1]
<strong>解释：</strong>
nums[0] 和 nums[3] 形成一个数对，并从 nums 中移除，nums = [3,2,3,2,2] 。
nums[0] 和 nums[2] 形成一个数对，并从 nums 中移除，nums = [2,2,2] 。
nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [2] 。
无法形成更多数对。总共形成 3 个数对，nums 中剩下 1 个数字。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [] 。
无法形成更多数对。总共形成 1 个数对，nums 中剩下 0 个数字。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [0]
<strong>输出：</strong>[0,1]
<strong>解释：</strong>无法形成数对，nums 中剩下 1 个数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们可以统计数组 `nums` 中每个数字 $x$ 出现的次数，记录在哈希表或数组 `cnt` 中。

然后遍历 `cnt`，对于每个数字 $x$，如果 $x$ 出现的次数 $v$ 大于 $1$，则可以从数组中选出两个 $x$ 形成一个数对，我们将 $v$ 除以 $2$ 向下取整，即可得到当前数字 $x$ 可以形成的数对数目，然后我们累加这个数目到变量 $s$ 中。

最后剩余的个数为数组 `nums` 的长度减去可以形成的数对数目乘以 $2$，即 $n - s \times 2$。

答案为 $[s, n - s \times 2]$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为数组 `nums` 的长度；而 $C$ 为数组 `nums` 中数字的范围，本题中 $C = 101$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfPairs(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        s = sum(v // 2 for v in cnt.values())
        return [s, len(nums) - s * 2]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int s = 0;
        for (int v : cnt) {
            s += v / 2;
        }
        return new int[] {s, nums.length - s * 2};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numberOfPairs(vector<int>& nums) {
        vector<int> cnt(101);
        for (int& x : nums) {
            ++cnt[x];
        }
        int s = 0;
        for (int& v : cnt) {
            s += v >> 1;
        }
        return {s, (int) nums.size() - s * 2};
    }
};
```

### **Go**

```go
func numberOfPairs(nums []int) []int {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	s := 0
	for _, v := range cnt {
		s += v / 2
	}
	return []int{s, len(nums) - s*2}
}
```

### **TypeScript**

```ts
function numberOfPairs(nums: number[]): number[] {
    const n = nums.length;
    const count = new Array(101).fill(0);
    for (const num of nums) {
        count[num]++;
    }
    const sum = count.reduce((r, v) => r + (v >> 1), 0);
    return [sum, n - sum * 2];
}
```

### **Rust**

```rust
impl Solution {
    pub fn number_of_pairs(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut count = [0; 101];
        for &v in nums.iter() {
            count[v as usize] += 1;
        }
        let mut sum = 0;
        for v in count.iter() {
            sum += v >> 1;
        }
        vec![sum as i32, (n - sum * 2) as i32]
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *numberOfPairs(int *nums, int numsSize, int *returnSize) {
    int count[101] = {0};
    for (int i = 0; i < numsSize; i++) {
        count[nums[i]]++;
    }
    int sum = 0;
    for (int i = 0; i < 101; i++) {
        sum += count[i] >> 1;
    }
    int *ans = malloc(sizeof(int) * 2);
    ans[0] = sum;
    ans[1] = numsSize - sum * 2;
    *returnSize = 2;
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var numberOfPairs = function (nums) {
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    const s = cnt.reduce((a, b) => a + (b >> 1), 0);
    return [s, nums.length - s * 2];
};
```

### **C#**

```cs
public class Solution {
    public int[] NumberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        foreach(int x in nums) {
            ++cnt[x];
        }
        int s = 0;
        foreach(int v in cnt) {
            s += v / 2;
        }
        return new int[] {s, nums.Length - s * 2};
    }
}
```

### **...**

```

```

<!-- tabs:end -->
