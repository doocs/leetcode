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
        n, res = len(nums), []
        if n < 4:
            return []
        nums.sort()
        for i in range(n - 3):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            for j in range(i + 1, n - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                k, l = j + 1, n - 1
                while k < l:
                    if nums[i] + nums[j] + nums[k] + nums[l] == target:
                        res.append([nums[i], nums[j], nums[k], nums[l]])
                        k += 1
                        l -= 1
                        while k < n and nums[k] == nums[k - 1]:
                            k += 1
                        while l > j and nums[l] == nums[l + 1]:
                            l -= 1
                    elif nums[i] + nums[j] + nums[k] + nums[l] < target:
                        k += 1
                    else:
                        l -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) {
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
                int k = j + 1, l = n - 1;
                while (k < l) {
                    if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        ++k;
                        --l;
                        while (k < n && nums[k] == nums[k - 1]) {
                            ++k;
                        }
                        while (l > j && nums[l] == nums[l + 1]) {
                            --l;
                        }
                    } else if (nums[i] + nums[j] + nums[k] + nums[l] < target) {
                        ++k;
                    } else {
                        --l;
                    }
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
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n = nums.size();
        if (n < 4) {
            return {};
        }
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1, l = n - 1;
                while (k < l) {
                    if (nums[i] + nums[j] == target - nums[k] - nums[l]) {
                        res.push_back({nums[i], nums[j], nums[k], nums[l]});
                        ++k;
                        --l;
                        while (k < n && nums[k] == nums[k - 1]) ++k;
                        while (l > j && nums[l] == nums[l + 1]) --l;
                    } else if (nums[i] + nums[j] < target - nums[k] - nums[l]) {
                        ++k;
                    } else {
                        --l;
                    }
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
func fourSum(nums []int, target int) [][]int {
	n, res := len(nums), make([][]int, 0)
	if n < 4 {
		return res
	}
	sort.Ints(nums)
	for i := 0; i < n-3; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		for j := i + 1; j < n-2; j++ {
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			k, l := j+1, n-1
			for k < l {
				if nums[i]+nums[j]+nums[k]+nums[l] == target {
					res = append(res, []int{nums[i], nums[j], nums[k], nums[l]})
					k++
					l--
					for k < n && nums[k] == nums[k-1] {
						k++
					}
					for l > j && nums[l] == nums[l+1] {
						l--
					}
				} else if nums[i]+nums[j]+nums[k]+nums[l] < target {
					k++
				} else {
					l--
				}
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
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
    const n = nums.length;
    if (n < 4) return [];
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 3; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        for (let j = i + 1; j < n - 2; ++j) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;
            let k = j + 1;
            let l = n - 1;
            while (k < l) {
                if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                    res.push([nums[i], nums[j], nums[k], nums[l]]);
                    ++k;
                    --l;
                    while (k < n && nums[k] == nums[k - 1]) ++k;
                    while (l > j && nums[l] == nums[l + 1]) --l;
                } else if (nums[i] + nums[j] + nums[k] + nums[l] < target) {
                    ++k;
                } else {
                    --l;
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
