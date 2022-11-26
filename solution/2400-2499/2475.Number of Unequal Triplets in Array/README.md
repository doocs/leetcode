# [2475. 数组中不等三元组的数目](https://leetcode.cn/problems/number-of-unequal-triplets-in-array)

[English Version](/solution/2400-2499/2475.Number%20of%20Unequal%20Triplets%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的正整数数组 <code>nums</code> 。请你找出并统计满足下述条件的三元组 <code>(i, j, k)</code> 的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; nums.length</code></li>
	<li><code>nums[i]</code>、<code>nums[j]</code> 和 <code>nums[k]</code> <strong>两两不同</strong> 。
	<ul>
		<li>换句话说：<code>nums[i] != nums[j]</code>、<code>nums[i] != nums[k]</code> 且 <code>nums[j] != nums[k]</code> 。</li>
	</ul>
	</li>
</ul>

<p>返回满足上述条件三元组的数目<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,4,2,4,3]
<strong>输出：</strong>3
<strong>解释：</strong>下面列出的三元组均满足题目条件：
- (0, 2, 4) 因为 4 != 2 != 3
- (1, 2, 4) 因为 4 != 2 != 3
- (2, 3, 4) 因为 2 != 4 != 3
共计 3 个三元组，返回 3 。
注意 (2, 0, 4) 不是有效的三元组，因为 2 &gt; 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1,1]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足条件的三元组，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

我们可以直接枚举所有的三元组 $(i, j, k)$，统计所有符合条件的数量。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：排序 + 枚举中间元素 + 二分查找**

我们可以先对数组 `nums` 进行排序。

然后遍历 `nums`，枚举中间元素 $nums[j]$，在 $nums[j]$ 左侧找到最近的下标 $i$，使得 $nums[i] \lt nums[j]$ 成立；在 $nums[j]$ 右侧找到最近的下标 $k$，使得 $nums[k] \gt nums[j]$ 成立。那么以 $nums[j]$ 作为中间元素，且符合条件的三元组数量为 $(i+1) \times (n - k)$，累加到答案中。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

**方法三：哈希表**

我们还可以使用哈希表 $cnt$ 来统计数组 `nums` 中每个元素的数量。

然后遍历哈希表 $cnt$，枚举中间元素的个数 $b$，左侧元素个数记为 $a$，那么右侧元素个数有 $n-a-b$，此时符合条件的三元组数量为 $a \times b \times c$，累加到答案中。接着更新 $a=a+b$，继续枚举中间元素的个数 $b$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    ans += nums[i] != nums[j] and nums[j] != nums[k] and nums[i] != nums[k]
        return ans
```

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for j in range(1, n - 1):
            i = bisect_left(nums, nums[j], hi=j) - 1
            k = bisect_right(nums, nums[j], lo=j+1)
            ans += (i >= 0 and k < n) * (i + 1) * (n - k)
        return ans
```

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        n = len(nums)
        ans = a = 0
        for b in cnt.values():
            c = n - a - b
            ans += a * b * c
            a += b
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n  = nums.length;
        for (int j = 1; j < n - 1; ++j) {
            int i = search(nums, nums[j], 0, j) - 1;
            int k = search(nums, nums[j] + 1, j + 1, n);
            if (i >= 0 && k < n) {
                ans += (i + 1) * (n - k);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        int ans = 0, a = 0;
        int n = nums.length;
        for (int b : cnt.values()) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, n = nums.size();
        for (int j = 1; j < n - 1; ++j) {
            int i = lower_bound(nums.begin(), nums.begin() + j, nums[j]) - nums.begin() - 1;
            int k = upper_bound(nums.begin() + j + 1, nums.end(), nums[j]) - nums.begin();
            if (i >= 0 && k < n) {
                ans += (i + 1) * (n - k);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int& v : nums) ++cnt[v];
        int ans = 0, a = 0;
        int n = nums.size();
        for (auto& [_, b] : cnt) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;
    }
};
```

### **Go**

```go
func unequalTriplets(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k] {
					ans++
				}
			}
		}
	}
	return
}
```

```go
func unequalTriplets(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for j := 1; j < n-1; j++ {
		i := sort.Search(j, func(h int) bool { return nums[h] >= nums[j] }) - 1
		k := sort.Search(n, func(h int) bool { return h > j && nums[h] > nums[j] })
		if i >= 0 && k < n {
			ans += (i + 1) * (n - k)
		}
	}
	return
}
```

```go
func unequalTriplets(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	a, n := 0, len(nums)
	for _, b := range cnt {
		c := n - a - b
		ans += a * b * c
		a += b
	}
	return
}
```

### **TypeScript**

```ts
function unequalTriplets(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            for (let k = j + 1; k < n; k++) {
                if (
                    nums[i] !== nums[j] &&
                    nums[j] !== nums[k] &&
                    nums[i] !== nums[k]
                ) {
                    ans++;
                }
            }
        }
    }
    return ans;
}
```

```ts
function unequalTriplets(nums: number[]): number {
    const n = nums.length;
    const cnt = new Map<number, number>();
    for (const num of nums) {
        cnt.set(num, (cnt.get(num) ?? 0) + 1);
    }
    let ans = 0;
    let a = 0;
    for (const b of cnt.values()) {
        const c = n - a - b;
        ans += a * b * c;
        a += b;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        for i in 0..n - 2 {
            for j in i + 1..n - 1 {
                for k in j + 1..n {
                    if nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k] {
                        ans += 1;
                    }
                }
            }
        }
        ans
    }
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for num in nums.iter() {
            *cnt.entry(num).or_insert(0) += 1;
        }
        let n = nums.len();
        let mut ans = 0;
        let mut a = 0;
        for v in cnt.values() {
            let b = n - a - v;
            ans += v * a * b;;
            a += v;
        }
        ans as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
