---
comments: true
difficulty: 简单
rating: 1264
source: 第 290 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
    - 排序
---

<!-- problem:start -->

# [2248. 多个数组求交集](https://leetcode.cn/problems/intersection-of-multiple-arrays)

[English Version](/solution/2200-2299/2248.Intersection%20of%20Multiple%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>nums</code> ，其中 <code>nums[i]</code> 是由 <strong>不同</strong> 正整数组成的一个非空数组，按 <strong>升序排列</strong> 返回一个数组，数组中的每个元素在 <code>nums</code>&nbsp;<strong>所有数组</strong> 中都出现过。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[<em><strong>3</strong></em>,1,2,<em><strong>4</strong></em>,5],[1,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>],[<em><strong>3</strong></em>,<em><strong>4</strong></em>,5,6]]
<strong>输出：</strong>[3,4]
<strong>解释：</strong>
nums[0] = [<em><strong>3</strong></em>,1,2,<em><strong>4</strong></em>,5]，nums[1] = [1,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>]，nums[2] = [<em><strong>3</strong></em>,<em><strong>4</strong></em>,5,6]，在 nums 中每个数组中都出现的数字是 3 和 4 ，所以返回 [3,4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[4,5,6]]
<strong>输出：</strong>[]
<strong>解释：</strong>
不存在同时出现在 nums[0] 和 nums[1] 的整数，所以返回一个空列表 [] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(nums[i].length) &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i][j] &lt;= 1000</code></li>
	<li><code>nums[i]</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

<!-- thinking:start -->

> **思考**
>
> 求同时出现在每一个子数组中的数，并升序输出。各子数组内部元素互异，值域为 $[1,1000]$。逐对做集合交也可以，但多次分配集合没有必要。
>
> 开长度为 $1001$ 的计数数组，每在一个子数组中见到 $x$ 就给 $cnt[x]$ 加一。最后 $cnt[x]$ 等于子数组个数的那些 $x$ 即为交集，按下标输出已有序。

<!-- thinking:end -->

遍历数组 `nums`，对于每个数组 `arr`，统计数组 `arr` 中每个数字出现的次数，然后遍历计数数组，统计出现次数等于数组 `nums` 的长度的数字，即为答案。

时间复杂度 $O(N)$，空间复杂度 $O(1000)$。其中 $N$ 为数组 `nums` 中数字的总数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        cnt = [0] * 1001
        for arr in nums:
            for x in arr:
                cnt[x] += 1
        return [x for x, v in enumerate(cnt) if v == len(nums)]
```

#### Java

```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] cnt = new int[1001];
        for (var arr : nums) {
            for (int x : arr) {
                ++cnt[x];
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = 0; x < 1001; ++x) {
            if (cnt[x] == nums.length) {
                ans.add(x);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        int cnt[1001]{};
        for (auto& arr : nums) {
            for (int& x : arr) {
                ++cnt[x];
            }
        }
        vector<int> ans;
        for (int x = 0; x < 1001; ++x) {
            if (cnt[x] == nums.size()) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intersection(nums [][]int) (ans []int) {
	cnt := [1001]int{}
	for _, arr := range nums {
		for _, x := range arr {
			cnt[x]++
		}
	}
	for x, v := range cnt {
		if v == len(nums) {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function intersection(nums: number[][]): number[] {
    const cnt = new Array(1001).fill(0);
    for (const arr of nums) {
        for (const x of arr) {
            cnt[x]++;
        }
    }
    const ans: number[] = [];
    for (let x = 0; x < 1001; x++) {
        if (cnt[x] === nums.length) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[][] $nums
     * @return Integer[]
     */
    function intersection($nums) {
        $rs = [];
        for ($i = 0; $i < count($nums); $i++) {
            for ($j = 0; $j < count($nums[$i]); $j++) {
                $hashtable[$nums[$i][$j]] += 1;
                if ($hashtable[$nums[$i][$j]] === count($nums)) {
                    array_push($rs, $nums[$i][$j]);
                }
            }
        }
        sort($rs);
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一扫完再收集答案。计数过程中一旦 $cnt[x]$ 达到子数组个数，就可以立刻把 $x$ 放入答案，少一次整表扫描。最后再排序即可。值域不大，两种写法时间同阶。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        cnt = Counter()
        ans = []
        for arr in nums:
            for x in arr:
                cnt[x] += 1
                if cnt[x] == len(nums):
                    ans.append(x)
        ans.sort()
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (var arr : nums) {
            for (int x : arr) {
                if (cnt.merge(x, 1, Integer::sum) == nums.length) {
                    ans.add(x);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        unordered_map<int, int> cnt;
        vector<int> ans;
        for (auto& arr : nums) {
            for (int& x : arr) {
                if (++cnt[x] == nums.size()) {
                    ans.push_back(x);
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func intersection(nums [][]int) (ans []int) {
	cnt := map[int]int{}
	for _, arr := range nums {
		for _, x := range arr {
			cnt[x]++
			if cnt[x] == len(nums) {
				ans = append(ans, x)
			}
		}
	}
	sort.Ints(ans)
	return
}
```

#### TypeScript

```ts
function intersection(nums: number[][]): number[] {
    const cnt = new Array(1001).fill(0);
    const ans: number[] = [];
    for (const arr of nums) {
        for (const x of arr) {
            if (++cnt[x] == nums.length) {
                ans.push(x);
            }
        }
    }
    ans.sort((a, b) => a - b);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
