---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.04.Power%20Set/README_EN.md
---

# [08.04. Power Set](https://leetcode.cn/problems/power-set-lcci)

[中文文档](/lcci/08.04.Power%20Set/README.md)

## Description

<p>Write a method to return all subsets of a set. The elements in a set are&nbsp;pairwise distinct.</p>

<p>Note: The result set should not contain duplicated subsets.</p>

<p><strong>Example:</strong></p>

<pre>

<strong> Input</strong>:  nums = [1,2,3]

<strong> Output</strong>: 

[

  [3],

&nbsp; [1],

&nbsp; [2],

&nbsp; [1,2,3],

&nbsp; [1,3],

&nbsp; [2,3],

&nbsp; [1,2],

&nbsp; []

]

</pre>

## Solutions

### Solution 1: Recursive Enumeration

We design a recursive function $dfs(u, t)$, where $u$ is the index of the current element being enumerated, and $t$ is the current subset.

For the current element with index $u$, we can choose to add it to the subset $t$, or we can choose not to add it to the subset $t$. Recursively making these two choices will yield all subsets.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array. Each element in the array has two states, namely chosen or not chosen, for a total of $2^n$ states. Each state requires $O(n)$ time to construct the subset.

<!-- tabs:start -->

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

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
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
        if (u == nums.size()) {
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

```ts
function subsets(nums: number[]): number[][] {
    const res = [[]];
    nums.forEach(num => {
        res.forEach(item => {
            res.push(item.concat(num));
        });
    });
    return res;
}
```

```rust
impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut res: Vec<Vec<i32>> = vec![vec![]];
        for i in 0..n {
            for j in 0..res.len() {
                res.push(vec![..res[j].clone(), vec![nums[i]]].concat());
            }
        }
        res
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function (nums) {
    let prev = [];
    let res = [];
    dfs(nums, 0, prev, res);
    return res;
};

function dfs(nums, depth, prev, res) {
    res.push(prev.slice());
    for (let i = depth; i < nums.length; i++) {
        prev.push(nums[i]);
        depth++;
        dfs(nums, depth, prev, res);
        prev.pop();
    }
}
```

```swift
class Solution {
    private var ans = [[Int]]()
    private var nums: [Int] = []

    func subsets(_ nums: [Int]) -> [[Int]] {
        self.nums = nums
        dfs(0, [])
        return ans.sorted { $0.count < $1.count }
    }

    private func dfs(_ u: Int, _ t: [Int]) {
        if u == nums.count {
            ans.append(t)
            return
        }
        dfs(u + 1, t)
        var tWithCurrent = t
        tWithCurrent.append(nums[u])
        dfs(u + 1, tWithCurrent)
    }
}
```

<!-- tabs:end -->

### Solution 2: Binary Enumeration

We can rewrite the recursive process in Method 1 into an iterative form, that is, using binary enumeration to enumerate all subsets.

We can use $2^n$ binary numbers to represent all subsets of $n$ elements. If the $i$-th bit of a binary number `mask` is $1$, it means that the subset contains the $i$-th element $v$ of the array; if it is $0$, it means that the subset does not contain the $i$-th element $v$ of the array.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array. There are a total of $2^n$ subsets, and each subset requires $O(n)$ time to construct.

<!-- tabs:start -->

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

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        for (int mask = 0; mask < 1 << n; ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) & 1) {
                    t.push_back(nums[i]);
                }
            }
            ans.push_back(t);
        }
        return ans;
    }
};
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

```ts
function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const res = [];
    const list = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...list]);
            return;
        }
        list.push(nums[i]);
        dfs(i + 1);
        list.pop();
        dfs(i + 1);
    };
    dfs(0);
    return res;
}
```

```rust
impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, res: &mut Vec<Vec<i32>>, list: &mut Vec<i32>) {
        if i == nums.len() {
            res.push(list.clone());
            return;
        }
        list.push(nums[i]);
        Self::dfs(nums, i + 1, res, list);
        list.pop();
        Self::dfs(nums, i + 1, res, list);
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(&nums, 0, &mut res, &mut vec![]);
        res
    }
}
```

<!-- tabs:end -->

<!-- end -->
