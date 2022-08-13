# [90. 子集 II](https://leetcode.cn/problems/subsets-ii)

[English Version](/solution/0000-0099/0090.Subsets%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。返回的解集中，子集可以按 <strong>任意顺序</strong> 排列。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2]
<strong>输出：</strong>[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, t):
            ans.append(t[:])
            for i in range(u, len(nums)):
                if i != u and nums[i] == nums[i - 1]:
                    continue
                t.append(nums[i])
                dfs(i + 1, t)
                t.pop()

        ans = []
        nums.sort()
        dfs(0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;
    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        ans.add(new ArrayList<>(t));
        for (int i = u; i < nums.length; ++i) {
            if (i != u && nums[i] == nums[i - 1]) {
                continue;
            }
            t.add(nums[i]);
            dfs(i + 1, t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, t, nums, ans);
        return ans;
    }

    void dfs(int u, vector<int>& t, vector<int>& nums, vector<vector<int>>& ans) {
        ans.push_back(t);
        for (int i = u; i < nums.size(); ++i) {
            if (i != u && nums[i] == nums[i - 1]) continue;
            t.push_back(nums[i]);
            dfs(i + 1, t, nums, ans);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		ans = append(ans, append([]int(nil), t...))
		for i := u; i < len(nums); i++ {
			if i != u && nums[i] == nums[i-1] {
				continue
			}
			t = append(t, nums[i])
			dfs(i+1, t)
			t = t[:len(t)-1]
		}
	}
	var t []int
	dfs(0, t)
	return ans
}
```

### **TypeScript**

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        const num = t.pop();
        while (i < n && nums[i] == num) {
            i++;
        }
        dfs(i);
    };
    dfs(0);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(mut i: usize, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        let n = nums.len();
        if i == n {
            res.push(t.clone());
            return;
        }
        t.push(nums[i]);
        Self::dfs(i + 1, t, res, nums);
        let num = t.pop().unwrap();
        while i < n && num == nums[i] {
            i += 1;
        }
        Self::dfs(i, t, res, nums);
    }

    pub fn subsets_with_dup(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let mut res = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut res, &nums);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
