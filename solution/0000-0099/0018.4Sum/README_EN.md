# [18. 4Sum](https://leetcode.com/problems/4sum)

[中文文档](/solution/0000-0099/0018.4Sum/README.md)

## Description

<p>Given an array <code>nums</code> of <code>n</code> integers, return <em>an array of all the <strong>unique</strong> quadruplets</em> <code>[nums[a], nums[b], nums[c], nums[d]]</code> such that:</p>

<ul>
	<li><code>0 &lt;= a, b, c, d&nbsp;&lt; n</code></li>
	<li><code>a</code>, <code>b</code>, <code>c</code>, and <code>d</code> are <strong>distinct</strong>.</li>
	<li><code>nums[a] + nums[b] + nums[c] + nums[d] == target</code></li>
</ul>

<p>You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,-1,0,-2,2], target = 0
<strong>Output:</strong> [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2], target = 8
<strong>Output:</strong> [[2,2,2,2]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**Solution 1: Two Pointers**

Time complexity $O(n^3)$, Space complexity $O(\log n)$.

<!-- tabs:start -->

### **Python3**

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
	ans, n := [][]int{}, len(nums)
	sort.Ints(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for l, r := j+1, n-1; l < r; {
				if nums[i]+nums[j]+nums[l]+nums[r] == target {
					ans = append(ans, []int{nums[i], nums[j], nums[l], nums[r]})
					l, r = l+1, r-1
					for l < r && nums[l] == nums[l-1] {
						l++
					}
					for l < r && nums[r] == nums[r+1] {
						r--
					}
				} else if nums[i]+nums[j]+nums[l]+nums[r] < target {
					l++
				} else {
					r--
				}
			}
			for j+1 < n && nums[j+1] == nums[j] {
				j++
			}
		}
		for i+1 < n && nums[i+1] == nums[i] {
			i++
		}
	}
	return ans
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
