# [15. 3Sum](https://leetcode.com/problems/3sum)

[中文文档](/solution/0000-0099/0015.3Sum/README.md)

## Description

<p>Given an integer array nums, return all the triplets <code>[nums[i], nums[j], nums[k]]</code> such that <code>i != j</code>, <code>i != k</code>, and <code>j != k</code>, and <code>nums[i] + nums[j] + nums[k] == 0</code>.</p>

<p>Notice that the solution set must not contain duplicate triplets.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0,1,2,-1,-4]
<strong>Output:</strong> [[-1,-1,2],[-1,0,1]]
<strong>Explanation:</strong> 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> The only possible triplet does not sum up to 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> [[0,0,0]]
<strong>Explanation:</strong> The only possible triplet sums up to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
