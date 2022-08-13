# [398. 随机数索引](https://leetcode.cn/problems/random-pick-index)

[English Version](/solution/0300-0399/0398.Random%20Pick%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个可能含有 <strong>重复元素</strong> 的整数数组&nbsp;<code>nums</code> ，请你随机输出给定的目标数字&nbsp;<code>target</code> 的索引。你可以假设给定的数字一定存在于数组中。</p>

<p>实现 <code>Solution</code> 类：</p>

<ul>
	<li><code>Solution(int[] nums)</code> 用数组 <code>nums</code> 初始化对象。</li>
	<li><code>int pick(int target)</code> 从 <code>nums</code> 中选出一个满足 <code>nums[i] == target</code> 的随机索引 <code>i</code> 。如果存在多个有效的索引，则每个索引的返回概率应当相等。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
<strong>输出</strong>
[null, 4, 0, 2]

<strong>解释</strong>
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。
solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
</pre>

<p>&nbsp;</p>

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>target</code> 是 <code>nums</code> 中的一个整数</li>
	<li>最多调用 <code>pick</code> 函数 <code>10<sup>4</sup></code> 次</li>
</ul>
</div>
</div>
</div>

<div class="fullscreen-btn-layer__2kn7">&nbsp;</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

蓄水池抽样问题。即从一个包含 n 个对象的列表 S 中随机选取 k 个对象，n 为一个非常大或者不知道的值。通常情况下，n 是一个非常大的值，大到无法一次性把所有列表 S 中的对象都放到内存中。我们这个问题是蓄水池抽样问题的一个特例，即 k=1。

**解法**：我们总是选择第一个对象，以 1/2 的概率选择第二个，以 1/3 的概率选择第三个，以此类推，以 1/m 的概率选择第 m 个对象。当该过程结束时，每一个对象具有相同的选中概率，即 1/n。

**证明**：第 m 个对象最终被选中的概率 P = `选择 m 的概率 × 其后面所有对象不被选择的概率`，即：

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0398.Random%20Pick%20Index/images/demo.gif"/>

思路同：[382. 链表随机节点](/solution/0300-0399/0382.Linked%20List%20Random%20Node/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def __init__(self, nums: List[int]):
        self.nums = nums

    def pick(self, target: int) -> int:
        n = ans = 0
        for i, v in enumerate(self.nums):
            if v == target:
                n += 1
                x = random.randint(1, n)
                if x == n:
                    ans = i
        return ans


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;
    private Random random = new Random();

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int n = 0, ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ++n;
                int x = 1 + random.nextInt(n);
                if (x == n) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nums;

    Solution(vector<int>& nums) {
        this->nums = nums;
    }

    int pick(int target) {
        int n = 0, ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == target) {
                ++n;
                int x = 1 + rand() % n;
                if (n == x) ans = i;
            }
        }
        return ans;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */
```

### **Go**

```go
type Solution struct {
	nums []int
}

func Constructor(nums []int) Solution {
	return Solution{nums}
}

func (this *Solution) Pick(target int) int {
	n, ans := 0, 0
	for i, v := range this.nums {
		if v == target {
			n++
			x := 1 + rand.Intn(n)
			if n == x {
				ans = i
			}
		}
	}
	return ans
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.Pick(target);
 */
```

### **...**

```

```

<!-- tabs:end -->
