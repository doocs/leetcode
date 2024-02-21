# [3034. 匹配模式数组的子数组数目 I](https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-i)

[English Version](/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README_EN.md)

<!-- tags:数组,字符串匹配,哈希函数,滚动哈希 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，和一个下标从 <code>0</code>&nbsp;开始长度为 <code>m</code>&nbsp;的整数数组&nbsp;<code>pattern</code>&nbsp;，<code>pattern</code>&nbsp;数组只包含整数&nbsp;<code>-1</code>&nbsp;，<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;。</p>

<p>大小为 <code>m + 1</code>&nbsp;的<span data-keyword="subarray">子数组</span>&nbsp;<code>nums[i..j]</code>&nbsp;如果对于每个元素 <code>pattern[k]</code>&nbsp;都满足以下条件，那么我们说这个子数组匹配模式数组&nbsp;<code>pattern</code>&nbsp;：</p>

<ul>
	<li>如果 <code>pattern[k] == 1</code> ，那么 <code>nums[i + k + 1] &gt; nums[i + k]</code></li>
	<li>如果&nbsp;<code>pattern[k] == 0</code>&nbsp;，那么&nbsp;<code>nums[i + k + 1] == nums[i + k]</code></li>
	<li>如果&nbsp;<code>pattern[k] == -1</code>&nbsp;，那么&nbsp;<code>nums[i + k + 1] &lt; nums[i + k]</code></li>
</ul>

<p>请你返回匹配 <code>pattern</code>&nbsp;的 <code>nums</code>&nbsp;子数组的 <strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5,6], pattern = [1,1]
<b>输出：</b>4
<b>解释：</b>模式 [1,1] 说明我们要找的子数组是长度为 3 且严格上升的。在数组 nums 中，子数组 [1,2,3] ，[2,3,4] ，[3,4,5] 和 [4,5,6] 都匹配这个模式。
所以 nums 中总共有 4 个子数组匹配这个模式。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
<b>输出：</b>2
<strong>解释：</strong>这里，模式数组 [1,0,-1] 说明我们需要找的子数组中，第一个元素小于第二个元素，第二个元素等于第三个元素，第三个元素大于第四个元素。在 nums 中，子数组 [1,4,4,1] 和 [3,5,5,3] 都匹配这个模式。
所以 nums 中总共有 2 个子数组匹配这个模式。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        n = len(nums)
        m = len(pattern)
        count = 0
        for i in range(n - m):
            flag = True
            for j in range(m):
                if (
                    (pattern[j] == 1 and nums[i + j + 1] <= nums[i + j])
                    or (pattern[j] == 0 and nums[i + j + 1] != nums[i + j])
                    or (pattern[j] == -1 and nums[i + j + 1] >= nums[i + j])
                ):
                    flag = False
                    break
            if flag:
                count += 1
        return count
```

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;
        int count = 0;
        for (int i = 0; i <= n - m - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if ((pattern[j] == 1 && nums[i + j + 1] <= nums[i + j]) ||
                    (pattern[j] == 0 && nums[i + j + 1] != nums[i + j]) ||
                    (pattern[j] == -1 && nums[i + j + 1] >= nums[i + j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }
}
```

```cpp
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int n = nums.size();
        int m = pattern.size();
        int c = 0;
        for (int i = 0; i <= n - m - 1; i++) {
            bool flag = true;
            for (int j = 0; j < m; j++) {
                if ((pattern[j] == 1 && nums[i + j + 1] <= nums[i + j]) || (pattern[j] == 0 && nums[i + j + 1] != nums[i + j]) || (pattern[j] == -1 && nums[i + j + 1] >= nums[i + j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                c++;
            }
        }
        return c;
    }
};
```

```go
func countMatchingSubarrays(nums []int, pattern []int) int {
	n := len(nums)
	m := len(pattern)
	count := 0
	for i := 0; i <= n-m-1; i++ {
		flag := true
		for j := 0; j < m; j++ {
			if (pattern[j] == 1 && nums[i+j+1] <= nums[i+j]) ||
				(pattern[j] == 0 && nums[i+j+1] != nums[i+j]) ||
				(pattern[j] == -1 && nums[i+j+1] >= nums[i+j]) {
				flag = false
				break
			}
		}
		if flag {
			count++
		}
	}
	return count
}
```

```ts
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const n: number = nums.length;
    const m: number = pattern.length;
    let count: number = 0;
    for (let i = 0; i <= n - m - 1; i++) {
        let flag: boolean = true;
        for (let j = 0; j < m; j++) {
            if (
                (pattern[j] === 1 && nums[i + j + 1] <= nums[i + j]) ||
                (pattern[j] === 0 && nums[i + j + 1] !== nums[i + j]) ||
                (pattern[j] === -1 && nums[i + j + 1] >= nums[i + j])
            ) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count++;
        }
    }
    return count;
}
```

<!-- tabs:end -->

<!-- end -->
