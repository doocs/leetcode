# [15. 三数之和](https://leetcode.cn/problems/3sum)

[English Version](/solution/0000-0099/0015.3Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个包含 <code>n</code> 个整数的数组&nbsp;<code>nums</code>，判断&nbsp;<code>nums</code>&nbsp;中是否存在三个元素 <em>a，b，c ，</em>使得&nbsp;<em>a + b + c = </em>0 ？请你找出所有和为 <code>0</code> 且不重复的三元组。</p>

<p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
<strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>[[0,0,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

若是使用三层嵌套循环，必然会导致程序超时，需要寻找其它方法。

因为不是返回对应的索引，所以可以对数组进行排序。

1. 对 `nums` 进行排序。
2. 遍历数组，并以当前遍历位置作为分割线，在右侧数组当中（不包括分割元素在内），寻找两个可以组成 `0 - nums[i]` 的值，将该题转换为**两数之和**。

> 更贴切的说，与 [剑指 Offer 57. 和为 s 的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/) 目标一致

**优化：**

-   当 `nums[i] > 0` 时，其后续的数值都比 `nums[i]` 大，那么就不可能存在两个数值一起组合为 0，可以提前结束遍历。
-   若当前遍历数值与上一个数值一致（`nums[i] == nums[i - 1]`），可直接跳过（去重复）。
-   相比两数之和，与其不同的是：**目标数组是有序的**。可使用**二分查找**或**头尾指针**快速搜索目标。

**重复问题：**

最简易的方式便是使用哈希表。还有一种技巧：**双指针**

能够使用双指针（头尾指针）搜索目标是因为**数组是有序的**，当移动指针时，数值的变化可预测的。

> 此处头尾指针使用 `l` 与 `r` 表示。

当找到目标值之后，`l` 与 `r` 都需要进行移动，并且是**移动到不等于组合时的值**。如 `nums[l] == 0`，那么 `l` 需要移动至 `nums[l] != 0` 的位置，`r` 同理。

为什么要同时移动两个指针，不会导致错过答案吗？并不会，如一个符合题意的组合是 `[-1, 0, 1]`，当 `l` 移动到了非 0 的位置时，那么 `nums[r] = 1` 不可能再组合出一个不重复的答案。

> 需注意，该技巧需要与第二条优化一起使用。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        n, res = len(nums), []
        if n < 3:
            return res
        nums.sort()
        for i in range(n - 2):
            if nums[i] > 0:
                break
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            j, k = i + 1, n - 1
            while j < k:
                if nums[i] + nums[j] + nums[k] == 0:
                    res.append([nums[i], nums[j], nums[k]])
                    j += 1
                    k -= 1
                    while j < n and nums[j] == nums[j - 1]:
                        j += 1
                    while k > i and nums[k] == nums[k + 1]:
                        k -= 1
                elif nums[i] + nums[j] + nums[k] < 0:
                    j += 1
                else:
                    k -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    ++j;
                    --k;
                    while (j < n && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (k > i && nums[k] == nums[k + 1]) {
                        --k;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) {
            return {};
        }
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        for (int i = 0; i < n - 2 && nums[i] <= 0; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.push_back({nums[i], nums[j], nums[k]});
                    ++j;
                    --k;
                    while (j < n && nums[j] == nums[j - 1]) ++j;
                    while (k > i && nums[k] == nums[k + 1]) --k;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
func threeSum(nums []int) [][]int {
	n, res := len(nums), make([][]int, 0)
	if n < 3 {
		return res
	}
	sort.Ints(nums)
	for i := 0; i < n-2 && nums[i] <= 0; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		j, k := i+1, n-1
		for j < k {
			if nums[i]+nums[j]+nums[k] == 0 {
				res = append(res, []int{nums[i], nums[j], nums[k]})
				j++
				k--
				for j < n && nums[j] == nums[j-1] {
					j++
				}
				for k > i && nums[k] == nums[k+1] {
					k--
				}
			} else if nums[i]+nums[j]+nums[k] < 0 {
				j++
			} else {
				k--
			}
		}
	}
	return res
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
    if (n < 3) return [];
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            if (nums[i] + nums[j] + nums[k] === 0) {
                res.push([nums[i], nums[j], nums[k]]);
                ++j;
                --k;
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
public class ThreeSumComparer: IEqualityComparer<IList<int>>
{
    public bool Equals(IList<int> left, IList<int> right)
    {
        return left[0] == right[0] && left[1] == right[1] && left[2] == right[2];
    }

    public int GetHashCode(IList<int> obj)
    {
        return (obj[0] ^ obj[1] ^ obj[2]).GetHashCode();
    }
}

public class Solution {
    public IList<IList<int>> ThreeSum(int[] nums) {
        Array.Sort(nums);
        var results = new HashSet<IList<int>>(new ThreeSumComparer());

        var cIndex = Array.BinarySearch(nums, 0);
        if (cIndex < 0) cIndex = ~cIndex;
        while (cIndex < nums.Length)
        {
            var c = nums[cIndex];
            var aIndex = 0;
            var bIndex = cIndex - 1;
            while (aIndex < bIndex)
            {
                if (nums[aIndex] + nums[bIndex] + c < 0)
                {
                    var step = 1;
                    while (aIndex + step < bIndex && nums[aIndex + step] + nums[bIndex] + c < 0)
                    {
                        aIndex += step;
                        step *= 2;
                    }
                    step /= 2;
                    while (step > 0)
                    {
                        if (aIndex + step < bIndex && nums[aIndex + step] + nums[bIndex] + c < 0)
                        {
                            aIndex += step;
                        }
                        step /= 2;
                    }
                }

                if (nums[aIndex] + nums[bIndex] + c > 0)
                {
                    var step = 1;
                    while (aIndex < bIndex - step && nums[aIndex] + nums[bIndex - step] + c > 0)
                    {
                        bIndex -= step;
                        step *= 2;
                    }
                    step /= 2;
                    while (step > 0)
                    {
                        if (aIndex < bIndex - step && nums[aIndex] + nums[bIndex - step] + c > 0)
                        {
                            bIndex -= step;
                        }
                        step /= 2;
                    }
                }

                if (nums[aIndex] + nums[bIndex] + c == 0)
                {
                    var list = new List<int> { nums[aIndex], nums[bIndex], c };
                    results.Add(list);
                    ++aIndex;
                    --bIndex;
                }
                else if (nums[aIndex] + nums[bIndex] + c < 0)
                {
                    ++aIndex;
                }
                else
                {
                    --bIndex;
                }
            }
            ++cIndex;
        }

        return results.ToList();
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
    const res = [];
    const n = nums.length;
    for (let i = 0; i < n - 2; i++) {
        if (nums[i] > 0) {
            break;
        }
        const target = 0 - nums[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            if (nums[l] + nums[r] === target) {
                res.push([nums[i], nums[l], nums[r]]);
                l++;
                r--;
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
    return res;
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
        if n < 3 {
            return res;
        }
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
