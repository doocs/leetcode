---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0045.Jump%20Game%20II/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii)

[English Version](/solution/0000-0099/0045.Jump%20Game%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的 <strong>0 索引</strong>整数数组 <code>nums</code>。初始位置为 <code>nums[0]</code>。</p>

<p>每个元素 <code>nums[i]</code> 表示从索引 <code>i</code> 向后跳转的最大长度。换句话说，如果你在 <code>nums[i]</code> 处，你可以跳转到任意 <code>nums[i + j]</code> 处:</p>

<ul>
	<li><code>0 &lt;= j &lt;= nums[i]</code>&nbsp;</li>
	<li><code>i + j &lt; n</code></li>
</ul>

<p>返回到达&nbsp;<code>nums[n - 1]</code> 的最小跳跃次数。生成的测试用例可以到达 <code>nums[n - 1]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,1,1,4]
<strong>输出:</strong> 2
<strong>解释:</strong> 跳到最后一个位置的最小跳跃数是 <code>2</code>。
&nbsp;    从下标为 0 跳到下标为 1 的位置，跳&nbsp;<code>1</code>&nbsp;步，然后跳&nbsp;<code>3</code>&nbsp;步到达数组的最后一个位置。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,0,1,4]
<strong>输出:</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li>题目保证可以到达&nbsp;<code>nums[n-1]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以用变量 $mx$ 记录当前位置能够到达的最远位置，用变量 $last$ 记录上一次跳跃到的位置，用变量 $ans$ 记录跳跃的次数。

接下来，我们遍历 $[0,..n - 2]$ 的每一个位置 $i$，对于每一个位置 $i$，我们可以通过 $i + nums[i]$ 计算出当前位置能够到达的最远位置，我们用 $mx$ 来记录这个最远位置，即 $mx = max(mx, i + nums[i])$。接下来，判断当前位置是否到达了上一次跳跃的边界，即 $i = last$，如果到达了，那么我们就需要进行一次跳跃，将 $last$ 更新为 $mx$，并且将跳跃次数 $ans$ 增加 $1$。

最后，我们返回跳跃的次数 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

相似题目：

-   [55. 跳跃游戏](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0055.Jump%20Game/README.md)
-   [1024. 视频拼接](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1024.Video%20Stitching/README.md)
-   [1326. 灌溉花园的最少水龙头数目](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        ans = mx = last = 0
        for i, x in enumerate(nums[:-1]):
            mx = max(mx, i + x)
            if last == i:
                ans += 1
                last = mx
        return ans
```

#### Java

```java
class Solution {
    public int jump(int[] nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            mx = Math.max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
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
    int jump(vector<int>& nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            mx = max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func jump(nums []int) (ans int) {
	mx, last := 0, 0
	for i, x := range nums[:len(nums)-1] {
		mx = max(mx, i+x)
		if last == i {
			ans++
			last = mx
		}
	}
	return
}
```

#### TypeScript

```ts
function jump(nums: number[]): number {
    let [ans, mx, last] = [0, 0, 0];
    for (let i = 0; i < nums.length - 1; ++i) {
        mx = Math.max(mx, i + nums[i]);
        if (last === i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn jump(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        let mut last = 0;
        for i in 0..(nums.len() - 1) {
            mx = mx.max(i as i32 + nums[i]);
            if last == i as i32 {
                ans += 1;
                last = mx;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int Jump(int[] nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.Length - 1; ++i) {
            mx = Math.Max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
            }
        }
        return ans;
    }
}
```

#### C

```c
int jump(int* nums, int numsSize) {
    int ans = 0;
    int mx = 0;
    int last = 0;
    for (int i = 0; i < numsSize - 1; ++i) {
        mx = (mx > i + nums[i]) ? mx : (i + nums[i]);
        if (last == i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function jump($nums) {
        $ans = 0;
        $mx = 0;
        $last = 0;

        for ($i = 0; $i < count($nums) - 1; $i++) {
            $mx = max($mx, $i + $nums[$i]);
            if ($last == $i) {
                $ans++;
                $last = $mx;
            }
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
