# [18. 四数之和](https://leetcode-cn.com/problems/4sum)

[English Version](/solution/0000-0099/0018.4Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含 <em>n</em> 个整数的数组 <code>nums</code> 和一个目标值 <code>target</code>，判断 <code>nums</code> 中是否存在四个元素 <em>a，</em><em>b，c</em> 和 <em>d</em> ，使得 <em>a</em> + <em>b</em> + <em>c</em> + <em>d</em> 的值与 <code>target</code> 相等？找出所有满足条件且不重复的四元组。</p>

<p><strong>注意：</strong>答案中不可以包含重复的四元组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,-1,0,-2,2], target = 0
<strong>输出：</strong>[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [], target = 0
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 200</code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

“排序 + 双指针”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        res = []
        if nums is None or len(nums) < 4:
            return res
        n = len(nums)
        nums.sort()
        for i in range(n - 3):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            for j in range(i + 1, n - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                p, q = j + 1, n - 1
                while p < q:
                    if p > j + 1 and nums[p] == nums[p - 1]:
                        p += 1
                        continue
                    if q < n - 1 and nums[q] == nums[q + 1]:
                        q -= 1
                        continue
                    t = nums[i] + nums[j] + nums[p] + nums[q]
                    if t == target:
                        res.append([nums[i], nums[j], nums[p], nums[q]])
                        p += 1
                        q -= 1
                    elif t < target:
                        p += 1
                    else:
                        q -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n;
        if (nums == null || (n = (nums.length)) < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int p = j + 1, q = n - 1;
                while (p < q) {
                    if (p > j + 1 && nums[p] == nums[p - 1]) {
                        ++p;
                        continue;
                    }
                    if (q < n - 1 && nums[q] == nums[q + 1]) {
                        --q;
                        continue;
                    }
                    int t = nums[i] + nums[j] + nums[p] + nums[q];
                    if (t == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        ++p;
                        --q;
                    } else if (t < target) {
                        ++p;
                    } else {
                        --q;
                    }
                }
            }
        }
        return res;
    }
}
```

### **JavaScript**
```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
    let len = nums.length;
    let res = [];
    if (len < 4) return [];
    nums.sort((a, b) => a - b);
    for (i = 0; i < len - 3; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) continue;
        if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
        for (j = i + 1; j < len - 2; j++) {
            if (j > i + 1 && nums[j] === nums[j - 1]) continue;
            let left = j + 1, right = len - 1;
            while (left < right) {
                if (nums[i] + nums[j] + nums[left] + nums[right] === target) {
                    res.push([nums[i], nums[j], nums[left], nums[right]]);
                    while (nums[left] === nums[left + 1]) left++;
                    left++;
                    while (nums[right] === nums[right - 1]) right--;
                    right--;
                    continue;
                } else if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                    right--;
                    continue;
                } else {
                    left++;
                    continue;
                }
            }
        }
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
