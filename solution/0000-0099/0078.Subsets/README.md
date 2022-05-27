# [78. 子集](https://leetcode.cn/problems/subsets)

[English Version](/solution/0000-0099/0078.Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[[],[0]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10</code></li>
	<li><code>-10 <= nums[i] <= 10</code></li>
	<li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：回溯**

**方法二：二进制枚举**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, t):
            if u == len(nums):
                ans.append(t[:])
                return
            dfs(u + 1, t)
            t.append(nums[u])
            dfs(u + 1, t)
            t.pop()

        ans = []
        dfs(0, [])
        return ans
```

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        ans = []
        for mask in range(1 << len(nums)):
            t = []
            for i, v in enumerate(nums):
                if (mask >> i) & 1:
                    t.append(v)
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        if (u == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(u + 1, t);
        t.add(nums[u]);
        dfs(u + 1, t);
        t.remove(t.size() - 1);
    }
}
```

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t.add(nums[i]);
                }
            }
            ans.add(t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, nums, t, ans);
        return ans;
    }

    void dfs(int u, vector<int>& nums, vector<int>& t, vector<vector<int>>& ans) {
        if (u == nums.size())
        {
            ans.push_back(t);
            return;
        }
        dfs(u + 1, nums, t, ans);
        t.push_back(nums[u]);
        dfs(u + 1, nums, t, ans);
        t.pop_back();
    }
};
```

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        for (int mask = 0; mask < 1 << n; ++mask)
        {
            t.clear();
            for (int i = 0; i < n; ++i)
            {
                if ((mask >> i) & 1)
                {
                    t.push_back(nums[i]);
                }
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func subsets(nums []int) [][]int {
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		if u == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(u+1, t)
		t = append(t, nums[u])
		dfs(u+1, t)
		t = t[:len(t)-1]
	}
	var t []int
	dfs(0, t)
	return ans
}
```

```go
func subsets(nums []int) [][]int {
	var ans [][]int
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		for i, v := range nums {
			if ((mask >> i) & 1) == 1 {
				t = append(t, v)
			}
		}
		ans = append(ans, t)
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(nums: &Vec<i32>, res: &mut Vec<Vec<i32>>, i: usize, base: &mut Vec<i32>) {
        let n = nums.len();
        if i == n {
            return;
        }
        for j in i..n {
            base.push(nums[j]);
            res.push(base.clone());
            Self::dfs(nums, res, j + 1, base);
            base.pop();
        }
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut base = vec![];
        let mut res = vec![vec![]];
        Self::dfs(&nums, &mut res, 0, &mut base);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
