# [剑指 Offer II 079. 所有子集](https://leetcode.cn/problems/TVdhkn)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 78&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/subsets/">https://leetcode.cn/problems/subsets/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

回溯法的基本模板：

```py
res = []
path = []

def backtrack(未探索区域, res, path):
    if path 满足条件:
        res.add(path) # 深度拷贝
        # return  # 如果不用继续搜索需要 return
    for 选择 in 未探索区域当前可能的选择:
        if 当前选择符合要求:
            path.add(当前选择)
            backtrack(新的未探索区域, res, path)
            path.pop()
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(i, n, t):
            res.append(t.copy())
            if i == n:
                return
            for j in range(i, n):
                t.append(nums[j])
                dfs(j + 1, n, t)
                t.pop()

        dfs(0, len(nums), [])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int[] nums, List<Integer> t, List<List<Integer>> res) {
        res.add(new ArrayList<>(t));
        if (i == nums.length) {
            return;
        }
        for (int j = i; j < nums.length; ++j) {
            t.add(nums[j]);
            dfs(j + 1, nums, t, res);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(0, nums, t, res);
        return res;
    }

    void dfs(int i, vector<int>& nums, vector<int> t, vector<vector<int>>& res) {
        res.push_back(t);
        if (i == nums.size()) return;
        for (int j = i; j < nums.size(); ++j) {
            t.push_back(nums[j]);
            dfs(j + 1, nums, t, res);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func subsets(nums []int) [][]int {
	var res [][]int
	var t []int
	dfs(0, nums, t, &res)
	return res
}

func dfs(i int, nums, t []int, res *[][]int) {
	cp := make([]int, len(t))
	copy(cp, t)
	*res = append(*res, cp)
	if i == len(nums) {
		return
	}
	for j := i; j < len(nums); j++ {
		t = append(t, nums[j])
		dfs(j+1, nums, t, res)
		t = t[:len(t)-1]
	}
}
```

### **TypeScipt**

```ts
function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const ans = [];
    const dfs = (i: number, t: number[]) => {
        ans.push([...t]);
        while (i < n) {
            t.push(nums[i++]);
            dfs(i, t);
            t.pop();
        }
    };
    dfs(0, []);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(mut i: usize, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        ans.push(t.clone());
        while i < nums.len() {
            t.push(nums[i]);
            i += 1;
            Self::dfs(i, t, ans, nums);
            t.pop();
        }
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        let mut t = Vec::new();
        Self::dfs(0, &mut t, &mut ans, &nums);
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
