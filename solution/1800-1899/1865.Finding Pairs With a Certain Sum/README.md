---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1865.Finding%20Pairs%20With%20a%20Certain%20Sum/README.md
rating: 1680
source: 第 241 场周赛 Q3
tags:
    - 设计
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1865. 找出和为指定值的下标对](https://leetcode.cn/problems/finding-pairs-with-a-certain-sum)

[English Version](/solution/1800-1899/1865.Finding%20Pairs%20With%20a%20Certain%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code> ，请你实现一个支持下述两类查询的数据结构：</p>

<ol>
	<li><strong>累加</strong> ，将一个正整数加到 <code>nums2</code> 中指定下标对应元素上。</li>
	<li><strong>计数 </strong>，统计满足 <code>nums1[i] + nums2[j]</code> 等于指定值的下标对 <code>(i, j)</code> 数目（<code>0 <= i < nums1.length</code> 且 <code>0 <= j < nums2.length</code>）。</li>
</ol>

<p>实现 <code>FindSumPairs</code> 类：</p>

<ul>
	<li><code>FindSumPairs(int[] nums1, int[] nums2)</code> 使用整数数组 <code>nums1</code> 和 <code>nums2</code> 初始化 <code>FindSumPairs</code> 对象。</li>
	<li><code>void add(int index, int val)</code> 将 <code>val</code> 加到 <code>nums2[index]</code> 上，即，执行 <code>nums2[index] += val</code> 。</li>
	<li><code>int count(int tot)</code> 返回满足 <code>nums1[i] + nums2[j] == tot</code> 的下标对 <code>(i, j)</code> 数目。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
[[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
<strong>输出：</strong>
[null, 8, null, 2, 1, null, null, 11]

<strong>解释：</strong>
FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
findSumPairs.count(7);  // 返回 8 ; 下标对 (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) 满足 2 + 5 = 7 ，下标对 (5,1), (5,5) 满足 3 + 4 = 7
findSumPairs.add(3, 2); // 此时 nums2 = [1,4,5,<em><strong>4</strong></em><code>,5,4</code>]
findSumPairs.count(8);  // 返回 2 ；下标对 (5,2), (5,4) 满足 3 + 5 = 8
findSumPairs.count(4);  // 返回 1 ；下标对 (5,0) 满足 3 + 1 = 4
findSumPairs.add(0, 1); // 此时 nums2 = [<em><strong><code>2</code></strong></em>,4,5,4<code>,5,4</code>]
findSumPairs.add(1, 1); // 此时 nums2 = [<code>2</code>,<em><strong>5</strong></em>,5,4<code>,5,4</code>]
findSumPairs.count(7);  // 返回 11 ；下标对 (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) 满足 2 + 5 = 7 ，下标对 (5,3), (5,5) 满足 3 + 4 = 7
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums1.length <= 1000</code></li>
	<li><code>1 <= nums2.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums1[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= nums2[i] <= 10<sup>5</sup></code></li>
	<li><code>0 <= index < nums2.length</code></li>
	<li><code>1 <= val <= 10<sup>5</sup></code></li>
	<li><code>1 <= tot <= 10<sup>9</sup></code></li>
	<li>最多调用 <code>add</code> 和 <code>count</code> 函数各 <code>1000</code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们注意到，数组 $\textit{nums1}$ 的长度不超过 ${10}^3$，数组 $\textit{nums2}$ 的长度达到 ${10}^5$，因此，如果直接暴力枚举所有下标对 $(i, j)$，计算 $\textit{nums1}[i] + \textit{nums2}[j]$ 是否等于指定值 $\textit{tot}$，那么会超出时间限制。

能否只枚举长度较短的数组 $\textit{nums1}$ 呢？答案是可以的。我们用一个哈希表 $\textit{cnt}$ 统计数组 $\textit{nums2}$ 中每个元素出现的次数，然后枚举数组 $\textit{nums1}$ 中的每个元素 $x$，计算 $\textit{cnt}[\textit{tot} - x]$ 的值之和即可。

在调用 $\text{add}$ 方法时，我们需要先将 $\textit{nums2}[index]$ 对应的值从 $\textit{cnt}$ 中减去 $1$，然后将 $\textit{nums2}[index]$ 的值加上 $\textit{val}$，最后将 $\textit{nums2}[index]$ 对应的值加上 $1$。

在调用 $\text{count}$ 方法时，我们只需要遍历数组 $\textit{nums1}$，对于每个元素 $x$，计算 $\textit{cnt}[\textit{tot} - x]$ 的值之和即可。

时间复杂度 $O(n \times q)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别是数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度，而 $q$ 是调用 $\text{count}$ 方法的次数。

<!-- tabs:start -->

#### Python3

```python
class FindSumPairs:

    def __init__(self, nums1: List[int], nums2: List[int]):
        self.cnt = Counter(nums2)
        self.nums1 = nums1
        self.nums2 = nums2

    def add(self, index: int, val: int) -> None:
        self.cnt[self.nums2[index]] -= 1
        self.nums2[index] += val
        self.cnt[self.nums2[index]] += 1

    def count(self, tot: int) -> int:
        return sum(self.cnt[tot - x] for x in self.nums1)


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)
```

#### Java

```java
class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> cnt = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int x : nums2) {
            cnt.merge(x, 1, Integer::sum);
        }
    }

    public void add(int index, int val) {
        cnt.merge(nums2[index], -1, Integer::sum);
        nums2[index] += val;
        cnt.merge(nums2[index], 1, Integer::sum);
    }

    public int count(int tot) {
        int ans = 0;
        for (int x : nums1) {
            ans += cnt.getOrDefault(tot - x, 0);
        }
        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
```

#### C++

```cpp
class FindSumPairs {
public:
    FindSumPairs(vector<int>& nums1, vector<int>& nums2) {
        this->nums1 = nums1;
        this->nums2 = nums2;
        for (int x : nums2) {
            ++cnt[x];
        }
    }

    void add(int index, int val) {
        --cnt[nums2[index]];
        nums2[index] += val;
        ++cnt[nums2[index]];
    }

    int count(int tot) {
        int ans = 0;
        for (int x : nums1) {
            ans += cnt[tot - x];
        }
        return ans;
    }

private:
    vector<int> nums1;
    vector<int> nums2;
    unordered_map<int, int> cnt;
};

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs* obj = new FindSumPairs(nums1, nums2);
 * obj->add(index,val);
 * int param_2 = obj->count(tot);
 */
```

#### Go

```go
type FindSumPairs struct {
	nums1 []int
	nums2 []int
	cnt   map[int]int
}

func Constructor(nums1 []int, nums2 []int) FindSumPairs {
	cnt := map[int]int{}
	for _, x := range nums2 {
		cnt[x]++
	}
	return FindSumPairs{nums1, nums2, cnt}
}

func (this *FindSumPairs) Add(index int, val int) {
	this.cnt[this.nums2[index]]--
	this.nums2[index] += val
	this.cnt[this.nums2[index]]++
}

func (this *FindSumPairs) Count(tot int) (ans int) {
	for _, x := range this.nums1 {
		ans += this.cnt[tot-x]
	}
	return
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * obj := Constructor(nums1, nums2);
 * obj.Add(index,val);
 * param_2 := obj.Count(tot);
 */
```

#### TypeScript

```ts
class FindSumPairs {
    private nums1: number[];
    private nums2: number[];
    private cnt: Map<number, number>;

    constructor(nums1: number[], nums2: number[]) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.cnt = new Map();
        for (const x of nums2) {
            this.cnt.set(x, (this.cnt.get(x) || 0) + 1);
        }
    }

    add(index: number, val: number): void {
        const old = this.nums2[index];
        this.cnt.set(old, this.cnt.get(old)! - 1);
        this.nums2[index] += val;
        const now = this.nums2[index];
        this.cnt.set(now, (this.cnt.get(now) || 0) + 1);
    }

    count(tot: number): number {
        return this.nums1.reduce((acc, x) => acc + (this.cnt.get(tot - x) || 0), 0);
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * var obj = new FindSumPairs(nums1, nums2)
 * obj.add(index,val)
 * var param_2 = obj.count(tot)
 */
```

#### Rust

```rust
use std::collections::HashMap;

struct FindSumPairs {
    nums1: Vec<i32>,
    nums2: Vec<i32>,
    cnt: HashMap<i32, i32>,
}

impl FindSumPairs {
    fn new(nums1: Vec<i32>, nums2: Vec<i32>) -> Self {
        let mut cnt = HashMap::new();
        for &x in &nums2 {
            *cnt.entry(x).or_insert(0) += 1;
        }
        Self { nums1, nums2, cnt }
    }

    fn add(&mut self, index: i32, val: i32) {
        let i = index as usize;
        let old_val = self.nums2[i];
        *self.cnt.entry(old_val).or_insert(0) -= 1;
        if self.cnt[&old_val] == 0 {
            self.cnt.remove(&old_val);
        }

        self.nums2[i] += val;
        let new_val = self.nums2[i];
        *self.cnt.entry(new_val).or_insert(0) += 1;
    }

    fn count(&self, tot: i32) -> i32 {
        let mut ans = 0;
        for &x in &self.nums1 {
            let target = tot - x;
            if let Some(&c) = self.cnt.get(&target) {
                ans += c;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 */
var FindSumPairs = function (nums1, nums2) {
    this.nums1 = nums1;
    this.nums2 = nums2;
    this.cnt = new Map();
    for (const x of nums2) {
        this.cnt.set(x, (this.cnt.get(x) || 0) + 1);
    }
};

/**
 * @param {number} index
 * @param {number} val
 * @return {void}
 */
FindSumPairs.prototype.add = function (index, val) {
    const old = this.nums2[index];
    this.cnt.set(old, this.cnt.get(old) - 1);
    this.nums2[index] += val;
    const now = this.nums2[index];
    this.cnt.set(now, (this.cnt.get(now) || 0) + 1);
};

/**
 * @param {number} tot
 * @return {number}
 */
FindSumPairs.prototype.count = function (tot) {
    return this.nums1.reduce((acc, x) => acc + (this.cnt.get(tot - x) || 0), 0);
};

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * var obj = new FindSumPairs(nums1, nums2)
 * obj.add(index,val)
 * var param_2 = obj.count(tot)
 */
```

#### C#

```cs
public class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Dictionary<int, int> cnt = new Dictionary<int, int>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        foreach (int x in nums2) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
        }
    }

    public void Add(int index, int val) {
        int oldVal = nums2[index];
        if (cnt.TryGetValue(oldVal, out int oldCount)) {
            if (oldCount == 1) {
                cnt.Remove(oldVal);
            } else {
                cnt[oldVal] = oldCount - 1;
            }
        }
        nums2[index] += val;
        int newVal = nums2[index];
        if (cnt.TryGetValue(newVal, out int newCount)) {
            cnt[newVal] = newCount + 1;
        } else {
            cnt[newVal] = 1;
        }
    }

    public int Count(int tot) {
        int ans = 0;
        foreach (int x in nums1) {
            int target = tot - x;
            if (cnt.TryGetValue(target, out int count)) {
                ans += count;
            }
        }
        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.Add(index,val);
 * int param_2 = obj.Count(tot);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
