# [15. 三数之和](https://leetcode.cn/problems/3sum)

[English Version](/solution/0000-0099/0015.3Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，判断是否存在三元组 <code>[nums[i], nums[j], nums[k]]</code> 满足 <code>i != j</code>、<code>i != k</code> 且 <code>j != k</code> ，同时还满足 <code>nums[i] + nums[j] + nums[k] == 0</code> 。请</p>

<p>你返回所有和为 <code>0</code> 且不重复的三元组。</p>

<p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
<strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
<strong>解释：</strong>
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1]
<strong>输出：</strong>[]
<strong>解释：</strong>唯一可能的三元组和不为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>[[0,0,0]]
<strong>解释：</strong>唯一可能的三元组和为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 双指针**

题目不要求我们按照顺序返回三元组，因此我们可以先对数组进行排序，这样就可以方便地跳过重复的元素。

接着枚举数组中的第一个元素 $nums[i]$，我们可以使用双指针的方法枚举第二个元素 $nums[j]$ 和第三个元素 $nums[k]$，使得它们的和为 $-nums[i]$。在枚举的过程中，我们需要跳过重复的元素，以避免出现重复的三元组。

时间复杂度 $O(n^2 + n\times \log n)$。其中 $n$ 是数组的长度。枚举第一个元素需要 $O(n)$ 的时间，枚举第二个元素和第三个元素需要 $O(n)$ 的时间，排序的时间复杂度为 $O(n\times \log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        n = len(nums)
        ans = []
        for i in range(n - 2):
            if nums[i] > 0:
                break
            if i and nums[i] == nums[i - 1]:
                continue
            j, k = i + 1, n - 1
            while j < k:
                if nums[i] + nums[j] + nums[k] == 0:
                    ans.append([nums[i], nums[j], nums[k]])
                    j, k = j + 1, k - 1
                    while j < n and nums[j] == nums[j - 1]:
                        j += 1
                    while k > j and nums[k] == nums[k + 1]:
                        k -= 1
                elif nums[i] + nums[j] + nums[k] < 0:
                    j += 1
                else:
                    k -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    while (j < n && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (k > j && nums[k] == nums[k + 1]) {
                        --k;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    ans.push_back({nums[i], nums[j++], nums[k--]});
                    while (j < k && nums[j] == nums[j - 1]) ++j;
                    while (j < k && nums[k] == nums[k + 1]) --k;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func threeSum(nums []int) (ans [][]int) {
    sort.Ints(nums)
    n := len(nums)
    for i := 0; i < n - 2 && nums[i] <= 0; i++ {
        if i > 0 && nums[i] == nums[i - 1] {
            continue
        }
        j, k := i + 1, n - 1
        for j < k {
            if nums[i] + nums[j] + nums[k] == 0 {
                ans = append(ans, []int{nums[i], nums[j], nums[k]})
                j, k = j + 1, k - 1
                for j < k && nums[j] == nums[j - 1] {
                    j++
                }
                for j < k && nums[k] == nums[k + 1] {
                    k--
                }
            } else if nums[i] + nums[j] + nums[k] < 0 {
                j++
            } else {
                k--
            }
        }
    }
    return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var threeSum = function (nums) {
    const n = nums.length;
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            if (nums[i] + nums[j] + nums[k] === 0) {
                res.push([nums[i], nums[j++], nums[k--]]);
                while (nums[j] === nums[j - 1]) ++j;
                while (nums[k] === nums[k + 1]) --k;
            } else if (nums[i] + nums[j] + nums[k] < 0) {
                ++j;
            } else {
                --k;
            }
        }
    }
    return res;
};
```

### **C#**

```cs
public class Solution {
    public IList<IList<int>> ThreeSum(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        IList<IList<int>> ans = new List<IList<int>>();
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    ans.Add(new List<int> { nums[i], nums[j++], nums[k--] });
                    while (j < n && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (k > j && nums[k] == nums[k + 1]) {
                        --k;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
}
```

### **Ruby**

```rb
# @param {Integer[]} nums
# @return {Integer[][]}
def three_sum(nums)
  res = []
  nums.sort!

  for i in 0..(nums.length - 3)
    next if i > 0 && nums[i - 1] == nums[i]
    j = i + 1
    k = nums.length - 1
    while j < k do
      sum = nums[i] + nums[j] + nums[k]
      if sum < 0
        j += 1
      elsif sum > 0
        k -= 1
      else
        res += [[nums[i], nums[j], nums[k]]]
        j += 1
        k -= 1
        j += 1 while nums[j] == nums[j - 1]
        k -= 1 while nums[k] == nums[k + 1]
      end
    end
  end

  res
end
```

### **TypeScript**

```ts
function threeSum(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const ans = [];
    const n = nums.length;
    for (let i = 0; i < n - 2 && nums[i] <= 0; i++) {
        const target = 0 - nums[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            if (nums[l] + nums[r] === target) {
                ans.push([nums[i], nums[l++], nums[r--]]);
                while (nums[l] === nums[l - 1]) {
                    l++;
                }
                while (nums[r] === nums[r + 1]) {
                    r--;
                }
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        while (nums[i] === nums[i + 1]) {
            i++;
        }
    }
    return ans;
}
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn three_sum(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let n = nums.len();
        let mut res = vec![];
        let mut i = 0;
        while i < n - 2 && nums[i] <= 0 {
            let mut l = i + 1;
            let mut r = n - 1;
            while l < r {
                match (nums[i] + nums[l] + nums[r]).cmp(&0) {
                    Ordering::Less => l += 1,
                    Ordering::Greater => r -= 1,
                    Ordering::Equal => {
                        res.push(vec![nums[i], nums[l], nums[r]]);
                        l += 1;
                        r -= 1;
                        while l < n && nums[l] == nums[l - 1] {
                            l += 1;
                        }
                        while r > 0 && nums[r] == nums[r + 1] {
                            r -= 1;
                        }
                    }
                }
            }
            i += 1;
            while i < n - 2 && nums[i] == nums[i - 1] {
                i += 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
